package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.common.base.Preconditions;
import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class RetryAlgorithm<ResponseT> {
    private final ResultRetryAlgorithm<ResponseT> resultAlgorithm;
    private final ResultRetryAlgorithmWithContext<ResponseT> resultAlgorithmWithContext;
    private final TimedRetryAlgorithm timedAlgorithm;
    private final TimedRetryAlgorithmWithContext timedAlgorithmWithContext;

    @Deprecated
    public RetryAlgorithm(ResultRetryAlgorithm<ResponseT> resultRetryAlgorithm, TimedRetryAlgorithm timedRetryAlgorithm) {
        this.resultAlgorithm = (ResultRetryAlgorithm) Preconditions.checkNotNull(resultRetryAlgorithm);
        this.timedAlgorithm = (TimedRetryAlgorithm) Preconditions.checkNotNull(timedRetryAlgorithm);
        this.resultAlgorithmWithContext = null;
        this.timedAlgorithmWithContext = null;
    }

    public RetryAlgorithm(ResultRetryAlgorithmWithContext<ResponseT> resultRetryAlgorithmWithContext, TimedRetryAlgorithmWithContext timedRetryAlgorithmWithContext) {
        this.resultAlgorithm = null;
        this.timedAlgorithm = null;
        this.resultAlgorithmWithContext = (ResultRetryAlgorithmWithContext) Preconditions.checkNotNull(resultRetryAlgorithmWithContext);
        this.timedAlgorithmWithContext = (TimedRetryAlgorithmWithContext) Preconditions.checkNotNull(timedRetryAlgorithmWithContext);
    }

    @Deprecated
    public TimedAttemptSettings createFirstAttempt() {
        return createFirstAttempt(null);
    }

    public TimedAttemptSettings createFirstAttempt(RetryingContext retryingContext) {
        TimedRetryAlgorithmWithContext timedRetryAlgorithmWithContext = this.timedAlgorithmWithContext;
        if (timedRetryAlgorithmWithContext != null && retryingContext != null) {
            return timedRetryAlgorithmWithContext.createFirstAttempt(retryingContext);
        }
        return getTimedAlgorithm().createFirstAttempt();
    }

    @Deprecated
    public TimedAttemptSettings createNextAttempt(Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) {
        return createNextAttempt(null, th, responset, timedAttemptSettings);
    }

    public TimedAttemptSettings createNextAttempt(RetryingContext retryingContext, Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) {
        if (!shouldRetryBasedOnResult(retryingContext, th, responset)) {
            return null;
        }
        TimedAttemptSettings createNextAttemptBasedOnResult = createNextAttemptBasedOnResult(retryingContext, th, responset, timedAttemptSettings);
        return createNextAttemptBasedOnResult == null ? createNextAttemptBasedOnTiming(retryingContext, timedAttemptSettings) : createNextAttemptBasedOnResult;
    }

    private TimedAttemptSettings createNextAttemptBasedOnResult(RetryingContext retryingContext, Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) {
        ResultRetryAlgorithmWithContext<ResponseT> resultRetryAlgorithmWithContext = this.resultAlgorithmWithContext;
        if (resultRetryAlgorithmWithContext != null && retryingContext != null) {
            return resultRetryAlgorithmWithContext.createNextAttempt(retryingContext, th, responset, timedAttemptSettings);
        }
        return getResultAlgorithm().createNextAttempt(th, responset, timedAttemptSettings);
    }

    private TimedAttemptSettings createNextAttemptBasedOnTiming(RetryingContext retryingContext, TimedAttemptSettings timedAttemptSettings) {
        TimedRetryAlgorithmWithContext timedRetryAlgorithmWithContext = this.timedAlgorithmWithContext;
        if (timedRetryAlgorithmWithContext != null && retryingContext != null) {
            return timedRetryAlgorithmWithContext.createNextAttempt(retryingContext, timedAttemptSettings);
        }
        return getTimedAlgorithm().createNextAttempt(timedAttemptSettings);
    }

    @Deprecated
    public boolean shouldRetry(Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) throws CancellationException {
        return shouldRetry(null, th, responset, timedAttemptSettings);
    }

    public boolean shouldRetry(RetryingContext retryingContext, Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) throws CancellationException {
        return shouldRetryBasedOnResult(retryingContext, th, responset) && shouldRetryBasedOnTiming(retryingContext, timedAttemptSettings);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldRetryBasedOnResult(RetryingContext retryingContext, Throwable th, ResponseT responset) {
        ResultRetryAlgorithmWithContext<ResponseT> resultRetryAlgorithmWithContext = this.resultAlgorithmWithContext;
        if (resultRetryAlgorithmWithContext != null && retryingContext != null) {
            return resultRetryAlgorithmWithContext.shouldRetry(retryingContext, th, responset);
        }
        return getResultAlgorithm().shouldRetry(th, responset);
    }

    private boolean shouldRetryBasedOnTiming(RetryingContext retryingContext, TimedAttemptSettings timedAttemptSettings) {
        if (timedAttemptSettings == null) {
            return false;
        }
        TimedRetryAlgorithmWithContext timedRetryAlgorithmWithContext = this.timedAlgorithmWithContext;
        if (timedRetryAlgorithmWithContext != null && retryingContext != null) {
            return timedRetryAlgorithmWithContext.shouldRetry(retryingContext, timedAttemptSettings);
        }
        return getTimedAlgorithm().shouldRetry(timedAttemptSettings);
    }

    @BetaApi("Surface for inspecting the a RetryAlgorithm is not yet stable")
    public ResultRetryAlgorithm<ResponseT> getResultAlgorithm() {
        ResultRetryAlgorithmWithContext<ResponseT> resultRetryAlgorithmWithContext = this.resultAlgorithmWithContext;
        return resultRetryAlgorithmWithContext != null ? resultRetryAlgorithmWithContext : this.resultAlgorithm;
    }

    @BetaApi("Surface for inspecting the a RetryAlgorithm is not yet stable")
    public TimedRetryAlgorithm getTimedAlgorithm() {
        TimedRetryAlgorithmWithContext timedRetryAlgorithmWithContext = this.timedAlgorithmWithContext;
        return timedRetryAlgorithmWithContext != null ? timedRetryAlgorithmWithContext : this.timedAlgorithm;
    }
}
