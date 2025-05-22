package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.RequestUrlParamsEncoder;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcServerStreamingRequestParamCallable<RequestT, ResponseT> extends ServerStreamingCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> callable;
    private final RequestUrlParamsEncoder<RequestT> paramsEncoder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcServerStreamingRequestParamCallable(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, RequestParamsExtractor<RequestT> requestParamsExtractor) {
        this.callable = (ServerStreamingCallable) Preconditions.checkNotNull(serverStreamingCallable);
        this.paramsEncoder = new RequestUrlParamsEncoder<>((RequestParamsExtractor) Preconditions.checkNotNull(requestParamsExtractor), false);
    }

    @Override // com.google.api.gax.rpc.ServerStreamingCallable
    public void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext) {
        this.callable.call(requestt, responseObserver, contextWithParamsEncoder(requestt, apiCallContext));
    }

    private ApiCallContext contextWithParamsEncoder(RequestT requestt, ApiCallContext apiCallContext) {
        return GrpcCallContext.createDefault().nullToSelf(apiCallContext).withRequestParamsDynamicHeaderOption(this.paramsEncoder.encode(requestt));
    }
}
