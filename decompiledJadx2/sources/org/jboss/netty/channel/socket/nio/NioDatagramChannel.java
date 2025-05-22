package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.InternetProtocolFamily;
import org.jboss.netty.util.internal.DetectionUtil;

/* loaded from: classes7.dex */
public final class NioDatagramChannel extends AbstractNioChannel<DatagramChannel> implements org.jboss.netty.channel.socket.DatagramChannel {
    private final NioDatagramChannelConfig config;
    private Map<InetAddress, List<MembershipKey>> memberships;

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

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioDatagramChannel(ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink, NioDatagramWorker nioDatagramWorker, InternetProtocolFamily internetProtocolFamily) {
        super(null, channelFactory, channelPipeline, channelSink, nioDatagramWorker, openNonBlockingChannel(internetProtocolFamily));
        this.config = new DefaultNioDatagramChannelConfig((DatagramChannel) this.channel);
        Channels.fireChannelOpen(this);
    }

    private static DatagramChannel openNonBlockingChannel(InternetProtocolFamily internetProtocolFamily) {
        DatagramChannel open;
        try {
            if (DetectionUtil.javaVersion() >= 7 && internetProtocolFamily != null) {
                int i = C86951.$SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily[internetProtocolFamily.ordinal()];
                if (i == 1) {
                    open = DatagramChannel.open(ProtocolFamilyConverter.convert(internetProtocolFamily));
                } else if (i == 2) {
                    open = DatagramChannel.open(ProtocolFamilyConverter.convert(internetProtocolFamily));
                } else {
                    throw new IllegalArgumentException();
                }
                open.configureBlocking(false);
                return open;
            }
            open = DatagramChannel.open();
            open.configureBlocking(false);
            return open;
        } catch (IOException e) {
            throw new ChannelException("Failed to open a DatagramChannel.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.channel.socket.nio.NioDatagramChannel$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C86951 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily = new int[InternetProtocolFamily.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv6.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel
    public NioDatagramWorker getWorker() {
        return (NioDatagramWorker) super.getWorker();
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isBound() {
        return isOpen() && ((DatagramChannel) this.channel).socket().isBound();
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isConnected() {
        return ((DatagramChannel) this.channel).isConnected();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel, org.jboss.netty.channel.AbstractChannel
    public boolean setClosed() {
        return super.setClosed();
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel, org.jboss.netty.channel.Channel
    public NioDatagramChannelConfig getConfig() {
        return this.config;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DatagramChannel getDatagramChannel() {
        return (DatagramChannel) this.channel;
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress) {
        try {
            return joinGroup(inetAddress, NetworkInterface.getByInetAddress(getLocalAddress().getAddress()), null);
        } catch (SocketException e) {
            return Channels.failedFuture(this, e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return joinGroup(inetSocketAddress.getAddress(), networkInterface, null);
    }

    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        MembershipKey join;
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        if (inetAddress == null) {
            throw new NullPointerException("multicastAddress");
        }
        if (networkInterface == null) {
            throw new NullPointerException("networkInterface");
        }
        try {
            if (inetAddress2 == null) {
                join = ((DatagramChannel) this.channel).join(inetAddress, networkInterface);
            } else {
                join = ((DatagramChannel) this.channel).join(inetAddress, networkInterface, inetAddress2);
            }
            synchronized (this) {
                if (this.memberships == null) {
                    this.memberships = new HashMap();
                }
                List<MembershipKey> list = this.memberships.get(inetAddress);
                if (list == null) {
                    list = new ArrayList<>();
                    this.memberships.put(inetAddress, list);
                }
                list.add(join);
            }
            return Channels.succeededFuture(this);
        } catch (Throwable th) {
            return Channels.failedFuture(this, th);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        try {
            return leaveGroup(inetAddress, NetworkInterface.getByInetAddress(getLocalAddress().getAddress()), null);
        } catch (SocketException e) {
            return Channels.failedFuture(this, e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return leaveGroup(inetSocketAddress.getAddress(), networkInterface, null);
    }

    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        List<MembershipKey> list;
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        if (inetAddress == null) {
            throw new NullPointerException("multicastAddress");
        }
        if (networkInterface == null) {
            throw new NullPointerException("networkInterface");
        }
        synchronized (this) {
            if (this.memberships != null && (list = this.memberships.get(inetAddress)) != null) {
                Iterator<MembershipKey> it = list.iterator();
                while (it.hasNext()) {
                    MembershipKey next = it.next();
                    if (networkInterface.equals(next.networkInterface()) && ((inetAddress2 == null && next.sourceAddress() == null) || (inetAddress2 != null && inetAddress2.equals(next.sourceAddress())))) {
                        next.drop();
                        it.remove();
                    }
                }
                if (list.isEmpty()) {
                    this.memberships.remove(inetAddress);
                }
            }
        }
        return Channels.succeededFuture(this);
    }

    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        if (inetAddress == null) {
            throw new NullPointerException("multicastAddress");
        }
        if (inetAddress2 == null) {
            throw new NullPointerException("sourceToBlock");
        }
        if (networkInterface == null) {
            throw new NullPointerException("networkInterface");
        }
        synchronized (this) {
            if (this.memberships != null) {
                for (MembershipKey membershipKey : this.memberships.get(inetAddress)) {
                    if (networkInterface.equals(membershipKey.networkInterface())) {
                        try {
                            membershipKey.block(inetAddress2);
                        } catch (IOException e) {
                            return Channels.failedFuture(this, e);
                        }
                    }
                }
            }
        }
        return Channels.succeededFuture(this);
    }

    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2) {
        try {
            block(inetAddress, NetworkInterface.getByInetAddress(getLocalAddress().getAddress()), inetAddress2);
            return Channels.succeededFuture(this);
        } catch (SocketException e) {
            return Channels.failedFuture(this, e);
        }
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel
    InetSocketAddress getLocalSocketAddress() throws Exception {
        return (InetSocketAddress) ((DatagramChannel) this.channel).socket().getLocalSocketAddress();
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioChannel
    InetSocketAddress getRemoteSocketAddress() throws Exception {
        return (InetSocketAddress) ((DatagramChannel) this.channel).socket().getRemoteSocketAddress();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj, SocketAddress socketAddress) {
        if (socketAddress == null || socketAddress.equals(getRemoteAddress())) {
            return super.write(obj, null);
        }
        return super.write(obj, socketAddress);
    }
}
