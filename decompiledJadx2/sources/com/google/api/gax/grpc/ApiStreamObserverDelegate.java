package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiStreamObserver;
import io.grpc.stub.StreamObserver;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class ApiStreamObserverDelegate<V> implements StreamObserver<V> {
    private final ApiStreamObserver<V> delegate;

    public ApiStreamObserverDelegate(ApiStreamObserver<V> apiStreamObserver) {
        this.delegate = apiStreamObserver;
    }

    @Override // io.grpc.stub.StreamObserver
    public void onNext(V v) {
        this.delegate.onNext(v);
    }

    @Override // io.grpc.stub.StreamObserver
    public void onError(Throwable th) {
        this.delegate.onError(th);
    }

    @Override // io.grpc.stub.StreamObserver
    public void onCompleted() {
        this.delegate.onCompleted();
    }
}
