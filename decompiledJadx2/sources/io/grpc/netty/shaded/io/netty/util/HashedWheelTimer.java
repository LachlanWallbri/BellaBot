package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HashedWheelTimer implements Timer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INSTANCE_COUNT_LIMIT = 64;
    public static final int WORKER_STATE_INIT = 0;
    public static final int WORKER_STATE_SHUTDOWN = 2;
    public static final int WORKER_STATE_STARTED = 1;
    private final Queue<HashedWheelTimeout> cancelledTimeouts;
    private final ResourceLeakTracker<HashedWheelTimer> leak;
    private final int mask;
    private final long maxPendingTimeouts;
    private final AtomicLong pendingTimeouts;
    private volatile long startTime;
    private final CountDownLatch startTimeInitialized;
    private final long tickDuration;
    private final Queue<HashedWheelTimeout> timeouts;
    private final HashedWheelBucket[] wheel;
    private final Worker worker;
    private volatile int workerState;
    private final Thread workerThread;
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) HashedWheelTimer.class);
    private static final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();
    private static final AtomicBoolean WARNED_TOO_MANY_INSTANCES = new AtomicBoolean();
    private static final long MILLISECOND_NANOS = TimeUnit.MILLISECONDS.toNanos(1);
    private static final ResourceLeakDetector<HashedWheelTimer> leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(HashedWheelTimer.class, 1);
    private static final AtomicIntegerFieldUpdater<HashedWheelTimer> WORKER_STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(HashedWheelTimer.class, "workerState");

    private static int normalizeTicksPerWheel(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
        }
        return i2;
    }

    public HashedWheelTimer() {
        this(Executors.defaultThreadFactory());
    }

    public HashedWheelTimer(long j, TimeUnit timeUnit) {
        this(Executors.defaultThreadFactory(), j, timeUnit);
    }

    public HashedWheelTimer(long j, TimeUnit timeUnit, int i) {
        this(Executors.defaultThreadFactory(), j, timeUnit, i);
    }

    public HashedWheelTimer(ThreadFactory threadFactory) {
        this(threadFactory, 100L, TimeUnit.MILLISECONDS);
    }

    public HashedWheelTimer(ThreadFactory threadFactory, long j, TimeUnit timeUnit) {
        this(threadFactory, j, timeUnit, 512);
    }

    public HashedWheelTimer(ThreadFactory threadFactory, long j, TimeUnit timeUnit, int i) {
        this(threadFactory, j, timeUnit, i, true);
    }

    public HashedWheelTimer(ThreadFactory threadFactory, long j, TimeUnit timeUnit, int i, boolean z) {
        this(threadFactory, j, timeUnit, i, z, -1L);
    }

    public HashedWheelTimer(ThreadFactory threadFactory, long j, TimeUnit timeUnit, int i, boolean z, long j2) {
        this.worker = new Worker();
        this.startTimeInitialized = new CountDownLatch(1);
        this.timeouts = PlatformDependent.newMpscQueue();
        this.cancelledTimeouts = PlatformDependent.newMpscQueue();
        this.pendingTimeouts = new AtomicLong(0L);
        ObjectUtil.checkNotNull(threadFactory, "threadFactory");
        ObjectUtil.checkNotNull(timeUnit, "unit");
        ObjectUtil.checkPositive(j, "tickDuration");
        ObjectUtil.checkPositive(i, "ticksPerWheel");
        this.wheel = createWheel(i);
        this.mask = this.wheel.length - 1;
        long nanos = timeUnit.toNanos(j);
        if (nanos >= Long.MAX_VALUE / this.wheel.length) {
            throw new IllegalArgumentException(String.format("tickDuration: %d (expected: 0 < tickDuration in nanos < %d", Long.valueOf(j), Long.valueOf(Long.MAX_VALUE / this.wheel.length)));
        }
        if (nanos < MILLISECOND_NANOS) {
            logger.warn("Configured tickDuration {} smaller then {}, using 1ms.", Long.valueOf(j), Long.valueOf(MILLISECOND_NANOS));
            this.tickDuration = MILLISECOND_NANOS;
        } else {
            this.tickDuration = nanos;
        }
        this.workerThread = threadFactory.newThread(this.worker);
        this.leak = (z || !this.workerThread.isDaemon()) ? leakDetector.track(this) : null;
        this.maxPendingTimeouts = j2;
        if (INSTANCE_COUNTER.incrementAndGet() <= 64 || !WARNED_TOO_MANY_INSTANCES.compareAndSet(false, true)) {
            return;
        }
        reportTooManyInstances();
    }

    protected void finalize() throws Throwable {
        try {
            super.finalize();
        } finally {
            if (WORKER_STATE_UPDATER.getAndSet(this, 2) != 2) {
                INSTANCE_COUNTER.decrementAndGet();
            }
        }
    }

    private static HashedWheelBucket[] createWheel(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + i);
        }
        if (i > 1073741824) {
            throw new IllegalArgumentException("ticksPerWheel may not be greater than 2^30: " + i);
        }
        HashedWheelBucket[] hashedWheelBucketArr = new HashedWheelBucket[normalizeTicksPerWheel(i)];
        for (int i2 = 0; i2 < hashedWheelBucketArr.length; i2++) {
            hashedWheelBucketArr[i2] = new HashedWheelBucket();
        }
        return hashedWheelBucketArr;
    }

    public void start() {
        int i = WORKER_STATE_UPDATER.get(this);
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    throw new IllegalStateException("cannot be started once stopped");
                }
                throw new Error("Invalid WorkerState");
            }
        } else if (WORKER_STATE_UPDATER.compareAndSet(this, 0, 1)) {
            this.workerThread.start();
        }
        while (this.startTime == 0) {
            try {
                this.startTimeInitialized.await();
            } catch (InterruptedException unused) {
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.Timer
    public Set<Timeout> stop() {
        if (Thread.currentThread() == this.workerThread) {
            throw new IllegalStateException(HashedWheelTimer.class.getSimpleName() + ".stop() cannot be called from " + TimerTask.class.getSimpleName());
        }
        if (!WORKER_STATE_UPDATER.compareAndSet(this, 1, 2)) {
            if (WORKER_STATE_UPDATER.getAndSet(this, 2) != 2) {
            }
            return Collections.emptySet();
        }
        boolean z = false;
        while (this.workerThread.isAlive()) {
            try {
                this.workerThread.interrupt();
                try {
                    this.workerThread.join(100L);
                } catch (InterruptedException unused) {
                    z = true;
                }
            } finally {
                INSTANCE_COUNTER.decrementAndGet();
                ResourceLeakTracker<HashedWheelTimer> resourceLeakTracker = this.leak;
                if (resourceLeakTracker != null) {
                    resourceLeakTracker.close(this);
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        INSTANCE_COUNTER.decrementAndGet();
        ResourceLeakTracker<HashedWheelTimer> resourceLeakTracker2 = this.leak;
        if (resourceLeakTracker2 != null) {
            resourceLeakTracker2.close(this);
        }
        return this.worker.unprocessedTimeouts();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.Timer
    public Timeout newTimeout(TimerTask timerTask, long j, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(timerTask, "task");
        ObjectUtil.checkNotNull(timeUnit, "unit");
        long incrementAndGet = this.pendingTimeouts.incrementAndGet();
        long j2 = this.maxPendingTimeouts;
        if (j2 > 0 && incrementAndGet > j2) {
            this.pendingTimeouts.decrementAndGet();
            throw new RejectedExecutionException("Number of pending timeouts (" + incrementAndGet + ") is greater than or equal to maximum allowed pending timeouts (" + this.maxPendingTimeouts + ")");
        }
        start();
        long nanoTime = (System.nanoTime() + timeUnit.toNanos(j)) - this.startTime;
        if (j > 0 && nanoTime < 0) {
            nanoTime = Long.MAX_VALUE;
        }
        HashedWheelTimeout hashedWheelTimeout = new HashedWheelTimeout(this, timerTask, nanoTime);
        this.timeouts.add(hashedWheelTimeout);
        return hashedWheelTimeout;
    }

    public long pendingTimeouts() {
        return this.pendingTimeouts.get();
    }

    private static void reportTooManyInstances() {
        if (logger.isErrorEnabled()) {
            String simpleClassName = StringUtil.simpleClassName((Class<?>) HashedWheelTimer.class);
            logger.error("You are creating too many " + simpleClassName + " instances. " + simpleClassName + " is a shared resource that must be reused across the JVM,so that only a few instances are created.");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class Worker implements Runnable {
        private long tick;
        private final Set<Timeout> unprocessedTimeouts;

        private Worker() {
            this.unprocessedTimeouts = new HashSet();
        }

        @Override // java.lang.Runnable
        public void run() {
            HashedWheelTimer.this.startTime = System.nanoTime();
            if (HashedWheelTimer.this.startTime == 0) {
                HashedWheelTimer.this.startTime = 1L;
            }
            HashedWheelTimer.this.startTimeInitialized.countDown();
            do {
                long waitForNextTick = waitForNextTick();
                if (waitForNextTick > 0) {
                    int i = (int) (this.tick & HashedWheelTimer.this.mask);
                    processCancelledTasks();
                    HashedWheelBucket hashedWheelBucket = HashedWheelTimer.this.wheel[i];
                    transferTimeoutsToBuckets();
                    hashedWheelBucket.expireTimeouts(waitForNextTick);
                    this.tick++;
                }
            } while (HashedWheelTimer.WORKER_STATE_UPDATER.get(HashedWheelTimer.this) == 1);
            for (HashedWheelBucket hashedWheelBucket2 : HashedWheelTimer.this.wheel) {
                hashedWheelBucket2.clearTimeouts(this.unprocessedTimeouts);
            }
            while (true) {
                HashedWheelTimeout hashedWheelTimeout = (HashedWheelTimeout) HashedWheelTimer.this.timeouts.poll();
                if (hashedWheelTimeout != null) {
                    if (!hashedWheelTimeout.isCancelled()) {
                        this.unprocessedTimeouts.add(hashedWheelTimeout);
                    }
                } else {
                    processCancelledTasks();
                    return;
                }
            }
        }

        private void transferTimeoutsToBuckets() {
            HashedWheelTimeout hashedWheelTimeout;
            for (int i = 0; i < 100000 && (hashedWheelTimeout = (HashedWheelTimeout) HashedWheelTimer.this.timeouts.poll()) != null; i++) {
                if (hashedWheelTimeout.state() != 1) {
                    long j = hashedWheelTimeout.deadline / HashedWheelTimer.this.tickDuration;
                    hashedWheelTimeout.remainingRounds = (j - this.tick) / HashedWheelTimer.this.wheel.length;
                    HashedWheelTimer.this.wheel[(int) (Math.max(j, this.tick) & HashedWheelTimer.this.mask)].addTimeout(hashedWheelTimeout);
                }
            }
        }

        private void processCancelledTasks() {
            while (true) {
                HashedWheelTimeout hashedWheelTimeout = (HashedWheelTimeout) HashedWheelTimer.this.cancelledTimeouts.poll();
                if (hashedWheelTimeout == null) {
                    return;
                }
                try {
                    hashedWheelTimeout.remove();
                } catch (Throwable th) {
                    if (HashedWheelTimer.logger.isWarnEnabled()) {
                        HashedWheelTimer.logger.warn("An exception was thrown while process a cancellation task", th);
                    }
                }
            }
        }

        private long waitForNextTick() {
            long j;
            long j2 = HashedWheelTimer.this.tickDuration * (this.tick + 1);
            while (true) {
                long nanoTime = System.nanoTime() - HashedWheelTimer.this.startTime;
                long j3 = ((j2 - nanoTime) + 999999) / 1000000;
                if (j3 <= 0) {
                    if (nanoTime == Long.MIN_VALUE) {
                        return -9223372036854775807L;
                    }
                    return nanoTime;
                }
                if (PlatformDependent.isWindows()) {
                    j = 10 * (j3 / 10);
                    if (j == 0) {
                        j = 1;
                    }
                } else {
                    j = j3;
                }
                try {
                    Thread.sleep(j);
                } catch (InterruptedException unused) {
                    if (HashedWheelTimer.WORKER_STATE_UPDATER.get(HashedWheelTimer.this) == 2) {
                        return Long.MIN_VALUE;
                    }
                }
            }
        }

        public Set<Timeout> unprocessedTimeouts() {
            return Collections.unmodifiableSet(this.unprocessedTimeouts);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class HashedWheelTimeout implements Timeout {
        private static final AtomicIntegerFieldUpdater<HashedWheelTimeout> STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(HashedWheelTimeout.class, "state");
        private static final int ST_CANCELLED = 1;
        private static final int ST_EXPIRED = 2;
        private static final int ST_INIT = 0;
        HashedWheelBucket bucket;
        private final long deadline;
        HashedWheelTimeout next;
        HashedWheelTimeout prev;
        long remainingRounds;
        private volatile int state = 0;
        private final TimerTask task;
        private final HashedWheelTimer timer;

        HashedWheelTimeout(HashedWheelTimer hashedWheelTimer, TimerTask timerTask, long j) {
            this.timer = hashedWheelTimer;
            this.task = timerTask;
            this.deadline = j;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.Timeout
        public Timer timer() {
            return this.timer;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.Timeout
        public TimerTask task() {
            return this.task;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.Timeout
        public boolean cancel() {
            if (!compareAndSetState(0, 1)) {
                return false;
            }
            this.timer.cancelledTimeouts.add(this);
            return true;
        }

        void remove() {
            HashedWheelBucket hashedWheelBucket = this.bucket;
            if (hashedWheelBucket == null) {
                this.timer.pendingTimeouts.decrementAndGet();
            } else {
                hashedWheelBucket.remove(this);
            }
        }

        public boolean compareAndSetState(int i, int i2) {
            return STATE_UPDATER.compareAndSet(this, i, i2);
        }

        public int state() {
            return this.state;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.Timeout
        public boolean isCancelled() {
            return state() == 1;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.Timeout
        public boolean isExpired() {
            return state() == 2;
        }

        public void expire() {
            if (compareAndSetState(0, 2)) {
                try {
                    this.task.run(this);
                } catch (Throwable th) {
                    if (HashedWheelTimer.logger.isWarnEnabled()) {
                        HashedWheelTimer.logger.warn("An exception was thrown by " + TimerTask.class.getSimpleName() + FilenameUtils.EXTENSION_SEPARATOR, th);
                    }
                }
            }
        }

        public String toString() {
            long nanoTime = (this.deadline - System.nanoTime()) + this.timer.startTime;
            StringBuilder sb = new StringBuilder(192);
            sb.append(StringUtil.simpleClassName(this));
            sb.append('(');
            sb.append("deadline: ");
            if (nanoTime > 0) {
                sb.append(nanoTime);
                sb.append(" ns later");
            } else if (nanoTime < 0) {
                sb.append(-nanoTime);
                sb.append(" ns ago");
            } else {
                sb.append("now");
            }
            if (isCancelled()) {
                sb.append(", cancelled");
            }
            sb.append(", task: ");
            sb.append(task());
            sb.append(')');
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class HashedWheelBucket {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private HashedWheelTimeout head;
        private HashedWheelTimeout tail;

        private HashedWheelBucket() {
        }

        public void addTimeout(HashedWheelTimeout hashedWheelTimeout) {
            hashedWheelTimeout.bucket = this;
            if (this.head == null) {
                this.tail = hashedWheelTimeout;
                this.head = hashedWheelTimeout;
            } else {
                HashedWheelTimeout hashedWheelTimeout2 = this.tail;
                hashedWheelTimeout2.next = hashedWheelTimeout;
                hashedWheelTimeout.prev = hashedWheelTimeout2;
                this.tail = hashedWheelTimeout;
            }
        }

        public void expireTimeouts(long j) {
            HashedWheelTimeout hashedWheelTimeout = this.head;
            while (hashedWheelTimeout != null) {
                HashedWheelTimeout hashedWheelTimeout2 = hashedWheelTimeout.next;
                if (hashedWheelTimeout.remainingRounds <= 0) {
                    hashedWheelTimeout2 = remove(hashedWheelTimeout);
                    if (hashedWheelTimeout.deadline <= j) {
                        hashedWheelTimeout.expire();
                    } else {
                        throw new IllegalStateException(String.format("timeout.deadline (%d) > deadline (%d)", Long.valueOf(hashedWheelTimeout.deadline), Long.valueOf(j)));
                    }
                } else if (hashedWheelTimeout.isCancelled()) {
                    hashedWheelTimeout = remove(hashedWheelTimeout);
                } else {
                    hashedWheelTimeout.remainingRounds--;
                }
                hashedWheelTimeout = hashedWheelTimeout2;
            }
        }

        public HashedWheelTimeout remove(HashedWheelTimeout hashedWheelTimeout) {
            HashedWheelTimeout hashedWheelTimeout2 = hashedWheelTimeout.next;
            if (hashedWheelTimeout.prev != null) {
                hashedWheelTimeout.prev.next = hashedWheelTimeout2;
            }
            if (hashedWheelTimeout.next != null) {
                hashedWheelTimeout.next.prev = hashedWheelTimeout.prev;
            }
            if (hashedWheelTimeout == this.head) {
                if (hashedWheelTimeout == this.tail) {
                    this.tail = null;
                    this.head = null;
                } else {
                    this.head = hashedWheelTimeout2;
                }
            } else if (hashedWheelTimeout == this.tail) {
                this.tail = hashedWheelTimeout.prev;
            }
            hashedWheelTimeout.prev = null;
            hashedWheelTimeout.next = null;
            hashedWheelTimeout.bucket = null;
            hashedWheelTimeout.timer.pendingTimeouts.decrementAndGet();
            return hashedWheelTimeout2;
        }

        public void clearTimeouts(Set<Timeout> set) {
            while (true) {
                HashedWheelTimeout pollTimeout = pollTimeout();
                if (pollTimeout == null) {
                    return;
                }
                if (!pollTimeout.isExpired() && !pollTimeout.isCancelled()) {
                    set.add(pollTimeout);
                }
            }
        }

        private HashedWheelTimeout pollTimeout() {
            HashedWheelTimeout hashedWheelTimeout = this.head;
            if (hashedWheelTimeout == null) {
                return null;
            }
            HashedWheelTimeout hashedWheelTimeout2 = hashedWheelTimeout.next;
            if (hashedWheelTimeout2 == null) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = hashedWheelTimeout2;
                hashedWheelTimeout2.prev = null;
            }
            hashedWheelTimeout.next = null;
            hashedWheelTimeout.prev = null;
            hashedWheelTimeout.bucket = null;
            return hashedWheelTimeout;
        }
    }
}
