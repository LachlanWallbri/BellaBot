package com.aliyun.alink.apiclient.threadpool;

import com.http.utils.LogUtils;
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
    private static final String TAG = "[ITC]ThreadPool";
    private static ExecutorService executorService;
    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    private static void init() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        LogUtils.print(TAG, "ThreadPoolcore maybe <0 which will cause crash in specific platform: " + availableProcessors);
        if (availableProcessors <= 0) {
            availableProcessors = 1;
        }
        int max = Math.max(4, availableProcessors);
        int min = Math.min(10, availableProcessors * 2);
        if (min < max) {
            max = min;
        }
        LogUtils.print("ThreadPool Start a ThreadPool with scale between " + max + " -> " + min + "and core:" + availableProcessors);
        executorService = new ThreadPoolExecutor(max, min, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(max, new DefaultThreadFactory());
    }

    public static void execute(Runnable runnable) {
        if (executorService == null) {
            init();
        }
        executorService.execute(runnable);
    }

    public static Future<?> submit(Runnable runnable) {
        if (executorService == null) {
            init();
        }
        return executorService.submit(runnable);
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        if (executorService == null) {
            init();
        }
        return executorService.submit(callable);
    }

    public static <T> Future<T> schedule(Callable<T> callable, long j, TimeUnit timeUnit) {
        if (scheduledThreadPoolExecutor == null) {
            init();
        }
        return scheduledThreadPoolExecutor.schedule(callable, j, timeUnit);
    }

    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        if (scheduledThreadPoolExecutor == null) {
            init();
        }
        return scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, j, j2, timeUnit);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class DefaultThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public DefaultThreadFactory() {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.group = threadGroup;
            this.namePrefix = "Shared-" + this.poolNumber.getAndIncrement() + "-t-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }
}
