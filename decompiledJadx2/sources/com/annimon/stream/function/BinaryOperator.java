package com.annimon.stream.function;

import com.annimon.stream.Objects;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface BinaryOperator<T> extends BiFunction<T, T, T> {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T> BinaryOperator<T> minBy(final Comparator<? super T> comparator) {
            Objects.requireNonNull(comparator);
            return new BinaryOperator<T>() { // from class: com.annimon.stream.function.BinaryOperator.Util.1
                @Override // com.annimon.stream.function.BiFunction
                public T apply(T t, T t2) {
                    return comparator.compare(t, t2) <= 0 ? t : t2;
                }
            };
        }

        public static <T> BinaryOperator<T> maxBy(final Comparator<? super T> comparator) {
            Objects.requireNonNull(comparator);
            return new BinaryOperator<T>() { // from class: com.annimon.stream.function.BinaryOperator.Util.2
                @Override // com.annimon.stream.function.BiFunction
                public T apply(T t, T t2) {
                    return comparator.compare(t, t2) >= 0 ? t : t2;
                }
            };
        }
    }
}
