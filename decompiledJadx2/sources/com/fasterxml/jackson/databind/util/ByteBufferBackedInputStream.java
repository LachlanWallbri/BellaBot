package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class ByteBufferBackedInputStream extends InputStream {

    /* renamed from: _b */
    protected final ByteBuffer f1274_b;

    public ByteBufferBackedInputStream(ByteBuffer byteBuffer) {
        this.f1274_b = byteBuffer;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f1274_b.remaining();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.f1274_b.hasRemaining()) {
            return this.f1274_b.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.f1274_b.hasRemaining()) {
            return -1;
        }
        int min = Math.min(i2, this.f1274_b.remaining());
        this.f1274_b.get(bArr, i, min);
        return min;
    }
}
