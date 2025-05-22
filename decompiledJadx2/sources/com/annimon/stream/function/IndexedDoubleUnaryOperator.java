package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IndexedDoubleUnaryOperator {
    double applyAsDouble(int i, double d);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static IndexedDoubleUnaryOperator wrap(final DoubleUnaryOperator doubleUnaryOperator) {
            Objects.requireNonNull(doubleUnaryOperator);
            return new IndexedDoubleUnaryOperator() { // from class: com.annimon.stream.function.IndexedDoubleUnaryOperator.Util.1
                @Override // com.annimon.stream.function.IndexedDoubleUnaryOperator
                public double applyAsDouble(int i, double d) {
                    return DoubleUnaryOperator.this.applyAsDouble(d);
                }
            };
        }
    }
}
