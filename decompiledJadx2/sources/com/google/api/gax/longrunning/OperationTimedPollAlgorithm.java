package com.google.api.gax.longrunning;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.core.NanoClock;
import com.google.api.gax.retrying.ExponentialRetryAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedAttemptSettings;
import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class OperationTimedPollAlgorithm extends ExponentialRetryAlgorithm {
    public static OperationTimedPollAlgorithm create(RetrySettings retrySettings) {
        return new OperationTimedPollAlgorithm(retrySettings, NanoClock.getDefaultClock());
    }

    public static OperationTimedPollAlgorithm create(RetrySettings retrySettings, ApiClock apiClock) {
        return new OperationTimedPollAlgorithm(retrySettings, apiClock);
    }

    private OperationTimedPollAlgorithm(RetrySettings retrySettings, ApiClock apiClock) {
        super(retrySettings, apiClock);
    }

    @Override // com.google.api.gax.retrying.ExponentialRetryAlgorithm, com.google.api.gax.retrying.TimedRetryAlgorithm
    public boolean shouldRetry(TimedAttemptSettings timedAttemptSettings) throws CancellationException {
        if (super.shouldRetry(timedAttemptSettings)) {
            return true;
        }
        throw new CancellationException();
    }
}
