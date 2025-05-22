package com.annimon.stream.operator;

import com.annimon.stream.function.UnaryOperator;
import com.annimon.stream.iterator.LsaIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjIterate<T> extends LsaIterator<T> {
    private T current;

    /* renamed from: op */
    private final UnaryOperator<T> f1210op;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public ObjIterate(T t, UnaryOperator<T> unaryOperator) {
        this.f1210op = unaryOperator;
        this.current = t;
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        T t = this.current;
        this.current = this.f1210op.apply(t);
        return t;
    }
}
