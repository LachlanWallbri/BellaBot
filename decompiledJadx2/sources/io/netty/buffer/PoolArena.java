package io.netty.buffer;

import io.netty.util.internal.LongCounter;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public abstract class PoolArena<T> implements PoolArenaMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final boolean HAS_UNSAFE = PlatformDependent.hasUnsafe();
    static final int numTinySubpagePools = 32;
    private long allocationsNormal;
    private final List<PoolChunkListMetric> chunkListMetrics;
    final int chunkSize;
    private long deallocationsNormal;
    private long deallocationsSmall;
    private long deallocationsTiny;
    final int directMemoryCacheAlignment;
    final int directMemoryCacheAlignmentMask;
    private final int maxOrder;
    final int numSmallSubpagePools;
    final int pageShifts;
    final int pageSize;
    final PooledByteBufAllocator parent;
    private final PoolChunkList<T> q000;
    private final PoolChunkList<T> q025;
    private final PoolChunkList<T> q050;
    private final PoolChunkList<T> q075;
    private final PoolChunkList<T> q100;
    private final PoolChunkList<T> qInit;
    private final PoolSubpage<T>[] smallSubpagePools;
    final int subpageOverflowMask;
    private final LongCounter allocationsTiny = PlatformDependent.newLongCounter();
    private final LongCounter allocationsSmall = PlatformDependent.newLongCounter();
    private final LongCounter allocationsHuge = PlatformDependent.newLongCounter();
    private final LongCounter activeBytesHuge = PlatformDependent.newLongCounter();
    private final LongCounter deallocationsHuge = PlatformDependent.newLongCounter();
    final AtomicInteger numThreadCaches = new AtomicInteger();
    private final PoolSubpage<T>[] tinySubpagePools = newSubpagePoolArray(32);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public enum SizeClass {
        Tiny,
        Small,
        Normal
    }

    static boolean isTiny(int i) {
        return (i & (-512)) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smallIdx(int i) {
        int i2 = i >>> 10;
        int i3 = 0;
        while (i2 != 0) {
            i2 >>>= 1;
            i3++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tinyIdx(int i) {
        return i >>> 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void destroyChunk(PoolChunk<T> poolChunk);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isDirect();

    protected abstract void memoryCopy(T t, int i, T t2, int i2, int i3);

    protected abstract PooledByteBuf<T> newByteBuf(int i);

    protected abstract PoolChunk<T> newChunk(int i, int i2, int i3, int i4);

    protected abstract PoolChunk<T> newUnpooledChunk(int i);

    protected PoolArena(PooledByteBufAllocator pooledByteBufAllocator, int i, int i2, int i3, int i4, int i5) {
        this.parent = pooledByteBufAllocator;
        this.pageSize = i;
        this.maxOrder = i2;
        this.pageShifts = i3;
        this.chunkSize = i4;
        this.directMemoryCacheAlignment = i5;
        this.directMemoryCacheAlignmentMask = i5 - 1;
        this.subpageOverflowMask = ~(i - 1);
        int i6 = 0;
        int i7 = 0;
        while (true) {
            PoolSubpage<T>[] poolSubpageArr = this.tinySubpagePools;
            if (i7 >= poolSubpageArr.length) {
                break;
            }
            poolSubpageArr[i7] = newSubpagePoolHead(i);
            i7++;
        }
        int i8 = i3 - 9;
        this.numSmallSubpagePools = i8;
        this.smallSubpagePools = newSubpagePoolArray(i8);
        while (true) {
            PoolSubpage<T>[] poolSubpageArr2 = this.smallSubpagePools;
            if (i6 < poolSubpageArr2.length) {
                poolSubpageArr2[i6] = newSubpagePoolHead(i);
                i6++;
            } else {
                this.q100 = new PoolChunkList<>(this, null, 100, Integer.MAX_VALUE, i4);
                this.q075 = new PoolChunkList<>(this, this.q100, 75, 100, i4);
                this.q050 = new PoolChunkList<>(this, this.q075, 50, 100, i4);
                this.q025 = new PoolChunkList<>(this, this.q050, 25, 75, i4);
                this.q000 = new PoolChunkList<>(this, this.q025, 1, 50, i4);
                this.qInit = new PoolChunkList<>(this, this.q000, Integer.MIN_VALUE, 25, i4);
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

    private PoolSubpage<T> newSubpagePoolHead(int i) {
        PoolSubpage<T> poolSubpage = new PoolSubpage<>(i);
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

    boolean isTinyOrSmall(int i) {
        return (i & this.subpageOverflowMask) == 0;
    }

    private void allocate(PoolThreadCache poolThreadCache, PooledByteBuf<T> pooledByteBuf, int i) {
        int smallIdx;
        PoolSubpage<T>[] poolSubpageArr;
        int normalizeCapacity = normalizeCapacity(i);
        if (isTinyOrSmall(normalizeCapacity)) {
            boolean isTiny = isTiny(normalizeCapacity);
            if (isTiny) {
                if (poolThreadCache.allocateTiny(this, pooledByteBuf, i, normalizeCapacity)) {
                    return;
                }
                smallIdx = tinyIdx(normalizeCapacity);
                poolSubpageArr = this.tinySubpagePools;
            } else {
                if (poolThreadCache.allocateSmall(this, pooledByteBuf, i, normalizeCapacity)) {
                    return;
                }
                smallIdx = smallIdx(normalizeCapacity);
                poolSubpageArr = this.smallSubpagePools;
            }
            PoolSubpage<T> poolSubpage = poolSubpageArr[smallIdx];
            synchronized (poolSubpage) {
                PoolSubpage<T> poolSubpage2 = poolSubpage.next;
                if (poolSubpage2 != poolSubpage) {
                    poolSubpage2.chunk.initBufWithSubpage(pooledByteBuf, poolSubpage2.allocate(), i);
                    incTinySmallAllocation(isTiny);
                    return;
                } else {
                    synchronized (this) {
                        allocateNormal(pooledByteBuf, i, normalizeCapacity);
                    }
                    incTinySmallAllocation(isTiny);
                    return;
                }
            }
        }
        if (normalizeCapacity <= this.chunkSize) {
            if (poolThreadCache.allocateNormal(this, pooledByteBuf, i, normalizeCapacity)) {
                return;
            }
            synchronized (this) {
                allocateNormal(pooledByteBuf, i, normalizeCapacity);
                this.allocationsNormal++;
            }
            return;
        }
        allocateHuge(pooledByteBuf, i);
    }

    private void allocateNormal(PooledByteBuf<T> pooledByteBuf, int i, int i2) {
        if (this.q050.allocate(pooledByteBuf, i, i2) || this.q025.allocate(pooledByteBuf, i, i2) || this.q000.allocate(pooledByteBuf, i, i2) || this.qInit.allocate(pooledByteBuf, i, i2) || this.q075.allocate(pooledByteBuf, i, i2)) {
            return;
        }
        PoolChunk<T> newChunk = newChunk(this.pageSize, this.maxOrder, this.pageShifts, this.chunkSize);
        newChunk.initBuf(pooledByteBuf, newChunk.allocate(i2), i);
        this.qInit.add(newChunk);
    }

    private void incTinySmallAllocation(boolean z) {
        if (z) {
            this.allocationsTiny.increment();
        } else {
            this.allocationsSmall.increment();
        }
    }

    private void allocateHuge(PooledByteBuf<T> pooledByteBuf, int i) {
        PoolChunk<T> newUnpooledChunk = newUnpooledChunk(i);
        this.activeBytesHuge.add(newUnpooledChunk.chunkSize());
        pooledByteBuf.initUnpooled(newUnpooledChunk, i);
        this.allocationsHuge.increment();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free(PoolChunk<T> poolChunk, long j, int i, PoolThreadCache poolThreadCache) {
        if (poolChunk.unpooled) {
            int chunkSize = poolChunk.chunkSize();
            destroyChunk(poolChunk);
            this.activeBytesHuge.add(-chunkSize);
            this.deallocationsHuge.increment();
            return;
        }
        SizeClass sizeClass = sizeClass(i);
        if (poolThreadCache == null || !poolThreadCache.add(this, poolChunk, j, i, sizeClass)) {
            freeChunk(poolChunk, j, sizeClass);
        }
    }

    private SizeClass sizeClass(int i) {
        if (isTinyOrSmall(i)) {
            return isTiny(i) ? SizeClass.Tiny : SizeClass.Small;
        }
        return SizeClass.Normal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* renamed from: io.netty.buffer.PoolArena$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C69091 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$buffer$PoolArena$SizeClass;

        static {
            int[] iArr = new int[SizeClass.values().length];
            $SwitchMap$io$netty$buffer$PoolArena$SizeClass = iArr;
            try {
                iArr[SizeClass.Normal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[SizeClass.Small.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[SizeClass.Tiny.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void freeChunk(PoolChunk<T> poolChunk, long j, SizeClass sizeClass) {
        boolean z;
        synchronized (this) {
            int i = C69091.$SwitchMap$io$netty$buffer$PoolArena$SizeClass[sizeClass.ordinal()];
            z = true;
            if (i == 1) {
                this.deallocationsNormal++;
            } else if (i == 2) {
                this.deallocationsSmall++;
            } else if (i == 3) {
                this.deallocationsTiny++;
            } else {
                throw new Error();
            }
            if (poolChunk.parent.free(poolChunk, j)) {
                z = false;
            }
        }
        if (z) {
            destroyChunk(poolChunk);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolSubpage<T> findSubpagePoolHead(int i) {
        PoolSubpage<T>[] poolSubpageArr;
        int i2;
        if (isTiny(i)) {
            i2 = i >>> 4;
            poolSubpageArr = this.tinySubpagePools;
        } else {
            int i3 = 0;
            int i4 = i >>> 10;
            while (i4 != 0) {
                i4 >>>= 1;
                i3++;
            }
            int i5 = i3;
            poolSubpageArr = this.smallSubpagePools;
            i2 = i5;
        }
        return poolSubpageArr[i2];
    }

    int normalizeCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity: " + i + " (expected: 0+)");
        }
        if (i >= this.chunkSize) {
            return this.directMemoryCacheAlignment == 0 ? i : alignCapacity(i);
        }
        if (isTiny(i)) {
            if (this.directMemoryCacheAlignment > 0) {
                return alignCapacity(i);
            }
            return (i & 15) == 0 ? i : (i & (-16)) + 16;
        }
        int i2 = i - 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = (i6 | (i6 >>> 16)) + 1;
        return i7 < 0 ? i7 >>> 1 : i7;
    }

    int alignCapacity(int i) {
        int i2 = this.directMemoryCacheAlignmentMask & i;
        return i2 == 0 ? i : (i + this.directMemoryCacheAlignment) - i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void reallocate(PooledByteBuf<T> pooledByteBuf, int i, boolean z) {
        if (i < 0 || i > pooledByteBuf.maxCapacity()) {
            throw new IllegalArgumentException("newCapacity: " + i);
        }
        int i2 = pooledByteBuf.length;
        if (i2 == i) {
            return;
        }
        PoolChunk<T> poolChunk = pooledByteBuf.chunk;
        long j = pooledByteBuf.handle;
        T t = pooledByteBuf.memory;
        int i3 = pooledByteBuf.offset;
        int i4 = pooledByteBuf.maxLength;
        int readerIndex = pooledByteBuf.readerIndex();
        int writerIndex = pooledByteBuf.writerIndex();
        allocate(this.parent.threadCache(), pooledByteBuf, i);
        if (i > i2) {
            memoryCopy(t, i3, pooledByteBuf.memory, pooledByteBuf.offset, i2);
        } else if (i < i2) {
            if (readerIndex < i) {
                if (writerIndex <= i) {
                    i = writerIndex;
                }
                memoryCopy(t, i3 + readerIndex, pooledByteBuf.memory, pooledByteBuf.offset + readerIndex, i - readerIndex);
                writerIndex = i;
            } else {
                writerIndex = i;
                pooledByteBuf.setIndex(i, writerIndex);
                if (z) {
                    return;
                }
                free(poolChunk, j, i4, pooledByteBuf.cache);
                return;
            }
        }
        i = readerIndex;
        pooledByteBuf.setIndex(i, writerIndex);
        if (z) {
        }
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public int numThreadCaches() {
        return this.numThreadCaches.get();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public int numTinySubpages() {
        return this.tinySubpagePools.length;
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public int numSmallSubpages() {
        return this.smallSubpagePools.length;
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public int numChunkLists() {
        return this.chunkListMetrics.size();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public List<PoolSubpageMetric> tinySubpages() {
        return subPageMetricList(this.tinySubpagePools);
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public List<PoolSubpageMetric> smallSubpages() {
        return subPageMetricList(this.smallSubpagePools);
    }

    @Override // io.netty.buffer.PoolArenaMetric
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

    @Override // io.netty.buffer.PoolArenaMetric
    public long numAllocations() {
        long j;
        synchronized (this) {
            j = this.allocationsNormal;
        }
        return this.allocationsTiny.value() + this.allocationsSmall.value() + j + this.allocationsHuge.value();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numTinyAllocations() {
        return this.allocationsTiny.value();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numSmallAllocations() {
        return this.allocationsSmall.value();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public synchronized long numNormalAllocations() {
        return this.allocationsNormal;
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numDeallocations() {
        long j;
        synchronized (this) {
            j = this.deallocationsTiny + this.deallocationsSmall + this.deallocationsNormal;
        }
        return j + this.deallocationsHuge.value();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public synchronized long numTinyDeallocations() {
        return this.deallocationsTiny;
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public synchronized long numSmallDeallocations() {
        return this.deallocationsSmall;
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public synchronized long numNormalDeallocations() {
        return this.deallocationsNormal;
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numHugeAllocations() {
        return this.allocationsHuge.value();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numHugeDeallocations() {
        return this.deallocationsHuge.value();
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numActiveAllocations() {
        long j;
        long value = ((this.allocationsTiny.value() + this.allocationsSmall.value()) + this.allocationsHuge.value()) - this.deallocationsHuge.value();
        synchronized (this) {
            j = value + (this.allocationsNormal - ((this.deallocationsTiny + this.deallocationsSmall) + this.deallocationsNormal));
        }
        return Math.max(j, 0L);
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numActiveTinyAllocations() {
        return Math.max(numTinyAllocations() - numTinyDeallocations(), 0L);
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numActiveSmallAllocations() {
        return Math.max(numSmallAllocations() - numSmallDeallocations(), 0L);
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numActiveNormalAllocations() {
        long j;
        synchronized (this) {
            j = this.allocationsNormal - this.deallocationsNormal;
        }
        return Math.max(j, 0L);
    }

    @Override // io.netty.buffer.PoolArenaMetric
    public long numActiveHugeAllocations() {
        return Math.max(numHugeAllocations() - numHugeDeallocations(), 0L);
    }

    @Override // io.netty.buffer.PoolArenaMetric
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
        sb.append("tiny subpages:");
        appendPoolSubPages(sb, this.tinySubpagePools);
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
            destroyPoolSubPages(this.tinySubpagePools);
            destroyPoolChunkLists(this.qInit, this.q000, this.q025, this.q050, this.q075, this.q100);
        } catch (Throwable th) {
            destroyPoolSubPages(this.smallSubpagePools);
            destroyPoolSubPages(this.tinySubpagePools);
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
      classes7.dex
     */
    /* loaded from: classes.dex */
    static final class HeapArena extends PoolArena<byte[]> {
        @Override // io.netty.buffer.PoolArena
        protected void destroyChunk(PoolChunk<byte[]> poolChunk) {
        }

        @Override // io.netty.buffer.PoolArena
        boolean isDirect() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public HeapArena(PooledByteBufAllocator pooledByteBufAllocator, int i, int i2, int i3, int i4, int i5) {
            super(pooledByteBufAllocator, i, i2, i3, i4, i5);
        }

        private static byte[] newByteArray(int i) {
            return PlatformDependent.allocateUninitializedArray(i);
        }

        @Override // io.netty.buffer.PoolArena
        protected PoolChunk<byte[]> newChunk(int i, int i2, int i3, int i4) {
            return new PoolChunk<>(this, newByteArray(i4), i, i2, i3, i4, 0);
        }

        @Override // io.netty.buffer.PoolArena
        protected PoolChunk<byte[]> newUnpooledChunk(int i) {
            return new PoolChunk<>(this, newByteArray(i), i, 0);
        }

        @Override // io.netty.buffer.PoolArena
        protected PooledByteBuf<byte[]> newByteBuf(int i) {
            return HAS_UNSAFE ? PooledUnsafeHeapByteBuf.newUnsafeInstance(i) : PooledHeapByteBuf.newInstance(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.buffer.PoolArena
        public void memoryCopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
            if (i3 == 0) {
                return;
            }
            System.arraycopy(bArr, i, bArr2, i2, i3);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    static final class DirectArena extends PoolArena<ByteBuffer> {
        @Override // io.netty.buffer.PoolArena
        boolean isDirect() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public DirectArena(PooledByteBufAllocator pooledByteBufAllocator, int i, int i2, int i3, int i4, int i5) {
            super(pooledByteBufAllocator, i, i2, i3, i4, i5);
        }

        private int offsetCacheLine(ByteBuffer byteBuffer) {
            if (HAS_UNSAFE) {
                return (int) (PlatformDependent.directBufferAddress(byteBuffer) & this.directMemoryCacheAlignmentMask);
            }
            return 0;
        }

        @Override // io.netty.buffer.PoolArena
        protected PoolChunk<ByteBuffer> newChunk(int i, int i2, int i3, int i4) {
            if (this.directMemoryCacheAlignment == 0) {
                return new PoolChunk<>(this, allocateDirect(i4), i, i2, i3, i4, 0);
            }
            ByteBuffer allocateDirect = allocateDirect(this.directMemoryCacheAlignment + i4);
            return new PoolChunk<>(this, allocateDirect, i, i2, i3, i4, offsetCacheLine(allocateDirect));
        }

        @Override // io.netty.buffer.PoolArena
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

        @Override // io.netty.buffer.PoolArena
        protected void destroyChunk(PoolChunk<ByteBuffer> poolChunk) {
            if (PlatformDependent.useDirectBufferNoCleaner()) {
                PlatformDependent.freeDirectNoCleaner(poolChunk.memory);
            } else {
                PlatformDependent.freeDirectBuffer(poolChunk.memory);
            }
        }

        @Override // io.netty.buffer.PoolArena
        protected PooledByteBuf<ByteBuffer> newByteBuf(int i) {
            if (HAS_UNSAFE) {
                return PooledUnsafeDirectByteBuf.newInstance(i);
            }
            return PooledDirectByteBuf.newInstance(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.buffer.PoolArena
        public void memoryCopy(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
            if (i3 == 0) {
                return;
            }
            if (HAS_UNSAFE) {
                PlatformDependent.copyMemory(PlatformDependent.directBufferAddress(byteBuffer) + i, PlatformDependent.directBufferAddress(byteBuffer2) + i2, i3);
                return;
            }
            ByteBuffer duplicate = byteBuffer.duplicate();
            ByteBuffer duplicate2 = byteBuffer2.duplicate();
            duplicate.position(i).limit(i + i3);
            duplicate2.position(i2);
            duplicate2.put(duplicate);
        }
    }
}
