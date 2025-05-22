package io.netty.handler.codec.redis;

import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class ArrayHeaderRedisMessage implements RedisMessage {
    private final long length;

    public ArrayHeaderRedisMessage(long j) {
        if (j < -1) {
            throw new RedisCodecException("length: " + j + " (expected: >= -1)");
        }
        this.length = j;
    }

    public final long length() {
        return this.length;
    }

    public boolean isNull() {
        return this.length == -1;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[length=" + this.length + ']';
    }
}
