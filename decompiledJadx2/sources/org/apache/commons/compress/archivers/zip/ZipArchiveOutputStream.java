package org.apache.commons.compress.archivers.zip;

import com.iflytek.aiui.vad.sdk.VadConstant;
import com.slamtec.slamware.robot.SystemParameters;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.IOUtils;

/* loaded from: classes8.dex */
public class ZipArchiveOutputStream extends ArchiveOutputStream {
    static final int BUFFER_SIZE = 512;
    private static final int CFH_COMMENT_LENGTH_OFFSET = 32;
    private static final int CFH_COMPRESSED_SIZE_OFFSET = 20;
    private static final int CFH_CRC_OFFSET = 16;
    private static final int CFH_DISK_NUMBER_OFFSET = 34;
    private static final int CFH_EXTERNAL_ATTRIBUTES_OFFSET = 38;
    private static final int CFH_EXTRA_LENGTH_OFFSET = 30;
    private static final int CFH_FILENAME_LENGTH_OFFSET = 28;
    private static final int CFH_FILENAME_OFFSET = 46;
    private static final int CFH_GPB_OFFSET = 8;
    private static final int CFH_INTERNAL_ATTRIBUTES_OFFSET = 36;
    private static final int CFH_LFH_OFFSET = 42;
    private static final int CFH_METHOD_OFFSET = 10;
    private static final int CFH_ORIGINAL_SIZE_OFFSET = 24;
    private static final int CFH_SIG_OFFSET = 0;
    private static final int CFH_TIME_OFFSET = 12;
    private static final int CFH_VERSION_MADE_BY_OFFSET = 4;
    private static final int CFH_VERSION_NEEDED_OFFSET = 6;
    public static final int DEFAULT_COMPRESSION = -1;
    static final String DEFAULT_ENCODING = "UTF8";
    public static final int DEFLATED = 8;

    @Deprecated
    public static final int EFS_FLAG = 2048;
    private static final int LFH_COMPRESSED_SIZE_OFFSET = 18;
    private static final int LFH_CRC_OFFSET = 14;
    private static final int LFH_EXTRA_LENGTH_OFFSET = 28;
    private static final int LFH_FILENAME_LENGTH_OFFSET = 26;
    private static final int LFH_FILENAME_OFFSET = 30;
    private static final int LFH_GPB_OFFSET = 6;
    private static final int LFH_METHOD_OFFSET = 8;
    private static final int LFH_ORIGINAL_SIZE_OFFSET = 22;
    private static final int LFH_SIG_OFFSET = 0;
    private static final int LFH_TIME_OFFSET = 10;
    private static final int LFH_VERSION_NEEDED_OFFSET = 4;
    public static final int STORED = 0;
    private final Calendar calendarInstance;
    private long cdDiskNumberStart;
    private long cdLength;
    private long cdOffset;
    private final SeekableByteChannel channel;
    private String comment;
    private final byte[] copyBuffer;
    private UnicodeExtraFieldPolicy createUnicodeExtraFields;
    protected final Deflater def;
    private String encoding;
    private final List<ZipArchiveEntry> entries;
    private CurrentEntry entry;
    private long eocdLength;
    private boolean fallbackToUTF8;
    protected boolean finished;
    private boolean hasCompressionLevelChanged;
    private boolean hasUsedZip64;
    private final boolean isSplitZip;
    private int level;
    private final Map<ZipArchiveEntry, EntryMetaData> metaData;
    private int method;
    private final Map<Integer, Integer> numberOfCDInDiskData;
    private final OutputStream out;
    private final StreamCompressor streamCompressor;
    private boolean useUTF8Flag;
    private Zip64Mode zip64Mode;
    private ZipEncoding zipEncoding;
    private static final byte[] ZERO = {0, 0};
    private static final byte[] LZERO = {0, 0, 0, 0};
    private static final byte[] ONE = ZipLong.getBytes(1);
    static final byte[] LFH_SIG = ZipLong.LFH_SIG.getBytes();
    static final byte[] DD_SIG = ZipLong.DD_SIG.getBytes();
    static final byte[] CFH_SIG = ZipLong.CFH_SIG.getBytes();
    static final byte[] EOCD_SIG = ZipLong.getBytes(101010256);
    static final byte[] ZIP64_EOCD_SIG = ZipLong.getBytes(101075792);
    static final byte[] ZIP64_EOCD_LOC_SIG = ZipLong.getBytes(117853008);

    private int versionNeededToExtractMethod(int i) {
        return i == 8 ? 20 : 10;
    }

    public ZipArchiveOutputStream(OutputStream outputStream) {
        this.comment = "";
        this.level = -1;
        this.method = 8;
        this.entries = new LinkedList();
        this.metaData = new HashMap();
        this.encoding = DEFAULT_ENCODING;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(DEFAULT_ENCODING);
        this.useUTF8Flag = true;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.zip64Mode = Zip64Mode.AsNeeded;
        this.copyBuffer = new byte[32768];
        this.calendarInstance = Calendar.getInstance();
        this.numberOfCDInDiskData = new HashMap();
        this.out = outputStream;
        this.channel = null;
        this.def = new Deflater(this.level, true);
        this.streamCompressor = StreamCompressor.create(outputStream, this.def);
        this.isSplitZip = false;
    }

    public ZipArchiveOutputStream(File file) throws IOException {
        this(file.toPath(), new OpenOption[0]);
    }

    public ZipArchiveOutputStream(Path path, OpenOption... openOptionArr) throws IOException {
        SeekableByteChannel seekableByteChannel;
        OutputStream newOutputStream;
        StreamCompressor create;
        this.comment = "";
        this.level = -1;
        this.method = 8;
        this.entries = new LinkedList();
        this.metaData = new HashMap();
        this.encoding = DEFAULT_ENCODING;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(DEFAULT_ENCODING);
        this.useUTF8Flag = true;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.zip64Mode = Zip64Mode.AsNeeded;
        this.copyBuffer = new byte[32768];
        this.calendarInstance = Calendar.getInstance();
        this.numberOfCDInDiskData = new HashMap();
        this.def = new Deflater(this.level, true);
        SeekableByteChannel seekableByteChannel2 = null;
        try {
            seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.TRUNCATE_EXISTING), new FileAttribute[0]);
        } catch (IOException unused) {
            seekableByteChannel = null;
        }
        try {
            create = StreamCompressor.create(seekableByteChannel, this.def);
            newOutputStream = null;
            seekableByteChannel2 = seekableByteChannel;
        } catch (IOException unused2) {
            IOUtils.closeQuietly(seekableByteChannel);
            newOutputStream = Files.newOutputStream(path, openOptionArr);
            create = StreamCompressor.create(newOutputStream, this.def);
            this.out = newOutputStream;
            this.channel = seekableByteChannel2;
            this.streamCompressor = create;
            this.isSplitZip = false;
        }
        this.out = newOutputStream;
        this.channel = seekableByteChannel2;
        this.streamCompressor = create;
        this.isSplitZip = false;
    }

    public ZipArchiveOutputStream(File file, long j) throws IOException {
        this.comment = "";
        this.level = -1;
        this.method = 8;
        this.entries = new LinkedList();
        this.metaData = new HashMap();
        this.encoding = DEFAULT_ENCODING;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(DEFAULT_ENCODING);
        this.useUTF8Flag = true;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.zip64Mode = Zip64Mode.AsNeeded;
        this.copyBuffer = new byte[32768];
        this.calendarInstance = Calendar.getInstance();
        this.numberOfCDInDiskData = new HashMap();
        this.def = new Deflater(this.level, true);
        this.out = new ZipSplitOutputStream(file, j);
        this.streamCompressor = StreamCompressor.create(this.out, this.def);
        this.channel = null;
        this.isSplitZip = true;
    }

    public ZipArchiveOutputStream(SeekableByteChannel seekableByteChannel) throws IOException {
        this.comment = "";
        this.level = -1;
        this.method = 8;
        this.entries = new LinkedList();
        this.metaData = new HashMap();
        this.encoding = DEFAULT_ENCODING;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(DEFAULT_ENCODING);
        this.useUTF8Flag = true;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.zip64Mode = Zip64Mode.AsNeeded;
        this.copyBuffer = new byte[32768];
        this.calendarInstance = Calendar.getInstance();
        this.numberOfCDInDiskData = new HashMap();
        this.channel = seekableByteChannel;
        this.def = new Deflater(this.level, true);
        this.streamCompressor = StreamCompressor.create(seekableByteChannel, this.def);
        this.out = null;
        this.isSplitZip = false;
    }

    public boolean isSeekable() {
        return this.channel != null;
    }

    public void setEncoding(String str) {
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        if (!this.useUTF8Flag || ZipEncodingHelper.isUTF8(str)) {
            return;
        }
        this.useUTF8Flag = false;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setUseLanguageEncodingFlag(boolean z) {
        this.useUTF8Flag = z && ZipEncodingHelper.isUTF8(this.encoding);
    }

    public void setCreateUnicodeExtraFields(UnicodeExtraFieldPolicy unicodeExtraFieldPolicy) {
        this.createUnicodeExtraFields = unicodeExtraFieldPolicy;
    }

    public void setFallbackToUTF8(boolean z) {
        this.fallbackToUTF8 = z;
    }

    public void setUseZip64(Zip64Mode zip64Mode) {
        this.zip64Mode = zip64Mode;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        if (this.entry != null) {
            throw new IOException("This archive contains unclosed entries.");
        }
        long totalBytesWritten = this.streamCompressor.getTotalBytesWritten();
        this.cdOffset = totalBytesWritten;
        if (this.isSplitZip) {
            this.cdOffset = ((ZipSplitOutputStream) this.out).getCurrentSplitSegmentBytesWritten();
            this.cdDiskNumberStart = r2.getCurrentSplitSegmentIndex();
        }
        writeCentralDirectoryInChunks();
        this.cdLength = this.streamCompressor.getTotalBytesWritten() - totalBytesWritten;
        ByteBuffer encode = this.zipEncoding.encode(this.comment);
        this.eocdLength = (encode.limit() - encode.position()) + 22;
        writeZip64CentralDirectory();
        writeCentralDirectoryEnd();
        this.metaData.clear();
        this.entries.clear();
        this.streamCompressor.close();
        if (this.isSplitZip) {
            this.out.close();
        }
        this.finished = true;
    }

    private void writeCentralDirectoryInChunks() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(VadConstant.ERROR_APPID_NOT_FOUND);
        Iterator<ZipArchiveEntry> it = this.entries.iterator();
        while (true) {
            int i = 0;
            while (it.hasNext()) {
                byteArrayOutputStream.write(createCentralFileHeader(it.next()));
                i++;
                if (i > 1000) {
                    break;
                }
            }
            writeCounted(byteArrayOutputStream.toByteArray());
            return;
            writeCounted(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.reset();
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        preClose();
        flushDeflater();
        long totalBytesWritten = this.streamCompressor.getTotalBytesWritten() - this.entry.dataStart;
        long crc32 = this.streamCompressor.getCrc32();
        this.entry.bytesRead = this.streamCompressor.getBytesRead();
        closeEntry(handleSizesAndCrc(totalBytesWritten, crc32, getEffectiveZip64Mode(this.entry.entry)), false);
        this.streamCompressor.reset();
    }

    private void closeCopiedEntry(boolean z) throws IOException {
        preClose();
        CurrentEntry currentEntry = this.entry;
        currentEntry.bytesRead = currentEntry.entry.getSize();
        closeEntry(checkIfNeedsZip64(getEffectiveZip64Mode(this.entry.entry)), z);
    }

    private void closeEntry(boolean z, boolean z2) throws IOException {
        if (!z2 && this.channel != null) {
            rewriteSizesAndCrc(z);
        }
        if (!z2) {
            writeDataDescriptor(this.entry.entry);
        }
        this.entry = null;
    }

    private void preClose() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        CurrentEntry currentEntry = this.entry;
        if (currentEntry == null) {
            throw new IOException("No current entry to close");
        }
        if (currentEntry.hasWritten) {
            return;
        }
        write(ByteUtils.EMPTY_BYTE_ARRAY, 0, 0);
    }

    public void addRawArchiveEntry(ZipArchiveEntry zipArchiveEntry, InputStream inputStream) throws IOException {
        ZipArchiveEntry zipArchiveEntry2 = new ZipArchiveEntry(zipArchiveEntry);
        if (hasZip64Extra(zipArchiveEntry2)) {
            zipArchiveEntry2.removeExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        }
        boolean z = (zipArchiveEntry2.getCrc() == -1 || zipArchiveEntry2.getSize() == -1 || zipArchiveEntry2.getCompressedSize() == -1) ? false : true;
        putArchiveEntry(zipArchiveEntry2, z);
        copyFromZipInputStream(inputStream);
        closeCopiedEntry(z);
    }

    private void flushDeflater() throws IOException {
        if (this.entry.entry.getMethod() == 8) {
            this.streamCompressor.flushDeflater();
        }
    }

    private boolean handleSizesAndCrc(long j, long j2, Zip64Mode zip64Mode) throws ZipException {
        if (this.entry.entry.getMethod() != 8) {
            if (this.channel != null) {
                this.entry.entry.setSize(j);
                this.entry.entry.setCompressedSize(j);
                this.entry.entry.setCrc(j2);
            } else {
                if (this.entry.entry.getCrc() != j2) {
                    throw new ZipException("Bad CRC checksum for entry " + this.entry.entry.getName() + ": " + Long.toHexString(this.entry.entry.getCrc()) + " instead of " + Long.toHexString(j2));
                }
                if (this.entry.entry.getSize() != j) {
                    throw new ZipException("Bad size for entry " + this.entry.entry.getName() + ": " + this.entry.entry.getSize() + " instead of " + j);
                }
            }
        } else {
            this.entry.entry.setSize(this.entry.bytesRead);
            this.entry.entry.setCompressedSize(j);
            this.entry.entry.setCrc(j2);
        }
        return checkIfNeedsZip64(zip64Mode);
    }

    private boolean checkIfNeedsZip64(Zip64Mode zip64Mode) throws ZipException {
        boolean isZip64Required = isZip64Required(this.entry.entry, zip64Mode);
        if (isZip64Required && zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
        }
        return isZip64Required;
    }

    private boolean isZip64Required(ZipArchiveEntry zipArchiveEntry, Zip64Mode zip64Mode) {
        return zip64Mode == Zip64Mode.Always || zip64Mode == Zip64Mode.AlwaysWithCompatibility || isTooLargeForZip32(zipArchiveEntry);
    }

    private boolean isTooLargeForZip32(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getSize() >= 4294967295L || zipArchiveEntry.getCompressedSize() >= 4294967295L;
    }

    private void rewriteSizesAndCrc(boolean z) throws IOException {
        long position = this.channel.position();
        this.channel.position(this.entry.localDataStart);
        writeOut(ZipLong.getBytes(this.entry.entry.getCrc()));
        if (hasZip64Extra(this.entry.entry) && z) {
            writeOut(ZipLong.ZIP64_MAGIC.getBytes());
            writeOut(ZipLong.ZIP64_MAGIC.getBytes());
        } else {
            writeOut(ZipLong.getBytes(this.entry.entry.getCompressedSize()));
            writeOut(ZipLong.getBytes(this.entry.entry.getSize()));
        }
        if (hasZip64Extra(this.entry.entry)) {
            ByteBuffer name = getName(this.entry.entry);
            this.channel.position(this.entry.localDataStart + 12 + 4 + (name.limit() - name.position()) + 4);
            writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getSize()));
            writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getCompressedSize()));
            if (!z) {
                this.channel.position(this.entry.localDataStart - 10);
                writeOut(ZipShort.getBytes(versionNeededToExtract(this.entry.entry.getMethod(), false, false)));
                this.entry.entry.removeExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
                this.entry.entry.setExtra();
                if (this.entry.causedUseOfZip64) {
                    this.hasUsedZip64 = false;
                }
            }
        }
        this.channel.position(position);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        putArchiveEntry(archiveEntry, false);
    }

    private void putArchiveEntry(ArchiveEntry archiveEntry, boolean z) throws IOException {
        ZipEightByteInteger zipEightByteInteger;
        ZipEightByteInteger zipEightByteInteger2;
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (this.entry != null) {
            closeArchiveEntry();
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
        this.entry = new CurrentEntry(zipArchiveEntry);
        this.entries.add(this.entry.entry);
        setDefaults(this.entry.entry);
        Zip64Mode effectiveZip64Mode = getEffectiveZip64Mode(this.entry.entry);
        validateSizeInformation(effectiveZip64Mode);
        if (shouldAddZip64Extra(this.entry.entry, effectiveZip64Mode)) {
            Zip64ExtendedInformationExtraField zip64Extra = getZip64Extra(this.entry.entry);
            if (z) {
                zipEightByteInteger = new ZipEightByteInteger(this.entry.entry.getSize());
                zipEightByteInteger2 = new ZipEightByteInteger(this.entry.entry.getCompressedSize());
            } else {
                if (this.entry.entry.getMethod() == 0 && this.entry.entry.getSize() != -1) {
                    zipEightByteInteger = new ZipEightByteInteger(this.entry.entry.getSize());
                } else {
                    zipEightByteInteger = ZipEightByteInteger.ZERO;
                }
                zipEightByteInteger2 = zipEightByteInteger;
            }
            zip64Extra.setSize(zipEightByteInteger);
            zip64Extra.setCompressedSize(zipEightByteInteger2);
            this.entry.entry.setExtra();
        }
        if (this.entry.entry.getMethod() == 8 && this.hasCompressionLevelChanged) {
            this.def.setLevel(this.level);
            this.hasCompressionLevelChanged = false;
        }
        writeLocalFileHeader(zipArchiveEntry, z);
    }

    private void setDefaults(ZipArchiveEntry zipArchiveEntry) {
        if (zipArchiveEntry.getMethod() == -1) {
            zipArchiveEntry.setMethod(this.method);
        }
        if (zipArchiveEntry.getTime() == -1) {
            zipArchiveEntry.setTime(System.currentTimeMillis());
        }
    }

    private void validateSizeInformation(Zip64Mode zip64Mode) throws ZipException {
        if (this.entry.entry.getMethod() == 0 && this.channel == null) {
            if (this.entry.entry.getSize() == -1) {
                throw new ZipException("Uncompressed size is required for STORED method when not writing to a file");
            }
            if (this.entry.entry.getCrc() == -1) {
                throw new ZipException("CRC checksum is required for STORED method when not writing to a file");
            }
            this.entry.entry.setCompressedSize(this.entry.entry.getSize());
        }
        if ((this.entry.entry.getSize() >= 4294967295L || this.entry.entry.getCompressedSize() >= 4294967295L) && zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
        }
    }

    private boolean shouldAddZip64Extra(ZipArchiveEntry zipArchiveEntry, Zip64Mode zip64Mode) {
        return zip64Mode == Zip64Mode.Always || zip64Mode == Zip64Mode.AlwaysWithCompatibility || zipArchiveEntry.getSize() >= 4294967295L || zipArchiveEntry.getCompressedSize() >= 4294967295L || !(zipArchiveEntry.getSize() != -1 || this.channel == null || zip64Mode == Zip64Mode.Never);
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setLevel(int i) {
        if (i < -1 || i > 9) {
            throw new IllegalArgumentException("Invalid compression level: " + i);
        }
        if (this.level == i) {
            return;
        }
        this.hasCompressionLevelChanged = true;
        this.level = i;
    }

    public void setMethod(int i) {
        this.method = i;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public boolean canWriteEntryData(ArchiveEntry archiveEntry) {
        if (!(archiveEntry instanceof ZipArchiveEntry)) {
            return false;
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
        return (zipArchiveEntry.getMethod() == ZipMethod.IMPLODING.getCode() || zipArchiveEntry.getMethod() == ZipMethod.UNSHRINKING.getCode() || !ZipUtil.canHandleEntryData(zipArchiveEntry)) ? false : true;
    }

    public void writePreamble(byte[] bArr) throws IOException {
        writePreamble(bArr, 0, bArr.length);
    }

    public void writePreamble(byte[] bArr, int i, int i2) throws IOException {
        if (this.entry != null) {
            throw new IllegalStateException("Preamble must be written before creating an entry");
        }
        this.streamCompressor.writeCounted(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        CurrentEntry currentEntry = this.entry;
        if (currentEntry == null) {
            throw new IllegalStateException("No current entry");
        }
        ZipUtil.checkRequestedFeatures(currentEntry.entry);
        count(this.streamCompressor.write(bArr, i, i2, this.entry.entry.getMethod()));
    }

    private void writeCounted(byte[] bArr) throws IOException {
        this.streamCompressor.writeCounted(bArr);
    }

    private void copyFromZipInputStream(InputStream inputStream) throws IOException {
        CurrentEntry currentEntry = this.entry;
        if (currentEntry == null) {
            throw new IllegalStateException("No current entry");
        }
        ZipUtil.checkRequestedFeatures(currentEntry.entry);
        this.entry.hasWritten = true;
        while (true) {
            int read = inputStream.read(this.copyBuffer);
            if (read < 0) {
                return;
            }
            this.streamCompressor.writeCounted(this.copyBuffer, 0, read);
            count(read);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (!this.finished) {
                finish();
            }
        } finally {
            destroy();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    protected final void deflate() throws IOException {
        this.streamCompressor.deflate();
    }

    protected void writeLocalFileHeader(ZipArchiveEntry zipArchiveEntry) throws IOException {
        writeLocalFileHeader(zipArchiveEntry, false);
    }

    private void writeLocalFileHeader(ZipArchiveEntry zipArchiveEntry, boolean z) throws IOException {
        boolean canEncode = this.zipEncoding.canEncode(zipArchiveEntry.getName());
        ByteBuffer name = getName(zipArchiveEntry);
        if (this.createUnicodeExtraFields != UnicodeExtraFieldPolicy.NEVER) {
            addUnicodeExtraFields(zipArchiveEntry, canEncode, name);
        }
        long totalBytesWritten = this.streamCompressor.getTotalBytesWritten();
        if (this.isSplitZip) {
            ZipSplitOutputStream zipSplitOutputStream = (ZipSplitOutputStream) this.out;
            zipArchiveEntry.setDiskNumberStart(zipSplitOutputStream.getCurrentSplitSegmentIndex());
            totalBytesWritten = zipSplitOutputStream.getCurrentSplitSegmentBytesWritten();
        }
        byte[] createLocalFileHeader = createLocalFileHeader(zipArchiveEntry, name, canEncode, z, totalBytesWritten);
        this.metaData.put(zipArchiveEntry, new EntryMetaData(totalBytesWritten, usesDataDescriptor(zipArchiveEntry.getMethod(), z)));
        this.entry.localDataStart = totalBytesWritten + 14;
        writeCounted(createLocalFileHeader);
        this.entry.dataStart = this.streamCompressor.getTotalBytesWritten();
    }

    private byte[] createLocalFileHeader(ZipArchiveEntry zipArchiveEntry, ByteBuffer byteBuffer, boolean z, boolean z2, long j) {
        ZipExtraField extraField = zipArchiveEntry.getExtraField(ResourceAlignmentExtraField.f8919ID);
        if (extraField != null) {
            zipArchiveEntry.removeExtraField(ResourceAlignmentExtraField.f8919ID);
        }
        ResourceAlignmentExtraField resourceAlignmentExtraField = extraField instanceof ResourceAlignmentExtraField ? (ResourceAlignmentExtraField) extraField : null;
        int alignment = zipArchiveEntry.getAlignment();
        if (alignment <= 0 && resourceAlignmentExtraField != null) {
            alignment = resourceAlignmentExtraField.getAlignment();
        }
        if (alignment > 1 || (resourceAlignmentExtraField != null && !resourceAlignmentExtraField.allowMethodChange())) {
            zipArchiveEntry.addExtraField(new ResourceAlignmentExtraField(alignment, resourceAlignmentExtraField != null && resourceAlignmentExtraField.allowMethodChange(), (int) (((((-j) - (((byteBuffer.limit() + 30) - byteBuffer.position()) + zipArchiveEntry.getLocalFileDataExtra().length)) - 4) - 2) & (alignment - 1))));
        }
        byte[] localFileDataExtra = zipArchiveEntry.getLocalFileDataExtra();
        int limit = byteBuffer.limit() - byteBuffer.position();
        int i = limit + 30;
        byte[] bArr = new byte[localFileDataExtra.length + i];
        System.arraycopy(LFH_SIG, 0, bArr, 0, 4);
        int method = zipArchiveEntry.getMethod();
        boolean usesDataDescriptor = usesDataDescriptor(method, z2);
        ZipShort.putShort(versionNeededToExtract(method, hasZip64Extra(zipArchiveEntry), usesDataDescriptor), bArr, 4);
        getGeneralPurposeBits(!z && this.fallbackToUTF8, usesDataDescriptor).encode(bArr, 6);
        ZipShort.putShort(method, bArr, 8);
        ZipUtil.toDosTime(this.calendarInstance, zipArchiveEntry.getTime(), bArr, 10);
        if (z2 || (method != 8 && this.channel == null)) {
            ZipLong.putLong(zipArchiveEntry.getCrc(), bArr, 14);
        } else {
            System.arraycopy(LZERO, 0, bArr, 14, 4);
        }
        if (hasZip64Extra(this.entry.entry)) {
            ZipLong.ZIP64_MAGIC.putLong(bArr, 18);
            ZipLong.ZIP64_MAGIC.putLong(bArr, 22);
        } else if (z2) {
            ZipLong.putLong(zipArchiveEntry.getCompressedSize(), bArr, 18);
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr, 22);
        } else if (method == 8 || this.channel != null) {
            System.arraycopy(LZERO, 0, bArr, 18, 4);
            System.arraycopy(LZERO, 0, bArr, 22, 4);
        } else {
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr, 18);
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr, 22);
        }
        ZipShort.putShort(limit, bArr, 26);
        ZipShort.putShort(localFileDataExtra.length, bArr, 28);
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset(), bArr, 30, limit);
        System.arraycopy(localFileDataExtra, 0, bArr, i, localFileDataExtra.length);
        return bArr;
    }

    private void addUnicodeExtraFields(ZipArchiveEntry zipArchiveEntry, boolean z, ByteBuffer byteBuffer) throws IOException {
        if (this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS || !z) {
            zipArchiveEntry.addExtraField(new UnicodePathExtraField(zipArchiveEntry.getName(), byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position()));
        }
        String comment = zipArchiveEntry.getComment();
        if (comment == null || "".equals(comment)) {
            return;
        }
        boolean canEncode = this.zipEncoding.canEncode(comment);
        if (this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS || !canEncode) {
            ByteBuffer encode = getEntryEncoding(zipArchiveEntry).encode(comment);
            zipArchiveEntry.addExtraField(new UnicodeCommentExtraField(comment, encode.array(), encode.arrayOffset(), encode.limit() - encode.position()));
        }
    }

    protected void writeDataDescriptor(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (usesDataDescriptor(zipArchiveEntry.getMethod(), false)) {
            writeCounted(DD_SIG);
            writeCounted(ZipLong.getBytes(zipArchiveEntry.getCrc()));
            if (!hasZip64Extra(zipArchiveEntry)) {
                writeCounted(ZipLong.getBytes(zipArchiveEntry.getCompressedSize()));
                writeCounted(ZipLong.getBytes(zipArchiveEntry.getSize()));
            } else {
                writeCounted(ZipEightByteInteger.getBytes(zipArchiveEntry.getCompressedSize()));
                writeCounted(ZipEightByteInteger.getBytes(zipArchiveEntry.getSize()));
            }
        }
    }

    protected void writeCentralFileHeader(ZipArchiveEntry zipArchiveEntry) throws IOException {
        writeCounted(createCentralFileHeader(zipArchiveEntry));
    }

    private byte[] createCentralFileHeader(ZipArchiveEntry zipArchiveEntry) throws IOException {
        EntryMetaData entryMetaData = this.metaData.get(zipArchiveEntry);
        boolean z = hasZip64Extra(zipArchiveEntry) || zipArchiveEntry.getCompressedSize() >= 4294967295L || zipArchiveEntry.getSize() >= 4294967295L || entryMetaData.offset >= 4294967295L || zipArchiveEntry.getDiskNumberStart() >= 65535 || this.zip64Mode == Zip64Mode.Always || this.zip64Mode == Zip64Mode.AlwaysWithCompatibility;
        if (z && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException("Archive's size exceeds the limit of 4GByte.");
        }
        handleZip64Extra(zipArchiveEntry, entryMetaData.offset, z);
        return createCentralFileHeader(zipArchiveEntry, getName(zipArchiveEntry), entryMetaData, z);
    }

    private byte[] createCentralFileHeader(ZipArchiveEntry zipArchiveEntry, ByteBuffer byteBuffer, EntryMetaData entryMetaData, boolean z) throws IOException {
        byte[] bArr;
        if (this.isSplitZip) {
            int currentSplitSegmentIndex = ((ZipSplitOutputStream) this.out).getCurrentSplitSegmentIndex();
            if (this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)) == null) {
                this.numberOfCDInDiskData.put(Integer.valueOf(currentSplitSegmentIndex), 1);
            } else {
                this.numberOfCDInDiskData.put(Integer.valueOf(currentSplitSegmentIndex), Integer.valueOf(this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)).intValue() + 1));
            }
        }
        byte[] centralDirectoryExtra = zipArchiveEntry.getCentralDirectoryExtra();
        int length = centralDirectoryExtra.length;
        String comment = zipArchiveEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        ByteBuffer encode = getEntryEncoding(zipArchiveEntry).encode(comment);
        int limit = byteBuffer.limit() - byteBuffer.position();
        int limit2 = encode.limit() - encode.position();
        int i = limit + 46;
        int i2 = i + length;
        byte[] bArr2 = new byte[i2 + limit2];
        System.arraycopy(CFH_SIG, 0, bArr2, 0, 4);
        ZipShort.putShort((zipArchiveEntry.getPlatform() << 8) | (!this.hasUsedZip64 ? 20 : 45), bArr2, 4);
        int method = zipArchiveEntry.getMethod();
        boolean canEncode = this.zipEncoding.canEncode(zipArchiveEntry.getName());
        ZipShort.putShort(versionNeededToExtract(method, z, entryMetaData.usesDataDescriptor), bArr2, 6);
        getGeneralPurposeBits(!canEncode && this.fallbackToUTF8, entryMetaData.usesDataDescriptor).encode(bArr2, 8);
        ZipShort.putShort(method, bArr2, 10);
        ZipUtil.toDosTime(this.calendarInstance, zipArchiveEntry.getTime(), bArr2, 12);
        ZipLong.putLong(zipArchiveEntry.getCrc(), bArr2, 16);
        if (zipArchiveEntry.getCompressedSize() >= 4294967295L || zipArchiveEntry.getSize() >= 4294967295L || this.zip64Mode == Zip64Mode.Always || this.zip64Mode == Zip64Mode.AlwaysWithCompatibility) {
            ZipLong.ZIP64_MAGIC.putLong(bArr2, 20);
            ZipLong.ZIP64_MAGIC.putLong(bArr2, 24);
        } else {
            ZipLong.putLong(zipArchiveEntry.getCompressedSize(), bArr2, 20);
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr2, 24);
        }
        ZipShort.putShort(limit, bArr2, 28);
        ZipShort.putShort(length, bArr2, 30);
        ZipShort.putShort(limit2, bArr2, 32);
        if (!this.isSplitZip) {
            System.arraycopy(ZERO, 0, bArr2, 34, 2);
        } else if (zipArchiveEntry.getDiskNumberStart() >= 65535 || this.zip64Mode == Zip64Mode.Always) {
            ZipShort.putShort(65535, bArr2, 34);
        } else {
            ZipShort.putShort((int) zipArchiveEntry.getDiskNumberStart(), bArr2, 34);
        }
        ZipShort.putShort(zipArchiveEntry.getInternalAttributes(), bArr2, 36);
        ZipLong.putLong(zipArchiveEntry.getExternalAttributes(), bArr2, 38);
        if (entryMetaData.offset >= 4294967295L || this.zip64Mode == Zip64Mode.Always) {
            bArr = centralDirectoryExtra;
            ZipLong.putLong(4294967295L, bArr2, 42);
        } else {
            bArr = centralDirectoryExtra;
            ZipLong.putLong(Math.min(entryMetaData.offset, 4294967295L), bArr2, 42);
        }
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset(), bArr2, 46, limit);
        System.arraycopy(bArr, 0, bArr2, i, length);
        System.arraycopy(encode.array(), encode.arrayOffset(), bArr2, i2, limit2);
        return bArr2;
    }

    private void handleZip64Extra(ZipArchiveEntry zipArchiveEntry, long j, boolean z) {
        if (z) {
            Zip64ExtendedInformationExtraField zip64Extra = getZip64Extra(zipArchiveEntry);
            if (zipArchiveEntry.getCompressedSize() >= 4294967295L || zipArchiveEntry.getSize() >= 4294967295L || this.zip64Mode == Zip64Mode.Always || this.zip64Mode == Zip64Mode.AlwaysWithCompatibility) {
                zip64Extra.setCompressedSize(new ZipEightByteInteger(zipArchiveEntry.getCompressedSize()));
                zip64Extra.setSize(new ZipEightByteInteger(zipArchiveEntry.getSize()));
            } else {
                zip64Extra.setCompressedSize(null);
                zip64Extra.setSize(null);
            }
            boolean z2 = j >= 4294967295L || this.zip64Mode == Zip64Mode.Always;
            boolean z3 = zipArchiveEntry.getDiskNumberStart() >= 65535 || this.zip64Mode == Zip64Mode.Always;
            if (z2 || z3) {
                zip64Extra.setRelativeHeaderOffset(new ZipEightByteInteger(j));
            }
            if (z3) {
                zip64Extra.setDiskStartNumber(new ZipLong(zipArchiveEntry.getDiskNumberStart()));
            }
            zipArchiveEntry.setExtra();
        }
    }

    protected void writeCentralDirectoryEnd() throws IOException {
        if (!this.hasUsedZip64 && this.isSplitZip) {
            ((ZipSplitOutputStream) this.out).prepareToWriteUnsplittableContent(this.eocdLength);
        }
        validateIfZip64IsNeededInEOCD();
        writeCounted(EOCD_SIG);
        int i = 0;
        int currentSplitSegmentIndex = this.isSplitZip ? ((ZipSplitOutputStream) this.out).getCurrentSplitSegmentIndex() : 0;
        writeCounted(ZipShort.getBytes(currentSplitSegmentIndex));
        writeCounted(ZipShort.getBytes((int) this.cdDiskNumberStart));
        int size = this.entries.size();
        if (!this.isSplitZip) {
            i = size;
        } else if (this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)) != null) {
            i = this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)).intValue();
        }
        writeCounted(ZipShort.getBytes(Math.min(i, 65535)));
        writeCounted(ZipShort.getBytes(Math.min(size, 65535)));
        writeCounted(ZipLong.getBytes(Math.min(this.cdLength, 4294967295L)));
        writeCounted(ZipLong.getBytes(Math.min(this.cdOffset, 4294967295L)));
        ByteBuffer encode = this.zipEncoding.encode(this.comment);
        int limit = encode.limit() - encode.position();
        writeCounted(ZipShort.getBytes(limit));
        this.streamCompressor.writeCounted(encode.array(), encode.arrayOffset(), limit);
    }

    private void validateIfZip64IsNeededInEOCD() throws Zip64RequiredException {
        if (this.zip64Mode != Zip64Mode.Never) {
            return;
        }
        int currentSplitSegmentIndex = this.isSplitZip ? ((ZipSplitOutputStream) this.out).getCurrentSplitSegmentIndex() : 0;
        if (currentSplitSegmentIndex >= 65535) {
            throw new Zip64RequiredException("Number of the disk of End Of Central Directory exceeds the limit of 65535.");
        }
        if (this.cdDiskNumberStart >= 65535) {
            throw new Zip64RequiredException("Number of the disk with the start of Central Directory exceeds the limit of 65535.");
        }
        if ((this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)) != null ? this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)).intValue() : 0) >= 65535) {
            throw new Zip64RequiredException("Number of entries on this disk exceeds the limit of 65535.");
        }
        if (this.entries.size() >= 65535) {
            throw new Zip64RequiredException("Archive contains more than 65535 entries.");
        }
        if (this.cdLength >= 4294967295L) {
            throw new Zip64RequiredException("The size of the entire central directory exceeds the limit of 4GByte.");
        }
        if (this.cdOffset >= 4294967295L) {
            throw new Zip64RequiredException("Archive's size exceeds the limit of 4GByte.");
        }
    }

    protected void writeZip64CentralDirectory() throws IOException {
        if (this.zip64Mode == Zip64Mode.Never) {
            return;
        }
        if (!this.hasUsedZip64 && shouldUseZip64EOCD()) {
            this.hasUsedZip64 = true;
        }
        if (this.hasUsedZip64) {
            long totalBytesWritten = this.streamCompressor.getTotalBytesWritten();
            long j = 0;
            if (this.isSplitZip) {
                ZipSplitOutputStream zipSplitOutputStream = (ZipSplitOutputStream) this.out;
                totalBytesWritten = zipSplitOutputStream.getCurrentSplitSegmentBytesWritten();
                j = zipSplitOutputStream.getCurrentSplitSegmentIndex();
            }
            writeOut(ZIP64_EOCD_SIG);
            writeOut(ZipEightByteInteger.getBytes(44L));
            writeOut(ZipShort.getBytes(45));
            writeOut(ZipShort.getBytes(45));
            int i = 0;
            int currentSplitSegmentIndex = this.isSplitZip ? ((ZipSplitOutputStream) this.out).getCurrentSplitSegmentIndex() : 0;
            writeOut(ZipLong.getBytes(currentSplitSegmentIndex));
            writeOut(ZipLong.getBytes(this.cdDiskNumberStart));
            if (this.isSplitZip) {
                if (this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)) != null) {
                    i = this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)).intValue();
                }
            } else {
                i = this.entries.size();
            }
            writeOut(ZipEightByteInteger.getBytes(i));
            writeOut(ZipEightByteInteger.getBytes(this.entries.size()));
            writeOut(ZipEightByteInteger.getBytes(this.cdLength));
            writeOut(ZipEightByteInteger.getBytes(this.cdOffset));
            if (this.isSplitZip) {
                ((ZipSplitOutputStream) this.out).prepareToWriteUnsplittableContent(this.eocdLength + 20);
            }
            writeOut(ZIP64_EOCD_LOC_SIG);
            writeOut(ZipLong.getBytes(j));
            writeOut(ZipEightByteInteger.getBytes(totalBytesWritten));
            if (this.isSplitZip) {
                writeOut(ZipLong.getBytes(((ZipSplitOutputStream) this.out).getCurrentSplitSegmentIndex() + 1));
            } else {
                writeOut(ONE);
            }
        }
    }

    private boolean shouldUseZip64EOCD() {
        int currentSplitSegmentIndex = this.isSplitZip ? ((ZipSplitOutputStream) this.out).getCurrentSplitSegmentIndex() : 0;
        return currentSplitSegmentIndex >= 65535 || this.cdDiskNumberStart >= 65535 || (this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)) == null ? 0 : this.numberOfCDInDiskData.get(Integer.valueOf(currentSplitSegmentIndex)).intValue()) >= 65535 || this.entries.size() >= 65535 || this.cdLength >= 4294967295L || this.cdOffset >= 4294967295L;
    }

    protected final void writeOut(byte[] bArr) throws IOException {
        this.streamCompressor.writeOut(bArr, 0, bArr.length);
    }

    protected final void writeOut(byte[] bArr, int i, int i2) throws IOException {
        this.streamCompressor.writeOut(bArr, i, i2);
    }

    private GeneralPurposeBit getGeneralPurposeBits(boolean z, boolean z2) {
        GeneralPurposeBit generalPurposeBit = new GeneralPurposeBit();
        generalPurposeBit.useUTF8ForNames(this.useUTF8Flag || z);
        if (z2) {
            generalPurposeBit.useDataDescriptor(true);
        }
        return generalPurposeBit;
    }

    private int versionNeededToExtract(int i, boolean z, boolean z2) {
        if (z) {
            return 45;
        }
        if (z2) {
            return 20;
        }
        return versionNeededToExtractMethod(i);
    }

    private boolean usesDataDescriptor(int i, boolean z) {
        return !z && i == 8 && this.channel == null;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new ZipArchiveEntry(file, str);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArchiveEntry createArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new ZipArchiveEntry(path, str, new LinkOption[0]);
    }

    private Zip64ExtendedInformationExtraField getZip64Extra(ZipArchiveEntry zipArchiveEntry) {
        CurrentEntry currentEntry = this.entry;
        if (currentEntry != null) {
            currentEntry.causedUseOfZip64 = !this.hasUsedZip64;
        }
        this.hasUsedZip64 = true;
        ZipExtraField extraField = zipArchiveEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = extraField instanceof Zip64ExtendedInformationExtraField ? (Zip64ExtendedInformationExtraField) extraField : null;
        if (zip64ExtendedInformationExtraField == null) {
            zip64ExtendedInformationExtraField = new Zip64ExtendedInformationExtraField();
        }
        zipArchiveEntry.addAsFirstExtraField(zip64ExtendedInformationExtraField);
        return zip64ExtendedInformationExtraField;
    }

    private boolean hasZip64Extra(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID) instanceof Zip64ExtendedInformationExtraField;
    }

    private Zip64Mode getEffectiveZip64Mode(ZipArchiveEntry zipArchiveEntry) {
        if (this.zip64Mode != Zip64Mode.AsNeeded || this.channel != null || zipArchiveEntry.getMethod() != 8 || zipArchiveEntry.getSize() != -1) {
            return this.zip64Mode;
        }
        return Zip64Mode.Never;
    }

    private ZipEncoding getEntryEncoding(ZipArchiveEntry zipArchiveEntry) {
        return (this.zipEncoding.canEncode(zipArchiveEntry.getName()) || !this.fallbackToUTF8) ? this.zipEncoding : ZipEncodingHelper.UTF8_ZIP_ENCODING;
    }

    private ByteBuffer getName(ZipArchiveEntry zipArchiveEntry) throws IOException {
        return getEntryEncoding(zipArchiveEntry).encode(zipArchiveEntry.getName());
    }

    void destroy() throws IOException {
        try {
            if (this.channel != null) {
                this.channel.close();
            }
        } finally {
            OutputStream outputStream = this.out;
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class UnicodeExtraFieldPolicy {
        public static final UnicodeExtraFieldPolicy ALWAYS = new UnicodeExtraFieldPolicy(SystemParameters.SYSVAL_DOCKED_REGISTER_STRATEGY_ALWAYS);
        public static final UnicodeExtraFieldPolicy NEVER = new UnicodeExtraFieldPolicy("never");
        public static final UnicodeExtraFieldPolicy NOT_ENCODEABLE = new UnicodeExtraFieldPolicy("not encodeable");
        private final String name;

        private UnicodeExtraFieldPolicy(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static final class CurrentEntry {
        private long bytesRead;
        private boolean causedUseOfZip64;
        private long dataStart;
        private final ZipArchiveEntry entry;
        private boolean hasWritten;
        private long localDataStart;

        private CurrentEntry(ZipArchiveEntry zipArchiveEntry) {
            this.entry = zipArchiveEntry;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static final class EntryMetaData {
        private final long offset;
        private final boolean usesDataDescriptor;

        private EntryMetaData(long j, boolean z) {
            this.offset = j;
            this.usesDataDescriptor = z;
        }
    }
}
