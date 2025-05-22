package io.netty.handler.codec.redis;

import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class IntegerRedisMessage implements RedisMessage {
    private final long value;

    public IntegerRedisMessage(long j) {
        this.value = j;
    }

    public long value() {
        return this.value;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[value=" + this.value + ']';
    }
}
