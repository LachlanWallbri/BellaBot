package com.google.api.gax.batching;

import com.google.api.core.BetaApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface BatchingThreshold<E> {
    void accumulate(E e);

    BatchingThreshold<E> copyWithZeroedValue();

    boolean isThresholdReached();
}
