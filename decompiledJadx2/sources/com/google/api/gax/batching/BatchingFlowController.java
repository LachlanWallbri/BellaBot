package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class BatchingFlowController<T> {
    private final ElementCounter<T> byteCounter;
    private final ElementCounter<T> elementCounter;
    private final FlowController flowController;

    public BatchingFlowController(FlowController flowController, ElementCounter<T> elementCounter, ElementCounter<T> elementCounter2) {
        this.flowController = flowController;
        this.elementCounter = elementCounter;
        this.byteCounter = elementCounter2;
    }

    public void reserve(T t) throws FlowController.FlowControlException {
        Preconditions.checkNotNull(t);
        this.flowController.reserve(Ints.checkedCast(this.elementCounter.count(t)), Ints.checkedCast(this.byteCounter.count(t)));
    }

    public void release(T t) {
        Preconditions.checkNotNull(t);
        this.flowController.release(Ints.checkedCast(this.elementCounter.count(t)), Ints.checkedCast(this.byteCounter.count(t)));
    }
}
