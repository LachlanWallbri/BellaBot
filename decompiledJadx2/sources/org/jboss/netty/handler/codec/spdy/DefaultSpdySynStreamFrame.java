package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdySynStreamFrame extends DefaultSpdyHeaderBlock implements SpdySynStreamFrame {
    private int associatedToStreamId;
    private boolean last;
    private byte priority;
    private int streamId;
    private boolean unidirectional;

    public DefaultSpdySynStreamFrame(int i, int i2, byte b) {
        setStreamId(i);
        setAssociatedToStreamId(i2);
        setPriority(b);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public int getStreamID() {
        return getStreamId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public int getStreamId() {
        return this.streamId;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public void setStreamID(int i) {
        setStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public void setStreamId(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Stream-ID must be positive: " + i);
        }
        this.streamId = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public int getAssociatedToStreamID() {
        return getAssociatedToStreamId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public int getAssociatedToStreamId() {
        return this.associatedToStreamId;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public void setAssociatedToStreamID(int i) {
        setAssociatedToStreamId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public void setAssociatedToStreamId(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Associated-To-Stream-ID cannot be negative: " + i);
        }
        this.associatedToStreamId = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public byte getPriority() {
        return this.priority;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public void setPriority(byte b) {
        if (b < 0 || b > 7) {
            throw new IllegalArgumentException("Priority must be between 0 and 7 inclusive: " + ((int) b));
        }
        this.priority = b;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public boolean isLast() {
        return this.last;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public void setLast(boolean z) {
        this.last = z;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public boolean isUnidirectional() {
        return this.unidirectional;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySynStreamFrame
    public void setUnidirectional(boolean z) {
        this.unidirectional = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(last: ");
        sb.append(isLast());
        sb.append("; unidirectional: ");
        sb.append(isUnidirectional());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Stream-ID = ");
        sb.append(this.streamId);
        sb.append(StringUtil.NEWLINE);
        if (this.associatedToStreamId != 0) {
            sb.append("--> Associated-To-Stream-ID = ");
            sb.append(this.associatedToStreamId);
            sb.append(StringUtil.NEWLINE);
        }
        sb.append("--> Priority = ");
        sb.append((int) this.priority);
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Headers:");
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }
}
