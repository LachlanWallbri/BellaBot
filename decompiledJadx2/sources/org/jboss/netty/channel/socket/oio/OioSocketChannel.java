package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.socket.DefaultSocketChannelConfig;
import org.jboss.netty.channel.socket.SocketChannel;
import org.jboss.netty.channel.socket.SocketChannelConfig;

/* loaded from: classes7.dex */
abstract class OioSocketChannel extends AbstractOioChannel implements SocketChannel {
    private final SocketChannelConfig config;
    final Socket socket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract PushbackInputStream getInputStream();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract OutputStream getOutputStream();

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioSocketChannel(Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink, Socket socket) {
        super(channel, channelFactory, channelPipeline, channelSink);
        this.socket = socket;
        this.config = new DefaultSocketChannelConfig(socket);
    }

    @Override // org.jboss.netty.channel.Channel
    public SocketChannelConfig getConfig() {
        return this.config;
    }

    @Override // org.jboss.netty.channel.socket.oio.AbstractOioChannel
    boolean isSocketBound() {
        return this.socket.isBound();
    }

    @Override // org.jboss.netty.channel.socket.oio.AbstractOioChannel
    boolean isSocketConnected() {
        return this.socket.isConnected();
    }

    @Override // org.jboss.netty.channel.socket.oio.AbstractOioChannel
    InetSocketAddress getLocalSocketAddress() throws Exception {
        return (InetSocketAddress) this.socket.getLocalSocketAddress();
    }

    @Override // org.jboss.netty.channel.socket.oio.AbstractOioChannel
    InetSocketAddress getRemoteSocketAddress() throws Exception {
        return (InetSocketAddress) this.socket.getRemoteSocketAddress();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jboss.netty.channel.socket.oio.AbstractOioChannel
    public void closeSocket() throws IOException {
        this.socket.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jboss.netty.channel.socket.oio.AbstractOioChannel
    public boolean isSocketClosed() {
        return this.socket.isClosed();
    }
}
