package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleLimit extends PrimitiveIterator.OfDouble {
    private long index = 0;
    private final PrimitiveIterator.OfDouble iterator;
    private final long maxSize;

    public DoubleLimit(PrimitiveIterator.OfDouble ofDouble, long j) {
        this.iterator = ofDouble;
        this.maxSize = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        this.index++;
        return this.iterator.nextDouble();
    }
}
