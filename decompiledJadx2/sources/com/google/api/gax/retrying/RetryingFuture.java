package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import java.util.concurrent.Callable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface RetryingFuture<ResponseT> extends ApiFuture<ResponseT> {
    ApiFuture<ResponseT> getAttemptResult();

    TimedAttemptSettings getAttemptSettings();

    Callable<ResponseT> getCallable();

    ApiFuture<ResponseT> peekAttemptResult();

    void setAttemptFuture(ApiFuture<ResponseT> apiFuture);
}
