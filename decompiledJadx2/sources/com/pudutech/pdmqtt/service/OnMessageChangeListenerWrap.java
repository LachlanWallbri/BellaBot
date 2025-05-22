package com.pudutech.pdmqtt.service;

import com.pudutech.pdmqtt.OnMessageChangeListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: MessageChangeListenerExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/OnMessageChangeListenerWrap;", "Lcom/pudutech/pdmqtt/OnMessageChangeListener$Stub;", "clientKey", "", "callback", "Lcom/pudutech/pdmqtt/service/OnMessageChangeListenerExt;", "(Ljava/lang/String;Lcom/pudutech/pdmqtt/service/OnMessageChangeListenerExt;)V", "getCallback", "()Lcom/pudutech/pdmqtt/service/OnMessageChangeListenerExt;", "onReceive", "", "topic", MqttServiceConstants.PAYLOAD, "onSend", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class OnMessageChangeListenerWrap extends OnMessageChangeListener.Stub {
    private final OnMessageChangeListenerExt callback;
    private final String clientKey;

    public final OnMessageChangeListenerExt getCallback() {
        return this.callback;
    }

    public OnMessageChangeListenerWrap(String clientKey, OnMessageChangeListenerExt callback) {
        Intrinsics.checkParameterIsNotNull(clientKey, "clientKey");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.clientKey = clientKey;
        this.callback = callback;
    }

    @Override // com.pudutech.pdmqtt.OnMessageChangeListener
    public void onReceive(String topic, String payload) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.callback.onReceive(this.clientKey, topic, payload);
    }

    @Override // com.pudutech.pdmqtt.OnMessageChangeListener
    public void onSend(String topic, String payload) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.callback.onSend(this.clientKey, topic, payload);
    }
}
