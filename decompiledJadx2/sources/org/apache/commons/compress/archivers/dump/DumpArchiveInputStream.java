package org.apache.commons.compress.archivers.dump;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.IOUtils;

/* loaded from: classes7.dex */
public class DumpArchiveInputStream extends ArchiveInputStream {
    private DumpArchiveEntry active;
    private byte[] blockBuffer;
    final String encoding;
    private long entryOffset;
    private long entrySize;
    private long filepos;
    private boolean hasHitEOF;
    private boolean isClosed;
    private final Map<Integer, Dirent> names;
    private final Map<Integer, DumpArchiveEntry> pending;
    private final Queue<DumpArchiveEntry> queue;
    protected TapeInputStream raw;
    private final byte[] readBuf;
    private int readIdx;
    private int recordOffset;
    private final DumpArchiveSummary summary;
    private final ZipEncoding zipEncoding;

    public DumpArchiveInputStream(InputStream inputStream) throws ArchiveException {
        this(inputStream, null);
    }

    public DumpArchiveInputStream(InputStream inputStream, String str) throws ArchiveException {
        this.readBuf = new byte[1024];
        this.names = new HashMap();
        this.pending = new HashMap();
        this.raw = new TapeInputStream(inputStream);
        this.hasHitEOF = false;
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        try {
            byte[] readRecord = this.raw.readRecord();
            if (!DumpArchiveUtil.verify(readRecord)) {
                throw new UnrecognizedFormatException();
            }
            this.summary = new DumpArchiveSummary(readRecord, this.zipEncoding);
            this.raw.resetBlockSize(this.summary.getNTRec(), this.summary.isCompressed());
            this.blockBuffer = new byte[4096];
            readCLRI();
            readBITS();
            this.names.put(2, new Dirent(2, 2, 4, "."));
            this.queue = new PriorityQueue(10, new Comparator() { // from class: org.apache.commons.compress.archivers.dump.-$$Lambda$DumpArchiveInputStream$bpCnvVEaViePOUbC8AeS-BPqVlc
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return DumpArchiveInputStream.lambda$new$0((DumpArchiveEntry) obj, (DumpArchiveEntry) obj2);
                }
            });
        } catch (IOException e) {
            throw new ArchiveException(e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$0(DumpArchiveEntry dumpArchiveEntry, DumpArchiveEntry dumpArchiveEntry2) {
        if (dumpArchiveEntry.getOriginalName() == null || dumpArchiveEntry2.getOriginalName() == null) {
            return Integer.MAX_VALUE;
        }
        return dumpArchiveEntry.getOriginalName().compareTo(dumpArchiveEntry2.getOriginalName());
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    @Deprecated
    public int getCount() {
        return (int) getBytesRead();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public long getBytesRead() {
        return this.raw.getBytesRead();
    }

    public DumpArchiveSummary getSummary() {
        return this.summary;
    }

    private void readCLRI() throws IOException {
        byte[] readRecord = this.raw.readRecord();
        if (!DumpArchiveUtil.verify(readRecord)) {
            throw new InvalidFormatException();
        }
        this.active = DumpArchiveEntry.parse(readRecord);
        if (DumpArchiveConstants.SEGMENT_TYPE.CLRI != this.active.getHeaderType()) {
            throw new InvalidFormatException();
        }
        if (this.raw.skip(this.active.getHeaderCount() * 1024) == -1) {
            throw new EOFException();
        }
        this.readIdx = this.active.getHeaderCount();
    }

    private void readBITS() throws IOException {
        byte[] readRecord = this.raw.readRecord();
        if (!DumpArchiveUtil.verify(readRecord)) {
            throw new InvalidFormatException();
        }
        this.active = DumpArchiveEntry.parse(readRecord);
        if (DumpArchiveConstants.SEGMENT_TYPE.BITS != this.active.getHeaderType()) {
            throw new InvalidFormatException();
        }
        if (this.raw.skip(this.active.getHeaderCount() * 1024) == -1) {
            throw new EOFException();
        }
        this.readIdx = this.active.getHeaderCount();
    }

    public DumpArchiveEntry getNextDumpEntry() throws IOException {
        return getNextEntry();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public DumpArchiveEntry getNextEntry() throws IOException {
        if (!this.queue.isEmpty()) {
            return this.queue.remove();
        }
        DumpArchiveEntry dumpArchiveEntry = null;
        String str = null;
        while (dumpArchiveEntry == null) {
            if (this.hasHitEOF) {
                return null;
            }
            while (this.readIdx < this.active.getHeaderCount()) {
                DumpArchiveEntry dumpArchiveEntry2 = this.active;
                int i = this.readIdx;
                this.readIdx = i + 1;
                if (!dumpArchiveEntry2.isSparseRecord(i) && this.raw.skip(1024L) == -1) {
                    throw new EOFException();
                }
            }
            this.readIdx = 0;
            this.filepos = this.raw.getBytesRead();
            byte[] readRecord = this.raw.readRecord();
            if (!DumpArchiveUtil.verify(readRecord)) {
                throw new InvalidFormatException();
            }
            this.active = DumpArchiveEntry.parse(readRecord);
            while (DumpArchiveConstants.SEGMENT_TYPE.ADDR == this.active.getHeaderType()) {
                if (this.raw.skip((this.active.getHeaderCount() - this.active.getHeaderHoles()) * 1024) == -1) {
                    throw new EOFException();
                }
                this.filepos = this.raw.getBytesRead();
                byte[] readRecord2 = this.raw.readRecord();
                if (!DumpArchiveUtil.verify(readRecord2)) {
                    throw new InvalidFormatException();
                }
                this.active = DumpArchiveEntry.parse(readRecord2);
            }
            if (DumpArchiveConstants.SEGMENT_TYPE.END == this.active.getHeaderType()) {
                this.hasHitEOF = true;
                return null;
            }
            DumpArchiveEntry dumpArchiveEntry3 = this.active;
            if (dumpArchiveEntry3.isDirectory()) {
                readDirectoryEntry(this.active);
                this.entryOffset = 0L;
                this.entrySize = 0L;
                this.readIdx = this.active.getHeaderCount();
            } else {
                this.entryOffset = 0L;
                this.entrySize = this.active.getEntrySize();
                this.readIdx = 0;
            }
            this.recordOffset = this.readBuf.length;
            String path = getPath(dumpArchiveEntry3);
            if (path == null) {
                dumpArchiveEntry3 = null;
            }
            DumpArchiveEntry dumpArchiveEntry4 = dumpArchiveEntry3;
            str = path;
            dumpArchiveEntry = dumpArchiveEntry4;
        }
        dumpArchiveEntry.setName(str);
        dumpArchiveEntry.setSimpleName(this.names.get(Integer.valueOf(dumpArchiveEntry.getIno())).getName());
        dumpArchiveEntry.setOffset(this.filepos);
        return dumpArchiveEntry;
    }

    private void readDirectoryEntry(DumpArchiveEntry dumpArchiveEntry) throws IOException {
        long entrySize = dumpArchiveEntry.getEntrySize();
        boolean z = true;
        while (true) {
            if (!z && DumpArchiveConstants.SEGMENT_TYPE.ADDR != dumpArchiveEntry.getHeaderType()) {
                return;
            }
            if (!z) {
                this.raw.readRecord();
            }
            if (!this.names.containsKey(Integer.valueOf(dumpArchiveEntry.getIno())) && DumpArchiveConstants.SEGMENT_TYPE.INODE == dumpArchiveEntry.getHeaderType()) {
                this.pending.put(Integer.valueOf(dumpArchiveEntry.getIno()), dumpArchiveEntry);
            }
            int headerCount = dumpArchiveEntry.getHeaderCount() * 1024;
            byte[] bArr = this.blockBuffer;
            if (bArr.length < headerCount) {
                this.blockBuffer = IOUtils.readRange(this.raw, headerCount);
                if (this.blockBuffer.length != headerCount) {
                    throw new EOFException();
                }
            } else if (this.raw.read(bArr, 0, headerCount) != headerCount) {
                throw new EOFException();
            }
            int i = 0;
            while (i < headerCount - 8 && i < entrySize - 8) {
                int convert32 = DumpArchiveUtil.convert32(this.blockBuffer, i);
                int convert16 = DumpArchiveUtil.convert16(this.blockBuffer, i + 4);
                byte[] bArr2 = this.blockBuffer;
                byte b = bArr2[i + 6];
                String decode = DumpArchiveUtil.decode(this.zipEncoding, bArr2, i + 8, bArr2[i + 7]);
                if (!".".equals(decode) && !"..".equals(decode)) {
                    this.names.put(Integer.valueOf(convert32), new Dirent(convert32, dumpArchiveEntry.getIno(), b, decode));
                    for (Map.Entry<Integer, DumpArchiveEntry> entry : this.pending.entrySet()) {
                        String path = getPath(entry.getValue());
                        if (path != null) {
                            entry.getValue().setName(path);
                            entry.getValue().setSimpleName(this.names.get(entry.getKey()).getName());
                            this.queue.add(entry.getValue());
                        }
                    }
                    Iterator<DumpArchiveEntry> it = this.queue.iterator();
                    while (it.hasNext()) {
                        this.pending.remove(Integer.valueOf(it.next().getIno()));
                    }
                }
                i += convert16;
            }
            byte[] peek = this.raw.peek();
            if (!DumpArchiveUtil.verify(peek)) {
                throw new InvalidFormatException();
            }
            dumpArchiveEntry = DumpArchiveEntry.parse(peek);
            entrySize -= 1024;
            z = false;
        }
    }

    private String getPath(DumpArchiveEntry dumpArchiveEntry) {
        Stack stack = new Stack();
        int ino = dumpArchiveEntry.getIno();
        while (true) {
            if (!this.names.containsKey(Integer.valueOf(ino))) {
                stack.clear();
                break;
            }
            Dirent dirent = this.names.get(Integer.valueOf(ino));
            stack.push(dirent.getName());
            if (dirent.getIno() == dirent.getParentIno()) {
                break;
            }
            ino = dirent.getParentIno();
        }
        if (stack.isEmpty()) {
            this.pending.put(Integer.valueOf(dumpArchiveEntry.getIno()), dumpArchiveEntry);
            return null;
        }
        StringBuilder sb = new StringBuilder((String) stack.pop());
        while (!stack.isEmpty()) {
            sb.append('/');
            sb.append((String) stack.pop());
        }
        return sb.toString();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.hasHitEOF || this.isClosed) {
            return -1;
        }
        long j = this.entryOffset;
        long j2 = this.entrySize;
        if (j >= j2) {
            return -1;
        }
        if (this.active == null) {
            throw new IllegalStateException("No current dump entry");
        }
        if (i2 + j > j2) {
            i2 = (int) (j2 - j);
        }
        int i3 = i;
        int i4 = 0;
        while (i2 > 0) {
            byte[] bArr2 = this.readBuf;
            int length = bArr2.length;
            int i5 = this.recordOffset;
            int length2 = i2 > length - i5 ? bArr2.length - i5 : i2;
            int i6 = this.recordOffset;
            int i7 = i6 + length2;
            byte[] bArr3 = this.readBuf;
            if (i7 <= bArr3.length) {
                System.arraycopy(bArr3, i6, bArr, i3, length2);
                i4 += length2;
                this.recordOffset += length2;
                i2 -= length2;
                i3 += length2;
            }
            if (i2 > 0) {
                if (this.readIdx >= 512) {
                    byte[] readRecord = this.raw.readRecord();
                    if (!DumpArchiveUtil.verify(readRecord)) {
                        throw new InvalidFormatException();
                    }
                    this.active = DumpArchiveEntry.parse(readRecord);
                    this.readIdx = 0;
                }
                DumpArchiveEntry dumpArchiveEntry = this.active;
                int i8 = this.readIdx;
                this.readIdx = i8 + 1;
                if (!dumpArchiveEntry.isSparseRecord(i8)) {
                    TapeInputStream tapeInputStream = this.raw;
                    byte[] bArr4 = this.readBuf;
                    if (tapeInputStream.read(bArr4, 0, bArr4.length) != this.readBuf.length) {
                        throw new EOFException();
                    }
                } else {
                    Arrays.fill(this.readBuf, (byte) 0);
                }
                this.recordOffset = 0;
            }
        }
        this.entryOffset += i4;
        return i4;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.isClosed) {
            return;
        }
        this.isClosed = true;
        this.raw.close();
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < 32) {
            return false;
        }
        if (i >= 1024) {
            return DumpArchiveUtil.verify(bArr);
        }
        return 60012 == DumpArchiveUtil.convert32(bArr, 24);
    }
}
