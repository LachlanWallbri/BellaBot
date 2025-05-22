package org.jboss.netty.handler.execution;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.util.ObjectSizeEstimator;
import org.jboss.netty.util.internal.ConcurrentIdentityWeakKeyHashMap;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
public class OrderedMemoryAwareThreadPoolExecutor extends MemoryAwareThreadPoolExecutor {
    protected final ConcurrentMap<Object, Executor> childExecutors;

    public OrderedMemoryAwareThreadPoolExecutor(int i, long j, long j2) {
        super(i, j, j2);
        this.childExecutors = newChildExecutorMap();
    }

    public OrderedMemoryAwareThreadPoolExecutor(int i, long j, long j2, long j3, TimeUnit timeUnit) {
        super(i, j, j2, j3, timeUnit);
        this.childExecutors = newChildExecutorMap();
    }

    public OrderedMemoryAwareThreadPoolExecutor(int i, long j, long j2, long j3, TimeUnit timeUnit, ThreadFactory threadFactory) {
        super(i, j, j2, j3, timeUnit, threadFactory);
        this.childExecutors = newChildExecutorMap();
    }

    public OrderedMemoryAwareThreadPoolExecutor(int i, long j, long j2, long j3, TimeUnit timeUnit, ObjectSizeEstimator objectSizeEstimator, ThreadFactory threadFactory) {
        super(i, j, j2, j3, timeUnit, objectSizeEstimator, threadFactory);
        this.childExecutors = newChildExecutorMap();
    }

    protected ConcurrentMap<Object, Executor> newChildExecutorMap() {
        return new ConcurrentIdentityWeakKeyHashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getChildExecutorKey(ChannelEvent channelEvent) {
        return channelEvent.getChannel();
    }

    protected Set<Object> getChildExecutorKeySet() {
        return this.childExecutors.keySet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean removeChildExecutor(Object obj) {
        return this.childExecutors.remove(obj) != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public void doExecute(Runnable runnable) {
        if (!(runnable instanceof ChannelEventRunnable)) {
            doUnorderedExecute(runnable);
        } else {
            getChildExecutor(((ChannelEventRunnable) runnable).getEvent()).execute(runnable);
        }
    }

    protected Executor getChildExecutor(ChannelEvent channelEvent) {
        Executor putIfAbsent;
        Object childExecutorKey = getChildExecutorKey(channelEvent);
        Executor executor = this.childExecutors.get(childExecutorKey);
        if (executor == null && (putIfAbsent = this.childExecutors.putIfAbsent(childExecutorKey, (executor = new ChildExecutor()))) != null) {
            executor = putIfAbsent;
        }
        if (channelEvent instanceof ChannelStateEvent) {
            Channel channel = channelEvent.getChannel();
            if (((ChannelStateEvent) channelEvent).getState() == ChannelState.OPEN && !channel.isOpen()) {
                removeChildExecutor(childExecutorKey);
            }
        }
        return executor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor
    public boolean shouldCount(Runnable runnable) {
        if (runnable instanceof ChildExecutor) {
            return false;
        }
        return super.shouldCount(runnable);
    }

    void onAfterExecute(Runnable runnable, Throwable th) {
        afterExecute(runnable, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes7.dex */
    public final class ChildExecutor implements Executor, Runnable {
        private final Queue<Runnable> tasks = QueueFactory.createQueue(Runnable.class);
        private final AtomicBoolean isRunning = new AtomicBoolean();

        /* JADX INFO: Access modifiers changed from: protected */
        public ChildExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.tasks.add(runnable);
            if (this.isRunning.get()) {
                return;
            }
            OrderedMemoryAwareThreadPoolExecutor.this.doUnorderedExecute(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = true;
            if (this.isRunning.compareAndSet(false, true)) {
                try {
                    Thread currentThread = Thread.currentThread();
                    while (true) {
                        Runnable poll = this.tasks.poll();
                        if (poll == null) {
                            break;
                        }
                        OrderedMemoryAwareThreadPoolExecutor.this.beforeExecute(currentThread, poll);
                        try {
                            poll.run();
                        } catch (RuntimeException e) {
                            e = e;
                            z = false;
                        }
                        try {
                            OrderedMemoryAwareThreadPoolExecutor.this.onAfterExecute(poll, null);
                        } catch (RuntimeException e2) {
                            e = e2;
                            if (!z) {
                                OrderedMemoryAwareThreadPoolExecutor.this.onAfterExecute(poll, e);
                            }
                            throw e;
                        }
                    }
                    this.isRunning.set(false);
                    if (this.isRunning.get() || this.tasks.peek() == null) {
                        return;
                    }
                    OrderedMemoryAwareThreadPoolExecutor.this.doUnorderedExecute(this);
                } catch (Throwable th) {
                    this.isRunning.set(false);
                    throw th;
                }
            }
        }
    }
}
