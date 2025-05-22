package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class SpoolingCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, List<ResponseT>> {
    private final ServerStreamingCallable<RequestT, ResponseT> streamingCallable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpoolingCallable(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable) {
        this.streamingCallable = serverStreamingCallable;
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<List<ResponseT>> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        SpoolingResponseObserver spoolingResponseObserver = new SpoolingResponseObserver();
        this.streamingCallable.call(requestt, spoolingResponseObserver, apiCallContext);
        return spoolingResponseObserver.getFuture();
    }
}
