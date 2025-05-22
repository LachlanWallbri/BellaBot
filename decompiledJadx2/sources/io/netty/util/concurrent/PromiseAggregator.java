package io.netty.util.concurrent;

import io.netty.util.concurrent.Future;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
@Deprecated
/* loaded from: classes.dex */
public class PromiseAggregator<V, F extends Future<V>> implements GenericFutureListener<F> {
    private final Promise<?> aggregatePromise;
    private final boolean failPending;
    private Set<Promise<V>> pendingPromises;

    public PromiseAggregator(Promise<Void> promise, boolean z) {
        if (promise == null) {
            throw new NullPointerException("aggregatePromise");
        }
        this.aggregatePromise = promise;
        this.failPending = z;
    }

    public PromiseAggregator(Promise<Void> promise) {
        this(promise, true);
    }

    @SafeVarargs
    public final PromiseAggregator<V, F> add(Promise<V>... promiseArr) {
        if (promiseArr == null) {
            throw new NullPointerException("promises");
        }
        if (promiseArr.length == 0) {
            return this;
        }
        synchronized (this) {
            if (this.pendingPromises == null) {
                this.pendingPromises = new LinkedHashSet(promiseArr.length > 1 ? promiseArr.length : 2);
            }
            for (Promise<V> promise : promiseArr) {
                if (promise != null) {
                    this.pendingPromises.add(promise);
                    promise.addListener((GenericFutureListener) this);
                }
            }
        }
        return this;
    }

    @Override // io.netty.util.concurrent.GenericFutureListener
    public synchronized void operationComplete(F f) throws Exception {
        if (this.pendingPromises == null) {
            this.aggregatePromise.setSuccess(null);
        } else {
            this.pendingPromises.remove(f);
            if (!f.isSuccess()) {
                Throwable cause = f.cause();
                this.aggregatePromise.setFailure(cause);
                if (this.failPending) {
                    Iterator<Promise<V>> it = this.pendingPromises.iterator();
                    while (it.hasNext()) {
                        it.next().setFailure(cause);
                    }
                }
            } else if (this.pendingPromises.isEmpty()) {
                this.aggregatePromise.setSuccess(null);
            }
        }
    }
}
