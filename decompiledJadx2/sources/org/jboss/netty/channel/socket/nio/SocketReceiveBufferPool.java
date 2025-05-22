package org.jboss.netty.channel.socket.nio;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
final class SocketReceiveBufferPool {
    private static final int POOL_SIZE = 8;
    private final SoftReference<ByteBuffer>[] pool = new SoftReference[8];

    private static int normalizeCapacity(int i) {
        int i2 = i >>> 10;
        if ((i & 1023) != 0) {
            i2++;
        }
        return i2 << 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer acquire(int i) {
        SoftReference<ByteBuffer>[] softReferenceArr = this.pool;
        for (int i2 = 0; i2 < 8; i2++) {
            SoftReference<ByteBuffer> softReference = softReferenceArr[i2];
            if (softReference != null) {
                ByteBuffer byteBuffer = softReference.get();
                if (byteBuffer == null) {
                    softReferenceArr[i2] = null;
                } else if (byteBuffer.capacity() >= i) {
                    softReferenceArr[i2] = null;
                    byteBuffer.clear();
                    return byteBuffer;
                }
            }
        }
        return ByteBuffer.allocateDirect(normalizeCapacity(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release(ByteBuffer byteBuffer) {
        SoftReference<ByteBuffer>[] softReferenceArr = this.pool;
        for (int i = 0; i < 8; i++) {
            SoftReference<ByteBuffer> softReference = softReferenceArr[i];
            if (softReference == null || softReference.get() == null) {
                softReferenceArr[i] = new SoftReference<>(byteBuffer);
                return;
            }
        }
        int capacity = byteBuffer.capacity();
        for (int i2 = 0; i2 < 8; i2++) {
            ByteBuffer byteBuffer2 = softReferenceArr[i2].get();
            if (byteBuffer2 == null) {
                softReferenceArr[i2] = null;
            } else if (byteBuffer2.capacity() < capacity) {
                softReferenceArr[i2] = new SoftReference<>(byteBuffer);
                return;
            }
        }
    }
}
