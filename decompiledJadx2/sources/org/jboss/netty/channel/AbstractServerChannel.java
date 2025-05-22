package org.jboss.netty.channel;

import java.net.SocketAddress;

/* loaded from: classes7.dex */
public abstract class AbstractServerChannel extends AbstractChannel implements ServerChannel {
    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public int getInterestOps() {
        return 0;
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isConnected() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.AbstractChannel
    public void setInterestOpsNow(int i) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractServerChannel(ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        super(null, channelFactory, channelPipeline, channelSink);
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture connect(SocketAddress socketAddress) {
        return getUnsupportedOperationFuture();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture disconnect() {
        return getUnsupportedOperationFuture();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture setInterestOps(int i) {
        return getUnsupportedOperationFuture();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj) {
        return getUnsupportedOperationFuture();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj, SocketAddress socketAddress) {
        return getUnsupportedOperationFuture();
    }
}
