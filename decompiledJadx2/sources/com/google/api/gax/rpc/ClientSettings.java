package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class ClientSettings<SettingsT extends ClientSettings<SettingsT>> {
    private final StubSettings stubSettings;

    public abstract <B extends Builder<SettingsT, B>> B toBuilder();

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientSettings(Builder builder) throws IOException {
        this.stubSettings = builder.stubSettings.build();
    }

    public final StubSettings getStubSettings() {
        return this.stubSettings;
    }

    public final ExecutorProvider getExecutorProvider() {
        return this.stubSettings.getExecutorProvider();
    }

    public final TransportChannelProvider getTransportChannelProvider() {
        return this.stubSettings.getTransportChannelProvider();
    }

    public final CredentialsProvider getCredentialsProvider() {
        return this.stubSettings.getCredentialsProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public final HeaderProvider getHeaderProvider() {
        return this.stubSettings.getHeaderProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    protected final HeaderProvider getInternalHeaderProvider() {
        return this.stubSettings.getInternalHeaderProvider();
    }

    public final ApiClock getClock() {
        return this.stubSettings.getClock();
    }

    public final String getEndpoint() {
        return this.stubSettings.getEndpoint();
    }

    public final String getQuotaProjectId() {
        return this.stubSettings.getQuotaProjectId();
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public final WatchdogProvider getWatchdogProvider() {
        return this.stubSettings.getStreamWatchdogProvider();
    }

    @Nonnull
    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public final Duration getWatchdogCheckInterval() {
        return this.stubSettings.getStreamWatchdogCheckInterval();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("executorProvider", getExecutorProvider()).add("transportChannelProvider", getTransportChannelProvider()).add("credentialsProvider", getCredentialsProvider()).add("headerProvider", getHeaderProvider()).add("internalHeaderProvider", getInternalHeaderProvider()).add("clock", getClock()).add("endpoint", getEndpoint()).add("quotaProjectId", getQuotaProjectId()).add("watchdogProvider", getWatchdogProvider()).add("watchdogCheckInterval", getWatchdogCheckInterval()).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder<SettingsT extends ClientSettings<SettingsT>, B extends Builder<SettingsT, B>> {
        private StubSettings.Builder stubSettings;

        public abstract SettingsT build() throws IOException;

        protected B self() {
            return this;
        }

        protected Builder(ClientSettings clientSettings) {
            this.stubSettings = clientSettings.stubSettings.toBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(StubSettings.Builder builder) {
            this.stubSettings = builder;
        }

        protected Builder() {
            this((StubSettings.Builder) null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public StubSettings.Builder getStubSettings() {
            return this.stubSettings;
        }

        public B setExecutorProvider(ExecutorProvider executorProvider) {
            this.stubSettings.setExecutorProvider(executorProvider);
            return self();
        }

        public B setCredentialsProvider(CredentialsProvider credentialsProvider) {
            this.stubSettings.setCredentialsProvider(credentialsProvider);
            return self();
        }

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        public B setHeaderProvider(HeaderProvider headerProvider) {
            this.stubSettings.setHeaderProvider(headerProvider);
            return self();
        }

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        protected B setInternalHeaderProvider(HeaderProvider headerProvider) {
            this.stubSettings.setInternalHeaderProvider(headerProvider);
            return self();
        }

        public B setTransportChannelProvider(TransportChannelProvider transportChannelProvider) {
            this.stubSettings.setTransportChannelProvider(transportChannelProvider);
            return self();
        }

        public B setClock(ApiClock apiClock) {
            this.stubSettings.setClock(apiClock);
            return self();
        }

        public B setEndpoint(String str) {
            this.stubSettings.setEndpoint(str);
            return self();
        }

        public B setQuotaProjectId(String str) {
            this.stubSettings.setQuotaProjectId(str);
            return self();
        }

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        public B setWatchdogProvider(@Nullable WatchdogProvider watchdogProvider) {
            this.stubSettings.setStreamWatchdogProvider(watchdogProvider);
            return self();
        }

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        public B setWatchdogCheckInterval(@Nullable Duration duration) {
            this.stubSettings.setStreamWatchdogCheckInterval(duration);
            return self();
        }

        public ExecutorProvider getExecutorProvider() {
            return this.stubSettings.getExecutorProvider();
        }

        public TransportChannelProvider getTransportChannelProvider() {
            return this.stubSettings.getTransportChannelProvider();
        }

        public CredentialsProvider getCredentialsProvider() {
            return this.stubSettings.getCredentialsProvider();
        }

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        public HeaderProvider getHeaderProvider() {
            return this.stubSettings.getHeaderProvider();
        }

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        protected HeaderProvider getInternalHeaderProvider() {
            return this.stubSettings.getInternalHeaderProvider();
        }

        public ApiClock getClock() {
            return this.stubSettings.getClock();
        }

        public String getEndpoint() {
            return this.stubSettings.getEndpoint();
        }

        public String getQuotaProjectId() {
            return this.stubSettings.getQuotaProjectId();
        }

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        @Nullable
        public WatchdogProvider getWatchdogProvider() {
            return this.stubSettings.getStreamWatchdogProvider();
        }

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        @Nullable
        public Duration getWatchdogCheckInterval() {
            return this.stubSettings.getStreamWatchdogCheckInterval();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static void applyToAllUnaryMethods(Iterable<UnaryCallSettings.Builder<?, ?>> iterable, ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) {
            StubSettings.Builder.applyToAllUnaryMethods(iterable, apiFunction);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("executorProvider", getExecutorProvider()).add("transportChannelProvider", getTransportChannelProvider()).add("credentialsProvider", getCredentialsProvider()).add("headerProvider", getHeaderProvider()).add("internalHeaderProvider", getInternalHeaderProvider()).add("clock", getClock()).add("endpoint", getEndpoint()).add("quotaProjectId", getQuotaProjectId()).add("watchdogProvider", getWatchdogProvider()).add("watchdogCheckInterval", getWatchdogCheckInterval()).toString();
        }
    }
}
