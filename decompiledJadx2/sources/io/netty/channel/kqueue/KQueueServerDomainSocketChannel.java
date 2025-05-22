package io.netty.channel.kqueue;

import io.netty.channel.Channel;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.ServerDomainSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class KQueueServerDomainSocketChannel extends AbstractKQueueServerChannel implements ServerDomainSocketChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) KQueueServerDomainSocketChannel.class);
    private final KQueueServerChannelConfig config;
    private volatile DomainSocketAddress local;

    public KQueueServerDomainSocketChannel() {
        super(BsdSocket.newSocketDomain(), false);
        this.config = new KQueueServerChannelConfig(this);
    }

    public KQueueServerDomainSocketChannel(int i) {
        this(new BsdSocket(i), false);
    }

    KQueueServerDomainSocketChannel(BsdSocket bsdSocket, boolean z) {
        super(bsdSocket, z);
        this.config = new KQueueServerChannelConfig(this);
    }

    @Override // io.netty.channel.kqueue.AbstractKQueueServerChannel
    protected Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception {
        return new KQueueDomainSocketChannel(this, new BsdSocket(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
    public DomainSocketAddress localAddress0() {
        return this.local;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
    public void doBind(SocketAddress socketAddress) throws Exception {
        this.socket.bind(socketAddress);
        this.socket.listen(this.config.getBacklog());
        this.local = (DomainSocketAddress) socketAddress;
        this.active = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
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

    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.Channel
    public KQueueServerChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public DomainSocketAddress remoteAddress() {
        return (DomainSocketAddress) super.remoteAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public DomainSocketAddress localAddress() {
        return (DomainSocketAddress) super.localAddress();
    }
}
