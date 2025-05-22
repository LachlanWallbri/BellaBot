package com.annimon.stream.operator;

import com.annimon.stream.function.Predicate;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjTakeUntil<T> extends LsaExtIterator<T> {
    private final Iterator<? extends T> iterator;
    private final Predicate<? super T> stopPredicate;

    public ObjTakeUntil(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.stopPredicate = predicate;
    }

    @Override // com.annimon.stream.iterator.LsaExtIterator
    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.next));
        if (this.hasNext) {
            this.next = this.iterator.next();
        }
    }
}
