package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleSkip extends PrimitiveIterator.OfDouble {
    private final PrimitiveIterator.OfDouble iterator;

    /* renamed from: n */
    private final long f1205n;
    private long skipped = 0;

    public DoubleSkip(PrimitiveIterator.OfDouble ofDouble, long j) {
        this.iterator = ofDouble;
        this.f1205n = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (this.iterator.hasNext() && this.skipped != this.f1205n) {
            this.iterator.nextDouble();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.iterator.nextDouble();
    }
}
