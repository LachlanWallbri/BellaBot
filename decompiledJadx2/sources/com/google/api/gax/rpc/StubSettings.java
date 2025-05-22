package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.core.NanoClock;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.core.FixedExecutorProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.api.gax.tracing.NoopApiTracerFactory;
import com.google.auth.oauth2.QuotaProjectIdProvider;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class StubSettings<SettingsT extends StubSettings<SettingsT>> {
    static final String QUOTA_PROJECT_ID_HEADER_KEY = "x-goog-user-project";
    private final ApiClock clock;
    private final CredentialsProvider credentialsProvider;
    private final String endpoint;
    private final ExecutorProvider executorProvider;
    private final HeaderProvider headerProvider;
    private final HeaderProvider internalHeaderProvider;
    private final String mtlsEndpoint;
    private final String quotaProjectId;

    @Nonnull
    private final Duration streamWatchdogCheckInterval;

    @Nullable
    private final WatchdogProvider streamWatchdogProvider;
    private final boolean switchToMtlsEndpointAllowed;

    @Nonnull
    private final ApiTracerFactory tracerFactory;
    private final TransportChannelProvider transportChannelProvider;

    public abstract Builder toBuilder();

    /* JADX INFO: Access modifiers changed from: protected */
    public StubSettings(Builder builder) {
        this.executorProvider = builder.executorProvider;
        this.transportChannelProvider = builder.transportChannelProvider;
        this.credentialsProvider = builder.credentialsProvider;
        this.headerProvider = builder.headerProvider;
        this.internalHeaderProvider = builder.internalHeaderProvider;
        this.clock = builder.clock;
        this.endpoint = builder.endpoint;
        this.mtlsEndpoint = builder.mtlsEndpoint;
        this.switchToMtlsEndpointAllowed = builder.switchToMtlsEndpointAllowed;
        this.quotaProjectId = builder.quotaProjectId;
        this.streamWatchdogProvider = builder.streamWatchdogProvider;
        this.streamWatchdogCheckInterval = builder.streamWatchdogCheckInterval;
        this.tracerFactory = builder.tracerFactory;
    }

    public final ExecutorProvider getExecutorProvider() {
        return this.executorProvider;
    }

    public final TransportChannelProvider getTransportChannelProvider() {
        return this.transportChannelProvider;
    }

    public final CredentialsProvider getCredentialsProvider() {
        return this.credentialsProvider;
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public final HeaderProvider getHeaderProvider() {
        return this.headerProvider;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public final HeaderProvider getInternalHeaderProvider() {
        return this.internalHeaderProvider;
    }

    public final ApiClock getClock() {
        return this.clock;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final String getMtlsEndpoint() {
        return this.mtlsEndpoint;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean getSwitchToMtlsEndpointAllowed() {
        return this.switchToMtlsEndpointAllowed;
    }

    public final String getQuotaProjectId() {
        return this.quotaProjectId;
    }

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public final WatchdogProvider getStreamWatchdogProvider() {
        return this.streamWatchdogProvider;
    }

    @Nonnull
    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public final Duration getStreamWatchdogCheckInterval() {
        return this.streamWatchdogCheckInterval;
    }

    @Nonnull
    @BetaApi("The surface for tracing is not stable yet and may change in the future.")
    public ApiTracerFactory getTracerFactory() {
        return this.tracerFactory;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("executorProvider", this.executorProvider).add("transportChannelProvider", this.transportChannelProvider).add("credentialsProvider", this.credentialsProvider).add("headerProvider", this.headerProvider).add("internalHeaderProvider", this.internalHeaderProvider).add("clock", this.clock).add("endpoint", this.endpoint).add("mtlsEndpoint", this.mtlsEndpoint).add("switchToMtlsEndpointAllowed", this.switchToMtlsEndpointAllowed).add("quotaProjectId", this.quotaProjectId).add("streamWatchdogProvider", this.streamWatchdogProvider).add("streamWatchdogCheckInterval", this.streamWatchdogCheckInterval).add("tracerFactory", this.tracerFactory).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder<SettingsT extends StubSettings<SettingsT>, B extends Builder<SettingsT, B>> {
        private ApiClock clock;
        private CredentialsProvider credentialsProvider;
        private String endpoint;
        private ExecutorProvider executorProvider;
        private HeaderProvider headerProvider;
        private HeaderProvider internalHeaderProvider;
        private String mtlsEndpoint;
        private String quotaProjectId;

        @Nonnull
        private Duration streamWatchdogCheckInterval;

        @Nullable
        private WatchdogProvider streamWatchdogProvider;
        private boolean switchToMtlsEndpointAllowed;

        @Nonnull
        private ApiTracerFactory tracerFactory;
        private TransportChannelProvider transportChannelProvider;

        public abstract <B extends StubSettings<B>> StubSettings<B> build() throws IOException;

        protected B self() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(StubSettings stubSettings) {
            this.switchToMtlsEndpointAllowed = false;
            this.executorProvider = stubSettings.executorProvider;
            this.transportChannelProvider = stubSettings.transportChannelProvider;
            this.credentialsProvider = stubSettings.credentialsProvider;
            this.headerProvider = stubSettings.headerProvider;
            this.internalHeaderProvider = stubSettings.internalHeaderProvider;
            this.clock = stubSettings.clock;
            this.endpoint = stubSettings.endpoint;
            this.mtlsEndpoint = stubSettings.mtlsEndpoint;
            this.switchToMtlsEndpointAllowed = stubSettings.switchToMtlsEndpointAllowed;
            this.quotaProjectId = stubSettings.quotaProjectId;
            this.streamWatchdogProvider = stubSettings.streamWatchdogProvider;
            this.streamWatchdogCheckInterval = stubSettings.streamWatchdogCheckInterval;
            this.tracerFactory = stubSettings.tracerFactory;
        }

        private static String getQuotaProjectIdFromClientContext(ClientContext clientContext) {
            if (clientContext.getQuotaProjectId() != null) {
                return clientContext.getQuotaProjectId();
            }
            if (clientContext.getCredentials() instanceof QuotaProjectIdProvider) {
                return ((QuotaProjectIdProvider) clientContext.getCredentials()).getQuotaProjectId();
            }
            if (clientContext.getHeaders().containsKey(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY)) {
                return clientContext.getHeaders().get(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY);
            }
            if (clientContext.getInternalHeaders().containsKey(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY)) {
                return clientContext.getInternalHeaders().get(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(ClientContext clientContext) {
            this.switchToMtlsEndpointAllowed = false;
            if (clientContext == null) {
                this.executorProvider = InstantiatingExecutorProvider.newBuilder().build();
                this.transportChannelProvider = null;
                this.credentialsProvider = NoCredentialsProvider.create();
                this.headerProvider = new NoHeaderProvider();
                this.internalHeaderProvider = new NoHeaderProvider();
                this.clock = NanoClock.getDefaultClock();
                this.endpoint = null;
                this.mtlsEndpoint = null;
                this.quotaProjectId = null;
                this.streamWatchdogProvider = InstantiatingWatchdogProvider.create();
                this.streamWatchdogCheckInterval = Duration.ofSeconds(10L);
                this.tracerFactory = NoopApiTracerFactory.getInstance();
                return;
            }
            this.executorProvider = FixedExecutorProvider.create(clientContext.getExecutor());
            this.transportChannelProvider = FixedTransportChannelProvider.create(clientContext.getTransportChannel());
            this.credentialsProvider = FixedCredentialsProvider.create(clientContext.getCredentials());
            this.headerProvider = FixedHeaderProvider.create(clientContext.getHeaders());
            this.internalHeaderProvider = FixedHeaderProvider.create(clientContext.getInternalHeaders());
            this.clock = clientContext.getClock();
            this.endpoint = clientContext.getEndpoint();
            String str = this.endpoint;
            if (str != null) {
                this.mtlsEndpoint = str.replace("googleapis.com", "mtls.googleapis.com");
            }
            this.streamWatchdogProvider = FixedWatchdogProvider.create(clientContext.getStreamWatchdog());
            this.streamWatchdogCheckInterval = clientContext.getStreamWatchdogCheckInterval();
            this.tracerFactory = clientContext.getTracerFactory();
            this.quotaProjectId = getQuotaProjectIdFromClientContext(clientContext);
        }

        protected Builder() {
            this((ClientContext) null);
        }

        public B setExecutorProvider(ExecutorProvider executorProvider) {
            this.executorProvider = executorProvider;
            return self();
        }

        public B setCredentialsProvider(CredentialsProvider credentialsProvider) {
            this.credentialsProvider = (CredentialsProvider) Preconditions.checkNotNull(credentialsProvider);
            return self();
        }

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        public B setHeaderProvider(HeaderProvider headerProvider) {
            this.headerProvider = headerProvider;
            if (this.quotaProjectId == null && headerProvider.getHeaders().containsKey(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY)) {
                this.quotaProjectId = headerProvider.getHeaders().get(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY);
            }
            return self();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        public B setInternalHeaderProvider(HeaderProvider headerProvider) {
            this.internalHeaderProvider = headerProvider;
            if (this.quotaProjectId == null && headerProvider.getHeaders().containsKey(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY)) {
                this.quotaProjectId = headerProvider.getHeaders().get(StubSettings.QUOTA_PROJECT_ID_HEADER_KEY);
            }
            return self();
        }

        public B setTransportChannelProvider(TransportChannelProvider transportChannelProvider) {
            this.transportChannelProvider = transportChannelProvider;
            return self();
        }

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        public B setStreamWatchdogProvider(@Nullable WatchdogProvider watchdogProvider) {
            this.streamWatchdogProvider = watchdogProvider;
            return self();
        }

        public B setClock(ApiClock apiClock) {
            this.clock = apiClock;
            return self();
        }

        public B setEndpoint(String str) {
            this.endpoint = str;
            this.switchToMtlsEndpointAllowed = false;
            String str2 = this.endpoint;
            if (str2 != null && this.mtlsEndpoint == null) {
                this.mtlsEndpoint = str2.replace("googleapis.com", "mtls.googleapis.com");
            }
            return self();
        }

        protected B setSwitchToMtlsEndpointAllowed(boolean z) {
            this.switchToMtlsEndpointAllowed = z;
            return self();
        }

        public B setMtlsEndpoint(String str) {
            this.mtlsEndpoint = str;
            return self();
        }

        public B setQuotaProjectId(String str) {
            this.quotaProjectId = str;
            return self();
        }

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        public B setStreamWatchdogCheckInterval(@Nonnull Duration duration) {
            Preconditions.checkNotNull(duration);
            this.streamWatchdogCheckInterval = duration;
            return self();
        }

        @BetaApi("The surface for tracing is not stable yet and may change in the future.")
        public B setTracerFactory(@Nonnull ApiTracerFactory apiTracerFactory) {
            Preconditions.checkNotNull(apiTracerFactory);
            this.tracerFactory = apiTracerFactory;
            return self();
        }

        public ExecutorProvider getExecutorProvider() {
            return this.executorProvider;
        }

        public TransportChannelProvider getTransportChannelProvider() {
            return this.transportChannelProvider;
        }

        public CredentialsProvider getCredentialsProvider() {
            return this.credentialsProvider;
        }

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        public HeaderProvider getHeaderProvider() {
            return this.headerProvider;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        public HeaderProvider getInternalHeaderProvider() {
            return this.internalHeaderProvider;
        }

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        @Nullable
        public WatchdogProvider getStreamWatchdogProvider() {
            return this.streamWatchdogProvider;
        }

        public ApiClock getClock() {
            return this.clock;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public String getMtlsEndpoint() {
            return this.mtlsEndpoint;
        }

        public String getQuotaProjectId() {
            return this.quotaProjectId;
        }

        @Nonnull
        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        public Duration getStreamWatchdogCheckInterval() {
            return this.streamWatchdogCheckInterval;
        }

        @Nonnull
        @BetaApi("The surface for tracing is not stable yet and may change in the future.")
        public ApiTracerFactory getTracerFactory() {
            return this.tracerFactory;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static void applyToAllUnaryMethods(Iterable<UnaryCallSettings.Builder<?, ?>> iterable, ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) {
            Iterator<UnaryCallSettings.Builder<?, ?>> it = iterable.iterator();
            while (it.hasNext()) {
                apiFunction.apply(it.next());
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("executorProvider", this.executorProvider).add("transportChannelProvider", this.transportChannelProvider).add("credentialsProvider", this.credentialsProvider).add("headerProvider", this.headerProvider).add("internalHeaderProvider", this.internalHeaderProvider).add("clock", this.clock).add("endpoint", this.endpoint).add("mtlsEndpoint", this.mtlsEndpoint).add("switchToMtlsEndpointAllowed", this.switchToMtlsEndpointAllowed).add("quotaProjectId", this.quotaProjectId).add("streamWatchdogProvider", this.streamWatchdogProvider).add("streamWatchdogCheckInterval", this.streamWatchdogCheckInterval).add("tracerFactory", this.tracerFactory).toString();
        }
    }
}
