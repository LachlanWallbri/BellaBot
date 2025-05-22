package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleScanIdentity extends PrimitiveExtIterator.OfDouble {
    private final DoubleBinaryOperator accumulator;
    private final double identity;
    private final PrimitiveIterator.OfDouble iterator;

    public DoubleScanIdentity(PrimitiveIterator.OfDouble ofDouble, double d, DoubleBinaryOperator doubleBinaryOperator) {
        this.iterator = ofDouble;
        this.identity = d;
        this.accumulator = doubleBinaryOperator;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble
    protected void nextIteration() {
        if (!this.isInit) {
            this.hasNext = true;
            this.next = this.identity;
            return;
        }
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            this.next = this.accumulator.applyAsDouble(this.next, this.iterator.next().doubleValue());
        }
    }
}
