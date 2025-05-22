package com.annimon.stream.operator;

import com.annimon.stream.IntStream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjFlatMapToInt<T> extends PrimitiveExtIterator.OfInt {
    private PrimitiveIterator.OfInt inner;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends IntStream> mapper;

    public ObjFlatMapToInt(Iterator<? extends T> it, Function<? super T, ? extends IntStream> function) {
        this.iterator = it;
        this.mapper = function;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfInt
    protected void nextIteration() {
        PrimitiveIterator.OfInt ofInt = this.inner;
        if (ofInt != null && ofInt.hasNext()) {
            this.next = this.inner.next().intValue();
            this.hasNext = true;
            return;
        }
        while (this.iterator.hasNext()) {
            PrimitiveIterator.OfInt ofInt2 = this.inner;
            if (ofInt2 == null || !ofInt2.hasNext()) {
                IntStream apply = this.mapper.apply(this.iterator.next());
                if (apply != null) {
                    this.inner = apply.iterator();
                }
            }
            PrimitiveIterator.OfInt ofInt3 = this.inner;
            if (ofInt3 != null && ofInt3.hasNext()) {
                this.next = this.inner.next().intValue();
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
