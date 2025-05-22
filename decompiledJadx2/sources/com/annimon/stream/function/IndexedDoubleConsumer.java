package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedDoubleConsumer {
    void accept(int i, double d);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedDoubleConsumer andThen(final IndexedDoubleConsumer indexedDoubleConsumer, final IndexedDoubleConsumer indexedDoubleConsumer2) {
            return new IndexedDoubleConsumer() { // from class: com.annimon.stream.function.IndexedDoubleConsumer.Util.1
                @Override // com.annimon.stream.function.IndexedDoubleConsumer
                public void accept(int i, double d) {
                    IndexedDoubleConsumer.this.accept(i, d);
                    indexedDoubleConsumer2.accept(i, d);
                }
            };
        }

        public static IndexedDoubleConsumer accept(final IntConsumer intConsumer, final DoubleConsumer doubleConsumer) {
            return new IndexedDoubleConsumer() { // from class: com.annimon.stream.function.IndexedDoubleConsumer.Util.2
                @Override // com.annimon.stream.function.IndexedDoubleConsumer
                public void accept(int i, double d) {
                    IntConsumer intConsumer2 = IntConsumer.this;
                    if (intConsumer2 != null) {
                        intConsumer2.accept(i);
                    }
                    DoubleConsumer doubleConsumer2 = doubleConsumer;
                    if (doubleConsumer2 != null) {
                        doubleConsumer2.accept(d);
                    }
                }
            };
        }
    }
}
