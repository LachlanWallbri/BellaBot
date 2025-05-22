package com.annimon.stream.operator;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjFlatMap<T, R> extends LsaExtIterator<R> {
    private Iterator<? extends R> inner;
    private Stream<? extends R> innerStream;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends Stream<? extends R>> mapper;

    public ObjFlatMap(Iterator<? extends T> it, Function<? super T, ? extends Stream<? extends R>> function) {
        this.iterator = it;
        this.mapper = function;
    }

    @Override // com.annimon.stream.iterator.LsaExtIterator
    protected void nextIteration() {
        Iterator<? extends R> it = this.inner;
        if (it != null && it.hasNext()) {
            this.next = this.inner.next();
            this.hasNext = true;
            return;
        }
        while (this.iterator.hasNext()) {
            Iterator<? extends R> it2 = this.inner;
            if (it2 == null || !it2.hasNext()) {
                Stream<? extends R> stream = this.innerStream;
                if (stream != null) {
                    stream.close();
                    this.innerStream = null;
                }
                Stream<? extends R> apply = this.mapper.apply(this.iterator.next());
                if (apply != null) {
                    this.inner = apply.iterator();
                    this.innerStream = apply;
                }
            }
            Iterator<? extends R> it3 = this.inner;
            if (it3 != null && it3.hasNext()) {
                this.next = this.inner.next();
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
        Stream<? extends R> stream2 = this.innerStream;
        if (stream2 != null) {
            stream2.close();
            this.innerStream = null;
        }
    }
}
