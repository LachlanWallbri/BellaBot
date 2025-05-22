package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class BatchedRequestIssuer<ResponseT> {
    private final BatchedFuture<ResponseT> batchedFuture;
    private boolean hasResponse;
    private final long messageCount;
    private ResponseT responseToSend = null;
    private Throwable throwableToSend = null;

    public BatchedRequestIssuer(BatchedFuture<ResponseT> batchedFuture, long j) {
        this.batchedFuture = batchedFuture;
        this.messageCount = j;
    }

    public long getMessageCount() {
        return this.messageCount;
    }

    public void setResponse(ResponseT responset) {
        Preconditions.checkState(this.throwableToSend == null, "Cannot set both exception and response");
        this.hasResponse = true;
        this.responseToSend = responset;
    }

    public void setException(Throwable th) {
        Preconditions.checkState(!this.hasResponse, "Cannot set both exception and response");
        this.throwableToSend = th;
    }

    public void sendResult() {
        if (this.hasResponse) {
            this.batchedFuture.set(this.responseToSend);
            return;
        }
        Throwable th = this.throwableToSend;
        if (th != null) {
            this.batchedFuture.setException(th);
            return;
        }
        throw new IllegalStateException("Neither response nor exception were set in BatchedRequestIssuer");
    }
}
