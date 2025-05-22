package com.annimon.stream.operator;

import com.annimon.stream.function.IntBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntScanIdentity extends PrimitiveExtIterator.OfInt {
    private final IntBinaryOperator accumulator;
    private final int identity;
    private final PrimitiveIterator.OfInt iterator;

    public IntScanIdentity(PrimitiveIterator.OfInt ofInt, int i, IntBinaryOperator intBinaryOperator) {
        this.iterator = ofInt;
        this.identity = i;
        this.accumulator = intBinaryOperator;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfInt
    protected void nextIteration() {
        if (!this.isInit) {
            this.hasNext = true;
            this.next = this.identity;
            return;
        }
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            this.next = this.accumulator.applyAsInt(this.next, this.iterator.next().intValue());
        }
    }
}
