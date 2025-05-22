package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import java.util.concurrent.Callable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface RetryingExecutor<ResponseT> {
    RetryingFuture<ResponseT> createFuture(Callable<ResponseT> callable);

    ApiFuture<ResponseT> submit(RetryingFuture<ResponseT> retryingFuture);
}
