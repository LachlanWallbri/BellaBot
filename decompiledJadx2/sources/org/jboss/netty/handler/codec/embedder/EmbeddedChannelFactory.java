package org.jboss.netty.handler.codec.embedder;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;

/* loaded from: classes7.dex */
final class EmbeddedChannelFactory implements ChannelFactory {
    static final ChannelFactory INSTANCE = new EmbeddedChannelFactory();

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
    }

    private EmbeddedChannelFactory() {
    }

    @Override // org.jboss.netty.channel.ChannelFactory
    public Channel newChannel(ChannelPipeline channelPipeline) {
        throw new UnsupportedOperationException();
    }
}
