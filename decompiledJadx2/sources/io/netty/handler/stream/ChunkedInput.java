package io.netty.handler.stream;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface ChunkedInput<B> {
    void close() throws Exception;

    boolean isEndOfInput() throws Exception;

    long length();

    long progress();

    B readChunk(ByteBufAllocator byteBufAllocator) throws Exception;

    @Deprecated
    B readChunk(ChannelHandlerContext channelHandlerContext) throws Exception;
}
