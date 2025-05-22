package com.google.api.gax.batching;

import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class BlockingSemaphore implements Semaphore64 {
    private long availablePermits;
    private long limit;

    private static void checkNotNegative(long j) {
        Preconditions.checkArgument(j >= 0, "negative permits not allowed: %s", j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BlockingSemaphore(long j) {
        checkNotNegative(j);
        this.availablePermits = j;
        this.limit = j;
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public synchronized void release(long j) {
        checkNotNegative(j);
        this.availablePermits = Math.min(this.availablePermits + j, this.limit);
        notifyAll();
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public synchronized boolean acquire(long j) {
        checkNotNegative(j);
        boolean z = false;
        while (this.availablePermits < j) {
            try {
                wait();
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        this.availablePermits -= j;
        if (z) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public synchronized boolean acquirePartial(long j) {
        checkNotNegative(j);
        boolean z = false;
        while (this.availablePermits < Math.min(this.limit, j)) {
            try {
                wait();
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        this.availablePermits -= j;
        return true;
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public synchronized void increasePermitLimit(long j) {
        checkNotNegative(j);
        this.availablePermits += j;
        this.limit += j;
        notifyAll();
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public synchronized void reducePermitLimit(long j) {
        checkNotNegative(j);
        Preconditions.checkState(this.limit - j > 0, "permit limit underflow");
        this.availablePermits -= j;
        this.limit -= j;
    }

    @Override // com.google.api.gax.batching.Semaphore64
    public synchronized long getPermitLimit() {
        return this.limit;
    }
}
