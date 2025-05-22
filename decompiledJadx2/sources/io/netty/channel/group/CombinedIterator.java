package io.netty.channel.group;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
final class CombinedIterator<E> implements Iterator<E> {
    private Iterator<E> currentIterator;

    /* renamed from: i1 */
    private final Iterator<E> f8450i1;

    /* renamed from: i2 */
    private final Iterator<E> f8451i2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedIterator(Iterator<E> it, Iterator<E> it2) {
        if (it == null) {
            throw new NullPointerException("i1");
        }
        if (it2 == null) {
            throw new NullPointerException("i2");
        }
        this.f8450i1 = it;
        this.f8451i2 = it2;
        this.currentIterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (!this.currentIterator.hasNext()) {
            if (this.currentIterator != this.f8450i1) {
                return false;
            }
            this.currentIterator = this.f8451i2;
        }
        return true;
    }

    @Override // java.util.Iterator
    public E next() {
        while (true) {
            try {
                return this.currentIterator.next();
            } catch (NoSuchElementException e) {
                if (this.currentIterator == this.f8450i1) {
                    this.currentIterator = this.f8451i2;
                } else {
                    throw e;
                }
            }
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        this.currentIterator.remove();
    }
}
