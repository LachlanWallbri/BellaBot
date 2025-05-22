package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedIntConsumer {
    void accept(int i, int i2);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedIntConsumer andThen(final IndexedIntConsumer indexedIntConsumer, final IndexedIntConsumer indexedIntConsumer2) {
            return new IndexedIntConsumer() { // from class: com.annimon.stream.function.IndexedIntConsumer.Util.1
                @Override // com.annimon.stream.function.IndexedIntConsumer
                public void accept(int i, int i2) {
                    IndexedIntConsumer.this.accept(i, i2);
                    indexedIntConsumer2.accept(i, i2);
                }
            };
        }

        public static IndexedIntConsumer accept(final IntConsumer intConsumer, final IntConsumer intConsumer2) {
            return new IndexedIntConsumer() { // from class: com.annimon.stream.function.IndexedIntConsumer.Util.2
                @Override // com.annimon.stream.function.IndexedIntConsumer
                public void accept(int i, int i2) {
                    IntConsumer intConsumer3 = IntConsumer.this;
                    if (intConsumer3 != null) {
                        intConsumer3.accept(i);
                    }
                    IntConsumer intConsumer4 = intConsumer2;
                    if (intConsumer4 != null) {
                        intConsumer4.accept(i2);
                    }
                }
            };
        }
    }
}
