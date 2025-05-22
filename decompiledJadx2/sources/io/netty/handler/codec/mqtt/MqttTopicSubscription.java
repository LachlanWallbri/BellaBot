package io.netty.handler.codec.mqtt;

import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttTopicSubscription {
    private final MqttQoS qualityOfService;
    private final String topicFilter;

    public MqttTopicSubscription(String str, MqttQoS mqttQoS) {
        this.topicFilter = str;
        this.qualityOfService = mqttQoS;
    }

    public String topicName() {
        return this.topicFilter;
    }

    public MqttQoS qualityOfService() {
        return this.qualityOfService;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[topicFilter=" + this.topicFilter + ", qualityOfService=" + this.qualityOfService + ']';
    }
}
