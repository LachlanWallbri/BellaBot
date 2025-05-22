package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class SimpleStreamResumptionStrategy<RequestT, ResponseT> implements StreamResumptionStrategy<RequestT, ResponseT> {
    private boolean seenFirstResponse;

    @Override // com.google.api.gax.retrying.StreamResumptionStrategy
    public StreamResumptionStrategy<RequestT, ResponseT> createNew() {
        return new SimpleStreamResumptionStrategy();
    }

    @Override // com.google.api.gax.retrying.StreamResumptionStrategy
    public ResponseT processResponse(ResponseT responset) {
        this.seenFirstResponse = true;
        return responset;
    }

    @Override // com.google.api.gax.retrying.StreamResumptionStrategy
    public RequestT getResumeRequest(RequestT requestt) {
        Preconditions.checkState(!this.seenFirstResponse, "Tried to resume an unresumeable stream.");
        return requestt;
    }

    @Override // com.google.api.gax.retrying.StreamResumptionStrategy
    public boolean canResume() {
        return !this.seenFirstResponse;
    }
}
