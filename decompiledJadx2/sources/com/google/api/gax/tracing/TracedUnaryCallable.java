package com.google.api.gax.tracing;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.common.util.concurrent.MoreExecutors;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use by google-cloud-java clients only")
@BetaApi("The surface for tracing is not stable and might change in the future")
/* loaded from: classes2.dex */
public class TracedUnaryCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> innerCallable;
    private final SpanName spanName;
    private final ApiTracerFactory tracerFactory;

    public TracedUnaryCallable(UnaryCallable<RequestT, ResponseT> unaryCallable, ApiTracerFactory apiTracerFactory, SpanName spanName) {
        this.innerCallable = unaryCallable;
        this.tracerFactory = apiTracerFactory;
        this.spanName = spanName;
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        ApiTracer newTracer = this.tracerFactory.newTracer(apiCallContext.getTracer(), this.spanName, ApiTracerFactory.OperationType.Unary);
        TraceFinisher traceFinisher = new TraceFinisher(newTracer);
        try {
            ApiFuture<ResponseT> futureCall = this.innerCallable.futureCall(requestt, apiCallContext.withTracer(newTracer));
            ApiFutures.addCallback(futureCall, traceFinisher, MoreExecutors.directExecutor());
            return futureCall;
        } catch (RuntimeException e) {
            traceFinisher.onFailure(e);
            throw e;
        }
    }
}
