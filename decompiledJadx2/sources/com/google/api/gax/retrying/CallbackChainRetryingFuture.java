package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class CallbackChainRetryingFuture<ResponseT> extends BasicRetryingFuture<ResponseT> {
    private volatile CallbackChainRetryingFuture<ResponseT>.AttemptCompletionListener attemptFutureCompletionListener;
    private final ScheduledRetryingExecutor<ResponseT> retryingExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallbackChainRetryingFuture(Callable<ResponseT> callable, RetryAlgorithm<ResponseT> retryAlgorithm, ScheduledRetryingExecutor<ResponseT> scheduledRetryingExecutor, RetryingContext retryingContext) {
        super(callable, retryAlgorithm, retryingContext);
        this.retryingExecutor = (ScheduledRetryingExecutor) Preconditions.checkNotNull(scheduledRetryingExecutor);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        synchronized (this.lock) {
            if (this.attemptFutureCompletionListener == null) {
                return super.cancel(z);
            }
            ((AttemptCompletionListener) this.attemptFutureCompletionListener).attemptFuture.cancel(z);
            return isCancelled();
        }
    }

    @Override // com.google.api.gax.retrying.BasicRetryingFuture, com.google.api.gax.retrying.RetryingFuture
    public void setAttemptFuture(ApiFuture<ResponseT> apiFuture) {
        if (isDone()) {
            return;
        }
        synchronized (this.lock) {
            if (isDone()) {
                return;
            }
            this.attemptFutureCompletionListener = new AttemptCompletionListener(apiFuture);
            apiFuture.addListener(this.attemptFutureCompletionListener, MoreExecutors.directExecutor());
        }
    }

    @Override // com.google.api.gax.retrying.BasicRetryingFuture
    void clearAttemptServiceData() {
        synchronized (this.lock) {
            this.attemptFutureCompletionListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public class AttemptCompletionListener implements Runnable {
        private final Future<ResponseT> attemptFuture;

        AttemptCompletionListener(Future<ResponseT> future) {
            this.attemptFuture = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                handle(null, this.attemptFuture.get());
            } catch (ExecutionException e) {
                handle(e.getCause(), null);
            } catch (Throwable th) {
                handle(th, null);
            }
        }

        private void handle(Throwable th, ResponseT responset) {
            if (this != CallbackChainRetryingFuture.this.attemptFutureCompletionListener || CallbackChainRetryingFuture.this.isDone()) {
                return;
            }
            synchronized (CallbackChainRetryingFuture.this.lock) {
                if (this == CallbackChainRetryingFuture.this.attemptFutureCompletionListener && !CallbackChainRetryingFuture.this.isDone()) {
                    CallbackChainRetryingFuture.this.handleAttempt(th, responset);
                    if (!CallbackChainRetryingFuture.this.isDone()) {
                        CallbackChainRetryingFuture.this.setAttemptFuture(CallbackChainRetryingFuture.this.retryingExecutor.submit(CallbackChainRetryingFuture.this));
                    }
                }
            }
        }
    }
}
