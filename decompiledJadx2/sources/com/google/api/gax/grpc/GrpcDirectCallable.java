package com.google.api.gax.grpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.ListenableFutureToApiFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;
import io.grpc.MethodDescriptor;
import io.grpc.stub.ClientCalls;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcDirectCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcDirectCallable(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
        this.descriptor = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        Preconditions.checkNotNull(requestt);
        Preconditions.checkNotNull(apiCallContext);
        return new ListenableFutureToApiFuture(ClientCalls.futureUnaryCall(GrpcClientCalls.newCall(this.descriptor, apiCallContext), requestt));
    }

    public String toString() {
        return String.format("direct(%s)", this.descriptor);
    }
}
