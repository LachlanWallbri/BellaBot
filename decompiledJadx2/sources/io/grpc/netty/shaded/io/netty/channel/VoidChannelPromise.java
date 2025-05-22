package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractFuture;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class VoidChannelPromise extends AbstractFuture<Void> implements ChannelPromise {
    private final Channel channel;
    private final ChannelFutureListener fireExceptionListener;

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Throwable cause() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Void getNow() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isCancellable() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isSuccess() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture
    public boolean isVoid() {
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public VoidChannelPromise setSuccess() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public VoidChannelPromise setSuccess(Void r1) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean setUncancellable() {
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public boolean trySuccess() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean trySuccess(Void r1) {
        return false;
    }

    public VoidChannelPromise(Channel channel, boolean z) {
        ObjectUtil.checkNotNull(channel, "channel");
        this.channel = channel;
        if (z) {
            this.fireExceptionListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.VoidChannelPromise.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    Throwable cause = channelFuture.cause();
                    if (cause != null) {
                        VoidChannelPromise.this.fireException0(cause);
                    }
                }
            };
        } else {
            this.fireExceptionListener = null;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        fail();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        fail();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> await() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean await(long j, TimeUnit timeUnit) {
        fail();
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean await(long j) {
        fail();
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> awaitUninterruptibly() {
        fail();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
        fail();
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean awaitUninterruptibly(long j) {
        fail();
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise, io.grpc.netty.shaded.io.netty.channel.ChannelFuture
    public Channel channel() {
        return this.channel;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> sync() {
        fail();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> syncUninterruptibly() {
        fail();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public VoidChannelPromise setFailure(Throwable th) {
        fireException0(th);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean tryFailure(Throwable th) {
        fireException0(th);
        return false;
    }

    private static void fail() {
        throw new IllegalStateException("void future");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public ChannelPromise unvoid() {
        DefaultChannelPromise defaultChannelPromise = new DefaultChannelPromise(this.channel);
        ChannelFutureListener channelFutureListener = this.fireExceptionListener;
        if (channelFutureListener != null) {
            defaultChannelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
        }
        return defaultChannelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireException0(Throwable th) {
        if (this.fireExceptionListener == null || !this.channel.isRegistered()) {
            return;
        }
        this.channel.pipeline().fireExceptionCaught(th);
    }
}
