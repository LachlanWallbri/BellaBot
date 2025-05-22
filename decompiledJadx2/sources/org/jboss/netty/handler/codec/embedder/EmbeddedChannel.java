package org.jboss.netty.handler.codec.embedder;

import java.net.SocketAddress;
import org.jboss.netty.channel.AbstractChannel;
import org.jboss.netty.channel.ChannelConfig;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.DefaultChannelConfig;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class EmbeddedChannel extends AbstractChannel {
    private static final Integer DUMMY_ID = 0;
    private final ChannelConfig config;
    private final SocketAddress localAddress;
    private final SocketAddress remoteAddress;

    @Override // org.jboss.netty.channel.Channel
    public boolean isBound() {
        return true;
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isConnected() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmbeddedChannel(ChannelPipeline channelPipeline, ChannelSink channelSink) {
        super(DUMMY_ID, null, EmbeddedChannelFactory.INSTANCE, channelPipeline, channelSink);
        this.localAddress = new EmbeddedSocketAddress();
        this.remoteAddress = new EmbeddedSocketAddress();
        this.config = new DefaultChannelConfig();
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelConfig getConfig() {
        return this.config;
    }

    @Override // org.jboss.netty.channel.Channel
    public SocketAddress getLocalAddress() {
        return this.localAddress;
    }

    @Override // org.jboss.netty.channel.Channel
    public SocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }
}
