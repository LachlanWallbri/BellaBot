package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjLimit<T> extends LsaIterator<T> {
    private long index = 0;
    private final Iterator<? extends T> iterator;
    private final long maxSize;

    public ObjLimit(Iterator<? extends T> it, long j) {
        this.iterator = it;
        this.maxSize = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        this.index++;
        return this.iterator.next();
    }
}
