package com.google.api.gax.retrying;

import com.google.api.core.ApiClock;
import com.google.common.base.Preconditions;
import java.util.concurrent.ThreadLocalRandom;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ExponentialRetryAlgorithm implements TimedRetryAlgorithmWithContext {
    private final ApiClock clock;
    private final RetrySettings globalSettings;

    public ExponentialRetryAlgorithm(RetrySettings retrySettings, ApiClock apiClock) {
        this.globalSettings = (RetrySettings) Preconditions.checkNotNull(retrySettings);
        this.clock = (ApiClock) Preconditions.checkNotNull(apiClock);
    }

    @Override // com.google.api.gax.retrying.TimedRetryAlgorithm
    public TimedAttemptSettings createFirstAttempt() {
        return TimedAttemptSettings.newBuilder().setGlobalSettings(this.globalSettings).setRetryDelay(Duration.ZERO).setRpcTimeout(this.globalSettings.getInitialRpcTimeout()).setRandomizedRetryDelay(Duration.ZERO).setAttemptCount(0).setOverallAttemptCount(0).setFirstAttemptStartTimeNanos(this.clock.nanoTime()).build();
    }

    @Override // com.google.api.gax.retrying.TimedRetryAlgorithmWithContext
    public TimedAttemptSettings createFirstAttempt(RetryingContext retryingContext) {
        if (retryingContext.getRetrySettings() == null) {
            return createFirstAttempt();
        }
        RetrySettings retrySettings = retryingContext.getRetrySettings();
        return TimedAttemptSettings.newBuilder().setGlobalSettings(retrySettings).setRpcTimeout(retrySettings.getInitialRpcTimeout()).setRetryDelay(Duration.ZERO).setRandomizedRetryDelay(Duration.ZERO).setAttemptCount(0).setOverallAttemptCount(0).setFirstAttemptStartTimeNanos(this.clock.nanoTime()).build();
    }

    @Override // com.google.api.gax.retrying.TimedRetryAlgorithm
    public TimedAttemptSettings createNextAttempt(TimedAttemptSettings timedAttemptSettings) {
        RetrySettings globalSettings = timedAttemptSettings.getGlobalSettings();
        long millis = globalSettings.getInitialRetryDelay().toMillis();
        if (timedAttemptSettings.getAttemptCount() > 0) {
            millis = Math.min((long) (globalSettings.getRetryDelayMultiplier() * timedAttemptSettings.getRetryDelay().toMillis()), globalSettings.getMaxRetryDelay().toMillis());
        }
        Duration ofMillis = Duration.ofMillis(nextRandomLong(millis));
        long min = Math.min((long) (globalSettings.getRpcTimeoutMultiplier() * timedAttemptSettings.getRpcTimeout().toMillis()), globalSettings.getMaxRpcTimeout().toMillis());
        if (!globalSettings.getTotalTimeout().isZero()) {
            min = Math.min(min, globalSettings.getTotalTimeout().minus(Duration.ofNanos(this.clock.nanoTime()).minus(Duration.ofNanos(timedAttemptSettings.getFirstAttemptStartTimeNanos()))).minus(ofMillis).toMillis());
        }
        return TimedAttemptSettings.newBuilder().setGlobalSettings(timedAttemptSettings.getGlobalSettings()).setRetryDelay(Duration.ofMillis(millis)).setRpcTimeout(Duration.ofMillis(min)).setRandomizedRetryDelay(ofMillis).setAttemptCount(timedAttemptSettings.getAttemptCount() + 1).setOverallAttemptCount(timedAttemptSettings.getOverallAttemptCount() + 1).setFirstAttemptStartTimeNanos(timedAttemptSettings.getFirstAttemptStartTimeNanos()).build();
    }

    @Override // com.google.api.gax.retrying.TimedRetryAlgorithmWithContext
    public TimedAttemptSettings createNextAttempt(RetryingContext retryingContext, TimedAttemptSettings timedAttemptSettings) {
        return createNextAttempt(timedAttemptSettings);
    }

    @Override // com.google.api.gax.retrying.TimedRetryAlgorithm
    public boolean shouldRetry(TimedAttemptSettings timedAttemptSettings) {
        RetrySettings globalSettings = timedAttemptSettings.getGlobalSettings();
        int maxAttempts = globalSettings.getMaxAttempts();
        long nanos = globalSettings.getTotalTimeout().toNanos();
        if (nanos == 0 && maxAttempts == 0) {
            return false;
        }
        long nanoTime = (this.clock.nanoTime() - timedAttemptSettings.getFirstAttemptStartTimeNanos()) + timedAttemptSettings.getRandomizedRetryDelay().toNanos();
        if (nanos <= 0 || nanoTime <= nanos) {
            return maxAttempts <= 0 || timedAttemptSettings.getAttemptCount() < maxAttempts;
        }
        return false;
    }

    @Override // com.google.api.gax.retrying.TimedRetryAlgorithmWithContext
    public boolean shouldRetry(RetryingContext retryingContext, TimedAttemptSettings timedAttemptSettings) {
        return shouldRetry(timedAttemptSettings);
    }

    protected long nextRandomLong(long j) {
        return (j <= 0 || !this.globalSettings.isJittered()) ? j : ThreadLocalRandom.current().nextLong(j);
    }
}
