package io.grpc.netty.shaded.io.netty.util.concurrent;

import io.grpc.netty.shaded.io.netty.util.internal.DefaultPriorityQueue;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractScheduledEventExecutor extends AbstractEventExecutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<ScheduledFutureTask<?>> SCHEDULED_FUTURE_TASK_COMPARATOR = new Comparator<ScheduledFutureTask<?>>() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.AbstractScheduledEventExecutor.1
        @Override // java.util.Comparator
        public int compare(ScheduledFutureTask<?> scheduledFutureTask, ScheduledFutureTask<?> scheduledFutureTask2) {
            return scheduledFutureTask.compareTo((Delayed) scheduledFutureTask2);
        }
    };
    static final Runnable WAKEUP_TASK = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.AbstractScheduledEventExecutor.2
        @Override // java.lang.Runnable
        public void run() {
        }
    };
    long nextTaskId;
    PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue;

    protected boolean afterScheduledTaskSubmitted(long j) {
        return true;
    }

    protected boolean beforeScheduledTaskSubmitted(long j) {
        return true;
    }

    @Deprecated
    protected void validateScheduled(long j, TimeUnit timeUnit) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractScheduledEventExecutor() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractScheduledEventExecutor(EventExecutorGroup eventExecutorGroup) {
        super(eventExecutorGroup);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long nanoTime() {
        return ScheduledFutureTask.nanoTime();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long deadlineToDelayNanos(long j) {
        return ScheduledFutureTask.deadlineToDelayNanos(j);
    }

    protected static long initialNanoTime() {
        return ScheduledFutureTask.initialNanoTime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue() {
        if (this.scheduledTaskQueue == null) {
            this.scheduledTaskQueue = new DefaultPriorityQueue(SCHEDULED_FUTURE_TASK_COMPARATOR, 11);
        }
        return this.scheduledTaskQueue;
    }

    private static boolean isNullOrEmpty(Queue<ScheduledFutureTask<?>> queue) {
        return queue == null || queue.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cancelScheduledTasks() {
        PriorityQueue<ScheduledFutureTask<?>> priorityQueue = this.scheduledTaskQueue;
        if (isNullOrEmpty(priorityQueue)) {
            return;
        }
        for (ScheduledFutureTask scheduledFutureTask : (ScheduledFutureTask[]) priorityQueue.toArray(new ScheduledFutureTask[0])) {
            scheduledFutureTask.cancelWithoutRemove(false);
        }
        priorityQueue.clearIgnoringIndexes();
    }

    protected final Runnable pollScheduledTask() {
        return pollScheduledTask(nanoTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Runnable pollScheduledTask(long j) {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask == null || peekScheduledTask.deadlineNanos() - j > 0) {
            return null;
        }
        this.scheduledTaskQueue.remove();
        peekScheduledTask.setConsumed();
        return peekScheduledTask;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long nextScheduledTaskNano() {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask != null) {
            return peekScheduledTask.delayNanos();
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long nextScheduledTaskDeadlineNanos() {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask != null) {
            return peekScheduledTask.deadlineNanos();
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ScheduledFutureTask<?> peekScheduledTask() {
        PriorityQueue<ScheduledFutureTask<?>> priorityQueue = this.scheduledTaskQueue;
        if (priorityQueue != null) {
            return priorityQueue.peek();
        }
        return null;
    }

    protected final boolean hasScheduledTasks() {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        return peekScheduledTask != null && peekScheduledTask.deadlineNanos() <= nanoTime();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(runnable, "command");
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (j < 0) {
            j = 0;
        }
        validateScheduled0(j, timeUnit);
        return schedule(new ScheduledFutureTask(this, runnable, ScheduledFutureTask.deadlineNanos(timeUnit.toNanos(j))));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(callable, "callable");
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (j < 0) {
            j = 0;
        }
        validateScheduled0(j, timeUnit);
        return schedule(new ScheduledFutureTask<>(this, callable, ScheduledFutureTask.deadlineNanos(timeUnit.toNanos(j))));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(runnable, "command");
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (j < 0) {
            throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", Long.valueOf(j)));
        }
        if (j2 <= 0) {
            throw new IllegalArgumentException(String.format("period: %d (expected: > 0)", Long.valueOf(j2)));
        }
        validateScheduled0(j, timeUnit);
        validateScheduled0(j2, timeUnit);
        return schedule(new ScheduledFutureTask(this, runnable, ScheduledFutureTask.deadlineNanos(timeUnit.toNanos(j)), timeUnit.toNanos(j2)));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(runnable, "command");
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (j < 0) {
            throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", Long.valueOf(j)));
        }
        if (j2 <= 0) {
            throw new IllegalArgumentException(String.format("delay: %d (expected: > 0)", Long.valueOf(j2)));
        }
        validateScheduled0(j, timeUnit);
        validateScheduled0(j2, timeUnit);
        return schedule(new ScheduledFutureTask(this, runnable, ScheduledFutureTask.deadlineNanos(timeUnit.toNanos(j)), -timeUnit.toNanos(j2)));
    }

    private void validateScheduled0(long j, TimeUnit timeUnit) {
        validateScheduled(j, timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void scheduleFromEventLoop(ScheduledFutureTask<?> scheduledFutureTask) {
        PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue = scheduledTaskQueue();
        long j = this.nextTaskId + 1;
        this.nextTaskId = j;
        scheduledTaskQueue.add(scheduledFutureTask.setId(j));
    }

    private <V> ScheduledFuture<V> schedule(ScheduledFutureTask<V> scheduledFutureTask) {
        if (inEventLoop()) {
            scheduleFromEventLoop(scheduledFutureTask);
        } else {
            long deadlineNanos = scheduledFutureTask.deadlineNanos();
            if (beforeScheduledTaskSubmitted(deadlineNanos)) {
                execute(scheduledFutureTask);
            } else {
                lazyExecute(scheduledFutureTask);
                if (afterScheduledTaskSubmitted(deadlineNanos)) {
                    execute(WAKEUP_TASK);
                }
            }
        }
        return scheduledFutureTask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeScheduled(ScheduledFutureTask<?> scheduledFutureTask) {
        if (inEventLoop()) {
            scheduledTaskQueue().removeTyped(scheduledFutureTask);
        } else {
            lazyExecute(scheduledFutureTask);
        }
    }
}
