package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.InternalApi;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For use by transport-specific implementations")
/* loaded from: classes2.dex */
public class BatchingCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final BatcherFactory<RequestT, ResponseT> batcherFactory;
    private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
    private final UnaryCallable<RequestT, ResponseT> callable;

    public BatchingCallable(UnaryCallable<RequestT, ResponseT> unaryCallable, BatchingDescriptor<RequestT, ResponseT> batchingDescriptor, BatcherFactory<RequestT, ResponseT> batcherFactory) {
        this.callable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.batchingDescriptor = (BatchingDescriptor) Preconditions.checkNotNull(batchingDescriptor);
        this.batcherFactory = (BatcherFactory) Preconditions.checkNotNull(batcherFactory);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        if (this.batcherFactory.getBatchingSettings().getIsEnabled().booleanValue()) {
            BatchedFuture create = BatchedFuture.create();
            try {
                this.batcherFactory.getPushingBatcher(this.batchingDescriptor.getBatchPartitionKey(requestt)).add(new Batch<>(this.batchingDescriptor, requestt, this.callable.withDefaultCallContext(apiCallContext), create));
                return create;
            } catch (FlowController.FlowControlException e) {
                throw FlowController.FlowControlRuntimeException.fromFlowControlException(e);
            }
        }
        return this.callable.futureCall(requestt, apiCallContext);
    }
}
