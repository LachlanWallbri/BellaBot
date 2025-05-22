package org.jboss.netty.handler.codec.http;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultHttpRequest extends DefaultHttpMessage implements HttpRequest {
    private HttpMethod method;
    private String uri;

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod httpMethod, String str) {
        super(httpVersion);
        setMethod(httpMethod);
        setUri(str);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpRequest
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // org.jboss.netty.handler.codec.http.HttpRequest
    public void setMethod(HttpMethod httpMethod) {
        if (httpMethod == null) {
            throw new NullPointerException("method");
        }
        this.method = httpMethod;
    }

    @Override // org.jboss.netty.handler.codec.http.HttpRequest
    public String getUri() {
        return this.uri;
    }

    @Override // org.jboss.netty.handler.codec.http.HttpRequest
    public void setUri(String str) {
        if (str == null) {
            throw new NullPointerException("uri");
        }
        this.uri = str;
    }

    @Override // org.jboss.netty.handler.codec.http.DefaultHttpMessage
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(chunked: ");
        sb.append(isChunked());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append(getMethod().toString());
        sb.append(' ');
        sb.append(getUri());
        sb.append(' ');
        sb.append(getProtocolVersion().getText());
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }
}
