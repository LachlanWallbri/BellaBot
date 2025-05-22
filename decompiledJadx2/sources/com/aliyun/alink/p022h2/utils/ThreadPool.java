package com.aliyun.alink.p022h2.utils;

import com.aliyun.alink.p022h2.p025b.C0879a;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ThreadPool {

    /* renamed from: a */
    private static ExecutorService f696a;

    /* renamed from: b */
    private static ScheduledThreadPoolExecutor f697b;

    /* renamed from: a */
    private static void m320a() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        C0879a.m206a("ThreadPool", "ThreadPoolcore maybe <0 which will cause crash in specific platform: " + availableProcessors);
        if (availableProcessors <= 0) {
            availableProcessors = 1;
        }
        int max = Math.max(4, availableProcessors);
        int min = Math.min(10, availableProcessors * 2);
        if (min < max) {
            max = min;
        }
        C0879a.m206a("ThreadPool", "ThreadPool Start a ThreadPool with scale between " + max + " -> " + min + "and core:" + availableProcessors);
        f696a = new ThreadPoolExecutor(max, min, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        f697b = new ScheduledThreadPoolExecutor(max, new DefaultThreadFactory());
    }

    public static void execute(Runnable runnable) {
        if (f696a == null) {
            m320a();
        }
        f696a.execute(runnable);
    }

    public static Future<?> submit(Runnable runnable) {
        if (f696a == null) {
            m320a();
        }
        return f696a.submit(runnable);
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        if (f696a == null) {
            m320a();
        }
        return f696a.submit(callable);
    }

    public static <T> Future<T> schedule(Callable<T> callable, long j, TimeUnit timeUnit) {
        if (f697b == null) {
            m320a();
        }
        return f697b.schedule(callable, j, timeUnit);
    }

    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        if (f697b == null) {
            m320a();
        }
        return f697b.scheduleAtFixedRate(runnable, j, j2, timeUnit);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class DefaultThreadFactory implements ThreadFactory {

        /* renamed from: b */
        private final ThreadGroup f699b;

        /* renamed from: d */
        private final String f701d;

        /* renamed from: a */
        private final AtomicInteger f698a = new AtomicInteger(1);

        /* renamed from: c */
        private final AtomicInteger f700c = new AtomicInteger(1);

        public DefaultThreadFactory() {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.f699b = threadGroup;
            this.f701d = "Shared-" + this.f698a.getAndIncrement() + "-t-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f699b, runnable, this.f701d + this.f700c.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }
}
