package io.grpc.netty.shaded.io.netty.channel.pool;

import io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GlobalEventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.util.Deque;
import java.util.concurrent.Callable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SimpleChannelPool implements ChannelPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final AttributeKey<SimpleChannelPool> POOL_KEY = AttributeKey.newInstance("io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool");
    private final Bootstrap bootstrap;
    private final Deque<Channel> deque;
    private final ChannelPoolHandler handler;
    private final ChannelHealthChecker healthCheck;
    private final boolean lastRecentUsed;
    private final boolean releaseHealthCheck;

    public SimpleChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler) {
        this(bootstrap, channelPoolHandler, ChannelHealthChecker.ACTIVE);
    }

    public SimpleChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler, ChannelHealthChecker channelHealthChecker) {
        this(bootstrap, channelPoolHandler, channelHealthChecker, true);
    }

    public SimpleChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler, ChannelHealthChecker channelHealthChecker, boolean z) {
        this(bootstrap, channelPoolHandler, channelHealthChecker, z, true);
    }

    public SimpleChannelPool(Bootstrap bootstrap, final ChannelPoolHandler channelPoolHandler, ChannelHealthChecker channelHealthChecker, boolean z, boolean z2) {
        this.deque = PlatformDependent.newConcurrentDeque();
        this.handler = (ChannelPoolHandler) ObjectUtil.checkNotNull(channelPoolHandler, "handler");
        this.healthCheck = (ChannelHealthChecker) ObjectUtil.checkNotNull(channelHealthChecker, "healthCheck");
        this.releaseHealthCheck = z;
        this.bootstrap = ((Bootstrap) ObjectUtil.checkNotNull(bootstrap, "bootstrap")).mo4504clone();
        this.bootstrap.handler(new ChannelInitializer<Channel>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInitializer
            protected void initChannel(Channel channel) throws Exception {
                channelPoolHandler.channelCreated(channel);
            }
        });
        this.lastRecentUsed = z2;
    }

    protected Bootstrap bootstrap() {
        return this.bootstrap;
    }

    protected ChannelPoolHandler handler() {
        return this.handler;
    }

    protected ChannelHealthChecker healthChecker() {
        return this.healthCheck;
    }

    protected boolean releaseHealthCheck() {
        return this.releaseHealthCheck;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool
    public final Future<Channel> acquire() {
        return acquire(this.bootstrap.config().group().next().newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool
    public Future<Channel> acquire(Promise<Channel> promise) {
        return acquireHealthyFromPoolOrNew((Promise) ObjectUtil.checkNotNull(promise, "promise"));
    }

    private Future<Channel> acquireHealthyFromPoolOrNew(final Promise<Channel> promise) {
        final Channel pollChannel;
        try {
            pollChannel = pollChannel();
        } catch (Throwable th) {
            promise.tryFailure(th);
        }
        if (pollChannel == null) {
            Bootstrap mo4504clone = this.bootstrap.mo4504clone();
            mo4504clone.attr(POOL_KEY, this);
            ChannelFuture connectChannel = connectChannel(mo4504clone);
            if (connectChannel.isDone()) {
                notifyConnect(connectChannel, promise);
            } else {
                connectChannel.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.2
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        SimpleChannelPool.this.notifyConnect(channelFuture, promise);
                    }
                });
            }
            return promise;
        }
        EventLoop eventLoop = pollChannel.eventLoop();
        if (eventLoop.inEventLoop()) {
            doHealthCheck(pollChannel, promise);
        } else {
            eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.3
                @Override // java.lang.Runnable
                public void run() {
                    SimpleChannelPool.this.doHealthCheck(pollChannel, promise);
                }
            });
        }
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConnect(ChannelFuture channelFuture, Promise<Channel> promise) throws Exception {
        if (channelFuture.isSuccess()) {
            Channel channel = channelFuture.channel();
            this.handler.channelAcquired(channel);
            if (promise.trySuccess(channel)) {
                return;
            }
            release(channel);
            return;
        }
        promise.tryFailure(channelFuture.cause());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHealthCheck(final Channel channel, final Promise<Channel> promise) {
        Future<Boolean> isHealthy = this.healthCheck.isHealthy(channel);
        if (isHealthy.isDone()) {
            notifyHealthCheck(isHealthy, channel, promise);
        } else {
            isHealthy.addListener(new FutureListener<Boolean>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.4
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<Boolean> future) throws Exception {
                    SimpleChannelPool.this.notifyHealthCheck(future, channel, promise);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyHealthCheck(Future<Boolean> future, Channel channel, Promise<Channel> promise) {
        if (future.isSuccess()) {
            if (future.getNow().booleanValue()) {
                try {
                    channel.attr(POOL_KEY).set(this);
                    this.handler.channelAcquired(channel);
                    promise.setSuccess(channel);
                    return;
                } catch (Throwable th) {
                    closeAndFail(channel, th, promise);
                    return;
                }
            }
            closeChannel(channel);
            acquireHealthyFromPoolOrNew(promise);
            return;
        }
        closeChannel(channel);
        acquireHealthyFromPoolOrNew(promise);
    }

    protected ChannelFuture connectChannel(Bootstrap bootstrap) {
        return bootstrap.connect();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool
    public final Future<Void> release(Channel channel) {
        return release(channel, channel.eventLoop().newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool
    public Future<Void> release(final Channel channel, final Promise<Void> promise) {
        ObjectUtil.checkNotNull(channel, "channel");
        ObjectUtil.checkNotNull(promise, "promise");
        try {
            EventLoop eventLoop = channel.eventLoop();
            if (eventLoop.inEventLoop()) {
                doReleaseChannel(channel, promise);
            } else {
                eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.5
                    @Override // java.lang.Runnable
                    public void run() {
                        SimpleChannelPool.this.doReleaseChannel(channel, promise);
                    }
                });
            }
        } catch (Throwable th) {
            closeAndFail(channel, th, promise);
        }
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doReleaseChannel(Channel channel, Promise<Void> promise) {
        if (channel.attr(POOL_KEY).getAndSet(null) != this) {
            closeAndFail(channel, new IllegalArgumentException("Channel " + channel + " was not acquired from this ChannelPool"), promise);
            return;
        }
        try {
            if (this.releaseHealthCheck) {
                doHealthCheckOnRelease(channel, promise);
            } else {
                releaseAndOffer(channel, promise);
            }
        } catch (Throwable th) {
            closeAndFail(channel, th, promise);
        }
    }

    private void doHealthCheckOnRelease(final Channel channel, final Promise<Void> promise) throws Exception {
        final Future<Boolean> isHealthy = this.healthCheck.isHealthy(channel);
        if (isHealthy.isDone()) {
            releaseAndOfferIfHealthy(channel, promise, isHealthy);
        } else {
            isHealthy.addListener(new FutureListener<Boolean>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.6
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<Boolean> future) throws Exception {
                    SimpleChannelPool.this.releaseAndOfferIfHealthy(channel, promise, isHealthy);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAndOfferIfHealthy(Channel channel, Promise<Void> promise, Future<Boolean> future) throws Exception {
        if (future.getNow().booleanValue()) {
            releaseAndOffer(channel, promise);
        } else {
            this.handler.channelReleased(channel);
            promise.setSuccess(null);
        }
    }

    private void releaseAndOffer(Channel channel, Promise<Void> promise) throws Exception {
        if (offerChannel(channel)) {
            this.handler.channelReleased(channel);
            promise.setSuccess(null);
        } else {
            closeAndFail(channel, new IllegalStateException("ChannelPool full") { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.7
                @Override // java.lang.Throwable
                public Throwable fillInStackTrace() {
                    return this;
                }
            }, promise);
        }
    }

    private void closeChannel(Channel channel) {
        channel.attr(POOL_KEY).getAndSet(null);
        channel.close();
    }

    private void closeAndFail(Channel channel, Throwable th, Promise<?> promise) {
        closeChannel(channel);
        promise.tryFailure(th);
    }

    protected Channel pollChannel() {
        return this.lastRecentUsed ? this.deque.pollLast() : this.deque.pollFirst();
    }

    protected boolean offerChannel(Channel channel) {
        return this.deque.offer(channel);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        while (true) {
            Channel pollChannel = pollChannel();
            if (pollChannel == null) {
                return;
            } else {
                pollChannel.close().awaitUninterruptibly();
            }
        }
    }

    public Future<Void> closeAsync() {
        return GlobalEventExecutor.INSTANCE.submit((Callable) new Callable<Void>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool.8
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                SimpleChannelPool.this.close();
                return null;
            }
        });
    }
}
