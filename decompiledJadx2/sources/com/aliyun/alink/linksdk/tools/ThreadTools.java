package com.aliyun.alink.linksdk.tools;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ThreadTools {
    private static final String TAG = "ThreadTools";
    private static LoopHandler uiThreadHandler = new LoopHandler(Looper.getMainLooper());
    private static ExecutorService executorService = null;

    public static void runOnUiThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        uiThreadHandler.enqueue(runnable);
    }

    public static String getProcessName(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == i) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    public static boolean isAppBroughtToBackgroundByTask(Application application) {
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) application.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks.isEmpty()) {
                return false;
            }
            ComponentName componentName = runningTasks.get(0).topActivity;
            return !componentName.getPackageName().equals(application.getPackageName());
        } catch (Exception unused) {
            return true;
        }
    }

    public static void submitTask(Runnable runnable, boolean z) {
        submitTask(runnable, z, 0);
    }

    public static void submitTask(Runnable runnable, boolean z, int i) {
        if (z) {
            getUIThreadHandler().enqueue(runnable, i);
        } else {
            getExecutorService().submit(runnable);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class LoopHandler extends Handler {
        private static final String TAG = "ThreadTools_ThreadTools";

        public LoopHandler(Looper looper) {
            super(looper);
        }

        public void enqueue(Runnable runnable) {
            enqueue(runnable, 0);
        }

        public void enqueue(Runnable runnable, int i) {
            Message obtainMessage = obtainMessage();
            obtainMessage.obj = runnable;
            sendMessageDelayed(obtainMessage, i);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null || message.obj == null || !(message.obj instanceof Runnable)) {
                return;
            }
            try {
                ((Runnable) message.obj).run();
            } catch (Exception e) {
                ALog.m480e(TAG, "run task error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static ExecutorService getExecutorService() {
        if (executorService == null) {
            createThreadPool();
        }
        return executorService;
    }

    private static LoopHandler getUIThreadHandler() {
        if (uiThreadHandler == null) {
            uiThreadHandler = new LoopHandler(Looper.myLooper());
        }
        return uiThreadHandler;
    }

    private static void createThreadPool() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors > 10) {
            executorService = new ThreadPoolExecutor(availableProcessors, availableProcessors + 7, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(15), new ALXHDefaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        } else {
            executorService = new ThreadPoolExecutor(availableProcessors, 15, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(15), new ALXHDefaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ALXHDefaultThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        ALXHDefaultThreadFactory() {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + this.poolNumber.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            Process.setThreadPriority(10);
            return thread;
        }
    }
}
