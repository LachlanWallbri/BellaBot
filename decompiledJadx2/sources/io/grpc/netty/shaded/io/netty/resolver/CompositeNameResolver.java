package io.grpc.netty.shaded.io.netty.resolver;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CompositeNameResolver<T> extends SimpleNameResolver<T> {
    private final NameResolver<T>[] resolvers;

    public CompositeNameResolver(EventExecutor eventExecutor, NameResolver<T>... nameResolverArr) {
        super(eventExecutor);
        ObjectUtil.checkNotNull(nameResolverArr, "resolvers");
        for (int i = 0; i < nameResolverArr.length; i++) {
            ObjectUtil.checkNotNull(nameResolverArr[i], "resolvers[" + i + ']');
        }
        if (nameResolverArr.length < 2) {
            throw new IllegalArgumentException("resolvers: " + Arrays.asList(nameResolverArr) + " (expected: at least 2 resolvers)");
        }
        this.resolvers = (NameResolver[]) nameResolverArr.clone();
    }

    @Override // io.grpc.netty.shaded.io.netty.resolver.SimpleNameResolver
    protected void doResolve(String str, Promise<T> promise) throws Exception {
        doResolveRec(str, promise, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doResolveRec(final String str, final Promise<T> promise, final int i, Throwable th) throws Exception {
        NameResolver<T>[] nameResolverArr = this.resolvers;
        if (i >= nameResolverArr.length) {
            promise.setFailure(th);
        } else {
            nameResolverArr[i].resolve(str).addListener(new FutureListener<T>() { // from class: io.grpc.netty.shaded.io.netty.resolver.CompositeNameResolver.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<T> future) throws Exception {
                    if (!future.isSuccess()) {
                        CompositeNameResolver.this.doResolveRec(str, promise, i + 1, future.cause());
                    } else {
                        promise.setSuccess(future.getNow());
                    }
                }
            });
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.resolver.SimpleNameResolver
    protected void doResolveAll(String str, Promise<List<T>> promise) throws Exception {
        doResolveAllRec(str, promise, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doResolveAllRec(final String str, final Promise<List<T>> promise, final int i, Throwable th) throws Exception {
        NameResolver<T>[] nameResolverArr = this.resolvers;
        if (i >= nameResolverArr.length) {
            promise.setFailure(th);
        } else {
            nameResolverArr[i].resolveAll(str).addListener(new FutureListener<List<T>>() { // from class: io.grpc.netty.shaded.io.netty.resolver.CompositeNameResolver.2
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<List<T>> future) throws Exception {
                    if (!future.isSuccess()) {
                        CompositeNameResolver.this.doResolveAllRec(str, promise, i + 1, future.cause());
                    } else {
                        promise.setSuccess(future.getNow());
                    }
                }
            });
        }
    }
}
