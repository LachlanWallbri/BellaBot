package com.google.common.collect;

import java.util.ListIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public abstract class ForwardingListIterator<E> extends ForwardingIterator<E> implements ListIterator<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
    public abstract ListIterator<E> delegate();

    protected ForwardingListIterator() {
    }

    @Override // java.util.ListIterator
    public void add(E e) {
        delegate().add(e);
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return delegate().hasPrevious();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return delegate().nextIndex();
    }

    @Override // java.util.ListIterator
    public E previous() {
        return delegate().previous();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return delegate().previousIndex();
    }

    @Override // java.util.ListIterator
    public void set(E e) {
        delegate().set(e);
    }
}
