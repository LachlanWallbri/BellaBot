package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class FirstElementCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> streamingCallable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirstElementCallable(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable) {
        this.streamingCallable = serverStreamingCallable;
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        FirstElementResponseObserver firstElementResponseObserver = new FirstElementResponseObserver();
        this.streamingCallable.call(requestt, firstElementResponseObserver, apiCallContext);
        return firstElementResponseObserver.getFuture();
    }
}
