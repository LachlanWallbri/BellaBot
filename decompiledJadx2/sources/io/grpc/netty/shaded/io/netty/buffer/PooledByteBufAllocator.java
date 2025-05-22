package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.buffer.PoolArena;
import io.grpc.netty.shaded.io.netty.util.NettyRuntime;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocalThread;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadExecutorMap;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class PooledByteBufAllocator extends AbstractByteBufAllocator implements ByteBufAllocatorMetricProvider {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final PooledByteBufAllocator DEFAULT;
    private static final int DEFAULT_CACHE_TRIM_INTERVAL;
    private static final long DEFAULT_CACHE_TRIM_INTERVAL_MILLIS;
    private static final int DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT;
    private static final int DEFAULT_MAX_CACHED_BUFFER_CAPACITY;
    static final int DEFAULT_MAX_CACHED_BYTEBUFFERS_PER_CHUNK;
    private static final int DEFAULT_MAX_ORDER;
    private static final int DEFAULT_NORMAL_CACHE_SIZE;
    private static final int DEFAULT_NUM_DIRECT_ARENA;
    private static final int DEFAULT_NUM_HEAP_ARENA;
    private static final int DEFAULT_PAGE_SIZE;
    private static final int DEFAULT_SMALL_CACHE_SIZE;
    private static final boolean DEFAULT_USE_CACHE_FOR_ALL_THREADS;
    private static final int MAX_CHUNK_SIZE = 1073741824;
    private static final int MIN_PAGE_SIZE = 4096;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PooledByteBufAllocator.class);
    private final int chunkSize;
    private final List<PoolArenaMetric> directArenaMetrics;
    private final PoolArena<ByteBuffer>[] directArenas;
    private final List<PoolArenaMetric> heapArenaMetrics;
    private final PoolArena<byte[]>[] heapArenas;
    private final PooledByteBufAllocatorMetric metric;
    private final int normalCacheSize;
    private final int smallCacheSize;
    private final PoolThreadLocalCache threadCache;
    private final Runnable trimTask;

    @Deprecated
    public static int defaultTinyCacheSize() {
        return 0;
    }

    @Deprecated
    public int tinyCacheSize() {
        return 0;
    }

    static {
        Object obj;
        int i = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.pageSize", 8192);
        Object th = null;
        try {
            validateAndCalculatePageShifts(i);
            obj = null;
        } catch (Throwable th2) {
            obj = th2;
            i = 8192;
        }
        DEFAULT_PAGE_SIZE = i;
        int i2 = 11;
        int i3 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.maxOrder", 11);
        try {
            validateAndCalculateChunkSize(DEFAULT_PAGE_SIZE, i3);
            i2 = i3;
        } catch (Throwable th3) {
            th = th3;
        }
        DEFAULT_MAX_ORDER = i2;
        Runtime runtime = Runtime.getRuntime();
        long availableProcessors = NettyRuntime.availableProcessors() * 2;
        long j = DEFAULT_PAGE_SIZE << DEFAULT_MAX_ORDER;
        DEFAULT_NUM_HEAP_ARENA = Math.max(0, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.numHeapArenas", (int) Math.min(availableProcessors, ((runtime.maxMemory() / j) / 2) / 3)));
        DEFAULT_NUM_DIRECT_ARENA = Math.max(0, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.numDirectArenas", (int) Math.min(availableProcessors, ((PlatformDependent.maxDirectMemory() / j) / 2) / 3)));
        DEFAULT_SMALL_CACHE_SIZE = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.smallCacheSize", 256);
        DEFAULT_NORMAL_CACHE_SIZE = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.normalCacheSize", 64);
        DEFAULT_MAX_CACHED_BUFFER_CAPACITY = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.maxCachedBufferCapacity", 32768);
        DEFAULT_CACHE_TRIM_INTERVAL = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.cacheTrimInterval", 8192);
        if (SystemPropertyUtil.contains("io.grpc.netty.shaded.io.netty.allocation.cacheTrimIntervalMillis")) {
            logger.warn("-Dio.netty.allocation.cacheTrimIntervalMillis is deprecated, use -Dio.netty.allocator.cacheTrimIntervalMillis");
            if (SystemPropertyUtil.contains("io.grpc.netty.shaded.io.netty.allocator.cacheTrimIntervalMillis")) {
                DEFAULT_CACHE_TRIM_INTERVAL_MILLIS = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.allocator.cacheTrimIntervalMillis", 0L);
            } else {
                DEFAULT_CACHE_TRIM_INTERVAL_MILLIS = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.allocation.cacheTrimIntervalMillis", 0L);
            }
        } else {
            DEFAULT_CACHE_TRIM_INTERVAL_MILLIS = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.allocator.cacheTrimIntervalMillis", 0L);
        }
        DEFAULT_USE_CACHE_FOR_ALL_THREADS = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.allocator.useCacheForAllThreads", true);
        DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.directMemoryCacheAlignment", 0);
        DEFAULT_MAX_CACHED_BYTEBUFFERS_PER_CHUNK = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.maxCachedByteBuffersPerChunk", 1023);
        if (logger.isDebugEnabled()) {
            logger.debug("-Dio.netty.allocator.numHeapArenas: {}", Integer.valueOf(DEFAULT_NUM_HEAP_ARENA));
            logger.debug("-Dio.netty.allocator.numDirectArenas: {}", Integer.valueOf(DEFAULT_NUM_DIRECT_ARENA));
            if (obj == null) {
                logger.debug("-Dio.netty.allocator.pageSize: {}", Integer.valueOf(DEFAULT_PAGE_SIZE));
            } else {
                logger.debug("-Dio.netty.allocator.pageSize: {}", Integer.valueOf(DEFAULT_PAGE_SIZE), obj);
            }
            if (th == null) {
                logger.debug("-Dio.netty.allocator.maxOrder: {}", Integer.valueOf(DEFAULT_MAX_ORDER));
            } else {
                logger.debug("-Dio.netty.allocator.maxOrder: {}", Integer.valueOf(DEFAULT_MAX_ORDER), th);
            }
            logger.debug("-Dio.netty.allocator.chunkSize: {}", Integer.valueOf(DEFAULT_PAGE_SIZE << DEFAULT_MAX_ORDER));
            logger.debug("-Dio.netty.allocator.smallCacheSize: {}", Integer.valueOf(DEFAULT_SMALL_CACHE_SIZE));
            logger.debug("-Dio.netty.allocator.normalCacheSize: {}", Integer.valueOf(DEFAULT_NORMAL_CACHE_SIZE));
            logger.debug("-Dio.netty.allocator.maxCachedBufferCapacity: {}", Integer.valueOf(DEFAULT_MAX_CACHED_BUFFER_CAPACITY));
            logger.debug("-Dio.netty.allocator.cacheTrimInterval: {}", Integer.valueOf(DEFAULT_CACHE_TRIM_INTERVAL));
            logger.debug("-Dio.netty.allocator.cacheTrimIntervalMillis: {}", Long.valueOf(DEFAULT_CACHE_TRIM_INTERVAL_MILLIS));
            logger.debug("-Dio.netty.allocator.useCacheForAllThreads: {}", Boolean.valueOf(DEFAULT_USE_CACHE_FOR_ALL_THREADS));
            logger.debug("-Dio.netty.allocator.maxCachedByteBuffersPerChunk: {}", Integer.valueOf(DEFAULT_MAX_CACHED_BYTEBUFFERS_PER_CHUNK));
        }
        DEFAULT = new PooledByteBufAllocator(PlatformDependent.directBufferPreferred());
    }

    public PooledByteBufAllocator() {
        this(false);
    }

    public PooledByteBufAllocator(boolean z) {
        this(z, DEFAULT_NUM_HEAP_ARENA, DEFAULT_NUM_DIRECT_ARENA, DEFAULT_PAGE_SIZE, DEFAULT_MAX_ORDER);
    }

    public PooledByteBufAllocator(int i, int i2, int i3, int i4) {
        this(false, i, i2, i3, i4);
    }

    @Deprecated
    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4) {
        this(z, i, i2, i3, i4, 0, DEFAULT_SMALL_CACHE_SIZE, DEFAULT_NORMAL_CACHE_SIZE);
    }

    @Deprecated
    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this(z, i, i2, i3, i4, i6, i7, DEFAULT_USE_CACHE_FOR_ALL_THREADS, DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT);
    }

    @Deprecated
    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2) {
        this(z, i, i2, i3, i4, i6, i7, z2);
    }

    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        this(z, i, i2, i3, i4, i5, i6, z2, DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT);
    }

    @Deprecated
    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2, int i8) {
        this(z, i, i2, i3, i4, i6, i7, z2, i8);
    }

    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, boolean z2, int i7) {
        super(z);
        this.trimTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.buffer.PooledByteBufAllocator.1
            @Override // java.lang.Runnable
            public void run() {
                PooledByteBufAllocator.this.trimCurrentThreadCache();
            }
        };
        this.threadCache = new PoolThreadLocalCache(z2);
        this.smallCacheSize = i5;
        this.normalCacheSize = i6;
        this.chunkSize = validateAndCalculateChunkSize(i3, i4);
        ObjectUtil.checkPositiveOrZero(i, "nHeapArena");
        ObjectUtil.checkPositiveOrZero(i2, "nDirectArena");
        ObjectUtil.checkPositiveOrZero(i7, "directMemoryCacheAlignment");
        if (i7 > 0 && !isDirectMemoryCacheAlignmentSupported()) {
            throw new IllegalArgumentException("directMemoryCacheAlignment is not supported");
        }
        if (((-i7) & i7) != i7) {
            throw new IllegalArgumentException("directMemoryCacheAlignment: " + i7 + " (expected: power of two)");
        }
        int validateAndCalculatePageShifts = validateAndCalculatePageShifts(i3);
        if (i > 0) {
            this.heapArenas = newArenaArray(i);
            ArrayList arrayList = new ArrayList(this.heapArenas.length);
            for (int i8 = 0; i8 < this.heapArenas.length; i8++) {
                PoolArena.HeapArena heapArena = new PoolArena.HeapArena(this, i3, validateAndCalculatePageShifts, this.chunkSize, i7);
                this.heapArenas[i8] = heapArena;
                arrayList.add(heapArena);
            }
            this.heapArenaMetrics = Collections.unmodifiableList(arrayList);
        } else {
            this.heapArenas = null;
            this.heapArenaMetrics = Collections.emptyList();
        }
        if (i2 > 0) {
            this.directArenas = newArenaArray(i2);
            ArrayList arrayList2 = new ArrayList(this.directArenas.length);
            for (int i9 = 0; i9 < this.directArenas.length; i9++) {
                PoolArena.DirectArena directArena = new PoolArena.DirectArena(this, i3, validateAndCalculatePageShifts, this.chunkSize, i7);
                this.directArenas[i9] = directArena;
                arrayList2.add(directArena);
            }
            this.directArenaMetrics = Collections.unmodifiableList(arrayList2);
        } else {
            this.directArenas = null;
            this.directArenaMetrics = Collections.emptyList();
        }
        this.metric = new PooledByteBufAllocatorMetric(this);
    }

    private static <T> PoolArena<T>[] newArenaArray(int i) {
        return new PoolArena[i];
    }

    private static int validateAndCalculatePageShifts(int i) {
        if (i >= 4096) {
            if (((i - 1) & i) != 0) {
                throw new IllegalArgumentException("pageSize: " + i + " (expected: power of 2)");
            }
            return 31 - Integer.numberOfLeadingZeros(i);
        }
        throw new IllegalArgumentException("pageSize: " + i + " (expected: 4096)");
    }

    private static int validateAndCalculateChunkSize(int i, int i2) {
        if (i2 > 14) {
            throw new IllegalArgumentException("maxOrder: " + i2 + " (expected: 0-14)");
        }
        int i3 = i;
        for (int i4 = i2; i4 > 0; i4--) {
            if (i3 > 536870912) {
                throw new IllegalArgumentException(String.format("pageSize (%d) << maxOrder (%d) must not exceed %d", Integer.valueOf(i), Integer.valueOf(i2), 1073741824));
            }
            i3 <<= 1;
        }
        return i3;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBufAllocator
    protected ByteBuf newHeapBuffer(int i, int i2) {
        ByteBuf unpooledUnsafeHeapByteBuf;
        PoolThreadCache poolThreadCache = this.threadCache.get();
        PoolArena<byte[]> poolArena = poolThreadCache.heapArena;
        if (poolArena != null) {
            unpooledUnsafeHeapByteBuf = poolArena.allocate(poolThreadCache, i, i2);
        } else {
            unpooledUnsafeHeapByteBuf = PlatformDependent.hasUnsafe() ? new UnpooledUnsafeHeapByteBuf(this, i, i2) : new UnpooledHeapByteBuf(this, i, i2);
        }
        return toLeakAwareBuffer(unpooledUnsafeHeapByteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBufAllocator
    protected ByteBuf newDirectBuffer(int i, int i2) {
        ByteBuf newUnsafeDirectByteBuf;
        PoolThreadCache poolThreadCache = this.threadCache.get();
        PoolArena<ByteBuffer> poolArena = poolThreadCache.directArena;
        if (poolArena != null) {
            newUnsafeDirectByteBuf = poolArena.allocate(poolThreadCache, i, i2);
        } else {
            newUnsafeDirectByteBuf = PlatformDependent.hasUnsafe() ? UnsafeByteBufUtil.newUnsafeDirectByteBuf(this, i, i2) : new UnpooledDirectByteBuf(this, i, i2);
        }
        return toLeakAwareBuffer(newUnsafeDirectByteBuf);
    }

    public static int defaultNumHeapArena() {
        return DEFAULT_NUM_HEAP_ARENA;
    }

    public static int defaultNumDirectArena() {
        return DEFAULT_NUM_DIRECT_ARENA;
    }

    public static int defaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    public static int defaultMaxOrder() {
        return DEFAULT_MAX_ORDER;
    }

    public static boolean defaultUseCacheForAllThreads() {
        return DEFAULT_USE_CACHE_FOR_ALL_THREADS;
    }

    public static boolean defaultPreferDirect() {
        return PlatformDependent.directBufferPreferred();
    }

    public static int defaultSmallCacheSize() {
        return DEFAULT_SMALL_CACHE_SIZE;
    }

    public static int defaultNormalCacheSize() {
        return DEFAULT_NORMAL_CACHE_SIZE;
    }

    public static boolean isDirectMemoryCacheAlignmentSupported() {
        return PlatformDependent.hasUnsafe();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public boolean isDirectBufferPooled() {
        return this.directArenas != null;
    }

    @Deprecated
    public boolean hasThreadLocalCache() {
        return this.threadCache.isSet();
    }

    @Deprecated
    public void freeThreadLocalCache() {
        this.threadCache.remove();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class PoolThreadLocalCache extends FastThreadLocal<PoolThreadCache> {
        private final boolean useCacheForAllThreads;

        PoolThreadLocalCache(boolean z) {
            this.useCacheForAllThreads = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public synchronized PoolThreadCache initialValue() {
            EventExecutor currentExecutor;
            PoolArena leastUsedArena = leastUsedArena(PooledByteBufAllocator.this.heapArenas);
            PoolArena leastUsedArena2 = leastUsedArena(PooledByteBufAllocator.this.directArenas);
            Thread currentThread = Thread.currentThread();
            if (!this.useCacheForAllThreads && !(currentThread instanceof FastThreadLocalThread)) {
                return new PoolThreadCache(leastUsedArena, leastUsedArena2, 0, 0, 0, 0);
            }
            PoolThreadCache poolThreadCache = new PoolThreadCache(leastUsedArena, leastUsedArena2, PooledByteBufAllocator.this.smallCacheSize, PooledByteBufAllocator.this.normalCacheSize, PooledByteBufAllocator.DEFAULT_MAX_CACHED_BUFFER_CAPACITY, PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL);
            if (PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL_MILLIS > 0 && (currentExecutor = ThreadExecutorMap.currentExecutor()) != null) {
                currentExecutor.scheduleAtFixedRate(PooledByteBufAllocator.this.trimTask, PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL_MILLIS, PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
            }
            return poolThreadCache;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public void onRemoval(PoolThreadCache poolThreadCache) {
            poolThreadCache.free(false);
        }

        private <T> PoolArena<T> leastUsedArena(PoolArena<T>[] poolArenaArr) {
            if (poolArenaArr == null || poolArenaArr.length == 0) {
                return null;
            }
            PoolArena<T> poolArena = poolArenaArr[0];
            for (int i = 1; i < poolArenaArr.length; i++) {
                PoolArena<T> poolArena2 = poolArenaArr[i];
                if (poolArena2.numThreadCaches.get() < poolArena.numThreadCaches.get()) {
                    poolArena = poolArena2;
                }
            }
            return poolArena;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocatorMetricProvider
    public PooledByteBufAllocatorMetric metric() {
        return this.metric;
    }

    @Deprecated
    public int numHeapArenas() {
        return this.heapArenaMetrics.size();
    }

    @Deprecated
    public int numDirectArenas() {
        return this.directArenaMetrics.size();
    }

    @Deprecated
    public List<PoolArenaMetric> heapArenas() {
        return this.heapArenaMetrics;
    }

    @Deprecated
    public List<PoolArenaMetric> directArenas() {
        return this.directArenaMetrics;
    }

    @Deprecated
    public int numThreadLocalCaches() {
        PoolArena[] poolArenaArr = this.heapArenas;
        if (poolArenaArr == null) {
            poolArenaArr = this.directArenas;
        }
        if (poolArenaArr == null) {
            return 0;
        }
        int i = 0;
        for (PoolArena poolArena : poolArenaArr) {
            i += poolArena.numThreadCaches.get();
        }
        return i;
    }

    @Deprecated
    public int smallCacheSize() {
        return this.smallCacheSize;
    }

    @Deprecated
    public int normalCacheSize() {
        return this.normalCacheSize;
    }

    @Deprecated
    public final int chunkSize() {
        return this.chunkSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long usedHeapMemory() {
        return usedMemory(this.heapArenas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long usedDirectMemory() {
        return usedMemory(this.directArenas);
    }

    private static long usedMemory(PoolArena<?>[] poolArenaArr) {
        if (poolArenaArr == null) {
            return -1L;
        }
        long j = 0;
        for (PoolArena<?> poolArena : poolArenaArr) {
            j += poolArena.numActiveBytes();
            if (j < 0) {
                return Long.MAX_VALUE;
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final PoolThreadCache threadCache() {
        return this.threadCache.get();
    }

    public boolean trimCurrentThreadCache() {
        PoolThreadCache ifExists = this.threadCache.getIfExists();
        if (ifExists == null) {
            return false;
        }
        ifExists.trim();
        return true;
    }

    public String dumpStats() {
        PoolArena<byte[]>[] poolArenaArr = this.heapArenas;
        int length = poolArenaArr == null ? 0 : poolArenaArr.length;
        StringBuilder sb = new StringBuilder(512);
        sb.append(length);
        sb.append(" heap arena(s):");
        sb.append(StringUtil.NEWLINE);
        if (length > 0) {
            for (PoolArena<byte[]> poolArena : this.heapArenas) {
                sb.append(poolArena);
            }
        }
        PoolArena<ByteBuffer>[] poolArenaArr2 = this.directArenas;
        int length2 = poolArenaArr2 == null ? 0 : poolArenaArr2.length;
        sb.append(length2);
        sb.append(" direct arena(s):");
        sb.append(StringUtil.NEWLINE);
        if (length2 > 0) {
            for (PoolArena<ByteBuffer> poolArena2 : this.directArenas) {
                sb.append(poolArena2);
            }
        }
        return sb.toString();
    }
}
