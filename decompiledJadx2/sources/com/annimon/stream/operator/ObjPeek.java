package com.annimon.stream.operator;

import com.annimon.stream.function.Consumer;
import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjPeek<T> extends LsaIterator<T> {
    private final Consumer<? super T> action;
    private final Iterator<? extends T> iterator;

    public ObjPeek(Iterator<? extends T> it, Consumer<? super T> consumer) {
        this.iterator = it;
        this.action = consumer;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        T next = this.iterator.next();
        this.action.accept(next);
        return next;
    }
}
