package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedConsumer<T> {
    void accept(int i, T t);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T> IndexedConsumer<T> wrap(final Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            return new IndexedConsumer<T>() { // from class: com.annimon.stream.function.IndexedConsumer.Util.1
                @Override // com.annimon.stream.function.IndexedConsumer
                public void accept(int i, T t) {
                    Consumer.this.accept(t);
                }
            };
        }

        public static <T> IndexedConsumer<T> accept(final IntConsumer intConsumer, final Consumer<? super T> consumer) {
            return new IndexedConsumer<T>() { // from class: com.annimon.stream.function.IndexedConsumer.Util.2
                @Override // com.annimon.stream.function.IndexedConsumer
                public void accept(int i, T t) {
                    IntConsumer intConsumer2 = IntConsumer.this;
                    if (intConsumer2 != null) {
                        intConsumer2.accept(i);
                    }
                    Consumer consumer2 = consumer;
                    if (consumer2 != null) {
                        consumer2.accept(t);
                    }
                }
            };
        }
    }
}
