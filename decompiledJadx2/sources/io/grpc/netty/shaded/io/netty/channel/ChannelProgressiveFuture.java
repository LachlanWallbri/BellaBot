package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ProgressiveFuture;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelProgressiveFuture extends ChannelFuture, ProgressiveFuture<Void> {
    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> await() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> awaitUninterruptibly();

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> sync() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> syncUninterruptibly();
}
