package com.google.api.gax.tracing;

import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
class TracedResponseObserver<ResponseT> implements ResponseObserver<ResponseT> {
    private final ResponseObserver<ResponseT> innerObserver;
    private final ApiTracer tracer;
    private AtomicBoolean wasCancelled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TracedResponseObserver(@Nonnull ApiTracer apiTracer, @Nonnull ResponseObserver<ResponseT> responseObserver) {
        this(apiTracer, responseObserver, new AtomicBoolean());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TracedResponseObserver(@Nonnull ApiTracer apiTracer, @Nonnull ResponseObserver<ResponseT> responseObserver, @Nonnull AtomicBoolean atomicBoolean) {
        this.tracer = (ApiTracer) Preconditions.checkNotNull(apiTracer, "tracer");
        this.innerObserver = (ResponseObserver) Preconditions.checkNotNull(responseObserver, "innerObserver");
        this.wasCancelled = (AtomicBoolean) Preconditions.checkNotNull(atomicBoolean, "wasCancelled");
    }

    @Override // com.google.api.gax.rpc.ResponseObserver
    public void onStart(final StreamController streamController) {
        this.innerObserver.onStart(new StreamController() { // from class: com.google.api.gax.tracing.TracedResponseObserver.1
            @Override // com.google.api.gax.rpc.StreamController
            public void cancel() {
                TracedResponseObserver.this.wasCancelled.set(true);
                streamController.cancel();
            }

            @Override // com.google.api.gax.rpc.StreamController
            public void disableAutoInboundFlowControl() {
                streamController.disableAutoInboundFlowControl();
            }

            @Override // com.google.api.gax.rpc.StreamController
            public void request(int i) {
                streamController.request(i);
            }
        });
    }

    @Override // com.google.api.gax.rpc.ResponseObserver
    public void onResponse(ResponseT responset) {
        this.tracer.responseReceived();
        this.innerObserver.onResponse(responset);
    }

    @Override // com.google.api.gax.rpc.ResponseObserver
    public void onError(Throwable th) {
        if (this.wasCancelled.get()) {
            this.tracer.operationCancelled();
        } else {
            this.tracer.operationFailed(th);
        }
        this.innerObserver.onError(th);
    }

    @Override // com.google.api.gax.rpc.ResponseObserver
    public void onComplete() {
        this.tracer.operationSucceeded();
        this.innerObserver.onComplete();
    }
}
