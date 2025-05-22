package io.netty.handler.codec.redis;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum RedisMessageType {
    INLINE_COMMAND(null, true),
    SIMPLE_STRING((byte) 43, true),
    ERROR((byte) 45, true),
    INTEGER((byte) 58, true),
    BULK_STRING(Byte.valueOf(BinaryMemcacheOpcodes.GATKQ), false),
    ARRAY_HEADER(Byte.valueOf(ClassDefinitionUtils.OPS_aload_0), false);

    private final boolean inline;
    private final Byte value;

    RedisMessageType(Byte b, boolean z) {
        this.value = b;
        this.inline = z;
    }

    public int length() {
        return this.value != null ? 1 : 0;
    }

    public boolean isInline() {
        return this.inline;
    }

    public static RedisMessageType readFrom(ByteBuf byteBuf, boolean z) {
        int readerIndex = byteBuf.readerIndex();
        RedisMessageType valueOf = valueOf(byteBuf.readByte());
        if (valueOf == INLINE_COMMAND) {
            if (!z) {
                throw new RedisCodecException("Decoding of inline commands is disabled");
            }
            byteBuf.readerIndex(readerIndex);
        }
        return valueOf;
    }

    public void writeTo(ByteBuf byteBuf) {
        Byte b = this.value;
        if (b == null) {
            return;
        }
        byteBuf.writeByte(b.byteValue());
    }

    private static RedisMessageType valueOf(byte b) {
        if (b == 36) {
            return BULK_STRING;
        }
        if (b == 45) {
            return ERROR;
        }
        if (b == 58) {
            return INTEGER;
        }
        if (b == 42) {
            return ARRAY_HEADER;
        }
        if (b == 43) {
            return SIMPLE_STRING;
        }
        return INLINE_COMMAND;
    }
}
