package io.netty.handler.codec.memcache.binary;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.memcache.AbstractMemcacheObjectEncoder;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheMessage;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractBinaryMemcacheEncoder<M extends BinaryMemcacheMessage> extends AbstractMemcacheObjectEncoder<M> {
    private static final int MINIMUM_HEADER_SIZE = 24;

    protected abstract void encodeHeader(ByteBuf byteBuf, M m);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.memcache.AbstractMemcacheObjectEncoder
    public ByteBuf encodeMessage(ChannelHandlerContext channelHandlerContext, M m) {
        ByteBuf buffer = channelHandlerContext.alloc().buffer(m.extrasLength() + 24 + m.keyLength());
        encodeHeader(buffer, m);
        encodeExtras(buffer, m.extras());
        encodeKey(buffer, m.key());
        return buffer;
    }

    private static void encodeExtras(ByteBuf byteBuf, ByteBuf byteBuf2) {
        if (byteBuf2 == null || !byteBuf2.isReadable()) {
            return;
        }
        byteBuf.writeBytes(byteBuf2);
    }

    private static void encodeKey(ByteBuf byteBuf, ByteBuf byteBuf2) {
        if (byteBuf2 == null || !byteBuf2.isReadable()) {
            return;
        }
        byteBuf.writeBytes(byteBuf2);
    }
}
