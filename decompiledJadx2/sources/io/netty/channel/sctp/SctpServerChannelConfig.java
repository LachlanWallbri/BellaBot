package io.netty.channel.sctp;

import com.sun.nio.sctp.SctpStandardSocketOptions;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface SctpServerChannelConfig extends ChannelConfig {
    int getBacklog();

    SctpStandardSocketOptions.InitMaxStreams getInitMaxStreams();

    int getReceiveBufferSize();

    int getSendBufferSize();

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setAutoClose(boolean z);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setAutoRead(boolean z);

    SctpServerChannelConfig setBacklog(int i);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setConnectTimeoutMillis(int i);

    SctpServerChannelConfig setInitMaxStreams(SctpStandardSocketOptions.InitMaxStreams initMaxStreams);

    @Override // io.netty.channel.ChannelConfig
    @Deprecated
    SctpServerChannelConfig setMaxMessagesPerRead(int i);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    SctpServerChannelConfig setReceiveBufferSize(int i);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    SctpServerChannelConfig setSendBufferSize(int i);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setWriteBufferHighWaterMark(int i);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setWriteBufferLowWaterMark(int i);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override // io.netty.channel.ChannelConfig
    SctpServerChannelConfig setWriteSpinCount(int i);
}
