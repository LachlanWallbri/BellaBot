package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedPredicate;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaExtIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjDropWhileIndexed<T> extends LsaExtIterator<T> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> predicate;

    public ObjDropWhileIndexed(IndexedIterator<? extends T> indexedIterator, IndexedPredicate<? super T> indexedPredicate) {
        this.iterator = indexedIterator;
        this.predicate = indexedPredicate;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
    
        if (r3.hasNext == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r3.iterator.hasNext() == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
    
        r3.hasNext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
    
        if (r3.hasNext != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
    
        r3.next = r3.iterator.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0035, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0002, code lost:
    
        if (r3.isInit == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0004, code lost:
    
        r0 = r3.iterator.hasNext();
        r3.hasNext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        r0 = r3.iterator.getIndex();
        r3.next = r3.iterator.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0024, code lost:
    
        if (r3.predicate.test(r0, r3.next) != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0026, code lost:
    
        return;
     */
    @Override // com.annimon.stream.iterator.LsaExtIterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void nextIteration() {
    }
}
