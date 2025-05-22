package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface StreamResumptionStrategy<RequestT, ResponseT> {
    boolean canResume();

    @Nonnull
    StreamResumptionStrategy<RequestT, ResponseT> createNew();

    @Nullable
    RequestT getResumeRequest(RequestT requestt);

    @Nonnull
    ResponseT processResponse(ResponseT responset);
}
