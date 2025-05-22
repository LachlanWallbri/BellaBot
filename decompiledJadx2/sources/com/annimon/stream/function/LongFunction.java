package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface LongFunction<R> {
    R apply(long j);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <R> LongFunction<R> safe(ThrowableLongFunction<? extends R, Throwable> throwableLongFunction) {
            return safe(throwableLongFunction, null);
        }

        public static <R> LongFunction<R> safe(final ThrowableLongFunction<? extends R, Throwable> throwableLongFunction, final R r) {
            return new LongFunction<R>() { // from class: com.annimon.stream.function.LongFunction.Util.1
                @Override // com.annimon.stream.function.LongFunction
                public R apply(long j) {
                    try {
                        return (R) ThrowableLongFunction.this.apply(j);
                    } catch (Throwable unused) {
                        return (R) r;
                    }
                }
            };
        }
    }
}
