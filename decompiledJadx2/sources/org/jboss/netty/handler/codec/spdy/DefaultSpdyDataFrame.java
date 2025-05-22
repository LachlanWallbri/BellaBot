package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdyDataFrame implements SpdyDataFrame {
    private boolean compressed;
    private ChannelBuffer data = ChannelBuffers.EMPTY_BUFFER;
    private boolean last;
    private int streamId;

    public DefaultSpdyDataFrame(int i) {
        setStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public int getStreamID() {
        return getStreamId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public int getStreamId() {
        return this.streamId;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public void setStreamID(int i) {
        setStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public void setStreamId(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Stream-ID must be positive: " + i);
        }
        this.streamId = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public boolean isLast() {
        return this.last;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public void setLast(boolean z) {
        this.last = z;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public boolean isCompressed() {
        return this.compressed;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public void setCompressed(boolean z) {
        this.compressed = z;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public ChannelBuffer getData() {
        return this.data;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyDataFrame
    public void setData(ChannelBuffer channelBuffer) {
        if (channelBuffer == null) {
            channelBuffer = ChannelBuffers.EMPTY_BUFFER;
        }
        if (channelBuffer.readableBytes() > 16777215) {
            throw new IllegalArgumentException("data payload cannot exceed 16777215 bytes");
        }
        this.data = channelBuffer;
    }

    public String toString() {
        return getClass().getSimpleName() + "(last: " + isLast() + "; compressed: " + isCompressed() + ')' + StringUtil.NEWLINE + "--> Stream-ID = " + this.streamId + StringUtil.NEWLINE + "--> Size = " + this.data.readableBytes();
    }
}
