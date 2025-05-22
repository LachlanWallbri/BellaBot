package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class UnaryCallable<RequestT, ResponseT> {
    public abstract ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext);

    public ApiFuture<ResponseT> futureCall(RequestT requestt) {
        return futureCall(requestt, null);
    }

    public ResponseT call(RequestT requestt, ApiCallContext apiCallContext) {
        return (ResponseT) ApiExceptions.callAndTranslateApiException(futureCall(requestt, apiCallContext));
    }

    public ResponseT call(RequestT requestt) {
        return (ResponseT) ApiExceptions.callAndTranslateApiException(futureCall(requestt));
    }

    public UnaryCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext apiCallContext) {
        return new UnaryCallable<RequestT, ResponseT>() { // from class: com.google.api.gax.rpc.UnaryCallable.1
            @Override // com.google.api.gax.rpc.UnaryCallable
            public ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext2) {
                return UnaryCallable.this.futureCall(requestt, apiCallContext.merge(apiCallContext2));
            }
        };
    }
}
