package io.netty.channel;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ProgressivePromise;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface ChannelProgressivePromise extends ProgressivePromise<Void>, ChannelProgressiveFuture, ChannelPromise {
    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise await() throws InterruptedException;

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise awaitUninterruptibly();

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.channel.ChannelPromise
    ChannelProgressivePromise setFailure(Throwable th);

    @Override // io.netty.util.concurrent.ProgressivePromise
    ProgressivePromise<Void> setProgress(long j, long j2);

    @Override // io.netty.channel.ChannelPromise
    ChannelProgressivePromise setSuccess();

    @Override // io.netty.channel.ChannelPromise
    ChannelProgressivePromise setSuccess(Void r1);

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise sync() throws InterruptedException;

    @Override // io.netty.util.concurrent.ProgressivePromise, io.netty.util.concurrent.Promise, io.netty.util.concurrent.Future
    ChannelProgressivePromise syncUninterruptibly();

    @Override // io.netty.channel.ChannelPromise
    ChannelProgressivePromise unvoid();
}
