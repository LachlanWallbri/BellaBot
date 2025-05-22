package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.Checksum;

/* loaded from: classes9.dex */
public class ChecksumCalculatingInputStream extends InputStream {
    private final Checksum checksum;

    /* renamed from: in */
    private final InputStream f8975in;

    public ChecksumCalculatingInputStream(Checksum checksum, InputStream inputStream) {
        Objects.requireNonNull(checksum, "checksum");
        Objects.requireNonNull(inputStream, "in");
        this.checksum = checksum;
        this.f8975in = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.f8975in.read();
        if (read >= 0) {
            this.checksum.update(read);
        }
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
        int read = this.f8975in.read(bArr, i, i2);
        if (read >= 0) {
            this.checksum.update(bArr, i, read);
        }
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return read() >= 0 ? 1L : 0L;
    }

    public long getValue() {
        return this.checksum.getValue();
    }
}
