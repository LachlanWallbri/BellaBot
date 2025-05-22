package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StatusCode;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcExceptionServerStreamingCallable<RequestT, ResponseT> extends ServerStreamingCallable<RequestT, ResponseT> {
    private final GrpcApiExceptionFactory exceptionFactory;
    private final ServerStreamingCallable<RequestT, ResponseT> inner;

    public GrpcExceptionServerStreamingCallable(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, Set<StatusCode.Code> set) {
        this.inner = serverStreamingCallable;
        this.exceptionFactory = new GrpcApiExceptionFactory(set);
    }

    @Override // com.google.api.gax.rpc.ServerStreamingCallable
    public void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext) {
        this.inner.call(requestt, new ExceptionResponseObserver(responseObserver, this.exceptionFactory), apiCallContext);
    }
}
