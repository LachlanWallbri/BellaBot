package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttpResponse extends DefaultHttpMessage implements HttpResponse {
    private HttpResponseStatus status;

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus) {
        this(httpVersion, httpResponseStatus, true, false);
    }

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus, boolean z) {
        this(httpVersion, httpResponseStatus, z, false);
    }

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus, boolean z, boolean z2) {
        super(httpVersion, z, z2);
        this.status = (HttpResponseStatus) ObjectUtil.checkNotNull(httpResponseStatus, "status");
    }

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus, HttpHeaders httpHeaders) {
        super(httpVersion, httpHeaders);
        this.status = (HttpResponseStatus) ObjectUtil.checkNotNull(httpResponseStatus, "status");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse
    @Deprecated
    public HttpResponseStatus getStatus() {
        return status();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse
    public HttpResponseStatus status() {
        return this.status;
    }

    public HttpResponse setStatus(HttpResponseStatus httpResponseStatus) {
        this.status = (HttpResponseStatus) ObjectUtil.checkNotNull(httpResponseStatus, "status");
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
    public HttpResponse setProtocolVersion(HttpVersion httpVersion) {
        super.setProtocolVersion(httpVersion);
        return this;
    }

    public String toString() {
        return HttpMessageUtil.appendResponse(new StringBuilder(256), this).toString();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpObject
    public int hashCode() {
        return ((this.status.hashCode() + 31) * 31) + super.hashCode();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpObject
    public boolean equals(Object obj) {
        return (obj instanceof DefaultHttpResponse) && this.status.equals(((DefaultHttpResponse) obj).status()) && super.equals(obj);
    }
}
