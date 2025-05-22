package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
@BetaApi("The surface for per invocation state is unstable and will probably change in the future")
/* loaded from: classes2.dex */
public interface RetryingExecutorWithContext<ResponseT> extends RetryingExecutor<ResponseT> {
    RetryingFuture<ResponseT> createFuture(@Nonnull Callable<ResponseT> callable, @Nonnull RetryingContext retryingContext);
}
