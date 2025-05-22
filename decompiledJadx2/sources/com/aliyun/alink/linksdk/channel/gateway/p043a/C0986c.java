package com.aliyun.alink.linksdk.channel.gateway.p043a;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.SubDeviceInfo;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.SubDeviceLoginState;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRegisterRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectConfig;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: SubDeviceChannelImpl.java */
/* renamed from: com.aliyun.alink.linksdk.channel.gateway.a.c */
/* loaded from: classes.dex */
public class C0986c implements ISubDeviceChannel {

    /* renamed from: a */
    private PersistentConnectConfig f992a;

    /* renamed from: b */
    private SubDeviceInfo f993b;

    /* renamed from: c */
    private ISubDeviceConnectListener f994c;

    public C0986c(PersistentConnectConfig persistentConnectConfig, SubDeviceInfo subDeviceInfo, ISubDeviceConnectListener iSubDeviceConnectListener) {
        this.f992a = persistentConnectConfig;
        this.f993b = subDeviceInfo;
        this.f994c = iSubDeviceConnectListener;
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public void online(final ISubDeviceActionListener iSubDeviceActionListener) {
        SubDeviceInfo subDeviceInfo;
        String clientId;
        PersistentConnectConfig persistentConnectConfig = this.f992a;
        if (persistentConnectConfig == null || !m468a(persistentConnectConfig) || (subDeviceInfo = this.f993b) == null || !subDeviceInfo.checkValid()) {
            return;
        }
        if (m467a() != ConnectState.CONNECTED) {
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.isRPC = true;
        mqttPublishRequest.topic = "/ext/session/" + this.f992a.productKey + "/" + this.f992a.deviceName + "/combine/login";
        mqttPublishRequest.replyTopic = mqttPublishRequest.topic + TmpConstant.URI_TOPIC_REPLY_POST;
        String str = C0987d.f1009a.incrementAndGet() + "";
        mqttPublishRequest.msgId = str;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("productKey", (Object) this.f993b.productKey);
        jSONObject.put("deviceName", (Object) this.f993b.deviceName);
        jSONObject.put(TmpConstant.KEY_SIGN_METHOD, (Object) this.f994c.getSignMethod());
        if (TextUtils.isEmpty(this.f994c.getClientId())) {
            clientId = this.f993b.deviceName + "&" + this.f993b.productKey;
        } else {
            clientId = this.f994c.getClientId();
        }
        jSONObject.put(TmpConstant.KEY_CLIENT_ID, (Object) clientId);
        jSONObject.put(TmpConstant.KEY_SIGN_VALUE, (Object) this.f994c.getSignValue());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", (Object) str);
        jSONObject2.put("params", (Object) jSONObject);
        mqttPublishRequest.payloadObj = jSONObject2.toJSONString();
        ConnectSDK.getInstance().send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.c.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                StringBuilder sb = new StringBuilder();
                sb.append("online(), onSuceess, rsp = ");
                sb.append((aResponse == null || aResponse.data == null) ? "" : aResponse.data);
                ALog.m479d("SubDeviceChannelImpl", sb.toString());
                try {
                    int intValue = JSONObject.parseObject((String) aResponse.data).getIntValue("code");
                    if (200 == intValue) {
                        C0986c.this.f993b.loginState = SubDeviceLoginState.ONLINE;
                        if (iSubDeviceActionListener != null) {
                            iSubDeviceActionListener.onSuccess();
                            return;
                        }
                        return;
                    }
                    C0986c.this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                    AError aError2 = new AError();
                    aError2.setCode(intValue);
                    aError2.setMsg("code =" + intValue);
                    if (iSubDeviceActionListener != null) {
                        iSubDeviceActionListener.onFailed(aError2);
                    }
                } catch (Exception e) {
                    ALog.m480e("SubDeviceChannelImpl", "online(), onSuccess(), parse error, e" + e.toString());
                    e.printStackTrace();
                    C0986c.this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                    AError aError3 = new AError();
                    aError3.setMsg("reqSuccess, parse error, e" + e.toString());
                    ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                    if (iSubDeviceActionListener2 != null) {
                        iSubDeviceActionListener2.onFailed(aError3);
                    }
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError2) {
                String str2;
                StringBuilder sb = new StringBuilder();
                sb.append("online(), onFailure(), aError, e");
                if (aError2 == null) {
                    str2 = null;
                } else {
                    str2 = "[" + aError2.getCode() + ":" + aError2.getMsg() + "]";
                }
                sb.append(str2);
                ALog.m480e("SubDeviceChannelImpl", sb.toString());
                C0986c.this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onFailed(aError2);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public void offline(final ISubDeviceActionListener iSubDeviceActionListener) {
        SubDeviceInfo subDeviceInfo;
        PersistentConnectConfig persistentConnectConfig = this.f992a;
        if (persistentConnectConfig == null || !m468a(persistentConnectConfig) || (subDeviceInfo = this.f993b) == null || !subDeviceInfo.checkValid()) {
            return;
        }
        if (m467a() != ConnectState.CONNECTED) {
            if (iSubDeviceActionListener != null) {
                this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                AError aError = new AError();
                aError.setCode(4101);
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.isRPC = true;
        mqttPublishRequest.topic = "/ext/session/" + this.f992a.productKey + "/" + this.f992a.deviceName + "/combine/logout";
        mqttPublishRequest.replyTopic = mqttPublishRequest.topic + TmpConstant.URI_TOPIC_REPLY_POST;
        String str = C0987d.f1009a.incrementAndGet() + "";
        mqttPublishRequest.msgId = str;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("productKey", (Object) this.f993b.productKey);
        jSONObject.put("deviceName", (Object) this.f993b.deviceName);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", (Object) str);
        jSONObject2.put("params", (Object) jSONObject);
        mqttPublishRequest.payloadObj = jSONObject2.toString();
        ConnectSDK.getInstance().send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.c.2
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                StringBuilder sb = new StringBuilder();
                sb.append("offline(), onSuceess, rsp = ");
                sb.append((aResponse == null || aResponse.data == null) ? "" : aResponse.data);
                ALog.m479d("SubDeviceChannelImpl", sb.toString());
                try {
                    int intValue = JSONObject.parseObject((String) aResponse.data).getIntValue("code");
                    if (200 == intValue) {
                        C0986c.this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                        if (iSubDeviceActionListener != null) {
                            iSubDeviceActionListener.onSuccess();
                            return;
                        }
                        return;
                    }
                    AError aError2 = new AError();
                    aError2.setCode(intValue);
                    aError2.setMsg("code =" + intValue);
                    if (iSubDeviceActionListener != null) {
                        iSubDeviceActionListener.onFailed(aError2);
                    }
                } catch (Exception e) {
                    ALog.m480e("SubDeviceChannelImpl", "offline(), onSuccess(), parse error, e" + e.toString());
                    e.printStackTrace();
                    C0986c.this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                    AError aError3 = new AError();
                    aError3.setMsg("reqSuccess, parse error, e" + e.toString());
                    ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                    if (iSubDeviceActionListener2 != null) {
                        iSubDeviceActionListener2.onFailed(aError3);
                    }
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError2) {
                String str2;
                StringBuilder sb = new StringBuilder();
                sb.append("offline(), onFailure(), aError, e");
                if (aError2 == null) {
                    str2 = null;
                } else {
                    str2 = "[" + aError2.getCode() + ":" + aError2.getMsg() + "]";
                }
                sb.append(str2);
                ALog.m480e("SubDeviceChannelImpl", sb.toString());
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onFailed(aError2);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public void setDisableListener(final IConnectRrpcListener iConnectRrpcListener) {
        SubDeviceInfo subDeviceInfo;
        PersistentConnectConfig persistentConnectConfig = this.f992a;
        if (persistentConnectConfig == null || !m468a(persistentConnectConfig) || (subDeviceInfo = this.f993b) == null || !subDeviceInfo.checkValid()) {
            return;
        }
        if (m467a() != ConnectState.CONNECTED) {
            if (iConnectRrpcListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                iConnectRrpcListener.onSubscribeFailed(null, aError);
                return;
            }
            return;
        }
        if (this.f993b.loginState == SubDeviceLoginState.OFFLINE) {
            AError aError2 = new AError();
            aError2.setCode(1101000);
            aError2.setMsg("SubDevice offline.");
            iConnectRrpcListener.onSubscribeFailed(null, aError2);
            return;
        }
        MqttRrpcRegisterRequest mqttRrpcRegisterRequest = new MqttRrpcRegisterRequest();
        mqttRrpcRegisterRequest.topic = "/sys/" + this.f993b.productKey + "/" + this.f993b.deviceName + "/thing/disable";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttRrpcRegisterRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttRrpcRegisterRequest.replyTopic = sb.toString();
        ConnectSDK.getInstance().subscribeRrpc(ConnectSDK.getInstance().getPersistentConnectId(), mqttRrpcRegisterRequest, new IConnectRrpcListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.c.3
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeSuccess(ARequest aRequest) {
                ALog.m479d("SubDeviceChannelImpl", "setDisableListener onSubscribeSuccess() called with: aRequest = [" + aRequest + "]");
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeSuccess(aRequest);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeFailed(ARequest aRequest, AError aError3) {
                ALog.m479d("SubDeviceChannelImpl", "setDisableListener onSubscribeFailed() called with: aRequest = [" + aRequest + "], aError = [" + aError3 + "]");
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeFailed(aRequest, aError3);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onReceived(ARequest aRequest, IConnectRrpcHandle iConnectRrpcHandle) {
                ALog.m479d("SubDeviceChannelImpl", "setDisableListener onReceived() called with: aRequest = [" + aRequest + "], iConnectRrpcHandle = [" + iConnectRrpcHandle + "]");
                C0986c.this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onReceived(aRequest, iConnectRrpcHandle);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseSuccess(ARequest aRequest) {
                ALog.m479d("SubDeviceChannelImpl", "setDisableListener onResponseSuccess() called with: aRequest = [" + aRequest + "]");
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onResponseSuccess(aRequest);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseFailed(ARequest aRequest, AError aError3) {
                String str;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("setDisableListener onResponseFailed() called with: aRequest = [");
                sb2.append(aRequest);
                sb2.append("], aError = [");
                if (aError3 == null) {
                    str = null;
                } else {
                    str = aError3.getCode() + aError3.getMsg();
                }
                sb2.append(str);
                sb2.append("]");
                ALog.m479d("SubDeviceChannelImpl", sb2.toString());
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeFailed(aRequest, aError3);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public void setDeleteListener(final IConnectRrpcListener iConnectRrpcListener) {
        SubDeviceInfo subDeviceInfo;
        PersistentConnectConfig persistentConnectConfig = this.f992a;
        if (persistentConnectConfig == null || !m468a(persistentConnectConfig) || (subDeviceInfo = this.f993b) == null || !subDeviceInfo.checkValid()) {
            return;
        }
        if (m467a() != ConnectState.CONNECTED) {
            if (iConnectRrpcListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                iConnectRrpcListener.onSubscribeFailed(null, aError);
                return;
            }
            return;
        }
        if (this.f993b.loginState == SubDeviceLoginState.OFFLINE) {
            AError aError2 = new AError();
            aError2.setCode(1101002);
            aError2.setMsg("SubDevice offline.");
            iConnectRrpcListener.onSubscribeFailed(null, aError2);
            return;
        }
        MqttRrpcRegisterRequest mqttRrpcRegisterRequest = new MqttRrpcRegisterRequest();
        mqttRrpcRegisterRequest.topic = "/sys/" + this.f993b.productKey + "/" + this.f993b.deviceName + "/thing/delete";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttRrpcRegisterRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttRrpcRegisterRequest.replyTopic = sb.toString();
        ConnectSDK.getInstance().subscribeRrpc(ConnectSDK.getInstance().getPersistentConnectId(), mqttRrpcRegisterRequest, new IConnectRrpcListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.c.4
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeSuccess(ARequest aRequest) {
                ALog.m479d("SubDeviceChannelImpl", "setDeleteListener onSubscribeSuccess() called with: aRequest = [" + aRequest + "]");
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeSuccess(aRequest);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeFailed(ARequest aRequest, AError aError3) {
                ALog.m479d("SubDeviceChannelImpl", "setDeleteListener onSubscribeFailed() called with: aRequest = [" + aRequest + "], aError = [" + aError3 + "]");
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeFailed(aRequest, aError3);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onReceived(ARequest aRequest, IConnectRrpcHandle iConnectRrpcHandle) {
                ALog.m479d("SubDeviceChannelImpl", "setDeleteListener onReceived() called with: aRequest = [" + aRequest + "], iConnectRrpcHandle = [" + iConnectRrpcHandle + "]");
                C0986c.this.f993b.loginState = SubDeviceLoginState.OFFLINE;
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onReceived(aRequest, iConnectRrpcHandle);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseSuccess(ARequest aRequest) {
                ALog.m479d("SubDeviceChannelImpl", "setDeleteListener onResponseSuccess() called with: aRequest = [" + aRequest + "]");
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onResponseSuccess(aRequest);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseFailed(ARequest aRequest, AError aError3) {
                String str;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("setDeleteListeneronResponseFailed() called with: aRequest = [");
                sb2.append(aRequest);
                sb2.append("], aError = [");
                if (aError3 == null) {
                    str = null;
                } else {
                    str = aError3.getCode() + aError3.getMsg();
                }
                sb2.append(str);
                sb2.append("]");
                ALog.m479d("SubDeviceChannelImpl", sb2.toString());
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeFailed(aRequest, aError3);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public void uploadData(String str, String str2, final ISubDeviceActionListener iSubDeviceActionListener) {
        SubDeviceInfo subDeviceInfo;
        PersistentConnectConfig persistentConnectConfig = this.f992a;
        if (persistentConnectConfig == null || !m468a(persistentConnectConfig) || (subDeviceInfo = this.f993b) == null || !subDeviceInfo.checkValid()) {
            return;
        }
        if (m467a() != ConnectState.CONNECTED) {
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        if (this.f993b.loginState == SubDeviceLoginState.OFFLINE) {
            AError aError2 = new AError();
            aError2.setCode(1101004);
            aError2.setMsg("SubDevice offline.");
            iSubDeviceActionListener.onFailed(aError2);
            return;
        }
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.isRPC = false;
        mqttPublishRequest.topic = str;
        mqttPublishRequest.payloadObj = str2;
        ConnectSDK.getInstance().send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.c.5
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                ALog.m479d("SubDeviceChannelImpl", "uploadData(), onSuceess");
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onSuccess();
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError3) {
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onFailed(aError3);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public void subscribe(String str, final ISubDeviceActionListener iSubDeviceActionListener) {
        SubDeviceInfo subDeviceInfo;
        SubDeviceInfo subDeviceInfo2;
        PersistentConnectConfig persistentConnectConfig = this.f992a;
        if (persistentConnectConfig == null || !m468a(persistentConnectConfig) || (subDeviceInfo = this.f993b) == null || !subDeviceInfo.checkValid() || TextUtils.isEmpty(str)) {
            return;
        }
        if (m467a() != ConnectState.CONNECTED) {
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        if (this.f993b.loginState == SubDeviceLoginState.OFFLINE) {
            AError aError2 = new AError();
            aError2.setCode(1101006);
            aError2.setMsg("SubDevice offline.");
            iSubDeviceActionListener.onFailed(aError2);
            return;
        }
        if (!str.startsWith("/sys/") && !str.startsWith("/ota/") && (subDeviceInfo2 = this.f993b) != null && subDeviceInfo2.checkValid()) {
            str = ("/sys/" + this.f993b.productKey + "/" + this.f993b.deviceName + "/" + str).replace("//", "/");
        }
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, new IConnectSubscribeListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.c.6
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onSuccess() {
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onSuccess();
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onFailure(AError aError3) {
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onFailed(aError3);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public void unSubscribe(String str, final ISubDeviceActionListener iSubDeviceActionListener) {
        SubDeviceInfo subDeviceInfo;
        SubDeviceInfo subDeviceInfo2;
        PersistentConnectConfig persistentConnectConfig = this.f992a;
        if (persistentConnectConfig == null || !m468a(persistentConnectConfig) || (subDeviceInfo = this.f993b) == null || !subDeviceInfo.checkValid() || TextUtils.isEmpty(str)) {
            return;
        }
        if (m467a() != ConnectState.CONNECTED) {
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        if (this.f993b.loginState == SubDeviceLoginState.OFFLINE) {
            AError aError2 = new AError();
            aError2.setCode(1101008);
            aError2.setMsg("SubDevice offline.");
            iSubDeviceActionListener.onFailed(aError2);
            return;
        }
        if (!str.startsWith("/sys/") && !str.startsWith("/ota/") && (subDeviceInfo2 = this.f993b) != null && subDeviceInfo2.checkValid()) {
            str = ("/sys/" + this.f993b.productKey + "/" + this.f993b.deviceName + "/" + str).replace("//", "/");
        }
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        ConnectSDK.getInstance().unsubscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, new IConnectUnscribeListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.c.7
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onSuccess() {
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onSuccess();
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onFailure(AError aError3) {
                ISubDeviceActionListener iSubDeviceActionListener2 = iSubDeviceActionListener;
                if (iSubDeviceActionListener2 != null) {
                    iSubDeviceActionListener2.onFailed(aError3);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel
    public SubDeviceInfo getSubDeviceInfo() {
        return this.f993b;
    }

    /* renamed from: a */
    private ConnectState m467a() {
        return ConnectSDK.getInstance().getConnectState(ConnectSDK.getInstance().getPersistentConnectId());
    }

    /* renamed from: a */
    private boolean m468a(PersistentConnectConfig persistentConnectConfig) {
        return (persistentConnectConfig == null || TextUtils.isEmpty(persistentConnectConfig.productKey) || TextUtils.isEmpty(persistentConnectConfig.deviceName)) ? false : true;
    }
}
