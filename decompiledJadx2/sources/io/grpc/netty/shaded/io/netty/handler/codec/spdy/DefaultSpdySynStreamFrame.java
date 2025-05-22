package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdySynStreamFrame extends DefaultSpdyHeadersFrame implements SpdySynStreamFrame {
    private int associatedStreamId;
    private byte priority;
    private boolean unidirectional;

    public DefaultSpdySynStreamFrame(int i, int i2, byte b) {
        this(i, i2, b, true);
    }

    public DefaultSpdySynStreamFrame(int i, int i2, byte b, boolean z) {
        super(i, z);
        setAssociatedStreamId(i2);
        setPriority(b);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyHeadersFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdySynStreamFrame setStreamId(int i) {
        super.setStreamId(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyHeadersFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdySynStreamFrame setLast(boolean z) {
        super.setLast(z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyHeadersFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    public SpdySynStreamFrame setInvalid() {
        super.setInvalid();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySynStreamFrame
    public int associatedStreamId() {
        return this.associatedStreamId;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySynStreamFrame
    public SpdySynStreamFrame setAssociatedStreamId(int i) {
        ObjectUtil.checkPositiveOrZero(i, "associatedStreamId");
        this.associatedStreamId = i;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySynStreamFrame
    public byte priority() {
        return this.priority;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySynStreamFrame
    public SpdySynStreamFrame setPriority(byte b) {
        if (b < 0 || b > 7) {
            throw new IllegalArgumentException("Priority must be between 0 and 7 inclusive: " + ((int) b));
        }
        this.priority = b;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySynStreamFrame
    public boolean isUnidirectional() {
        return this.unidirectional;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySynStreamFrame
    public SpdySynStreamFrame setUnidirectional(boolean z) {
        this.unidirectional = z;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyHeadersFrame
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName(this));
        sb.append("(last: ");
        sb.append(isLast());
        sb.append("; unidirectional: ");
        sb.append(isUnidirectional());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Stream-ID = ");
        sb.append(streamId());
        sb.append(StringUtil.NEWLINE);
        if (this.associatedStreamId != 0) {
            sb.append("--> Associated-To-Stream-ID = ");
            sb.append(associatedStreamId());
            sb.append(StringUtil.NEWLINE);
        }
        sb.append("--> Priority = ");
        sb.append((int) priority());
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Headers:");
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }
}
