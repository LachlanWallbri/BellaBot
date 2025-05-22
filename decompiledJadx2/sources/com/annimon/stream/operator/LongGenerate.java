package com.annimon.stream.operator;

import com.annimon.stream.function.LongSupplier;
import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongGenerate extends PrimitiveIterator.OfLong {
    private final LongSupplier supplier;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public LongGenerate(LongSupplier longSupplier) {
        this.supplier = longSupplier;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return this.supplier.getAsLong();
    }
}
