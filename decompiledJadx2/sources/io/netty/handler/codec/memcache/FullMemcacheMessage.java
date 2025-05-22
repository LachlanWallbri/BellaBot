package io.netty.handler.codec.memcache;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface FullMemcacheMessage extends MemcacheMessage, LastMemcacheContent {
    @Override // io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullMemcacheMessage copy();

    @Override // io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullMemcacheMessage duplicate();

    @Override // io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullMemcacheMessage replace(ByteBuf byteBuf);

    @Override // io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullMemcacheMessage retain();

    @Override // io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullMemcacheMessage retain(int i);

    @Override // io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullMemcacheMessage retainedDuplicate();

    @Override // io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullMemcacheMessage touch();

    @Override // io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullMemcacheMessage touch(Object obj);
}
