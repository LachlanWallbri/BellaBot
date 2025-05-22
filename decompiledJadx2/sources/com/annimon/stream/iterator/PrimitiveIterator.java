package com.annimon.stream.iterator;

import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class PrimitiveIterator {
    private PrimitiveIterator() {
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static abstract class OfInt implements Iterator<Integer> {
        public abstract int nextInt();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Integer next() {
            return Integer.valueOf(nextInt());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static abstract class OfLong implements Iterator<Long> {
        public abstract long nextLong();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Long next() {
            return Long.valueOf(nextLong());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static abstract class OfDouble implements Iterator<Double> {
        public abstract double nextDouble();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Double next() {
            return Double.valueOf(nextDouble());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
