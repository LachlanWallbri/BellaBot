package com.google.api.core;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class AbstractApiFuture<V> implements ApiFuture<V> {
    private final AbstractApiFuture<V>.InternalSettableFuture impl = new InternalSettableFuture();

    protected void interruptTask() {
    }

    @Override // com.google.api.core.ApiFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.impl.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.impl.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        return this.impl.get();
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.impl.get(j, timeUnit);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.impl.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.impl.isDone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean set(V v) {
        return this.impl.set(v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setException(Throwable th) {
        return this.impl.setException(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenableFuture<V> getInternalListenableFuture() {
        return this.impl;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class InternalSettableFuture extends AbstractFuture<V> {
        private InternalSettableFuture() {
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        protected boolean set(@Nullable V v) {
            return super.set(v);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        protected boolean setException(Throwable th) {
            return super.setException(th);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        protected void interruptTask() {
            AbstractApiFuture.this.interruptTask();
        }
    }
}
