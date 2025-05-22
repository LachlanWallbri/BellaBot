package org.jboss.netty.channel.socket;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;

/* loaded from: classes7.dex */
public interface DatagramChannelFactory extends ChannelFactory {
    @Override // org.jboss.netty.channel.ChannelFactory
    DatagramChannel newChannel(ChannelPipeline channelPipeline);
}
