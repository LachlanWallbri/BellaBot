package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.internal.WritableBuffer;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class NettyWritableBuffer implements WritableBuffer {
    private final ByteBuf bytebuf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyWritableBuffer(ByteBuf byteBuf) {
        this.bytebuf = byteBuf;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte[] bArr, int i, int i2) {
        this.bytebuf.writeBytes(bArr, i, i2);
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte b) {
        this.bytebuf.writeByte(b);
    }

    @Override // io.grpc.internal.WritableBuffer
    public int writableBytes() {
        return this.bytebuf.writableBytes();
    }

    @Override // io.grpc.internal.WritableBuffer
    public int readableBytes() {
        return this.bytebuf.readableBytes();
    }

    @Override // io.grpc.internal.WritableBuffer
    public void release() {
        this.bytebuf.release();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuf bytebuf() {
        return this.bytebuf;
    }
}
