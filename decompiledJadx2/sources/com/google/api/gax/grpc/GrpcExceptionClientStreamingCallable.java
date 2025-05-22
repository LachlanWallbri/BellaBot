package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.api.gax.rpc.StatusCode;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class GrpcExceptionClientStreamingCallable<RequestT, ResponseT> extends ClientStreamingCallable<RequestT, ResponseT> {
    private final GrpcApiExceptionFactory exceptionFactory;
    private final ClientStreamingCallable<RequestT, ResponseT> innerCallable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcExceptionClientStreamingCallable(ClientStreamingCallable<RequestT, ResponseT> clientStreamingCallable, Set<StatusCode.Code> set) {
        this.innerCallable = clientStreamingCallable;
        this.exceptionFactory = new GrpcApiExceptionFactory(set);
    }

    @Override // com.google.api.gax.rpc.ClientStreamingCallable
    public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver, ApiCallContext apiCallContext) {
        return this.innerCallable.clientStreamingCall(new GrpcExceptionTranslatingStreamObserver(apiStreamObserver, this.exceptionFactory), apiCallContext);
    }
}
