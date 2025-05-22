package io.grpc.netty.shaded.io.netty.util.concurrent;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PromiseCombiner {
    private Promise<Void> aggregatePromise;
    private Throwable cause;
    private int doneCount;
    private final EventExecutor executor;
    private int expectedCount;
    private final GenericFutureListener<Future<?>> listener;

    static /* synthetic */ int access$204(PromiseCombiner promiseCombiner) {
        int i = promiseCombiner.doneCount + 1;
        promiseCombiner.doneCount = i;
        return i;
    }

    @Deprecated
    public PromiseCombiner() {
        this(ImmediateEventExecutor.INSTANCE);
    }

    public PromiseCombiner(EventExecutor eventExecutor) {
        this.listener = new GenericFutureListener<Future<?>>() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.PromiseCombiner.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(final Future<?> future) {
                if (!PromiseCombiner.this.executor.inEventLoop()) {
                    PromiseCombiner.this.executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.PromiseCombiner.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            operationComplete0(future);
                        }
                    });
                } else {
                    operationComplete0(future);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void operationComplete0(Future<?> future) {
                PromiseCombiner.access$204(PromiseCombiner.this);
                if (!future.isSuccess() && PromiseCombiner.this.cause == null) {
                    PromiseCombiner.this.cause = future.cause();
                }
                if (PromiseCombiner.this.doneCount != PromiseCombiner.this.expectedCount || PromiseCombiner.this.aggregatePromise == null) {
                    return;
                }
                PromiseCombiner.this.tryPromise();
            }
        };
        this.executor = (EventExecutor) ObjectUtil.checkNotNull(eventExecutor, "executor");
    }

    @Deprecated
    public void add(Promise promise) {
        add((Future) promise);
    }

    public void add(Future future) {
        checkAddAllowed();
        checkInEventLoop();
        this.expectedCount++;
        future.addListener(this.listener);
    }

    @Deprecated
    public void addAll(Promise... promiseArr) {
        addAll((Future[]) promiseArr);
    }

    public void addAll(Future... futureArr) {
        for (Future future : futureArr) {
            add(future);
        }
    }

    public void finish(Promise<Void> promise) {
        ObjectUtil.checkNotNull(promise, "aggregatePromise");
        checkInEventLoop();
        if (this.aggregatePromise != null) {
            throw new IllegalStateException("Already finished");
        }
        this.aggregatePromise = promise;
        if (this.doneCount == this.expectedCount) {
            tryPromise();
        }
    }

    private void checkInEventLoop() {
        if (!this.executor.inEventLoop()) {
            throw new IllegalStateException("Must be called from EventExecutor thread");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean tryPromise() {
        Throwable th = this.cause;
        return th == null ? this.aggregatePromise.trySuccess(null) : this.aggregatePromise.tryFailure(th);
    }

    private void checkAddAllowed() {
        if (this.aggregatePromise != null) {
            throw new IllegalStateException("Adding promises is not allowed after finished adding");
        }
    }
}
