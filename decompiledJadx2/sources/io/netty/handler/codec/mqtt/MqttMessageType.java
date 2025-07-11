package io.netty.handler.codec.mqtt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum MqttMessageType {
    CONNECT(1),
    CONNACK(2),
    PUBLISH(3),
    PUBACK(4),
    PUBREC(5),
    PUBREL(6),
    PUBCOMP(7),
    SUBSCRIBE(8),
    SUBACK(9),
    UNSUBSCRIBE(10),
    UNSUBACK(11),
    PINGREQ(12),
    PINGRESP(13),
    DISCONNECT(14);

    private final int value;

    MqttMessageType(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static MqttMessageType valueOf(int i) {
        for (MqttMessageType mqttMessageType : values()) {
            if (mqttMessageType.value == i) {
                return mqttMessageType;
            }
        }
        throw new IllegalArgumentException("unknown message type: " + i);
    }
}
