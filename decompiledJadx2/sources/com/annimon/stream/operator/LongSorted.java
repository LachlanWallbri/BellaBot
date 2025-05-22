package com.annimon.stream.operator;

import com.annimon.stream.internal.Operators;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongSorted extends PrimitiveExtIterator.OfLong {
    private long[] array;
    private int index = 0;
    private final PrimitiveIterator.OfLong iterator;

    public LongSorted(PrimitiveIterator.OfLong ofLong) {
        this.iterator = ofLong;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfLong
    protected void nextIteration() {
        if (!this.isInit) {
            this.array = Operators.toLongArray(this.iterator);
            Arrays.sort(this.array);
        }
        this.hasNext = this.index < this.array.length;
        if (this.hasNext) {
            long[] jArr = this.array;
            int i = this.index;
            this.index = i + 1;
            this.next = jArr[i];
        }
    }
}
