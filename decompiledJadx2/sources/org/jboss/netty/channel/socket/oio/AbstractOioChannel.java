package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.jboss.netty.channel.AbstractChannel;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.socket.Worker;

/* loaded from: classes7.dex */
abstract class AbstractOioChannel extends AbstractChannel {
    final Object interestOpsLock;
    private volatile InetSocketAddress localAddress;
    volatile InetSocketAddress remoteAddress;
    volatile Worker worker;
    volatile Thread workerThread;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void closeSocket() throws IOException;

    abstract InetSocketAddress getLocalSocketAddress() throws Exception;

    abstract InetSocketAddress getRemoteSocketAddress() throws Exception;

    abstract boolean isSocketBound();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isSocketClosed();

    abstract boolean isSocketConnected();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractOioChannel(Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        super(channel, channelFactory, channelPipeline, channelSink);
        this.interestOpsLock = new Object();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.AbstractChannel
    public boolean setClosed() {
        return super.setClosed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.AbstractChannel
    public void setInterestOpsNow(int i) {
        super.setInterestOpsNow(i);
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj, SocketAddress socketAddress) {
        if (socketAddress == null || socketAddress.equals(getRemoteAddress())) {
            return super.write(obj, null);
        }
        return super.write(obj, socketAddress);
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isBound() {
        return isOpen() && isSocketBound();
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isConnected() {
        return isOpen() && isSocketConnected();
    }

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getLocalAddress() {
        InetSocketAddress inetSocketAddress = this.localAddress;
        if (inetSocketAddress != null) {
            return inetSocketAddress;
        }
        try {
            InetSocketAddress localSocketAddress = getLocalSocketAddress();
            this.localAddress = localSocketAddress;
            return localSocketAddress;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getRemoteAddress() {
        InetSocketAddress inetSocketAddress = this.remoteAddress;
        if (inetSocketAddress != null) {
            return inetSocketAddress;
        }
        try {
            InetSocketAddress remoteSocketAddress = getRemoteSocketAddress();
            this.remoteAddress = remoteSocketAddress;
            return remoteSocketAddress;
        } catch (Throwable unused) {
            return null;
        }
    }
}
