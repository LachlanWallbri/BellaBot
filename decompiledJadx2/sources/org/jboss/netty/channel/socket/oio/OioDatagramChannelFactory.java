package org.jboss.netty.channel.socket.oio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.jboss.netty.channel.socket.DatagramChannelFactory;
import org.jboss.netty.util.internal.ExecutorUtil;

/* loaded from: classes7.dex */
public class OioDatagramChannelFactory implements DatagramChannelFactory {
    final OioDatagramPipelineSink sink;
    private final Executor workerExecutor;

    public OioDatagramChannelFactory() {
        this(Executors.newCachedThreadPool());
    }

    public OioDatagramChannelFactory(Executor executor) {
        if (executor == null) {
            throw new NullPointerException("workerExecutor");
        }
        this.workerExecutor = executor;
        this.sink = new OioDatagramPipelineSink(executor);
    }

    @Override // org.jboss.netty.channel.ChannelFactory
    public DatagramChannel newChannel(ChannelPipeline channelPipeline) {
        return new OioDatagramChannel(this, channelPipeline, this.sink);
    }

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        ExecutorUtil.terminate(this.workerExecutor);
    }
}
