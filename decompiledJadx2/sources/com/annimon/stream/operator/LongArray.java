package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongArray extends PrimitiveIterator.OfLong {
    private int index = 0;
    private final long[] values;

    public LongArray(long[] jArr) {
        this.values = jArr;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        long[] jArr = this.values;
        int i = this.index;
        this.index = i + 1;
        return jArr[i];
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.values.length;
    }
}
