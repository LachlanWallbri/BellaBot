package io.netty.handler.codec.memcache;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface MemcacheContent extends MemcacheObject, ByteBufHolder {
    @Override // io.netty.buffer.ByteBufHolder
    MemcacheContent copy();

    @Override // io.netty.buffer.ByteBufHolder
    MemcacheContent duplicate();

    @Override // io.netty.buffer.ByteBufHolder
    MemcacheContent replace(ByteBuf byteBuf);

    MemcacheContent retain();

    MemcacheContent retain(int i);

    @Override // io.netty.buffer.ByteBufHolder
    MemcacheContent retainedDuplicate();

    MemcacheContent touch();

    MemcacheContent touch(Object obj);
}
