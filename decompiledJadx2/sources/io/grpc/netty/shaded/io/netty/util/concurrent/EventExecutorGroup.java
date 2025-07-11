package io.grpc.netty.shaded.io.netty.util.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface EventExecutorGroup extends ScheduledExecutorService, Iterable<EventExecutor> {
    boolean isShuttingDown();

    Iterator<EventExecutor> iterator();

    EventExecutor next();

    @Override // java.util.concurrent.ScheduledExecutorService
    ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit);

    @Override // java.util.concurrent.ScheduledExecutorService
    ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit);

    @Override // java.util.concurrent.ExecutorService
    @Deprecated
    void shutdown();

    Future<?> shutdownGracefully();

    Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit);

    @Deprecated
    List<Runnable> shutdownNow();

    Future<?> submit(Runnable runnable);

    <T> Future<T> submit(Runnable runnable, T t);

    <T> Future<T> submit(Callable<T> callable);

    Future<?> terminationFuture();
}
