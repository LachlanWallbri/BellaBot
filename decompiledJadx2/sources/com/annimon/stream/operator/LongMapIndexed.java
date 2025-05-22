package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedLongUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongMapIndexed extends PrimitiveIterator.OfLong {
    private final PrimitiveIndexedIterator.OfLong iterator;
    private final IndexedLongUnaryOperator mapper;

    public LongMapIndexed(PrimitiveIndexedIterator.OfLong ofLong, IndexedLongUnaryOperator indexedLongUnaryOperator) {
        this.iterator = ofLong;
        this.mapper = indexedLongUnaryOperator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.getIndex(), this.iterator.next().longValue());
    }
}
