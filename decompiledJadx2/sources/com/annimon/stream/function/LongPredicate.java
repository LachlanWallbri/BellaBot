package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface LongPredicate {
    boolean test(long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static LongPredicate and(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.1
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return LongPredicate.this.test(j) && longPredicate2.test(j);
                }
            };
        }

        /* renamed from: or */
        public static LongPredicate m538or(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.2
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return LongPredicate.this.test(j) || longPredicate2.test(j);
                }
            };
        }

        public static LongPredicate xor(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.3
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return longPredicate2.test(j) ^ LongPredicate.this.test(j);
                }
            };
        }

        public static LongPredicate negate(final LongPredicate longPredicate) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.4
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return !LongPredicate.this.test(j);
                }
            };
        }

        public static LongPredicate safe(ThrowableLongPredicate<Throwable> throwableLongPredicate) {
            return safe(throwableLongPredicate, false);
        }

        public static LongPredicate safe(final ThrowableLongPredicate<Throwable> throwableLongPredicate, final boolean z) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.5
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    try {
                        return ThrowableLongPredicate.this.test(j);
                    } catch (Throwable unused) {
                        return z;
                    }
                }
            };
        }
    }
}
