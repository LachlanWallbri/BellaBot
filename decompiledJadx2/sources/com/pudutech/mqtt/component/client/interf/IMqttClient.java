package com.pudutech.mqtt.component.client.interf;

import android.content.Context;
import com.pudutech.mqtt.component.client.bean.SubscribeMessage;
import com.pudutech.mqtt.component.client.callback.SubscribeStateCallback;
import com.pudutech.mqtt.component.client.callback.UnsubscribeStateCallback;
import com.pudutech.mqtt.component.client.config.MqttParamOptions;
import com.pudutech.mqtt.component.client.config.Qos;
import java.util.ArrayList;
import kotlin.Metadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: IMqttClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J$\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH&J\u001c\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0003H&J/\u0010\u0014\u001a\u00020\u00032\u0016\u0010\u0015\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00170\u0016\"\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H&¢\u0006\u0002\u0010\u001aJ.\u0010\u0014\u001a\u00020\u00032\u001a\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001bj\n\u0012\u0004\u0012\u00020\u0017\u0018\u0001`\u001c2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H&J/\u0010\u001d\u001a\u00020\u00032\u0016\u0010\u001e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0016\"\u0004\u0018\u00010\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H&¢\u0006\u0002\u0010!¨\u0006\""}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/interf/IMqttClient;", "", "close", "", MqttServiceConstants.CONNECT_ACTION, "init", "context", "Landroid/content/Context;", "paramsOptions", "Lcom/pudutech/mqtt/component/client/config/MqttParamOptions;", "publishMsg", "topic", "", MqttServiceConstants.QOS, "Lcom/pudutech/mqtt/component/client/config/Qos;", MqttServiceConstants.PAYLOAD, "reconnect", "isFirstConnect", "", "release", MqttServiceConstants.SUBSCRIBE_ACTION, "subscribeMessages", "", "Lcom/pudutech/mqtt/component/client/bean/SubscribeMessage;", "subscribeStateCallback", "Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;", "([Lcom/pudutech/mqtt/component/client/bean/SubscribeMessage;Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", MqttServiceConstants.UNSUBSCRIBE_ACTION, "topics", "unsubscribeStateCallback", "Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;", "([Ljava/lang/String;Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;)V", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IMqttClient {
    void close();

    void connect();

    IMqttClient init(Context context, MqttParamOptions paramsOptions);

    void publishMsg(String topic, Qos qos, String payload);

    void publishMsg(String topic, String payload);

    void reconnect(boolean isFirstConnect);

    void release();

    void subscribe(ArrayList<SubscribeMessage> subscribeMessages, SubscribeStateCallback subscribeStateCallback);

    void subscribe(SubscribeMessage[] subscribeMessages, SubscribeStateCallback subscribeStateCallback);

    void unsubscribe(String[] topics, UnsubscribeStateCallback unsubscribeStateCallback);
}
