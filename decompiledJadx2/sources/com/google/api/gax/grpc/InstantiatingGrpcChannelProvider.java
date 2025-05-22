package com.google.api.gax.grpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.rpc.FixedHeaderProvider;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannel;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.mtls.MtlsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;
import io.grpc.ChannelCredentials;
import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.TlsChannelCredentials;
import io.grpc.alts.ComputeEngineChannelBuilder;
import io.grpc.internal.GrpcUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.KeyManagerFactory;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public final class InstantiatingGrpcChannelProvider implements TransportChannelProvider {
    static final String DIRECT_PATH_ENV_VAR = "GOOGLE_CLOUD_ENABLE_DIRECT_PATH";
    static final long DIRECT_PATH_KEEP_ALIVE_TIMEOUT_SECONDS = 20;
    static final long DIRECT_PATH_KEEP_ALIVE_TIME_SECONDS = 3600;
    static final String GCE_PRODUCTION_NAME_AFTER_2016 = "Google Compute Engine";
    static final String GCE_PRODUCTION_NAME_PRIOR_2016 = "Google";
    static final int MAX_POOL_SIZE = 1000;

    @Nullable
    private final Boolean attemptDirectPath;

    @Nullable
    private final ApiFunction<ManagedChannelBuilder, ManagedChannelBuilder> channelConfigurator;

    @Nullable
    private final ChannelPrimer channelPrimer;

    @Nullable
    private final Credentials credentials;
    final ImmutableMap<String, ?> directPathServiceConfig;
    private final String endpoint;
    private final EnvironmentProvider envProvider;
    private final Executor executor;
    private final HeaderProvider headerProvider;

    @Nullable
    private final GrpcInterceptorProvider interceptorProvider;

    @Nullable
    private final Duration keepAliveTime;

    @Nullable
    private final Duration keepAliveTimeout;

    @Nullable
    private final Boolean keepAliveWithoutCalls;

    @Nullable
    private final Integer maxInboundMessageSize;

    @Nullable
    private final Integer maxInboundMetadataSize;

    @Nullable
    private final MtlsProvider mtlsProvider;

    @Nullable
    private final Integer poolSize;
    private final int processorCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface EnvironmentProvider {
        @Nullable
        String getenv(String str);
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean shouldAutoClose() {
        return true;
    }

    private InstantiatingGrpcChannelProvider(Builder builder) {
        ImmutableMap<String, ?> immutableMap;
        this.processorCount = builder.processorCount;
        this.executor = builder.executor;
        this.headerProvider = builder.headerProvider;
        this.endpoint = builder.endpoint;
        this.mtlsProvider = builder.mtlsProvider;
        this.envProvider = builder.envProvider;
        this.interceptorProvider = builder.interceptorProvider;
        this.maxInboundMessageSize = builder.maxInboundMessageSize;
        this.maxInboundMetadataSize = builder.maxInboundMetadataSize;
        this.keepAliveTime = builder.keepAliveTime;
        this.keepAliveTimeout = builder.keepAliveTimeout;
        this.keepAliveWithoutCalls = builder.keepAliveWithoutCalls;
        this.poolSize = builder.poolSize;
        this.channelConfigurator = builder.channelConfigurator;
        this.credentials = builder.credentials;
        this.channelPrimer = builder.channelPrimer;
        this.attemptDirectPath = builder.attemptDirectPath;
        if (builder.directPathServiceConfig == null) {
            immutableMap = getDefaultDirectPathServiceConfig();
        } else {
            immutableMap = builder.directPathServiceConfig;
        }
        this.directPathServiceConfig = immutableMap;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean needsExecutor() {
        return this.executor == null;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @Deprecated
    public TransportChannelProvider withExecutor(ScheduledExecutorService scheduledExecutorService) {
        return withExecutor((Executor) scheduledExecutorService);
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public TransportChannelProvider withExecutor(Executor executor) {
        return toBuilder().setExecutor(executor).build();
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public boolean needsHeaders() {
        return this.headerProvider == null;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public TransportChannelProvider withHeaders(Map<String, String> map) {
        return toBuilder().setHeaderProvider(FixedHeaderProvider.create(map)).build();
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public String getTransportName() {
        return GrpcTransportChannel.getGrpcTransportName();
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean needsEndpoint() {
        return this.endpoint == null;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public TransportChannelProvider withEndpoint(String str) {
        validateEndpoint(str);
        return toBuilder().setEndpoint(str).build();
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing pool size is not stable yet and may change in the future.")
    public boolean acceptsPoolSize() {
        return this.poolSize == null;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing pool size is not stable yet and may change in the future.")
    public TransportChannelProvider withPoolSize(int i) {
        Preconditions.checkState(acceptsPoolSize(), "pool size already set to %s", this.poolSize);
        return toBuilder().setPoolSize(i).build();
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean needsCredentials() {
        return this.credentials == null;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public TransportChannelProvider withCredentials(Credentials credentials) {
        return toBuilder().setCredentials(credentials).build();
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public TransportChannel getTransportChannel() throws IOException {
        if (needsExecutor()) {
            throw new IllegalStateException("getTransportChannel() called when needsExecutor() is true");
        }
        if (needsHeaders()) {
            throw new IllegalStateException("getTransportChannel() called when needsHeaders() is true");
        }
        if (needsEndpoint()) {
            throw new IllegalStateException("getTransportChannel() called when needsEndpoint() is true");
        }
        return createChannel();
    }

    private TransportChannel createChannel() throws IOException {
        ChannelPool create;
        int intValue = ((Integer) MoreObjects.firstNonNull(this.poolSize, 1)).intValue();
        ChannelFactory channelFactory = new ChannelFactory() { // from class: com.google.api.gax.grpc.InstantiatingGrpcChannelProvider.1
            @Override // com.google.api.gax.grpc.ChannelFactory
            public ManagedChannel createSingleChannel() throws IOException {
                try {
                    return InstantiatingGrpcChannelProvider.this.createSingleChannel();
                } catch (GeneralSecurityException e) {
                    throw new IOException(e);
                }
            }
        };
        if (this.channelPrimer != null) {
            create = ChannelPool.createRefreshing(intValue, channelFactory);
        } else {
            create = ChannelPool.create(intValue, channelFactory);
        }
        return GrpcTransportChannel.create(create);
    }

    private boolean isDirectPathEnabled(String str) {
        Boolean bool = this.attemptDirectPath;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str2 = this.envProvider.getenv(DIRECT_PATH_ENV_VAR);
        if (str2 == null) {
            return false;
        }
        for (String str3 : str2.split(",")) {
            if (!str3.isEmpty() && str.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    static boolean isOnComputeEngine() {
        if (!"Linux".equals(System.getProperty("os.name"))) {
            return false;
        }
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "cat /sys/class/dmi/id/product_name"});
            exec.waitFor();
            String charStreams = CharStreams.toString(new InputStreamReader(exec.getInputStream(), "UTF-8"));
            if (!charStreams.contains(GCE_PRODUCTION_NAME_PRIOR_2016)) {
                if (!charStreams.contains(GCE_PRODUCTION_NAME_AFTER_2016)) {
                    return false;
                }
            }
            return true;
        } catch (IOException | InterruptedException unused) {
            return false;
        }
    }

    ChannelCredentials createMtlsChannelCredentials() throws IOException, GeneralSecurityException {
        KeyStore keyStore;
        if (!this.mtlsProvider.useMtlsClientCertificate() || (keyStore = this.mtlsProvider.getKeyStore()) == null) {
            return null;
        }
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, new char[0]);
        return TlsChannelCredentials.newBuilder().keyManager(keyManagerFactory.getKeyManagers()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ManagedChannel createSingleChannel() throws IOException, GeneralSecurityException {
        ManagedChannelBuilder forAddress;
        GrpcHeaderInterceptor grpcHeaderInterceptor = new GrpcHeaderInterceptor(this.headerProvider.getHeaders());
        GrpcMetadataHandlerInterceptor grpcMetadataHandlerInterceptor = new GrpcMetadataHandlerInterceptor();
        int lastIndexOf = this.endpoint.lastIndexOf(58);
        if (lastIndexOf < 0) {
            throw new IllegalStateException("invalid endpoint - should have been validated: " + this.endpoint);
        }
        int parseInt = Integer.parseInt(this.endpoint.substring(lastIndexOf + 1));
        String substring = this.endpoint.substring(0, lastIndexOf);
        if (isDirectPathEnabled(substring) && (this.credentials instanceof ComputeEngineCredentials) && isOnComputeEngine()) {
            forAddress = ComputeEngineChannelBuilder.forAddress(substring, parseInt);
            forAddress.keepAliveTime(DIRECT_PATH_KEEP_ALIVE_TIME_SECONDS, TimeUnit.SECONDS);
            forAddress.keepAliveTimeout(DIRECT_PATH_KEEP_ALIVE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            forAddress.defaultServiceConfig(this.directPathServiceConfig);
        } else {
            ChannelCredentials createMtlsChannelCredentials = createMtlsChannelCredentials();
            if (createMtlsChannelCredentials != null) {
                forAddress = Grpc.newChannelBuilder(this.endpoint, createMtlsChannelCredentials);
            } else {
                forAddress = ManagedChannelBuilder.forAddress(substring, parseInt);
            }
        }
        ManagedChannelBuilder executor = forAddress.disableServiceConfigLookUp().intercept(new GrpcChannelUUIDInterceptor()).intercept(grpcHeaderInterceptor).intercept(grpcMetadataHandlerInterceptor).userAgent(grpcHeaderInterceptor.getUserAgentHeader()).executor(this.executor);
        Integer num = this.maxInboundMetadataSize;
        if (num != null) {
            executor.maxInboundMetadataSize(num.intValue());
        }
        Integer num2 = this.maxInboundMessageSize;
        if (num2 != null) {
            executor.maxInboundMessageSize(num2.intValue());
        }
        Duration duration = this.keepAliveTime;
        if (duration != null) {
            executor.keepAliveTime(duration.toMillis(), TimeUnit.MILLISECONDS);
        }
        Duration duration2 = this.keepAliveTimeout;
        if (duration2 != null) {
            executor.keepAliveTimeout(duration2.toMillis(), TimeUnit.MILLISECONDS);
        }
        Boolean bool = this.keepAliveWithoutCalls;
        if (bool != null) {
            executor.keepAliveWithoutCalls(bool.booleanValue());
        }
        GrpcInterceptorProvider grpcInterceptorProvider = this.interceptorProvider;
        if (grpcInterceptorProvider != null) {
            executor.intercept(grpcInterceptorProvider.getInterceptors());
        }
        ApiFunction<ManagedChannelBuilder, ManagedChannelBuilder> apiFunction = this.channelConfigurator;
        if (apiFunction != null) {
            executor = apiFunction.apply(executor);
        }
        ManagedChannel build = executor.build();
        ChannelPrimer channelPrimer = this.channelPrimer;
        if (channelPrimer != null) {
            channelPrimer.primeChannel(build);
        }
        return build;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public Duration getKeepAliveTime() {
        return this.keepAliveTime;
    }

    public Duration getKeepAliveTimeout() {
        return this.keepAliveTimeout;
    }

    public Boolean getKeepAliveWithoutCalls() {
        return this.keepAliveWithoutCalls;
    }

    @BetaApi("The surface for maximum metadata size is not stable yet and may change in the future.")
    public Integer getMaxInboundMetadataSize() {
        return this.maxInboundMetadataSize;
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder {

        @Nullable
        private Boolean attemptDirectPath;

        @Nullable
        private ApiFunction<ManagedChannelBuilder, ManagedChannelBuilder> channelConfigurator;

        @Nullable
        private ChannelPrimer channelPrimer;

        @Nullable
        private Credentials credentials;

        @Nullable
        private ImmutableMap<String, ?> directPathServiceConfig;
        private String endpoint;
        private EnvironmentProvider envProvider;
        private Executor executor;
        private HeaderProvider headerProvider;

        @Nullable
        private GrpcInterceptorProvider interceptorProvider;

        @Nullable
        private Duration keepAliveTime;

        @Nullable
        private Duration keepAliveTimeout;

        @Nullable
        private Boolean keepAliveWithoutCalls;

        @Nullable
        private Integer maxInboundMessageSize;

        @Nullable
        private Integer maxInboundMetadataSize;
        private MtlsProvider mtlsProvider;

        @Nullable
        private Integer poolSize;
        private int processorCount;

        private Builder() {
            this.mtlsProvider = new MtlsProvider();
            this.processorCount = Runtime.getRuntime().availableProcessors();
            this.envProvider = DirectPathEnvironmentProvider.getInstance();
        }

        private Builder(InstantiatingGrpcChannelProvider instantiatingGrpcChannelProvider) {
            this.mtlsProvider = new MtlsProvider();
            this.processorCount = instantiatingGrpcChannelProvider.processorCount;
            this.executor = instantiatingGrpcChannelProvider.executor;
            this.headerProvider = instantiatingGrpcChannelProvider.headerProvider;
            this.endpoint = instantiatingGrpcChannelProvider.endpoint;
            this.envProvider = instantiatingGrpcChannelProvider.envProvider;
            this.interceptorProvider = instantiatingGrpcChannelProvider.interceptorProvider;
            this.maxInboundMessageSize = instantiatingGrpcChannelProvider.maxInboundMessageSize;
            this.maxInboundMetadataSize = instantiatingGrpcChannelProvider.maxInboundMetadataSize;
            this.keepAliveTime = instantiatingGrpcChannelProvider.keepAliveTime;
            this.keepAliveTimeout = instantiatingGrpcChannelProvider.keepAliveTimeout;
            this.keepAliveWithoutCalls = instantiatingGrpcChannelProvider.keepAliveWithoutCalls;
            this.poolSize = instantiatingGrpcChannelProvider.poolSize;
            this.channelConfigurator = instantiatingGrpcChannelProvider.channelConfigurator;
            this.credentials = instantiatingGrpcChannelProvider.credentials;
            this.channelPrimer = instantiatingGrpcChannelProvider.channelPrimer;
            this.attemptDirectPath = instantiatingGrpcChannelProvider.attemptDirectPath;
            this.directPathServiceConfig = instantiatingGrpcChannelProvider.directPathServiceConfig;
            this.mtlsProvider = instantiatingGrpcChannelProvider.mtlsProvider;
        }

        Builder setProcessorCount(int i) {
            this.processorCount = i;
            return this;
        }

        public Builder setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        @Deprecated
        public Builder setExecutorProvider(ExecutorProvider executorProvider) {
            return setExecutor(executorProvider.getExecutor());
        }

        public Builder setHeaderProvider(HeaderProvider headerProvider) {
            this.headerProvider = headerProvider;
            return this;
        }

        public Builder setEndpoint(String str) {
            InstantiatingGrpcChannelProvider.validateEndpoint(str);
            this.endpoint = str;
            return this;
        }

        Builder setMtlsProvider(MtlsProvider mtlsProvider) {
            this.mtlsProvider = mtlsProvider;
            return this;
        }

        public Builder setInterceptorProvider(GrpcInterceptorProvider grpcInterceptorProvider) {
            this.interceptorProvider = grpcInterceptorProvider;
            return this;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public Builder setMaxInboundMessageSize(Integer num) {
            this.maxInboundMessageSize = num;
            return this;
        }

        public Integer getMaxInboundMessageSize() {
            return this.maxInboundMessageSize;
        }

        @BetaApi("The surface for maximum metadata size is not stable yet and may change in the future.")
        public Builder setMaxInboundMetadataSize(Integer num) {
            this.maxInboundMetadataSize = num;
            return this;
        }

        @BetaApi("The surface for maximum metadata size is not stable yet and may change in the future.")
        public Integer getMaxInboundMetadataSize() {
            return this.maxInboundMetadataSize;
        }

        public Builder setKeepAliveTime(Duration duration) {
            this.keepAliveTime = duration;
            return this;
        }

        public Duration getKeepAliveTime() {
            return this.keepAliveTime;
        }

        public Builder setKeepAliveTimeout(Duration duration) {
            this.keepAliveTimeout = duration;
            return this;
        }

        public Duration getKeepAliveTimeout() {
            return this.keepAliveTimeout;
        }

        public Builder setKeepAliveWithoutCalls(Boolean bool) {
            this.keepAliveWithoutCalls = bool;
            return this;
        }

        public Boolean getKeepAliveWithoutCalls() {
            return this.keepAliveWithoutCalls;
        }

        public int getPoolSize() {
            Integer num = this.poolSize;
            if (num == null) {
                return 1;
            }
            return num.intValue();
        }

        public Builder setPoolSize(int i) {
            Preconditions.checkArgument(i > 0, "Pool size must be positive");
            Preconditions.checkArgument(i <= 1000, "Pool size must be less than %s", 1000);
            this.poolSize = Integer.valueOf(i);
            return this;
        }

        public Builder setChannelsPerCpu(double d) {
            return setChannelsPerCpu(d, 100);
        }

        public Builder setChannelsPerCpu(double d, int i) {
            Preconditions.checkArgument(d > 0.0d, "multiplier must be positive");
            Preconditions.checkArgument(i > 0, "maxChannels must be positive");
            int ceil = (int) Math.ceil(this.processorCount * d);
            if (ceil > i) {
                ceil = i;
            }
            return setPoolSize(ceil);
        }

        public Builder setCredentials(Credentials credentials) {
            this.credentials = credentials;
            return this;
        }

        @InternalApi("For internal use by google-cloud-java clients only")
        public Builder setChannelPrimer(ChannelPrimer channelPrimer) {
            this.channelPrimer = channelPrimer;
            return this;
        }

        @InternalApi("For internal use by google-cloud-java clients only")
        public Builder setAttemptDirectPath(boolean z) {
            this.attemptDirectPath = Boolean.valueOf(z);
            return this;
        }

        @InternalApi("For internal use by google-cloud-java clients only")
        public Builder setDirectPathServiceConfig(Map<String, ?> map) {
            Preconditions.checkNotNull(map, "serviceConfig");
            this.directPathServiceConfig = ImmutableMap.copyOf((Map) map);
            return this;
        }

        public InstantiatingGrpcChannelProvider build() {
            return new InstantiatingGrpcChannelProvider(this);
        }

        @BetaApi("Surface for advanced channel configuration is not yet stable")
        public Builder setChannelConfigurator(@Nullable ApiFunction<ManagedChannelBuilder, ManagedChannelBuilder> apiFunction) {
            this.channelConfigurator = apiFunction;
            return this;
        }

        @Nullable
        public ApiFunction<ManagedChannelBuilder, ManagedChannelBuilder> getChannelConfigurator() {
            return this.channelConfigurator;
        }
    }

    private static ImmutableMap<String, ?> getDefaultDirectPathServiceConfig() {
        return ImmutableMap.m654of("loadBalancingConfig", ImmutableList.m635of(ImmutableMap.m654of("grpclb", ImmutableMap.m654of("childPolicy", ImmutableList.m635of(ImmutableMap.m654of(GrpcUtil.DEFAULT_LB_POLICY, ImmutableMap.m653of()))))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateEndpoint(String str) {
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf < 0) {
            throw new IllegalArgumentException(String.format("invalid endpoint, expecting \"<host>:<port>\"", new Object[0]));
        }
        Integer.parseInt(str.substring(lastIndexOf + 1));
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class DirectPathEnvironmentProvider implements EnvironmentProvider {
        private static DirectPathEnvironmentProvider provider;

        private DirectPathEnvironmentProvider() {
        }

        public static DirectPathEnvironmentProvider getInstance() {
            if (provider == null) {
                provider = new DirectPathEnvironmentProvider();
            }
            return provider;
        }

        @Override // com.google.api.gax.grpc.InstantiatingGrpcChannelProvider.EnvironmentProvider
        public String getenv(String str) {
            return System.getenv(str);
        }
    }
}
