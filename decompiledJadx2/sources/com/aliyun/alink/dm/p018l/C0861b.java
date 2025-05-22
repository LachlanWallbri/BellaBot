package com.aliyun.alink.dm.p018l;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.alink.dm.api.DMConfigParams;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.coap.C0851b;
import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.dm.model.ResponseModel;
import com.aliyun.alink.dm.p010d.C0852a;
import com.aliyun.alink.dm.p012f.C0854a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.dm.p021o.C0868c;
import com.aliyun.alink.dm.p021o.C0869d;
import com.aliyun.alink.dm.p021o.C0870e;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPConstant;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPContext;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPRequest;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPResponse;
import com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPReqHandler;
import com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: TokenBizImpl.java */
/* renamed from: com.aliyun.alink.dm.l.b */
/* loaded from: classes.dex */
public class C0861b implements InterfaceC0860a {

    /* renamed from: e */
    private static C0862c f410e;

    /* renamed from: a */
    private long f411a = -1;

    /* renamed from: b */
    private AlcsCoAPRequest f412b = null;

    /* renamed from: c */
    private String f413c = null;

    /* renamed from: d */
    private String f414d = null;

    /* renamed from: f */
    private boolean f415f = false;

    /* renamed from: g */
    private AtomicBoolean f416g = new AtomicBoolean(false);

    /* renamed from: h */
    private AtomicBoolean f417h = new AtomicBoolean(false);

    /* renamed from: i */
    private IAlcsCoAPReqHandler f418i = new IAlcsCoAPReqHandler() { // from class: com.aliyun.alink.dm.l.b.2
        @Override // com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPReqHandler
        public void onReqComplete(AlcsCoAPContext alcsCoAPContext, int i, AlcsCoAPResponse alcsCoAPResponse) {
            try {
                ResponseModel responseModel = (ResponseModel) JSONObject.parseObject(alcsCoAPResponse.getPayloadString(), new TypeReference<ResponseModel<String>>() { // from class: com.aliyun.alink.dm.l.b.2.1
                }.getType(), new Feature[0]);
                C0859a.m131a("TokenBizImpl", "onReqComplete receive ack payload=" + alcsCoAPResponse.getPayloadString());
                if (responseModel != null && ErrorCode.UNKNOWN_SUCCESS_CODE.equals(responseModel.code) && C0861b.this.f415f) {
                    C0859a.m131a("TokenBizImpl", "onReqComplete acked token=" + C0861b.f410e);
                    C0861b.this.f415f = false;
                    if (C0861b.f410e != null) {
                        C0861b.f410e.m152a(false);
                    }
                }
            } catch (Exception unused) {
            }
        }
    };

    /* renamed from: j */
    private IAlcsCoAPResHandler f419j = new IAlcsCoAPResHandler() { // from class: com.aliyun.alink.dm.l.b.3
        @Override // com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler
        public void onRecRequest(AlcsCoAPContext alcsCoAPContext, final AlcsCoAPRequest alcsCoAPRequest) {
            try {
                RequestModel requestModel = (RequestModel) JSONObject.parseObject(alcsCoAPRequest.getPayloadString(), new TypeReference<RequestModel<DeviceInfo>>() { // from class: com.aliyun.alink.dm.l.b.3.1
                }.getType(), new Feature[0]);
                if (requestModel != null && requestModel.method != null && "device.info.get".equals(requestModel.method)) {
                    if (!C0861b.this.f417h.get()) {
                        C0859a.m133b("TokenBizImpl", "onRecRequest device resetting or deinited, return");
                        return;
                    } else {
                        C0861b.this.mo137a(new IConnectSendListener() { // from class: com.aliyun.alink.dm.l.b.3.2
                            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                            public void onResponse(ARequest aRequest, AResponse aResponse) {
                                C0859a.m131a("TokenBizImpl", "onResponse tokenWrapper=" + C0861b.f410e);
                                if (C0861b.this.m143a(C0861b.f410e)) {
                                    C0861b.this.m139a(alcsCoAPRequest, C0861b.f410e);
                                    return;
                                }
                                C0859a.m135d("TokenBizImpl", "onResponse token invalid, return." + C0861b.f410e);
                            }

                            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                            public void onFailure(ARequest aRequest, AError aError) {
                                C0859a.m134c("TokenBizImpl", "onFailure aRequest==" + aRequest + ",aError=" + aError);
                            }
                        });
                        return;
                    }
                }
                C0859a.m131a("TokenBizImpl", "invalid message.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public C0861b() {
        f410e = new C0862c();
    }

    @Override // com.aliyun.alink.dm.p018l.InterfaceC0860a
    /* renamed from: a */
    public void mo136a(DMConfigParams dMConfigParams) {
        if (dMConfigParams == null || dMConfigParams.enableLocalCommunication) {
            C0859a.m133b("TokenBizImpl", "init enableNotify=true.");
            C0851b.m111a().m112a(this.f419j);
            this.f417h.set(true);
        } else {
            C0851b.m111a().m115b(this.f419j);
            C0859a.m133b("TokenBizImpl", "init enableNotify=false.");
        }
    }

    /* renamed from: c */
    private String m146c() {
        try {
            return C0870e.m192a((((C0854a.m116a() == null || C0854a.m116a().m121c() == null) ? "" : C0854a.m116a().m121c().deviceSecret) + C0870e.m189a(32)).getBytes(), "SHA256");
        } catch (NoSuchAlgorithmException e) {
            C0859a.m131a("TokenBizImpl", "sha256 random failed." + e);
            return C0870e.m189a(32);
        }
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [T, java.util.Map, java.util.HashMap] */
    @Override // com.aliyun.alink.dm.p018l.InterfaceC0860a
    /* renamed from: a */
    public void mo137a(final IConnectSendListener iConnectSendListener) {
        C0859a.m133b("TokenBizImpl", "tokenPost() called");
        if (C0854a.m116a().m121c() == null) {
            return;
        }
        if (!this.f417h.get()) {
            C0859a.m131a("TokenBizImpl", "tokenPost return, not allowed to response.");
            if (iConnectSendListener != null) {
                iConnectSendListener.onResponse(null, null);
                return;
            }
            return;
        }
        if (m143a(f410e)) {
            C0859a.m131a("TokenBizImpl", "tokenPost return, already have a valid token.");
            if (iConnectSendListener != null) {
                iConnectSendListener.onResponse(null, null);
                return;
            }
            return;
        }
        final String m146c = m146c();
        RequestModel requestModel = new RequestModel();
        requestModel.f462id = String.valueOf(C0868c.m183a());
        requestModel.version = "1.0";
        requestModel.method = C0852a.f384m;
        ?? hashMap = new HashMap();
        hashMap.put("token", m146c);
        requestModel.params = hashMap;
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.isRPC = false;
        mqttPublishRequest.topic = C0852a.f383l.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, C0854a.m116a().m121c().productKey).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, C0854a.m116a().m121c().deviceName);
        mqttPublishRequest.payloadObj = requestModel.toString();
        ConnectSDK.getInstance().send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.dm.l.b.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                StringBuilder sb = new StringBuilder();
                sb.append("onResponse() called with: aRequest = [");
                sb.append(aRequest);
                sb.append("], aResponse = [");
                sb.append(aResponse == null ? null : aResponse.data);
                sb.append("]");
                C0859a.m131a("TokenBizImpl", sb.toString());
                if (C0861b.f410e == null || !C0861b.this.m143a(C0861b.f410e) || !C0861b.this.f415f) {
                    C0861b.f410e.m150a(System.currentTimeMillis());
                    C0861b.f410e.m151a(m146c);
                    C0861b.f410e.m152a(true);
                } else {
                    C0859a.m134c("TokenBizImpl", "onResponse tokenWrapper=" + C0861b.f410e);
                }
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onResponse(aRequest, aResponse);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                C0859a.m131a("TokenBizImpl", "onFailure() called with: aRequest = [" + aRequest + "], aError = [" + aError + "]");
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onFailure(aRequest, aError);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m139a(AlcsCoAPRequest alcsCoAPRequest, C0862c c0862c) {
        DeviceInfo m121c = C0854a.m116a().m121c();
        if (m121c == null || c0862c == null) {
            return;
        }
        AlcsCoAPResponse createResponse = AlcsCoAPResponse.createResponse(alcsCoAPRequest, AlcsCoAPConstant.ResponseCode._UNKNOWN_SUCCESS_CODE);
        createResponse.setToken(alcsCoAPRequest.getToken());
        createResponse.setMID(alcsCoAPRequest.getMID());
        if (this.f414d == null) {
            this.f414d = C0869d.m187a("wlan0", C0854a.m116a().m120b());
        }
        if (this.f413c == null) {
            this.f413c = C0869d.m186a(C0854a.m116a().m120b());
        }
        String replace = C0852a.f370A.replace(C0852a.f388q, "123").replace(C0852a.f394w, String.valueOf(45000 - (System.currentTimeMillis() - c0862c.m153b()))).replace(C0852a.f389r, m121c.productKey).replace(C0852a.f390s, m121c.deviceName);
        String str = C0852a.f391t;
        String str2 = this.f414d;
        if (str2 == null) {
            str2 = "";
        }
        String replace2 = replace.replace(str, str2);
        String str3 = C0852a.f392u;
        String str4 = this.f413c;
        createResponse.setPayload(replace2.replace(str3, str4 != null ? str4 : "").replace(C0852a.f393v, c0862c.m149a()));
        C0859a.m133b("TokenBizImpl", "ack token=" + alcsCoAPRequest.getTokenString() + ",msgId=" + alcsCoAPRequest.getMID());
        C0851b.m111a().m113a(createResponse);
        c0862c.m152a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m143a(C0862c c0862c) {
        if (c0862c == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long m153b = currentTimeMillis - c0862c.m153b();
        C0859a.m131a("TokenBizImpl", "isValidToken token=" + c0862c + ",differ=" + m153b + ",currentTime=" + currentTimeMillis);
        return c0862c.m154c() && c0862c.m153b() > 0 && m153b < 45000 && m153b > -1;
    }

    /* renamed from: a */
    public void m148a(boolean z) {
        this.f417h.set(z);
    }

    /* renamed from: a */
    public void m147a() {
        this.f416g.set(true);
        C0862c c0862c = f410e;
        if (c0862c != null) {
            c0862c.m152a(false);
        }
        this.f415f = false;
        C0851b.m111a().m115b(this.f419j);
        m148a(true);
    }
}
