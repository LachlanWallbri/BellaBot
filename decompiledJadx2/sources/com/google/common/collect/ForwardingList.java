package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract List<E> delegate();

    public void add(int i, E e) {
        delegate().add(i, e);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        return delegate().addAll(i, collection);
    }

    @Override // java.util.List
    public E get(int i) {
        return delegate().get(i);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return delegate().indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return delegate().lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return delegate().listIterator(i);
    }

    @Override // java.util.List
    public E remove(int i) {
        return delegate().remove(i);
    }

    @Override // java.util.List
    public E set(int i, E e) {
        return delegate().set(i, e);
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return delegate().subList(i, i2);
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return delegate().hashCode();
    }

    protected boolean standardAdd(E e) {
        add(size(), e);
        return true;
    }

    protected boolean standardAddAll(int i, Iterable<? extends E> iterable) {
        return Lists.addAllImpl(this, i, iterable);
    }

    protected int standardIndexOf(Object obj) {
        return Lists.indexOfImpl(this, obj);
    }

    protected int standardLastIndexOf(Object obj) {
        return Lists.lastIndexOfImpl(this, obj);
    }

    protected Iterator<E> standardIterator() {
        return listIterator();
    }

    protected ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    protected ListIterator<E> standardListIterator(int i) {
        return Lists.listIteratorImpl(this, i);
    }

    protected List<E> standardSubList(int i, int i2) {
        return Lists.subListImpl(this, i, i2);
    }

    protected boolean standardEquals(Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    protected int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }
}
