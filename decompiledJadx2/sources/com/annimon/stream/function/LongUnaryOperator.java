package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface LongUnaryOperator {
    long applyAsLong(long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static LongUnaryOperator identity() {
            return new LongUnaryOperator() { // from class: com.annimon.stream.function.LongUnaryOperator.Util.1
                @Override // com.annimon.stream.function.LongUnaryOperator
                public long applyAsLong(long j) {
                    return j;
                }
            };
        }
    }
}
