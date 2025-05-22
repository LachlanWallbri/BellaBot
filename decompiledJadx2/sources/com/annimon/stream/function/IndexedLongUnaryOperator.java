package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedLongUnaryOperator {
    long applyAsLong(int i, long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedLongUnaryOperator wrap(final LongUnaryOperator longUnaryOperator) {
            Objects.requireNonNull(longUnaryOperator);
            return new IndexedLongUnaryOperator() { // from class: com.annimon.stream.function.IndexedLongUnaryOperator.Util.1
                @Override // com.annimon.stream.function.IndexedLongUnaryOperator
                public long applyAsLong(int i, long j) {
                    return LongUnaryOperator.this.applyAsLong(j);
                }
            };
        }
    }
}
