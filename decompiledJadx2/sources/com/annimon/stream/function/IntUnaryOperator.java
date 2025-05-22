package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IntUnaryOperator {
    int applyAsInt(int i);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IntUnaryOperator identity() {
            return new IntUnaryOperator() { // from class: com.annimon.stream.function.IntUnaryOperator.Util.1
                @Override // com.annimon.stream.function.IntUnaryOperator
                public int applyAsInt(int i) {
                    return i;
                }
            };
        }
    }
}
