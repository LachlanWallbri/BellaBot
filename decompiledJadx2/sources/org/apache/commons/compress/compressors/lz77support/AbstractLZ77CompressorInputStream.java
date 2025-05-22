package org.apache.commons.compress.compressors.lz77support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* loaded from: classes8.dex */
public abstract class AbstractLZ77CompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private int backReferenceOffset;
    private final byte[] buf;
    private long bytesRemaining;

    /* renamed from: in */
    private final CountingInputStream f8940in;
    private int readIndex;
    private int size;
    private final int windowSize;
    private int writeIndex;
    private final byte[] oneByte = new byte[1];
    protected final ByteUtils.ByteSupplier supplier = new ByteUtils.ByteSupplier() { // from class: org.apache.commons.compress.compressors.lz77support.-$$Lambda$YP6CS1JE5dLCuvRW5YXvpVB_KFs
        @Override // org.apache.commons.compress.utils.ByteUtils.ByteSupplier
        public final int getAsByte() {
            return AbstractLZ77CompressorInputStream.this.readOneByte();
        }
    };

    public AbstractLZ77CompressorInputStream(InputStream inputStream, int i) throws IOException {
        this.f8940in = new CountingInputStream(inputStream);
        if (i <= 0) {
            throw new IllegalArgumentException("windowSize must be bigger than 0");
        }
        this.windowSize = i;
        this.buf = new byte[i * 3];
        this.readIndex = 0;
        this.writeIndex = 0;
        this.bytesRemaining = 0L;
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
        this.f8940in.close();
    }

    @Override // java.io.InputStream
    public int available() {
        return this.writeIndex - this.readIndex;
    }

    public int getSize() {
        return this.size;
    }

    public void prefill(byte[] bArr) {
        if (this.writeIndex != 0) {
            throw new IllegalStateException("The stream has already been read from, can't prefill anymore");
        }
        int min = Math.min(this.windowSize, bArr.length);
        System.arraycopy(bArr, bArr.length - min, this.buf, 0, min);
        this.writeIndex += min;
        this.readIndex += min;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.f8940in.getBytesRead();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startLiteral(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("length must not be negative");
        }
        this.bytesRemaining = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean hasMoreDataInBlock() {
        return this.bytesRemaining > 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int readLiteral(byte[] bArr, int i, int i2) throws IOException {
        int available = available();
        if (i2 > available) {
            tryToReadLiteral(i2 - available);
        }
        return readFromBuffer(bArr, i, i2);
    }

    private void tryToReadLiteral(int i) throws IOException {
        int min = Math.min((int) Math.min(i, this.bytesRemaining), this.buf.length - this.writeIndex);
        int readFully = min > 0 ? IOUtils.readFully(this.f8940in, this.buf, this.writeIndex, min) : 0;
        count(readFully);
        if (min != readFully) {
            throw new IOException("Premature end of stream reading literal");
        }
        this.writeIndex += min;
        this.bytesRemaining -= min;
    }

    private int readFromBuffer(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, available());
        if (min > 0) {
            System.arraycopy(this.buf, this.readIndex, bArr, i, min);
            this.readIndex += min;
            if (this.readIndex > this.windowSize * 2) {
                slideBuffer();
            }
        }
        this.size += min;
        return min;
    }

    private void slideBuffer() {
        byte[] bArr = this.buf;
        int i = this.windowSize;
        System.arraycopy(bArr, i, bArr, 0, i * 2);
        int i2 = this.writeIndex;
        int i3 = this.windowSize;
        this.writeIndex = i2 - i3;
        this.readIndex -= i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startBackReference(int i, long j) {
        if (i <= 0 || i > this.writeIndex) {
            throw new IllegalArgumentException("offset must be bigger than 0 but not bigger than the number of bytes available for back-references");
        }
        if (j < 0) {
            throw new IllegalArgumentException("length must not be negative");
        }
        this.backReferenceOffset = i;
        this.bytesRemaining = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int readBackReference(byte[] bArr, int i, int i2) {
        int available = available();
        if (i2 > available) {
            tryToCopy(i2 - available);
        }
        return readFromBuffer(bArr, i, i2);
    }

    private void tryToCopy(int i) {
        int min = Math.min((int) Math.min(i, this.bytesRemaining), this.buf.length - this.writeIndex);
        if (min != 0) {
            int i2 = this.backReferenceOffset;
            if (i2 == 1) {
                byte[] bArr = this.buf;
                int i3 = this.writeIndex;
                Arrays.fill(bArr, i3, i3 + min, bArr[i3 - 1]);
                this.writeIndex += min;
            } else if (min < i2) {
                byte[] bArr2 = this.buf;
                int i4 = this.writeIndex;
                System.arraycopy(bArr2, i4 - i2, bArr2, i4, min);
                this.writeIndex += min;
            } else {
                int i5 = min / i2;
                for (int i6 = 0; i6 < i5; i6++) {
                    byte[] bArr3 = this.buf;
                    int i7 = this.writeIndex;
                    int i8 = this.backReferenceOffset;
                    System.arraycopy(bArr3, i7 - i8, bArr3, i7, i8);
                    this.writeIndex += this.backReferenceOffset;
                }
                int i9 = this.backReferenceOffset;
                int i10 = min - (i5 * i9);
                if (i10 > 0) {
                    byte[] bArr4 = this.buf;
                    int i11 = this.writeIndex;
                    System.arraycopy(bArr4, i11 - i9, bArr4, i11, i10);
                    this.writeIndex += i10;
                }
            }
        }
        this.bytesRemaining -= min;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int readOneByte() throws IOException {
        int read = this.f8940in.read();
        if (read == -1) {
            return -1;
        }
        count(1);
        return read & 255;
    }
}
