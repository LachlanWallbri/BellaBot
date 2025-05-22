package io.netty.channel.kqueue;

import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.unix.NativeInetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class KQueueServerSocketChannel extends AbstractKQueueServerChannel implements ServerSocketChannel {
    private final KQueueServerSocketChannelConfig config;

    public KQueueServerSocketChannel() {
        super(BsdSocket.newSocketStream(), false);
        this.config = new KQueueServerSocketChannelConfig(this);
    }

    public KQueueServerSocketChannel(int i) {
        this(new BsdSocket(i));
    }

    KQueueServerSocketChannel(BsdSocket bsdSocket) {
        super(bsdSocket);
        this.config = new KQueueServerSocketChannelConfig(this);
    }

    KQueueServerSocketChannel(BsdSocket bsdSocket, boolean z) {
        super(bsdSocket, z);
        this.config = new KQueueServerSocketChannelConfig(this);
    }

    @Override // io.netty.channel.kqueue.AbstractKQueueServerChannel, io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof KQueueEventLoop;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
    public void doBind(SocketAddress socketAddress) throws Exception {
        super.doBind(socketAddress);
        this.socket.listen(this.config.getBacklog());
        this.active = true;
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.Channel
    public KQueueServerSocketChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.kqueue.AbstractKQueueServerChannel
    protected Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception {
        return new KQueueSocketChannel(this, new BsdSocket(i), NativeInetAddress.address(bArr, i2, i3));
    }
}
