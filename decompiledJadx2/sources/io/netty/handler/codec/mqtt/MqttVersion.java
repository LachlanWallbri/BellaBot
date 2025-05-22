package io.netty.handler.codec.mqtt;

import io.netty.util.CharsetUtil;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum MqttVersion {
    MQTT_3_1("MQIsdp", (byte) 3),
    MQTT_3_1_1("MQTT", (byte) 4);

    private final byte level;
    private final String name;

    MqttVersion(String str, byte b) {
        this.name = (String) ObjectUtil.checkNotNull(str, "protocolName");
        this.level = b;
    }

    public String protocolName() {
        return this.name;
    }

    public byte[] protocolNameBytes() {
        return this.name.getBytes(CharsetUtil.UTF_8);
    }

    public byte protocolLevel() {
        return this.level;
    }

    public static MqttVersion fromProtocolNameAndLevel(String str, byte b) {
        for (MqttVersion mqttVersion : values()) {
            if (mqttVersion.name.equals(str)) {
                if (mqttVersion.level == b) {
                    return mqttVersion;
                }
                throw new MqttUnacceptableProtocolVersionException(str + " and " + ((int) b) + " are not match");
            }
        }
        throw new MqttUnacceptableProtocolVersionException(str + "is unknown protocol name");
    }
}
