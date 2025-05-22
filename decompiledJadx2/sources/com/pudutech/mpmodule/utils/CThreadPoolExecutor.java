package com.pudutech.mpmodule.utils;

import android.os.Handler;
import android.os.Looper;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CThreadPoolExecutor {
    private static final long KEEP_ALIVE_TIME = 30;
    private static final int WAIT_COUNT = 128;
    private static Handler mainHandler;
    private static Thread mainThread;
    private static HashMap<Runnable, Runnable> mapToMainHandler;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = (CPU_COUNT * 2) + 1;
    private static ThreadPoolExecutor pool = createThreadPoolExecutor();
    private static ExecutorService jobsForUI = Executors.newFixedThreadPool(CORE_POOL_SIZE, new CThreadFactory("CJobsForUI", 4));

    static /* synthetic */ ThreadPoolExecutor access$200() {
        return createThreadPoolExecutor();
    }

    static {
        Looper mainLooper = Looper.getMainLooper();
        mainThread = mainLooper.getThread();
        mainHandler = new Handler(mainLooper);
        mapToMainHandler = new HashMap<>();
    }

    private static ThreadPoolExecutor createThreadPoolExecutor() {
        if (pool == null) {
            pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new CThreadFactory("CThreadPool", 3), new CHandlerException());
        }
        return pool;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class CThreadFactory implements ThreadFactory {
        private AtomicInteger counter;
        private String prefix;
        private int priority;

        public CThreadFactory(String str, int i) {
            this.counter = new AtomicInteger(1);
            this.prefix = "";
            this.priority = 5;
            this.prefix = str;
            this.priority = i;
        }

        public CThreadFactory(String str) {
            this.counter = new AtomicInteger(1);
            this.prefix = "";
            this.priority = 5;
            this.prefix = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.prefix + " #" + this.counter.getAndIncrement());
            thread.setDaemon(true);
            thread.setPriority(this.priority);
            return thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class CHandlerException extends ThreadPoolExecutor.AbortPolicy {
        private CHandlerException() {
        }

        @Override // java.util.concurrent.ThreadPoolExecutor.AbortPolicy, java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Pdlog.m3273d("CThreadPoolExecutor", "rejectedExecution:" + runnable);
            Pdlog.m3274e("CThreadPoolExecutor", CThreadPoolExecutor.logAllThreadStackTrace().toString());
            if (!CThreadPoolExecutor.pool.isShutdown()) {
                CThreadPoolExecutor.pool.shutdown();
                ThreadPoolExecutor unused = CThreadPoolExecutor.pool = null;
            }
            ThreadPoolExecutor unused2 = CThreadPoolExecutor.pool = CThreadPoolExecutor.access$200();
        }
    }

    public static void startConsumer(final Runnable runnable, final String str) {
        runInBackground(new Runnable() { // from class: com.pudutech.mpmodule.utils.CThreadPoolExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                new CThreadFactory(str, 2).newThread(runnable).start();
            }
        });
    }

    public static <T> Future<T> submitTask(Callable<T> callable) {
        return jobsForUI.submit(callable);
    }

    public static <T> void cancelTask(Future<T> future) {
        if (future != null) {
            future.cancel(true);
        }
    }

    public static <T> T getFromTask(Future<T> future, String str, String str2) {
        String str3;
        try {
            return future.get();
        } catch (Exception e) {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            if (str2 != null) {
                str3 = str2 + ": ";
            } else {
                str3 = "";
            }
            sb.append(str3);
            sb.append(e.toString());
            objArr[0] = sb.toString();
            Pdlog.m3274e(str, objArr);
            return null;
        }
    }

    public static void runInBackground(Runnable runnable) {
        if (pool == null) {
            createThreadPoolExecutor();
        }
        pool.execute(runnable);
    }

    public static boolean isOnMainThread() {
        return mainThread == Thread.currentThread();
    }

    public static void runOnMainThread(Runnable runnable) {
        if (isOnMainThread()) {
            runnable.run();
        } else {
            mainHandler.post(runnable);
        }
    }

    public static void runOnMainThread(Runnable runnable, long j) {
        if (j <= 0) {
            runOnMainThread(runnable);
        } else {
            mainHandler.postDelayed(runnable, j);
        }
    }

    public static void runInBackground(final Runnable runnable, long j) {
        if (j <= 0) {
            runInBackground(runnable);
            return;
        }
        Runnable runnable2 = new Runnable() { // from class: com.pudutech.mpmodule.utils.CThreadPoolExecutor.2
            @Override // java.lang.Runnable
            public void run() {
                CThreadPoolExecutor.mapToMainHandler.remove(runnable);
                CThreadPoolExecutor.pool.execute(runnable);
            }
        };
        mapToMainHandler.put(runnable, runnable2);
        mainHandler.postDelayed(runnable2, j);
    }

    public static void removeCallbackOnMainThread(Runnable runnable) {
        mainHandler.removeCallbacks(runnable);
    }

    public static void removeCallbackInBackground(Runnable runnable) {
        Runnable runnable2 = mapToMainHandler.get(runnable);
        if (runnable2 != null) {
            mainHandler.removeCallbacks(runnable2);
        }
    }

    public static void logStatus() {
        Pdlog.m3273d("CThreadPoolExecutor", "getActiveCount" + pool.getActiveCount() + "\ngetTaskCount" + pool.getTaskCount() + "\ngetCompletedTaskCount" + pool.getCompletedTaskCount());
    }

    public static StringBuilder logAllThreadStackTrace() {
        StringBuilder sb = new StringBuilder();
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Thread thread : allStackTraces.keySet()) {
            sb.append("Thread ");
            sb.append(thread.getName());
            sb.append("\n");
            for (StackTraceElement stackTraceElement : allStackTraces.get(thread)) {
                sb.append("\tat ");
                sb.append(stackTraceElement);
                sb.append("\n");
            }
        }
        return sb;
    }

    public static void main(String[] strArr) {
        for (final int i = 0; i < 10000; i++) {
            System.out.println("index=" + i);
            runInBackground(new Runnable() { // from class: com.pudutech.mpmodule.utils.CThreadPoolExecutor.3
                @Override // java.lang.Runnable
                public void run() {
                    System.out.println("正在运行第[" + (i + 1) + "]个线程.");
                }
            });
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
