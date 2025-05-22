package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
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
    private final Semaphore threadLock;
    private volatile ThreadProperties threadProperties;
    static final int DEFAULT_MAX_PENDING_EXECUTOR_TASKS = Math.max(16, SystemPropertyUtil.getInt("io.netty.eventexecutor.maxPendingTasks", Integer.MAX_VALUE));
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SingleThreadEventExecutor.class);
    private static final Runnable WAKEUP_TASK = new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.1
        @Override // java.lang.Runnable
        public void run() {
        }
    };
    private static final Runnable NOOP_TASK = new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.2
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
    /* loaded from: classes8.dex */
    protected interface NonWakeupRunnable extends Runnable {
    }

    protected void afterRunningAllTasks() {
    }

    protected void cleanup() {
    }

    protected abstract void run();

    protected boolean wakesUpForTask(Runnable runnable) {
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
        this.threadLock = new Semaphore(0);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = z;
        this.maxPendingTasks = Math.max(16, i);
        this.executor = (Executor) ObjectUtil.checkNotNull(executor, "executor");
        this.taskQueue = newTaskQueue(this.maxPendingTasks);
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

    /* JADX INFO: Access modifiers changed from: protected */
    public Runnable pollTask() {
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
        Queue<Runnable> queue = this.taskQueue;
        if (!(queue instanceof BlockingQueue)) {
            throw new UnsupportedOperationException();
        }
        BlockingQueue blockingQueue = (BlockingQueue) queue;
        do {
            ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
            runnable = null;
            if (peekScheduledTask == null) {
                try {
                    Runnable runnable2 = (Runnable) blockingQueue.take();
                    try {
                        if (runnable2 == WAKEUP_TASK) {
                            return null;
                        }
                    } catch (InterruptedException unused) {
                    }
                    return runnable2;
                } catch (InterruptedException unused2) {
                    return null;
                }
            }
            long delayNanos = peekScheduledTask.delayNanos();
            if (delayNanos > 0) {
                try {
                    runnable = (Runnable) blockingQueue.poll(delayNanos, TimeUnit.NANOSECONDS);
                } catch (InterruptedException unused3) {
                    return null;
                }
            }
            if (runnable == null) {
                fetchFromScheduledTaskQueue();
                runnable = (Runnable) blockingQueue.poll();
            }
        } while (runnable == null);
        return runnable;
    }

    private boolean fetchFromScheduledTaskQueue() {
        long nanoTime = AbstractScheduledEventExecutor.nanoTime();
        Runnable pollScheduledTask = pollScheduledTask(nanoTime);
        while (pollScheduledTask != null) {
            if (!this.taskQueue.offer(pollScheduledTask)) {
                scheduledTaskQueue().add((ScheduledFutureTask) pollScheduledTask);
                return false;
            }
            pollScheduledTask = pollScheduledTask(nanoTime);
        }
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
        if (runnable == null) {
            throw new NullPointerException("task");
        }
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
        if (runnable == null) {
            throw new NullPointerException("task");
        }
        return this.taskQueue.remove(runnable);
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

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean runAllTasks(long j) {
        long nanoTime;
        fetchFromScheduledTaskQueue();
        Runnable pollTask = pollTask();
        if (pollTask == null) {
            afterRunningAllTasks();
            return false;
        }
        long nanoTime2 = ScheduledFutureTask.nanoTime() + j;
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

    /* JADX INFO: Access modifiers changed from: protected */
    public long delayNanos(long j) {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask == null) {
            return SCHEDULE_PURGE_INTERVAL;
        }
        return peekScheduledTask.delayNanos(j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateLastExecutionTime() {
        this.lastExecutionTime = ScheduledFutureTask.nanoTime();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void wakeup(boolean z) {
        if (!z || this.state == 3) {
            this.taskQueue.offer(WAKEUP_TASK);
        }
    }

    @Override // io.netty.util.concurrent.EventExecutor
    public boolean inEventLoop(Thread thread) {
        return thread == this.thread;
    }

    public void addShutdownHook(final Runnable runnable) {
        if (inEventLoop()) {
            this.shutdownHooks.add(runnable);
        } else {
            execute(new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.3
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
            execute(new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.4
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

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        boolean z;
        if (j < 0) {
            throw new IllegalArgumentException("quietPeriod: " + j + " (expected >= 0)");
        }
        if (j2 < j) {
            throw new IllegalArgumentException("timeout: " + j2 + " (expected >= quietPeriod (" + j + "))");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit");
        }
        if (isShuttingDown()) {
            return terminationFuture();
        }
        boolean inEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            int i = this.state;
            int i2 = 3;
            if (inEventLoop || i == 1 || i == 2) {
                z = true;
            } else {
                z = false;
                i2 = i;
            }
            if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                this.gracefulShutdownQuietPeriod = timeUnit.toNanos(j);
                this.gracefulShutdownTimeout = timeUnit.toNanos(j2);
                if (i == 1) {
                    try {
                        doStartThread();
                    } catch (Throwable th) {
                        STATE_UPDATER.set(this, 5);
                        this.terminationFuture.tryFailure(th);
                        if (!(th instanceof Exception)) {
                            PlatformDependent.throwException(th);
                        }
                        return this.terminationFuture;
                    }
                }
                if (z) {
                    wakeup(inEventLoop);
                }
                return terminationFuture();
            }
        }
        return terminationFuture();
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    @Override // io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ExecutorService, io.netty.util.concurrent.EventExecutorGroup
    @Deprecated
    public void shutdown() {
        boolean z;
        if (isShutdown()) {
            return;
        }
        boolean inEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            int i = this.state;
            int i2 = 4;
            if (inEventLoop || i == 1 || i == 2 || i == 3) {
                z = true;
            } else {
                z = false;
                i2 = i;
            }
            if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                if (i == 1) {
                    try {
                        doStartThread();
                    } catch (Throwable th) {
                        STATE_UPDATER.set(this, 5);
                        this.terminationFuture.tryFailure(th);
                        if (th instanceof Exception) {
                            return;
                        }
                        PlatformDependent.throwException(th);
                        return;
                    }
                }
                if (z) {
                    wakeup(inEventLoop);
                    return;
                }
                return;
            }
        }
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
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
            wakeup(true);
            return false;
        }
        long nanoTime = ScheduledFutureTask.nanoTime();
        if (isShutdown() || nanoTime - this.gracefulShutdownStartTime > this.gracefulShutdownTimeout || nanoTime - this.lastExecutionTime > this.gracefulShutdownQuietPeriod) {
            return true;
        }
        wakeup(true);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException unused) {
        }
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        if (timeUnit == null) {
            throw new NullPointerException("unit");
        }
        if (inEventLoop()) {
            throw new IllegalStateException("cannot await termination of the current thread");
        }
        if (this.threadLock.tryAcquire(j, timeUnit)) {
            this.threadLock.release();
        }
        return isTerminated();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("task");
        }
        boolean inEventLoop = inEventLoop();
        if (inEventLoop) {
            addTask(runnable);
        } else {
            startThread();
            addTask(runnable);
            if (isShutdown() && removeTask(runnable)) {
                reject();
            }
        }
        if (this.addTaskWakesUp || !wakesUpForTask(runnable)) {
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
                STATE_UPDATER.set(this, 1);
                PlatformDependent.throwException(th);
            }
        }
    }

    private void doStartThread() {
        this.executor.execute(new Runnable() { // from class: io.netty.util.concurrent.SingleThreadEventExecutor.5
            /* JADX WARN: Code restructure failed: missing block: B:115:0x0369, code lost:
            
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:116:0x036a, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER.set(r9.this$0, 5);
                r9.this$0.threadLock.release();
             */
            /* JADX WARN: Code restructure failed: missing block: B:117:0x0386, code lost:
            
                if (r9.this$0.taskQueue.isEmpty() == false) goto L89;
             */
            /* JADX WARN: Code restructure failed: missing block: B:118:0x0388, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + r9.this$0.taskQueue.size() + ')');
             */
            /* JADX WARN: Code restructure failed: missing block: B:119:0x03ab, code lost:
            
                r9.this$0.terminationFuture.setSuccess(null);
             */
            /* JADX WARN: Code restructure failed: missing block: B:120:0x03b4, code lost:
            
                throw r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:29:0x00d5, code lost:
            
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:34:0x0120, code lost:
            
                throw r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:73:0x0213, code lost:
            
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:74:0x0214, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER.set(r9.this$0, 5);
                r9.this$0.threadLock.release();
             */
            /* JADX WARN: Code restructure failed: missing block: B:75:0x0230, code lost:
            
                if (r9.this$0.taskQueue.isEmpty() == false) goto L57;
             */
            /* JADX WARN: Code restructure failed: missing block: B:76:0x0232, code lost:
            
                io.netty.util.concurrent.SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + r9.this$0.taskQueue.size() + ')');
             */
            /* JADX WARN: Code restructure failed: missing block: B:77:0x0255, code lost:
            
                r9.this$0.terminationFuture.setSuccess(null);
             */
            /* JADX WARN: Code restructure failed: missing block: B:78:0x025e, code lost:
            
                throw r1;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                int i;
                int i2;
                InternalLogger internalLogger;
                StringBuilder sb;
                int i3;
                SingleThreadEventExecutor.this.thread = Thread.currentThread();
                if (SingleThreadEventExecutor.this.interrupted) {
                    SingleThreadEventExecutor.this.thread.interrupt();
                }
                SingleThreadEventExecutor.this.updateLastExecutionTime();
                try {
                    SingleThreadEventExecutor.this.run();
                    do {
                        i3 = SingleThreadEventExecutor.this.state;
                        if (i3 >= 3) {
                            break;
                        }
                    } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i3, 3));
                    if (SingleThreadEventExecutor.this.gracefulShutdownStartTime == 0) {
                        SingleThreadEventExecutor.logger.error("Buggy " + EventExecutor.class.getSimpleName() + " implementation; " + SingleThreadEventExecutor.class.getSimpleName() + ".confirmShutdown() must be called before run() implementation terminates.");
                    }
                    do {
                        try {
                        } catch (Throwable th) {
                            try {
                                SingleThreadEventExecutor.this.cleanup();
                                throw th;
                            } finally {
                                SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                SingleThreadEventExecutor.this.threadLock.release();
                                if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                    SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                }
                                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                            }
                        }
                    } while (!SingleThreadEventExecutor.this.confirmShutdown());
                    SingleThreadEventExecutor.this.cleanup();
                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                    SingleThreadEventExecutor.this.threadLock.release();
                } catch (Throwable th2) {
                    try {
                        SingleThreadEventExecutor.logger.warn("Unexpected exception from an event executor: ", th2);
                        do {
                            i2 = SingleThreadEventExecutor.this.state;
                            if (i2 < 3) {
                            }
                            break;
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i2, 3));
                        break;
                        do {
                            try {
                            } catch (Throwable th3) {
                                try {
                                    SingleThreadEventExecutor.this.cleanup();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.release();
                                    if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                    throw th3;
                                } finally {
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.release();
                                    if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                }
                            }
                        } while (!SingleThreadEventExecutor.this.confirmShutdown());
                        SingleThreadEventExecutor.this.cleanup();
                        SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                        SingleThreadEventExecutor.this.threadLock.release();
                        if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                            internalLogger = SingleThreadEventExecutor.logger;
                            sb = new StringBuilder();
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
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.release();
                                    if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                    throw th5;
                                } finally {
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.release();
                                    if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                }
                            }
                        } while (!SingleThreadEventExecutor.this.confirmShutdown());
                        SingleThreadEventExecutor.this.cleanup();
                        SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                        SingleThreadEventExecutor.this.threadLock.release();
                        if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                            SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                        }
                        SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                        throw th4;
                    }
                }
                if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                    internalLogger = SingleThreadEventExecutor.logger;
                    sb = new StringBuilder();
                    sb.append("An event executor terminated with non-empty task queue (");
                    sb.append(SingleThreadEventExecutor.this.taskQueue.size());
                    sb.append(')');
                    internalLogger.warn(sb.toString());
                }
                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static final class DefaultThreadProperties implements ThreadProperties {

        /* renamed from: t */
        private final Thread f8579t;

        DefaultThreadProperties(Thread thread) {
            this.f8579t = thread;
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public Thread.State state() {
            return this.f8579t.getState();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public int priority() {
            return this.f8579t.getPriority();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public boolean isInterrupted() {
            return this.f8579t.isInterrupted();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public boolean isDaemon() {
            return this.f8579t.isDaemon();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public String name() {
            return this.f8579t.getName();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        /* renamed from: id */
        public long mo3945id() {
            return this.f8579t.getId();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public StackTraceElement[] stackTrace() {
            return this.f8579t.getStackTrace();
        }

        @Override // io.netty.util.concurrent.ThreadProperties
        public boolean isAlive() {
            return this.f8579t.isAlive();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.netty.util.concurrent.SingleThreadEventExecutor$6 */
    /* loaded from: classes8.dex */
    class RunnableC74806 implements Runnable {
        RunnableC74806() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:135:0x03e8, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:136:0x03e9, code lost:
        
            io.netty.util.concurrent.FastThreadLocal.removeAll();
            io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER.set(r9.this$0, 5);
            r9.this$0.threadLock.countDown();
         */
        /* JADX WARN: Code restructure failed: missing block: B:137:0x0406, code lost:
        
            if (io.netty.util.concurrent.SingleThreadEventExecutor.logger.isWarnEnabled() != false) goto L109;
         */
        /* JADX WARN: Code restructure failed: missing block: B:140:0x0414, code lost:
        
            io.netty.util.concurrent.SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + r9.this$0.taskQueue.size() + ')');
         */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x0437, code lost:
        
            r9.this$0.terminationFuture.setSuccess(null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:142:0x0440, code lost:
        
            throw r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00ec, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0144, code lost:
        
            throw r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x025e, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x025f, code lost:
        
            io.netty.util.concurrent.FastThreadLocal.removeAll();
            io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER.set(r9.this$0, 5);
            r9.this$0.threadLock.countDown();
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x027c, code lost:
        
            if (io.netty.util.concurrent.SingleThreadEventExecutor.logger.isWarnEnabled() != false) goto L69;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x028a, code lost:
        
            io.netty.util.concurrent.SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + r9.this$0.taskQueue.size() + ')');
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x02ad, code lost:
        
            r9.this$0.terminationFuture.setSuccess(null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x02b6, code lost:
        
            throw r1;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            int i;
            int i2;
            InternalLogger internalLogger;
            StringBuilder sb;
            int i3;
            SingleThreadEventExecutor.this.thread = Thread.currentThread();
            if (SingleThreadEventExecutor.this.interrupted) {
                SingleThreadEventExecutor.this.thread.interrupt();
            }
            SingleThreadEventExecutor.this.updateLastExecutionTime();
            try {
                SingleThreadEventExecutor.this.run();
                do {
                    i3 = SingleThreadEventExecutor.this.state;
                    if (i3 >= 3) {
                        break;
                    }
                } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i3, 3));
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
                            if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                            }
                            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                        }
                    }
                } while (!SingleThreadEventExecutor.this.confirmShutdown());
                SingleThreadEventExecutor.this.cleanup();
                FastThreadLocal.removeAll();
                SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                SingleThreadEventExecutor.this.threadLock.countDown();
            } catch (Throwable th2) {
                try {
                    SingleThreadEventExecutor.logger.warn("Unexpected exception from an event executor: ", th2);
                    do {
                        i2 = SingleThreadEventExecutor.this.state;
                        if (i2 < 3) {
                        }
                        break;
                    } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i2, 3));
                    break;
                    do {
                        try {
                        } catch (Throwable th3) {
                            try {
                                SingleThreadEventExecutor.this.cleanup();
                                FastThreadLocal.removeAll();
                                SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                SingleThreadEventExecutor.this.threadLock.countDown();
                                if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                    SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                }
                                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                throw th3;
                            } finally {
                                FastThreadLocal.removeAll();
                                SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                SingleThreadEventExecutor.this.threadLock.countDown();
                                if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                    SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                }
                                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                            }
                        }
                    } while (!SingleThreadEventExecutor.this.confirmShutdown());
                    SingleThreadEventExecutor.this.cleanup();
                    FastThreadLocal.removeAll();
                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                    SingleThreadEventExecutor.this.threadLock.countDown();
                    if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                        internalLogger = SingleThreadEventExecutor.logger;
                        sb = new StringBuilder();
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
                                if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                    SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                }
                                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                throw th5;
                            } finally {
                                FastThreadLocal.removeAll();
                                SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                SingleThreadEventExecutor.this.threadLock.countDown();
                                if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                                    SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                                }
                                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                            }
                        }
                    } while (!SingleThreadEventExecutor.this.confirmShutdown());
                    SingleThreadEventExecutor.this.cleanup();
                    FastThreadLocal.removeAll();
                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                    SingleThreadEventExecutor.this.threadLock.countDown();
                    if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                    }
                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                    throw th4;
                }
            }
            if (SingleThreadEventExecutor.logger.isWarnEnabled() && !SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                internalLogger = SingleThreadEventExecutor.logger;
                sb = new StringBuilder();
                sb.append("An event executor terminated with non-empty task queue (");
                sb.append(SingleThreadEventExecutor.this.taskQueue.size());
                sb.append(')');
                internalLogger.warn(sb.toString());
            }
            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
        }
    }
}
