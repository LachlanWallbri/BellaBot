package io.grpc.netty.shaded.io.netty.util.concurrent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ProgressivePromise<V> extends Promise<V>, ProgressiveFuture<V> {
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> addListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> await() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> awaitUninterruptibly();

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> removeListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    ProgressivePromise<V> setFailure(Throwable th);

    ProgressivePromise<V> setProgress(long j, long j2);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    ProgressivePromise<V> setSuccess(V v);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> sync() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ProgressivePromise<V> syncUninterruptibly();

    boolean tryProgress(long j, long j2);
}
