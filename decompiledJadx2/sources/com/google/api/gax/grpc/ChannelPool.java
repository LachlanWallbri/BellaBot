package com.google.api.gax.grpc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class ChannelPool extends ManagedChannel {
    private static final int CHANNEL_REFRESH_EXECUTOR_SIZE = 2;
    private final String authority;

    @Nullable
    private ScheduledExecutorService channelRefreshExecutorService;
    private final ImmutableList<ManagedChannel> channels;
    private final AtomicInteger indexTicker = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChannelPool create(int i, ChannelFactory channelFactory) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(channelFactory.createSingleChannel());
        }
        return new ChannelPool(arrayList, null);
    }

    static ChannelPool createRefreshing(int i, ChannelFactory channelFactory, ScheduledExecutorService scheduledExecutorService) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(new RefreshingManagedChannel(channelFactory, scheduledExecutorService));
        }
        return new ChannelPool(arrayList, scheduledExecutorService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChannelPool createRefreshing(int i, ChannelFactory channelFactory) throws IOException {
        return createRefreshing(i, channelFactory, Executors.newScheduledThreadPool(2));
    }

    private ChannelPool(List<ManagedChannel> list, @Nullable ScheduledExecutorService scheduledExecutorService) {
        this.channels = ImmutableList.copyOf((Collection) list);
        this.authority = list.get(0).authority();
        this.channelRefreshExecutorService = scheduledExecutorService;
    }

    @Override // io.grpc.Channel
    public String authority() {
        return this.authority;
    }

    @Override // io.grpc.Channel
    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return getNextChannel().newCall(methodDescriptor, callOptions);
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannel shutdown() {
        UnmodifiableIterator<ManagedChannel> it = this.channels.iterator();
        while (it.hasNext()) {
            it.next().shutdown();
        }
        ScheduledExecutorService scheduledExecutorService = this.channelRefreshExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        return this;
    }

    @Override // io.grpc.ManagedChannel
    public boolean isShutdown() {
        UnmodifiableIterator<ManagedChannel> it = this.channels.iterator();
        while (it.hasNext()) {
            if (!it.next().isShutdown()) {
                return false;
            }
        }
        ScheduledExecutorService scheduledExecutorService = this.channelRefreshExecutorService;
        return scheduledExecutorService == null || scheduledExecutorService.isShutdown();
    }

    @Override // io.grpc.ManagedChannel
    public boolean isTerminated() {
        UnmodifiableIterator<ManagedChannel> it = this.channels.iterator();
        while (it.hasNext()) {
            if (!it.next().isTerminated()) {
                return false;
            }
        }
        ScheduledExecutorService scheduledExecutorService = this.channelRefreshExecutorService;
        return scheduledExecutorService == null || scheduledExecutorService.isTerminated();
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannel shutdownNow() {
        UnmodifiableIterator<ManagedChannel> it = this.channels.iterator();
        while (it.hasNext()) {
            it.next().shutdownNow();
        }
        ScheduledExecutorService scheduledExecutorService = this.channelRefreshExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        return this;
    }

    @Override // io.grpc.ManagedChannel
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanoTime = System.nanoTime() + timeUnit.toNanos(j);
        UnmodifiableIterator<ManagedChannel> it = this.channels.iterator();
        while (it.hasNext()) {
            ManagedChannel next = it.next();
            long nanoTime2 = nanoTime - System.nanoTime();
            if (nanoTime2 <= 0) {
                break;
            }
            next.awaitTermination(nanoTime2, TimeUnit.NANOSECONDS);
        }
        if (this.channelRefreshExecutorService != null) {
            this.channelRefreshExecutorService.awaitTermination(nanoTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }
        return isTerminated();
    }

    private ManagedChannel getNextChannel() {
        return getChannel(this.indexTicker.getAndIncrement());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ManagedChannel getChannel(int i) {
        int abs = Math.abs(i % this.channels.size());
        if (abs < 0) {
            abs = 0;
        }
        return this.channels.get(abs);
    }
}
