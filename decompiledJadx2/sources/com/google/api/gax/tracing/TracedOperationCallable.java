package com.google.api.gax.tracing;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.InternalApi;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.common.util.concurrent.MoreExecutors;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use by google-cloud-java clients only")
/* loaded from: classes2.dex */
public class TracedOperationCallable<RequestT, ResponseT, MetadataT> extends OperationCallable<RequestT, ResponseT, MetadataT> {

    @Nonnull
    private OperationCallable<RequestT, ResponseT, MetadataT> innerCallable;

    @Nonnull
    private SpanName spanName;

    @Nonnull
    private ApiTracerFactory tracerFactory;

    public TracedOperationCallable(@Nonnull OperationCallable<RequestT, ResponseT, MetadataT> operationCallable, @Nonnull ApiTracerFactory apiTracerFactory, @Nonnull SpanName spanName) {
        this.innerCallable = operationCallable;
        this.tracerFactory = apiTracerFactory;
        this.spanName = spanName;
    }

    @Override // com.google.api.gax.rpc.OperationCallable
    public OperationFuture<ResponseT, MetadataT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        ApiTracer newTracer = this.tracerFactory.newTracer(apiCallContext.getTracer(), this.spanName, ApiTracerFactory.OperationType.LongRunning);
        TraceFinisher traceFinisher = new TraceFinisher(newTracer);
        try {
            OperationFuture<ResponseT, MetadataT> futureCall = this.innerCallable.futureCall(requestt, apiCallContext.withTracer(newTracer));
            ApiFutures.addCallback(futureCall, traceFinisher, MoreExecutors.directExecutor());
            return futureCall;
        } catch (RuntimeException e) {
            traceFinisher.onFailure(e);
            throw e;
        }
    }

    @Override // com.google.api.gax.rpc.OperationCallable
    public OperationFuture<ResponseT, MetadataT> resumeFutureCall(String str, ApiCallContext apiCallContext) {
        ApiTracer newTracer = this.tracerFactory.newTracer(apiCallContext.getTracer(), this.spanName, ApiTracerFactory.OperationType.LongRunning);
        TraceFinisher traceFinisher = new TraceFinisher(newTracer);
        try {
            OperationFuture<ResponseT, MetadataT> resumeFutureCall = this.innerCallable.resumeFutureCall(str, apiCallContext.withTracer(newTracer));
            ApiFutures.addCallback(resumeFutureCall, traceFinisher, MoreExecutors.directExecutor());
            return resumeFutureCall;
        } catch (RuntimeException e) {
            traceFinisher.onFailure(e);
            throw e;
        }
    }

    @Override // com.google.api.gax.rpc.OperationCallable
    public ApiFuture<Void> cancel(String str, ApiCallContext apiCallContext) {
        ApiTracer newTracer = this.tracerFactory.newTracer(apiCallContext.getTracer(), SpanName.m579of(this.spanName.getClientName(), this.spanName.getMethodName() + ".Cancel"), ApiTracerFactory.OperationType.Unary);
        TraceFinisher traceFinisher = new TraceFinisher(newTracer);
        try {
            ApiFuture<Void> cancel = this.innerCallable.cancel(str, apiCallContext.withTracer(newTracer));
            ApiFutures.addCallback(cancel, traceFinisher, MoreExecutors.directExecutor());
            return cancel;
        } catch (RuntimeException e) {
            traceFinisher.onFailure(e);
            throw e;
        }
    }
}
