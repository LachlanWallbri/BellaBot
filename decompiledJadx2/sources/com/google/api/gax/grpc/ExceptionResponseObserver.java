package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;
import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class ExceptionResponseObserver<RequestT, ResponseT> extends StateCheckingResponseObserver<ResponseT> {
    private volatile CancellationException cancellationException;
    private final GrpcApiExceptionFactory exceptionFactory;
    private ResponseObserver<ResponseT> innerObserver;

    public ExceptionResponseObserver(ResponseObserver<ResponseT> responseObserver, GrpcApiExceptionFactory grpcApiExceptionFactory) {
        this.innerObserver = responseObserver;
        this.exceptionFactory = grpcApiExceptionFactory;
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onStartImpl(final StreamController streamController) {
        this.innerObserver.onStart(new StreamController() { // from class: com.google.api.gax.grpc.ExceptionResponseObserver.1
            @Override // com.google.api.gax.rpc.StreamController
            public void cancel() {
                ExceptionResponseObserver.this.cancellationException = new CancellationException("User cancelled stream");
                streamController.cancel();
            }

            @Override // com.google.api.gax.rpc.StreamController
            public void disableAutoInboundFlowControl() {
                streamController.disableAutoInboundFlowControl();
            }

            @Override // com.google.api.gax.rpc.StreamController
            public void request(int i) {
                streamController.request(i);
            }
        });
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onResponseImpl(ResponseT responset) {
        this.innerObserver.onResponse(responset);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onErrorImpl(Throwable th) {
        Throwable create;
        if (this.cancellationException != null) {
            create = this.cancellationException;
        } else {
            create = this.exceptionFactory.create(th);
        }
        this.innerObserver.onError(create);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onCompleteImpl() {
        this.innerObserver.onComplete();
    }
}
