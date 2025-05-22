package com.google.api.gax.rpc;

import com.google.api.core.ApiFutures;
import com.google.api.gax.retrying.NonCancellableFuture;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class CheckingAttemptCallable<RequestT, ResponseT> implements Callable<ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private volatile RetryingFuture<ResponseT> externalFuture;
    private final ApiCallContext originalCallContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CheckingAttemptCallable(UnaryCallable<RequestT, ResponseT> unaryCallable, ApiCallContext apiCallContext) {
        this.callable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.originalCallContext = (ApiCallContext) Preconditions.checkNotNull(apiCallContext);
    }

    public void setExternalFuture(RetryingFuture<ResponseT> retryingFuture) {
        this.externalFuture = (RetryingFuture) Preconditions.checkNotNull(retryingFuture);
    }

    @Override // java.util.concurrent.Callable
    public ResponseT call() {
        ApiCallContext apiCallContext = this.originalCallContext;
        try {
            Duration rpcTimeout = this.externalFuture.getAttemptSettings().getRpcTimeout();
            if (!rpcTimeout.isZero()) {
                apiCallContext = apiCallContext.withTimeout(rpcTimeout);
            }
            this.externalFuture.setAttemptFuture(new NonCancellableFuture());
        } catch (Throwable th) {
            this.externalFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(th));
        }
        if (this.externalFuture.isDone()) {
            return null;
        }
        apiCallContext.getTracer().attemptStarted(this.externalFuture.getAttemptSettings().getOverallAttemptCount());
        this.externalFuture.setAttemptFuture(this.callable.futureCall(null, apiCallContext));
        return null;
    }
}
