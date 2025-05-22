package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.retrying.RetryingExecutorWithContext;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class RecheckingCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final RetryingExecutorWithContext<ResponseT> executor;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.api.gax.rpc.UnaryCallable
    public /* bridge */ /* synthetic */ ApiFuture futureCall(Object obj, ApiCallContext apiCallContext) {
        return futureCall((RecheckingCallable<RequestT, ResponseT>) obj, apiCallContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecheckingCallable(UnaryCallable<RequestT, ResponseT> unaryCallable, RetryingExecutorWithContext<ResponseT> retryingExecutorWithContext) {
        this.callable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.executor = (RetryingExecutorWithContext) Preconditions.checkNotNull(retryingExecutorWithContext);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public RetryingFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        CheckingAttemptCallable checkingAttemptCallable = new CheckingAttemptCallable(this.callable, apiCallContext);
        RetryingFuture<ResponseT> createFuture = this.executor.createFuture(checkingAttemptCallable, apiCallContext);
        checkingAttemptCallable.setExternalFuture(createFuture);
        checkingAttemptCallable.call();
        return createFuture;
    }

    public String toString() {
        return String.format("rechecking(%s)", this.callable);
    }
}
