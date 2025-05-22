package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class ByteCountingOutputStream extends OutputStream {
    long count;

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.count += i2;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.count++;
    }
}
