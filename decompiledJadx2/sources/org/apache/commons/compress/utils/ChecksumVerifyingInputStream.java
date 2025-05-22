package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Checksum;

/* loaded from: classes9.dex */
public class ChecksumVerifyingInputStream extends InputStream {
    private long bytesRemaining;
    private final Checksum checksum;
    private final long expectedChecksum;

    /* renamed from: in */
    private final InputStream f8976in;

    public ChecksumVerifyingInputStream(Checksum checksum, InputStream inputStream, long j, long j2) {
        this.checksum = checksum;
        this.f8976in = inputStream;
        this.expectedChecksum = j2;
        this.bytesRemaining = j;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.bytesRemaining <= 0) {
            return -1;
        }
        int read = this.f8976in.read();
        if (read >= 0) {
            this.checksum.update(read);
            this.bytesRemaining--;
        }
        if (this.bytesRemaining != 0 || this.expectedChecksum == this.checksum.getValue()) {
            return read;
        }
        throw new IOException("Checksum verification failed");
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
        int read = this.f8976in.read(bArr, i, i2);
        if (read >= 0) {
            this.checksum.update(bArr, i, read);
            this.bytesRemaining -= read;
        }
        if (this.bytesRemaining > 0 || this.expectedChecksum == this.checksum.getValue()) {
            return read;
        }
        throw new IOException("Checksum verification failed");
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return read() >= 0 ? 1L : 0L;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f8976in.close();
    }

    public long getBytesRemaining() {
        return this.bytesRemaining;
    }
}
