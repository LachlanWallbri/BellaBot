package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.pudutech.mirsdk.SolicitService;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5941y;
import com.tencent.bugly.proguard.C5942z;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.d */
/* loaded from: classes7.dex */
public final class C5888d {

    /* renamed from: a */
    private static C5888d f7949a;

    /* renamed from: b */
    private C5876a f7950b;

    /* renamed from: c */
    private C5873a f7951c;

    /* renamed from: d */
    private C5886b f7952d;

    /* renamed from: e */
    private Context f7953e;

    /* renamed from: a */
    static /* synthetic */ void m3570a(C5888d c5888d) {
        C5940x.m3823c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            c5888d.f7951c.getClass();
            C5942z.m3864a(cls, "sdkPackageName", "com.tencent.bugly", (Object) null);
            C5940x.m3823c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable unused) {
            C5940x.m3818a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x015a A[Catch: all -> 0x0250, TRY_ENTER, TryCatch #1 {all -> 0x0250, blocks: (B:15:0x0045, B:17:0x004d, B:18:0x0054, B:21:0x0060, B:23:0x0068, B:32:0x00b6, B:34:0x00ba, B:39:0x00e0, B:42:0x015a, B:44:0x0161, B:46:0x0166, B:48:0x01fa, B:50:0x0203, B:51:0x0208, B:53:0x0243, B:57:0x00c8, B:59:0x00cc), top: B:14:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01fa A[Catch: all -> 0x0250, TryCatch #1 {all -> 0x0250, blocks: (B:15:0x0045, B:17:0x004d, B:18:0x0054, B:21:0x0060, B:23:0x0068, B:32:0x00b6, B:34:0x00ba, B:39:0x00e0, B:42:0x015a, B:44:0x0161, B:46:0x0166, B:48:0x01fa, B:50:0x0203, B:51:0x0208, B:53:0x0243, B:57:0x00c8, B:59:0x00cc), top: B:14:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0203 A[Catch: all -> 0x0250, TryCatch #1 {all -> 0x0250, blocks: (B:15:0x0045, B:17:0x004d, B:18:0x0054, B:21:0x0060, B:23:0x0068, B:32:0x00b6, B:34:0x00ba, B:39:0x00e0, B:42:0x015a, B:44:0x0161, B:46:0x0166, B:48:0x01fa, B:50:0x0203, B:51:0x0208, B:53:0x0243, B:57:0x00c8, B:59:0x00cc), top: B:14:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0243 A[Catch: all -> 0x0250, TRY_LEAVE, TryCatch #1 {all -> 0x0250, blocks: (B:15:0x0045, B:17:0x004d, B:18:0x0054, B:21:0x0060, B:23:0x0068, B:32:0x00b6, B:34:0x00ba, B:39:0x00e0, B:42:0x015a, B:44:0x0161, B:46:0x0166, B:48:0x01fa, B:50:0x0203, B:51:0x0208, B:53:0x0243, B:57:0x00c8, B:59:0x00cc), top: B:14:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0165  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ void m3571a(C5888d c5888d, Thread thread, int i, String str, String str2, String str3, Map map) {
        String str4;
        int i2;
        CrashDetailBean crashDetailBean;
        String str5;
        String str6;
        Thread currentThread = thread == null ? Thread.currentThread() : thread;
        if (i == 4) {
            str4 = "Unity";
        } else if (i == 5 || i == 6) {
            str4 = "Cocos";
        } else {
            if (i != 8) {
                C5940x.m3824d("[ExtraCrashManager] Unknown extra crash type: %d", Integer.valueOf(i));
                return;
            }
            str4 = "H5";
        }
        C5940x.m3825e("[ExtraCrashManager] %s Crash Happen", str4);
        try {
            if (!c5888d.f7950b.m3496b()) {
                C5940x.m3824d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
            }
            StrategyBean m3497c = c5888d.f7950b.m3497c();
            if (!m3497c.f7790g && c5888d.f7950b.m3496b()) {
                C5940x.m3825e("[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                C5886b.m3531a(str4, C5942z.m3852a(), c5888d.f7951c.f7756d, currentThread.getName(), str + "\n" + str2 + "\n" + str3, null);
                C5940x.m3825e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            if (i != 4) {
                if (i != 5 && i != 6) {
                    i2 = 8;
                    if (i == 8) {
                        if (!m3497c.f7796m) {
                            C5940x.m3825e("[ExtraCrashManager] %s report is disabled.", str4);
                            C5940x.m3825e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                            return;
                        }
                    }
                    int i3 = i != i2 ? i : 5;
                    crashDetailBean = new CrashDetailBean();
                    crashDetailBean.f7827C = C5874b.m3470k();
                    crashDetailBean.f7828D = C5874b.m3466i();
                    crashDetailBean.f7829E = C5874b.m3474m();
                    crashDetailBean.f7830F = c5888d.f7951c.m3438p();
                    crashDetailBean.f7831G = c5888d.f7951c.m3437o();
                    crashDetailBean.f7832H = c5888d.f7951c.m3439q();
                    crashDetailBean.f7870w = C5942z.m3854a(c5888d.f7953e, C5887c.f7921e, (String) null);
                    crashDetailBean.f7849b = i3;
                    crashDetailBean.f7852e = c5888d.f7951c.m3430h();
                    crashDetailBean.f7853f = c5888d.f7951c.f7762j;
                    crashDetailBean.f7854g = c5888d.f7951c.m3445w();
                    crashDetailBean.f7860m = c5888d.f7951c.m3428g();
                    crashDetailBean.f7861n = str;
                    crashDetailBean.f7862o = str2;
                    str5 = "";
                    if (str3 == null) {
                        String[] split = str3.split("\n");
                        str5 = split.length > 0 ? split[0] : "";
                        str6 = str3;
                    } else {
                        str6 = "";
                    }
                    crashDetailBean.f7863p = str5;
                    crashDetailBean.f7864q = str6;
                    crashDetailBean.f7865r = System.currentTimeMillis();
                    crashDetailBean.f7868u = C5942z.m3879b(crashDetailBean.f7864q.getBytes());
                    crashDetailBean.f7873z = C5942z.m3861a(C5887c.f7922f, false);
                    crashDetailBean.f7825A = c5888d.f7951c.f7756d;
                    crashDetailBean.f7826B = currentThread.getName() + "(" + currentThread.getId() + ")";
                    crashDetailBean.f7833I = c5888d.f7951c.m3447y();
                    crashDetailBean.f7855h = c5888d.f7951c.m3444v();
                    crashDetailBean.f7837M = c5888d.f7951c.f7728a;
                    crashDetailBean.f7838N = c5888d.f7951c.m3415a();
                    crashDetailBean.f7840P = c5888d.f7951c.m3399H();
                    crashDetailBean.f7841Q = c5888d.f7951c.m3400I();
                    crashDetailBean.f7842R = c5888d.f7951c.m3393B();
                    crashDetailBean.f7843S = c5888d.f7951c.m3398G();
                    c5888d.f7952d.m3545c(crashDetailBean);
                    crashDetailBean.f7872y = C5941y.m3832a();
                    if (crashDetailBean.f7839O == null) {
                        crashDetailBean.f7839O = new LinkedHashMap();
                    }
                    if (map != null) {
                        crashDetailBean.f7839O.putAll(map);
                    }
                    C5886b.m3531a(str4, C5942z.m3852a(), c5888d.f7951c.f7756d, currentThread.getName(), str + "\n" + str2 + "\n" + str3, crashDetailBean);
                    if (!c5888d.f7952d.m3542a(crashDetailBean)) {
                        c5888d.f7952d.m3540a(crashDetailBean, SolicitService.CAMERA_OPEN_TIME_OUT, false);
                    }
                    C5940x.m3825e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                }
                if (!m3497c.f7795l) {
                    C5940x.m3825e("[ExtraCrashManager] %s report is disabled.", str4);
                    C5940x.m3825e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                    return;
                }
            }
            i2 = 8;
            if (i != i2) {
            }
            crashDetailBean = new CrashDetailBean();
            crashDetailBean.f7827C = C5874b.m3470k();
            crashDetailBean.f7828D = C5874b.m3466i();
            crashDetailBean.f7829E = C5874b.m3474m();
            crashDetailBean.f7830F = c5888d.f7951c.m3438p();
            crashDetailBean.f7831G = c5888d.f7951c.m3437o();
            crashDetailBean.f7832H = c5888d.f7951c.m3439q();
            crashDetailBean.f7870w = C5942z.m3854a(c5888d.f7953e, C5887c.f7921e, (String) null);
            crashDetailBean.f7849b = i3;
            crashDetailBean.f7852e = c5888d.f7951c.m3430h();
            crashDetailBean.f7853f = c5888d.f7951c.f7762j;
            crashDetailBean.f7854g = c5888d.f7951c.m3445w();
            crashDetailBean.f7860m = c5888d.f7951c.m3428g();
            crashDetailBean.f7861n = str;
            crashDetailBean.f7862o = str2;
            str5 = "";
            if (str3 == null) {
            }
            crashDetailBean.f7863p = str5;
            crashDetailBean.f7864q = str6;
            crashDetailBean.f7865r = System.currentTimeMillis();
            crashDetailBean.f7868u = C5942z.m3879b(crashDetailBean.f7864q.getBytes());
            crashDetailBean.f7873z = C5942z.m3861a(C5887c.f7922f, false);
            crashDetailBean.f7825A = c5888d.f7951c.f7756d;
            crashDetailBean.f7826B = currentThread.getName() + "(" + currentThread.getId() + ")";
            crashDetailBean.f7833I = c5888d.f7951c.m3447y();
            crashDetailBean.f7855h = c5888d.f7951c.m3444v();
            crashDetailBean.f7837M = c5888d.f7951c.f7728a;
            crashDetailBean.f7838N = c5888d.f7951c.m3415a();
            crashDetailBean.f7840P = c5888d.f7951c.m3399H();
            crashDetailBean.f7841Q = c5888d.f7951c.m3400I();
            crashDetailBean.f7842R = c5888d.f7951c.m3393B();
            crashDetailBean.f7843S = c5888d.f7951c.m3398G();
            c5888d.f7952d.m3545c(crashDetailBean);
            crashDetailBean.f7872y = C5941y.m3832a();
            if (crashDetailBean.f7839O == null) {
            }
            if (map != null) {
            }
            C5886b.m3531a(str4, C5942z.m3852a(), c5888d.f7951c.f7756d, currentThread.getName(), str + "\n" + str2 + "\n" + str3, crashDetailBean);
            if (!c5888d.f7952d.m3542a(crashDetailBean)) {
            }
            C5940x.m3825e("[ExtraCrashManager] Successfully handled.", new Object[0]);
        } catch (Throwable th) {
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                C5940x.m3825e("[ExtraCrashManager] Successfully handled.", new Object[0]);
            } catch (Throwable th2) {
                C5940x.m3825e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                throw th2;
            }
        }
    }

    private C5888d(Context context) {
        C5887c m3547a = C5887c.m3547a();
        if (m3547a == null) {
            return;
        }
        this.f7950b = C5876a.m3487a();
        this.f7951c = C5873a.m3389a(context);
        this.f7952d = m3547a.f7933p;
        this.f7953e = context;
        C5939w.m3810a().m3812a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.d.1
            @Override // java.lang.Runnable
            public final void run() {
                C5888d.m3570a(C5888d.this);
            }
        });
    }

    /* renamed from: a */
    public static C5888d m3569a(Context context) {
        if (f7949a == null) {
            f7949a = new C5888d(context);
        }
        return f7949a;
    }

    /* renamed from: a */
    public static void m3572a(final Thread thread, final int i, final String str, final String str2, final String str3, final Map<String, String> map) {
        C5939w.m3810a().m3812a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.d.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (C5888d.f7949a != null) {
                        C5888d.m3571a(C5888d.f7949a, thread, i, str, str2, str3, map);
                    } else {
                        C5940x.m3825e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    }
                } catch (Throwable th) {
                    if (!C5940x.m3822b(th)) {
                        th.printStackTrace();
                    }
                    C5940x.m3825e("[ExtraCrashManager] Crash error %s %s %s", str, str2, str3);
                }
            }
        });
    }
}
