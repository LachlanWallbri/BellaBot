package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdyHeadersFrame extends DefaultSpdyStreamFrame implements SpdyHeadersFrame {
    private final SpdyHeaders headers;
    private boolean invalid;
    private boolean truncated;

    public DefaultSpdyHeadersFrame(int i) {
        this(i, true);
    }

    public DefaultSpdyHeadersFrame(int i, boolean z) {
        super(i);
        this.headers = new DefaultSpdyHeaders(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyHeadersFrame setStreamId(int i) {
        super.setStreamId(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyHeadersFrame setLast(boolean z) {
        super.setLast(z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    public boolean isInvalid() {
        return this.invalid;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    public SpdyHeadersFrame setInvalid() {
        this.invalid = true;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    public boolean isTruncated() {
        return this.truncated;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    public SpdyHeadersFrame setTruncated() {
        this.truncated = true;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    public SpdyHeaders headers() {
        return this.headers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName(this));
        sb.append("(last: ");
        sb.append(isLast());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Stream-ID = ");
        sb.append(streamId());
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Headers:");
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendHeaders(StringBuilder sb) {
        for (Map.Entry<CharSequence, CharSequence> entry : headers()) {
            sb.append("    ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append(StringUtil.NEWLINE);
        }
    }
}
