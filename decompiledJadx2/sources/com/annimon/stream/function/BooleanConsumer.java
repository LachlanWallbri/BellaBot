package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface BooleanConsumer {
    void accept(boolean z);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static BooleanConsumer andThen(final BooleanConsumer booleanConsumer, final BooleanConsumer booleanConsumer2) {
            return new BooleanConsumer() { // from class: com.annimon.stream.function.BooleanConsumer.Util.1
                @Override // com.annimon.stream.function.BooleanConsumer
                public void accept(boolean z) {
                    BooleanConsumer.this.accept(z);
                    booleanConsumer2.accept(z);
                }
            };
        }
    }
}
