package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdyWindowUpdateFrame implements SpdyWindowUpdateFrame {
    private int deltaWindowSize;
    private int streamId;

    public DefaultSpdyWindowUpdateFrame(int i, int i2) {
        setStreamId(i);
        setDeltaWindowSize(i2);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public int getStreamID() {
        return getStreamId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public int getStreamId() {
        return this.streamId;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public void setStreamID(int i) {
        setStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public void setStreamId(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Stream-ID must be positive: " + i);
        }
        this.streamId = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public int getDeltaWindowSize() {
        return this.deltaWindowSize;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public void setDeltaWindowSize(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Delta-Window-Size must be positive: " + i);
        }
        this.deltaWindowSize = i;
    }

    public String toString() {
        return getClass().getSimpleName() + StringUtil.NEWLINE + "--> Stream-ID = " + this.streamId + StringUtil.NEWLINE + "--> Delta-Window-Size = " + this.deltaWindowSize;
    }
}
