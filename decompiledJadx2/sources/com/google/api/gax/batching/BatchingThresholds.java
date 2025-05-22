package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class BatchingThresholds {
    public static <E> List<BatchingThreshold<E>> create(long j) {
        return ImmutableList.m635of(new NumericThreshold(j, new ElementCounter<E>() { // from class: com.google.api.gax.batching.BatchingThresholds.1
            @Override // com.google.api.gax.batching.ElementCounter
            public long count(E e) {
                return 1L;
            }
        }));
    }
}
