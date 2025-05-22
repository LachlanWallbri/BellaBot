package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.auth.Credentials;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_ClientContext extends ClientContext {
    private final List<BackgroundResource> backgroundResources;
    private final ApiClock clock;
    private final Credentials credentials;
    private final ApiCallContext defaultCallContext;
    private final String endpoint;
    private final ScheduledExecutorService executor;
    private final Map<String, String> headers;
    private final Map<String, String> internalHeaders;
    private final String quotaProjectId;
    private final Watchdog streamWatchdog;
    private final Duration streamWatchdogCheckInterval;
    private final ApiTracerFactory tracerFactory;
    private final TransportChannel transportChannel;

    private AutoValue_ClientContext(List<BackgroundResource> list, ScheduledExecutorService scheduledExecutorService, @Nullable Credentials credentials, @Nullable TransportChannel transportChannel, Map<String, String> map, Map<String, String> map2, ApiClock apiClock, ApiCallContext apiCallContext, @Nullable Watchdog watchdog, Duration duration, @Nullable String str, @Nullable String str2, ApiTracerFactory apiTracerFactory) {
        this.backgroundResources = list;
        this.executor = scheduledExecutorService;
        this.credentials = credentials;
        this.transportChannel = transportChannel;
        this.headers = map;
        this.internalHeaders = map2;
        this.clock = apiClock;
        this.defaultCallContext = apiCallContext;
        this.streamWatchdog = watchdog;
        this.streamWatchdogCheckInterval = duration;
        this.endpoint = str;
        this.quotaProjectId = str2;
        this.tracerFactory = apiTracerFactory;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    public List<BackgroundResource> getBackgroundResources() {
        return this.backgroundResources;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    public ScheduledExecutorService getExecutor() {
        return this.executor;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @Nullable
    public Credentials getCredentials() {
        return this.credentials;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @Nullable
    public TransportChannel getTransportChannel() {
        return this.transportChannel;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.api.gax.rpc.ClientContext
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public Map<String, String> getInternalHeaders() {
        return this.internalHeaders;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    public ApiClock getClock() {
        return this.clock;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    public ApiCallContext getDefaultCallContext() {
        return this.defaultCallContext;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public Watchdog getStreamWatchdog() {
        return this.streamWatchdog;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @Nonnull
    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public Duration getStreamWatchdogCheckInterval() {
        return this.streamWatchdogCheckInterval;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @Nullable
    public String getEndpoint() {
        return this.endpoint;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @Nullable
    public String getQuotaProjectId() {
        return this.quotaProjectId;
    }

    @Override // com.google.api.gax.rpc.ClientContext
    @Nonnull
    @BetaApi("The surface for tracing is not stable yet and may change in the future.")
    public ApiTracerFactory getTracerFactory() {
        return this.tracerFactory;
    }

    public String toString() {
        return "ClientContext{backgroundResources=" + this.backgroundResources + ", executor=" + this.executor + ", credentials=" + this.credentials + ", transportChannel=" + this.transportChannel + ", headers=" + this.headers + ", internalHeaders=" + this.internalHeaders + ", clock=" + this.clock + ", defaultCallContext=" + this.defaultCallContext + ", streamWatchdog=" + this.streamWatchdog + ", streamWatchdogCheckInterval=" + this.streamWatchdogCheckInterval + ", endpoint=" + this.endpoint + ", quotaProjectId=" + this.quotaProjectId + ", tracerFactory=" + this.tracerFactory + "}";
    }

    public boolean equals(Object obj) {
        Credentials credentials;
        TransportChannel transportChannel;
        Watchdog watchdog;
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientContext)) {
            return false;
        }
        ClientContext clientContext = (ClientContext) obj;
        return this.backgroundResources.equals(clientContext.getBackgroundResources()) && this.executor.equals(clientContext.getExecutor()) && ((credentials = this.credentials) != null ? credentials.equals(clientContext.getCredentials()) : clientContext.getCredentials() == null) && ((transportChannel = this.transportChannel) != null ? transportChannel.equals(clientContext.getTransportChannel()) : clientContext.getTransportChannel() == null) && this.headers.equals(clientContext.getHeaders()) && this.internalHeaders.equals(clientContext.getInternalHeaders()) && this.clock.equals(clientContext.getClock()) && this.defaultCallContext.equals(clientContext.getDefaultCallContext()) && ((watchdog = this.streamWatchdog) != null ? watchdog.equals(clientContext.getStreamWatchdog()) : clientContext.getStreamWatchdog() == null) && this.streamWatchdogCheckInterval.equals(clientContext.getStreamWatchdogCheckInterval()) && ((str = this.endpoint) != null ? str.equals(clientContext.getEndpoint()) : clientContext.getEndpoint() == null) && ((str2 = this.quotaProjectId) != null ? str2.equals(clientContext.getQuotaProjectId()) : clientContext.getQuotaProjectId() == null) && this.tracerFactory.equals(clientContext.getTracerFactory());
    }

    public int hashCode() {
        int hashCode = (((this.backgroundResources.hashCode() ^ 1000003) * 1000003) ^ this.executor.hashCode()) * 1000003;
        Credentials credentials = this.credentials;
        int hashCode2 = (hashCode ^ (credentials == null ? 0 : credentials.hashCode())) * 1000003;
        TransportChannel transportChannel = this.transportChannel;
        int hashCode3 = (((((((((hashCode2 ^ (transportChannel == null ? 0 : transportChannel.hashCode())) * 1000003) ^ this.headers.hashCode()) * 1000003) ^ this.internalHeaders.hashCode()) * 1000003) ^ this.clock.hashCode()) * 1000003) ^ this.defaultCallContext.hashCode()) * 1000003;
        Watchdog watchdog = this.streamWatchdog;
        int hashCode4 = (((hashCode3 ^ (watchdog == null ? 0 : watchdog.hashCode())) * 1000003) ^ this.streamWatchdogCheckInterval.hashCode()) * 1000003;
        String str = this.endpoint;
        int hashCode5 = (hashCode4 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.quotaProjectId;
        return ((hashCode5 ^ (str2 != null ? str2.hashCode() : 0)) * 1000003) ^ this.tracerFactory.hashCode();
    }

    @Override // com.google.api.gax.rpc.ClientContext
    public ClientContext.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends ClientContext.Builder {
        private List<BackgroundResource> backgroundResources;
        private ApiClock clock;
        private Credentials credentials;
        private ApiCallContext defaultCallContext;
        private String endpoint;
        private ScheduledExecutorService executor;
        private Map<String, String> headers;
        private Map<String, String> internalHeaders;
        private String quotaProjectId;
        private Watchdog streamWatchdog;
        private Duration streamWatchdogCheckInterval;
        private ApiTracerFactory tracerFactory;
        private TransportChannel transportChannel;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(ClientContext clientContext) {
            this.backgroundResources = clientContext.getBackgroundResources();
            this.executor = clientContext.getExecutor();
            this.credentials = clientContext.getCredentials();
            this.transportChannel = clientContext.getTransportChannel();
            this.headers = clientContext.getHeaders();
            this.internalHeaders = clientContext.getInternalHeaders();
            this.clock = clientContext.getClock();
            this.defaultCallContext = clientContext.getDefaultCallContext();
            this.streamWatchdog = clientContext.getStreamWatchdog();
            this.streamWatchdogCheckInterval = clientContext.getStreamWatchdogCheckInterval();
            this.endpoint = clientContext.getEndpoint();
            this.quotaProjectId = clientContext.getQuotaProjectId();
            this.tracerFactory = clientContext.getTracerFactory();
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setBackgroundResources(List<BackgroundResource> list) {
            if (list == null) {
                throw new NullPointerException("Null backgroundResources");
            }
            this.backgroundResources = list;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setExecutor(ScheduledExecutorService scheduledExecutorService) {
            if (scheduledExecutorService == null) {
                throw new NullPointerException("Null executor");
            }
            this.executor = scheduledExecutorService;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setCredentials(@Nullable Credentials credentials) {
            this.credentials = credentials;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setTransportChannel(@Nullable TransportChannel transportChannel) {
            this.transportChannel = transportChannel;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setHeaders(Map<String, String> map) {
            if (map == null) {
                throw new NullPointerException("Null headers");
            }
            this.headers = map;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        protected ClientContext.Builder setInternalHeaders(Map<String, String> map) {
            if (map == null) {
                throw new NullPointerException("Null internalHeaders");
            }
            this.internalHeaders = map;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setClock(ApiClock apiClock) {
            if (apiClock == null) {
                throw new NullPointerException("Null clock");
            }
            this.clock = apiClock;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setDefaultCallContext(ApiCallContext apiCallContext) {
            if (apiCallContext == null) {
                throw new NullPointerException("Null defaultCallContext");
            }
            this.defaultCallContext = apiCallContext;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setStreamWatchdog(@Nullable Watchdog watchdog) {
            this.streamWatchdog = watchdog;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setStreamWatchdogCheckInterval(Duration duration) {
            if (duration == null) {
                throw new NullPointerException("Null streamWatchdogCheckInterval");
            }
            this.streamWatchdogCheckInterval = duration;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setEndpoint(@Nullable String str) {
            this.endpoint = str;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setQuotaProjectId(@Nullable String str) {
            this.quotaProjectId = str;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext.Builder setTracerFactory(ApiTracerFactory apiTracerFactory) {
            if (apiTracerFactory == null) {
                throw new NullPointerException("Null tracerFactory");
            }
            this.tracerFactory = apiTracerFactory;
            return this;
        }

        @Override // com.google.api.gax.rpc.ClientContext.Builder
        public ClientContext build() {
            String str = "";
            if (this.backgroundResources == null) {
                str = " backgroundResources";
            }
            if (this.executor == null) {
                str = str + " executor";
            }
            if (this.headers == null) {
                str = str + " headers";
            }
            if (this.internalHeaders == null) {
                str = str + " internalHeaders";
            }
            if (this.clock == null) {
                str = str + " clock";
            }
            if (this.defaultCallContext == null) {
                str = str + " defaultCallContext";
            }
            if (this.streamWatchdogCheckInterval == null) {
                str = str + " streamWatchdogCheckInterval";
            }
            if (this.tracerFactory == null) {
                str = str + " tracerFactory";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_ClientContext(this.backgroundResources, this.executor, this.credentials, this.transportChannel, this.headers, this.internalHeaders, this.clock, this.defaultCallContext, this.streamWatchdog, this.streamWatchdogCheckInterval, this.endpoint, this.quotaProjectId, this.tracerFactory);
        }
    }
}
