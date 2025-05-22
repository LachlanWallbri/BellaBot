package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Http2LifecycleManager {
    void closeStream(Http2Stream http2Stream, ChannelFuture channelFuture);

    void closeStreamLocal(Http2Stream http2Stream, ChannelFuture channelFuture);

    void closeStreamRemote(Http2Stream http2Stream, ChannelFuture channelFuture);

    ChannelFuture goAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise);

    void onError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th);

    ChannelFuture resetStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise);
}
