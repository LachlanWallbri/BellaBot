package com.annimon.stream.operator;

import com.annimon.stream.function.LongBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongScanIdentity extends PrimitiveExtIterator.OfLong {
    private final LongBinaryOperator accumulator;
    private final long identity;
    private final PrimitiveIterator.OfLong iterator;

    public LongScanIdentity(PrimitiveIterator.OfLong ofLong, long j, LongBinaryOperator longBinaryOperator) {
        this.iterator = ofLong;
        this.identity = j;
        this.accumulator = longBinaryOperator;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfLong
    protected void nextIteration() {
        if (!this.isInit) {
            this.hasNext = true;
            this.next = this.identity;
            return;
        }
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            this.next = this.accumulator.applyAsLong(this.next, this.iterator.next().longValue());
        }
    }
}
