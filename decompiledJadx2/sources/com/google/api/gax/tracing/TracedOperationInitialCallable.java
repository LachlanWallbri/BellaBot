package com.google.api.gax.tracing;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.util.concurrent.MoreExecutors;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class TracedOperationInitialCallable<RequestT> extends TracedUnaryCallable<RequestT, OperationSnapshot> {
    public TracedOperationInitialCallable(UnaryCallable<RequestT, OperationSnapshot> unaryCallable, ApiTracerFactory apiTracerFactory, SpanName spanName) {
        super(unaryCallable, apiTracerFactory, spanName);
    }

    @Override // com.google.api.gax.tracing.TracedUnaryCallable, com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<OperationSnapshot> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        InitialCallableFinisher initialCallableFinisher = new InitialCallableFinisher(apiCallContext.getTracer());
        try {
            ApiFuture<OperationSnapshot> futureCall = super.futureCall(requestt, apiCallContext);
            ApiFutures.addCallback(futureCall, initialCallableFinisher, MoreExecutors.directExecutor());
            return futureCall;
        } catch (RuntimeException e) {
            initialCallableFinisher.onFailure(e);
            throw e;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private static class InitialCallableFinisher implements ApiFutureCallback<OperationSnapshot> {
        private final ApiTracer operationTracer;

        private InitialCallableFinisher(ApiTracer apiTracer) {
            this.operationTracer = apiTracer;
        }

        @Override // com.google.api.core.ApiFutureCallback
        public void onSuccess(OperationSnapshot operationSnapshot) {
            this.operationTracer.lroStartSucceeded();
        }

        @Override // com.google.api.core.ApiFutureCallback
        public void onFailure(Throwable th) {
            this.operationTracer.lroStartFailed(th);
        }
    }
}
