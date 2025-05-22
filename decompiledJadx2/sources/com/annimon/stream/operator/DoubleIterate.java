package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleIterate extends PrimitiveIterator.OfDouble {
    private double current;

    /* renamed from: op */
    private final DoubleUnaryOperator f1204op;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public DoubleIterate(double d, DoubleUnaryOperator doubleUnaryOperator) {
        this.f1204op = doubleUnaryOperator;
        this.current = d;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        double d = this.current;
        this.current = this.f1204op.applyAsDouble(d);
        return d;
    }
}
