package com.google.api.gax.retrying;

import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ResultRetryAlgorithm<ResponseT> {
    TimedAttemptSettings createNextAttempt(Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings);

    boolean shouldRetry(Throwable th, ResponseT responset) throws CancellationException;
}
