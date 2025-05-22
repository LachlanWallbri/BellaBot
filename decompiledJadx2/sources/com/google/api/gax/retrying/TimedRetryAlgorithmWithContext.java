package com.google.api.gax.retrying;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface TimedRetryAlgorithmWithContext extends TimedRetryAlgorithm {
    TimedAttemptSettings createFirstAttempt(RetryingContext retryingContext);

    TimedAttemptSettings createNextAttempt(RetryingContext retryingContext, TimedAttemptSettings timedAttemptSettings);

    boolean shouldRetry(RetryingContext retryingContext, TimedAttemptSettings timedAttemptSettings);
}
