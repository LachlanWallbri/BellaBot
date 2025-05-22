package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface DoublePredicate {
    boolean test(double d);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static DoublePredicate and(final DoublePredicate doublePredicate, final DoublePredicate doublePredicate2) {
            return new DoublePredicate() { // from class: com.annimon.stream.function.DoublePredicate.Util.1
                @Override // com.annimon.stream.function.DoublePredicate
                public boolean test(double d) {
                    return DoublePredicate.this.test(d) && doublePredicate2.test(d);
                }
            };
        }

        /* renamed from: or */
        public static DoublePredicate m536or(final DoublePredicate doublePredicate, final DoublePredicate doublePredicate2) {
            return new DoublePredicate() { // from class: com.annimon.stream.function.DoublePredicate.Util.2
                @Override // com.annimon.stream.function.DoublePredicate
                public boolean test(double d) {
                    return DoublePredicate.this.test(d) || doublePredicate2.test(d);
                }
            };
        }

        public static DoublePredicate xor(final DoublePredicate doublePredicate, final DoublePredicate doublePredicate2) {
            return new DoublePredicate() { // from class: com.annimon.stream.function.DoublePredicate.Util.3
                @Override // com.annimon.stream.function.DoublePredicate
                public boolean test(double d) {
                    return doublePredicate2.test(d) ^ DoublePredicate.this.test(d);
                }
            };
        }

        public static DoublePredicate negate(final DoublePredicate doublePredicate) {
            return new DoublePredicate() { // from class: com.annimon.stream.function.DoublePredicate.Util.4
                @Override // com.annimon.stream.function.DoublePredicate
                public boolean test(double d) {
                    return !DoublePredicate.this.test(d);
                }
            };
        }

        public static DoublePredicate safe(ThrowableDoublePredicate<Throwable> throwableDoublePredicate) {
            return safe(throwableDoublePredicate, false);
        }

        public static DoublePredicate safe(final ThrowableDoublePredicate<Throwable> throwableDoublePredicate, final boolean z) {
            return new DoublePredicate() { // from class: com.annimon.stream.function.DoublePredicate.Util.5
                @Override // com.annimon.stream.function.DoublePredicate
                public boolean test(double d) {
                    try {
                        return ThrowableDoublePredicate.this.test(d);
                    } catch (Throwable unused) {
                        return z;
                    }
                }
            };
        }
    }
}
