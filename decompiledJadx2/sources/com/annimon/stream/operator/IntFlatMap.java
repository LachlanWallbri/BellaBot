package com.annimon.stream.operator;

import com.annimon.stream.IntStream;
import com.annimon.stream.function.IntFunction;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntFlatMap extends PrimitiveIterator.OfInt {
    private PrimitiveIterator.OfInt inner;
    private IntStream innerStream;
    private final PrimitiveIterator.OfInt iterator;
    private final IntFunction<? extends IntStream> mapper;

    public IntFlatMap(PrimitiveIterator.OfInt ofInt, IntFunction<? extends IntStream> intFunction) {
        this.iterator = ofInt;
        this.mapper = intFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        PrimitiveIterator.OfInt ofInt = this.inner;
        if (ofInt != null && ofInt.hasNext()) {
            return true;
        }
        while (this.iterator.hasNext()) {
            IntStream intStream = this.innerStream;
            if (intStream != null) {
                intStream.close();
                this.innerStream = null;
            }
            IntStream apply = this.mapper.apply(this.iterator.nextInt());
            if (apply != null) {
                this.innerStream = apply;
                if (apply.iterator().hasNext()) {
                    this.inner = apply.iterator();
                    return true;
                }
            }
        }
        IntStream intStream2 = this.innerStream;
        if (intStream2 == null) {
            return false;
        }
        intStream2.close();
        this.innerStream = null;
        return false;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        PrimitiveIterator.OfInt ofInt = this.inner;
        if (ofInt == null) {
            throw new NoSuchElementException();
        }
        return ofInt.nextInt();
    }
}
