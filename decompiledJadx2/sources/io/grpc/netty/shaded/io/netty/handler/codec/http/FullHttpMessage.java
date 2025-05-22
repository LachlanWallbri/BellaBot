package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface FullHttpMessage extends HttpMessage, LastHttpContent {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpMessage copy();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpMessage duplicate();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpMessage replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpMessage retain();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpMessage retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FullHttpMessage retainedDuplicate();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpMessage touch();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FullHttpMessage touch(Object obj);
}
