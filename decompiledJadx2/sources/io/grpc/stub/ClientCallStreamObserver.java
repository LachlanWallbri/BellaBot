package io.grpc.stub;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ClientCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract void cancel(@Nullable String str, @Nullable Throwable th);

    @Override // io.grpc.stub.CallStreamObserver
    public abstract boolean isReady();

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void request(int i);

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void setMessageCompression(boolean z);

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void setOnReadyHandler(Runnable runnable);

    public void disableAutoRequestWithInitial(int i) {
        throw new UnsupportedOperationException();
    }
}
