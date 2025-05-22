package com.google.api.gax.core;

import com.google.api.gax.core.AutoValue_InstantiatingExecutorProvider;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class InstantiatingExecutorProvider implements ExecutorProvider {
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() { // from class: com.google.api.gax.core.InstantiatingExecutorProvider.1
        private final AtomicInteger threadCount = new AtomicInteger();

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("Gax-" + this.threadCount.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract InstantiatingExecutorProvider build();

        public abstract int getExecutorThreadCount();

        public abstract ThreadFactory getThreadFactory();

        public abstract Builder setExecutorThreadCount(int i);

        public abstract Builder setThreadFactory(ThreadFactory threadFactory);
    }

    public abstract int getExecutorThreadCount();

    public abstract ThreadFactory getThreadFactory();

    @Override // com.google.api.gax.core.ExecutorProvider
    public boolean shouldAutoClose() {
        return true;
    }

    public abstract Builder toBuilder();

    @Override // com.google.api.gax.core.ExecutorProvider
    public ScheduledExecutorService getExecutor() {
        return new ScheduledThreadPoolExecutor(getExecutorThreadCount(), getThreadFactory());
    }

    public static Builder newBuilder() {
        return new AutoValue_InstantiatingExecutorProvider.Builder().setExecutorThreadCount(Math.max(4, Runtime.getRuntime().availableProcessors())).setThreadFactory(DEFAULT_THREAD_FACTORY);
    }
}
