package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HttpResponse extends HttpMessage {
    @Deprecated
    HttpResponseStatus getStatus();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
    HttpResponse setProtocolVersion(HttpVersion httpVersion);

    HttpResponse setStatus(HttpResponseStatus httpResponseStatus);

    HttpResponseStatus status();
}
