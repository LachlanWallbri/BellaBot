package io.netty.handler.codec.mqtt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttConnectMessage extends MqttMessage {
    public MqttConnectMessage(MqttFixedHeader mqttFixedHeader, MqttConnectVariableHeader mqttConnectVariableHeader, MqttConnectPayload mqttConnectPayload) {
        super(mqttFixedHeader, mqttConnectVariableHeader, mqttConnectPayload);
    }

    @Override // io.netty.handler.codec.mqtt.MqttMessage
    public MqttConnectVariableHeader variableHeader() {
        return (MqttConnectVariableHeader) super.variableHeader();
    }

    @Override // io.netty.handler.codec.mqtt.MqttMessage
    public MqttConnectPayload payload() {
        return (MqttConnectPayload) super.payload();
    }
}
