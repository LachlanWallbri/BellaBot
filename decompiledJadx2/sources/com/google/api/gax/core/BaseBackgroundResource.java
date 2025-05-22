package com.google.api.gax.core;

import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class BaseBackgroundResource implements BackgroundResource {
    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return false;
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return false;
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
    }

    @Override // java.lang.AutoCloseable
    public void close() throws Exception {
        shutdown();
    }
}
