package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.core.NanoClock;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.ExecutorAsBackgroundResource;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.rpc.AutoValue_ClientContext;
import com.google.api.gax.rpc.internal.QuotaProjectIdHidingCredentials;
import com.google.api.gax.rpc.mtls.MtlsProvider;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.api.gax.tracing.NoopApiTracerFactory;
import com.google.auth.Credentials;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class ClientContext {
    private static final String QUOTA_PROJECT_ID_HEADER_KEY = "x-goog-user-project";

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract ClientContext build();

        public abstract Builder setBackgroundResources(List<BackgroundResource> list);

        public abstract Builder setClock(ApiClock apiClock);

        public abstract Builder setCredentials(Credentials credentials);

        public abstract Builder setDefaultCallContext(ApiCallContext apiCallContext);

        public abstract Builder setEndpoint(String str);

        public abstract Builder setExecutor(ScheduledExecutorService scheduledExecutorService);

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        public abstract Builder setHeaders(Map<String, String> map);

        @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
        protected abstract Builder setInternalHeaders(Map<String, String> map);

        public abstract Builder setQuotaProjectId(String str);

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        public abstract Builder setStreamWatchdog(Watchdog watchdog);

        @BetaApi("The surface for streaming is not stable yet and may change in the future.")
        public abstract Builder setStreamWatchdogCheckInterval(Duration duration);

        @BetaApi("The surface for tracing is not stable yet and may change in the future.")
        public abstract Builder setTracerFactory(ApiTracerFactory apiTracerFactory);

        public abstract Builder setTransportChannel(TransportChannel transportChannel);
    }

    public abstract List<BackgroundResource> getBackgroundResources();

    public abstract ApiClock getClock();

    @Nullable
    public abstract Credentials getCredentials();

    public abstract ApiCallContext getDefaultCallContext();

    @Nullable
    public abstract String getEndpoint();

    public abstract ScheduledExecutorService getExecutor();

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public abstract Map<String, String> getHeaders();

    /* JADX INFO: Access modifiers changed from: protected */
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public abstract Map<String, String> getInternalHeaders();

    @Nullable
    public abstract String getQuotaProjectId();

    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public abstract Watchdog getStreamWatchdog();

    @Nonnull
    @BetaApi("The surface for streaming is not stable yet and may change in the future.")
    public abstract Duration getStreamWatchdogCheckInterval();

    @Nonnull
    @BetaApi("The surface for tracing is not stable yet and may change in the future.")
    public abstract ApiTracerFactory getTracerFactory();

    @Nullable
    public abstract TransportChannel getTransportChannel();

    public abstract Builder toBuilder();

    public static Builder newBuilder() {
        return new AutoValue_ClientContext.Builder().setBackgroundResources(Collections.emptyList()).setExecutor(Executors.newScheduledThreadPool(0)).setHeaders(Collections.emptyMap()).setInternalHeaders(Collections.emptyMap()).setClock(NanoClock.getDefaultClock()).setStreamWatchdog(null).setStreamWatchdogCheckInterval(Duration.ZERO).setTracerFactory(NoopApiTracerFactory.getInstance()).setQuotaProjectId(null);
    }

    public static ClientContext create(ClientSettings clientSettings) throws IOException {
        return create(clientSettings.getStubSettings());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.api.gax.rpc.ClientContext$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C21051 {

        /* renamed from: $SwitchMap$com$google$api$gax$rpc$mtls$MtlsProvider$MtlsEndpointUsagePolicy */
        static final /* synthetic */ int[] f1344x6eb4a60a = new int[MtlsProvider.MtlsEndpointUsagePolicy.values().length];

        static {
            try {
                f1344x6eb4a60a[MtlsProvider.MtlsEndpointUsagePolicy.ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1344x6eb4a60a[MtlsProvider.MtlsEndpointUsagePolicy.NEVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static String getEndpoint(String str, String str2, boolean z, MtlsProvider mtlsProvider) throws IOException {
        if (!z) {
            return str;
        }
        int i = C21051.f1344x6eb4a60a[mtlsProvider.getMtlsEndpointUsagePolicy().ordinal()];
        return i != 1 ? (i == 2 || !mtlsProvider.useMtlsClientCertificate() || mtlsProvider.getKeyStore() == null) ? str : str2 : str2;
    }

    public static ClientContext create(StubSettings stubSettings) throws IOException {
        ApiClock clock = stubSettings.getClock();
        ExecutorProvider executorProvider = stubSettings.getExecutorProvider();
        ScheduledExecutorService executor = executorProvider.getExecutor();
        Credentials credentials = stubSettings.getCredentialsProvider().getCredentials();
        if (stubSettings.getQuotaProjectId() != null) {
            credentials = new QuotaProjectIdHidingCredentials(credentials);
        }
        TransportChannelProvider transportChannelProvider = stubSettings.getTransportChannelProvider();
        if (transportChannelProvider.needsExecutor()) {
            transportChannelProvider = transportChannelProvider.withExecutor((Executor) executor);
        }
        Map<String, String> headersFromSettings = getHeadersFromSettings(stubSettings);
        if (transportChannelProvider.needsHeaders()) {
            transportChannelProvider = transportChannelProvider.withHeaders(headersFromSettings);
        }
        if (transportChannelProvider.needsCredentials() && credentials != null) {
            transportChannelProvider = transportChannelProvider.withCredentials(credentials);
        }
        String endpoint = getEndpoint(stubSettings.getEndpoint(), stubSettings.getMtlsEndpoint(), stubSettings.getSwitchToMtlsEndpointAllowed(), new MtlsProvider());
        if (transportChannelProvider.needsEndpoint()) {
            transportChannelProvider = transportChannelProvider.withEndpoint(endpoint);
        }
        TransportChannel transportChannel = transportChannelProvider.getTransportChannel();
        ApiCallContext withTransportChannel = transportChannel.getEmptyCallContext().withTransportChannel(transportChannel);
        if (credentials != null) {
            withTransportChannel = withTransportChannel.withCredentials(credentials);
        }
        WatchdogProvider streamWatchdogProvider = stubSettings.getStreamWatchdogProvider();
        Watchdog watchdog = null;
        if (streamWatchdogProvider != null) {
            if (streamWatchdogProvider.needsCheckInterval()) {
                streamWatchdogProvider = streamWatchdogProvider.withCheckInterval(stubSettings.getStreamWatchdogCheckInterval());
            }
            if (streamWatchdogProvider.needsClock()) {
                streamWatchdogProvider = streamWatchdogProvider.withClock(clock);
            }
            if (streamWatchdogProvider.needsExecutor()) {
                streamWatchdogProvider = streamWatchdogProvider.withExecutor(executor);
            }
            watchdog = streamWatchdogProvider.getWatchdog();
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        if (transportChannelProvider.shouldAutoClose()) {
            builder.add((ImmutableList.Builder) transportChannel);
        }
        if (executorProvider.shouldAutoClose()) {
            builder.add((ImmutableList.Builder) new ExecutorAsBackgroundResource(executor));
        }
        if (streamWatchdogProvider != null && streamWatchdogProvider.shouldAutoClose()) {
            builder.add((ImmutableList.Builder) watchdog);
        }
        return newBuilder().setBackgroundResources(builder.build()).setExecutor(executor).setCredentials(credentials).setTransportChannel(transportChannel).setHeaders(ImmutableMap.copyOf((Map) stubSettings.getHeaderProvider().getHeaders())).setInternalHeaders(ImmutableMap.copyOf((Map) stubSettings.getInternalHeaderProvider().getHeaders())).setClock(clock).setDefaultCallContext(withTransportChannel).setEndpoint(stubSettings.getEndpoint()).setQuotaProjectId(stubSettings.getQuotaProjectId()).setStreamWatchdog(watchdog).setStreamWatchdogCheckInterval(stubSettings.getStreamWatchdogCheckInterval()).setTracerFactory(stubSettings.getTracerFactory()).build();
    }

    private static Map<String, String> getHeadersFromSettings(StubSettings stubSettings) {
        Map<String, String> headers = stubSettings.getHeaderProvider().getHeaders();
        Map<String, String> headers2 = stubSettings.getInternalHeaderProvider().getHeaders();
        HashMap hashMap = new HashMap();
        for (String str : Sets.intersection(headers.keySet(), headers2.keySet())) {
            if ("user-agent".equals(str)) {
                hashMap.put(str, headers.get(str) + " " + headers2.get(str));
            } else if (!QUOTA_PROJECT_ID_HEADER_KEY.equals(str) || stubSettings.getQuotaProjectId() == null) {
                throw new IllegalArgumentException("Header provider can't override the header: " + str);
            }
        }
        if (stubSettings.getQuotaProjectId() != null) {
            hashMap.put(QUOTA_PROJECT_ID_HEADER_KEY, stubSettings.getQuotaProjectId());
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.putAll(headers2);
        hashMap2.putAll(headers);
        hashMap2.putAll(hashMap);
        return ImmutableMap.copyOf((Map) hashMap2);
    }
}
