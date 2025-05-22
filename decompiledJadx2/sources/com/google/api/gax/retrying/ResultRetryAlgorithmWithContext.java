package com.google.api.gax.retrying;

import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ResultRetryAlgorithmWithContext<ResponseT> extends ResultRetryAlgorithm<ResponseT> {
    TimedAttemptSettings createNextAttempt(RetryingContext retryingContext, Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings);

    boolean shouldRetry(RetryingContext retryingContext, Throwable th, ResponseT responset) throws CancellationException;
}
