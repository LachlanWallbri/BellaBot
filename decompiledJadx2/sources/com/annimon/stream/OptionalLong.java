package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.LongConsumer;
import com.annimon.stream.function.LongFunction;
import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.function.LongSupplier;
import com.annimon.stream.function.LongToIntFunction;
import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.function.Supplier;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class OptionalLong {
    private static final OptionalLong EMPTY = new OptionalLong();
    private final boolean isPresent;
    private final long value;

    public static OptionalLong empty() {
        return EMPTY;
    }

    /* renamed from: of */
    public static OptionalLong m529of(long j) {
        return new OptionalLong(j);
    }

    public static OptionalLong ofNullable(Long l) {
        return l == null ? EMPTY : new OptionalLong(l.longValue());
    }

    private OptionalLong() {
        this.isPresent = false;
        this.value = 0L;
    }

    private OptionalLong(long j) {
        this.isPresent = true;
        this.value = j;
    }

    public long getAsLong() {
        return orElseThrow();
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public boolean isEmpty() {
        return !this.isPresent;
    }

    public void ifPresent(LongConsumer longConsumer) {
        if (this.isPresent) {
            longConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(LongConsumer longConsumer, Runnable runnable) {
        if (this.isPresent) {
            longConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public OptionalLong executeIfPresent(LongConsumer longConsumer) {
        ifPresent(longConsumer);
        return this;
    }

    public OptionalLong executeIfAbsent(Runnable runnable) {
        if (!isPresent()) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<OptionalLong, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public OptionalLong filter(LongPredicate longPredicate) {
        if (isPresent() && !longPredicate.test(this.value)) {
            return empty();
        }
        return this;
    }

    public OptionalLong filterNot(LongPredicate longPredicate) {
        return filter(LongPredicate.Util.negate(longPredicate));
    }

    public OptionalLong map(LongUnaryOperator longUnaryOperator) {
        if (!isPresent()) {
            return empty();
        }
        Objects.requireNonNull(longUnaryOperator);
        return m529of(longUnaryOperator.applyAsLong(this.value));
    }

    public <U> Optional<U> mapToObj(LongFunction<U> longFunction) {
        if (!isPresent()) {
            return Optional.empty();
        }
        Objects.requireNonNull(longFunction);
        return Optional.ofNullable(longFunction.apply(this.value));
    }

    public OptionalInt mapToInt(LongToIntFunction longToIntFunction) {
        if (!isPresent()) {
            return OptionalInt.empty();
        }
        Objects.requireNonNull(longToIntFunction);
        return OptionalInt.m527of(longToIntFunction.applyAsInt(this.value));
    }

    public LongStream stream() {
        if (!isPresent()) {
            return LongStream.empty();
        }
        return LongStream.m518of(this.value);
    }

    /* renamed from: or */
    public OptionalLong m530or(Supplier<OptionalLong> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (OptionalLong) Objects.requireNonNull(supplier.get());
    }

    public long orElse(long j) {
        return this.isPresent ? this.value : j;
    }

    public long orElseGet(LongSupplier longSupplier) {
        return this.isPresent ? this.value : longSupplier.getAsLong();
    }

    public long orElseThrow() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    public <X extends Throwable> long orElseThrow(Supplier<X> supplier) throws Throwable {
        if (this.isPresent) {
            return this.value;
        }
        throw supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalLong)) {
            return false;
        }
        OptionalLong optionalLong = (OptionalLong) obj;
        if (this.isPresent && optionalLong.isPresent) {
            if (this.value == optionalLong.value) {
                return true;
            }
        } else if (this.isPresent == optionalLong.isPresent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.isPresent) {
            return Objects.hashCode(Long.valueOf(this.value));
        }
        return 0;
    }

    public String toString() {
        return this.isPresent ? String.format("OptionalLong[%s]", Long.valueOf(this.value)) : "OptionalLong.empty";
    }
}
