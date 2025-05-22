package com.google.api.gax.tracing;

import com.google.api.core.InternalApi;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use by google-cloud-java clients only")
/* loaded from: classes2.dex */
public interface ApiTracer {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface Scope extends AutoCloseable {
        @Override // java.lang.AutoCloseable
        void close();
    }

    void attemptCancelled();

    void attemptFailed(Throwable th, Duration duration);

    void attemptFailedRetriesExhausted(Throwable th);

    void attemptPermanentFailure(Throwable th);

    void attemptStarted(int i);

    void attemptSucceeded();

    void batchRequestSent(long j, long j2);

    void connectionSelected(String str);

    Scope inScope();

    void lroStartFailed(Throwable th);

    void lroStartSucceeded();

    void operationCancelled();

    void operationFailed(Throwable th);

    void operationSucceeded();

    void requestSent();

    void responseReceived();
}
