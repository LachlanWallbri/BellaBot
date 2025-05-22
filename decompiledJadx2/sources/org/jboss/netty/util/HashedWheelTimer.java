package org.jboss.netty.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.ConcurrentIdentityHashMap;
import org.jboss.netty.util.internal.DetectionUtil;
import org.jboss.netty.util.internal.ReusableIterator;
import org.jboss.netty.util.internal.SharedResourceMisuseDetector;

/* loaded from: classes7.dex */
public class HashedWheelTimer implements Timer {
    final ReusableIterator<HashedWheelTimeout>[] iterators;
    final ReadWriteLock lock;
    final int mask;
    private final long roundDuration;
    final AtomicBoolean shutdown;
    final long tickDuration;
    final Set<HashedWheelTimeout>[] wheel;
    volatile int wheelCursor;
    private final Worker worker;
    final Thread workerThread;
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) HashedWheelTimer.class);

    /* renamed from: id */
    private static final AtomicInteger f10051id = new AtomicInteger();
    private static final SharedResourceMisuseDetector misuseDetector = new SharedResourceMisuseDetector(HashedWheelTimer.class);

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
        this.worker = new Worker();
        this.shutdown = new AtomicBoolean();
        this.lock = new ReentrantReadWriteLock();
        if (threadFactory == null) {
            throw new NullPointerException("threadFactory");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit");
        }
        if (j <= 0) {
            throw new IllegalArgumentException("tickDuration must be greater than 0: " + j);
        }
        if (i <= 0) {
            throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + i);
        }
        this.wheel = createWheel(i);
        this.iterators = createIterators(this.wheel);
        this.mask = this.wheel.length - 1;
        long millis = timeUnit.toMillis(j);
        this.tickDuration = millis;
        if (millis != Long.MAX_VALUE) {
            Set<HashedWheelTimeout>[] setArr = this.wheel;
            if (millis < Long.MAX_VALUE / setArr.length) {
                this.roundDuration = millis * setArr.length;
                this.workerThread = threadFactory.newThread(new ThreadRenamingRunnable(this.worker, "Hashed wheel timer #" + f10051id.incrementAndGet()));
                misuseDetector.increase();
                return;
            }
        }
        throw new IllegalArgumentException("tickDuration is too long: " + millis + ' ' + timeUnit);
    }

    private static Set<HashedWheelTimeout>[] createWheel(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + i);
        }
        if (i > 1073741824) {
            throw new IllegalArgumentException("ticksPerWheel may not be greater than 2^30: " + i);
        }
        Set<HashedWheelTimeout>[] setArr = new Set[normalizeTicksPerWheel(i)];
        for (int i2 = 0; i2 < setArr.length; i2++) {
            setArr[i2] = new MapBackedSet(new ConcurrentIdentityHashMap(16, 0.95f, 4));
        }
        return setArr;
    }

    private static ReusableIterator<HashedWheelTimeout>[] createIterators(Set<HashedWheelTimeout>[] setArr) {
        ReusableIterator<HashedWheelTimeout>[] reusableIteratorArr = new ReusableIterator[setArr.length];
        for (int i = 0; i < setArr.length; i++) {
            reusableIteratorArr[i] = (ReusableIterator) setArr[i].iterator();
        }
        return reusableIteratorArr;
    }

    public synchronized void start() {
        if (this.shutdown.get()) {
            throw new IllegalStateException("cannot be started once stopped");
        }
        if (!this.workerThread.isAlive()) {
            this.workerThread.start();
        }
    }

    @Override // org.jboss.netty.util.Timer
    public synchronized Set<Timeout> stop() {
        if (Thread.currentThread() == this.workerThread) {
            throw new IllegalStateException(HashedWheelTimer.class.getSimpleName() + ".stop() cannot be called from " + TimerTask.class.getSimpleName());
        }
        if (!this.shutdown.compareAndSet(false, true)) {
            return Collections.emptySet();
        }
        boolean z = false;
        while (this.workerThread.isAlive()) {
            this.workerThread.interrupt();
            try {
                this.workerThread.join(100L);
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        misuseDetector.decrease();
        HashSet hashSet = new HashSet();
        for (Set<HashedWheelTimeout> set : this.wheel) {
            hashSet.addAll(set);
            set.clear();
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // org.jboss.netty.util.Timer
    public Timeout newTimeout(TimerTask timerTask, long j, TimeUnit timeUnit) {
        long currentTimeMillis = System.currentTimeMillis();
        if (timerTask == null) {
            throw new NullPointerException("task");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit");
        }
        if (!this.workerThread.isAlive()) {
            start();
        }
        long millis = timeUnit.toMillis(j);
        HashedWheelTimeout hashedWheelTimeout = new HashedWheelTimeout(timerTask, currentTimeMillis + millis);
        scheduleTimeout(hashedWheelTimeout, millis);
        return hashedWheelTimeout;
    }

    void scheduleTimeout(HashedWheelTimeout hashedWheelTimeout, long j) {
        long j2 = this.tickDuration;
        if (j < j2) {
            j = j2;
        }
        long j3 = j % this.roundDuration;
        long j4 = this.tickDuration;
        long j5 = (j3 / j4) + (j % j4 != 0 ? 1 : 0);
        long j6 = this.roundDuration;
        long j7 = (j / j6) - (j % j6 != 0 ? 0 : 1);
        this.lock.readLock().lock();
        try {
            int i = (int) ((this.wheelCursor + j5) & this.mask);
            hashedWheelTimeout.stopIndex = i;
            hashedWheelTimeout.remainingRounds = j7;
            this.wheel[i].add(hashedWheelTimeout);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    /* loaded from: classes7.dex */
    private final class Worker implements Runnable {
        private long startTime;
        private long tick;

        Worker() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList arrayList = new ArrayList();
            this.startTime = System.currentTimeMillis();
            this.tick = 1L;
            while (!HashedWheelTimer.this.shutdown.get()) {
                long waitForNextTick = waitForNextTick();
                if (waitForNextTick > 0) {
                    fetchExpiredTimeouts(arrayList, waitForNextTick);
                    notifyExpiredTimeouts(arrayList);
                }
            }
        }

        private void fetchExpiredTimeouts(List<HashedWheelTimeout> list, long j) {
            HashedWheelTimer.this.lock.writeLock().lock();
            try {
                HashedWheelTimer hashedWheelTimer = HashedWheelTimer.this;
                int i = (HashedWheelTimer.this.wheelCursor + 1) & HashedWheelTimer.this.mask;
                hashedWheelTimer.wheelCursor = i;
                fetchExpiredTimeouts(list, HashedWheelTimer.this.iterators[i], j);
            } finally {
                HashedWheelTimer.this.lock.writeLock().unlock();
            }
        }

        private void fetchExpiredTimeouts(List<HashedWheelTimeout> list, ReusableIterator<HashedWheelTimeout> reusableIterator, long j) {
            reusableIterator.rewind();
            ArrayList<HashedWheelTimeout> arrayList = null;
            while (reusableIterator.hasNext()) {
                HashedWheelTimeout next = reusableIterator.next();
                if (next.remainingRounds <= 0) {
                    reusableIterator.remove();
                    if (next.deadline <= j) {
                        list.add(next);
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(next);
                    }
                } else {
                    next.remainingRounds--;
                }
            }
            if (arrayList != null) {
                for (HashedWheelTimeout hashedWheelTimeout : arrayList) {
                    HashedWheelTimer.this.scheduleTimeout(hashedWheelTimeout, hashedWheelTimeout.deadline - j);
                }
            }
        }

        private void notifyExpiredTimeouts(List<HashedWheelTimeout> list) {
            for (int size = list.size() - 1; size >= 0; size--) {
                list.get(size).expire();
            }
            list.clear();
        }

        private long waitForNextTick() {
            long j = this.startTime + (HashedWheelTimer.this.tickDuration * this.tick);
            while (true) {
                long currentTimeMillis = (HashedWheelTimer.this.tickDuration * this.tick) - (System.currentTimeMillis() - this.startTime);
                if (DetectionUtil.isWindows()) {
                    currentTimeMillis = (currentTimeMillis / 10) * 10;
                }
                if (currentTimeMillis > 0) {
                    try {
                        Thread.sleep(currentTimeMillis);
                    } catch (InterruptedException unused) {
                        if (HashedWheelTimer.this.shutdown.get()) {
                            return -1L;
                        }
                    }
                } else {
                    this.tick++;
                    return j;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class HashedWheelTimeout implements Timeout {
        private static final int ST_CANCELLED = 1;
        private static final int ST_EXPIRED = 2;
        private static final int ST_INIT = 0;
        final long deadline;
        volatile long remainingRounds;
        private final AtomicInteger state = new AtomicInteger(0);
        volatile int stopIndex;
        private final TimerTask task;

        HashedWheelTimeout(TimerTask timerTask, long j) {
            this.task = timerTask;
            this.deadline = j;
        }

        @Override // org.jboss.netty.util.Timeout
        public Timer getTimer() {
            return HashedWheelTimer.this;
        }

        @Override // org.jboss.netty.util.Timeout
        public TimerTask getTask() {
            return this.task;
        }

        @Override // org.jboss.netty.util.Timeout
        public void cancel() {
            if (this.state.compareAndSet(0, 1)) {
                HashedWheelTimer.this.wheel[this.stopIndex].remove(this);
            }
        }

        @Override // org.jboss.netty.util.Timeout
        public boolean isCancelled() {
            return this.state.get() == 1;
        }

        @Override // org.jboss.netty.util.Timeout
        public boolean isExpired() {
            return this.state.get() != 0;
        }

        public void expire() {
            if (this.state.compareAndSet(0, 2)) {
                try {
                    this.task.run(this);
                } catch (Throwable th) {
                    if (HashedWheelTimer.logger.isWarnEnabled()) {
                        HashedWheelTimer.logger.warn("An exception was thrown by " + TimerTask.class.getSimpleName() + ".", th);
                    }
                }
            }
        }

        public String toString() {
            long currentTimeMillis = this.deadline - System.currentTimeMillis();
            StringBuilder sb = new StringBuilder(192);
            sb.append(getClass().getSimpleName());
            sb.append('(');
            sb.append("deadline: ");
            if (currentTimeMillis > 0) {
                sb.append(currentTimeMillis);
                sb.append(" ms later, ");
            } else if (currentTimeMillis < 0) {
                sb.append(-currentTimeMillis);
                sb.append(" ms ago, ");
            } else {
                sb.append("now, ");
            }
            if (isCancelled()) {
                sb.append(", cancelled");
            }
            sb.append(')');
            return sb.toString();
        }
    }
}
