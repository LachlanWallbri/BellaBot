package com.annimon.stream;

import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ThrowableFunction;
import com.annimon.stream.function.ThrowableSupplier;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Exceptional<T> {
    private final Throwable throwable;
    private final T value;

    /* renamed from: of */
    public static <T> Exceptional<T> m512of(ThrowableSupplier<T, Throwable> throwableSupplier) {
        try {
            return new Exceptional<>(throwableSupplier.get(), null);
        } catch (Throwable th) {
            return m513of(th);
        }
    }

    /* renamed from: of */
    public static <T> Exceptional<T> m513of(Throwable th) {
        return new Exceptional<>(null, th);
    }

    private Exceptional(T t, Throwable th) {
        this.value = t;
        this.throwable = th;
    }

    public T get() {
        return this.value;
    }

    public boolean isPresent() {
        return this.throwable == null;
    }

    public T getOrElse(T t) {
        return this.throwable == null ? this.value : t;
    }

    public T getOrElse(Supplier<? extends T> supplier) {
        return this.throwable == null ? this.value : supplier.get();
    }

    public Optional<T> getOptional() {
        return Optional.ofNullable(this.value);
    }

    public Throwable getException() {
        return this.throwable;
    }

    public T getOrThrow() throws Throwable {
        Throwable th = this.throwable;
        if (th != null) {
            throw th;
        }
        return this.value;
    }

    public T getOrThrowRuntimeException() throws RuntimeException {
        Throwable th = this.throwable;
        if (th != null) {
            throw new RuntimeException(th);
        }
        return this.value;
    }

    public <E extends Throwable> T getOrThrow(E e) throws Throwable {
        Throwable th = this.throwable;
        if (th != null) {
            e.initCause(th);
            throw e;
        }
        return this.value;
    }

    /* renamed from: or */
    public Exceptional<T> m514or(Supplier<Exceptional<T>> supplier) {
        if (this.throwable == null) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (Exceptional) Objects.requireNonNull(supplier.get());
    }

    public <R> R custom(Function<Exceptional<T>, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public <U> Exceptional<U> map(ThrowableFunction<? super T, ? extends U, Throwable> throwableFunction) {
        Throwable th = this.throwable;
        if (th != null) {
            return m513of(th);
        }
        Objects.requireNonNull(throwableFunction);
        try {
            return new Exceptional<>(throwableFunction.apply(this.value), null);
        } catch (Throwable th2) {
            return m513of(th2);
        }
    }

    public Exceptional<T> ifPresent(Consumer<? super T> consumer) {
        if (this.throwable == null) {
            consumer.accept(this.value);
        }
        return this;
    }

    public Exceptional<T> ifException(Consumer<Throwable> consumer) {
        Throwable th = this.throwable;
        if (th != null) {
            consumer.accept(th);
        }
        return this;
    }

    public <E extends Throwable> Exceptional<T> ifExceptionIs(Class<E> cls, Consumer<? super E> consumer) {
        Throwable th = this.throwable;
        if (th != null && cls.isAssignableFrom(th.getClass())) {
            consumer.accept(this.throwable);
        }
        return this;
    }

    public Exceptional<T> recover(ThrowableFunction<Throwable, ? extends T, Throwable> throwableFunction) {
        if (this.throwable == null) {
            return this;
        }
        Objects.requireNonNull(throwableFunction);
        try {
            return new Exceptional<>(throwableFunction.apply(this.throwable), null);
        } catch (Throwable th) {
            return m513of(th);
        }
    }

    public Exceptional<T> recoverWith(Function<Throwable, ? extends Exceptional<T>> function) {
        if (this.throwable == null) {
            return this;
        }
        Objects.requireNonNull(function);
        return (Exceptional) Objects.requireNonNull(function.apply(this.throwable));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Exceptional)) {
            return false;
        }
        Exceptional exceptional = (Exceptional) obj;
        return Objects.equals(this.value, exceptional.value) && Objects.equals(this.throwable, exceptional.throwable);
    }

    public int hashCode() {
        return Objects.hash(this.value, this.throwable);
    }

    public String toString() {
        Throwable th = this.throwable;
        if (th == null) {
            return String.format("Exceptional value %s", this.value);
        }
        return String.format("Exceptional throwable %s", th);
    }
}
