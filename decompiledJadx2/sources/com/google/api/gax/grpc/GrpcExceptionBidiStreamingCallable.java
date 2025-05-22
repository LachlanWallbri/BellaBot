package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ClientStreamReadyObserver;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StatusCode;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class GrpcExceptionBidiStreamingCallable<RequestT, ResponseT> extends BidiStreamingCallable<RequestT, ResponseT> {
    private final GrpcApiExceptionFactory exceptionFactory;
    private final BidiStreamingCallable<RequestT, ResponseT> innerCallable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcExceptionBidiStreamingCallable(BidiStreamingCallable<RequestT, ResponseT> bidiStreamingCallable, Set<StatusCode.Code> set) {
        this.innerCallable = bidiStreamingCallable;
        this.exceptionFactory = new GrpcApiExceptionFactory(set);
    }

    @Override // com.google.api.gax.rpc.BidiStreamingCallable
    public ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, ClientStreamReadyObserver<RequestT> clientStreamReadyObserver, ApiCallContext apiCallContext) {
        return this.innerCallable.internalCall(new ExceptionResponseObserver(responseObserver, this.exceptionFactory), clientStreamReadyObserver, apiCallContext);
    }
}
