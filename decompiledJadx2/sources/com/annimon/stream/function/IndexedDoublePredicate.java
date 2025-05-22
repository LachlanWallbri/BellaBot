package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedDoublePredicate {
    boolean test(int i, double d);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedDoublePredicate wrap(final DoublePredicate doublePredicate) {
            Objects.requireNonNull(doublePredicate);
            return new IndexedDoublePredicate() { // from class: com.annimon.stream.function.IndexedDoublePredicate.Util.1
                @Override // com.annimon.stream.function.IndexedDoublePredicate
                public boolean test(int i, double d) {
                    return DoublePredicate.this.test(d);
                }
            };
        }
    }
}
