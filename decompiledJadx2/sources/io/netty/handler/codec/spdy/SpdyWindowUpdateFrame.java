package io.netty.handler.codec.spdy;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface SpdyWindowUpdateFrame extends SpdyFrame {
    int deltaWindowSize();

    SpdyWindowUpdateFrame setDeltaWindowSize(int i);

    SpdyWindowUpdateFrame setStreamId(int i);

    int streamId();
}
