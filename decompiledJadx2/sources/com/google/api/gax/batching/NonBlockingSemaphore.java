package com.google.api.gax.batching;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class NonBlockingSemaphore implements Semaphore64 {
    private AtomicLong acquiredPermits;
    private AtomicLong limit;

    private static void checkNotNegative(long j) {
        Preconditions.checkArgument(j >= 0, "negative permits not allowed: %s", j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NonBlockingSemaphore(long j) {
        checkNotNegative(j);
        this.acquiredPermits = new AtomicLong(0L);
        this.limit = new AtomicLong(j);
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public void release(long j) {
        long j2;
        checkNotNegative(j);
        do {
            j2 = this.acquiredPermits.get();
        } while (!this.acquiredPermits.compareAndSet(j2, Math.max(0L, j2 - j)));
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public boolean acquire(long j) {
        long j2;
        long j3;
        checkNotNegative(j);
        do {
            j2 = this.acquiredPermits.get();
            j3 = j2 + j;
            if (j3 > this.limit.get()) {
                return false;
            }
        } while (!this.acquiredPermits.compareAndSet(j2, j3));
        return true;
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public boolean acquirePartial(long j) {
        long j2;
        long j3;
        checkNotNegative(j);
        do {
            j2 = this.acquiredPermits.get();
            j3 = j2 + j;
            if (j3 > this.limit.get() && j2 > 0) {
                return false;
            }
        } while (!this.acquiredPermits.compareAndSet(j2, j3));
        return true;
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public void increasePermitLimit(long j) {
        checkNotNegative(j);
        this.limit.addAndGet(j);
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public void reducePermitLimit(long j) {
        long j2;
        long j3;
        checkNotNegative(j);
        do {
            j2 = this.limit.get();
            j3 = j2 - j;
            Preconditions.checkState(j3 > 0, "permit limit underflow");
        } while (!this.limit.compareAndSet(j2, j3));
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public long getPermitLimit() {
        return this.limit.get();
    }
}
