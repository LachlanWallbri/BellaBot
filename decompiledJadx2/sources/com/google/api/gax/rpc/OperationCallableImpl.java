package com.google.api.gax.rpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationFutureImpl;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.RetryingExecutorWithContext;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class OperationCallableImpl<RequestT, ResponseT, MetadataT> extends OperationCallable<RequestT, ResponseT, MetadataT> {
    private final RetryingExecutorWithContext<OperationSnapshot> executor;
    private final UnaryCallable<RequestT, OperationSnapshot> initialCallable;
    private final LongRunningClient longRunningClient;
    private final ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;
    private final ApiFunction<OperationSnapshot, ResponseT> responseTransformer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OperationCallableImpl(UnaryCallable<RequestT, OperationSnapshot> unaryCallable, RetryingExecutorWithContext<OperationSnapshot> retryingExecutorWithContext, LongRunningClient longRunningClient, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings) {
        this.initialCallable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.executor = (RetryingExecutorWithContext) Preconditions.checkNotNull(retryingExecutorWithContext);
        this.longRunningClient = (LongRunningClient) Preconditions.checkNotNull(longRunningClient);
        this.responseTransformer = operationCallSettings.getResponseTransformer();
        this.metadataTransformer = operationCallSettings.getMetadataTransformer();
    }

    @Override // com.google.api.gax.rpc.OperationCallable
    public OperationFuture<ResponseT, MetadataT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        return futureCall(this.initialCallable.futureCall(requestt, apiCallContext), apiCallContext);
    }

    OperationFutureImpl<ResponseT, MetadataT> futureCall(ApiFuture<OperationSnapshot> apiFuture, ApiCallContext apiCallContext) {
        return new OperationFutureImpl<>(new RecheckingCallable(new OperationCheckingCallable(this.longRunningClient, apiFuture), this.executor).futureCall((RecheckingCallable) null, apiCallContext), apiFuture, this.responseTransformer, this.metadataTransformer);
    }

    @Override // com.google.api.gax.rpc.OperationCallable
    public OperationFuture<ResponseT, MetadataT> resumeFutureCall(String str, ApiCallContext apiCallContext) {
        return futureCall(this.longRunningClient.getOperationCallable().futureCall(str, apiCallContext), apiCallContext);
    }

    @Override // com.google.api.gax.rpc.OperationCallable
    public ApiFuture<Void> cancel(String str, ApiCallContext apiCallContext) {
        return this.longRunningClient.cancelOperationCallable().futureCall(str, apiCallContext);
    }
}
