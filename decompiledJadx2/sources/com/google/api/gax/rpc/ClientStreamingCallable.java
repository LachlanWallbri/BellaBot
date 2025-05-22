package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public abstract class ClientStreamingCallable<RequestT, ResponseT> {
    public abstract ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver, ApiCallContext apiCallContext);

    public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver) {
        return clientStreamingCall(apiStreamObserver, null);
    }

    public ClientStreamingCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext apiCallContext) {
        return new ClientStreamingCallable<RequestT, ResponseT>() { // from class: com.google.api.gax.rpc.ClientStreamingCallable.1
            @Override // com.google.api.gax.rpc.ClientStreamingCallable
            public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver, ApiCallContext apiCallContext2) {
                return ClientStreamingCallable.this.clientStreamingCall(apiStreamObserver, apiCallContext.merge(apiCallContext2));
            }
        };
    }
}
