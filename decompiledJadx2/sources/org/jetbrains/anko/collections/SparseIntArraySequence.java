package org.jetbrains.anko.collections;

import android.util.SparseIntArray;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Arrays.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0096\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lorg/jetbrains/anko/collections/SparseIntArraySequence;", "Lkotlin/sequences/Sequence;", "", "a", "Landroid/util/SparseIntArray;", "(Landroid/util/SparseIntArray;)V", "iterator", "", "SparseIntArrayIterator", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class SparseIntArraySequence implements Sequence<Integer> {
    private final SparseIntArray a;

    public SparseIntArraySequence(SparseIntArray a) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        this.a = a;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<Integer> iterator() {
        return new SparseIntArrayIterator();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: Arrays.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\t\u0010\u0006\u001a\u00020\u0007H\u0096\u0002J\u000e\u0010\b\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0002\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lorg/jetbrains/anko/collections/SparseIntArraySequence$SparseIntArrayIterator;", "", "", "(Lorg/jetbrains/anko/collections/SparseIntArraySequence;)V", "index", "size", "hasNext", "", ES6Iterator.NEXT_METHOD, "()Ljava/lang/Integer;", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    private final class SparseIntArrayIterator implements Iterator<Integer>, KMappedMarker {
        private int index;
        private final int size;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public SparseIntArrayIterator() {
            this.size = SparseIntArraySequence.this.a.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.size > this.index;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Integer next() {
            if (SparseIntArraySequence.this.a.size() == this.size) {
                SparseIntArray sparseIntArray = SparseIntArraySequence.this.a;
                int i = this.index;
                this.index = i + 1;
                return Integer.valueOf(sparseIntArray.get(i));
            }
            throw new ConcurrentModificationException();
        }
    }
}
