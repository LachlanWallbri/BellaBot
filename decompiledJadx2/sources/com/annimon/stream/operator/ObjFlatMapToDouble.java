package com.annimon.stream.operator;

import com.annimon.stream.DoubleStream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjFlatMapToDouble<T> extends PrimitiveExtIterator.OfDouble {
    private PrimitiveIterator.OfDouble inner;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends DoubleStream> mapper;

    public ObjFlatMapToDouble(Iterator<? extends T> it, Function<? super T, ? extends DoubleStream> function) {
        this.iterator = it;
        this.mapper = function;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble
    protected void nextIteration() {
        PrimitiveIterator.OfDouble ofDouble = this.inner;
        if (ofDouble != null && ofDouble.hasNext()) {
            this.next = this.inner.next().doubleValue();
            this.hasNext = true;
            return;
        }
        while (this.iterator.hasNext()) {
            PrimitiveIterator.OfDouble ofDouble2 = this.inner;
            if (ofDouble2 == null || !ofDouble2.hasNext()) {
                DoubleStream apply = this.mapper.apply(this.iterator.next());
                if (apply != null) {
                    this.inner = apply.iterator();
                }
            }
            PrimitiveIterator.OfDouble ofDouble3 = this.inner;
            if (ofDouble3 != null && ofDouble3.hasNext()) {
                this.next = this.inner.next().doubleValue();
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
