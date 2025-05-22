package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.jboss.netty.channel.AbstractServerChannel;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.DefaultServerSocketChannelConfig;
import org.jboss.netty.channel.socket.ServerSocketChannel;
import org.jboss.netty.channel.socket.ServerSocketChannelConfig;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
class OioServerSocketChannel extends AbstractServerChannel implements ServerSocketChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OioServerSocketChannel.class);
    private final ServerSocketChannelConfig config;
    final Lock shutdownLock;
    final ServerSocket socket;

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getRemoteAddress() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioServerSocketChannel(ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        super(channelFactory, channelPipeline, channelSink);
        this.shutdownLock = new ReentrantLock();
        try {
            this.socket = new ServerSocket();
            try {
                this.socket.setSoTimeout(1000);
                this.config = new DefaultServerSocketChannelConfig(this.socket);
                Channels.fireChannelOpen(this);
            } catch (IOException e) {
                try {
                    this.socket.close();
                } catch (IOException e2) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Failed to close a partially initialized socket.", e2);
                    }
                }
                throw new ChannelException("Failed to set the server socket timeout.", e);
            }
        } catch (IOException e3) {
            throw new ChannelException("Failed to open a server socket.", e3);
        }
    }

    @Override // org.jboss.netty.channel.Channel
    public ServerSocketChannelConfig getConfig() {
        return this.config;
    }

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getLocalAddress() {
        return (InetSocketAddress) this.socket.getLocalSocketAddress();
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isBound() {
        return isOpen() && this.socket.isBound();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.AbstractChannel
    public boolean setClosed() {
        return super.setClosed();
    }
}
