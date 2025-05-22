package com.aliyun.alink.dm.p019m;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IOta;
import com.aliyun.alink.dm.api.OtaInfo;
import com.aliyun.alink.dm.api.ResultCallback;
import com.aliyun.alink.dm.model.ResponseModel;
import com.aliyun.alink.dm.p008c.C0847a;
import com.aliyun.alink.dm.p012f.C0854a;
import com.aliyun.alink.dm.p019m.C0863a;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: OtaImpl.java */
/* renamed from: com.aliyun.alink.dm.m.b */
/* loaded from: classes.dex */
public class C0864b implements IOta {

    /* renamed from: a */
    private C0847a f443a;

    /* renamed from: b */
    private C0863a f444b;

    /* renamed from: c */
    private IConnectNotifyListener f445c;

    /* renamed from: d */
    private IOta.OtaListener f446d;

    /* renamed from: e */
    private IOta.OtaConfig f447e;

    public C0864b() {
        m168a();
    }

    /* renamed from: a */
    void m168a() {
        this.f443a = C0847a.m89a();
    }

    @Override // com.aliyun.alink.dm.api.IOta
    public void tryStartOta(IOta.OtaConfig otaConfig, IOta.OtaListener otaListener) {
        ALog.m479d("OtaImpl", "tryStartOta");
        this.f447e = otaConfig;
        this.f446d = otaListener;
        m169a(otaConfig.deviceVersion, this.f446d, null);
        m171b();
    }

    @Override // com.aliyun.alink.dm.api.IOta
    public void tryStopOta() {
        ALog.m479d("OtaImpl", "tryStopOta");
        m172c();
        m173d();
        IOta.OtaConfig otaConfig = this.f447e;
        if (otaConfig != null && otaConfig.otaFile != null) {
            this.f447e.otaFile.delete();
        }
        this.f447e = null;
        this.f446d = null;
    }

    @Override // com.aliyun.alink.dm.api.IOta
    public void reportVersion(String str, ResultCallback<String> resultCallback) {
        m169a(str, null, resultCallback);
    }

    /* renamed from: a */
    public void m169a(String str, final IOta.OtaListener otaListener, final ResultCallback<String> resultCallback) {
        DeviceInfo m121c = C0854a.m116a().m121c();
        String str2 = "/ota/device/inform/" + m121c.productKey + "/" + m121c.deviceName;
        HashMap hashMap = new HashMap();
        hashMap.put("id", 1);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("version", str);
        hashMap.put("params", hashMap2);
        String jSONObject = new JSONObject(hashMap).toString();
        ALog.m479d("OtaImpl", "reportVersion topic:" + str2);
        ALog.m479d("OtaImpl", "reportVersion data :" + jSONObject);
        this.f443a.m94a(str2, jSONObject, new IConnectSendListener() { // from class: com.aliyun.alink.dm.m.b.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                ALog.m479d("OtaImpl", "reportVersion onResponse. aResponse:" + aResponse.data);
                ResultCallback resultCallback2 = resultCallback;
                if (resultCallback2 != null) {
                    resultCallback2.onRusult(0, "");
                }
                IOta.OtaListener otaListener2 = otaListener;
                if (otaListener2 != null) {
                    otaListener2.onOtaProgress(1, new a(0, ""));
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m484w("OtaImpl", "reportVersion onFailure");
                ResultCallback resultCallback2 = resultCallback;
                if (resultCallback2 != null) {
                    resultCallback2.onRusult(-1, C0847a.m89a().m96b(aError));
                }
                IOta.OtaListener otaListener2 = otaListener;
                if (otaListener2 != null) {
                    otaListener2.onOtaProgress(1, new a(1, C0847a.m89a().m96b(aError)));
                }
            }
        });
    }

    /* renamed from: b */
    public boolean m171b() {
        if (this.f445c != null) {
            ALog.m484w("OtaImpl", "has subscribed.");
            return false;
        }
        final String m167e = m167e();
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = m167e;
        mqttSubscribeRequest.isSubscribe = true;
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, new IConnectSubscribeListener() { // from class: com.aliyun.alink.dm.m.b.2
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onSuccess() {
                ALog.m479d("OtaImpl", "subscribe success topic:" + m167e);
                C0864b.this.f446d.onOtaProgress(2, new a(0, ""));
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onFailure(AError aError) {
                ALog.m484w("OtaImpl", "subscribe onFailure topic:" + m167e + ", error=" + C0847a.m89a().m96b(aError));
                C0864b.this.f446d.onOtaProgress(2, new a(1, ""));
            }
        });
        this.f445c = new IConnectNotifyListener() { // from class: com.aliyun.alink.dm.m.b.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
            public void onNotify(String str, String str2, AMessage aMessage) {
                ALog.m479d("OtaImpl", "onNotify success s:" + str + " s1:" + str2 + " aMessage:" + aMessage.data);
                if (aMessage == null || aMessage.data == null || !(aMessage.data instanceof byte[])) {
                    C0864b.this.f446d.onOtaProgress(3, new a(1, ""));
                    return;
                }
                String str3 = new String((byte[]) aMessage.data);
                ALog.m479d("OtaImpl", "onNotify response:" + str3);
                ResponseModel responseModel = (ResponseModel) JSONObject.parseObject(str3, new TypeReference<ResponseModel<OtaInfo>>() { // from class: com.aliyun.alink.dm.m.b.3.1
                }.getType(), new Feature[0]);
                if (responseModel != null) {
                    if (true == C0864b.this.f446d.onOtaProgress(3, new a(0, responseModel.data))) {
                        C0864b c0864b = C0864b.this;
                        c0864b.m170a(c0864b.f447e.otaFile.getPath(), (OtaInfo) responseModel.data);
                        return;
                    }
                    return;
                }
                C0864b.this.f446d.onOtaProgress(3, new a(1, ""));
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
            public boolean shouldHandle(String str, String str2) {
                ALog.m479d("OtaImpl", "shouldHandle s:" + str + " s1:" + str2);
                return m167e.equalsIgnoreCase(str2);
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
            public void onConnectStateChange(String str, ConnectState connectState) {
                ALog.m479d("OtaImpl", "onConnectStateChange connectState:" + connectState);
            }
        };
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getPersistentConnectId(), this.f445c);
        return true;
    }

    /* renamed from: e */
    private String m167e() {
        DeviceInfo m121c = C0854a.m116a().m121c();
        return "/ota/device/upgrade/" + m121c.productKey + "/" + m121c.deviceName;
    }

    /* renamed from: c */
    public boolean m172c() {
        if (this.f445c != null) {
            String m167e = m167e();
            MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
            mqttSubscribeRequest.topic = m167e;
            mqttSubscribeRequest.isSubscribe = false;
            ConnectSDK.getInstance().unsubscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, new IConnectUnscribeListener() { // from class: com.aliyun.alink.dm.m.b.4
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
                public void onSuccess() {
                    ALog.m479d("OtaImpl", "unsubscribe onSuccess");
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
                public void onFailure(AError aError) {
                    ALog.m484w("OtaImpl", "unsubscribe onFailure error=" + C0847a.m89a().m96b(aError));
                }
            });
            ConnectSDK.getInstance().unregisterNofityListener(this.f445c);
            this.f445c = null;
            return true;
        }
        ALog.m484w("OtaImpl", "un match subscribe/unsubscribe");
        return false;
    }

    /* renamed from: a */
    public boolean m170a(String str, OtaInfo otaInfo) {
        if (this.f444b == null) {
            C0863a.a aVar = new C0863a.a();
            aVar.f441c = otaInfo.size;
            aVar.f440b = otaInfo.url;
            aVar.f439a = otaInfo.md5;
            aVar.f442d = str;
            C0863a c0863a = new C0863a(aVar, new C0863a.b() { // from class: com.aliyun.alink.dm.m.b.5
                @Override // com.aliyun.alink.dm.p019m.C0863a.b
                /* renamed from: a */
                public void mo163a(int i, int i2) {
                    C0864b.this.f446d.onOtaProgress(4, new a(0, Integer.valueOf((int) ((i / i2) * 100.0f))));
                }

                @Override // com.aliyun.alink.dm.p019m.C0863a.b
                /* renamed from: a */
                public void mo164a(int i, String str2) {
                    if (i != 0) {
                        ALog.m484w("OtaImpl", "onResult. code:" + i + " errorStr:" + str2);
                        C0864b.this.f446d.onOtaProgress(4, new a(3, str2));
                    }
                    C0864b.this.m173d();
                }
            });
            this.f444b = c0863a;
            c0863a.m160a();
            return true;
        }
        ALog.m484w("OtaImpl", "a download is ongoing. ignore.");
        this.f446d.onOtaProgress(4, new a(3, "a download is ongoing. ignore."));
        return false;
    }

    /* renamed from: d */
    public boolean m173d() {
        C0863a c0863a = this.f444b;
        if (c0863a != null) {
            c0863a.m161b();
            this.f444b = null;
            return true;
        }
        ALog.m484w("OtaImpl", "no download to stop.");
        return false;
    }

    @Override // com.aliyun.alink.dm.api.IOta
    public void reportProgress(int i, String str, final ResultCallback<String> resultCallback) {
        DeviceInfo m121c = C0854a.m116a().m121c();
        String str2 = "/ota/device/progress/" + m121c.productKey + "/" + m121c.deviceName;
        HashMap hashMap = new HashMap();
        hashMap.put("id", 1);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("step", i + "");
        hashMap2.put(TmpConstant.SERVICE_DESC, str);
        hashMap.put("params", hashMap2);
        String jSONObject = new JSONObject(hashMap).toString();
        ALog.m479d("OtaImpl", "reportProgress topic:" + str2);
        ALog.m479d("OtaImpl", "reportProgress data :" + jSONObject);
        this.f443a.m94a(str2, jSONObject, new IConnectSendListener() { // from class: com.aliyun.alink.dm.m.b.6
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                StringBuilder sb = new StringBuilder();
                sb.append("reportProgress onResponse. aResponse:");
                sb.append(aResponse == null ? "null" : aResponse.data);
                ALog.m479d("OtaImpl", sb.toString());
                resultCallback.onRusult(0, "");
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m484w("OtaImpl", "reportProgress onFailure error=" + C0847a.m89a().m96b(aError));
                resultCallback.onRusult(-1, C0847a.m89a().m96b(aError));
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: OtaImpl.java */
    /* renamed from: com.aliyun.alink.dm.m.b$a */
    /* loaded from: classes.dex */
    public static class a implements IOta.OtaResult {

        /* renamed from: a */
        private int f460a;

        /* renamed from: b */
        private Object f461b;

        public a(int i, Object obj) {
            this.f460a = i;
            this.f461b = obj;
        }

        @Override // com.aliyun.alink.dm.api.IOta.OtaResult
        public int getErrorCode() {
            return this.f460a;
        }

        @Override // com.aliyun.alink.dm.api.IOta.OtaResult
        public Object getData() {
            return this.f461b;
        }
    }
}
