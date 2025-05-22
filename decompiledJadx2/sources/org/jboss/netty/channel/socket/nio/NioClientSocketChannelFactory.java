package org.jboss.netty.channel.socket.nio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.SocketChannel;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.internal.ExecutorUtil;

/* loaded from: classes7.dex */
public class NioClientSocketChannelFactory implements ClientSocketChannelFactory {
    private static final int DEFAULT_BOSS_COUNT = 1;
    private final Executor bossExecutor;
    private final NioClientSocketPipelineSink sink;
    private final WorkerPool<NioWorker> workerPool;

    public NioClientSocketChannelFactory() {
        this(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
    }

    public NioClientSocketChannelFactory(Executor executor, Executor executor2) {
        this(executor, executor2, 1, SelectorUtil.DEFAULT_IO_THREADS);
    }

    public NioClientSocketChannelFactory(Executor executor, Executor executor2, int i) {
        this(executor, executor2, 1, i);
    }

    public NioClientSocketChannelFactory(Executor executor, Executor executor2, int i, int i2) {
        this(executor, i, new NioWorkerPool(executor2, i2, true));
    }

    public NioClientSocketChannelFactory(Executor executor, int i, WorkerPool<NioWorker> workerPool) {
        if (executor == null) {
            throw new NullPointerException("bossExecutor");
        }
        if (workerPool == null) {
            throw new NullPointerException("workerPool");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("bossCount (" + i + ") must be a positive integer.");
        }
        this.bossExecutor = executor;
        this.workerPool = workerPool;
        this.sink = new NioClientSocketPipelineSink(executor, i, workerPool);
    }

    @Override // org.jboss.netty.channel.ChannelFactory
    public SocketChannel newChannel(ChannelPipeline channelPipeline) {
        NioClientSocketPipelineSink nioClientSocketPipelineSink = this.sink;
        return new NioClientSocketChannel(this, channelPipeline, nioClientSocketPipelineSink, nioClientSocketPipelineSink.nextWorker());
    }

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        ExecutorUtil.terminate(this.bossExecutor);
        WorkerPool<NioWorker> workerPool = this.workerPool;
        if (workerPool instanceof ExternalResourceReleasable) {
            ((ExternalResourceReleasable) workerPool).releaseExternalResources();
        }
    }
}
