package com.pudutech.mqtt.component.client.callback;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: MessageReceiverListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/callback/MessageReceiverListener;", "", "onReceiverMessage", "", "topic", "", NotificationCompat.CATEGORY_MESSAGE, "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface MessageReceiverListener {
    void onReceiverMessage(String topic, String msg);
}
