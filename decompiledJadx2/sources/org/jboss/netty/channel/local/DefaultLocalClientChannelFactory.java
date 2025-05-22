package org.jboss.netty.channel.local;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;

/* loaded from: classes7.dex */
public class DefaultLocalClientChannelFactory implements LocalClientChannelFactory {
    private final ChannelSink sink = new LocalClientChannelSink();

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
    }

    @Override // org.jboss.netty.channel.ChannelFactory
    public LocalChannel newChannel(ChannelPipeline channelPipeline) {
        return new DefaultLocalChannel(null, this, channelPipeline, this.sink, null);
    }
}
