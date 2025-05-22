package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface BiConsumer<T, U> {
    void accept(T t, U u);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T, U> BiConsumer<T, U> andThen(final BiConsumer<? super T, ? super U> biConsumer, final BiConsumer<? super T, ? super U> biConsumer2) {
            return new BiConsumer<T, U>() { // from class: com.annimon.stream.function.BiConsumer.Util.1
                @Override // com.annimon.stream.function.BiConsumer
                public void accept(T t, U u) {
                    BiConsumer.this.accept(t, u);
                    biConsumer2.accept(t, u);
                }
            };
        }
    }
}
