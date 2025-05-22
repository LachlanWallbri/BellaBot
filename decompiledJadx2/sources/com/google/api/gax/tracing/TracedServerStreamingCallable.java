package com.google.api.gax.tracing;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use by google-cloud-java clients only")
@BetaApi("The surface for tracing is not stable and might change in the future")
/* loaded from: classes2.dex */
public final class TracedServerStreamingCallable<RequestT, ResponseT> extends ServerStreamingCallable<RequestT, ResponseT> {

    @Nonnull
    private final ServerStreamingCallable<RequestT, ResponseT> innerCallable;

    @Nonnull
    private final SpanName spanName;

    @Nonnull
    private final ApiTracerFactory tracerFactory;

    public TracedServerStreamingCallable(@Nonnull ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, @Nonnull ApiTracerFactory apiTracerFactory, @Nonnull SpanName spanName) {
        this.tracerFactory = (ApiTracerFactory) Preconditions.checkNotNull(apiTracerFactory, "tracerFactory can't be null");
        this.spanName = (SpanName) Preconditions.checkNotNull(spanName, "spanName can't be null");
        this.innerCallable = (ServerStreamingCallable) Preconditions.checkNotNull(serverStreamingCallable, "innerCallable can't be null");
    }

    @Override // com.google.api.gax.rpc.ServerStreamingCallable
    public void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext) {
        ApiTracer newTracer = this.tracerFactory.newTracer(apiCallContext.getTracer(), this.spanName, ApiTracerFactory.OperationType.ServerStreaming);
        TracedResponseObserver tracedResponseObserver = new TracedResponseObserver(newTracer, responseObserver);
        try {
            this.innerCallable.call(requestt, tracedResponseObserver, apiCallContext.withTracer(newTracer));
        } catch (RuntimeException e) {
            tracedResponseObserver.onError(e);
            throw e;
        }
    }
}
