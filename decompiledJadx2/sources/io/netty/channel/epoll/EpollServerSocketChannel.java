package io.netty.channel.epoll;

import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.unix.NativeInetAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class EpollServerSocketChannel extends AbstractEpollServerChannel implements ServerSocketChannel {
    private final EpollServerSocketChannelConfig config;
    private volatile Collection<InetAddress> tcpMd5SigAddresses;

    public EpollServerSocketChannel() {
        super(LinuxSocket.newSocketStream(), false);
        this.tcpMd5SigAddresses = Collections.emptyList();
        this.config = new EpollServerSocketChannelConfig(this);
    }

    public EpollServerSocketChannel(int i) {
        this(new LinuxSocket(i));
    }

    EpollServerSocketChannel(LinuxSocket linuxSocket) {
        super(linuxSocket);
        this.tcpMd5SigAddresses = Collections.emptyList();
        this.config = new EpollServerSocketChannelConfig(this);
    }

    EpollServerSocketChannel(LinuxSocket linuxSocket, boolean z) {
        super(linuxSocket, z);
        this.tcpMd5SigAddresses = Collections.emptyList();
        this.config = new EpollServerSocketChannelConfig(this);
    }

    @Override // io.netty.channel.epoll.AbstractEpollServerChannel, io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof EpollEventLoop;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.AbstractChannel
    public void doBind(SocketAddress socketAddress) throws Exception {
        super.doBind(socketAddress);
        if (Native.IS_SUPPORTING_TCP_FASTOPEN && this.config.getTcpFastopen() > 0) {
            this.socket.setTcpFastOpen(this.config.getTcpFastopen());
        }
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

    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.Channel
    public EpollServerSocketChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.epoll.AbstractEpollServerChannel
    protected Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception {
        return new EpollSocketChannel(this, new LinuxSocket(i), NativeInetAddress.address(bArr, i2, i3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection<InetAddress> tcpMd5SigAddresses() {
        return this.tcpMd5SigAddresses;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpMd5Sig(Map<InetAddress, byte[]> map) throws IOException {
        this.tcpMd5SigAddresses = TcpMd5Util.newTcpMd5Sigs(this, this.tcpMd5SigAddresses, map);
    }
}
