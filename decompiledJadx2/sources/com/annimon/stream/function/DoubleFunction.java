package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface DoubleFunction<R> {
    R apply(double d);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <R> DoubleFunction<R> safe(ThrowableDoubleFunction<? extends R, Throwable> throwableDoubleFunction) {
            return safe(throwableDoubleFunction, null);
        }

        public static <R> DoubleFunction<R> safe(final ThrowableDoubleFunction<? extends R, Throwable> throwableDoubleFunction, final R r) {
            return new DoubleFunction<R>() { // from class: com.annimon.stream.function.DoubleFunction.Util.1
                @Override // com.annimon.stream.function.DoubleFunction
                public R apply(double d) {
                    try {
                        return (R) ThrowableDoubleFunction.this.apply(d);
                    } catch (Throwable unused) {
                        return (R) r;
                    }
                }
            };
        }
    }
}
