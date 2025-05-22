package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongLimit extends PrimitiveIterator.OfLong {
    private long index = 0;
    private final PrimitiveIterator.OfLong iterator;
    private final long maxSize;

    public LongLimit(PrimitiveIterator.OfLong ofLong, long j) {
        this.iterator = ofLong;
        this.maxSize = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        this.index++;
        return this.iterator.nextLong();
    }
}
