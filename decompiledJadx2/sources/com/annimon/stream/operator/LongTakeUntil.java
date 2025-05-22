package com.annimon.stream.operator;

import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongTakeUntil extends PrimitiveExtIterator.OfLong {
    private final PrimitiveIterator.OfLong iterator;
    private final LongPredicate stopPredicate;

    public LongTakeUntil(PrimitiveIterator.OfLong ofLong, LongPredicate longPredicate) {
        this.iterator = ofLong;
        this.stopPredicate = longPredicate;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfLong
    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.next));
        if (this.hasNext) {
            this.next = this.iterator.next().longValue();
        }
    }
}
