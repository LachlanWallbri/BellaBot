package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientCall;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DelayedClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Executor callExecutor;
    private final Context context;
    private DelayedListener<RespT> delayedListener;
    private Status error;

    @Nullable
    private final ScheduledFuture<?> initialDeadlineMonitor;
    private ClientCall.Listener<RespT> listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingRunnables = new ArrayList();
    private ClientCall<ReqT, RespT> realCall;
    private static final Logger logger = Logger.getLogger(DelayedClientCall.class.getName());
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() { // from class: io.grpc.internal.DelayedClientCall.7
        @Override // io.grpc.ClientCall
        public void cancel(String str, Throwable th) {
        }

        @Override // io.grpc.ClientCall
        public void halfClose() {
        }

        @Override // io.grpc.ClientCall
        public boolean isReady() {
            return false;
        }

        @Override // io.grpc.ClientCall
        public void request(int i) {
        }

        @Override // io.grpc.ClientCall
        public void sendMessage(Object obj) {
        }

        @Override // io.grpc.ClientCall
        public void start(ClientCall.Listener<Object> listener, Metadata metadata) {
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    public void callCancelled() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DelayedClientCall(Executor executor, ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        this.callExecutor = (Executor) Preconditions.checkNotNull(executor, "callExecutor");
        Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.context = Context.current();
        this.initialDeadlineMonitor = scheduleDeadlineIfNeeded(scheduledExecutorService, deadline);
    }

    @Nullable
    private ScheduledFuture<?> scheduleDeadlineIfNeeded(ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        Deadline deadline2 = this.context.getDeadline();
        if (deadline == null && deadline2 == null) {
            return null;
        }
        long min = deadline != null ? Math.min(Long.MAX_VALUE, deadline.timeRemaining(TimeUnit.NANOSECONDS)) : Long.MAX_VALUE;
        if (deadline2 != null && deadline2.timeRemaining(TimeUnit.NANOSECONDS) < min) {
            min = deadline2.timeRemaining(TimeUnit.NANOSECONDS);
            if (logger.isLoggable(Level.FINE)) {
                StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(min)));
                if (deadline == null) {
                    sb.append(" Explicit call timeout was not set.");
                } else {
                    sb.append(String.format(" Explicit call timeout was '%d' ns.", Long.valueOf(deadline.timeRemaining(TimeUnit.NANOSECONDS))));
                }
                logger.fine(sb.toString());
            }
        }
        long abs = Math.abs(min) / TimeUnit.SECONDS.toNanos(1L);
        long abs2 = Math.abs(min) % TimeUnit.SECONDS.toNanos(1L);
        final StringBuilder sb2 = new StringBuilder();
        if (min < 0) {
            sb2.append("ClientCall started after deadline exceeded. Deadline exceeded after -");
        } else {
            sb2.append("Deadline exceeded after ");
        }
        sb2.append(abs);
        sb2.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
        sb2.append("s. ");
        return scheduledExecutorService.schedule(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.1DeadlineExceededRunnable
            @Override // java.lang.Runnable
            public void run() {
                DelayedClientCall.this.cancel(Status.DEADLINE_EXCEEDED.withDescription(sb2.toString()), true);
            }
        }, min, TimeUnit.NANOSECONDS);
    }

    public final void setCall(ClientCall<ReqT, RespT> clientCall) {
        synchronized (this) {
            if (this.realCall != null) {
                return;
            }
            setRealCall((ClientCall) Preconditions.checkNotNull(clientCall, NotificationCompat.CATEGORY_CALL));
            drainPendingCalls();
        }
    }

    @Override // io.grpc.ClientCall
    public final void start(final ClientCall.Listener<RespT> listener, final Metadata metadata) {
        Status status;
        boolean z;
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            this.listener = (ClientCall.Listener) Preconditions.checkNotNull(listener, "listener");
            status = this.error;
            z = this.passThrough;
            if (!z) {
                DelayedListener<RespT> delayedListener = new DelayedListener<>(listener);
                this.delayedListener = delayedListener;
                listener = delayedListener;
            }
        }
        if (status != null) {
            this.callExecutor.execute(new CloseListenerRunnable(listener, status));
        } else if (z) {
            this.realCall.start(listener, metadata);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.1
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.start(listener, metadata);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void cancel(@Nullable String str, @Nullable Throwable th) {
        Status withDescription;
        Status status = Status.CANCELLED;
        if (str != null) {
            withDescription = status.withDescription(str);
        } else {
            withDescription = status.withDescription("Call cancelled without message");
        }
        if (th != null) {
            withDescription = withDescription.withCause(th);
        }
        cancel(withDescription, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void cancel(final Status status, boolean z) {
        boolean z2;
        ClientCall.Listener<RespT> listener;
        synchronized (this) {
            if (this.realCall == null) {
                setRealCall(NOOP_CALL);
                z2 = false;
                listener = this.listener;
                this.error = status;
            } else {
                if (z) {
                    return;
                }
                z2 = true;
                listener = null;
            }
            if (z2) {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedClientCall.this.realCall.cancel(status.getDescription(), status.getCause());
                    }
                });
            } else {
                if (listener != null) {
                    this.callExecutor.execute(new CloseListenerRunnable(listener, status));
                }
                drainPendingCalls();
            }
            callCancelled();
        }
    }

    private void delayOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingRunnables.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0031, code lost:
    
        if (r0.hasNext() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
    
        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0029, code lost:
    
        r0 = r1.iterator();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void drainPendingCalls() {
        final DelayedListener<RespT> delayedListener;
        List list;
        List arrayList = new ArrayList();
        while (true) {
            synchronized (this) {
                if (this.pendingRunnables.isEmpty()) {
                    break;
                }
                list = this.pendingRunnables;
                this.pendingRunnables = arrayList;
            }
            if (delayedListener == null) {
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.DelayedClientCall.1DrainListenerRunnable
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(DelayedClientCall.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        delayedListener.drainPendingCallbacks();
                    }
                });
                return;
            }
            return;
            list.clear();
            arrayList = list;
        }
        this.pendingRunnables = null;
        this.passThrough = true;
        delayedListener = this.delayedListener;
        if (delayedListener == null) {
        }
    }

    private void setRealCall(ClientCall<ReqT, RespT> clientCall) {
        Preconditions.checkState(this.realCall == null, "realCall already set to %s", this.realCall);
        ScheduledFuture<?> scheduledFuture = this.initialDeadlineMonitor;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.realCall = clientCall;
    }

    final ClientCall<ReqT, RespT> getRealCall() {
        return this.realCall;
    }

    @Override // io.grpc.ClientCall
    public final void sendMessage(final ReqT reqt) {
        if (this.passThrough) {
            this.realCall.sendMessage(reqt);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.sendMessage(reqt);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void setMessageCompression(final boolean z) {
        if (this.passThrough) {
            this.realCall.setMessageCompression(z);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.4
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.setMessageCompression(z);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void request(final int i) {
        if (this.passThrough) {
            this.realCall.request(i);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.5
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.request(i);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void halfClose() {
        delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.6
            @Override // java.lang.Runnable
            public void run() {
                DelayedClientCall.this.realCall.halfClose();
            }
        });
    }

    @Override // io.grpc.ClientCall
    public final boolean isReady() {
        if (this.passThrough) {
            return this.realCall.isReady();
        }
        return false;
    }

    @Override // io.grpc.ClientCall
    public final Attributes getAttributes() {
        ClientCall<ReqT, RespT> clientCall;
        synchronized (this) {
            clientCall = this.realCall;
        }
        if (clientCall != null) {
            return clientCall.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("realCall", this.realCall).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class CloseListenerRunnable extends ContextRunnable {
        final ClientCall.Listener<RespT> listener;
        final Status status;

        CloseListenerRunnable(ClientCall.Listener<RespT> listener, Status status) {
            super(DelayedClientCall.this.context);
            this.listener = listener;
            this.status = status;
        }

        @Override // io.grpc.internal.ContextRunnable
        public void runInContext() {
            this.listener.onClose(this.status, new Metadata());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DelayedListener<RespT> extends ClientCall.Listener<RespT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        private final ClientCall.Listener<RespT> realListener;

        public DelayedListener(ClientCall.Listener<RespT> listener) {
            this.realListener = listener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onHeaders(final Metadata metadata) {
            if (this.passThrough) {
                this.realListener.onHeaders(metadata);
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onHeaders(metadata);
                    }
                });
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onMessage(final RespT respt) {
            if (this.passThrough) {
                this.realListener.onMessage(respt);
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onMessage(respt);
                    }
                });
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onClose(final Status status, final Metadata metadata) {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.3
                @Override // java.lang.Runnable
                public void run() {
                    DelayedListener.this.realListener.onClose(status, metadata);
                }
            });
        }

        @Override // io.grpc.ClientCall.Listener
        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onReady();
                    }
                });
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drainPendingCallbacks() {
            List list;
            List arrayList = new ArrayList();
            while (true) {
                synchronized (this) {
                    if (this.pendingCallbacks.isEmpty()) {
                        this.pendingCallbacks = null;
                        this.passThrough = true;
                        return;
                    } else {
                        list = this.pendingCallbacks;
                        this.pendingCallbacks = arrayList;
                    }
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
                list.clear();
                arrayList = list;
            }
        }
    }
}
