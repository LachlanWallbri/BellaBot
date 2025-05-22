package com.google.api.gax.tracing;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.common.base.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
@BetaApi("The surface for tracing is not stable and might change in the future")
/* loaded from: classes2.dex */
public class TracedClientStreamingCallable<RequestT, ResponseT> extends ClientStreamingCallable<RequestT, ResponseT> {
    private final ClientStreamingCallable<RequestT, ResponseT> innerCallable;
    private final SpanName spanName;
    private final ApiTracerFactory tracerFactory;

    public TracedClientStreamingCallable(@Nonnull ClientStreamingCallable<RequestT, ResponseT> clientStreamingCallable, @Nonnull ApiTracerFactory apiTracerFactory, @Nonnull SpanName spanName) {
        this.tracerFactory = (ApiTracerFactory) Preconditions.checkNotNull(apiTracerFactory, "tracerFactory can't be null");
        this.spanName = (SpanName) Preconditions.checkNotNull(spanName, "spanName can't be null");
        this.innerCallable = (ClientStreamingCallable) Preconditions.checkNotNull(clientStreamingCallable, "innerCallable can't be null");
    }

    @Override // com.google.api.gax.rpc.ClientStreamingCallable
    public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver, ApiCallContext apiCallContext) {
        ApiTracer newTracer = this.tracerFactory.newTracer(apiCallContext.getTracer(), this.spanName, ApiTracerFactory.OperationType.ClientStreaming);
        ApiCallContext withTracer = apiCallContext.withTracer(newTracer);
        AtomicReference atomicReference = new AtomicReference(null);
        try {
            return new TracedRequestObserver(newTracer, this.innerCallable.clientStreamingCall(new TracedResponseObserver(newTracer, apiStreamObserver, atomicReference), withTracer), atomicReference);
        } catch (RuntimeException e) {
            newTracer.operationFailed(e);
            throw e;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private static class TracedRequestObserver<RequestT> implements ApiStreamObserver<RequestT> {
        private final AtomicReference<Throwable> cancellationCauseHolder;
        private final ApiStreamObserver<RequestT> innerObserver;
        private final ApiTracer tracer;

        TracedRequestObserver(@Nonnull ApiTracer apiTracer, @Nonnull ApiStreamObserver<RequestT> apiStreamObserver, @Nonnull AtomicReference<Throwable> atomicReference) {
            this.tracer = (ApiTracer) Preconditions.checkNotNull(apiTracer, "tracer can't be null");
            this.innerObserver = (ApiStreamObserver) Preconditions.checkNotNull(apiStreamObserver, "innerObserver can't be null");
            this.cancellationCauseHolder = (AtomicReference) Preconditions.checkNotNull(atomicReference, "cancellationCauseHolder can't be null");
        }

        @Override // com.google.api.gax.rpc.ApiStreamObserver
        public void onNext(RequestT requestt) {
            this.tracer.requestSent();
            this.innerObserver.onNext(requestt);
        }

        @Override // com.google.api.gax.rpc.ApiStreamObserver
        public void onError(Throwable th) {
            if (th == null) {
                th = new CancellationException("Cancelled without a cause");
            }
            this.cancellationCauseHolder.compareAndSet(null, th);
            this.innerObserver.onError(th);
        }

        @Override // com.google.api.gax.rpc.ApiStreamObserver
        public void onCompleted() {
            this.innerObserver.onCompleted();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private static class TracedResponseObserver<RequestT> implements ApiStreamObserver<RequestT> {

        @Nonnull
        private final AtomicReference<Throwable> cancellationCauseHolder;

        @Nonnull
        private final ApiStreamObserver<RequestT> innerObserver;

        @Nonnull
        private final ApiTracer tracer;

        TracedResponseObserver(@Nonnull ApiTracer apiTracer, @Nonnull ApiStreamObserver<RequestT> apiStreamObserver, @Nonnull AtomicReference<Throwable> atomicReference) {
            this.tracer = (ApiTracer) Preconditions.checkNotNull(apiTracer, "tracer can't be null");
            this.innerObserver = (ApiStreamObserver) Preconditions.checkNotNull(apiStreamObserver, "innerObserver can't be null");
            this.cancellationCauseHolder = atomicReference;
        }

        @Override // com.google.api.gax.rpc.ApiStreamObserver
        public void onNext(RequestT requestt) {
            this.tracer.responseReceived();
            this.innerObserver.onNext(requestt);
        }

        @Override // com.google.api.gax.rpc.ApiStreamObserver
        public void onError(Throwable th) {
            if (this.cancellationCauseHolder.get() != null) {
                this.tracer.operationCancelled();
            } else {
                this.tracer.operationFailed(th);
            }
            this.innerObserver.onError(th);
        }

        @Override // com.google.api.gax.rpc.ApiStreamObserver
        public void onCompleted() {
            this.tracer.operationSucceeded();
            this.innerObserver.onCompleted();
        }
    }
}
