package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2FlowController {
    void channelHandlerContext(ChannelHandlerContext channelHandlerContext) throws Http2Exception;

    void incrementWindowSize(Http2Stream http2Stream, int i) throws Http2Exception;

    int initialWindowSize();

    void initialWindowSize(int i) throws Http2Exception;

    int windowSize(Http2Stream http2Stream);
}
