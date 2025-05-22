package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.pudutech.mirsdk.SolicitService;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5941y;
import com.tencent.bugly.proguard.C5942z;
import java.lang.Thread;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.e */
/* loaded from: classes7.dex */
public final class C5889e implements Thread.UncaughtExceptionHandler {

    /* renamed from: h */
    private static String f7961h;

    /* renamed from: i */
    private static final Object f7962i = new Object();

    /* renamed from: a */
    private Context f7963a;

    /* renamed from: b */
    private C5886b f7964b;

    /* renamed from: c */
    private C5876a f7965c;

    /* renamed from: d */
    private C5873a f7966d;

    /* renamed from: e */
    private Thread.UncaughtExceptionHandler f7967e;

    /* renamed from: f */
    private Thread.UncaughtExceptionHandler f7968f;

    /* renamed from: g */
    private boolean f7969g = false;

    /* renamed from: j */
    private int f7970j;

    public C5889e(Context context, C5886b c5886b, C5876a c5876a, C5873a c5873a) {
        this.f7963a = context;
        this.f7964b = c5886b;
        this.f7965c = c5876a;
        this.f7966d = c5873a;
    }

    /* renamed from: a */
    public final synchronized void m3578a() {
        if (this.f7970j >= 10) {
            C5940x.m3818a("java crash handler over %d, no need set.", 10);
            return;
        }
        this.f7969g = true;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            if (getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                return;
            }
            if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                C5940x.m3818a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                this.f7968f = defaultUncaughtExceptionHandler;
                this.f7967e = defaultUncaughtExceptionHandler;
            } else {
                C5940x.m3818a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                this.f7967e = defaultUncaughtExceptionHandler;
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.f7970j++;
        C5940x.m3818a("registered java monitor: %s", toString());
    }

    /* renamed from: b */
    public final synchronized void m3581b() {
        this.f7969g = false;
        C5940x.m3818a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            C5940x.m3818a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.f7967e);
            this.f7970j--;
        }
    }

    /* renamed from: b */
    private CrashDetailBean m3576b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String m3573a;
        boolean z2 = false;
        if (th == null) {
            C5940x.m3824d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        boolean m3565k = C5887c.m3547a().m3565k();
        String str2 = (m3565k && z) ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (m3565k && z) {
            C5940x.m3825e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f7827C = C5874b.m3470k();
        crashDetailBean.f7828D = C5874b.m3466i();
        crashDetailBean.f7829E = C5874b.m3474m();
        crashDetailBean.f7830F = this.f7966d.m3438p();
        crashDetailBean.f7831G = this.f7966d.m3437o();
        crashDetailBean.f7832H = this.f7966d.m3439q();
        crashDetailBean.f7870w = C5942z.m3854a(this.f7963a, C5887c.f7921e, (String) null);
        crashDetailBean.f7872y = C5941y.m3832a();
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.f7872y == null ? 0 : crashDetailBean.f7872y.length);
        C5940x.m3818a("user log size:%d", objArr);
        crashDetailBean.f7849b = z ? 0 : 2;
        crashDetailBean.f7852e = this.f7966d.m3430h();
        crashDetailBean.f7853f = this.f7966d.f7762j;
        crashDetailBean.f7854g = this.f7966d.m3445w();
        crashDetailBean.f7860m = this.f7966d.m3428g();
        String name = th.getClass().getName();
        String m3577b = m3577b(th, 1000);
        if (m3577b == null) {
            m3577b = "";
        }
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        C5940x.m3825e("stack frame :%d, has cause %b", objArr2);
        String stackTraceElement = th.getStackTrace().length > 0 ? th.getStackTrace()[0].toString() : "";
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 != null && th2 != th) {
            crashDetailBean.f7861n = th2.getClass().getName();
            crashDetailBean.f7862o = m3577b(th2, 1000);
            if (crashDetailBean.f7862o == null) {
                crashDetailBean.f7862o = "";
            }
            if (th2.getStackTrace().length > 0) {
                crashDetailBean.f7863p = th2.getStackTrace()[0].toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(":");
            sb.append(m3577b);
            sb.append("\n");
            sb.append(stackTraceElement);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.f7861n);
            sb.append(":");
            sb.append(crashDetailBean.f7862o);
            sb.append("\n");
            m3573a = m3573a(th2, C5887c.f7922f);
            sb.append(m3573a);
            crashDetailBean.f7864q = sb.toString();
        } else {
            crashDetailBean.f7861n = name;
            crashDetailBean.f7862o = m3577b + str2;
            if (crashDetailBean.f7862o == null) {
                crashDetailBean.f7862o = "";
            }
            crashDetailBean.f7863p = stackTraceElement;
            m3573a = m3573a(th, C5887c.f7922f);
            crashDetailBean.f7864q = m3573a;
        }
        crashDetailBean.f7865r = System.currentTimeMillis();
        crashDetailBean.f7868u = C5942z.m3879b(crashDetailBean.f7864q.getBytes());
        try {
            crashDetailBean.f7873z = C5942z.m3861a(C5887c.f7922f, false);
            crashDetailBean.f7825A = this.f7966d.f7756d;
            crashDetailBean.f7826B = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.f7873z.put(crashDetailBean.f7826B, m3573a);
            crashDetailBean.f7833I = this.f7966d.m3447y();
            crashDetailBean.f7855h = this.f7966d.m3444v();
            crashDetailBean.f7856i = this.f7966d.m3401J();
            crashDetailBean.f7837M = this.f7966d.f7728a;
            crashDetailBean.f7838N = this.f7966d.m3415a();
            crashDetailBean.f7840P = this.f7966d.m3399H();
            crashDetailBean.f7841Q = this.f7966d.m3400I();
            crashDetailBean.f7842R = this.f7966d.m3393B();
            crashDetailBean.f7843S = this.f7966d.m3398G();
        } catch (Throwable th3) {
            C5940x.m3825e("handle crash error %s", th3.toString());
        }
        if (z) {
            this.f7964b.m3545c(crashDetailBean);
        } else {
            boolean z3 = str != null && str.length() > 0;
            if (bArr != null && bArr.length > 0) {
                z2 = true;
            }
            if (z3) {
                crashDetailBean.f7839O = new HashMap(1);
                crashDetailBean.f7839O.put("UserData", str);
            }
            if (z2) {
                crashDetailBean.f7844T = bArr;
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    private static boolean m3575a(Thread thread) {
        synchronized (f7962i) {
            if (f7961h != null && thread.getName().equals(f7961h)) {
                return true;
            }
            f7961h = thread.getName();
            return false;
        }
    }

    /* renamed from: a */
    public final void m3580a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        if (z) {
            C5940x.m3825e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (m3575a(thread)) {
                C5940x.m3818a("this class has handled this exception", new Object[0]);
                if (this.f7968f != null) {
                    C5940x.m3818a("call system handler", new Object[0]);
                    this.f7968f.uncaughtException(thread, th);
                } else {
                    C5940x.m3825e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        } else {
            C5940x.m3825e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.f7969g) {
                C5940x.m3823c("Java crash handler is disable. Just return.", new Object[0]);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f7967e;
                    if (uncaughtExceptionHandler != null && m3574a(uncaughtExceptionHandler)) {
                        C5940x.m3825e("sys default last handle start!", new Object[0]);
                        this.f7967e.uncaughtException(thread, th);
                        C5940x.m3825e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f7968f != null) {
                        C5940x.m3825e("system handle start!", new Object[0]);
                        this.f7968f.uncaughtException(thread, th);
                        C5940x.m3825e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C5940x.m3825e("crashreport last handle start!", new Object[0]);
                        C5940x.m3825e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C5940x.m3825e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            if (!this.f7965c.m3496b()) {
                C5940x.m3824d("no remote but still store!", new Object[0]);
            }
            if (!this.f7965c.m3497c().f7790g && this.f7965c.m3496b()) {
                C5940x.m3825e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                C5886b.m3531a(z ? "JAVA_CRASH" : "JAVA_CATCH", C5942z.m3852a(), this.f7966d.f7756d, thread.getName(), C5942z.m3857a(th), null);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f7967e;
                    if (uncaughtExceptionHandler2 != null && m3574a(uncaughtExceptionHandler2)) {
                        C5940x.m3825e("sys default last handle start!", new Object[0]);
                        this.f7967e.uncaughtException(thread, th);
                        C5940x.m3825e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f7968f != null) {
                        C5940x.m3825e("system handle start!", new Object[0]);
                        this.f7968f.uncaughtException(thread, th);
                        C5940x.m3825e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C5940x.m3825e("crashreport last handle start!", new Object[0]);
                        C5940x.m3825e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C5940x.m3825e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            CrashDetailBean m3576b = m3576b(thread, th, z, str, bArr);
            if (m3576b == null) {
                C5940x.m3825e("pkg crash datas fail!", new Object[0]);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler3 = this.f7967e;
                    if (uncaughtExceptionHandler3 != null && m3574a(uncaughtExceptionHandler3)) {
                        C5940x.m3825e("sys default last handle start!", new Object[0]);
                        this.f7967e.uncaughtException(thread, th);
                        C5940x.m3825e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f7968f != null) {
                        C5940x.m3825e("system handle start!", new Object[0]);
                        this.f7968f.uncaughtException(thread, th);
                        C5940x.m3825e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C5940x.m3825e("crashreport last handle start!", new Object[0]);
                        C5940x.m3825e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C5940x.m3825e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            C5886b.m3531a(z ? "JAVA_CRASH" : "JAVA_CATCH", C5942z.m3852a(), this.f7966d.f7756d, thread.getName(), C5942z.m3857a(th), m3576b);
            if (!this.f7964b.m3542a(m3576b)) {
                this.f7964b.m3540a(m3576b, SolicitService.CAMERA_OPEN_TIME_OUT, z);
            }
            if (z) {
                this.f7964b.m3544b(m3576b);
            }
            if (z) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler4 = this.f7967e;
                if (uncaughtExceptionHandler4 != null && m3574a(uncaughtExceptionHandler4)) {
                    C5940x.m3825e("sys default last handle start!", new Object[0]);
                    this.f7967e.uncaughtException(thread, th);
                    C5940x.m3825e("sys default last handle end!", new Object[0]);
                } else if (this.f7968f != null) {
                    C5940x.m3825e("system handle start!", new Object[0]);
                    this.f7968f.uncaughtException(thread, th);
                    C5940x.m3825e("system handle end!", new Object[0]);
                } else {
                    C5940x.m3825e("crashreport last handle start!", new Object[0]);
                    C5940x.m3825e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    C5940x.m3825e("crashreport last handle end!", new Object[0]);
                }
            }
        } catch (Throwable th2) {
            try {
                if (!C5940x.m3819a(th2)) {
                    th2.printStackTrace();
                }
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler5 = this.f7967e;
                    if (uncaughtExceptionHandler5 != null && m3574a(uncaughtExceptionHandler5)) {
                        C5940x.m3825e("sys default last handle start!", new Object[0]);
                        this.f7967e.uncaughtException(thread, th);
                        C5940x.m3825e("sys default last handle end!", new Object[0]);
                    } else if (this.f7968f != null) {
                        C5940x.m3825e("system handle start!", new Object[0]);
                        this.f7968f.uncaughtException(thread, th);
                        C5940x.m3825e("system handle end!", new Object[0]);
                    } else {
                        C5940x.m3825e("crashreport last handle start!", new Object[0]);
                        C5940x.m3825e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C5940x.m3825e("crashreport last handle end!", new Object[0]);
                    }
                }
            } catch (Throwable th3) {
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler6 = this.f7967e;
                    if (uncaughtExceptionHandler6 != null && m3574a(uncaughtExceptionHandler6)) {
                        C5940x.m3825e("sys default last handle start!", new Object[0]);
                        this.f7967e.uncaughtException(thread, th);
                        C5940x.m3825e("sys default last handle end!", new Object[0]);
                    } else if (this.f7968f != null) {
                        C5940x.m3825e("system handle start!", new Object[0]);
                        this.f7968f.uncaughtException(thread, th);
                        C5940x.m3825e("system handle end!", new Object[0]);
                    } else {
                        C5940x.m3825e("crashreport last handle start!", new Object[0]);
                        C5940x.m3825e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C5940x.m3825e("crashreport last handle end!", new Object[0]);
                    }
                }
                throw th3;
            }
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (f7962i) {
            m3580a(thread, th, true, null, null);
        }
    }

    /* renamed from: a */
    private static boolean m3574a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && "uncaughtException".equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public final synchronized void m3579a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f7790g != this.f7969g) {
                C5940x.m3818a("java changed to %b", Boolean.valueOf(strategyBean.f7790g));
                if (strategyBean.f7790g) {
                    m3578a();
                    return;
                }
                m3581b();
            }
        }
    }

    /* renamed from: a */
    private static String m3573a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return sb.toString();
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
        } catch (Throwable th2) {
            C5940x.m3825e("gen stack error %s", th2.toString());
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static String m3577b(Throwable th, int i) {
        if (th.getMessage() == null) {
            return "";
        }
        if (th.getMessage().length() <= 1000) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, 1000) + "\n[Message over limit size:1000, has been cutted!]";
    }
}
