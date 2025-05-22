package com.annimon.stream.iterator;

import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IndexedIterator<T> implements Iterator<T> {
    private int index;
    private final Iterator<? extends T> iterator;
    private final int step;

    public IndexedIterator(Iterator<? extends T> it) {
        this(0, 1, it);
    }

    public IndexedIterator(int i, int i2, Iterator<? extends T> it) {
        this.iterator = it;
        this.step = i2;
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        T next = this.iterator.next();
        this.index += this.step;
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }
}
