package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedIntFunction<R> {
    R apply(int i, int i2);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <R> IndexedIntFunction<R> wrap(final IntFunction<? extends R> intFunction) {
            Objects.requireNonNull(intFunction);
            return new IndexedIntFunction<R>() { // from class: com.annimon.stream.function.IndexedIntFunction.Util.1
                @Override // com.annimon.stream.function.IndexedIntFunction
                public R apply(int i, int i2) {
                    return (R) IntFunction.this.apply(i2);
                }
            };
        }
    }
}
