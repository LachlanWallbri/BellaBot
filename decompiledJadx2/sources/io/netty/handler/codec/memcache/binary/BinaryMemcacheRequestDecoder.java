package io.netty.handler.codec.memcache.binary;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class BinaryMemcacheRequestDecoder extends AbstractBinaryMemcacheDecoder<BinaryMemcacheRequest> {
    public BinaryMemcacheRequestDecoder() {
        this(8192);
    }

    public BinaryMemcacheRequestDecoder(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheDecoder
    public BinaryMemcacheRequest decodeHeader(ByteBuf byteBuf) {
        DefaultBinaryMemcacheRequest defaultBinaryMemcacheRequest = new DefaultBinaryMemcacheRequest();
        defaultBinaryMemcacheRequest.setMagic(byteBuf.readByte());
        defaultBinaryMemcacheRequest.setOpcode(byteBuf.readByte());
        defaultBinaryMemcacheRequest.setKeyLength(byteBuf.readShort());
        defaultBinaryMemcacheRequest.setExtrasLength(byteBuf.readByte());
        defaultBinaryMemcacheRequest.setDataType(byteBuf.readByte());
        defaultBinaryMemcacheRequest.setReserved(byteBuf.readShort());
        defaultBinaryMemcacheRequest.setTotalBodyLength(byteBuf.readInt());
        defaultBinaryMemcacheRequest.setOpaque(byteBuf.readInt());
        defaultBinaryMemcacheRequest.setCas(byteBuf.readLong());
        return defaultBinaryMemcacheRequest;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheDecoder
    public BinaryMemcacheRequest buildInvalidMessage() {
        return new DefaultBinaryMemcacheRequest(Unpooled.EMPTY_BUFFER, Unpooled.EMPTY_BUFFER);
    }
}
