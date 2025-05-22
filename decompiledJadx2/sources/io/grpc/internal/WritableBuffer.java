package io.grpc.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface WritableBuffer {
    int readableBytes();

    void release();

    int writableBytes();

    void write(byte b);

    void write(byte[] bArr, int i, int i2);
}
