package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.tracing.ApiTracer;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for passing per operation state is not yet stable")
/* loaded from: classes2.dex */
public interface RetryingContext {
    @Nullable
    RetrySettings getRetrySettings();

    @Nullable
    Set<StatusCode.Code> getRetryableCodes();

    @Nonnull
    ApiTracer getTracer();
}
