package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public abstract class StateCheckingResponseObserver<V> implements ResponseObserver<V> {
    private boolean isClosed;
    private boolean isStarted;

    protected abstract void onCompleteImpl();

    protected abstract void onErrorImpl(Throwable th);

    protected abstract void onResponseImpl(V v);

    protected abstract void onStartImpl(StreamController streamController);

    @Override // com.google.api.gax.rpc.ResponseObserver
    public final void onStart(StreamController streamController) {
        Preconditions.checkState(!this.isStarted, getClass() + " is already started.");
        this.isStarted = true;
        onStartImpl(streamController);
    }

    @Override // com.google.api.gax.rpc.ResponseObserver
    public final void onResponse(V v) {
        Preconditions.checkState(!this.isClosed, getClass() + " received a response after being closed.");
        onResponseImpl(v);
    }

    @Override // com.google.api.gax.rpc.ResponseObserver
    public final void onComplete() {
        Preconditions.checkState(!this.isClosed, getClass() + " tried to double close.");
        this.isClosed = true;
        onCompleteImpl();
    }

    @Override // com.google.api.gax.rpc.ResponseObserver
    public final void onError(Throwable th) {
        Preconditions.checkState(!this.isClosed, getClass() + " received error after being closed", th);
        this.isClosed = true;
        onErrorImpl(th);
    }
}
