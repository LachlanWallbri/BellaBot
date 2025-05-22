package org.jboss.netty.handler.codec.replay;

import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.DynamicChannelBuffer;

@Deprecated
/* loaded from: classes7.dex */
class UnsafeDynamicChannelBuffer extends DynamicChannelBuffer {
    @Override // org.jboss.netty.buffer.AbstractChannelBuffer
    protected void checkReadableBytes(int i) {
    }

    UnsafeDynamicChannelBuffer(ChannelBufferFactory channelBufferFactory, int i) {
        super(channelBufferFactory.getDefaultOrder(), i, channelBufferFactory);
    }

    UnsafeDynamicChannelBuffer(ChannelBufferFactory channelBufferFactory) {
        this(channelBufferFactory, 256);
    }
}
