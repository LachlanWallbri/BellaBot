package io.grpc.netty.shaded.io.netty.util.concurrent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface EventExecutor extends EventExecutorGroup {
    boolean inEventLoop();

    boolean inEventLoop(Thread thread);

    <V> Future<V> newFailedFuture(Throwable th);

    <V> ProgressivePromise<V> newProgressivePromise();

    <V> Promise<V> newPromise();

    <V> Future<V> newSucceededFuture(V v);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    EventExecutor next();

    EventExecutorGroup parent();
}
