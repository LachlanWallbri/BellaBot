package io.grpc.netty.shaded.io.netty.util.concurrent;

import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadExecutorMap;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class SingleThreadEventExecutor extends AbstractScheduledEventExecutor implements OrderedEventExecutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ST_NOT_STARTED = 1;
    private static final int ST_SHUTDOWN = 4;
    private static final int ST_SHUTTING_DOWN = 3;
    private static final int ST_STARTED = 2;
    private static final int ST_TERMINATED = 5;
    private final boolean addTaskWakesUp;
    private final Executor executor;
    private volatile long gracefulShutdownQuietPeriod;
    private long gracefulShutdownStartTime;
    private volatile long gracefulShutdownTimeout;
    private volatile boolean interrupted;
    private long lastExecutionTime;
    private final int maxPendingTasks;
    private final RejectedExecutionHandler rejectedExecutionHandler;
    private final Set<Runnable> shutdownHooks;
    private volatile int state;
    private final Queue<Runnable> taskQueue;
    private final Promise<?> terminationFuture;
    private volatile Thread thread;
    private final CountDownLatch threadLock;
    private volatile ThreadProperties threadProperties;
    static final int DEFAULT_MAX_PENDING_EXECUTOR_TASKS = Math.max(16, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.eventexecutor.maxPendingTasks", Integer.MAX_VALUE));
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SingleThreadEventExecutor.class);
    private static final Runnable NOOP_TASK = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.1
        @Override // java.lang.Runnable
        public void run() {
        }
    };
    private static final AtomicIntegerFieldUpdater<SingleThreadEventExecutor> STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(SingleThreadEventExecutor.class, "state");
    private static final AtomicReferenceFieldUpdater<SingleThreadEventExecutor, ThreadProperties> PROPERTIES_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SingleThreadEventExecutor.class, ThreadProperties.class, "threadProperties");
    private static final long SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(1);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @Deprecated
    /* loaded from: classes7.dex */
    protected interface NonWakeupRunnable extends AbstractEventExecutor.LazyRunnable {
    }

    protected void afterRunningAllTasks() {
    }

    protected void cleanup() {
    }

    protected abstract void run();

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean wakesUpForTask(Runnable runnable) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, boolean z) {
        this(eventExecutorGroup, new ThreadPerTaskExecutor(threadFactory), z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, boolean z, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        this(eventExecutorGroup, new ThreadPerTaskExecutor(threadFactory), z, i, rejectedExecutionHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, boolean z) {
        this(eventExecutorGroup, executor, z, DEFAULT_MAX_PENDING_EXECUTOR_TASKS, RejectedExecutionHandlers.reject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, boolean z, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = z;
        this.maxPendingTasks = Math.max(16, i);
        this.executor = ThreadExecutorMap.apply(executor, this);
        this.taskQueue = newTaskQueue(this.maxPendingTasks);
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedExecutionHandler, "rejectedHandler");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, boolean z, Queue<Runnable> queue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = z;
        this.maxPendingTasks = DEFAULT_MAX_PENDING_EXECUTOR_TASKS;
        this.executor = ThreadExecutorMap.apply(executor, this);
        this.taskQueue = (Queue) ObjectUtil.checkNotNull(queue, "taskQueue");
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedExecutionHandler, "rejectedHandler");
    }

    @Deprecated
    protected Queue<Runnable> newTaskQueue() {
        return newTaskQueue(this.maxPendingTasks);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Queue<Runnable> newTaskQueue(int i) {
        return new LinkedBlockingQueue(i);
    }

    protected void interruptThread() {
        Thread thread = this.thread;
        if (thread == null) {
            this.interrupted = true;
        } else {
            thread.interrupt();
        }
    }

    protected Runnable pollTask() {
        return pollTaskFrom(this.taskQueue);
    }

    protected static Runnable pollTaskFrom(Queue<Runnable> queue) {
        Runnable poll;
        do {
            poll = queue.poll();
        } while (poll == WAKEUP_TASK);
        return poll;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Runnable takeTask() {
        Runnable runnable;
        Runnable runnable2;
        Queue<Runnable> queue = this.taskQueue;
        if (!(queue instanceof BlockingQueue)) {
            throw new UnsupportedOperationException();
        }
        BlockingQueue blockingQueue = (BlockingQueue) queue;
        do {
            ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
            if (peekScheduledTask == null) {
                try {
                    runnable = (Runnable) blockingQueue.take();
                } catch (InterruptedException unused) {
                }
                try {
                    if (runnable != WAKEUP_TASK) {
                        return runnable;
                    }
                    return null;
                } catch (InterruptedException unused2) {
                    return runnable;
                }
            }
            long delayNanos = peekScheduledTask.delayNanos();
            if (delayNanos > 0) {
                try {
                    runnable2 = (Runnable) blockingQueue.poll(delayNanos, TimeUnit.NANOSECONDS);
                } catch (InterruptedException unused3) {
                    return null;
                }
            } else {
                runnable2 = null;
            }
            if (runnable2 == null) {
                fetchFromScheduledTaskQueue();
                runnable2 = (Runnable) blockingQueue.poll();
            }
        } while (runnable2 == null);
        return runnable2;
    }

    private boolean fetchFromScheduledTaskQueue() {
        Runnable pollScheduledTask;
        if (this.scheduledTaskQueue == null || this.scheduledTaskQueue.isEmpty()) {
            return true;
        }
        long nanoTime = AbstractScheduledEventExecutor.nanoTime();
        do {
            pollScheduledTask = pollScheduledTask(nanoTime);
            if (pollScheduledTask == null) {
                return true;
            }
        } while (this.taskQueue.offer(pollScheduledTask));
        this.scheduledTaskQueue.add((ScheduledFutureTask) pollScheduledTask);
        return false;
    }

    private boolean executeExpiredScheduledTasks() {
        long nanoTime;
        Runnable pollScheduledTask;
        if (this.scheduledTaskQueue == null || this.scheduledTaskQueue.isEmpty() || (pollScheduledTask = pollScheduledTask((nanoTime = AbstractScheduledEventExecutor.nanoTime()))) == null) {
            return false;
        }
        do {
            safeExecute(pollScheduledTask);
            pollScheduledTask = pollScheduledTask(nanoTime);
        } while (pollScheduledTask != null);
        return true;
    }

    protected Runnable peekTask() {
        return this.taskQueue.peek();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasTasks() {
        return !this.taskQueue.isEmpty();
    }

    public int pendingTasks() {
        return this.taskQueue.size();
    }

    protected void addTask(Runnable runnable) {
        ObjectUtil.checkNotNull(runnable, "task");
        if (offerTask(runnable)) {
            return;
        }
        reject(runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean offerTask(Runnable runnable) {
        if (isShutdown()) {
            reject();
        }
        return this.taskQueue.offer(runnable);
    }

    protected boolean removeTask(Runnable runnable) {
        return this.taskQueue.remove(ObjectUtil.checkNotNull(runnable, "task"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean runAllTasks() {
        boolean fetchFromScheduledTaskQueue;
        boolean z = false;
        do {
            fetchFromScheduledTaskQueue = fetchFromScheduledTaskQueue();
            if (runAllTasksFrom(this.taskQueue)) {
                z = true;
            }
        } while (!fetchFromScheduledTaskQueue);
        if (z) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        afterRunningAllTasks();
        return z;
    }

    protected final boolean runScheduledAndExecutorTasks(int i) {
        int i2 = 0;
        while ((runExistingTasksFrom(this.taskQueue) | executeExpiredScheduledTasks()) && (i2 = i2 + 1) < i) {
        }
        if (i2 > 0) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        afterRunningAllTasks();
        return i2 > 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean runAllTasksFrom(Queue<Runnable> queue) {
        Runnable pollTaskFrom = pollTaskFrom(queue);
        if (pollTaskFrom == null) {
            return false;
        }
        do {
            safeExecute(pollTaskFrom);
            pollTaskFrom = pollTaskFrom(queue);
        } while (pollTaskFrom != null);
        return true;
    }

    private boolean runExistingTasksFrom(Queue<Runnable> queue) {
        Runnable poll;
        Runnable pollTaskFrom = pollTaskFrom(queue);
        if (pollTaskFrom == null) {
            return false;
        }
        int min = Math.min(this.maxPendingTasks, queue.size());
        safeExecute(pollTaskFrom);
        while (true) {
            int i = min - 1;
            if (min <= 0 || (poll = queue.poll()) == null) {
                return true;
            }
            safeExecute(poll);
            min = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean runAllTasks(long j) {
        long nanoTime;
        fetchFromScheduledTaskQueue();
        Runnable pollTask = pollTask();
        if (pollTask == null) {
            afterRunningAllTasks();
            return false;
        }
        long nanoTime2 = j > 0 ? j + ScheduledFutureTask.nanoTime() : 0L;
        long j2 = 0;
        while (true) {
            safeExecute(pollTask);
            j2++;
            if ((63 & j2) == 0) {
                nanoTime = ScheduledFutureTask.nanoTime();
                if (nanoTime >= nanoTime2) {
                    break;
                }
            }
            pollTask = pollTask();
            if (pollTask == null) {
                nanoTime = ScheduledFutureTask.nanoTime();
                break;
            }
        }
        afterRunningAllTasks();
        this.lastExecutionTime = nanoTime;
        return true;
    }

    protected long delayNanos(long j) {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask == null) {
            return SCHEDULE_PURGE_INTERVAL;
        }
        return peekScheduledTask.delayNanos(j);
    }

    protected long deadlineNanos() {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask == null) {
            return nanoTime() + SCHEDULE_PURGE_INTERVAL;
        }
        return peekScheduledTask.deadlineNanos();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateLastExecutionTime() {
        this.lastExecutionTime = ScheduledFutureTask.nanoTime();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void wakeup(boolean z) {
        if (z) {
            return;
        }
        this.taskQueue.offer(WAKEUP_TASK);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor
    public boolean inEventLoop(Thread thread) {
        return thread == this.thread;
    }

    public void addShutdownHook(final Runnable runnable) {
        if (inEventLoop()) {
            this.shutdownHooks.add(runnable);
        } else {
            execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.2
                @Override // java.lang.Runnable
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.add(runnable);
                }
            });
        }
    }

    public void removeShutdownHook(final Runnable runnable) {
        if (inEventLoop()) {
            this.shutdownHooks.remove(runnable);
        } else {
            execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.3
                @Override // java.lang.Runnable
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.remove(runnable);
                }
            });
        }
    }

    private boolean runShutdownHooks() {
        boolean z = false;
        while (!this.shutdownHooks.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.shutdownHooks);
            this.shutdownHooks.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    ((Runnable) it.next()).run();
                } finally {
                    z = true;
                }
                z = true;
            }
        }
        if (z) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        return z;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        ObjectUtil.checkPositiveOrZero(j, "quietPeriod");
        if (j2 < j) {
            throw new IllegalArgumentException("timeout: " + j2 + " (expected >= quietPeriod (" + j + "))");
        }
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (isShuttingDown()) {
            return terminationFuture();
        }
        boolean inEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            int i = this.state;
            int i2 = 3;
            boolean z = true;
            if (!inEventLoop && i != 1 && i != 2) {
                z = false;
                i2 = i;
            }
            if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                this.gracefulShutdownQuietPeriod = timeUnit.toNanos(j);
                this.gracefulShutdownTimeout = timeUnit.toNanos(j2);
                if (ensureThreadStarted(i)) {
                    return this.terminationFuture;
                }
                if (z) {
                    this.taskQueue.offer(WAKEUP_TASK);
                    if (!this.addTaskWakesUp) {
                        wakeup(inEventLoop);
                    }
                }
                return terminationFuture();
            }
        }
        return terminationFuture();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ExecutorService, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    @Deprecated
    public void shutdown() {
        if (isShutdown()) {
            return;
        }
        boolean inEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            int i = this.state;
            int i2 = 4;
            boolean z = true;
            if (!inEventLoop && i != 1 && i != 2 && i != 3) {
                z = false;
                i2 = i;
            }
            if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                if (!ensureThreadStarted(i) && z) {
                    this.taskQueue.offer(WAKEUP_TASK);
                    if (this.addTaskWakesUp) {
                        return;
                    }
                    wakeup(inEventLoop);
                    return;
                }
                return;
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public boolean isShuttingDown() {
        return this.state >= 3;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.state >= 4;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.state == 5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean confirmShutdown() {
        if (!isShuttingDown()) {
            return false;
        }
        if (!inEventLoop()) {
            throw new IllegalStateException("must be invoked from an event loop");
        }
        cancelScheduledTasks();
        if (this.gracefulShutdownStartTime == 0) {
            this.gracefulShutdownStartTime = ScheduledFutureTask.nanoTime();
        }
        if (runAllTasks() || runShutdownHooks()) {
            if (isShutdown() || this.gracefulShutdownQuietPeriod == 0) {
                return true;
            }
            this.taskQueue.offer(WAKEUP_TASK);
            return false;
        }
        long nanoTime = ScheduledFutureTask.nanoTime();
        if (isShutdown() || nanoTime - this.gracefulShutdownStartTime > this.gracefulShutdownTimeout || nanoTime - this.lastExecutionTime > this.gracefulShutdownQuietPeriod) {
            return true;
        }
        this.taskQueue.offer(WAKEUP_TASK);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException unused) {
        }
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (inEventLoop()) {
            throw new IllegalStateException("cannot await termination of the current thread");
        }
        this.threadLock.await(j, timeUnit);
        return isTerminated();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        ObjectUtil.checkNotNull(runnable, "task");
        execute(runnable, !(runnable instanceof AbstractEventExecutor.LazyRunnable) && wakesUpForTask(runnable));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor
    public void lazyExecute(Runnable runnable) {
        execute((Runnable) ObjectUtil.checkNotNull(runnable, "task"), false);
    }

    private void execute(Runnable runnable, boolean z) {
        boolean inEventLoop = inEventLoop();
        addTask(runnable);
        if (!inEventLoop) {
            startThread();
            if (isShutdown()) {
                boolean z2 = false;
                try {
                    z2 = removeTask(runnable);
                } catch (UnsupportedOperationException unused) {
                }
                if (z2) {
                    reject();
                }
            }
        }
        if (this.addTaskWakesUp || !z) {
            return;
        }
        wakeup(inEventLoop);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        throwIfInEventLoop("invokeAny");
        return (T) super.invokeAny(collection);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        throwIfInEventLoop("invokeAny");
        return (T) super.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(collection);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(collection, j, timeUnit);
    }

    private void throwIfInEventLoop(String str) {
        if (inEventLoop()) {
            throw new RejectedExecutionException("Calling " + str + " from within the EventLoop is not allowed");
        }
    }

    public final ThreadProperties threadProperties() {
        ThreadProperties threadProperties = this.threadProperties;
        if (threadProperties != null) {
            return threadProperties;
        }
        Thread thread = this.thread;
        if (thread == null) {
            submit(NOOP_TASK).syncUninterruptibly();
            thread = this.thread;
        }
        DefaultThreadProperties defaultThreadProperties = new DefaultThreadProperties(thread);
        return !PROPERTIES_UPDATER.compareAndSet(this, null, defaultThreadProperties) ? this.threadProperties : defaultThreadProperties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void reject() {
        throw new RejectedExecutionException("event executor terminated");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void reject(Runnable runnable) {
        this.rejectedExecutionHandler.rejected(runnable, this);
    }

    private void startThread() {
        if (this.state == 1 && STATE_UPDATER.compareAndSet(this, 1, 2)) {
            try {
                doStartThread();
            } catch (Throwable th) {
                STATE_UPDATER.compareAndSet(this, 2, 1);
                throw th;
            }
        }
    }

    private boolean ensureThreadStarted(int i) {
        if (i != 1) {
            return false;
        }
        try {
            doStartThread();
            return false;
        } catch (Throwable th) {
            STATE_UPDATER.set(this, 5);
            this.terminationFuture.tryFailure(th);
            if (!(th instanceof Exception)) {
                PlatformDependent.throwException(th);
            }
            return true;
        }
    }

    private void doStartThread() {
        this.executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.4
            @Override // java.lang.Runnable
            public void run() {
                int i;
                int i2;
                int i3;
                int i4;
                int drainTasks;
                InternalLogger internalLogger;
                StringBuilder sb;
                int i5;
                int i6;
                SingleThreadEventExecutor.this.thread = Thread.currentThread();
                if (SingleThreadEventExecutor.this.interrupted) {
                    SingleThreadEventExecutor.this.thread.interrupt();
                }
                SingleThreadEventExecutor.this.updateLastExecutionTime();
                try {
                    SingleThreadEventExecutor.this.run();
                    do {
                        i5 = SingleThreadEventExecutor.this.state;
                        if (i5 >= 3) {
                            break;
                        }
                    } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i5, 3));
                    if (SingleThreadEventExecutor.this.gracefulShutdownStartTime == 0 && SingleThreadEventExecutor.logger.isErrorEnabled()) {
                        SingleThreadEventExecutor.logger.error("Buggy " + EventExecutor.class.getSimpleName() + " implementation; " + SingleThreadEventExecutor.class.getSimpleName() + ".confirmShutdown() must be called before run() implementation terminates.");
                    }
                    do {
                        try {
                        } catch (Throwable th) {
                            try {
                                SingleThreadEventExecutor.this.cleanup();
                                throw th;
                            } finally {
                                FastThreadLocal.removeAll();
                                SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                SingleThreadEventExecutor.this.threadLock.countDown();
                                int drainTasks2 = SingleThreadEventExecutor.this.drainTasks();
                                if (drainTasks2 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                    SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks2 + ')');
                                }
                                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                            }
                        }
                    } while (!SingleThreadEventExecutor.this.confirmShutdown());
                    do {
                        i6 = SingleThreadEventExecutor.this.state;
                        if (i6 >= 4) {
                            break;
                        }
                    } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i6, 4));
                    SingleThreadEventExecutor.this.confirmShutdown();
                } catch (Throwable th2) {
                    try {
                        SingleThreadEventExecutor.logger.warn("Unexpected exception from an event executor: ", th2);
                        do {
                            i3 = SingleThreadEventExecutor.this.state;
                            if (i3 < 3) {
                            }
                            break;
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i3, 3));
                        break;
                        do {
                            try {
                            } catch (Throwable th3) {
                                try {
                                    SingleThreadEventExecutor.this.cleanup();
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int drainTasks3 = SingleThreadEventExecutor.this.drainTasks();
                                    if (drainTasks3 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks3 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                    throw th3;
                                } finally {
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int drainTasks4 = SingleThreadEventExecutor.this.drainTasks();
                                    if (drainTasks4 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks4 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                }
                            }
                        } while (!SingleThreadEventExecutor.this.confirmShutdown());
                        do {
                            i4 = SingleThreadEventExecutor.this.state;
                            if (i4 >= 4) {
                                break;
                            }
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i4, 4));
                        SingleThreadEventExecutor.this.confirmShutdown();
                        try {
                            SingleThreadEventExecutor.this.cleanup();
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            drainTasks = SingleThreadEventExecutor.this.drainTasks();
                            if (drainTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                internalLogger = SingleThreadEventExecutor.logger;
                                sb = new StringBuilder();
                            }
                        } finally {
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            int drainTasks5 = SingleThreadEventExecutor.this.drainTasks();
                            if (drainTasks5 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks5 + ')');
                            }
                            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                        }
                    } catch (Throwable th4) {
                        do {
                            i = SingleThreadEventExecutor.this.state;
                            if (i < 3) {
                            }
                            break;
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i, 3));
                        break;
                        do {
                            try {
                            } catch (Throwable th5) {
                                try {
                                    SingleThreadEventExecutor.this.cleanup();
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int drainTasks6 = SingleThreadEventExecutor.this.drainTasks();
                                    if (drainTasks6 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks6 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                    throw th5;
                                } finally {
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int drainTasks7 = SingleThreadEventExecutor.this.drainTasks();
                                    if (drainTasks7 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks7 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                }
                            }
                        } while (!SingleThreadEventExecutor.this.confirmShutdown());
                        do {
                            i2 = SingleThreadEventExecutor.this.state;
                            if (i2 >= 4) {
                                break;
                            }
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i2, 4));
                        SingleThreadEventExecutor.this.confirmShutdown();
                        try {
                            SingleThreadEventExecutor.this.cleanup();
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            int drainTasks8 = SingleThreadEventExecutor.this.drainTasks();
                            if (drainTasks8 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks8 + ')');
                            }
                            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                            throw th4;
                        } finally {
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            int drainTasks9 = SingleThreadEventExecutor.this.drainTasks();
                            if (drainTasks9 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + drainTasks9 + ')');
                            }
                            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                        }
                    }
                }
                try {
                    SingleThreadEventExecutor.this.cleanup();
                    FastThreadLocal.removeAll();
                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                    SingleThreadEventExecutor.this.threadLock.countDown();
                    drainTasks = SingleThreadEventExecutor.this.drainTasks();
                    if (drainTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                        internalLogger = SingleThreadEventExecutor.logger;
                        sb = new StringBuilder();
                        sb.append("An event executor terminated with non-empty task queue (");
                        sb.append(drainTasks);
                        sb.append(')');
                        internalLogger.warn(sb.toString());
                    }
                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                } finally {
                }
            }
        });
    }

    final int drainTasks() {
        int i = 0;
        while (true) {
            Runnable poll = this.taskQueue.poll();
            if (poll == null) {
                return i;
            }
            if (WAKEUP_TASK != poll) {
                i++;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class DefaultThreadProperties implements ThreadProperties {

        /* renamed from: t */
        private final Thread f8432t;

        DefaultThreadProperties(Thread thread) {
            this.f8432t = thread;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public Thread.State state() {
            return this.f8432t.getState();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public int priority() {
            return this.f8432t.getPriority();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public boolean isInterrupted() {
            return this.f8432t.isInterrupted();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public boolean isDaemon() {
            return this.f8432t.isDaemon();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public String name() {
            return this.f8432t.getName();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        /* renamed from: id */
        public long mo3921id() {
            return this.f8432t.getId();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public StackTraceElement[] stackTrace() {
            return this.f8432t.getStackTrace();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public boolean isAlive() {
            return this.f8432t.isAlive();
        }
    }
}
