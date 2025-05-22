package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedPredicate<T> {
    boolean test(int i, T t);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T> IndexedPredicate<T> wrap(final Predicate<? super T> predicate) {
            Objects.requireNonNull(predicate);
            return new IndexedPredicate<T>() { // from class: com.annimon.stream.function.IndexedPredicate.Util.1
                @Override // com.annimon.stream.function.IndexedPredicate
                public boolean test(int i, T t) {
                    return Predicate.this.test(t);
                }
            };
        }
    }
}
