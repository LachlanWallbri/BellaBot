package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpRequestDecoder extends HttpObjectDecoder {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder
    protected boolean isDecodingRequest() {
        return true;
    }

    public HttpRequestDecoder() {
    }

    public HttpRequestDecoder(int i, int i2, int i3) {
        super(i, i2, i3, true);
    }

    public HttpRequestDecoder(int i, int i2, int i3, boolean z) {
        super(i, i2, i3, true, z);
    }

    public HttpRequestDecoder(int i, int i2, int i3, boolean z, int i4) {
        super(i, i2, i3, true, z, i4);
    }

    public HttpRequestDecoder(int i, int i2, int i3, boolean z, int i4, boolean z2) {
        super(i, i2, i3, true, z, i4, z2);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder
    protected HttpMessage createMessage(String[] strArr) throws Exception {
        return new DefaultHttpRequest(HttpVersion.valueOf(strArr[2]), HttpMethod.valueOf(strArr[0]), strArr[1], this.validateHeaders);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder
    protected HttpMessage createInvalidMessage() {
        return new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, "/bad-request", this.validateHeaders);
    }
}
