package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.RequestParamsExtractor;
import io.grpc.MethodDescriptor;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class GrpcCallSettings<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> methodDescriptor;
    private final RequestParamsExtractor<RequestT> paramsExtractor;

    private GrpcCallSettings(MethodDescriptor<RequestT, ResponseT> methodDescriptor, RequestParamsExtractor<RequestT> requestParamsExtractor) {
        this.methodDescriptor = methodDescriptor;
        this.paramsExtractor = requestParamsExtractor;
    }

    public MethodDescriptor<RequestT, ResponseT> getMethodDescriptor() {
        return this.methodDescriptor;
    }

    @BetaApi
    public RequestParamsExtractor<RequestT> getParamsExtractor() {
        return this.paramsExtractor;
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder() {
        return new Builder<>();
    }

    public static <RequestT, ResponseT> GrpcCallSettings<RequestT, ResponseT> create(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
        return newBuilder().setMethodDescriptor(methodDescriptor).build();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<RequestT, ResponseT> {
        private MethodDescriptor<RequestT, ResponseT> methodDescriptor;
        private RequestParamsExtractor<RequestT> paramsExtractor;

        private Builder() {
        }

        private Builder(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings) {
            this.methodDescriptor = ((GrpcCallSettings) grpcCallSettings).methodDescriptor;
        }

        public Builder<RequestT, ResponseT> setMethodDescriptor(MethodDescriptor<RequestT, ResponseT> methodDescriptor) {
            this.methodDescriptor = methodDescriptor;
            return this;
        }

        @BetaApi
        public Builder<RequestT, ResponseT> setParamsExtractor(RequestParamsExtractor<RequestT> requestParamsExtractor) {
            this.paramsExtractor = requestParamsExtractor;
            return this;
        }

        public GrpcCallSettings<RequestT, ResponseT> build() {
            return new GrpcCallSettings<>(this.methodDescriptor, this.paramsExtractor);
        }
    }
}
