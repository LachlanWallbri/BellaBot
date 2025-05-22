package com.annimon.stream.iterator;

import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LazyIterator<T> implements Iterator<T> {
    private final Iterable<? extends T> iterable;
    private Iterator<? extends T> iterator;

    public LazyIterator(Iterable<? extends T> iterable) {
        this.iterable = iterable;
    }

    private void ensureIterator() {
        if (this.iterator != null) {
            return;
        }
        this.iterator = this.iterable.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        ensureIterator();
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        ensureIterator();
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        ensureIterator();
        this.iterator.remove();
    }
}
