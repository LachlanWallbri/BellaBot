package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.jboss.netty.channel.socket.DatagramChannelConfig;
import org.jboss.netty.channel.socket.DefaultDatagramChannelConfig;

/* loaded from: classes7.dex */
final class OioDatagramChannel extends AbstractOioChannel implements DatagramChannel {
    private final DatagramChannelConfig config;
    final MulticastSocket socket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioDatagramChannel(ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        super(null, channelFactory, channelPipeline, channelSink);
        try {
            this.socket = new MulticastSocket((SocketAddress) null);
            try {
                this.socket.setSoTimeout(10);
                this.socket.setBroadcast(false);
                this.config = new DefaultDatagramChannelConfig(this.socket);
                Channels.fireChannelOpen(this);
            } catch (SocketException e) {
                throw new ChannelException("Failed to configure the datagram socket timeout.", e);
            }
        } catch (IOException e2) {
            throw new ChannelException("Failed to open a datagram socket.", e2);
        }
    }

    @Override // org.jboss.netty.channel.Channel
    public DatagramChannelConfig getConfig() {
        return this.config;
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress) {
        ensureBound();
        try {
            this.socket.joinGroup(inetAddress);
            return Channels.succeededFuture(this);
        } catch (IOException e) {
            return Channels.failedFuture(this, e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        ensureBound();
        try {
            this.socket.joinGroup(inetSocketAddress, networkInterface);
            return Channels.succeededFuture(this);
        } catch (IOException e) {
            return Channels.failedFuture(this, e);
        }
    }

    private void ensureBound() {
        if (isBound()) {
            return;
        }
        throw new IllegalStateException(DatagramChannel.class.getName() + " must be bound to join a group.");
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        try {
            this.socket.leaveGroup(inetAddress);
            return Channels.succeededFuture(this);
        } catch (IOException e) {
            return Channels.failedFuture(this, e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        try {
            this.socket.leaveGroup(inetSocketAddress, networkInterface);
            return Channels.succeededFuture(this);
        } catch (IOException e) {
            return Channels.failedFuture(this, e);
        }
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
