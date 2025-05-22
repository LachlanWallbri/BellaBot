package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationFuture;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public abstract class OperationCallable<RequestT, ResponseT, MetadataT> {
    public abstract ApiFuture<Void> cancel(String str, ApiCallContext apiCallContext);

    public abstract OperationFuture<ResponseT, MetadataT> futureCall(RequestT requestt, ApiCallContext apiCallContext);

    public abstract OperationFuture<ResponseT, MetadataT> resumeFutureCall(String str, ApiCallContext apiCallContext);

    public OperationFuture<ResponseT, MetadataT> futureCall(RequestT requestt) {
        return futureCall(requestt, null);
    }

    public ResponseT call(RequestT requestt, ApiCallContext apiCallContext) {
        return (ResponseT) ApiExceptions.callAndTranslateApiException(futureCall(requestt, apiCallContext));
    }

    public ResponseT call(RequestT requestt) {
        return (ResponseT) ApiExceptions.callAndTranslateApiException(futureCall(requestt));
    }

    public OperationFuture<ResponseT, MetadataT> resumeFutureCall(String str) {
        return resumeFutureCall(str, null);
    }

    public ApiFuture<Void> cancel(String str) {
        return cancel(str, null);
    }

    public OperationCallable<RequestT, ResponseT, MetadataT> withDefaultCallContext(final ApiCallContext apiCallContext) {
        return new OperationCallable<RequestT, ResponseT, MetadataT>() { // from class: com.google.api.gax.rpc.OperationCallable.1
            @Override // com.google.api.gax.rpc.OperationCallable
            public OperationFuture<ResponseT, MetadataT> futureCall(RequestT requestt, ApiCallContext apiCallContext2) {
                return OperationCallable.this.futureCall(requestt, apiCallContext.merge(apiCallContext2));
            }

            @Override // com.google.api.gax.rpc.OperationCallable
            public OperationFuture<ResponseT, MetadataT> resumeFutureCall(String str, ApiCallContext apiCallContext2) {
                return OperationCallable.this.resumeFutureCall(str, apiCallContext.merge(apiCallContext2));
            }

            @Override // com.google.api.gax.rpc.OperationCallable
            public ApiFuture<Void> cancel(String str, ApiCallContext apiCallContext2) {
                return OperationCallable.this.cancel(str, apiCallContext.merge(apiCallContext2));
            }
        };
    }
}
