package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* loaded from: classes8.dex */
public class ZipArchiveInputStream extends ArchiveInputStream implements InputStreamStatistics {
    private static final int CFH_LEN = 46;
    private static final int LFH_LEN = 30;
    private static final long TWO_EXP_32 = 4294967296L;
    private static final String USE_ZIPFILE_INSTEAD_OF_STREAM_DISCLAIMER = " while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile";
    private boolean allowStoredEntriesWithDataDescriptor;
    private final ByteBuffer buf;
    private boolean closed;
    private CurrentEntry current;
    final String encoding;
    private int entriesRead;
    private boolean hitCentralDirectory;

    /* renamed from: in */
    private final InputStream f8923in;
    private final Inflater inf;
    private ByteArrayInputStream lastStoredEntry;
    private final byte[] lfhBuf;
    private final byte[] shortBuf;
    private final byte[] skipBuf;
    private final boolean skipSplitSig;
    private final byte[] twoDwordBuf;
    private long uncompressedCount;
    private final boolean useUnicodeExtraFields;
    private final byte[] wordBuf;
    private final ZipEncoding zipEncoding;
    private static final byte[] LFH = ZipLong.LFH_SIG.getBytes();
    private static final byte[] CFH = ZipLong.CFH_SIG.getBytes();

    /* renamed from: DD */
    private static final byte[] f8922DD = ZipLong.DD_SIG.getBytes();
    private static final byte[] APK_SIGNING_BLOCK_MAGIC = {65, 80, TarConstants.LF_GNUTYPE_LONGLINK, 32, TarConstants.LF_GNUTYPE_SPARSE, 105, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 32, 66, 108, 111, 99, 107, 32, TarConstants.LF_BLK, TarConstants.LF_SYMLINK};
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    public ZipArchiveInputStream(InputStream inputStream) {
        this(inputStream, "UTF8");
    }

    public ZipArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, str, true);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z) {
        this(inputStream, str, z, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z, boolean z2) {
        this(inputStream, str, z, z2, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z, boolean z2, boolean z3) {
        this.inf = new Inflater(true);
        this.buf = ByteBuffer.allocate(512);
        this.lfhBuf = new byte[30];
        this.skipBuf = new byte[1024];
        this.shortBuf = new byte[2];
        this.wordBuf = new byte[4];
        this.twoDwordBuf = new byte[16];
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.useUnicodeExtraFields = z;
        this.f8923in = new PushbackInputStream(inputStream, this.buf.capacity());
        this.allowStoredEntriesWithDataDescriptor = z2;
        this.skipSplitSig = z3;
        this.buf.limit(0);
    }

    public ZipArchiveEntry getNextZipEntry() throws IOException {
        boolean z;
        ZipLong zipLong;
        ZipLong zipLong2;
        this.uncompressedCount = 0L;
        C80971 c80971 = null;
        if (!this.closed && !this.hitCentralDirectory) {
            if (this.current != null) {
                closeEntry();
                z = false;
            } else {
                z = true;
            }
            long bytesRead = getBytesRead();
            try {
                if (z) {
                    readFirstLocalFileHeader();
                } else {
                    readFully(this.lfhBuf);
                }
                ZipLong zipLong3 = new ZipLong(this.lfhBuf);
                if (!zipLong3.equals(ZipLong.LFH_SIG)) {
                    if (zipLong3.equals(ZipLong.CFH_SIG) || zipLong3.equals(ZipLong.AED_SIG) || isApkSigningBlock(this.lfhBuf)) {
                        this.hitCentralDirectory = true;
                        skipRemainderOfArchive();
                        return null;
                    }
                    throw new ZipException(String.format("Unexpected record signature: 0X%X", Long.valueOf(zipLong3.getValue())));
                }
                this.current = new CurrentEntry(c80971);
                this.current.entry.setPlatform((ZipShort.getValue(this.lfhBuf, 4) >> 8) & 15);
                GeneralPurposeBit parse = GeneralPurposeBit.parse(this.lfhBuf, 6);
                boolean usesUTF8ForNames = parse.usesUTF8ForNames();
                ZipEncoding zipEncoding = usesUTF8ForNames ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
                this.current.hasDataDescriptor = parse.usesDataDescriptor();
                this.current.entry.setGeneralPurposeBit(parse);
                this.current.entry.setMethod(ZipShort.getValue(this.lfhBuf, 8));
                this.current.entry.setTime(ZipUtil.dosToJavaTime(ZipLong.getValue(this.lfhBuf, 10)));
                if (this.current.hasDataDescriptor) {
                    zipLong = null;
                    zipLong2 = null;
                } else {
                    this.current.entry.setCrc(ZipLong.getValue(this.lfhBuf, 14));
                    zipLong = new ZipLong(this.lfhBuf, 18);
                    zipLong2 = new ZipLong(this.lfhBuf, 22);
                }
                int value = ZipShort.getValue(this.lfhBuf, 26);
                int value2 = ZipShort.getValue(this.lfhBuf, 28);
                byte[] readRange = readRange(value);
                this.current.entry.setName(zipEncoding.decode(readRange), readRange);
                if (usesUTF8ForNames) {
                    this.current.entry.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
                }
                try {
                    this.current.entry.setExtra(readRange(value2));
                    if (!usesUTF8ForNames && this.useUnicodeExtraFields) {
                        ZipUtil.setNameAndCommentFromExtraFields(this.current.entry, readRange, null);
                    }
                    processZip64Extra(zipLong2, zipLong);
                    this.current.entry.setLocalHeaderOffset(bytesRead);
                    this.current.entry.setDataOffset(getBytesRead());
                    this.current.entry.setStreamContiguous(true);
                    ZipMethod methodByCode = ZipMethod.getMethodByCode(this.current.entry.getMethod());
                    if (this.current.entry.getCompressedSize() == -1) {
                        if (methodByCode == ZipMethod.ENHANCED_DEFLATED) {
                            this.current.f8925in = new Deflate64CompressorInputStream(this.f8923in);
                        }
                    } else if (ZipUtil.canHandleEntryData(this.current.entry) && methodByCode != ZipMethod.STORED && methodByCode != ZipMethod.DEFLATED) {
                        BoundedInputStream boundedInputStream = new BoundedInputStream(this.f8923in, this.current.entry.getCompressedSize());
                        int i = C80971.$SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[methodByCode.ordinal()];
                        if (i == 1) {
                            this.current.f8925in = new UnshrinkingInputStream(boundedInputStream);
                        } else if (i == 2) {
                            try {
                                this.current.f8925in = new ExplodingInputStream(this.current.entry.getGeneralPurposeBit().getSlidingDictionarySize(), this.current.entry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), boundedInputStream);
                            } catch (IllegalArgumentException e) {
                                throw new IOException("bad IMPLODE data", e);
                            }
                        } else if (i == 3) {
                            this.current.f8925in = new BZip2CompressorInputStream(boundedInputStream);
                        } else if (i == 4) {
                            this.current.f8925in = new Deflate64CompressorInputStream(boundedInputStream);
                        }
                    }
                    this.entriesRead++;
                    return this.current.entry;
                } catch (RuntimeException e2) {
                    ZipException zipException = new ZipException("Invalid extra data in entry " + this.current.entry.getName());
                    zipException.initCause(e2);
                    throw zipException;
                }
            } catch (EOFException unused) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.compress.archivers.zip.ZipArchiveInputStream$1 */
    /* loaded from: classes8.dex */
    public static /* synthetic */ class C80971 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod = new int[ZipMethod.values().length];

        static {
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.UNSHRINKING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.IMPLODING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.BZIP2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.ENHANCED_DEFLATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void readFirstLocalFileHeader() throws IOException {
        readFully(this.lfhBuf);
        ZipLong zipLong = new ZipLong(this.lfhBuf);
        if (!this.skipSplitSig && zipLong.equals(ZipLong.DD_SIG)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.SPLITTING);
        }
        if (zipLong.equals(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER) || zipLong.equals(ZipLong.DD_SIG)) {
            byte[] bArr = new byte[4];
            readFully(bArr);
            byte[] bArr2 = this.lfhBuf;
            System.arraycopy(bArr2, 4, bArr2, 0, 26);
            System.arraycopy(bArr, 0, this.lfhBuf, 26, 4);
        }
    }

    private void processZip64Extra(ZipLong zipLong, ZipLong zipLong2) throws ZipException {
        ZipExtraField extraField = this.current.entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (extraField != null && !(extraField instanceof Zip64ExtendedInformationExtraField)) {
            throw new ZipException("archive contains unparseable zip64 extra field");
        }
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) extraField;
        this.current.usesZip64 = zip64ExtendedInformationExtraField != null;
        if (this.current.hasDataDescriptor) {
            return;
        }
        if (zip64ExtendedInformationExtraField == null || !(ZipLong.ZIP64_MAGIC.equals(zipLong2) || ZipLong.ZIP64_MAGIC.equals(zipLong))) {
            if (zipLong2 == null || zipLong == null) {
                return;
            }
            if (zipLong2.getValue() < 0) {
                throw new ZipException("broken archive, entry with negative compressed size");
            }
            this.current.entry.setCompressedSize(zipLong2.getValue());
            if (zipLong.getValue() < 0) {
                throw new ZipException("broken archive, entry with negative size");
            }
            this.current.entry.setSize(zipLong.getValue());
            return;
        }
        if (zip64ExtendedInformationExtraField.getCompressedSize() == null || zip64ExtendedInformationExtraField.getSize() == null) {
            throw new ZipException("archive contains corrupted zip64 extra field");
        }
        long longValue = zip64ExtendedInformationExtraField.getCompressedSize().getLongValue();
        if (longValue < 0) {
            throw new ZipException("broken archive, entry with negative compressed size");
        }
        this.current.entry.setCompressedSize(longValue);
        long longValue2 = zip64ExtendedInformationExtraField.getSize().getLongValue();
        if (longValue2 < 0) {
            throw new ZipException("broken archive, entry with negative size");
        }
        this.current.entry.setSize(longValue2);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextZipEntry();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        if (!(archiveEntry instanceof ZipArchiveEntry)) {
            return false;
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
        return ZipUtil.canHandleEntryData(zipArchiveEntry) && supportsDataDescriptorFor(zipArchiveEntry) && supportsCompressedSizeFor(zipArchiveEntry);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read;
        if (i2 == 0) {
            return 0;
        }
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        CurrentEntry currentEntry = this.current;
        if (currentEntry == null) {
            return -1;
        }
        if (i > bArr.length || i2 < 0 || i < 0 || bArr.length - i < i2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ZipUtil.checkRequestedFeatures(currentEntry.entry);
        if (!supportsDataDescriptorFor(this.current.entry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.DATA_DESCRIPTOR, this.current.entry);
        }
        if (!supportsCompressedSizeFor(this.current.entry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.UNKNOWN_COMPRESSED_SIZE, this.current.entry);
        }
        if (this.current.entry.getMethod() == 0) {
            read = readStored(bArr, i, i2);
        } else if (this.current.entry.getMethod() == 8) {
            read = readDeflated(bArr, i, i2);
        } else {
            if (this.current.entry.getMethod() != ZipMethod.UNSHRINKING.getCode() && this.current.entry.getMethod() != ZipMethod.IMPLODING.getCode() && this.current.entry.getMethod() != ZipMethod.ENHANCED_DEFLATED.getCode() && this.current.entry.getMethod() != ZipMethod.BZIP2.getCode()) {
                throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(this.current.entry.getMethod()), this.current.entry);
            }
            read = this.current.f8925in.read(bArr, i, i2);
        }
        if (read >= 0) {
            this.current.crc.update(bArr, i, read);
            this.uncompressedCount += read;
        }
        return read;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        if (this.current.entry.getMethod() == 0) {
            return this.current.bytesRead;
        }
        if (this.current.entry.getMethod() == 8) {
            return getBytesInflated();
        }
        if (this.current.entry.getMethod() == ZipMethod.UNSHRINKING.getCode()) {
            return ((UnshrinkingInputStream) this.current.f8925in).getCompressedCount();
        }
        if (this.current.entry.getMethod() == ZipMethod.IMPLODING.getCode()) {
            return ((ExplodingInputStream) this.current.f8925in).getCompressedCount();
        }
        if (this.current.entry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode()) {
            return ((Deflate64CompressorInputStream) this.current.f8925in).getCompressedCount();
        }
        if (this.current.entry.getMethod() == ZipMethod.BZIP2.getCode()) {
            return ((BZip2CompressorInputStream) this.current.f8925in).getCompressedCount();
        }
        return -1L;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getUncompressedCount() {
        return this.uncompressedCount;
    }

    private int readStored(byte[] bArr, int i, int i2) throws IOException {
        if (this.current.hasDataDescriptor) {
            if (this.lastStoredEntry == null) {
                readStoredEntry();
            }
            return this.lastStoredEntry.read(bArr, i, i2);
        }
        long size = this.current.entry.getSize();
        if (this.current.bytesRead >= size) {
            return -1;
        }
        if (this.buf.position() >= this.buf.limit()) {
            this.buf.position(0);
            int read = this.f8923in.read(this.buf.array());
            if (read == -1) {
                this.buf.limit(0);
                throw new IOException("Truncated ZIP file");
            }
            this.buf.limit(read);
            count(read);
            this.current.bytesReadFromStream += read;
        }
        int min = Math.min(this.buf.remaining(), i2);
        if (size - this.current.bytesRead < min) {
            min = (int) (size - this.current.bytesRead);
        }
        this.buf.get(bArr, i, min);
        this.current.bytesRead += min;
        return min;
    }

    private int readDeflated(byte[] bArr, int i, int i2) throws IOException {
        int readFromInflater = readFromInflater(bArr, i, i2);
        if (readFromInflater <= 0) {
            if (this.inf.finished()) {
                return -1;
            }
            if (this.inf.needsDictionary()) {
                throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
            }
            if (readFromInflater == -1) {
                throw new IOException("Truncated ZIP file");
            }
        }
        return readFromInflater;
    }

    private int readFromInflater(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (true) {
            if (this.inf.needsInput()) {
                int fill = fill();
                if (fill > 0) {
                    this.current.bytesReadFromStream += this.buf.limit();
                } else if (fill == -1) {
                    return -1;
                }
            }
            try {
                i3 = this.inf.inflate(bArr, i, i2);
                if (i3 != 0 || !this.inf.needsInput()) {
                    break;
                }
            } catch (DataFormatException e) {
                throw ((IOException) new ZipException(e.getMessage()).initCause(e));
            }
        }
        return i3;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        try {
            this.f8923in.close();
        } finally {
            this.inf.end();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        while (j2 < j) {
            long j3 = j - j2;
            byte[] bArr = this.skipBuf;
            if (bArr.length <= j3) {
                j3 = bArr.length;
            }
            int read = read(bArr, 0, (int) j3);
            if (read == -1) {
                return j2;
            }
            j2 += read;
        }
        return j2;
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < ZipArchiveOutputStream.LFH_SIG.length) {
            return false;
        }
        return checksig(bArr, ZipArchiveOutputStream.LFH_SIG) || checksig(bArr, ZipArchiveOutputStream.EOCD_SIG) || checksig(bArr, ZipArchiveOutputStream.DD_SIG) || checksig(bArr, ZipLong.SINGLE_SEGMENT_SPLIT_MARKER.getBytes());
    }

    private static boolean checksig(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    private void closeEntry() throws IOException {
        long j;
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        if (this.current == null) {
            return;
        }
        if (currentEntryHasOutstandingBytes()) {
            drainCurrentEntryData();
        } else {
            skip(Long.MAX_VALUE);
            if (this.current.entry.getMethod() == 8) {
                j = getBytesInflated();
            } else {
                j = this.current.bytesRead;
            }
            int i = (int) (this.current.bytesReadFromStream - j);
            if (i > 0) {
                pushback(this.buf.array(), this.buf.limit() - i, i);
                this.current.bytesReadFromStream -= i;
            }
            if (currentEntryHasOutstandingBytes()) {
                drainCurrentEntryData();
            }
        }
        if (this.lastStoredEntry == null && this.current.hasDataDescriptor) {
            readDataDescriptor();
        }
        this.inf.reset();
        this.buf.clear().flip();
        this.current = null;
        this.lastStoredEntry = null;
    }

    private boolean currentEntryHasOutstandingBytes() {
        return this.current.bytesReadFromStream <= this.current.entry.getCompressedSize() && !this.current.hasDataDescriptor;
    }

    private void drainCurrentEntryData() throws IOException {
        long compressedSize = this.current.entry.getCompressedSize() - this.current.bytesReadFromStream;
        while (compressedSize > 0) {
            long read = this.f8923in.read(this.buf.array(), 0, (int) Math.min(this.buf.capacity(), compressedSize));
            if (read >= 0) {
                count(read);
                compressedSize -= read;
            } else {
                throw new EOFException("Truncated ZIP entry: " + ArchiveUtils.sanitize(this.current.entry.getName()));
            }
        }
    }

    private long getBytesInflated() {
        long bytesRead = this.inf.getBytesRead();
        if (this.current.bytesReadFromStream >= 4294967296L) {
            while (true) {
                long j = bytesRead + 4294967296L;
                if (j > this.current.bytesReadFromStream) {
                    break;
                }
                bytesRead = j;
            }
        }
        return bytesRead;
    }

    private int fill() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        int read = this.f8923in.read(this.buf.array());
        if (read > 0) {
            this.buf.limit(read);
            count(this.buf.limit());
            this.inf.setInput(this.buf.array(), 0, this.buf.limit());
        }
        return read;
    }

    private void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0);
    }

    private void readFully(byte[] bArr, int i) throws IOException {
        int length = bArr.length - i;
        int readFully = IOUtils.readFully(this.f8923in, bArr, i, length);
        count(readFully);
        if (readFully < length) {
            throw new EOFException();
        }
    }

    private byte[] readRange(int i) throws IOException {
        byte[] readRange = IOUtils.readRange(this.f8923in, i);
        count(readRange.length);
        if (readRange.length >= i) {
            return readRange;
        }
        throw new EOFException();
    }

    private void readDataDescriptor() throws IOException {
        readFully(this.wordBuf);
        ZipLong zipLong = new ZipLong(this.wordBuf);
        if (ZipLong.DD_SIG.equals(zipLong)) {
            readFully(this.wordBuf);
            zipLong = new ZipLong(this.wordBuf);
        }
        this.current.entry.setCrc(zipLong.getValue());
        readFully(this.twoDwordBuf);
        ZipLong zipLong2 = new ZipLong(this.twoDwordBuf, 8);
        if (zipLong2.equals(ZipLong.CFH_SIG) || zipLong2.equals(ZipLong.LFH_SIG)) {
            pushback(this.twoDwordBuf, 8, 8);
            long value = ZipLong.getValue(this.twoDwordBuf);
            if (value < 0) {
                throw new ZipException("broken archive, entry with negative compressed size");
            }
            this.current.entry.setCompressedSize(value);
            long value2 = ZipLong.getValue(this.twoDwordBuf, 4);
            if (value2 < 0) {
                throw new ZipException("broken archive, entry with negative size");
            }
            this.current.entry.setSize(value2);
            return;
        }
        long longValue = ZipEightByteInteger.getLongValue(this.twoDwordBuf);
        if (longValue < 0) {
            throw new ZipException("broken archive, entry with negative compressed size");
        }
        this.current.entry.setCompressedSize(longValue);
        long longValue2 = ZipEightByteInteger.getLongValue(this.twoDwordBuf, 8);
        if (longValue2 < 0) {
            throw new ZipException("broken archive, entry with negative size");
        }
        this.current.entry.setSize(longValue2);
    }

    private boolean supportsDataDescriptorFor(ZipArchiveEntry zipArchiveEntry) {
        return !zipArchiveEntry.getGeneralPurposeBit().usesDataDescriptor() || (this.allowStoredEntriesWithDataDescriptor && zipArchiveEntry.getMethod() == 0) || zipArchiveEntry.getMethod() == 8 || zipArchiveEntry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode();
    }

    private boolean supportsCompressedSizeFor(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getCompressedSize() != -1 || zipArchiveEntry.getMethod() == 8 || zipArchiveEntry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode() || (zipArchiveEntry.getGeneralPurposeBit().usesDataDescriptor() && this.allowStoredEntriesWithDataDescriptor && zipArchiveEntry.getMethod() == 0);
    }

    private void readStoredEntry() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = this.current.usesZip64 ? 20 : 12;
        boolean z = false;
        int i2 = 0;
        while (!z) {
            int read = this.f8923in.read(this.buf.array(), i2, 512 - i2);
            if (read <= 0) {
                throw new IOException("Truncated ZIP file");
            }
            int i3 = read + i2;
            if (i3 < 4) {
                i2 = i3;
            } else {
                z = bufferContainsSignature(byteArrayOutputStream, i2, read, i);
                if (!z) {
                    i2 = cacheBytesRead(byteArrayOutputStream, i2, read, i);
                }
            }
        }
        if (this.current.entry.getCompressedSize() != this.current.entry.getSize()) {
            throw new ZipException("compressed and uncompressed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray.length != this.current.entry.getSize()) {
            throw new ZipException("actual and claimed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        this.lastStoredEntry = new ByteArrayInputStream(byteArray);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean bufferContainsSignature(ByteArrayOutputStream byteArrayOutputStream, int i, int i2, int i3) throws IOException {
        int i4;
        boolean z = false;
        int i5 = 0;
        while (!z) {
            int i6 = i + i2;
            if (i5 >= i6 - 4) {
                break;
            }
            if (this.buf.array()[i5] == LFH[0]) {
                if (this.buf.array()[i5 + 1] == LFH[1]) {
                    if (i5 < i3 || this.buf.array()[i5 + 2] != LFH[2] || this.buf.array()[i5 + 3] != LFH[3]) {
                        int i7 = i5 + 2;
                        if (this.buf.array()[i7] != CFH[2] || this.buf.array()[i5 + 3] != CFH[3]) {
                            r6 = (this.buf.array()[i7] == f8922DD[2] && this.buf.array()[i5 + 3] == f8922DD[3]) ? true : z;
                            i4 = i5;
                            if (r6) {
                                pushback(this.buf.array(), i4, i6 - i4);
                                byteArrayOutputStream.write(this.buf.array(), 0, i4);
                                readDataDescriptor();
                            }
                            z = r6;
                        }
                    }
                    i4 = i5 - i3;
                    if (r6) {
                    }
                    z = r6;
                }
            }
            i5++;
        }
        return z;
    }

    private int cacheBytesRead(ByteArrayOutputStream byteArrayOutputStream, int i, int i2, int i3) {
        int i4 = i + i2;
        int i5 = (i4 - i3) - 3;
        if (i5 <= 0) {
            return i4;
        }
        byteArrayOutputStream.write(this.buf.array(), 0, i5);
        int i6 = i3 + 3;
        System.arraycopy(this.buf.array(), i5, this.buf.array(), 0, i6);
        return i6;
    }

    private void pushback(byte[] bArr, int i, int i2) throws IOException {
        ((PushbackInputStream) this.f8923in).unread(bArr, i, i2);
        pushedBackBytes(i2);
    }

    private void skipRemainderOfArchive() throws IOException {
        int i = this.entriesRead;
        if (i > 0) {
            realSkip((i * 46) - 30);
            if (findEocdRecord()) {
                realSkip(16L);
                readFully(this.shortBuf);
                int value = ZipShort.getValue(this.shortBuf);
                if (value >= 0) {
                    realSkip(value);
                    return;
                }
            }
        }
        throw new IOException("Truncated ZIP file");
    }

    private boolean findEocdRecord() throws IOException {
        boolean z = false;
        int i = -1;
        while (true) {
            if (!z) {
                int readOneByte = readOneByte();
                if (readOneByte <= -1) {
                    break;
                }
                i = readOneByte;
            }
            if (isFirstByteOfEocdSig(i)) {
                i = readOneByte();
                if (i == ZipArchiveOutputStream.EOCD_SIG[1]) {
                    i = readOneByte();
                    if (i == ZipArchiveOutputStream.EOCD_SIG[2]) {
                        i = readOneByte();
                        if (i == -1) {
                            break;
                        }
                        if (i == ZipArchiveOutputStream.EOCD_SIG[3]) {
                            return true;
                        }
                        z = isFirstByteOfEocdSig(i);
                    } else {
                        if (i == -1) {
                            break;
                        }
                        z = isFirstByteOfEocdSig(i);
                    }
                } else {
                    if (i == -1) {
                        break;
                    }
                    z = isFirstByteOfEocdSig(i);
                }
            } else {
                z = false;
            }
        }
        return false;
    }

    private void realSkip(long j) throws IOException {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        while (j2 < j) {
            long j3 = j - j2;
            InputStream inputStream = this.f8923in;
            byte[] bArr = this.skipBuf;
            if (bArr.length <= j3) {
                j3 = bArr.length;
            }
            int read = inputStream.read(bArr, 0, (int) j3);
            if (read == -1) {
                return;
            }
            count(read);
            j2 += read;
        }
    }

    private int readOneByte() throws IOException {
        int read = this.f8923in.read();
        if (read != -1) {
            count(1);
        }
        return read;
    }

    private boolean isFirstByteOfEocdSig(int i) {
        return i == ZipArchiveOutputStream.EOCD_SIG[0];
    }

    private boolean isApkSigningBlock(byte[] bArr) throws IOException {
        BigInteger add = ZipEightByteInteger.getValue(bArr).add(BigInteger.valueOf((8 - bArr.length) - APK_SIGNING_BLOCK_MAGIC.length));
        byte[] bArr2 = new byte[APK_SIGNING_BLOCK_MAGIC.length];
        try {
            if (add.signum() < 0) {
                int length = bArr.length + add.intValue();
                if (length < 8) {
                    return false;
                }
                int abs = Math.abs(add.intValue());
                System.arraycopy(bArr, length, bArr2, 0, Math.min(abs, bArr2.length));
                if (abs < bArr2.length) {
                    readFully(bArr2, abs);
                }
            } else {
                while (add.compareTo(LONG_MAX) > 0) {
                    realSkip(Long.MAX_VALUE);
                    add = add.add(LONG_MAX.negate());
                }
                realSkip(add.longValue());
                readFully(bArr2);
            }
            return Arrays.equals(bArr2, APK_SIGNING_BLOCK_MAGIC);
        } catch (EOFException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static final class CurrentEntry {
        private long bytesRead;
        private long bytesReadFromStream;
        private final CRC32 crc;
        private final ZipArchiveEntry entry;
        private boolean hasDataDescriptor;

        /* renamed from: in */
        private InputStream f8925in;
        private boolean usesZip64;

        private CurrentEntry() {
            this.entry = new ZipArchiveEntry();
            this.crc = new CRC32();
        }

        /* synthetic */ CurrentEntry(C80971 c80971) {
            this();
        }

        static /* synthetic */ long access$708(CurrentEntry currentEntry) {
            long j = currentEntry.bytesReadFromStream;
            currentEntry.bytesReadFromStream = 1 + j;
            return j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class BoundedInputStream extends InputStream {

        /* renamed from: in */
        private final InputStream f8924in;
        private final long max;
        private long pos;

        public BoundedInputStream(InputStream inputStream, long j) {
            this.max = j;
            this.f8924in = inputStream;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            long j = this.max;
            if (j >= 0 && this.pos >= j) {
                return -1;
            }
            int read = this.f8924in.read();
            this.pos++;
            ZipArchiveInputStream.this.count(1);
            CurrentEntry.access$708(ZipArchiveInputStream.this.current);
            return read;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (i2 == 0) {
                return 0;
            }
            long j = this.max;
            if (j >= 0 && this.pos >= j) {
                return -1;
            }
            long j2 = this.max;
            int read = this.f8924in.read(bArr, i, (int) (j2 >= 0 ? Math.min(i2, j2 - this.pos) : i2));
            if (read == -1) {
                return -1;
            }
            long j3 = read;
            this.pos += j3;
            ZipArchiveInputStream.this.count(read);
            ZipArchiveInputStream.this.current.bytesReadFromStream += j3;
            return read;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = this.max;
            if (j2 >= 0) {
                j = Math.min(j, j2 - this.pos);
            }
            long skip = IOUtils.skip(this.f8924in, j);
            this.pos += skip;
            return skip;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            long j = this.max;
            if (j < 0 || this.pos < j) {
                return this.f8924in.available();
            }
            return 0;
        }
    }
}
