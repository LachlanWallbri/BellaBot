package io.netty.handler.codec.memcache.binary;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultBinaryMemcacheResponse extends AbstractBinaryMemcacheMessage implements BinaryMemcacheResponse {
    public static final byte RESPONSE_MAGIC_BYTE = -127;
    private short status;

    public DefaultBinaryMemcacheResponse() {
        this(null, null);
    }

    public DefaultBinaryMemcacheResponse(ByteBuf byteBuf) {
        this(byteBuf, null);
    }

    public DefaultBinaryMemcacheResponse(ByteBuf byteBuf, ByteBuf byteBuf2) {
        super(byteBuf, byteBuf2);
        setMagic(RESPONSE_MAGIC_BYTE);
    }

    @Override // io.netty.handler.codec.memcache.binary.BinaryMemcacheResponse
    public short status() {
        return this.status;
    }

    @Override // io.netty.handler.codec.memcache.binary.BinaryMemcacheResponse
    public BinaryMemcacheResponse setStatus(short s) {
        this.status = s;
        return this;
    }

    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheMessage, io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public BinaryMemcacheResponse retain() {
        super.retain();
        return this;
    }

    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheMessage, io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public BinaryMemcacheResponse retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheMessage, io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public BinaryMemcacheResponse touch() {
        super.touch();
        return this;
    }

    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheMessage, io.netty.util.ReferenceCounted
    public BinaryMemcacheResponse touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
