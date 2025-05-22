package org.jboss.netty.channel.socket.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;

/* loaded from: classes7.dex */
public class NioSocketChannel extends AbstractNioChannel<SocketChannel> implements org.jboss.netty.channel.socket.SocketChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ST_BOUND = 1;
    private static final int ST_CLOSED = -1;
    private static final int ST_CONNECTED = 2;
    private static final int ST_OPEN = 0;
    private final NioSocketChannelConfig config;
    volatile int state;

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel, org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public /* bridge */ /* synthetic */ int getInterestOps() {
        return super.getInterestOps();
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel, org.jboss.netty.channel.Channel
    public /* bridge */ /* synthetic */ InetSocketAddress getLocalAddress() {
        return super.getLocalAddress();
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel, org.jboss.netty.channel.Channel
    public /* bridge */ /* synthetic */ InetSocketAddress getRemoteAddress() {
        return super.getRemoteAddress();
    }

    public NioSocketChannel(Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink, SocketChannel socketChannel, NioWorker nioWorker) {
        super(channel, channelFactory, channelPipeline, channelSink, nioWorker, socketChannel);
        this.state = 0;
        this.config = new DefaultNioSocketChannelConfig(socketChannel.socket());
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel
    public NioWorker getWorker() {
        return (NioWorker) super.getWorker();
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel, org.jboss.netty.channel.Channel
    public NioSocketChannelConfig getConfig() {
        return this.config;
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public boolean isOpen() {
        return this.state >= 0;
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isBound() {
        return this.state >= 1;
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isConnected() {
        return this.state == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setBound() {
        this.state = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setConnected() {
        if (this.state != -1) {
            this.state = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel, org.jboss.netty.channel.AbstractChannel
    public boolean setClosed() {
        this.state = -1;
        return super.setClosed();
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel
    InetSocketAddress getLocalSocketAddress() throws Exception {
        return (InetSocketAddress) ((SocketChannel) this.channel).socket().getLocalSocketAddress();
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel
    InetSocketAddress getRemoteSocketAddress() throws Exception {
        return (InetSocketAddress) ((SocketChannel) this.channel).socket().getRemoteSocketAddress();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj, SocketAddress socketAddress) {
        if (socketAddress == null || socketAddress.equals(getRemoteAddress())) {
            return super.write(obj, null);
        }
        return getUnsupportedOperationFuture();
    }
}
