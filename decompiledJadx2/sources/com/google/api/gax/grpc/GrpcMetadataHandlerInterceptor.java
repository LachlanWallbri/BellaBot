package com.google.api.gax.grpc;

import com.google.api.core.InternalApi;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
class GrpcMetadataHandlerInterceptor implements ClientInterceptor {
    @Override // io.grpc.ClientInterceptor
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        ClientCall<ReqT, RespT> newCall = channel.newCall(methodDescriptor, callOptions);
        final ResponseMetadataHandler metadataHandlerOption = CallOptionsUtil.getMetadataHandlerOption(callOptions);
        return metadataHandlerOption == null ? newCall : new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(newCall) { // from class: com.google.api.gax.grpc.GrpcMetadataHandlerInterceptor.1
            @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
            public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(listener) { // from class: com.google.api.gax.grpc.GrpcMetadataHandlerInterceptor.1.1
                    @Override // io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener, io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
                    public void onHeaders(Metadata metadata2) {
                        super.onHeaders(metadata2);
                        metadataHandlerOption.onHeaders(metadata2);
                    }

                    @Override // io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener, io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
                    public void onClose(Status status, Metadata metadata2) {
                        super.onClose(status, metadata2);
                        metadataHandlerOption.onTrailers(metadata2);
                    }
                }, metadata);
            }
        };
    }
}
