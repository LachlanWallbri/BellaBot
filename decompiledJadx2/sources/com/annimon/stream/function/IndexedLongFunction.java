package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedLongFunction<R> {
    R apply(int i, long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <R> IndexedLongFunction<R> wrap(final LongFunction<? extends R> longFunction) {
            Objects.requireNonNull(longFunction);
            return new IndexedLongFunction<R>() { // from class: com.annimon.stream.function.IndexedLongFunction.Util.1
                @Override // com.annimon.stream.function.IndexedLongFunction
                public R apply(int i, long j) {
                    return (R) LongFunction.this.apply(j);
                }
            };
        }
    }
}
