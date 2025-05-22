package org.jboss.netty.handler.codec.spdy;

/* loaded from: classes7.dex */
public interface SpdySynReplyFrame extends SpdyHeaderBlock {
    @Deprecated
    int getStreamID();

    int getStreamId();

    boolean isLast();

    void setLast(boolean z);

    @Deprecated
    void setStreamID(int i);

    void setStreamId(int i);
}
