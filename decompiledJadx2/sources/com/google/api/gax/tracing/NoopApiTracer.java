package com.google.api.gax.tracing;

import com.google.api.core.InternalApi;
import com.google.api.gax.tracing.ApiTracer;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public final class NoopApiTracer implements ApiTracer {
    private static final ApiTracer INSTANCE = new NoopApiTracer();
    private static final ApiTracer.Scope NOOP_SCOPE = new ApiTracer.Scope() { // from class: com.google.api.gax.tracing.NoopApiTracer.1
        @Override // com.google.api.gax.tracing.ApiTracer.Scope, java.lang.AutoCloseable
        public void close() {
        }
    };

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptCancelled() {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptFailed(Throwable th, Duration duration) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptFailedRetriesExhausted(Throwable th) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptPermanentFailure(Throwable th) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptStarted(int i) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptSucceeded() {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void batchRequestSent(long j, long j2) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void connectionSelected(String str) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void lroStartFailed(Throwable th) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void lroStartSucceeded() {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void operationCancelled() {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void operationFailed(Throwable th) {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void operationSucceeded() {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void requestSent() {
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void responseReceived() {
    }

    private NoopApiTracer() {
    }

    public static ApiTracer getInstance() {
        return INSTANCE;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public ApiTracer.Scope inScope() {
        return NOOP_SCOPE;
    }
}
