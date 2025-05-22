package com.aliyun.alink.linksdk.channel.core.persistent.mqtt;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.core.base.AError;
import com.aliyun.alink.linksdk.channel.core.base.ARequest;
import com.aliyun.alink.linksdk.channel.core.base.AResponse;
import com.aliyun.alink.linksdk.channel.core.base.IOnCallListener;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnRrpcResponseHandle;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentConnectState;
import com.aliyun.alink.linksdk.channel.core.persistent.event.PersistentEventDispatcher;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttPublishRequest;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttRrpcRequest;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ThreadTools;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttDefaulCallback.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a */
/* loaded from: classes.dex */
public class C0973a implements MqttCallbackExtended {

    /* renamed from: a */
    private Map<String, IOnSubscribeRrpcListener> f901a;

    /* renamed from: b */
    private Map<String, IOnSubscribeRrpcListener> f902b;

    /* renamed from: a */
    public void m407a(String str, IOnSubscribeRrpcListener iOnSubscribeRrpcListener) {
        C0969a.m393a("MqttDefaulCallback", "registerRrpcListener(), topic = " + str);
        if (TextUtils.isEmpty(str) || iOnSubscribeRrpcListener == null) {
            C0969a.m393a("MqttDefaulCallback", "registerRrpcListener(), params error ");
            return;
        }
        if (this.f901a == null) {
            this.f901a = new HashMap();
        }
        if (this.f902b == null) {
            this.f902b = new HashMap();
        }
        if (str.contains(MqttTopic.MULTI_LEVEL_WILDCARD) || str.contains("+")) {
            C0969a.m393a("MqttDefaulCallback", "registerRrpcListener(), pattern topic ");
            this.f902b.put(str, iOnSubscribeRrpcListener);
        } else {
            this.f901a.put(str, iOnSubscribeRrpcListener);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
    public void connectComplete(final boolean z, String str) {
        C0969a.m394b("MqttDefaulCallback", "mqtt connectComplete,reconnect = " + z + " ," + str);
        if (!z) {
            C0978b.m421a().m431a(PersistentConnectState.CONNECTED);
            PersistentEventDispatcher.getInstance().broadcastMessage(1, null, null, 0, "connect success");
        } else {
            ThreadTools.submitTask(new Runnable() { // from class: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a.1
                @Override // java.lang.Runnable
                public void run() {
                    IMqttAsyncClient m433c = C0978b.m421a().m433c();
                    C0969a.m393a("MqttDefaulCallback", "connectComplete, reconnect=" + z + ", client=" + m433c + ",threadId=" + Thread.currentThread());
                    if (m433c != null && C0978b.m421a().m434d()) {
                        C0978b.m421a().m431a(PersistentConnectState.CONNECTED);
                        PersistentEventDispatcher.getInstance().broadcastMessage(1, null, null, 0, "reconnect  success");
                    } else {
                        C0969a.m393a("MqttDefaulCallback", "connectComplete, try reconnect");
                    }
                }
            }, true, 1000);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void connectionLost(Throwable th) {
        C0969a.m395c("MqttDefaulCallback", "mqtt connectionLost,cause:" + th);
        if (th != null) {
            th.printStackTrace();
        }
        C0978b.m421a().m431a(PersistentConnectState.DISCONNECTED);
        if (th instanceof MqttException) {
            MqttException mqttException = (MqttException) th;
            PersistentEventDispatcher.getInstance().broadcastMessage(2, null, null, mqttException.getReasonCode(), mqttException.getMessage() + "，" + mqttException);
            return;
        }
        PersistentEventDispatcher.getInstance().broadcastMessage(2, null, null, 4201, "connection lost " + th);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void messageArrived(String str, MqttMessage mqttMessage) {
        try {
            C0969a.m393a("MqttDefaulCallback", "messageArrived,topic = [" + str + "] , msg = [" + new String(mqttMessage.getPayload(), "UTF-8") + "],  ");
            try {
                PersistentEventDispatcher.getInstance().broadcastMessage(3, str, mqttMessage.getPayload(), 0, null);
            } catch (Exception unused) {
                C0969a.m393a("MqttDefaulCallback", "messageArrived(), send broadcastMsg error");
            }
            if (this.f901a != null && this.f901a.containsKey(str)) {
                MqttRrpcRequest mqttRrpcRequest = new MqttRrpcRequest();
                mqttRrpcRequest.setTopic(str);
                mqttRrpcRequest.payloadObj = mqttMessage.getPayload();
                m405a(mqttRrpcRequest, this.f901a.get(str));
                return;
            }
            if (this.f902b == null || this.f902b.size() <= 0) {
                return;
            }
            for (String str2 : this.f902b.keySet()) {
                if (m406a(str2, str)) {
                    C0969a.m393a("MqttDefaulCallback", "messageArrived(), match pattern");
                    MqttRrpcRequest mqttRrpcRequest2 = new MqttRrpcRequest();
                    mqttRrpcRequest2.setTopic(str);
                    mqttRrpcRequest2.payloadObj = mqttMessage.getPayload();
                    m405a(mqttRrpcRequest2, this.f902b.get(str2));
                    return;
                }
            }
        } catch (Throwable th) {
            C0969a.m396d("MqttDefaulCallback", "messageArrived() handle error:" + th.toString());
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        StringBuilder sb = new StringBuilder();
        sb.append("deliveryComplete! ");
        sb.append((iMqttDeliveryToken == null || iMqttDeliveryToken.getResponse() == null) ? "null" : iMqttDeliveryToken.getResponse().getKey());
        C0969a.m393a("MqttDefaulCallback", sb.toString());
    }

    /* renamed from: a */
    private void m405a(final MqttRrpcRequest mqttRrpcRequest, final IOnSubscribeRrpcListener iOnSubscribeRrpcListener) {
        C0969a.m393a("MqttDefaulCallback", "handleRrpcRequest()");
        if (iOnSubscribeRrpcListener == null || mqttRrpcRequest == null) {
            return;
        }
        if (iOnSubscribeRrpcListener.needUISafety()) {
            ThreadTools.runOnUiThread(new Runnable() { // from class: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a.2
                @Override // java.lang.Runnable
                public void run() {
                    iOnSubscribeRrpcListener.onReceived(mqttRrpcRequest.topic, mqttRrpcRequest, new a(mqttRrpcRequest.topic, iOnSubscribeRrpcListener));
                }
            });
        } else {
            iOnSubscribeRrpcListener.onReceived(mqttRrpcRequest.topic, mqttRrpcRequest, new a(mqttRrpcRequest.topic, iOnSubscribeRrpcListener));
        }
    }

    /* renamed from: a */
    private boolean m406a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                if (str.contains(MqttTopic.MULTI_LEVEL_WILDCARD) && str2.startsWith(str.split(MqttTopic.MULTI_LEVEL_WILDCARD)[0])) {
                    return true;
                }
                if (str.contains("+")) {
                    String str3 = str.split("\\+")[0];
                    String str4 = str.split("\\+", 2)[1];
                    if (str2.startsWith(str3)) {
                        if (str2.endsWith(str4)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                C0969a.m393a("MqttDefaulCallback", "isTopicMatchForPattern(),e = " + e.toString());
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: MqttDefaulCallback.java */
    /* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a$a */
    /* loaded from: classes.dex */
    public class a implements IOnRrpcResponseHandle {

        /* renamed from: b */
        private String f909b;

        /* renamed from: c */
        private IOnSubscribeRrpcListener f910c;

        public a(String str, IOnSubscribeRrpcListener iOnSubscribeRrpcListener) {
            this.f909b = str;
            this.f910c = iOnSubscribeRrpcListener;
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnRrpcResponseHandle
        public void onRrpcResponse(String str, AResponse aResponse) {
            C0969a.m393a("MqttDefaulCallback", "onRrpcResponse(), reply topic = " + str);
            MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
            mqttPublishRequest.isRPC = false;
            if (TextUtils.isEmpty(str)) {
                mqttPublishRequest.topic = this.f909b + TmpConstant.URI_TOPIC_REPLY_POST;
            } else {
                mqttPublishRequest.topic = str;
            }
            if (aResponse != null && aResponse.data != null) {
                mqttPublishRequest.payloadObj = aResponse.data;
            }
            C0978b.m421a().asyncSend(mqttPublishRequest, new IOnCallListener() { // from class: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a.a.1
                @Override // com.aliyun.alink.linksdk.channel.core.base.IOnCallListener
                public void onSuccess(ARequest aRequest, AResponse aResponse2) {
                    C0969a.m393a("MqttDefaulCallback", "onRrpcResponse(), publish succ");
                    a.this.f910c.onResponseSuccess(a.this.f909b);
                }

                @Override // com.aliyun.alink.linksdk.channel.core.base.IOnCallListener
                public void onFailed(ARequest aRequest, AError aError) {
                    C0969a.m393a("MqttDefaulCallback", "onRrpcResponse(), publish fail");
                    a.this.f910c.onResponseFailed(a.this.f909b, aError);
                }

                @Override // com.aliyun.alink.linksdk.channel.core.base.IOnCallListener
                public boolean needUISafety() {
                    return a.this.f910c.needUISafety();
                }
            });
        }
    }
}
