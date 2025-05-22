package com.annimon.stream.operator;

import com.annimon.stream.internal.Operators;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjSorted<T> extends LsaExtIterator<T> {
    private final Comparator<? super T> comparator;
    private final Iterator<? extends T> iterator;
    private Iterator<T> sortedIterator;

    public ObjSorted(Iterator<? extends T> it, Comparator<? super T> comparator) {
        this.iterator = it;
        this.comparator = comparator;
    }

    @Override // com.annimon.stream.iterator.LsaExtIterator
    protected void nextIteration() {
        if (!this.isInit) {
            List list = Operators.toList(this.iterator);
            Collections.sort(list, this.comparator);
            this.sortedIterator = list.iterator();
        }
        this.hasNext = this.sortedIterator.hasNext();
        if (this.hasNext) {
            this.next = this.sortedIterator.next();
        }
    }
}
