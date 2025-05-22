package com.loc;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SDKLogHandler.java */
/* renamed from: com.loc.z */
/* loaded from: classes4.dex */
public final class C3900z extends C3897w implements Thread.UncaughtExceptionHandler {

    /* renamed from: e */
    private static ExecutorService f4352e;

    /* renamed from: f */
    private static Set<Integer> f4353f = Collections.synchronizedSet(new HashSet());

    /* renamed from: g */
    private static final ThreadFactory f4354g = new ThreadFactory() { // from class: com.loc.z.2

        /* renamed from: a */
        private final AtomicInteger f4360a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.f4360a.getAndIncrement());
        }
    };

    /* renamed from: d */
    private Context f4355d;

    /* compiled from: SDKLogHandler.java */
    /* renamed from: com.loc.z$a */
    /* loaded from: classes4.dex */
    private static class a implements InterfaceC3838bm {

        /* renamed from: a */
        private Context f4361a;

        a(Context context) {
            this.f4361a = context;
        }

        @Override // com.loc.InterfaceC3838bm
        /* renamed from: a */
        public final void mo2625a() {
            try {
                C3898x.m3259b(this.f4361a);
            } catch (Throwable th) {
                C3897w.m3249a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }

    private C3900z(Context context) {
        this.f4355d = context;
        C3837bl.m2619a(new a(context));
        try {
            this.f4330b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.f4330b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f4331c = true;
                return;
            }
            String obj = this.f4330b.toString();
            if (obj.indexOf("com.amap.api") == -1 && obj.indexOf("com.loc") == -1) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f4331c = true;
                return;
            }
            this.f4331c = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static synchronized C3900z m3263a(Context context, C3893s c3893s) throws C3884j {
        synchronized (C3900z.class) {
            try {
                if (c3893s == null) {
                    throw new C3884j("sdk info is null");
                }
                if (c3893s.m3206a() == null || "".equals(c3893s.m3206a())) {
                    throw new C3884j("sdk name is invalid");
                }
                try {
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                if (!f4353f.add(Integer.valueOf(c3893s.hashCode()))) {
                    return (C3900z) C3897w.f4329a;
                }
                if (C3897w.f4329a == null) {
                    C3897w.f4329a = new C3900z(context);
                } else {
                    C3897w.f4329a.f4331c = false;
                }
                C3897w.f4329a.mo3250a(context, c3893s, C3897w.f4329a.f4331c);
                return (C3900z) C3897w.f4329a;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m3264a() {
        synchronized (C3900z.class) {
            try {
                if (f4352e != null) {
                    f4352e.shutdown();
                }
                C3830be.m2545a();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (C3897w.f4329a != null && Thread.getDefaultUncaughtExceptionHandler() == C3897w.f4329a && C3897w.f4329a.f4330b != null) {
                    Thread.setDefaultUncaughtExceptionHandler(C3897w.f4329a.f4330b);
                }
                C3897w.f4329a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static synchronized ExecutorService m3265b() {
        synchronized (C3900z.class) {
            try {
                if (f4352e == null || f4352e.isShutdown()) {
                    f4352e = Executors.newSingleThreadExecutor(f4354g);
                }
            } finally {
                return f4352e;
            }
        }
        return f4352e;
    }

    /* renamed from: b */
    public static void m3266b(C3893s c3893s, String str, String str2) {
        if (C3897w.f4329a != null) {
            C3897w.f4329a.mo3251a(c3893s, str, str2);
        }
    }

    /* renamed from: b */
    public static void m3267b(Throwable th, String str, String str2) {
        if (C3897w.f4329a != null) {
            C3897w.f4329a.mo3252a(th, 1, str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.loc.C3897w
    /* renamed from: a */
    public final void mo3250a(final Context context, final C3893s c3893s, final boolean z) {
        try {
            ExecutorService m3265b = m3265b();
            if (m3265b != null && !m3265b.isShutdown()) {
                m3265b.submit(new Runnable() { // from class: com.loc.z.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new C3814ap(context, true).m2456a(c3893s);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    C3815aq c3815aq = new C3815aq(context);
                                    C3816ar c3816ar = new C3816ar();
                                    c3816ar.m2464c(true);
                                    c3816ar.m2460a(true);
                                    c3816ar.m2462b(true);
                                    c3815aq.m2459a(c3816ar);
                                }
                                C3898x.m3255a(C3900z.this.f4355d);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException unused) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.loc.C3897w
    /* renamed from: a */
    public final void mo3251a(C3893s c3893s, String str, String str2) {
        C3898x.m3256a(this.f4355d, c3893s, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.loc.C3897w
    /* renamed from: a */
    public final void mo3252a(Throwable th, int i, String str, String str2) {
        C3898x.m3257a(this.f4355d, th, i, str, str2);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (th == null) {
            return;
        }
        mo3252a(th, 0, null, null);
        if (this.f4330b != null) {
            try {
                Thread.setDefaultUncaughtExceptionHandler(this.f4330b);
            } catch (Throwable unused) {
            }
            this.f4330b.uncaughtException(thread, th);
        }
    }
}
