package io.grpc.stub;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ServerCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract boolean isCancelled();

    @Override // io.grpc.stub.CallStreamObserver
    public abstract boolean isReady();

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void request(int i);

    public abstract void setCompression(String str);

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void setMessageCompression(boolean z);

    public abstract void setOnCancelHandler(Runnable runnable);

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void setOnReadyHandler(Runnable runnable);

    public void disableAutoRequest() {
        throw new UnsupportedOperationException();
    }
}
