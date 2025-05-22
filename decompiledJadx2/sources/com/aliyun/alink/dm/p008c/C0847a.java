package com.aliyun.alink.dm.p008c;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.api.IShadowRRPC;
import com.aliyun.alink.dm.p010d.C0852a;
import com.aliyun.alink.dm.p014h.C0856a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.dm.shadow.ShadowResponse;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DMConnectManager.java */
/* renamed from: com.aliyun.alink.dm.c.a */
/* loaded from: classes.dex */
public class C0847a {

    /* renamed from: a */
    private a f337a;

    /* renamed from: b */
    private b f338b;

    private C0847a() {
        this.f337a = new a();
        this.f338b = new b();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: DMConnectManager.java */
    /* renamed from: com.aliyun.alink.dm.c.a$c */
    /* loaded from: classes.dex */
    private static class c {

        /* renamed from: a */
        private static final C0847a f360a = new C0847a();
    }

    /* renamed from: a */
    public static C0847a m89a() {
        return c.f360a;
    }

    /* renamed from: a */
    public void m94a(String str, String str2, IConnectSendListener iConnectSendListener) {
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = str;
        mqttPublishRequest.payloadObj = str2;
        ConnectSDK.getInstance().send(mqttPublishRequest, iConnectSendListener);
    }

    /* renamed from: a */
    public void m92a(ARequest aRequest, final IConnectSendListener iConnectSendListener) {
        ConnectSDK.getInstance().send(aRequest, new IConnectSendListener() { // from class: com.aliyun.alink.dm.c.a.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest2, AResponse aResponse) {
                C0859a.m131a("DMConnectManager", "onResponse() called with: aRequest = [" + aRequest2 + "], aResponse = [" + C0847a.this.m90a(aResponse) + "]");
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onResponse(aRequest2, aResponse);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest2, AError aError) {
                C0859a.m131a("DMConnectManager", "onFailure() called with: aRequest = [" + aRequest2 + "], aError = [" + C0847a.this.m91a(aError) + "]");
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onFailure(aRequest2, aError);
                }
            }
        });
    }

    /* renamed from: a */
    public String m91a(AError aError) {
        if (aError == null) {
            return null;
        }
        return "code=" + aError.getCode() + ", subcode=" + aError.getSubCode() + ",msg=" + aError.getMsg() + ",subMsg=" + aError.getSubMsg() + ",error=" + aError;
    }

    /* renamed from: a */
    public String m90a(AResponse aResponse) {
        if (aResponse == null) {
            return null;
        }
        return "response.data=" + aResponse.data + ", response=" + aResponse;
    }

    /* renamed from: a */
    public void m95a(String str, String str2, String str3, final IConnectSendListener iConnectSendListener) {
        C0859a.m131a("DMConnectManager", "publishShadowRPC() called with: topic = [" + str + "], replyTopic = [" + str2 + "], data = [" + str3 + "], listener = [" + iConnectSendListener + "]");
        final MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = str;
        mqttPublishRequest.isRPC = false;
        mqttPublishRequest.payloadObj = str3;
        final MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str2;
        mqttPublishRequest.replyTopic = str2;
        this.f337a.m97a(mqttPublishRequest, iConnectSendListener);
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getPersistentConnectId(), this.f337a);
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, new IConnectSubscribeListener() { // from class: com.aliyun.alink.dm.c.a.2
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onSuccess() {
                C0859a.m131a("DMConnectManager", "onSuccess() called");
                try {
                    ConnectSDK.getInstance().send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.dm.c.a.2.1
                        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                        public void onResponse(ARequest aRequest, AResponse aResponse) {
                            C0859a.m131a("DMConnectManager", "onResponse() called with: aRequest = [" + aRequest + "], aResponse = [" + aResponse + "]");
                        }

                        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                        public void onFailure(ARequest aRequest, AError aError) {
                            C0859a.m134c("DMConnectManager", "onFailure() called with: aRequest = [" + aRequest + "], aError = [" + aError + "]");
                            if (iConnectSendListener != null) {
                                iConnectSendListener.onFailure(aRequest, C0856a.m127a(aError));
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    ConnectSDK.getInstance().unregisterNofityListener(C0847a.this.f337a);
                    if (iConnectSendListener != null) {
                        AError aError = new AError();
                        aError.setCode(DMErrorCode.ERROR_SHADOW_UPDATE_FAILED);
                        aError.setMsg("ShadowSend exception " + e);
                        iConnectSendListener.onFailure(null, C0856a.m127a(aError));
                    }
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onFailure(AError aError) {
                C0859a.m131a("DMConnectManager", "onFailure() called with: aError = [" + aError + "]");
                ConnectSDK.getInstance().unregisterNofityListener(C0847a.this.f337a);
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onFailure(mqttSubscribeRequest, C0856a.m127a(aError));
                }
            }
        });
    }

    /* renamed from: a */
    public void m93a(String str, String str2, final IShadowRRPC iShadowRRPC) {
        C0859a.m131a("DMConnectManager", "subscribeShadowRRPC() called with: topic = [" + str + "], replyTopic = [" + str2 + "], listener = [" + iShadowRRPC + "]");
        final MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        if (iShadowRRPC == null) {
            return;
        }
        this.f338b.m100a(iShadowRRPC, mqttSubscribeRequest, str);
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getPersistentConnectId(), this.f338b);
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, new IConnectSubscribeListener() { // from class: com.aliyun.alink.dm.c.a.3
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onSuccess() {
                IShadowRRPC iShadowRRPC2 = iShadowRRPC;
                if (iShadowRRPC2 != null) {
                    iShadowRRPC2.onSubscribeSuccess(mqttSubscribeRequest);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onFailure(AError aError) {
                IShadowRRPC iShadowRRPC2 = iShadowRRPC;
                if (iShadowRRPC2 != null) {
                    iShadowRRPC2.onSubscribeFailed(mqttSubscribeRequest, C0856a.m127a(aError));
                }
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: DMConnectManager.java */
    /* renamed from: com.aliyun.alink.dm.c.a$a */
    /* loaded from: classes.dex */
    private class a implements IConnectNotifyListener {

        /* renamed from: b */
        private MqttPublishRequest f350b;

        /* renamed from: c */
        private IConnectSendListener f351c;

        private a() {
            this.f350b = null;
        }

        /* renamed from: a */
        public void m97a(MqttPublishRequest mqttPublishRequest, IConnectSendListener iConnectSendListener) {
            this.f350b = mqttPublishRequest;
            this.f351c = iConnectSendListener;
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public void onNotify(String str, String str2, AMessage aMessage) {
            C0859a.m131a("DMConnectManager", "onNotify() rpc called with: connectId = [" + str + "], topic = [" + str2 + "], aMessage = [" + aMessage + "]");
            AResponse aResponse = new AResponse();
            if (aMessage != null) {
                aResponse.data = aMessage.data;
            }
            if (this.f351c == null) {
                return;
            }
            try {
                ShadowResponse shadowResponse = (ShadowResponse) JSONObject.parseObject(new String((byte[]) aMessage.data, "UTF-8"), new TypeReference<ShadowResponse<String>>() { // from class: com.aliyun.alink.dm.c.a.a.1
                }.getType(), new Feature[0]);
                if (shadowResponse == null || !C0852a.f396y.equals(shadowResponse.method)) {
                    C0859a.m131a("DMConnectManager", "onNotify rpc not reply, return.");
                } else {
                    this.f351c.onResponse(this.f350b, aResponse);
                }
            } catch (Exception e) {
                e.printStackTrace();
                C0859a.m134c("DMConnectManager", "onNotify rpc parse data error.");
            }
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public boolean shouldHandle(String str, String str2) {
            C0859a.m131a("DMConnectManager", "shouldHandle() rpc called with: connectId = [" + str + "], topic = [" + str2 + "]");
            return this.f350b != null && !TextUtils.isEmpty(str2) && str2.equals(this.f350b.replyTopic) && ConnectSDK.getInstance().getPersistentConnectId().equals(str);
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public void onConnectStateChange(String str, ConnectState connectState) {
            C0859a.m131a("DMConnectManager", "onConnectStateChange() called with: connectId = [" + str + "], connectState = [" + connectState + "]");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: DMConnectManager.java */
    /* renamed from: com.aliyun.alink.dm.c.a$b */
    /* loaded from: classes.dex */
    private class b implements IConnectNotifyListener {

        /* renamed from: b */
        private IShadowRRPC f354b;

        /* renamed from: c */
        private MqttSubscribeRequest f355c;

        /* renamed from: d */
        private String f356d;

        private b() {
        }

        /* renamed from: a */
        public void m100a(IShadowRRPC iShadowRRPC, MqttSubscribeRequest mqttSubscribeRequest, String str) {
            this.f354b = iShadowRRPC;
            this.f355c = mqttSubscribeRequest;
            this.f356d = str;
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public void onNotify(String str, String str2, AMessage aMessage) {
            C0859a.m131a("DMConnectManager", "onNotify() rrpc called with: connectId = [" + str + "], topic = [" + str2 + "], aMessage = [" + aMessage + "]");
            AResponse aResponse = new AResponse();
            if (aMessage != null) {
                aResponse.data = aMessage.data;
            }
            try {
                ShadowResponse shadowResponse = (ShadowResponse) JSONObject.parseObject(new String((byte[]) aMessage.data, "UTF-8"), new TypeReference<ShadowResponse<String>>() { // from class: com.aliyun.alink.dm.c.a.b.1
                }.getType(), new Feature[0]);
                if (shadowResponse == null || !C0852a.f395x.equals(shadowResponse.method)) {
                    C0859a.m131a("DMConnectManager", "rrpc not control, return.");
                    return;
                }
                IShadowRRPC iShadowRRPC = this.f354b;
                if (iShadowRRPC == null) {
                    return;
                }
                iShadowRRPC.onReceived(this.f355c, aResponse, new IConnectRrpcHandle() { // from class: com.aliyun.alink.dm.c.a.b.2
                    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle
                    public void onRrpcResponse(String str3, AResponse aResponse2) {
                        C0859a.m131a("DMConnectManager", "onRrpcResponse() rrpc called with: replyTopic = [" + str3 + "], aResponse = [" + aResponse2 + "]");
                        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
                        if (TextUtils.isEmpty(str3)) {
                            mqttPublishRequest.topic = b.this.f356d;
                        } else {
                            mqttPublishRequest.topic = str3;
                        }
                        if (aResponse2 != null && aResponse2.data != null) {
                            mqttPublishRequest.payloadObj = aResponse2.data.toString();
                        }
                        ConnectSDK.getInstance().send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.dm.c.a.b.2.1
                            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                            public void onResponse(ARequest aRequest, AResponse aResponse3) {
                                C0859a.m131a("DMConnectManager", "onResponse() called with: aRequest = [" + aRequest + "], aResponse = [" + aResponse3 + "]");
                                b.this.f354b.onResponseSuccess(aRequest);
                            }

                            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                            public void onFailure(ARequest aRequest, AError aError) {
                                C0859a.m131a("DMConnectManager", "onFailure() called with: aRequest = [" + aRequest + "], aError = [" + aError + "]");
                                b.this.f354b.onResponseFailed(aRequest, aError);
                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                C0859a.m134c("DMConnectManager", "onNotify parse data error.");
            }
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public boolean shouldHandle(String str, String str2) {
            C0859a.m131a("DMConnectManager", "shouldHandle() rrpc called with: connectId = [" + str + "], topic = [" + str2 + "]");
            return (this.f355c == null || this.f354b == null || TextUtils.isEmpty(str2) || !str2.equals(this.f356d) || !ConnectSDK.getInstance().getPersistentConnectId().equals(str)) ? false : true;
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public void onConnectStateChange(String str, ConnectState connectState) {
            C0859a.m131a("DMConnectManager", "onConnectStateChange() called with: connectId = [" + str + "], connectState = [" + connectState + "]");
        }
    }

    /* renamed from: b */
    public String m96b(AError aError) {
        if (aError == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("code=");
        stringBuffer.append(aError.getCode());
        stringBuffer.append(",");
        stringBuffer.append("subCode=");
        stringBuffer.append(aError.getSubCode());
        stringBuffer.append(",");
        stringBuffer.append("msg=");
        stringBuffer.append(aError.getMsg());
        stringBuffer.append(",");
        stringBuffer.append("subMsg=");
        stringBuffer.append(aError.getSubMsg());
        stringBuffer.append(",");
        stringBuffer.append("subDomain=");
        stringBuffer.append(aError.getSubDomain());
        stringBuffer.append(",");
        stringBuffer.append("originResponseObj=");
        stringBuffer.append(aError.getOriginResponseObject());
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
