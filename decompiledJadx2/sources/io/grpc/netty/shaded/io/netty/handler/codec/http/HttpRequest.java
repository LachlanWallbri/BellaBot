package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HttpRequest extends HttpMessage {
    @Deprecated
    HttpMethod getMethod();

    @Deprecated
    String getUri();

    HttpMethod method();

    HttpRequest setMethod(HttpMethod httpMethod);

    HttpRequest setProtocolVersion(HttpVersion httpVersion);

    HttpRequest setUri(String str);

    String uri();
}
