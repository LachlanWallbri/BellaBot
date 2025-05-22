package com.google.api.gax.batching;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.core.SettableApiFuture;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For google-cloud-java client use only")
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class BatcherImpl<ElementT, ElementResultT, RequestT, ResponseT> implements Batcher<ElementT, ElementResultT> {
    private static final Logger LOG = Logger.getLogger(BatcherImpl.class.getName());
    private final BatcherStats batcherStats;
    private final BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor;
    private final BatchingSettings batchingSettings;
    private final BatcherReference currentBatcherReference;
    private Batch<ElementT, ElementResultT, RequestT, ResponseT> currentOpenBatch;
    private final Object elementLock;
    private final FlowController flowController;
    private final Object flushLock;
    private volatile boolean isClosed;
    private final AtomicInteger numOfOutstandingBatches;
    private final RequestT prototype;
    private final Future<?> scheduledFuture;
    private final UnaryCallable<RequestT, ResponseT> unaryCallable;

    public BatcherImpl(BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor, UnaryCallable<RequestT, ResponseT> unaryCallable, RequestT requestt, BatchingSettings batchingSettings, ScheduledExecutorService scheduledExecutorService) {
        this(batchingDescriptor, unaryCallable, requestt, batchingSettings, scheduledExecutorService, null);
    }

    public BatcherImpl(BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor, UnaryCallable<RequestT, ResponseT> unaryCallable, RequestT requestt, BatchingSettings batchingSettings, ScheduledExecutorService scheduledExecutorService, @Nullable FlowController flowController) {
        this.numOfOutstandingBatches = new AtomicInteger(0);
        this.flushLock = new Object();
        this.elementLock = new Object();
        this.isClosed = false;
        this.batcherStats = new BatcherStats();
        this.batchingDescriptor = (BatchingDescriptor) Preconditions.checkNotNull(batchingDescriptor, "batching descriptor cannot be null");
        this.unaryCallable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable, "callable cannot be null");
        this.prototype = (RequestT) Preconditions.checkNotNull(requestt, "request prototype cannot be null");
        this.batchingSettings = (BatchingSettings) Preconditions.checkNotNull(batchingSettings, "batching setting cannot be null");
        Preconditions.checkNotNull(scheduledExecutorService, "executor cannot be null");
        flowController = flowController == null ? new FlowController(batchingSettings.getFlowControlSettings()) : flowController;
        if (flowController.getLimitExceededBehavior() != FlowController.LimitExceededBehavior.Ignore) {
            boolean z = true;
            Preconditions.checkArgument(flowController.getMaxElementCountLimit() == null || batchingSettings.getElementCountThreshold() == null || flowController.getMaxElementCountLimit().longValue() >= batchingSettings.getElementCountThreshold().longValue(), "If throttling and batching on element count are enabled, FlowController#maxOutstandingElementCount must be greater or equal to elementCountThreshold");
            if (flowController.getMaxRequestBytesLimit() != null && batchingSettings.getRequestByteThreshold() != null && flowController.getMaxRequestBytesLimit().longValue() < batchingSettings.getRequestByteThreshold().longValue()) {
                z = false;
            }
            Preconditions.checkArgument(z, "If throttling and batching on request bytes are enabled, FlowController#maxOutstandingRequestBytes must be greater or equal to requestByteThreshold");
        }
        this.flowController = flowController;
        this.currentOpenBatch = new Batch<>(requestt, batchingDescriptor, batchingSettings, this.batcherStats);
        if (batchingSettings.getDelayThreshold() != null) {
            long millis = batchingSettings.getDelayThreshold().toMillis();
            this.scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new PushCurrentBatchRunnable(this), millis, millis, TimeUnit.MILLISECONDS);
        } else {
            this.scheduledFuture = Futures.immediateCancelledFuture();
        }
        this.currentBatcherReference = new BatcherReference(this);
    }

    @Override // com.google.api.gax.batching.Batcher
    public ApiFuture<ElementResultT> add(ElementT elementt) {
        Preconditions.checkState(!this.isClosed, "Cannot add elements on a closed batcher");
        try {
            this.flowController.reserve(1L, this.batchingDescriptor.countBytes(elementt));
            SettableApiFuture<ElementResultT> create = SettableApiFuture.create();
            synchronized (this.elementLock) {
                this.currentOpenBatch.add(elementt, create);
            }
            if (this.currentOpenBatch.hasAnyThresholdReached()) {
                sendOutstanding();
            }
            return create;
        } catch (FlowController.FlowControlException e) {
            throw FlowController.FlowControlRuntimeException.fromFlowControlException(e);
        }
    }

    @Override // com.google.api.gax.batching.Batcher
    public void flush() throws InterruptedException {
        sendOutstanding();
        awaitAllOutstandingBatches();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.api.gax.batching.Batcher
    public void sendOutstanding() {
        synchronized (this.elementLock) {
            if (this.currentOpenBatch.isEmpty()) {
                return;
            }
            final Batch<ElementT, ElementResultT, RequestT, ResponseT> batch = this.currentOpenBatch;
            this.currentOpenBatch = new Batch<>(this.prototype, this.batchingDescriptor, this.batchingSettings, this.batcherStats);
            ApiFuture futureCall = this.unaryCallable.futureCall(((Batch) batch).builder.build());
            this.numOfOutstandingBatches.incrementAndGet();
            ApiFutures.addCallback(futureCall, new ApiFutureCallback<ResponseT>() { // from class: com.google.api.gax.batching.BatcherImpl.1
                @Override // com.google.api.core.ApiFutureCallback
                public void onSuccess(ResponseT responset) {
                    try {
                        BatcherImpl.this.flowController.release(batch.elementCounter, batch.byteCounter);
                        batch.onBatchSuccess(responset);
                    } finally {
                        BatcherImpl.this.onBatchCompletion();
                    }
                }

                @Override // com.google.api.core.ApiFutureCallback
                public void onFailure(Throwable th) {
                    try {
                        BatcherImpl.this.flowController.release(batch.elementCounter, batch.byteCounter);
                        batch.onBatchFailure(th);
                    } finally {
                        BatcherImpl.this.onBatchCompletion();
                    }
                }
            }, MoreExecutors.directExecutor());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBatchCompletion() {
        if (this.numOfOutstandingBatches.decrementAndGet() == 0) {
            synchronized (this.flushLock) {
                this.flushLock.notifyAll();
            }
        }
    }

    private void awaitAllOutstandingBatches() throws InterruptedException {
        while (this.numOfOutstandingBatches.get() > 0) {
            synchronized (this.flushLock) {
                if (this.numOfOutstandingBatches.get() == 0) {
                    return;
                } else {
                    this.flushLock.wait();
                }
            }
        }
    }

    @Override // com.google.api.gax.batching.Batcher, java.lang.AutoCloseable
    public void close() throws InterruptedException {
        if (this.isClosed) {
            return;
        }
        flush();
        this.scheduledFuture.cancel(true);
        this.isClosed = true;
        this.currentBatcherReference.closed = true;
        this.currentBatcherReference.clear();
        BatchingException asException = this.batcherStats.asException();
        if (asException != null) {
            throw asException;
        }
    }

    @InternalApi("For google-cloud-java client use only")
    public FlowController getFlowController() {
        return this.flowController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Batch<ElementT, ElementResultT, RequestT, ResponseT> {
        private final BatcherStats batcherStats;
        private final BatchingRequestBuilder<ElementT, RequestT> builder;
        private long byteCounter;
        private final long bytesThreshold;
        private final BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> descriptor;
        private long elementCounter;
        private final long elementThreshold;
        private final List<BatchEntry<ElementT, ElementResultT>> entries;

        private Batch(RequestT requestt, BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> batchingDescriptor, BatchingSettings batchingSettings, BatcherStats batcherStats) {
            this.elementCounter = 0L;
            this.byteCounter = 0L;
            this.descriptor = batchingDescriptor;
            this.builder = batchingDescriptor.newRequestBuilder(requestt);
            this.entries = new ArrayList();
            Long elementCountThreshold = batchingSettings.getElementCountThreshold();
            this.elementThreshold = elementCountThreshold == null ? 0L : elementCountThreshold.longValue();
            Long requestByteThreshold = batchingSettings.getRequestByteThreshold();
            this.bytesThreshold = requestByteThreshold != null ? requestByteThreshold.longValue() : 0L;
            this.batcherStats = batcherStats;
        }

        void add(ElementT elementt, SettableApiFuture<ElementResultT> settableApiFuture) {
            this.builder.add(elementt);
            this.entries.add(BatchEntry.create(elementt, settableApiFuture));
            this.elementCounter++;
            this.byteCounter += this.descriptor.countBytes(elementt);
        }

        void onBatchSuccess(ResponseT responset) {
            try {
                this.descriptor.splitResponse(responset, this.entries);
                this.batcherStats.recordBatchElementsCompletion(this.entries);
            } catch (Exception e) {
                onBatchFailure(e);
            }
        }

        void onBatchFailure(Throwable th) {
            try {
                this.descriptor.splitException(th, this.entries);
            } catch (Exception e) {
                Iterator<BatchEntry<ElementT, ElementResultT>> it = this.entries.iterator();
                while (it.hasNext()) {
                    it.next().getResultFuture().setException(e);
                }
            }
            this.batcherStats.recordBatchFailure(th);
        }

        boolean isEmpty() {
            return this.elementCounter == 0;
        }

        boolean hasAnyThresholdReached() {
            return this.elementCounter >= this.elementThreshold || this.byteCounter >= this.bytesThreshold;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class PushCurrentBatchRunnable<ElementT, ElementResultT, RequestT, ResponseT> implements Runnable {
        private final WeakReference<BatcherImpl<ElementT, ElementResultT, RequestT, ResponseT>> batcherReferent;
        private Future<?> scheduledFuture;

        PushCurrentBatchRunnable(BatcherImpl<ElementT, ElementResultT, RequestT, ResponseT> batcherImpl) {
            this.batcherReferent = new WeakReference<>(batcherImpl);
        }

        @Override // java.lang.Runnable
        public void run() {
            BatcherImpl<ElementT, ElementResultT, RequestT, ResponseT> batcherImpl = this.batcherReferent.get();
            if (batcherImpl == null) {
                this.scheduledFuture.cancel(true);
            } else {
                batcherImpl.sendOutstanding();
            }
        }

        void setScheduledFuture(Future<?> future) {
            this.scheduledFuture = future;
        }

        boolean isCancelled() {
            return this.scheduledFuture.isCancelled();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static final class BatcherReference extends WeakReference<BatcherImpl> {
        private final Reference<RuntimeException> allocationSite;
        private volatile boolean closed;
        private static final ReferenceQueue<BatcherImpl> refQueue = new ReferenceQueue<>();
        private static final ConcurrentMap<BatcherReference, BatcherReference> refs = new ConcurrentHashMap();
        private static final String ALLOCATION_SITE_PROPERTY_NAME = "com.google.api.gax.batching.Batcher.enableAllocationTracking";
        private static final boolean ENABLE_ALLOCATION_TRACKING = Boolean.parseBoolean(System.getProperty(ALLOCATION_SITE_PROPERTY_NAME, "true"));
        private static final RuntimeException missingCallSite = missingCallSite();

        BatcherReference(BatcherImpl batcherImpl) {
            super(batcherImpl, refQueue);
            this.allocationSite = new SoftReference(ENABLE_ALLOCATION_TRACKING ? new RuntimeException("Batcher allocation site") : missingCallSite);
            refs.put(this, this);
            cleanQueue();
        }

        @Override // java.lang.ref.Reference
        public void clear() {
            clearInternal();
            cleanQueue();
        }

        private void clearInternal() {
            super.clear();
            refs.remove(this);
            this.allocationSite.clear();
        }

        static int cleanQueue() {
            int i = 0;
            while (true) {
                BatcherReference batcherReference = (BatcherReference) refQueue.poll();
                if (batcherReference == null) {
                    return i;
                }
                RuntimeException runtimeException = batcherReference.allocationSite.get();
                batcherReference.clearInternal();
                if (!batcherReference.closed) {
                    i++;
                    if (BatcherImpl.LOG.isLoggable(Level.SEVERE)) {
                        BatcherImpl.LOG.log(Level.SEVERE, "Batcher was not closed properly!!! Make sure to call close().", (Throwable) runtimeException);
                    }
                }
            }
        }

        private static RuntimeException missingCallSite() {
            RuntimeException runtimeException = new RuntimeException("Batcher allocation site not recorded.  Set -Dcom.google.api.gax.batching.Batcher.enableAllocationTracking=true to enable it");
            runtimeException.setStackTrace(new StackTraceElement[0]);
            return runtimeException;
        }
    }
}
