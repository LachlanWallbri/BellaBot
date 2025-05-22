package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleToIntFunction;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleMapToInt extends PrimitiveIterator.OfInt {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleToIntFunction mapper;

    public DoubleMapToInt(PrimitiveIterator.OfDouble ofDouble, DoubleToIntFunction doubleToIntFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleToIntFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.nextDouble());
    }
}
