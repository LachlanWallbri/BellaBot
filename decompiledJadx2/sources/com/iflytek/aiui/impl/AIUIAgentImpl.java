package com.iflytek.aiui.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.aiui.Version;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.aiui.data.audio.player.C3539a;
import com.iflytek.aiui.jni.AIUI;
import com.iflytek.aiui.pro.AbstractC3577ag;
import com.iflytek.aiui.pro.AbstractC3617h0;
import com.iflytek.aiui.pro.C3579ai;
import com.iflytek.aiui.pro.C3582al;
import com.iflytek.aiui.pro.C3583am;
import com.iflytek.aiui.pro.C3584an;
import com.iflytek.aiui.pro.C3585ao;
import com.iflytek.aiui.pro.C3589as;
import com.iflytek.aiui.pro.C3590at;
import com.iflytek.aiui.pro.C3592av;
import com.iflytek.aiui.pro.C3594ax;
import com.iflytek.aiui.pro.C3602bc;
import com.iflytek.aiui.pro.C3615g0;
import com.iflytek.aiui.pro.C3627m0;
import com.iflytek.aiui.pro.C3633p0;
import com.iflytek.aiui.pro.C3635q0;
import com.iflytek.aiui.pro.C3649x0;
import com.iflytek.aiui.pro.C3651y0;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.msc.util.Config;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class AIUIAgentImpl {

    /* renamed from: a */
    private Context f2197a;

    /* renamed from: b */
    private AbstractC3577ag f2198b;

    /* renamed from: f */
    private AIUIListener f2202f;

    /* renamed from: g */
    private C3583am f2203g;

    /* renamed from: c */
    private String f2199c = AIUIConstant.AUDIO_CAPTOR_ALSA;

    /* renamed from: d */
    private String f2200d = "sdk";

    /* renamed from: e */
    private String f2201e = AIUIConstant.INTERACT_MODE_CONTINUOUS;

    /* renamed from: h */
    private long f2204h = 0;

    /* renamed from: i */
    private Handler f2205i = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.aiui.impl.AIUIAgentImpl.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            AIUIEvent aIUIEvent = (AIUIEvent) message.obj;
            AIUIAgentImpl.this.m839a(aIUIEvent);
            if (AIUIAgentImpl.this.f2202f != null) {
                AIUIAgentImpl.this.f2202f.onEvent(aIUIEvent);
            }
        }
    };

    /* renamed from: j */
    private AbstractC3577ag.a f2206j = new AbstractC3577ag.a() { // from class: com.iflytek.aiui.impl.AIUIAgentImpl.3
        @Override // com.iflytek.aiui.pro.AbstractC3577ag.a
        /* renamed from: a */
        public void mo856a() {
            Message.obtain(AIUIAgentImpl.this.f2205i, 1, new AIUIEvent(11, 0, 0, "", null)).sendToTarget();
        }

        @Override // com.iflytek.aiui.pro.AbstractC3577ag.a
        /* renamed from: a */
        public void mo857a(int i, String str) {
            Message.obtain(AIUIAgentImpl.this.f2205i, 1, new AIUIEvent(2, i, 0, str, null)).sendToTarget();
        }

        @Override // com.iflytek.aiui.pro.AbstractC3577ag.a
        /* renamed from: a */
        public void mo858a(byte[] bArr, int i, Bundle bundle) {
            AIUI.sendMessage(2, 0, 0, "data_type=audio,sample_rate=16000", bArr);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3577ag.a
        /* renamed from: b */
        public void mo859b() {
            Message.obtain(AIUIAgentImpl.this.f2205i, 1, new AIUIEvent(12, 0, 0, "", null)).sendToTarget();
            AIUI.sendMessage(3, 0, 0, "data_type=audio", null);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3577ag.a
        /* renamed from: c */
        public void mo860c() {
        }
    };

    /* renamed from: com.iflytek.aiui.impl.AIUIAgentImpl$a */
    /* loaded from: classes4.dex */
    class HandlerC3562a extends Handler {
        HandlerC3562a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            AIUIEvent aIUIEvent = (AIUIEvent) message.obj;
            AIUIAgentImpl.b(AIUIAgentImpl.this, aIUIEvent);
            if (AIUIAgentImpl.this.f2205i != null) {
                AIUIAgentImpl.this.f2205i.onEvent(aIUIEvent);
            }
        }
    }

    /* renamed from: com.iflytek.aiui.impl.AIUIAgentImpl$b */
    /* loaded from: classes4.dex */
    class RunnableC3563b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f2211a;

        /* renamed from: b */
        final /* synthetic */ AIUIListener f2212b;

        RunnableC3563b(String str, AIUIListener aIUIListener) {
            this.f2211a = str;
            this.f2212b = aIUIListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3633p0.m1483l(this.f2211a);
            C3651y0.m1628j(C3633p0.m1477f("log", "debug_log", "0").equals("1"));
            if (AIUIAgentImpl.l(AIUIAgentImpl.this) == null) {
                AIUIAgentImpl aIUIAgentImpl = AIUIAgentImpl.this;
                AIUIAgentImpl.m(aIUIAgentImpl, new C3635q0(aIUIAgentImpl));
            }
            if (AIUIAgentImpl.n(AIUIAgentImpl.this) == null) {
                AIUIAgentImpl.o(AIUIAgentImpl.this, new C3539a());
            }
            AIUIAgentImpl.p(AIUIAgentImpl.this);
            AIUIAgentImpl.q(AIUIAgentImpl.this);
            AIUIAgentImpl.r(AIUIAgentImpl.this);
            C3615g0.m1345c(AIUIAgentImpl.s(AIUIAgentImpl.this));
            AIUIAgentImpl.e(AIUIAgentImpl.this, this.f2212b);
            String m1479h = C3633p0.m1479h(AIUIAgentImpl.s(AIUIAgentImpl.this), this.f2211a);
            AIUIAgentImpl aIUIAgentImpl2 = AIUIAgentImpl.this;
            AIUIAgentImpl.u(aIUIAgentImpl2, AIUI.createAgent(AIUIAgentImpl.s(aIUIAgentImpl2), m1479h, AIUIAgentImpl.this, "onEvent"));
            if (0 == AIUIAgentImpl.t(AIUIAgentImpl.this)) {
                C3651y0.m1622d("AIUIAgentImpl", "create native agent failed.");
            }
        }
    }

    /* renamed from: com.iflytek.aiui.impl.AIUIAgentImpl$c */
    /* loaded from: classes4.dex */
    class RunnableC3564c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ AIUIMessage f2214a;

        RunnableC3564c(AIUIMessage aIUIMessage) {
            this.f2214a = aIUIMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            AIUIMessage aIUIMessage = this.f2214a;
            if (aIUIMessage != null) {
                int i = aIUIMessage.msgType;
                if (i == 6) {
                    AIUIAgentImpl.f(AIUIAgentImpl.this);
                } else if (i == 10) {
                    aIUIMessage.params = C3633p0.m1479h(AIUIAgentImpl.s(AIUIAgentImpl.this), this.f2214a.params);
                    C3633p0.m1483l(this.f2214a.params);
                    if (AIUIAgentImpl.l(AIUIAgentImpl.this) != null) {
                        AIUIAgentImpl.l(AIUIAgentImpl.this).m1498k();
                    }
                } else {
                    if (i == 31) {
                        AIUIAgentImpl.l(AIUIAgentImpl.this).m1499n();
                        return;
                    }
                    if (i == 18) {
                        AIUIAgentImpl.n(AIUIAgentImpl.this).m810h();
                    } else if (i == 19) {
                        AIUIAgentImpl.n(AIUIAgentImpl.this).m811j();
                    } else if (i != 22) {
                        if (i == 23) {
                            C3651y0.m1620b("AIUIAgentImpl", "CMD_STOP_RECORD");
                            if (AIUIAgentImpl.d(AIUIAgentImpl.this, this.f2214a)) {
                                return;
                            }
                        }
                    } else if (AIUIAgentImpl.v(AIUIAgentImpl.this, aIUIMessage)) {
                        return;
                    }
                }
                if (27 == this.f2214a.msgType && AIUIAgentImpl.l(AIUIAgentImpl.this) != null) {
                    AIUIAgentImpl.l(AIUIAgentImpl.this).m1497i(this.f2214a);
                    AIUIMessage aIUIMessage2 = this.f2214a;
                    if (aIUIMessage2.arg1 == 1) {
                        aIUIMessage2.params = AIUIAgentImpl.l(AIUIAgentImpl.this).m1495c(this.f2214a.params);
                    }
                }
                AIUIMessage aIUIMessage3 = this.f2214a;
                AIUI.sendMessage(aIUIMessage3.msgType, aIUIMessage3.arg1, aIUIMessage3.arg2, aIUIMessage3.params, aIUIMessage3.data);
            }
        }
    }

    /* renamed from: com.iflytek.aiui.impl.AIUIAgentImpl$d */
    /* loaded from: classes4.dex */
    class RunnableC3565d implements Runnable {
        RunnableC3565d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AIUIAgentImpl.g(AIUIAgentImpl.this);
            AIUIAgentImpl.h(AIUIAgentImpl.this);
            if (AIUIAgentImpl.l(AIUIAgentImpl.this) != null) {
                AIUIAgentImpl.l(AIUIAgentImpl.this).m1494b();
            }
            if (0 != AIUIAgentImpl.t(AIUIAgentImpl.this)) {
                AIUI.destroyAgent();
                AIUIAgentImpl.u(AIUIAgentImpl.this, 0L);
            }
            AIUIAgentImpl.i(AIUIAgentImpl.this).quit();
        }
    }

    /* renamed from: com.iflytek.aiui.impl.AIUIAgentImpl$e */
    /* loaded from: classes4.dex */
    class C3566e implements AbstractC3617h0.a {
        C3566e() {
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: a */
        public void mo861a(int i, String str) {
            Message.obtain(AIUIAgentImpl.j(AIUIAgentImpl.this), 1, new AIUIEvent(2, i, 0, str, null)).sendToTarget();
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: b */
        public void mo862b() {
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: d */
        public void mo863d(byte[] bArr, int i, Bundle bundle) {
            AIUI.sendMessage(2, 0, 0, "data_type=audio,sample_rate=16000", bArr);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: e */
        public void mo864e() {
            Message.obtain(AIUIAgentImpl.j(AIUIAgentImpl.this), 1, new AIUIEvent(12, 0, 0, "", null)).sendToTarget();
            AIUI.sendMessage(3, 0, 0, "data_type=audio", null);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: g */
        public void mo865g() {
            Message.obtain(AIUIAgentImpl.j(AIUIAgentImpl.this), 1, new AIUIEvent(11, 0, 0, "", null)).sendToTarget();
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: h */
        public void mo866h(int i, byte[] bArr, int i2, Bundle bundle) {
            AIUI.sendMessage(2, i, 0, "data_type=audio,sample_rate=16000", C3627m0.m1405b(C3633p0.m1477f("recorder", "converter", ""), bArr));
        }
    }

    /* renamed from: com.iflytek.aiui.impl.AIUIAgentImpl$f */
    /* loaded from: classes4.dex */
    class C3567f implements AbstractC3617h0.a {
        C3567f() {
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: a */
        public void mo861a(int i, String str) {
            Message.obtain(AIUIAgentImpl.j(AIUIAgentImpl.this), 1, new AIUIEvent(2, i, 0, str, null)).sendToTarget();
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: b */
        public void mo862b() {
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: d */
        public void mo863d(byte[] bArr, int i, Bundle bundle) {
            C3649x0 k = AIUIAgentImpl.k(AIUIAgentImpl.this);
            if (k != null) {
                k.m1613d(bArr, bundle);
            }
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: e */
        public void mo864e() {
            Message.obtain(AIUIAgentImpl.j(AIUIAgentImpl.this), 1, new AIUIEvent(12, 0, 0, "", null)).sendToTarget();
            AIUI.sendMessage(3, 0, 0, "data_type=audio", null);
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: g */
        public void mo865g() {
            Message.obtain(AIUIAgentImpl.j(AIUIAgentImpl.this), 1, new AIUIEvent(11, 0, 0, "", null)).sendToTarget();
        }

        @Override // com.iflytek.aiui.pro.AbstractC3617h0.a
        /* renamed from: h */
        public void mo866h(int i, byte[] bArr, int i2, Bundle bundle) {
            mo863d(bArr, i2, bundle);
        }
    }

    public AIUIAgentImpl(Context context) {
        this.f2197a = context;
    }

    /* renamed from: a */
    private Bundle m837a(HashMap<String, AIUI.DataItem> hashMap) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, AIUI.DataItem> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            AIUI.DataItem value = entry.getValue();
            int i = value.type;
            if (i == 1) {
                bundle.putInt(key, C3585ao.m1050a(value.data, 0));
            } else if (i == 2) {
                bundle.putLong(key, C3585ao.m1050a(value.data, 0));
            } else if (i != 3) {
                bundle.putByteArray(key, value.data);
            } else {
                bundle.putString(key, new String(value.data));
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m839a(AIUIEvent aIUIEvent) {
        if (aIUIEvent.eventType == 6 && 2 == aIUIEvent.arg1 && AIUIConstant.INTERACT_MODE_ONESHOT.equals(this.f2201e) && "off".equals(C3582al.m1025a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_WAKEUP_MODE, "off"))) {
            m849g();
        }
    }

    /* renamed from: b */
    private void m842b(AIUIMessage aIUIMessage) {
        synchronized (this) {
            if (InternalConstant.DTYPE_AUDIO.equals(C3582al.m1032d(aIUIMessage.params).m1015a("data_type")) && this.f2198b != null) {
                this.f2198b.mo980a();
            }
        }
    }

    /* renamed from: c */
    private void m844c() {
        AIUI.m869b();
        if (!AIUI.m868a()) {
            C3589as.m1063b("AIUIAgentImpl", "Load native library failed.");
            return;
        }
        AIUI.setVersionType(Version.getVersionType().ordinal(), this);
        AIUI.setAIUIDir(AIUISetting.getAIUIDir());
        AIUI.setMscCfg(AIUISetting.getMscCfg());
        AIUI.setDataLogDir(AIUISetting.getDataLogDir());
        AIUI.setSaveDataLog(AIUISetting.getSaveDataLog());
        AIUI.setLogLevel(AIUISetting.getLogLevel().ordinal());
        AIUI.setNetLogLevel(AIUISetting.getNetLogLevel().ordinal());
        m846d();
    }

    /* renamed from: c */
    private void m845c(AIUIMessage aIUIMessage) {
        synchronized (this) {
            if (InternalConstant.DTYPE_AUDIO.equals(C3582al.m1032d(aIUIMessage.params).m1015a("data_type"))) {
                m849g();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.iflytek.aiui.impl.AIUIAgentImpl$2] */
    /* renamed from: d */
    private void m846d() {
        for (Map.Entry<String, String> entry : C3594ax.m1106a(this.f2197a).m1021c().entrySet()) {
            AIUI.setSystemInfo(entry.getKey(), entry.getValue(), this);
        }
        AIUI.setSystemInfo("unique_id", C3592av.m1086b(this.f2197a), this);
        new Thread() { // from class: com.iflytek.aiui.impl.AIUIAgentImpl.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    AIUI.setSystemInfo("heart_info", C3602bc.m1167b(AIUIAgentImpl.this.f2197a), AIUIAgentImpl.this);
                } catch (Throwable th) {
                    C3589as.m1062b("get Heart Info Error, Don't Panic");
                    th.printStackTrace();
                }
            }
        }.start();
        setNetType_cc();
        setGpsPos_cc();
    }

    /* renamed from: e */
    private void m847e() {
        if (AIUIConstant.AUDIO_CAPTOR_SYSTEM.equals(this.f2199c) && "sdk".equals(this.f2200d)) {
            this.f2198b = new C3579ai(this.f2206j);
        }
    }

    /* renamed from: f */
    private void m848f() {
        String str;
        this.f2200d = C3582al.m1025a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_DATA_SOURCE, "sdk");
        if (Version.getVersionType() == Version.VersionType.MOBILE_PHONE) {
            this.f2199c = C3582al.m1025a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_AUDIO_CAPTOR, AIUIConstant.AUDIO_CAPTOR_SYSTEM);
            str = AIUIConstant.INTERACT_MODE_ONESHOT;
        } else {
            this.f2199c = C3582al.m1025a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_AUDIO_CAPTOR, AIUIConstant.AUDIO_CAPTOR_ALSA);
            str = AIUIConstant.INTERACT_MODE_CONTINUOUS;
        }
        this.f2201e = C3582al.m1025a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTERACT_MODE, str);
    }

    /* renamed from: g */
    private void m849g() {
        AbstractC3577ag abstractC3577ag = this.f2198b;
        if (abstractC3577ag != null) {
            abstractC3577ag.mo981b();
        }
    }

    /* renamed from: h */
    private void m850h() {
        synchronized (this) {
            if (this.f2198b != null) {
                this.f2198b.mo982c();
            }
        }
    }

    private void onEvent(int i, int i2, int i3, String str, Object obj) {
        C3583am c3583am;
        if (this.f2202f != null) {
            Bundle m837a = obj != null ? m837a((HashMap<String, AIUI.DataItem>) obj) : new Bundle();
            AIUIEvent aIUIEvent = new AIUIEvent(i, i2, i3, str, m837a);
            if (9 == i) {
                this.f2202f.onEvent(aIUIEvent);
                return;
            }
            if (1 == i) {
                if ("tts".equals(m837a.getString("sub"))) {
                    C3583am c3583am2 = this.f2203g;
                    if (c3583am2 != null) {
                        c3583am2.m1040a(aIUIEvent);
                        return;
                    }
                    return;
                }
            } else if (2 == i && (c3583am = this.f2203g) != null) {
                c3583am.m1040a(aIUIEvent);
            }
            Message.obtain(this.f2205i, 1, aIUIEvent).sendToTarget();
        }
    }

    private void setNetType_cc() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f2197a.getSystemService("connectivity")).getActiveNetworkInfo();
        String m1069a = C3590at.m1069a(activeNetworkInfo);
        String m1070b = C3590at.m1070b(activeNetworkInfo);
        AIUI.setSystemInfo(SpeechConstant.NET_TYPE, m1069a, this);
        AIUI.setSystemInfo("net_subtype", m1070b, this);
    }

    /* renamed from: a */
    public int m851a(String str, AIUIListener aIUIListener) {
        C3582al.m1030b(str);
        String m1024a = C3582al.m1024a(this.f2197a, str);
        if (this.f2203g == null) {
            this.f2203g = new C3583am(this);
        }
        m848f();
        m847e();
        m844c();
        this.f2202f = aIUIListener;
        long createAgent = AIUI.createAgent(this.f2197a, m1024a, this, "onEvent");
        this.f2204h = createAgent;
        if (0 != createAgent) {
            return 0;
        }
        C3589as.m1063b("AIUIAgentImpl", "create native agent failed.");
        return -1;
    }

    /* renamed from: a */
    public void m852a() {
        m850h();
        C3583am c3583am = this.f2203g;
        if (c3583am != null) {
            c3583am.m1043c();
        }
        if (0 != this.f2204h) {
            AIUI.destroyAgent();
            this.f2204h = 0L;
        }
    }

    /* renamed from: a */
    public void m853a(AIUIEvent aIUIEvent, boolean z) {
        if (!z) {
            Message.obtain(this.f2205i, 1, aIUIEvent).sendToTarget();
            return;
        }
        AIUIListener aIUIListener = this.f2202f;
        if (aIUIListener != null) {
            aIUIListener.onEvent(aIUIEvent);
        }
    }

    /* renamed from: a */
    public void m854a(AIUIMessage aIUIMessage) {
        if (aIUIMessage != null) {
            int i = aIUIMessage.msgType;
            if (i == 6) {
                m849g();
            } else if (i == 10) {
                aIUIMessage.params = C3582al.m1024a(this.f2197a, aIUIMessage.params);
                C3582al.m1030b(aIUIMessage.params);
                C3583am c3583am = this.f2203g;
                if (c3583am != null) {
                    c3583am.m1039a();
                }
            } else if (i == 22) {
                m842b(aIUIMessage);
            } else if (i == 23) {
                m845c(aIUIMessage);
            }
            if (27 == aIUIMessage.msgType) {
                if (this.f2203g.m1042b()) {
                    return;
                }
                C3583am c3583am2 = this.f2203g;
                if (c3583am2 != null) {
                    c3583am2.m1041a(aIUIMessage);
                    if (aIUIMessage.arg1 == 1) {
                        aIUIMessage.params = this.f2203g.m1038a(aIUIMessage.params);
                    }
                }
            }
            AIUI.sendMessage(aIUIMessage.msgType, aIUIMessage.arg1, aIUIMessage.arg2, aIUIMessage.params, aIUIMessage.data);
        }
    }

    /* renamed from: b */
    public Context m855b() {
        return this.f2197a;
    }

    protected void setGpsPos_cc() {
        C3584an m1046a = C3584an.m1046a(this.f2197a);
        AIUI.setGpsPos(m1046a.m1048a(Config.KEY_LONGITUDE), m1046a.m1048a(Config.KEY_LATITUDE));
    }
}
