package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class CompressionUtil {
    private CompressionUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkChecksum(ByteBufChecksum byteBufChecksum, ByteBuf byteBuf, int i) {
        byteBufChecksum.reset();
        byteBufChecksum.update(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
        int value = (int) byteBufChecksum.getValue();
        if (value != i) {
            throw new DecompressionException(String.format("stream corrupted: mismatching checksum: %d (expected: %d)", Integer.valueOf(value), Integer.valueOf(i)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer safeNioBuffer(ByteBuf byteBuf) {
        return byteBuf.nioBufferCount() == 1 ? byteBuf.internalNioBuffer(byteBuf.readerIndex(), byteBuf.readableBytes()) : byteBuf.nioBuffer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer safeNioBuffer(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.nioBufferCount() == 1 ? byteBuf.internalNioBuffer(i, i2) : byteBuf.nioBuffer(i, i2);
    }
}
