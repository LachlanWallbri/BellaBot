package io.netty.buffer;

import com.google.common.primitives.Longs;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class PoolSubpage<T> implements PoolSubpageMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long[] bitmap;
    private int bitmapLength;
    final PoolChunk<T> chunk;
    boolean doNotDestroy;
    int elemSize;
    private int maxNumElems;
    private final int memoryMapIdx;
    PoolSubpage<T> next;
    private int nextAvail;
    private int numAvail;
    private final int pageSize;
    PoolSubpage<T> prev;
    private final int runOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolSubpage(int i) {
        this.chunk = null;
        this.memoryMapIdx = -1;
        this.runOffset = -1;
        this.elemSize = -1;
        this.pageSize = i;
        this.bitmap = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolSubpage(PoolSubpage<T> poolSubpage, PoolChunk<T> poolChunk, int i, int i2, int i3, int i4) {
        this.chunk = poolChunk;
        this.memoryMapIdx = i;
        this.runOffset = i2;
        this.pageSize = i3;
        this.bitmap = new long[i3 >>> 10];
        init(poolSubpage, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(PoolSubpage<T> poolSubpage, int i) {
        this.doNotDestroy = true;
        this.elemSize = i;
        if (i != 0) {
            int i2 = this.pageSize / i;
            this.numAvail = i2;
            this.maxNumElems = i2;
            this.nextAvail = 0;
            int i3 = i2 >>> 6;
            this.bitmapLength = i3;
            if ((i2 & 63) != 0) {
                this.bitmapLength = i3 + 1;
            }
            for (int i4 = 0; i4 < this.bitmapLength; i4++) {
                this.bitmap[i4] = 0;
            }
        }
        addToPool(poolSubpage);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long allocate() {
        if (this.elemSize == 0) {
            return toHandle(0);
        }
        if (this.numAvail == 0 || !this.doNotDestroy) {
            return -1L;
        }
        int nextAvail = getNextAvail();
        int i = nextAvail >>> 6;
        long[] jArr = this.bitmap;
        jArr[i] = jArr[i] | (1 << (nextAvail & 63));
        int i2 = this.numAvail - 1;
        this.numAvail = i2;
        if (i2 == 0) {
            removeFromPool();
        }
        return toHandle(nextAvail);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean free(PoolSubpage<T> poolSubpage, int i) {
        if (this.elemSize == 0) {
            return true;
        }
        int i2 = i >>> 6;
        long[] jArr = this.bitmap;
        jArr[i2] = jArr[i2] ^ (1 << (i & 63));
        setNextAvail(i);
        int i3 = this.numAvail;
        int i4 = i3 + 1;
        this.numAvail = i4;
        if (i3 == 0) {
            addToPool(poolSubpage);
            return true;
        }
        if (i4 != this.maxNumElems || this.prev == this.next) {
            return true;
        }
        this.doNotDestroy = false;
        removeFromPool();
        return false;
    }

    private void addToPool(PoolSubpage<T> poolSubpage) {
        this.prev = poolSubpage;
        PoolSubpage<T> poolSubpage2 = poolSubpage.next;
        this.next = poolSubpage2;
        poolSubpage2.prev = this;
        poolSubpage.next = this;
    }

    private void removeFromPool() {
        PoolSubpage<T> poolSubpage = this.prev;
        poolSubpage.next = this.next;
        this.next.prev = poolSubpage;
        this.next = null;
        this.prev = null;
    }

    private void setNextAvail(int i) {
        this.nextAvail = i;
    }

    private int getNextAvail() {
        int i = this.nextAvail;
        if (i >= 0) {
            this.nextAvail = -1;
            return i;
        }
        return findNextAvail();
    }

    private int findNextAvail() {
        long[] jArr = this.bitmap;
        int i = this.bitmapLength;
        for (int i2 = 0; i2 < i; i2++) {
            long j = jArr[i2];
            if ((~j) != 0) {
                return findNextAvail0(i2, j);
            }
        }
        return -1;
    }

    private int findNextAvail0(int i, long j) {
        int i2 = this.maxNumElems;
        int i3 = i << 6;
        for (int i4 = 0; i4 < 64; i4++) {
            if ((1 & j) == 0) {
                int i5 = i3 | i4;
                if (i5 < i2) {
                    return i5;
                }
                return -1;
            }
            j >>>= 1;
        }
        return -1;
    }

    private long toHandle(int i) {
        return (i << 32) | Longs.MAX_POWER_OF_TWO | this.memoryMapIdx;
    }

    public String toString() {
        boolean z;
        int i;
        int i2;
        int i3;
        synchronized (this.chunk.arena) {
            if (this.doNotDestroy) {
                z = true;
                i = this.maxNumElems;
                i2 = this.numAvail;
                i3 = this.elemSize;
            } else {
                z = false;
                i = -1;
                i2 = -1;
                i3 = -1;
            }
        }
        if (!z) {
            return "(" + this.memoryMapIdx + ": not in use)";
        }
        return "(" + this.memoryMapIdx + ": " + (i - i2) + '/' + i + ", offset: " + this.runOffset + ", length: " + this.pageSize + ", elemSize: " + i3 + ')';
    }

    @Override // io.netty.buffer.PoolSubpageMetric
    public int maxNumElements() {
        int i;
        synchronized (this.chunk.arena) {
            i = this.maxNumElems;
        }
        return i;
    }

    @Override // io.netty.buffer.PoolSubpageMetric
    public int numAvailable() {
        int i;
        synchronized (this.chunk.arena) {
            i = this.numAvail;
        }
        return i;
    }

    @Override // io.netty.buffer.PoolSubpageMetric
    public int elementSize() {
        int i;
        synchronized (this.chunk.arena) {
            i = this.elemSize;
        }
        return i;
    }

    @Override // io.netty.buffer.PoolSubpageMetric
    public int pageSize() {
        return this.pageSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy() {
        PoolChunk<T> poolChunk = this.chunk;
        if (poolChunk != null) {
            poolChunk.destroy();
        }
    }
}
