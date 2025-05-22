package io.netty.handler.codec.http;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface HttpResponse extends HttpMessage {
    @Deprecated
    HttpResponseStatus getStatus();

    @Override // io.netty.handler.codec.http.HttpMessage, io.netty.handler.codec.http.HttpRequest, io.netty.handler.codec.http.FullHttpRequest
    HttpResponse setProtocolVersion(HttpVersion httpVersion);

    HttpResponse setStatus(HttpResponseStatus httpResponseStatus);

    HttpResponseStatus status();
}
