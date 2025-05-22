package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleFunction;
import com.annimon.stream.iterator.LsaIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleMapToObj<R> extends LsaIterator<R> {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleFunction<? extends R> mapper;

    public DoubleMapToObj(PrimitiveIterator.OfDouble ofDouble, DoubleFunction<? extends R> doubleFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public R nextIteration() {
        return this.mapper.apply(this.iterator.nextDouble());
    }
}
