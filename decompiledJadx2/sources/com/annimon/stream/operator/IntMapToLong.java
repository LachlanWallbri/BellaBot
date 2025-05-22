package com.annimon.stream.operator;

import com.annimon.stream.function.IntToLongFunction;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntMapToLong extends PrimitiveIterator.OfLong {
    private final PrimitiveIterator.OfInt iterator;
    private final IntToLongFunction mapper;

    public IntMapToLong(PrimitiveIterator.OfInt ofInt, IntToLongFunction intToLongFunction) {
        this.iterator = ofInt;
        this.mapper = intToLongFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.nextInt());
    }
}
