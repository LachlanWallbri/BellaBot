package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes2.dex */
class ApiStreamObserverAdapter<T> extends StateCheckingResponseObserver<T> {
    private final ApiStreamObserver<T> delegate;

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onStartImpl(StreamController streamController) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiStreamObserverAdapter(ApiStreamObserver<T> apiStreamObserver) {
        this.delegate = apiStreamObserver;
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onResponseImpl(T t) {
        this.delegate.onNext(t);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onErrorImpl(Throwable th) {
        this.delegate.onError(th);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onCompleteImpl() {
        this.delegate.onCompleted();
    }
}
