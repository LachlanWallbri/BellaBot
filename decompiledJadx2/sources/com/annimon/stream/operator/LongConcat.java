package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongConcat extends PrimitiveIterator.OfLong {
    private boolean firstStreamIsCurrent = true;
    private final PrimitiveIterator.OfLong iterator1;
    private final PrimitiveIterator.OfLong iterator2;

    public LongConcat(PrimitiveIterator.OfLong ofLong, PrimitiveIterator.OfLong ofLong2) {
        this.iterator1 = ofLong;
        this.iterator2 = ofLong2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.firstStreamIsCurrent) {
            if (this.iterator1.hasNext()) {
                return true;
            }
            this.firstStreamIsCurrent = false;
        }
        return this.iterator2.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return (this.firstStreamIsCurrent ? this.iterator1 : this.iterator2).nextLong();
    }
}
