package com.google.api.gax.tracing;

import com.google.api.core.ApiFutureCallback;
import com.google.common.base.Preconditions;
import java.util.concurrent.CancellationException;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class TraceFinisher<T> implements ApiFutureCallback<T> {
    private final ApiTracer tracer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TraceFinisher(@Nonnull ApiTracer apiTracer) {
        this.tracer = (ApiTracer) Preconditions.checkNotNull(apiTracer, "tracer can't be null");
    }

    @Override // com.google.api.core.ApiFutureCallback
    public void onFailure(Throwable th) {
        if (th instanceof CancellationException) {
            this.tracer.operationCancelled();
        } else {
            this.tracer.operationFailed(th);
        }
    }

    @Override // com.google.api.core.ApiFutureCallback
    public void onSuccess(T t) {
        this.tracer.operationSucceeded();
    }
}
