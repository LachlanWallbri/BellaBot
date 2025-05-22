package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public abstract class BoundedArchiveInputStream extends InputStream {
    private final long end;
    private long loc;
    private ByteBuffer singleByteBuffer;

    protected abstract int read(long j, ByteBuffer byteBuffer) throws IOException;

    public BoundedArchiveInputStream(long j, long j2) {
        this.end = j + j2;
        if (this.end < j) {
            throw new IllegalArgumentException("Invalid length of stream at offset=" + j + ", length=" + j2);
        }
        this.loc = j;
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        if (this.loc >= this.end) {
            return -1;
        }
        if (this.singleByteBuffer == null) {
            this.singleByteBuffer = ByteBuffer.allocate(1);
        } else {
            this.singleByteBuffer.rewind();
        }
        if (read(this.loc, this.singleByteBuffer) < 1) {
            return -1;
        }
        this.loc++;
        return this.singleByteBuffer.get() & 255;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.loc >= this.end) {
            return -1;
        }
        long min = Math.min(i2, this.end - this.loc);
        if (min <= 0) {
            return 0;
        }
        if (i < 0 || i > bArr.length || min > bArr.length - i) {
            throw new IndexOutOfBoundsException("offset or len are out of bounds");
        }
        int read = read(this.loc, ByteBuffer.wrap(bArr, i, (int) min));
        if (read > 0) {
            this.loc += read;
        }
        return read;
    }
}
