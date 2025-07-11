package io.netty.handler.codec.stomp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface StompContentSubframe extends ByteBufHolder, StompSubframe {
    @Override // io.netty.buffer.ByteBufHolder
    StompContentSubframe copy();

    @Override // io.netty.buffer.ByteBufHolder
    StompContentSubframe duplicate();

    @Override // io.netty.buffer.ByteBufHolder
    StompContentSubframe replace(ByteBuf byteBuf);

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    StompContentSubframe retain();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    StompContentSubframe retain(int i);

    @Override // io.netty.buffer.ByteBufHolder
    StompContentSubframe retainedDuplicate();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    StompContentSubframe touch();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    StompContentSubframe touch(Object obj);
}
