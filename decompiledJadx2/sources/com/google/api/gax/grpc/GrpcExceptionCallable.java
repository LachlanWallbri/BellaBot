package com.google.api.gax.grpc;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Set;
import java.util.concurrent.CancellationException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class GrpcExceptionCallable<RequestT, ResponseT> extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final GrpcApiExceptionFactory exceptionFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcExceptionCallable(UnaryCallable<RequestT, ResponseT> unaryCallable, Set<StatusCode.Code> set) {
        this.callable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.exceptionFactory = new GrpcApiExceptionFactory(set);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<ResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        ApiFuture<ResponseT> futureCall = this.callable.futureCall(requestt, GrpcCallContext.createDefault().nullToSelf(apiCallContext));
        ExceptionTransformingFuture exceptionTransformingFuture = new ExceptionTransformingFuture(futureCall);
        ApiFutures.addCallback(futureCall, exceptionTransformingFuture, MoreExecutors.directExecutor());
        return exceptionTransformingFuture;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class ExceptionTransformingFuture extends AbstractApiFuture<ResponseT> implements ApiFutureCallback<ResponseT> {
        private volatile boolean cancelled = false;
        private ApiFuture<ResponseT> innerCallFuture;

        public ExceptionTransformingFuture(ApiFuture<ResponseT> apiFuture) {
            this.innerCallFuture = apiFuture;
        }

        @Override // com.google.api.core.AbstractApiFuture
        protected void interruptTask() {
            this.cancelled = true;
            this.innerCallFuture.cancel(true);
        }

        @Override // com.google.api.core.ApiFutureCallback
        public void onSuccess(ResponseT responset) {
            super.set(responset);
        }

        @Override // com.google.api.core.ApiFutureCallback
        public void onFailure(Throwable th) {
            if ((th instanceof CancellationException) && this.cancelled) {
                return;
            }
            setException(GrpcExceptionCallable.this.exceptionFactory.create(th));
        }
    }
}
