package com.annimon.stream.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class LsaExtIterator<T> implements Iterator<T> {
    protected boolean hasNext;
    protected boolean isInit;
    protected T next;

    protected abstract void nextIteration();

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.isInit) {
            nextIteration();
            this.isInit = true;
        }
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public T next() {
        if (!this.isInit) {
            hasNext();
        }
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        T t = this.next;
        nextIteration();
        if (!this.hasNext) {
            this.next = null;
        }
        return t;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }
}
