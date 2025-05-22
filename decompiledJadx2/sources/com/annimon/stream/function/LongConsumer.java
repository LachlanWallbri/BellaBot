package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface LongConsumer {
    void accept(long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static LongConsumer andThen(final LongConsumer longConsumer, final LongConsumer longConsumer2) {
            return new LongConsumer() { // from class: com.annimon.stream.function.LongConsumer.Util.1
                @Override // com.annimon.stream.function.LongConsumer
                public void accept(long j) {
                    LongConsumer.this.accept(j);
                    longConsumer2.accept(j);
                }
            };
        }

        public static LongConsumer safe(ThrowableLongConsumer<Throwable> throwableLongConsumer) {
            return safe(throwableLongConsumer, null);
        }

        public static LongConsumer safe(final ThrowableLongConsumer<Throwable> throwableLongConsumer, final LongConsumer longConsumer) {
            return new LongConsumer() { // from class: com.annimon.stream.function.LongConsumer.Util.2
                @Override // com.annimon.stream.function.LongConsumer
                public void accept(long j) {
                    try {
                        ThrowableLongConsumer.this.accept(j);
                    } catch (Throwable unused) {
                        LongConsumer longConsumer2 = longConsumer;
                        if (longConsumer2 != null) {
                            longConsumer2.accept(j);
                        }
                    }
                }
            };
        }
    }
}
