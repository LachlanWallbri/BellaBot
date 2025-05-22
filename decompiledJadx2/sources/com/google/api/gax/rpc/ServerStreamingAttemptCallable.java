package com.google.api.gax.rpc;

import com.google.api.core.SettableApiFuture;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.retrying.ServerStreamingAttemptException;
import com.google.api.gax.retrying.StreamResumptionStrategy;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class ServerStreamingAttemptCallable<RequestT, ResponseT> implements Callable<Void> {
    private Throwable cancellationCause;
    private ApiCallContext context;
    private final RequestT initialRequest;
    private SettableApiFuture<Void> innerAttemptFuture;
    private final ServerStreamingCallable<RequestT, ResponseT> innerCallable;
    private StreamController innerController;
    private boolean isStarted;
    private int numAttempts;
    private final ResponseObserver<ResponseT> outerObserver;
    private RetryingFuture<Void> outerRetryingFuture;
    private int pendingRequests;
    private final StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy;
    private boolean seenSuccessSinceLastError;
    private final Object lock = new Object();
    private boolean autoFlowControl = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerStreamingAttemptCallable(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, StreamResumptionStrategy<RequestT, ResponseT> streamResumptionStrategy, RequestT requestt, ApiCallContext apiCallContext, ResponseObserver<ResponseT> responseObserver) {
        this.innerCallable = serverStreamingCallable;
        this.resumptionStrategy = streamResumptionStrategy;
        this.initialRequest = requestt;
        this.context = apiCallContext;
        this.outerObserver = responseObserver;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExternalFuture(RetryingFuture<Void> retryingFuture) {
        Preconditions.checkState(!this.isStarted, "Can't change the RetryingFuture once the call has start");
        Preconditions.checkNotNull(retryingFuture, "RetryingFuture can't be null");
        this.outerRetryingFuture = retryingFuture;
    }

    public void start() {
        ApiCallContext apiCallContext;
        Preconditions.checkState(!this.isStarted, "Already started");
        this.outerObserver.onStart(new StreamController() { // from class: com.google.api.gax.rpc.ServerStreamingAttemptCallable.1
            @Override // com.google.api.gax.rpc.StreamController
            public void disableAutoInboundFlowControl() {
                Preconditions.checkState(!ServerStreamingAttemptCallable.this.isStarted, "Can't disable auto flow control once the stream is started");
                ServerStreamingAttemptCallable.this.autoFlowControl = false;
            }

            @Override // com.google.api.gax.rpc.StreamController
            public void request(int i) {
                ServerStreamingAttemptCallable.this.onRequest(i);
            }

            @Override // com.google.api.gax.rpc.StreamController
            public void cancel() {
                ServerStreamingAttemptCallable.this.onCancel();
            }
        });
        if (this.autoFlowControl) {
            synchronized (this.lock) {
                this.pendingRequests = Integer.MAX_VALUE;
            }
        }
        this.isStarted = true;
        Duration totalTimeout = this.outerRetryingFuture.getAttemptSettings().getGlobalSettings().getTotalTimeout();
        if (totalTimeout != null && (apiCallContext = this.context) != null && apiCallContext.getTimeout() == null) {
            this.context = this.context.withTimeout(totalTimeout);
        }
        call();
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        Preconditions.checkState(this.isStarted, "Must be started first");
        int i = this.numAttempts + 1;
        this.numAttempts = i;
        RequestT resumeRequest = i == 1 ? this.initialRequest : this.resumptionStrategy.getResumeRequest(this.initialRequest);
        Preconditions.checkState(resumeRequest != null, "ResumptionStrategy returned a null request.");
        this.innerAttemptFuture = SettableApiFuture.create();
        this.seenSuccessSinceLastError = false;
        ApiCallContext apiCallContext = this.context;
        if (!this.outerRetryingFuture.getAttemptSettings().getRpcTimeout().isZero() && apiCallContext.getStreamWaitTimeout() == null) {
            apiCallContext = apiCallContext.withStreamWaitTimeout(this.outerRetryingFuture.getAttemptSettings().getRpcTimeout());
        }
        apiCallContext.getTracer().attemptStarted(this.outerRetryingFuture.getAttemptSettings().getOverallAttemptCount());
        this.innerCallable.call(resumeRequest, new StateCheckingResponseObserver<ResponseT>() { // from class: com.google.api.gax.rpc.ServerStreamingAttemptCallable.2
            @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
            public void onStartImpl(StreamController streamController) {
                ServerStreamingAttemptCallable.this.onAttemptStart(streamController);
            }

            @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
            public void onResponseImpl(ResponseT responset) {
                ServerStreamingAttemptCallable.this.onAttemptResponse(responset);
            }

            @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
            public void onErrorImpl(Throwable th) {
                ServerStreamingAttemptCallable.this.onAttemptError(th);
            }

            @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
            public void onCompleteImpl() {
                ServerStreamingAttemptCallable.this.onAttemptComplete();
            }
        }, apiCallContext);
        this.outerRetryingFuture.setAttemptFuture(this.innerAttemptFuture);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAttemptStart(StreamController streamController) {
        Throwable th;
        int i;
        if (!this.autoFlowControl) {
            streamController.disableAutoInboundFlowControl();
        }
        synchronized (this.lock) {
            this.innerController = streamController;
            th = this.cancellationCause;
            i = this.autoFlowControl ? 0 : this.pendingRequests;
        }
        if (th != null) {
            streamController.cancel();
        } else if (i > 0) {
            streamController.request(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCancel() {
        synchronized (this.lock) {
            if (this.cancellationCause != null) {
                return;
            }
            this.cancellationCause = new ServerStreamingAttemptException(new CancellationException("User cancelled stream"), this.resumptionStrategy.canResume(), this.seenSuccessSinceLastError);
            StreamController streamController = this.innerController;
            if (streamController != null) {
                streamController.cancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequest(int i) {
        int min;
        StreamController streamController;
        Preconditions.checkState(!this.autoFlowControl, "Automatic flow control is enabled");
        Preconditions.checkArgument(i > 0, "Count must be > 0");
        synchronized (this.lock) {
            min = Math.min(Integer.MAX_VALUE - this.pendingRequests, i);
            this.pendingRequests += min;
            streamController = this.innerController;
        }
        if (streamController != null) {
            streamController.request(min);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAttemptResponse(ResponseT responset) {
        if (!this.autoFlowControl) {
            synchronized (this.lock) {
                this.pendingRequests--;
            }
        }
        this.seenSuccessSinceLastError = true;
        this.outerObserver.onResponse(this.resumptionStrategy.processResponse(responset));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAttemptError(Throwable th) {
        Throwable th2;
        synchronized (this.lock) {
            th2 = this.cancellationCause;
        }
        if (th2 != null) {
            this.innerAttemptFuture.setException(th2);
        } else {
            this.innerAttemptFuture.setException(new ServerStreamingAttemptException(th, this.resumptionStrategy.canResume(), this.seenSuccessSinceLastError));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAttemptComplete() {
        this.innerAttemptFuture.set(null);
    }
}
