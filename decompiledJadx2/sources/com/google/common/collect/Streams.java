package com.google.common.collect;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.google.common.math.LongMath;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/* loaded from: classes3.dex */
public final class Streams {

    /* loaded from: classes3.dex */
    public interface DoubleFunctionWithIndex<R> {
        R apply(double d, long j);
    }

    /* loaded from: classes3.dex */
    public interface FunctionWithIndex<T, R> {
        R apply(T t, long j);
    }

    /* loaded from: classes3.dex */
    public interface IntFunctionWithIndex<R> {
        R apply(int i, long j);
    }

    /* loaded from: classes3.dex */
    public interface LongFunctionWithIndex<R> {
        R apply(long j, long j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Spliterator lambda$concat$0(Spliterator spliterator) {
        return spliterator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Spliterator.OfInt lambda$concat$2(Spliterator.OfInt ofInt) {
        return ofInt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Spliterator.OfLong lambda$concat$4(Spliterator.OfLong ofLong) {
        return ofLong;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Spliterator.OfDouble lambda$concat$6(Spliterator.OfDouble ofDouble) {
        return ofDouble;
    }

    public static <T> Stream<T> stream(Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).stream();
        }
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    @Deprecated
    public static <T> Stream<T> stream(Collection<T> collection) {
        return collection.stream();
    }

    public static <T> Stream<T> stream(Iterator<T> it) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, 0), false);
    }

    public static <T> Stream<T> stream(Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
    }

    public static <T> Stream<T> stream(java.util.Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
    }

    public static IntStream stream(OptionalInt optionalInt) {
        return optionalInt.isPresent() ? IntStream.of(optionalInt.getAsInt()) : IntStream.empty();
    }

    public static LongStream stream(OptionalLong optionalLong) {
        return optionalLong.isPresent() ? LongStream.of(optionalLong.getAsLong()) : LongStream.empty();
    }

    public static DoubleStream stream(OptionalDouble optionalDouble) {
        return optionalDouble.isPresent() ? DoubleStream.of(optionalDouble.getAsDouble()) : DoubleStream.empty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void closeAll(BaseStream<?, ?>[] baseStreamArr) {
        for (BaseStream<?, ?> baseStream : baseStreamArr) {
            baseStream.close();
        }
    }

    @SafeVarargs
    public static <T> Stream<T> concat(final Stream<? extends T>... streamArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder(streamArr.length);
        int i = 336;
        long j = 0;
        boolean z = false;
        for (Stream<? extends T> stream : streamArr) {
            z |= stream.isParallel();
            Spliterator<? extends T> spliterator = stream.spliterator();
            builder.add((ImmutableList.Builder) spliterator);
            i &= spliterator.characteristics();
            j = LongMath.saturatedAdd(j, spliterator.estimateSize());
        }
        return (Stream) StreamSupport.stream(CollectSpliterators.flatMap(builder.build().spliterator(), new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$pg5lCmobtfFNBTlUWWOJMXOdCHI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$0((Spliterator) obj);
            }
        }, i, j), z).onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$Streams$-fJ3BDxCKVPJ_jbG_129irN7jjs
            @Override // java.lang.Runnable
            public final void run() {
                Streams.closeAll(streamArr);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.Spliterator$OfInt, java.lang.Object] */
    public static IntStream concat(final IntStream... intStreamArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder(intStreamArr.length);
        int i = 336;
        long j = 0;
        boolean z = false;
        for (IntStream intStream : intStreamArr) {
            z |= intStream.isParallel();
            ?? spliterator = intStream.spliterator();
            builder.add((ImmutableList.Builder) spliterator);
            i &= spliterator.characteristics();
            j = LongMath.saturatedAdd(j, spliterator.estimateSize());
        }
        return (IntStream) StreamSupport.intStream(CollectSpliterators.flatMapToInt(builder.build().spliterator(), new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$-jlVyzka-mQsb6si2gGn3xPLMFM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$2((Spliterator.OfInt) obj);
            }
        }, i, j), z).onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$Streams$IT5_nSTEUattTKM7kDpCq5Sbdjg
            @Override // java.lang.Runnable
            public final void run() {
                Streams.closeAll(intStreamArr);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.Spliterator$OfLong, java.lang.Object] */
    public static LongStream concat(final LongStream... longStreamArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder(longStreamArr.length);
        int i = 336;
        long j = 0;
        boolean z = false;
        for (LongStream longStream : longStreamArr) {
            z |= longStream.isParallel();
            ?? spliterator = longStream.spliterator();
            builder.add((ImmutableList.Builder) spliterator);
            i &= spliterator.characteristics();
            j = LongMath.saturatedAdd(j, spliterator.estimateSize());
        }
        return (LongStream) StreamSupport.longStream(CollectSpliterators.flatMapToLong(builder.build().spliterator(), new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$DvKIxdNnMG4giYuziqkMH3cUob8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$4((Spliterator.OfLong) obj);
            }
        }, i, j), z).onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$Streams$Tt7Rd6Knfk-QWfNlQ7Qzdvc9Was
            @Override // java.lang.Runnable
            public final void run() {
                Streams.closeAll(longStreamArr);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.Spliterator$OfDouble, java.lang.Object] */
    public static DoubleStream concat(final DoubleStream... doubleStreamArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder(doubleStreamArr.length);
        int i = 336;
        long j = 0;
        boolean z = false;
        for (DoubleStream doubleStream : doubleStreamArr) {
            z |= doubleStream.isParallel();
            ?? spliterator = doubleStream.spliterator();
            builder.add((ImmutableList.Builder) spliterator);
            i &= spliterator.characteristics();
            j = LongMath.saturatedAdd(j, spliterator.estimateSize());
        }
        return (DoubleStream) StreamSupport.doubleStream(CollectSpliterators.flatMapToDouble(builder.build().spliterator(), new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$xwxdTCSjkSgIGj29-vnu64joKbg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Streams.lambda$concat$6((Spliterator.OfDouble) obj);
            }
        }, i, j), z).onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$Streams$xALJTn21aCJXxY56HpISsjyp16o
            @Override // java.lang.Runnable
            public final void run() {
                Streams.closeAll(doubleStreamArr);
            }
        });
    }

    public static <A, B, R> Stream<R> zip(Stream<A> stream, Stream<B> stream2, final BiFunction<? super A, ? super B, R> biFunction) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(stream2);
        Preconditions.checkNotNull(biFunction);
        boolean z = stream.isParallel() || stream2.isParallel();
        Spliterator<A> spliterator = stream.spliterator();
        Spliterator<B> spliterator2 = stream2.spliterator();
        int characteristics = spliterator.characteristics() & spliterator2.characteristics() & 80;
        final Iterator it = Spliterators.iterator(spliterator);
        final Iterator it2 = Spliterators.iterator(spliterator2);
        Stream stream3 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Math.min(spliterator.estimateSize(), spliterator2.estimateSize()), characteristics) { // from class: com.google.common.collect.Streams.1
            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super R> consumer) {
                if (!it.hasNext() || !it2.hasNext()) {
                    return false;
                }
                consumer.accept((Object) biFunction.apply(it.next(), it2.next()));
                return true;
            }
        }, z);
        Objects.requireNonNull(stream);
        Stream stream4 = (Stream) stream3.onClose(new $$Lambda$Zs9InZa8XuPuxDobaApOmS3136M(stream));
        Objects.requireNonNull(stream2);
        return (Stream) stream4.onClose(new $$Lambda$Zs9InZa8XuPuxDobaApOmS3136M(stream2));
    }

    public static <A, B> void forEachPair(Stream<A> stream, Stream<B> stream2, final BiConsumer<? super A, ? super B> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        if (stream.isParallel() || stream2.isParallel()) {
            zip(stream, stream2, new BiFunction() { // from class: com.google.common.collect.-$$Lambda$honSnvIdwT3TeQ412WPHBfMqYLk
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return new Streams.TemporaryPair(obj, obj2);
                }
            }).forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Streams$jnUHQqHNFYngGW3KB_xwBJP_1SY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    biConsumer.accept(r2.f1956a, ((Streams.TemporaryPair) obj).f1957b);
                }
            });
            return;
        }
        Iterator<A> it = stream.iterator();
        Iterator<B> it2 = stream2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            biConsumer.accept(it.next(), it2.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class TemporaryPair<A, B> {

        /* renamed from: a */
        final A f1956a;

        /* renamed from: b */
        final B f1957b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public TemporaryPair(A a, B b) {
            this.f1956a = a;
            this.f1957b = b;
        }
    }

    public static <T, R> Stream<R> mapWithIndex(Stream<T> stream, final FunctionWithIndex<? super T, ? extends R> functionWithIndex) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(functionWithIndex);
        boolean isParallel = stream.isParallel();
        Spliterator<T> spliterator = stream.spliterator();
        if (!spliterator.hasCharacteristics(16384)) {
            final Iterator it = Spliterators.iterator(spliterator);
            Stream stream2 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(spliterator.estimateSize(), spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.2
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    FunctionWithIndex functionWithIndex2 = functionWithIndex;
                    Object next = it.next();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) functionWithIndex2.apply(next, j));
                    return true;
                }
            }, isParallel);
            Objects.requireNonNull(stream);
            return (Stream) stream2.onClose(new $$Lambda$Zs9InZa8XuPuxDobaApOmS3136M(stream));
        }
        Stream stream3 = StreamSupport.stream(new C1Splitr(spliterator, 0L, functionWithIndex), isParallel);
        Objects.requireNonNull(stream);
        return (Stream) stream3.onClose(new $$Lambda$Zs9InZa8XuPuxDobaApOmS3136M(stream));
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* renamed from: com.google.common.collect.Streams$1Splitr, reason: invalid class name */
    /* loaded from: classes3.dex */
    class C1Splitr<R, T> extends MapWithIndexSpliterator<Spliterator<T>, R, C1Splitr> implements Consumer<T> {
        T holder;
        final /* synthetic */ FunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$FunctionWithIndex), (r0 I:com.google.common.collect.Streams$1Splitr) (LINE:444) com.google.common.collect.Streams.1Splitr.val$function com.google.common.collect.Streams$FunctionWithIndex, block:B:1:0x0000 */
        C1Splitr(Spliterator spliterator, Spliterator<T> spliterator2, long j) {
            super(spliterator, spliterator2);
            FunctionWithIndex functionWithIndex;
            this.val$function = functionWithIndex;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!this.fromSpliterator.tryAdvance(this)) {
                return false;
            }
            try {
                FunctionWithIndex functionWithIndex = this.val$function;
                T t = this.holder;
                long j = this.index;
                this.index = 1 + j;
                consumer.accept((Object) functionWithIndex.apply(t, j));
                return true;
            } finally {
                this.holder = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C1Splitr createSplit(Spliterator<T> spliterator, long j) {
            return new C1Splitr(spliterator, j, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfInt] */
    public static <R> Stream<R> mapWithIndex(final IntStream intStream, final IntFunctionWithIndex<R> intFunctionWithIndex) {
        Preconditions.checkNotNull(intStream);
        Preconditions.checkNotNull(intFunctionWithIndex);
        boolean isParallel = intStream.isParallel();
        ?? spliterator = intStream.spliterator();
        if (!spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfInt it = Spliterators.iterator((Spliterator.OfInt) spliterator);
            Stream stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(spliterator.estimateSize(), spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.3
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    IntFunctionWithIndex intFunctionWithIndex2 = intFunctionWithIndex;
                    int nextInt = it.nextInt();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) intFunctionWithIndex2.apply(nextInt, j));
                    return true;
                }
            }, isParallel);
            Objects.requireNonNull(intStream);
            return (Stream) stream.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$M_9LS_7GbvWBZmrlfKOp3wKwBM0
                @Override // java.lang.Runnable
                public final void run() {
                    intStream.close();
                }
            });
        }
        Stream stream2 = StreamSupport.stream(new C2Splitr(spliterator, 0L, intFunctionWithIndex), isParallel);
        Objects.requireNonNull(intStream);
        return (Stream) stream2.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$M_9LS_7GbvWBZmrlfKOp3wKwBM0
            @Override // java.lang.Runnable
            public final void run() {
                intStream.close();
            }
        });
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$2Splitr, reason: invalid class name */
    /* loaded from: classes3.dex */
    class C2Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfInt, R, C2Splitr> implements IntConsumer, Spliterator<R> {
        int holder;
        final /* synthetic */ IntFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$IntFunctionWithIndex), (r0 I:com.google.common.collect.Streams$2Splitr) (LINE:527) com.google.common.collect.Streams.2Splitr.val$function com.google.common.collect.Streams$IntFunctionWithIndex, block:B:1:0x0000 */
        C2Splitr(Spliterator.OfInt ofInt, Spliterator.OfInt ofInt2, long j) {
            super(ofInt, ofInt2);
            IntFunctionWithIndex intFunctionWithIndex;
            this.val$function = intFunctionWithIndex;
        }

        @Override // java.util.function.IntConsumer
        public void accept(int i) {
            this.holder = i;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfInt) this.fromSpliterator).tryAdvance((IntConsumer) this)) {
                return false;
            }
            IntFunctionWithIndex intFunctionWithIndex = this.val$function;
            int i = this.holder;
            long j = this.index;
            this.index = 1 + j;
            consumer.accept((Object) intFunctionWithIndex.apply(i, j));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C2Splitr createSplit(Spliterator.OfInt ofInt, long j) {
            return new C2Splitr(ofInt, j, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfLong] */
    public static <R> Stream<R> mapWithIndex(final LongStream longStream, final LongFunctionWithIndex<R> longFunctionWithIndex) {
        Preconditions.checkNotNull(longStream);
        Preconditions.checkNotNull(longFunctionWithIndex);
        boolean isParallel = longStream.isParallel();
        ?? spliterator = longStream.spliterator();
        if (!spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfLong it = Spliterators.iterator((Spliterator.OfLong) spliterator);
            Stream stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(spliterator.estimateSize(), spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.4
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    LongFunctionWithIndex longFunctionWithIndex2 = longFunctionWithIndex;
                    long nextLong = it.nextLong();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) longFunctionWithIndex2.apply(nextLong, j));
                    return true;
                }
            }, isParallel);
            Objects.requireNonNull(longStream);
            return (Stream) stream.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$y3UzC9Ucub3hbmrgNozGHDddSDo
                @Override // java.lang.Runnable
                public final void run() {
                    longStream.close();
                }
            });
        }
        Stream stream2 = StreamSupport.stream(new C3Splitr(spliterator, 0L, longFunctionWithIndex), isParallel);
        Objects.requireNonNull(longStream);
        return (Stream) stream2.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$y3UzC9Ucub3hbmrgNozGHDddSDo
            @Override // java.lang.Runnable
            public final void run() {
                longStream.close();
            }
        });
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$3Splitr, reason: invalid class name */
    /* loaded from: classes3.dex */
    class C3Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfLong, R, C3Splitr> implements LongConsumer, Spliterator<R> {
        long holder;
        final /* synthetic */ LongFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$LongFunctionWithIndex), (r0 I:com.google.common.collect.Streams$3Splitr) (LINE:606) com.google.common.collect.Streams.3Splitr.val$function com.google.common.collect.Streams$LongFunctionWithIndex, block:B:1:0x0000 */
        C3Splitr(Spliterator.OfLong ofLong, Spliterator.OfLong ofLong2, long j) {
            super(ofLong, ofLong2);
            LongFunctionWithIndex longFunctionWithIndex;
            this.val$function = longFunctionWithIndex;
        }

        @Override // java.util.function.LongConsumer
        public void accept(long j) {
            this.holder = j;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfLong) this.fromSpliterator).tryAdvance((LongConsumer) this)) {
                return false;
            }
            LongFunctionWithIndex longFunctionWithIndex = this.val$function;
            long j = this.holder;
            long j2 = this.index;
            this.index = 1 + j2;
            consumer.accept((Object) longFunctionWithIndex.apply(j, j2));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C3Splitr createSplit(Spliterator.OfLong ofLong, long j) {
            return new C3Splitr(ofLong, j, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfDouble] */
    public static <R> Stream<R> mapWithIndex(final DoubleStream doubleStream, final DoubleFunctionWithIndex<R> doubleFunctionWithIndex) {
        Preconditions.checkNotNull(doubleStream);
        Preconditions.checkNotNull(doubleFunctionWithIndex);
        boolean isParallel = doubleStream.isParallel();
        ?? spliterator = doubleStream.spliterator();
        if (!spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfDouble it = Spliterators.iterator((Spliterator.OfDouble) spliterator);
            Stream stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(spliterator.estimateSize(), spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.5
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (!it.hasNext()) {
                        return false;
                    }
                    DoubleFunctionWithIndex doubleFunctionWithIndex2 = doubleFunctionWithIndex;
                    double nextDouble = it.nextDouble();
                    long j = this.index;
                    this.index = 1 + j;
                    consumer.accept((Object) doubleFunctionWithIndex2.apply(nextDouble, j));
                    return true;
                }
            }, isParallel);
            Objects.requireNonNull(doubleStream);
            return (Stream) stream.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$GBpOF54sAws-PHwS5bXxRWrL0gU
                @Override // java.lang.Runnable
                public final void run() {
                    doubleStream.close();
                }
            });
        }
        Stream stream2 = StreamSupport.stream(new C4Splitr(spliterator, 0L, doubleFunctionWithIndex), isParallel);
        Objects.requireNonNull(doubleStream);
        return (Stream) stream2.onClose(new Runnable() { // from class: com.google.common.collect.-$$Lambda$GBpOF54sAws-PHwS5bXxRWrL0gU
            @Override // java.lang.Runnable
            public final void run() {
                doubleStream.close();
            }
        });
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$4Splitr, reason: invalid class name */
    /* loaded from: classes3.dex */
    class C4Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfDouble, R, C4Splitr> implements DoubleConsumer, Spliterator<R> {
        double holder;
        final /* synthetic */ DoubleFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0000: IPUT (r4 I:com.google.common.collect.Streams$DoubleFunctionWithIndex), (r0 I:com.google.common.collect.Streams$4Splitr) (LINE:686) com.google.common.collect.Streams.4Splitr.val$function com.google.common.collect.Streams$DoubleFunctionWithIndex, block:B:1:0x0000 */
        C4Splitr(Spliterator.OfDouble ofDouble, Spliterator.OfDouble ofDouble2, long j) {
            super(ofDouble, ofDouble2);
            DoubleFunctionWithIndex doubleFunctionWithIndex;
            this.val$function = doubleFunctionWithIndex;
        }

        @Override // java.util.function.DoubleConsumer
        public void accept(double d) {
            this.holder = d;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (!((Spliterator.OfDouble) this.fromSpliterator).tryAdvance((DoubleConsumer) this)) {
                return false;
            }
            DoubleFunctionWithIndex doubleFunctionWithIndex = this.val$function;
            double d = this.holder;
            long j = this.index;
            this.index = 1 + j;
            consumer.accept((Object) doubleFunctionWithIndex.apply(d, j));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C4Splitr createSplit(Spliterator.OfDouble ofDouble, long j) {
            return new C4Splitr(ofDouble, j, this.val$function);
        }
    }

    /* loaded from: classes3.dex */
    private static abstract class MapWithIndexSpliterator<F extends Spliterator<?>, R, S extends MapWithIndexSpliterator<F, R, S>> implements Spliterator<R> {
        final F fromSpliterator;
        long index;

        abstract S createSplit(F f, long j);

        MapWithIndexSpliterator(F f, long j) {
            this.fromSpliterator = f;
            this.index = j;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Spliterator
        public S trySplit() {
            Spliterator trySplit = this.fromSpliterator.trySplit();
            if (trySplit == null) {
                return null;
            }
            S s = (S) createSplit(trySplit, this.index);
            this.index += trySplit.getExactSizeIfKnown();
            return s;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.fromSpliterator.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.fromSpliterator.characteristics() & 16464;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Streams$1OptionalState, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class C1OptionalState {
        boolean set = false;
        T value = null;

        C1OptionalState() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void set(T t) {
            this.set = true;
            this.value = t;
        }

        T get() {
            Preconditions.checkState(this.set);
            return this.value;
        }
    }

    public static <T> java.util.Optional<T> findLast(Stream<T> stream) {
        final C1OptionalState c1OptionalState = new C1OptionalState();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addLast(stream.spliterator());
        while (!arrayDeque.isEmpty()) {
            Spliterator<T> spliterator = (Spliterator) arrayDeque.removeLast();
            if (spliterator.getExactSizeIfKnown() != 0) {
                if (spliterator.hasCharacteristics(16384)) {
                    while (true) {
                        Spliterator<T> trySplit = spliterator.trySplit();
                        if (trySplit == null || trySplit.getExactSizeIfKnown() == 0) {
                            break;
                        }
                        if (spliterator.getExactSizeIfKnown() == 0) {
                            spliterator = trySplit;
                            break;
                        }
                    }
                    spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$FdgNCSLr1Jyf_TTg4Zv1xfQXf18
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            Streams.C1OptionalState.this.set(obj);
                        }
                    });
                    return java.util.Optional.of(c1OptionalState.get());
                }
                Spliterator<T> trySplit2 = spliterator.trySplit();
                if (trySplit2 == null || trySplit2.getExactSizeIfKnown() == 0) {
                    spliterator.forEachRemaining(new Consumer() { // from class: com.google.common.collect.-$$Lambda$FdgNCSLr1Jyf_TTg4Zv1xfQXf18
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            Streams.C1OptionalState.this.set(obj);
                        }
                    });
                    if (c1OptionalState.set) {
                        return java.util.Optional.of(c1OptionalState.get());
                    }
                } else {
                    arrayDeque.addLast(trySplit2);
                    arrayDeque.addLast(spliterator);
                }
            }
        }
        return java.util.Optional.empty();
    }

    public static OptionalInt findLast(IntStream intStream) {
        return (OptionalInt) findLast(intStream.boxed()).map(new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$UsTZmVbi6P1L4aT7R0dkdew4-1s
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                OptionalInt of;
                of = OptionalInt.of(((Integer) obj).intValue());
                return of;
            }
        }).orElseGet(new Supplier() { // from class: com.google.common.collect.-$$Lambda$Streams$zKuhgXKT7Omlg45euCD5nojygCE
            @Override // java.util.function.Supplier
            public final Object get() {
                OptionalInt empty;
                empty = OptionalInt.empty();
                return empty;
            }
        });
    }

    public static OptionalLong findLast(LongStream longStream) {
        return (OptionalLong) findLast(longStream.boxed()).map(new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$qT1n-W7Yd3gPZ5sGn2cCC_JUAe8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                OptionalLong of;
                of = OptionalLong.of(((Long) obj).longValue());
                return of;
            }
        }).orElseGet(new Supplier() { // from class: com.google.common.collect.-$$Lambda$Streams$rxIe97KLHUBj5uhrCPmf8hQ1dS8
            @Override // java.util.function.Supplier
            public final Object get() {
                OptionalLong empty;
                empty = OptionalLong.empty();
                return empty;
            }
        });
    }

    public static OptionalDouble findLast(DoubleStream doubleStream) {
        return (OptionalDouble) findLast(doubleStream.boxed()).map(new Function() { // from class: com.google.common.collect.-$$Lambda$Streams$tiDFasrkhDmoXp8Fi9HtBwV3VtA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                OptionalDouble of;
                of = OptionalDouble.of(((Double) obj).doubleValue());
                return of;
            }
        }).orElseGet(new Supplier() { // from class: com.google.common.collect.-$$Lambda$Streams$aIlS99T97tJOL1AZyLDslPkr6I0
            @Override // java.util.function.Supplier
            public final Object get() {
                OptionalDouble empty;
                empty = OptionalDouble.empty();
                return empty;
            }
        });
    }

    private Streams() {
    }
}
