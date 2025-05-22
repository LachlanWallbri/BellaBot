package org.jboss.netty.channel.socket.oio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.SocketChannel;
import org.jboss.netty.util.internal.ExecutorUtil;

/* loaded from: classes7.dex */
public class OioClientSocketChannelFactory implements ClientSocketChannelFactory {
    final OioClientSocketPipelineSink sink;
    private final Executor workerExecutor;

    public OioClientSocketChannelFactory() {
        this(Executors.newCachedThreadPool());
    }

    public OioClientSocketChannelFactory(Executor executor) {
        if (executor == null) {
            throw new NullPointerException("workerExecutor");
        }
        this.workerExecutor = executor;
        this.sink = new OioClientSocketPipelineSink(executor);
    }

    @Override // org.jboss.netty.channel.ChannelFactory
    public SocketChannel newChannel(ChannelPipeline channelPipeline) {
        return new OioClientSocketChannel(this, channelPipeline, this.sink);
    }

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        ExecutorUtil.terminate(this.workerExecutor);
    }
}
