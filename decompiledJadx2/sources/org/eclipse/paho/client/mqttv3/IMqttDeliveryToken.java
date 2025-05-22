package org.eclipse.paho.client.mqttv3;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public interface IMqttDeliveryToken extends IMqttToken {
    MqttMessage getMessage() throws MqttException;
}
