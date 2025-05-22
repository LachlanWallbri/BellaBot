package com.google.api.gax.retrying;

import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface TimedRetryAlgorithm {
    TimedAttemptSettings createFirstAttempt();

    TimedAttemptSettings createNextAttempt(TimedAttemptSettings timedAttemptSettings);

    boolean shouldRetry(TimedAttemptSettings timedAttemptSettings) throws CancellationException;
}
