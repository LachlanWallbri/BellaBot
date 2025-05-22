package com.annimon.stream.function;

import com.annimon.stream.Objects;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface Predicate<T> {
    boolean test(T t);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T> Predicate<T> and(final Predicate<? super T> predicate, final Predicate<? super T> predicate2) {
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.1
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    return Predicate.this.test(t) && predicate2.test(t);
                }
            };
        }

        public static <T> Predicate<T> and(final Predicate<? super T> predicate, final Predicate<? super T> predicate2, final Predicate<? super T>... predicateArr) {
            Objects.requireNonNull(predicate);
            Objects.requireNonNull(predicate2);
            Objects.requireNonNull(predicateArr);
            Objects.requireNonNullElements(Arrays.asList(predicateArr));
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.2
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    if (!(Predicate.this.test(t) && predicate2.test(t))) {
                        return false;
                    }
                    for (Predicate predicate3 : predicateArr) {
                        if (!predicate3.test(t)) {
                            return false;
                        }
                    }
                    return true;
                }
            };
        }

        /* renamed from: or */
        public static <T> Predicate<T> m539or(final Predicate<? super T> predicate, final Predicate<? super T> predicate2) {
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.3
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    return Predicate.this.test(t) || predicate2.test(t);
                }
            };
        }

        /* renamed from: or */
        public static <T> Predicate<T> m540or(final Predicate<? super T> predicate, final Predicate<? super T> predicate2, final Predicate<? super T>... predicateArr) {
            Objects.requireNonNull(predicate);
            Objects.requireNonNull(predicate2);
            Objects.requireNonNull(predicateArr);
            Objects.requireNonNullElements(Arrays.asList(predicateArr));
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.4
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    if (Predicate.this.test(t) || predicate2.test(t)) {
                        return true;
                    }
                    for (Predicate predicate3 : predicateArr) {
                        if (predicate3.test(t)) {
                            return true;
                        }
                    }
                    return false;
                }
            };
        }

        public static <T> Predicate<T> xor(final Predicate<? super T> predicate, final Predicate<? super T> predicate2) {
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.5
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    return predicate2.test(t) ^ Predicate.this.test(t);
                }
            };
        }

        public static <T> Predicate<T> negate(final Predicate<? super T> predicate) {
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.6
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    return !Predicate.this.test(t);
                }
            };
        }

        public static <T> Predicate<T> notNull() {
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.7
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    return t != null;
                }
            };
        }

        public static <T> Predicate<T> safe(ThrowablePredicate<? super T, Throwable> throwablePredicate) {
            return safe(throwablePredicate, false);
        }

        public static <T> Predicate<T> safe(final ThrowablePredicate<? super T, Throwable> throwablePredicate, final boolean z) {
            return new Predicate<T>() { // from class: com.annimon.stream.function.Predicate.Util.8
                @Override // com.annimon.stream.function.Predicate
                public boolean test(T t) {
                    try {
                        return ThrowablePredicate.this.test(t);
                    } catch (Throwable unused) {
                        return z;
                    }
                }
            };
        }
    }
}
