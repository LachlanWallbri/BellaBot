package io.netty.util.concurrent;

import io.netty.util.internal.InternalThreadLocalMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class FastThreadLocalThread extends Thread {
    private final boolean cleanupFastThreadLocals;
    private InternalThreadLocalMap threadLocalMap;

    public FastThreadLocalThread() {
        this.cleanupFastThreadLocals = false;
    }

    public FastThreadLocalThread(Runnable runnable) {
        super(FastThreadLocalRunnable.wrap(runnable));
        this.cleanupFastThreadLocals = true;
    }

    public FastThreadLocalThread(ThreadGroup threadGroup, Runnable runnable) {
        super(threadGroup, FastThreadLocalRunnable.wrap(runnable));
        this.cleanupFastThreadLocals = true;
    }

    public FastThreadLocalThread(String str) {
        super(str);
        this.cleanupFastThreadLocals = false;
    }

    public FastThreadLocalThread(ThreadGroup threadGroup, String str) {
        super(threadGroup, str);
        this.cleanupFastThreadLocals = false;
    }

    public FastThreadLocalThread(Runnable runnable, String str) {
        super(FastThreadLocalRunnable.wrap(runnable), str);
        this.cleanupFastThreadLocals = true;
    }

    public FastThreadLocalThread(ThreadGroup threadGroup, Runnable runnable, String str) {
        super(threadGroup, FastThreadLocalRunnable.wrap(runnable), str);
        this.cleanupFastThreadLocals = true;
    }

    public FastThreadLocalThread(ThreadGroup threadGroup, Runnable runnable, String str, long j) {
        super(threadGroup, FastThreadLocalRunnable.wrap(runnable), str, j);
        this.cleanupFastThreadLocals = true;
    }

    public final InternalThreadLocalMap threadLocalMap() {
        return this.threadLocalMap;
    }

    public final void setThreadLocalMap(InternalThreadLocalMap internalThreadLocalMap) {
        this.threadLocalMap = internalThreadLocalMap;
    }

    public boolean willCleanupFastThreadLocals() {
        return this.cleanupFastThreadLocals;
    }

    public static boolean willCleanupFastThreadLocals(Thread thread) {
        return (thread instanceof FastThreadLocalThread) && ((FastThreadLocalThread) thread).willCleanupFastThreadLocals();
    }
}
