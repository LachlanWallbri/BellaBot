package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface FullHttpResponse extends HttpResponse, FullHttpMessage {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpResponse copy();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpResponse duplicate();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpResponse replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpResponse retain();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpResponse retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpResponse retainedDuplicate();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
    FullHttpResponse setProtocolVersion(HttpVersion httpVersion);

    FullHttpResponse setStatus(HttpResponseStatus httpResponseStatus);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpResponse touch();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpResponse touch(Object obj);
}
