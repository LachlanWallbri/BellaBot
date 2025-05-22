package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IntSupplier {
    int getAsInt();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IntSupplier safe(ThrowableIntSupplier<Throwable> throwableIntSupplier) {
            return safe(throwableIntSupplier, 0);
        }

        public static IntSupplier safe(final ThrowableIntSupplier<Throwable> throwableIntSupplier, final int i) {
            return new IntSupplier() { // from class: com.annimon.stream.function.IntSupplier.Util.1
                @Override // com.annimon.stream.function.IntSupplier
                public int getAsInt() {
                    try {
                        return ThrowableIntSupplier.this.getAsInt();
                    } catch (Throwable unused) {
                        return i;
                    }
                }
            };
        }
    }
}
