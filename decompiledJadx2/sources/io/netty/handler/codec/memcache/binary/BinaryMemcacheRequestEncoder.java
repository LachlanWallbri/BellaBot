package io.netty.handler.codec.memcache.binary;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class BinaryMemcacheRequestEncoder extends AbstractBinaryMemcacheEncoder<BinaryMemcacheRequest> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheEncoder
    public void encodeHeader(ByteBuf byteBuf, BinaryMemcacheRequest binaryMemcacheRequest) {
        byteBuf.writeByte(binaryMemcacheRequest.magic());
        byteBuf.writeByte(binaryMemcacheRequest.opcode());
        byteBuf.writeShort(binaryMemcacheRequest.keyLength());
        byteBuf.writeByte(binaryMemcacheRequest.extrasLength());
        byteBuf.writeByte(binaryMemcacheRequest.dataType());
        byteBuf.writeShort(binaryMemcacheRequest.reserved());
        byteBuf.writeInt(binaryMemcacheRequest.totalBodyLength());
        byteBuf.writeInt(binaryMemcacheRequest.opaque());
        byteBuf.writeLong(binaryMemcacheRequest.cas());
    }
}
