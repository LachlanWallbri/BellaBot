package com.annimon.stream.operator;

import com.annimon.stream.function.LongToDoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongMapToDouble extends PrimitiveIterator.OfDouble {
    private final PrimitiveIterator.OfLong iterator;
    private final LongToDoubleFunction mapper;

    public LongMapToDouble(PrimitiveIterator.OfLong ofLong, LongToDoubleFunction longToDoubleFunction) {
        this.iterator = ofLong;
        this.mapper = longToDoubleFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.nextLong());
    }
}
