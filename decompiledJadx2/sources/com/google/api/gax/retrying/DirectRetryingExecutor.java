package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.common.base.Preconditions;
import java.io.InterruptedIOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.concurrent.Callable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class DirectRetryingExecutor<ResponseT> implements RetryingExecutorWithContext<ResponseT> {
    private final RetryAlgorithm<ResponseT> retryAlgorithm;

    public DirectRetryingExecutor(RetryAlgorithm<ResponseT> retryAlgorithm) {
        this.retryAlgorithm = (RetryAlgorithm) Preconditions.checkNotNull(retryAlgorithm);
    }

    @Override // com.google.api.gax.retrying.RetryingExecutor
    public RetryingFuture<ResponseT> createFuture(Callable<ResponseT> callable) {
        return createFuture(callable, NoopRetryingContext.create());
    }

    @Override // com.google.api.gax.retrying.RetryingExecutorWithContext
    @BetaApi("The surface for passing per operation state is not yet stable")
    public RetryingFuture<ResponseT> createFuture(Callable<ResponseT> callable, RetryingContext retryingContext) {
        return new BasicRetryingFuture(callable, this.retryAlgorithm, retryingContext);
    }

    @Override // com.google.api.gax.retrying.RetryingExecutor
    public ApiFuture<ResponseT> submit(RetryingFuture<ResponseT> retryingFuture) {
        while (!retryingFuture.isDone()) {
            try {
                sleep(retryingFuture.getAttemptSettings().getRandomizedRetryDelay());
                retryingFuture.setAttemptFuture(ApiFutures.immediateFuture(retryingFuture.getCallable().call()));
            } catch (InterruptedIOException e) {
                e = e;
                Thread.currentThread().interrupt();
                retryingFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e));
            } catch (InterruptedException e2) {
                e = e2;
                Thread.currentThread().interrupt();
                retryingFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e));
            } catch (ClosedByInterruptException e3) {
                e = e3;
                Thread.currentThread().interrupt();
                retryingFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e));
            } catch (Exception e4) {
                retryingFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e4));
            }
        }
        return retryingFuture;
    }

    protected void sleep(Duration duration) throws InterruptedException {
        if (Duration.ZERO.compareTo(duration) < 0) {
            Thread.sleep(duration.toMillis());
        }
    }
}
