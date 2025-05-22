package org.jboss.netty.buffer;

import java.nio.ByteOrder;

/* loaded from: classes7.dex */
public abstract class AbstractChannelBufferFactory implements ChannelBufferFactory {
    private final ByteOrder defaultOrder;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractChannelBufferFactory() {
        this(ByteOrder.BIG_ENDIAN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractChannelBufferFactory(ByteOrder byteOrder) {
        if (byteOrder == null) {
            throw new NullPointerException("defaultOrder");
        }
        this.defaultOrder = byteOrder;
    }

    @Override // org.jboss.netty.buffer.ChannelBufferFactory
    public ChannelBuffer getBuffer(int i) {
        return getBuffer(getDefaultOrder(), i);
    }

    @Override // org.jboss.netty.buffer.ChannelBufferFactory
    public ChannelBuffer getBuffer(byte[] bArr, int i, int i2) {
        return getBuffer(getDefaultOrder(), bArr, i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBufferFactory
    public ByteOrder getDefaultOrder() {
        return this.defaultOrder;
    }
}
