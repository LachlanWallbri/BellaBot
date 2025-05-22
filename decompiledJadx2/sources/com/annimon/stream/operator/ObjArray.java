package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjArray<T> extends LsaIterator<T> {
    private final T[] elements;
    private int index = 0;

    public ObjArray(T[] tArr) {
        this.elements = tArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.elements.length;
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        T[] tArr = this.elements;
        int i = this.index;
        this.index = i + 1;
        return tArr[i];
    }
}
