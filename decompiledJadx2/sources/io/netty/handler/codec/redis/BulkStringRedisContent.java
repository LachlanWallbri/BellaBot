package io.netty.handler.codec.redis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface BulkStringRedisContent extends RedisMessage, ByteBufHolder {
    @Override // io.netty.buffer.ByteBufHolder
    BulkStringRedisContent copy();

    @Override // io.netty.buffer.ByteBufHolder
    BulkStringRedisContent duplicate();

    @Override // io.netty.buffer.ByteBufHolder
    BulkStringRedisContent replace(ByteBuf byteBuf);

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    BulkStringRedisContent retain();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    BulkStringRedisContent retain(int i);

    @Override // io.netty.buffer.ByteBufHolder
    BulkStringRedisContent retainedDuplicate();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    BulkStringRedisContent touch();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    BulkStringRedisContent touch(Object obj);
}
