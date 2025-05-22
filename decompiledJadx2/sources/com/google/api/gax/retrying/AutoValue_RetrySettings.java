package com.google.api.gax.retrying;

import com.google.api.gax.retrying.RetrySettings;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_RetrySettings extends RetrySettings {
    private static final long serialVersionUID = 8258475264439710899L;
    private final Duration initialRetryDelay;
    private final Duration initialRpcTimeout;
    private final boolean jittered;
    private final int maxAttempts;
    private final Duration maxRetryDelay;
    private final Duration maxRpcTimeout;
    private final double retryDelayMultiplier;
    private final double rpcTimeoutMultiplier;
    private final Duration totalTimeout;

    private AutoValue_RetrySettings(Duration duration, Duration duration2, double d, Duration duration3, int i, boolean z, Duration duration4, double d2, Duration duration5) {
        this.totalTimeout = duration;
        this.initialRetryDelay = duration2;
        this.retryDelayMultiplier = d;
        this.maxRetryDelay = duration3;
        this.maxAttempts = i;
        this.jittered = z;
        this.initialRpcTimeout = duration4;
        this.rpcTimeoutMultiplier = d2;
        this.maxRpcTimeout = duration5;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public Duration getTotalTimeout() {
        return this.totalTimeout;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public Duration getInitialRetryDelay() {
        return this.initialRetryDelay;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public double getRetryDelayMultiplier() {
        return this.retryDelayMultiplier;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public Duration getMaxRetryDelay() {
        return this.maxRetryDelay;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    @Deprecated
    public boolean isJittered() {
        return this.jittered;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public Duration getInitialRpcTimeout() {
        return this.initialRpcTimeout;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public double getRpcTimeoutMultiplier() {
        return this.rpcTimeoutMultiplier;
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public Duration getMaxRpcTimeout() {
        return this.maxRpcTimeout;
    }

    public String toString() {
        return "RetrySettings{totalTimeout=" + this.totalTimeout + ", initialRetryDelay=" + this.initialRetryDelay + ", retryDelayMultiplier=" + this.retryDelayMultiplier + ", maxRetryDelay=" + this.maxRetryDelay + ", maxAttempts=" + this.maxAttempts + ", jittered=" + this.jittered + ", initialRpcTimeout=" + this.initialRpcTimeout + ", rpcTimeoutMultiplier=" + this.rpcTimeoutMultiplier + ", maxRpcTimeout=" + this.maxRpcTimeout + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RetrySettings)) {
            return false;
        }
        RetrySettings retrySettings = (RetrySettings) obj;
        return this.totalTimeout.equals(retrySettings.getTotalTimeout()) && this.initialRetryDelay.equals(retrySettings.getInitialRetryDelay()) && Double.doubleToLongBits(this.retryDelayMultiplier) == Double.doubleToLongBits(retrySettings.getRetryDelayMultiplier()) && this.maxRetryDelay.equals(retrySettings.getMaxRetryDelay()) && this.maxAttempts == retrySettings.getMaxAttempts() && this.jittered == retrySettings.isJittered() && this.initialRpcTimeout.equals(retrySettings.getInitialRpcTimeout()) && Double.doubleToLongBits(this.rpcTimeoutMultiplier) == Double.doubleToLongBits(retrySettings.getRpcTimeoutMultiplier()) && this.maxRpcTimeout.equals(retrySettings.getMaxRpcTimeout());
    }

    public int hashCode() {
        return (((int) ((((((((((((int) (((((this.totalTimeout.hashCode() ^ 1000003) * 1000003) ^ this.initialRetryDelay.hashCode()) * 1000003) ^ ((Double.doubleToLongBits(this.retryDelayMultiplier) >>> 32) ^ Double.doubleToLongBits(this.retryDelayMultiplier)))) * 1000003) ^ this.maxRetryDelay.hashCode()) * 1000003) ^ this.maxAttempts) * 1000003) ^ (this.jittered ? 1231 : 1237)) * 1000003) ^ this.initialRpcTimeout.hashCode()) * 1000003) ^ ((Double.doubleToLongBits(this.rpcTimeoutMultiplier) >>> 32) ^ Double.doubleToLongBits(this.rpcTimeoutMultiplier)))) * 1000003) ^ this.maxRpcTimeout.hashCode();
    }

    @Override // com.google.api.gax.retrying.RetrySettings
    public RetrySettings.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends RetrySettings.Builder {
        private Duration initialRetryDelay;
        private Duration initialRpcTimeout;
        private Boolean jittered;
        private Integer maxAttempts;
        private Duration maxRetryDelay;
        private Duration maxRpcTimeout;
        private Double retryDelayMultiplier;
        private Double rpcTimeoutMultiplier;
        private Duration totalTimeout;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(RetrySettings retrySettings) {
            this.totalTimeout = retrySettings.getTotalTimeout();
            this.initialRetryDelay = retrySettings.getInitialRetryDelay();
            this.retryDelayMultiplier = Double.valueOf(retrySettings.getRetryDelayMultiplier());
            this.maxRetryDelay = retrySettings.getMaxRetryDelay();
            this.maxAttempts = Integer.valueOf(retrySettings.getMaxAttempts());
            this.jittered = Boolean.valueOf(retrySettings.isJittered());
            this.initialRpcTimeout = retrySettings.getInitialRpcTimeout();
            this.rpcTimeoutMultiplier = Double.valueOf(retrySettings.getRpcTimeoutMultiplier());
            this.maxRpcTimeout = retrySettings.getMaxRpcTimeout();
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setTotalTimeout(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null totalTimeout");
            }
            this.totalTimeout = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public Duration getTotalTimeout() {
            Duration duration = this.totalTimeout;
            if (duration != null) {
                return duration;
            }
            throw new IllegalStateException("Property \"totalTimeout\" has not been set");
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setInitialRetryDelay(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null initialRetryDelay");
            }
            this.initialRetryDelay = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public Duration getInitialRetryDelay() {
            Duration duration = this.initialRetryDelay;
            if (duration != null) {
                return duration;
            }
            throw new IllegalStateException("Property \"initialRetryDelay\" has not been set");
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setRetryDelayMultiplier(double d) {
            this.retryDelayMultiplier = Double.valueOf(d);
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public double getRetryDelayMultiplier() {
            Double d = this.retryDelayMultiplier;
            if (d == null) {
                throw new IllegalStateException("Property \"retryDelayMultiplier\" has not been set");
            }
            return d.doubleValue();
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setMaxRetryDelay(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null maxRetryDelay");
            }
            this.maxRetryDelay = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public Duration getMaxRetryDelay() {
            Duration duration = this.maxRetryDelay;
            if (duration != null) {
                return duration;
            }
            throw new IllegalStateException("Property \"maxRetryDelay\" has not been set");
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setMaxAttempts(int i) {
            this.maxAttempts = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public int getMaxAttempts() {
            Integer num = this.maxAttempts;
            if (num == null) {
                throw new IllegalStateException("Property \"maxAttempts\" has not been set");
            }
            return num.intValue();
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setJittered(boolean z) {
            this.jittered = Boolean.valueOf(z);
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public boolean isJittered() {
            Boolean bool = this.jittered;
            if (bool == null) {
                throw new IllegalStateException("Property \"jittered\" has not been set");
            }
            return bool.booleanValue();
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setInitialRpcTimeout(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null initialRpcTimeout");
            }
            this.initialRpcTimeout = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public Duration getInitialRpcTimeout() {
            Duration duration = this.initialRpcTimeout;
            if (duration != null) {
                return duration;
            }
            throw new IllegalStateException("Property \"initialRpcTimeout\" has not been set");
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setRpcTimeoutMultiplier(double d) {
            this.rpcTimeoutMultiplier = Double.valueOf(d);
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public double getRpcTimeoutMultiplier() {
            Double d = this.rpcTimeoutMultiplier;
            if (d == null) {
                throw new IllegalStateException("Property \"rpcTimeoutMultiplier\" has not been set");
            }
            return d.doubleValue();
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public RetrySettings.Builder setMaxRpcTimeout(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null maxRpcTimeout");
            }
            this.maxRpcTimeout = duration;
            return this;
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        public Duration getMaxRpcTimeout() {
            Duration duration = this.maxRpcTimeout;
            if (duration != null) {
                return duration;
            }
            throw new IllegalStateException("Property \"maxRpcTimeout\" has not been set");
        }

        @Override // com.google.api.gax.retrying.RetrySettings.Builder
        RetrySettings autoBuild() {
            String str = "";
            if (this.totalTimeout == null) {
                str = " totalTimeout";
            }
            if (this.initialRetryDelay == null) {
                str = str + " initialRetryDelay";
            }
            if (this.retryDelayMultiplier == null) {
                str = str + " retryDelayMultiplier";
            }
            if (this.maxRetryDelay == null) {
                str = str + " maxRetryDelay";
            }
            if (this.maxAttempts == null) {
                str = str + " maxAttempts";
            }
            if (this.jittered == null) {
                str = str + " jittered";
            }
            if (this.initialRpcTimeout == null) {
                str = str + " initialRpcTimeout";
            }
            if (this.rpcTimeoutMultiplier == null) {
                str = str + " rpcTimeoutMultiplier";
            }
            if (this.maxRpcTimeout == null) {
                str = str + " maxRpcTimeout";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_RetrySettings(this.totalTimeout, this.initialRetryDelay, this.retryDelayMultiplier.doubleValue(), this.maxRetryDelay, this.maxAttempts.intValue(), this.jittered.booleanValue(), this.initialRpcTimeout, this.rpcTimeoutMultiplier.doubleValue(), this.maxRpcTimeout);
        }
    }
}
