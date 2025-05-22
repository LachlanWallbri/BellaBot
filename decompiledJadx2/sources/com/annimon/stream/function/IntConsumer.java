package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IntConsumer {
    void accept(int i);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IntConsumer andThen(final IntConsumer intConsumer, final IntConsumer intConsumer2) {
            return new IntConsumer() { // from class: com.annimon.stream.function.IntConsumer.Util.1
                @Override // com.annimon.stream.function.IntConsumer
                public void accept(int i) {
                    IntConsumer.this.accept(i);
                    intConsumer2.accept(i);
                }
            };
        }

        public static IntConsumer safe(ThrowableIntConsumer<Throwable> throwableIntConsumer) {
            return safe(throwableIntConsumer, null);
        }

        public static IntConsumer safe(final ThrowableIntConsumer<Throwable> throwableIntConsumer, final IntConsumer intConsumer) {
            return new IntConsumer() { // from class: com.annimon.stream.function.IntConsumer.Util.2
                @Override // com.annimon.stream.function.IntConsumer
                public void accept(int i) {
                    try {
                        ThrowableIntConsumer.this.accept(i);
                    } catch (Throwable unused) {
                        IntConsumer intConsumer2 = intConsumer;
                        if (intConsumer2 != null) {
                            intConsumer2.accept(i);
                        }
                    }
                }
            };
        }
    }
}
