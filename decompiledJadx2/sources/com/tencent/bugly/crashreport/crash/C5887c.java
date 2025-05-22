package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Build;
import com.pudutech.mirsdk.SolicitService;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.anr.C5885b;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C5932p;
import com.tencent.bugly.proguard.C5934r;
import com.tencent.bugly.proguard.C5937u;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import com.tencent.bugly.proguard.InterfaceC5931o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.c */
/* loaded from: classes7.dex */
public final class C5887c {

    /* renamed from: a */
    public static int f7917a = 0;

    /* renamed from: b */
    public static boolean f7918b = false;

    /* renamed from: c */
    public static int f7919c = 2;

    /* renamed from: d */
    public static boolean f7920d = true;

    /* renamed from: e */
    public static int f7921e = 20480;

    /* renamed from: f */
    public static int f7922f = 20480;

    /* renamed from: g */
    public static long f7923g = 604800000;

    /* renamed from: h */
    public static String f7924h = null;

    /* renamed from: i */
    public static boolean f7925i = false;

    /* renamed from: j */
    public static String f7926j = null;

    /* renamed from: k */
    public static int f7927k = 5000;

    /* renamed from: l */
    public static boolean f7928l = true;

    /* renamed from: m */
    public static boolean f7929m = false;

    /* renamed from: n */
    public static String f7930n;

    /* renamed from: o */
    public static String f7931o;

    /* renamed from: r */
    private static C5887c f7932r;

    /* renamed from: p */
    public final C5886b f7933p;

    /* renamed from: q */
    private final Context f7934q;

    /* renamed from: s */
    private final C5889e f7935s;

    /* renamed from: t */
    private final NativeCrashHandler f7936t;

    /* renamed from: u */
    private C5876a f7937u;

    /* renamed from: v */
    private C5939w f7938v;

    /* renamed from: w */
    private final C5885b f7939w;

    /* renamed from: x */
    private Boolean f7940x;

    private C5887c(int i, Context context, C5939w c5939w, boolean z, BuglyStrategy.C5863a c5863a, InterfaceC5931o interfaceC5931o, String str) {
        f7917a = i;
        Context m3846a = C5942z.m3846a(context);
        this.f7934q = m3846a;
        this.f7937u = C5876a.m3487a();
        this.f7938v = c5939w;
        this.f7933p = new C5886b(i, m3846a, C5937u.m3773a(), C5932p.m3740a(), this.f7937u, c5863a, interfaceC5931o);
        C5873a m3389a = C5873a.m3389a(m3846a);
        this.f7935s = new C5889e(m3846a, this.f7933p, this.f7937u, m3389a);
        this.f7936t = NativeCrashHandler.getInstance(m3846a, m3389a, this.f7933p, this.f7937u, c5939w, z, str);
        m3389a.f7705D = this.f7936t;
        this.f7939w = new C5885b(m3846a, this.f7937u, m3389a, c5939w, this.f7933p);
    }

    /* renamed from: a */
    public static synchronized C5887c m3548a(int i, Context context, boolean z, BuglyStrategy.C5863a c5863a, InterfaceC5931o interfaceC5931o, String str) {
        C5887c c5887c;
        synchronized (C5887c.class) {
            if (f7932r == null) {
                f7932r = new C5887c(1004, context, C5939w.m3810a(), z, c5863a, null, null);
            }
            c5887c = f7932r;
        }
        return c5887c;
    }

    /* renamed from: a */
    public static synchronized C5887c m3547a() {
        C5887c c5887c;
        synchronized (C5887c.class) {
            c5887c = f7932r;
        }
        return c5887c;
    }

    /* renamed from: a */
    public final void m3552a(StrategyBean strategyBean) {
        this.f7935s.m3579a(strategyBean);
        this.f7936t.onStrategyChanged(strategyBean);
        this.f7939w.m3518a(strategyBean);
        C5939w.m3810a().m3813a(new AnonymousClass2(), SolicitService.CAMERA_OPEN_TIME_OUT);
    }

    /* renamed from: b */
    public final boolean m3556b() {
        Boolean bool = this.f7940x;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = C5873a.m3390b().f7756d;
        List<C5934r> m3757a = C5932p.m3740a().m3757a(1);
        ArrayList arrayList = new ArrayList();
        if (m3757a != null && m3757a.size() > 0) {
            for (C5934r c5934r : m3757a) {
                if (str.equals(c5934r.f8212c)) {
                    this.f7940x = true;
                    arrayList.add(c5934r);
                }
            }
            if (arrayList.size() > 0) {
                C5932p.m3740a().m3759a(arrayList);
            }
            return true;
        }
        this.f7940x = false;
        return false;
    }

    /* renamed from: c */
    public final synchronized void m3557c() {
        this.f7935s.m3578a();
        this.f7936t.setUserOpened(true);
        if (Build.VERSION.SDK_INT <= 19) {
            this.f7939w.m3520a(true);
        } else {
            this.f7939w.m3524c();
        }
    }

    /* renamed from: d */
    public final synchronized void m3558d() {
        this.f7935s.m3581b();
        this.f7936t.setUserOpened(false);
        if (Build.VERSION.SDK_INT < 19) {
            this.f7939w.m3520a(false);
        } else {
            this.f7939w.m3525d();
        }
    }

    /* renamed from: e */
    public final void m3559e() {
        this.f7935s.m3578a();
    }

    /* renamed from: f */
    public final void m3560f() {
        this.f7936t.setUserOpened(false);
    }

    /* renamed from: g */
    public final void m3561g() {
        this.f7936t.setUserOpened(true);
    }

    /* renamed from: h */
    public final void m3562h() {
        if (Build.VERSION.SDK_INT <= 19) {
            this.f7939w.m3520a(true);
        } else {
            this.f7939w.m3524c();
        }
    }

    /* renamed from: i */
    public final void m3563i() {
        if (Build.VERSION.SDK_INT < 19) {
            this.f7939w.m3520a(false);
        } else {
            this.f7939w.m3525d();
        }
    }

    /* renamed from: a */
    public final synchronized void m3555a(boolean z, boolean z2, boolean z3) {
        this.f7936t.testNativeCrash(z, z2, z3);
    }

    /* renamed from: j */
    public final synchronized void m3564j() {
        C5885b c5885b = this.f7939w;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i < 30) {
                try {
                    C5940x.m3818a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    C5942z.m3881b(5000L);
                    i = i2;
                } catch (Throwable th) {
                    if (C5940x.m3819a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            }
        }
    }

    /* renamed from: k */
    public final boolean m3565k() {
        return this.f7939w.m3521a();
    }

    /* renamed from: a */
    public final void m3554a(final Thread thread, final Throwable th, boolean z, String str, byte[] bArr, final boolean z2) {
        final boolean z3 = false;
        final String str2 = null;
        final byte[] bArr2 = null;
        this.f7938v.m3812a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    C5940x.m3823c("post a throwable %b", Boolean.valueOf(z3));
                    C5887c.this.f7935s.m3580a(thread, th, false, str2, bArr2);
                    if (z2) {
                        C5940x.m3818a("clear user datas", new Object[0]);
                        C5873a.m3389a(C5887c.this.f7934q).m3394C();
                    }
                } catch (Throwable th2) {
                    if (!C5940x.m3822b(th2)) {
                        th2.printStackTrace();
                    }
                    C5940x.m3825e("java catch error: %s", th.toString());
                }
            }
        });
    }

    /* renamed from: a */
    public final void m3553a(CrashDetailBean crashDetailBean) {
        this.f7933p.m3546d(crashDetailBean);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.c$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    final class AnonymousClass2 extends Thread {
        AnonymousClass2() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            List<CrashDetailBean> list;
            if (C5942z.m3865a(C5887c.this.f7934q, "local_crash_lock", 10000L)) {
                List<CrashDetailBean> m3539a = C5887c.this.f7933p.m3539a();
                if (m3539a != null && m3539a.size() > 0) {
                    C5940x.m3823c("Size of crash list: %s", Integer.valueOf(m3539a.size()));
                    int size = m3539a.size();
                    if (size > 100) {
                        ArrayList arrayList = new ArrayList();
                        Collections.sort(m3539a);
                        for (int i = 0; i < 100; i++) {
                            arrayList.add(m3539a.get((size - 1) - i));
                        }
                        list = arrayList;
                    } else {
                        list = m3539a;
                    }
                    C5887c.this.f7933p.m3541a(list, 0L, false, false, false);
                }
                C5942z.m3884b(C5887c.this.f7934q, "local_crash_lock");
            }
        }
    }

    /* renamed from: a */
    public final void m3551a(long j) {
        C5939w.m3810a().m3813a(new AnonymousClass2(), j);
    }

    /* renamed from: l */
    public final void m3566l() {
        this.f7936t.checkUploadRecordCrash();
    }

    /* renamed from: m */
    public final void m3567m() {
        if (C5873a.m3390b().f7756d.equals(AppInfo.m3376a(this.f7934q))) {
            this.f7936t.removeEmptyNativeRecordFiles();
        }
    }
}
