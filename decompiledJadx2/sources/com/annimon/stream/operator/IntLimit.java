package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntLimit extends PrimitiveIterator.OfInt {
    private long index = 0;
    private final PrimitiveIterator.OfInt iterator;
    private final long maxSize;

    public IntLimit(PrimitiveIterator.OfInt ofInt, long j) {
        this.iterator = ofInt;
        this.maxSize = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        this.index++;
        return this.iterator.nextInt();
    }
}
