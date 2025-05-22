package com.annimon.stream.operator;

import com.annimon.stream.LongStream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjFlatMapToLong<T> extends PrimitiveExtIterator.OfLong {
    private PrimitiveIterator.OfLong inner;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends LongStream> mapper;

    public ObjFlatMapToLong(Iterator<? extends T> it, Function<? super T, ? extends LongStream> function) {
        this.iterator = it;
        this.mapper = function;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfLong
    protected void nextIteration() {
        PrimitiveIterator.OfLong ofLong = this.inner;
        if (ofLong != null && ofLong.hasNext()) {
            this.next = this.inner.next().longValue();
            this.hasNext = true;
            return;
        }
        while (this.iterator.hasNext()) {
            PrimitiveIterator.OfLong ofLong2 = this.inner;
            if (ofLong2 == null || !ofLong2.hasNext()) {
                LongStream apply = this.mapper.apply(this.iterator.next());
                if (apply != null) {
                    this.inner = apply.iterator();
                }
            }
            PrimitiveIterator.OfLong ofLong3 = this.inner;
            if (ofLong3 != null && ofLong3.hasNext()) {
                this.next = this.inner.next().longValue();
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
