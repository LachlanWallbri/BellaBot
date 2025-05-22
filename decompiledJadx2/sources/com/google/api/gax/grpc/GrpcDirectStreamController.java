package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.base.Preconditions;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.concurrent.CancellationException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class GrpcDirectStreamController<RequestT, ResponseT> implements StreamController {
    private static final Runnable NOOP_RUNNABLE = new Runnable() { // from class: com.google.api.gax.grpc.GrpcDirectStreamController.1
        @Override // java.lang.Runnable
        public void run() {
        }
    };
    private boolean autoflowControl;
    private volatile CancellationException cancellationException;
    private final ClientCall<RequestT, ResponseT> clientCall;
    private boolean hasStarted;
    private int numRequested;
    private final Runnable onReady;
    private final ResponseObserver<ResponseT> responseObserver;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcDirectStreamController(ClientCall<RequestT, ResponseT> clientCall, ResponseObserver<ResponseT> responseObserver) {
        this(clientCall, responseObserver, NOOP_RUNNABLE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcDirectStreamController(ClientCall<RequestT, ResponseT> clientCall, ResponseObserver<ResponseT> responseObserver, Runnable runnable) {
        this.autoflowControl = true;
        this.clientCall = clientCall;
        this.responseObserver = responseObserver;
        this.onReady = runnable;
    }

    @Override // com.google.api.gax.rpc.StreamController
    public void cancel() {
        this.cancellationException = new CancellationException("User cancelled stream");
        this.clientCall.cancel(null, this.cancellationException);
    }

    @Override // com.google.api.gax.rpc.StreamController
    public void disableAutoInboundFlowControl() {
        Preconditions.checkState(!this.hasStarted, "Can't disable automatic flow control after the stream has started.");
        this.autoflowControl = false;
    }

    @Override // com.google.api.gax.rpc.StreamController
    public void request(int i) {
        Preconditions.checkState(!this.autoflowControl, "Autoflow control is enabled.");
        if (!this.hasStarted) {
            this.numRequested += i;
        } else {
            this.clientCall.request(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start(RequestT requestt) {
        startCommon();
        this.clientCall.sendMessage(requestt);
        this.clientCall.halfClose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startBidi() {
        startCommon();
    }

    private void startCommon() {
        this.responseObserver.onStart(this);
        this.hasStarted = true;
        this.clientCall.start(new ResponseObserverAdapter(), new Metadata());
        if (this.autoflowControl) {
            this.clientCall.request(1);
            return;
        }
        int i = this.numRequested;
        if (i > 0) {
            this.clientCall.request(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public class ResponseObserverAdapter extends ClientCall.Listener<ResponseT> {
        private ResponseObserverAdapter() {
        }

        @Override // io.grpc.ClientCall.Listener
        public void onMessage(ResponseT responset) {
            GrpcDirectStreamController.this.responseObserver.onResponse(responset);
            if (GrpcDirectStreamController.this.autoflowControl) {
                GrpcDirectStreamController.this.clientCall.request(1);
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                GrpcDirectStreamController.this.responseObserver.onComplete();
            } else if (GrpcDirectStreamController.this.cancellationException != null) {
                GrpcDirectStreamController.this.responseObserver.onError(GrpcDirectStreamController.this.cancellationException);
            } else {
                GrpcDirectStreamController.this.responseObserver.onError(status.asRuntimeException(metadata));
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onReady() {
            GrpcDirectStreamController.this.onReady.run();
        }
    }
}
