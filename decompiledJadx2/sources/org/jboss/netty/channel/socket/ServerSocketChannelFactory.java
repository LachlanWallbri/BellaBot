package org.jboss.netty.channel.socket;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ServerChannelFactory;

/* loaded from: classes7.dex */
public interface ServerSocketChannelFactory extends ServerChannelFactory {
    @Override // org.jboss.netty.channel.ServerChannelFactory, org.jboss.netty.channel.ChannelFactory
    ServerSocketChannel newChannel(ChannelPipeline channelPipeline);
}
