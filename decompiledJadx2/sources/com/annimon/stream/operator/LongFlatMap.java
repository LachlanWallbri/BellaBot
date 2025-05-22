package com.annimon.stream.operator;

import com.annimon.stream.LongStream;
import com.annimon.stream.function.LongFunction;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongFlatMap extends PrimitiveIterator.OfLong {
    private PrimitiveIterator.OfLong inner;
    private LongStream innerStream;
    private final PrimitiveIterator.OfLong iterator;
    private final LongFunction<? extends LongStream> mapper;

    public LongFlatMap(PrimitiveIterator.OfLong ofLong, LongFunction<? extends LongStream> longFunction) {
        this.iterator = ofLong;
        this.mapper = longFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        PrimitiveIterator.OfLong ofLong = this.inner;
        if (ofLong != null && ofLong.hasNext()) {
            return true;
        }
        while (this.iterator.hasNext()) {
            LongStream longStream = this.innerStream;
            if (longStream != null) {
                longStream.close();
                this.innerStream = null;
            }
            LongStream apply = this.mapper.apply(this.iterator.nextLong());
            if (apply != null) {
                this.innerStream = apply;
                if (apply.iterator().hasNext()) {
                    this.inner = apply.iterator();
                    return true;
                }
            }
        }
        LongStream longStream2 = this.innerStream;
        if (longStream2 == null) {
            return false;
        }
        longStream2.close();
        this.innerStream = null;
        return false;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        PrimitiveIterator.OfLong ofLong = this.inner;
        if (ofLong == null) {
            throw new NoSuchElementException();
        }
        return ofLong.nextLong();
    }
}
