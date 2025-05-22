package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.batching.BatchingFlowController;
import com.google.api.gax.batching.BatchingSettings;
import com.google.api.gax.batching.BatchingThreshold;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.batching.NumericThreshold;
import com.google.api.gax.batching.PartitionKey;
import com.google.api.gax.batching.ThresholdBatcher;
import com.google.api.gax.rpc.Batch;
import com.google.common.collect.ImmutableList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public final class BatcherFactory<RequestT, ResponseT> {
    private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
    private final BatchingSettings batchingSettings;
    private final ScheduledExecutorService executor;
    private final FlowController flowController;
    private final Map<PartitionKey, ThresholdBatcher<Batch<RequestT, ResponseT>>> batchers = new ConcurrentHashMap();
    private final Object lock = new Object();

    public BatcherFactory(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor, BatchingSettings batchingSettings, ScheduledExecutorService scheduledExecutorService, FlowController flowController) {
        this.batchingDescriptor = batchingDescriptor;
        this.batchingSettings = batchingSettings;
        this.executor = scheduledExecutorService;
        this.flowController = flowController;
    }

    public ThresholdBatcher<Batch<RequestT, ResponseT>> getPushingBatcher(PartitionKey partitionKey) {
        ThresholdBatcher<Batch<RequestT, ResponseT>> thresholdBatcher = this.batchers.get(partitionKey);
        if (thresholdBatcher == null) {
            synchronized (this.lock) {
                thresholdBatcher = this.batchers.get(partitionKey);
                if (thresholdBatcher == null) {
                    thresholdBatcher = createBatcher(partitionKey);
                    this.batchers.put(partitionKey, thresholdBatcher);
                }
            }
        }
        return thresholdBatcher;
    }

    @InternalApi
    public BatchingSettings getBatchingSettings() {
        return this.batchingSettings;
    }

    private ThresholdBatcher<Batch<RequestT, ResponseT>> createBatcher(PartitionKey partitionKey) {
        BatchExecutor batchExecutor = new BatchExecutor(this.batchingDescriptor, partitionKey);
        return ThresholdBatcher.newBuilder().setThresholds(getThresholds(this.batchingSettings)).setExecutor(this.executor).setMaxDelay(this.batchingSettings.getDelayThreshold()).setReceiver(batchExecutor).setFlowController(new BatchingFlowController(this.flowController, new Batch.BatchElementCounter(this.batchingDescriptor), new Batch.BatchByteCounter())).setBatchMerger(new Batch.BatchMergerImpl()).build();
    }

    private ImmutableList<BatchingThreshold<Batch<RequestT, ResponseT>>> getThresholds(BatchingSettings batchingSettings) {
        ImmutableList.Builder builder = ImmutableList.builder();
        if (batchingSettings.getElementCountThreshold() != null) {
            builder.add((ImmutableList.Builder) new NumericThreshold(batchingSettings.getElementCountThreshold().longValue(), new Batch.BatchElementCounter(this.batchingDescriptor)));
        }
        if (batchingSettings.getRequestByteThreshold() != null) {
            builder.add((ImmutableList.Builder) new NumericThreshold(batchingSettings.getRequestByteThreshold().longValue(), new Batch.BatchByteCounter()));
        }
        return builder.build();
    }
}
