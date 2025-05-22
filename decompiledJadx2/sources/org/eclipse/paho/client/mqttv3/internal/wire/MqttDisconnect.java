package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class MqttDisconnect extends MqttWireMessage {
    public static final String KEY = "Disc";

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String getKey() {
        return KEY;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte getMessageInfo() {
        return (byte) 0;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return false;
    }

    public MqttDisconnect() {
        super((byte) 14);
    }

    public MqttDisconnect(byte b, byte[] bArr) throws IOException {
        super((byte) 14);
    }
}
