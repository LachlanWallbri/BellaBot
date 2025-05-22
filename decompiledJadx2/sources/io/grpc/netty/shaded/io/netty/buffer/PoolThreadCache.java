package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.buffer.PoolArena;
import io.grpc.netty.shaded.io.netty.util.internal.MathUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PoolThreadCache {
    private static final int INTEGER_SIZE_MINUS_ONE = 31;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PoolThreadCache.class);
    private int allocations;
    final PoolArena<ByteBuffer> directArena;
    private final int freeSweepAllocationThreshold;
    private final AtomicBoolean freed = new AtomicBoolean();
    final PoolArena<byte[]> heapArena;
    private final MemoryRegionCache<ByteBuffer>[] normalDirectCaches;
    private final MemoryRegionCache<byte[]>[] normalHeapCaches;
    private final int numShiftsNormalDirect;
    private final int numShiftsNormalHeap;
    private final MemoryRegionCache<ByteBuffer>[] smallSubPageDirectCaches;
    private final MemoryRegionCache<byte[]>[] smallSubPageHeapCaches;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolThreadCache(PoolArena<byte[]> poolArena, PoolArena<ByteBuffer> poolArena2, int i, int i2, int i3, int i4) {
        ObjectUtil.checkPositiveOrZero(i3, "maxCachedBufferCapacity");
        this.freeSweepAllocationThreshold = i4;
        this.heapArena = poolArena;
        this.directArena = poolArena2;
        if (poolArena2 != null) {
            this.smallSubPageDirectCaches = createSubPageCaches(i, poolArena2.numSmallSubpagePools);
            this.numShiftsNormalDirect = log2(poolArena2.pageSize);
            this.normalDirectCaches = createNormalCaches(i2, i3, poolArena2);
            poolArena2.numThreadCaches.getAndIncrement();
        } else {
            this.smallSubPageDirectCaches = null;
            this.normalDirectCaches = null;
            this.numShiftsNormalDirect = -1;
        }
        if (poolArena != null) {
            this.smallSubPageHeapCaches = createSubPageCaches(i, poolArena.numSmallSubpagePools);
            this.numShiftsNormalHeap = log2(poolArena.pageSize);
            this.normalHeapCaches = createNormalCaches(i2, i3, poolArena);
            poolArena.numThreadCaches.getAndIncrement();
        } else {
            this.smallSubPageHeapCaches = null;
            this.normalHeapCaches = null;
            this.numShiftsNormalHeap = -1;
        }
        if (!(this.smallSubPageDirectCaches == null && this.normalDirectCaches == null && this.smallSubPageHeapCaches == null && this.normalHeapCaches == null) && i4 < 1) {
            throw new IllegalArgumentException("freeSweepAllocationThreshold: " + i4 + " (expected: > 0)");
        }
    }

    private static <T> MemoryRegionCache<T>[] createSubPageCaches(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        MemoryRegionCache<T>[] memoryRegionCacheArr = new MemoryRegionCache[i2];
        for (int i3 = 0; i3 < memoryRegionCacheArr.length; i3++) {
            memoryRegionCacheArr[i3] = new SubPageMemoryRegionCache(i);
        }
        return memoryRegionCacheArr;
    }

    private static <T> MemoryRegionCache<T>[] createNormalCaches(int i, int i2, PoolArena<T> poolArena) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        MemoryRegionCache<T>[] memoryRegionCacheArr = new MemoryRegionCache[Math.max(1, log2(Math.min(poolArena.chunkSize, i2) / poolArena.pageSize) + 1)];
        for (int i3 = 0; i3 < memoryRegionCacheArr.length; i3++) {
            memoryRegionCacheArr[i3] = new NormalMemoryRegionCache(i);
        }
        return memoryRegionCacheArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int log2(int i) {
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean allocateSmall(PoolArena<?> poolArena, PooledByteBuf<?> pooledByteBuf, int i, int i2) {
        return allocate(cacheForSmall(poolArena, i2), pooledByteBuf, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean allocateNormal(PoolArena<?> poolArena, PooledByteBuf<?> pooledByteBuf, int i, int i2) {
        return allocate(cacheForNormal(poolArena, i2), pooledByteBuf, i);
    }

    private boolean allocate(MemoryRegionCache<?> memoryRegionCache, PooledByteBuf pooledByteBuf, int i) {
        if (memoryRegionCache == null) {
            return false;
        }
        boolean allocate = memoryRegionCache.allocate(pooledByteBuf, i, this);
        int i2 = this.allocations + 1;
        this.allocations = i2;
        if (i2 >= this.freeSweepAllocationThreshold) {
            this.allocations = 0;
            trim();
        }
        return allocate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean add(PoolArena<?> poolArena, PoolChunk poolChunk, ByteBuffer byteBuffer, long j, int i, PoolArena.SizeClass sizeClass) {
        MemoryRegionCache<?> cache = cache(poolArena, poolArena.size2SizeIdx(i), sizeClass);
        if (cache == null) {
            return false;
        }
        return cache.add(poolChunk, byteBuffer, j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C62841 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$buffer$PoolArena$SizeClass = new int[PoolArena.SizeClass.values().length];

        static {
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[PoolArena.SizeClass.Normal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[PoolArena.SizeClass.Small.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private MemoryRegionCache<?> cache(PoolArena<?> poolArena, int i, PoolArena.SizeClass sizeClass) {
        int i2 = C62841.$SwitchMap$io$netty$buffer$PoolArena$SizeClass[sizeClass.ordinal()];
        if (i2 == 1) {
            return cacheForNormal(poolArena, i);
        }
        if (i2 == 2) {
            return cacheForSmall(poolArena, i);
        }
        throw new Error();
    }

    protected void finalize() throws Throwable {
        try {
            super.finalize();
        } finally {
            free(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free(boolean z) {
        if (this.freed.compareAndSet(false, true)) {
            int free = free(this.smallSubPageDirectCaches, z) + free(this.normalDirectCaches, z) + free((MemoryRegionCache<?>[]) this.smallSubPageHeapCaches, z) + free((MemoryRegionCache<?>[]) this.normalHeapCaches, z);
            if (free > 0 && logger.isDebugEnabled()) {
                logger.debug("Freed {} thread-local buffer(s) from thread: {}", Integer.valueOf(free), Thread.currentThread().getName());
            }
            PoolArena<ByteBuffer> poolArena = this.directArena;
            if (poolArena != null) {
                poolArena.numThreadCaches.getAndDecrement();
            }
            PoolArena<byte[]> poolArena2 = this.heapArena;
            if (poolArena2 != null) {
                poolArena2.numThreadCaches.getAndDecrement();
            }
        }
    }

    private static int free(MemoryRegionCache<?>[] memoryRegionCacheArr, boolean z) {
        if (memoryRegionCacheArr == null) {
            return 0;
        }
        int i = 0;
        for (MemoryRegionCache<?> memoryRegionCache : memoryRegionCacheArr) {
            i += free(memoryRegionCache, z);
        }
        return i;
    }

    private static int free(MemoryRegionCache<?> memoryRegionCache, boolean z) {
        if (memoryRegionCache == null) {
            return 0;
        }
        return memoryRegionCache.free(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void trim() {
        trim(this.smallSubPageDirectCaches);
        trim(this.normalDirectCaches);
        trim((MemoryRegionCache<?>[]) this.smallSubPageHeapCaches);
        trim((MemoryRegionCache<?>[]) this.normalHeapCaches);
    }

    private static void trim(MemoryRegionCache<?>[] memoryRegionCacheArr) {
        if (memoryRegionCacheArr == null) {
            return;
        }
        for (MemoryRegionCache<?> memoryRegionCache : memoryRegionCacheArr) {
            trim(memoryRegionCache);
        }
    }

    private static void trim(MemoryRegionCache<?> memoryRegionCache) {
        if (memoryRegionCache == null) {
            return;
        }
        memoryRegionCache.trim();
    }

    private MemoryRegionCache<?> cacheForSmall(PoolArena<?> poolArena, int i) {
        if (poolArena.isDirect()) {
            return cache(this.smallSubPageDirectCaches, i);
        }
        return cache(this.smallSubPageHeapCaches, i);
    }

    private MemoryRegionCache<?> cacheForNormal(PoolArena<?> poolArena, int i) {
        if (poolArena.isDirect()) {
            return cache(this.normalDirectCaches, i);
        }
        return cache(this.normalHeapCaches, i);
    }

    private static <T> MemoryRegionCache<T> cache(MemoryRegionCache<T>[] memoryRegionCacheArr, int i) {
        if (memoryRegionCacheArr == null || i > memoryRegionCacheArr.length - 1) {
            return null;
        }
        return memoryRegionCacheArr[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SubPageMemoryRegionCache<T> extends MemoryRegionCache<T> {
        SubPageMemoryRegionCache(int i) {
            super(i, PoolArena.SizeClass.Small);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache.MemoryRegionCache
        protected void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            poolChunk.initBufWithSubpage(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class NormalMemoryRegionCache<T> extends MemoryRegionCache<T> {
        NormalMemoryRegionCache(int i) {
            super(i, PoolArena.SizeClass.Normal);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache.MemoryRegionCache
        protected void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            poolChunk.initBuf(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class MemoryRegionCache<T> {
        private static final ObjectPool<Entry> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<Entry>() { // from class: io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache.MemoryRegionCache.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.ObjectCreator
            public Entry newObject(ObjectPool.Handle<Entry> handle) {
                return new Entry(handle);
            }
        });
        private int allocations;
        private final Queue<Entry<T>> queue;
        private final int size;
        private final PoolArena.SizeClass sizeClass;

        protected abstract void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache);

        MemoryRegionCache(int i, PoolArena.SizeClass sizeClass) {
            this.size = MathUtil.safeFindNextPositivePowerOfTwo(i);
            this.queue = PlatformDependent.newFixedMpscQueue(this.size);
            this.sizeClass = sizeClass;
        }

        public final boolean add(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, int i) {
            Entry<T> newEntry = newEntry(poolChunk, byteBuffer, j, i);
            boolean offer = this.queue.offer(newEntry);
            if (!offer) {
                newEntry.recycle();
            }
            return offer;
        }

        public final boolean allocate(PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            Entry<T> poll = this.queue.poll();
            if (poll == null) {
                return false;
            }
            initBuf(poll.chunk, poll.nioBuffer, poll.handle, pooledByteBuf, i, poolThreadCache);
            poll.recycle();
            this.allocations++;
            return true;
        }

        public final int free(boolean z) {
            return free(Integer.MAX_VALUE, z);
        }

        private int free(int i, boolean z) {
            int i2 = 0;
            while (i2 < i) {
                Entry<T> poll = this.queue.poll();
                if (poll == null) {
                    break;
                }
                freeEntry(poll, z);
                i2++;
            }
            return i2;
        }

        public final void trim() {
            int i = this.size - this.allocations;
            this.allocations = 0;
            if (i > 0) {
                free(i, false);
            }
        }

        private void freeEntry(Entry entry, boolean z) {
            PoolChunk<T> poolChunk = entry.chunk;
            long j = entry.handle;
            ByteBuffer byteBuffer = entry.nioBuffer;
            if (!z) {
                entry.recycle();
            }
            poolChunk.arena.freeChunk(poolChunk, j, entry.normCapacity, this.sizeClass, byteBuffer, z);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Entry<T> {
            PoolChunk<T> chunk;
            long handle = -1;
            ByteBuffer nioBuffer;
            int normCapacity;
            final ObjectPool.Handle<Entry<?>> recyclerHandle;

            Entry(ObjectPool.Handle<Entry<?>> handle) {
                this.recyclerHandle = handle;
            }

            void recycle() {
                this.chunk = null;
                this.nioBuffer = null;
                this.handle = -1L;
                this.recyclerHandle.recycle(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static Entry newEntry(PoolChunk<?> poolChunk, ByteBuffer byteBuffer, long j, int i) {
            Entry entry = RECYCLER.get();
            entry.chunk = poolChunk;
            entry.nioBuffer = byteBuffer;
            entry.handle = j;
            entry.normCapacity = i;
            return entry;
        }
    }
}
