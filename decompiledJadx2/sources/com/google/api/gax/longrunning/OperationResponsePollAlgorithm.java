package com.google.api.gax.longrunning;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.TimedAttemptSettings;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class OperationResponsePollAlgorithm implements ResultRetryAlgorithm<OperationSnapshot> {
    @Override // com.google.api.gax.retrying.ResultRetryAlgorithm
    public TimedAttemptSettings createNextAttempt(Throwable th, OperationSnapshot operationSnapshot, TimedAttemptSettings timedAttemptSettings) {
        return null;
    }

    @Override // com.google.api.gax.retrying.ResultRetryAlgorithm
    public boolean shouldRetry(Throwable th, OperationSnapshot operationSnapshot) {
        return (th != null || operationSnapshot == null || operationSnapshot.isDone()) ? false : true;
    }
}
