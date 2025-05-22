package io.grpc.netty.shaded.io.netty.channel.socket.oio;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;
import io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public interface OioServerSocketChannelConfig extends ServerSocketChannelConfig {
    int getSoTimeout();

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setAutoClose(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setAutoRead(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    OioServerSocketChannelConfig setBacklog(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setConnectTimeoutMillis(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    OioServerSocketChannelConfig setMaxMessagesPerRead(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    OioServerSocketChannelConfig setPerformancePreferences(int i, int i2, int i3);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    OioServerSocketChannelConfig setReceiveBufferSize(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    OioServerSocketChannelConfig setReuseAddress(boolean z);

    OioServerSocketChannelConfig setSoTimeout(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setWriteBufferHighWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setWriteBufferLowWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    OioServerSocketChannelConfig setWriteSpinCount(int i);
}
