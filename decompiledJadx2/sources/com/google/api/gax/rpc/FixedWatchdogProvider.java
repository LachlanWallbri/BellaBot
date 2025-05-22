package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class FixedWatchdogProvider implements WatchdogProvider {

    @Nullable
    private final Watchdog watchdog;

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean needsCheckInterval() {
        return false;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean needsClock() {
        return false;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean needsExecutor() {
        return false;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public boolean shouldAutoClose() {
        return false;
    }

    public static WatchdogProvider create(Watchdog watchdog) {
        return new FixedWatchdogProvider(watchdog);
    }

    private FixedWatchdogProvider(Watchdog watchdog) {
        this.watchdog = watchdog;
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public WatchdogProvider withClock(@Nonnull ApiClock apiClock) {
        throw new UnsupportedOperationException("FixedWatchdogProvider doesn't need a clock");
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public WatchdogProvider withCheckInterval(Duration duration) {
        throw new UnsupportedOperationException("FixedWatchdogProvider doesn't need a checkInterval");
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public WatchdogProvider withExecutor(ScheduledExecutorService scheduledExecutorService) {
        throw new UnsupportedOperationException("FixedWatchdogProvider doesn't need an executor");
    }

    @Override // com.google.api.gax.rpc.WatchdogProvider
    public Watchdog getWatchdog() {
        return this.watchdog;
    }
}
