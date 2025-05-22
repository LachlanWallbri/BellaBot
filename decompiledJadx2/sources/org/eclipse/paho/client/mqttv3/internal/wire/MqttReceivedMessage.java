package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class MqttReceivedMessage extends MqttMessage {
    public void setMessageId(int i) {
        super.setId(i);
    }

    public int getMessageId() {
        return super.getId();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttMessage
    public void setDuplicate(boolean z) {
        super.setDuplicate(z);
    }
}
