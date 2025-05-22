package org.xerial.snappy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class SnappyInputStream extends InputStream {
    private byte[] compressed;

    /* renamed from: in */
    protected final InputStream f10298in;
    private byte[] uncompressed;
    private boolean finishedReading = false;
    private int uncompressedCursor = 0;
    private int uncompressedLimit = 0;
    private byte[] header = new byte[SnappyCodec.headerSize()];

    public SnappyInputStream(InputStream inputStream) throws IOException {
        this.f10298in = inputStream;
        readHeader();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.compressed = null;
        this.uncompressed = null;
        InputStream inputStream = this.f10298in;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    protected void readHeader() throws IOException {
        int read;
        int i = 0;
        while (true) {
            byte[] bArr = this.header;
            if (i >= bArr.length || (read = this.f10298in.read(bArr, i, bArr.length - i)) == -1) {
                break;
            } else {
                i += read;
            }
        }
        if (i == 0) {
            throw new SnappyIOException(SnappyErrorCode.EMPTY_INPUT, "Cannot decompress empty stream");
        }
        byte[] bArr2 = this.header;
        if (i < bArr2.length || !SnappyCodec.hasMagicHeaderPrefix(bArr2)) {
            readFully(this.header, i);
        }
    }

    private static boolean isValidHeader(byte[] bArr) throws IOException {
        SnappyCodec readHeader = SnappyCodec.readHeader(new ByteArrayInputStream(bArr));
        if (!readHeader.isValidMagicHeader()) {
            return false;
        }
        if (readHeader.version >= 1) {
            return true;
        }
        throw new SnappyIOException(SnappyErrorCode.INCOMPATIBLE_VERSION, String.format("Compressed with an incompatible codec version %d. At least version %d is required", Integer.valueOf(readHeader.version), 1));
    }

    protected void readFully(byte[] bArr, int i) throws IOException {
        if (i == 0) {
            this.finishedReading = true;
            return;
        }
        this.compressed = new byte[Math.max(8192, i)];
        System.arraycopy(bArr, 0, this.compressed, 0, i);
        while (true) {
            InputStream inputStream = this.f10298in;
            byte[] bArr2 = this.compressed;
            int read = inputStream.read(bArr2, i, bArr2.length - i);
            if (read != -1) {
                i += read;
                byte[] bArr3 = this.compressed;
                if (i >= bArr3.length) {
                    byte[] bArr4 = new byte[bArr3.length * 2];
                    System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                    this.compressed = bArr4;
                }
            } else {
                this.finishedReading = true;
                int uncompressedLength = Snappy.uncompressedLength(this.compressed, 0, i);
                this.uncompressed = new byte[uncompressedLength];
                Snappy.uncompress(this.compressed, 0, i, this.uncompressed, 0);
                this.uncompressedCursor = 0;
                this.uncompressedLimit = uncompressedLength;
                return;
            }
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = this.uncompressedCursor;
            int i5 = this.uncompressedLimit;
            if (i4 >= i5) {
                if (!hasNextChunk()) {
                    if (i3 == 0) {
                        return -1;
                    }
                    return i3;
                }
            } else {
                int min = Math.min(i5 - i4, i2 - i3);
                System.arraycopy(this.uncompressed, this.uncompressedCursor, bArr, i + i3, min);
                i3 += min;
                this.uncompressedCursor += min;
            }
        }
        return i3;
    }

    public int rawRead(Object obj, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = this.uncompressedCursor;
            int i5 = this.uncompressedLimit;
            if (i4 >= i5) {
                if (!hasNextChunk()) {
                    if (i3 == 0) {
                        return -1;
                    }
                    return i3;
                }
            } else {
                int min = Math.min(i5 - i4, i2 - i3);
                Snappy.arrayCopy(this.uncompressed, this.uncompressedCursor, min, obj, i + i3);
                i3 += min;
                this.uncompressedCursor += min;
            }
        }
        return i3;
    }

    public int read(long[] jArr, int i, int i2) throws IOException {
        return rawRead(jArr, i * 8, i2 * 8);
    }

    public int read(long[] jArr) throws IOException {
        return read(jArr, 0, jArr.length);
    }

    public int read(double[] dArr, int i, int i2) throws IOException {
        return rawRead(dArr, i * 8, i2 * 8);
    }

    public int read(double[] dArr) throws IOException {
        return read(dArr, 0, dArr.length);
    }

    public int read(int[] iArr) throws IOException {
        return read(iArr, 0, iArr.length);
    }

    public int read(int[] iArr, int i, int i2) throws IOException {
        return rawRead(iArr, i * 4, i2 * 4);
    }

    public int read(float[] fArr, int i, int i2) throws IOException {
        return rawRead(fArr, i * 4, i2 * 4);
    }

    public int read(float[] fArr) throws IOException {
        return read(fArr, 0, fArr.length);
    }

    public int read(short[] sArr, int i, int i2) throws IOException {
        return rawRead(sArr, i * 2, i2 * 2);
    }

    public int read(short[] sArr) throws IOException {
        return read(sArr, 0, sArr.length);
    }

    private int readNext(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = this.f10298in.read(bArr, i3 + i, i2 - i3);
            if (read == -1) {
                this.finishedReading = true;
                return i3;
            }
            i3 += read;
        }
        return i3;
    }

    protected boolean hasNextChunk() throws IOException {
        if (this.finishedReading) {
            return false;
        }
        this.uncompressedCursor = 0;
        this.uncompressedLimit = 0;
        if (readNext(this.header, 0, 4) < 4) {
            return false;
        }
        int readInt = SnappyOutputStream.readInt(this.header, 0);
        if (readInt == SnappyCodec.MAGIC_HEADER_HEAD) {
            int headerSize = SnappyCodec.headerSize() - 4;
            if (readNext(this.header, 4, headerSize) < headerSize) {
                throw new SnappyIOException(SnappyErrorCode.FAILED_TO_UNCOMPRESS, String.format("Insufficient header size in a concatenated block", new Object[0]));
            }
            if (isValidHeader(this.header)) {
                return hasNextChunk();
            }
            return false;
        }
        byte[] bArr = this.compressed;
        if (bArr == null || readInt > bArr.length) {
            this.compressed = new byte[readInt];
        }
        int i = 0;
        while (i < readInt) {
            int read = this.f10298in.read(this.compressed, i, readInt - i);
            if (read == -1) {
                break;
            }
            i += read;
        }
        if (i < readInt) {
            throw new IOException("failed to read chunk");
        }
        int uncompressedLength = Snappy.uncompressedLength(this.compressed, 0, readInt);
        byte[] bArr2 = this.uncompressed;
        if (bArr2 == null || uncompressedLength > bArr2.length) {
            this.uncompressed = new byte[uncompressedLength];
        }
        int uncompress = Snappy.uncompress(this.compressed, 0, readInt, this.uncompressed, 0);
        if (uncompressedLength != uncompress) {
            throw new SnappyIOException(SnappyErrorCode.INVALID_CHUNK_SIZE, String.format("expected %,d bytes, but decompressed chunk has %,d bytes", Integer.valueOf(uncompressedLength), Integer.valueOf(uncompress)));
        }
        this.uncompressedLimit = uncompress;
        return true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.uncompressedCursor;
        if (i < this.uncompressedLimit) {
            byte[] bArr = this.uncompressed;
            this.uncompressedCursor = i + 1;
            return bArr[i] & 255;
        }
        if (hasNextChunk()) {
            return read();
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int i = this.uncompressedCursor;
        int i2 = this.uncompressedLimit;
        if (i < i2) {
            return i2 - i;
        }
        if (hasNextChunk()) {
            return this.uncompressedLimit - this.uncompressedCursor;
        }
        return 0;
    }
}
