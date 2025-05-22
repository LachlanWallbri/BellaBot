package org.jboss.netty.handler.codec.spdy;

/* loaded from: classes7.dex */
public interface SpdyRstStreamFrame {
    SpdyStreamStatus getStatus();

    @Deprecated
    int getStreamID();

    int getStreamId();

    void setStatus(SpdyStreamStatus spdyStreamStatus);

    @Deprecated
    void setStreamID(int i);

    void setStreamId(int i);
}
