package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntRangeClosed extends PrimitiveIterator.OfInt {
    private int current;
    private final int endInclusive;
    private boolean hasNext;

    public IntRangeClosed(int i, int i2) {
        this.endInclusive = i2;
        this.current = i;
        this.hasNext = this.current <= i2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        int i = this.current;
        int i2 = this.endInclusive;
        if (i >= i2) {
            this.hasNext = false;
            return i2;
        }
        this.current = i + 1;
        return i;
    }
}
