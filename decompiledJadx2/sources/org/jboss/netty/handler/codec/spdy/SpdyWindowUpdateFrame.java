package org.jboss.netty.handler.codec.spdy;

/* loaded from: classes7.dex */
public interface SpdyWindowUpdateFrame {
    int getDeltaWindowSize();

    @Deprecated
    int getStreamID();

    int getStreamId();

    void setDeltaWindowSize(int i);

    void setStreamID(int i);

    void setStreamId(int i);
}
