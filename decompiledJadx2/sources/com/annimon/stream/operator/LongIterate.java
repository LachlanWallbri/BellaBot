package com.annimon.stream.operator;

import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongIterate extends PrimitiveIterator.OfLong {
    private long current;

    /* renamed from: op */
    private final LongUnaryOperator f1208op;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public LongIterate(long j, LongUnaryOperator longUnaryOperator) {
        this.f1208op = longUnaryOperator;
        this.current = j;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        long j = this.current;
        this.current = this.f1208op.applyAsLong(j);
        return j;
    }
}
