package io.netty.handler.codec.mqtt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttConnAckMessage extends MqttMessage {
    public MqttConnAckMessage(MqttFixedHeader mqttFixedHeader, MqttConnAckVariableHeader mqttConnAckVariableHeader) {
        super(mqttFixedHeader, mqttConnAckVariableHeader);
    }

    @Override // io.netty.handler.codec.mqtt.MqttMessage
    public MqttConnAckVariableHeader variableHeader() {
        return (MqttConnAckVariableHeader) super.variableHeader();
    }
}
