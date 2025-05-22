package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface WatchdogProvider {
    Watchdog getWatchdog();

    boolean needsCheckInterval();

    boolean needsClock();

    boolean needsExecutor();

    boolean shouldAutoClose();

    WatchdogProvider withCheckInterval(Duration duration);

    WatchdogProvider withClock(@Nonnull ApiClock apiClock);

    WatchdogProvider withExecutor(ScheduledExecutorService scheduledExecutorService);
}
