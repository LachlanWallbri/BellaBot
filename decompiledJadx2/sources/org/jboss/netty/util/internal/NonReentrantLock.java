package org.jboss.netty.util.internal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes7.dex */
public final class NonReentrantLock extends AbstractQueuedSynchronizer implements Lock {
    private static final long serialVersionUID = -833780837233068610L;
    private Thread owner;

    @Override // java.util.concurrent.locks.Lock
    public void lock() {
        acquire(1);
    }

    @Override // java.util.concurrent.locks.Lock
    public void lockInterruptibly() throws InterruptedException {
        acquireInterruptibly(1);
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock() {
        return tryAcquire(1);
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
        return tryAcquireNanos(1, timeUnit.toNanos(j));
    }

    @Override // java.util.concurrent.locks.Lock
    public void unlock() {
        release(1);
    }

    public boolean isHeldByCurrentThread() {
        return isHeldExclusively();
    }

    @Override // java.util.concurrent.locks.Lock
    public Condition newCondition() {
        return new AbstractQueuedSynchronizer.ConditionObject(this);
    }

    @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
    protected boolean tryAcquire(int i) {
        if (!compareAndSetState(0, 1)) {
            return false;
        }
        this.owner = Thread.currentThread();
        return true;
    }

    @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
    protected boolean tryRelease(int i) {
        if (Thread.currentThread() != this.owner) {
            throw new IllegalMonitorStateException();
        }
        this.owner = null;
        setState(0);
        return true;
    }

    @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
    protected boolean isHeldExclusively() {
        return getState() != 0 && this.owner == Thread.currentThread();
    }
}
