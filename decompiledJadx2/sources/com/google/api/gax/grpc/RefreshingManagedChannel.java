package com.google.api.gax.grpc;

import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class RefreshingManagedChannel extends ManagedChannel {
    private static final double jitterPercentage = 0.15d;
    private final ChannelFactory channelFactory;
    private volatile SafeShutdownManagedChannel delegate;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private volatile ScheduledFuture<?> nextScheduledRefresh = scheduleNextRefresh();
    private final ScheduledExecutorService scheduledExecutorService;
    private static final Logger LOG = Logger.getLogger(RefreshingManagedChannel.class.getName());
    private static final Duration refreshPeriod = Duration.ofMinutes(50);

    /* JADX INFO: Access modifiers changed from: package-private */
    public RefreshingManagedChannel(ChannelFactory channelFactory, ScheduledExecutorService scheduledExecutorService) throws IOException {
        this.delegate = new SafeShutdownManagedChannel(channelFactory.createSingleChannel());
        this.channelFactory = channelFactory;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshChannel() {
        try {
            SafeShutdownManagedChannel safeShutdownManagedChannel = new SafeShutdownManagedChannel(this.channelFactory.createSingleChannel());
            SafeShutdownManagedChannel safeShutdownManagedChannel2 = this.delegate;
            this.lock.writeLock().lock();
            try {
                if (Thread.currentThread().isInterrupted()) {
                    safeShutdownManagedChannel.shutdownNow();
                    return;
                }
                this.delegate = safeShutdownManagedChannel;
                this.nextScheduledRefresh = scheduleNextRefresh();
                this.lock.writeLock().unlock();
                safeShutdownManagedChannel2.shutdownSafely();
            } finally {
                this.lock.writeLock().unlock();
            }
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Failed to create a new channel when refreshing channel. This has no effect on the existing channels. The existing channel will continue to be used", (Throwable) e);
        }
    }

    private ScheduledFuture<?> scheduleNextRefresh() {
        long millis = refreshPeriod.toMillis();
        return this.scheduledExecutorService.schedule(new Runnable() { // from class: com.google.api.gax.grpc.RefreshingManagedChannel.1
            @Override // java.lang.Runnable
            public void run() {
                RefreshingManagedChannel.this.refreshChannel();
            }
        }, ((long) ((Math.random() - 0.5d) * jitterPercentage * millis)) + millis, TimeUnit.MILLISECONDS);
    }

    @Override // io.grpc.Channel
    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        this.lock.readLock().lock();
        try {
            return this.delegate.newCall(methodDescriptor, callOptions);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // io.grpc.Channel
    public String authority() {
        return this.delegate.authority();
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannel shutdown() {
        this.lock.readLock().lock();
        try {
            this.nextScheduledRefresh.cancel(true);
            this.delegate.shutdown();
            return this;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannel shutdownNow() {
        this.lock.readLock().lock();
        try {
            this.nextScheduledRefresh.cancel(true);
            this.delegate.shutdownNow();
            return this;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // io.grpc.ManagedChannel
    public boolean isShutdown() {
        this.lock.readLock().lock();
        try {
            return this.delegate.isShutdown();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // io.grpc.ManagedChannel
    public boolean isTerminated() {
        this.lock.readLock().lock();
        try {
            return this.delegate.isTerminated();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // io.grpc.ManagedChannel
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        this.lock.readLock().lock();
        try {
            return this.delegate.awaitTermination(j, timeUnit);
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
