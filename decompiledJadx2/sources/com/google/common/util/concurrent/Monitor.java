package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Monitor {
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static abstract class Guard {
        final Condition condition;
        final Monitor monitor;
        Guard next;
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        /* JADX INFO: Access modifiers changed from: protected */
        public Guard(Monitor monitor) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.condition = monitor.lock.newCondition();
        }
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean z) {
        this.activeGuards = null;
        this.fair = z;
        this.lock = new ReentrantLock(z);
    }

    public Guard newGuard(final BooleanSupplier booleanSupplier) {
        Preconditions.checkNotNull(booleanSupplier, "isSatisfied");
        return new Guard(this, this) { // from class: com.google.common.util.concurrent.Monitor.1
            @Override // com.google.common.util.concurrent.Monitor.Guard
            public boolean isSatisfied() {
                return booleanSupplier.getAsBoolean();
            }
        };
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enter(Duration duration) {
        return enter(Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    public boolean enter(long j, TimeUnit timeUnit) {
        boolean tryLock;
        long safeNanos = toSafeNanos(j, timeUnit);
        ReentrantLock reentrantLock = this.lock;
        if (!this.fair && reentrantLock.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            long nanoTime = System.nanoTime();
            long j2 = safeNanos;
            while (true) {
                try {
                    try {
                        tryLock = reentrantLock.tryLock(j2, TimeUnit.NANOSECONDS);
                        break;
                    } catch (InterruptedException unused) {
                        j2 = remainingNanos(nanoTime, safeNanos);
                        interrupted = true;
                    }
                } catch (Throwable th) {
                    th = th;
                    interrupted = true;
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return tryLock;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enterInterruptibly(Duration duration) throws InterruptedException {
        return enterInterruptibly(Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    public boolean enterInterruptibly(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.lock.tryLock(j, timeUnit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lockInterruptibly();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            await(guard, isHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    public boolean enterWhen(Guard guard, Duration duration) throws InterruptedException {
        return enterWhen(guard, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        if (awaitNanos(r11, r0, r3) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004c A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhen(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        long initNanoTime;
        long safeNanos = toSafeNanos(j, timeUnit);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        boolean z = false;
        try {
            if (!this.fair) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                if (reentrantLock.tryLock()) {
                    initNanoTime = 0;
                    if (!guard.isSatisfied()) {
                        if (initNanoTime != 0) {
                            safeNanos = remainingNanos(initNanoTime, safeNanos);
                        }
                    }
                    z = true;
                    if (!z) {
                    }
                    return z;
                }
            }
            if (!guard.isSatisfied()) {
            }
            z = true;
            if (!z) {
            }
            return z;
        } catch (Throwable th) {
            if (!isHeldByCurrentThread) {
                try {
                    signalNextWaiter();
                } finally {
                    reentrantLock.unlock();
                }
            }
            throw th;
        }
        initNanoTime = initNanoTime(safeNanos);
        if (!reentrantLock.tryLock(j, timeUnit)) {
            return false;
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lock();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            awaitUninterruptibly(guard, isHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    public boolean enterWhenUninterruptibly(Guard guard, Duration duration) {
        return enterWhenUninterruptibly(guard, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004b A[Catch: all -> 0x0073, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0073, blocks: (B:5:0x0013, B:7:0x001a, B:22:0x004b, B:32:0x0059, B:33:0x005c, B:34:0x0023, B:37:0x0028, B:13:0x0030, B:17:0x003b, B:18:0x0045, B:27:0x0041), top: B:4:0x0013, inners: #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhenUninterruptibly(Guard guard, long j, TimeUnit timeUnit) {
        long initNanoTime;
        long remainingNanos;
        long safeNanos = toSafeNanos(j, timeUnit);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        boolean interrupted = Thread.interrupted();
        boolean z = true;
        try {
            if (!this.fair && reentrantLock.tryLock()) {
                initNanoTime = 0;
                while (!guard.isSatisfied()) {
                    try {
                        if (initNanoTime == 0) {
                            initNanoTime = initNanoTime(safeNanos);
                            remainingNanos = safeNanos;
                        } else {
                            remainingNanos = remainingNanos(initNanoTime, safeNanos);
                        }
                        z = awaitNanos(guard, remainingNanos, isHeldByCurrentThread);
                    } catch (InterruptedException unused) {
                        interrupted = z;
                        isHeldByCurrentThread = false;
                    } catch (Throwable th) {
                        reentrantLock.unlock();
                        throw th;
                    }
                }
                if (!z) {
                    reentrantLock.unlock();
                }
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                return z;
            }
            initNanoTime = initNanoTime(safeNanos);
            long j2 = safeNanos;
            while (true) {
                try {
                    try {
                        break;
                    } catch (InterruptedException unused2) {
                        j2 = remainingNanos(initNanoTime, safeNanos);
                        interrupted = true;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    interrupted = true;
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (!reentrantLock.tryLock(j2, TimeUnit.NANOSECONDS)) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                return false;
            }
            while (!guard.isSatisfied()) {
            }
            if (!z) {
            }
            if (interrupted) {
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean enterIf(Guard guard, Duration duration) {
        return enterIf(guard, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    public boolean enterIf(Guard guard, long j, TimeUnit timeUnit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        if (!enter(j, timeUnit)) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean enterIfInterruptibly(Guard guard, Duration duration) throws InterruptedException {
        return enterIfInterruptibly(guard, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    public boolean enterIfInterruptibly(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        if (!reentrantLock.tryLock(j, timeUnit)) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        if (!reentrantLock.tryLock()) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (!((guard.monitor == this) & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        await(guard, true);
    }

    public boolean waitFor(Guard guard, Duration duration) throws InterruptedException {
        return waitFor(guard, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    public boolean waitFor(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        long safeNanos = toSafeNanos(j, timeUnit);
        if (!((guard.monitor == this) & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return true;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return awaitNanos(guard, safeNanos, true);
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!((guard.monitor == this) & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        awaitUninterruptibly(guard, true);
    }

    public boolean waitForUninterruptibly(Guard guard, Duration duration) {
        return waitForUninterruptibly(guard, Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean waitForUninterruptibly(Guard guard, long j, TimeUnit timeUnit) {
        long safeNanos = toSafeNanos(j, timeUnit);
        boolean z = true;
        if (!((guard.monitor == this) & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return true;
        }
        long initNanoTime = initNanoTime(safeNanos);
        long j2 = safeNanos;
        boolean interrupted = Thread.interrupted();
        boolean z2 = true;
        while (true) {
            try {
                try {
                    boolean awaitNanos = awaitNanos(guard, j2, z2);
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return awaitNanos;
                } catch (InterruptedException unused) {
                    if (!guard.isSatisfied()) {
                        j2 = remainingNanos(initNanoTime, safeNanos);
                        z2 = false;
                        interrupted = true;
                    } else {
                        Thread.currentThread().interrupt();
                        return true;
                    }
                } catch (Throwable th) {
                    th = th;
                    z = interrupted;
                    if (z) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        this.lock.lock();
        try {
            return guard.waiterCount;
        } finally {
            this.lock.unlock();
        }
    }

    private static long toSafeNanos(long j, TimeUnit timeUnit) {
        return Longs.constrainToRange(timeUnit.toNanos(j), 0L, 6917529027641081853L);
    }

    private static long initNanoTime(long j) {
        if (j <= 0) {
            return 0L;
        }
        long nanoTime = System.nanoTime();
        if (nanoTime == 0) {
            return 1L;
        }
        return nanoTime;
    }

    private static long remainingNanos(long j, long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        return j2 - (System.nanoTime() - j);
    }

    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            throw th;
        }
    }

    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    private void beginWaitingFor(Guard guard) {
        int i = guard.waiterCount;
        guard.waiterCount = i + 1;
        if (i == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    private void endWaitingFor(Guard guard) {
        int i = guard.waiterCount - 1;
        guard.waiterCount = i;
        if (i == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.next;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    private void await(Guard guard, boolean z) throws InterruptedException {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private void awaitUninterruptibly(Guard guard, boolean z) {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private boolean awaitNanos(Guard guard, long j, boolean z) throws InterruptedException {
        boolean z2 = true;
        while (j > 0) {
            if (z2) {
                if (z) {
                    try {
                        signalNextWaiter();
                    } finally {
                        if (!z2) {
                            endWaitingFor(guard);
                        }
                    }
                }
                beginWaitingFor(guard);
                z2 = false;
            }
            j = guard.condition.awaitNanos(j);
            if (guard.isSatisfied()) {
                if (!z2) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        return false;
    }
}
