package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttpRequest extends DefaultHttpMessage implements HttpRequest {
    private static final int HASH_CODE_PRIME = 31;
    private HttpMethod method;
    private String uri;

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod httpMethod, String str) {
        this(httpVersion, httpMethod, str, true);
    }

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod httpMethod, String str, boolean z) {
        super(httpVersion, z, false);
        this.method = (HttpMethod) ObjectUtil.checkNotNull(httpMethod, "method");
        this.uri = (String) ObjectUtil.checkNotNull(str, "uri");
    }

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod httpMethod, String str, HttpHeaders httpHeaders) {
        super(httpVersion, httpHeaders);
        this.method = (HttpMethod) ObjectUtil.checkNotNull(httpMethod, "method");
        this.uri = (String) ObjectUtil.checkNotNull(str, "uri");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
    @Deprecated
    public HttpMethod getMethod() {
        return method();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
    public HttpMethod method() {
        return this.method;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
    @Deprecated
    public String getUri() {
        return uri();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
    public String uri() {
        return this.uri;
    }

    public HttpRequest setMethod(HttpMethod httpMethod) {
        this.method = (HttpMethod) ObjectUtil.checkNotNull(httpMethod, "method");
        return this;
    }

    public HttpRequest setUri(String str) {
        this.uri = (String) ObjectUtil.checkNotNull(str, "uri");
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
    public HttpRequest setProtocolVersion(HttpVersion httpVersion) {
        super.setProtocolVersion(httpVersion);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpObject
    public int hashCode() {
        return ((((this.method.hashCode() + 31) * 31) + this.uri.hashCode()) * 31) + super.hashCode();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttpRequest)) {
            return false;
        }
        DefaultHttpRequest defaultHttpRequest = (DefaultHttpRequest) obj;
        return method().equals(defaultHttpRequest.method()) && uri().equalsIgnoreCase(defaultHttpRequest.uri()) && super.equals(obj);
    }

    public String toString() {
        return HttpMessageUtil.appendRequest(new StringBuilder(256), this).toString();
    }
}
