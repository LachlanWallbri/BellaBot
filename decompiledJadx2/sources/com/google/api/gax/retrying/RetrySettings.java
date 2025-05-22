package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.AutoValue_RetrySettings;
import java.io.Serializable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class RetrySettings implements Serializable {
    private static final long serialVersionUID = 8258475264439710899L;

    public abstract Duration getInitialRetryDelay();

    public abstract Duration getInitialRpcTimeout();

    public abstract int getMaxAttempts();

    public abstract Duration getMaxRetryDelay();

    public abstract Duration getMaxRpcTimeout();

    public abstract double getRetryDelayMultiplier();

    public abstract double getRpcTimeoutMultiplier();

    public abstract Duration getTotalTimeout();

    @Deprecated
    public abstract boolean isJittered();

    public abstract Builder toBuilder();

    public static Builder newBuilder() {
        return new AutoValue_RetrySettings.Builder().setTotalTimeout(Duration.ZERO).setInitialRetryDelay(Duration.ZERO).setRetryDelayMultiplier(1.0d).setMaxRetryDelay(Duration.ZERO).setMaxAttempts(0).setJittered(true).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        abstract RetrySettings autoBuild();

        public abstract Duration getInitialRetryDelay();

        public abstract Duration getInitialRpcTimeout();

        public abstract int getMaxAttempts();

        public abstract Duration getMaxRetryDelay();

        public abstract Duration getMaxRpcTimeout();

        public abstract double getRetryDelayMultiplier();

        public abstract double getRpcTimeoutMultiplier();

        public abstract Duration getTotalTimeout();

        public abstract boolean isJittered();

        public abstract Builder setInitialRetryDelay(Duration duration);

        public abstract Builder setInitialRpcTimeout(Duration duration);

        @Deprecated
        public abstract Builder setJittered(boolean z);

        public abstract Builder setMaxAttempts(int i);

        public abstract Builder setMaxRetryDelay(Duration duration);

        public abstract Builder setMaxRpcTimeout(Duration duration);

        public abstract Builder setRetryDelayMultiplier(double d);

        public abstract Builder setRpcTimeoutMultiplier(double d);

        public abstract Builder setTotalTimeout(Duration duration);

        @BetaApi
        public Builder setLogicalTimeout(Duration duration) {
            return setRpcTimeoutMultiplier(1.0d).setInitialRpcTimeout(duration).setMaxRpcTimeout(duration).setTotalTimeout(duration);
        }

        public RetrySettings build() {
            RetrySettings autoBuild = autoBuild();
            if (autoBuild.getTotalTimeout().toMillis() < 0) {
                throw new IllegalStateException("total timeout must not be negative");
            }
            if (autoBuild.getInitialRetryDelay().toMillis() < 0) {
                throw new IllegalStateException("initial retry delay must not be negative");
            }
            if (autoBuild.getRetryDelayMultiplier() < 1.0d) {
                throw new IllegalStateException("retry delay multiplier must be at least 1");
            }
            if (autoBuild.getMaxRetryDelay().compareTo(autoBuild.getInitialRetryDelay()) < 0) {
                throw new IllegalStateException("max retry delay must not be shorter than initial delay");
            }
            if (autoBuild.getMaxAttempts() < 0) {
                throw new IllegalStateException("max attempts must be non-negative");
            }
            if (autoBuild.getInitialRpcTimeout().toMillis() < 0) {
                throw new IllegalStateException("initial rpc timeout must not be negative");
            }
            if (autoBuild.getMaxRpcTimeout().compareTo(autoBuild.getInitialRpcTimeout()) < 0) {
                throw new IllegalStateException("max rpc timeout must not be shorter than initial timeout");
            }
            if (autoBuild.getRpcTimeoutMultiplier() >= 1.0d) {
                return autoBuild;
            }
            throw new IllegalStateException("rpc timeout multiplier must be at least 1");
        }

        public Builder merge(Builder builder) {
            if (builder.getTotalTimeout() != null) {
                setTotalTimeout(builder.getTotalTimeout());
            }
            if (builder.getInitialRetryDelay() != null) {
                setInitialRetryDelay(builder.getInitialRetryDelay());
            }
            if (builder.getRetryDelayMultiplier() >= 1.0d) {
                setRetryDelayMultiplier(builder.getRetryDelayMultiplier());
            }
            if (builder.getMaxRetryDelay() != null) {
                setMaxRetryDelay(builder.getMaxRetryDelay());
            }
            setMaxAttempts(builder.getMaxAttempts());
            setJittered(builder.isJittered());
            if (builder.getInitialRpcTimeout() != null) {
                setInitialRpcTimeout(builder.getInitialRpcTimeout());
            }
            if (builder.getRpcTimeoutMultiplier() >= 1.0d) {
                setRpcTimeoutMultiplier(builder.getRpcTimeoutMultiplier());
            }
            if (builder.getMaxRpcTimeout() != null) {
                setMaxRpcTimeout(builder.getMaxRpcTimeout());
            }
            return this;
        }
    }
}
