package com.google.api.gax.longrunning;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
@BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class OperationFutureImpl<ResponseT, MetadataT> implements OperationFuture<ResponseT, MetadataT> {
    private volatile ApiFuture<OperationSnapshot> gottenAttemptResult;
    private volatile ApiFuture<MetadataT> gottenPollResult;
    private final ApiFuture<OperationSnapshot> initialFuture;
    private final Object lock = new Object();
    private final ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;
    private volatile ApiFuture<OperationSnapshot> peekedAttemptResult;
    private volatile ApiFuture<MetadataT> peekedPollResult;
    private final RetryingFuture<OperationSnapshot> pollingFuture;
    private final ApiFuture<ResponseT> resultFuture;

    public OperationFutureImpl(RetryingFuture<OperationSnapshot> retryingFuture, ApiFuture<OperationSnapshot> apiFuture, ApiFunction<OperationSnapshot, ResponseT> apiFunction, ApiFunction<OperationSnapshot, MetadataT> apiFunction2) {
        this.pollingFuture = (RetryingFuture) Preconditions.checkNotNull(retryingFuture);
        this.initialFuture = (ApiFuture) Preconditions.checkNotNull(apiFuture);
        this.resultFuture = ApiFutures.transform(retryingFuture, apiFunction, MoreExecutors.directExecutor());
        this.metadataTransformer = (ApiFunction) Preconditions.checkNotNull(apiFunction2);
    }

    public OperationFutureImpl(RetryingFuture<OperationSnapshot> retryingFuture, ApiFuture<OperationSnapshot> apiFuture, ApiFunction<OperationSnapshot, ResponseT> apiFunction, ApiFunction<OperationSnapshot, MetadataT> apiFunction2, ApiFunction<Exception, ResponseT> apiFunction3) {
        this.pollingFuture = (RetryingFuture) Preconditions.checkNotNull(retryingFuture);
        this.initialFuture = (ApiFuture) Preconditions.checkNotNull(apiFuture);
        this.resultFuture = ApiFutures.catching(ApiFutures.transform(retryingFuture, apiFunction, MoreExecutors.directExecutor()), Exception.class, apiFunction3, MoreExecutors.directExecutor());
        this.metadataTransformer = (ApiFunction) Preconditions.checkNotNull(apiFunction2);
    }

    @Override // com.google.api.core.ApiFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.pollingFuture.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.pollingFuture.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.pollingFuture.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.pollingFuture.isDone();
    }

    @Override // java.util.concurrent.Future
    public ResponseT get() throws InterruptedException, ExecutionException {
        return this.resultFuture.get();
    }

    @Override // java.util.concurrent.Future
    public ResponseT get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.resultFuture.get(j, timeUnit);
    }

    @Override // com.google.api.gax.longrunning.OperationFuture
    public String getName() throws ExecutionException, InterruptedException {
        return this.initialFuture.get().getName();
    }

    @Override // com.google.api.gax.longrunning.OperationFuture
    public ApiFuture<OperationSnapshot> getInitialFuture() {
        return this.initialFuture;
    }

    @Override // com.google.api.gax.longrunning.OperationFuture
    public RetryingFuture<OperationSnapshot> getPollingFuture() {
        return this.pollingFuture;
    }

    @Override // com.google.api.gax.longrunning.OperationFuture
    public ApiFuture<MetadataT> peekMetadata() {
        ApiFuture<OperationSnapshot> peekAttemptResult = this.pollingFuture.peekAttemptResult();
        synchronized (this.lock) {
            if (this.peekedAttemptResult == peekAttemptResult) {
                return this.peekedPollResult;
            }
            this.peekedAttemptResult = peekAttemptResult;
            this.peekedPollResult = ApiFutures.transform(this.peekedAttemptResult, this.metadataTransformer, MoreExecutors.directExecutor());
            return this.peekedPollResult;
        }
    }

    @Override // com.google.api.gax.longrunning.OperationFuture
    public ApiFuture<MetadataT> getMetadata() {
        ApiFuture<OperationSnapshot> attemptResult = this.pollingFuture.getAttemptResult();
        synchronized (this.lock) {
            if (this.gottenAttemptResult == attemptResult) {
                return this.gottenPollResult;
            }
            this.gottenAttemptResult = attemptResult;
            this.gottenPollResult = ApiFutures.transform(this.gottenAttemptResult, this.metadataTransformer, MoreExecutors.directExecutor());
            return this.gottenPollResult;
        }
    }
}
