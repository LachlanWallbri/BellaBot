package com.annimon.stream;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.BinaryOperator;
import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;
import com.annimon.stream.function.UnaryOperator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class Collectors {
    private static final Supplier<long[]> LONG_2ELEMENTS_ARRAY_SUPPLIER = new Supplier<long[]>() { // from class: com.annimon.stream.Collectors.1
        @Override // com.annimon.stream.function.Supplier
        public long[] get() {
            return new long[]{0, 0};
        }
    };
    private static final Supplier<double[]> DOUBLE_2ELEMENTS_ARRAY_SUPPLIER = new Supplier<double[]>() { // from class: com.annimon.stream.Collectors.2
        @Override // com.annimon.stream.function.Supplier
        public double[] get() {
            return new double[]{0.0d, 0.0d};
        }
    };

    private Collectors() {
    }

    public static <T, R extends Collection<T>> Collector<T, ?, R> toCollection(Supplier<R> supplier) {
        return new CollectorsImpl(supplier, new BiConsumer<R, T>() { // from class: com.annimon.stream.Collectors.3
            /* JADX WARN: Incorrect types in method signature: (TR;TT;)V */
            @Override // com.annimon.stream.function.BiConsumer
            public void accept(Collection collection, Object obj) {
                collection.add(obj);
            }
        });
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorsImpl(new Supplier<List<T>>() { // from class: com.annimon.stream.Collectors.4
            @Override // com.annimon.stream.function.Supplier
            public List<T> get() {
                return new ArrayList();
            }
        }, new BiConsumer<List<T>, T>() { // from class: com.annimon.stream.Collectors.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
                accept((List<List<T>>) obj, (List<T>) obj2);
            }

            public void accept(List<T> list, T t) {
                list.add(t);
            }
        });
    }

    public static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
        return collectingAndThen(toList(), new UnaryOperator<List<T>>() { // from class: com.annimon.stream.Collectors.6
            @Override // com.annimon.stream.function.Function
            public List<T> apply(List<T> list) {
                Objects.requireNonNullElements(list);
                return Collections.unmodifiableList(list);
            }
        });
    }

    public static <T> Collector<T, ?, Set<T>> toSet() {
        return new CollectorsImpl(new Supplier<Set<T>>() { // from class: com.annimon.stream.Collectors.7
            @Override // com.annimon.stream.function.Supplier
            public Set<T> get() {
                return new HashSet();
            }
        }, new BiConsumer<Set<T>, T>() { // from class: com.annimon.stream.Collectors.8
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
                accept((Set<Set<T>>) obj, (Set<T>) obj2);
            }

            public void accept(Set<T> set, T t) {
                set.add(t);
            }
        });
    }

    public static <T> Collector<T, ?, Set<T>> toUnmodifiableSet() {
        return collectingAndThen(toSet(), new UnaryOperator<Set<T>>() { // from class: com.annimon.stream.Collectors.9
            @Override // com.annimon.stream.function.Function
            public Set<T> apply(Set<T> set) {
                Objects.requireNonNullElements(set);
                return Collections.unmodifiableSet(set);
            }
        });
    }

    public static <T, K> Collector<T, ?, Map<K, T>> toMap(Function<? super T, ? extends K> function) {
        return toMap(function, UnaryOperator.Util.identity());
    }

    public static <T, K, V> Collector<T, ?, Map<K, V>> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return toMap(function, function2, hashMapSupplier());
    }

    public static <T, K, V, M extends Map<K, V>> Collector<T, ?, M> toMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, Supplier<M> supplier) {
        return new CollectorsImpl(supplier, new BiConsumer<M, T>() { // from class: com.annimon.stream.Collectors.10
            /* JADX WARN: Incorrect types in method signature: (TM;TT;)V */
            @Override // com.annimon.stream.function.BiConsumer
            public void accept(Map map, Object obj) {
                Object apply = Function.this.apply(obj);
                Object requireNonNull = Objects.requireNonNull(function2.apply(obj));
                Object put = map.put(apply, requireNonNull);
                if (put == null) {
                    return;
                }
                map.put(apply, put);
                throw Collectors.duplicateKeyException(apply, put, requireNonNull);
            }
        });
    }

    public static <T, K, V> Collector<T, ?, Map<K, V>> toUnmodifiableMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return collectingAndThen(toMap(function, function2), toUnmodifiableMapConverter());
    }

    public static <T, K, V> Collector<T, ?, Map<K, V>> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, BinaryOperator<V> binaryOperator) {
        return toMap(function, function2, binaryOperator, hashMapSupplier());
    }

    public static <T, K, V, M extends Map<K, V>> Collector<T, ?, M> toMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, final BinaryOperator<V> binaryOperator, Supplier<M> supplier) {
        return new CollectorsImpl(supplier, new BiConsumer<M, T>() { // from class: com.annimon.stream.Collectors.11
            /* JADX WARN: Incorrect types in method signature: (TM;TT;)V */
            @Override // com.annimon.stream.function.BiConsumer
            public void accept(Map map, Object obj) {
                Collectors.mapMerge(map, Function.this.apply(obj), function2.apply(obj), binaryOperator);
            }
        });
    }

    public static <T, K, V> Collector<T, ?, Map<K, V>> toUnmodifiableMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, BinaryOperator<V> binaryOperator) {
        return collectingAndThen(toMap(function, function2, binaryOperator, hashMapSupplier()), toUnmodifiableMapConverter());
    }

    public static Collector<CharSequence, ?, String> joining() {
        return joining("");
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence) {
        return joining(charSequence, "", "");
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return joining(charSequence, charSequence2, charSequence3, charSequence2.toString() + charSequence3.toString());
    }

    public static Collector<CharSequence, ?, String> joining(final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final String str) {
        return new CollectorsImpl(new Supplier<StringBuilder>() { // from class: com.annimon.stream.Collectors.12
            @Override // com.annimon.stream.function.Supplier
            public StringBuilder get() {
                return new StringBuilder();
            }
        }, new BiConsumer<StringBuilder, CharSequence>() { // from class: com.annimon.stream.Collectors.13
            @Override // com.annimon.stream.function.BiConsumer
            public void accept(StringBuilder sb, CharSequence charSequence4) {
                if (sb.length() > 0) {
                    sb.append(charSequence);
                } else {
                    sb.append(charSequence2);
                }
                sb.append(charSequence4);
            }
        }, new Function<StringBuilder, String>() { // from class: com.annimon.stream.Collectors.14
            @Override // com.annimon.stream.function.Function
            public String apply(StringBuilder sb) {
                if (sb.length() == 0) {
                    return str;
                }
                sb.append(charSequence3);
                return sb.toString();
            }
        });
    }

    @Deprecated
    public static <T> Collector<T, ?, Double> averaging(final Function<? super T, Double> function) {
        return averagingDouble(new ToDoubleFunction<T>() { // from class: com.annimon.stream.Collectors.15
            @Override // com.annimon.stream.function.ToDoubleFunction
            public double applyAsDouble(T t) {
                return ((Double) Function.this.apply(t)).doubleValue();
            }
        });
    }

    public static <T> Collector<T, ?, Double> averagingInt(final ToIntFunction<? super T> toIntFunction) {
        return averagingHelper(new BiConsumer<long[], T>() { // from class: com.annimon.stream.Collectors.16
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(long[] jArr, Object obj) {
                accept2(jArr, (long[]) obj);
            }

            /* renamed from: accept, reason: avoid collision after fix types in other method */
            public void accept2(long[] jArr, T t) {
                jArr[0] = jArr[0] + 1;
                jArr[1] = jArr[1] + ToIntFunction.this.applyAsInt(t);
            }
        });
    }

    public static <T> Collector<T, ?, Double> averagingLong(final ToLongFunction<? super T> toLongFunction) {
        return averagingHelper(new BiConsumer<long[], T>() { // from class: com.annimon.stream.Collectors.17
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(long[] jArr, Object obj) {
                accept2(jArr, (long[]) obj);
            }

            /* renamed from: accept, reason: avoid collision after fix types in other method */
            public void accept2(long[] jArr, T t) {
                jArr[0] = jArr[0] + 1;
                jArr[1] = jArr[1] + ToLongFunction.this.applyAsLong(t);
            }
        });
    }

    private static <T> Collector<T, ?, Double> averagingHelper(BiConsumer<long[], T> biConsumer) {
        return new CollectorsImpl(LONG_2ELEMENTS_ARRAY_SUPPLIER, biConsumer, new Function<long[], Double>() { // from class: com.annimon.stream.Collectors.18
            @Override // com.annimon.stream.function.Function
            public Double apply(long[] jArr) {
                return jArr[0] == 0 ? Double.valueOf(0.0d) : Double.valueOf(jArr[1] / jArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Double> averagingDouble(final ToDoubleFunction<? super T> toDoubleFunction) {
        return new CollectorsImpl(DOUBLE_2ELEMENTS_ARRAY_SUPPLIER, new BiConsumer<double[], T>() { // from class: com.annimon.stream.Collectors.19
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(double[] dArr, Object obj) {
                accept2(dArr, (double[]) obj);
            }

            /* renamed from: accept, reason: avoid collision after fix types in other method */
            public void accept2(double[] dArr, T t) {
                dArr[0] = dArr[0] + 1.0d;
                dArr[1] = dArr[1] + ToDoubleFunction.this.applyAsDouble(t);
            }
        }, new Function<double[], Double>() { // from class: com.annimon.stream.Collectors.20
            @Override // com.annimon.stream.function.Function
            public Double apply(double[] dArr) {
                return dArr[0] == 0.0d ? Double.valueOf(0.0d) : Double.valueOf(dArr[1] / dArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Integer> summingInt(final ToIntFunction<? super T> toIntFunction) {
        return new CollectorsImpl(new Supplier<int[]>() { // from class: com.annimon.stream.Collectors.21
            @Override // com.annimon.stream.function.Supplier
            public int[] get() {
                return new int[]{0};
            }
        }, new BiConsumer<int[], T>() { // from class: com.annimon.stream.Collectors.22
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(int[] iArr, Object obj) {
                accept2(iArr, (int[]) obj);
            }

            /* renamed from: accept, reason: avoid collision after fix types in other method */
            public void accept2(int[] iArr, T t) {
                iArr[0] = iArr[0] + ToIntFunction.this.applyAsInt(t);
            }
        }, new Function<int[], Integer>() { // from class: com.annimon.stream.Collectors.23
            @Override // com.annimon.stream.function.Function
            public Integer apply(int[] iArr) {
                return Integer.valueOf(iArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Long> summingLong(final ToLongFunction<? super T> toLongFunction) {
        return new CollectorsImpl(LONG_2ELEMENTS_ARRAY_SUPPLIER, new BiConsumer<long[], T>() { // from class: com.annimon.stream.Collectors.24
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(long[] jArr, Object obj) {
                accept2(jArr, (long[]) obj);
            }

            /* renamed from: accept, reason: avoid collision after fix types in other method */
            public void accept2(long[] jArr, T t) {
                jArr[0] = jArr[0] + ToLongFunction.this.applyAsLong(t);
            }
        }, new Function<long[], Long>() { // from class: com.annimon.stream.Collectors.25
            @Override // com.annimon.stream.function.Function
            public Long apply(long[] jArr) {
                return Long.valueOf(jArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Double> summingDouble(final ToDoubleFunction<? super T> toDoubleFunction) {
        return new CollectorsImpl(DOUBLE_2ELEMENTS_ARRAY_SUPPLIER, new BiConsumer<double[], T>() { // from class: com.annimon.stream.Collectors.26
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(double[] dArr, Object obj) {
                accept2(dArr, (double[]) obj);
            }

            /* renamed from: accept, reason: avoid collision after fix types in other method */
            public void accept2(double[] dArr, T t) {
                dArr[0] = dArr[0] + ToDoubleFunction.this.applyAsDouble(t);
            }
        }, new Function<double[], Double>() { // from class: com.annimon.stream.Collectors.27
            @Override // com.annimon.stream.function.Function
            public Double apply(double[] dArr) {
                return Double.valueOf(dArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Long> counting() {
        return summingLong(new ToLongFunction<T>() { // from class: com.annimon.stream.Collectors.28
            @Override // com.annimon.stream.function.ToLongFunction
            public long applyAsLong(T t) {
                return 1L;
            }
        });
    }

    public static <T> Collector<T, ?, T> reducing(final T t, final BinaryOperator<T> binaryOperator) {
        return new CollectorsImpl(new Supplier<Tuple1<T>>() { // from class: com.annimon.stream.Collectors.29
            @Override // com.annimon.stream.function.Supplier
            public Tuple1<T> get() {
                return new Tuple1<>(t);
            }
        }, new BiConsumer<Tuple1<T>, T>() { // from class: com.annimon.stream.Collectors.30
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
                accept((Tuple1<Tuple1<T>>) obj, (Tuple1<T>) obj2);
            }

            public void accept(Tuple1<T> tuple1, T t2) {
                tuple1.f1201a = BinaryOperator.this.apply(tuple1.f1201a, t2);
            }
        }, new Function<Tuple1<T>, T>() { // from class: com.annimon.stream.Collectors.31
            @Override // com.annimon.stream.function.Function
            public T apply(Tuple1<T> tuple1) {
                return tuple1.f1201a;
            }
        });
    }

    public static <T, R> Collector<T, ?, R> reducing(final R r, final Function<? super T, ? extends R> function, final BinaryOperator<R> binaryOperator) {
        return new CollectorsImpl(new Supplier<Tuple1<R>>() { // from class: com.annimon.stream.Collectors.32
            @Override // com.annimon.stream.function.Supplier
            public Tuple1<R> get() {
                return new Tuple1<>(r);
            }
        }, new BiConsumer<Tuple1<R>, T>() { // from class: com.annimon.stream.Collectors.33
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
                accept((Tuple1) obj, (Tuple1<R>) obj2);
            }

            /* JADX WARN: Type inference failed for: r5v2, types: [A, java.lang.Object] */
            public void accept(Tuple1<R> tuple1, T t) {
                tuple1.f1201a = BinaryOperator.this.apply(tuple1.f1201a, function.apply(t));
            }
        }, new Function<Tuple1<R>, R>() { // from class: com.annimon.stream.Collectors.34
            @Override // com.annimon.stream.function.Function
            public R apply(Tuple1<R> tuple1) {
                return tuple1.f1201a;
            }
        });
    }

    public static <T, A, R> Collector<T, ?, R> filtering(final Predicate<? super T> predicate, Collector<? super T, A, R> collector) {
        final BiConsumer<A, ? super T> accumulator = collector.accumulator();
        return new CollectorsImpl(collector.supplier(), new BiConsumer<A, T>() { // from class: com.annimon.stream.Collectors.35
            @Override // com.annimon.stream.function.BiConsumer
            public void accept(A a, T t) {
                if (Predicate.this.test(t)) {
                    accumulator.accept(a, t);
                }
            }
        }, collector.finisher());
    }

    public static <T, U, A, R> Collector<T, ?, R> mapping(final Function<? super T, ? extends U> function, Collector<? super U, A, R> collector) {
        final BiConsumer<A, ? super U> accumulator = collector.accumulator();
        return new CollectorsImpl(collector.supplier(), new BiConsumer<A, T>() { // from class: com.annimon.stream.Collectors.36
            @Override // com.annimon.stream.function.BiConsumer
            public void accept(A a, T t) {
                BiConsumer.this.accept(a, function.apply(t));
            }
        }, collector.finisher());
    }

    public static <T, U, A, R> Collector<T, ?, R> flatMapping(final Function<? super T, ? extends Stream<? extends U>> function, Collector<? super U, A, R> collector) {
        final BiConsumer<A, ? super U> accumulator = collector.accumulator();
        return new CollectorsImpl(collector.supplier(), new BiConsumer<A, T>() { // from class: com.annimon.stream.Collectors.37
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public void accept(final A a, T t) {
                Stream stream = (Stream) Function.this.apply(t);
                if (stream == 0) {
                    return;
                }
                stream.forEach(new Consumer<U>() { // from class: com.annimon.stream.Collectors.37.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.annimon.stream.function.Consumer
                    public void accept(U u) {
                        accumulator.accept(a, u);
                    }
                });
            }
        }, collector.finisher());
    }

    public static <T, A, IR, OR> Collector<T, A, OR> collectingAndThen(Collector<T, A, IR> collector, Function<IR, OR> function) {
        Function<A, IR> finisher = collector.finisher();
        if (finisher == null) {
            finisher = castIdentity();
        }
        return new CollectorsImpl(collector.supplier(), collector.accumulator(), Function.Util.andThen(finisher, function));
    }

    public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> function) {
        return groupingBy(function, toList());
    }

    public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> function, Collector<? super T, A, D> collector) {
        return groupingBy(function, hashMapSupplier(), collector);
    }

    public static <T, K, D, A, M extends Map<K, D>> Collector<T, ?, M> groupingBy(final Function<? super T, ? extends K> function, Supplier<M> supplier, final Collector<? super T, A, D> collector) {
        final Function<A, D> finisher = collector.finisher();
        return new CollectorsImpl(supplier, new BiConsumer<Map<K, A>, T>() { // from class: com.annimon.stream.Collectors.39
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
                accept((Map) obj, (Map<K, A>) obj2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public void accept(Map<K, A> map, T t) {
                Object requireNonNull = Objects.requireNonNull(Function.this.apply(t), "element cannot be mapped to a null key");
                Object obj = map.get(requireNonNull);
                if (obj == null) {
                    obj = collector.supplier().get();
                    map.put(requireNonNull, obj);
                }
                collector.accumulator().accept(obj, t);
            }
        }, finisher != null ? new Function<Map<K, A>, M>() { // from class: com.annimon.stream.Collectors.38
            /* JADX WARN: Incorrect return type in method signature: (Ljava/util/Map<TK;TA;>;)TM; */
            @Override // com.annimon.stream.function.Function
            public Map apply(Map map) {
                for (Map.Entry entry : map.entrySet()) {
                    entry.setValue(Function.this.apply(entry.getValue()));
                }
                return map;
            }
        } : null);
    }

    public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate) {
        return partitioningBy(predicate, toList());
    }

    public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy(final Predicate<? super T> predicate, final Collector<? super T, A, D> collector) {
        final BiConsumer<A, ? super T> accumulator = collector.accumulator();
        return new CollectorsImpl(new Supplier<Tuple2<A>>() { // from class: com.annimon.stream.Collectors.40
            @Override // com.annimon.stream.function.Supplier
            public Tuple2<A> get() {
                return new Tuple2<>(Collector.this.supplier().get(), Collector.this.supplier().get());
            }
        }, new BiConsumer<Tuple2<A>, T>() { // from class: com.annimon.stream.Collectors.41
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
                accept((Tuple2) obj, (Tuple2<A>) obj2);
            }

            public void accept(Tuple2<A> tuple2, T t) {
                BiConsumer.this.accept(predicate.test(t) ? tuple2.f1202a : tuple2.f1203b, t);
            }
        }, new Function<Tuple2<A>, Map<Boolean, D>>() { // from class: com.annimon.stream.Collectors.42
            @Override // com.annimon.stream.function.Function
            public Map<Boolean, D> apply(Tuple2<A> tuple2) {
                Function finisher = Collector.this.finisher();
                if (finisher == null) {
                    finisher = Collectors.castIdentity();
                }
                HashMap hashMap = new HashMap(2);
                hashMap.put(Boolean.TRUE, finisher.apply(tuple2.f1202a));
                hashMap.put(Boolean.FALSE, finisher.apply(tuple2.f1203b));
                return hashMap;
            }
        });
    }

    private static <K, V> Supplier<Map<K, V>> hashMapSupplier() {
        return new Supplier<Map<K, V>>() { // from class: com.annimon.stream.Collectors.43
            @Override // com.annimon.stream.function.Supplier
            public Map<K, V> get() {
                return new HashMap();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IllegalStateException duplicateKeyException(Object obj, Object obj2, Object obj3) {
        return new IllegalStateException(String.format("Duplicate key %s (attempted merging values %s and %s)", obj, obj2, obj3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> void mapMerge(Map<K, V> map, K k, V v, BinaryOperator<V> binaryOperator) {
        V v2 = map.get(k);
        if (v2 != null) {
            v = (V) binaryOperator.apply(v2, v);
        }
        if (v == null) {
            map.remove(k);
        } else {
            map.put(k, v);
        }
    }

    private static <K, V> UnaryOperator<Map<K, V>> toUnmodifiableMapConverter() {
        return new UnaryOperator<Map<K, V>>() { // from class: com.annimon.stream.Collectors.44
            @Override // com.annimon.stream.function.Function
            public Map<K, V> apply(Map<K, V> map) {
                Objects.requireNonNullElements(map.keySet());
                Objects.requireNonNullElements(map.values());
                return Collections.unmodifiableMap(map);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <A, R> Function<A, R> castIdentity() {
        return new Function<A, R>() { // from class: com.annimon.stream.Collectors.45
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.annimon.stream.function.Function
            public R apply(A a) {
                return a;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class Tuple1<A> {

        /* renamed from: a */
        A f1201a;

        Tuple1(A a) {
            this.f1201a = a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class Tuple2<A> {

        /* renamed from: a */
        final A f1202a;

        /* renamed from: b */
        final A f1203b;

        Tuple2(A a, A a2) {
            this.f1202a = a;
            this.f1203b = a2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class CollectorsImpl<T, A, R> implements Collector<T, A, R> {
        private final BiConsumer<A, T> accumulator;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        public CollectorsImpl(Supplier<A> supplier, BiConsumer<A, T> biConsumer) {
            this(supplier, biConsumer, null);
        }

        public CollectorsImpl(Supplier<A> supplier, BiConsumer<A, T> biConsumer, Function<A, R> function) {
            this.supplier = supplier;
            this.accumulator = biConsumer;
            this.finisher = function;
        }

        @Override // com.annimon.stream.Collector
        public Supplier<A> supplier() {
            return this.supplier;
        }

        @Override // com.annimon.stream.Collector
        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        @Override // com.annimon.stream.Collector
        public Function<A, R> finisher() {
            return this.finisher;
        }
    }
}
