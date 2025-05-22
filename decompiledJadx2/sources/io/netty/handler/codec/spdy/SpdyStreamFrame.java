package io.netty.handler.codec.spdy;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface SpdyStreamFrame extends SpdyFrame {
    boolean isLast();

    SpdyStreamFrame setLast(boolean z);

    SpdyStreamFrame setStreamId(int i);

    int streamId();
}
