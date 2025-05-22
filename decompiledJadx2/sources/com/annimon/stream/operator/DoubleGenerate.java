package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleSupplier;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleGenerate extends PrimitiveIterator.OfDouble {
    private final DoubleSupplier supplier;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public DoubleGenerate(DoubleSupplier doubleSupplier) {
        this.supplier = doubleSupplier;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.supplier.getAsDouble();
    }
}
