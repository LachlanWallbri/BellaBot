package com.annimon.stream.operator;

import com.annimon.stream.function.LongConsumer;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongPeek extends PrimitiveIterator.OfLong {
    private final LongConsumer action;
    private final PrimitiveIterator.OfLong iterator;

    public LongPeek(PrimitiveIterator.OfLong ofLong, LongConsumer longConsumer) {
        this.iterator = ofLong;
        this.action = longConsumer;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        long nextLong = this.iterator.nextLong();
        this.action.accept(nextLong);
        return nextLong;
    }
}
