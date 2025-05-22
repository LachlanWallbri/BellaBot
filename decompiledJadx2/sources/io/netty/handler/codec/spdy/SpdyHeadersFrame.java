package io.netty.handler.codec.spdy;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface SpdyHeadersFrame extends SpdyStreamFrame {
    SpdyHeaders headers();

    boolean isInvalid();

    boolean isTruncated();

    SpdyHeadersFrame setInvalid();

    @Override // io.netty.handler.codec.spdy.SpdyStreamFrame, io.netty.handler.codec.spdy.SpdyDataFrame
    SpdyHeadersFrame setLast(boolean z);

    @Override // io.netty.handler.codec.spdy.SpdyStreamFrame, io.netty.handler.codec.spdy.SpdyDataFrame
    SpdyHeadersFrame setStreamId(int i);

    SpdyHeadersFrame setTruncated();
}
