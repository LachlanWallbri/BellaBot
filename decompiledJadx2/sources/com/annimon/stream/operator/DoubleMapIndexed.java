package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedDoubleUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleMapIndexed extends PrimitiveIterator.OfDouble {
    private final PrimitiveIndexedIterator.OfDouble iterator;
    private final IndexedDoubleUnaryOperator mapper;

    public DoubleMapIndexed(PrimitiveIndexedIterator.OfDouble ofDouble, IndexedDoubleUnaryOperator indexedDoubleUnaryOperator) {
        this.iterator = ofDouble;
        this.mapper = indexedDoubleUnaryOperator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.getIndex(), this.iterator.next().doubleValue());
    }
}
