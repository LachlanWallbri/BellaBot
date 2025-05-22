package com.google.api.gax.batching;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
interface Semaphore64 {
    boolean acquire(long j);

    boolean acquirePartial(long j);

    long getPermitLimit();

    void increasePermitLimit(long j);

    void reducePermitLimit(long j);

    void release(long j);
}
