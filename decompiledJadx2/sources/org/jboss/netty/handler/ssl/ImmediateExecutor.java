package org.jboss.netty.handler.ssl;

import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
final class ImmediateExecutor implements Executor {
    static final ImmediateExecutor INSTANCE = new ImmediateExecutor();

    ImmediateExecutor() {
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
