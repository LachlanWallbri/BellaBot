package org.jboss.netty.channel.local;

import java.util.concurrent.atomic.AtomicBoolean;
import org.jboss.netty.channel.AbstractServerChannel;
import org.jboss.netty.channel.ChannelConfig;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DefaultServerChannelConfig;

/* loaded from: classes7.dex */
final class DefaultLocalServerChannel extends AbstractServerChannel implements LocalServerChannel {
    final AtomicBoolean bound;
    final ChannelConfig channelConfig;
    volatile LocalAddress localAddress;

    @Override // org.jboss.netty.channel.Channel
    public LocalAddress getRemoteAddress() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultLocalServerChannel(ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        super(channelFactory, channelPipeline, channelSink);
        this.bound = new AtomicBoolean();
        this.channelConfig = new DefaultServerChannelConfig();
        Channels.fireChannelOpen(this);
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelConfig getConfig() {
        return this.channelConfig;
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isBound() {
        return isOpen() && this.bound.get();
    }

    @Override // org.jboss.netty.channel.Channel
    public LocalAddress getLocalAddress() {
        if (isBound()) {
            return this.localAddress;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.AbstractChannel
    public boolean setClosed() {
        return super.setClosed();
    }
}
