package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface BiFunction<T, U, R> {
    R apply(T t, U u);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T, U, R, V> BiFunction<T, U, V> andThen(final BiFunction<? super T, ? super U, ? extends R> biFunction, final Function<? super R, ? extends V> function) {
            return new BiFunction<T, U, V>() { // from class: com.annimon.stream.function.BiFunction.Util.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.annimon.stream.function.BiFunction
                public V apply(T t, U u) {
                    return (V) Function.this.apply(biFunction.apply(t, u));
                }
            };
        }

        public static <T, U, R> BiFunction<U, T, R> reverse(final BiFunction<? super T, ? super U, ? extends R> biFunction) {
            Objects.requireNonNull(biFunction);
            return new BiFunction<U, T, R>() { // from class: com.annimon.stream.function.BiFunction.Util.2
                @Override // com.annimon.stream.function.BiFunction
                public R apply(U u, T t) {
                    return (R) BiFunction.this.apply(t, u);
                }
            };
        }
    }
}
