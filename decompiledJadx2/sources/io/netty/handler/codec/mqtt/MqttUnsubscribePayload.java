package io.netty.handler.codec.mqtt;

import io.netty.util.internal.StringUtil;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttUnsubscribePayload {
    private final List<String> topics;

    public MqttUnsubscribePayload(List<String> list) {
        this.topics = Collections.unmodifiableList(list);
    }

    public List<String> topics() {
        return this.topics;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(StringUtil.simpleClassName(this));
        sb.append('[');
        for (int i = 0; i < this.topics.size() - 1; i++) {
            sb.append("topicName = ");
            sb.append(this.topics.get(i));
            sb.append(", ");
        }
        sb.append("topicName = ");
        sb.append(this.topics.get(r1.size() - 1));
        sb.append(']');
        return sb.toString();
    }
}
