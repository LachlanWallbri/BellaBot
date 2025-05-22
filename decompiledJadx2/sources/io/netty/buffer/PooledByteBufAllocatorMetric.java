package io.netty.buffer;

import io.netty.util.internal.StringUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class PooledByteBufAllocatorMetric implements ByteBufAllocatorMetric {
    private final PooledByteBufAllocator allocator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PooledByteBufAllocatorMetric(PooledByteBufAllocator pooledByteBufAllocator) {
        this.allocator = pooledByteBufAllocator;
    }

    public int numHeapArenas() {
        return this.allocator.numHeapArenas();
    }

    public int numDirectArenas() {
        return this.allocator.numDirectArenas();
    }

    public List<PoolArenaMetric> heapArenas() {
        return this.allocator.heapArenas();
    }

    public List<PoolArenaMetric> directArenas() {
        return this.allocator.directArenas();
    }

    public int numThreadLocalCaches() {
        return this.allocator.numThreadLocalCaches();
    }

    public int tinyCacheSize() {
        return this.allocator.tinyCacheSize();
    }

    public int smallCacheSize() {
        return this.allocator.smallCacheSize();
    }

    public int normalCacheSize() {
        return this.allocator.normalCacheSize();
    }

    public int chunkSize() {
        return this.allocator.chunkSize();
    }

    @Override // io.netty.buffer.ByteBufAllocatorMetric
    public long usedHeapMemory() {
        return this.allocator.usedHeapMemory();
    }

    @Override // io.netty.buffer.ByteBufAllocatorMetric
    public long usedDirectMemory() {
        return this.allocator.usedDirectMemory();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append(StringUtil.simpleClassName(this));
        sb.append("(usedHeapMemory: ");
        sb.append(usedHeapMemory());
        sb.append("; usedDirectMemory: ");
        sb.append(usedDirectMemory());
        sb.append("; numHeapArenas: ");
        sb.append(numHeapArenas());
        sb.append("; numDirectArenas: ");
        sb.append(numDirectArenas());
        sb.append("; tinyCacheSize: ");
        sb.append(tinyCacheSize());
        sb.append("; smallCacheSize: ");
        sb.append(smallCacheSize());
        sb.append("; normalCacheSize: ");
        sb.append(normalCacheSize());
        sb.append("; numThreadLocalCaches: ");
        sb.append(numThreadLocalCaches());
        sb.append("; chunkSize: ");
        sb.append(chunkSize());
        sb.append(')');
        return sb.toString();
    }
}
