package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.pudutech.mirsdk.compat.topo.MapElement;
import io.grpc.ConnectivityState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ConnectivityStateManager {
    private ArrayList<Listener> listeners = new ArrayList<>();
    private volatile ConnectivityState state = ConnectivityState.IDLE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyWhenStateChanged(Runnable runnable, Executor executor, ConnectivityState connectivityState) {
        Preconditions.checkNotNull(runnable, "callback");
        Preconditions.checkNotNull(executor, "executor");
        Preconditions.checkNotNull(connectivityState, MapElement.Source.SOURCE);
        Listener listener = new Listener(runnable, executor);
        if (this.state != connectivityState) {
            listener.runInExecutor();
        } else {
            this.listeners.add(listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void gotoState(@Nonnull ConnectivityState connectivityState) {
        Preconditions.checkNotNull(connectivityState, "newState");
        if (this.state == connectivityState || this.state == ConnectivityState.SHUTDOWN) {
            return;
        }
        this.state = connectivityState;
        if (this.listeners.isEmpty()) {
            return;
        }
        ArrayList<Listener> arrayList = this.listeners;
        this.listeners = new ArrayList<>();
        Iterator<Listener> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().runInExecutor();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityState getState() {
        ConnectivityState connectivityState = this.state;
        if (connectivityState != null) {
            return connectivityState;
        }
        throw new UnsupportedOperationException("Channel state API is not implemented");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class Listener {
        final Runnable callback;
        final Executor executor;

        Listener(Runnable runnable, Executor executor) {
            this.callback = runnable;
            this.executor = executor;
        }

        void runInExecutor() {
            this.executor.execute(this.callback);
        }
    }
}
