package com.annimon.stream.operator;

import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntGenerate extends PrimitiveIterator.OfInt {
    private final IntSupplier supplier;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public IntGenerate(IntSupplier intSupplier) {
        this.supplier = intSupplier;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return this.supplier.getAsInt();
    }
}
