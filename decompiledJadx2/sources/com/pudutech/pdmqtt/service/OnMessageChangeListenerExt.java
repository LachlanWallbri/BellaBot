package com.pudutech.pdmqtt.service;

import kotlin.Metadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: MessageChangeListenerExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/OnMessageChangeListenerExt;", "", "onReceive", "", "clientKey", "", "topic", MqttServiceConstants.PAYLOAD, "onSend", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface OnMessageChangeListenerExt {
    void onReceive(String clientKey, String topic, String payload);

    void onSend(String clientKey, String topic, String payload);
}
