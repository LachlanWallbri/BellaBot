package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.IndexedLongConsumer;
import com.annimon.stream.function.IndexedLongPredicate;
import com.annimon.stream.function.IndexedLongUnaryOperator;
import com.annimon.stream.function.LongBinaryOperator;
import com.annimon.stream.function.LongConsumer;
import com.annimon.stream.function.LongFunction;
import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.function.LongSupplier;
import com.annimon.stream.function.LongToDoubleFunction;
import com.annimon.stream.function.LongToIntFunction;
import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.function.ObjLongConsumer;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToLongFunction;
import com.annimon.stream.internal.Compose;
import com.annimon.stream.internal.Operators;
import com.annimon.stream.internal.Params;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import com.annimon.stream.operator.LongArray;
import com.annimon.stream.operator.LongConcat;
import com.annimon.stream.operator.LongDropWhile;
import com.annimon.stream.operator.LongFilter;
import com.annimon.stream.operator.LongFilterIndexed;
import com.annimon.stream.operator.LongFlatMap;
import com.annimon.stream.operator.LongGenerate;
import com.annimon.stream.operator.LongIterate;
import com.annimon.stream.operator.LongLimit;
import com.annimon.stream.operator.LongMap;
import com.annimon.stream.operator.LongMapIndexed;
import com.annimon.stream.operator.LongMapToDouble;
import com.annimon.stream.operator.LongMapToInt;
import com.annimon.stream.operator.LongMapToObj;
import com.annimon.stream.operator.LongPeek;
import com.annimon.stream.operator.LongRangeClosed;
import com.annimon.stream.operator.LongSample;
import com.annimon.stream.operator.LongScan;
import com.annimon.stream.operator.LongScanIdentity;
import com.annimon.stream.operator.LongSkip;
import com.annimon.stream.operator.LongSorted;
import com.annimon.stream.operator.LongTakeUntil;
import com.annimon.stream.operator.LongTakeWhile;
import java.io.Closeable;
import java.util.Comparator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class LongStream implements Closeable {
    private static final LongStream EMPTY = new LongStream(new PrimitiveIterator.OfLong() { // from class: com.annimon.stream.LongStream.1
        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
        public long nextLong() {
            return 0L;
        }
    });
    private static final ToLongFunction<Long> UNBOX_FUNCTION = new ToLongFunction<Long>() { // from class: com.annimon.stream.LongStream.5
        @Override // com.annimon.stream.function.ToLongFunction
        public long applyAsLong(Long l) {
            return l.longValue();
        }
    };
    private final PrimitiveIterator.OfLong iterator;
    private final Params params;

    public static LongStream empty() {
        return EMPTY;
    }

    /* renamed from: of */
    public static LongStream m519of(PrimitiveIterator.OfLong ofLong) {
        Objects.requireNonNull(ofLong);
        return new LongStream(ofLong);
    }

    /* renamed from: of */
    public static LongStream m520of(long... jArr) {
        Objects.requireNonNull(jArr);
        if (jArr.length == 0) {
            return empty();
        }
        return new LongStream(new LongArray(jArr));
    }

    /* renamed from: of */
    public static LongStream m518of(long j) {
        return new LongStream(new LongArray(new long[]{j}));
    }

    public static LongStream range(long j, long j2) {
        if (j >= j2) {
            return empty();
        }
        return rangeClosed(j, j2 - 1);
    }

    public static LongStream rangeClosed(long j, long j2) {
        if (j > j2) {
            return empty();
        }
        if (j == j2) {
            return m518of(j);
        }
        return new LongStream(new LongRangeClosed(j, j2));
    }

    public static LongStream generate(LongSupplier longSupplier) {
        Objects.requireNonNull(longSupplier);
        return new LongStream(new LongGenerate(longSupplier));
    }

    public static LongStream iterate(long j, LongUnaryOperator longUnaryOperator) {
        Objects.requireNonNull(longUnaryOperator);
        return new LongStream(new LongIterate(j, longUnaryOperator));
    }

    public static LongStream iterate(long j, LongPredicate longPredicate, LongUnaryOperator longUnaryOperator) {
        Objects.requireNonNull(longPredicate);
        return iterate(j, longUnaryOperator).takeWhile(longPredicate);
    }

    public static LongStream concat(LongStream longStream, LongStream longStream2) {
        Objects.requireNonNull(longStream);
        Objects.requireNonNull(longStream2);
        return new LongStream(new LongConcat(longStream.iterator, longStream2.iterator)).onClose(Compose.closeables(longStream, longStream2));
    }

    private LongStream(PrimitiveIterator.OfLong ofLong) {
        this(null, ofLong);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LongStream(Params params, PrimitiveIterator.OfLong ofLong) {
        this.params = params;
        this.iterator = ofLong;
    }

    public PrimitiveIterator.OfLong iterator() {
        return this.iterator;
    }

    public <R> R custom(Function<LongStream, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Stream<Long> boxed() {
        return new Stream<>(this.params, this.iterator);
    }

    public LongStream filter(LongPredicate longPredicate) {
        return new LongStream(this.params, new LongFilter(this.iterator, longPredicate));
    }

    public LongStream filterIndexed(IndexedLongPredicate indexedLongPredicate) {
        return filterIndexed(0, 1, indexedLongPredicate);
    }

    public LongStream filterIndexed(int i, int i2, IndexedLongPredicate indexedLongPredicate) {
        return new LongStream(this.params, new LongFilterIndexed(new PrimitiveIndexedIterator.OfLong(i, i2, this.iterator), indexedLongPredicate));
    }

    public LongStream filterNot(LongPredicate longPredicate) {
        return filter(LongPredicate.Util.negate(longPredicate));
    }

    public LongStream map(LongUnaryOperator longUnaryOperator) {
        return new LongStream(this.params, new LongMap(this.iterator, longUnaryOperator));
    }

    public LongStream mapIndexed(IndexedLongUnaryOperator indexedLongUnaryOperator) {
        return mapIndexed(0, 1, indexedLongUnaryOperator);
    }

    public LongStream mapIndexed(int i, int i2, IndexedLongUnaryOperator indexedLongUnaryOperator) {
        return new LongStream(this.params, new LongMapIndexed(new PrimitiveIndexedIterator.OfLong(i, i2, this.iterator), indexedLongUnaryOperator));
    }

    public <R> Stream<R> mapToObj(LongFunction<? extends R> longFunction) {
        return new Stream<>(this.params, new LongMapToObj(this.iterator, longFunction));
    }

    public IntStream mapToInt(LongToIntFunction longToIntFunction) {
        return new IntStream(this.params, new LongMapToInt(this.iterator, longToIntFunction));
    }

    public DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
        return new DoubleStream(this.params, new LongMapToDouble(this.iterator, longToDoubleFunction));
    }

    public LongStream flatMap(LongFunction<? extends LongStream> longFunction) {
        return new LongStream(this.params, new LongFlatMap(this.iterator, longFunction));
    }

    public LongStream distinct() {
        return boxed().distinct().mapToLong(UNBOX_FUNCTION);
    }

    public LongStream sorted() {
        return new LongStream(this.params, new LongSorted(this.iterator));
    }

    public LongStream sorted(Comparator<Long> comparator) {
        return boxed().sorted(comparator).mapToLong(UNBOX_FUNCTION);
    }

    public LongStream sample(int i) {
        if (i > 0) {
            return i == 1 ? this : new LongStream(this.params, new LongSample(this.iterator, i));
        }
        throw new IllegalArgumentException("stepWidth cannot be zero or negative");
    }

    public LongStream peek(LongConsumer longConsumer) {
        return new LongStream(this.params, new LongPeek(this.iterator, longConsumer));
    }

    public LongStream scan(LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new LongStream(this.params, new LongScan(this.iterator, longBinaryOperator));
    }

    public LongStream scan(long j, LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new LongStream(this.params, new LongScanIdentity(this.iterator, j, longBinaryOperator));
    }

    public LongStream takeWhile(LongPredicate longPredicate) {
        return new LongStream(this.params, new LongTakeWhile(this.iterator, longPredicate));
    }

    public LongStream takeUntil(LongPredicate longPredicate) {
        return new LongStream(this.params, new LongTakeUntil(this.iterator, longPredicate));
    }

    public LongStream dropWhile(LongPredicate longPredicate) {
        return new LongStream(this.params, new LongDropWhile(this.iterator, longPredicate));
    }

    public LongStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("maxSize cannot be negative");
        }
        if (j == 0) {
            return empty();
        }
        return new LongStream(this.params, new LongLimit(this.iterator, j));
    }

    public LongStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : new LongStream(this.params, new LongSkip(this.iterator, j));
        }
        throw new IllegalArgumentException("n cannot be negative");
    }

    public void forEach(LongConsumer longConsumer) {
        while (this.iterator.hasNext()) {
            longConsumer.accept(this.iterator.nextLong());
        }
    }

    public void forEachIndexed(IndexedLongConsumer indexedLongConsumer) {
        forEachIndexed(0, 1, indexedLongConsumer);
    }

    public void forEachIndexed(int i, int i2, IndexedLongConsumer indexedLongConsumer) {
        while (this.iterator.hasNext()) {
            indexedLongConsumer.accept(i, this.iterator.nextLong());
            i += i2;
        }
    }

    public long reduce(long j, LongBinaryOperator longBinaryOperator) {
        while (this.iterator.hasNext()) {
            j = longBinaryOperator.applyAsLong(j, this.iterator.nextLong());
        }
        return j;
    }

    public OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
        boolean z = false;
        long j = 0;
        while (this.iterator.hasNext()) {
            long nextLong = this.iterator.nextLong();
            if (z) {
                j = longBinaryOperator.applyAsLong(j, nextLong);
            } else {
                z = true;
                j = nextLong;
            }
        }
        return z ? OptionalLong.m529of(j) : OptionalLong.empty();
    }

    public long[] toArray() {
        return Operators.toLongArray(this.iterator);
    }

    public <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> objLongConsumer) {
        R r = supplier.get();
        while (this.iterator.hasNext()) {
            objLongConsumer.accept(r, this.iterator.nextLong());
        }
        return r;
    }

    public long sum() {
        long j = 0;
        while (this.iterator.hasNext()) {
            j += this.iterator.nextLong();
        }
        return j;
    }

    public OptionalLong min() {
        return reduce(new LongBinaryOperator() { // from class: com.annimon.stream.LongStream.2
            @Override // com.annimon.stream.function.LongBinaryOperator
            public long applyAsLong(long j, long j2) {
                return Math.min(j, j2);
            }
        });
    }

    public OptionalLong max() {
        return reduce(new LongBinaryOperator() { // from class: com.annimon.stream.LongStream.3
            @Override // com.annimon.stream.function.LongBinaryOperator
            public long applyAsLong(long j, long j2) {
                return Math.max(j, j2);
            }
        });
    }

    public long count() {
        long j = 0;
        while (this.iterator.hasNext()) {
            this.iterator.nextLong();
            j++;
        }
        return j;
    }

    public boolean anyMatch(LongPredicate longPredicate) {
        while (this.iterator.hasNext()) {
            if (longPredicate.test(this.iterator.nextLong())) {
                return true;
            }
        }
        return false;
    }

    public boolean allMatch(LongPredicate longPredicate) {
        while (this.iterator.hasNext()) {
            if (!longPredicate.test(this.iterator.nextLong())) {
                return false;
            }
        }
        return true;
    }

    public boolean noneMatch(LongPredicate longPredicate) {
        while (this.iterator.hasNext()) {
            if (longPredicate.test(this.iterator.nextLong())) {
                return false;
            }
        }
        return true;
    }

    public OptionalLong findFirst() {
        if (this.iterator.hasNext()) {
            return OptionalLong.m529of(this.iterator.nextLong());
        }
        return OptionalLong.empty();
    }

    public OptionalLong findLast() {
        return reduce(new LongBinaryOperator() { // from class: com.annimon.stream.LongStream.4
            @Override // com.annimon.stream.function.LongBinaryOperator
            public long applyAsLong(long j, long j2) {
                return j2;
            }
        });
    }

    public long single() {
        if (!this.iterator.hasNext()) {
            throw new NoSuchElementException("LongStream contains no element");
        }
        long nextLong = this.iterator.nextLong();
        if (this.iterator.hasNext()) {
            throw new IllegalStateException("LongStream contains more than one element");
        }
        return nextLong;
    }

    public OptionalLong findSingle() {
        if (!this.iterator.hasNext()) {
            return OptionalLong.empty();
        }
        long nextLong = this.iterator.nextLong();
        if (this.iterator.hasNext()) {
            throw new IllegalStateException("LongStream contains more than one element");
        }
        return OptionalLong.m529of(nextLong);
    }

    public LongStream onClose(Runnable runnable) {
        Objects.requireNonNull(runnable);
        Params params = this.params;
        if (params == null) {
            params = new Params();
            params.closeHandler = runnable;
        } else {
            params.closeHandler = Compose.runnables(params.closeHandler, runnable);
        }
        return new LongStream(params, this.iterator);
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
