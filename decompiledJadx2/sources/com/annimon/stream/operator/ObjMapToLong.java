package com.annimon.stream.operator;

import com.annimon.stream.function.ToLongFunction;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjMapToLong<T> extends PrimitiveIterator.OfLong {
    private final Iterator<? extends T> iterator;
    private final ToLongFunction<? super T> mapper;

    public ObjMapToLong(Iterator<? extends T> it, ToLongFunction<? super T> toLongFunction) {
        this.iterator = it;
        this.mapper = toLongFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.next());
    }
}
