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
public interface SocketChannelConfig extends ChannelConfig {
    int getReceiveBufferSize();

    int getSendBufferSize();

    int getSoLinger();

    int getTrafficClass();

    boolean isAllowHalfClosure();

    boolean isKeepAlive();

    boolean isReuseAddress();

    boolean isTcpNoDelay();

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    SocketChannelConfig setAllowHalfClosure(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setAutoClose(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setAutoRead(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setConnectTimeoutMillis(int i);

    SocketChannelConfig setKeepAlive(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    SocketChannelConfig setMaxMessagesPerRead(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    SocketChannelConfig setPerformancePreferences(int i, int i2, int i3);

    SocketChannelConfig setReceiveBufferSize(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    SocketChannelConfig setReuseAddress(boolean z);

    SocketChannelConfig setSendBufferSize(int i);

    SocketChannelConfig setSoLinger(int i);

    SocketChannelConfig setTcpNoDelay(boolean z);

    SocketChannelConfig setTrafficClass(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setWriteSpinCount(int i);
}
