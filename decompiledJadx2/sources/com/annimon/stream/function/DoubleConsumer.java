package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface DoubleConsumer {
    void accept(double d);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static DoubleConsumer andThen(final DoubleConsumer doubleConsumer, final DoubleConsumer doubleConsumer2) {
            return new DoubleConsumer() { // from class: com.annimon.stream.function.DoubleConsumer.Util.1
                @Override // com.annimon.stream.function.DoubleConsumer
                public void accept(double d) {
                    DoubleConsumer.this.accept(d);
                    doubleConsumer2.accept(d);
                }
            };
        }

        public static DoubleConsumer safe(ThrowableDoubleConsumer<Throwable> throwableDoubleConsumer) {
            return safe(throwableDoubleConsumer, null);
        }

        public static DoubleConsumer safe(final ThrowableDoubleConsumer<Throwable> throwableDoubleConsumer, final DoubleConsumer doubleConsumer) {
            return new DoubleConsumer() { // from class: com.annimon.stream.function.DoubleConsumer.Util.2
                @Override // com.annimon.stream.function.DoubleConsumer
                public void accept(double d) {
                    try {
                        ThrowableDoubleConsumer.this.accept(d);
                    } catch (Throwable unused) {
                        DoubleConsumer doubleConsumer2 = doubleConsumer;
                        if (doubleConsumer2 != null) {
                            doubleConsumer2.accept(d);
                        }
                    }
                }
            };
        }
    }
}
