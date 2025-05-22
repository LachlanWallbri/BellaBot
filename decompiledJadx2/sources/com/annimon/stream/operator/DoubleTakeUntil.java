package com.annimon.stream.operator;

import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleTakeUntil extends PrimitiveExtIterator.OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoublePredicate stopPredicate;

    public DoubleTakeUntil(PrimitiveIterator.OfDouble ofDouble, DoublePredicate doublePredicate) {
        this.iterator = ofDouble;
        this.stopPredicate = doublePredicate;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble
    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.next));
        if (this.hasNext) {
            this.next = this.iterator.next().doubleValue();
        }
    }
}
