package com.annimon.stream.operator;

import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjMap<T, R> extends LsaIterator<R> {
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends R> mapper;

    public ObjMap(Iterator<? extends T> it, Function<? super T, ? extends R> function) {
        this.iterator = it;
        this.mapper = function;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public R nextIteration() {
        return this.mapper.apply(this.iterator.next());
    }
}
