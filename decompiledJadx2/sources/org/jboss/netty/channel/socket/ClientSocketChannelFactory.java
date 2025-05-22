package org.jboss.netty.channel.socket;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;

/* loaded from: classes7.dex */
public interface ClientSocketChannelFactory extends ChannelFactory {
    @Override // org.jboss.netty.channel.ChannelFactory
    SocketChannel newChannel(ChannelPipeline channelPipeline);
}
