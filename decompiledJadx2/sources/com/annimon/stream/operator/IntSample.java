package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntSample extends PrimitiveIterator.OfInt {
    private final PrimitiveIterator.OfInt iterator;
    private final int stepWidth;

    public IntSample(PrimitiveIterator.OfInt ofInt, int i) {
        this.iterator = ofInt;
        this.stepWidth = i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        int nextInt = this.iterator.nextInt();
        for (int i = 1; i < this.stepWidth && this.iterator.hasNext(); i++) {
            this.iterator.nextInt();
        }
        return nextInt;
    }
}
