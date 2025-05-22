package org.jboss.netty.util.internal;

import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public class UnterminatableExecutor implements Executor {
    private final Executor executor;

    public UnterminatableExecutor(Executor executor) {
        if (executor == null) {
            throw new NullPointerException("executor");
        }
        this.executor = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.executor.execute(runnable);
    }
}
