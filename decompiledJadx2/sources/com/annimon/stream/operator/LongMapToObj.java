package com.annimon.stream.operator;

import com.annimon.stream.function.LongFunction;
import com.annimon.stream.iterator.LsaIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongMapToObj<R> extends LsaIterator<R> {
    private final PrimitiveIterator.OfLong iterator;
    private final LongFunction<? extends R> mapper;

    public LongMapToObj(PrimitiveIterator.OfLong ofLong, LongFunction<? extends R> longFunction) {
        this.iterator = ofLong;
        this.mapper = longFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public R nextIteration() {
        return this.mapper.apply(this.iterator.nextLong());
    }
}
