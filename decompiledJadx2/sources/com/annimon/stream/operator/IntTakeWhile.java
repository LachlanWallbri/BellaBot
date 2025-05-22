package com.annimon.stream.operator;

import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntTakeWhile extends PrimitiveExtIterator.OfInt {
    private final PrimitiveIterator.OfInt iterator;
    private final IntPredicate predicate;

    public IntTakeWhile(PrimitiveIterator.OfInt ofInt, IntPredicate intPredicate) {
        this.iterator = ofInt;
        this.predicate = intPredicate;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfInt
    protected void nextIteration() {
        boolean z;
        if (this.iterator.hasNext()) {
            IntPredicate intPredicate = this.predicate;
            int intValue = this.iterator.next().intValue();
            this.next = intValue;
            if (intPredicate.test(intValue)) {
                z = true;
                this.hasNext = z;
            }
        }
        z = false;
        this.hasNext = z;
    }
}
