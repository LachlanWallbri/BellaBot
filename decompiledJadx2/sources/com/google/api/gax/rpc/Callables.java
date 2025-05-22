package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationResponsePollAlgorithm;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.ExponentialRetryAlgorithm;
import com.google.api.gax.retrying.ResultRetryAlgorithmWithContext;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.ScheduledRetryingExecutor;
import com.google.api.gax.retrying.StreamingRetryAlgorithm;
import com.google.api.gax.retrying.TimedRetryAlgorithmWithContext;
import com.google.api.gax.rpc.StatusCode;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class Callables {
    private Callables() {
    }

    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> retrying(UnaryCallable<RequestT, ResponseT> unaryCallable, UnaryCallSettings<?, ?> unaryCallSettings, ClientContext clientContext) {
        if (areRetriesDisabled(unaryCallSettings.getRetryableCodes(), unaryCallSettings.getRetrySettings())) {
            unaryCallSettings = unaryCallSettings.toBuilder().setSimpleTimeoutNoRetries(unaryCallSettings.getRetrySettings().getTotalTimeout()).build();
        }
        return new RetryingCallable(clientContext.getDefaultCallContext(), unaryCallable, new ScheduledRetryingExecutor(new RetryAlgorithm((ResultRetryAlgorithmWithContext) new ApiResultRetryAlgorithm(), (TimedRetryAlgorithmWithContext) new ExponentialRetryAlgorithm(unaryCallSettings.getRetrySettings(), clientContext.getClock())), clientContext.getExecutor()));
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> retrying(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, ServerStreamingCallSettings<RequestT, ResponseT> serverStreamingCallSettings, ClientContext clientContext) {
        if (areRetriesDisabled(serverStreamingCallSettings.getRetryableCodes(), serverStreamingCallSettings.getRetrySettings())) {
            serverStreamingCallSettings = serverStreamingCallSettings.toBuilder().setSimpleTimeoutNoRetries(serverStreamingCallSettings.getRetrySettings().getTotalTimeout()).build();
        }
        return new RetryingServerStreamingCallable(serverStreamingCallable, new ScheduledRetryingExecutor(new StreamingRetryAlgorithm((ResultRetryAlgorithmWithContext) new ApiResultRetryAlgorithm(), (TimedRetryAlgorithmWithContext) new ExponentialRetryAlgorithm(serverStreamingCallSettings.getRetrySettings(), clientContext.getClock())), clientContext.getExecutor()), serverStreamingCallSettings.getResumptionStrategy());
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> watched(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, ServerStreamingCallSettings<RequestT, ResponseT> serverStreamingCallSettings, ClientContext clientContext) {
        return new WatchdogServerStreamingCallable(serverStreamingCallable, clientContext.getStreamWatchdog()).withDefaultCallContext(clientContext.getDefaultCallContext().withStreamIdleTimeout(serverStreamingCallSettings.getIdleTimeout()));
    }

    @BetaApi("The surface for batching is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> batching(UnaryCallable<RequestT, ResponseT> unaryCallable, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext clientContext) {
        return batchingImpl(unaryCallable, batchingCallSettings, clientContext).unaryCallable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class BatchingCreateResult<RequestT, ResponseT> {
        private final BatcherFactory<RequestT, ResponseT> batcherFactory;
        private final UnaryCallable<RequestT, ResponseT> unaryCallable;

        private BatchingCreateResult(BatcherFactory<RequestT, ResponseT> batcherFactory, UnaryCallable<RequestT, ResponseT> unaryCallable) {
            this.batcherFactory = batcherFactory;
            this.unaryCallable = unaryCallable;
        }

        public BatcherFactory<RequestT, ResponseT> getBatcherFactory() {
            return this.batcherFactory;
        }

        public UnaryCallable<RequestT, ResponseT> getUnaryCallable() {
            return this.unaryCallable;
        }
    }

    static <RequestT, ResponseT> BatchingCreateResult<RequestT, ResponseT> batchingImpl(UnaryCallable<RequestT, ResponseT> unaryCallable, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext clientContext) {
        BatcherFactory batcherFactory = new BatcherFactory(batchingCallSettings.getBatchingDescriptor(), batchingCallSettings.getBatchingSettings(), clientContext.getExecutor(), batchingCallSettings.getFlowController());
        return new BatchingCreateResult<>(batcherFactory, new BatchingCallable(unaryCallable, batchingCallSettings.getBatchingDescriptor(), batcherFactory));
    }

    public static <RequestT, ResponseT, PagedListResponseT> UnaryCallable<RequestT, PagedListResponseT> paged(UnaryCallable<RequestT, ResponseT> unaryCallable, PagedCallSettings<RequestT, ResponseT, PagedListResponseT> pagedCallSettings) {
        return new PagedCallable(unaryCallable, pagedCallSettings.getPagedListResponseFactory());
    }

    public static <RequestT, ResponseT, MetadataT> OperationCallable<RequestT, ResponseT, MetadataT> longRunningOperation(UnaryCallable<RequestT, OperationSnapshot> unaryCallable, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, LongRunningClient longRunningClient) {
        return longRunningOperationImpl(unaryCallable, operationCallSettings, clientContext, longRunningClient);
    }

    static <RequestT, ResponseT, MetadataT> OperationCallableImpl<RequestT, ResponseT, MetadataT> longRunningOperationImpl(UnaryCallable<RequestT, OperationSnapshot> unaryCallable, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, LongRunningClient longRunningClient) {
        return new OperationCallableImpl<>(unaryCallable, new ScheduledRetryingExecutor(new RetryAlgorithm(new OperationResponsePollAlgorithm(), operationCallSettings.getPollingAlgorithm()), clientContext.getExecutor()), longRunningClient, operationCallSettings);
    }

    private static boolean areRetriesDisabled(Collection<StatusCode.Code> collection, RetrySettings retrySettings) {
        if (retrySettings.getMaxAttempts() == 1 || collection.isEmpty()) {
            return true;
        }
        return retrySettings.getMaxAttempts() == 0 && retrySettings.getTotalTimeout().isZero();
    }
}
