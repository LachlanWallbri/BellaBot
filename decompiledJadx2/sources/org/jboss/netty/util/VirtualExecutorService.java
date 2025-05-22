package org.jboss.netty.util;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class VirtualExecutorService extends AbstractExecutorService {

    /* renamed from: e */
    private final Executor f10053e;

    /* renamed from: s */
    private final ExecutorService f10054s;
    volatile boolean shutdown;
    final Object startStopLock = new Object();
    Set<Thread> activeThreads = new MapBackedSet(new IdentityHashMap());

    public VirtualExecutorService(Executor executor) {
        if (executor == null) {
            throw new NullPointerException("parent");
        }
        if (executor instanceof ExecutorService) {
            this.f10053e = null;
            this.f10054s = (ExecutorService) executor;
        } else {
            this.f10053e = executor;
            this.f10054s = null;
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        boolean z;
        synchronized (this.startStopLock) {
            z = this.shutdown;
        }
        return z;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        boolean z;
        synchronized (this.startStopLock) {
            z = this.shutdown && this.activeThreads.isEmpty();
        }
        return z;
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        synchronized (this.startStopLock) {
            if (this.shutdown) {
                return;
            }
            this.shutdown = true;
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        synchronized (this.startStopLock) {
            if (!isTerminated()) {
                shutdown();
                Iterator<Thread> it = this.activeThreads.iterator();
                while (it.hasNext()) {
                    it.next().interrupt();
                }
            }
        }
        return Collections.emptyList();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean isTerminated;
        synchronized (this.startStopLock) {
            while (!isTerminated()) {
                this.startStopLock.wait(TimeUnit.MILLISECONDS.convert(j, timeUnit));
            }
            isTerminated = isTerminated();
        }
        return isTerminated;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("command");
        }
        if (this.shutdown) {
            throw new RejectedExecutionException();
        }
        ExecutorService executorService = this.f10054s;
        if (executorService != null) {
            executorService.execute(new ChildExecutorRunnable(runnable));
        } else {
            this.f10053e.execute(new ChildExecutorRunnable(runnable));
        }
    }

    /* loaded from: classes7.dex */
    private class ChildExecutorRunnable implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Runnable runnable;

        ChildExecutorRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread currentThread = Thread.currentThread();
            synchronized (VirtualExecutorService.this.startStopLock) {
                VirtualExecutorService.this.activeThreads.add(currentThread);
            }
            try {
                this.runnable.run();
                synchronized (VirtualExecutorService.this.startStopLock) {
                    VirtualExecutorService.this.activeThreads.remove(currentThread);
                    if (VirtualExecutorService.this.isTerminated()) {
                        VirtualExecutorService.this.startStopLock.notifyAll();
                    }
                }
            } catch (Throwable th) {
                synchronized (VirtualExecutorService.this.startStopLock) {
                    VirtualExecutorService.this.activeThreads.remove(currentThread);
                    if (VirtualExecutorService.this.isTerminated()) {
                        VirtualExecutorService.this.startStopLock.notifyAll();
                    }
                    throw th;
                }
            }
        }
    }
}
