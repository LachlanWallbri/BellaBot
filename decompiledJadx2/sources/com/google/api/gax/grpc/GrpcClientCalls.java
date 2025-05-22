package com.google.api.gax.grpc;

import com.google.api.client.util.Preconditions;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.tracing.ApiTracer;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptors;
import io.grpc.Deadline;
import io.grpc.MethodDescriptor;
import io.grpc.stub.MetadataUtils;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcClientCalls {
    private GrpcClientCalls() {
    }

    public static <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, ApiCallContext apiCallContext) {
        if (!(apiCallContext instanceof GrpcCallContext)) {
            throw new IllegalArgumentException("context must be an instance of GrpcCallContext, but found " + apiCallContext.getClass().getName());
        }
        GrpcCallContext grpcCallContext = (GrpcCallContext) apiCallContext;
        Preconditions.checkNotNull(grpcCallContext.getChannel());
        CallOptions callOptions = grpcCallContext.getCallOptions();
        Preconditions.checkNotNull(callOptions);
        if (grpcCallContext.getTimeout() != null) {
            Deadline after = Deadline.after(grpcCallContext.getTimeout().toMillis(), TimeUnit.MILLISECONDS);
            Deadline deadline = callOptions.getDeadline();
            if (deadline == null || after.isBefore(deadline)) {
                callOptions = callOptions.withDeadline(after);
            }
        }
        Channel channel = grpcCallContext.getChannel();
        if (grpcCallContext.getChannelAffinity() != null && (channel instanceof ChannelPool)) {
            channel = ((ChannelPool) channel).getChannel(grpcCallContext.getChannelAffinity().intValue());
        }
        if (!grpcCallContext.getExtraHeaders().isEmpty()) {
            channel = ClientInterceptors.intercept(channel, MetadataUtils.newAttachHeadersInterceptor(grpcCallContext.getMetadata()));
        }
        ApiTracer.Scope inScope = grpcCallContext.getTracer().inScope();
        try {
            ClientCall<RequestT, ResponseT> newCall = channel.newCall(methodDescriptor, callOptions);
            if (inScope != null) {
                inScope.close();
            }
            return newCall;
        } finally {
        }
    }
}
