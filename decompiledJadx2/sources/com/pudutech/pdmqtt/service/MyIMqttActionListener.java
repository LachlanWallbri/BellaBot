package com.pudutech.pdmqtt.service;

import android.util.Log;
import com.pudutech.pdmqtt.OnPublishCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/* compiled from: MyIMqttActionListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0010H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/MyIMqttActionListener;", "Lorg/eclipse/paho/client/mqttv3/IMqttActionListener;", "topic", "", MqttServiceConstants.PAYLOAD, "callback", "Lcom/pudutech/pdmqtt/OnPublishCallback;", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/pdmqtt/OnPublishCallback;)V", "getCallback", "()Lcom/pudutech/pdmqtt/OnPublishCallback;", "getPayload", "()Ljava/lang/String;", "getTopic", "onFailure", "", "asyncActionToken", "Lorg/eclipse/paho/client/mqttv3/IMqttToken;", MqttServiceConstants.TRACE_EXCEPTION, "", "onSuccess", "token", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MyIMqttActionListener implements IMqttActionListener {
    private final OnPublishCallback callback;
    private final String payload;
    private final String topic;

    public MyIMqttActionListener(String topic, String payload, OnPublishCallback onPublishCallback) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.topic = topic;
        this.payload = payload;
        this.callback = onPublishCallback;
    }

    public final OnPublishCallback getCallback() {
        return this.callback;
    }

    public final String getPayload() {
        return this.payload;
    }

    public final String getTopic() {
        return this.topic;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onSuccess(IMqttToken token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Log.i("MyIMqttActionListener", "onSuccess " + this.topic + ' ' + this.payload);
        OnPublishCallback onPublishCallback = this.callback;
        if (onPublishCallback != null) {
            onPublishCallback.onSuccess(this.topic, this.payload);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Intrinsics.checkParameterIsNotNull(asyncActionToken, "asyncActionToken");
        Log.i("MyIMqttActionListener", "onFailure " + this.topic + ' ' + this.payload);
        OnPublishCallback onPublishCallback = this.callback;
        if (onPublishCallback != null) {
            onPublishCallback.onFailue(this.topic, this.payload, -1, exception != null ? exception.getMessage() : null);
        }
    }
}
