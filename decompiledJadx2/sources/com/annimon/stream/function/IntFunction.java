package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IntFunction<R> {
    R apply(int i);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <R> IntFunction<R> safe(ThrowableIntFunction<? extends R, Throwable> throwableIntFunction) {
            return safe(throwableIntFunction, null);
        }

        public static <R> IntFunction<R> safe(final ThrowableIntFunction<? extends R, Throwable> throwableIntFunction, final R r) {
            return new IntFunction<R>() { // from class: com.annimon.stream.function.IntFunction.Util.1
                @Override // com.annimon.stream.function.IntFunction
                public R apply(int i) {
                    try {
                        return (R) ThrowableIntFunction.this.apply(i);
                    } catch (Throwable unused) {
                        return (R) r;
                    }
                }
            };
        }
    }
}
