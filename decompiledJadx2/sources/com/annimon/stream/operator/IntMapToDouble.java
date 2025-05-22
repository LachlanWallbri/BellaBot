package com.annimon.stream.operator;

import com.annimon.stream.function.IntToDoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntMapToDouble extends PrimitiveIterator.OfDouble {
    private final PrimitiveIterator.OfInt iterator;
    private final IntToDoubleFunction mapper;

    public IntMapToDouble(PrimitiveIterator.OfInt ofInt, IntToDoubleFunction intToDoubleFunction) {
        this.iterator = ofInt;
        this.mapper = intToDoubleFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.nextInt());
    }
}
