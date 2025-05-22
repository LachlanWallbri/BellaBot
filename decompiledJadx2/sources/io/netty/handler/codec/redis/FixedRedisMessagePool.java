package io.netty.handler.codec.redis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import io.netty.util.collection.LongObjectHashMap;
import io.netty.util.collection.LongObjectMap;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class FixedRedisMessagePool implements RedisMessagePool {
    private static final long MAX_CACHED_INTEGER_NUMBER = 128;
    private static final long MIN_CACHED_INTEGER_NUMBER = -1;
    private static final int SIZE_CACHED_INTEGER_NUMBER = 129;
    private final Map<ByteBuf, ErrorRedisMessage> byteBufToErrors;
    private final Map<ByteBuf, IntegerRedisMessage> byteBufToIntegers;
    private final LongObjectMap<byte[]> longToByteBufs;
    private final LongObjectMap<IntegerRedisMessage> longToIntegers;
    private final Map<String, ErrorRedisMessage> stringToErrors;
    private static final String[] DEFAULT_SIMPLE_STRINGS = {"OK", "PONG", "QUEUED"};
    private static final String[] DEFAULT_ERRORS = {"ERR", "ERR index out of range", "ERR no such key", "ERR source and destination objects are the same", "ERR syntax error", "BUSY Redis is busy running a script. You can only call SCRIPT KILL or SHUTDOWN NOSAVE.", "BUSYKEY Target key name already exists.", "EXECABORT Transaction discarded because of previous errors.", "LOADING Redis is loading the dataset in memory", "MASTERDOWN Link with MASTER is down and slave-serve-stale-data is set to 'no'.", "MISCONF Redis is configured to save RDB snapshots, but is currently not able to persist on disk. Commands that may modify the data set are disabled. Please check Redis logs for details about the error.", "NOAUTH Authentication required.", "NOREPLICAS Not enough good slaves to write.", "NOSCRIPT No matching script. Please use EVAL.", "OOM command not allowed when used memory > 'maxmemory'.", "READONLY You can't write against a read only slave.", "WRONGTYPE Operation against a key holding the wrong kind of value"};
    public static final FixedRedisMessagePool INSTANCE = new FixedRedisMessagePool();
    private final Map<ByteBuf, SimpleStringRedisMessage> byteBufToSimpleStrings = new HashMap(DEFAULT_SIMPLE_STRINGS.length, 1.0f);
    private final Map<String, SimpleStringRedisMessage> stringToSimpleStrings = new HashMap(DEFAULT_SIMPLE_STRINGS.length, 1.0f);

    private FixedRedisMessagePool() {
        for (String str : DEFAULT_SIMPLE_STRINGS) {
            ByteBuf unmodifiableBuffer = Unpooled.unmodifiableBuffer(Unpooled.unreleasableBuffer(Unpooled.wrappedBuffer(str.getBytes(CharsetUtil.UTF_8))));
            SimpleStringRedisMessage simpleStringRedisMessage = new SimpleStringRedisMessage(str);
            this.byteBufToSimpleStrings.put(unmodifiableBuffer, simpleStringRedisMessage);
            this.stringToSimpleStrings.put(str, simpleStringRedisMessage);
        }
        this.byteBufToErrors = new HashMap(DEFAULT_ERRORS.length, 1.0f);
        this.stringToErrors = new HashMap(DEFAULT_ERRORS.length, 1.0f);
        for (String str2 : DEFAULT_ERRORS) {
            ByteBuf unmodifiableBuffer2 = Unpooled.unmodifiableBuffer(Unpooled.unreleasableBuffer(Unpooled.wrappedBuffer(str2.getBytes(CharsetUtil.UTF_8))));
            ErrorRedisMessage errorRedisMessage = new ErrorRedisMessage(str2);
            this.byteBufToErrors.put(unmodifiableBuffer2, errorRedisMessage);
            this.stringToErrors.put(str2, errorRedisMessage);
        }
        this.byteBufToIntegers = new HashMap(129, 1.0f);
        this.longToIntegers = new LongObjectHashMap(129, 1.0f);
        this.longToByteBufs = new LongObjectHashMap(129, 1.0f);
        for (long j = -1; j < 128; j++) {
            byte[] longToAsciiBytes = RedisCodecUtil.longToAsciiBytes(j);
            ByteBuf unmodifiableBuffer3 = Unpooled.unmodifiableBuffer(Unpooled.unreleasableBuffer(Unpooled.wrappedBuffer(longToAsciiBytes)));
            IntegerRedisMessage integerRedisMessage = new IntegerRedisMessage(j);
            this.byteBufToIntegers.put(unmodifiableBuffer3, integerRedisMessage);
            this.longToIntegers.put(j, (long) integerRedisMessage);
            this.longToByteBufs.put(j, (long) longToAsciiBytes);
        }
    }

    @Override // io.netty.handler.codec.redis.RedisMessagePool
    public SimpleStringRedisMessage getSimpleString(String str) {
        return this.stringToSimpleStrings.get(str);
    }

    @Override // io.netty.handler.codec.redis.RedisMessagePool
    public SimpleStringRedisMessage getSimpleString(ByteBuf byteBuf) {
        return this.byteBufToSimpleStrings.get(byteBuf);
    }

    @Override // io.netty.handler.codec.redis.RedisMessagePool
    public ErrorRedisMessage getError(String str) {
        return this.stringToErrors.get(str);
    }

    @Override // io.netty.handler.codec.redis.RedisMessagePool
    public ErrorRedisMessage getError(ByteBuf byteBuf) {
        return this.byteBufToErrors.get(byteBuf);
    }

    @Override // io.netty.handler.codec.redis.RedisMessagePool
    public IntegerRedisMessage getInteger(long j) {
        return this.longToIntegers.get(j);
    }

    @Override // io.netty.handler.codec.redis.RedisMessagePool
    public IntegerRedisMessage getInteger(ByteBuf byteBuf) {
        return this.byteBufToIntegers.get(byteBuf);
    }

    @Override // io.netty.handler.codec.redis.RedisMessagePool
    public byte[] getByteBufOfInteger(long j) {
        return this.longToByteBufs.get(j);
    }
}
