package org.jboss.netty.channel.socket.oio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.socket.ServerSocketChannel;
import org.jboss.netty.channel.socket.ServerSocketChannelFactory;
import org.jboss.netty.util.internal.ExecutorUtil;

/* loaded from: classes7.dex */
public class OioServerSocketChannelFactory implements ServerSocketChannelFactory {
    final Executor bossExecutor;
    private final ChannelSink sink;
    private final Executor workerExecutor;

    public OioServerSocketChannelFactory() {
        this(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
    }

    public OioServerSocketChannelFactory(Executor executor, Executor executor2) {
        if (executor == null) {
            throw new NullPointerException("bossExecutor");
        }
        if (executor2 == null) {
            throw new NullPointerException("workerExecutor");
        }
        this.bossExecutor = executor;
        this.workerExecutor = executor2;
        this.sink = new OioServerSocketPipelineSink(executor2);
    }

    @Override // org.jboss.netty.channel.ServerChannelFactory, org.jboss.netty.channel.ChannelFactory
    public ServerSocketChannel newChannel(ChannelPipeline channelPipeline) {
        return new OioServerSocketChannel(this, channelPipeline, this.sink);
    }

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        ExecutorUtil.terminate(this.bossExecutor, this.workerExecutor);
    }
}
