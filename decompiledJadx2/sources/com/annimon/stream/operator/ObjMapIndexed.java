package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedFunction;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjMapIndexed<T, R> extends LsaIterator<R> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedFunction<? super T, ? extends R> mapper;

    public ObjMapIndexed(IndexedIterator<? extends T> indexedIterator, IndexedFunction<? super T, ? extends R> indexedFunction) {
        this.iterator = indexedIterator;
        this.mapper = indexedFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public R nextIteration() {
        return this.mapper.apply(this.iterator.getIndex(), this.iterator.next());
    }
}
