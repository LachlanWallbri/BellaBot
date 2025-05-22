package com.google.api.gax.rpc;

import com.google.api.gax.retrying.BasicResultRetryAlgorithm;
import com.google.api.gax.retrying.RetryingContext;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class ApiResultRetryAlgorithm<ResponseT> extends BasicResultRetryAlgorithm<ResponseT> {
    @Override // com.google.api.gax.retrying.BasicResultRetryAlgorithm, com.google.api.gax.retrying.ResultRetryAlgorithm
    public boolean shouldRetry(Throwable th, ResponseT responset) {
        return (th instanceof ApiException) && ((ApiException) th).isRetryable();
    }

    @Override // com.google.api.gax.retrying.BasicResultRetryAlgorithm, com.google.api.gax.retrying.ResultRetryAlgorithmWithContext
    public boolean shouldRetry(RetryingContext retryingContext, Throwable th, ResponseT responset) {
        if (retryingContext.getRetryableCodes() != null) {
            return (th instanceof ApiException) && retryingContext.getRetryableCodes().contains(((ApiException) th).getStatusCode().getCode());
        }
        return shouldRetry(th, responset);
    }
}
