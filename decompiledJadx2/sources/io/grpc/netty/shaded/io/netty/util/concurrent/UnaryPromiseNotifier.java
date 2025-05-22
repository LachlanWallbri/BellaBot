package io.grpc.netty.shaded.io.netty.util.concurrent;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class UnaryPromiseNotifier<T> implements FutureListener<T> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) UnaryPromiseNotifier.class);
    private final Promise<? super T> promise;

    public UnaryPromiseNotifier(Promise<? super T> promise) {
        this.promise = (Promise) ObjectUtil.checkNotNull(promise, "promise");
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
    public void operationComplete(Future<T> future) throws Exception {
        cascadeTo(future, this.promise);
    }

    public static <X> void cascadeTo(Future<X> future, Promise<? super X> promise) {
        if (future.isSuccess()) {
            if (promise.trySuccess(future.getNow())) {
                return;
            }
            logger.warn("Failed to mark a promise as success because it is done already: {}", promise);
        } else if (future.isCancelled()) {
            if (promise.cancel(false)) {
                return;
            }
            logger.warn("Failed to cancel a promise because it is done already: {}", promise);
        } else {
            if (promise.tryFailure(future.cause())) {
                return;
            }
            logger.warn("Failed to mark a promise as failure because it's done already: {}", promise, future.cause());
        }
    }
}
