package org.xerial.snappy.pool;

import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public final class QuiescentBufferPool implements BufferPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final QuiescentBufferPool INSTANCE = new QuiescentBufferPool();

    @Override // org.xerial.snappy.pool.BufferPool
    public void releaseArray(byte[] bArr) {
    }

    private QuiescentBufferPool() {
    }

    public static BufferPool getInstance() {
        return INSTANCE;
    }

    @Override // org.xerial.snappy.pool.BufferPool
    public byte[] allocateArray(int i) {
        return new byte[i];
    }

    @Override // org.xerial.snappy.pool.BufferPool
    public ByteBuffer allocateDirect(int i) {
        return ByteBuffer.allocateDirect(i);
    }

    @Override // org.xerial.snappy.pool.BufferPool
    public void releaseDirect(ByteBuffer byteBuffer) {
        DirectByteBuffers.releaseDirectByteBuffer(byteBuffer);
    }
}
