package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntConcat extends PrimitiveIterator.OfInt {
    private boolean firstStreamIsCurrent = true;
    private final PrimitiveIterator.OfInt iterator1;
    private final PrimitiveIterator.OfInt iterator2;

    public IntConcat(PrimitiveIterator.OfInt ofInt, PrimitiveIterator.OfInt ofInt2) {
        this.iterator1 = ofInt;
        this.iterator2 = ofInt2;
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

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return (this.firstStreamIsCurrent ? this.iterator1 : this.iterator2).nextInt();
    }
}
