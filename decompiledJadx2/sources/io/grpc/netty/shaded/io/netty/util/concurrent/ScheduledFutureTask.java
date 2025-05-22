package io.grpc.netty.shaded.io.netty.util.concurrent;

import io.grpc.netty.shaded.io.netty.util.internal.DefaultPriorityQueue;
import io.grpc.netty.shaded.io.netty.util.internal.PriorityQueueNode;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ScheduledFutureTask<V> extends PromiseTask<V> implements ScheduledFuture<V>, PriorityQueueNode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long START_TIME = System.nanoTime();
    private long deadlineNanos;

    /* renamed from: id */
    private long f8431id;
    private final long periodNanos;
    private int queueIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long nanoTime() {
        return System.nanoTime() - START_TIME;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long deadlineNanos(long j) {
        long nanoTime = nanoTime() + j;
        if (nanoTime < 0) {
            return Long.MAX_VALUE;
        }
        return nanoTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long initialNanoTime() {
        return START_TIME;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Runnable runnable, long j) {
        super(abstractScheduledEventExecutor, runnable);
        this.queueIndex = -1;
        this.deadlineNanos = j;
        this.periodNanos = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Runnable runnable, long j, long j2) {
        super(abstractScheduledEventExecutor, runnable);
        this.queueIndex = -1;
        this.deadlineNanos = j;
        this.periodNanos = validatePeriod(j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Callable<V> callable, long j, long j2) {
        super(abstractScheduledEventExecutor, callable);
        this.queueIndex = -1;
        this.deadlineNanos = j;
        this.periodNanos = validatePeriod(j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Callable<V> callable, long j) {
        super(abstractScheduledEventExecutor, callable);
        this.queueIndex = -1;
        this.deadlineNanos = j;
        this.periodNanos = 0L;
    }

    private static long validatePeriod(long j) {
        if (j != 0) {
            return j;
        }
        throw new IllegalArgumentException("period: 0 (expected: != 0)");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScheduledFutureTask<V> setId(long j) {
        if (this.f8431id == 0) {
            this.f8431id = j;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
    public EventExecutor executor() {
        return super.executor();
    }

    public long deadlineNanos() {
        return this.deadlineNanos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConsumed() {
        if (this.periodNanos == 0) {
            this.deadlineNanos = 0L;
        }
    }

    public long delayNanos() {
        return deadlineToDelayNanos(deadlineNanos());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long deadlineToDelayNanos(long j) {
        if (j == 0) {
            return 0L;
        }
        return Math.max(0L, j - nanoTime());
    }

    public long delayNanos(long j) {
        if (this.deadlineNanos == 0) {
            return 0L;
        }
        return Math.max(0L, deadlineNanos() - (j - START_TIME));
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(delayNanos(), TimeUnit.NANOSECONDS);
    }

    @Override // java.lang.Comparable
    public int compareTo(Delayed delayed) {
        if (this == delayed) {
            return 0;
        }
        ScheduledFutureTask scheduledFutureTask = (ScheduledFutureTask) delayed;
        long deadlineNanos = deadlineNanos() - scheduledFutureTask.deadlineNanos();
        if (deadlineNanos < 0) {
            return -1;
        }
        return (deadlineNanos <= 0 && this.f8431id < scheduledFutureTask.f8431id) ? -1 : 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.PromiseTask, java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        try {
            if (delayNanos() > 0) {
                if (isCancelled()) {
                    scheduledExecutor().scheduledTaskQueue().removeTyped(this);
                    return;
                } else {
                    scheduledExecutor().scheduleFromEventLoop(this);
                    return;
                }
            }
            if (this.periodNanos == 0) {
                if (setUncancellableInternal()) {
                    setSuccessInternal(runTask());
                }
            } else {
                if (isCancelled()) {
                    return;
                }
                runTask();
                if (executor().isShutdown()) {
                    return;
                }
                if (this.periodNanos > 0) {
                    this.deadlineNanos += this.periodNanos;
                } else {
                    this.deadlineNanos = nanoTime() - this.periodNanos;
                }
                if (isCancelled()) {
                    return;
                }
                scheduledExecutor().scheduledTaskQueue().add(this);
            }
        } catch (Throwable th) {
            setFailureInternal(th);
        }
    }

    private AbstractScheduledEventExecutor scheduledExecutor() {
        return (AbstractScheduledEventExecutor) executor();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.PromiseTask, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        if (cancel) {
            scheduledExecutor().removeScheduled(this);
        }
        return cancel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean cancelWithoutRemove(boolean z) {
        return super.cancel(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.PromiseTask, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
    protected StringBuilder toStringBuilder() {
        StringBuilder stringBuilder = super.toStringBuilder();
        stringBuilder.setCharAt(stringBuilder.length() - 1, ',');
        stringBuilder.append(" deadline: ");
        stringBuilder.append(this.deadlineNanos);
        stringBuilder.append(", period: ");
        stringBuilder.append(this.periodNanos);
        stringBuilder.append(')');
        return stringBuilder;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.PriorityQueueNode
    public int priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue) {
        return this.queueIndex;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.PriorityQueueNode
    public void priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue, int i) {
        this.queueIndex = i;
    }
}
