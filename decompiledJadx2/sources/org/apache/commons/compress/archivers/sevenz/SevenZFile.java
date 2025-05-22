package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes8.dex */
public class SevenZFile implements Closeable {
    private static final String DEFAULT_FILE_NAME = "unknown archive";
    static final int SIGNATURE_HEADER_SIZE = 32;
    private final Archive archive;
    private SeekableByteChannel channel;
    private long compressedBytesReadFromCurrentEntry;
    private int currentEntryIndex;
    private int currentFolderIndex;
    private InputStream currentFolderInputStream;
    private final ArrayList<InputStream> deferredBlockStreams;
    private final String fileName;
    private final SevenZFileOptions options;
    private byte[] password;
    private long uncompressedBytesReadFromCurrentEntry;
    static final byte[] sevenZSignature = {TarConstants.LF_CONTIG, 122, PSSSigner.TRAILER_IMPLICIT, -81, 39, 28};
    private static final CharsetEncoder PASSWORD_ENCODER = StandardCharsets.UTF_16LE.newEncoder();

    public SevenZFile(File file, char[] cArr) throws IOException {
        this(file, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), utf16Decode(cArr), true, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(File file, byte[] bArr) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), bArr, true, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, null, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr) throws IOException {
        this(seekableByteChannel, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, cArr, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr) throws IOException {
        this(seekableByteChannel, str, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, utf16Decode(cArr), false, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        this(seekableByteChannel, str, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, null, false, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, bArr);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr) throws IOException {
        this(seekableByteChannel, str, bArr, false, SevenZFileOptions.DEFAULT);
    }

    private SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr, boolean z, SevenZFileOptions sevenZFileOptions) throws IOException {
        this.currentEntryIndex = -1;
        this.currentFolderIndex = -1;
        this.deferredBlockStreams = new ArrayList<>();
        this.channel = seekableByteChannel;
        this.fileName = str;
        this.options = sevenZFileOptions;
        try {
            this.archive = readHeaders(bArr);
            if (bArr != null) {
                this.password = Arrays.copyOf(bArr, bArr.length);
            } else {
                this.password = null;
            }
        } catch (Throwable th) {
            if (z) {
                this.channel.close();
            }
            throw th;
        }
    }

    public SevenZFile(File file) throws IOException {
        this(file, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(file, (char[]) null, sevenZFileOptions);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        SeekableByteChannel seekableByteChannel = this.channel;
        if (seekableByteChannel != null) {
            try {
                seekableByteChannel.close();
            } finally {
                this.channel = null;
                byte[] bArr = this.password;
                if (bArr != null) {
                    Arrays.fill(bArr, (byte) 0);
                }
                this.password = null;
            }
        }
    }

    public SevenZArchiveEntry getNextEntry() throws IOException {
        if (this.currentEntryIndex >= this.archive.files.length - 1) {
            return null;
        }
        this.currentEntryIndex++;
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[this.currentEntryIndex];
        if (sevenZArchiveEntry.getName() == null && this.options.getUseDefaultNameForUnnamedEntries()) {
            sevenZArchiveEntry.setName(getDefaultName());
        }
        buildDecodingStream(this.currentEntryIndex, false);
        this.compressedBytesReadFromCurrentEntry = 0L;
        this.uncompressedBytesReadFromCurrentEntry = 0L;
        return sevenZArchiveEntry;
    }

    public Iterable<SevenZArchiveEntry> getEntries() {
        return new ArrayList(Arrays.asList(this.archive.files));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Archive readHeaders(byte[] bArr) throws IOException {
        ByteBuffer order = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
        readFully(order);
        byte[] bArr2 = new byte[6];
        order.get(bArr2);
        if (!Arrays.equals(bArr2, sevenZSignature)) {
            throw new IOException("Bad 7z signature");
        }
        byte b = order.get();
        byte b2 = order.get();
        boolean z = false;
        if (b != 0) {
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", Byte.valueOf(b), Byte.valueOf(b2)));
        }
        long j = order.getInt() & 4294967295L;
        if (j == 0) {
            long position = this.channel.position();
            ByteBuffer allocate = ByteBuffer.allocate(20);
            readFully(allocate);
            this.channel.position(position);
            while (allocate.hasRemaining()) {
                if (allocate.get() != 0) {
                }
            }
            if (!z) {
                return initializeArchive(readStartHeader(j), bArr, true);
            }
            if (this.options.getTryToRecoverBrokenArchives()) {
                return tryToLocateEndHeader(bArr);
            }
            throw new IOException("archive seems to be invalid.\nYou may want to retry and enable the tryToRecoverBrokenArchives if the archive could be a multi volume archive that has been closed prematurely.");
        }
        z = true;
        if (!z) {
        }
    }

    private Archive tryToLocateEndHeader(byte[] bArr) throws IOException {
        long size;
        ByteBuffer allocate = ByteBuffer.allocate(1);
        long position = this.channel.position() + 20;
        if (this.channel.position() + 1048576 > this.channel.size()) {
            size = this.channel.position();
        } else {
            size = this.channel.size() - 1048576;
        }
        long size2 = this.channel.size() - 1;
        while (size2 > size) {
            size2--;
            this.channel.position(size2);
            allocate.rewind();
            if (this.channel.read(allocate) < 1) {
                throw new EOFException();
            }
            byte b = allocate.array()[0];
            if (b == 23 || b == 1) {
                try {
                    StartHeader startHeader = new StartHeader();
                    startHeader.nextHeaderOffset = size2 - position;
                    startHeader.nextHeaderSize = this.channel.size() - size2;
                    Archive initializeArchive = initializeArchive(startHeader, bArr, false);
                    if (initializeArchive.packSizes.length > 0 && initializeArchive.files.length > 0) {
                        return initializeArchive;
                    }
                } catch (Exception unused) {
                    continue;
                }
            }
        }
        throw new IOException("Start header corrupt and unable to guess end header");
    }

    private Archive initializeArchive(StartHeader startHeader, byte[] bArr, boolean z) throws IOException {
        assertFitsIntoNonNegativeInt("nextHeaderSize", startHeader.nextHeaderSize);
        int i = (int) startHeader.nextHeaderSize;
        this.channel.position(startHeader.nextHeaderOffset + 32);
        ByteBuffer order = ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
        readFully(order);
        if (z) {
            CRC32 crc32 = new CRC32();
            crc32.update(order.array());
            if (startHeader.nextHeaderCrc != crc32.getValue()) {
                throw new IOException("NextHeader CRC mismatch");
            }
        }
        Archive archive = new Archive();
        int unsignedByte = getUnsignedByte(order);
        if (unsignedByte == 23) {
            order = readEncodedHeader(order, archive, bArr);
            archive = new Archive();
            unsignedByte = getUnsignedByte(order);
        }
        if (unsignedByte != 1) {
            throw new IOException("Broken or unsupported archive: no Header");
        }
        readHeader(order, archive);
        archive.subStreamsInfo = null;
        return archive;
    }

    private StartHeader readStartHeader(long j) throws IOException {
        StartHeader startHeader = new StartHeader();
        DataInputStream dataInputStream = new DataInputStream(new CRC32VerifyingInputStream(new BoundedSeekableByteChannelInputStream(this.channel, 20L), 20L, j));
        try {
            startHeader.nextHeaderOffset = Long.reverseBytes(dataInputStream.readLong());
            if (startHeader.nextHeaderOffset < 0 || startHeader.nextHeaderOffset + 32 > this.channel.size()) {
                throw new IOException("nextHeaderOffset is out of bounds");
            }
            startHeader.nextHeaderSize = Long.reverseBytes(dataInputStream.readLong());
            long j2 = startHeader.nextHeaderOffset + startHeader.nextHeaderSize;
            if (j2 < startHeader.nextHeaderOffset || j2 + 32 > this.channel.size()) {
                throw new IOException("nextHeaderSize is out of bounds");
            }
            startHeader.nextHeaderCrc = 4294967295L & Integer.reverseBytes(dataInputStream.readInt());
            dataInputStream.close();
            return startHeader;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    dataInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private void readHeader(ByteBuffer byteBuffer, Archive archive) throws IOException {
        int position = byteBuffer.position();
        sanityCheckAndCollectStatistics(byteBuffer).assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(position);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            readArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 3) {
            throw new IOException("Additional streams unsupported");
        }
        if (unsignedByte == 4) {
            readStreamsInfo(byteBuffer, archive);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 5) {
            readFilesInfo(byteBuffer, archive);
            getUnsignedByte(byteBuffer);
        }
    }

    private ArchiveStatistics sanityCheckAndCollectStatistics(ByteBuffer byteBuffer) throws IOException {
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            sanityCheckArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 3) {
            throw new IOException("Additional streams unsupported");
        }
        if (unsignedByte == 4) {
            sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 5) {
            sanityCheckFilesInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 0) {
            return archiveStatistics;
        }
        throw new IOException("Badly terminated header, found " + unsignedByte);
    }

    private void readArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            get(byteBuffer, new byte[(int) readUint64(byteBuffer)]);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            long assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("propertySize", readUint64(byteBuffer));
            if (skipBytesFully(byteBuffer, assertFitsIntoNonNegativeInt) < assertFitsIntoNonNegativeInt) {
                throw new IOException("invalid property size");
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
    }

    private ByteBuffer readEncodedHeader(ByteBuffer byteBuffer, Archive archive, byte[] bArr) throws IOException {
        int position = byteBuffer.position();
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
        archiveStatistics.assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(position);
        readStreamsInfo(byteBuffer, archive);
        if (archive.folders == null || archive.folders.length == 0) {
            throw new IOException("no folders, can't read encoded header");
        }
        if (archive.packSizes == null || archive.packSizes.length == 0) {
            throw new IOException("no packed streams, can't read encoded header");
        }
        Folder folder = archive.folders[0];
        this.channel.position(archive.packPos + 32 + 0);
        BoundedSeekableByteChannelInputStream boundedSeekableByteChannelInputStream = new BoundedSeekableByteChannelInputStream(this.channel, archive.packSizes[0]);
        InputStream inputStream = boundedSeekableByteChannelInputStream;
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1 || coder.numOutStreams != 1) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            inputStream = Coders.addDecoder(this.fileName, inputStream, folder.getUnpackSizeForCoder(coder), coder, bArr, this.options.getMaxMemoryLimitInKb());
        }
        InputStream cRC32VerifyingInputStream = folder.hasCrc ? new CRC32VerifyingInputStream(inputStream, folder.getUnpackSize(), folder.crc) : inputStream;
        int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("unpackSize", folder.getUnpackSize());
        byte[] readRange = IOUtils.readRange(cRC32VerifyingInputStream, assertFitsIntoNonNegativeInt);
        if (readRange.length < assertFitsIntoNonNegativeInt) {
            throw new IOException("premature end of stream");
        }
        cRC32VerifyingInputStream.close();
        return ByteBuffer.wrap(readRange).order(ByteOrder.LITTLE_ENDIAN);
    }

    private void sanityCheckStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            sanityCheckPackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            sanityCheckUnpackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 8) {
            sanityCheckSubStreamsInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated StreamsInfo");
        }
    }

    private void readStreamsInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            readPackInfo(byteBuffer, archive);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            readUnpackInfo(byteBuffer, archive);
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            archive.folders = Folder.EMPTY_FOLDER_ARRAY;
        }
        if (unsignedByte == 8) {
            readSubStreamsInfo(byteBuffer, archive);
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckPackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        long readUint64 = readUint64(byteBuffer);
        long j = 0;
        if (readUint64 >= 0) {
            long j2 = 32 + readUint64;
            if (j2 <= this.channel.size() && j2 >= 0) {
                archiveStatistics.numberOfPackedStreams = assertFitsIntoNonNegativeInt("numPackStreams", readUint64(byteBuffer));
                int unsignedByte = getUnsignedByte(byteBuffer);
                if (unsignedByte == 9) {
                    int i = 0;
                    long j3 = 0;
                    while (i < archiveStatistics.numberOfPackedStreams) {
                        long readUint642 = readUint64(byteBuffer);
                        j3 += readUint642;
                        long j4 = j2 + j3;
                        if (readUint642 < j || j4 > this.channel.size() || j4 < readUint64) {
                            throw new IOException("packSize (" + readUint642 + ") is out of range");
                        }
                        i++;
                        j = 0;
                    }
                    unsignedByte = getUnsignedByte(byteBuffer);
                }
                if (unsignedByte == 10) {
                    long cardinality = readAllOrBits(byteBuffer, archiveStatistics.numberOfPackedStreams).cardinality() * 4;
                    if (skipBytesFully(byteBuffer, cardinality) < cardinality) {
                        throw new IOException("invalid number of CRCs in PackInfo");
                    }
                    unsignedByte = getUnsignedByte(byteBuffer);
                }
                if (unsignedByte == 0) {
                    return;
                }
                throw new IOException("Badly terminated PackInfo (" + unsignedByte + ")");
            }
        }
        throw new IOException("packPos (" + readUint64 + ") is out of range");
    }

    private void readPackInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        archive.packPos = readUint64(byteBuffer);
        int readUint64 = (int) readUint64(byteBuffer);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 9) {
            archive.packSizes = new long[readUint64];
            for (int i = 0; i < archive.packSizes.length; i++) {
                archive.packSizes[i] = readUint64(byteBuffer);
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 10) {
            archive.packCrcsDefined = readAllOrBits(byteBuffer, readUint64);
            archive.packCrcs = new long[readUint64];
            for (int i2 = 0; i2 < readUint64; i2++) {
                if (archive.packCrcsDefined.get(i2)) {
                    archive.packCrcs[i2] = 4294967295L & getInt(byteBuffer);
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckUnpackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte != 11) {
            throw new IOException("Expected kFolder, got " + unsignedByte);
        }
        archiveStatistics.numberOfFolders = assertFitsIntoNonNegativeInt("numFolders", readUint64(byteBuffer));
        if (getUnsignedByte(byteBuffer) != 0) {
            throw new IOException("External unsupported");
        }
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < archiveStatistics.numberOfFolders; i++) {
            linkedList.add(Integer.valueOf(sanityCheckFolder(byteBuffer, archiveStatistics)));
        }
        if (archiveStatistics.numberOfInStreams - (archiveStatistics.numberOfOutStreams - archiveStatistics.numberOfFolders) < archiveStatistics.numberOfPackedStreams) {
            throw new IOException("archive doesn't contain enough packed streams");
        }
        int unsignedByte2 = getUnsignedByte(byteBuffer);
        if (unsignedByte2 != 12) {
            throw new IOException("Expected kCodersUnpackSize, got " + unsignedByte2);
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            for (int i2 = 0; i2 < intValue; i2++) {
                if (readUint64(byteBuffer) < 0) {
                    throw new IllegalArgumentException("negative unpackSize");
                }
            }
        }
        int unsignedByte3 = getUnsignedByte(byteBuffer);
        if (unsignedByte3 == 10) {
            archiveStatistics.folderHasCrc = readAllOrBits(byteBuffer, archiveStatistics.numberOfFolders);
            long cardinality = archiveStatistics.folderHasCrc.cardinality() * 4;
            if (skipBytesFully(byteBuffer, cardinality) < cardinality) {
                throw new IOException("invalid number of CRCs in UnpackInfo");
            }
            unsignedByte3 = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte3 != 0) {
            throw new IOException("Badly terminated UnpackInfo");
        }
    }

    private void readUnpackInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        getUnsignedByte(byteBuffer);
        int readUint64 = (int) readUint64(byteBuffer);
        Folder[] folderArr = new Folder[readUint64];
        archive.folders = folderArr;
        getUnsignedByte(byteBuffer);
        for (int i = 0; i < readUint64; i++) {
            folderArr[i] = readFolder(byteBuffer);
        }
        getUnsignedByte(byteBuffer);
        for (Folder folder : folderArr) {
            assertFitsIntoNonNegativeInt("totalOutputStreams", folder.totalOutputStreams);
            folder.unpackSizes = new long[(int) folder.totalOutputStreams];
            for (int i2 = 0; i2 < folder.totalOutputStreams; i2++) {
                folder.unpackSizes[i2] = readUint64(byteBuffer);
            }
        }
        if (getUnsignedByte(byteBuffer) == 10) {
            BitSet readAllOrBits = readAllOrBits(byteBuffer, readUint64);
            for (int i3 = 0; i3 < readUint64; i3++) {
                if (readAllOrBits.get(i3)) {
                    folderArr[i3].hasCrc = true;
                    folderArr[i3].crc = 4294967295L & getInt(byteBuffer);
                } else {
                    folderArr[i3].hasCrc = false;
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckSubStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int i;
        int unsignedByte = getUnsignedByte(byteBuffer);
        LinkedList linkedList = new LinkedList();
        int i2 = 0;
        if (unsignedByte == 13) {
            for (int i3 = 0; i3 < archiveStatistics.numberOfFolders; i3++) {
                linkedList.add(Integer.valueOf(assertFitsIntoNonNegativeInt("numStreams", readUint64(byteBuffer))));
            }
            archiveStatistics.numberOfUnpackSubStreams = ((Long) linkedList.stream().collect(Collectors.summingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.sevenz.-$$Lambda$SevenZFile$XKADHdTpC27A-iei-Wr6jTlcABY
                @Override // java.util.function.ToLongFunction
                public final long applyAsLong(Object obj) {
                    long longValue;
                    longValue = ((Integer) obj).longValue();
                    return longValue;
                }
            }))).longValue();
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            archiveStatistics.numberOfUnpackSubStreams = archiveStatistics.numberOfFolders;
        }
        assertFitsIntoNonNegativeInt("totalUnpackStreams", archiveStatistics.numberOfUnpackSubStreams);
        if (unsignedByte == 9) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                if (intValue != 0) {
                    for (int i4 = 0; i4 < intValue - 1; i4++) {
                        if (readUint64(byteBuffer) < 0) {
                            throw new IOException("negative unpackSize");
                        }
                    }
                }
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (!linkedList.isEmpty()) {
            Iterator it2 = linkedList.iterator();
            int i5 = 0;
            while (it2.hasNext()) {
                int intValue2 = ((Integer) it2.next()).intValue();
                if (intValue2 == 1 && archiveStatistics.folderHasCrc != null) {
                    int i6 = i5 + 1;
                    if (archiveStatistics.folderHasCrc.get(i5)) {
                        i5 = i6;
                    } else {
                        i5 = i6;
                    }
                }
                i2 += intValue2;
            }
            i = i2;
        } else {
            i = archiveStatistics.folderHasCrc == null ? archiveStatistics.numberOfFolders : archiveStatistics.numberOfFolders - archiveStatistics.folderHasCrc.cardinality();
        }
        if (unsignedByte == 10) {
            assertFitsIntoNonNegativeInt("numDigests", i);
            long cardinality = readAllOrBits(byteBuffer, i).cardinality() * 4;
            if (skipBytesFully(byteBuffer, cardinality) < cardinality) {
                throw new IOException("invalid number of missing CRCs in SubStreamInfo");
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated SubStreamsInfo");
        }
    }

    private void readSubStreamsInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        for (Folder folder : archive.folders) {
            folder.numUnpackSubStreams = 1;
        }
        long length = archive.folders.length;
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 13) {
            long j = 0;
            for (Folder folder2 : archive.folders) {
                long readUint64 = readUint64(byteBuffer);
                folder2.numUnpackSubStreams = (int) readUint64;
                j += readUint64;
            }
            unsignedByte = getUnsignedByte(byteBuffer);
            length = j;
        }
        int i = (int) length;
        SubStreamsInfo subStreamsInfo = new SubStreamsInfo();
        subStreamsInfo.unpackSizes = new long[i];
        subStreamsInfo.hasCrc = new BitSet(i);
        subStreamsInfo.crcs = new long[i];
        int i2 = 0;
        for (Folder folder3 : archive.folders) {
            if (folder3.numUnpackSubStreams != 0) {
                long j2 = 0;
                if (unsignedByte == 9) {
                    int i3 = i2;
                    int i4 = 0;
                    while (i4 < folder3.numUnpackSubStreams - 1) {
                        long readUint642 = readUint64(byteBuffer);
                        subStreamsInfo.unpackSizes[i3] = readUint642;
                        j2 += readUint642;
                        i4++;
                        i3++;
                    }
                    i2 = i3;
                }
                if (j2 > folder3.getUnpackSize()) {
                    throw new IOException("sum of unpack sizes of folder exceeds total unpack size");
                }
                subStreamsInfo.unpackSizes[i2] = folder3.getUnpackSize() - j2;
                i2++;
            }
        }
        if (unsignedByte == 9) {
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        int i5 = 0;
        for (Folder folder4 : archive.folders) {
            if (folder4.numUnpackSubStreams != 1 || !folder4.hasCrc) {
                i5 += folder4.numUnpackSubStreams;
            }
        }
        if (unsignedByte == 10) {
            BitSet readAllOrBits = readAllOrBits(byteBuffer, i5);
            long[] jArr = new long[i5];
            for (int i6 = 0; i6 < i5; i6++) {
                if (readAllOrBits.get(i6)) {
                    jArr[i6] = 4294967295L & getInt(byteBuffer);
                }
            }
            int i7 = 0;
            int i8 = 0;
            for (Folder folder5 : archive.folders) {
                if (folder5.numUnpackSubStreams == 1 && folder5.hasCrc) {
                    subStreamsInfo.hasCrc.set(i7, true);
                    subStreamsInfo.crcs[i7] = folder5.crc;
                    i7++;
                } else {
                    int i9 = i8;
                    int i10 = i7;
                    for (int i11 = 0; i11 < folder5.numUnpackSubStreams; i11++) {
                        subStreamsInfo.hasCrc.set(i10, readAllOrBits.get(i9));
                        subStreamsInfo.crcs[i10] = jArr[i9];
                        i10++;
                        i9++;
                    }
                    i7 = i10;
                    i8 = i9;
                }
            }
            getUnsignedByte(byteBuffer);
        }
        archive.subStreamsInfo = subStreamsInfo;
    }

    private int sanityCheckFolder(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("numCoders", readUint64(byteBuffer));
        if (assertFitsIntoNonNegativeInt == 0) {
            throw new IOException("Folder without coders");
        }
        archiveStatistics.numberOfCoders += assertFitsIntoNonNegativeInt;
        long j = 0;
        long j2 = 0;
        int i = 0;
        while (true) {
            long j3 = 1;
            if (i < assertFitsIntoNonNegativeInt) {
                int unsignedByte = getUnsignedByte(byteBuffer);
                get(byteBuffer, new byte[unsignedByte & 15]);
                boolean z = (unsignedByte & 16) == 0;
                boolean z2 = (unsignedByte & 32) != 0;
                if ((unsignedByte & 128) != 0) {
                    throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
                }
                if (z) {
                    j++;
                } else {
                    j += assertFitsIntoNonNegativeInt("numInStreams", readUint64(byteBuffer));
                    j3 = assertFitsIntoNonNegativeInt("numOutStreams", readUint64(byteBuffer));
                }
                j2 += j3;
                if (z2) {
                    long assertFitsIntoNonNegativeInt2 = assertFitsIntoNonNegativeInt("propertiesSize", readUint64(byteBuffer));
                    if (skipBytesFully(byteBuffer, assertFitsIntoNonNegativeInt2) < assertFitsIntoNonNegativeInt2) {
                        throw new IOException("invalid propertiesSize in folder");
                    }
                }
                i++;
            } else {
                assertFitsIntoNonNegativeInt("totalInStreams", j);
                assertFitsIntoNonNegativeInt("totalOutStreams", j2);
                archiveStatistics.numberOfOutStreams += j2;
                archiveStatistics.numberOfInStreams += j;
                if (j2 == 0) {
                    throw new IOException("Total output streams can't be 0");
                }
                int assertFitsIntoNonNegativeInt3 = assertFitsIntoNonNegativeInt("numBindPairs", j2 - 1);
                long j4 = assertFitsIntoNonNegativeInt3;
                if (j < j4) {
                    throw new IOException("Total input streams can't be less than the number of bind pairs");
                }
                BitSet bitSet = new BitSet((int) j);
                for (int i2 = 0; i2 < assertFitsIntoNonNegativeInt3; i2++) {
                    int assertFitsIntoNonNegativeInt4 = assertFitsIntoNonNegativeInt("inIndex", readUint64(byteBuffer));
                    if (j <= assertFitsIntoNonNegativeInt4) {
                        throw new IOException("inIndex is bigger than number of inStreams");
                    }
                    bitSet.set(assertFitsIntoNonNegativeInt4);
                    if (j2 <= assertFitsIntoNonNegativeInt("outIndex", readUint64(byteBuffer))) {
                        throw new IOException("outIndex is bigger than number of outStreams");
                    }
                }
                int assertFitsIntoNonNegativeInt5 = assertFitsIntoNonNegativeInt("numPackedStreams", j - j4);
                if (assertFitsIntoNonNegativeInt5 != 1) {
                    for (int i3 = 0; i3 < assertFitsIntoNonNegativeInt5; i3++) {
                        if (assertFitsIntoNonNegativeInt("packedStreamIndex", readUint64(byteBuffer)) >= j) {
                            throw new IOException("packedStreamIndex is bigger than number of totalInStreams");
                        }
                    }
                } else if (bitSet.nextClearBit(0) == -1) {
                    throw new IOException("Couldn't find stream's bind pair index");
                }
                return (int) j2;
            }
        }
    }

    private Folder readFolder(ByteBuffer byteBuffer) throws IOException {
        Folder folder = new Folder();
        Coder[] coderArr = new Coder[(int) readUint64(byteBuffer)];
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < coderArr.length; i++) {
            coderArr[i] = new Coder();
            int unsignedByte = getUnsignedByte(byteBuffer);
            int i2 = unsignedByte & 15;
            boolean z = (unsignedByte & 16) == 0;
            boolean z2 = (unsignedByte & 32) != 0;
            boolean z3 = (unsignedByte & 128) != 0;
            coderArr[i].decompressionMethodId = new byte[i2];
            get(byteBuffer, coderArr[i].decompressionMethodId);
            if (z) {
                coderArr[i].numInStreams = 1L;
                coderArr[i].numOutStreams = 1L;
            } else {
                coderArr[i].numInStreams = readUint64(byteBuffer);
                coderArr[i].numOutStreams = readUint64(byteBuffer);
            }
            j += coderArr[i].numInStreams;
            j2 += coderArr[i].numOutStreams;
            if (z2) {
                coderArr[i].properties = new byte[(int) readUint64(byteBuffer)];
                get(byteBuffer, coderArr[i].properties);
            }
            if (z3) {
                throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
            }
        }
        folder.coders = coderArr;
        folder.totalInputStreams = j;
        folder.totalOutputStreams = j2;
        long j3 = j2 - 1;
        BindPair[] bindPairArr = new BindPair[(int) j3];
        for (int i3 = 0; i3 < bindPairArr.length; i3++) {
            bindPairArr[i3] = new BindPair();
            bindPairArr[i3].inIndex = readUint64(byteBuffer);
            bindPairArr[i3].outIndex = readUint64(byteBuffer);
        }
        folder.bindPairs = bindPairArr;
        long j4 = j - j3;
        int i4 = (int) j4;
        long[] jArr = new long[i4];
        if (j4 == 1) {
            int i5 = 0;
            while (i5 < ((int) j) && folder.findBindPairForInStream(i5) >= 0) {
                i5++;
            }
            jArr[0] = i5;
        } else {
            for (int i6 = 0; i6 < i4; i6++) {
                jArr[i6] = readUint64(byteBuffer);
            }
        }
        folder.packedStreams = jArr;
        return folder;
    }

    private BitSet readAllOrBits(ByteBuffer byteBuffer, int i) throws IOException {
        if (getUnsignedByte(byteBuffer) != 0) {
            BitSet bitSet = new BitSet(i);
            for (int i2 = 0; i2 < i; i2++) {
                bitSet.set(i2, true);
            }
            return bitSet;
        }
        return readBits(byteBuffer, i);
    }

    private BitSet readBits(ByteBuffer byteBuffer, int i) throws IOException {
        BitSet bitSet = new BitSet(i);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 == 0) {
                i2 = 128;
                i3 = getUnsignedByte(byteBuffer);
            }
            bitSet.set(i4, (i3 & i2) != 0);
            i2 >>>= 1;
        }
        return bitSet;
    }

    private void sanityCheckFilesInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        archiveStatistics.numberOfEntries = assertFitsIntoNonNegativeInt("numFiles", readUint64(byteBuffer));
        int i = -1;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer);
            if (unsignedByte != 0) {
                long readUint64 = readUint64(byteBuffer);
                switch (unsignedByte) {
                    case 14:
                        i = readBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        break;
                    case 15:
                        if (i == -1) {
                            throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
                        }
                        readBits(byteBuffer, i);
                        break;
                    case 16:
                        if (i == -1) {
                            throw new IOException("Header format error: kEmptyStream must appear before kAnti");
                        }
                        readBits(byteBuffer, i);
                        break;
                    case 17:
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("file names length", readUint64 - 1);
                        if ((assertFitsIntoNonNegativeInt & 1) != 0) {
                            throw new IOException("File names length invalid");
                        }
                        int i2 = 0;
                        for (int i3 = 0; i3 < assertFitsIntoNonNegativeInt; i3 += 2) {
                            if (getChar(byteBuffer) == 0) {
                                i2++;
                            }
                        }
                        if (i2 != archiveStatistics.numberOfEntries) {
                            throw new IOException("Invalid number of file names (" + i2 + " instead of " + archiveStatistics.numberOfEntries + ")");
                        }
                        break;
                    case 18:
                        int cardinality = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j = cardinality * 8;
                        if (skipBytesFully(byteBuffer, j) < j) {
                            throw new IOException("invalid creation dates size");
                        }
                        break;
                    case 19:
                        int cardinality2 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j2 = cardinality2 * 8;
                        if (skipBytesFully(byteBuffer, j2) < j2) {
                            throw new IOException("invalid access dates size");
                        }
                        break;
                    case 20:
                        int cardinality3 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j3 = cardinality3 * 8;
                        if (skipBytesFully(byteBuffer, j3) < j3) {
                            throw new IOException("invalid modification dates size");
                        }
                        break;
                    case 21:
                        int cardinality4 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                        if (getUnsignedByte(byteBuffer) != 0) {
                            throw new IOException("Not implemented");
                        }
                        long j4 = cardinality4 * 4;
                        if (skipBytesFully(byteBuffer, j4) < j4) {
                            throw new IOException("invalid windows attributes size");
                        }
                        break;
                    case 22:
                    case 23:
                    default:
                        if (skipBytesFully(byteBuffer, readUint64) < readUint64) {
                            throw new IOException("Incomplete property of type " + unsignedByte);
                        }
                        break;
                    case 24:
                        throw new IOException("kStartPos is unsupported, please report");
                    case 25:
                        if (skipBytesFully(byteBuffer, readUint64) < readUint64) {
                            throw new IOException("Incomplete kDummy property");
                        }
                        break;
                }
            } else {
                int i4 = archiveStatistics.numberOfEntries;
                if (i <= 0) {
                    i = 0;
                }
                archiveStatistics.numberOfEntriesWithStream = i4 - i;
                return;
            }
        }
    }

    private void readFilesInfo(ByteBuffer byteBuffer, Archive archive) throws IOException {
        Archive archive2 = archive;
        int readUint64 = (int) readUint64(byteBuffer);
        HashMap hashMap = new HashMap();
        BitSet bitSet = null;
        BitSet bitSet2 = null;
        BitSet bitSet3 = null;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer);
            int i = 0;
            if (unsignedByte != 0) {
                long readUint642 = readUint64(byteBuffer);
                if (unsignedByte != 25) {
                    switch (unsignedByte) {
                        case 14:
                            bitSet = readBits(byteBuffer, readUint64);
                            break;
                        case 15:
                            bitSet2 = readBits(byteBuffer, bitSet.cardinality());
                            break;
                        case 16:
                            bitSet3 = readBits(byteBuffer, bitSet.cardinality());
                            break;
                        case 17:
                            getUnsignedByte(byteBuffer);
                            byte[] bArr = new byte[(int) (readUint642 - 1)];
                            int length = bArr.length;
                            get(byteBuffer, bArr);
                            int i2 = 0;
                            int i3 = 0;
                            while (i < length) {
                                if (bArr[i] == 0 && bArr[i + 1] == 0) {
                                    checkEntryIsInitialized(hashMap, i3);
                                    hashMap.get(Integer.valueOf(i3)).setName(new String(bArr, i2, i - i2, StandardCharsets.UTF_16LE));
                                    i3++;
                                    i2 = i + 2;
                                }
                                i += 2;
                            }
                            if (i2 == length && i3 == readUint64) {
                                break;
                            }
                            break;
                        case 18:
                            BitSet readAllOrBits = readAllOrBits(byteBuffer, readUint64);
                            getUnsignedByte(byteBuffer);
                            while (i < readUint64) {
                                checkEntryIsInitialized(hashMap, i);
                                SevenZArchiveEntry sevenZArchiveEntry = hashMap.get(Integer.valueOf(i));
                                sevenZArchiveEntry.setHasCreationDate(readAllOrBits.get(i));
                                if (sevenZArchiveEntry.getHasCreationDate()) {
                                    sevenZArchiveEntry.setCreationDate(getLong(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        case 19:
                            BitSet readAllOrBits2 = readAllOrBits(byteBuffer, readUint64);
                            getUnsignedByte(byteBuffer);
                            while (i < readUint64) {
                                checkEntryIsInitialized(hashMap, i);
                                SevenZArchiveEntry sevenZArchiveEntry2 = hashMap.get(Integer.valueOf(i));
                                sevenZArchiveEntry2.setHasAccessDate(readAllOrBits2.get(i));
                                if (sevenZArchiveEntry2.getHasAccessDate()) {
                                    sevenZArchiveEntry2.setAccessDate(getLong(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        case 20:
                            BitSet readAllOrBits3 = readAllOrBits(byteBuffer, readUint64);
                            getUnsignedByte(byteBuffer);
                            while (i < readUint64) {
                                checkEntryIsInitialized(hashMap, i);
                                SevenZArchiveEntry sevenZArchiveEntry3 = hashMap.get(Integer.valueOf(i));
                                sevenZArchiveEntry3.setHasLastModifiedDate(readAllOrBits3.get(i));
                                if (sevenZArchiveEntry3.getHasLastModifiedDate()) {
                                    sevenZArchiveEntry3.setLastModifiedDate(getLong(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        case 21:
                            BitSet readAllOrBits4 = readAllOrBits(byteBuffer, readUint64);
                            getUnsignedByte(byteBuffer);
                            while (i < readUint64) {
                                checkEntryIsInitialized(hashMap, i);
                                SevenZArchiveEntry sevenZArchiveEntry4 = hashMap.get(Integer.valueOf(i));
                                sevenZArchiveEntry4.setHasWindowsAttributes(readAllOrBits4.get(i));
                                if (sevenZArchiveEntry4.getHasWindowsAttributes()) {
                                    sevenZArchiveEntry4.setWindowsAttributes(getInt(byteBuffer));
                                }
                                i++;
                            }
                            break;
                        default:
                            skipBytesFully(byteBuffer, readUint642);
                            break;
                    }
                } else {
                    skipBytesFully(byteBuffer, readUint642);
                }
                archive2 = archive;
            } else {
                int i4 = 0;
                int i5 = 0;
                for (int i6 = 0; i6 < readUint64; i6++) {
                    SevenZArchiveEntry sevenZArchiveEntry5 = hashMap.get(Integer.valueOf(i6));
                    if (sevenZArchiveEntry5 != null) {
                        sevenZArchiveEntry5.setHasStream(bitSet == null || !bitSet.get(i6));
                        if (sevenZArchiveEntry5.hasStream()) {
                            if (archive2.subStreamsInfo == null) {
                                throw new IOException("Archive contains file with streams but no subStreamsInfo");
                            }
                            sevenZArchiveEntry5.setDirectory(false);
                            sevenZArchiveEntry5.setAntiItem(false);
                            sevenZArchiveEntry5.setHasCrc(archive2.subStreamsInfo.hasCrc.get(i5));
                            sevenZArchiveEntry5.setCrcValue(archive2.subStreamsInfo.crcs[i5]);
                            sevenZArchiveEntry5.setSize(archive2.subStreamsInfo.unpackSizes[i5]);
                            if (sevenZArchiveEntry5.getSize() < 0) {
                                throw new IOException("broken archive, entry with negative size");
                            }
                            i5++;
                        } else {
                            sevenZArchiveEntry5.setDirectory(bitSet2 == null || !bitSet2.get(i4));
                            sevenZArchiveEntry5.setAntiItem(bitSet3 != null && bitSet3.get(i4));
                            sevenZArchiveEntry5.setHasCrc(false);
                            sevenZArchiveEntry5.setSize(0L);
                            i4++;
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (SevenZArchiveEntry sevenZArchiveEntry6 : hashMap.values()) {
                    if (sevenZArchiveEntry6 != null) {
                        arrayList.add(sevenZArchiveEntry6);
                    }
                }
                archive2.files = (SevenZArchiveEntry[]) arrayList.toArray(SevenZArchiveEntry.EMPTY_SEVEN_Z_ARCHIVE_ENTRY_ARRAY);
                calculateStreamMap(archive2);
                return;
            }
        }
        throw new IOException("Error parsing file names");
    }

    private void checkEntryIsInitialized(Map<Integer, SevenZArchiveEntry> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            map.put(Integer.valueOf(i), new SevenZArchiveEntry());
        }
    }

    private void calculateStreamMap(Archive archive) throws IOException {
        StreamMap streamMap = new StreamMap();
        int length = archive.folders != null ? archive.folders.length : 0;
        streamMap.folderFirstPackStreamIndex = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            streamMap.folderFirstPackStreamIndex[i2] = i;
            i += archive.folders[i2].packedStreams.length;
        }
        int length2 = archive.packSizes.length;
        streamMap.packStreamOffsets = new long[length2];
        long j = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            streamMap.packStreamOffsets[i3] = j;
            j += archive.packSizes[i3];
        }
        streamMap.folderFirstFileIndex = new int[length];
        streamMap.fileFolderIndex = new int[archive.files.length];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < archive.files.length; i6++) {
            if (!archive.files[i6].hasStream() && i4 == 0) {
                streamMap.fileFolderIndex[i6] = -1;
            } else {
                if (i4 == 0) {
                    while (i5 < archive.folders.length) {
                        streamMap.folderFirstFileIndex[i5] = i6;
                        if (archive.folders[i5].numUnpackSubStreams > 0) {
                            break;
                        } else {
                            i5++;
                        }
                    }
                    if (i5 >= archive.folders.length) {
                        throw new IOException("Too few folders in archive");
                    }
                }
                streamMap.fileFolderIndex[i6] = i5;
                if (archive.files[i6].hasStream() && (i4 = i4 + 1) >= archive.folders[i5].numUnpackSubStreams) {
                    i5++;
                    i4 = 0;
                }
            }
        }
        archive.streamMap = streamMap;
    }

    private void buildDecodingStream(int i, boolean z) throws IOException {
        boolean z2;
        if (this.archive.streamMap == null) {
            throw new IOException("Archive doesn't contain stream information to read entries");
        }
        int i2 = this.archive.streamMap.fileFolderIndex[i];
        if (i2 < 0) {
            this.deferredBlockStreams.clear();
            return;
        }
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[i];
        if (this.currentFolderIndex == i2) {
            if (i > 0) {
                sevenZArchiveEntry.setContentMethods(this.archive.files[i - 1].getContentMethods());
            }
            if (z && sevenZArchiveEntry.getContentMethods() == null) {
                sevenZArchiveEntry.setContentMethods(this.archive.files[this.archive.streamMap.folderFirstFileIndex[i2]].getContentMethods());
            }
            z2 = true;
        } else {
            this.currentFolderIndex = i2;
            reopenFolderInputStream(i2, sevenZArchiveEntry);
            z2 = false;
        }
        boolean skipEntriesWhenNeeded = z ? skipEntriesWhenNeeded(i, z2, i2) : false;
        if (z && this.currentEntryIndex == i && !skipEntriesWhenNeeded) {
            return;
        }
        BoundedInputStream boundedInputStream = new BoundedInputStream(this.currentFolderInputStream, sevenZArchiveEntry.getSize());
        this.deferredBlockStreams.add(sevenZArchiveEntry.getHasCrc() ? new CRC32VerifyingInputStream(boundedInputStream, sevenZArchiveEntry.getSize(), sevenZArchiveEntry.getCrcValue()) : boundedInputStream);
    }

    private void reopenFolderInputStream(int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.deferredBlockStreams.clear();
        InputStream inputStream = this.currentFolderInputStream;
        if (inputStream != null) {
            inputStream.close();
            this.currentFolderInputStream = null;
        }
        Folder folder = this.archive.folders[i];
        int i2 = this.archive.streamMap.folderFirstPackStreamIndex[i];
        this.currentFolderInputStream = buildDecoderStack(folder, this.archive.packPos + 32 + this.archive.streamMap.packStreamOffsets[i2], i2, sevenZArchiveEntry);
    }

    private boolean skipEntriesWhenNeeded(int i, boolean z, int i2) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[i];
        if (this.currentEntryIndex == i && !hasCurrentEntryBeenRead()) {
            return false;
        }
        int i3 = this.archive.streamMap.folderFirstFileIndex[this.currentFolderIndex];
        if (z) {
            int i4 = this.currentEntryIndex;
            if (i4 < i) {
                i3 = i4 + 1;
            } else {
                reopenFolderInputStream(i2, sevenZArchiveEntry);
            }
        }
        while (i3 < i) {
            SevenZArchiveEntry sevenZArchiveEntry2 = this.archive.files[i3];
            BoundedInputStream boundedInputStream = new BoundedInputStream(this.currentFolderInputStream, sevenZArchiveEntry2.getSize());
            this.deferredBlockStreams.add(sevenZArchiveEntry2.getHasCrc() ? new CRC32VerifyingInputStream(boundedInputStream, sevenZArchiveEntry2.getSize(), sevenZArchiveEntry2.getCrcValue()) : boundedInputStream);
            sevenZArchiveEntry2.setContentMethods(sevenZArchiveEntry.getContentMethods());
            i3++;
        }
        return true;
    }

    private boolean hasCurrentEntryBeenRead() {
        if (this.deferredBlockStreams.isEmpty()) {
            return false;
        }
        ArrayList<InputStream> arrayList = this.deferredBlockStreams;
        InputStream inputStream = arrayList.get(arrayList.size() - 1);
        return inputStream instanceof BoundedInputStream ? ((BoundedInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize() : (inputStream instanceof CRC32VerifyingInputStream) && ((CRC32VerifyingInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize();
    }

    private InputStream buildDecoderStack(Folder folder, long j, int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.channel.position(j);
        FilterInputStream filterInputStream = new FilterInputStream(new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.channel, this.archive.packSizes[i]))) { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile.1
            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                int read = this.in.read();
                if (read >= 0) {
                    count(1);
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr) throws IOException {
                return read(bArr, 0, bArr.length);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) throws IOException {
                if (i3 == 0) {
                    return 0;
                }
                int read = this.in.read(bArr, i2, i3);
                if (read >= 0) {
                    count(read);
                }
                return read;
            }

            private void count(int i2) {
                SevenZFile.this.compressedBytesReadFromCurrentEntry += i2;
            }
        };
        LinkedList linkedList = new LinkedList();
        InputStream inputStream = filterInputStream;
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1 || coder.numOutStreams != 1) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            SevenZMethod byId = SevenZMethod.byId(coder.decompressionMethodId);
            inputStream = Coders.addDecoder(this.fileName, inputStream, folder.getUnpackSizeForCoder(coder), coder, this.password, this.options.getMaxMemoryLimitInKb());
            linkedList.addFirst(new SevenZMethodConfiguration(byId, Coders.findByMethod(byId).getOptionsFromCoder(coder, inputStream)));
        }
        sevenZArchiveEntry.setContentMethods(linkedList);
        return folder.hasCrc ? new CRC32VerifyingInputStream(inputStream, folder.getUnpackSize(), folder.crc) : inputStream;
    }

    public int read() throws IOException {
        int read = getCurrentStream().read();
        if (read >= 0) {
            this.uncompressedBytesReadFromCurrentEntry++;
        }
        return read;
    }

    private InputStream getCurrentStream() throws IOException {
        if (this.archive.files[this.currentEntryIndex].getSize() == 0) {
            return new ByteArrayInputStream(ByteUtils.EMPTY_BYTE_ARRAY);
        }
        if (this.deferredBlockStreams.isEmpty()) {
            throw new IllegalStateException("No current 7z entry (call getNextEntry() first).");
        }
        while (this.deferredBlockStreams.size() > 1) {
            InputStream remove = this.deferredBlockStreams.remove(0);
            try {
                IOUtils.skip(remove, Long.MAX_VALUE);
                if (remove != null) {
                    remove.close();
                }
                this.compressedBytesReadFromCurrentEntry = 0L;
            } finally {
            }
        }
        return this.deferredBlockStreams.get(0);
    }

    public InputStream getInputStream(SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        int i = 0;
        while (true) {
            if (i >= this.archive.files.length) {
                i = -1;
                break;
            }
            if (sevenZArchiveEntry == this.archive.files[i]) {
                break;
            }
            i++;
        }
        if (i < 0) {
            throw new IllegalArgumentException("Can not find " + sevenZArchiveEntry.getName() + " in " + this.fileName);
        }
        buildDecodingStream(i, true);
        this.currentEntryIndex = i;
        this.currentFolderIndex = this.archive.streamMap.fileFolderIndex[i];
        return getCurrentStream();
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = getCurrentStream().read(bArr, i, i2);
        if (read > 0) {
            this.uncompressedBytesReadFromCurrentEntry += read;
        }
        return read;
    }

    public InputStreamStatistics getStatisticsForCurrentEntry() {
        return new InputStreamStatistics() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile.2
            @Override // org.apache.commons.compress.utils.InputStreamStatistics
            public long getCompressedCount() {
                return SevenZFile.this.compressedBytesReadFromCurrentEntry;
            }

            @Override // org.apache.commons.compress.utils.InputStreamStatistics
            public long getUncompressedCount() {
                return SevenZFile.this.uncompressedBytesReadFromCurrentEntry;
            }
        };
    }

    private static long readUint64(ByteBuffer byteBuffer) throws IOException {
        long unsignedByte = getUnsignedByte(byteBuffer);
        int i = 128;
        long j = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            if ((i & unsignedByte) == 0) {
                return ((unsignedByte & (i - 1)) << (i2 * 8)) | j;
            }
            j |= getUnsignedByte(byteBuffer) << (i2 * 8);
            i >>>= 1;
        }
        return j;
    }

    private static char getChar(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 2) {
            throw new EOFException();
        }
        return byteBuffer.getChar();
    }

    private static int getInt(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 4) {
            throw new EOFException();
        }
        return byteBuffer.getInt();
    }

    private static long getLong(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 8) {
            throw new EOFException();
        }
        return byteBuffer.getLong();
    }

    private static void get(ByteBuffer byteBuffer, byte[] bArr) throws IOException {
        if (byteBuffer.remaining() < bArr.length) {
            throw new EOFException();
        }
        byteBuffer.get(bArr);
    }

    private static int getUnsignedByte(ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.hasRemaining()) {
            throw new EOFException();
        }
        return byteBuffer.get() & 255;
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < sevenZSignature.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = sevenZSignature;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    private static long skipBytesFully(ByteBuffer byteBuffer, long j) throws IOException {
        if (j < 1) {
            return 0L;
        }
        int position = byteBuffer.position();
        long remaining = byteBuffer.remaining();
        if (remaining < j) {
            j = remaining;
        }
        byteBuffer.position(position + ((int) j));
        return j;
    }

    private void readFully(ByteBuffer byteBuffer) throws IOException {
        byteBuffer.rewind();
        IOUtils.readFully(this.channel, byteBuffer);
        byteBuffer.flip();
    }

    public String toString() {
        return this.archive.toString();
    }

    public String getDefaultName() {
        String str;
        if (DEFAULT_FILE_NAME.equals(this.fileName) || (str = this.fileName) == null) {
            return null;
        }
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf > 0) {
            return name.substring(0, lastIndexOf);
        }
        return name + "~";
    }

    private static byte[] utf16Decode(char[] cArr) throws IOException {
        if (cArr == null) {
            return null;
        }
        ByteBuffer encode = PASSWORD_ENCODER.encode(CharBuffer.wrap(cArr));
        if (encode.hasArray()) {
            return encode.array();
        }
        byte[] bArr = new byte[encode.remaining()];
        encode.get(bArr);
        return bArr;
    }

    private static int assertFitsIntoNonNegativeInt(String str, long j) throws IOException {
        if (j <= 2147483647L && j >= 0) {
            return (int) j;
        }
        throw new IOException("Cannot handle " + str + " " + j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class ArchiveStatistics {
        private BitSet folderHasCrc;
        private long numberOfCoders;
        private int numberOfEntries;
        private int numberOfEntriesWithStream;
        private int numberOfFolders;
        private long numberOfInStreams;
        private long numberOfOutStreams;
        private int numberOfPackedStreams;
        private long numberOfUnpackSubStreams;

        private long bindPairSize() {
            return 16L;
        }

        private long coderSize() {
            return 22L;
        }

        private long entrySize() {
            return 100L;
        }

        private long folderSize() {
            return 30L;
        }

        private ArchiveStatistics() {
        }

        public String toString() {
            return "Archive with " + this.numberOfEntries + " entries in " + this.numberOfFolders + " folders. Estimated size " + (estimateSize() / 1024) + " kB.";
        }

        long estimateSize() {
            long folderSize = (this.numberOfPackedStreams * 16) + (r0 / 8) + (this.numberOfFolders * folderSize()) + (this.numberOfCoders * coderSize()) + ((this.numberOfOutStreams - this.numberOfFolders) * bindPairSize());
            long j = this.numberOfInStreams;
            long j2 = this.numberOfOutStreams;
            return (folderSize + (((j - j2) + this.numberOfFolders) * 8) + (j2 * 8) + (this.numberOfEntries * entrySize()) + streamMapSize()) * 2;
        }

        void assertValidity(int i) throws IOException {
            if (this.numberOfEntriesWithStream > 0 && this.numberOfFolders == 0) {
                throw new IOException("archive with entries but no folders");
            }
            if (this.numberOfEntriesWithStream > this.numberOfUnpackSubStreams) {
                throw new IOException("archive doesn't contain enough substreams for entries");
            }
            long estimateSize = estimateSize() / 1024;
            if (i < estimateSize) {
                throw new MemoryLimitException(estimateSize, i);
            }
        }

        private long streamMapSize() {
            return (this.numberOfFolders * 8) + (this.numberOfPackedStreams * 8) + (this.numberOfEntries * 4);
        }
    }
}
