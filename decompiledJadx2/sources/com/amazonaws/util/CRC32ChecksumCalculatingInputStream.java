package com.amazonaws.util;

import com.amazonaws.internal.SdkFilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

/* loaded from: classes.dex */
public class CRC32ChecksumCalculatingInputStream extends SdkFilterInputStream {
    private CRC32 crc32;

    public CRC32ChecksumCalculatingInputStream(InputStream inputStream) {
        super(inputStream);
        this.crc32 = new CRC32();
    }

    public long getCRC32Checksum() {
        return this.crc32.getValue();
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        abortIfNeeded();
        this.crc32.reset();
        this.in.reset();
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        abortIfNeeded();
        int read = this.in.read();
        if (read != -1) {
            this.crc32.update(read);
        }
        return read;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        abortIfNeeded();
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            this.crc32.update(bArr, i, read);
        }
        return read;
    }
}
