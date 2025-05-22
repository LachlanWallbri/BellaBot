package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleConcat extends PrimitiveIterator.OfDouble {
    private boolean firstStreamIsCurrent = true;
    private final PrimitiveIterator.OfDouble iterator1;
    private final PrimitiveIterator.OfDouble iterator2;

    public DoubleConcat(PrimitiveIterator.OfDouble ofDouble, PrimitiveIterator.OfDouble ofDouble2) {
        this.iterator1 = ofDouble;
        this.iterator2 = ofDouble2;
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

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return (this.firstStreamIsCurrent ? this.iterator1 : this.iterator2).nextDouble();
    }
}
