package io.netty.handler.codec.redis;

import com.iflytek.aiui.AIUIConstant;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractStringRedisMessage implements RedisMessage {
    private final String content;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractStringRedisMessage(String str) {
        this.content = (String) ObjectUtil.checkNotNull(str, AIUIConstant.KEY_CONTENT);
    }

    public final String content() {
        return this.content;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[content=" + this.content + ']';
    }
}
