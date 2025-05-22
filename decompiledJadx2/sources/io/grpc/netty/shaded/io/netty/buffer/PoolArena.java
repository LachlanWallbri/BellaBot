package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.LongCounter;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class PoolArena<T> extends SizeClasses implements PoolArenaMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final boolean HAS_UNSAFE = PlatformDependent.hasUnsafe();
    private final LongCounter activeBytesHuge;
    private final LongCounter allocationsHuge;
    private long allocationsNormal;
    private final LongCounter allocationsSmall;
    private final List<PoolChunkListMetric> chunkListMetrics;
    private final LongCounter deallocationsHuge;
    private long deallocationsNormal;
    private long deallocationsSmall;
    final int directMemoryCacheAlignment;
    final int directMemoryCacheAlignmentMask;
    final int numSmallSubpagePools;
    final AtomicInteger numThreadCaches;
    final PooledByteBufAllocator parent;
    private final PoolChunkList<T> q000;
    private final PoolChunkList<T> q025;
    private final PoolChunkList<T> q050;
    private final PoolChunkList<T> q075;
    private final PoolChunkList<T> q100;
    private final PoolChunkList<T> qInit;
    private final PoolSubpage<T>[] smallSubpagePools;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum SizeClass {
        Small,
        Normal
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void destroyChunk(PoolChunk<T> poolChunk);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isDirect();

    protected abstract void memoryCopy(T t, int i, PooledByteBuf<T> pooledByteBuf, int i2);

    protected abstract PooledByteBuf<T> newByteBuf(int i);

    protected abstract PoolChunk<T> newChunk(int i, int i2, int i3, int i4);

    protected abstract PoolChunk<T> newUnpooledChunk(int i);

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numActiveTinyAllocations() {
        return 0L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numTinyAllocations() {
        return 0L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numTinyDeallocations() {
        return 0L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public int numTinySubpages() {
        return 0;
    }

    protected PoolArena(PooledByteBufAllocator pooledByteBufAllocator, int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
        this.allocationsSmall = PlatformDependent.newLongCounter();
        this.allocationsHuge = PlatformDependent.newLongCounter();
        this.activeBytesHuge = PlatformDependent.newLongCounter();
        this.deallocationsHuge = PlatformDependent.newLongCounter();
        this.numThreadCaches = new AtomicInteger();
        this.parent = pooledByteBufAllocator;
        this.directMemoryCacheAlignment = i4;
        this.directMemoryCacheAlignmentMask = i4 - 1;
        this.numSmallSubpagePools = this.nSubpages;
        this.smallSubpagePools = newSubpagePoolArray(this.numSmallSubpagePools);
        int i5 = 0;
        while (true) {
            PoolSubpage<T>[] poolSubpageArr = this.smallSubpagePools;
            if (i5 < poolSubpageArr.length) {
                poolSubpageArr[i5] = newSubpagePoolHead();
                i5++;
            } else {
                this.q100 = new PoolChunkList<>(this, null, 100, Integer.MAX_VALUE, i3);
                this.q075 = new PoolChunkList<>(this, this.q100, 75, 100, i3);
                this.q050 = new PoolChunkList<>(this, this.q075, 50, 100, i3);
                this.q025 = new PoolChunkList<>(this, this.q050, 25, 75, i3);
                this.q000 = new PoolChunkList<>(this, this.q025, 1, 50, i3);
                this.qInit = new PoolChunkList<>(this, this.q000, Integer.MIN_VALUE, 25, i3);
                this.q100.prevList(this.q075);
                this.q075.prevList(this.q050);
                this.q050.prevList(this.q025);
                this.q025.prevList(this.q000);
                this.q000.prevList(null);
                PoolChunkList<T> poolChunkList = this.qInit;
                poolChunkList.prevList(poolChunkList);
                ArrayList arrayList = new ArrayList(6);
                arrayList.add(this.qInit);
                arrayList.add(this.q000);
                arrayList.add(this.q025);
                arrayList.add(this.q050);
                arrayList.add(this.q075);
                arrayList.add(this.q100);
                this.chunkListMetrics = Collections.unmodifiableList(arrayList);
                return;
            }
        }
    }

    private PoolSubpage<T> newSubpagePoolHead() {
        PoolSubpage<T> poolSubpage = new PoolSubpage<>();
        poolSubpage.prev = poolSubpage;
        poolSubpage.next = poolSubpage;
        return poolSubpage;
    }

    private PoolSubpage<T>[] newSubpagePoolArray(int i) {
        return new PoolSubpage[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PooledByteBuf<T> allocate(PoolThreadCache poolThreadCache, int i, int i2) {
        PooledByteBuf<T> newByteBuf = newByteBuf(i2);
        allocate(poolThreadCache, newByteBuf, i);
        return newByteBuf;
    }

    private void allocate(PoolThreadCache poolThreadCache, PooledByteBuf<T> pooledByteBuf, int i) {
        int size2SizeIdx = size2SizeIdx(i);
        if (size2SizeIdx <= this.smallMaxSizeIdx) {
            tcacheAllocateSmall(poolThreadCache, pooledByteBuf, i, size2SizeIdx);
        } else {
            if (size2SizeIdx < this.nSizes) {
                tcacheAllocateNormal(poolThreadCache, pooledByteBuf, i, size2SizeIdx);
                return;
            }
            if (this.directMemoryCacheAlignment > 0) {
                i = normalizeSize(i);
            }
            allocateHuge(pooledByteBuf, i);
        }
    }

    private void tcacheAllocateSmall(PoolThreadCache poolThreadCache, PooledByteBuf<T> pooledByteBuf, int i, int i2) {
        boolean z;
        if (poolThreadCache.allocateSmall(this, pooledByteBuf, i, i2)) {
            return;
        }
        PoolSubpage<T> poolSubpage = this.smallSubpagePools[i2];
        synchronized (poolSubpage) {
            PoolSubpage<T> poolSubpage2 = poolSubpage.next;
            z = poolSubpage2 == poolSubpage;
            if (!z) {
                poolSubpage2.chunk.initBufWithSubpage(pooledByteBuf, null, poolSubpage2.allocate(), i, poolThreadCache);
            }
        }
        if (z) {
            synchronized (this) {
                allocateNormal(pooledByteBuf, i, i2, poolThreadCache);
            }
        }
        incSmallAllocation();
    }

    private void tcacheAllocateNormal(PoolThreadCache poolThreadCache, PooledByteBuf<T> pooledByteBuf, int i, int i2) {
        if (poolThreadCache.allocateNormal(this, pooledByteBuf, i, i2)) {
            return;
        }
        synchronized (this) {
            allocateNormal(pooledByteBuf, i, i2, poolThreadCache);
            this.allocationsNormal++;
        }
    }

    private void allocateNormal(PooledByteBuf<T> pooledByteBuf, int i, int i2, PoolThreadCache poolThreadCache) {
        if (this.q050.allocate(pooledByteBuf, i, i2, poolThreadCache) || this.q025.allocate(pooledByteBuf, i, i2, poolThreadCache) || this.q000.allocate(pooledByteBuf, i, i2, poolThreadCache) || this.qInit.allocate(pooledByteBuf, i, i2, poolThreadCache) || this.q075.allocate(pooledByteBuf, i, i2, poolThreadCache)) {
            return;
        }
        PoolChunk<T> newChunk = newChunk(this.pageSize, this.nPSizes, this.pageShifts, this.chunkSize);
        newChunk.allocate(pooledByteBuf, i, i2, poolThreadCache);
        this.qInit.add(newChunk);
    }

    private void incSmallAllocation() {
        this.allocationsSmall.increment();
    }

    private void allocateHuge(PooledByteBuf<T> pooledByteBuf, int i) {
        PoolChunk<T> newUnpooledChunk = newUnpooledChunk(i);
        this.activeBytesHuge.add(newUnpooledChunk.chunkSize());
        pooledByteBuf.initUnpooled(newUnpooledChunk, i);
        this.allocationsHuge.increment();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, int i, PoolThreadCache poolThreadCache) {
        if (poolChunk.unpooled) {
            int chunkSize = poolChunk.chunkSize();
            destroyChunk(poolChunk);
            this.activeBytesHuge.add(-chunkSize);
            this.deallocationsHuge.increment();
            return;
        }
        SizeClass sizeClass = sizeClass(j);
        if (poolThreadCache == null || !poolThreadCache.add(this, poolChunk, byteBuffer, j, i, sizeClass)) {
            freeChunk(poolChunk, j, i, sizeClass, byteBuffer, false);
        }
    }

    private SizeClass sizeClass(long j) {
        return PoolChunk.isSubpage(j) ? SizeClass.Small : SizeClass.Normal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void freeChunk(PoolChunk<T> poolChunk, long j, int i, SizeClass sizeClass, ByteBuffer byteBuffer, boolean z) {
        boolean z2;
        synchronized (this) {
            z2 = true;
            if (!z) {
                int i2 = C62831.$SwitchMap$io$netty$buffer$PoolArena$SizeClass[sizeClass.ordinal()];
                if (i2 == 1) {
                    this.deallocationsNormal++;
                } else if (i2 == 2) {
                    this.deallocationsSmall++;
                } else {
                    throw new Error();
                }
            }
            if (poolChunk.parent.free(poolChunk, j, i, byteBuffer)) {
                z2 = false;
            }
        }
        if (z2) {
            destroyChunk(poolChunk);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.buffer.PoolArena$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C62831 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$buffer$PoolArena$SizeClass = new int[SizeClass.values().length];

        static {
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[SizeClass.Normal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[SizeClass.Small.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolSubpage<T> findSubpagePoolHead(int i) {
        return this.smallSubpagePools[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reallocate(PooledByteBuf<T> pooledByteBuf, int i, boolean z) {
        int i2 = pooledByteBuf.length;
        if (i2 == i) {
            return;
        }
        PoolChunk<T> poolChunk = pooledByteBuf.chunk;
        ByteBuffer byteBuffer = pooledByteBuf.tmpNioBuf;
        long j = pooledByteBuf.handle;
        T t = pooledByteBuf.memory;
        int i3 = pooledByteBuf.offset;
        int i4 = pooledByteBuf.maxLength;
        allocate(this.parent.threadCache(), pooledByteBuf, i);
        if (i > i2) {
            i = i2;
        } else {
            pooledByteBuf.trimIndicesToCapacity(i);
        }
        memoryCopy(t, i3, pooledByteBuf, i);
        if (z) {
            free(poolChunk, byteBuffer, j, i4, pooledByteBuf.cache);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public int numThreadCaches() {
        return this.numThreadCaches.get();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public int numSmallSubpages() {
        return this.smallSubpagePools.length;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public int numChunkLists() {
        return this.chunkListMetrics.size();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public List<PoolSubpageMetric> tinySubpages() {
        return Collections.emptyList();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public List<PoolSubpageMetric> smallSubpages() {
        return subPageMetricList(this.smallSubpagePools);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public List<PoolChunkListMetric> chunkLists() {
        return this.chunkListMetrics;
    }

    private static List<PoolSubpageMetric> subPageMetricList(PoolSubpage<?>[] poolSubpageArr) {
        ArrayList arrayList = new ArrayList();
        for (PoolSubpage<?> poolSubpage : poolSubpageArr) {
            if (poolSubpage.next != poolSubpage) {
                PoolSubpage poolSubpage2 = poolSubpage.next;
                do {
                    arrayList.add(poolSubpage2);
                    poolSubpage2 = poolSubpage2.next;
                } while (poolSubpage2 != poolSubpage);
            }
        }
        return arrayList;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numAllocations() {
        long j;
        synchronized (this) {
            j = this.allocationsNormal;
        }
        return this.allocationsSmall.value() + j + this.allocationsHuge.value();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numSmallAllocations() {
        return this.allocationsSmall.value();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public synchronized long numNormalAllocations() {
        return this.allocationsNormal;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numDeallocations() {
        long j;
        synchronized (this) {
            j = this.deallocationsSmall + this.deallocationsNormal;
        }
        return j + this.deallocationsHuge.value();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public synchronized long numSmallDeallocations() {
        return this.deallocationsSmall;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public synchronized long numNormalDeallocations() {
        return this.deallocationsNormal;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numHugeAllocations() {
        return this.allocationsHuge.value();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numHugeDeallocations() {
        return this.deallocationsHuge.value();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numActiveAllocations() {
        long j;
        long value = (this.allocationsSmall.value() + this.allocationsHuge.value()) - this.deallocationsHuge.value();
        synchronized (this) {
            j = value + (this.allocationsNormal - (this.deallocationsSmall + this.deallocationsNormal));
        }
        return Math.max(j, 0L);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numActiveSmallAllocations() {
        return Math.max(numSmallAllocations() - numSmallDeallocations(), 0L);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numActiveNormalAllocations() {
        long j;
        synchronized (this) {
            j = this.allocationsNormal - this.deallocationsNormal;
        }
        return Math.max(j, 0L);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numActiveHugeAllocations() {
        return Math.max(numHugeAllocations() - numHugeDeallocations(), 0L);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArenaMetric
    public long numActiveBytes() {
        long value = this.activeBytesHuge.value();
        synchronized (this) {
            for (int i = 0; i < this.chunkListMetrics.size(); i++) {
                while (this.chunkListMetrics.get(i).iterator().hasNext()) {
                    value += r3.next().chunkSize();
                }
            }
        }
        return Math.max(0L, value);
    }

    public synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("Chunk(s) at 0~25%:");
        sb.append(StringUtil.NEWLINE);
        sb.append(this.qInit);
        sb.append(StringUtil.NEWLINE);
        sb.append("Chunk(s) at 0~50%:");
        sb.append(StringUtil.NEWLINE);
        sb.append(this.q000);
        sb.append(StringUtil.NEWLINE);
        sb.append("Chunk(s) at 25~75%:");
        sb.append(StringUtil.NEWLINE);
        sb.append(this.q025);
        sb.append(StringUtil.NEWLINE);
        sb.append("Chunk(s) at 50~100%:");
        sb.append(StringUtil.NEWLINE);
        sb.append(this.q050);
        sb.append(StringUtil.NEWLINE);
        sb.append("Chunk(s) at 75~100%:");
        sb.append(StringUtil.NEWLINE);
        sb.append(this.q075);
        sb.append(StringUtil.NEWLINE);
        sb.append("Chunk(s) at 100%:");
        sb.append(StringUtil.NEWLINE);
        sb.append(this.q100);
        sb.append(StringUtil.NEWLINE);
        sb.append("small subpages:");
        appendPoolSubPages(sb, this.smallSubpagePools);
        sb.append(StringUtil.NEWLINE);
        return sb.toString();
    }

    private static void appendPoolSubPages(StringBuilder sb, PoolSubpage<?>[] poolSubpageArr) {
        for (int i = 0; i < poolSubpageArr.length; i++) {
            PoolSubpage<?> poolSubpage = poolSubpageArr[i];
            if (poolSubpage.next != poolSubpage) {
                sb.append(StringUtil.NEWLINE);
                sb.append(i);
                sb.append(": ");
                PoolSubpage poolSubpage2 = poolSubpage.next;
                do {
                    sb.append(poolSubpage2);
                    poolSubpage2 = poolSubpage2.next;
                } while (poolSubpage2 != poolSubpage);
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            super.finalize();
            destroyPoolSubPages(this.smallSubpagePools);
            destroyPoolChunkLists(this.qInit, this.q000, this.q025, this.q050, this.q075, this.q100);
        } catch (Throwable th) {
            destroyPoolSubPages(this.smallSubpagePools);
            destroyPoolChunkLists(this.qInit, this.q000, this.q025, this.q050, this.q075, this.q100);
            throw th;
        }
    }

    private static void destroyPoolSubPages(PoolSubpage<?>[] poolSubpageArr) {
        for (PoolSubpage<?> poolSubpage : poolSubpageArr) {
            poolSubpage.destroy();
        }
    }

    private void destroyPoolChunkLists(PoolChunkList<T>... poolChunkListArr) {
        for (PoolChunkList<T> poolChunkList : poolChunkListArr) {
            poolChunkList.destroy(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class HeapArena extends PoolArena<byte[]> {
        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected void destroyChunk(PoolChunk<byte[]> poolChunk) {
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        boolean isDirect() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public HeapArena(PooledByteBufAllocator pooledByteBufAllocator, int i, int i2, int i3, int i4) {
            super(pooledByteBufAllocator, i, i2, i3, i4);
        }

        private static byte[] newByteArray(int i) {
            return PlatformDependent.allocateUninitializedArray(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected PoolChunk<byte[]> newChunk(int i, int i2, int i3, int i4) {
            return new PoolChunk<>(this, newByteArray(i4), i, i3, i4, i2, 0);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected PoolChunk<byte[]> newUnpooledChunk(int i) {
            return new PoolChunk<>(this, newByteArray(i), i, 0);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected PooledByteBuf<byte[]> newByteBuf(int i) {
            return HAS_UNSAFE ? PooledUnsafeHeapByteBuf.newUnsafeInstance(i) : PooledHeapByteBuf.newInstance(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        public void memoryCopy(byte[] bArr, int i, PooledByteBuf<byte[]> pooledByteBuf, int i2) {
            if (i2 == 0) {
                return;
            }
            System.arraycopy(bArr, i, pooledByteBuf.memory, pooledByteBuf.offset, i2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class DirectArena extends PoolArena<ByteBuffer> {
        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        boolean isDirect() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public DirectArena(PooledByteBufAllocator pooledByteBufAllocator, int i, int i2, int i3, int i4) {
            super(pooledByteBufAllocator, i, i2, i3, i4);
        }

        int offsetCacheLine(ByteBuffer byteBuffer) {
            return this.directMemoryCacheAlignment - (HAS_UNSAFE ? (int) (PlatformDependent.directBufferAddress(byteBuffer) & this.directMemoryCacheAlignmentMask) : 0);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected PoolChunk<ByteBuffer> newChunk(int i, int i2, int i3, int i4) {
            if (this.directMemoryCacheAlignment == 0) {
                return new PoolChunk<>(this, allocateDirect(i4), i, i3, i4, i2, 0);
            }
            ByteBuffer allocateDirect = allocateDirect(this.directMemoryCacheAlignment + i4);
            return new PoolChunk<>(this, allocateDirect, i, i3, i4, i2, offsetCacheLine(allocateDirect));
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected PoolChunk<ByteBuffer> newUnpooledChunk(int i) {
            if (this.directMemoryCacheAlignment == 0) {
                return new PoolChunk<>(this, allocateDirect(i), i, 0);
            }
            ByteBuffer allocateDirect = allocateDirect(this.directMemoryCacheAlignment + i);
            return new PoolChunk<>(this, allocateDirect, i, offsetCacheLine(allocateDirect));
        }

        private static ByteBuffer allocateDirect(int i) {
            return PlatformDependent.useDirectBufferNoCleaner() ? PlatformDependent.allocateDirectNoCleaner(i) : ByteBuffer.allocateDirect(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected void destroyChunk(PoolChunk<ByteBuffer> poolChunk) {
            if (PlatformDependent.useDirectBufferNoCleaner()) {
                PlatformDependent.freeDirectNoCleaner(poolChunk.memory);
            } else {
                PlatformDependent.freeDirectBuffer(poolChunk.memory);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        protected PooledByteBuf<ByteBuffer> newByteBuf(int i) {
            if (HAS_UNSAFE) {
                return PooledUnsafeDirectByteBuf.newInstance(i);
            }
            return PooledDirectByteBuf.newInstance(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolArena
        public void memoryCopy(ByteBuffer byteBuffer, int i, PooledByteBuf<ByteBuffer> pooledByteBuf, int i2) {
            if (i2 == 0) {
                return;
            }
            if (HAS_UNSAFE) {
                PlatformDependent.copyMemory(PlatformDependent.directBufferAddress(byteBuffer) + i, PlatformDependent.directBufferAddress(pooledByteBuf.memory) + pooledByteBuf.offset, i2);
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            ByteBuffer internalNioBuffer = pooledByteBuf.internalNioBuffer();
            duplicate.position(i).limit(i + i2);
            internalNioBuffer.position(pooledByteBuf.offset);
            internalNioBuffer.put(duplicate);
        }
    }
}
