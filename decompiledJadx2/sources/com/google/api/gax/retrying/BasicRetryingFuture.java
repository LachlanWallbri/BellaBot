package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.tracing.ApiTracer;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class BasicRetryingFuture<ResponseT> extends AbstractFuture<ResponseT> implements RetryingFuture<ResponseT> {
    private static final Logger LOG = Logger.getLogger(BasicRetryingFuture.class.getName());
    private volatile ApiFuture<ResponseT> attemptResult;
    private volatile TimedAttemptSettings attemptSettings;
    private final Callable<ResponseT> callable;
    private volatile ApiFuture<ResponseT> latestCompletedAttemptResult;
    final Object lock = new Object();
    private final RetryAlgorithm<ResponseT> retryAlgorithm;
    private final RetryingContext retryingContext;

    void clearAttemptServiceData() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicRetryingFuture(Callable<ResponseT> callable, RetryAlgorithm<ResponseT> retryAlgorithm, RetryingContext retryingContext) {
        this.callable = (Callable) Preconditions.checkNotNull(callable);
        this.retryAlgorithm = (RetryAlgorithm) Preconditions.checkNotNull(retryAlgorithm);
        this.retryingContext = (RetryingContext) Preconditions.checkNotNull(retryingContext);
        this.attemptSettings = retryAlgorithm.createFirstAttempt(retryingContext);
        super.addListener(new CompletionListener(), MoreExecutors.directExecutor());
    }

    @Override // com.google.api.gax.retrying.RetryingFuture
    public void setAttemptFuture(ApiFuture<ResponseT> apiFuture) {
        try {
            if (isDone()) {
                return;
            }
            handleAttempt(null, apiFuture.get());
        } catch (ExecutionException e) {
            handleAttempt(e.getCause(), null);
        } catch (Throwable th) {
            handleAttempt(th, null);
        }
    }

    @Override // com.google.api.gax.retrying.RetryingFuture
    public Callable<ResponseT> getCallable() {
        return this.callable;
    }

    @Override // com.google.api.gax.retrying.RetryingFuture
    public TimedAttemptSettings getAttemptSettings() {
        TimedAttemptSettings timedAttemptSettings;
        synchronized (this.lock) {
            timedAttemptSettings = this.attemptSettings;
        }
        return timedAttemptSettings;
    }

    @Override // com.google.api.gax.retrying.RetryingFuture
    public ApiFuture<ResponseT> peekAttemptResult() {
        ApiFuture<ResponseT> apiFuture;
        synchronized (this.lock) {
            apiFuture = this.latestCompletedAttemptResult;
        }
        return apiFuture;
    }

    @Override // com.google.api.gax.retrying.RetryingFuture
    public ApiFuture<ResponseT> getAttemptResult() {
        ApiFuture<ResponseT> apiFuture;
        synchronized (this.lock) {
            if (this.attemptResult == null) {
                this.attemptResult = new NonCancellableFuture();
            }
            apiFuture = this.attemptResult;
        }
        return apiFuture;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleAttempt(Throwable th, ResponseT responset) {
        ApiTracer tracer = this.retryingContext.getTracer();
        synchronized (this.lock) {
            try {
                clearAttemptServiceData();
                if (th instanceof CancellationException) {
                    tracer.attemptCancelled();
                    super.cancel(false);
                } else if (th instanceof RejectedExecutionException) {
                    tracer.attemptPermanentFailure(th);
                    super.setException(th);
                }
            } catch (CancellationException e) {
                tracer.attemptFailedRetriesExhausted(e);
                super.cancel(false);
            } catch (Exception e2) {
                tracer.attemptPermanentFailure(e2);
                super.setException(e2);
            }
            if (isDone()) {
                return;
            }
            TimedAttemptSettings createNextAttempt = this.retryAlgorithm.createNextAttempt(this.retryingContext, th, responset, this.attemptSettings);
            if (this.retryAlgorithm.shouldRetry(this.retryingContext, th, responset, createNextAttempt)) {
                if (LOG.isLoggable(Level.FINEST)) {
                    Logger logger = LOG;
                    Level level = Level.FINEST;
                    Object[] objArr = new Object[4];
                    StringBuilder sb = new StringBuilder();
                    sb.append("enclosingMethod: ");
                    sb.append(this.callable.getClass().getEnclosingMethod() != null ? this.callable.getClass().getEnclosingMethod().getName() : "");
                    objArr[0] = sb.toString();
                    objArr[1] = "attemptCount: " + this.attemptSettings.getAttemptCount();
                    objArr[2] = "delay: " + this.attemptSettings.getRetryDelay();
                    objArr[3] = "retriableException: " + th;
                    logger.log(level, "Retrying with:\n{0}\n{1}\n{2}\n{3}", objArr);
                }
                tracer.attemptFailed(th, createNextAttempt.getRandomizedRetryDelay());
                this.attemptSettings = createNextAttempt;
                setAttemptResult(th, responset, true);
            } else if (th != null) {
                if (this.retryAlgorithm.shouldRetryBasedOnResult(this.retryingContext, th, responset)) {
                    tracer.attemptFailedRetriesExhausted(th);
                } else {
                    tracer.attemptPermanentFailure(th);
                }
                super.setException(th);
            } else {
                tracer.attemptSucceeded();
                super.set(responset);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAttemptResult(Throwable th, ResponseT responset, boolean z) {
        ApiFuture<ResponseT> apiFuture = this.attemptResult;
        try {
            ApiFuture<ResponseT> apiFuture2 = null;
            if (th instanceof CancellationException) {
                NonCancellableFuture nonCancellableFuture = new NonCancellableFuture();
                nonCancellableFuture.cancelPrivately();
                this.latestCompletedAttemptResult = nonCancellableFuture;
                if (!z) {
                    apiFuture2 = this.latestCompletedAttemptResult;
                }
                this.attemptResult = apiFuture2;
                if (apiFuture instanceof NonCancellableFuture) {
                    ((NonCancellableFuture) apiFuture).cancelPrivately();
                    return;
                }
                return;
            }
            if (th != null) {
                this.latestCompletedAttemptResult = ApiFutures.immediateFailedFuture(th);
                if (!z) {
                    apiFuture2 = this.latestCompletedAttemptResult;
                }
                this.attemptResult = apiFuture2;
                if (apiFuture instanceof NonCancellableFuture) {
                    ((NonCancellableFuture) apiFuture).setExceptionPrivately(th);
                    return;
                }
                return;
            }
            this.latestCompletedAttemptResult = ApiFutures.immediateFuture(responset);
            if (!z) {
                apiFuture2 = this.latestCompletedAttemptResult;
            }
            this.attemptResult = apiFuture2;
            if (apiFuture instanceof NonCancellableFuture) {
                ((NonCancellableFuture) apiFuture).setPrivately(responset);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class CompletionListener implements Runnable {
        private CompletionListener() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (BasicRetryingFuture.this.lock) {
                try {
                    BasicRetryingFuture.this.clearAttemptServiceData();
                    BasicRetryingFuture.this.setAttemptResult(null, BasicRetryingFuture.this.get(), false);
                } catch (ExecutionException e) {
                    BasicRetryingFuture.this.setAttemptResult(e.getCause(), null, false);
                } catch (Throwable th) {
                    BasicRetryingFuture.this.setAttemptResult(th, null, false);
                }
            }
        }
    }
}
