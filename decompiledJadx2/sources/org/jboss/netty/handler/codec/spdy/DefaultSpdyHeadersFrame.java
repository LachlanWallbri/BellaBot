package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdyHeadersFrame extends DefaultSpdyHeaderBlock implements SpdyHeadersFrame {
    private boolean last;
    private int streamId;

    public DefaultSpdyHeadersFrame(int i) {
        setStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeadersFrame
    public int getStreamID() {
        return getStreamId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeadersFrame
    public int getStreamId() {
        return this.streamId;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeadersFrame
    public void setStreamID(int i) {
        setStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeadersFrame
    public void setStreamId(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Stream-ID must be positive: " + i);
        }
        this.streamId = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeadersFrame
    public boolean isLast() {
        return this.last;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeadersFrame
    public void setLast(boolean z) {
        this.last = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(last: ");
        sb.append(isLast());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Stream-ID = ");
        sb.append(this.streamId);
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Headers:");
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }
}
