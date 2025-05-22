package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedLongPredicate {
    boolean test(int i, long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedLongPredicate wrap(final LongPredicate longPredicate) {
            Objects.requireNonNull(longPredicate);
            return new IndexedLongPredicate() { // from class: com.annimon.stream.function.IndexedLongPredicate.Util.1
                @Override // com.annimon.stream.function.IndexedLongPredicate
                public boolean test(int i, long j) {
                    return LongPredicate.this.test(j);
                }
            };
        }
    }
}
