package io.netty.handler.codec.redis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.internal.ObjectUtil;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class RedisEncoder extends MessageToMessageEncoder<RedisMessage> {
    private final RedisMessagePool messagePool;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, RedisMessage redisMessage, List list) throws Exception {
        encode2(channelHandlerContext, redisMessage, (List<Object>) list);
    }

    public RedisEncoder() {
        this(FixedRedisMessagePool.INSTANCE);
    }

    public RedisEncoder(RedisMessagePool redisMessagePool) {
        this.messagePool = (RedisMessagePool) ObjectUtil.checkNotNull(redisMessagePool, "messagePool");
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, RedisMessage redisMessage, List<Object> list) throws Exception {
        try {
            writeRedisMessage(channelHandlerContext.alloc(), redisMessage, list);
        } catch (CodecException e) {
            throw e;
        } catch (Exception e2) {
            throw new CodecException(e2);
        }
    }

    private void writeRedisMessage(ByteBufAllocator byteBufAllocator, RedisMessage redisMessage, List<Object> list) {
        if (redisMessage instanceof InlineCommandRedisMessage) {
            writeInlineCommandMessage(byteBufAllocator, (InlineCommandRedisMessage) redisMessage, list);
            return;
        }
        if (redisMessage instanceof SimpleStringRedisMessage) {
            writeSimpleStringMessage(byteBufAllocator, (SimpleStringRedisMessage) redisMessage, list);
            return;
        }
        if (redisMessage instanceof ErrorRedisMessage) {
            writeErrorMessage(byteBufAllocator, (ErrorRedisMessage) redisMessage, list);
            return;
        }
        if (redisMessage instanceof IntegerRedisMessage) {
            writeIntegerMessage(byteBufAllocator, (IntegerRedisMessage) redisMessage, list);
            return;
        }
        if (redisMessage instanceof FullBulkStringRedisMessage) {
            writeFullBulkStringMessage(byteBufAllocator, (FullBulkStringRedisMessage) redisMessage, list);
            return;
        }
        if (redisMessage instanceof BulkStringRedisContent) {
            writeBulkStringContent(byteBufAllocator, (BulkStringRedisContent) redisMessage, list);
            return;
        }
        if (redisMessage instanceof BulkStringHeaderRedisMessage) {
            writeBulkStringHeader(byteBufAllocator, (BulkStringHeaderRedisMessage) redisMessage, list);
            return;
        }
        if (redisMessage instanceof ArrayHeaderRedisMessage) {
            writeArrayHeader(byteBufAllocator, (ArrayHeaderRedisMessage) redisMessage, list);
        } else {
            if (redisMessage instanceof ArrayRedisMessage) {
                writeArrayMessage(byteBufAllocator, (ArrayRedisMessage) redisMessage, list);
                return;
            }
            throw new CodecException("unknown message type: " + redisMessage);
        }
    }

    private static void writeInlineCommandMessage(ByteBufAllocator byteBufAllocator, InlineCommandRedisMessage inlineCommandRedisMessage, List<Object> list) {
        writeString(byteBufAllocator, RedisMessageType.INLINE_COMMAND, inlineCommandRedisMessage.content(), list);
    }

    private static void writeSimpleStringMessage(ByteBufAllocator byteBufAllocator, SimpleStringRedisMessage simpleStringRedisMessage, List<Object> list) {
        writeString(byteBufAllocator, RedisMessageType.SIMPLE_STRING, simpleStringRedisMessage.content(), list);
    }

    private static void writeErrorMessage(ByteBufAllocator byteBufAllocator, ErrorRedisMessage errorRedisMessage, List<Object> list) {
        writeString(byteBufAllocator, RedisMessageType.ERROR, errorRedisMessage.content(), list);
    }

    private static void writeString(ByteBufAllocator byteBufAllocator, RedisMessageType redisMessageType, String str, List<Object> list) {
        ByteBuf ioBuffer = byteBufAllocator.ioBuffer(redisMessageType.length() + ByteBufUtil.utf8MaxBytes(str) + 2);
        redisMessageType.writeTo(ioBuffer);
        ByteBufUtil.writeUtf8(ioBuffer, str);
        ioBuffer.writeShort(RedisConstants.EOL_SHORT);
        list.add(ioBuffer);
    }

    private void writeIntegerMessage(ByteBufAllocator byteBufAllocator, IntegerRedisMessage integerRedisMessage, List<Object> list) {
        ByteBuf ioBuffer = byteBufAllocator.ioBuffer(23);
        RedisMessageType.INTEGER.writeTo(ioBuffer);
        ioBuffer.writeBytes(numberToBytes(integerRedisMessage.value()));
        ioBuffer.writeShort(RedisConstants.EOL_SHORT);
        list.add(ioBuffer);
    }

    private void writeBulkStringHeader(ByteBufAllocator byteBufAllocator, BulkStringHeaderRedisMessage bulkStringHeaderRedisMessage, List<Object> list) {
        ByteBuf ioBuffer = byteBufAllocator.ioBuffer((bulkStringHeaderRedisMessage.isNull() ? 2 : 22) + 1);
        RedisMessageType.BULK_STRING.writeTo(ioBuffer);
        if (bulkStringHeaderRedisMessage.isNull()) {
            ioBuffer.writeShort(RedisConstants.NULL_SHORT);
        } else {
            ioBuffer.writeBytes(numberToBytes(bulkStringHeaderRedisMessage.bulkStringLength()));
            ioBuffer.writeShort(RedisConstants.EOL_SHORT);
        }
        list.add(ioBuffer);
    }

    private static void writeBulkStringContent(ByteBufAllocator byteBufAllocator, BulkStringRedisContent bulkStringRedisContent, List<Object> list) {
        list.add(bulkStringRedisContent.content().retain());
        if (bulkStringRedisContent instanceof LastBulkStringRedisContent) {
            list.add(byteBufAllocator.ioBuffer(2).writeShort(RedisConstants.EOL_SHORT));
        }
    }

    private void writeFullBulkStringMessage(ByteBufAllocator byteBufAllocator, FullBulkStringRedisMessage fullBulkStringRedisMessage, List<Object> list) {
        if (fullBulkStringRedisMessage.isNull()) {
            ByteBuf ioBuffer = byteBufAllocator.ioBuffer(5);
            RedisMessageType.BULK_STRING.writeTo(ioBuffer);
            ioBuffer.writeShort(RedisConstants.NULL_SHORT);
            ioBuffer.writeShort(RedisConstants.EOL_SHORT);
            list.add(ioBuffer);
            return;
        }
        ByteBuf ioBuffer2 = byteBufAllocator.ioBuffer(23);
        RedisMessageType.BULK_STRING.writeTo(ioBuffer2);
        ioBuffer2.writeBytes(numberToBytes(fullBulkStringRedisMessage.content().readableBytes()));
        ioBuffer2.writeShort(RedisConstants.EOL_SHORT);
        list.add(ioBuffer2);
        list.add(fullBulkStringRedisMessage.content().retain());
        list.add(byteBufAllocator.ioBuffer(2).writeShort(RedisConstants.EOL_SHORT));
    }

    private void writeArrayHeader(ByteBufAllocator byteBufAllocator, ArrayHeaderRedisMessage arrayHeaderRedisMessage, List<Object> list) {
        writeArrayHeader(byteBufAllocator, arrayHeaderRedisMessage.isNull(), arrayHeaderRedisMessage.length(), list);
    }

    private void writeArrayMessage(ByteBufAllocator byteBufAllocator, ArrayRedisMessage arrayRedisMessage, List<Object> list) {
        if (arrayRedisMessage.isNull()) {
            writeArrayHeader(byteBufAllocator, arrayRedisMessage.isNull(), -1L, list);
            return;
        }
        writeArrayHeader(byteBufAllocator, arrayRedisMessage.isNull(), arrayRedisMessage.children().size(), list);
        Iterator<RedisMessage> it = arrayRedisMessage.children().iterator();
        while (it.hasNext()) {
            writeRedisMessage(byteBufAllocator, it.next(), list);
        }
    }

    private void writeArrayHeader(ByteBufAllocator byteBufAllocator, boolean z, long j, List<Object> list) {
        if (z) {
            ByteBuf ioBuffer = byteBufAllocator.ioBuffer(5);
            RedisMessageType.ARRAY_HEADER.writeTo(ioBuffer);
            ioBuffer.writeShort(RedisConstants.NULL_SHORT);
            ioBuffer.writeShort(RedisConstants.EOL_SHORT);
            list.add(ioBuffer);
            return;
        }
        ByteBuf ioBuffer2 = byteBufAllocator.ioBuffer(23);
        RedisMessageType.ARRAY_HEADER.writeTo(ioBuffer2);
        ioBuffer2.writeBytes(numberToBytes(j));
        ioBuffer2.writeShort(RedisConstants.EOL_SHORT);
        list.add(ioBuffer2);
    }

    private byte[] numberToBytes(long j) {
        byte[] byteBufOfInteger = this.messagePool.getByteBufOfInteger(j);
        return byteBufOfInteger != null ? byteBufOfInteger : RedisCodecUtil.longToAsciiBytes(j);
    }
}
