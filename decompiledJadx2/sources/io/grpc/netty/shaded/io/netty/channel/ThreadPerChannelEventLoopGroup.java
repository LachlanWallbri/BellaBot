package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutorGroup;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GlobalEventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.concurrent.ThreadPerTaskExecutor;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.ReadOnlyIterator;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class ThreadPerChannelEventLoopGroup extends AbstractEventExecutorGroup implements EventLoopGroup {
    final Set<EventLoop> activeChildren;
    private final Object[] childArgs;
    private final FutureListener<Object> childTerminationListener;
    final Executor executor;
    final Queue<EventLoop> idleChildren;
    private final int maxChannels;
    private volatile boolean shuttingDown;
    private final Promise<?> terminationFuture;
    private final ChannelException tooManyChannels;

    protected ThreadPerChannelEventLoopGroup() {
        this(0);
    }

    protected ThreadPerChannelEventLoopGroup(int i) {
        this(i, (ThreadFactory) null, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ThreadPerChannelEventLoopGroup(int i, ThreadFactory threadFactory, Object... objArr) {
        this(i, threadFactory == null ? null : new ThreadPerTaskExecutor(threadFactory), objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ThreadPerChannelEventLoopGroup(int i, Executor executor, Object... objArr) {
        this.activeChildren = Collections.newSetFromMap(PlatformDependent.newConcurrentHashMap());
        this.idleChildren = new ConcurrentLinkedQueue();
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.childTerminationListener = new FutureListener<Object>() { // from class: io.grpc.netty.shaded.io.netty.channel.ThreadPerChannelEventLoopGroup.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Object> future) throws Exception {
                if (ThreadPerChannelEventLoopGroup.this.isTerminated()) {
                    ThreadPerChannelEventLoopGroup.this.terminationFuture.trySuccess(null);
                }
            }
        };
        ObjectUtil.checkPositiveOrZero(i, "maxChannels");
        executor = executor == null ? new ThreadPerTaskExecutor(new DefaultThreadFactory(getClass())) : executor;
        if (objArr == null) {
            this.childArgs = EmptyArrays.EMPTY_OBJECTS;
        } else {
            this.childArgs = (Object[]) objArr.clone();
        }
        this.maxChannels = i;
        this.executor = executor;
        this.tooManyChannels = (ChannelException) ThrowableUtil.unknownStackTrace(ChannelException.newStatic("too many channels (max: " + i + ')', null), ThreadPerChannelEventLoopGroup.class, "nextChild()");
    }

    protected EventLoop newChild(Object... objArr) throws Exception {
        return new ThreadPerChannelEventLoop(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, java.lang.Iterable
    public Iterator<EventExecutor> iterator() {
        return new ReadOnlyIterator(this.activeChildren.iterator());
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public EventLoop next() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        this.shuttingDown = true;
        Iterator<EventLoop> it = this.activeChildren.iterator();
        while (it.hasNext()) {
            it.next().shutdownGracefully(j, j2, timeUnit);
        }
        Iterator<EventLoop> it2 = this.idleChildren.iterator();
        while (it2.hasNext()) {
            it2.next().shutdownGracefully(j, j2, timeUnit);
        }
        if (isTerminated()) {
            this.terminationFuture.trySuccess(null);
        }
        return terminationFuture();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutorGroup, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, java.util.concurrent.ExecutorService
    @Deprecated
    public void shutdown() {
        this.shuttingDown = true;
        Iterator<EventLoop> it = this.activeChildren.iterator();
        while (it.hasNext()) {
            it.next().shutdown();
        }
        Iterator<EventLoop> it2 = this.idleChildren.iterator();
        while (it2.hasNext()) {
            it2.next().shutdown();
        }
        if (isTerminated()) {
            this.terminationFuture.trySuccess(null);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public boolean isShuttingDown() {
        Iterator<EventLoop> it = this.activeChildren.iterator();
        while (it.hasNext()) {
            if (!it.next().isShuttingDown()) {
                return false;
            }
        }
        Iterator<EventLoop> it2 = this.idleChildren.iterator();
        while (it2.hasNext()) {
            if (!it2.next().isShuttingDown()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        Iterator<EventLoop> it = this.activeChildren.iterator();
        while (it.hasNext()) {
            if (!it.next().isShutdown()) {
                return false;
            }
        }
        Iterator<EventLoop> it2 = this.idleChildren.iterator();
        while (it2.hasNext()) {
            if (!it2.next().isShutdown()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        Iterator<EventLoop> it = this.activeChildren.iterator();
        while (it.hasNext()) {
            if (!it.next().isTerminated()) {
                return false;
            }
        }
        Iterator<EventLoop> it2 = this.idleChildren.iterator();
        while (it2.hasNext()) {
            if (!it2.next().isTerminated()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanoTime;
        long nanoTime2;
        long nanoTime3 = System.nanoTime() + timeUnit.toNanos(j);
        for (EventLoop eventLoop : this.activeChildren) {
            do {
                nanoTime2 = nanoTime3 - System.nanoTime();
                if (nanoTime2 <= 0) {
                    return isTerminated();
                }
            } while (!eventLoop.awaitTermination(nanoTime2, TimeUnit.NANOSECONDS));
        }
        for (EventLoop eventLoop2 : this.idleChildren) {
            do {
                nanoTime = nanoTime3 - System.nanoTime();
                if (nanoTime <= 0) {
                    return isTerminated();
                }
            } while (!eventLoop2.awaitTermination(nanoTime, TimeUnit.NANOSECONDS));
        }
        return isTerminated();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public ChannelFuture register(Channel channel) {
        ObjectUtil.checkNotNull(channel, "channel");
        try {
            EventLoop nextChild = nextChild();
            return nextChild.register(new DefaultChannelPromise(channel, nextChild));
        } catch (Throwable th) {
            return new FailedChannelFuture(channel, GlobalEventExecutor.INSTANCE, th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public ChannelFuture register(ChannelPromise channelPromise) {
        try {
            return nextChild().register(channelPromise);
        } catch (Throwable th) {
            channelPromise.setFailure(th);
            return channelPromise;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    @Deprecated
    public ChannelFuture register(Channel channel, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channel, "channel");
        try {
            return nextChild().register(channel, channelPromise);
        } catch (Throwable th) {
            channelPromise.setFailure(th);
            return channelPromise;
        }
    }

    private EventLoop nextChild() throws Exception {
        if (this.shuttingDown) {
            throw new RejectedExecutionException("shutting down");
        }
        EventLoop poll = this.idleChildren.poll();
        if (poll == null) {
            if (this.maxChannels > 0 && this.activeChildren.size() >= this.maxChannels) {
                throw this.tooManyChannels;
            }
            poll = newChild(this.childArgs);
            poll.terminationFuture().addListener(this.childTerminationListener);
        }
        this.activeChildren.add(poll);
        return poll;
    }
}
