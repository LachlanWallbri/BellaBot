package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class BoundedInputStream extends InputStream {
    private long bytesRemaining;

    /* renamed from: in */
    private final InputStream f8972in;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public BoundedInputStream(InputStream inputStream, long j) {
        this.f8972in = inputStream;
        this.bytesRemaining = j;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        long j = this.bytesRemaining;
        if (j <= 0) {
            return -1;
        }
        this.bytesRemaining = j - 1;
        return this.f8972in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (i2 > j) {
            i2 = (int) j;
        }
        int read = this.f8972in.read(bArr, i, i2);
        if (read >= 0) {
            this.bytesRemaining -= read;
        }
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long skip = this.f8972in.skip(Math.min(this.bytesRemaining, j));
        this.bytesRemaining -= skip;
        return skip;
    }

    public long getBytesRemaining() {
        return this.bytesRemaining;
    }
}
