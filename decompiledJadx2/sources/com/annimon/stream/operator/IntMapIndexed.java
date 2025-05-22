package com.annimon.stream.operator;

import com.annimon.stream.function.IntBinaryOperator;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntMapIndexed extends PrimitiveIterator.OfInt {
    private final PrimitiveIndexedIterator.OfInt iterator;
    private final IntBinaryOperator mapper;

    public IntMapIndexed(PrimitiveIndexedIterator.OfInt ofInt, IntBinaryOperator intBinaryOperator) {
        this.iterator = ofInt;
        this.mapper = intBinaryOperator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.getIndex(), this.iterator.next().intValue());
    }
}
