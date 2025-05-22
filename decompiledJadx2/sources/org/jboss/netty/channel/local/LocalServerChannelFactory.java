package org.jboss.netty.channel.local;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ServerChannelFactory;

/* loaded from: classes7.dex */
public interface LocalServerChannelFactory extends ServerChannelFactory {
    @Override // org.jboss.netty.channel.ServerChannelFactory, org.jboss.netty.channel.ChannelFactory
    LocalServerChannel newChannel(ChannelPipeline channelPipeline);
}
