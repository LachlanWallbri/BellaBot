package org.jboss.netty.handler.codec.http;

/* loaded from: classes7.dex */
public class HttpResponseDecoder extends HttpMessageDecoder {
    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
    protected boolean isDecodingRequest() {
        return false;
    }

    public HttpResponseDecoder() {
    }

    public HttpResponseDecoder(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
    protected HttpMessage createMessage(String[] strArr) {
        return new DefaultHttpResponse(HttpVersion.valueOf(strArr[0]), new HttpResponseStatus(Integer.valueOf(strArr[1]).intValue(), strArr[2]));
    }
}
