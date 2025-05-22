package org.jboss.netty.handler.execution;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.jboss.netty.util.ObjectSizeEstimator;

/* loaded from: classes7.dex */
public final class OrderedDownstreamThreadPoolExecutor extends OrderedMemoryAwareThreadPoolExecutor {
    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public long getMaxChannelMemorySize() {
        return 0L;
    }

    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public long getMaxTotalMemorySize() {
        return 0L;
    }

    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public ObjectSizeEstimator getObjectSizeEstimator() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor, org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public boolean shouldCount(Runnable runnable) {
        return false;
    }

    public OrderedDownstreamThreadPoolExecutor(int i) {
        super(i, 0L, 0L);
    }

    public OrderedDownstreamThreadPoolExecutor(int i, long j, TimeUnit timeUnit) {
        super(i, 0L, 0L, j, timeUnit);
    }

    public OrderedDownstreamThreadPoolExecutor(int i, long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        super(i, 0L, 0L, j, timeUnit, threadFactory);
    }

    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public void setObjectSizeEstimator(ObjectSizeEstimator objectSizeEstimator) {
        throw new UnsupportedOperationException("Not supported by this implementation");
    }

    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public void setMaxChannelMemorySize(long j) {
        throw new UnsupportedOperationException("Not supported by this implementation");
    }

    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public void setMaxTotalMemorySize(long j) {
        throw new UnsupportedOperationException("Not supported by this implementation");
    }

    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor, java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable instanceof ChannelUpstreamEventRunnable) {
            throw new RejectedExecutionException("command must be enclosed with an downstream event.");
        }
        doExecute(runnable);
    }

    @Override // org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor
    protected Executor getChildExecutor(ChannelEvent channelEvent) {
        final Object childExecutorKey = getChildExecutorKey(channelEvent);
        Executor executor = this.childExecutors.get(childExecutorKey);
        if (executor != null) {
            return executor;
        }
        OrderedMemoryAwareThreadPoolExecutor.ChildExecutor childExecutor = new OrderedMemoryAwareThreadPoolExecutor.ChildExecutor();
        Executor putIfAbsent = this.childExecutors.putIfAbsent(childExecutorKey, childExecutor);
        if (putIfAbsent != null) {
            return putIfAbsent;
        }
        channelEvent.getChannel().getCloseFuture().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.execution.OrderedDownstreamThreadPoolExecutor.1
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                OrderedDownstreamThreadPoolExecutor.this.removeChildExecutor(childExecutorKey);
            }
        });
        return childExecutor;
    }
}
