package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class BufferedMessage {
    private MqttWireMessage message;
    private MqttToken token;

    public BufferedMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) {
        this.message = mqttWireMessage;
        this.token = mqttToken;
    }

    public MqttWireMessage getMessage() {
        return this.message;
    }

    public MqttToken getToken() {
        return this.token;
    }
}
