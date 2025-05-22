package com.google.api.core;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class ApiFutures {
    private ApiFutures() {
    }

    @Deprecated
    public static <V> void addCallback(ApiFuture<V> apiFuture, ApiFutureCallback<? super V> apiFutureCallback) {
        addCallback(apiFuture, apiFutureCallback, MoreExecutors.directExecutor());
    }

    public static <V> void addCallback(ApiFuture<V> apiFuture, final ApiFutureCallback<? super V> apiFutureCallback, Executor executor) {
        Futures.addCallback(listenableFutureForApiFuture(apiFuture), new FutureCallback<V>() { // from class: com.google.api.core.ApiFutures.1
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                ApiFutureCallback.this.onFailure(th);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(V v) {
                ApiFutureCallback.this.onSuccess(v);
            }
        }, executor);
    }

    @Deprecated
    public static <V, X extends Throwable> ApiFuture<V> catching(ApiFuture<? extends V> apiFuture, Class<X> cls, ApiFunction<? super X, ? extends V> apiFunction) {
        return catching(apiFuture, cls, apiFunction, MoreExecutors.directExecutor());
    }

    public static <V, X extends Throwable> ApiFuture<V> catching(ApiFuture<? extends V> apiFuture, Class<X> cls, ApiFunction<? super X, ? extends V> apiFunction, Executor executor) {
        return new ListenableFutureToApiFuture(Futures.catching(listenableFutureForApiFuture(apiFuture), cls, new ApiFunctionToGuavaFunction(apiFunction), executor));
    }

    @BetaApi
    public static <V, X extends Throwable> ApiFuture<V> catchingAsync(ApiFuture<V> apiFuture, Class<X> cls, final ApiAsyncFunction<? super X, V> apiAsyncFunction, Executor executor) {
        return new ListenableFutureToApiFuture(Futures.catchingAsync(listenableFutureForApiFuture(apiFuture), cls, new AsyncFunction<X, V>() { // from class: com.google.api.core.ApiFutures.2
            /* JADX WARN: Incorrect types in method signature: (TX;)Lcom/google/common/util/concurrent/ListenableFuture<TV;>; */
            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture apply(Throwable th) throws Exception {
                return ApiFutures.listenableFutureForApiFuture(ApiAsyncFunction.this.apply(th));
            }
        }, executor));
    }

    public static <V> ApiFuture<V> immediateFuture(V v) {
        return new ListenableFutureToApiFuture(Futures.immediateFuture(v));
    }

    public static <V> ApiFuture<V> immediateFailedFuture(Throwable th) {
        return new ListenableFutureToApiFuture(Futures.immediateFailedFuture(th));
    }

    public static <V> ApiFuture<V> immediateCancelledFuture() {
        return new ListenableFutureToApiFuture(Futures.immediateCancelledFuture());
    }

    @Deprecated
    public static <V, X> ApiFuture<X> transform(ApiFuture<? extends V> apiFuture, ApiFunction<? super V, ? extends X> apiFunction) {
        return transform(apiFuture, apiFunction, MoreExecutors.directExecutor());
    }

    public static <V, X> ApiFuture<X> transform(ApiFuture<? extends V> apiFuture, ApiFunction<? super V, ? extends X> apiFunction, Executor executor) {
        return new ListenableFutureToApiFuture(Futures.transform(listenableFutureForApiFuture(apiFuture), new ApiFunctionToGuavaFunction(apiFunction), executor));
    }

    public static <V> ApiFuture<List<V>> allAsList(Iterable<? extends ApiFuture<? extends V>> iterable) {
        return new ListenableFutureToApiFuture(Futures.allAsList(Iterables.transform(iterable, new Function<ApiFuture<? extends V>, ListenableFuture<? extends V>>() { // from class: com.google.api.core.ApiFutures.3
            @Override // com.google.common.base.Function, java.util.function.Function
            public ListenableFuture<? extends V> apply(ApiFuture<? extends V> apiFuture) {
                return ApiFutures.listenableFutureForApiFuture(apiFuture);
            }
        })));
    }

    @BetaApi
    public static <V> ApiFuture<List<V>> successfulAsList(Iterable<? extends ApiFuture<? extends V>> iterable) {
        return new ListenableFutureToApiFuture(Futures.successfulAsList(Iterables.transform(iterable, new Function<ApiFuture<? extends V>, ListenableFuture<? extends V>>() { // from class: com.google.api.core.ApiFutures.4
            @Override // com.google.common.base.Function, java.util.function.Function
            public ListenableFuture<? extends V> apply(ApiFuture<? extends V> apiFuture) {
                return ApiFutures.listenableFutureForApiFuture(apiFuture);
            }
        })));
    }

    @Deprecated
    public static <I, O> ApiFuture<O> transformAsync(ApiFuture<I> apiFuture, ApiAsyncFunction<I, O> apiAsyncFunction) {
        return transformAsync(apiFuture, apiAsyncFunction, MoreExecutors.directExecutor());
    }

    public static <I, O> ApiFuture<O> transformAsync(ApiFuture<I> apiFuture, final ApiAsyncFunction<I, O> apiAsyncFunction, Executor executor) {
        return new ListenableFutureToApiFuture(Futures.transformAsync(listenableFutureForApiFuture(apiFuture), new AsyncFunction<I, O>() { // from class: com.google.api.core.ApiFutures.5
            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture<O> apply(I i) throws Exception {
                return ApiFutures.listenableFutureForApiFuture(ApiAsyncFunction.this.apply(i));
            }
        }, executor));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <V> ListenableFuture<V> listenableFutureForApiFuture(ApiFuture<V> apiFuture) {
        if (apiFuture instanceof AbstractApiFuture) {
            return ((AbstractApiFuture) apiFuture).getInternalListenableFuture();
        }
        return new ApiFutureToListenableFuture(apiFuture);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ApiFunctionToGuavaFunction<X, V> implements Function<X, V> {

        /* renamed from: f */
        private ApiFunction<? super X, ? extends V> f1342f;

        public ApiFunctionToGuavaFunction(ApiFunction<? super X, ? extends V> apiFunction) {
            this.f1342f = apiFunction;
        }

        @Override // com.google.common.base.Function, java.util.function.Function
        @Nullable
        public V apply(@Nullable X x) {
            return this.f1342f.apply(x);
        }
    }
}
