package io.netty.channel.pool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ThrowableUtil;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class FixedChannelPool extends SimpleChannelPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long acquireTimeoutNanos;
    private int acquiredChannelCount;
    private boolean closed;
    private final EventExecutor executor;
    private final int maxConnections;
    private final int maxPendingAcquires;
    private int pendingAcquireCount;
    private final Queue<AcquireTask> pendingAcquireQueue;
    private final Runnable timeoutTask;
    private static final IllegalStateException FULL_EXCEPTION = (IllegalStateException) ThrowableUtil.unknownStackTrace(new IllegalStateException("Too many outstanding acquire operations"), FixedChannelPool.class, "acquire0(...)");
    private static final TimeoutException TIMEOUT_EXCEPTION = (TimeoutException) ThrowableUtil.unknownStackTrace(new TimeoutException("Acquire operation took longer then configured maximum time"), FixedChannelPool.class, "<init>(...)");
    static final IllegalStateException POOL_CLOSED_ON_RELEASE_EXCEPTION = (IllegalStateException) ThrowableUtil.unknownStackTrace(new IllegalStateException("FixedChannelPooled was closed"), FixedChannelPool.class, "release(...)");
    static final IllegalStateException POOL_CLOSED_ON_ACQUIRE_EXCEPTION = (IllegalStateException) ThrowableUtil.unknownStackTrace(new IllegalStateException("FixedChannelPooled was closed"), FixedChannelPool.class, "acquire0(...)");

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public enum AcquireTimeoutAction {
        NEW,
        FAIL
    }

    static /* synthetic */ int access$1108(FixedChannelPool fixedChannelPool) {
        int i = fixedChannelPool.acquiredChannelCount;
        fixedChannelPool.acquiredChannelCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$906(FixedChannelPool fixedChannelPool) {
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
            int i3 = C70586.f8456xa1ee8ab3[acquireTimeoutAction.ordinal()];
            if (i3 == 1) {
                this.timeoutTask = new TimeoutTask() { // from class: io.netty.channel.pool.FixedChannelPool.1

                    /* JADX WARN: Classes with same name are omitted:
                      classes4.dex
                     */
                    /* renamed from: io.netty.channel.pool.FixedChannelPool$1$1, reason: invalid class name */
                    /* loaded from: classes5.dex */
                    class AnonymousClass1 extends TimeoutException {
                        AnonymousClass1(String str) {
                            super(str);
                        }

                        @Override // java.lang.Throwable
                        public synchronized Throwable fillInStackTrace() {
                            return this;
                        }
                    }

                    @Override // io.netty.channel.pool.FixedChannelPool.TimeoutTask
                    public void onTimeout(AcquireTask acquireTask) {
                        acquireTask.promise.setFailure(FixedChannelPool.TIMEOUT_EXCEPTION);
                    }
                };
            } else if (i3 == 2) {
                this.timeoutTask = new TimeoutTask() { // from class: io.netty.channel.pool.FixedChannelPool.2
                    @Override // io.netty.channel.pool.FixedChannelPool.TimeoutTask
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
      classes4.dex
     */
    /* renamed from: io.netty.channel.pool.FixedChannelPool$7 */
    /* loaded from: classes5.dex */
    static /* synthetic */ class C70597 {

        /* renamed from: $SwitchMap$io$netty$channel$pool$FixedChannelPool$AcquireTimeoutAction */
        static final /* synthetic */ int[] f8457xa1ee8ab3 = new int[AcquireTimeoutAction.values().length];

        static {
            try {
                f8457xa1ee8ab3[AcquireTimeoutAction.FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8457xa1ee8ab3[AcquireTimeoutAction.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* renamed from: io.netty.channel.pool.FixedChannelPool$6 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C70586 {

        /* renamed from: $SwitchMap$io$netty$channel$pool$FixedChannelPool$AcquireTimeoutAction */
        static final /* synthetic */ int[] f8456xa1ee8ab3;

        static {
            int[] iArr = new int[AcquireTimeoutAction.values().length];
            f8456xa1ee8ab3 = iArr;
            try {
                iArr[AcquireTimeoutAction.FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8456xa1ee8ab3[AcquireTimeoutAction.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // io.netty.channel.pool.SimpleChannelPool, io.netty.channel.pool.ChannelPool
    public Future<Channel> acquire(final Promise<Channel> promise) {
        try {
            if (this.executor.inEventLoop()) {
                acquire0(promise);
            } else {
                this.executor.execute(new Runnable() { // from class: io.netty.channel.pool.FixedChannelPool.3
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
            promise.setFailure(POOL_CLOSED_ON_ACQUIRE_EXCEPTION);
            return;
        }
        if (this.acquiredChannelCount < this.maxConnections) {
            Promise<Channel> newPromise = this.executor.newPromise();
            AcquireListener acquireListener = new AcquireListener(promise);
            acquireListener.acquired();
            newPromise.addListener((GenericFutureListener<? extends Future<? super Channel>>) acquireListener);
            super.acquire(newPromise);
            return;
        }
        if (this.pendingAcquireCount >= this.maxPendingAcquires) {
            promise.setFailure(FULL_EXCEPTION);
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
        promise.setFailure(FULL_EXCEPTION);
    }

    @Override // io.netty.channel.pool.SimpleChannelPool, io.netty.channel.pool.ChannelPool
    public Future<Void> release(final Channel channel, final Promise<Void> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        super.release(channel, this.executor.newPromise().addListener((GenericFutureListener) new FutureListener<Void>() { // from class: io.netty.channel.pool.FixedChannelPool.4
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Void> future) throws Exception {
                if (FixedChannelPool.this.closed) {
                    channel.close();
                    promise.setFailure(FixedChannelPool.POOL_CLOSED_ON_RELEASE_EXCEPTION);
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
        this.acquiredChannelCount--;
        runTaskQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runTaskQueue() {
        AcquireTask poll;
        while (this.acquiredChannelCount < this.maxConnections && (poll = this.pendingAcquireQueue.poll()) != null) {
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
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public final class AcquireTask extends AcquireListener {
        final long expireNanoTime;
        final Promise<Channel> promise;
        ScheduledFuture<?> timeoutFuture;

        public AcquireTask(Promise<Channel> promise) {
            super(promise);
            this.expireNanoTime = System.nanoTime() + FixedChannelPool.this.acquireTimeoutNanos;
            this.promise = FixedChannelPool.this.executor.newPromise().addListener((GenericFutureListener) this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
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
                FixedChannelPool.access$906(FixedChannelPool.this);
                onTimeout(acquireTask);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public class AcquireListener implements FutureListener<Channel> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        protected boolean acquired;
        private final Promise<Channel> originalPromise;

        AcquireListener(Promise<Channel> promise) {
            this.originalPromise = promise;
        }

        @Override // io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(Future<Channel> future) throws Exception {
            if (FixedChannelPool.this.closed) {
                if (future.isSuccess()) {
                    future.getNow().close();
                }
                this.originalPromise.setFailure(FixedChannelPool.POOL_CLOSED_ON_ACQUIRE_EXCEPTION);
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
            FixedChannelPool.access$1108(FixedChannelPool.this);
            this.acquired = true;
        }
    }

    @Override // io.netty.channel.pool.SimpleChannelPool, io.netty.channel.pool.ChannelPool, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.executor.execute(new Runnable() { // from class: io.netty.channel.pool.FixedChannelPool.5
            @Override // java.lang.Runnable
            public void run() {
                if (FixedChannelPool.this.closed) {
                    return;
                }
                FixedChannelPool.this.closed = true;
                while (true) {
                    AcquireTask acquireTask = (AcquireTask) FixedChannelPool.this.pendingAcquireQueue.poll();
                    if (acquireTask == null) {
                        FixedChannelPool.this.acquiredChannelCount = 0;
                        FixedChannelPool.this.pendingAcquireCount = 0;
                        FixedChannelPool.super.close();
                        return;
                    } else {
                        ScheduledFuture<?> scheduledFuture = acquireTask.timeoutFuture;
                        if (scheduledFuture != null) {
                            scheduledFuture.cancel(false);
                        }
                        acquireTask.promise.setFailure(new ClosedChannelException());
                    }
                }
            }

            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* renamed from: io.netty.channel.pool.FixedChannelPool$5$1, reason: invalid class name */
            /* loaded from: classes5.dex */
            class AnonymousClass1 implements FutureListener<Void> {
                AnonymousClass1() {
                }

                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<Void> future) throws Exception {
                    if (future.isSuccess()) {
                        RunnableC70575.this.val$closeComplete.setSuccess(null);
                    } else {
                        RunnableC70575.this.val$closeComplete.setFailure(future.cause());
                    }
                }
            }
        });
    }
}
