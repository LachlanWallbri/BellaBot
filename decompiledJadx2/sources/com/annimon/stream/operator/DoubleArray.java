package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleArray extends PrimitiveIterator.OfDouble {
    private int index = 0;
    private final double[] values;

    public DoubleArray(double[] dArr) {
        this.values = dArr;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        double[] dArr = this.values;
        int i = this.index;
        this.index = i + 1;
        return dArr[i];
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.values.length;
    }
}
