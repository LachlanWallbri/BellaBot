package com.aliyun.alink.linksdk.channel.core.persistent;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.core.base.ARequest;
import com.aliyun.alink.linksdk.channel.core.base.ASend;
import com.aliyun.alink.linksdk.channel.core.base.IOnCallListener;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.C0978b;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.PersisitentNetParams;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PersistentNet implements IPersisitentNet {

    /* renamed from: a */
    private static String f885a = "PersistentNet";

    /* renamed from: b */
    private static String f886b = "MQTT";

    /* renamed from: c */
    private static String f887c = "0.7.1";

    /* renamed from: d */
    private String f888d = f886b;

    /* renamed from: e */
    private IPersisitentNet f889e = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class InstanceHolder {

        /* renamed from: a */
        private static final PersistentNet f890a = new PersistentNet();

        private InstanceHolder() {
        }
    }

    public static PersistentNet getInstance() {
        return InstanceHolder.f890a;
    }

    public void setDefaultProtocol(String str) {
        if (str == null || !str.equals(f886b)) {
            return;
        }
        this.f888d = f886b;
        this.f889e = C0978b.m421a();
    }

    public String getDefaultProtocol() {
        return this.f888d;
    }

    protected void setReportVersion(String str) {
        C0969a.m393a(f885a, "setReportVersion() called with: version = [" + str + "]， moduleVersion=0.7.1-17eccf4");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f887c = str;
    }

    public String getSDKVersion() {
        return f887c;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void openLog(boolean z) {
        m397a().openLog(z);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void init(Context context, PersistentInitParams persistentInitParams) {
        C0969a.m393a(f885a, "init(), SDK Ver = " + f887c);
        m397a().init(context, persistentInitParams);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void destroy() {
        m397a().destroy();
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void destroy(long j, Object obj, IMqttActionListener iMqttActionListener) {
        m397a().destroy(j, obj, iMqttActionListener);
    }

    public PersistentInitParams getInitParams() {
        IPersisitentNet m397a = m397a();
        if (m397a instanceof C0978b) {
            return ((C0978b) m397a).m432b();
        }
        return null;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public PersistentConnectState getConnectState() {
        return m397a().getConnectState();
    }

    @Override // com.aliyun.alink.linksdk.channel.core.base.INet
    public ASend asyncSend(ARequest aRequest, IOnCallListener iOnCallListener) {
        return m397a().asyncSend((PersistentRequest) aRequest, iOnCallListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.base.INet
    public void retry(ASend aSend) {
        m397a().retry(aSend);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void subscribe(String str, IOnSubscribeListener iOnSubscribeListener) {
        m397a().subscribe(str, iOnSubscribeListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void subscribe(String str, PersisitentNetParams persisitentNetParams, IOnSubscribeListener iOnSubscribeListener) {
        m397a().subscribe(str, persisitentNetParams, iOnSubscribeListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void unSubscribe(String str, IOnSubscribeListener iOnSubscribeListener) {
        m397a().unSubscribe(str, iOnSubscribeListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void subscribeRrpc(String str, IOnSubscribeRrpcListener iOnSubscribeRrpcListener) {
        m397a().subscribeRrpc(str, iOnSubscribeRrpcListener);
    }

    /* renamed from: a */
    private IPersisitentNet m397a() {
        if (this.f889e == null) {
            this.f889e = C0978b.m421a();
        }
        return this.f889e;
    }
}
