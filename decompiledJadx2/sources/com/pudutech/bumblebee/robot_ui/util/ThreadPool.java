package com.pudutech.bumblebee.robot_ui.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class ThreadPool {
    private static ThreadPool instance;
    private final int EXECUTOR_THREADS = Runtime.getRuntime().availableProcessors();
    private ExecutorService cachedPools;
    private ExecutorService processorsPools;

    private ThreadPool() {
    }

    public static synchronized ThreadPool getInstance() {
        ThreadPool threadPool;
        synchronized (ThreadPool.class) {
            if (instance == null) {
                instance = new ThreadPool();
            }
            threadPool = instance;
        }
        return threadPool;
    }

    public ExecutorService getProcessorsPools() {
        if (this.processorsPools == null) {
            this.processorsPools = Executors.newFixedThreadPool(this.EXECUTOR_THREADS);
        }
        return this.processorsPools;
    }

    public ExecutorService getCachedPools() {
        if (this.cachedPools == null) {
            this.cachedPools = Executors.newCachedThreadPool();
        }
        return this.cachedPools;
    }
}
