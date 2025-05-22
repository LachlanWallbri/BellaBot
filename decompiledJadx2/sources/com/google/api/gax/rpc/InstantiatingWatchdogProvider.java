package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class InstantiatingWatchdogProvider implements WatchdogProvider {

    @Nullable
    private final Duration checkInterval;

    @Nullable
    private final ApiClock clock;

    @Nullable
    private final ScheduledExecutorService executor;

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean shouldAutoClose() {
        return true;
    }

    public static WatchdogProvider create() {
        return new InstantiatingWatchdogProvider(null, null, null);
    }

    private InstantiatingWatchdogProvider(@Nullable ApiClock apiClock, @Nullable ScheduledExecutorService scheduledExecutorService, @Nullable Duration duration) {
        this.clock = apiClock;
        this.executor = scheduledExecutorService;
        this.checkInterval = duration;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean needsClock() {
        return this.clock == null;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public WatchdogProvider withClock(@Nonnull ApiClock apiClock) {
        return new InstantiatingWatchdogProvider((ApiClock) Preconditions.checkNotNull(apiClock), this.executor, this.checkInterval);
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean needsCheckInterval() {
        return this.checkInterval == null;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public WatchdogProvider withCheckInterval(@Nonnull Duration duration) {
        return new InstantiatingWatchdogProvider(this.clock, this.executor, (Duration) Preconditions.checkNotNull(duration));
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean needsExecutor() {
        return this.executor == null;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public WatchdogProvider withExecutor(ScheduledExecutorService scheduledExecutorService) {
        return new InstantiatingWatchdogProvider(this.clock, (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService), this.checkInterval);
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    @Nullable
    public Watchdog getWatchdog() {
        Preconditions.checkState(!needsClock(), "A clock is needed");
        Preconditions.checkState(!needsCheckInterval(), "A check interval is needed");
        Preconditions.checkState(!needsExecutor(), "An executor is needed");
        if (this.checkInterval.isZero()) {
            return null;
        }
        return Watchdog.create(this.clock, this.checkInterval, this.executor);
    }
}
