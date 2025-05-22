package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ClientStreamReadyObserver;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.common.base.Preconditions;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcDirectBidiStreamingCallable<RequestT, ResponseT> extends BidiStreamingCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcDirectBidiStreamingCallable(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
        this.descriptor = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor);
    }

    @Override // com.google.api.gax.rpc.BidiStreamingCallable
    public ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, final ClientStreamReadyObserver<RequestT> clientStreamReadyObserver, ApiCallContext apiCallContext) {
        Preconditions.checkNotNull(responseObserver);
        final ClientCall newCall = GrpcClientCalls.newCall(this.descriptor, apiCallContext);
        final ClientStream<RequestT> clientStream = new ClientStream<RequestT>() { // from class: com.google.api.gax.grpc.GrpcDirectBidiStreamingCallable.1
            @Override // com.google.api.gax.rpc.ClientStream
            public void send(RequestT requestt) {
                newCall.sendMessage(requestt);
            }

            @Override // com.google.api.gax.rpc.ClientStream
            public void closeSendWithError(Throwable th) {
                newCall.cancel(null, th);
            }

            @Override // com.google.api.gax.rpc.ClientStream
            public void closeSend() {
                newCall.halfClose();
            }

            @Override // com.google.api.gax.rpc.ClientStream
            public boolean isSendReady() {
                return newCall.isReady();
            }
        };
        new GrpcDirectStreamController(newCall, responseObserver, new Runnable() { // from class: com.google.api.gax.grpc.GrpcDirectBidiStreamingCallable.2
            @Override // java.lang.Runnable
            public void run() {
                clientStreamReadyObserver.onReady(clientStream);
            }
        }).startBidi();
        return clientStream;
    }
}
