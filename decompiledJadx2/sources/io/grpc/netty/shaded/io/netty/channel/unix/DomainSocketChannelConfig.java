package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface DomainSocketChannelConfig extends ChannelConfig {
    DomainSocketReadMode getReadMode();

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setAutoClose(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setAutoRead(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setConnectTimeoutMillis(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    DomainSocketChannelConfig setMaxMessagesPerRead(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    DomainSocketChannelConfig setReadMode(DomainSocketReadMode domainSocketReadMode);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    DomainSocketChannelConfig setWriteBufferHighWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    DomainSocketChannelConfig setWriteBufferLowWaterMark(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DomainSocketChannelConfig setWriteSpinCount(int i);
}
