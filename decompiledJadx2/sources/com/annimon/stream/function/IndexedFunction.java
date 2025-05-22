package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedFunction<T, R> {
    R apply(int i, T t);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T, R> IndexedFunction<T, R> wrap(final Function<? super T, ? extends R> function) {
            Objects.requireNonNull(function);
            return new IndexedFunction<T, R>() { // from class: com.annimon.stream.function.IndexedFunction.Util.1
                @Override // com.annimon.stream.function.IndexedFunction
                public R apply(int i, T t) {
                    return (R) Function.this.apply(t);
                }
            };
        }
    }
}
