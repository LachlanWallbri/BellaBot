package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdyRstStreamFrame implements SpdyRstStreamFrame {
    private SpdyStreamStatus status;
    private int streamId;

    public DefaultSpdyRstStreamFrame(int i, int i2) {
        this(i, SpdyStreamStatus.valueOf(i2));
    }

    public DefaultSpdyRstStreamFrame(int i, SpdyStreamStatus spdyStreamStatus) {
        setStreamId(i);
        setStatus(spdyStreamStatus);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyRstStreamFrame
    public int getStreamID() {
        return getStreamId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyRstStreamFrame
    public int getStreamId() {
        return this.streamId;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyRstStreamFrame
    public void setStreamID(int i) {
        setStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyRstStreamFrame
    public void setStreamId(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Stream-ID must be positive: " + i);
        }
        this.streamId = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyRstStreamFrame
    public SpdyStreamStatus getStatus() {
        return this.status;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyRstStreamFrame
    public void setStatus(SpdyStreamStatus spdyStreamStatus) {
        this.status = spdyStreamStatus;
    }

    public String toString() {
        return getClass().getSimpleName() + StringUtil.NEWLINE + "--> Stream-ID = " + this.streamId + StringUtil.NEWLINE + "--> Status: " + this.status.toString();
    }
}
