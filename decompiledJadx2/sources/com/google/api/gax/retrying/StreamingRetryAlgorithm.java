package com.google.api.gax.retrying;

import com.google.api.core.InternalApi;
import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use only")
/* loaded from: classes2.dex */
public final class StreamingRetryAlgorithm<ResponseT> extends RetryAlgorithm<ResponseT> {
    @Deprecated
    public StreamingRetryAlgorithm(ResultRetryAlgorithm<ResponseT> resultRetryAlgorithm, TimedRetryAlgorithm timedRetryAlgorithm) {
        super(resultRetryAlgorithm, timedRetryAlgorithm);
    }

    public StreamingRetryAlgorithm(ResultRetryAlgorithmWithContext<ResponseT> resultRetryAlgorithmWithContext, TimedRetryAlgorithmWithContext timedRetryAlgorithmWithContext) {
        super((ResultRetryAlgorithmWithContext) resultRetryAlgorithmWithContext, timedRetryAlgorithmWithContext);
    }

    @Override // com.google.api.gax.retrying.RetryAlgorithm
    public TimedAttemptSettings createNextAttempt(Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) {
        return createNextAttempt(null, th, responset, timedAttemptSettings);
    }

    @Override // com.google.api.gax.retrying.RetryAlgorithm
    public TimedAttemptSettings createNextAttempt(RetryingContext retryingContext, Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) {
        if (th instanceof ServerStreamingAttemptException) {
            ServerStreamingAttemptException serverStreamingAttemptException = (ServerStreamingAttemptException) th;
            th = th.getCause();
            if (serverStreamingAttemptException.hasSeenResponses()) {
                timedAttemptSettings = createFirstAttempt(retryingContext).toBuilder().setFirstAttemptStartTimeNanos(timedAttemptSettings.getFirstAttemptStartTimeNanos()).setOverallAttemptCount(timedAttemptSettings.getOverallAttemptCount()).build();
            }
        }
        return super.createNextAttempt(retryingContext, th, responset, timedAttemptSettings);
    }

    @Override // com.google.api.gax.retrying.RetryAlgorithm
    public boolean shouldRetry(Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) throws CancellationException {
        return shouldRetry(null, th, responset, timedAttemptSettings);
    }

    @Override // com.google.api.gax.retrying.RetryAlgorithm
    public boolean shouldRetry(RetryingContext retryingContext, Throwable th, ResponseT responset, TimedAttemptSettings timedAttemptSettings) throws CancellationException {
        if (th instanceof ServerStreamingAttemptException) {
            ServerStreamingAttemptException serverStreamingAttemptException = (ServerStreamingAttemptException) th;
            th = th.getCause();
            if (!serverStreamingAttemptException.canResume()) {
                return false;
            }
        }
        return super.shouldRetry(retryingContext, th, responset, timedAttemptSettings);
    }
}
