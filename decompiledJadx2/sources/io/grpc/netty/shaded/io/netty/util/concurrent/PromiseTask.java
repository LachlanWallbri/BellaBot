package io.grpc.netty.shaded.io.netty.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class PromiseTask<V> extends DefaultPromise<V> implements RunnableFuture<V> {
    private Object task;
    private static final Runnable COMPLETED = new SentinelRunnable("COMPLETED");
    private static final Runnable CANCELLED = new SentinelRunnable("CANCELLED");
    private static final Runnable FAILED = new SentinelRunnable("FAILED");

    public final boolean equals(Object obj) {
        return this == obj;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public final boolean tryFailure(Throwable th) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public final boolean trySuccess(V v) {
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class RunnableAdapter<T> implements Callable<T> {
        final T result;
        final Runnable task;

        RunnableAdapter(Runnable runnable, T t) {
            this.task = runnable;
            this.result = t;
        }

        @Override // java.util.concurrent.Callable
        public T call() {
            this.task.run();
            return this.result;
        }

        public String toString() {
            return "Callable(task: " + this.task + ", result: " + this.result + ')';
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class SentinelRunnable implements Runnable {
        private final String name;

        @Override // java.lang.Runnable
        public void run() {
        }

        SentinelRunnable(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PromiseTask(EventExecutor eventExecutor, Runnable runnable, V v) {
        super(eventExecutor);
        this.task = v == null ? runnable : new RunnableAdapter(runnable, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PromiseTask(EventExecutor eventExecutor, Runnable runnable) {
        super(eventExecutor);
        this.task = runnable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PromiseTask(EventExecutor eventExecutor, Callable<V> callable) {
        super(eventExecutor);
        this.task = callable;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final V runTask() throws Exception {
        Object obj = this.task;
        if (obj instanceof Callable) {
            return (V) ((Callable) obj).call();
        }
        ((Runnable) obj).run();
        return null;
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        try {
            if (setUncancellableInternal()) {
                setSuccessInternal(runTask());
            }
        } catch (Throwable th) {
            setFailureInternal(th);
        }
    }

    private boolean clearTaskAfterCompletion(boolean z, Runnable runnable) {
        if (z) {
            this.task = runnable;
        }
        return z;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public final Promise<V> setFailure(Throwable th) {
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Promise<V> setFailureInternal(Throwable th) {
        super.setFailure(th);
        clearTaskAfterCompletion(true, FAILED);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean tryFailureInternal(Throwable th) {
        return clearTaskAfterCompletion(super.tryFailure(th), FAILED);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public final Promise<V> setSuccess(V v) {
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Promise<V> setSuccessInternal(V v) {
        super.setSuccess(v);
        clearTaskAfterCompletion(true, COMPLETED);
        return this;
    }

    protected final boolean trySuccessInternal(V v) {
        return clearTaskAfterCompletion(super.trySuccess(v), COMPLETED);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public final boolean setUncancellable() {
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean setUncancellableInternal() {
        return super.setUncancellable();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return clearTaskAfterCompletion(super.cancel(z), CANCELLED);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
    public StringBuilder toStringBuilder() {
        StringBuilder stringBuilder = super.toStringBuilder();
        stringBuilder.setCharAt(stringBuilder.length() - 1, ',');
        stringBuilder.append(" task: ");
        stringBuilder.append(this.task);
        stringBuilder.append(')');
        return stringBuilder;
    }
}
