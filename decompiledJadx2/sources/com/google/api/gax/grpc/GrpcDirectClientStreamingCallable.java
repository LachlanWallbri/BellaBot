package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.common.base.Preconditions;
import io.grpc.MethodDescriptor;
import io.grpc.stub.ClientCalls;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcDirectClientStreamingCallable<RequestT, ResponseT> extends ClientStreamingCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcDirectClientStreamingCallable(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
        this.descriptor = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor);
    }

    @Override // com.google.api.gax.rpc.ClientStreamingCallable
    public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver, ApiCallContext apiCallContext) {
        Preconditions.checkNotNull(apiStreamObserver);
        return new StreamObserverDelegate(ClientCalls.asyncClientStreamingCall(GrpcClientCalls.newCall(this.descriptor, apiCallContext), new ApiStreamObserverDelegate(apiStreamObserver)));
    }
}
