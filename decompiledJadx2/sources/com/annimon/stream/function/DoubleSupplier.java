package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface DoubleSupplier {
    double getAsDouble();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static DoubleSupplier safe(ThrowableDoubleSupplier<Throwable> throwableDoubleSupplier) {
            return safe(throwableDoubleSupplier, 0.0d);
        }

        public static DoubleSupplier safe(final ThrowableDoubleSupplier<Throwable> throwableDoubleSupplier, final double d) {
            return new DoubleSupplier() { // from class: com.annimon.stream.function.DoubleSupplier.Util.1
                @Override // com.annimon.stream.function.DoubleSupplier
                public double getAsDouble() {
                    try {
                        return ThrowableDoubleSupplier.this.getAsDouble();
                    } catch (Throwable unused) {
                        return d;
                    }
                }
            };
        }
    }
}
