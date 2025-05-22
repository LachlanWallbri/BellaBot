package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes8.dex */
public class FramedSnappyCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    static final int COMPRESSED_CHUNK_TYPE = 0;
    static final long MASK_OFFSET = 2726488792L;
    private static final int MAX_SKIPPABLE_TYPE = 253;
    private static final int MAX_UNSKIPPABLE_TYPE = 127;
    private static final int MIN_UNSKIPPABLE_TYPE = 2;
    private static final int PADDING_CHUNK_TYPE = 254;
    private static final int STREAM_IDENTIFIER_TYPE = 255;
    static final byte[] SZ_SIGNATURE = {-1, 6, 0, 0, 115, 78, 97, 80, 112, ClassDefinitionUtils.OPS_dup};
    private static final int UNCOMPRESSED_CHUNK_TYPE = 1;
    private final int blockSize;
    private final PureJavaCrc32C checksum;
    private final CountingInputStream countingStream;
    private SnappyCompressorInputStream currentCompressedChunk;
    private final FramedSnappyDialect dialect;
    private boolean endReached;
    private long expectedChecksum;
    private boolean inUncompressedChunk;
    private final PushbackInputStream inputStream;
    private final byte[] oneByte;
    private final ByteUtils.ByteSupplier supplier;
    private int uncompressedBytesRemaining;
    private long unreadBytes;

    static long unmask(long j) {
        long j2 = (j - MASK_OFFSET) & 4294967295L;
        return ((j2 << 15) | (j2 >> 17)) & 4294967295L;
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, FramedSnappyDialect.STANDARD);
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream, FramedSnappyDialect framedSnappyDialect) throws IOException {
        this(inputStream, 32768, framedSnappyDialect);
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream, int i, FramedSnappyDialect framedSnappyDialect) throws IOException {
        this.oneByte = new byte[1];
        this.expectedChecksum = -1L;
        this.checksum = new PureJavaCrc32C();
        this.supplier = new ByteUtils.ByteSupplier() { // from class: org.apache.commons.compress.compressors.snappy.-$$Lambda$FramedSnappyCompressorInputStream$goSNA0YBo7bM9P6ePvQmQptBU3o
            @Override // org.apache.commons.compress.utils.ByteUtils.ByteSupplier
            public final int getAsByte() {
                int readOneByte;
                readOneByte = FramedSnappyCompressorInputStream.this.readOneByte();
                return readOneByte;
            }
        };
        if (i <= 0) {
            throw new IllegalArgumentException("blockSize must be bigger than 0");
        }
        this.countingStream = new CountingInputStream(inputStream);
        this.inputStream = new PushbackInputStream(this.countingStream, 1);
        this.blockSize = i;
        this.dialect = framedSnappyDialect;
        if (framedSnappyDialect.hasStreamIdentifier()) {
            readStreamIdentifier();
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByte, 0, 1) == -1) {
            return -1;
        }
        return this.oneByte[0] & 255;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (this.currentCompressedChunk != null) {
                this.currentCompressedChunk.close();
                this.currentCompressedChunk = null;
            }
        } finally {
            this.inputStream.close();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int readOnce = readOnce(bArr, i, i2);
        if (readOnce != -1) {
            return readOnce;
        }
        readNextBlock();
        if (this.endReached) {
            return -1;
        }
        return readOnce(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.inUncompressedChunk) {
            return Math.min(this.uncompressedBytesRemaining, this.inputStream.available());
        }
        SnappyCompressorInputStream snappyCompressorInputStream = this.currentCompressedChunk;
        if (snappyCompressorInputStream != null) {
            return snappyCompressorInputStream.available();
        }
        return 0;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getBytesRead() - this.unreadBytes;
    }

    private int readOnce(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.inUncompressedChunk) {
            int min = Math.min(this.uncompressedBytesRemaining, i2);
            if (min == 0) {
                return -1;
            }
            i3 = this.inputStream.read(bArr, i, min);
            if (i3 != -1) {
                this.uncompressedBytesRemaining -= i3;
                count(i3);
            }
        } else {
            SnappyCompressorInputStream snappyCompressorInputStream = this.currentCompressedChunk;
            if (snappyCompressorInputStream != null) {
                long bytesRead = snappyCompressorInputStream.getBytesRead();
                i3 = this.currentCompressedChunk.read(bArr, i, i2);
                if (i3 == -1) {
                    this.currentCompressedChunk.close();
                    this.currentCompressedChunk = null;
                } else {
                    count(this.currentCompressedChunk.getBytesRead() - bytesRead);
                }
            } else {
                i3 = -1;
            }
        }
        if (i3 > 0) {
            this.checksum.update(bArr, i, i3);
        }
        return i3;
    }

    private void readNextBlock() throws IOException {
        verifyLastChecksumAndReset();
        this.inUncompressedChunk = false;
        int readOneByte = readOneByte();
        if (readOneByte == -1) {
            this.endReached = true;
            return;
        }
        if (readOneByte == 255) {
            this.inputStream.unread(readOneByte);
            this.unreadBytes++;
            pushedBackBytes(1L);
            readStreamIdentifier();
            readNextBlock();
            return;
        }
        if (readOneByte == 254 || (readOneByte > 127 && readOneByte <= MAX_SKIPPABLE_TYPE)) {
            skipBlock();
            readNextBlock();
            return;
        }
        if (readOneByte >= 2 && readOneByte <= 127) {
            throw new IOException("Unskippable chunk with type " + readOneByte + " (hex " + Integer.toHexString(readOneByte) + ") detected.");
        }
        if (readOneByte == 1) {
            this.inUncompressedChunk = true;
            this.uncompressedBytesRemaining = readSize() - 4;
            if (this.uncompressedBytesRemaining < 0) {
                throw new IOException("Found illegal chunk with negative size");
            }
            this.expectedChecksum = unmask(readCrc());
            return;
        }
        if (readOneByte == 0) {
            boolean usesChecksumWithCompressedChunks = this.dialect.usesChecksumWithCompressedChunks();
            long readSize = readSize() - (usesChecksumWithCompressedChunks ? 4L : 0L);
            if (readSize < 0) {
                throw new IOException("Found illegal chunk with negative size");
            }
            if (usesChecksumWithCompressedChunks) {
                this.expectedChecksum = unmask(readCrc());
            } else {
                this.expectedChecksum = -1L;
            }
            this.currentCompressedChunk = new SnappyCompressorInputStream(new BoundedInputStream(this.inputStream, readSize), this.blockSize);
            count(this.currentCompressedChunk.getBytesRead());
            return;
        }
        throw new IOException("Unknown chunk type " + readOneByte + " detected.");
    }

    private long readCrc() throws IOException {
        byte[] bArr = new byte[4];
        int readFully = IOUtils.readFully(this.inputStream, bArr);
        count(readFully);
        if (readFully != 4) {
            throw new IOException("Premature end of stream");
        }
        return ByteUtils.fromLittleEndian(bArr);
    }

    private int readSize() throws IOException {
        return (int) ByteUtils.fromLittleEndian(this.supplier, 3);
    }

    private void skipBlock() throws IOException {
        int readSize = readSize();
        if (readSize < 0) {
            throw new IOException("Found illegal chunk with negative size");
        }
        long j = readSize;
        long skip = IOUtils.skip(this.inputStream, j);
        count(skip);
        if (skip != j) {
            throw new IOException("Premature end of stream");
        }
    }

    private void readStreamIdentifier() throws IOException {
        byte[] bArr = new byte[10];
        int readFully = IOUtils.readFully(this.inputStream, bArr);
        count(readFully);
        if (10 != readFully || !matches(bArr, 10)) {
            throw new IOException("Not a framed Snappy stream");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int readOneByte() throws IOException {
        int read = this.inputStream.read();
        if (read == -1) {
            return -1;
        }
        count(1);
        return read & 255;
    }

    private void verifyLastChecksumAndReset() throws IOException {
        long j = this.expectedChecksum;
        if (j >= 0 && j != this.checksum.getValue()) {
            throw new IOException("Checksum verification failed");
        }
        this.expectedChecksum = -1L;
        this.checksum.reset();
    }

    public static boolean matches(byte[] bArr, int i) {
        byte[] bArr2 = SZ_SIGNATURE;
        if (i < bArr2.length) {
            return false;
        }
        if (bArr.length > bArr2.length) {
            byte[] bArr3 = new byte[bArr2.length];
            System.arraycopy(bArr, 0, bArr3, 0, bArr2.length);
            bArr = bArr3;
        }
        return Arrays.equals(bArr, SZ_SIGNATURE);
    }
}
