package com.annimon.stream.function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface Supplier<T> {
    T get();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T> Supplier<T> safe(ThrowableSupplier<? extends T, Throwable> throwableSupplier) {
            return safe(throwableSupplier, null);
        }

        public static <T> Supplier<T> safe(final ThrowableSupplier<? extends T, Throwable> throwableSupplier, final T t) {
            return new Supplier<T>() { // from class: com.annimon.stream.function.Supplier.Util.1
                @Override // com.annimon.stream.function.Supplier
                public T get() {
                    try {
                        return (T) ThrowableSupplier.this.get();
                    } catch (Throwable unused) {
                        return (T) t;
                    }
                }
            };
        }
    }
}
