package io.grpc.netty.shaded.io.netty.util.concurrent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ProgressiveFuture<V> extends Future<V> {
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> addListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> addListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> await() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> awaitUninterruptibly();

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> removeListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> sync() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressiveFuture<V> syncUninterruptibly();
}
