package io.grpc.netty.shaded.io.netty.channel.group;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelGroupFuture extends Future<Void>, Iterable<ChannelFuture> {
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> await() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> awaitUninterruptibly();

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    ChannelGroupException cause();

    ChannelFuture find(Channel channel);

    ChannelGroup group();

    boolean isPartialFailure();

    boolean isPartialSuccess();

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    boolean isSuccess();

    @Override // java.lang.Iterable
    Iterator<ChannelFuture> iterator();

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr);

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> sync() throws InterruptedException;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    Future<Void> syncUninterruptibly();
}
