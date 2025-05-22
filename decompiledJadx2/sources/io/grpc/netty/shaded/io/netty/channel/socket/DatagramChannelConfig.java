package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;
import java.net.InetAddress;
import java.net.NetworkInterface;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface DatagramChannelConfig extends ChannelConfig {
    InetAddress getInterface();

    NetworkInterface getNetworkInterface();

    int getReceiveBufferSize();

    int getSendBufferSize();

    int getTimeToLive();

    int getTrafficClass();

    boolean isBroadcast();

    boolean isLoopbackModeDisabled();

    boolean isReuseAddress();

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setAutoClose(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setAutoRead(boolean z);

    DatagramChannelConfig setBroadcast(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setConnectTimeoutMillis(int i);

    DatagramChannelConfig setInterface(InetAddress inetAddress);

    DatagramChannelConfig setLoopbackModeDisabled(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    DatagramChannelConfig setMaxMessagesPerRead(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    DatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface);

    DatagramChannelConfig setReceiveBufferSize(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    DatagramChannelConfig setReuseAddress(boolean z);

    DatagramChannelConfig setSendBufferSize(int i);

    DatagramChannelConfig setTimeToLive(int i);

    DatagramChannelConfig setTrafficClass(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setWriteSpinCount(int i);
}
