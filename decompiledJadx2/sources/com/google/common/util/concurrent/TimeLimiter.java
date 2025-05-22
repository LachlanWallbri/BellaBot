package com.google.common.util.concurrent;

import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use FakeTimeLimiter")
/* loaded from: classes3.dex */
public interface TimeLimiter {
    <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws TimeoutException, ExecutionException;

    <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws TimeoutException, InterruptedException, ExecutionException;

    <T> T newProxy(T t, Class<T> cls, long j, TimeUnit timeUnit);

    void runUninterruptiblyWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) throws TimeoutException;

    void runWithTimeout(Runnable runnable, long j, TimeUnit timeUnit) throws TimeoutException, InterruptedException;

    default <T> T newProxy(T t, Class<T> cls, Duration duration) {
        return (T) newProxy(t, cls, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    default <T> T callWithTimeout(Callable<T> callable, Duration duration) throws TimeoutException, InterruptedException, ExecutionException {
        return (T) callWithTimeout(callable, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    default <T> T callUninterruptiblyWithTimeout(Callable<T> callable, Duration duration) throws TimeoutException, ExecutionException {
        return (T) callUninterruptiblyWithTimeout(callable, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    default void runWithTimeout(Runnable runnable, Duration duration) throws TimeoutException, InterruptedException {
        runWithTimeout(runnable, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    default void runUninterruptiblyWithTimeout(Runnable runnable, Duration duration) throws TimeoutException {
        runUninterruptiblyWithTimeout(runnable, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }
}
