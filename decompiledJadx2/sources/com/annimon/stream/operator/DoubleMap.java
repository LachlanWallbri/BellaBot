package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleMap extends PrimitiveIterator.OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleUnaryOperator mapper;

    public DoubleMap(PrimitiveIterator.OfDouble ofDouble, DoubleUnaryOperator doubleUnaryOperator) {
        this.iterator = ofDouble;
        this.mapper = doubleUnaryOperator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.nextDouble());
    }
}
