package io.netty.handler.codec.memcache.binary;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.memcache.FullMemcacheMessage;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface FullBinaryMemcacheRequest extends BinaryMemcacheRequest, FullMemcacheMessage {
    @Override // io.netty.handler.codec.memcache.FullMemcacheMessage, io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullBinaryMemcacheRequest copy();

    @Override // io.netty.handler.codec.memcache.FullMemcacheMessage, io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullBinaryMemcacheRequest duplicate();

    @Override // io.netty.handler.codec.memcache.FullMemcacheMessage, io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullBinaryMemcacheRequest replace(ByteBuf byteBuf);

    @Override // io.netty.handler.codec.memcache.binary.BinaryMemcacheRequest, io.netty.handler.codec.memcache.binary.BinaryMemcacheMessage, io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullBinaryMemcacheRequest retain();

    @Override // io.netty.handler.codec.memcache.binary.BinaryMemcacheRequest, io.netty.handler.codec.memcache.binary.BinaryMemcacheMessage, io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullBinaryMemcacheRequest retain(int i);

    @Override // io.netty.handler.codec.memcache.FullMemcacheMessage, io.netty.handler.codec.memcache.LastMemcacheContent, io.netty.handler.codec.memcache.MemcacheContent, io.netty.buffer.ByteBufHolder
    FullBinaryMemcacheRequest retainedDuplicate();

    @Override // io.netty.handler.codec.memcache.binary.BinaryMemcacheRequest, io.netty.handler.codec.memcache.binary.BinaryMemcacheMessage, io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullBinaryMemcacheRequest touch();

    @Override // io.netty.handler.codec.memcache.binary.BinaryMemcacheRequest, io.netty.handler.codec.memcache.binary.BinaryMemcacheMessage, io.netty.handler.codec.memcache.MemcacheMessage, io.netty.util.ReferenceCounted
    FullBinaryMemcacheRequest touch(Object obj);
}
