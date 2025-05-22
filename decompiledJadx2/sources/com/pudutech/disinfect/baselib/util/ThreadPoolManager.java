package com.pudutech.disinfect.baselib.util;

import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class ThreadPoolManager {
    private static final String TAG = ThreadPoolManager.class.getSimpleName();
    private ExecutorService mInitExecutor;
    private ExecutorService mSimpleExecutor;

    private ThreadPoolManager() {
        this.mSimpleExecutor = new RobotExecutor(0, 1, 3L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new MyThreadFactory("SimpleTaskPool"));
        this.mInitExecutor = new RobotExecutor(0, 1, 3L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new MyThreadFactory("InitExecutor"));
    }

    public static ThreadPoolManager getInstance() {
        return TaskExecManagerHolder.sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logException(Runnable runnable, Throwable th) {
        if (runnable == null || !(runnable instanceof Future)) {
            return;
        }
        Future future = (Future) runnable;
        if (future.isDone()) {
            try {
                future.get();
            } catch (InterruptedException | CancellationException unused) {
            } catch (ExecutionException e) {
                Log.e(TAG, "exception occured. runnalbe:" + runnable.toString());
                Log.e(TAG, e.getCause().toString());
            } catch (Exception e2) {
                Log.e(TAG, "exception occured. runnalbe:" + runnable.toString());
                Log.e(TAG, e2.toString());
            }
        }
    }

    public void execSimpleTask(Runnable runnable) {
        this.mSimpleExecutor.submit(runnable);
    }

    public <T> Future<T> execSimpleTask(Callable<T> callable) {
        return this.mSimpleExecutor.submit(callable);
    }

    public void execInitTask(Runnable runnable) {
        this.mInitExecutor.submit(runnable);
    }

    public void stop() {
        this.mSimpleExecutor.shutdown();
        try {
            this.mSimpleExecutor.awaitTermination(3L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    private class MyThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        MyThreadFactory(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = str + "-" + this.poolNumber.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(10);
            return thread;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    private static class TaskExecManagerHolder {
        private static ThreadPoolManager sInstance = new ThreadPoolManager();

        private TaskExecManagerHolder() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    private static class RobotExecutor extends ThreadPoolExecutor {
        public RobotExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
            super(i, i2, j, timeUnit, blockingQueue, threadFactory);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void afterExecute(Runnable runnable, Throwable th) {
            super.afterExecute(runnable, th);
            ThreadPoolManager.logException(runnable, th);
        }
    }
}
