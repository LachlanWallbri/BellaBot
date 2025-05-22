package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ZlibDecoder extends ByteToMessageDecoder {
    protected final int maxAllocation;

    protected void decompressionBufferExhausted(ByteBuf byteBuf) {
    }

    public abstract boolean isClosed();

    public ZlibDecoder() {
        this(0);
    }

    public ZlibDecoder(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("maxAllocation must be >= 0");
        }
        this.maxAllocation = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuf prepareDecompressBuffer(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, int i) {
        if (byteBuf == null) {
            if (this.maxAllocation == 0) {
                return channelHandlerContext.alloc().heapBuffer(i);
            }
            return channelHandlerContext.alloc().heapBuffer(Math.min(i, this.maxAllocation), this.maxAllocation);
        }
        if (byteBuf.ensureWritable(i, true) != 1) {
            return byteBuf;
        }
        decompressionBufferExhausted(byteBuf.duplicate());
        byteBuf.skipBytes(byteBuf.readableBytes());
        throw new DecompressionException("Decompression buffer has reached maximum size: " + byteBuf.maxCapacity());
    }
}
