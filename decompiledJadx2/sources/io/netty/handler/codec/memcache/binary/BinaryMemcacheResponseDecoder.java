package io.netty.handler.codec.memcache.binary;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class BinaryMemcacheResponseDecoder extends AbstractBinaryMemcacheDecoder<BinaryMemcacheResponse> {
    public BinaryMemcacheResponseDecoder() {
        this(8192);
    }

    public BinaryMemcacheResponseDecoder(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheDecoder
    public BinaryMemcacheResponse decodeHeader(ByteBuf byteBuf) {
        DefaultBinaryMemcacheResponse defaultBinaryMemcacheResponse = new DefaultBinaryMemcacheResponse();
        defaultBinaryMemcacheResponse.setMagic(byteBuf.readByte());
        defaultBinaryMemcacheResponse.setOpcode(byteBuf.readByte());
        defaultBinaryMemcacheResponse.setKeyLength(byteBuf.readShort());
        defaultBinaryMemcacheResponse.setExtrasLength(byteBuf.readByte());
        defaultBinaryMemcacheResponse.setDataType(byteBuf.readByte());
        defaultBinaryMemcacheResponse.setStatus(byteBuf.readShort());
        defaultBinaryMemcacheResponse.setTotalBodyLength(byteBuf.readInt());
        defaultBinaryMemcacheResponse.setOpaque(byteBuf.readInt());
        defaultBinaryMemcacheResponse.setCas(byteBuf.readLong());
        return defaultBinaryMemcacheResponse;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheDecoder
    public BinaryMemcacheResponse buildInvalidMessage() {
        return new DefaultBinaryMemcacheResponse(Unpooled.EMPTY_BUFFER, Unpooled.EMPTY_BUFFER);
    }
}
