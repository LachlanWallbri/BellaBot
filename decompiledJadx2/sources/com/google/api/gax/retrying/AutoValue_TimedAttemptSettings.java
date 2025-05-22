package com.google.api.gax.retrying;

import com.google.api.gax.retrying.TimedAttemptSettings;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_TimedAttemptSettings extends TimedAttemptSettings {
    private final int attemptCount;
    private final long firstAttemptStartTimeNanos;
    private final RetrySettings globalSettings;
    private final int overallAttemptCount;
    private final Duration randomizedRetryDelay;
    private final Duration retryDelay;
    private final Duration rpcTimeout;

    private AutoValue_TimedAttemptSettings(RetrySettings retrySettings, Duration duration, Duration duration2, Duration duration3, int i, int i2, long j) {
        this.globalSettings = retrySettings;
        this.retryDelay = duration;
        this.rpcTimeout = duration2;
        this.randomizedRetryDelay = duration3;
        this.attemptCount = i;
        this.overallAttemptCount = i2;
        this.firstAttemptStartTimeNanos = j;
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public RetrySettings getGlobalSettings() {
        return this.globalSettings;
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public Duration getRetryDelay() {
        return this.retryDelay;
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public Duration getRpcTimeout() {
        return this.rpcTimeout;
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public Duration getRandomizedRetryDelay() {
        return this.randomizedRetryDelay;
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public int getAttemptCount() {
        return this.attemptCount;
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public int getOverallAttemptCount() {
        return this.overallAttemptCount;
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public long getFirstAttemptStartTimeNanos() {
        return this.firstAttemptStartTimeNanos;
    }

    public String toString() {
        return "TimedAttemptSettings{globalSettings=" + this.globalSettings + ", retryDelay=" + this.retryDelay + ", rpcTimeout=" + this.rpcTimeout + ", randomizedRetryDelay=" + this.randomizedRetryDelay + ", attemptCount=" + this.attemptCount + ", overallAttemptCount=" + this.overallAttemptCount + ", firstAttemptStartTimeNanos=" + this.firstAttemptStartTimeNanos + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimedAttemptSettings)) {
            return false;
        }
        TimedAttemptSettings timedAttemptSettings = (TimedAttemptSettings) obj;
        return this.globalSettings.equals(timedAttemptSettings.getGlobalSettings()) && this.retryDelay.equals(timedAttemptSettings.getRetryDelay()) && this.rpcTimeout.equals(timedAttemptSettings.getRpcTimeout()) && this.randomizedRetryDelay.equals(timedAttemptSettings.getRandomizedRetryDelay()) && this.attemptCount == timedAttemptSettings.getAttemptCount() && this.overallAttemptCount == timedAttemptSettings.getOverallAttemptCount() && this.firstAttemptStartTimeNanos == timedAttemptSettings.getFirstAttemptStartTimeNanos();
    }

    public int hashCode() {
        long hashCode = (((((((((((this.globalSettings.hashCode() ^ 1000003) * 1000003) ^ this.retryDelay.hashCode()) * 1000003) ^ this.rpcTimeout.hashCode()) * 1000003) ^ this.randomizedRetryDelay.hashCode()) * 1000003) ^ this.attemptCount) * 1000003) ^ this.overallAttemptCount) * 1000003;
        long j = this.firstAttemptStartTimeNanos;
        return (int) (hashCode ^ (j ^ (j >>> 32)));
    }

    @Override // com.google.api.gax.retrying.TimedAttemptSettings
    public TimedAttemptSettings.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends TimedAttemptSettings.Builder {
        private Integer attemptCount;
        private Long firstAttemptStartTimeNanos;
        private RetrySettings globalSettings;
        private Integer overallAttemptCount;
        private Duration randomizedRetryDelay;
        private Duration retryDelay;
        private Duration rpcTimeout;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(TimedAttemptSettings timedAttemptSettings) {
            this.globalSettings = timedAttemptSettings.getGlobalSettings();
            this.retryDelay = timedAttemptSettings.getRetryDelay();
            this.rpcTimeout = timedAttemptSettings.getRpcTimeout();
            this.randomizedRetryDelay = timedAttemptSettings.getRandomizedRetryDelay();
            this.attemptCount = Integer.valueOf(timedAttemptSettings.getAttemptCount());
            this.overallAttemptCount = Integer.valueOf(timedAttemptSettings.getOverallAttemptCount());
            this.firstAttemptStartTimeNanos = Long.valueOf(timedAttemptSettings.getFirstAttemptStartTimeNanos());
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings.Builder setGlobalSettings(RetrySettings retrySettings) {
            if (retrySettings == null) {
                throw new NullPointerException("Null globalSettings");
            }
            this.globalSettings = retrySettings;
            return this;
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings.Builder setRetryDelay(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null retryDelay");
            }
            this.retryDelay = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings.Builder setRpcTimeout(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null rpcTimeout");
            }
            this.rpcTimeout = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings.Builder setRandomizedRetryDelay(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null randomizedRetryDelay");
            }
            this.randomizedRetryDelay = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings.Builder setAttemptCount(int i) {
            this.attemptCount = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings.Builder setOverallAttemptCount(int i) {
            this.overallAttemptCount = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings.Builder setFirstAttemptStartTimeNanos(long j) {
            this.firstAttemptStartTimeNanos = Long.valueOf(j);
            return this;
        }

        @Override // com.google.api.gax.retrying.TimedAttemptSettings.Builder
        public TimedAttemptSettings build() {
            String str = "";
            if (this.globalSettings == null) {
                str = " globalSettings";
            }
            if (this.retryDelay == null) {
                str = str + " retryDelay";
            }
            if (this.rpcTimeout == null) {
                str = str + " rpcTimeout";
            }
            if (this.randomizedRetryDelay == null) {
                str = str + " randomizedRetryDelay";
            }
            if (this.attemptCount == null) {
                str = str + " attemptCount";
            }
            if (this.overallAttemptCount == null) {
                str = str + " overallAttemptCount";
            }
            if (this.firstAttemptStartTimeNanos == null) {
                str = str + " firstAttemptStartTimeNanos";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_TimedAttemptSettings(this.globalSettings, this.retryDelay, this.rpcTimeout, this.randomizedRetryDelay, this.attemptCount.intValue(), this.overallAttemptCount.intValue(), this.firstAttemptStartTimeNanos.longValue());
        }
    }
}
