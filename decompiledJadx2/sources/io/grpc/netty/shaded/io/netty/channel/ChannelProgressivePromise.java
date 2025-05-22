package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelProgressivePromise extends ProgressivePromise<Void>, ChannelProgressiveFuture, ChannelPromise {
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise await() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise awaitUninterruptibly();

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    ChannelProgressivePromise setFailure(Throwable th);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise
    ProgressivePromise<Void> setProgress(long j, long j2);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    ChannelProgressivePromise setSuccess();

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    ChannelProgressivePromise setSuccess(Void r1);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise sync() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelProgressivePromise syncUninterruptibly();

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    ChannelProgressivePromise unvoid();
}
