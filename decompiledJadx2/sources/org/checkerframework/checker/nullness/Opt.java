package org.checkerframework.checker.nullness;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes9.dex */
public final class Opt {
    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public static boolean isPresent(Object obj) {
        return obj != null;
    }

    public static <T> T orElse(T t, T t2) {
        return t != null ? t : t2;
    }

    private Opt() {
        throw new AssertionError("shouldn't be instantiated");
    }

    public static <T> T get(T t) {
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public static <T> void ifPresent(T t, Consumer<? super T> consumer) {
        if (t != null) {
            consumer.accept(t);
        }
    }

    public static <T> T filter(T t, Predicate<? super T> predicate) {
        if (t != null && predicate.test(t)) {
            return t;
        }
        return null;
    }

    public static <T, U> U map(T t, Function<? super T, ? extends U> function) {
        if (t == null) {
            return null;
        }
        return function.apply(t);
    }

    public static <T> T orElseGet(T t, Supplier<? extends T> supplier) {
        return t != null ? t : supplier.get();
    }

    public static <T, X extends Throwable> T orElseThrow(T t, Supplier<? extends X> supplier) throws Throwable {
        if (t != null) {
            return t;
        }
        throw supplier.get();
    }
}
