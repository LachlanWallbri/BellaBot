package io.netty.handler.codec.mqtt;

import io.netty.util.internal.StringUtil;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttSubscribePayload {
    private final List<MqttTopicSubscription> topicSubscriptions;

    public MqttSubscribePayload(List<MqttTopicSubscription> list) {
        this.topicSubscriptions = Collections.unmodifiableList(list);
    }

    public List<MqttTopicSubscription> topicSubscriptions() {
        return this.topicSubscriptions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(StringUtil.simpleClassName(this));
        sb.append('[');
        for (int i = 0; i < this.topicSubscriptions.size() - 1; i++) {
            sb.append(this.topicSubscriptions.get(i));
            sb.append(", ");
        }
        sb.append(this.topicSubscriptions.get(r1.size() - 1));
        sb.append(']');
        return sb.toString();
    }
}
