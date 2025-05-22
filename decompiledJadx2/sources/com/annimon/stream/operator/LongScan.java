package com.annimon.stream.operator;

import com.annimon.stream.function.LongBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongScan extends PrimitiveExtIterator.OfLong {
    private final LongBinaryOperator accumulator;
    private final PrimitiveIterator.OfLong iterator;

    public LongScan(PrimitiveIterator.OfLong ofLong, LongBinaryOperator longBinaryOperator) {
        this.iterator = ofLong;
        this.accumulator = longBinaryOperator;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfLong
    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            long nextLong = this.iterator.nextLong();
            if (this.isInit) {
                this.next = this.accumulator.applyAsLong(this.next, nextLong);
            } else {
                this.next = nextLong;
            }
        }
    }
}
