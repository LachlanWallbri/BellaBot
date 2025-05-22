package org.jboss.netty.channel.local;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.group.DefaultChannelGroup;

/* loaded from: classes7.dex */
public class DefaultLocalServerChannelFactory implements LocalServerChannelFactory {
    private final DefaultChannelGroup group = new DefaultChannelGroup();
    private final ChannelSink sink = new LocalServerChannelSink();

    @Override // org.jboss.netty.channel.ServerChannelFactory, org.jboss.netty.channel.ChannelFactory
    public LocalServerChannel newChannel(ChannelPipeline channelPipeline) {
        DefaultLocalServerChannel defaultLocalServerChannel = new DefaultLocalServerChannel(this, channelPipeline, this.sink);
        this.group.add((Channel) defaultLocalServerChannel);
        return defaultLocalServerChannel;
    }

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        this.group.close().awaitUninterruptibly();
    }
}
