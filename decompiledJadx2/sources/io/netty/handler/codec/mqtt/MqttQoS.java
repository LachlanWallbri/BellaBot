package io.netty.handler.codec.mqtt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum MqttQoS {
    AT_MOST_ONCE(0),
    AT_LEAST_ONCE(1),
    EXACTLY_ONCE(2),
    FAILURE(128);

    private final int value;

    MqttQoS(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static MqttQoS valueOf(int i) {
        for (MqttQoS mqttQoS : values()) {
            if (mqttQoS.value == i) {
                return mqttQoS;
            }
        }
        throw new IllegalArgumentException("invalid QoS: " + i);
    }
}
