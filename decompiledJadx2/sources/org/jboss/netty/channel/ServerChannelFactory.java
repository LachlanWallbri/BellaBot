package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public interface ServerChannelFactory extends ChannelFactory {
    @Override // org.jboss.netty.channel.ChannelFactory
    ServerChannel newChannel(ChannelPipeline channelPipeline);
}
