package com.annimon.stream.operator;

import com.annimon.stream.function.IntFunction;
import com.annimon.stream.iterator.LsaIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntMapToObj<R> extends LsaIterator<R> {
    private final PrimitiveIterator.OfInt iterator;
    private final IntFunction<? extends R> mapper;

    public IntMapToObj(PrimitiveIterator.OfInt ofInt, IntFunction<? extends R> intFunction) {
        this.iterator = ofInt;
        this.mapper = intFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public R nextIteration() {
        return this.mapper.apply(this.iterator.nextInt());
    }
}
