package com.annimon.stream.iterator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class PrimitiveIndexedIterator {
    private PrimitiveIndexedIterator() {
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class OfInt extends PrimitiveIterator.OfInt {
        private int index;
        private final PrimitiveIterator.OfInt iterator;
        private final int step;

        public OfInt(PrimitiveIterator.OfInt ofInt) {
            this(0, 1, ofInt);
        }

        public OfInt(int i, int i2, PrimitiveIterator.OfInt ofInt) {
            this.iterator = ofInt;
            this.step = i2;
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
        public int nextInt() {
            int intValue = this.iterator.next().intValue();
            this.index += this.step;
            return intValue;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class OfLong extends PrimitiveIterator.OfLong {
        private int index;
        private final PrimitiveIterator.OfLong iterator;
        private final int step;

        public OfLong(PrimitiveIterator.OfLong ofLong) {
            this(0, 1, ofLong);
        }

        public OfLong(int i, int i2, PrimitiveIterator.OfLong ofLong) {
            this.iterator = ofLong;
            this.step = i2;
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
        public long nextLong() {
            long longValue = this.iterator.next().longValue();
            this.index += this.step;
            return longValue;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class OfDouble extends PrimitiveIterator.OfDouble {
        private int index;
        private final PrimitiveIterator.OfDouble iterator;
        private final int step;

        public OfDouble(PrimitiveIterator.OfDouble ofDouble) {
            this(0, 1, ofDouble);
        }

        public OfDouble(int i, int i2, PrimitiveIterator.OfDouble ofDouble) {
            this.iterator = ofDouble;
            this.step = i2;
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
        public double nextDouble() {
            double doubleValue = this.iterator.next().doubleValue();
            this.index += this.step;
            return doubleValue;
        }
    }
}
