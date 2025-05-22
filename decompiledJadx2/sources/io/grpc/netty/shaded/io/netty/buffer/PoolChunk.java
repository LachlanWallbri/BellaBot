package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.collection.IntObjectHashMap;
import io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PoolChunk<T> implements PoolChunkMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BITMAP_IDX_BIT_LENGTH = 32;
    private static final int INUSED_BIT_LENGTH = 1;
    static final int IS_SUBPAGE_SHIFT = 32;
    static final int IS_USED_SHIFT = 33;
    private static final int OFFSET_BIT_LENGTH = 15;
    static final int RUN_OFFSET_SHIFT = 49;
    private static final int SIZE_BIT_LENGTH = 15;
    static final int SIZE_SHIFT = 34;
    private static final int SUBPAGE_BIT_LENGTH = 1;
    final PoolArena<T> arena;
    private final Deque<ByteBuffer> cachedNioBuffers;
    private final int chunkSize;
    int freeBytes;
    final T memory;
    PoolChunk<T> next;
    final int offset;
    private final int pageShifts;
    private final int pageSize;
    PoolChunkList<T> parent;
    PoolChunk<T> prev;
    private final PriorityQueue<Long>[] runsAvail;
    private final IntObjectMap<Long> runsAvailMap;
    private final PoolSubpage<T>[] subpages;
    final boolean unpooled;

    static int bitmapIdx(long j) {
        return (int) j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSubpage(long j) {
        return ((j >> 32) & 1) == 1;
    }

    static boolean isUsed(long j) {
        return ((j >> 33) & 1) == 1;
    }

    private static int lastPage(int i, int i2) {
        return (i + i2) - 1;
    }

    static int runOffset(long j) {
        return (int) (j >> 49);
    }

    static int runPages(long j) {
        return (int) ((j >> 34) & 32767);
    }

    private static long toRunHandle(int i, int i2, int i3) {
        return (i2 << 34) | (i << 49) | (i3 << 33);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolChunk(PoolArena<T> poolArena, T t, int i, int i2, int i3, int i4, int i5) {
        this.unpooled = false;
        this.arena = poolArena;
        this.memory = t;
        this.pageSize = i;
        this.pageShifts = i2;
        this.chunkSize = i3;
        this.offset = i5;
        this.freeBytes = i3;
        this.runsAvail = newRunsAvailqueueArray(i4);
        this.runsAvailMap = new IntObjectHashMap();
        int i6 = i3 >> i2;
        this.subpages = new PoolSubpage[i6];
        insertAvailRun(0, i6, Long.valueOf(i6 << 34));
        this.cachedNioBuffers = new ArrayDeque(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolChunk(PoolArena<T> poolArena, T t, int i, int i2) {
        this.unpooled = true;
        this.arena = poolArena;
        this.memory = t;
        this.offset = i2;
        this.pageSize = 0;
        this.pageShifts = 0;
        this.runsAvailMap = null;
        this.runsAvail = null;
        this.subpages = null;
        this.chunkSize = i;
        this.cachedNioBuffers = null;
    }

    private static PriorityQueue<Long>[] newRunsAvailqueueArray(int i) {
        PriorityQueue<Long>[] priorityQueueArr = new PriorityQueue[i];
        for (int i2 = 0; i2 < priorityQueueArr.length; i2++) {
            priorityQueueArr[i2] = new PriorityQueue<>();
        }
        return priorityQueueArr;
    }

    private void insertAvailRun(int i, int i2, Long l) {
        this.runsAvail[this.arena.pages2pageIdxFloor(i2)].offer(l);
        insertAvailRun0(i, l);
        if (i2 > 1) {
            insertAvailRun0(lastPage(i, i2), l);
        }
    }

    private void insertAvailRun0(int i, Long l) {
        this.runsAvailMap.put(i, (int) l);
    }

    private void removeAvailRun(long j) {
        removeAvailRun(this.runsAvail[this.arena.pages2pageIdxFloor(runPages(j))], j);
    }

    private void removeAvailRun(PriorityQueue<Long> priorityQueue, long j) {
        priorityQueue.remove(Long.valueOf(j));
        int runOffset = runOffset(j);
        int runPages = runPages(j);
        this.runsAvailMap.remove(runOffset);
        if (runPages > 1) {
            this.runsAvailMap.remove(lastPage(runOffset, runPages));
        }
    }

    private Long getAvailRunByOffset(int i) {
        return this.runsAvailMap.get(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolChunkMetric
    public int usage() {
        int i;
        synchronized (this.arena) {
            i = this.freeBytes;
        }
        return usage(i);
    }

    private int usage(int i) {
        if (i == 0) {
            return 100;
        }
        int i2 = (int) ((i * 100) / this.chunkSize);
        if (i2 == 0) {
            return 99;
        }
        return 100 - i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean allocate(PooledByteBuf<T> pooledByteBuf, int i, int i2, PoolThreadCache poolThreadCache) {
        long allocateRun;
        if (i2 <= this.arena.smallMaxSizeIdx) {
            allocateRun = allocateSubpage(i2);
            if (allocateRun < 0) {
                return false;
            }
        } else {
            allocateRun = allocateRun(this.arena.sizeIdx2size(i2));
            if (allocateRun < 0) {
                return false;
            }
        }
        long j = allocateRun;
        Deque<ByteBuffer> deque = this.cachedNioBuffers;
        initBuf(pooledByteBuf, deque != null ? deque.pollLast() : null, j, i, poolThreadCache);
        return true;
    }

    private long allocateRun(int i) {
        int i2 = i >> this.pageShifts;
        int pages2pageIdx = this.arena.pages2pageIdx(i2);
        synchronized (this.runsAvail) {
            int runFirstBestFit = runFirstBestFit(pages2pageIdx);
            if (runFirstBestFit == -1) {
                return -1L;
            }
            PriorityQueue<Long> priorityQueue = this.runsAvail[runFirstBestFit];
            long longValue = priorityQueue.poll().longValue();
            removeAvailRun(priorityQueue, longValue);
            if (longValue != -1) {
                longValue = splitLargeRun(longValue, i2);
            }
            this.freeBytes -= runSize(this.pageShifts, longValue);
            return longValue;
        }
    }

    private int calculateRunSize(int i) {
        int i2;
        int i3 = 1 << (this.pageShifts - 4);
        int sizeIdx2size = this.arena.sizeIdx2size(i);
        int i4 = 0;
        do {
            i4 += this.pageSize;
            i2 = i4 / sizeIdx2size;
            if (i2 >= i3) {
                break;
            }
        } while (i4 != i2 * sizeIdx2size);
        while (i2 > i3) {
            i4 -= this.pageSize;
            i2 = i4 / sizeIdx2size;
        }
        return i4;
    }

    private int runFirstBestFit(int i) {
        if (this.freeBytes == this.chunkSize) {
            return this.arena.nPSizes - 1;
        }
        while (i < this.arena.nPSizes) {
            PriorityQueue<Long> priorityQueue = this.runsAvail[i];
            if (priorityQueue != null && !priorityQueue.isEmpty()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private long splitLargeRun(long j, int i) {
        int runPages = runPages(j) - i;
        if (runPages <= 0) {
            return j | 8589934592L;
        }
        int runOffset = runOffset(j);
        int i2 = runOffset + i;
        insertAvailRun(i2, runPages, Long.valueOf(toRunHandle(i2, runPages, 0)));
        return toRunHandle(runOffset, i, 1);
    }

    private long allocateSubpage(int i) {
        PoolSubpage<T> findSubpagePoolHead = this.arena.findSubpagePoolHead(i);
        synchronized (findSubpagePoolHead) {
            long allocateRun = allocateRun(calculateRunSize(i));
            if (allocateRun < 0) {
                return -1L;
            }
            int runOffset = runOffset(allocateRun);
            PoolSubpage<T> poolSubpage = new PoolSubpage<>(findSubpagePoolHead, this, this.pageShifts, runOffset, runSize(this.pageShifts, allocateRun), this.arena.sizeIdx2size(i));
            this.subpages[runOffset] = poolSubpage;
            return poolSubpage.allocate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free(long j, int i, ByteBuffer byteBuffer) {
        Deque<ByteBuffer> deque;
        if (isSubpage(j)) {
            PoolSubpage<T> findSubpagePoolHead = this.arena.findSubpagePoolHead(this.arena.size2SizeIdx(i));
            PoolSubpage<T> poolSubpage = this.subpages[runOffset(j)];
            synchronized (findSubpagePoolHead) {
                if (poolSubpage.free(findSubpagePoolHead, bitmapIdx(j))) {
                    return;
                }
            }
        }
        int runPages = runPages(j);
        synchronized (this.runsAvail) {
            long collapseRuns = collapseRuns(j) & (-8589934593L) & (-4294967297L);
            insertAvailRun(runOffset(collapseRuns), runPages(collapseRuns), Long.valueOf(collapseRuns));
            this.freeBytes += runPages << this.pageShifts;
        }
        if (byteBuffer == null || (deque = this.cachedNioBuffers) == null || deque.size() >= PooledByteBufAllocator.DEFAULT_MAX_CACHED_BYTEBUFFERS_PER_CHUNK) {
            return;
        }
        this.cachedNioBuffers.offer(byteBuffer);
    }

    private long collapseRuns(long j) {
        return collapseNext(collapsePast(j));
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x003b, code lost:
    
        return r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private long collapsePast(long j) {
        while (true) {
            int runOffset = runOffset(j);
            int runPages = runPages(j);
            Long availRunByOffset = getAvailRunByOffset(runOffset - 1);
            if (availRunByOffset == null) {
                return j;
            }
            int runOffset2 = runOffset(availRunByOffset.longValue());
            int runPages2 = runPages(availRunByOffset.longValue());
            if (availRunByOffset.longValue() == j || runOffset2 + runPages2 != runOffset) {
                break;
            }
            removeAvailRun(availRunByOffset.longValue());
            j = toRunHandle(runOffset2, runPages2 + runPages, 0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0039, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private long collapseNext(long j) {
        while (true) {
            int runOffset = runOffset(j);
            int runPages = runPages(j);
            int i = runOffset + runPages;
            Long availRunByOffset = getAvailRunByOffset(i);
            if (availRunByOffset == null) {
                return j;
            }
            int runOffset2 = runOffset(availRunByOffset.longValue());
            int runPages2 = runPages(availRunByOffset.longValue());
            if (availRunByOffset.longValue() == j || i != runOffset2) {
                break;
            }
            removeAvailRun(availRunByOffset.longValue());
            j = toRunHandle(runOffset, runPages + runPages2, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initBuf(PooledByteBuf<T> pooledByteBuf, ByteBuffer byteBuffer, long j, int i, PoolThreadCache poolThreadCache) {
        if (isRun(j)) {
            int runOffset = runOffset(j);
            int i2 = this.pageShifts;
            pooledByteBuf.init(this, byteBuffer, j, runOffset << i2, i, runSize(i2, j), this.arena.parent.threadCache());
            return;
        }
        initBufWithSubpage(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initBufWithSubpage(PooledByteBuf<T> pooledByteBuf, ByteBuffer byteBuffer, long j, int i, PoolThreadCache poolThreadCache) {
        int runOffset = runOffset(j);
        int bitmapIdx = bitmapIdx(j);
        PoolSubpage<T> poolSubpage = this.subpages[runOffset];
        pooledByteBuf.init(this, byteBuffer, j, (runOffset << this.pageShifts) + (bitmapIdx * poolSubpage.elemSize) + this.offset, i, poolSubpage.elemSize, poolThreadCache);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolChunkMetric
    public int chunkSize() {
        return this.chunkSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.PoolChunkMetric
    public int freeBytes() {
        int i;
        synchronized (this.arena) {
            i = this.freeBytes;
        }
        return i;
    }

    public String toString() {
        int i;
        synchronized (this.arena) {
            i = this.freeBytes;
        }
        return "Chunk(" + Integer.toHexString(System.identityHashCode(this)) + ": " + usage(i) + "%, " + (this.chunkSize - i) + '/' + this.chunkSize + ')';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy() {
        this.arena.destroyChunk(this);
    }

    static int runSize(int i, long j) {
        return runPages(j) << i;
    }

    static boolean isRun(long j) {
        return !isSubpage(j);
    }
}
