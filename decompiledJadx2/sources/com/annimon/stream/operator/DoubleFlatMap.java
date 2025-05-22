package com.annimon.stream.operator;

import com.annimon.stream.DoubleStream;
import com.annimon.stream.function.DoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleFlatMap extends PrimitiveIterator.OfDouble {
    private PrimitiveIterator.OfDouble inner;
    private DoubleStream innerStream;
    private final PrimitiveIterator.OfDouble iterator;
    private final DoubleFunction<? extends DoubleStream> mapper;

    public DoubleFlatMap(PrimitiveIterator.OfDouble ofDouble, DoubleFunction<? extends DoubleStream> doubleFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        PrimitiveIterator.OfDouble ofDouble = this.inner;
        if (ofDouble != null && ofDouble.hasNext()) {
            return true;
        }
        while (this.iterator.hasNext()) {
            DoubleStream doubleStream = this.innerStream;
            if (doubleStream != null) {
                doubleStream.close();
                this.innerStream = null;
            }
            DoubleStream apply = this.mapper.apply(this.iterator.nextDouble());
            if (apply != null) {
                this.innerStream = apply;
                if (apply.iterator().hasNext()) {
                    this.inner = apply.iterator();
                    return true;
                }
            }
        }
        DoubleStream doubleStream2 = this.innerStream;
        if (doubleStream2 == null) {
            return false;
        }
        doubleStream2.close();
        this.innerStream = null;
        return false;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        PrimitiveIterator.OfDouble ofDouble = this.inner;
        if (ofDouble == null) {
            throw new NoSuchElementException();
        }
        return ofDouble.nextDouble();
    }
}
