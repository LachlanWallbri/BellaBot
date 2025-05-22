package io.netty.handler.codec.mqtt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttSubscribeMessage extends MqttMessage {
    public MqttSubscribeMessage(MqttFixedHeader mqttFixedHeader, MqttMessageIdVariableHeader mqttMessageIdVariableHeader, MqttSubscribePayload mqttSubscribePayload) {
        super(mqttFixedHeader, mqttMessageIdVariableHeader, mqttSubscribePayload);
    }

    @Override // io.netty.handler.codec.mqtt.MqttMessage
    public MqttMessageIdVariableHeader variableHeader() {
        return (MqttMessageIdVariableHeader) super.variableHeader();
    }

    @Override // io.netty.handler.codec.mqtt.MqttMessage
    public MqttSubscribePayload payload() {
        return (MqttSubscribePayload) super.payload();
    }
}
