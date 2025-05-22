package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface SpdyGoAwayFrame extends SpdyFrame {
    int lastGoodStreamId();

    SpdyGoAwayFrame setLastGoodStreamId(int i);

    SpdyGoAwayFrame setStatus(SpdySessionStatus spdySessionStatus);

    SpdySessionStatus status();
}
