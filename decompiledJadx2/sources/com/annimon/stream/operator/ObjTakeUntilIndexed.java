package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedPredicate;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaExtIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjTakeUntilIndexed<T> extends LsaExtIterator<T> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> stopPredicate;

    public ObjTakeUntilIndexed(IndexedIterator<? extends T> indexedIterator, IndexedPredicate<? super T> indexedPredicate) {
        this.iterator = indexedIterator;
        this.stopPredicate = indexedPredicate;
    }

    @Override // com.annimon.stream.iterator.LsaExtIterator
    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.iterator.getIndex(), this.next));
        if (this.hasNext) {
            this.next = this.iterator.next();
        }
    }
}
