package io.netty.channel.nio;

import java.nio.channels.SelectionKey;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class SelectedSelectionKeySet extends AbstractSet<SelectionKey> {
    SelectionKey[] keys = new SelectionKey[1024];
    int size;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(SelectionKey selectionKey) {
        if (selectionKey == null) {
            return false;
        }
        SelectionKey[] selectionKeyArr = this.keys;
        int i = this.size;
        int i2 = i + 1;
        this.size = i2;
        selectionKeyArr[i] = selectionKey;
        if (i2 != selectionKeyArr.length) {
            return true;
        }
        increaseCapacity();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: io.netty.channel.nio.SelectedSelectionKeySet$1 */
    /* loaded from: classes5.dex */
    class C70461 implements Iterator<SelectionKey> {
        private int idx;

        C70461() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.idx < SelectedSelectionKeySet.this.size;
        }

        @Override // java.util.Iterator
        public SelectionKey next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            SelectionKey[] selectionKeyArr = SelectedSelectionKeySet.this.keys;
            int i = this.idx;
            this.idx = i + 1;
            return selectionKeyArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<SelectionKey> iterator() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        reset(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset(int i) {
        Arrays.fill(this.keys, i, this.size, (Object) null);
        this.size = 0;
    }

    private void increaseCapacity() {
        SelectionKey[] selectionKeyArr = this.keys;
        SelectionKey[] selectionKeyArr2 = new SelectionKey[selectionKeyArr.length << 1];
        System.arraycopy(selectionKeyArr, 0, selectionKeyArr2, 0, this.size);
        this.keys = selectionKeyArr2;
    }
}
