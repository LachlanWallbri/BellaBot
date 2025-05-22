package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedIntPredicate;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntFilterIndexed extends PrimitiveIterator.OfInt {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final PrimitiveIndexedIterator.OfInt iterator;
    private int next;
    private final IndexedIntPredicate predicate;

    public IntFilterIndexed(PrimitiveIndexedIterator.OfInt ofInt, IndexedIntPredicate indexedIntPredicate) {
        this.iterator = ofInt;
        this.predicate = indexedIntPredicate;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        if (!this.hasNextEvaluated) {
            this.hasNext = hasNext();
        }
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        this.hasNextEvaluated = false;
        return this.next;
    }

    private void nextIteration() {
        while (this.iterator.hasNext()) {
            int index = this.iterator.getIndex();
            this.next = this.iterator.next().intValue();
            if (this.predicate.test(index, this.next)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
