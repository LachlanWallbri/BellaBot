package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2ConnectionEncoder extends Http2FrameWriter {
    Http2Connection connection();

    Http2RemoteFlowController flowController();

    Http2FrameWriter frameWriter();

    void lifecycleManager(Http2LifecycleManager http2LifecycleManager);

    Http2Settings pollSentSettings();

    void remoteSettings(Http2Settings http2Settings) throws Http2Exception;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    ChannelFuture writeFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf, ChannelPromise channelPromise);
}
