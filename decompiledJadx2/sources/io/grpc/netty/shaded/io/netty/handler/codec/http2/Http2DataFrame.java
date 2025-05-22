package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2DataFrame extends Http2StreamFrame, ByteBufHolder {
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    ByteBuf content();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2DataFrame copy();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2DataFrame duplicate();

    int initialFlowControlledBytes();

    boolean isEndStream();

    int padding();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2DataFrame replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2DataFrame retain();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2DataFrame retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2DataFrame retainedDuplicate();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2DataFrame touch();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2DataFrame touch(Object obj);
}
