package io.grpc.netty.shaded.io.netty.channel.pool;

import io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GlobalEventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class FixedChannelPool extends SimpleChannelPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long acquireTimeoutNanos;
    private final AtomicInteger acquiredChannelCount;
    private boolean closed;
    private final EventExecutor executor;
    private final int maxConnections;
    private final int maxPendingAcquires;
    private int pendingAcquireCount;
    private final Queue<AcquireTask> pendingAcquireQueue;
    private final Runnable timeoutTask;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum AcquireTimeoutAction {
        NEW,
        FAIL
    }

    static /* synthetic */ int access$806(FixedChannelPool fixedChannelPool) {
        int i = fixedChannelPool.pendingAcquireCount - 1;
        fixedChannelPool.pendingAcquireCount = i;
        return i;
    }

    public FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler, int i) {
        this(bootstrap, channelPoolHandler, i, Integer.MAX_VALUE);
    }

    public FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler, int i, int i2) {
        this(bootstrap, channelPoolHandler, ChannelHealthChecker.ACTIVE, null, -1L, i, i2);
    }

    public FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler, ChannelHealthChecker channelHealthChecker, AcquireTimeoutAction acquireTimeoutAction, long j, int i, int i2) {
        this(bootstrap, channelPoolHandler, channelHealthChecker, acquireTimeoutAction, j, i, i2, true);
    }

    public FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler, ChannelHealthChecker channelHealthChecker, AcquireTimeoutAction acquireTimeoutAction, long j, int i, int i2, boolean z) {
        this(bootstrap, channelPoolHandler, channelHealthChecker, acquireTimeoutAction, j, i, i2, z, true);
    }

    public FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler channelPoolHandler, ChannelHealthChecker channelHealthChecker, AcquireTimeoutAction acquireTimeoutAction, long j, int i, int i2, boolean z, boolean z2) {
        super(bootstrap, channelPoolHandler, channelHealthChecker, z, z2);
        this.pendingAcquireQueue = new ArrayDeque();
        this.acquiredChannelCount = new AtomicInteger();
        if (i < 1) {
            throw new IllegalArgumentException("maxConnections: " + i + " (expected: >= 1)");
        }
        if (i2 < 1) {
            throw new IllegalArgumentException("maxPendingAcquires: " + i2 + " (expected: >= 1)");
        }
        if (acquireTimeoutAction == null && j == -1) {
            this.timeoutTask = null;
            this.acquireTimeoutNanos = -1L;
        } else {
            if (acquireTimeoutAction == null && j != -1) {
                throw new NullPointerException("action");
            }
            if (acquireTimeoutAction != null && j < 0) {
                throw new IllegalArgumentException("acquireTimeoutMillis: " + j + " (expected: >= 0)");
            }
            this.acquireTimeoutNanos = TimeUnit.MILLISECONDS.toNanos(j);
            int i3 = C64107.f8342xa1ee8ab3[acquireTimeoutAction.ordinal()];
            if (i3 == 1) {
                this.timeoutTask = new TimeoutTask() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.1
                    @Override // io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.TimeoutTask
                    public void onTimeout(AcquireTask acquireTask) {
                        acquireTask.promise.setFailure(new TimeoutException("Acquire operation took longer then configured maximum time") { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.1.1
                            @Override // java.lang.Throwable
                            public Throwable fillInStackTrace() {
                                return this;
                            }
                        });
                    }
                };
            } else if (i3 == 2) {
                this.timeoutTask = new TimeoutTask() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.2
                    @Override // io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.TimeoutTask
                    public void onTimeout(AcquireTask acquireTask) {
                        acquireTask.acquired();
                        FixedChannelPool.super.acquire(acquireTask.promise);
                    }
                };
            } else {
                throw new Error();
            }
        }
        this.executor = bootstrap.config().group().next();
        this.maxConnections = i;
        this.maxPendingAcquires = i2;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool$7 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64107 {

        /* renamed from: $SwitchMap$io$netty$channel$pool$FixedChannelPool$AcquireTimeoutAction */
        static final /* synthetic */ int[] f8342xa1ee8ab3 = new int[AcquireTimeoutAction.values().length];

        static {
            try {
                f8342xa1ee8ab3[AcquireTimeoutAction.FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8342xa1ee8ab3[AcquireTimeoutAction.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public int acquiredChannelCount() {
        return this.acquiredChannelCount.get();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool, io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool
    public Future<Channel> acquire(final Promise<Channel> promise) {
        try {
            if (this.executor.inEventLoop()) {
                acquire0(promise);
            } else {
                this.executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.3
                    @Override // java.lang.Runnable
                    public void run() {
                        FixedChannelPool.this.acquire0(promise);
                    }
                });
            }
        } catch (Throwable th) {
            promise.setFailure(th);
        }
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acquire0(Promise<Channel> promise) {
        if (this.closed) {
            promise.setFailure(new IllegalStateException("FixedChannelPool was closed"));
            return;
        }
        if (this.acquiredChannelCount.get() < this.maxConnections) {
            Promise<Channel> newPromise = this.executor.newPromise();
            AcquireListener acquireListener = new AcquireListener(promise);
            acquireListener.acquired();
            newPromise.addListener((GenericFutureListener<? extends Future<? super Channel>>) acquireListener);
            super.acquire(newPromise);
            return;
        }
        if (this.pendingAcquireCount >= this.maxPendingAcquires) {
            tooManyOutstanding(promise);
            return;
        }
        AcquireTask acquireTask = new AcquireTask(promise);
        if (this.pendingAcquireQueue.offer(acquireTask)) {
            this.pendingAcquireCount++;
            Runnable runnable = this.timeoutTask;
            if (runnable != null) {
                acquireTask.timeoutFuture = this.executor.schedule(runnable, this.acquireTimeoutNanos, TimeUnit.NANOSECONDS);
                return;
            }
            return;
        }
        tooManyOutstanding(promise);
    }

    private void tooManyOutstanding(Promise<?> promise) {
        promise.setFailure(new IllegalStateException("Too many outstanding acquire operations"));
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool, io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool
    public Future<Void> release(final Channel channel, final Promise<Void> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        super.release(channel, this.executor.newPromise().addListener((GenericFutureListener) new FutureListener<Void>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.4
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Void> future) throws Exception {
                if (FixedChannelPool.this.closed) {
                    channel.close();
                    promise.setFailure(new IllegalStateException("FixedChannelPool was closed"));
                } else if (future.isSuccess()) {
                    FixedChannelPool.this.decrementAndRunTaskQueue();
                    promise.setSuccess(null);
                } else {
                    if (!(future.cause() instanceof IllegalArgumentException)) {
                        FixedChannelPool.this.decrementAndRunTaskQueue();
                    }
                    promise.setFailure(future.cause());
                }
            }
        }));
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decrementAndRunTaskQueue() {
        this.acquiredChannelCount.decrementAndGet();
        runTaskQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runTaskQueue() {
        AcquireTask poll;
        while (this.acquiredChannelCount.get() < this.maxConnections && (poll = this.pendingAcquireQueue.poll()) != null) {
            ScheduledFuture<?> scheduledFuture = poll.timeoutFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.pendingAcquireCount--;
            poll.acquired();
            super.acquire(poll.promise);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class AcquireTask extends AcquireListener {
        final long expireNanoTime;
        final Promise<Channel> promise;
        ScheduledFuture<?> timeoutFuture;

        AcquireTask(Promise<Channel> promise) {
            super(promise);
            this.expireNanoTime = System.nanoTime() + FixedChannelPool.this.acquireTimeoutNanos;
            this.promise = FixedChannelPool.this.executor.newPromise().addListener((GenericFutureListener) this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private abstract class TimeoutTask implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        public abstract void onTimeout(AcquireTask acquireTask);

        private TimeoutTask() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long nanoTime = System.nanoTime();
            while (true) {
                AcquireTask acquireTask = (AcquireTask) FixedChannelPool.this.pendingAcquireQueue.peek();
                if (acquireTask == null || nanoTime - acquireTask.expireNanoTime < 0) {
                    return;
                }
                FixedChannelPool.this.pendingAcquireQueue.remove();
                FixedChannelPool.access$806(FixedChannelPool.this);
                onTimeout(acquireTask);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class AcquireListener implements FutureListener<Channel> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        protected boolean acquired;
        private final Promise<Channel> originalPromise;

        AcquireListener(Promise<Channel> promise) {
            this.originalPromise = promise;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(Future<Channel> future) throws Exception {
            if (FixedChannelPool.this.closed) {
                if (future.isSuccess()) {
                    future.getNow().close();
                }
                this.originalPromise.setFailure(new IllegalStateException("FixedChannelPool was closed"));
            } else {
                if (future.isSuccess()) {
                    this.originalPromise.setSuccess(future.getNow());
                    return;
                }
                if (this.acquired) {
                    FixedChannelPool.this.decrementAndRunTaskQueue();
                } else {
                    FixedChannelPool.this.runTaskQueue();
                }
                this.originalPromise.setFailure(future.cause());
            }
        }

        public void acquired() {
            if (this.acquired) {
                return;
            }
            FixedChannelPool.this.acquiredChannelCount.incrementAndGet();
            this.acquired = true;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool, io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            closeAsync().await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.SimpleChannelPool
    public Future<Void> closeAsync() {
        if (this.executor.inEventLoop()) {
            return close0();
        }
        final Promise newPromise = this.executor.newPromise();
        this.executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.5
            @Override // java.lang.Runnable
            public void run() {
                FixedChannelPool.this.close0().addListener(new FutureListener<Void>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.5.1
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<Void> future) throws Exception {
                        if (future.isSuccess()) {
                            newPromise.setSuccess(null);
                        } else {
                            newPromise.setFailure(future.cause());
                        }
                    }
                });
            }
        });
        return newPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Future<Void> close0() {
        if (!this.closed) {
            this.closed = true;
            while (true) {
                AcquireTask poll = this.pendingAcquireQueue.poll();
                if (poll != null) {
                    ScheduledFuture<?> scheduledFuture = poll.timeoutFuture;
                    if (scheduledFuture != null) {
                        scheduledFuture.cancel(false);
                    }
                    poll.promise.setFailure(new ClosedChannelException());
                } else {
                    this.acquiredChannelCount.set(0);
                    this.pendingAcquireCount = 0;
                    return GlobalEventExecutor.INSTANCE.submit((Callable) new Callable<Void>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.FixedChannelPool.6
                        @Override // java.util.concurrent.Callable
                        public Void call() throws Exception {
                            FixedChannelPool.super.close();
                            return null;
                        }
                    });
                }
            }
        } else {
            return GlobalEventExecutor.INSTANCE.newSucceededFuture(null);
        }
    }
}
