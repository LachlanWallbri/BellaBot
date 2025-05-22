package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface SpdySynReplyFrame extends SpdyHeadersFrame {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    SpdySynReplyFrame setInvalid();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    SpdySynReplyFrame setLast(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    SpdySynReplyFrame setStreamId(int i);
}
