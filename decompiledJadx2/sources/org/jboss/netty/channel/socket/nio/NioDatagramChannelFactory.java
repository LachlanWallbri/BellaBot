package org.jboss.netty.channel.socket.nio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.jboss.netty.channel.socket.DatagramChannelFactory;
import org.jboss.netty.channel.socket.InternetProtocolFamily;
import org.jboss.netty.util.ExternalResourceReleasable;

/* loaded from: classes7.dex */
public class NioDatagramChannelFactory implements DatagramChannelFactory {
    private final InternetProtocolFamily family;
    private final NioDatagramPipelineSink sink;
    private final WorkerPool<NioDatagramWorker> workerPool;

    public NioDatagramChannelFactory() {
        this(Executors.newCachedThreadPool(), (InternetProtocolFamily) null);
    }

    public NioDatagramChannelFactory(InternetProtocolFamily internetProtocolFamily) {
        this(Executors.newCachedThreadPool(), internetProtocolFamily);
    }

    public NioDatagramChannelFactory(Executor executor) {
        this(executor, SelectorUtil.DEFAULT_IO_THREADS);
    }

    public NioDatagramChannelFactory(Executor executor, int i) {
        this(new NioDatagramWorkerPool(executor, i, true));
    }

    public NioDatagramChannelFactory(WorkerPool<NioDatagramWorker> workerPool) {
        this(workerPool, (InternetProtocolFamily) null);
    }

    public NioDatagramChannelFactory(Executor executor, InternetProtocolFamily internetProtocolFamily) {
        this(executor, SelectorUtil.DEFAULT_IO_THREADS, internetProtocolFamily);
    }

    public NioDatagramChannelFactory(Executor executor, int i, InternetProtocolFamily internetProtocolFamily) {
        this(new NioDatagramWorkerPool(executor, i, true), internetProtocolFamily);
    }

    public NioDatagramChannelFactory(WorkerPool<NioDatagramWorker> workerPool, InternetProtocolFamily internetProtocolFamily) {
        this.workerPool = workerPool;
        this.family = internetProtocolFamily;
        this.sink = new NioDatagramPipelineSink(workerPool);
    }

    @Override // org.jboss.netty.channel.ChannelFactory
    public DatagramChannel newChannel(ChannelPipeline channelPipeline) {
        NioDatagramPipelineSink nioDatagramPipelineSink = this.sink;
        return new NioDatagramChannel(this, channelPipeline, nioDatagramPipelineSink, nioDatagramPipelineSink.nextWorker(), this.family);
    }

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        WorkerPool<NioDatagramWorker> workerPool = this.workerPool;
        if (workerPool instanceof ExternalResourceReleasable) {
            ((ExternalResourceReleasable) workerPool).releaseExternalResources();
        }
    }
}
