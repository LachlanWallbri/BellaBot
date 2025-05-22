package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.SimpleStreamResumptionStrategy;
import com.google.api.gax.retrying.StreamResumptionStrategy;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.StreamingCallSettings;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nonnull;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public final class ServerStreamingCallSettings<RequestT, ResponseT> extends StreamingCallSettings<RequestT, ResponseT> {

    @Nonnull
    private final Duration idleTimeout;

    @Nonnull
    private final StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy;

    @Nonnull
    private final RetrySettings retrySettings;

    @Nonnull
    private final Set<StatusCode.Code> retryableCodes;

    private ServerStreamingCallSettings(Builder<RequestT, ResponseT> builder) {
        this.retryableCodes = ImmutableSet.copyOf((Collection) ((Builder) builder).retryableCodes);
        this.retrySettings = ((Builder) builder).retrySettingsBuilder.build();
        this.resumptionStrategy = ((Builder) builder).resumptionStrategy;
        this.idleTimeout = ((Builder) builder).idleTimeout;
    }

    @Nonnull
    public Set<StatusCode.Code> getRetryableCodes() {
        return this.retryableCodes;
    }

    @Nonnull
    public RetrySettings getRetrySettings() {
        return this.retrySettings;
    }

    @Nonnull
    public StreamResumptionStrategy<RequestT, ResponseT> getResumptionStrategy() {
        return this.resumptionStrategy;
    }

    @Nonnull
    public Duration getIdleTimeout() {
        return this.idleTimeout;
    }

    @Override // com.google.api.gax.rpc.StreamingCallSettings
    public Builder<RequestT, ResponseT> toBuilder() {
        return new Builder<>();
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder() {
        return new Builder<>();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("idleTimeout", this.idleTimeout).add("retryableCodes", this.retryableCodes).add("retrySettings", this.retrySettings).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<RequestT, ResponseT> extends StreamingCallSettings.Builder<RequestT, ResponseT> {

        @Nonnull
        private Duration idleTimeout;

        @Nonnull
        private StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy;

        @Nonnull
        private RetrySettings.Builder retrySettingsBuilder;

        @Nonnull
        private Set<StatusCode.Code> retryableCodes;

        private Builder() {
            this.retryableCodes = ImmutableSet.m676of();
            this.retrySettingsBuilder = RetrySettings.newBuilder();
            this.resumptionStrategy = new SimpleStreamResumptionStrategy();
            this.idleTimeout = Duration.ZERO;
        }

        private Builder(ServerStreamingCallSettings<RequestT, ResponseT> serverStreamingCallSettings) {
            super(serverStreamingCallSettings);
            this.retryableCodes = ((ServerStreamingCallSettings) serverStreamingCallSettings).retryableCodes;
            this.retrySettingsBuilder = ((ServerStreamingCallSettings) serverStreamingCallSettings).retrySettings.toBuilder();
            this.resumptionStrategy = ((ServerStreamingCallSettings) serverStreamingCallSettings).resumptionStrategy;
            this.idleTimeout = ((ServerStreamingCallSettings) serverStreamingCallSettings).idleTimeout;
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(StatusCode.Code... codeArr) {
            setRetryableCodes(Sets.newHashSet(codeArr));
            return this;
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(Set<StatusCode.Code> set) {
            Preconditions.checkNotNull(set);
            this.retryableCodes = Sets.newHashSet(set);
            return this;
        }

        @Nonnull
        public Set<StatusCode.Code> getRetryableCodes() {
            return this.retryableCodes;
        }

        public RetrySettings.Builder retrySettings() {
            return this.retrySettingsBuilder;
        }

        public Builder<RequestT, ResponseT> setRetrySettings(@Nonnull RetrySettings retrySettings) {
            Preconditions.checkNotNull(retrySettings);
            this.retrySettingsBuilder = retrySettings.toBuilder();
            return this;
        }

        @Nonnull
        public RetrySettings getRetrySettings() {
            return this.retrySettingsBuilder.build();
        }

        public Builder<RequestT, ResponseT> setSimpleTimeoutNoRetries(@Nonnull Duration duration) {
            setRetryableCodes(new StatusCode.Code[0]);
            setRetrySettings(RetrySettings.newBuilder().setTotalTimeout(duration).setInitialRetryDelay(Duration.ZERO).setRetryDelayMultiplier(1.0d).setMaxRetryDelay(Duration.ZERO).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ZERO).setMaxAttempts(1).build());
            return this;
        }

        public Builder<RequestT, ResponseT> setResumptionStrategy(@Nonnull StreamResumptionStrategy<RequestT, ResponseT> streamResumptionStrategy) {
            Preconditions.checkNotNull(streamResumptionStrategy);
            this.resumptionStrategy = (StreamResumptionStrategy) Preconditions.checkNotNull(streamResumptionStrategy);
            return this;
        }

        @Nonnull
        public StreamResumptionStrategy<RequestT, ResponseT> getResumptionStrategy() {
            return this.resumptionStrategy;
        }

        @Nonnull
        public Duration getIdleTimeout() {
            return this.idleTimeout;
        }

        public Builder<RequestT, ResponseT> setIdleTimeout(@Nonnull Duration duration) {
            this.idleTimeout = (Duration) Preconditions.checkNotNull(duration);
            return this;
        }

        @Override // com.google.api.gax.rpc.StreamingCallSettings.Builder
        public ServerStreamingCallSettings<RequestT, ResponseT> build() {
            return new ServerStreamingCallSettings<>(this);
        }
    }
}
