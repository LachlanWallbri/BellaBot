package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface LongSupplier {
    long getAsLong();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static LongSupplier safe(ThrowableLongSupplier<Throwable> throwableLongSupplier) {
            return safe(throwableLongSupplier, 0L);
        }

        public static LongSupplier safe(final ThrowableLongSupplier<Throwable> throwableLongSupplier, final long j) {
            return new LongSupplier() { // from class: com.annimon.stream.function.LongSupplier.Util.1
                @Override // com.annimon.stream.function.LongSupplier
                public long getAsLong() {
                    try {
                        return ThrowableLongSupplier.this.getAsLong();
                    } catch (Throwable unused) {
                        return j;
                    }
                }
            };
        }
    }
}
