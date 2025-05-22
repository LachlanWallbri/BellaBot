package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Status;
import io.grpc.internal.ManagedClientTransport;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ClientTransportLifecycleManager {
    private final ManagedClientTransport.Listener listener;
    private Status shutdownStatus;
    private Throwable shutdownThrowable;
    private boolean transportInUse;
    private boolean transportReady;
    private boolean transportShutdown;
    private boolean transportTerminated;

    public ClientTransportLifecycleManager(ManagedClientTransport.Listener listener) {
        this.listener = listener;
    }

    public void notifyReady() {
        if (this.transportReady || this.transportShutdown) {
            return;
        }
        this.transportReady = true;
        this.listener.transportReady();
    }

    public void notifyGracefulShutdown(Status status) {
        if (this.transportShutdown) {
            return;
        }
        this.transportShutdown = true;
        this.listener.transportShutdown(status);
    }

    public boolean notifyShutdown(Status status) {
        notifyGracefulShutdown(status);
        if (this.shutdownStatus != null) {
            return false;
        }
        this.shutdownStatus = status;
        this.shutdownThrowable = status.asException();
        return true;
    }

    public void notifyInUse(boolean z) {
        if (z == this.transportInUse) {
            return;
        }
        this.transportInUse = z;
        this.listener.transportInUse(z);
    }

    public void notifyTerminated(Status status) {
        if (this.transportTerminated) {
            return;
        }
        this.transportTerminated = true;
        notifyShutdown(status);
        this.listener.transportTerminated();
    }

    public Status getShutdownStatus() {
        return this.shutdownStatus;
    }

    public Throwable getShutdownThrowable() {
        return this.shutdownThrowable;
    }
}
