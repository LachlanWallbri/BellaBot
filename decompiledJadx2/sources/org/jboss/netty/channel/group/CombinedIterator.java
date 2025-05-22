package org.jboss.netty.channel.group;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes7.dex */
final class CombinedIterator<E> implements Iterator<E> {
    private Iterator<E> currentIterator;

    /* renamed from: i1 */
    private final Iterator<E> f10018i1;

    /* renamed from: i2 */
    private final Iterator<E> f10019i2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedIterator(Iterator<E> it, Iterator<E> it2) {
        if (it == null) {
            throw new NullPointerException("i1");
        }
        if (it2 == null) {
            throw new NullPointerException("i2");
        }
        this.f10018i1 = it;
        this.f10019i2 = it2;
        this.currentIterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.currentIterator.hasNext()) {
            return true;
        }
        if (this.currentIterator != this.f10018i1) {
            return false;
        }
        this.currentIterator = this.f10019i2;
        return hasNext();
    }

    @Override // java.util.Iterator
    public E next() {
        try {
            return this.currentIterator.next();
        } catch (NoSuchElementException e) {
            if (this.currentIterator == this.f10018i1) {
                this.currentIterator = this.f10019i2;
                return next();
            }
            throw e;
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        this.currentIterator.remove();
    }
}
