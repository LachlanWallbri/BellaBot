package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.BoundedArchiveInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.BoundedSeekableByteChannelInputStream;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;

/* loaded from: classes8.dex */
public class TarFile implements Closeable {
    private static final int SMALL_BUFFER_SIZE = 256;
    private final SeekableByteChannel archive;
    private final int blockSize;
    private TarArchiveEntry currEntry;
    private final LinkedList<TarArchiveEntry> entries;
    private Map<String, String> globalPaxHeaders;
    private final List<TarArchiveStructSparse> globalSparseHeaders;
    private boolean hasHitEOF;
    private final boolean lenient;
    private final ByteBuffer recordBuffer;
    private final int recordSize;
    private final byte[] smallBuf;
    private final Map<String, List<InputStream>> sparseInputStreams;
    private final ZipEncoding zipEncoding;

    public TarFile(byte[] bArr) throws IOException {
        this(new SeekableInMemoryByteChannel(bArr));
    }

    public TarFile(byte[] bArr, String str) throws IOException {
        this(new SeekableInMemoryByteChannel(bArr), TarConstants.DEFAULT_BLKSIZE, 512, str, false);
    }

    public TarFile(byte[] bArr, boolean z) throws IOException {
        this(new SeekableInMemoryByteChannel(bArr), TarConstants.DEFAULT_BLKSIZE, 512, null, z);
    }

    public TarFile(File file) throws IOException {
        this(file.toPath());
    }

    public TarFile(File file, String str) throws IOException {
        this(file.toPath(), str);
    }

    public TarFile(File file, boolean z) throws IOException {
        this(file.toPath(), z);
    }

    public TarFile(Path path) throws IOException {
        this(Files.newByteChannel(path, new OpenOption[0]), TarConstants.DEFAULT_BLKSIZE, 512, null, false);
    }

    public TarFile(Path path, String str) throws IOException {
        this(Files.newByteChannel(path, new OpenOption[0]), TarConstants.DEFAULT_BLKSIZE, 512, str, false);
    }

    public TarFile(Path path, boolean z) throws IOException {
        this(Files.newByteChannel(path, new OpenOption[0]), TarConstants.DEFAULT_BLKSIZE, 512, null, z);
    }

    public TarFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, TarConstants.DEFAULT_BLKSIZE, 512, null, false);
    }

    public TarFile(SeekableByteChannel seekableByteChannel, int i, int i2, String str, boolean z) throws IOException {
        this.smallBuf = new byte[256];
        this.entries = new LinkedList<>();
        this.globalSparseHeaders = new ArrayList();
        this.globalPaxHeaders = new HashMap();
        this.sparseInputStreams = new HashMap();
        this.archive = seekableByteChannel;
        this.hasHitEOF = false;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.recordSize = i2;
        this.recordBuffer = ByteBuffer.allocate(this.recordSize);
        this.blockSize = i;
        this.lenient = z;
        while (true) {
            TarArchiveEntry nextTarEntry = getNextTarEntry();
            if (nextTarEntry == null) {
                return;
            } else {
                this.entries.add(nextTarEntry);
            }
        }
    }

    private TarArchiveEntry getNextTarEntry() throws IOException {
        if (isAtEOF()) {
            return null;
        }
        TarArchiveEntry tarArchiveEntry = this.currEntry;
        if (tarArchiveEntry != null) {
            repositionForwardTo(tarArchiveEntry.getDataOffset() + this.currEntry.getSize());
            throwExceptionIfPositionIsNotInArchive();
            skipRecordPadding();
        }
        ByteBuffer record = getRecord();
        if (record == null) {
            this.currEntry = null;
            return null;
        }
        try {
            this.currEntry = new TarArchiveEntry(record.array(), this.zipEncoding, this.lenient, this.archive.position());
            if (this.currEntry.isGNULongLinkEntry()) {
                byte[] longNameData = getLongNameData();
                if (longNameData == null) {
                    return null;
                }
                this.currEntry.setLinkName(this.zipEncoding.decode(longNameData));
            }
            if (this.currEntry.isGNULongNameEntry()) {
                byte[] longNameData2 = getLongNameData();
                if (longNameData2 == null) {
                    return null;
                }
                String decode = this.zipEncoding.decode(longNameData2);
                this.currEntry.setName(decode);
                if (this.currEntry.isDirectory() && !decode.endsWith("/")) {
                    this.currEntry.setName(decode + "/");
                }
            }
            if (this.currEntry.isGlobalPaxHeader()) {
                readGlobalPaxHeaders();
            }
            try {
                if (this.currEntry.isPaxHeader()) {
                    paxHeaders();
                } else if (!this.globalPaxHeaders.isEmpty()) {
                    applyPaxHeadersToCurrentEntry(this.globalPaxHeaders, this.globalSparseHeaders);
                }
                if (this.currEntry.isOldGNUSparse()) {
                    readOldGNUSparse();
                }
                return this.currEntry;
            } catch (NumberFormatException e) {
                throw new IOException("Error detected parsing the pax header", e);
            }
        } catch (IllegalArgumentException e2) {
            throw new IOException("Error detected parsing the header", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003f, code lost:
    
        throw new java.io.IOException("premature end of tar archive. Didn't find extended_header after header with extended flag.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0040, code lost:
    
        buildSparseInputStreams();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0043, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0006, code lost:
    
        if (r6.currEntry.isExtended() != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0008, code lost:
    
        r0 = getRecord();
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r0 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        r1 = new org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry(r0.array());
        r6.currEntry.getSparseHeaders().addAll(r1.getSparseHeaders());
        r0 = r6.currEntry;
        r0.setDataOffset(r0.getDataOffset() + r6.recordSize);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0035, code lost:
    
        if (r1.isExtended() != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void readOldGNUSparse() throws IOException {
    }

    private void buildSparseInputStreams() throws IOException {
        ArrayList arrayList = new ArrayList();
        List<TarArchiveStructSparse> orderedSparseHeaders = this.currEntry.getOrderedSparseHeaders();
        TarArchiveSparseZeroInputStream tarArchiveSparseZeroInputStream = new TarArchiveSparseZeroInputStream();
        long j = 0;
        long j2 = 0;
        for (TarArchiveStructSparse tarArchiveStructSparse : orderedSparseHeaders) {
            long offset = tarArchiveStructSparse.getOffset() - j;
            if (offset < 0) {
                throw new IOException("Corrupted struct sparse detected");
            }
            if (offset > 0) {
                arrayList.add(new BoundedInputStream(tarArchiveSparseZeroInputStream, offset));
                j2 += offset;
            }
            if (tarArchiveStructSparse.getNumbytes() > 0) {
                long dataOffset = (this.currEntry.getDataOffset() + tarArchiveStructSparse.getOffset()) - j2;
                if (tarArchiveStructSparse.getNumbytes() + dataOffset < dataOffset) {
                    throw new IOException("Unreadable TAR archive, sparse block offset or length too big");
                }
                arrayList.add(new BoundedSeekableByteChannelInputStream(dataOffset, tarArchiveStructSparse.getNumbytes(), this.archive));
            }
            j = tarArchiveStructSparse.getOffset() + tarArchiveStructSparse.getNumbytes();
        }
        this.sparseInputStreams.put(this.currEntry.getName(), arrayList);
    }

    private void applyPaxHeadersToCurrentEntry(Map<String, String> map, List<TarArchiveStructSparse> list) throws IOException {
        this.currEntry.updateEntryFromPaxHeaders(map);
        this.currEntry.setSparseHeaders(list);
    }

    private void paxHeaders() throws IOException {
        ArrayList arrayList = new ArrayList();
        InputStream inputStream = getInputStream(this.currEntry);
        try {
            Map<String, String> parsePaxHeaders = TarUtils.parsePaxHeaders(inputStream, arrayList, this.globalPaxHeaders, this.currEntry.getSize());
            if (inputStream != null) {
                inputStream.close();
            }
            if (parsePaxHeaders.containsKey("GNU.sparse.map")) {
                arrayList = new ArrayList(TarUtils.parseFromPAX01SparseHeaders(parsePaxHeaders.get("GNU.sparse.map")));
            }
            getNextTarEntry();
            if (this.currEntry == null) {
                throw new IOException("premature end of tar archive. Didn't find any entry after PAX header.");
            }
            applyPaxHeadersToCurrentEntry(parsePaxHeaders, arrayList);
            if (this.currEntry.isPaxGNU1XSparse()) {
                inputStream = getInputStream(this.currEntry);
                try {
                    List<TarArchiveStructSparse> parsePAX1XSparseHeaders = TarUtils.parsePAX1XSparseHeaders(inputStream, this.recordSize);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    this.currEntry.setSparseHeaders(parsePAX1XSparseHeaders);
                    TarArchiveEntry tarArchiveEntry = this.currEntry;
                    tarArchiveEntry.setDataOffset(tarArchiveEntry.getDataOffset() + this.recordSize);
                } finally {
                }
            }
            buildSparseInputStreams();
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    private void readGlobalPaxHeaders() throws IOException {
        InputStream inputStream = getInputStream(this.currEntry);
        try {
            this.globalPaxHeaders = TarUtils.parsePaxHeaders(inputStream, this.globalSparseHeaders, this.globalPaxHeaders, this.currEntry.getSize());
            if (inputStream != null) {
                inputStream.close();
            }
            getNextTarEntry();
            if (this.currEntry == null) {
                throw new IOException("Error detected parsing the pax header");
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private byte[] getLongNameData() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = getInputStream(this.currEntry);
        while (true) {
            try {
                int read = inputStream.read(this.smallBuf);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(this.smallBuf, 0, read);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        getNextTarEntry();
        if (this.currEntry == null) {
            return null;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        while (length > 0 && byteArray[length - 1] == 0) {
            length--;
        }
        if (length == byteArray.length) {
            return byteArray;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 0, bArr, 0, length);
        return bArr;
    }

    private void skipRecordPadding() throws IOException {
        if (isDirectory() || this.currEntry.getSize() <= 0 || this.currEntry.getSize() % this.recordSize == 0) {
            return;
        }
        long size = this.currEntry.getSize();
        int i = this.recordSize;
        repositionForwardBy((((size / i) + 1) * i) - this.currEntry.getSize());
        throwExceptionIfPositionIsNotInArchive();
    }

    private void repositionForwardTo(long j) throws IOException {
        if (j < this.archive.position()) {
            throw new IOException("trying to move backwards inside of the archive");
        }
        this.archive.position(j);
    }

    private void repositionForwardBy(long j) throws IOException {
        repositionForwardTo(this.archive.position() + j);
    }

    private void throwExceptionIfPositionIsNotInArchive() throws IOException {
        if (this.archive.size() < this.archive.position()) {
            throw new IOException("Truncated TAR archive");
        }
    }

    private ByteBuffer getRecord() throws IOException {
        ByteBuffer readRecord = readRecord();
        setAtEOF(isEOFRecord(readRecord));
        if (!isAtEOF() || readRecord == null) {
            return readRecord;
        }
        tryToConsumeSecondEOFRecord();
        consumeRemainderOfLastBlock();
        return null;
    }

    private void tryToConsumeSecondEOFRecord() throws IOException {
        try {
            if (!isEOFRecord(readRecord())) {
            }
        } finally {
            SeekableByteChannel seekableByteChannel = this.archive;
            seekableByteChannel.position(seekableByteChannel.position() - this.recordSize);
        }
    }

    private void consumeRemainderOfLastBlock() throws IOException {
        long position = this.archive.position();
        int i = this.blockSize;
        long j = position % i;
        if (j > 0) {
            repositionForwardBy(i - j);
        }
    }

    private ByteBuffer readRecord() throws IOException {
        this.recordBuffer.rewind();
        if (this.archive.read(this.recordBuffer) != this.recordSize) {
            return null;
        }
        return this.recordBuffer;
    }

    public List<TarArchiveEntry> getEntries() {
        return new ArrayList(this.entries);
    }

    private boolean isEOFRecord(ByteBuffer byteBuffer) {
        return byteBuffer == null || ArchiveUtils.isArrayZero(byteBuffer.array(), this.recordSize);
    }

    protected final boolean isAtEOF() {
        return this.hasHitEOF;
    }

    protected final void setAtEOF(boolean z) {
        this.hasHitEOF = z;
    }

    private boolean isDirectory() {
        TarArchiveEntry tarArchiveEntry = this.currEntry;
        return tarArchiveEntry != null && tarArchiveEntry.isDirectory();
    }

    public InputStream getInputStream(TarArchiveEntry tarArchiveEntry) throws IOException {
        try {
            return new BoundedTarEntryInputStream(tarArchiveEntry, this.archive);
        } catch (RuntimeException e) {
            throw new IOException("Corrupted TAR archive. Can't read entry", e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.archive.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public final class BoundedTarEntryInputStream extends BoundedArchiveInputStream {
        private final SeekableByteChannel channel;
        private int currentSparseInputStreamIndex;
        private final TarArchiveEntry entry;
        private long entryOffset;

        BoundedTarEntryInputStream(TarArchiveEntry tarArchiveEntry, SeekableByteChannel seekableByteChannel) throws IOException {
            super(tarArchiveEntry.getDataOffset(), tarArchiveEntry.getRealSize());
            if (seekableByteChannel.size() - tarArchiveEntry.getSize() < tarArchiveEntry.getDataOffset()) {
                throw new IOException("entry size exceeds archive size");
            }
            this.entry = tarArchiveEntry;
            this.channel = seekableByteChannel;
        }

        @Override // org.apache.commons.compress.utils.BoundedArchiveInputStream
        protected int read(long j, ByteBuffer byteBuffer) throws IOException {
            int readArchive;
            if (this.entryOffset >= this.entry.getRealSize()) {
                return -1;
            }
            if (this.entry.isSparse()) {
                readArchive = readSparse(this.entryOffset, byteBuffer, byteBuffer.limit());
            } else {
                readArchive = readArchive(j, byteBuffer);
            }
            if (readArchive == -1) {
                if (byteBuffer.array().length > 0) {
                    throw new IOException("Truncated TAR archive");
                }
                TarFile.this.setAtEOF(true);
            } else {
                this.entryOffset += readArchive;
                byteBuffer.flip();
            }
            return readArchive;
        }

        private int readSparse(long j, ByteBuffer byteBuffer, int i) throws IOException {
            List list = (List) TarFile.this.sparseInputStreams.get(this.entry.getName());
            if (list == null || list.isEmpty()) {
                return readArchive(this.entry.getDataOffset() + j, byteBuffer);
            }
            if (this.currentSparseInputStreamIndex >= list.size()) {
                return -1;
            }
            byte[] bArr = new byte[i];
            int read = ((InputStream) list.get(this.currentSparseInputStreamIndex)).read(bArr);
            if (read != -1) {
                byteBuffer.put(bArr, 0, read);
            }
            if (this.currentSparseInputStreamIndex == list.size() - 1) {
                return read;
            }
            if (read == -1) {
                this.currentSparseInputStreamIndex++;
                return readSparse(j, byteBuffer, i);
            }
            if (read >= i) {
                return read;
            }
            this.currentSparseInputStreamIndex++;
            int readSparse = readSparse(j + read, byteBuffer, i - read);
            return readSparse == -1 ? read : read + readSparse;
        }

        private int readArchive(long j, ByteBuffer byteBuffer) throws IOException {
            this.channel.position(j);
            return this.channel.read(byteBuffer);
        }
    }
}
