package com.google.api.gax.grpc;

import com.google.api.gax.tracing.ApiTracer;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcChannelUUIDInterceptor implements ClientInterceptor {
    private final String uuid = UUID.randomUUID().toString();

    @Override // io.grpc.ClientInterceptor
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        ApiTracer apiTracer = (ApiTracer) callOptions.getOption(GrpcCallContext.TRACER_KEY);
        if (apiTracer != null) {
            apiTracer.connectionSelected(this.uuid);
        }
        return channel.newCall(methodDescriptor, callOptions);
    }
}
