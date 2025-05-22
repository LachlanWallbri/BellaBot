package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class OperationCheckingCallable<RequestT> extends UnaryCallable<RequestT, OperationSnapshot> {
    private final ApiFuture<OperationSnapshot> initialFuture;
    private final LongRunningClient longRunningClient;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OperationCheckingCallable(LongRunningClient longRunningClient, ApiFuture<OperationSnapshot> apiFuture) {
        this.longRunningClient = (LongRunningClient) Preconditions.checkNotNull(longRunningClient);
        this.initialFuture = (ApiFuture) Preconditions.checkNotNull(apiFuture);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<OperationSnapshot> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        try {
            if (this.initialFuture.isDone() && !this.initialFuture.isCancelled()) {
                OperationSnapshot operationSnapshot = this.initialFuture.get();
                if (operationSnapshot.isDone()) {
                    return this.initialFuture;
                }
                return this.longRunningClient.getOperationCallable().futureCall(operationSnapshot.getName(), apiCallContext);
            }
            return this.initialFuture;
        } catch (InterruptedException e) {
            return ApiFutures.immediateFailedFuture(e);
        } catch (ExecutionException e2) {
            return ApiFutures.immediateFailedFuture(e2.getCause());
        }
    }
}
