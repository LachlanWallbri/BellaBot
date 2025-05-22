package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface FullHttpRequest extends HttpRequest, FullHttpMessage {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpRequest copy();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpRequest duplicate();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpRequest replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpRequest retain();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpRequest retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpRequest retainedDuplicate();

    FullHttpRequest setMethod(HttpMethod httpMethod);

    FullHttpRequest setProtocolVersion(HttpVersion httpVersion);

    FullHttpRequest setUri(String str);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpRequest touch();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpRequest touch(Object obj);
}
