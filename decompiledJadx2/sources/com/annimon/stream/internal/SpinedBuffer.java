package com.annimon.stream.internal;

import com.annimon.stream.function.DoubleConsumer;
import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.function.LongConsumer;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Arrays;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
final class SpinedBuffer {
    private static final int MAX_CHUNK_POWER = 30;
    static final int MIN_CHUNK_POWER = 4;
    static final int MIN_CHUNK_SIZE = 16;
    static final int MIN_SPINE_SIZE = 8;

    private SpinedBuffer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static abstract class OfPrimitive<E, T_ARR, T_CONS> implements Iterable<E> {
        T_ARR curChunk;
        int elementIndex;
        final int initialChunkPower;
        long[] priorElementCount;
        T_ARR[] spine;
        int spineIndex;

        protected abstract int arrayLength(T_ARR t_arr);

        @Override // java.lang.Iterable
        public abstract Iterator<E> iterator();

        protected abstract T_ARR newArray(int i);

        protected abstract T_ARR[] newArrayArray(int i);

        OfPrimitive(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + i);
            }
            this.initialChunkPower = Math.max(4, 32 - Integer.numberOfLeadingZeros(i - 1));
            this.curChunk = newArray(1 << this.initialChunkPower);
        }

        OfPrimitive() {
            this.initialChunkPower = 4;
            this.curChunk = newArray(1 << this.initialChunkPower);
        }

        public boolean isEmpty() {
            return this.spineIndex == 0 && this.elementIndex == 0;
        }

        public long count() {
            int i = this.spineIndex;
            return i == 0 ? this.elementIndex : this.priorElementCount[i] + this.elementIndex;
        }

        int chunkSize(int i) {
            int i2;
            if (i == 0 || i == 1) {
                i2 = this.initialChunkPower;
            } else {
                i2 = Math.min((this.initialChunkPower + i) - 1, 30);
            }
            return 1 << i2;
        }

        long capacity() {
            int i = this.spineIndex;
            if (i == 0) {
                return arrayLength(this.curChunk);
            }
            return arrayLength(this.spine[i]) + this.priorElementCount[i];
        }

        private void inflateSpine() {
            if (this.spine == null) {
                this.spine = newArrayArray(8);
                this.priorElementCount = new long[8];
                this.spine[0] = this.curChunk;
            }
        }

        final void ensureCapacity(long j) {
            long capacity = capacity();
            if (j <= capacity) {
                return;
            }
            inflateSpine();
            int i = this.spineIndex;
            while (true) {
                i++;
                if (j <= capacity) {
                    return;
                }
                T_ARR[] t_arrArr = this.spine;
                if (i >= t_arrArr.length) {
                    int length = t_arrArr.length * 2;
                    this.spine = (T_ARR[]) Arrays.copyOf(t_arrArr, length);
                    this.priorElementCount = Arrays.copyOf(this.priorElementCount, length);
                }
                int chunkSize = chunkSize(i);
                this.spine[i] = newArray(chunkSize);
                long[] jArr = this.priorElementCount;
                jArr[i] = jArr[i - 1] + arrayLength(this.spine[r5]);
                capacity += chunkSize;
            }
        }

        void increaseCapacity() {
            ensureCapacity(capacity() + 1);
        }

        int chunkFor(long j) {
            if (this.spineIndex == 0) {
                if (j < this.elementIndex) {
                    return 0;
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            if (j >= count()) {
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            for (int i = 0; i <= this.spineIndex; i++) {
                if (j < this.priorElementCount[i] + arrayLength(this.spine[i])) {
                    return i;
                }
            }
            throw new IndexOutOfBoundsException(Long.toString(j));
        }

        void copyInto(T_ARR t_arr, int i) {
            long j = i;
            long count = count() + j;
            if (count > arrayLength(t_arr) || count < j) {
                throw new IndexOutOfBoundsException("does not fit");
            }
            if (this.spineIndex == 0) {
                System.arraycopy(this.curChunk, 0, t_arr, i, this.elementIndex);
                return;
            }
            int i2 = i;
            for (int i3 = 0; i3 < this.spineIndex; i3++) {
                T_ARR[] t_arrArr = this.spine;
                System.arraycopy(t_arrArr[i3], 0, t_arr, i2, arrayLength(t_arrArr[i3]));
                i2 += arrayLength(this.spine[i3]);
            }
            int i4 = this.elementIndex;
            if (i4 > 0) {
                System.arraycopy(this.curChunk, 0, t_arr, i2, i4);
            }
        }

        public T_ARR asPrimitiveArray() {
            long count = count();
            Compat.checkMaxArraySize(count);
            T_ARR newArray = newArray((int) count);
            copyInto(newArray, 0);
            return newArray;
        }

        void preAccept() {
            if (this.elementIndex == arrayLength(this.curChunk)) {
                inflateSpine();
                int i = this.spineIndex;
                int i2 = i + 1;
                T_ARR[] t_arrArr = this.spine;
                if (i2 >= t_arrArr.length || t_arrArr[i + 1] == null) {
                    increaseCapacity();
                }
                this.elementIndex = 0;
                this.spineIndex++;
                this.curChunk = this.spine[this.spineIndex];
            }
        }

        public void clear() {
            T_ARR[] t_arrArr = this.spine;
            if (t_arrArr != null) {
                this.curChunk = t_arrArr[0];
                this.spine = null;
                this.priorElementCount = null;
            }
            this.elementIndex = 0;
            this.spineIndex = 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class OfInt extends OfPrimitive<Integer, int[], IntConsumer> implements IntConsumer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public OfInt() {
        }

        OfInt(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public int[][] newArrayArray(int i) {
            return new int[i];
        }

        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public int[] newArray(int i) {
            return new int[i];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public int arrayLength(int[] iArr) {
            return iArr.length;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.annimon.stream.function.IntConsumer
        public void accept(int i) {
            preAccept();
            int[] iArr = (int[]) this.curChunk;
            int i2 = this.elementIndex;
            this.elementIndex = i2 + 1;
            iArr[i2] = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int get(long j) {
            int chunkFor = chunkFor(j);
            if (this.spineIndex == 0 && chunkFor == 0) {
                return ((int[]) this.curChunk)[(int) j];
            }
            return ((int[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive, java.lang.Iterable
        public PrimitiveIterator.OfInt iterator() {
            return new PrimitiveIterator.OfInt() { // from class: com.annimon.stream.internal.SpinedBuffer.OfInt.1
                long index = 0;

                @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
                public int nextInt() {
                    OfInt ofInt = OfInt.this;
                    long j = this.index;
                    this.index = 1 + j;
                    return ofInt.get(j);
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < OfInt.this.count();
                }
            };
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class OfLong extends OfPrimitive<Long, long[], LongConsumer> implements LongConsumer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public OfLong() {
        }

        OfLong(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public long[][] newArrayArray(int i) {
            return new long[i];
        }

        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public long[] newArray(int i) {
            return new long[i];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public int arrayLength(long[] jArr) {
            return jArr.length;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.annimon.stream.function.LongConsumer
        public void accept(long j) {
            preAccept();
            long[] jArr = (long[]) this.curChunk;
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            jArr[i] = j;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public long get(long j) {
            int chunkFor = chunkFor(j);
            if (this.spineIndex == 0 && chunkFor == 0) {
                return ((long[]) this.curChunk)[(int) j];
            }
            return ((long[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive, java.lang.Iterable
        public PrimitiveIterator.OfLong iterator() {
            return new PrimitiveIterator.OfLong() { // from class: com.annimon.stream.internal.SpinedBuffer.OfLong.1
                long index = 0;

                @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
                public long nextLong() {
                    OfLong ofLong = OfLong.this;
                    long j = this.index;
                    this.index = 1 + j;
                    return ofLong.get(j);
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < OfLong.this.count();
                }
            };
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class OfDouble extends OfPrimitive<Double, double[], DoubleConsumer> implements DoubleConsumer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public OfDouble() {
        }

        OfDouble(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public double[][] newArrayArray(int i) {
            return new double[i];
        }

        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public double[] newArray(int i) {
            return new double[i];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive
        public int arrayLength(double[] dArr) {
            return dArr.length;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.annimon.stream.function.DoubleConsumer
        public void accept(double d) {
            preAccept();
            double[] dArr = (double[]) this.curChunk;
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            dArr[i] = d;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public double get(long j) {
            int chunkFor = chunkFor(j);
            if (this.spineIndex == 0 && chunkFor == 0) {
                return ((double[]) this.curChunk)[(int) j];
            }
            return ((double[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        @Override // com.annimon.stream.internal.SpinedBuffer.OfPrimitive, java.lang.Iterable
        public PrimitiveIterator.OfDouble iterator() {
            return new PrimitiveIterator.OfDouble() { // from class: com.annimon.stream.internal.SpinedBuffer.OfDouble.1
                long index = 0;

                @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
                public double nextDouble() {
                    OfDouble ofDouble = OfDouble.this;
                    long j = this.index;
                    this.index = 1 + j;
                    return ofDouble.get(j);
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < OfDouble.this.count();
                }
            };
        }
    }
}
