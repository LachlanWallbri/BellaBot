package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiStreamObserver;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class GrpcExceptionTranslatingStreamObserver<ResponseT> implements ApiStreamObserver<ResponseT> {
    private final GrpcApiExceptionFactory exceptionFactory;
    private final ApiStreamObserver<ResponseT> innerObserver;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcExceptionTranslatingStreamObserver(ApiStreamObserver<ResponseT> apiStreamObserver, GrpcApiExceptionFactory grpcApiExceptionFactory) {
        this.innerObserver = apiStreamObserver;
        this.exceptionFactory = grpcApiExceptionFactory;
    }

    @Override // com.google.api.gax.rpc.ApiStreamObserver
    public void onNext(ResponseT responset) {
        this.innerObserver.onNext(responset);
    }

    @Override // com.google.api.gax.rpc.ApiStreamObserver
    public void onError(Throwable th) {
        this.innerObserver.onError(this.exceptionFactory.create(th));
    }

    @Override // com.google.api.gax.rpc.ApiStreamObserver
    public void onCompleted() {
        this.innerObserver.onCompleted();
    }
}
