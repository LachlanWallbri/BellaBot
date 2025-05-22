package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.unix.DomainSocketAddress;
import io.grpc.netty.shaded.io.netty.channel.unix.ServerDomainSocketChannel;
import io.grpc.netty.shaded.io.netty.channel.unix.Socket;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class EpollServerDomainSocketChannel extends AbstractEpollServerChannel implements ServerDomainSocketChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) EpollServerDomainSocketChannel.class);
    private final EpollServerChannelConfig config;
    private volatile DomainSocketAddress local;

    public EpollServerDomainSocketChannel() {
        super(LinuxSocket.newSocketDomain(), false);
        this.config = new EpollServerChannelConfig(this);
    }

    public EpollServerDomainSocketChannel(int i) {
        super(i);
        this.config = new EpollServerChannelConfig(this);
    }

    EpollServerDomainSocketChannel(LinuxSocket linuxSocket) {
        super(linuxSocket);
        this.config = new EpollServerChannelConfig(this);
    }

    EpollServerDomainSocketChannel(LinuxSocket linuxSocket, boolean z) {
        super(linuxSocket, z);
        this.config = new EpollServerChannelConfig(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollServerChannel
    protected Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception {
        return new EpollDomainSocketChannel(this, new Socket(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public DomainSocketAddress localAddress0() {
        return this.local;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doBind(SocketAddress socketAddress) throws Exception {
        this.socket.bind(socketAddress);
        this.socket.listen(this.config.getBacklog());
        this.local = (DomainSocketAddress) socketAddress;
        this.active = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doClose() throws Exception {
        DomainSocketAddress domainSocketAddress;
        boolean delete;
        try {
            super.doClose();
            if (domainSocketAddress != null) {
                if (delete) {
                    return;
                }
            }
        } finally {
            domainSocketAddress = this.local;
            if (domainSocketAddress != null && !new File(domainSocketAddress.path()).delete() && logger.isDebugEnabled()) {
                logger.debug("Failed to delete a domain socket file: {}", domainSocketAddress.path());
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public EpollServerChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public DomainSocketAddress remoteAddress() {
        return (DomainSocketAddress) super.remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public DomainSocketAddress localAddress() {
        return (DomainSocketAddress) super.localAddress();
    }
}
