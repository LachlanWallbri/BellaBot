package com.google.api.gax.core;

import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ExecutorAsBackgroundResource implements BackgroundResource {
    private final ExecutorService executor;

    public ExecutorAsBackgroundResource(ExecutorService executorService) {
        this.executor = (ExecutorService) Preconditions.checkNotNull(executorService);
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        this.executor.shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return this.executor.isShutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return this.executor.isTerminated();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        this.executor.shutdownNow();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.executor.awaitTermination(j, timeUnit);
    }

    @Override // java.lang.AutoCloseable
    public void close() throws Exception {
        shutdown();
    }
}
