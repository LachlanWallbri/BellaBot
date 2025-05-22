package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send;

import com.aliyun.alink.linksdk.channel.core.base.AError;
import com.aliyun.alink.linksdk.channel.core.base.ARequest;
import com.aliyun.alink.linksdk.channel.core.base.AResponse;
import com.aliyun.alink.linksdk.channel.core.base.ASend;
import com.aliyun.alink.linksdk.channel.core.base.IOnCallListener;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.BadNetworkException;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a.C0977d;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttPublishRequest;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.tools.ThreadTools;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttSend.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send.b */
/* loaded from: classes.dex */
public class C0980b extends ASend implements IMqttActionListener {

    /* renamed from: a */
    private IOnSubscribeListener f938a;

    /* renamed from: b */
    private HashMap<String, String> f939b;

    public C0980b(ARequest aRequest, IOnCallListener iOnCallListener) {
        super(aRequest, iOnCallListener);
        this.f938a = null;
        this.f939b = new HashMap<>();
        m438a(MqttSendStatus.waitingToSend);
    }

    public C0980b(ARequest aRequest, IOnSubscribeListener iOnSubscribeListener) {
        super(aRequest, null);
        this.f938a = null;
        this.f939b = new HashMap<>();
        this.f938a = iOnSubscribeListener;
        m438a(MqttSendStatus.waitingToSend);
    }

    /* renamed from: a */
    public void m438a(MqttSendStatus mqttSendStatus) {
        this.status = mqttSendStatus;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.base.ASend
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MqttSendStatus getStatus() {
        return (MqttSendStatus) this.status;
    }

    /* renamed from: b */
    public IOnSubscribeListener m441b() {
        return this.f938a;
    }

    /* renamed from: a */
    public void m439a(String str, String str2) {
        C0969a.m393a("MqttSend", "addTrackData() called with: key = [" + str + "], value = [" + str2 + "]");
        HashMap<String, String> hashMap = this.f939b;
        if (hashMap != null) {
            hashMap.put(str, str2);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onSuccess(IMqttToken iMqttToken) {
        boolean z = true;
        if (this.request instanceof MqttSubscribeRequest) {
            m438a(MqttSendStatus.completed);
            try {
                if (iMqttToken.getGrantedQos()[0] == 128) {
                    z = false;
                }
            } catch (Exception unused) {
                C0969a.m393a("MqttSend", "onSuccess(),getGrantedQos");
            }
            IOnSubscribeListener iOnSubscribeListener = this.f938a;
            if (iOnSubscribeListener != null) {
                if (iOnSubscribeListener.needUISafety()) {
                    ThreadTools.runOnUiThread(new RunnableC0982d(this, z ? (byte) 4 : (byte) 5, null));
                    m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
                    if (!z) {
                        m439a(AUserTrack.UTKEY_ERROR_CODE, String.valueOf(4201));
                    }
                    C0977d.m419a("mqtt-send", this.f939b);
                    return;
                }
                if (z) {
                    this.f938a.onSuccess(((MqttSubscribeRequest) this.request).topic);
                    m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
                    C0977d.m419a("mqtt-send", this.f939b);
                    return;
                }
                AError aError = new AError();
                aError.setCode(4103);
                aError.setMsg("subACK Failure");
                this.f938a.onFailed(((MqttSubscribeRequest) this.request).topic, aError);
                m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
                m439a(AUserTrack.UTKEY_ERROR_CODE, String.valueOf(4103));
                C0977d.m419a("mqtt-send", this.f939b);
                return;
            }
            m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
            C0977d.m419a("mqtt-send", this.f939b);
            return;
        }
        if (this.request instanceof MqttPublishRequest) {
            if (!((MqttPublishRequest) this.request).isRPC) {
                m438a(MqttSendStatus.completed);
                if (this.listener != null) {
                    if (this.listener.needUISafety()) {
                        ThreadTools.runOnUiThread(new RunnableC0982d(this, (byte) 1, null));
                    } else {
                        this.listener.onSuccess(this.request, this.response);
                    }
                }
                m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
                C0977d.m419a("mqtt-send", this.f939b);
                return;
            }
            if (this.status == MqttSendStatus.waitingToSubReply) {
                m438a(MqttSendStatus.subReplyed);
                m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
                C0977d.m419a("mqtt-send", this.f939b);
                new C0981c().asyncSend(this);
                return;
            }
            if (this.status == MqttSendStatus.waitingToPublish) {
                m438a(MqttSendStatus.published);
                m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
                C0977d.m419a("mqtt-send", this.f939b);
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        int i;
        String message = th != null ? th.getMessage() : "MqttNet send failed: unknown error";
        m438a(MqttSendStatus.completed);
        int i2 = 0;
        if (this.request instanceof MqttSubscribeRequest) {
            byte b = 5;
            if (th instanceof BadNetworkException) {
                b = 6;
                i = 4101;
            } else {
                i = 4201;
            }
            IOnSubscribeListener iOnSubscribeListener = this.f938a;
            if (iOnSubscribeListener != null) {
                if (iOnSubscribeListener.needUISafety()) {
                    ThreadTools.runOnUiThread(new RunnableC0982d(this, b, message));
                } else if (b == 6) {
                    AError aError = new AError();
                    aError.setCode(4101);
                    this.f938a.onFailed(((MqttSubscribeRequest) this.request).topic, aError);
                } else {
                    AError aError2 = new AError();
                    aError2.setCode(4201);
                    aError2.setMsg(message);
                    this.f938a.onFailed(((MqttSubscribeRequest) this.request).topic, aError2);
                }
            }
        } else {
            if (this.request instanceof MqttPublishRequest) {
                byte b2 = 2;
                if (th instanceof BadNetworkException) {
                    b2 = 3;
                    i = 4101;
                } else {
                    i = 4201;
                }
                if (this.listener != null) {
                    if (this.listener.needUISafety()) {
                        ThreadTools.runOnUiThread(new RunnableC0982d(this, b2, message));
                    } else if (b2 == 3) {
                        AError aError3 = new AError();
                        aError3.setCode(4101);
                        this.listener.onFailed(this.request, aError3);
                    } else {
                        AError aError4 = new AError();
                        aError4.setCode(4201);
                        aError4.setMsg(message);
                        this.listener.onFailed(this.request, aError4);
                    }
                }
            }
            m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
            m439a(AUserTrack.UTKEY_ERROR_CODE, String.valueOf(i2));
            C0977d.m419a("mqtt-send", this.f939b);
        }
        i2 = i;
        m439a("endTime-send", String.valueOf(System.currentTimeMillis()));
        m439a(AUserTrack.UTKEY_ERROR_CODE, String.valueOf(i2));
        C0977d.m419a("mqtt-send", this.f939b);
    }

    /* renamed from: a */
    public void m440a(String str, MqttMessage mqttMessage) {
        C0969a.m393a("MqttSend", "rpcMessageArrived(), topic =" + str + " msg =" + mqttMessage.toString());
        if (this.request instanceof MqttPublishRequest) {
            MqttPublishRequest mqttPublishRequest = (MqttPublishRequest) this.request;
            if (mqttPublishRequest.isRPC) {
                if ((this.status == MqttSendStatus.published || this.status == MqttSendStatus.waitingToPublish) && str.equals(mqttPublishRequest.replyTopic)) {
                    C0969a.m393a("MqttSend", "messageArrived(), match!");
                    m438a(MqttSendStatus.completed);
                    if (this.response == null) {
                        this.response = new AResponse();
                    }
                    this.response.data = mqttMessage.toString();
                    if (this.listener != null) {
                        if (this.listener.needUISafety()) {
                            ThreadTools.runOnUiThread(new RunnableC0982d(this, (byte) 1, null));
                        } else {
                            this.listener.onSuccess(this.request, this.response);
                        }
                    }
                }
            }
        }
    }
}
