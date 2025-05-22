package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ServerSocketChannelConfig extends ChannelConfig {
    int getBacklog();

    int getReceiveBufferSize();

    boolean isReuseAddress();

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setAutoRead(boolean z);

    ServerSocketChannelConfig setBacklog(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setConnectTimeoutMillis(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    ServerSocketChannelConfig setMaxMessagesPerRead(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    ServerSocketChannelConfig setPerformancePreferences(int i, int i2, int i3);

    ServerSocketChannelConfig setReceiveBufferSize(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    ServerSocketChannelConfig setReuseAddress(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteBufferHighWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteBufferLowWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteSpinCount(int i);
}
