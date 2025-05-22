package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Comparators {
    private Comparators() {
    }

    public static <T, S extends T> Comparator<Iterable<S>> lexicographical(Comparator<T> comparator) {
        return new LexicographicalOrdering((Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <T> boolean isInOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T> boolean isInStrictOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T> Collector<T, ?, List<T>> least(final int i, final Comparator<? super T> comparator) {
        CollectPreconditions.checkNonnegative(i, "k");
        Preconditions.checkNotNull(comparator);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.-$$Lambda$Comparators$_DWKGNH-cVQUcyfzpO4yn-mD4as
            @Override // java.util.function.Supplier
            public final Object get() {
                TopKSelector least;
                least = TopKSelector.least(i, comparator);
                return least;
            }
        }, new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$gdvJ8zzvQat-Rh6knqtwfSxKqxQ
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((TopKSelector) obj).offer(obj2);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.-$$Lambda$Iilf_x3Yim75FBgBCib4qGxg29c
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((TopKSelector) obj).combine((TopKSelector) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.-$$Lambda$VPCwCVNyDcTS8_-paG2FrPedynw
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((TopKSelector) obj).topK();
            }
        }, Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, List<T>> greatest(int i, Comparator<? super T> comparator) {
        return least(i, comparator.reversed());
    }

    public static <T> Comparator<Optional<T>> emptiesFirst(Comparator<? super T> comparator) {
        Preconditions.checkNotNull(comparator);
        return Comparator.comparing(new Function() { // from class: com.google.common.collect.-$$Lambda$Comparators$UiCe-AsF2a9gO9y8sRhfhsM498w
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object orElse;
                orElse = ((Optional) obj).orElse(null);
                return orElse;
            }
        }, Comparator.nullsFirst(comparator));
    }

    public static <T> Comparator<Optional<T>> emptiesLast(Comparator<? super T> comparator) {
        Preconditions.checkNotNull(comparator);
        return Comparator.comparing(new Function() { // from class: com.google.common.collect.-$$Lambda$Comparators$5-rbgZYIdOv59hWI8fiqZx2chh4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object orElse;
                orElse = ((Optional) obj).orElse(null);
                return orElse;
            }
        }, Comparator.nullsLast(comparator));
    }

    public static <T extends Comparable<? super T>> T min(T t, T t2) {
        return t.compareTo(t2) <= 0 ? t : t2;
    }

    public static <T> T min(T t, T t2, Comparator<T> comparator) {
        return comparator.compare(t, t2) <= 0 ? t : t2;
    }

    public static <T extends Comparable<? super T>> T max(T t, T t2) {
        return t.compareTo(t2) >= 0 ? t : t2;
    }

    public static <T> T max(T t, T t2, Comparator<T> comparator) {
        return comparator.compare(t, t2) >= 0 ? t : t2;
    }
}
