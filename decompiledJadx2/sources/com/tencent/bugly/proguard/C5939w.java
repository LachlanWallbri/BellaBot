package com.tencent.bugly.proguard;

import com.tencent.bugly.C5865b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.w */
/* loaded from: classes7.dex */
public final class C5939w {

    /* renamed from: a */
    private static final AtomicInteger f8269a = new AtomicInteger(1);

    /* renamed from: b */
    private static C5939w f8270b;

    /* renamed from: c */
    private ScheduledExecutorService f8271c;

    protected C5939w() {
        this.f8271c = null;
        this.f8271c = Executors.newScheduledThreadPool(3, new ThreadFactory(this) { // from class: com.tencent.bugly.proguard.w.1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BuglyThread-" + C5939w.f8269a.getAndIncrement());
                return thread;
            }
        });
        ScheduledExecutorService scheduledExecutorService = this.f8271c;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            C5940x.m3824d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    /* renamed from: a */
    public static synchronized C5939w m3810a() {
        C5939w c5939w;
        synchronized (C5939w.class) {
            if (f8270b == null) {
                f8270b = new C5939w();
            }
            c5939w = f8270b;
        }
        return c5939w;
    }

    /* renamed from: a */
    public final synchronized boolean m3813a(Runnable runnable, long j) {
        if (!m3815c()) {
            C5940x.m3824d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (runnable == null) {
            C5940x.m3824d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        }
        if (j <= 0) {
            j = 0;
        }
        C5940x.m3823c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
        try {
            this.f8271c.schedule(runnable, j, TimeUnit.MILLISECONDS);
            return true;
        } catch (Throwable th) {
            if (C5865b.f7644c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: a */
    public final synchronized boolean m3812a(Runnable runnable) {
        if (!m3815c()) {
            C5940x.m3824d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (runnable == null) {
            C5940x.m3824d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        }
        C5940x.m3823c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
        try {
            this.f8271c.execute(runnable);
            return true;
        } catch (Throwable th) {
            if (C5865b.f7644c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: b */
    public final synchronized void m3814b() {
        if (this.f8271c != null && !this.f8271c.isShutdown()) {
            C5940x.m3823c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.f8271c.shutdownNow();
        }
    }

    /* renamed from: c */
    public final synchronized boolean m3815c() {
        boolean z;
        if (this.f8271c != null) {
            z = this.f8271c.isShutdown() ? false : true;
        }
        return z;
    }
}
