package org.jboss.netty.handler.codec.spdy;

/* loaded from: classes7.dex */
public interface SpdyGoAwayFrame {
    @Deprecated
    int getLastGoodStreamID();

    int getLastGoodStreamId();

    SpdySessionStatus getStatus();

    @Deprecated
    void setLastGoodStreamID(int i);

    void setLastGoodStreamId(int i);

    void setStatus(SpdySessionStatus spdySessionStatus);
}
