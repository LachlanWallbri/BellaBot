package org.jboss.netty.handler.codec.spdy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdyHeaderBlock implements SpdyHeaderBlock {
    private final SpdyHeaders headers = new SpdyHeaders();
    private boolean invalid;

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public boolean isInvalid() {
        return this.invalid;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public void setInvalid() {
        this.invalid = true;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public void addHeader(String str, Object obj) {
        this.headers.addHeader(str, obj);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public void setHeader(String str, Object obj) {
        this.headers.setHeader(str, obj);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public void setHeader(String str, Iterable<?> iterable) {
        this.headers.setHeader(str, iterable);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public void removeHeader(String str) {
        this.headers.removeHeader(str);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public void clearHeaders() {
        this.headers.clearHeaders();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public String getHeader(String str) {
        return this.headers.getHeader(str);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public List<String> getHeaders(String str) {
        return this.headers.getHeaders(str);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public List<Map.Entry<String, String>> getHeaders() {
        return this.headers.getHeaders();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public boolean containsHeader(String str) {
        return this.headers.containsHeader(str);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlock
    public Set<String> getHeaderNames() {
        return this.headers.getHeaderNames();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendHeaders(StringBuilder sb) {
        for (Map.Entry<String, String> entry : getHeaders()) {
            sb.append("    ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append(StringUtil.NEWLINE);
        }
    }
}
