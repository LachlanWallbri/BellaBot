package io.grpc.netty.shaded.io.netty.channel.group;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class CombinedIterator<E> implements Iterator<E> {
    private Iterator<E> currentIterator;

    /* renamed from: i1 */
    private final Iterator<E> f8336i1;

    /* renamed from: i2 */
    private final Iterator<E> f8337i2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedIterator(Iterator<E> it, Iterator<E> it2) {
        this.f8336i1 = (Iterator) ObjectUtil.checkNotNull(it, "i1");
        this.f8337i2 = (Iterator) ObjectUtil.checkNotNull(it2, "i2");
        this.currentIterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (!this.currentIterator.hasNext()) {
            if (this.currentIterator != this.f8336i1) {
                return false;
            }
            this.currentIterator = this.f8337i2;
        }
        return true;
    }

    @Override // java.util.Iterator
    public E next() {
        while (true) {
            try {
                return this.currentIterator.next();
            } catch (NoSuchElementException e) {
                if (this.currentIterator == this.f8336i1) {
                    this.currentIterator = this.f8337i2;
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
