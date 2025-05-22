package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.AutoValue_TimedAttemptSettings;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public abstract class TimedAttemptSettings {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract TimedAttemptSettings build();

        public abstract Builder setAttemptCount(int i);

        public abstract Builder setFirstAttemptStartTimeNanos(long j);

        public abstract Builder setGlobalSettings(RetrySettings retrySettings);

        public abstract Builder setOverallAttemptCount(int i);

        public abstract Builder setRandomizedRetryDelay(Duration duration);

        public abstract Builder setRetryDelay(Duration duration);

        public abstract Builder setRpcTimeout(Duration duration);
    }

    public abstract int getAttemptCount();

    public abstract long getFirstAttemptStartTimeNanos();

    public abstract RetrySettings getGlobalSettings();

    public abstract int getOverallAttemptCount();

    public abstract Duration getRandomizedRetryDelay();

    public abstract Duration getRetryDelay();

    public abstract Duration getRpcTimeout();

    public abstract Builder toBuilder();

    public static Builder newBuilder() {
        return new AutoValue_TimedAttemptSettings.Builder().setOverallAttemptCount(0);
    }
}
