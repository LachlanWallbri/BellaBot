package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface SpdyWindowUpdateFrame extends SpdyFrame {
    int deltaWindowSize();

    SpdyWindowUpdateFrame setDeltaWindowSize(int i);

    SpdyWindowUpdateFrame setStreamId(int i);

    int streamId();
}
