package org.jboss.netty.handler.codec.http;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultHttpResponse extends DefaultHttpMessage implements HttpResponse {
    private HttpResponseStatus status;

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus) {
        super(httpVersion);
        setStatus(httpResponseStatus);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpResponse
    public HttpResponseStatus getStatus() {
        return this.status;
    }

    @Override // org.jboss.netty.handler.codec.http.HttpResponse
    public void setStatus(HttpResponseStatus httpResponseStatus) {
        if (httpResponseStatus == null) {
            throw new NullPointerException("status");
        }
        this.status = httpResponseStatus;
    }

    @Override // org.jboss.netty.handler.codec.http.DefaultHttpMessage
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(chunked: ");
        sb.append(isChunked());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append(getProtocolVersion().getText());
        sb.append(' ');
        sb.append(getStatus().toString());
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }
}
