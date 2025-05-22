package com.aliyun.alink.linksdk.channel.core.persistent.mqtt;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.core.base.AError;
import com.aliyun.alink.linksdk.channel.core.base.ARequest;
import com.aliyun.alink.linksdk.channel.core.base.ASend;
import com.aliyun.alink.linksdk.channel.core.base.IOnCallListener;
import com.aliyun.alink.linksdk.channel.core.p040a.C0968e;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener;
import com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentConnectState;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentInitParams;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentNet;
import com.aliyun.alink.linksdk.channel.core.persistent.event.PersistentEventDispatcher;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a.C0975b;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a.C0976c;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a.C0977d;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttSubscribeRequestParams;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.PersisitentNetParams;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send.C0980b;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send.C0981c;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send.MqttThrowable;
import com.aliyun.alink.linksdk.id2.Id2ItlsSdk;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.iflytek.cloud.SpeechUtility;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttNet.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.b */
/* loaded from: classes.dex */
public class C0978b implements IPersisitentNet {

    /* renamed from: a */
    private Context f916a;

    /* renamed from: b */
    private MemoryPersistence f917b;

    /* renamed from: c */
    private IMqttAsyncClient f918c;

    /* renamed from: d */
    private SSLSocketFactory f919d;

    /* renamed from: e */
    private MqttConnectOptions f920e;

    /* renamed from: f */
    private InputStream f921f;

    /* renamed from: k */
    private MqttInitParams f926k;

    /* renamed from: g */
    private AtomicBoolean f922g = new AtomicBoolean(false);

    /* renamed from: h */
    private AtomicBoolean f923h = new AtomicBoolean(false);

    /* renamed from: i */
    private PersistentConnectState f924i = PersistentConnectState.DISCONNECTED;

    /* renamed from: j */
    private C0973a f925j = null;

    /* renamed from: l */
    private AtomicBoolean f927l = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: MqttNet.java */
    /* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.b$a */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        private static final C0978b f936a = new C0978b();
    }

    /* renamed from: a */
    public static C0978b m421a() {
        return a.f936a;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void openLog(boolean z) {
        if (z) {
            ALog.setLevel((byte) 1);
            LoggerFactory.setLogger(C0975b.class.getName());
            C0975b.f912a = true;
            return;
        }
        C0975b.f912a = false;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void init(Context context, PersistentInitParams persistentInitParams) {
        C0969a.m393a("MqttNet", "init()");
        if (this.f922g.get() || this.f923h.get() || this.f924i == PersistentConnectState.CONNECTING || this.f924i == PersistentConnectState.CONNECTED) {
            C0969a.m393a("MqttNet", "init(), already init, ignore init call!");
            return;
        }
        if (context != null && persistentInitParams != null && (persistentInitParams instanceof MqttInitParams)) {
            MqttInitParams mqttInitParams = (MqttInitParams) persistentInitParams;
            if (mqttInitParams.checkValid()) {
                this.f922g.set(true);
                this.f923h.set(false);
                this.f916a = context;
                this.f926k = mqttInitParams;
                LoggerFactory.setLogger(C0975b.class.getName());
                MqttConfigure.productKey = this.f926k.productKey;
                MqttConfigure.productSecret = this.f926k.productSecret;
                MqttConfigure.deviceName = this.f926k.deviceName;
                MqttConfigure.deviceSecret = this.f926k.deviceSecret;
                MqttConfigure.cleanSession = !this.f926k.receiveOfflineMsg;
                MqttConfigure.SECURE_MODE = this.f926k.secureMode;
                if (MqttConfigure.SECURE_MODE == 8) {
                    Id2ItlsSdk.init(context);
                }
                if (MqttConfigure.SECURE_MODE == 2 && MqttConfigure.isCheckRootCrt) {
                    if (MqttConfigure.mqttRootCrtFile == null) {
                        C0969a.m393a("MqttNet", "init(),default cert file");
                        try {
                            this.f921f = context.getAssets().open(MqttConfigure.DEFAULT_ROOTCRT);
                        } catch (Exception e) {
                            C0969a.m396d("MqttNet", "setCertFile : cannot config cert file：" + e.getMessage());
                        }
                    } else {
                        C0969a.m393a("MqttNet", "init(),custom cert file");
                        this.f921f = MqttConfigure.mqttRootCrtFile;
                    }
                }
                m429f();
                return;
            }
        }
        C0969a.m396d("MqttNet", "init error ,params error");
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void destroy() {
        C0969a.m393a("MqttNet", "destroy()");
        this.f927l.set(false);
        try {
            C0969a.m393a("MqttNet", "before destroy with no params." + System.currentTimeMillis());
            destroy(30000L, null, null);
            C0969a.m393a("MqttNet", "after destroy with no params." + System.currentTimeMillis());
        } catch (MqttException e) {
            C0969a.m395c("MqttNet", "destroy exception=" + e);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void destroy(long j, Object obj, final IMqttActionListener iMqttActionListener) {
        C0969a.m393a("MqttNet", "destroyP() called with: quiesceTimeout = [" + j + "], userContext = [" + obj + "], callback = [" + iMqttActionListener + "] " + hashCode());
        this.f922g.set(false);
        this.f923h.set(false);
        if (m433c() == null) {
            C0969a.m393a("MqttNet", "destroyP(), client is null");
            m426a("onClientNull -> closeConnect callback=" + iMqttActionListener);
            if (iMqttActionListener != null) {
                iMqttActionListener.onFailure(null, new MqttThrowable("initialize first"));
                return;
            }
            return;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            C0969a.m393a("MqttNet", "destroyP->disconnect");
            this.f918c.disconnect(j, obj, new IMqttActionListener() { // from class: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.b.1
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    C0969a.m393a("MqttNet", "onSuccess() called with: iMqttToken = [" + iMqttToken + "], callback = [" + iMqttActionListener + "]");
                    C0978b.this.m426a("onSuccess -> closeConnect callback = " + iMqttActionListener + ", hasCallback = " + atomicBoolean);
                    if (iMqttActionListener != null && atomicBoolean.compareAndSet(false, true)) {
                        atomicBoolean.set(true);
                        iMqttActionListener.onSuccess(iMqttToken);
                    }
                    CountDownLatch countDownLatch2 = countDownLatch;
                    if (countDownLatch2 != null) {
                        countDownLatch2.countDown();
                    }
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    C0969a.m393a("MqttNet", "onFailure() called with: iMqttToken = [" + iMqttToken + "], throwable = [" + th + "], callback = [" + iMqttActionListener + "]");
                    C0978b c0978b = C0978b.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onFailure -> closeConnect callback = ");
                    sb.append(iMqttActionListener);
                    sb.append(", hasCallback = ");
                    sb.append(atomicBoolean);
                    c0978b.m426a(sb.toString());
                    if (iMqttActionListener != null && atomicBoolean.compareAndSet(false, true)) {
                        iMqttActionListener.onFailure(iMqttToken, th);
                    }
                    CountDownLatch countDownLatch2 = countDownLatch;
                    if (countDownLatch2 != null) {
                        countDownLatch2.countDown();
                    }
                }
            });
            C0969a.m393a("MqttNet", "destroyP->disconnected");
        } catch (Exception e) {
            C0969a.m393a("MqttNet", "destroyP(), error, e = " + e.toString());
            e.printStackTrace();
            m426a("onFailure exception -> closeConnect callback=" + iMqttActionListener + ", hasCallback = " + atomicBoolean);
            if (iMqttActionListener != null && atomicBoolean.compareAndSet(false, true)) {
                iMqttActionListener.onFailure(null, e);
            }
            countDownLatch.countDown();
        }
        try {
            this.f924i = PersistentConnectState.DISCONNECTED;
            this.f919d = null;
            this.f926k = null;
            C0969a.m395c("MqttNet", "connection lost disconnect by user.");
            PersistentEventDispatcher.getInstance().broadcastMessage(2, null, null, 0, "disconnect success");
        } catch (Exception e2) {
            C0969a.m395c("MqttNet", "destroyP(), internal error, e = " + e2.toString());
            e2.printStackTrace();
        }
        try {
            if (this.f917b != null) {
                this.f917b.close();
            }
        } catch (Exception unused) {
        }
        this.f917b = null;
        try {
            if (this.f921f != null) {
                this.f921f.close();
            }
        } catch (Exception unused2) {
        }
        this.f921f = null;
        try {
            countDownLatch.await(30L, TimeUnit.SECONDS);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        if (iMqttActionListener != null && atomicBoolean.compareAndSet(false, true)) {
            m426a("onSuccess -> closeConnect callback = " + iMqttActionListener + ", hasCallback = " + atomicBoolean);
            iMqttActionListener.onSuccess(null);
        }
        C0969a.m394b("MqttNet", "mqtt disconnect finished. " + hashCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m426a(String str) {
        try {
            C0969a.m393a("MqttNet", "closeConnect " + str);
            if (this.f918c != null) {
                this.f918c.close();
            }
        } catch (Exception e) {
            C0969a.m395c("MqttNet", "closeConnect e = " + e.toString());
            e.printStackTrace();
        }
        this.f918c = null;
    }

    /* renamed from: b */
    public PersistentInitParams m432b() {
        return this.f926k;
    }

    /* renamed from: a */
    public void m431a(PersistentConnectState persistentConnectState) {
        this.f924i = persistentConnectState;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public PersistentConnectState getConnectState() {
        C0969a.m393a("MqttNet", "getConnectState()");
        if (m433c() == null) {
            C0969a.m393a("MqttNet", "getConnectState() client is empty");
            this.f924i = PersistentConnectState.DISCONNECTED;
        } else {
            C0969a.m393a("MqttNet", "getConnectState() paho state = " + m421a().m434d());
            this.f924i = m421a().m434d() ? PersistentConnectState.CONNECTED : PersistentConnectState.DISCONNECTED;
        }
        return this.f924i;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.base.INet
    public ASend asyncSend(ARequest aRequest, IOnCallListener iOnCallListener) {
        C0980b c0980b = new C0980b(aRequest, iOnCallListener);
        new C0981c().asyncSend(c0980b);
        return c0980b;
    }

    @Override // com.aliyun.alink.linksdk.channel.core.base.INet
    public void retry(ASend aSend) {
        new C0981c().asyncSend(aSend);
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void subscribe(String str, IOnSubscribeListener iOnSubscribeListener) {
        if (TextUtils.isEmpty(str)) {
            C0969a.m393a("MqttNet", "subscribe, topic is empty");
            return;
        }
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        mqttSubscribeRequest.isSubscribe = true;
        new C0981c().asyncSend(new C0980b(mqttSubscribeRequest, iOnSubscribeListener));
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void subscribe(String str, PersisitentNetParams persisitentNetParams, IOnSubscribeListener iOnSubscribeListener) {
        if (TextUtils.isEmpty(str)) {
            C0969a.m393a("MqttNet", "subscribe, topic is empty");
            return;
        }
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        mqttSubscribeRequest.isSubscribe = true;
        if (persisitentNetParams != null && (persisitentNetParams instanceof MqttSubscribeRequestParams)) {
            mqttSubscribeRequest.subscribeRequestParams = (MqttSubscribeRequestParams) persisitentNetParams;
        }
        new C0981c().asyncSend(new C0980b(mqttSubscribeRequest, iOnSubscribeListener));
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void unSubscribe(String str, IOnSubscribeListener iOnSubscribeListener) {
        if (TextUtils.isEmpty(str)) {
            C0969a.m393a("MqttNet", "unSubscribe, topic is empty");
            return;
        }
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        mqttSubscribeRequest.isSubscribe = false;
        new C0981c().asyncSend(new C0980b(mqttSubscribeRequest, iOnSubscribeListener));
    }

    @Override // com.aliyun.alink.linksdk.channel.core.persistent.IPersisitentNet
    public void subscribeRrpc(String str, final IOnSubscribeRrpcListener iOnSubscribeRrpcListener) {
        C0969a.m393a("MqttNet", "subscribeRrpc(),topic = " + str);
        if (TextUtils.isEmpty(str) || iOnSubscribeRrpcListener == null) {
            C0969a.m393a("MqttNet", "subscribeRrpc(), params error");
            return;
        }
        subscribe(str, new IOnSubscribeListener() { // from class: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.b.2
            @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
            public void onSuccess(String str2) {
                iOnSubscribeRrpcListener.onSubscribeSuccess(str2);
            }

            @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
            public void onFailed(String str2, AError aError) {
                iOnSubscribeRrpcListener.onSubscribeFailed(str2, aError);
            }

            @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
            public boolean needUISafety() {
                return iOnSubscribeRrpcListener.needUISafety();
            }
        });
        if (this.f925j != null) {
            C0969a.m393a("MqttNet", "subscribeRrpc(), registerRrpcListener");
            this.f925j.m407a(str, iOnSubscribeRrpcListener);
        }
    }

    /* renamed from: c */
    public IMqttAsyncClient m433c() {
        return this.f918c;
    }

    /* renamed from: d */
    public boolean m434d() {
        try {
            if (this.f918c != null) {
                return this.f918c.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: e */
    public Context m435e() {
        return this.f916a;
    }

    /* renamed from: f */
    private void m429f() {
        String str;
        String str2;
        this.f917b = new MemoryPersistence();
        String str3 = System.currentTimeMillis() + "";
        String str4 = MqttConfigure.mqttHost;
        if (TextUtils.isEmpty(MqttConfigure.mqttHost)) {
            str4 = MqttConfigure.SECURE_MODE == 8 ? MqttConfigure.DEFAULT_ITLS_HOST : MqttConfigure.DEFAULT_HOST;
        }
        if (str4.contains("${productKey}")) {
            str4 = str4.replace("${productKey}", MqttConfigure.productKey);
        }
        if (MqttConfigure.SECURE_MODE == 3 && !str4.startsWith("tcp://")) {
            str4 = "tcp://" + str4;
        } else if (MqttConfigure.SECURE_MODE != 3 && !str4.startsWith("ssl://")) {
            str4 = "ssl://" + str4;
        }
        String str5 = MqttConfigure.clientId;
        if (TextUtils.isEmpty(str5)) {
            str5 = MqttConfigure.deviceName + "&" + MqttConfigure.productKey;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("productKey", MqttConfigure.productKey);
        hashMap.put("deviceName", MqttConfigure.deviceName);
        hashMap.put(TmpConstant.KEY_CLIENT_ID, str5);
        hashMap.put("timestamp", str3);
        StringBuilder sb = new StringBuilder();
        sb.append(str5);
        sb.append("|securemode=");
        sb.append(MqttConfigure.SECURE_MODE);
        sb.append(",_v=");
        sb.append(PersistentNet.getInstance().getSDKVersion());
        sb.append(",lan=Android");
        sb.append(",os=");
        sb.append(Build.VERSION.RELEASE);
        sb.append(",signmethod=");
        sb.append(MqttConfigure.SIGN_METHOD);
        sb.append(MqttConfigure.SECURE_MODE == 8 ? ",authtype=id2" : "");
        sb.append(",ext=1");
        sb.append(",timestamp=");
        sb.append(str3);
        sb.append("|");
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(MqttConfigure.deviceSecret)) {
            str = MqttConfigure.deviceName + "&" + MqttConfigure.productKey;
            str2 = m422a(hashMap, MqttConfigure.deviceSecret);
        } else {
            str = MqttConfigure.mqttUserName;
            str2 = MqttConfigure.mqttPassWord;
            sb2 = MqttConfigure.mqttClientId;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.f922g.set(false);
            this.f923h.set(false);
            this.f924i = PersistentConnectState.CONNECTFAIL;
            PersistentEventDispatcher.getInstance().broadcastMessage(7, null, null, 4201, "create mqtt client error empty username or password");
            return;
        }
        try {
            if (MqttConfigure.pingSender != null) {
                C0969a.m393a("MqttNet", "use user define timer ping sender.");
                this.f918c = new C0968e(str4, sb2, this.f917b, MqttConfigure.pingSender);
            } else {
                C0969a.m393a("MqttNet", "use java timer ping sender.");
                this.f918c = new C0968e(str4, sb2, this.f917b);
            }
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            this.f920e = mqttConnectOptions;
            mqttConnectOptions.setMqttVersion(4);
            if (MqttConfigure.SECURE_MODE == 2 && MqttConfigure.isCheckRootCrt) {
                try {
                    SSLSocketFactory m430g = m430g();
                    this.f919d = m430g;
                    this.f920e.setSocketFactory(m430g);
                } catch (Exception e) {
                    C0969a.m396d("MqttNet", "create SSL Socket error" + e.toString());
                    e.printStackTrace();
                }
            }
            this.f920e.setAutomaticReconnect(MqttConfigure.automaticReconnect);
            this.f920e.setCleanSession(MqttConfigure.cleanSession);
            this.f920e.setUserName(str);
            this.f920e.setPassword(str2.toCharArray());
            this.f920e.setKeepAliveInterval(MqttConfigure.getKeepAliveInterval());
            this.f920e.setMaxInflight(MqttConfigure.maxInflight);
            C0973a c0973a = new C0973a();
            this.f925j = c0973a;
            this.f918c.setCallback(c0973a);
            final HashMap hashMap2 = new HashMap();
            hashMap2.put("startTime-connect", String.valueOf(System.currentTimeMillis()));
            try {
                this.f924i = PersistentConnectState.CONNECTING;
                this.f918c.connect(this.f920e, null, new IMqttActionListener() { // from class: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.b.3
                    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                    public void onSuccess(IMqttToken iMqttToken) {
                        C0969a.m394b("MqttNet", "mqtt connect onSuccess");
                        if (C0978b.this.f922g.compareAndSet(true, false)) {
                            C0978b.this.f923h.set(true);
                            C0978b.this.f924i = PersistentConnectState.CONNECTED;
                            hashMap2.put("endTime-connect", String.valueOf(System.currentTimeMillis()));
                            hashMap2.put(SpeechUtility.TAG_RESOURCE_RESULT, "1");
                            if (C0978b.this.f927l.compareAndSet(false, true)) {
                                C0977d.m419a("mqtt-connect", hashMap2);
                            }
                        }
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                    public void onFailure(IMqttToken iMqttToken, Throwable th) {
                        C0969a.m396d("MqttNet", "mqtt connect onFailure, exce = " + th.toString());
                        C0978b.this.f922g.set(false);
                        C0978b.this.f923h.set(false);
                        C0978b.this.f924i = PersistentConnectState.CONNECTFAIL;
                        hashMap2.put("endTime-connect", String.valueOf(System.currentTimeMillis()));
                        hashMap2.put(SpeechUtility.TAG_RESOURCE_RESULT, "0");
                        if (th instanceof MqttException) {
                            MqttException mqttException = (MqttException) th;
                            hashMap2.put(AUserTrack.UTKEY_ERROR_CODE, String.valueOf(mqttException.getReasonCode()));
                            PersistentEventDispatcher.getInstance().broadcastMessage(7, null, null, mqttException.getReasonCode(), mqttException.toString());
                        } else {
                            PersistentEventDispatcher.getInstance().broadcastMessage(7, null, null, 4201, th.toString());
                            hashMap2.put(AUserTrack.UTKEY_ERROR_CODE, String.valueOf(4201));
                        }
                        if (C0978b.this.f927l.compareAndSet(false, true)) {
                            C0977d.m419a("mqtt-connect", hashMap2);
                        }
                    }
                });
                C0969a.m394b("MqttNet", "mqtt client connect..," + str4);
            } catch (MqttException e2) {
                C0969a.m396d("MqttNet", " mqtt client connect error,e" + e2.toString());
                e2.printStackTrace();
                this.f922g.set(false);
                this.f923h.set(false);
                this.f924i = PersistentConnectState.CONNECTFAIL;
                PersistentEventDispatcher.getInstance().broadcastMessage(7, null, null, e2.getReasonCode(), e2.toString());
            } catch (Exception e3) {
                C0969a.m396d("MqttNet", " mqtt client connect error,e" + e3.toString());
                e3.printStackTrace();
                this.f922g.set(false);
                this.f923h.set(false);
                this.f924i = PersistentConnectState.CONNECTFAIL;
                PersistentEventDispatcher.getInstance().broadcastMessage(7, null, null, 4201, e3.toString());
            }
        } catch (Exception e4) {
            C0969a.m396d("MqttNet", "create mqtt client error,e" + e4.toString());
            e4.printStackTrace();
            this.f923h.set(false);
            this.f922g.set(false);
            this.f924i = PersistentConnectState.CONNECTFAIL;
            PersistentEventDispatcher.getInstance().broadcastMessage(7, null, null, 4201, "create mqtt client error,e" + e4.toString());
        }
    }

    /* renamed from: g */
    private SSLSocketFactory m430g() {
        SSLContext sSLContext = SSLContext.getInstance("TLSV1.2");
        sSLContext.init(null, new TrustManager[]{new C0976c(this.f921f)}, null);
        return sSLContext.getSocketFactory();
    }

    /* renamed from: a */
    private String m422a(Map<String, String> map, String str) {
        if (map != null && !TextUtils.isEmpty(str)) {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            Arrays.sort(strArr);
            StringBuilder sb = new StringBuilder();
            for (String str2 : strArr) {
                if (!TmpConstant.KEY_SIGN_VALUE.equalsIgnoreCase(str2)) {
                    sb.append(str2);
                    sb.append(map.get(str2));
                }
            }
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("utf-8"), MqttConfigure.SIGN_METHOD);
                Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
                mac.init(secretKeySpec);
                return m423a(mac.doFinal(sb.toString().getBytes("utf-8")));
            } catch (Exception e) {
                C0969a.m396d("MqttNet", "hmacSign error, e" + e.toString());
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static final String m423a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }
}
