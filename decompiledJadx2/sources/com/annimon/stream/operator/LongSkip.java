package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LongSkip extends PrimitiveIterator.OfLong {
    private final PrimitiveIterator.OfLong iterator;

    /* renamed from: n */
    private final long f1209n;
    private long skipped = 0;

    public LongSkip(PrimitiveIterator.OfLong ofLong, long j) {
        this.iterator = ofLong;
        this.f1209n = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (this.iterator.hasNext() && this.skipped != this.f1209n) {
            this.iterator.nextLong();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        return this.iterator.nextLong();
    }
}
