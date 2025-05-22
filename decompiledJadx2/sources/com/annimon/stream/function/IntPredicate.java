package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IntPredicate {
    boolean test(int i);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IntPredicate and(final IntPredicate intPredicate, final IntPredicate intPredicate2) {
            return new IntPredicate() { // from class: com.annimon.stream.function.IntPredicate.Util.1
                @Override // com.annimon.stream.function.IntPredicate
                public boolean test(int i) {
                    return IntPredicate.this.test(i) && intPredicate2.test(i);
                }
            };
        }

        /* renamed from: or */
        public static IntPredicate m537or(final IntPredicate intPredicate, final IntPredicate intPredicate2) {
            return new IntPredicate() { // from class: com.annimon.stream.function.IntPredicate.Util.2
                @Override // com.annimon.stream.function.IntPredicate
                public boolean test(int i) {
                    return IntPredicate.this.test(i) || intPredicate2.test(i);
                }
            };
        }

        public static IntPredicate xor(final IntPredicate intPredicate, final IntPredicate intPredicate2) {
            return new IntPredicate() { // from class: com.annimon.stream.function.IntPredicate.Util.3
                @Override // com.annimon.stream.function.IntPredicate
                public boolean test(int i) {
                    return intPredicate2.test(i) ^ IntPredicate.this.test(i);
                }
            };
        }

        public static IntPredicate negate(final IntPredicate intPredicate) {
            return new IntPredicate() { // from class: com.annimon.stream.function.IntPredicate.Util.4
                @Override // com.annimon.stream.function.IntPredicate
                public boolean test(int i) {
                    return !IntPredicate.this.test(i);
                }
            };
        }

        public static IntPredicate safe(ThrowableIntPredicate<Throwable> throwableIntPredicate) {
            return safe(throwableIntPredicate, false);
        }

        public static IntPredicate safe(final ThrowableIntPredicate<Throwable> throwableIntPredicate, final boolean z) {
            return new IntPredicate() { // from class: com.annimon.stream.function.IntPredicate.Util.5
                @Override // com.annimon.stream.function.IntPredicate
                public boolean test(int i) {
                    try {
                        return ThrowableIntPredicate.this.test(i);
                    } catch (Throwable unused) {
                        return z;
                    }
                }
            };
        }
    }
}
