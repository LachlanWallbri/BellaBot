package com.google.api.gax.batching;

import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class AccumulatingBatchReceiver<T> implements ThresholdBatchReceiver<T> {
    private final ConcurrentLinkedQueue<T> batches = new ConcurrentLinkedQueue<>();
    private final ApiFuture<?> retFuture;

    @Override // com.google.api.gax.batching.ThresholdBatchReceiver
    public void validateBatch(T t) {
    }

    public AccumulatingBatchReceiver(ApiFuture<?> apiFuture) {
        this.retFuture = apiFuture;
    }

    @Override // com.google.api.gax.batching.ThresholdBatchReceiver
    public ApiFuture<?> processBatch(T t) {
        this.batches.add(t);
        return this.retFuture;
    }

    public List<T> getBatches() {
        return new ArrayList(this.batches);
    }
}
