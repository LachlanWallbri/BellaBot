package com.google.api.gax.tracing;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ClientStreamReadyObserver;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.common.base.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use by google-cloud-java clients only")
@BetaApi("The surface for tracing is not stable and might change in the future")
/* loaded from: classes2.dex */
public class TracedBidiCallable<RequestT, ResponseT> extends BidiStreamingCallable<RequestT, ResponseT> {

    @Nonnull
    private final BidiStreamingCallable<RequestT, ResponseT> innerCallable;

    @Nonnull
    private final SpanName spanName;

    @Nonnull
    private final ApiTracerFactory tracerFactory;

    public TracedBidiCallable(@Nonnull BidiStreamingCallable<RequestT, ResponseT> bidiStreamingCallable, @Nonnull ApiTracerFactory apiTracerFactory, @Nonnull SpanName spanName) {
        this.tracerFactory = (ApiTracerFactory) Preconditions.checkNotNull(apiTracerFactory, "tracerFactory can't be null");
        this.spanName = (SpanName) Preconditions.checkNotNull(spanName, "spanName can't be null");
        this.innerCallable = (BidiStreamingCallable) Preconditions.checkNotNull(bidiStreamingCallable, "innerCallable can't be null");
    }

    @Override // com.google.api.gax.rpc.BidiStreamingCallable
    public ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, ClientStreamReadyObserver<RequestT> clientStreamReadyObserver, ApiCallContext apiCallContext) {
        ApiTracer newTracer = this.tracerFactory.newTracer(apiCallContext.getTracer(), this.spanName, ApiTracerFactory.OperationType.BidiStreaming);
        ApiCallContext withTracer = apiCallContext.withTracer(newTracer);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        try {
            return new TracingClientStream(newTracer, this.innerCallable.internalCall(new TracedResponseObserver(newTracer, responseObserver, atomicBoolean), new TracedClientStreamReadyObserver(newTracer, clientStreamReadyObserver, atomicBoolean), withTracer), atomicBoolean);
        } catch (RuntimeException e) {
            newTracer.operationFailed(e);
            throw e;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private static class TracedClientStreamReadyObserver<RequestT> implements ClientStreamReadyObserver<RequestT> {
        private final ClientStreamReadyObserver<RequestT> innerObserver;
        private final ApiTracer tracer;
        private final AtomicBoolean wasCancelled;

        TracedClientStreamReadyObserver(ApiTracer apiTracer, ClientStreamReadyObserver<RequestT> clientStreamReadyObserver, AtomicBoolean atomicBoolean) {
            this.tracer = apiTracer;
            this.innerObserver = clientStreamReadyObserver;
            this.wasCancelled = atomicBoolean;
        }

        @Override // com.google.api.gax.rpc.ClientStreamReadyObserver
        public void onReady(ClientStream<RequestT> clientStream) {
            this.innerObserver.onReady(new TracingClientStream(this.tracer, clientStream, this.wasCancelled));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private static class TracingClientStream<RequestT> implements ClientStream<RequestT> {
        private final ClientStream<RequestT> innerStream;
        private final ApiTracer tracer;
        private final AtomicBoolean wasCancelled;

        private TracingClientStream(ApiTracer apiTracer, ClientStream<RequestT> clientStream, AtomicBoolean atomicBoolean) {
            this.tracer = apiTracer;
            this.innerStream = clientStream;
            this.wasCancelled = atomicBoolean;
        }

        @Override // com.google.api.gax.rpc.ClientStream
        public void send(RequestT requestt) {
            this.tracer.requestSent();
            this.innerStream.send(requestt);
        }

        @Override // com.google.api.gax.rpc.ClientStream
        public void closeSendWithError(Throwable th) {
            if (th == null) {
                th = new CancellationException();
            }
            this.wasCancelled.set(true);
            this.innerStream.closeSendWithError(th);
        }

        @Override // com.google.api.gax.rpc.ClientStream
        public void closeSend() {
            this.innerStream.closeSend();
        }

        @Override // com.google.api.gax.rpc.ClientStream
        public boolean isSendReady() {
            return this.innerStream.isSendReady();
        }
    }
}
