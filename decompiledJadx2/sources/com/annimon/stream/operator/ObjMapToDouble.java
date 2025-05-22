package com.annimon.stream.operator;

import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjMapToDouble<T> extends PrimitiveIterator.OfDouble {
    private final Iterator<? extends T> iterator;
    private final ToDoubleFunction<? super T> mapper;

    public ObjMapToDouble(Iterator<? extends T> it, ToDoubleFunction<? super T> toDoubleFunction) {
        this.iterator = it;
        this.mapper = toDoubleFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.next());
    }
}
