package org.jetbrains.anko.collections;

import android.util.SparseArray;
import androidx.exifinterface.media.ExifInterface;
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
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\bB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0096\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lorg/jetbrains/anko/collections/SparseArraySequence;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/Sequence;", "a", "Landroid/util/SparseArray;", "(Landroid/util/SparseArray;)V", "iterator", "", "SparseArrayIterator", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class SparseArraySequence<T> implements Sequence<T> {
    private final SparseArray<T> a;

    public SparseArraySequence(SparseArray<T> a) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        this.a = a;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new SparseArrayIterator();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: Arrays.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0006\u001a\u00020\u0007H\u0096\u0002J\u000e\u0010\b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lorg/jetbrains/anko/collections/SparseArraySequence$SparseArrayIterator;", "", "(Lorg/jetbrains/anko/collections/SparseArraySequence;)V", "index", "", "size", "hasNext", "", ES6Iterator.NEXT_METHOD, "()Ljava/lang/Object;", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    private final class SparseArrayIterator implements Iterator<T>, KMappedMarker {
        private int index;
        private final int size;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public SparseArrayIterator() {
            this.size = SparseArraySequence.this.a.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.size > this.index;
        }

        @Override // java.util.Iterator
        public T next() {
            if (SparseArraySequence.this.a.size() == this.size) {
                SparseArray sparseArray = SparseArraySequence.this.a;
                int i = this.index;
                this.index = i + 1;
                return (T) sparseArray.valueAt(i);
            }
            throw new ConcurrentModificationException();
        }
    }
}
