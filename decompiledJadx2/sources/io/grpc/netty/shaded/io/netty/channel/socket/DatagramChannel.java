package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface DatagramChannel extends Channel {
    ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2);

    ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2, ChannelPromise channelPromise);

    ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2);

    ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise);

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    DatagramChannelConfig config();

    boolean isConnected();

    ChannelFuture joinGroup(InetAddress inetAddress);

    ChannelFuture joinGroup(InetAddress inetAddress, ChannelPromise channelPromise);

    ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2);

    ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise);

    ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface);

    ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise);

    ChannelFuture leaveGroup(InetAddress inetAddress);

    ChannelFuture leaveGroup(InetAddress inetAddress, ChannelPromise channelPromise);

    ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2);

    ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise);

    ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface);

    ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise);

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    InetSocketAddress localAddress();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    InetSocketAddress remoteAddress();
}
