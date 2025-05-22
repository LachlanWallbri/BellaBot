package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ServerBuilder;
import io.grpc.ServerCredentials;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalServer;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators;
import io.grpc.netty.shaded.io.netty.channel.ChannelFactory;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ReflectiveChannelFactory;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import java.io.File;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.net.ssl.SSLException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NettyServerBuilder extends AbstractServerImplBuilder<NettyServerBuilder> {
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 1048576;
    static final long MAX_CONNECTION_AGE_GRACE_NANOS_INFINITE = Long.MAX_VALUE;
    static final long MAX_CONNECTION_AGE_NANOS_DISABLED = Long.MAX_VALUE;
    static final long MAX_CONNECTION_IDLE_NANOS_DISABLED = Long.MAX_VALUE;
    private boolean forceHeapBuffer;
    private final boolean freezeProtocolNegotiatorFactory;
    private boolean permitKeepAliveWithoutCalls;
    private ProtocolNegotiator.ServerFactory protocolNegotiatorFactory;
    private static final long MIN_KEEPALIVE_TIME_NANO = TimeUnit.MILLISECONDS.toNanos(1);
    private static final long MIN_KEEPALIVE_TIMEOUT_NANO = TimeUnit.MICROSECONDS.toNanos(499);
    private static final long MIN_MAX_CONNECTION_IDLE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final long MIN_MAX_CONNECTION_AGE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    private static final ObjectPool<? extends EventLoopGroup> DEFAULT_BOSS_EVENT_LOOP_GROUP_POOL = SharedResourcePool.forResource(Utils.DEFAULT_BOSS_EVENT_LOOP_GROUP);
    private static final ObjectPool<? extends EventLoopGroup> DEFAULT_WORKER_EVENT_LOOP_GROUP_POOL = SharedResourcePool.forResource(Utils.DEFAULT_WORKER_EVENT_LOOP_GROUP);
    private final List<SocketAddress> listenAddresses = new ArrayList();
    private TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
    private ChannelFactory<? extends ServerChannel> channelFactory = Utils.DEFAULT_SERVER_CHANNEL_FACTORY;
    private final Map<ChannelOption<?>, Object> channelOptions = new HashMap();
    private final Map<ChannelOption<?>, Object> childChannelOptions = new HashMap();
    private ObjectPool<? extends EventLoopGroup> bossEventLoopGroupPool = DEFAULT_BOSS_EVENT_LOOP_GROUP_POOL;
    private ObjectPool<? extends EventLoopGroup> workerEventLoopGroupPool = DEFAULT_WORKER_EVENT_LOOP_GROUP_POOL;
    private int maxConcurrentCallsPerConnection = Integer.MAX_VALUE;
    private boolean autoFlowControl = true;
    private int flowControlWindow = 1048576;
    private int maxMessageSize = 4194304;
    private int maxHeaderListSize = 8192;
    private long keepAliveTimeInNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIME_NANOS;
    private long keepAliveTimeoutInNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS;
    private long maxConnectionIdleInNanos = Long.MAX_VALUE;
    private long maxConnectionAgeInNanos = Long.MAX_VALUE;
    private long maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
    private long permitKeepAliveTimeInNanos = TimeUnit.MINUTES.toNanos(5);
    private Attributes eagAttributes = Attributes.EMPTY;
    private final ServerImplBuilder serverImplBuilder = new ServerImplBuilder(new NettyClientTransportServersBuilder());

    @CheckReturnValue
    public static NettyServerBuilder forPort(int i) {
        return forAddress(new InetSocketAddress(i));
    }

    @CheckReturnValue
    public static NettyServerBuilder forPort(int i, ServerCredentials serverCredentials) {
        return forAddress(new InetSocketAddress(i), serverCredentials);
    }

    @CheckReturnValue
    public static NettyServerBuilder forAddress(SocketAddress socketAddress) {
        return new NettyServerBuilder(socketAddress);
    }

    @CheckReturnValue
    public static NettyServerBuilder forAddress(SocketAddress socketAddress, ServerCredentials serverCredentials) {
        ProtocolNegotiators.FromServerCredentialsResult from = ProtocolNegotiators.from(serverCredentials);
        if (from.error != null) {
            throw new IllegalArgumentException(from.error);
        }
        return new NettyServerBuilder(socketAddress, from.negotiator);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class NettyClientTransportServersBuilder implements ServerImplBuilder.ClientTransportServersBuilder {
        private NettyClientTransportServersBuilder() {
        }

        @Override // io.grpc.internal.ServerImplBuilder.ClientTransportServersBuilder
        public InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> list) {
            return NettyServerBuilder.this.buildTransportServers(list);
        }
    }

    @CheckReturnValue
    private NettyServerBuilder(SocketAddress socketAddress) {
        this.listenAddresses.add(socketAddress);
        this.protocolNegotiatorFactory = ProtocolNegotiators.serverPlaintextFactory();
        this.freezeProtocolNegotiatorFactory = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckReturnValue
    public NettyServerBuilder(SocketAddress socketAddress, ProtocolNegotiator.ServerFactory serverFactory) {
        this.listenAddresses.add(socketAddress);
        this.protocolNegotiatorFactory = (ProtocolNegotiator.ServerFactory) Preconditions.checkNotNull(serverFactory, "negotiatorFactory");
        this.freezeProtocolNegotiatorFactory = true;
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder
    protected ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NettyServerBuilder addListenAddress(SocketAddress socketAddress) {
        this.listenAddresses.add(Preconditions.checkNotNull(socketAddress, "listenAddress"));
        return this;
    }

    public NettyServerBuilder channelType(Class<? extends ServerChannel> cls) {
        Preconditions.checkNotNull(cls, "channelType");
        return channelFactory(new ReflectiveChannelFactory(cls));
    }

    public NettyServerBuilder channelFactory(ChannelFactory<? extends ServerChannel> channelFactory) {
        this.channelFactory = (ChannelFactory) Preconditions.checkNotNull(channelFactory, "channelFactory");
        return this;
    }

    public <T> NettyServerBuilder withOption(ChannelOption<T> channelOption, T t) {
        this.channelOptions.put(channelOption, t);
        return this;
    }

    public <T> NettyServerBuilder withChildOption(ChannelOption<T> channelOption, T t) {
        this.childChannelOptions.put(channelOption, t);
        return this;
    }

    public NettyServerBuilder bossEventLoopGroup(EventLoopGroup eventLoopGroup) {
        if (eventLoopGroup != null) {
            return bossEventLoopGroupPool(new FixedObjectPool(eventLoopGroup));
        }
        return bossEventLoopGroupPool(DEFAULT_BOSS_EVENT_LOOP_GROUP_POOL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyServerBuilder bossEventLoopGroupPool(ObjectPool<? extends EventLoopGroup> objectPool) {
        this.bossEventLoopGroupPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "bossEventLoopGroupPool");
        return this;
    }

    public NettyServerBuilder workerEventLoopGroup(EventLoopGroup eventLoopGroup) {
        if (eventLoopGroup != null) {
            return workerEventLoopGroupPool(new FixedObjectPool(eventLoopGroup));
        }
        return workerEventLoopGroupPool(DEFAULT_WORKER_EVENT_LOOP_GROUP_POOL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyServerBuilder workerEventLoopGroupPool(ObjectPool<? extends EventLoopGroup> objectPool) {
        this.workerEventLoopGroupPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "workerEventLoopGroupPool");
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setForceHeapBuffer(boolean z) {
        this.forceHeapBuffer = z;
    }

    public NettyServerBuilder sslContext(SslContext sslContext) {
        Preconditions.checkState(!this.freezeProtocolNegotiatorFactory, "Cannot change security when using ServerCredentials");
        if (sslContext != null) {
            Preconditions.checkArgument(sslContext.isServer(), "Client SSL context can not be used for server");
            GrpcSslContexts.ensureAlpnAndH2Enabled(sslContext.applicationProtocolNegotiator());
            this.protocolNegotiatorFactory = ProtocolNegotiators.serverTlsFactory(sslContext);
        } else {
            this.protocolNegotiatorFactory = ProtocolNegotiators.serverPlaintextFactory();
        }
        return this;
    }

    public final NettyServerBuilder protocolNegotiator(ProtocolNegotiator protocolNegotiator) {
        Preconditions.checkState(!this.freezeProtocolNegotiatorFactory, "Cannot change security when using ServerCredentials");
        this.protocolNegotiatorFactory = ProtocolNegotiators.fixedServerFactory(protocolNegotiator);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTracingEnabled(boolean z) {
        this.serverImplBuilder.setTracingEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.serverImplBuilder.setStatsEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsRecordStartedRpcs(boolean z) {
        this.serverImplBuilder.setStatsRecordStartedRpcs(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.serverImplBuilder.setStatsRecordRealTimeMetrics(z);
    }

    public NettyServerBuilder maxConcurrentCallsPerConnection(int i) {
        Preconditions.checkArgument(i > 0, "max must be positive: %s", i);
        this.maxConcurrentCallsPerConnection = i;
        return this;
    }

    public NettyServerBuilder initialFlowControlWindow(int i) {
        Preconditions.checkArgument(i > 0, "initialFlowControlWindow must be positive");
        this.flowControlWindow = i;
        this.autoFlowControl = true;
        return this;
    }

    public NettyServerBuilder flowControlWindow(int i) {
        Preconditions.checkArgument(i > 0, "flowControlWindow must be positive: %s", i);
        this.flowControlWindow = i;
        this.autoFlowControl = false;
        return this;
    }

    @Deprecated
    public NettyServerBuilder maxMessageSize(int i) {
        return maxInboundMessageSize(i);
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public NettyServerBuilder maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "bytes must be non-negative: %s", i);
        this.maxMessageSize = i;
        return this;
    }

    @Deprecated
    public NettyServerBuilder maxHeaderListSize(int i) {
        return maxInboundMetadataSize(i);
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public NettyServerBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be positive: %s", i);
        this.maxHeaderListSize = i;
        return this;
    }

    public NettyServerBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive time must be positive：%s", j);
        this.keepAliveTimeInNanos = timeUnit.toNanos(j);
        this.keepAliveTimeInNanos = KeepAliveManager.clampKeepAliveTimeInNanos(this.keepAliveTimeInNanos);
        if (this.keepAliveTimeInNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeInNanos = Long.MAX_VALUE;
        }
        long j2 = this.keepAliveTimeInNanos;
        long j3 = MIN_KEEPALIVE_TIME_NANO;
        if (j2 < j3) {
            this.keepAliveTimeInNanos = j3;
        }
        return this;
    }

    public NettyServerBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive timeout must be positive: %s", j);
        this.keepAliveTimeoutInNanos = timeUnit.toNanos(j);
        this.keepAliveTimeoutInNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(this.keepAliveTimeoutInNanos);
        long j2 = this.keepAliveTimeoutInNanos;
        long j3 = MIN_KEEPALIVE_TIMEOUT_NANO;
        if (j2 < j3) {
            this.keepAliveTimeoutInNanos = j3;
        }
        return this;
    }

    public NettyServerBuilder maxConnectionIdle(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "max connection idle must be positive: %s", j);
        this.maxConnectionIdleInNanos = timeUnit.toNanos(j);
        if (this.maxConnectionIdleInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionIdleInNanos = Long.MAX_VALUE;
        }
        long j2 = this.maxConnectionIdleInNanos;
        long j3 = MIN_MAX_CONNECTION_IDLE_NANO;
        if (j2 < j3) {
            this.maxConnectionIdleInNanos = j3;
        }
        return this;
    }

    public NettyServerBuilder maxConnectionAge(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "max connection age must be positive: %s", j);
        this.maxConnectionAgeInNanos = timeUnit.toNanos(j);
        if (this.maxConnectionAgeInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeInNanos = Long.MAX_VALUE;
        }
        long j2 = this.maxConnectionAgeInNanos;
        long j3 = MIN_MAX_CONNECTION_AGE_NANO;
        if (j2 < j3) {
            this.maxConnectionAgeInNanos = j3;
        }
        return this;
    }

    public NettyServerBuilder maxConnectionAgeGrace(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "max connection age grace must be non-negative: %s", j);
        this.maxConnectionAgeGraceInNanos = timeUnit.toNanos(j);
        if (this.maxConnectionAgeGraceInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
        }
        return this;
    }

    public NettyServerBuilder permitKeepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "permit keepalive time must be non-negative: %s", j);
        this.permitKeepAliveTimeInNanos = timeUnit.toNanos(j);
        return this;
    }

    public NettyServerBuilder permitKeepAliveWithoutCalls(boolean z) {
        this.permitKeepAliveWithoutCalls = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void eagAttributes(Attributes attributes) {
        this.eagAttributes = (Attributes) Preconditions.checkNotNull(attributes, "eagAttributes");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckReturnValue
    public NettyServer buildTransportServers(List<? extends ServerStreamTracer.Factory> list) {
        assertEventLoopsAndChannelType();
        return new NettyServer(this.listenAddresses, this.channelFactory, this.channelOptions, this.childChannelOptions, this.bossEventLoopGroupPool, this.workerEventLoopGroupPool, this.forceHeapBuffer, this.protocolNegotiatorFactory.newNegotiator(this.serverImplBuilder.getExecutorPool()), list, this.transportTracerFactory, this.maxConcurrentCallsPerConnection, this.autoFlowControl, this.flowControlWindow, this.maxMessageSize, this.maxHeaderListSize, this.keepAliveTimeInNanos, this.keepAliveTimeoutInNanos, this.maxConnectionIdleInNanos, this.maxConnectionAgeInNanos, this.maxConnectionAgeGraceInNanos, this.permitKeepAliveWithoutCalls, this.permitKeepAliveTimeInNanos, this.eagAttributes, this.serverImplBuilder.getChannelz());
    }

    void assertEventLoopsAndChannelType() {
        boolean z = true;
        boolean z2 = (this.channelFactory == Utils.DEFAULT_SERVER_CHANNEL_FACTORY || this.bossEventLoopGroupPool == DEFAULT_BOSS_EVENT_LOOP_GROUP_POOL || this.workerEventLoopGroupPool == DEFAULT_WORKER_EVENT_LOOP_GROUP_POOL) ? false : true;
        boolean z3 = this.channelFactory == Utils.DEFAULT_SERVER_CHANNEL_FACTORY && this.bossEventLoopGroupPool == DEFAULT_BOSS_EVENT_LOOP_GROUP_POOL && this.workerEventLoopGroupPool == DEFAULT_WORKER_EVENT_LOOP_GROUP_POOL;
        if (!z2 && !z3) {
            z = false;
        }
        Preconditions.checkState(z, "All of BossEventLoopGroup, WorkerEventLoopGroup and ChannelType should be provided or neither should be");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyServerBuilder setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return this;
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public NettyServerBuilder useTransportSecurity(File file, File file2) {
        Preconditions.checkState(!this.freezeProtocolNegotiatorFactory, "Cannot change security when using ServerCredentials");
        try {
            this.protocolNegotiatorFactory = ProtocolNegotiators.serverTlsFactory(GrpcSslContexts.forServer(file, file2).build());
            return this;
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public NettyServerBuilder useTransportSecurity(InputStream inputStream, InputStream inputStream2) {
        Preconditions.checkState(!this.freezeProtocolNegotiatorFactory, "Cannot change security when using ServerCredentials");
        try {
            this.protocolNegotiatorFactory = ProtocolNegotiators.serverTlsFactory(GrpcSslContexts.forServer(inputStream, inputStream2).build());
            return this;
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }
}
