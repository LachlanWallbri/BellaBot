package com.google.common.util.concurrent;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ListeningScheduledExecutorService extends ScheduledExecutorService, ListeningExecutorService {
    @Override // java.util.concurrent.ScheduledExecutorService
    ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit);

    default ListenableScheduledFuture<?> schedule(Runnable runnable, Duration duration) {
        return schedule(runnable, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    default <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, Duration duration) {
        return schedule((Callable) callable, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    default ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, Duration duration, Duration duration2) {
        return scheduleAtFixedRate(runnable, Internal.toNanosSaturated(duration), Internal.toNanosSaturated(duration2), TimeUnit.NANOSECONDS);
    }

    default ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, Duration duration, Duration duration2) {
        return scheduleWithFixedDelay(runnable, Internal.toNanosSaturated(duration), Internal.toNanosSaturated(duration2), TimeUnit.NANOSECONDS);
    }
}
