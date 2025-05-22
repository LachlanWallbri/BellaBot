package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.core.ListenableFutureToApiFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ScheduledRetryingExecutor<ResponseT> implements RetryingExecutorWithContext<ResponseT> {
    private final RetryAlgorithm<ResponseT> retryAlgorithm;
    private final ListeningScheduledExecutorService scheduler;

    public ScheduledRetryingExecutor(RetryAlgorithm<ResponseT> retryAlgorithm, ScheduledExecutorService scheduledExecutorService) {
        this.retryAlgorithm = retryAlgorithm;
        this.scheduler = MoreExecutors.listeningDecorator(scheduledExecutorService);
    }

    @Override // com.google.api.gax.retrying.RetryingExecutor
    public RetryingFuture<ResponseT> createFuture(Callable<ResponseT> callable) {
        return createFuture(callable, NoopRetryingContext.create());
    }

    @Override // com.google.api.gax.retrying.RetryingExecutorWithContext
    @BetaApi("The surface for passing per operation state is not yet stable")
    public RetryingFuture<ResponseT> createFuture(Callable<ResponseT> callable, RetryingContext retryingContext) {
        return new CallbackChainRetryingFuture(callable, this.retryAlgorithm, this, retryingContext);
    }

    @Override // com.google.api.gax.retrying.RetryingExecutor
    public ApiFuture<ResponseT> submit(RetryingFuture<ResponseT> retryingFuture) {
        try {
            return new ListenableFutureToApiFuture(this.scheduler.schedule((Callable) retryingFuture.getCallable(), retryingFuture.getAttemptSettings().getRandomizedRetryDelay().toMillis(), TimeUnit.MILLISECONDS));
        } catch (RejectedExecutionException e) {
            return ApiFutures.immediateFailedFuture(e);
        }
    }
}
