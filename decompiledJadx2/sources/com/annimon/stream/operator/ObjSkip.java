package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjSkip<T> extends LsaIterator<T> {
    private final Iterator<? extends T> iterator;

    /* renamed from: n */
    private final long f1211n;
    private long skipped = 0;

    public ObjSkip(Iterator<? extends T> it, long j) {
        this.iterator = it;
        this.f1211n = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (this.skipped < this.f1211n) {
            if (!this.iterator.hasNext()) {
                return false;
            }
            this.iterator.next();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        return this.iterator.next();
    }
}
