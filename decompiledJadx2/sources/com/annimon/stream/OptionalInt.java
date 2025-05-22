package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.function.IntFunction;
import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.function.IntToDoubleFunction;
import com.annimon.stream.function.IntToLongFunction;
import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.function.Supplier;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class OptionalInt {
    private static final OptionalInt EMPTY = new OptionalInt();
    private final boolean isPresent;
    private final int value;

    private OptionalInt() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalInt empty() {
        return EMPTY;
    }

    private OptionalInt(int i) {
        this.isPresent = true;
        this.value = i;
    }

    /* renamed from: of */
    public static OptionalInt m527of(int i) {
        return new OptionalInt(i);
    }

    public static OptionalInt ofNullable(Integer num) {
        return num == null ? EMPTY : new OptionalInt(num.intValue());
    }

    public int getAsInt() {
        return orElseThrow();
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public boolean isEmpty() {
        return !this.isPresent;
    }

    public void ifPresent(IntConsumer intConsumer) {
        if (this.isPresent) {
            intConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(IntConsumer intConsumer, Runnable runnable) {
        if (this.isPresent) {
            intConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public OptionalInt executeIfPresent(IntConsumer intConsumer) {
        ifPresent(intConsumer);
        return this;
    }

    public OptionalInt executeIfAbsent(Runnable runnable) {
        if (!isPresent()) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<OptionalInt, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public OptionalInt filter(IntPredicate intPredicate) {
        if (isPresent() && !intPredicate.test(this.value)) {
            return empty();
        }
        return this;
    }

    public OptionalInt filterNot(IntPredicate intPredicate) {
        return filter(IntPredicate.Util.negate(intPredicate));
    }

    public OptionalInt map(IntUnaryOperator intUnaryOperator) {
        return !isPresent() ? empty() : m527of(intUnaryOperator.applyAsInt(this.value));
    }

    public <U> Optional<U> mapToObj(IntFunction<U> intFunction) {
        return !isPresent() ? Optional.empty() : Optional.ofNullable(intFunction.apply(this.value));
    }

    public OptionalLong mapToLong(IntToLongFunction intToLongFunction) {
        return !isPresent() ? OptionalLong.empty() : OptionalLong.m529of(intToLongFunction.applyAsLong(this.value));
    }

    public OptionalDouble mapToDouble(IntToDoubleFunction intToDoubleFunction) {
        return !isPresent() ? OptionalDouble.empty() : OptionalDouble.m525of(intToDoubleFunction.applyAsDouble(this.value));
    }

    public IntStream stream() {
        return !isPresent() ? IntStream.empty() : IntStream.m515of(this.value);
    }

    /* renamed from: or */
    public OptionalInt m528or(Supplier<OptionalInt> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (OptionalInt) Objects.requireNonNull(supplier.get());
    }

    public int orElse(int i) {
        return this.isPresent ? this.value : i;
    }

    public int orElseGet(IntSupplier intSupplier) {
        return this.isPresent ? this.value : intSupplier.getAsInt();
    }

    public int orElseThrow() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    public <X extends Throwable> int orElseThrow(Supplier<X> supplier) throws Throwable {
        if (this.isPresent) {
            return this.value;
        }
        throw supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalInt)) {
            return false;
        }
        OptionalInt optionalInt = (OptionalInt) obj;
        if (this.isPresent && optionalInt.isPresent) {
            if (this.value == optionalInt.value) {
                return true;
            }
        } else if (this.isPresent == optionalInt.isPresent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.isPresent) {
            return this.value;
        }
        return 0;
    }

    public String toString() {
        return this.isPresent ? String.format("OptionalInt[%s]", Integer.valueOf(this.value)) : "OptionalInt.empty";
    }
}
