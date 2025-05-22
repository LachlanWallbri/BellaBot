package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2GoAwayFrame extends Http2Frame, ByteBufHolder {
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    ByteBuf content();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame copy();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame duplicate();

    long errorCode();

    int extraStreamIds();

    int lastStreamId();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2GoAwayFrame retain();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2GoAwayFrame retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame retainedDuplicate();

    Http2GoAwayFrame setExtraStreamIds(int i);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2GoAwayFrame touch();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    Http2GoAwayFrame touch(Object obj);
}
