package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.CollectSpliterators;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class CollectSpliterators {
    private CollectSpliterators() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Spliterator<T> indexed(int i, int i2, IntFunction<T> intFunction) {
        return indexed(i, i2, intFunction, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Spliterator$OfInt] */
    public static <T> Spliterator<T> indexed(int i, int i2, IntFunction<T> intFunction, Comparator<? super T> comparator) {
        if (comparator != null) {
            Preconditions.checkArgument((i2 & 4) != 0);
        }
        return new C1WithCharacteristics(IntStream.range(0, i).spliterator(), intFunction, i2, comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1WithCharacteristics, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class C1WithCharacteristics<T> implements Spliterator<T> {
        private final Spliterator.OfInt delegate;
        final /* synthetic */ Comparator val$comparator;
        final /* synthetic */ int val$extraCharacteristics;
        final /* synthetic */ IntFunction val$function;

        C1WithCharacteristics(Spliterator.OfInt ofInt, IntFunction intFunction, int i, Comparator comparator) {
            this.val$function = intFunction;
            this.val$extraCharacteristics = i;
            this.val$comparator = comparator;
            this.delegate = ofInt;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(final Consumer<? super T> consumer) {
            Spliterator.OfInt ofInt = this.delegate;
            final IntFunction intFunction = this.val$function;
            return ofInt.tryAdvance(new IntConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1WithCharacteristics$Ppbs97RrPYJsJbzJdcRLgGdu-2Y
                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    consumer.accept(intFunction.apply(i));
                }
            });
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(final Consumer<? super T> consumer) {
            Spliterator.OfInt ofInt = this.delegate;
            final IntFunction intFunction = this.val$function;
            ofInt.forEachRemaining(new IntConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1WithCharacteristics$MwEJ6T__R5HaO67DWYNzWwnkyE0
                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    consumer.accept(intFunction.apply(i));
                }
            });
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator.OfInt trySplit = this.delegate.trySplit();
            if (trySplit == null) {
                return null;
            }
            return new C1WithCharacteristics(trySplit, this.val$function, this.val$extraCharacteristics, this.val$comparator);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.delegate.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.val$extraCharacteristics | 16464;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(4)) {
                return this.val$comparator;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <InElementT, OutElementT> Spliterator<OutElementT> map(Spliterator<InElementT> spliterator, Function<? super InElementT, ? extends OutElementT> function) {
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(function);
        return new C27541(spliterator, function);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [OutElementT] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1 */
    /* loaded from: classes3.dex */
    public class C27541<OutElementT> implements Spliterator<OutElementT> {
        final /* synthetic */ Spliterator val$fromSpliterator;
        final /* synthetic */ Function val$function;

        C27541(Spliterator spliterator, Function function) {
            this.val$fromSpliterator = spliterator;
            this.val$function = function;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(final Consumer<? super OutElementT> consumer) {
            Spliterator spliterator = this.val$fromSpliterator;
            final Function function = this.val$function;
            return spliterator.tryAdvance(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1$tUVVC5zxvercGsU0t5Ecw4g8_CE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    consumer.accept(function.apply(obj));
                }
            });
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(final Consumer<? super OutElementT> consumer) {
            Spliterator spliterator = this.val$fromSpliterator;
            final Function function = this.val$function;
            spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$1$3ja4CkGn22AZNAJvW7vfn5y9SgI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    consumer.accept(function.apply(obj));
                }
            });
        }

        @Override // java.util.Spliterator
        public Spliterator<OutElementT> trySplit() {
            Spliterator trySplit = this.val$fromSpliterator.trySplit();
            if (trySplit != null) {
                return CollectSpliterators.map(trySplit, this.val$function);
            }
            return null;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.val$fromSpliterator.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.val$fromSpliterator.characteristics() & (-262);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Spliterator<T> filter(Spliterator<T> spliterator, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(predicate);
        return new C1Splitr(spliterator, predicate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1Splitr, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class C1Splitr<T> implements Spliterator<T>, Consumer<T> {
        T holder = null;
        final /* synthetic */ Spliterator val$fromSpliterator;
        final /* synthetic */ Predicate val$predicate;

        C1Splitr(Spliterator spliterator, Predicate predicate) {
            this.val$fromSpliterator = spliterator;
            this.val$predicate = predicate;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            while (this.val$fromSpliterator.tryAdvance(this)) {
                try {
                    if (this.val$predicate.test(this.holder)) {
                        consumer.accept(this.holder);
                        return true;
                    }
                } finally {
                    this.holder = null;
                }
            }
            return false;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator<T> trySplit = this.val$fromSpliterator.trySplit();
            if (trySplit == null) {
                return null;
            }
            return CollectSpliterators.filter(trySplit, this.val$predicate);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.val$fromSpliterator.estimateSize() / 2;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            return this.val$fromSpliterator.getComparator();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.val$fromSpliterator.characteristics() & 277;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <InElementT, OutElementT> Spliterator<OutElementT> flatMap(Spliterator<InElementT> spliterator, Function<? super InElementT, Spliterator<OutElementT>> function, int i, long j) {
        Preconditions.checkArgument((i & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((i & 4) == 0, "flatMap does not support SORTED characteristic");
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(function);
        return new FlatMapSpliteratorOfObject(null, spliterator, function, i, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <InElementT> Spliterator.OfInt flatMapToInt(Spliterator<InElementT> spliterator, Function<? super InElementT, Spliterator.OfInt> function, int i, long j) {
        Preconditions.checkArgument((i & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((i & 4) == 0, "flatMap does not support SORTED characteristic");
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(function);
        return new FlatMapSpliteratorOfInt(null, spliterator, function, i, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <InElementT> Spliterator.OfLong flatMapToLong(Spliterator<InElementT> spliterator, Function<? super InElementT, Spliterator.OfLong> function, int i, long j) {
        Preconditions.checkArgument((i & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((i & 4) == 0, "flatMap does not support SORTED characteristic");
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(function);
        return new FlatMapSpliteratorOfLong(null, spliterator, function, i, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <InElementT> Spliterator.OfDouble flatMapToDouble(Spliterator<InElementT> spliterator, Function<? super InElementT, Spliterator.OfDouble> function, int i, long j) {
        Preconditions.checkArgument((i & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((i & 4) == 0, "flatMap does not support SORTED characteristic");
        Preconditions.checkNotNull(spliterator);
        Preconditions.checkNotNull(function);
        return new FlatMapSpliteratorOfDouble(null, spliterator, function, i, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class FlatMapSpliterator<InElementT, OutElementT, OutSpliteratorT extends Spliterator<OutElementT>> implements Spliterator<OutElementT> {
        int characteristics;
        long estimatedSize;
        final Factory<InElementT, OutSpliteratorT> factory;
        final Spliterator<InElementT> from;
        final Function<? super InElementT, OutSpliteratorT> function;
        OutSpliteratorT prefix;

        /* JADX INFO: Access modifiers changed from: package-private */
        @FunctionalInterface
        /* loaded from: classes3.dex */
        public interface Factory<InElementT, OutSpliteratorT extends Spliterator<?>> {
            OutSpliteratorT newFlatMapSpliterator(OutSpliteratorT outspliteratort, Spliterator<InElementT> spliterator, Function<? super InElementT, OutSpliteratorT> function, int i, long j);
        }

        FlatMapSpliterator(OutSpliteratorT outspliteratort, Spliterator<InElementT> spliterator, Function<? super InElementT, OutSpliteratorT> function, Factory<InElementT, OutSpliteratorT> factory, int i, long j) {
            this.prefix = outspliteratort;
            this.from = spliterator;
            this.function = function;
            this.factory = factory;
            this.characteristics = i;
            this.estimatedSize = j;
        }

        @Override // java.util.Spliterator
        public final boolean tryAdvance(Consumer<? super OutElementT> consumer) {
            do {
                OutSpliteratorT outspliteratort = this.prefix;
                if (outspliteratort != null && outspliteratort.tryAdvance(consumer)) {
                    long j = this.estimatedSize;
                    if (j == Long.MAX_VALUE) {
                        return true;
                    }
                    this.estimatedSize = j - 1;
                    return true;
                }
                this.prefix = null;
            } while (this.from.tryAdvance(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$FlatMapSpliterator$cpQ69KIv7G5C6Hx1I5QBJ48DgP4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliterator.this.lambda$tryAdvance$0$CollectSpliterators$FlatMapSpliterator(obj);
                }
            }));
            return false;
        }

        public /* synthetic */ void lambda$tryAdvance$0$CollectSpliterators$FlatMapSpliterator(Object obj) {
            this.prefix = this.function.apply(obj);
        }

        @Override // java.util.Spliterator
        public final void forEachRemaining(final Consumer<? super OutElementT> consumer) {
            OutSpliteratorT outspliteratort = this.prefix;
            if (outspliteratort != null) {
                outspliteratort.forEachRemaining(consumer);
                this.prefix = null;
            }
            this.from.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$FlatMapSpliterator$sH0ponbNrefmSEUcTa6Hqge05mI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliterator.this.lambda$forEachRemaining$1$CollectSpliterators$FlatMapSpliterator(consumer, obj);
                }
            });
            this.estimatedSize = 0L;
        }

        public /* synthetic */ void lambda$forEachRemaining$1$CollectSpliterators$FlatMapSpliterator(Consumer consumer, Object obj) {
            OutSpliteratorT apply = this.function.apply(obj);
            if (apply != null) {
                apply.forEachRemaining(consumer);
            }
        }

        @Override // java.util.Spliterator
        public final OutSpliteratorT trySplit() {
            Spliterator<InElementT> trySplit = this.from.trySplit();
            if (trySplit != null) {
                int i = this.characteristics & (-65);
                long estimateSize = estimateSize();
                if (estimateSize < Long.MAX_VALUE) {
                    estimateSize /= 2;
                    this.estimatedSize -= estimateSize;
                    this.characteristics = i;
                }
                OutSpliteratorT newFlatMapSpliterator = this.factory.newFlatMapSpliterator(this.prefix, trySplit, this.function, i, estimateSize);
                this.prefix = null;
                return newFlatMapSpliterator;
            }
            OutSpliteratorT outspliteratort = this.prefix;
            if (outspliteratort == null) {
                return null;
            }
            this.prefix = null;
            return outspliteratort;
        }

        @Override // java.util.Spliterator
        public final long estimateSize() {
            OutSpliteratorT outspliteratort = this.prefix;
            if (outspliteratort != null) {
                this.estimatedSize = Math.max(this.estimatedSize, outspliteratort.estimateSize());
            }
            return Math.max(this.estimatedSize, 0L);
        }

        @Override // java.util.Spliterator
        public final int characteristics() {
            return this.characteristics;
        }
    }

    /* loaded from: classes3.dex */
    static final class FlatMapSpliteratorOfObject<InElementT, OutElementT> extends FlatMapSpliterator<InElementT, OutElementT, Spliterator<OutElementT>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public FlatMapSpliteratorOfObject(Spliterator<OutElementT> spliterator, Spliterator<InElementT> spliterator2, Function<? super InElementT, Spliterator<OutElementT>> function, int i, long j) {
            super(spliterator, spliterator2, function, new FlatMapSpliterator.Factory() { // from class: com.google.common.collect.-$$Lambda$u770MGGi4iyXdk4TaX9TQ3v79y4
                @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliterator.Factory
                public final Spliterator newFlatMapSpliterator(Spliterator spliterator3, Spliterator spliterator4, Function function2, int i2, long j2) {
                    return new CollectSpliterators.FlatMapSpliteratorOfObject(spliterator3, spliterator4, function2, i2, j2);
                }
            }, i, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class FlatMapSpliteratorOfPrimitive<InElementT, OutElementT, OutConsumerT, OutSpliteratorT extends Spliterator.OfPrimitive<OutElementT, OutConsumerT, OutSpliteratorT>> extends FlatMapSpliterator<InElementT, OutElementT, OutSpliteratorT> implements Spliterator.OfPrimitive<OutElementT, OutConsumerT, OutSpliteratorT> {
        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliterator, java.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) super.trySplit();
        }

        FlatMapSpliteratorOfPrimitive(OutSpliteratorT outspliteratort, Spliterator<InElementT> spliterator, Function<? super InElementT, OutSpliteratorT> function, FlatMapSpliterator.Factory<InElementT, OutSpliteratorT> factory, int i, long j) {
            super(outspliteratort, spliterator, function, factory, i, j);
        }

        @Override // java.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(OutConsumerT outconsumert) {
            do {
                if (this.prefix != 0 && ((Spliterator.OfPrimitive) this.prefix).tryAdvance((Spliterator.OfPrimitive) outconsumert)) {
                    if (this.estimatedSize == Long.MAX_VALUE) {
                        return true;
                    }
                    this.estimatedSize--;
                    return true;
                }
                this.prefix = null;
            } while (this.from.tryAdvance(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$FlatMapSpliteratorOfPrimitive$rH3zDghJFSxHdjFMb024RBGe1kE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliteratorOfPrimitive.this.m623xf55be0fe(obj);
                }
            }));
            return false;
        }

        /* renamed from: lambda$tryAdvance$0$CollectSpliterators$FlatMapSpliteratorOfPrimitive */
        public /* synthetic */ void m623xf55be0fe(Object obj) {
            this.prefix = (OutSpliteratorT) this.function.apply(obj);
        }

        @Override // java.util.Spliterator.OfPrimitive
        public final void forEachRemaining(final OutConsumerT outconsumert) {
            if (this.prefix != 0) {
                ((Spliterator.OfPrimitive) this.prefix).forEachRemaining((Spliterator.OfPrimitive) outconsumert);
                this.prefix = null;
            }
            this.from.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CollectSpliterators$FlatMapSpliteratorOfPrimitive$zGIyeBx--AFXNJGGejHypWIQzhs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CollectSpliterators.FlatMapSpliteratorOfPrimitive.this.m622x168a5ce4(outconsumert, obj);
                }
            });
            this.estimatedSize = 0L;
        }

        /* renamed from: lambda$forEachRemaining$1$CollectSpliterators$FlatMapSpliteratorOfPrimitive */
        public /* synthetic */ void m622x168a5ce4(Object obj, Object obj2) {
            Spliterator.OfPrimitive ofPrimitive = (Spliterator.OfPrimitive) this.function.apply(obj2);
            if (ofPrimitive != null) {
                ofPrimitive.forEachRemaining((Spliterator.OfPrimitive) obj);
            }
        }
    }

    /* loaded from: classes3.dex */
    static final class FlatMapSpliteratorOfInt<InElementT> extends FlatMapSpliteratorOfPrimitive<InElementT, Integer, IntConsumer, Spliterator.OfInt> implements Spliterator.OfInt {
        @Override // java.util.Spliterator.OfInt
        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
            super.forEachRemaining((FlatMapSpliteratorOfInt<InElementT>) intConsumer);
        }

        @Override // java.util.Spliterator.OfInt
        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
            return super.tryAdvance((FlatMapSpliteratorOfInt<InElementT>) intConsumer);
        }

        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliteratorOfPrimitive, com.google.common.collect.CollectSpliterators.FlatMapSpliterator, java.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
            return (Spliterator.OfInt) super.trySplit();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public FlatMapSpliteratorOfInt(Spliterator.OfInt ofInt, Spliterator<InElementT> spliterator, Function<? super InElementT, Spliterator.OfInt> function, int i, long j) {
            super(ofInt, spliterator, function, new FlatMapSpliterator.Factory() { // from class: com.google.common.collect.-$$Lambda$fm_xeEIvkU29mJcUFQBWxICJR48
                @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliterator.Factory
                public final Spliterator newFlatMapSpliterator(Spliterator spliterator2, Spliterator spliterator3, Function function2, int i2, long j2) {
                    return new CollectSpliterators.FlatMapSpliteratorOfInt((Spliterator.OfInt) spliterator2, spliterator3, function2, i2, j2);
                }
            }, i, j);
        }
    }

    /* loaded from: classes3.dex */
    static final class FlatMapSpliteratorOfLong<InElementT> extends FlatMapSpliteratorOfPrimitive<InElementT, Long, LongConsumer, Spliterator.OfLong> implements Spliterator.OfLong {
        @Override // java.util.Spliterator.OfLong
        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
            super.forEachRemaining((FlatMapSpliteratorOfLong<InElementT>) longConsumer);
        }

        @Override // java.util.Spliterator.OfLong
        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
            return super.tryAdvance((FlatMapSpliteratorOfLong<InElementT>) longConsumer);
        }

        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliteratorOfPrimitive, com.google.common.collect.CollectSpliterators.FlatMapSpliterator, java.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
            return (Spliterator.OfLong) super.trySplit();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public FlatMapSpliteratorOfLong(Spliterator.OfLong ofLong, Spliterator<InElementT> spliterator, Function<? super InElementT, Spliterator.OfLong> function, int i, long j) {
            super(ofLong, spliterator, function, new FlatMapSpliterator.Factory() { // from class: com.google.common.collect.-$$Lambda$DfW1rPn1WN87nndXOk2d8to1uxE
                @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliterator.Factory
                public final Spliterator newFlatMapSpliterator(Spliterator spliterator2, Spliterator spliterator3, Function function2, int i2, long j2) {
                    return new CollectSpliterators.FlatMapSpliteratorOfLong((Spliterator.OfLong) spliterator2, spliterator3, function2, i2, j2);
                }
            }, i, j);
        }
    }

    /* loaded from: classes3.dex */
    static final class FlatMapSpliteratorOfDouble<InElementT> extends FlatMapSpliteratorOfPrimitive<InElementT, Double, DoubleConsumer, Spliterator.OfDouble> implements Spliterator.OfDouble {
        @Override // java.util.Spliterator.OfDouble
        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
            super.forEachRemaining((FlatMapSpliteratorOfDouble<InElementT>) doubleConsumer);
        }

        @Override // java.util.Spliterator.OfDouble
        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return super.tryAdvance((FlatMapSpliteratorOfDouble<InElementT>) doubleConsumer);
        }

        @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliteratorOfPrimitive, com.google.common.collect.CollectSpliterators.FlatMapSpliterator, java.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
            return (Spliterator.OfDouble) super.trySplit();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public FlatMapSpliteratorOfDouble(Spliterator.OfDouble ofDouble, Spliterator<InElementT> spliterator, Function<? super InElementT, Spliterator.OfDouble> function, int i, long j) {
            super(ofDouble, spliterator, function, new FlatMapSpliterator.Factory() { // from class: com.google.common.collect.-$$Lambda$AHgDRwE-kxF9DB_bMdLpMNkW530
                @Override // com.google.common.collect.CollectSpliterators.FlatMapSpliterator.Factory
                public final Spliterator newFlatMapSpliterator(Spliterator spliterator2, Spliterator spliterator3, Function function2, int i2, long j2) {
                    return new CollectSpliterators.FlatMapSpliteratorOfDouble((Spliterator.OfDouble) spliterator2, spliterator3, function2, i2, j2);
                }
            }, i, j);
        }
    }
}
