package io.grpc.okhttp;

import io.grpc.internal.WritableBuffer;
import okio.Buffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class OkHttpWritableBuffer implements WritableBuffer {
    private final Buffer buffer;
    private int readableBytes;
    private int writableBytes;

    @Override // io.grpc.internal.WritableBuffer
    public void release() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpWritableBuffer(Buffer buffer, int i) {
        this.buffer = buffer;
        this.writableBytes = i;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
        this.writableBytes -= i2;
        this.readableBytes += i2;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte b) {
        this.buffer.writeByte((int) b);
        this.writableBytes--;
        this.readableBytes++;
    }

    @Override // io.grpc.internal.WritableBuffer
    public int writableBytes() {
        return this.writableBytes;
    }

    @Override // io.grpc.internal.WritableBuffer
    public int readableBytes() {
        return this.readableBytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Buffer buffer() {
        return this.buffer;
    }
}
