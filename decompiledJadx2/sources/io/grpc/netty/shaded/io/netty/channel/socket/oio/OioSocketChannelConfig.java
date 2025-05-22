package io.grpc.netty.shaded.io.netty.channel.socket.oio;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;
import io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public interface OioSocketChannelConfig extends SocketChannelConfig {
    int getSoTimeout();

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setAllowHalfClosure(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setAutoClose(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setAutoRead(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setConnectTimeoutMillis(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setKeepAlive(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    OioSocketChannelConfig setMaxMessagesPerRead(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setPerformancePreferences(int i, int i2, int i3);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setReceiveBufferSize(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setReuseAddress(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setSendBufferSize(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setSoLinger(int i);

    OioSocketChannelConfig setSoTimeout(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setTcpNoDelay(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig
    OioSocketChannelConfig setTrafficClass(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setWriteBufferHighWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setWriteBufferLowWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.SocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioSocketChannelConfig setWriteSpinCount(int i);
}
