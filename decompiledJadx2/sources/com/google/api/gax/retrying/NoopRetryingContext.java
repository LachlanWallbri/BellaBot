package com.google.api.gax.retrying;

import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.tracing.ApiTracer;
import com.google.api.gax.tracing.NoopApiTracer;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class NoopRetryingContext implements RetryingContext {
    @Override // com.google.api.gax.retrying.RetryingContext
    public RetrySettings getRetrySettings() {
        return null;
    }

    @Override // com.google.api.gax.retrying.RetryingContext
    public Set<StatusCode.Code> getRetryableCodes() {
        return null;
    }

    NoopRetryingContext() {
    }

    public static RetryingContext create() {
        return new NoopRetryingContext();
    }

    @Override // com.google.api.gax.retrying.RetryingContext
    @Nonnull
    public ApiTracer getTracer() {
        return NoopApiTracer.getInstance();
    }
}
