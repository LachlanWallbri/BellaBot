package io.grpc.netty.shaded.io.netty.util.concurrent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SucceededFuture<V> extends CompleteFuture<V> {
    private final V result;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Throwable cause() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isSuccess() {
        return true;
    }

    public SucceededFuture(EventExecutor eventExecutor, V v) {
        super(eventExecutor);
        this.result = v;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public V getNow() {
        return this.result;
    }
}
