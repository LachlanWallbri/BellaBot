package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.retrying.RetryingExecutorWithContext;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class RetryingCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final ApiCallContext callContextPrototype;
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final RetryingExecutorWithContext<ResponseT> executor;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.api.gax.rpc.UnaryCallable
    public /* bridge */ /* synthetic */ ApiFuture futureCall(Object obj, ApiCallContext apiCallContext) {
        return futureCall((RetryingCallable<RequestT, ResponseT>) obj, apiCallContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RetryingCallable(ApiCallContext apiCallContext, UnaryCallable<RequestT, ResponseT> unaryCallable, RetryingExecutorWithContext<ResponseT> retryingExecutorWithContext) {
        this.callContextPrototype = (ApiCallContext) Preconditions.checkNotNull(apiCallContext);
        this.callable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.executor = (RetryingExecutorWithContext) Preconditions.checkNotNull(retryingExecutorWithContext);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public RetryingFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        AttemptCallable attemptCallable = new AttemptCallable(this.callable, requestt, this.callContextPrototype.nullToSelf(apiCallContext));
        RetryingFuture<ResponseT> createFuture = this.executor.createFuture(attemptCallable, apiCallContext);
        attemptCallable.setExternalFuture(createFuture);
        attemptCallable.call();
        return createFuture;
    }

    public String toString() {
        return String.format("retrying(%s)", this.callable);
    }
}
