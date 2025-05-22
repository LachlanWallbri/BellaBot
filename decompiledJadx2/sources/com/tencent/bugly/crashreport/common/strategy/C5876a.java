package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import com.iflytek.speech.UtilityConfig;
import com.tencent.bugly.AbstractC5864a;
import com.tencent.bugly.crashreport.biz.C5871b;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.proguard.C5915as;
import com.tencent.bugly.proguard.C5932p;
import com.tencent.bugly.proguard.C5934r;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import com.tencent.bugly.proguard.InterfaceC5931o;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.common.strategy.a */
/* loaded from: classes7.dex */
public final class C5876a {

    /* renamed from: a */
    public static int f7809a = 1000;

    /* renamed from: b */
    private static C5876a f7810b;

    /* renamed from: h */
    private static String f7811h;

    /* renamed from: c */
    private final List<AbstractC5864a> f7812c;

    /* renamed from: g */
    private Context f7816g;

    /* renamed from: f */
    private StrategyBean f7815f = null;

    /* renamed from: e */
    private final StrategyBean f7814e = new StrategyBean();

    /* renamed from: d */
    private final C5939w f7813d = C5939w.m3810a();

    private C5876a(Context context, List<AbstractC5864a> list) {
        this.f7816g = context;
        this.f7812c = list;
    }

    /* renamed from: a */
    public static synchronized C5876a m3488a(Context context, List<AbstractC5864a> list) {
        C5876a c5876a;
        synchronized (C5876a.class) {
            if (f7810b == null) {
                f7810b = new C5876a(context, list);
            }
            c5876a = f7810b;
        }
        return c5876a;
    }

    /* renamed from: a */
    public final void m3493a(long j) {
        this.f7813d.m3813a(new Thread() { // from class: com.tencent.bugly.crashreport.common.strategy.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                try {
                    Map<String, byte[]> m3758a = C5932p.m3740a().m3758a(C5876a.f7809a, (InterfaceC5931o) null, true);
                    if (m3758a != null) {
                        byte[] bArr = m3758a.get(UtilityConfig.KEY_DEVICE_INFO);
                        byte[] bArr2 = m3758a.get("gateway");
                        if (bArr != null) {
                            C5873a.m3389a(C5876a.this.f7816g).m3425e(new String(bArr));
                        }
                        if (bArr2 != null) {
                            C5873a.m3389a(C5876a.this.f7816g).m3423d(new String(bArr2));
                        }
                    }
                    C5876a c5876a = C5876a.this;
                    C5876a c5876a2 = C5876a.this;
                    c5876a.f7815f = C5876a.m3491d();
                    if (C5876a.this.f7815f != null && !C5942z.m3868a(C5876a.f7811h) && C5942z.m3891c(C5876a.f7811h)) {
                        C5876a.this.f7815f.f7801r = C5876a.f7811h;
                        C5876a.this.f7815f.f7802s = C5876a.f7811h;
                    }
                } catch (Throwable th) {
                    if (!C5940x.m3819a(th)) {
                        th.printStackTrace();
                    }
                }
                C5876a c5876a3 = C5876a.this;
                c5876a3.m3494a(c5876a3.f7815f, false);
            }
        }, j);
    }

    /* renamed from: a */
    public static synchronized C5876a m3487a() {
        C5876a c5876a;
        synchronized (C5876a.class) {
            c5876a = f7810b;
        }
        return c5876a;
    }

    /* renamed from: b */
    public final synchronized boolean m3496b() {
        return this.f7815f != null;
    }

    /* renamed from: c */
    public final StrategyBean m3497c() {
        StrategyBean strategyBean = this.f7815f;
        return strategyBean != null ? strategyBean : this.f7814e;
    }

    /* renamed from: a */
    protected final void m3494a(StrategyBean strategyBean, boolean z) {
        C5940x.m3823c("[Strategy] Notify %s", C5871b.class.getName());
        C5871b.m3359a(strategyBean, z);
        for (AbstractC5864a abstractC5864a : this.f7812c) {
            try {
                C5940x.m3823c("[Strategy] Notify %s", abstractC5864a.getClass().getName());
                abstractC5864a.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m3489a(String str) {
        if (C5942z.m3868a(str) || !C5942z.m3891c(str)) {
            C5940x.m3824d("URL user set is invalid.", new Object[0]);
        } else {
            f7811h = str;
        }
    }

    /* renamed from: a */
    public final void m3495a(C5915as c5915as) {
        if (c5915as == null) {
            return;
        }
        if (this.f7815f == null || c5915as.f8118h != this.f7815f.f7799p) {
            StrategyBean strategyBean = new StrategyBean();
            strategyBean.f7790g = c5915as.f8111a;
            strategyBean.f7792i = c5915as.f8113c;
            strategyBean.f7791h = c5915as.f8112b;
            if (C5942z.m3868a(f7811h) || !C5942z.m3891c(f7811h)) {
                if (C5942z.m3891c(c5915as.f8114d)) {
                    C5940x.m3823c("[Strategy] Upload url changes to %s", c5915as.f8114d);
                    strategyBean.f7801r = c5915as.f8114d;
                }
                if (C5942z.m3891c(c5915as.f8115e)) {
                    C5940x.m3823c("[Strategy] Exception upload url changes to %s", c5915as.f8115e);
                    strategyBean.f7802s = c5915as.f8115e;
                }
            }
            if (c5915as.f8116f != null && !C5942z.m3868a(c5915as.f8116f.f8106a)) {
                strategyBean.f7804u = c5915as.f8116f.f8106a;
            }
            if (c5915as.f8118h != 0) {
                strategyBean.f7799p = c5915as.f8118h;
            }
            if (c5915as.f8117g != null && c5915as.f8117g.size() > 0) {
                strategyBean.f7805v = c5915as.f8117g;
                String str = c5915as.f8117g.get("B11");
                if (str != null && str.equals("1")) {
                    strategyBean.f7793j = true;
                } else {
                    strategyBean.f7793j = false;
                }
                String str2 = c5915as.f8117g.get("B3");
                if (str2 != null) {
                    strategyBean.f7808y = Long.valueOf(str2).longValue();
                }
                strategyBean.f7800q = c5915as.f8119i;
                strategyBean.f7807x = c5915as.f8119i;
                String str3 = c5915as.f8117g.get("B27");
                if (str3 != null && str3.length() > 0) {
                    try {
                        int parseInt = Integer.parseInt(str3);
                        if (parseInt > 0) {
                            strategyBean.f7806w = parseInt;
                        }
                    } catch (Exception e) {
                        if (!C5940x.m3819a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                String str4 = c5915as.f8117g.get("B25");
                if (str4 != null && str4.equals("1")) {
                    strategyBean.f7795l = true;
                } else {
                    strategyBean.f7795l = false;
                }
            }
            C5940x.m3818a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", Boolean.valueOf(strategyBean.f7790g), Boolean.valueOf(strategyBean.f7792i), Boolean.valueOf(strategyBean.f7791h), Boolean.valueOf(strategyBean.f7793j), Boolean.valueOf(strategyBean.f7794k), Boolean.valueOf(strategyBean.f7797n), Boolean.valueOf(strategyBean.f7798o), Long.valueOf(strategyBean.f7800q), Boolean.valueOf(strategyBean.f7795l), Long.valueOf(strategyBean.f7799p));
            this.f7815f = strategyBean;
            C5932p.m3740a().m3763b(2);
            C5934r c5934r = new C5934r();
            c5934r.f8211b = 2;
            c5934r.f8210a = strategyBean.f7788e;
            c5934r.f8214e = strategyBean.f7789f;
            c5934r.f8216g = C5942z.m3871a(strategyBean);
            C5932p.m3740a().m3762a(c5934r);
            m3494a(strategyBean, true);
        }
    }

    /* renamed from: d */
    public static StrategyBean m3491d() {
        List<C5934r> m3757a = C5932p.m3740a().m3757a(2);
        if (m3757a == null || m3757a.size() <= 0) {
            return null;
        }
        C5934r c5934r = m3757a.get(0);
        if (c5934r.f8216g != null) {
            return (StrategyBean) C5942z.m3851a(c5934r.f8216g, StrategyBean.CREATOR);
        }
        return null;
    }
}
