package org.eclipse.paho.client.mqttv3.internal.wire;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public abstract class MqttAck extends MqttWireMessage {
    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte getMessageInfo() {
        return (byte) 0;
    }

    public MqttAck(byte b) {
        super(b);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        return super.toString() + " msgId " + this.msgId;
    }
}
