package io.netty.handler.codec.http2;

import io.netty.channel.ChannelHandlerContext;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Http2FlowController {
    void channelHandlerContext(ChannelHandlerContext channelHandlerContext) throws Http2Exception;

    void incrementWindowSize(Http2Stream http2Stream, int i) throws Http2Exception;

    int initialWindowSize();

    void initialWindowSize(int i) throws Http2Exception;

    int windowSize(Http2Stream http2Stream);
}
