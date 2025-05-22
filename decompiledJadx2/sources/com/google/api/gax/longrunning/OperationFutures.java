package com.google.api.gax.longrunning;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class OperationFutures {
    private OperationFutures() {
    }

    public static final <ResponseT, MetadataT> OperationFuture<ResponseT, MetadataT> immediateOperationFuture(final OperationSnapshot operationSnapshot) {
        Preconditions.checkArgument(operationSnapshot.isDone(), "given snapshot must already be completed");
        final ApiFuture immediateFuture = ApiFutures.immediateFuture(operationSnapshot.getMetadata());
        final ApiFuture immediateFuture2 = ApiFutures.immediateFuture(operationSnapshot);
        return new OperationFuture<ResponseT, MetadataT>() { // from class: com.google.api.gax.longrunning.OperationFutures.1
            @Override // java.util.concurrent.Future
            public boolean cancel(boolean z) {
                return false;
            }

            @Override // java.util.concurrent.Future
            public boolean isCancelled() {
                return false;
            }

            @Override // java.util.concurrent.Future
            public boolean isDone() {
                return true;
            }

            @Override // com.google.api.gax.longrunning.OperationFuture
            public String getName() {
                return OperationSnapshot.this.getName();
            }

            @Override // com.google.api.gax.longrunning.OperationFuture
            public ApiFuture<MetadataT> getMetadata() {
                return immediateFuture;
            }

            @Override // com.google.api.gax.longrunning.OperationFuture
            public ApiFuture<MetadataT> peekMetadata() {
                return immediateFuture;
            }

            @Override // com.google.api.gax.longrunning.OperationFuture
            public ApiFuture<OperationSnapshot> getInitialFuture() {
                return immediateFuture2;
            }

            @Override // com.google.api.gax.longrunning.OperationFuture
            public RetryingFuture<OperationSnapshot> getPollingFuture() {
                throw new UnsupportedOperationException("Not implemented: getPollingFuture().");
            }

            @Override // com.google.api.core.ApiFuture
            public void addListener(Runnable runnable, Executor executor) {
                immediateFuture2.addListener(runnable, executor);
            }

            @Override // java.util.concurrent.Future
            public ResponseT get(long j, TimeUnit timeUnit) throws ExecutionException {
                return get();
            }

            @Override // java.util.concurrent.Future
            public ResponseT get() throws ExecutionException {
                if (OperationSnapshot.this.getErrorCode().getCode().equals(StatusCode.Code.OK)) {
                    return (ResponseT) OperationSnapshot.this.getResponse();
                }
                throw new ExecutionException(new ApiException(null, OperationSnapshot.this.getErrorCode(), false));
            }
        };
    }
}
