package io.netty.buffer;

import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class PoolChunkList<T> implements PoolChunkListMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Iterator<PoolChunkMetric> EMPTY_METRICS = Collections.emptyList().iterator();
    private final PoolArena<T> arena;
    private PoolChunk<T> head;
    private final int maxCapacity;
    private final int maxUsage;
    private final int minUsage;
    private final PoolChunkList<T> nextList;
    private PoolChunkList<T> prevList;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoolChunkList(PoolArena<T> poolArena, PoolChunkList<T> poolChunkList, int i, int i2, int i3) {
        this.arena = poolArena;
        this.nextList = poolChunkList;
        this.minUsage = i;
        this.maxUsage = i2;
        this.maxCapacity = calculateMaxCapacity(i, i3);
    }

    private static int calculateMaxCapacity(int i, int i2) {
        int minUsage0 = minUsage0(i);
        if (minUsage0 == 100) {
            return 0;
        }
        return (int) ((i2 * (100 - minUsage0)) / 100);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void prevList(PoolChunkList<T> poolChunkList) {
        this.prevList = poolChunkList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean allocate(PooledByteBuf<T> pooledByteBuf, int i, int i2) {
        PoolChunk<T> poolChunk = this.head;
        if (poolChunk == null || i2 > this.maxCapacity) {
            return false;
        }
        do {
            long allocate = poolChunk.allocate(i2);
            if (allocate < 0) {
                poolChunk = poolChunk.next;
            } else {
                poolChunk.initBuf(pooledByteBuf, allocate, i);
                if (poolChunk.usage() < this.maxUsage) {
                    return true;
                }
                remove(poolChunk);
                this.nextList.add(poolChunk);
                return true;
            }
        } while (poolChunk != null);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean free(PoolChunk<T> poolChunk, long j) {
        poolChunk.free(j);
        if (poolChunk.usage() >= this.minUsage) {
            return true;
        }
        remove(poolChunk);
        return move0(poolChunk);
    }

    private boolean move(PoolChunk<T> poolChunk) {
        if (poolChunk.usage() < this.minUsage) {
            return move0(poolChunk);
        }
        add0(poolChunk);
        return true;
    }

    private boolean move0(PoolChunk<T> poolChunk) {
        PoolChunkList<T> poolChunkList = this.prevList;
        if (poolChunkList == null) {
            return false;
        }
        return poolChunkList.move(poolChunk);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(PoolChunk<T> poolChunk) {
        if (poolChunk.usage() >= this.maxUsage) {
            this.nextList.add(poolChunk);
        } else {
            add0(poolChunk);
        }
    }

    void add0(PoolChunk<T> poolChunk) {
        poolChunk.parent = this;
        if (this.head == null) {
            this.head = poolChunk;
            poolChunk.prev = null;
            poolChunk.next = null;
        } else {
            poolChunk.prev = null;
            poolChunk.next = this.head;
            this.head.prev = poolChunk;
            this.head = poolChunk;
        }
    }

    private void remove(PoolChunk<T> poolChunk) {
        if (poolChunk == this.head) {
            PoolChunk<T> poolChunk2 = poolChunk.next;
            this.head = poolChunk2;
            if (poolChunk2 != null) {
                poolChunk2.prev = null;
                return;
            }
            return;
        }
        PoolChunk<T> poolChunk3 = poolChunk.next;
        poolChunk.prev.next = poolChunk3;
        if (poolChunk3 != null) {
            poolChunk3.prev = poolChunk.prev;
        }
    }

    @Override // io.netty.buffer.PoolChunkListMetric
    public int minUsage() {
        return minUsage0(this.minUsage);
    }

    @Override // io.netty.buffer.PoolChunkListMetric
    public int maxUsage() {
        return Math.min(this.maxUsage, 100);
    }

    private static int minUsage0(int i) {
        return Math.max(1, i);
    }

    @Override // java.lang.Iterable
    public Iterator<PoolChunkMetric> iterator() {
        synchronized (this.arena) {
            if (this.head == null) {
                return EMPTY_METRICS;
            }
            ArrayList arrayList = new ArrayList();
            PoolChunk<T> poolChunk = this.head;
            do {
                arrayList.add(poolChunk);
                poolChunk = poolChunk.next;
            } while (poolChunk != null);
            return arrayList.iterator();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.arena) {
            if (this.head == null) {
                return "none";
            }
            PoolChunk<T> poolChunk = this.head;
            while (true) {
                sb.append(poolChunk);
                poolChunk = poolChunk.next;
                if (poolChunk != null) {
                    sb.append(StringUtil.NEWLINE);
                } else {
                    return sb.toString();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy(PoolArena<T> poolArena) {
        for (PoolChunk<T> poolChunk = this.head; poolChunk != null; poolChunk = poolChunk.next) {
            poolArena.destroyChunk(poolChunk);
        }
        this.head = null;
    }
}
