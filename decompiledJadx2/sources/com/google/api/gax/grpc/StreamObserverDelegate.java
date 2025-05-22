package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiStreamObserver;
import io.grpc.stub.StreamObserver;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class StreamObserverDelegate<V> implements ApiStreamObserver<V> {
    private final StreamObserver<V> delegate;

    public StreamObserverDelegate(StreamObserver<V> streamObserver) {
        this.delegate = streamObserver;
    }

    @Override // com.google.api.gax.rpc.ApiStreamObserver
    public void onNext(V v) {
        this.delegate.onNext(v);
    }

    @Override // com.google.api.gax.rpc.ApiStreamObserver
    public void onError(Throwable th) {
        this.delegate.onError(th);
    }

    @Override // com.google.api.gax.rpc.ApiStreamObserver
    public void onCompleted() {
        this.delegate.onCompleted();
    }
}
