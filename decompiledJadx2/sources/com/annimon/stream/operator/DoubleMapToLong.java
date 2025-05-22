package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleToLongFunction;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleMapToLong extends PrimitiveIterator.OfLong {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleToLongFunction mapper;

    public DoubleMapToLong(PrimitiveIterator.OfDouble ofDouble, DoubleToLongFunction doubleToLongFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleToLongFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.nextDouble());
    }
}
