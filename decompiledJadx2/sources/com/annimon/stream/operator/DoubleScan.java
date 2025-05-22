package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleScan extends PrimitiveExtIterator.OfDouble {
    private final DoubleBinaryOperator accumulator;
    private final PrimitiveIterator.OfDouble iterator;

    public DoubleScan(PrimitiveIterator.OfDouble ofDouble, DoubleBinaryOperator doubleBinaryOperator) {
        this.iterator = ofDouble;
        this.accumulator = doubleBinaryOperator;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble
    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            double nextDouble = this.iterator.nextDouble();
            if (this.isInit) {
                this.next = this.accumulator.applyAsDouble(this.next, nextDouble);
            } else {
                this.next = nextDouble;
            }
        }
    }
}
