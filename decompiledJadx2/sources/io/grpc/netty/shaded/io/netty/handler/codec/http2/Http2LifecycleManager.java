package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2LifecycleManager {
    void closeStream(Http2Stream http2Stream, ChannelFuture channelFuture);

    void closeStreamLocal(Http2Stream http2Stream, ChannelFuture channelFuture);

    void closeStreamRemote(Http2Stream http2Stream, ChannelFuture channelFuture);

    ChannelFuture goAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise);

    void onError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th);

    ChannelFuture resetStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise);
}
