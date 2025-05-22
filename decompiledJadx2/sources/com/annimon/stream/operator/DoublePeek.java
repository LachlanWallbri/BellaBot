package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleConsumer;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoublePeek extends PrimitiveIterator.OfDouble {
    private final DoubleConsumer action;
    private final PrimitiveIterator.OfDouble iterator;

    public DoublePeek(PrimitiveIterator.OfDouble ofDouble, DoubleConsumer doubleConsumer) {
        this.iterator = ofDouble;
        this.action = doubleConsumer;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        double nextDouble = this.iterator.nextDouble();
        this.action.accept(nextDouble);
        return nextDouble;
    }
}
