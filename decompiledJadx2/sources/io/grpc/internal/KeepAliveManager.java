package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Status;
import io.grpc.internal.ClientTransport;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class KeepAliveManager {
    private final boolean keepAliveDuringTransportIdle;
    private final KeepAlivePinger keepAlivePinger;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private ScheduledFuture<?> pingFuture;
    private final ScheduledExecutorService scheduler;
    private final Runnable sendPing;
    private final Runnable shutdown;
    private ScheduledFuture<?> shutdownFuture;
    private State state;
    private final Stopwatch stopwatch;
    private static final long MIN_KEEPALIVE_TIME_NANOS = TimeUnit.SECONDS.toNanos(10);
    private static final long MIN_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(10);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface KeepAlivePinger {
        void onPingTimeout();

        void ping();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        IDLE,
        PING_SCHEDULED,
        PING_DELAYED,
        PING_SENT,
        IDLE_AND_PING_SENT,
        DISCONNECTED
    }

    public KeepAliveManager(KeepAlivePinger keepAlivePinger, ScheduledExecutorService scheduledExecutorService, long j, long j2, boolean z) {
        this(keepAlivePinger, scheduledExecutorService, Stopwatch.createUnstarted(), j, j2, z);
    }

    KeepAliveManager(KeepAlivePinger keepAlivePinger, ScheduledExecutorService scheduledExecutorService, Stopwatch stopwatch, long j, long j2, boolean z) {
        this.state = State.IDLE;
        this.shutdown = new LogExceptionRunnable(new Runnable() { // from class: io.grpc.internal.KeepAliveManager.1
            @Override // java.lang.Runnable
            public void run() {
                boolean z2;
                synchronized (KeepAliveManager.this) {
                    if (KeepAliveManager.this.state != State.DISCONNECTED) {
                        KeepAliveManager.this.state = State.DISCONNECTED;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (z2) {
                    KeepAliveManager.this.keepAlivePinger.onPingTimeout();
                }
            }
        });
        this.sendPing = new LogExceptionRunnable(new Runnable() { // from class: io.grpc.internal.KeepAliveManager.2
            @Override // java.lang.Runnable
            public void run() {
                boolean z2;
                synchronized (KeepAliveManager.this) {
                    KeepAliveManager.this.pingFuture = null;
                    if (KeepAliveManager.this.state != State.PING_SCHEDULED) {
                        if (KeepAliveManager.this.state == State.PING_DELAYED) {
                            KeepAliveManager.this.pingFuture = KeepAliveManager.this.scheduler.schedule(KeepAliveManager.this.sendPing, KeepAliveManager.this.keepAliveTimeInNanos - KeepAliveManager.this.stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
                            KeepAliveManager.this.state = State.PING_SCHEDULED;
                        }
                        z2 = false;
                    } else {
                        z2 = true;
                        KeepAliveManager.this.state = State.PING_SENT;
                        KeepAliveManager.this.shutdownFuture = KeepAliveManager.this.scheduler.schedule(KeepAliveManager.this.shutdown, KeepAliveManager.this.keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
                    }
                }
                if (z2) {
                    KeepAliveManager.this.keepAlivePinger.ping();
                }
            }
        });
        this.keepAlivePinger = (KeepAlivePinger) Preconditions.checkNotNull(keepAlivePinger, "keepAlivePinger");
        this.scheduler = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, "stopwatch");
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        this.keepAliveDuringTransportIdle = z;
        stopwatch.reset().start();
    }

    public synchronized void onTransportStarted() {
        if (this.keepAliveDuringTransportIdle) {
            onTransportActive();
        }
    }

    public synchronized void onDataReceived() {
        this.stopwatch.reset().start();
        if (this.state == State.PING_SCHEDULED) {
            this.state = State.PING_DELAYED;
        } else if (this.state == State.PING_SENT || this.state == State.IDLE_AND_PING_SENT) {
            if (this.shutdownFuture != null) {
                this.shutdownFuture.cancel(false);
            }
            if (this.state == State.IDLE_AND_PING_SENT) {
                this.state = State.IDLE;
            } else {
                this.state = State.PING_SCHEDULED;
                Preconditions.checkState(this.pingFuture == null, "There should be no outstanding pingFuture");
                this.pingFuture = this.scheduler.schedule(this.sendPing, this.keepAliveTimeInNanos, TimeUnit.NANOSECONDS);
            }
        }
    }

    public synchronized void onTransportActive() {
        if (this.state == State.IDLE) {
            this.state = State.PING_SCHEDULED;
            if (this.pingFuture == null) {
                this.pingFuture = this.scheduler.schedule(this.sendPing, this.keepAliveTimeInNanos - this.stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
            }
        } else if (this.state == State.IDLE_AND_PING_SENT) {
            this.state = State.PING_SENT;
        }
    }

    public synchronized void onTransportIdle() {
        if (this.keepAliveDuringTransportIdle) {
            return;
        }
        if (this.state == State.PING_SCHEDULED || this.state == State.PING_DELAYED) {
            this.state = State.IDLE;
        }
        if (this.state == State.PING_SENT) {
            this.state = State.IDLE_AND_PING_SENT;
        }
    }

    public synchronized void onTransportTermination() {
        if (this.state != State.DISCONNECTED) {
            this.state = State.DISCONNECTED;
            if (this.shutdownFuture != null) {
                this.shutdownFuture.cancel(false);
            }
            if (this.pingFuture != null) {
                this.pingFuture.cancel(false);
                this.pingFuture = null;
            }
        }
    }

    public static long clampKeepAliveTimeInNanos(long j) {
        return Math.max(j, MIN_KEEPALIVE_TIME_NANOS);
    }

    public static long clampKeepAliveTimeoutInNanos(long j) {
        return Math.max(j, MIN_KEEPALIVE_TIMEOUT_NANOS);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ClientKeepAlivePinger implements KeepAlivePinger {
        private final ConnectionClientTransport transport;

        public ClientKeepAlivePinger(ConnectionClientTransport connectionClientTransport) {
            this.transport = connectionClientTransport;
        }

        @Override // io.grpc.internal.KeepAliveManager.KeepAlivePinger
        public void ping() {
            this.transport.ping(new ClientTransport.PingCallback() { // from class: io.grpc.internal.KeepAliveManager.ClientKeepAlivePinger.1
                @Override // io.grpc.internal.ClientTransport.PingCallback
                public void onSuccess(long j) {
                }

                @Override // io.grpc.internal.ClientTransport.PingCallback
                public void onFailure(Throwable th) {
                    ClientKeepAlivePinger.this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
                }
            }, MoreExecutors.directExecutor());
        }

        @Override // io.grpc.internal.KeepAliveManager.KeepAlivePinger
        public void onPingTimeout() {
            this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
        }
    }
}
