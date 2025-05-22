package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface Function<T, R> {
    R apply(T t);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <V, T, R> Function<V, R> compose(Function<? super T, ? extends R> function, Function<? super V, ? extends T> function2) {
            return andThen(function2, function);
        }

        public static <T, R, V> Function<T, V> andThen(final Function<? super T, ? extends R> function, final Function<? super R, ? extends V> function2) {
            return new Function<T, V>() { // from class: com.annimon.stream.function.Function.Util.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.annimon.stream.function.Function
                public V apply(T t) {
                    return (V) Function.this.apply(function.apply(t));
                }
            };
        }

        public static <T, R> Function<T, R> safe(ThrowableFunction<? super T, ? extends R, Throwable> throwableFunction) {
            return safe(throwableFunction, null);
        }

        public static <T, R> Function<T, R> safe(final ThrowableFunction<? super T, ? extends R, Throwable> throwableFunction, final R r) {
            return new Function<T, R>() { // from class: com.annimon.stream.function.Function.Util.2
                @Override // com.annimon.stream.function.Function
                public R apply(T t) {
                    try {
                        return (R) ThrowableFunction.this.apply(t);
                    } catch (Throwable unused) {
                        return (R) r;
                    }
                }
            };
        }
    }
}
