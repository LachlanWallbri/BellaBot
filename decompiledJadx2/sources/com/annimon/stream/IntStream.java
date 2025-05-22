package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.IndexedIntConsumer;
import com.annimon.stream.function.IndexedIntPredicate;
import com.annimon.stream.function.IntBinaryOperator;
import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.function.IntFunction;
import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.function.IntToDoubleFunction;
import com.annimon.stream.function.IntToLongFunction;
import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.function.ObjIntConsumer;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.internal.Compose;
import com.annimon.stream.internal.Operators;
import com.annimon.stream.internal.Params;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import com.annimon.stream.operator.IntArray;
import com.annimon.stream.operator.IntCodePoints;
import com.annimon.stream.operator.IntConcat;
import com.annimon.stream.operator.IntDropWhile;
import com.annimon.stream.operator.IntFilter;
import com.annimon.stream.operator.IntFilterIndexed;
import com.annimon.stream.operator.IntFlatMap;
import com.annimon.stream.operator.IntGenerate;
import com.annimon.stream.operator.IntIterate;
import com.annimon.stream.operator.IntLimit;
import com.annimon.stream.operator.IntMap;
import com.annimon.stream.operator.IntMapIndexed;
import com.annimon.stream.operator.IntMapToDouble;
import com.annimon.stream.operator.IntMapToLong;
import com.annimon.stream.operator.IntMapToObj;
import com.annimon.stream.operator.IntPeek;
import com.annimon.stream.operator.IntRangeClosed;
import com.annimon.stream.operator.IntSample;
import com.annimon.stream.operator.IntScan;
import com.annimon.stream.operator.IntScanIdentity;
import com.annimon.stream.operator.IntSkip;
import com.annimon.stream.operator.IntSorted;
import com.annimon.stream.operator.IntTakeUntil;
import com.annimon.stream.operator.IntTakeWhile;
import java.io.Closeable;
import java.util.Comparator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class IntStream implements Closeable {
    private static final IntStream EMPTY = new IntStream(new PrimitiveIterator.OfInt() { // from class: com.annimon.stream.IntStream.1
        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
        public int nextInt() {
            return 0;
        }
    });
    private static final ToIntFunction<Integer> UNBOX_FUNCTION = new ToIntFunction<Integer>() { // from class: com.annimon.stream.IntStream.5
        @Override // com.annimon.stream.function.ToIntFunction
        public int applyAsInt(Integer num) {
            return num.intValue();
        }
    };
    private final PrimitiveIterator.OfInt iterator;
    private final Params params;

    public static IntStream empty() {
        return EMPTY;
    }

    /* renamed from: of */
    public static IntStream m516of(PrimitiveIterator.OfInt ofInt) {
        Objects.requireNonNull(ofInt);
        return new IntStream(ofInt);
    }

    /* renamed from: of */
    public static IntStream m517of(int... iArr) {
        Objects.requireNonNull(iArr);
        if (iArr.length == 0) {
            return empty();
        }
        return new IntStream(new IntArray(iArr));
    }

    /* renamed from: of */
    public static IntStream m515of(int i) {
        return new IntStream(new IntArray(new int[]{i}));
    }

    public static IntStream ofCodePoints(CharSequence charSequence) {
        return new IntStream(new IntCodePoints(charSequence));
    }

    public static IntStream range(int i, int i2) {
        if (i >= i2) {
            return empty();
        }
        return rangeClosed(i, i2 - 1);
    }

    public static IntStream rangeClosed(int i, int i2) {
        if (i > i2) {
            return empty();
        }
        if (i == i2) {
            return m515of(i);
        }
        return new IntStream(new IntRangeClosed(i, i2));
    }

    public static IntStream generate(IntSupplier intSupplier) {
        Objects.requireNonNull(intSupplier);
        return new IntStream(new IntGenerate(intSupplier));
    }

    public static IntStream iterate(int i, IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        return new IntStream(new IntIterate(i, intUnaryOperator));
    }

    public static IntStream iterate(int i, IntPredicate intPredicate, IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intPredicate);
        return iterate(i, intUnaryOperator).takeWhile(intPredicate);
    }

    public static IntStream concat(IntStream intStream, IntStream intStream2) {
        Objects.requireNonNull(intStream);
        Objects.requireNonNull(intStream2);
        return new IntStream(new IntConcat(intStream.iterator, intStream2.iterator)).onClose(Compose.closeables(intStream, intStream2));
    }

    private IntStream(PrimitiveIterator.OfInt ofInt) {
        this(null, ofInt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IntStream(Params params, PrimitiveIterator.OfInt ofInt) {
        this.params = params;
        this.iterator = ofInt;
    }

    public PrimitiveIterator.OfInt iterator() {
        return this.iterator;
    }

    public <R> R custom(Function<IntStream, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Stream<Integer> boxed() {
        return new Stream<>(this.params, this.iterator);
    }

    public IntStream filter(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntFilter(this.iterator, intPredicate));
    }

    public IntStream filterIndexed(IndexedIntPredicate indexedIntPredicate) {
        return filterIndexed(0, 1, indexedIntPredicate);
    }

    public IntStream filterIndexed(int i, int i2, IndexedIntPredicate indexedIntPredicate) {
        return new IntStream(this.params, new IntFilterIndexed(new PrimitiveIndexedIterator.OfInt(i, i2, this.iterator), indexedIntPredicate));
    }

    public IntStream filterNot(IntPredicate intPredicate) {
        return filter(IntPredicate.Util.negate(intPredicate));
    }

    public IntStream map(IntUnaryOperator intUnaryOperator) {
        return new IntStream(this.params, new IntMap(this.iterator, intUnaryOperator));
    }

    public IntStream mapIndexed(IntBinaryOperator intBinaryOperator) {
        return mapIndexed(0, 1, intBinaryOperator);
    }

    public IntStream mapIndexed(int i, int i2, IntBinaryOperator intBinaryOperator) {
        return new IntStream(this.params, new IntMapIndexed(new PrimitiveIndexedIterator.OfInt(i, i2, this.iterator), intBinaryOperator));
    }

    public <R> Stream<R> mapToObj(IntFunction<? extends R> intFunction) {
        return new Stream<>(this.params, new IntMapToObj(this.iterator, intFunction));
    }

    public LongStream mapToLong(IntToLongFunction intToLongFunction) {
        return new LongStream(this.params, new IntMapToLong(this.iterator, intToLongFunction));
    }

    public DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
        return new DoubleStream(this.params, new IntMapToDouble(this.iterator, intToDoubleFunction));
    }

    public IntStream flatMap(IntFunction<? extends IntStream> intFunction) {
        return new IntStream(this.params, new IntFlatMap(this.iterator, intFunction));
    }

    public IntStream distinct() {
        return boxed().distinct().mapToInt(UNBOX_FUNCTION);
    }

    public IntStream sorted() {
        return new IntStream(this.params, new IntSorted(this.iterator));
    }

    public IntStream sorted(Comparator<Integer> comparator) {
        return boxed().sorted(comparator).mapToInt(UNBOX_FUNCTION);
    }

    public IntStream sample(int i) {
        if (i > 0) {
            return i == 1 ? this : new IntStream(this.params, new IntSample(this.iterator, i));
        }
        throw new IllegalArgumentException("stepWidth cannot be zero or negative");
    }

    public IntStream peek(IntConsumer intConsumer) {
        return new IntStream(this.params, new IntPeek(this.iterator, intConsumer));
    }

    public IntStream scan(IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new IntStream(this.params, new IntScan(this.iterator, intBinaryOperator));
    }

    public IntStream scan(int i, IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new IntStream(this.params, new IntScanIdentity(this.iterator, i, intBinaryOperator));
    }

    public IntStream takeWhile(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntTakeWhile(this.iterator, intPredicate));
    }

    public IntStream takeUntil(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntTakeUntil(this.iterator, intPredicate));
    }

    public IntStream dropWhile(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntDropWhile(this.iterator, intPredicate));
    }

    public IntStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("maxSize cannot be negative");
        }
        if (j == 0) {
            return empty();
        }
        return new IntStream(this.params, new IntLimit(this.iterator, j));
    }

    public IntStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : new IntStream(this.params, new IntSkip(this.iterator, j));
        }
        throw new IllegalArgumentException("n cannot be negative");
    }

    public void forEach(IntConsumer intConsumer) {
        while (this.iterator.hasNext()) {
            intConsumer.accept(this.iterator.nextInt());
        }
    }

    public void forEachIndexed(IndexedIntConsumer indexedIntConsumer) {
        forEachIndexed(0, 1, indexedIntConsumer);
    }

    public void forEachIndexed(int i, int i2, IndexedIntConsumer indexedIntConsumer) {
        while (this.iterator.hasNext()) {
            indexedIntConsumer.accept(i, this.iterator.nextInt());
            i += i2;
        }
    }

    public int reduce(int i, IntBinaryOperator intBinaryOperator) {
        while (this.iterator.hasNext()) {
            i = intBinaryOperator.applyAsInt(i, this.iterator.nextInt());
        }
        return i;
    }

    public OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        boolean z = false;
        int i = 0;
        while (this.iterator.hasNext()) {
            int nextInt = this.iterator.nextInt();
            if (z) {
                i = intBinaryOperator.applyAsInt(i, nextInt);
            } else {
                z = true;
                i = nextInt;
            }
        }
        return z ? OptionalInt.m527of(i) : OptionalInt.empty();
    }

    public int[] toArray() {
        return Operators.toIntArray(this.iterator);
    }

    public <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> objIntConsumer) {
        R r = supplier.get();
        while (this.iterator.hasNext()) {
            objIntConsumer.accept(r, this.iterator.nextInt());
        }
        return r;
    }

    public int sum() {
        int i = 0;
        while (this.iterator.hasNext()) {
            i += this.iterator.nextInt();
        }
        return i;
    }

    public OptionalInt min() {
        return reduce(new IntBinaryOperator() { // from class: com.annimon.stream.IntStream.2
            @Override // com.annimon.stream.function.IntBinaryOperator
            public int applyAsInt(int i, int i2) {
                return i < i2 ? i : i2;
            }
        });
    }

    public OptionalInt max() {
        return reduce(new IntBinaryOperator() { // from class: com.annimon.stream.IntStream.3
            @Override // com.annimon.stream.function.IntBinaryOperator
            public int applyAsInt(int i, int i2) {
                return i > i2 ? i : i2;
            }
        });
    }

    public long count() {
        long j = 0;
        while (this.iterator.hasNext()) {
            this.iterator.nextInt();
            j++;
        }
        return j;
    }

    public boolean anyMatch(IntPredicate intPredicate) {
        while (this.iterator.hasNext()) {
            if (intPredicate.test(this.iterator.nextInt())) {
                return true;
            }
        }
        return false;
    }

    public boolean allMatch(IntPredicate intPredicate) {
        while (this.iterator.hasNext()) {
            if (!intPredicate.test(this.iterator.nextInt())) {
                return false;
            }
        }
        return true;
    }

    public boolean noneMatch(IntPredicate intPredicate) {
        while (this.iterator.hasNext()) {
            if (intPredicate.test(this.iterator.nextInt())) {
                return false;
            }
        }
        return true;
    }

    public OptionalInt findFirst() {
        if (this.iterator.hasNext()) {
            return OptionalInt.m527of(this.iterator.nextInt());
        }
        return OptionalInt.empty();
    }

    public OptionalInt findLast() {
        return reduce(new IntBinaryOperator() { // from class: com.annimon.stream.IntStream.4
            @Override // com.annimon.stream.function.IntBinaryOperator
            public int applyAsInt(int i, int i2) {
                return i2;
            }
        });
    }

    public int single() {
        if (this.iterator.hasNext()) {
            int nextInt = this.iterator.nextInt();
            if (this.iterator.hasNext()) {
                throw new IllegalStateException("IntStream contains more than one element");
            }
            return nextInt;
        }
        throw new NoSuchElementException("IntStream contains no element");
    }

    public OptionalInt findSingle() {
        if (this.iterator.hasNext()) {
            int nextInt = this.iterator.nextInt();
            if (this.iterator.hasNext()) {
                throw new IllegalStateException("IntStream contains more than one element");
            }
            return OptionalInt.m527of(nextInt);
        }
        return OptionalInt.empty();
    }

    public IntStream onClose(Runnable runnable) {
        Objects.requireNonNull(runnable);
        Params params = this.params;
        if (params == null) {
            params = new Params();
            params.closeHandler = runnable;
        } else {
            params.closeHandler = Compose.runnables(params.closeHandler, runnable);
        }
        return new IntStream(params, this.iterator);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Params params = this.params;
        if (params == null || params.closeHandler == null) {
            return;
        }
        this.params.closeHandler.run();
        this.params.closeHandler = null;
    }
}
