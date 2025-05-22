package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public class FixedReceiveBufferSizePredictor implements ReceiveBufferSizePredictor {
    private final int bufferSize;

    @Override // org.jboss.netty.channel.ReceiveBufferSizePredictor
    public void previousReceiveBufferSize(int i) {
    }

    public FixedReceiveBufferSizePredictor(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("bufferSize must greater than 0: " + i);
        }
        this.bufferSize = i;
    }

    @Override // org.jboss.netty.channel.ReceiveBufferSizePredictor
    public int nextReceiveBufferSize() {
        return this.bufferSize;
    }
}
