package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public final class Watchdog implements Runnable, BackgroundResource {
    private static final Logger LOG = Logger.getLogger(Watchdog.class.getName());
    private static Object PRESENT = new Object();
    private final ApiClock clock;
    private final ScheduledExecutorService executor;
    private ScheduledFuture<?> future;
    private final ConcurrentHashMap<WatchdogStream, Object> openStreams = new ConcurrentHashMap<>();
    private final Duration scheduleInterval;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum State {
        IDLE,
        WAITING,
        DELIVERING
    }

    public static Watchdog create(ApiClock apiClock, Duration duration, ScheduledExecutorService scheduledExecutorService) {
        Watchdog watchdog = new Watchdog(apiClock, duration, scheduledExecutorService);
        watchdog.start();
        return watchdog;
    }

    private Watchdog(ApiClock apiClock, Duration duration, ScheduledExecutorService scheduledExecutorService) {
        this.clock = (ApiClock) Preconditions.checkNotNull(apiClock, "clock can't be null");
        this.scheduleInterval = duration;
        this.executor = scheduledExecutorService;
    }

    private void start() {
        this.future = this.executor.scheduleAtFixedRate(this, this.scheduleInterval.toMillis(), this.scheduleInterval.toMillis(), TimeUnit.MILLISECONDS);
    }

    public <ResponseT> ResponseObserver<ResponseT> watch(ResponseObserver<ResponseT> responseObserver, @Nonnull Duration duration, @Nonnull Duration duration2) {
        Preconditions.checkNotNull(responseObserver, "innerObserver can't be null");
        Preconditions.checkNotNull(duration, "waitTimeout can't be null");
        Preconditions.checkNotNull(duration2, "idleTimeout can't be null");
        if (duration.isZero() && duration2.isZero()) {
            return responseObserver;
        }
        WatchdogStream watchdogStream = new WatchdogStream(responseObserver, duration, duration2);
        this.openStreams.put(watchdogStream, PRESENT);
        return watchdogStream;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            runUnsafe();
        } catch (Throwable th) {
            LOG.log(Level.SEVERE, "Caught throwable in periodic Watchdog run. Continuing.", th);
        }
    }

    private void runUnsafe() {
        Iterator<Map.Entry<WatchdogStream, Object>> it = this.openStreams.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().cancelIfStale()) {
                it.remove();
            }
        }
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        this.future.cancel(false);
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return this.executor.isShutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return this.executor.isTerminated();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        this.future.cancel(true);
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.executor.awaitTermination(j, timeUnit);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        shutdown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public class WatchdogStream<ResponseT> extends StateCheckingResponseObserver<ResponseT> {
        private volatile Throwable error;
        private boolean hasStarted;
        private final Duration idleTimeout;
        private volatile StreamController innerController;
        private long lastActivityAt;
        private final ResponseObserver<ResponseT> outerResponseObserver;
        private final Duration waitTimeout;
        private final Object lock = new Object();
        private boolean autoAutoFlowControl = true;
        private State state = State.IDLE;
        private int pendingCount = 0;

        WatchdogStream(ResponseObserver<ResponseT> responseObserver, Duration duration, Duration duration2) {
            this.lastActivityAt = Watchdog.this.clock.millisTime();
            this.waitTimeout = duration;
            this.idleTimeout = duration2;
            this.outerResponseObserver = responseObserver;
        }

        @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
        public void onStartImpl(StreamController streamController) {
            this.innerController = streamController;
            this.outerResponseObserver.onStart(new StreamController() { // from class: com.google.api.gax.rpc.Watchdog.WatchdogStream.1
                @Override // com.google.api.gax.rpc.StreamController
                public void disableAutoInboundFlowControl() {
                    Preconditions.checkState(!WatchdogStream.this.hasStarted, "Can't disable automatic flow control after the stream has started");
                    WatchdogStream.this.autoAutoFlowControl = false;
                    WatchdogStream.this.innerController.disableAutoInboundFlowControl();
                }

                @Override // com.google.api.gax.rpc.StreamController
                public void request(int i) {
                    WatchdogStream.this.onRequest(i);
                }

                @Override // com.google.api.gax.rpc.StreamController
                public void cancel() {
                    WatchdogStream.this.onCancel();
                }
            });
            this.hasStarted = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onRequest(int i) {
            int min;
            Preconditions.checkArgument(i > 0, "count must be > 0");
            Preconditions.checkState(true ^ this.autoAutoFlowControl, "Auto flow control is enabled");
            synchronized (this.lock) {
                if (this.state == State.IDLE) {
                    this.state = State.WAITING;
                    this.lastActivityAt = Watchdog.this.clock.millisTime();
                }
                min = Math.min(Integer.MAX_VALUE - this.pendingCount, i);
                this.pendingCount += min;
            }
            this.innerController.request(min);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onCancel() {
            this.error = new CancellationException("User cancelled stream");
            this.innerController.cancel();
        }

        @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
        public void onResponseImpl(ResponseT responset) {
            synchronized (this.lock) {
                this.state = State.DELIVERING;
            }
            this.outerResponseObserver.onResponse(responset);
            synchronized (this.lock) {
                this.pendingCount--;
                this.lastActivityAt = Watchdog.this.clock.millisTime();
                if (!this.autoAutoFlowControl && this.pendingCount <= 0) {
                    this.state = State.IDLE;
                }
                this.state = State.WAITING;
            }
        }

        @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
        public void onErrorImpl(Throwable th) {
            if (this.error != null) {
                th = this.error;
            }
            Watchdog.this.openStreams.remove(this);
            this.outerResponseObserver.onError(th);
        }

        @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
        public void onCompleteImpl() {
            Watchdog.this.openStreams.remove(this);
            this.outerResponseObserver.onComplete();
        }

        boolean cancelIfStale() {
            if (this.innerController == null) {
                return false;
            }
            WatchdogTimeoutException watchdogTimeoutException = null;
            synchronized (this.lock) {
                long millisTime = Watchdog.this.clock.millisTime() - this.lastActivityAt;
                int i = C21171.$SwitchMap$com$google$api$gax$rpc$Watchdog$State[this.state.ordinal()];
                if (i == 1) {
                    if (!this.idleTimeout.isZero() && millisTime >= this.idleTimeout.toMillis()) {
                        watchdogTimeoutException = new WatchdogTimeoutException("Canceled due to idle connection", false);
                    }
                } else if (i == 2 && !this.waitTimeout.isZero() && millisTime >= this.waitTimeout.toMillis()) {
                    watchdogTimeoutException = new WatchdogTimeoutException("Canceled due to timeout waiting for next response", true);
                }
            }
            if (watchdogTimeoutException == null) {
                return false;
            }
            this.error = watchdogTimeoutException;
            this.innerController.cancel();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.api.gax.rpc.Watchdog$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C21171 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$gax$rpc$Watchdog$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$com$google$api$gax$rpc$Watchdog$State[State.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$gax$rpc$Watchdog$State[State.WAITING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
