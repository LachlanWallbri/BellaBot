package com.annimon.stream.operator;

import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntPeek extends PrimitiveIterator.OfInt {
    private final IntConsumer action;
    private final PrimitiveIterator.OfInt iterator;

    public IntPeek(PrimitiveIterator.OfInt ofInt, IntConsumer intConsumer) {
        this.iterator = ofInt;
        this.action = intConsumer;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        int nextInt = this.iterator.nextInt();
        this.action.accept(nextInt);
        return nextInt;
    }
}
