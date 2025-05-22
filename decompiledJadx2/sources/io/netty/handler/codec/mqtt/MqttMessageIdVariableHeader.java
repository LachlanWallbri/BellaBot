package io.netty.handler.codec.mqtt;

import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttMessageIdVariableHeader {
    private final int messageId;

    public static MqttMessageIdVariableHeader from(int i) {
        if (i < 1 || i > 65535) {
            throw new IllegalArgumentException("messageId: " + i + " (expected: 1 ~ 65535)");
        }
        return new MqttMessageIdVariableHeader(i);
    }

    private MqttMessageIdVariableHeader(int i) {
        this.messageId = i;
    }

    public int messageId() {
        return this.messageId;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[messageId=" + this.messageId + ']';
    }
}
