package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedIntPredicate {
    boolean test(int i, int i2);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedIntPredicate wrap(final IntPredicate intPredicate) {
            Objects.requireNonNull(intPredicate);
            return new IndexedIntPredicate() { // from class: com.annimon.stream.function.IndexedIntPredicate.Util.1
                @Override // com.annimon.stream.function.IndexedIntPredicate
                public boolean test(int i, int i2) {
                    return IntPredicate.this.test(i2);
                }
            };
        }
    }
}
