package io.grpc;

import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ManagedChannel extends Channel {
    public abstract boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException;

    public void enterIdle() {
    }

    public abstract boolean isShutdown();

    public abstract boolean isTerminated();

    public void resetConnectBackoff() {
    }

    public abstract ManagedChannel shutdown();

    public abstract ManagedChannel shutdownNow();

    public ConnectivityState getState(boolean z) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void notifyWhenStateChanged(ConnectivityState connectivityState, Runnable runnable) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
