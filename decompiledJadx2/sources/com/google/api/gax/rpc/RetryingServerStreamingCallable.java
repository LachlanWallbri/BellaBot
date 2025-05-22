package com.google.api.gax.rpc;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.retrying.ScheduledRetryingExecutor;
import com.google.api.gax.retrying.ServerStreamingAttemptException;
import com.google.api.gax.retrying.StreamResumptionStrategy;
import com.google.common.util.concurrent.MoreExecutors;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class RetryingServerStreamingCallable<RequestT, ResponseT> extends ServerStreamingCallable<RequestT, ResponseT> {
    private final ScheduledRetryingExecutor<Void> executor;
    private final ServerStreamingCallable<RequestT, ResponseT> innerCallable;
    private final StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategyPrototype;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RetryingServerStreamingCallable(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, ScheduledRetryingExecutor<Void> scheduledRetryingExecutor, StreamResumptionStrategy<RequestT, ResponseT> streamResumptionStrategy) {
        this.innerCallable = serverStreamingCallable;
        this.executor = scheduledRetryingExecutor;
        this.resumptionStrategyPrototype = streamResumptionStrategy;
    }

    @Override // com.google.api.gax.rpc.ServerStreamingCallable
    public void call(RequestT requestt, final ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext) {
        ServerStreamingAttemptCallable serverStreamingAttemptCallable = new ServerStreamingAttemptCallable(this.innerCallable, this.resumptionStrategyPrototype.createNew(), requestt, apiCallContext, responseObserver);
        RetryingFuture<Void> createFuture = this.executor.createFuture(serverStreamingAttemptCallable, apiCallContext);
        serverStreamingAttemptCallable.setExternalFuture(createFuture);
        serverStreamingAttemptCallable.start();
        ApiFutures.addCallback(createFuture, new ApiFutureCallback<Void>() { // from class: com.google.api.gax.rpc.RetryingServerStreamingCallable.1
            @Override // com.google.api.core.ApiFutureCallback
            public void onFailure(Throwable th) {
                if (th instanceof ServerStreamingAttemptException) {
                    th = th.getCause();
                }
                responseObserver.onError(th);
            }

            @Override // com.google.api.core.ApiFutureCallback
            public void onSuccess(Void r1) {
                responseObserver.onComplete();
            }
        }, MoreExecutors.directExecutor());
    }
}
