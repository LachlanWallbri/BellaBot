package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntArray extends PrimitiveIterator.OfInt {
    private int index = 0;
    private final int[] values;

    public IntArray(int[] iArr) {
        this.values = iArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.values.length;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        int[] iArr = this.values;
        int i = this.index;
        this.index = i + 1;
        return iArr[i];
    }
}
