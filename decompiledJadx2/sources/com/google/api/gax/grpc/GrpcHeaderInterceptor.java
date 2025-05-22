package com.google.api.gax.grpc;

import com.google.api.core.InternalApi;
import com.google.common.collect.ImmutableMap;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public class GrpcHeaderInterceptor implements ClientInterceptor {
    private final Map<Metadata.Key<String>, String> staticHeaders;
    private final String userAgentHeader;

    public GrpcHeaderInterceptor(Map<String, String> map) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        String str = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Metadata.Key m3901of = Metadata.Key.m3901of(entry.getKey(), Metadata.ASCII_STRING_MARSHALLER);
            if ("user-agent".equals(m3901of.name())) {
                str = entry.getValue();
            } else {
                builder.put(m3901of, entry.getValue());
            }
        }
        this.staticHeaders = builder.build();
        this.userAgentHeader = str;
    }

    @Override // io.grpc.ClientInterceptor
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, final CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) { // from class: com.google.api.gax.grpc.GrpcHeaderInterceptor.1
            @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
            public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                for (Map.Entry entry : GrpcHeaderInterceptor.this.staticHeaders.entrySet()) {
                    metadata.put((Metadata.Key) entry.getKey(), entry.getValue());
                }
                for (Map.Entry<Metadata.Key<String>, String> entry2 : CallOptionsUtil.getDynamicHeadersOption(callOptions).entrySet()) {
                    metadata.put(entry2.getKey(), entry2.getValue());
                }
                super.start(listener, metadata);
            }
        };
    }

    public String getUserAgentHeader() {
        return this.userAgentHeader;
    }
}
