package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ChoiceChannelCredentials;
import io.grpc.CompositeCallCredentials;
import io.grpc.CompositeChannelCredentials;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannelBuilder;
import io.grpc.TlsChannelCredentials;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.SslSocketFactoryChannelCredentials;
import io.grpc.okhttp.internal.CipherSuite;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.TlsVersion;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder<OkHttpChannelBuilder> {
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    private ConnectionSpec connectionSpec;
    private int flowControlWindow;
    private final boolean freezeSecurityConfiguration;
    private HostnameVerifier hostnameVerifier;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private int maxInboundMessageSize;
    private int maxInboundMetadataSize;
    private NegotiationType negotiationType;
    private ScheduledExecutorService scheduledExecutorService;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private Executor transportExecutor;
    private TransportTracer.Factory transportTracerFactory;
    private final boolean useGetForSafeMethods;
    private static final Logger log = Logger.getLogger(OkHttpChannelBuilder.class.getName());
    static final ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384).tlsVersions(TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    private static final SharedResourceHolder.Resource<Executor> SHARED_EXECUTOR = new SharedResourceHolder.Resource<Executor>() { // from class: io.grpc.okhttp.OkHttpChannelBuilder.1
        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    private static final EnumSet<TlsChannelCredentials.Feature> understoodTlsFeatures = EnumSet.of(TlsChannelCredentials.Feature.MTLS, TlsChannelCredentials.Feature.CUSTOM_MANAGERS);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum NegotiationType {
        TLS,
        PLAINTEXT
    }

    public static OkHttpChannelBuilder forAddress(String str, int i) {
        return new OkHttpChannelBuilder(str, i);
    }

    public static OkHttpChannelBuilder forAddress(String str, int i, ChannelCredentials channelCredentials) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i), channelCredentials);
    }

    public static OkHttpChannelBuilder forTarget(String str) {
        return new OkHttpChannelBuilder(str);
    }

    public static OkHttpChannelBuilder forTarget(String str, ChannelCredentials channelCredentials) {
        SslSocketFactoryResult sslSocketFactoryFrom = sslSocketFactoryFrom(channelCredentials);
        if (sslSocketFactoryFrom.error != null) {
            throw new IllegalArgumentException(sslSocketFactoryFrom.error);
        }
        return new OkHttpChannelBuilder(str, channelCredentials, sslSocketFactoryFrom.callCredentials, sslSocketFactoryFrom.factory);
    }

    private OkHttpChannelBuilder(String str, int i) {
        this(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    private OkHttpChannelBuilder(String str) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMessageSize = 4194304;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(str, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.freezeSecurityConfiguration = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpChannelBuilder(String str, ChannelCredentials channelCredentials, CallCredentials callCredentials, SSLSocketFactory sSLSocketFactory) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMessageSize = 4194304;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(str, channelCredentials, callCredentials, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.sslSocketFactory = sSLSocketFactory;
        this.negotiationType = sSLSocketFactory == null ? NegotiationType.PLAINTEXT : NegotiationType.TLS;
        this.freezeSecurityConfiguration = true;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class OkHttpChannelTransportFactoryBuilder implements ManagedChannelImplBuilder.ClientTransportFactoryBuilder {
        private OkHttpChannelTransportFactoryBuilder() {
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
        public ClientTransportFactory buildClientTransportFactory() {
            return OkHttpChannelBuilder.this.buildTransportFactory();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class OkHttpChannelDefaultPortProvider implements ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider {
        private OkHttpChannelDefaultPortProvider() {
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider
        public int getDefaultPort() {
            return OkHttpChannelBuilder.this.getDefaultPort();
        }
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder
    protected ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    OkHttpChannelBuilder setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return this;
    }

    public OkHttpChannelBuilder transportExecutor(@Nullable Executor executor) {
        this.transportExecutor = executor;
        return this;
    }

    public OkHttpChannelBuilder socketFactory(@Nullable SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }

    @Deprecated
    public OkHttpChannelBuilder negotiationType(io.grpc.okhttp.NegotiationType negotiationType) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(negotiationType, "type");
        int i = C68132.$SwitchMap$io$grpc$okhttp$NegotiationType[negotiationType.ordinal()];
        if (i == 1) {
            this.negotiationType = NegotiationType.TLS;
        } else if (i == 2) {
            this.negotiationType = NegotiationType.PLAINTEXT;
        } else {
            throw new AssertionError("Unknown negotiation type: " + negotiationType);
        }
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive time must be positive");
        this.keepAliveTimeNanos = timeUnit.toNanos(j);
        this.keepAliveTimeNanos = KeepAliveManager.clampKeepAliveTimeInNanos(this.keepAliveTimeNanos);
        if (this.keepAliveTimeNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive timeout must be positive");
        this.keepAliveTimeoutNanos = timeUnit.toNanos(j);
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(this.keepAliveTimeoutNanos);
        return this;
    }

    public OkHttpChannelBuilder flowControlWindow(int i) {
        Preconditions.checkState(i > 0, "flowControlWindow must be positive");
        this.flowControlWindow = i;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveWithoutCalls(boolean z) {
        this.keepAliveWithoutCalls = z;
        return this;
    }

    public OkHttpChannelBuilder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.sslSocketFactory = sSLSocketFactory;
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder hostnameVerifier(@Nullable HostnameVerifier hostnameVerifier) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public OkHttpChannelBuilder connectionSpec(com.squareup.okhttp.ConnectionSpec connectionSpec) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkArgument(connectionSpec.isTls(), "plaintext ConnectionSpec is not accepted");
        this.connectionSpec = Utils.convertSpec(connectionSpec);
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder usePlaintext() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.PLAINTEXT;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder useTransportSecurity() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "negative max");
        this.maxInboundMessageSize = i;
        return this;
    }

    ClientTransportFactory buildTransportFactory() {
        return new OkHttpTransportFactory(this.transportExecutor, this.scheduledExecutorService, this.socketFactory, createSslSocketFactory(), this.hostnameVerifier, this.connectionSpec, this.maxInboundMessageSize, this.keepAliveTimeNanos != Long.MAX_VALUE, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpChannelBuilder disableCheckAuthority() {
        this.managedChannelImplBuilder.disableCheckAuthority();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpChannelBuilder enableCheckAuthority() {
        this.managedChannelImplBuilder.enableCheckAuthority();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C68132 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$NegotiationType;
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType = new int[NegotiationType.values().length];

        static {
            try {
                $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[NegotiationType.PLAINTEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[NegotiationType.TLS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$io$grpc$okhttp$NegotiationType = new int[io.grpc.okhttp.NegotiationType.values().length];
            try {
                $SwitchMap$io$grpc$okhttp$NegotiationType[io.grpc.okhttp.NegotiationType.TLS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$okhttp$NegotiationType[io.grpc.okhttp.NegotiationType.PLAINTEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    int getDefaultPort() {
        int i = C68132.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return 80;
        }
        if (i == 2) {
            return 443;
        }
        throw new AssertionError(this.negotiationType + " not handled");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.managedChannelImplBuilder.setStatsEnabled(z);
    }

    @Nullable
    SSLSocketFactory createSslSocketFactory() {
        int i = C68132.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            try {
                if (this.sslSocketFactory == null) {
                    this.sslSocketFactory = SSLContext.getInstance("Default", Platform.get().getProvider()).getSocketFactory();
                }
                return this.sslSocketFactory;
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("TLS Provider failure", e);
            }
        }
        throw new RuntimeException("Unknown negotiation type: " + this.negotiationType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SslSocketFactoryResult sslSocketFactoryFrom(ChannelCredentials channelCredentials) {
        KeyManager[] keyManagerArr;
        TrustManager[] createTrustManager;
        if (channelCredentials instanceof TlsChannelCredentials) {
            TlsChannelCredentials tlsChannelCredentials = (TlsChannelCredentials) channelCredentials;
            Set<TlsChannelCredentials.Feature> incomprehensible = tlsChannelCredentials.incomprehensible(understoodTlsFeatures);
            if (!incomprehensible.isEmpty()) {
                return SslSocketFactoryResult.error("TLS features not understood: " + incomprehensible);
            }
            if (tlsChannelCredentials.getKeyManagers() != null) {
                keyManagerArr = (KeyManager[]) tlsChannelCredentials.getKeyManagers().toArray(new KeyManager[0]);
            } else {
                if (tlsChannelCredentials.getPrivateKey() != null) {
                    return SslSocketFactoryResult.error("byte[]-based private key unsupported. Use KeyManager");
                }
                keyManagerArr = null;
            }
            if (tlsChannelCredentials.getTrustManagers() != null) {
                createTrustManager = (TrustManager[]) tlsChannelCredentials.getTrustManagers().toArray(new TrustManager[0]);
            } else if (tlsChannelCredentials.getRootCertificates() != null) {
                try {
                    createTrustManager = createTrustManager(tlsChannelCredentials.getRootCertificates());
                } catch (GeneralSecurityException e) {
                    log.log(Level.FINE, "Exception loading root certificates from credential", (Throwable) e);
                    return SslSocketFactoryResult.error("Unable to load root certificates: " + e.getMessage());
                }
            } else {
                createTrustManager = null;
            }
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS", Platform.get().getProvider());
                sSLContext.init(keyManagerArr, createTrustManager, null);
                return SslSocketFactoryResult.factory(sSLContext.getSocketFactory());
            } catch (GeneralSecurityException e2) {
                throw new RuntimeException("TLS Provider failure", e2);
            }
        }
        if (channelCredentials instanceof InsecureChannelCredentials) {
            return SslSocketFactoryResult.plaintext();
        }
        if (channelCredentials instanceof CompositeChannelCredentials) {
            CompositeChannelCredentials compositeChannelCredentials = (CompositeChannelCredentials) channelCredentials;
            return sslSocketFactoryFrom(compositeChannelCredentials.getChannelCredentials()).withCallCredentials(compositeChannelCredentials.getCallCredentials());
        }
        if (channelCredentials instanceof SslSocketFactoryChannelCredentials.ChannelCredentials) {
            return SslSocketFactoryResult.factory(((SslSocketFactoryChannelCredentials.ChannelCredentials) channelCredentials).getFactory());
        }
        if (channelCredentials instanceof ChoiceChannelCredentials) {
            StringBuilder sb = new StringBuilder();
            Iterator<ChannelCredentials> it = ((ChoiceChannelCredentials) channelCredentials).getCredentialsList().iterator();
            while (it.hasNext()) {
                SslSocketFactoryResult sslSocketFactoryFrom = sslSocketFactoryFrom(it.next());
                if (sslSocketFactoryFrom.error == null) {
                    return sslSocketFactoryFrom;
                }
                sb.append(", ");
                sb.append(sslSocketFactoryFrom.error);
            }
            return SslSocketFactoryResult.error(sb.substring(2));
        }
        return SslSocketFactoryResult.error("Unsupported credential type: " + channelCredentials.getClass().getName());
    }

    static TrustManager[] createTrustManager(byte[] bArr) throws GeneralSecurityException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            keyStore.load(null, null);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
                keyStore.setCertificateEntry(x509Certificate.getSubjectX500Principal().getName("RFC2253"), x509Certificate);
                GrpcUtil.closeQuietly(byteArrayInputStream);
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                return trustManagerFactory.getTrustManagers();
            } catch (Throwable th) {
                GrpcUtil.closeQuietly(byteArrayInputStream);
                throw th;
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SslSocketFactoryResult {
        public final CallCredentials callCredentials;
        public final String error;
        public final SSLSocketFactory factory;

        private SslSocketFactoryResult(SSLSocketFactory sSLSocketFactory, CallCredentials callCredentials, String str) {
            this.factory = sSLSocketFactory;
            this.callCredentials = callCredentials;
            this.error = str;
        }

        public static SslSocketFactoryResult error(String str) {
            return new SslSocketFactoryResult(null, null, (String) Preconditions.checkNotNull(str, "error"));
        }

        public static SslSocketFactoryResult plaintext() {
            return new SslSocketFactoryResult(null, null, null);
        }

        public static SslSocketFactoryResult factory(SSLSocketFactory sSLSocketFactory) {
            return new SslSocketFactoryResult((SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "factory"), null, null);
        }

        public SslSocketFactoryResult withCallCredentials(CallCredentials callCredentials) {
            Preconditions.checkNotNull(callCredentials, "callCreds");
            if (this.error != null) {
                return this;
            }
            CallCredentials callCredentials2 = this.callCredentials;
            if (callCredentials2 != null) {
                callCredentials = new CompositeCallCredentials(callCredentials2, callCredentials);
            }
            return new SslSocketFactoryResult(this.factory, callCredentials, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        private final Executor executor;
        private final int flowControlWindow;

        @Nullable
        private final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveBackoff;
        private final long keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        private final int maxInboundMetadataSize;
        private final int maxMessageSize;
        private final SocketFactory socketFactory;

        @Nullable
        private final SSLSocketFactory sslSocketFactory;
        private final ScheduledExecutorService timeoutService;
        private final TransportTracer.Factory transportTracerFactory;
        private final boolean useGetForSafeMethods;
        private final boolean usingSharedExecutor;
        private final boolean usingSharedScheduler;

        private OkHttpTransportFactory(Executor executor, @Nullable ScheduledExecutorService scheduledExecutorService, @Nullable SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, ConnectionSpec connectionSpec, int i, boolean z, long j, long j2, int i2, boolean z2, int i3, TransportTracer.Factory factory, boolean z3) {
            this.usingSharedScheduler = scheduledExecutorService == null;
            this.timeoutService = this.usingSharedScheduler ? (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : scheduledExecutorService;
            this.socketFactory = socketFactory;
            this.sslSocketFactory = sSLSocketFactory;
            this.hostnameVerifier = hostnameVerifier;
            this.connectionSpec = connectionSpec;
            this.maxMessageSize = i;
            this.enableKeepAlive = z;
            this.keepAliveTimeNanos = j;
            this.keepAliveBackoff = new AtomicBackoff("keepalive time nanos", j);
            this.keepAliveTimeoutNanos = j2;
            this.flowControlWindow = i2;
            this.keepAliveWithoutCalls = z2;
            this.maxInboundMetadataSize = i3;
            this.useGetForSafeMethods = z3;
            this.usingSharedExecutor = executor == null;
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(factory, "transportTracerFactory");
            if (this.usingSharedExecutor) {
                this.executor = (Executor) SharedResourceHolder.get(OkHttpChannelBuilder.SHARED_EXECUTOR);
            } else {
                this.executor = executor;
            }
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (this.closed) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            final AtomicBackoff.State state = this.keepAliveBackoff.getState();
            OkHttpClientTransport okHttpClientTransport = new OkHttpClientTransport((InetSocketAddress) socketAddress, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), clientTransportOptions.getEagAttributes(), this.executor, this.socketFactory, this.sslSocketFactory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.flowControlWindow, clientTransportOptions.getHttpConnectProxiedSocketAddress(), new Runnable() { // from class: io.grpc.okhttp.OkHttpChannelBuilder.OkHttpTransportFactory.1
                @Override // java.lang.Runnable
                public void run() {
                    state.backoff();
                }
            }, this.maxInboundMetadataSize, this.transportTracerFactory.create(), this.useGetForSafeMethods);
            if (this.enableKeepAlive) {
                okHttpClientTransport.enableKeepAlive(true, state.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            }
            return okHttpClientTransport;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timeoutService;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        @CheckReturnValue
        @Nullable
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials) {
            SslSocketFactoryResult sslSocketFactoryFrom = OkHttpChannelBuilder.sslSocketFactoryFrom(channelCredentials);
            if (sslSocketFactoryFrom.error != null) {
                return null;
            }
            return new ClientTransportFactory.SwapChannelCredentialsResult(new OkHttpTransportFactory(this.executor, this.timeoutService, this.socketFactory, sslSocketFactoryFrom.factory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.enableKeepAlive, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, this.useGetForSafeMethods), sslSocketFactoryFrom.callCredentials);
        }

        @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.usingSharedScheduler) {
                SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timeoutService);
            }
            if (this.usingSharedExecutor) {
                SharedResourceHolder.release(OkHttpChannelBuilder.SHARED_EXECUTOR, this.executor);
            }
        }
    }
}
