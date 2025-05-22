package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.MultimapBuilder;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* loaded from: classes3.dex */
final class CollectCollectors {
    private static final Collector<Object, ?, ImmutableList<Object>> TO_IMMUTABLE_LIST = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$Dfyd-zOF3bxBxHq3iqs49ZgMLUs
        @Override // java.util.function.Supplier
        public final Object get() {
            return ImmutableList.builder();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$Qvyai-L_hYayIQFE2St24To5wZE
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((ImmutableList.Builder) obj).add((ImmutableList.Builder) obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$Qx83BY6lz6Jpv1dd6polKavxmK8
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((ImmutableList.Builder) obj).combine((ImmutableList.Builder) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$B6qMqDjsj2i6-zaZiqfU9Ziu4KU
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ImmutableList.Builder) obj).build();
        }
    }, new Collector.Characteristics[0]);
    private static final Collector<Object, ?, ImmutableSet<Object>> TO_IMMUTABLE_SET = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$D2fWAlgZqZ-KOlZH1XayB5NNFYg
        @Override // java.util.function.Supplier
        public final Object get() {
            return ImmutableSet.builder();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$IdVJip9QnNnOjsjbknfdGci9RiE
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((ImmutableSet.Builder) obj).add((ImmutableSet.Builder) obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$wUARe-slCQiBwCn6kktgOARAFWY
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((ImmutableSet.Builder) obj).combine((ImmutableSet.Builder) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$T_uso8JlwUHIict0GBhQKP-JhLA
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ImmutableSet.Builder) obj).build();
        }
    }, new Collector.Characteristics[0]);
    private static final Collector<Range<Comparable<?>>, ?, ImmutableRangeSet<Comparable<?>>> TO_IMMUTABLE_RANGE_SET = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$qYrmkTOaUrLYeL7EApXANHaSYdI
        @Override // java.util.function.Supplier
        public final Object get() {
            return ImmutableRangeSet.builder();
        }
    }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$lowwVYyQOaWraSEhFKduT64LPV0
        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((ImmutableRangeSet.Builder) obj).add((Range) obj2);
        }
    }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$7Mxhy81NAiwcDk9tE05wrS15B5g
        @Override // java.util.function.BiFunction
        public final Object apply(Object obj, Object obj2) {
            return ((ImmutableRangeSet.Builder) obj).combine((ImmutableRangeSet.Builder) obj2);
        }
    }, new Function() { // from class: com.google.common.collect.-$$Lambda$3snfJOhsHXrS4Qvlzt9iufmIkY8
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ImmutableRangeSet.Builder) obj).build();
        }
    }, new Collector.Characteristics[0]);

    /* renamed from: lambda$Xo2ok-hFb7m6fzlhfOLhNRk6fP0, reason: not valid java name */
    public static /* synthetic */ LinkedHashMap m4276lambda$Xo2okhFb7m6fzlhfOLhNRk6fP0() {
        return new LinkedHashMap();
    }

    CollectCollectors() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Collector<E, ?, ImmutableList<E>> toImmutableList() {
        return (Collector<E, ?, ImmutableList<E>>) TO_IMMUTABLE_LIST;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return (Collector<E, ?, ImmutableSet<E>>) TO_IMMUTABLE_SET;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(final Comparator<? super E> comparator) {
        Preconditions.checkNotNull(comparator);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$OABLeSC_ooovsWY6HMAKkdGgYmQ
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.lambda$toImmutableSortedSet$0(comparator);
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$FLuZpkQmNg7JOBdGkuDvpE1c7wY
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableSortedSet.Builder) obj).add((ImmutableSortedSet.Builder) obj2);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$Gb0cLX-snGYbZK6C3LsaZrtl7k8
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableSortedSet.Builder) obj).combine((ImmutableSet.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$HYy5QkqepjzFGDSicdDzvjxFZSk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableSortedSet.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ImmutableSortedSet.Builder lambda$toImmutableSortedSet$0(Comparator comparator) {
        return new ImmutableSortedSet.Builder(comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E extends Enum<E>> Collector<E, ?, ImmutableSet<E>> toImmutableEnumSet() {
        return (Collector<E, ?, ImmutableSet<E>>) EnumSetAccumulator.TO_IMMUTABLE_ENUM_SET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class EnumSetAccumulator<E extends Enum<E>> {
        static final Collector<Enum<?>, ?, ImmutableSet<? extends Enum<?>>> TO_IMMUTABLE_ENUM_SET = Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$EnumSetAccumulator$2BPJ2pS4eVmxkoeFGXoQ0lwgimg
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.EnumSetAccumulator.lambda$2BPJ2pS4eVmxkoeFGXoQ0lwgimg();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$vksCOWSsmWDxz4voabG1ZhjipEM
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((CollectCollectors.EnumSetAccumulator) obj).add((Enum) obj2);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$veR0qgQPIng3k1CYnZsUutDeEYI
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((CollectCollectors.EnumSetAccumulator) obj).combine((CollectCollectors.EnumSetAccumulator) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$x4Awx2ohYVLS_H7RnIiW_wjmziA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CollectCollectors.EnumSetAccumulator) obj).toImmutableSet();
            }
        }, Collector.Characteristics.UNORDERED);
        private EnumSet<E> set;

        public static /* synthetic */ EnumSetAccumulator lambda$2BPJ2pS4eVmxkoeFGXoQ0lwgimg() {
            return new EnumSetAccumulator();
        }

        private EnumSetAccumulator() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void add(E e) {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                this.set = EnumSet.of((Enum) e);
            } else {
                enumSet.add(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public EnumSetAccumulator<E> combine(EnumSetAccumulator<E> enumSetAccumulator) {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                return enumSetAccumulator;
            }
            EnumSet<E> enumSet2 = enumSetAccumulator.set;
            if (enumSet2 == null) {
                return this;
            }
            enumSet.addAll(enumSet2);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ImmutableSet<E> toImmutableSet() {
            EnumSet<E> enumSet = this.set;
            return enumSet == null ? ImmutableSet.m676of() : ImmutableEnumSet.asImmutable(enumSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E extends Comparable<? super E>> Collector<Range<E>, ?, ImmutableRangeSet<E>> toImmutableRangeSet() {
        return (Collector<Range<E>, ?, ImmutableRangeSet<E>>) TO_IMMUTABLE_RANGE_SET;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, E> Collector<T, ?, ImmutableMultiset<E>> toImmutableMultiset(final Function<? super T, ? extends E> function, final ToIntFunction<? super T> toIntFunction) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(toIntFunction);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$D3oviQ9ZA_OVdxA685lwkI5pkC8
            @Override // java.util.function.Supplier
            public final Object get() {
                return LinkedHashMultiset.create();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$lQzTbClsATSYlArrDocvgBycOqc
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Multiset) obj).add(Preconditions.checkNotNull(function.apply(obj2)), toIntFunction.applyAsInt(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$qETJOkouxDIZ7c9i3CuPrKJ3ZoY
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return CollectCollectors.lambda$toImmutableMultiset$2((Multiset) obj, (Multiset) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$ada32lVQUMhzimnNZx483g-5u-c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ImmutableMultiset copyFromEntries;
                copyFromEntries = ImmutableMultiset.copyFromEntries(((Multiset) obj).entrySet());
                return copyFromEntries;
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Multiset lambda$toImmutableMultiset$2(Multiset multiset, Multiset multiset2) {
        multiset.addAll(multiset2);
        return multiset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, E, M extends Multiset<E>> Collector<T, ?, M> toMultiset(final Function<? super T, E> function, final ToIntFunction<? super T> toIntFunction, Supplier<M> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(toIntFunction);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$EKHsweDM1uwXKgltbxyXPfrkUOE
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Multiset) obj).add(function.apply(obj2), toIntFunction.applyAsInt(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$YFQLBpgTn7P8n9MbnJ7cl3EWXAY
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return CollectCollectors.lambda$toMultiset$5((Multiset) obj, (Multiset) obj2);
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Multiset lambda$toMultiset$5(Multiset multiset, Multiset multiset2) {
        multiset.addAll(multiset2);
        return multiset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$2DoWASnF6dZ7iLd1Bp9mMxtgx34
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ImmutableMap.Builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$dKn-Gpdlnak6Jpqv9Z695CszEs0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableMap.Builder) obj).put(function.apply(obj2), function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$iMl639MbEQfK2Uwz4dSjiDOdD0Y
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableMap.Builder) obj).combine((ImmutableMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$-JNR12gyt_ZEeeKFrcwlQXfJuBo
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableMap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(binaryOperator);
        return Collectors.collectingAndThen(Collectors.toMap(function, function2, binaryOperator, new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$Xo2ok-hFb7m6fzlhfOLhNRk6fP0
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.m4276lambda$Xo2okhFb7m6fzlhfOLhNRk6fP0();
            }
        }), new Function() { // from class: com.google.common.collect.-$$Lambda$zrsSClWYFqpBGyueTshJD-tHBto
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ImmutableMap.copyOf((Map) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(final Comparator<? super K> comparator, final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$66FlJFVoYLdLPSfowl2ocns1Cxw
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.lambda$toImmutableSortedMap$7(comparator);
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$kX33pmGNARt67pAxTSttT42-2N4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableSortedMap.Builder) obj).put((ImmutableSortedMap.Builder) function.apply(obj2), (ImmutableSortedMap.Builder) function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$xRTACJ_W0N2wW_qcPf4oUEy47wM
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableSortedMap.Builder) obj).combine((ImmutableMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$siK1rvzVZhWHDX4C7n3RSZYbQfs
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableSortedMap.Builder) obj).build();
            }
        }, Collector.Characteristics.UNORDERED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ImmutableSortedMap.Builder lambda$toImmutableSortedMap$7(Comparator comparator) {
        return new ImmutableSortedMap.Builder(comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(final Comparator<? super K> comparator, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(binaryOperator);
        return Collectors.collectingAndThen(Collectors.toMap(function, function2, binaryOperator, new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$VhQgGBJKlbKy6UCFeI-sN3GH7ks
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.lambda$toImmutableSortedMap$9(comparator);
            }
        }), new Function() { // from class: com.google.common.collect.-$$Lambda$WRrToC596FCyHKpXFGR2rH8N7lQ
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ImmutableSortedMap.copyOfSorted((TreeMap) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TreeMap lambda$toImmutableSortedMap$9(Comparator comparator) {
        return new TreeMap(comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableBiMap<K, V>> toImmutableBiMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$_m_t76O2vKrvy2DwB7sQvYoUFCw
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ImmutableBiMap.Builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$vWlYl9aDEN5daC0CSLTB-EyAYcE
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableBiMap.Builder) obj).put((ImmutableBiMap.Builder) function.apply(obj2), (ImmutableBiMap.Builder) function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$QPuM9iKoW1PCf2mQzuKDfsTjICw
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableBiMap.Builder) obj).combine((ImmutableMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$XhUs_LRH-e4OdfH7q5ERshr3mO8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableBiMap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$iNeJM3qCNeJzbXr6wctOxGKPP-k
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.lambda$toImmutableEnumMap$12();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$u5wh_bbKv8S-PdqAbN7c-Ze-124
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((CollectCollectors.EnumMapAccumulator) obj).put((Enum) Preconditions.checkNotNull((Enum) function.apply(obj2), "Null key for input %s", obj2), Preconditions.checkNotNull(function2.apply(obj2), "Null value for input %s", obj2));
            }
        }, $$Lambda$eV01jdA0Vgsswcl1z5ZzPmMV4.INSTANCE, $$Lambda$VTjmVxYvQu0bNGkSQ_bnos31reY.INSTANCE, Collector.Characteristics.UNORDERED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EnumMapAccumulator lambda$toImmutableEnumMap$12() {
        return new EnumMapAccumulator(new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$tq2HhpV-1uASpMHedsRbr8CDM2o
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return CollectCollectors.lambda$toImmutableEnumMap$11(obj, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$toImmutableEnumMap$11(Object obj, Object obj2) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27 + String.valueOf(valueOf2).length());
        sb.append("Multiple values for key: ");
        sb.append(valueOf);
        sb.append(", ");
        sb.append(valueOf2);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, final BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(binaryOperator);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$P_IQ0mqlL9obEFZFHAVRl7bzt8o
            @Override // java.util.function.Supplier
            public final Object get() {
                return CollectCollectors.lambda$toImmutableEnumMap$14(binaryOperator);
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$J71U-4i8GdZf3ZtBQ3pYcLP_0vs
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((CollectCollectors.EnumMapAccumulator) obj).put((Enum) Preconditions.checkNotNull((Enum) function.apply(obj2), "Null key for input %s", obj2), Preconditions.checkNotNull(function2.apply(obj2), "Null value for input %s", obj2));
            }
        }, $$Lambda$eV01jdA0Vgsswcl1z5ZzPmMV4.INSTANCE, $$Lambda$VTjmVxYvQu0bNGkSQ_bnos31reY.INSTANCE, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EnumMapAccumulator lambda$toImmutableEnumMap$14(BinaryOperator binaryOperator) {
        return new EnumMapAccumulator(binaryOperator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class EnumMapAccumulator<K extends Enum<K>, V> {
        private EnumMap<K, V> map = null;
        private final BinaryOperator<V> mergeFunction;

        EnumMapAccumulator(BinaryOperator<V> binaryOperator) {
            this.mergeFunction = binaryOperator;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void put(K k, V v) {
            if (this.map == null) {
                this.map = new EnumMap<>(k.getDeclaringClass());
            }
            this.map.merge(k, v, this.mergeFunction);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public EnumMapAccumulator<K, V> combine(EnumMapAccumulator<K, V> enumMapAccumulator) {
            if (this.map == null) {
                return enumMapAccumulator;
            }
            EnumMap<K, V> enumMap = enumMapAccumulator.map;
            if (enumMap == null) {
                return this;
            }
            enumMap.forEach(new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$thu_Llf4EJu_TspJAxSDRo6JQrM
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CollectCollectors.EnumMapAccumulator.this.put((Enum) obj, obj2);
                }
            });
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ImmutableMap<K, V> toImmutableMap() {
            EnumMap<K, V> enumMap = this.map;
            return enumMap == null ? ImmutableMap.m653of() : ImmutableEnumMap.asImmutable(enumMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K extends Comparable<? super K>, V> Collector<T, ?, ImmutableRangeMap<K, V>> toImmutableRangeMap(final Function<? super T, Range<K>> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$gysTBkYkLoQ6CU7u_E1e7mz2F-g
            @Override // java.util.function.Supplier
            public final Object get() {
                return ImmutableRangeMap.builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$tY0iEbMi2ePEfDtWIXKuEc2f444
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableRangeMap.Builder) obj).put((Range) function.apply(obj2), function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$-zFfqyVq5T798wGUZivKwgD4lls
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableRangeMap.Builder) obj).combine((ImmutableRangeMap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$k2Nywlv8-zwjiSiBaJUxSur-cGA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableRangeMap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> toImmutableListMultimap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function, "keyFunction");
        Preconditions.checkNotNull(function2, "valueFunction");
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$ki6G207kprHsN7dV-JVjhPKT0bs
            @Override // java.util.function.Supplier
            public final Object get() {
                return ImmutableListMultimap.builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$1qpCpTzzvtnWPT7X4CbB6x4Ma9c
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableListMultimap.Builder) obj).put((ImmutableListMultimap.Builder) function.apply(obj2), (ImmutableListMultimap.Builder) function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$kX-gwA_sZzDnEmZmoJP3cLI_W5k
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableListMultimap.Builder) obj).combine((ImmutableMultimap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$97U0YQNPGq3BJMaleKjDH1EOwEA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableListMultimap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> flatteningToImmutableListMultimap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends Stream<? extends V>> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Function function3 = new Function() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$uI183oNL_Em88BiPu10OiSK2LXs
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object checkNotNull;
                checkNotNull = Preconditions.checkNotNull(function.apply(obj));
                return checkNotNull;
            }
        };
        Function function4 = new Function() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$PK1qhLUSgrnoFN8S1u1N3dJXcz0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream peek;
                peek = ((Stream) function2.apply(obj)).peek($$Lambda$mMGTWQ3xwuIaCkgFwTr_lFVvQ4M.INSTANCE);
                return peek;
            }
        };
        final MultimapBuilder.ListMultimapBuilder<Object, Object> arrayListValues = MultimapBuilder.linkedHashKeys().arrayListValues();
        Objects.requireNonNull(arrayListValues);
        return Collectors.collectingAndThen(flatteningToMultimap(function3, function4, new Supplier() { // from class: com.google.common.collect.-$$Lambda$yEFp6dlddIAxuEuCjcM4vSwhwAE
            @Override // java.util.function.Supplier
            public final Object get() {
                return MultimapBuilder.ListMultimapBuilder.this.build();
            }
        }), new Function() { // from class: com.google.common.collect.-$$Lambda$Xxw32g7ROf3L8FxiPJeloRx_PDA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ImmutableListMultimap.copyOf((Multimap) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> toImmutableSetMultimap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function, "keyFunction");
        Preconditions.checkNotNull(function2, "valueFunction");
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$JMuuO5tNa9NyxKBQEkc8YlC4xz4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ImmutableSetMultimap.builder();
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$dQsfh36x-Wr5FRgu5jC5c3MJ4Zw
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ImmutableSetMultimap.Builder) obj).put((ImmutableSetMultimap.Builder) function.apply(obj2), (ImmutableSetMultimap.Builder) function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$SIBU91s2V_lsg9WNIhV53gu2h_w
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((ImmutableSetMultimap.Builder) obj).combine((ImmutableMultimap.Builder) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$EgphYFJShmxml3u__-T3BV50hz8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ImmutableSetMultimap.Builder) obj).build();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> flatteningToImmutableSetMultimap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends Stream<? extends V>> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Function function3 = new Function() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$qkkStvQH4mtA-wlaR_vdv3weWSI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object checkNotNull;
                checkNotNull = Preconditions.checkNotNull(function.apply(obj));
                return checkNotNull;
            }
        };
        Function function4 = new Function() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$l9h5WsnRa23P9t9fMyLC5grkh1U
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream peek;
                peek = ((Stream) function2.apply(obj)).peek($$Lambda$mMGTWQ3xwuIaCkgFwTr_lFVvQ4M.INSTANCE);
                return peek;
            }
        };
        final MultimapBuilder.SetMultimapBuilder<Object, Object> linkedHashSetValues = MultimapBuilder.linkedHashKeys().linkedHashSetValues();
        Objects.requireNonNull(linkedHashSetValues);
        return Collectors.collectingAndThen(flatteningToMultimap(function3, function4, new Supplier() { // from class: com.google.common.collect.-$$Lambda$nsORw7IQZpknnH5_OFSB_yNu0GE
            @Override // java.util.function.Supplier
            public final Object get() {
                return MultimapBuilder.SetMultimapBuilder.this.build();
            }
        }), new Function() { // from class: com.google.common.collect.-$$Lambda$zSHdU_CyjfI1-rocqBvafH4ppd0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ImmutableSetMultimap.copyOf((Multimap) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> toMultimap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, Supplier<M> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$Uq17xbgkouxoqvyMUZBha14qX30
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Multimap) obj).put(function.apply(obj2), function2.apply(obj2));
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$y2800GNpcq7jNRmkxhCaZLjNzCo
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return CollectCollectors.lambda$toMultimap$24((Multimap) obj, (Multimap) obj2);
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Multimap lambda$toMultimap$24(Multimap multimap, Multimap multimap2) {
        multimap.putAll(multimap2);
        return multimap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> flatteningToMultimap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends Stream<? extends V>> function2, Supplier<M> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$yaipZoWdW3lVWQ8Onhv30vXrz8o
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CollectCollectors.lambda$flatteningToMultimap$25(function, function2, (Multimap) obj, obj2);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$CollectCollectors$ssavTlsVbz9Z331TIs6Hgrpo68g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return CollectCollectors.lambda$flatteningToMultimap$26((Multimap) obj, (Multimap) obj2);
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$flatteningToMultimap$25(Function function, Function function2, Multimap multimap, Object obj) {
        final Collection collection = multimap.get(function.apply(obj));
        Stream stream = (Stream) function2.apply(obj);
        Objects.requireNonNull(collection);
        stream.forEachOrdered(new Consumer() { // from class: com.google.common.collect.-$$Lambda$CMyQKLwBy0EAPqyDBUlb1AohkGk
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                collection.add(obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Multimap lambda$flatteningToMultimap$26(Multimap multimap, Multimap multimap2) {
        multimap.putAll(multimap2);
        return multimap;
    }
}
