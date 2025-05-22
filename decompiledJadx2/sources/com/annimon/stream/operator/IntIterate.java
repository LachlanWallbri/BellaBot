package com.annimon.stream.operator;

import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntIterate extends PrimitiveIterator.OfInt {
    private int current;

    /* renamed from: op */
    private final IntUnaryOperator f1206op;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public IntIterate(int i, IntUnaryOperator intUnaryOperator) {
        this.f1206op = intUnaryOperator;
        this.current = i;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        int i = this.current;
        this.current = this.f1206op.applyAsInt(i);
        return i;
    }
}
