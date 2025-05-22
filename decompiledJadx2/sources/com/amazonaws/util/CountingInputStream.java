package com.amazonaws.util;

import com.amazonaws.internal.SdkFilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Deprecated
/* loaded from: classes.dex */
public class CountingInputStream extends SdkFilterInputStream {
    private long byteCount;

    public CountingInputStream(InputStream inputStream) {
        super(inputStream);
        this.byteCount = 0L;
    }

    public long getByteCount() {
        return this.byteCount;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        this.byteCount += read >= 0 ? 1L : 0L;
        return read;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        this.byteCount += read >= 0 ? read : 0L;
        return read;
    }
}
