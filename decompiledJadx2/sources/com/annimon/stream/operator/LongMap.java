package com.annimon.stream.operator;

import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongMap extends PrimitiveIterator.OfLong {
    private final PrimitiveIterator.OfLong iterator;
    private final LongUnaryOperator mapper;

    public LongMap(PrimitiveIterator.OfLong ofLong, LongUnaryOperator longUnaryOperator) {
        this.iterator = ofLong;
        this.mapper = longUnaryOperator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.nextLong());
    }
}
