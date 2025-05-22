package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a.C0974a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttPublishRequest;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttRpcMessageCallback.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send.a */
/* loaded from: classes.dex */
public class C0979a implements IMqttMessageListener {

    /* renamed from: a */
    private static Map<String, C0980b> f937a;

    public C0979a(String str, C0980b c0980b) {
        if (f937a == null) {
            f937a = new HashMap();
        }
        if (c0980b == null || TextUtils.isEmpty(str) || c0980b.getRequest() == null || !(c0980b.getRequest() instanceof MqttPublishRequest) || TextUtils.isEmpty(((MqttPublishRequest) c0980b.getRequest()).msgId)) {
            return;
        }
        f937a.put(str + ",id=" + ((MqttPublishRequest) c0980b.getRequest()).msgId, c0980b);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttMessageListener
    public void messageArrived(String str, MqttMessage mqttMessage) {
        C0969a.m393a("MqttRpcMessageCallback", "messageArrived()");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String m410a = C0974a.m410a(mqttMessage.toString());
        if (TextUtils.isEmpty(m410a)) {
            return;
        }
        if (f937a.containsKey(str + ",id=" + m410a)) {
            C0969a.m393a("MqttRpcMessageCallback", "messageArrived(), match Id = <" + str + ",id=" + m410a + ">");
            Map<String, C0980b> map = f937a;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(",id=");
            sb.append(m410a);
            map.get(sb.toString()).m440a(str, mqttMessage);
            f937a.remove(str + ",id=" + m410a);
        }
    }
}
