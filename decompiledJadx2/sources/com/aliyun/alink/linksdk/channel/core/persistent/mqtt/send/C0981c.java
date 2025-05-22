package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.core.base.ASend;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.BadNetworkException;
import com.aliyun.alink.linksdk.channel.core.persistent.ISendExecutor;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentConnectState;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.C0978b;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a.C0974a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttPublishRequest;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.NetTools;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttSendExecutor.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send.c */
/* loaded from: classes.dex */
public class C0981c implements ISendExecutor {
    @Override // com.aliyun.alink.linksdk.channel.core.persistent.ISendExecutor
    public void asyncSend(ASend aSend) {
        byte[] bArr;
        String obj;
        if (aSend == null || aSend.getRequest() == null) {
            C0969a.m396d("MqttSendExecutor", "asyncSend(): bad parameters: NULL");
            return;
        }
        if (!(aSend instanceof C0980b)) {
            C0969a.m393a("MqttSendExecutor", "asyncSend(): bad parameter: need MqttSend");
            return;
        }
        C0980b c0980b = (C0980b) aSend;
        IMqttAsyncClient m433c = C0978b.m421a().m433c();
        if (m433c == null) {
            C0969a.m396d("MqttSendExecutor", "asyncSend(): MqttNet::getClient() return null");
            c0980b.m438a(MqttSendStatus.completed);
            c0980b.onFailure(null, new IllegalStateException("init mqtt first"));
            return;
        }
        Context m435e = C0978b.m421a().m435e();
        if (!MqttConfigure.disableNetworkCheckBeforeSend && m435e != null && !NetTools.isAvailable(m435e)) {
            C0969a.m393a("MqttSendExecutor", "asyncSend(): bad Network");
            c0980b.m438a(MqttSendStatus.completed);
            c0980b.onFailure(null, new BadNetworkException());
            return;
        }
        if (C0978b.m421a().getConnectState() != PersistentConnectState.CONNECTED) {
            C0969a.m393a("MqttSendExecutor", "asyncSend(): gateway disconnect");
            c0980b.m438a(MqttSendStatus.completed);
            c0980b.onFailure(null, new BadNetworkException());
            return;
        }
        c0980b.m439a("startTime-send", String.valueOf(System.currentTimeMillis()));
        if (aSend.getRequest() instanceof MqttPublishRequest) {
            MqttPublishRequest mqttPublishRequest = (MqttPublishRequest) aSend.getRequest();
            if (TextUtils.isEmpty(mqttPublishRequest.topic) || mqttPublishRequest.payloadObj == null) {
                C0969a.m396d("MqttSendExecutor", "asyncSend(): bad parameters: topic or payload empty");
                c0980b.onFailure(null, new NullPointerException("topic or payload empty"));
                return;
            }
            if (mqttPublishRequest.isRPC && (c0980b.getStatus() == MqttSendStatus.waitingToSend || c0980b.getStatus() == MqttSendStatus.completed)) {
                try {
                    if (mqttPublishRequest.payloadObj instanceof String) {
                        obj = mqttPublishRequest.payloadObj.toString();
                    } else if (mqttPublishRequest.payloadObj instanceof byte[]) {
                        obj = new String((byte[]) mqttPublishRequest.payloadObj, "UTF-8");
                    } else {
                        try {
                            obj = mqttPublishRequest.payloadObj.toString();
                        } catch (Exception e) {
                            C0969a.m393a("MqttSendExecutor", "asyncSend(), publish , toString error," + e.toString());
                            c0980b.m438a(MqttSendStatus.completed);
                            c0980b.onFailure(null, new MqttThrowable("RPC request ,payload should be String or byte[]"));
                            return;
                        }
                    }
                    mqttPublishRequest.msgId = C0974a.m410a(obj);
                    if (TextUtils.isEmpty(mqttPublishRequest.replyTopic)) {
                        mqttPublishRequest.replyTopic = mqttPublishRequest.topic + TmpConstant.URI_TOPIC_REPLY_POST;
                    }
                    C0969a.m393a("MqttSendExecutor", "publish: RPC sub reply topic: [ " + mqttPublishRequest.replyTopic + " ]");
                    c0980b.m438a(MqttSendStatus.waitingToSubReply);
                    m433c.subscribe(mqttPublishRequest.replyTopic, 0, (Object) null, c0980b, new C0979a(mqttPublishRequest.replyTopic, c0980b));
                    return;
                } catch (Exception e2) {
                    C0969a.m393a("MqttSendExecutor", "asyncSend(), publish , send subsribe reply error, e = " + e2.toString());
                    c0980b.m438a(MqttSendStatus.completed);
                    c0980b.onFailure(null, new MqttThrowable(e2.getMessage()));
                    return;
                }
            }
            try {
                if (mqttPublishRequest.payloadObj instanceof String) {
                    bArr = mqttPublishRequest.payloadObj.toString().getBytes("utf-8");
                } else if (mqttPublishRequest.payloadObj instanceof byte[]) {
                    bArr = (byte[]) mqttPublishRequest.payloadObj;
                } else {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(mqttPublishRequest.payloadObj);
                        objectOutputStream.flush();
                        bArr = byteArrayOutputStream.toByteArray();
                    } catch (Exception e3) {
                        C0969a.m396d("MqttSendExecutor", "asyncSend(): convert payload Obj to byte array error");
                        e3.printStackTrace();
                        bArr = null;
                    }
                }
                if (mqttPublishRequest.payloadObj == null) {
                    C0969a.m393a("MqttSendExecutor", "asyncSend(): payload is empty");
                    c0980b.onFailure(null, new NullPointerException("payload empty"));
                    return;
                }
                C0969a.m393a("MqttSendExecutor", "publish: topic: [ " + mqttPublishRequest.topic + " ]");
                C0969a.m393a("MqttSendExecutor", "publish: payload: [ " + mqttPublishRequest.payloadObj.toString() + " ]");
                MqttMessage mqttMessage = new MqttMessage(bArr);
                mqttMessage.setQos(mqttPublishRequest.qos);
                if (mqttPublishRequest.isRPC) {
                    c0980b.m438a(MqttSendStatus.waitingToPublish);
                } else {
                    c0980b.m438a(MqttSendStatus.waitingToComplete);
                }
                m433c.publish(mqttPublishRequest.topic, mqttMessage, (Object) null, c0980b);
                return;
            } catch (Exception e4) {
                C0969a.m393a("MqttSendExecutor", "asyncSend(), send publish error, e = " + e4.toString());
                c0980b.m438a(MqttSendStatus.completed);
                c0980b.onFailure(null, new MqttThrowable(e4.getMessage()));
                return;
            }
        }
        if (aSend.getRequest() instanceof MqttSubscribeRequest) {
            MqttSubscribeRequest mqttSubscribeRequest = (MqttSubscribeRequest) aSend.getRequest();
            if (TextUtils.isEmpty(mqttSubscribeRequest.topic)) {
                C0969a.m396d("MqttSendExecutor", "asyncSend(): bad parameters: subsribe req , topic empty");
                c0980b.onFailure(null, new NullPointerException("subsribe req , topic empty"));
                return;
            }
            try {
                c0980b.m438a(MqttSendStatus.waitingToComplete);
                if (mqttSubscribeRequest.isSubscribe) {
                    C0969a.m393a("MqttSendExecutor", "subscribe: topic: [ " + mqttSubscribeRequest.topic + " ]");
                    m433c.subscribe(mqttSubscribeRequest.topic, mqttSubscribeRequest.subscribeRequestParams == null ? 0 : mqttSubscribeRequest.subscribeRequestParams.qos, (Object) null, c0980b);
                    return;
                }
                C0969a.m393a("MqttSendExecutor", "unsubscribe: topic: [ " + mqttSubscribeRequest.topic + " ]");
                m433c.unsubscribe(mqttSubscribeRequest.topic, (Object) null, c0980b);
            } catch (Exception e5) {
                C0969a.m393a("MqttSendExecutor", "asyncSend(), send subsribe error, e = " + e5.toString());
                c0980b.m438a(MqttSendStatus.completed);
                c0980b.onFailure(null, new MqttThrowable(e5.getMessage()));
            }
        }
    }
}
