package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface Consumer<T> {
    void accept(T t);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T> Consumer<T> andThen(final Consumer<? super T> consumer, final Consumer<? super T> consumer2) {
            return new Consumer<T>() { // from class: com.annimon.stream.function.Consumer.Util.1
                @Override // com.annimon.stream.function.Consumer
                public void accept(T t) {
                    Consumer.this.accept(t);
                    consumer2.accept(t);
                }
            };
        }

        public static <T> Consumer<T> safe(ThrowableConsumer<? super T, Throwable> throwableConsumer) {
            return safe(throwableConsumer, null);
        }

        public static <T> Consumer<T> safe(final ThrowableConsumer<? super T, Throwable> throwableConsumer, final Consumer<? super T> consumer) {
            return new Consumer<T>() { // from class: com.annimon.stream.function.Consumer.Util.2
                @Override // com.annimon.stream.function.Consumer
                public void accept(T t) {
                    Objects.requireNonNull(ThrowableConsumer.this);
                    try {
                        ThrowableConsumer.this.accept(t);
                    } catch (Throwable unused) {
                        Consumer consumer2 = consumer;
                        if (consumer2 != null) {
                            consumer2.accept(t);
                        }
                    }
                }
            };
        }
    }
}
