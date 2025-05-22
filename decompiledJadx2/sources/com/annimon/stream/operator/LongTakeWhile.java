package com.annimon.stream.operator;

import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongTakeWhile extends PrimitiveExtIterator.OfLong {
    private final PrimitiveIterator.OfLong iterator;
    private final LongPredicate predicate;

    public LongTakeWhile(PrimitiveIterator.OfLong ofLong, LongPredicate longPredicate) {
        this.iterator = ofLong;
        this.predicate = longPredicate;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfLong
    protected void nextIteration() {
        boolean z;
        if (this.iterator.hasNext()) {
            LongPredicate longPredicate = this.predicate;
            long longValue = this.iterator.next().longValue();
            this.next = longValue;
            if (longPredicate.test(longValue)) {
                z = true;
                this.hasNext = z;
            }
        }
        z = false;
        this.hasNext = z;
    }
}
