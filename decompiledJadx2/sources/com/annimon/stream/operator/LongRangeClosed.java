package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongRangeClosed extends PrimitiveIterator.OfLong {
    private long current;
    private final long endInclusive;
    private boolean hasNext;

    public LongRangeClosed(long j, long j2) {
        this.endInclusive = j2;
        this.current = j;
        this.hasNext = this.current <= j2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        long j = this.current;
        long j2 = this.endInclusive;
        if (j >= j2) {
            this.hasNext = false;
            return j2;
        }
        this.current = 1 + j;
        return j;
    }
}
