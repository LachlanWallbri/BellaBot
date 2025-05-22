package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongSample extends PrimitiveIterator.OfLong {
    private final PrimitiveIterator.OfLong iterator;
    private final int stepWidth;

    public LongSample(PrimitiveIterator.OfLong ofLong, int i) {
        this.iterator = ofLong;
        this.stepWidth = i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        long nextLong = this.iterator.nextLong();
        for (int i = 1; i < this.stepWidth && this.iterator.hasNext(); i++) {
            this.iterator.nextLong();
        }
        return nextLong;
    }
}
