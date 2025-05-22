package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleSample extends PrimitiveIterator.OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final int stepWidth;

    public DoubleSample(PrimitiveIterator.OfDouble ofDouble, int i) {
        this.iterator = ofDouble;
        this.stepWidth = i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        double nextDouble = this.iterator.nextDouble();
        for (int i = 1; i < this.stepWidth && this.iterator.hasNext(); i++) {
            this.iterator.nextDouble();
        }
        return nextDouble;
    }
}
