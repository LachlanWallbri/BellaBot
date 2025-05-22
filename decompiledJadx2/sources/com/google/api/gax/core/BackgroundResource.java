package com.google.api.gax.core;

import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface BackgroundResource extends AutoCloseable {
    boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException;

    boolean isShutdown();

    boolean isTerminated();

    void shutdown();

    void shutdownNow();
}
