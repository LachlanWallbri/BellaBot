package com.google.api.gax.batching;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.core.SettableApiFuture;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class ThresholdBatcher<E> {
    private final BatchMerger<E> batchMerger;
    private Future<?> currentAlarmFuture;
    private E currentOpenBatch;
    private final ScheduledExecutorService executor;
    private final BatchingFlowController<E> flowController;
    private final ReentrantLock lock;
    private final Duration maxDelay;
    private final Runnable pushCurrentBatchRunnable;
    private final ThresholdBatchReceiver<E> receiver;
    private final ArrayList<BatchingThreshold<E>> thresholds;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class ReleaseResourcesFunction<T> implements ApiFunction<T, Void> {
        private final E batch;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.api.core.ApiFunction
        public /* bridge */ /* synthetic */ Void apply(Object obj) {
            return apply2((ReleaseResourcesFunction<T>) obj);
        }

        private ReleaseResourcesFunction(E e) {
            this.batch = e;
        }

        @Override // com.google.api.core.ApiFunction
        /* renamed from: apply, reason: avoid collision after fix types in other method */
        public Void apply2(T t) {
            ThresholdBatcher.this.flowController.release(this.batch);
            return null;
        }
    }

    private ThresholdBatcher(Builder<E> builder) {
        this.pushCurrentBatchRunnable = new Runnable() { // from class: com.google.api.gax.batching.ThresholdBatcher.1
            @Override // java.lang.Runnable
            public void run() {
                ThresholdBatcher.this.pushCurrentBatch();
            }
        };
        this.lock = new ReentrantLock();
        this.thresholds = new ArrayList<>(((Builder) builder).thresholds);
        this.executor = (ScheduledExecutorService) Preconditions.checkNotNull(((Builder) builder).executor);
        this.maxDelay = (Duration) Preconditions.checkNotNull(((Builder) builder).maxDelay);
        this.receiver = (ThresholdBatchReceiver) Preconditions.checkNotNull(((Builder) builder).receiver);
        this.flowController = (BatchingFlowController) Preconditions.checkNotNull(((Builder) builder).flowController);
        this.batchMerger = (BatchMerger) Preconditions.checkNotNull(((Builder) builder).batchMerger);
        resetThresholds();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<E> {
        private BatchMerger<E> batchMerger;
        private ScheduledExecutorService executor;
        private BatchingFlowController<E> flowController;
        private Duration maxDelay;
        private ThresholdBatchReceiver<E> receiver;
        private Collection<BatchingThreshold<E>> thresholds;

        private Builder() {
        }

        public Builder<E> setExecutor(ScheduledExecutorService scheduledExecutorService) {
            this.executor = scheduledExecutorService;
            return this;
        }

        public Builder<E> setMaxDelay(Duration duration) {
            this.maxDelay = duration;
            return this;
        }

        public Builder<E> setThresholds(Collection<BatchingThreshold<E>> collection) {
            this.thresholds = collection;
            return this;
        }

        public Builder<E> setReceiver(ThresholdBatchReceiver<E> thresholdBatchReceiver) {
            this.receiver = thresholdBatchReceiver;
            return this;
        }

        public Builder<E> setFlowController(BatchingFlowController<E> batchingFlowController) {
            this.flowController = batchingFlowController;
            return this;
        }

        public Builder<E> setBatchMerger(BatchMerger<E> batchMerger) {
            this.batchMerger = batchMerger;
            return this;
        }

        public ThresholdBatcher<E> build() {
            return new ThresholdBatcher<>(this);
        }
    }

    public static <E> Builder<E> newBuilder() {
        return new Builder<>();
    }

    public void add(E e) throws FlowController.FlowControlException {
        this.flowController.reserve(e);
        this.lock.lock();
        try {
            this.receiver.validateBatch(e);
            boolean isAnyThresholdReached = isAnyThresholdReached(e);
            if (this.currentOpenBatch == null) {
                this.currentOpenBatch = e;
                if (!isAnyThresholdReached) {
                    this.currentAlarmFuture = this.executor.schedule(this.pushCurrentBatchRunnable, this.maxDelay.toMillis(), TimeUnit.MILLISECONDS);
                }
            } else {
                this.batchMerger.merge(this.currentOpenBatch, e);
            }
            if (isAnyThresholdReached) {
                pushCurrentBatch();
            }
        } finally {
            this.lock.unlock();
        }
    }

    boolean isEmpty() {
        this.lock.lock();
        try {
            return this.currentOpenBatch == null;
        } finally {
            this.lock.unlock();
        }
    }

    public ApiFuture<Void> pushCurrentBatch() {
        final E removeBatch = removeBatch();
        if (removeBatch == null) {
            return ApiFutures.immediateFuture(null);
        }
        final SettableApiFuture create = SettableApiFuture.create();
        ApiFutures.addCallback(this.receiver.processBatch(removeBatch), new ApiFutureCallback<Object>() { // from class: com.google.api.gax.batching.ThresholdBatcher.2
            @Override // com.google.api.core.ApiFutureCallback
            public void onSuccess(Object obj) {
                ThresholdBatcher.this.flowController.release(removeBatch);
                create.set(null);
            }

            @Override // com.google.api.core.ApiFutureCallback
            public void onFailure(Throwable th) {
                ThresholdBatcher.this.flowController.release(removeBatch);
                create.setException(th);
            }
        }, MoreExecutors.directExecutor());
        return create;
    }

    private E removeBatch() {
        this.lock.lock();
        try {
            E e = this.currentOpenBatch;
            this.currentOpenBatch = null;
            if (this.currentAlarmFuture != null) {
                this.currentAlarmFuture.cancel(false);
                this.currentAlarmFuture = null;
            }
            resetThresholds();
            return e;
        } finally {
            this.lock.unlock();
        }
    }

    private boolean isAnyThresholdReached(E e) {
        Iterator<BatchingThreshold<E>> it = this.thresholds.iterator();
        while (it.hasNext()) {
            BatchingThreshold<E> next = it.next();
            next.accumulate(e);
            if (next.isThresholdReached()) {
                return true;
            }
        }
        return false;
    }

    private void resetThresholds() {
        for (int i = 0; i < this.thresholds.size(); i++) {
            ArrayList<BatchingThreshold<E>> arrayList = this.thresholds;
            arrayList.set(i, arrayList.get(i).copyWithZeroedValue());
        }
    }
}
