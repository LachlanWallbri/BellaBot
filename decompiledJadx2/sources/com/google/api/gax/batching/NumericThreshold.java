package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class NumericThreshold<E> implements BatchingThreshold<E> {
    private final ElementCounter<E> extractor;
    private long sum = 0;
    private final long threshold;

    public NumericThreshold(long j, ElementCounter<E> elementCounter) {
        this.threshold = j;
        this.extractor = (ElementCounter) Preconditions.checkNotNull(elementCounter);
    }

    @Override // com.google.api.gax.batching.BatchingThreshold
    public void accumulate(E e) {
        this.sum += this.extractor.count(e);
    }

    @Override // com.google.api.gax.batching.BatchingThreshold
    public boolean isThresholdReached() {
        return this.sum >= this.threshold;
    }

    @Override // com.google.api.gax.batching.BatchingThreshold
    public BatchingThreshold<E> copyWithZeroedValue() {
        return new NumericThreshold(this.threshold, this.extractor);
    }
}
