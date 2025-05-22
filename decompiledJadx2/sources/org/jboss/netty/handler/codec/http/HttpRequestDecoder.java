package org.jboss.netty.handler.codec.http;

/* loaded from: classes7.dex */
public class HttpRequestDecoder extends HttpMessageDecoder {
    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
    protected boolean isDecodingRequest() {
        return true;
    }

    public HttpRequestDecoder() {
    }

    public HttpRequestDecoder(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
    protected HttpMessage createMessage(String[] strArr) throws Exception {
        return new DefaultHttpRequest(HttpVersion.valueOf(strArr[2]), HttpMethod.valueOf(strArr[0]), strArr[1]);
    }
}
