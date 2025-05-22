package com.annimon.stream.operator;

import com.annimon.stream.function.LongToIntFunction;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongMapToInt extends PrimitiveIterator.OfInt {
    private final PrimitiveIterator.OfLong iterator;
    private final LongToIntFunction mapper;

    public LongMapToInt(PrimitiveIterator.OfLong ofLong, LongToIntFunction longToIntFunction) {
        this.iterator = ofLong;
        this.mapper = longToIntFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.nextLong());
    }
}
