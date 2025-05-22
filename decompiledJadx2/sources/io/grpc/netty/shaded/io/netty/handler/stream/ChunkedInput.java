package io.grpc.netty.shaded.io.netty.handler.stream;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChunkedInput<B> {
    void close() throws Exception;

    boolean isEndOfInput() throws Exception;

    long length();

    long progress();

    B readChunk(ByteBufAllocator byteBufAllocator) throws Exception;

    @Deprecated
    B readChunk(ChannelHandlerContext channelHandlerContext) throws Exception;
}
