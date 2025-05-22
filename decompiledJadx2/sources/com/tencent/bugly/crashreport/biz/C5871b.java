package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.core.os.EnvironmentCompat;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.biz.C5870a;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.biz.b */
/* loaded from: classes7.dex */
public class C5871b {

    /* renamed from: a */
    public static C5870a f7682a = null;

    /* renamed from: b */
    private static boolean f7683b = false;

    /* renamed from: c */
    private static int f7684c = 10;

    /* renamed from: d */
    private static long f7685d = 300000;

    /* renamed from: e */
    private static long f7686e = 30000;

    /* renamed from: f */
    private static long f7687f = 0;

    /* renamed from: g */
    private static int f7688g = 0;

    /* renamed from: h */
    private static long f7689h = 0;

    /* renamed from: i */
    private static long f7690i = 0;

    /* renamed from: j */
    private static long f7691j = 0;

    /* renamed from: k */
    private static Application.ActivityLifecycleCallbacks f7692k = null;

    /* renamed from: l */
    private static Class<?> f7693l = null;

    /* renamed from: m */
    private static boolean f7694m = true;

    /* renamed from: a */
    static /* synthetic */ String m3354a(String str, String str2) {
        return C5942z.m3852a() + "  " + str + "  " + str2 + "\n";
    }

    /* renamed from: g */
    static /* synthetic */ int m3369g() {
        int i = f7688g;
        f7688g = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0069  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m3365c(Context context, BuglyStrategy buglyStrategy) {
        boolean z;
        boolean z2;
        boolean z3;
        if (buglyStrategy != null) {
            z = buglyStrategy.recordUserInfoOnceADay();
            z2 = buglyStrategy.isEnableUserInfo();
        } else {
            z = false;
            z2 = true;
        }
        if (z) {
            C5873a m3389a = C5873a.m3389a(context);
            List<UserInfoBean> m3349a = f7682a.m3349a(m3389a.f7756d);
            if (m3349a != null) {
                for (int i = 0; i < m3349a.size(); i++) {
                    UserInfoBean userInfoBean = m3349a.get(i);
                    if (userInfoBean.f7663n.equals(m3389a.f7762j) && userInfoBean.f7651b == 1) {
                        long m3876b = C5942z.m3876b();
                        if (m3876b <= 0) {
                            break;
                        }
                        if (userInfoBean.f7654e >= m3876b) {
                            if (userInfoBean.f7655f <= 0) {
                                C5870a c5870a = f7682a;
                                C5939w m3810a = C5939w.m3810a();
                                if (m3810a != null) {
                                    m3810a.m3812a(new C5870a.AnonymousClass2());
                                }
                            }
                            z3 = false;
                            if (z3) {
                                return;
                            } else {
                                z2 = false;
                            }
                        }
                    }
                }
            }
            z3 = true;
            if (z3) {
            }
        }
        C5873a m3390b = C5873a.m3390b();
        if (m3390b != null) {
            boolean z4 = false;
            String str = null;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (stackTraceElement.getMethodName().equals("onCreate")) {
                    str = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals("android.app.Activity")) {
                    z4 = true;
                }
            }
            if (str == null) {
                str = EnvironmentCompat.MEDIA_UNKNOWN;
            } else if (z4) {
                m3390b.m3414a(true);
            } else {
                str = "background";
            }
            m3390b.f7768p = str;
        }
        if (z2 && Build.VERSION.SDK_INT >= 14) {
            Application application = context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
            if (application != null) {
                try {
                    if (f7692k == null) {
                        f7692k = new Application.ActivityLifecycleCallbacks() { // from class: com.tencent.bugly.crashreport.biz.b.2
                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public final void onActivityStarted(Activity activity) {
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public final void onActivityStopped(Activity activity) {
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public final void onActivityResumed(Activity activity) {
                                String name = activity != null ? activity.getClass().getName() : EnvironmentCompat.MEDIA_UNKNOWN;
                                if (C5871b.f7693l == null || C5871b.f7693l.getName().equals(name)) {
                                    C5940x.m3823c(">>> %s onResumed <<<", name);
                                    C5873a m3390b2 = C5873a.m3390b();
                                    if (m3390b2 == null) {
                                        return;
                                    }
                                    m3390b2.f7704C.add(C5871b.m3354a(name, "onResumed"));
                                    m3390b2.m3414a(true);
                                    m3390b2.f7768p = name;
                                    m3390b2.f7769q = System.currentTimeMillis();
                                    m3390b2.f7772t = m3390b2.f7769q - C5871b.f7690i;
                                    long j = m3390b2.f7769q - C5871b.f7689h;
                                    if (j > (C5871b.f7687f > 0 ? C5871b.f7687f : C5871b.f7686e)) {
                                        m3390b2.m3422d();
                                        C5871b.m3369g();
                                        C5940x.m3818a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(j / 1000), Long.valueOf(C5871b.f7686e / 1000));
                                        if (C5871b.f7688g % C5871b.f7684c == 0) {
                                            C5871b.f7682a.m3351a(4, C5871b.f7694m, 0L);
                                            return;
                                        }
                                        C5871b.f7682a.m3351a(4, false, 0L);
                                        long currentTimeMillis = System.currentTimeMillis();
                                        if (currentTimeMillis - C5871b.f7691j > C5871b.f7685d) {
                                            long unused = C5871b.f7691j = currentTimeMillis;
                                            C5940x.m3818a("add a timer to upload hot start user info", new Object[0]);
                                            if (C5871b.f7694m) {
                                                C5939w.m3810a().m3813a(new C5870a.a(null, true), C5871b.f7685d);
                                            }
                                        }
                                    }
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public final void onActivityPaused(Activity activity) {
                                String name = activity != null ? activity.getClass().getName() : EnvironmentCompat.MEDIA_UNKNOWN;
                                if (C5871b.f7693l == null || C5871b.f7693l.getName().equals(name)) {
                                    C5940x.m3823c(">>> %s onPaused <<<", name);
                                    C5873a m3390b2 = C5873a.m3390b();
                                    if (m3390b2 == null) {
                                        return;
                                    }
                                    m3390b2.f7704C.add(C5871b.m3354a(name, "onPaused"));
                                    m3390b2.m3414a(false);
                                    m3390b2.f7770r = System.currentTimeMillis();
                                    m3390b2.f7771s = m3390b2.f7770r - m3390b2.f7769q;
                                    long unused = C5871b.f7689h = m3390b2.f7770r;
                                    if (m3390b2.f7771s < 0) {
                                        m3390b2.f7771s = 0L;
                                    }
                                    if (activity != null) {
                                        m3390b2.f7768p = "background";
                                    } else {
                                        m3390b2.f7768p = EnvironmentCompat.MEDIA_UNKNOWN;
                                    }
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public final void onActivityDestroyed(Activity activity) {
                                String name = activity != null ? activity.getClass().getName() : EnvironmentCompat.MEDIA_UNKNOWN;
                                if (C5871b.f7693l == null || C5871b.f7693l.getName().equals(name)) {
                                    C5940x.m3823c(">>> %s onDestroyed <<<", name);
                                    C5873a m3390b2 = C5873a.m3390b();
                                    if (m3390b2 != null) {
                                        m3390b2.f7704C.add(C5871b.m3354a(name, "onDestroyed"));
                                    }
                                }
                            }

                            @Override // android.app.Application.ActivityLifecycleCallbacks
                            public final void onActivityCreated(Activity activity, Bundle bundle) {
                                String name = activity != null ? activity.getClass().getName() : EnvironmentCompat.MEDIA_UNKNOWN;
                                if (C5871b.f7693l == null || C5871b.f7693l.getName().equals(name)) {
                                    C5940x.m3823c(">>> %s onCreated <<<", name);
                                    C5873a m3390b2 = C5873a.m3390b();
                                    if (m3390b2 != null) {
                                        m3390b2.f7704C.add(C5871b.m3354a(name, "onCreated"));
                                    }
                                }
                            }
                        };
                    }
                    application.registerActivityLifecycleCallbacks(f7692k);
                } catch (Exception e) {
                    if (!C5940x.m3819a(e)) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (f7694m) {
            f7690i = System.currentTimeMillis();
            f7682a.m3351a(1, false, 0L);
            C5940x.m3818a("[session] launch app, new start", new Object[0]);
            f7682a.m3350a();
            C5939w.m3810a().m3813a(new C5870a.c(21600000L), 21600000L);
        }
    }

    /* renamed from: a */
    public static void m3358a(final Context context, final BuglyStrategy buglyStrategy) {
        long j;
        if (f7683b) {
            return;
        }
        f7694m = C5873a.m3389a(context).f7757e;
        f7682a = new C5870a(context, f7694m);
        f7683b = true;
        if (buglyStrategy != null) {
            f7693l = buglyStrategy.getUserInfoActivity();
            j = buglyStrategy.getAppReportDelay();
        } else {
            j = 0;
        }
        if (j <= 0) {
            m3365c(context, buglyStrategy);
        } else {
            C5939w.m3810a().m3813a(new Runnable() { // from class: com.tencent.bugly.crashreport.biz.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    C5871b.m3365c(context, buglyStrategy);
                }
            }, j);
        }
    }

    /* renamed from: a */
    public static void m3356a(long j) {
        if (j < 0) {
            j = C5876a.m3487a().m3497c().f7800q;
        }
        f7687f = j;
    }

    /* renamed from: a */
    public static void m3359a(StrategyBean strategyBean, boolean z) {
        C5939w m3810a;
        C5870a c5870a = f7682a;
        if (c5870a != null && !z && (m3810a = C5939w.m3810a()) != null) {
            m3810a.m3812a(new C5870a.AnonymousClass2());
        }
        if (strategyBean == null) {
            return;
        }
        if (strategyBean.f7800q > 0) {
            f7686e = strategyBean.f7800q;
        }
        if (strategyBean.f7806w > 0) {
            f7684c = strategyBean.f7806w;
        }
        if (strategyBean.f7807x > 0) {
            f7685d = strategyBean.f7807x;
        }
    }

    /* renamed from: a */
    public static void m3355a() {
        C5870a c5870a = f7682a;
        if (c5870a != null) {
            c5870a.m3351a(2, false, 0L);
        }
    }

    /* renamed from: a */
    public static void m3357a(Context context) {
        if (!f7683b || context == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            Application application = context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
            if (application != null) {
                try {
                    if (f7692k != null) {
                        application.unregisterActivityLifecycleCallbacks(f7692k);
                    }
                } catch (Exception e) {
                    if (!C5940x.m3819a(e)) {
                        e.printStackTrace();
                    }
                }
            }
        }
        f7683b = false;
    }
}
