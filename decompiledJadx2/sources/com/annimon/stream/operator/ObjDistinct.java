package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaExtIterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjDistinct<T> extends LsaExtIterator<T> {
    private final Iterator<? extends T> iterator;
    private final Set<T> set = new HashSet();

    public ObjDistinct(Iterator<? extends T> it) {
        this.iterator = it;
    }

    @Override // com.annimon.stream.iterator.LsaExtIterator
    protected void nextIteration() {
        do {
            boolean hasNext = this.iterator.hasNext();
            this.hasNext = hasNext;
            if (!hasNext) {
                return;
            } else {
                this.next = this.iterator.next();
            }
        } while (!this.set.add(this.next));
    }
}
