package com.annimon.stream.operator;

import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjMapToInt<T> extends PrimitiveIterator.OfInt {
    private final Iterator<? extends T> iterator;
    private final ToIntFunction<? super T> mapper;

    public ObjMapToInt(Iterator<? extends T> it, ToIntFunction<? super T> toIntFunction) {
        this.iterator = it;
        this.mapper = toIntFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.next());
    }
}
