package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface UnaryOperator<T> extends Function<T, T> {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T> UnaryOperator<T> identity() {
            return new UnaryOperator<T>() { // from class: com.annimon.stream.function.UnaryOperator.Util.1
                @Override // com.annimon.stream.function.Function
                public T apply(T t) {
                    return t;
                }
            };
        }
    }
}
