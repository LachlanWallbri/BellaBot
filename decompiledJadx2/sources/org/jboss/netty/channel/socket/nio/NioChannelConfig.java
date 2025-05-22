package org.jboss.netty.channel.socket.nio;

import org.jboss.netty.channel.ChannelConfig;

/* loaded from: classes7.dex */
public interface NioChannelConfig extends ChannelConfig {
    int getWriteBufferHighWaterMark();

    int getWriteBufferLowWaterMark();

    int getWriteSpinCount();

    void setWriteBufferHighWaterMark(int i);

    void setWriteBufferLowWaterMark(int i);

    void setWriteSpinCount(int i);
}
