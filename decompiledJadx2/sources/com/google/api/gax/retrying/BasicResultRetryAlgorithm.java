package com.google.api.gax.retrying;

import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class BasicResultRetryAlgorithm<ResponseT> implements ResultRetryAlgorithmWithContext<ResponseT> {
    @Override // com.google.api.gax.retrying.ResultRetryAlgorithm
    public TimedAttemptSettings createNextAttempt(Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) {
        return null;
    }

    @Override // com.google.api.gax.retrying.ResultRetryAlgorithm
    public boolean shouldRetry(Throwable th, ResponseT responset) {
        return th != null;
    }

    @Override // com.google.api.gax.retrying.ResultRetryAlgorithmWithContext
    public TimedAttemptSettings createNextAttempt(RetryingContext retryingContext, Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) {
        return createNextAttempt(th, responset, timedAttemptSettings);
    }

    @Override // com.google.api.gax.retrying.ResultRetryAlgorithmWithContext
    public boolean shouldRetry(RetryingContext retryingContext, Throwable th, ResponseT responset) throws CancellationException {
        return shouldRetry(th, responset);
    }
}
