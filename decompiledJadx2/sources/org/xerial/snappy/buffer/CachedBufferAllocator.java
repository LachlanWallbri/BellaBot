package org.xerial.snappy.buffer;

import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class CachedBufferAllocator implements BufferAllocator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static BufferAllocatorFactory factory = new BufferAllocatorFactory() { // from class: org.xerial.snappy.buffer.CachedBufferAllocator.1
        @Override // org.xerial.snappy.buffer.BufferAllocatorFactory
        public BufferAllocator getBufferAllocator(int i) {
            return CachedBufferAllocator.getAllocator(i);
        }
    };
    private static final Map<Integer, SoftReference<CachedBufferAllocator>> queueTable = new HashMap();
    private final Deque<byte[]> bufferQueue = new ArrayDeque();
    private final int bufferSize;

    public static void setBufferAllocatorFactory(BufferAllocatorFactory bufferAllocatorFactory) {
        factory = bufferAllocatorFactory;
    }

    public static BufferAllocatorFactory getBufferAllocatorFactory() {
        return factory;
    }

    public CachedBufferAllocator(int i) {
        this.bufferSize = i;
    }

    public static synchronized CachedBufferAllocator getAllocator(int i) {
        CachedBufferAllocator cachedBufferAllocator;
        synchronized (CachedBufferAllocator.class) {
            cachedBufferAllocator = queueTable.containsKey(Integer.valueOf(i)) ? queueTable.get(Integer.valueOf(i)).get() : null;
            if (cachedBufferAllocator == null) {
                cachedBufferAllocator = new CachedBufferAllocator(i);
                queueTable.put(Integer.valueOf(i), new SoftReference<>(cachedBufferAllocator));
            }
        }
        return cachedBufferAllocator;
    }

    @Override // org.xerial.snappy.buffer.BufferAllocator
    public byte[] allocate(int i) {
        synchronized (this) {
            if (this.bufferQueue.isEmpty()) {
                return new byte[i];
            }
            return this.bufferQueue.pollFirst();
        }
    }

    @Override // org.xerial.snappy.buffer.BufferAllocator
    public void release(byte[] bArr) {
        synchronized (this) {
            this.bufferQueue.addLast(bArr);
        }
    }
}
