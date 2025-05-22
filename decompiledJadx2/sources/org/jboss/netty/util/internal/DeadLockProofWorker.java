package org.jboss.netty.util.internal;

import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public final class DeadLockProofWorker {
    public static final ThreadLocal<Executor> PARENT = new ThreadLocal<>();

    public static void start(final Executor executor, final Runnable runnable) {
        if (executor == null) {
            throw new NullPointerException("parent");
        }
        if (runnable == null) {
            throw new NullPointerException("runnable");
        }
        executor.execute(new Runnable() { // from class: org.jboss.netty.util.internal.DeadLockProofWorker.1
            @Override // java.lang.Runnable
            public void run() {
                DeadLockProofWorker.PARENT.set(executor);
                try {
                    runnable.run();
                } finally {
                    DeadLockProofWorker.PARENT.remove();
                }
            }
        });
    }

    private DeadLockProofWorker() {
    }
}
