package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedLongConsumer {
    void accept(int i, long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedLongConsumer andThen(final IndexedLongConsumer indexedLongConsumer, final IndexedLongConsumer indexedLongConsumer2) {
            return new IndexedLongConsumer() { // from class: com.annimon.stream.function.IndexedLongConsumer.Util.1
                @Override // com.annimon.stream.function.IndexedLongConsumer
                public void accept(int i, long j) {
                    IndexedLongConsumer.this.accept(i, j);
                    indexedLongConsumer2.accept(i, j);
                }
            };
        }

        public static IndexedLongConsumer accept(final IntConsumer intConsumer, final LongConsumer longConsumer) {
            return new IndexedLongConsumer() { // from class: com.annimon.stream.function.IndexedLongConsumer.Util.2
                @Override // com.annimon.stream.function.IndexedLongConsumer
                public void accept(int i, long j) {
                    IntConsumer intConsumer2 = IntConsumer.this;
                    if (intConsumer2 != null) {
                        intConsumer2.accept(i);
                    }
                    LongConsumer longConsumer2 = longConsumer;
                    if (longConsumer2 != null) {
                        longConsumer2.accept(j);
                    }
                }
            };
        }
    }
}
