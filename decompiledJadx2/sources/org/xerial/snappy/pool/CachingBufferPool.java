package org.xerial.snappy.pool;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes9.dex */
public final class CachingBufferPool implements BufferPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final IntFunction<byte[]> ARRAY_FUNCTION = new IntFunction<byte[]>() { // from class: org.xerial.snappy.pool.CachingBufferPool.1
        @Override // org.xerial.snappy.pool.CachingBufferPool.IntFunction
        public byte[] create(int i) {
            return new byte[i];
        }
    };
    private static final IntFunction<ByteBuffer> DBB_FUNCTION = new IntFunction<ByteBuffer>() { // from class: org.xerial.snappy.pool.CachingBufferPool.2
        @Override // org.xerial.snappy.pool.CachingBufferPool.IntFunction
        public ByteBuffer create(int i) {
            return ByteBuffer.allocateDirect(i);
        }
    };
    private static final CachingBufferPool INSTANCE = new CachingBufferPool();
    private final ConcurrentMap<Integer, ConcurrentLinkedDeque<SoftReference<byte[]>>> bytes = new ConcurrentHashMap();
    private final ConcurrentMap<Integer, ConcurrentLinkedDeque<SoftReference<ByteBuffer>>> buffers = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface IntFunction<E> {
        E create(int i);
    }

    private static int roundToPowers(int i, int i2) {
        int i3 = ((Integer.MAX_VALUE >> i2) << i2) & i;
        return i3 == i ? i : (1 << i2) + i3;
    }

    private CachingBufferPool() {
    }

    public static BufferPool getInstance() {
        return INSTANCE;
    }

    @Override // org.xerial.snappy.pool.BufferPool
    public byte[] allocateArray(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("size is invalid: " + i);
        }
        return (byte[]) getOrCreate(i, this.bytes, ARRAY_FUNCTION);
    }

    @Override // org.xerial.snappy.pool.BufferPool
    public void releaseArray(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("buffer is null");
        }
        returnValue(bArr, Integer.valueOf(bArr.length), this.bytes);
    }

    @Override // org.xerial.snappy.pool.BufferPool
    public ByteBuffer allocateDirect(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("size is invalid: " + i);
        }
        return (ByteBuffer) getOrCreate(i, this.buffers, DBB_FUNCTION);
    }

    @Override // org.xerial.snappy.pool.BufferPool
    public void releaseDirect(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("buffer is null");
        }
        byteBuffer.clear();
        returnValue(byteBuffer, Integer.valueOf(byteBuffer.capacity()), this.buffers);
    }

    private static <E> E getOrCreate(int i, ConcurrentMap<Integer, ConcurrentLinkedDeque<SoftReference<E>>> concurrentMap, IntFunction<E> intFunction) {
        E e;
        int adjustSize = adjustSize(i);
        ConcurrentLinkedDeque optimisticGetEntry = optimisticGetEntry(Integer.valueOf(adjustSize), concurrentMap);
        do {
            SoftReference softReference = (SoftReference) optimisticGetEntry.pollFirst();
            if (softReference != null) {
                e = (E) softReference.get();
            } else {
                return intFunction.create(adjustSize);
            }
        } while (e == null);
        return e;
    }

    static int adjustSize(int i) {
        switch (Integer.numberOfLeadingZeros(i)) {
            case 1:
            case 2:
                if (i <= 1610612736) {
                    return roundToPowers(i, 27);
                }
                return Integer.MAX_VALUE;
            case 3:
            case 4:
                return roundToPowers(i, 24);
            case 5:
            case 6:
            case 7:
                return roundToPowers(i, 22);
            case 8:
            case 9:
            case 10:
                return roundToPowers(i, 19);
            case 11:
            case 12:
                return roundToPowers(i, 17);
            case 13:
            case 14:
            case 15:
            case 16:
                return roundToPowers(i, 14);
            case 17:
            case 18:
            case 19:
                return roundToPowers(i, 11);
            default:
                return 4096;
        }
    }

    private static <E> ConcurrentLinkedDeque<SoftReference<E>> optimisticGetEntry(Integer num, ConcurrentMap<Integer, ConcurrentLinkedDeque<SoftReference<E>>> concurrentMap) {
        ConcurrentLinkedDeque<SoftReference<E>> concurrentLinkedDeque = concurrentMap.get(num);
        if (concurrentLinkedDeque != null) {
            return concurrentLinkedDeque;
        }
        concurrentMap.putIfAbsent(num, new ConcurrentLinkedDeque<>());
        return concurrentMap.get(num);
    }

    private static <E> void returnValue(E e, Integer num, ConcurrentMap<Integer, ConcurrentLinkedDeque<SoftReference<E>>> concurrentMap) {
        ConcurrentLinkedDeque<SoftReference<E>> concurrentLinkedDeque = concurrentMap.get(num);
        if (concurrentLinkedDeque != null) {
            concurrentLinkedDeque.addFirst(new SoftReference<>(e));
            boolean z = true;
            while (z) {
                SoftReference<E> peekLast = concurrentLinkedDeque.peekLast();
                if (peekLast == null) {
                    return;
                }
                if (peekLast.get() == null) {
                    concurrentLinkedDeque.removeLastOccurrence(peekLast);
                } else {
                    z = false;
                }
            }
        }
    }

    public String toString() {
        return "CachingBufferPool [bytes=" + this.bytes + ", buffers=" + this.buffers + "]";
    }
}
