package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedPredicate;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaExtIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjTakeWhileIndexed<T> extends LsaExtIterator<T> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> predicate;

    public ObjTakeWhileIndexed(IndexedIterator<? extends T> indexedIterator, IndexedPredicate<? super T> indexedPredicate) {
        this.iterator = indexedIterator;
        this.predicate = indexedPredicate;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
    @Override // com.annimon.stream.iterator.LsaExtIterator
    protected void nextIteration() {
        boolean z;
        if (this.iterator.hasNext()) {
            IndexedPredicate<? super T> indexedPredicate = this.predicate;
            int index = this.iterator.getIndex();
            T next = this.iterator.next();
            this.next = next;
            if (indexedPredicate.test(index, next)) {
                z = true;
                this.hasNext = z;
            }
        }
        z = false;
        this.hasNext = z;
    }
}
