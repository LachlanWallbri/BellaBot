package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class MqttDeliveryTokenAndroid extends MqttTokenAndroid implements IMqttDeliveryToken {
    private MqttMessage message;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MqttDeliveryTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener, MqttMessage mqttMessage) {
        super(mqttAndroidClient, obj, iMqttActionListener);
        this.message = mqttMessage;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
    public MqttMessage getMessage() throws MqttException {
        return this.message;
    }

    void setMessage(MqttMessage mqttMessage) {
        this.message = mqttMessage;
    }

    void notifyDelivery(MqttMessage mqttMessage) {
        this.message = mqttMessage;
        super.notifyComplete();
    }
}
