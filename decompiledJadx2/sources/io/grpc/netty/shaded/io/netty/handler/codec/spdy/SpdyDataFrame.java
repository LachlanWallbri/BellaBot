package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface SpdyDataFrame extends ByteBufHolder, SpdyStreamFrame {
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    ByteBuf content();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    SpdyDataFrame copy();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    SpdyDataFrame duplicate();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    SpdyDataFrame replace(ByteBuf byteBuf);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    SpdyDataFrame retain();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    SpdyDataFrame retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    SpdyDataFrame retainedDuplicate();

    SpdyDataFrame setLast(boolean z);

    SpdyDataFrame setStreamId(int i);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    SpdyDataFrame touch();

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    SpdyDataFrame touch(Object obj);
}
