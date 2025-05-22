package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.common.base.Preconditions;
import io.grpc.MethodDescriptor;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcDirectServerStreamingCallable<RequestT, ResponseT> extends ServerStreamingCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcDirectServerStreamingCallable(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
        this.descriptor = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor);
    }

    @Override // com.google.api.gax.rpc.ServerStreamingCallable
    public void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext) {
        Preconditions.checkNotNull(requestt);
        Preconditions.checkNotNull(responseObserver);
        new GrpcDirectStreamController(GrpcClientCalls.newCall(this.descriptor, apiCallContext), responseObserver).start(requestt);
    }
}
