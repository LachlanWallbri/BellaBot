package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2UnknownFrame extends Http2StreamFrame, ByteBufHolder {
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2UnknownFrame copy();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2UnknownFrame duplicate();

    Http2Flags flags();

    byte frameType();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2UnknownFrame replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2UnknownFrame retain();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2UnknownFrame retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2UnknownFrame retainedDuplicate();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamFrame
    Http2FrameStream stream();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamFrame
    Http2UnknownFrame stream(Http2FrameStream http2FrameStream);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2UnknownFrame touch();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2UnknownFrame touch(Object obj);
}
