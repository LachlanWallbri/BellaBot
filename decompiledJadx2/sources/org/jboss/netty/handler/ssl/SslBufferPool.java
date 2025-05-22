package org.jboss.netty.handler.ssl;

import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public class SslBufferPool {
    private static final int DEFAULT_POOL_SIZE = 19162112;
    private static final int MAX_PACKET_SIZE = 18713;
    private int index;
    private final int maxBufferCount;
    private final ByteBuffer[] pool;

    public SslBufferPool() {
        this(DEFAULT_POOL_SIZE);
    }

    public SslBufferPool(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxPoolSize: " + i);
        }
        int i2 = i / MAX_PACKET_SIZE;
        i2 = i % MAX_PACKET_SIZE != 0 ? i2 + 1 : i2;
        this.pool = new ByteBuffer[i2];
        this.maxBufferCount = i2;
    }

    public int getMaxPoolSize() {
        return this.maxBufferCount * MAX_PACKET_SIZE;
    }

    public synchronized int getUnacquiredPoolSize() {
        return this.index * MAX_PACKET_SIZE;
    }

    public ByteBuffer acquireBuffer() {
        return acquire();
    }

    @Deprecated
    synchronized ByteBuffer acquire() {
        if (this.index == 0) {
            return ByteBuffer.allocate(MAX_PACKET_SIZE);
        }
        ByteBuffer[] byteBufferArr = this.pool;
        int i = this.index - 1;
        this.index = i;
        return (ByteBuffer) byteBufferArr[i].clear();
    }

    public void releaseBuffer(ByteBuffer byteBuffer) {
        release(byteBuffer);
    }

    @Deprecated
    synchronized void release(ByteBuffer byteBuffer) {
        if (this.index < this.maxBufferCount) {
            ByteBuffer[] byteBufferArr = this.pool;
            int i = this.index;
            this.index = i + 1;
            byteBufferArr[i] = byteBuffer;
        }
    }
}
