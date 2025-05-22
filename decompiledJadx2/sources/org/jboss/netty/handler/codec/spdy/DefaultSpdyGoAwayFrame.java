package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdyGoAwayFrame implements SpdyGoAwayFrame {
    private int lastGoodStreamId;
    private SpdySessionStatus status;

    public DefaultSpdyGoAwayFrame(int i) {
        this(i, 0);
    }

    public DefaultSpdyGoAwayFrame(int i, int i2) {
        this(i, SpdySessionStatus.valueOf(i2));
    }

    public DefaultSpdyGoAwayFrame(int i, SpdySessionStatus spdySessionStatus) {
        setLastGoodStreamId(i);
        setStatus(spdySessionStatus);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyGoAwayFrame
    public int getLastGoodStreamID() {
        return getLastGoodStreamId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyGoAwayFrame
    public int getLastGoodStreamId() {
        return this.lastGoodStreamId;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyGoAwayFrame
    public void setLastGoodStreamID(int i) {
        setLastGoodStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyGoAwayFrame
    public void setLastGoodStreamId(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Last-good-stream-ID cannot be negative: " + i);
        }
        this.lastGoodStreamId = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyGoAwayFrame
    public SpdySessionStatus getStatus() {
        return this.status;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyGoAwayFrame
    public void setStatus(SpdySessionStatus spdySessionStatus) {
        this.status = spdySessionStatus;
    }

    public String toString() {
        return getClass().getSimpleName() + StringUtil.NEWLINE + "--> Last-good-stream-ID = " + this.lastGoodStreamId + StringUtil.NEWLINE + "--> Status: " + this.status.toString();
    }
}
