package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Http2GoAwayFrame extends Http2Frame, ByteBufHolder {
    @Override // io.netty.buffer.ByteBufHolder
    ByteBuf content();

    @Override // io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame copy();

    @Override // io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame duplicate();

    long errorCode();

    int extraStreamIds();

    int lastStreamId();

    @Override // io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame replace(ByteBuf byteBuf);

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2GoAwayFrame retain();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2GoAwayFrame retain(int i);

    @Override // io.netty.buffer.ByteBufHolder
    Http2GoAwayFrame retainedDuplicate();

    Http2GoAwayFrame setExtraStreamIds(int i);

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2GoAwayFrame touch();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    Http2GoAwayFrame touch(Object obj);
}
