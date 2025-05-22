package org.eclipse.paho.client.mqttv3;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public interface MqttCallback {
    void connectionLost(Throwable th);

    void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken);

    void messageArrived(String str, MqttMessage mqttMessage) throws Exception;
}
