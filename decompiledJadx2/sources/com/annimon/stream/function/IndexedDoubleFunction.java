package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedDoubleFunction<R> {
    R apply(int i, double d);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <R> IndexedDoubleFunction<R> wrap(final DoubleFunction<? extends R> doubleFunction) {
            Objects.requireNonNull(doubleFunction);
            return new IndexedDoubleFunction<R>() { // from class: com.annimon.stream.function.IndexedDoubleFunction.Util.1
                @Override // com.annimon.stream.function.IndexedDoubleFunction
                public R apply(int i, double d) {
                    return (R) DoubleFunction.this.apply(d);
                }
            };
        }
    }
}
