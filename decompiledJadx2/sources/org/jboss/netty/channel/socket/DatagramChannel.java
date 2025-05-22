package org.jboss.netty.channel.socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

/* loaded from: classes7.dex */
public interface DatagramChannel extends Channel {
    @Override // org.jboss.netty.channel.Channel
    DatagramChannelConfig getConfig();

    @Override // org.jboss.netty.channel.Channel
    InetSocketAddress getLocalAddress();

    @Override // org.jboss.netty.channel.Channel
    InetSocketAddress getRemoteAddress();

    ChannelFuture joinGroup(InetAddress inetAddress);

    ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface);

    ChannelFuture leaveGroup(InetAddress inetAddress);

    ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface);
}
