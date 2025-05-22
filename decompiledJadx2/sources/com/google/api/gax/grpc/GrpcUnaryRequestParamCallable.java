package com.google.api.gax.grpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.RequestUrlParamsEncoder;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcUnaryRequestParamCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final RequestUrlParamsEncoder<RequestT> paramsEncoder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcUnaryRequestParamCallable(UnaryCallable<RequestT, ResponseT> unaryCallable, RequestParamsExtractor<RequestT> requestParamsExtractor) {
        this.callable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.paramsEncoder = new RequestUrlParamsEncoder<>((RequestParamsExtractor) Preconditions.checkNotNull(requestParamsExtractor), false);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        return this.callable.futureCall(requestt, GrpcCallContext.createDefault().nullToSelf(apiCallContext).withRequestParamsDynamicHeaderOption(this.paramsEncoder.encode(requestt)));
    }
}
