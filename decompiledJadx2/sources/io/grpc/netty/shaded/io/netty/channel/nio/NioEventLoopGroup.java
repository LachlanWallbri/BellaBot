package io.grpc.netty.shaded.io.netty.channel.nio;

import io.grpc.netty.shaded.io.netty.channel.DefaultSelectStrategyFactory;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.EventLoopTaskQueueFactory;
import io.grpc.netty.shaded.io.netty.channel.MultithreadEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.SelectStrategyFactory;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorChooserFactory;
import io.grpc.netty.shaded.io.netty.util.concurrent.RejectedExecutionHandler;
import io.grpc.netty.shaded.io.netty.util.concurrent.RejectedExecutionHandlers;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NioEventLoopGroup extends MultithreadEventLoopGroup {
    public NioEventLoopGroup() {
        this(0);
    }

    public NioEventLoopGroup(int i) {
        this(i, (Executor) null);
    }

    public NioEventLoopGroup(ThreadFactory threadFactory) {
        this(0, threadFactory, SelectorProvider.provider());
    }

    public NioEventLoopGroup(int i, ThreadFactory threadFactory) {
        this(i, threadFactory, SelectorProvider.provider());
    }

    public NioEventLoopGroup(int i, Executor executor) {
        this(i, executor, SelectorProvider.provider());
    }

    public NioEventLoopGroup(int i, ThreadFactory threadFactory, SelectorProvider selectorProvider) {
        this(i, threadFactory, selectorProvider, DefaultSelectStrategyFactory.INSTANCE);
    }

    public NioEventLoopGroup(int i, ThreadFactory threadFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        super(i, threadFactory, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());
    }

    public NioEventLoopGroup(int i, Executor executor, SelectorProvider selectorProvider) {
        this(i, executor, selectorProvider, DefaultSelectStrategyFactory.INSTANCE);
    }

    public NioEventLoopGroup(int i, Executor executor, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        super(i, executor, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());
    }

    public NioEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        super(i, executor, eventExecutorChooserFactory, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());
    }

    public NioEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, executor, eventExecutorChooserFactory, selectorProvider, selectStrategyFactory, rejectedExecutionHandler);
    }

    public NioEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        super(i, executor, eventExecutorChooserFactory, selectorProvider, selectStrategyFactory, rejectedExecutionHandler, eventLoopTaskQueueFactory);
    }

    public void setIoRatio(int i) {
        Iterator<EventExecutor> it = iterator();
        while (it.hasNext()) {
            ((NioEventLoop) it.next()).setIoRatio(i);
        }
    }

    public void rebuildSelectors() {
        Iterator<EventExecutor> it = iterator();
        while (it.hasNext()) {
            ((NioEventLoop) it.next()).rebuildSelector();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.MultithreadEventLoopGroup, io.grpc.netty.shaded.io.netty.util.concurrent.MultithreadEventExecutorGroup
    public EventLoop newChild(Executor executor, Object... objArr) throws Exception {
        return new NioEventLoop(this, executor, (SelectorProvider) objArr[0], ((SelectStrategyFactory) objArr[1]).newSelectStrategy(), (RejectedExecutionHandler) objArr[2], objArr.length == 4 ? (EventLoopTaskQueueFactory) objArr[3] : null);
    }
}
