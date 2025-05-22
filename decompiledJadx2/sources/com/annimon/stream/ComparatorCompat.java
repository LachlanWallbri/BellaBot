package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;
import java.util.Collections;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class ComparatorCompat<T> implements Comparator<T> {
    private static final ComparatorCompat<Comparable<Object>> NATURAL_ORDER = new ComparatorCompat<>(new Comparator<Comparable<Object>>() { // from class: com.annimon.stream.ComparatorCompat.1
        @Override // java.util.Comparator
        public int compare(Comparable<Object> comparable, Comparable<Object> comparable2) {
            return comparable.compareTo(comparable2);
        }
    });
    private static final ComparatorCompat<Comparable<Object>> REVERSE_ORDER = new ComparatorCompat<>(Collections.reverseOrder());
    private final Comparator<? super T> comparator;

    public static <T extends Comparable<? super T>> ComparatorCompat<T> naturalOrder() {
        return (ComparatorCompat<T>) NATURAL_ORDER;
    }

    public static <T extends Comparable<? super T>> ComparatorCompat<T> reverseOrder() {
        return (ComparatorCompat<T>) REVERSE_ORDER;
    }

    public static <T> Comparator<T> reversed(Comparator<T> comparator) {
        return Collections.reverseOrder(comparator);
    }

    public static <T> Comparator<T> thenComparing(final Comparator<? super T> comparator, final Comparator<? super T> comparator2) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(comparator2);
        return new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.2
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                int compare = comparator.compare(t, t2);
                return compare != 0 ? compare : comparator2.compare(t, t2);
            }
        };
    }

    public static <T, U> ComparatorCompat<T> comparing(final Function<? super T, ? extends U> function, final Comparator<? super U> comparator) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(comparator);
        return new ComparatorCompat<>(new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                return comparator.compare(Function.this.apply(t), Function.this.apply(t2));
            }
        });
    }

    public static <T, U extends Comparable<? super U>> ComparatorCompat<T> comparing(final Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function);
        return new ComparatorCompat<>(new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.4
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                return ((Comparable) Function.this.apply(t)).compareTo((Comparable) Function.this.apply(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> comparingInt(final ToIntFunction<? super T> toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new ComparatorCompat<>(new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.5
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                return Objects.compareInt(ToIntFunction.this.applyAsInt(t), ToIntFunction.this.applyAsInt(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> comparingLong(final ToLongFunction<? super T> toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new ComparatorCompat<>(new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.6
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                return Objects.compareLong(ToLongFunction.this.applyAsLong(t), ToLongFunction.this.applyAsLong(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> comparingDouble(final ToDoubleFunction<? super T> toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new ComparatorCompat<>(new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.7
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                return Double.compare(ToDoubleFunction.this.applyAsDouble(t), ToDoubleFunction.this.applyAsDouble(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> nullsFirst() {
        return nullsComparator(true, null);
    }

    public static <T> ComparatorCompat<T> nullsFirst(Comparator<? super T> comparator) {
        return nullsComparator(true, comparator);
    }

    public static <T> ComparatorCompat<T> nullsLast() {
        return nullsComparator(false, null);
    }

    public static <T> ComparatorCompat<T> nullsLast(Comparator<? super T> comparator) {
        return nullsComparator(false, comparator);
    }

    private static <T> ComparatorCompat<T> nullsComparator(final boolean z, final Comparator<? super T> comparator) {
        return new ComparatorCompat<>(new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.8
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                if (t == null) {
                    if (t2 == null) {
                        return 0;
                    }
                    return z ? -1 : 1;
                }
                if (t2 == null) {
                    return z ? 1 : -1;
                }
                Comparator comparator2 = comparator;
                if (comparator2 == null) {
                    return 0;
                }
                return comparator2.compare(t, t2);
            }
        });
    }

    public static <T> ComparatorCompat<T> chain(Comparator<T> comparator) {
        return new ComparatorCompat<>(comparator);
    }

    public ComparatorCompat(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override // java.util.Comparator
    public ComparatorCompat<T> reversed() {
        return new ComparatorCompat<>(Collections.reverseOrder(this.comparator));
    }

    @Override // java.util.Comparator
    public ComparatorCompat<T> thenComparing(final Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return new ComparatorCompat<>(new Comparator<T>() { // from class: com.annimon.stream.ComparatorCompat.9
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                int compare = ComparatorCompat.this.comparator.compare(t, t2);
                return compare != 0 ? compare : comparator.compare(t, t2);
            }
        });
    }

    public <U> ComparatorCompat<T> thenComparing(Function<? super T, ? extends U> function, Comparator<? super U> comparator) {
        return thenComparing((Comparator) comparing(function, comparator));
    }

    public <U extends Comparable<? super U>> ComparatorCompat<T> thenComparing(Function<? super T, ? extends U> function) {
        return thenComparing((Comparator) comparing(function));
    }

    public ComparatorCompat<T> thenComparingInt(ToIntFunction<? super T> toIntFunction) {
        return thenComparing((Comparator) comparingInt(toIntFunction));
    }

    public ComparatorCompat<T> thenComparingLong(ToLongFunction<? super T> toLongFunction) {
        return thenComparing((Comparator) comparingLong(toLongFunction));
    }

    public ComparatorCompat<T> thenComparingDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return thenComparing((Comparator) comparingDouble(toDoubleFunction));
    }

    public Comparator<T> comparator() {
        return this.comparator;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        return this.comparator.compare(t, t2);
    }
}
