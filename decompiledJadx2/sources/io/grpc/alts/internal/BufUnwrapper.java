package io.grpc.alts.internal;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class BufUnwrapper implements AutoCloseable {
    private final ByteBuffer[] singleReadBuffer = new ByteBuffer[1];
    private final ByteBuffer[] singleWriteBuffer = new ByteBuffer[1];

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer[] writableNioBuffers(ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        int writerIndex = byteBuf.writerIndex();
        byteBuf.readerIndex(writerIndex);
        byteBuf.writerIndex(byteBuf.capacity());
        try {
            return nioBuffers(byteBuf, this.singleWriteBuffer);
        } finally {
            byteBuf.readerIndex(readerIndex);
            byteBuf.writerIndex(writerIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer[] readableNioBuffers(ByteBuf byteBuf) {
        return nioBuffers(byteBuf, this.singleReadBuffer);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.singleReadBuffer[0] = null;
        this.singleWriteBuffer[0] = null;
    }

    private static ByteBuffer[] nioBuffers(ByteBuf byteBuf, ByteBuffer[] byteBufferArr) {
        if (!(byteBuf instanceof CompositeByteBuf) && byteBuf.nioBufferCount() == 1) {
            byteBufferArr[0] = byteBuf.internalNioBuffer(byteBuf.readerIndex(), byteBuf.readableBytes());
            return byteBufferArr;
        }
        return byteBuf.nioBuffers();
    }
}
