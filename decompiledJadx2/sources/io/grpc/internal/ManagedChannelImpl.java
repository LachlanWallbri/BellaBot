package io.grpc.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ClientStreamTracer;
import io.grpc.CompressorRegistry;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.ForwardingClientCall;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.InternalConfigSelector;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.NameResolverRegistry;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.AutoConfiguredLoadBalancerFactory;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.CallTracer;
import io.grpc.internal.ClientCallImpl;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.ManagedChannelServiceConfig;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.RetriableStream;
import java.lang.Thread;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented<InternalChannelz.ChannelStats> {
    static final long IDLE_TIMEOUT_MILLIS_DISABLE = -1;
    static final long SUBCHANNEL_SHUTDOWN_DELAY_SECONDS = 5;

    @Nullable
    private final String authorityOverride;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private final ExecutorHolder balancerRpcExecutorHolder;
    private final ObjectPool<? extends Executor> balancerRpcExecutorPool;
    private final CallTracer.Factory callTracerFactory;
    private final long channelBufferLimit;
    private final CallTracer channelCallTracer;
    private final ChannelLogger channelLogger;
    private final ChannelTracer channelTracer;
    private final InternalChannelz channelz;
    private final CompressorRegistry compressorRegistry;
    private final DecompressorRegistry decompressorRegistry;

    @Nullable
    private final ManagedChannelServiceConfig defaultServiceConfig;
    private final DelayedClientTransport delayedTransport;
    private final ManagedClientTransport.Listener delayedTransportListener;
    private final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    private boolean fullStreamDecompression;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    final InUseStateAggregator<Object> inUseStateAggregator;
    private final Channel interceptorChannel;
    private ManagedChannelServiceConfig lastServiceConfig;

    @Nullable
    private LbHelperImpl lbHelper;
    private final AutoConfiguredLoadBalancerFactory loadBalancerFactory;
    private final InternalLogId logId;
    private final boolean lookUpServiceConfig;
    private final int maxTraceEvents;
    private NameResolver nameResolver;
    private final NameResolver.Args nameResolverArgs;

    @Nullable
    private BackoffPolicy nameResolverBackoffPolicy;
    private final NameResolver.Factory nameResolverFactory;
    private final NameResolverRegistry nameResolverRegistry;
    private boolean nameResolverStarted;
    private final ExecutorHolder offloadExecutorHolder;
    private final ClientTransportFactory oobTransportFactory;

    @Nullable
    private final ChannelCredentials originalChannelCreds;
    private final ClientTransportFactory originalTransportFactory;
    private boolean panicMode;

    @Nullable
    private Collection<RealChannel.PendingCall<?, ?>> pendingCalls;
    private final long perRpcBufferLimit;
    private final RealChannel realChannel;
    private final boolean retryEnabled;
    private final RestrictedScheduledExecutor scheduledExecutor;

    @Nullable
    private SynchronizationContext.ScheduledHandle scheduledNameResolverRefresh;
    private boolean serviceConfigUpdated;
    private boolean shutdownNowed;
    private final Supplier<Stopwatch> stopwatchSupplier;

    @Nullable
    private volatile LoadBalancer.SubchannelPicker subchannelPicker;
    private final String target;
    private volatile boolean terminated;
    private boolean terminating;
    private final TimeProvider timeProvider;
    private final ClientTransportFactory transportFactory;
    private final ClientCallImpl.ClientStreamProvider transportProvider;
    private final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry;

    @Nullable
    private final String userAgent;
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    private static final ManagedChannelServiceConfig EMPTY_SERVICE_CONFIG = ManagedChannelServiceConfig.empty();
    private static final InternalConfigSelector INITIAL_PENDING_SELECTOR = new InternalConfigSelector() { // from class: io.grpc.internal.ManagedChannelImpl.1
        @Override // io.grpc.InternalConfigSelector
        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            throw new IllegalStateException("Resolution is pending");
        }
    };
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() { // from class: io.grpc.internal.ManagedChannelImpl.5
        @Override // io.grpc.ClientCall
        public void cancel(String str, Throwable th) {
        }

        @Override // io.grpc.ClientCall
        public void halfClose() {
        }

        @Override // io.grpc.ClientCall
        public boolean isReady() {
            return false;
        }

        @Override // io.grpc.ClientCall
        public void request(int i) {
        }

        @Override // io.grpc.ClientCall
        public void sendMessage(Object obj) {
        }

        @Override // io.grpc.ClientCall
        public void start(ClientCall.Listener<Object> listener, Metadata metadata) {
        }
    };
    final SynchronizationContext syncContext = new SynchronizationContext(new Thread.UncaughtExceptionHandler() { // from class: io.grpc.internal.ManagedChannelImpl.2
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            ManagedChannelImpl.logger.log(Level.SEVERE, "[" + ManagedChannelImpl.this.getLogId() + "] Uncaught exception in the SynchronizationContext. Panic!", th);
            ManagedChannelImpl.this.panic(th);
        }
    });
    private final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    private final Set<InternalSubchannel> subchannels = new HashSet(16, 0.75f);
    private final Object pendingCallsInUseObject = new Object();
    private final Set<OobChannel> oobChannels = new HashSet(1, 0.75f);
    private final AtomicBoolean shutdown = new AtomicBoolean(false);
    private final CountDownLatch terminatedLatch = new CountDownLatch(1);
    private ResolutionState lastResolutionState = ResolutionState.NO_RESOLUTION;
    private final RetriableStream.ChannelBufferMeter channelBufferUsed = new RetriableStream.ChannelBufferMeter();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum ResolutionState {
        NO_RESOLUTION,
        SUCCESS,
        ERROR
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            Iterator<InternalSubchannel> it = this.subchannels.iterator();
            while (it.hasNext()) {
                it.next().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            Iterator<OobChannel> it2 = this.oobChannels.iterator();
            while (it2.hasNext()) {
                it2.next().getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1StatsFetcher
            @Override // java.lang.Runnable
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                ManagedChannelImpl.this.channelCallTracer.updateBuilder(builder);
                ManagedChannelImpl.this.channelTracer.updateBuilder(builder);
                builder.setTarget(ManagedChannelImpl.this.target).setState(ManagedChannelImpl.this.channelStateManager.getState());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(ManagedChannelImpl.this.subchannels);
                arrayList.addAll(ManagedChannelImpl.this.oobChannels);
                builder.setSubchannels(arrayList);
                create.set(builder.build());
            }
        });
        return create;
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class IdleModeTimer implements Runnable {
        private IdleModeTimer() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ManagedChannelImpl.this.enterIdleMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownNameResolverAndLoadBalancer(boolean z) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (z) {
            Preconditions.checkState(this.nameResolverStarted, "nameResolver is not started");
            Preconditions.checkState(this.lbHelper != null, "lbHelper is null");
        }
        if (this.nameResolver != null) {
            cancelNameResolverBackoff();
            this.nameResolver.shutdown();
            this.nameResolverStarted = false;
            if (z) {
                this.nameResolver = getNameResolver(this.target, this.authorityOverride, this.nameResolverFactory, this.nameResolverArgs);
            } else {
                this.nameResolver = null;
            }
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            lbHelperImpl.f8318lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    void exitIdleMode() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.shutdown.get() || this.panicMode) {
            return;
        }
        if (this.inUseStateAggregator.isInUse()) {
            cancelIdleTimer(false);
        } else {
            rescheduleIdleTimer();
        }
        if (this.lbHelper != null) {
            return;
        }
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Exiting idle mode");
        LbHelperImpl lbHelperImpl = new LbHelperImpl();
        lbHelperImpl.f8318lb = this.loadBalancerFactory.newLoadBalancer(lbHelperImpl);
        this.lbHelper = lbHelperImpl;
        this.nameResolver.start((NameResolver.Listener2) new NameResolverListener(lbHelperImpl, this.nameResolver));
        this.nameResolverStarted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterIdleMode() {
        shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess(null);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering IDLE state");
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
        if (this.inUseStateAggregator.isInUse()) {
            exitIdleMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelIdleTimer(boolean z) {
        this.idleTimer.cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j == -1) {
            return;
        }
        this.idleTimer.reschedule(j, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class DelayedNameResolverRefresh implements Runnable {
        DelayedNameResolverRefresh() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ManagedChannelImpl.this.scheduledNameResolverRefresh = null;
            ManagedChannelImpl.this.refreshNameResolution();
        }
    }

    private void cancelNameResolverBackoff() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduledNameResolverRefresh;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.scheduledNameResolverRefresh = null;
            this.nameResolverBackoffPolicy = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshAndResetNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        cancelNameResolverBackoff();
        refreshNameResolution();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.nameResolverStarted) {
            this.nameResolver.refresh();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class ChannelStreamProvider implements ClientCallImpl.ClientStreamProvider {
        private ChannelStreamProvider() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ClientTransport getTransport(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            LoadBalancer.SubchannelPicker subchannelPicker = ManagedChannelImpl.this.subchannelPicker;
            if (ManagedChannelImpl.this.shutdown.get()) {
                return ManagedChannelImpl.this.delayedTransport;
            }
            if (subchannelPicker == null) {
                ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.ChannelStreamProvider.1ExitIdleModeForTransport
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagedChannelImpl.this.exitIdleMode();
                    }
                });
                return ManagedChannelImpl.this.delayedTransport;
            }
            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(subchannelPicker.pickSubchannel(pickSubchannelArgs), pickSubchannelArgs.getCallOptions().isWaitForReady());
            return transportFromPickResult != null ? transportFromPickResult : ManagedChannelImpl.this.delayedTransport;
        }

        @Override // io.grpc.internal.ClientCallImpl.ClientStreamProvider
        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            if (ManagedChannelImpl.this.retryEnabled) {
                RetriableStream.Throttle retryThrottling = ManagedChannelImpl.this.lastServiceConfig.getRetryThrottling();
                ManagedChannelServiceConfig.MethodInfo methodInfo = (ManagedChannelServiceConfig.MethodInfo) callOptions.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
                return new RetriableStream<ReqT>(methodDescriptor, metadata, callOptions, methodInfo == null ? null : methodInfo.retryPolicy, methodInfo == null ? null : methodInfo.hedgingPolicy, retryThrottling, context) { // from class: io.grpc.internal.ManagedChannelImpl.ChannelStreamProvider.1RetryStream
                    final /* synthetic */ CallOptions val$callOptions;
                    final /* synthetic */ Context val$context;
                    final /* synthetic */ Metadata val$headers;
                    final /* synthetic */ HedgingPolicy val$hedgingPolicy;
                    final /* synthetic */ MethodDescriptor val$method;
                    final /* synthetic */ RetryPolicy val$retryPolicy;
                    final /* synthetic */ RetriableStream.Throttle val$throttle;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(methodDescriptor, metadata, ManagedChannelImpl.this.channelBufferUsed, ManagedChannelImpl.this.perRpcBufferLimit, ManagedChannelImpl.this.channelBufferLimit, ManagedChannelImpl.this.getCallExecutor(callOptions), ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), r20, r21, retryThrottling);
                        this.val$method = methodDescriptor;
                        this.val$headers = metadata;
                        this.val$callOptions = callOptions;
                        this.val$retryPolicy = r20;
                        this.val$hedgingPolicy = r21;
                        this.val$throttle = retryThrottling;
                        this.val$context = context;
                    }

                    @Override // io.grpc.internal.RetriableStream
                    Status prestart() {
                        return ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.add(this);
                    }

                    @Override // io.grpc.internal.RetriableStream
                    void postCommit() {
                        ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.remove(this);
                    }

                    @Override // io.grpc.internal.RetriableStream
                    ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata2) {
                        CallOptions withStreamTracerFactory = this.val$callOptions.withStreamTracerFactory(factory);
                        ClientTransport transport = ChannelStreamProvider.this.getTransport(new PickSubchannelArgsImpl(this.val$method, metadata2, withStreamTracerFactory));
                        Context attach = this.val$context.attach();
                        try {
                            return transport.newStream(this.val$method, metadata2, withStreamTracerFactory);
                        } finally {
                            this.val$context.detach(attach);
                        }
                    }
                };
            }
            ClientTransport transport = getTransport(new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions));
            Context attach = context.attach();
            try {
                return transport.newStream(methodDescriptor, metadata, callOptions);
            } finally {
                context.detach(attach);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ManagedChannelImpl(ManagedChannelImplBuilder managedChannelImplBuilder, ClientTransportFactory clientTransportFactory, BackoffPolicy.Provider provider, ObjectPool<? extends Executor> objectPool, Supplier<Stopwatch> supplier, List<ClientInterceptor> list, final TimeProvider timeProvider) {
        this.uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
        this.lastServiceConfig = EMPTY_SERVICE_CONFIG;
        this.serviceConfigUpdated = false;
        this.delayedTransportListener = new DelayedTransportListener();
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelStreamProvider();
        this.target = (String) Preconditions.checkNotNull(managedChannelImplBuilder.target, TypedValues.Attributes.S_TARGET);
        this.logId = InternalLogId.allocate("Channel", this.target);
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider, "timeProvider");
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder.executorPool, "executorPool");
        this.executor = (Executor) Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
        this.originalChannelCreds = managedChannelImplBuilder.channelCredentials;
        this.originalTransportFactory = clientTransportFactory;
        this.transportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, managedChannelImplBuilder.callCredentials, this.executor);
        this.oobTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, null, this.executor);
        this.scheduledExecutor = new RestrictedScheduledExecutor(this.transportFactory.getScheduledExecutorService());
        this.maxTraceEvents = managedChannelImplBuilder.maxTraceEvents;
        this.channelTracer = new ChannelTracer(this.logId, managedChannelImplBuilder.maxTraceEvents, timeProvider.currentTimeNanos(), "Channel for '" + this.target + "'");
        this.channelLogger = new ChannelLoggerImpl(this.channelTracer, timeProvider);
        ProxyDetector proxyDetector = managedChannelImplBuilder.proxyDetector != null ? managedChannelImplBuilder.proxyDetector : GrpcUtil.DEFAULT_PROXY_DETECTOR;
        this.retryEnabled = managedChannelImplBuilder.retryEnabled && !managedChannelImplBuilder.temporarilyDisableRetry;
        this.loadBalancerFactory = new AutoConfiguredLoadBalancerFactory(managedChannelImplBuilder.defaultLbPolicy);
        this.offloadExecutorHolder = new ExecutorHolder((ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder.offloadExecutorPool, "offloadExecutorPool"));
        this.nameResolverRegistry = managedChannelImplBuilder.nameResolverRegistry;
        ScParser scParser = new ScParser(this.retryEnabled, managedChannelImplBuilder.maxRetryAttempts, managedChannelImplBuilder.maxHedgedAttempts, this.loadBalancerFactory);
        this.nameResolverArgs = NameResolver.Args.newBuilder().setDefaultPort(managedChannelImplBuilder.getDefaultPort()).setProxyDetector(proxyDetector).setSynchronizationContext(this.syncContext).setScheduledExecutorService(this.scheduledExecutor).setServiceConfigParser(scParser).setChannelLogger(this.channelLogger).setOffloadExecutor(new Executor() { // from class: io.grpc.internal.ManagedChannelImpl.3
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                ManagedChannelImpl.this.offloadExecutorHolder.getExecutor().execute(runnable);
            }
        }).build();
        this.authorityOverride = managedChannelImplBuilder.authorityOverride;
        this.nameResolverFactory = managedChannelImplBuilder.nameResolverFactory;
        this.nameResolver = getNameResolver(this.target, this.authorityOverride, this.nameResolverFactory, this.nameResolverArgs);
        this.balancerRpcExecutorPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "balancerRpcExecutorPool");
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool);
        this.delayedTransport = new DelayedClientTransport(this.executor, this.syncContext);
        this.delayedTransport.start(this.delayedTransportListener);
        this.backoffPolicyProvider = provider;
        if (managedChannelImplBuilder.defaultServiceConfig != null) {
            NameResolver.ConfigOrError parseServiceConfig = scParser.parseServiceConfig(managedChannelImplBuilder.defaultServiceConfig);
            Preconditions.checkState(parseServiceConfig.getError() == null, "Default config is invalid: %s", parseServiceConfig.getError());
            this.defaultServiceConfig = (ManagedChannelServiceConfig) parseServiceConfig.getConfig();
            this.lastServiceConfig = this.defaultServiceConfig;
        } else {
            this.defaultServiceConfig = null;
        }
        this.lookUpServiceConfig = managedChannelImplBuilder.lookUpServiceConfig;
        this.realChannel = new RealChannel(this.nameResolver.getServiceAuthority());
        Channel channel = this.realChannel;
        this.interceptorChannel = ClientInterceptors.intercept(managedChannelImplBuilder.binlog != null ? managedChannelImplBuilder.binlog.wrapChannel(channel) : channel, list);
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        if (managedChannelImplBuilder.idleTimeoutMillis == -1) {
            this.idleTimeoutMillis = managedChannelImplBuilder.idleTimeoutMillis;
        } else {
            Preconditions.checkArgument(managedChannelImplBuilder.idleTimeoutMillis >= ManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS, "invalid idleTimeoutMillis %s", managedChannelImplBuilder.idleTimeoutMillis);
            this.idleTimeoutMillis = managedChannelImplBuilder.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), this.syncContext, this.transportFactory.getScheduledExecutorService(), supplier.get());
        this.fullStreamDecompression = managedChannelImplBuilder.fullStreamDecompression;
        this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = (CompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder.compressorRegistry, "compressorRegistry");
        this.userAgent = managedChannelImplBuilder.userAgent;
        this.channelBufferLimit = managedChannelImplBuilder.retryBufferSize;
        this.perRpcBufferLimit = managedChannelImplBuilder.perRpcBufferLimit;
        this.callTracerFactory = new CallTracer.Factory() { // from class: io.grpc.internal.ManagedChannelImpl.1ChannelCallTracerFactory
            @Override // io.grpc.internal.CallTracer.Factory
            public CallTracer create() {
                return new CallTracer(timeProvider);
            }
        };
        this.channelCallTracer = this.callTracerFactory.create();
        this.channelz = (InternalChannelz) Preconditions.checkNotNull(managedChannelImplBuilder.channelz);
        this.channelz.addRootChannel(this);
        if (this.lookUpServiceConfig) {
            return;
        }
        if (this.defaultServiceConfig != null) {
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Service config look-up disabled, using default service config");
        }
        this.serviceConfigUpdated = true;
    }

    private static NameResolver getNameResolver(String str, NameResolver.Factory factory, NameResolver.Args args) {
        URI uri;
        NameResolver newNameResolver;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        if (uri != null && (newNameResolver = factory.newNameResolver(uri, args)) != null) {
            return newNameResolver;
        }
        String str2 = "";
        if (!URI_PATTERN.matcher(str).matches()) {
            try {
                NameResolver newNameResolver2 = factory.newNameResolver(new URI(factory.getDefaultScheme(), "", "/" + str, null), args);
                if (newNameResolver2 != null) {
                    return newNameResolver2;
                }
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (sb.length() > 0) {
            str2 = " (" + ((Object) sb) + ")";
        }
        objArr[1] = str2;
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", objArr));
    }

    static NameResolver getNameResolver(String str, @Nullable final String str2, NameResolver.Factory factory, NameResolver.Args args) {
        NameResolver nameResolver = getNameResolver(str, factory, args);
        return str2 == null ? nameResolver : new ForwardingNameResolver(nameResolver) { // from class: io.grpc.internal.ManagedChannelImpl.4
            @Override // io.grpc.internal.ForwardingNameResolver, io.grpc.NameResolver
            public String getServiceAuthority() {
                return str2;
            }
        };
    }

    InternalConfigSelector getConfigSelector() {
        return (InternalConfigSelector) this.realChannel.configSelector.get();
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannelImpl shutdown() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdown() called");
        if (!this.shutdown.compareAndSet(false, true)) {
            return this;
        }
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1Shutdown
            @Override // java.lang.Runnable
            public void run() {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering SHUTDOWN state");
                ManagedChannelImpl.this.channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            }
        });
        this.realChannel.shutdown();
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1CancelIdleTimer
            @Override // java.lang.Runnable
            public void run() {
                ManagedChannelImpl.this.cancelIdleTimer(true);
            }
        });
        return this;
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannelImpl shutdownNow() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdownNow() called");
        shutdown();
        this.realChannel.shutdownNow();
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1ShutdownNow
            @Override // java.lang.Runnable
            public void run() {
                if (ManagedChannelImpl.this.shutdownNowed) {
                    return;
                }
                ManagedChannelImpl.this.shutdownNowed = true;
                ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            }
        });
        return this;
    }

    void panic(final Throwable th) {
        if (this.panicMode) {
            return;
        }
        this.panicMode = true;
        cancelIdleTimer(true);
        shutdownNameResolverAndLoadBalancer(false);
        updateSubchannelPicker(new LoadBalancer.SubchannelPicker() { // from class: io.grpc.internal.ManagedChannelImpl.1PanicSubchannelPicker
            private final LoadBalancer.PickResult panicPickResult;

            {
                this.panicPickResult = LoadBalancer.PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(th));
            }

            @Override // io.grpc.LoadBalancer.SubchannelPicker
            public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                return this.panicPickResult;
            }

            public String toString() {
                return MoreObjects.toStringHelper((Class<?>) C1PanicSubchannelPicker.class).add("panicPickResult", this.panicPickResult).toString();
            }
        });
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
        this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
    }

    boolean isInPanicMode() {
        return this.panicMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubchannelPicker(LoadBalancer.SubchannelPicker subchannelPicker) {
        this.subchannelPicker = subchannelPicker;
        this.delayedTransport.reprocess(subchannelPicker);
    }

    @Override // io.grpc.ManagedChannel
    public boolean isShutdown() {
        return this.shutdown.get();
    }

    @Override // io.grpc.ManagedChannel
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    @Override // io.grpc.ManagedChannel
    public boolean isTerminated() {
        return this.terminated;
    }

    @Override // io.grpc.Channel
    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    @Override // io.grpc.Channel
    public String authority() {
        return this.interceptorChannel.authority();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Executor getCallExecutor(CallOptions callOptions) {
        Executor executor = callOptions.getExecutor();
        return executor == null ? this.executor : executor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class RealChannel extends Channel {
        private final String authority;
        private final Channel clientCallImplChannel;
        private final AtomicReference<InternalConfigSelector> configSelector;

        private RealChannel(String str) {
            this.configSelector = new AtomicReference<>(ManagedChannelImpl.INITIAL_PENDING_SELECTOR);
            this.clientCallImplChannel = new Channel() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.1
                @Override // io.grpc.Channel
                public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
                    return new ClientCallImpl(methodDescriptor, ManagedChannelImpl.this.getCallExecutor(callOptions), callOptions, ManagedChannelImpl.this.transportProvider, ManagedChannelImpl.this.terminated ? null : ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelCallTracer, null).setFullStreamDecompression(ManagedChannelImpl.this.fullStreamDecompression).setDecompressorRegistry(ManagedChannelImpl.this.decompressorRegistry).setCompressorRegistry(ManagedChannelImpl.this.compressorRegistry);
                }

                @Override // io.grpc.Channel
                public String authority() {
                    return RealChannel.this.authority;
                }
            };
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        @Override // io.grpc.Channel
        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(methodDescriptor, callOptions);
            }
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.2
                @Override // java.lang.Runnable
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                }
            });
            if (this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                if (ManagedChannelImpl.this.shutdown.get()) {
                    return new ClientCall<ReqT, RespT>() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.3
                        @Override // io.grpc.ClientCall
                        public void cancel(@Nullable String str, @Nullable Throwable th) {
                        }

                        @Override // io.grpc.ClientCall
                        public void halfClose() {
                        }

                        @Override // io.grpc.ClientCall
                        public void request(int i) {
                        }

                        @Override // io.grpc.ClientCall
                        public void sendMessage(ReqT reqt) {
                        }

                        @Override // io.grpc.ClientCall
                        public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                            listener.onClose(ManagedChannelImpl.SHUTDOWN_STATUS, new Metadata());
                        }
                    };
                }
                final PendingCall pendingCall = new PendingCall(Context.current(), methodDescriptor, callOptions);
                ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                            if (ManagedChannelImpl.this.pendingCalls == null) {
                                ManagedChannelImpl.this.pendingCalls = new LinkedHashSet();
                                ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.pendingCallsInUseObject, true);
                            }
                            ManagedChannelImpl.this.pendingCalls.add(pendingCall);
                            return;
                        }
                        pendingCall.reprocess();
                    }
                });
                return pendingCall;
            }
            return newClientCall(methodDescriptor, callOptions);
        }

        void updateConfigSelector(@Nullable InternalConfigSelector internalConfigSelector) {
            InternalConfigSelector internalConfigSelector2 = this.configSelector.get();
            this.configSelector.set(internalConfigSelector);
            if (internalConfigSelector2 != ManagedChannelImpl.INITIAL_PENDING_SELECTOR || ManagedChannelImpl.this.pendingCalls == null) {
                return;
            }
            Iterator it = ManagedChannelImpl.this.pendingCalls.iterator();
            while (it.hasNext()) {
                ((PendingCall) it.next()).reprocess();
            }
        }

        void onConfigError() {
            if (this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                updateConfigSelector(null);
            }
        }

        void shutdown() {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.1RealChannelShutdown
                @Override // java.lang.Runnable
                public void run() {
                    if (ManagedChannelImpl.this.pendingCalls == null) {
                        if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                            RealChannel.this.configSelector.set(null);
                        }
                        ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    }
                }
            });
        }

        void shutdownNow() {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.1RealChannelShutdownNow
                @Override // java.lang.Runnable
                public void run() {
                    if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        RealChannel.this.configSelector.set(null);
                    }
                    if (ManagedChannelImpl.this.pendingCalls != null) {
                        Iterator it = ManagedChannelImpl.this.pendingCalls.iterator();
                        while (it.hasNext()) {
                            ((PendingCall) it.next()).cancel("Channel is forcefully shutdown", (Throwable) null);
                        }
                    }
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdownNow(ManagedChannelImpl.SHUTDOWN_NOW_STATUS);
                }
            });
        }

        @Override // io.grpc.Channel
        public String authority() {
            return this.authority;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public final class PendingCall<ReqT, RespT> extends DelayedClientCall<ReqT, RespT> {
            final CallOptions callOptions;
            final Context context;
            final MethodDescriptor<ReqT, RespT> method;

            PendingCall(Context context, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
                super(ManagedChannelImpl.this.getCallExecutor(callOptions), ManagedChannelImpl.this.scheduledExecutor, callOptions.getDeadline());
                this.context = context;
                this.method = methodDescriptor;
                this.callOptions = callOptions;
            }

            void reprocess() {
                ManagedChannelImpl.this.getCallExecutor(this.callOptions).execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.PendingCall.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Context attach = PendingCall.this.context.attach();
                        try {
                            ClientCall<ReqT, RespT> newClientCall = RealChannel.this.newClientCall(PendingCall.this.method, PendingCall.this.callOptions);
                            PendingCall.this.context.detach(attach);
                            PendingCall.this.setCall(newClientCall);
                            ManagedChannelImpl.this.syncContext.execute(new PendingCallRemoval());
                        } catch (Throwable th) {
                            PendingCall.this.context.detach(attach);
                            throw th;
                        }
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.internal.DelayedClientCall
            public void callCancelled() {
                super.callCancelled();
                ManagedChannelImpl.this.syncContext.execute(new PendingCallRemoval());
            }

            /* JADX WARN: Classes with same name are omitted:
              classes6.dex
             */
            /* loaded from: classes7.dex */
            final class PendingCallRemoval implements Runnable {
                PendingCallRemoval() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (ManagedChannelImpl.this.pendingCalls != null) {
                        ManagedChannelImpl.this.pendingCalls.remove(PendingCall.this);
                        if (ManagedChannelImpl.this.pendingCalls.isEmpty()) {
                            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.pendingCallsInUseObject, false);
                            ManagedChannelImpl.this.pendingCalls = null;
                            if (ManagedChannelImpl.this.shutdown.get()) {
                                ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                            }
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <ReqT, RespT> ClientCall<ReqT, RespT> newClientCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            InternalConfigSelector internalConfigSelector = this.configSelector.get();
            if (internalConfigSelector == null) {
                return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
            }
            if (internalConfigSelector instanceof ManagedChannelServiceConfig.ServiceConfigConvertedSelector) {
                ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig.ServiceConfigConvertedSelector) internalConfigSelector).config.getMethodConfig(methodDescriptor);
                if (methodConfig != null) {
                    callOptions = callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
                }
                return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
            }
            return new ConfigSelectingClientCall(internalConfigSelector, this.clientCallImplChannel, ManagedChannelImpl.this.executor, methodDescriptor, callOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ConfigSelectingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private final Executor callExecutor;
        private CallOptions callOptions;
        private final Channel channel;
        private final InternalConfigSelector configSelector;
        private final Context context;
        private ClientCall<ReqT, RespT> delegate;
        private final MethodDescriptor<ReqT, RespT> method;

        ConfigSelectingClientCall(InternalConfigSelector internalConfigSelector, Channel channel, Executor executor, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            this.configSelector = internalConfigSelector;
            this.channel = channel;
            this.method = methodDescriptor;
            this.callExecutor = callOptions.getExecutor() != null ? callOptions.getExecutor() : executor;
            this.callOptions = callOptions.withExecutor(this.callExecutor);
            this.context = Context.current();
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall
        protected ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
        public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
            InternalConfigSelector.Result selectConfig = this.configSelector.selectConfig(new PickSubchannelArgsImpl(this.method, metadata, this.callOptions));
            Status status = selectConfig.getStatus();
            if (!status.isOk()) {
                executeCloseObserverInContext(listener, status);
                this.delegate = ManagedChannelImpl.NOOP_CALL;
                return;
            }
            ClientInterceptor interceptor = selectConfig.getInterceptor();
            ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig) selectConfig.getConfig()).getMethodConfig(this.method);
            if (methodConfig != null) {
                this.callOptions = this.callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
            }
            if (interceptor != null) {
                this.delegate = interceptor.interceptCall(this.method, this.callOptions, this.channel);
            } else {
                this.delegate = this.channel.newCall(this.method, this.callOptions);
            }
            this.delegate.start(listener, metadata);
        }

        private void executeCloseObserverInContext(final ClientCall.Listener<RespT> listener, final Status status) {
            this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ManagedChannelImpl.ConfigSelectingClientCall.1CloseInContext
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(ConfigSelectingClientCall.this.context);
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    listener.onClose(status, new Metadata());
                }
            });
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
        public void cancel(@Nullable String str, @Nullable Throwable th) {
            ClientCall<ReqT, RespT> clientCall = this.delegate;
            if (clientCall != null) {
                clientCall.cancel(str, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
            this.channelz.removeRootChannel(this);
            this.executorPool.returnObject(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.offloadExecutorHolder.release();
            this.transportFactory.close();
            this.terminated = true;
            this.terminatedLatch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleInternalSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
        if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            refreshAndResetNameResolution();
        }
    }

    @Override // io.grpc.ManagedChannel
    public ConnectivityState getState(boolean z) {
        ConnectivityState state = this.channelStateManager.getState();
        if (z && state == ConnectivityState.IDLE) {
            this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1RequestConnection
                @Override // java.lang.Runnable
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                    if (ManagedChannelImpl.this.subchannelPicker != null) {
                        ManagedChannelImpl.this.subchannelPicker.requestConnection();
                    }
                    if (ManagedChannelImpl.this.lbHelper != null) {
                        ManagedChannelImpl.this.lbHelper.f8318lb.requestConnection();
                    }
                }
            });
        }
        return state;
    }

    @Override // io.grpc.ManagedChannel
    public void notifyWhenStateChanged(final ConnectivityState connectivityState, final Runnable runnable) {
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1NotifyStateChanged
            @Override // java.lang.Runnable
            public void run() {
                ManagedChannelImpl.this.channelStateManager.notifyWhenStateChanged(runnable, ManagedChannelImpl.this.executor, connectivityState);
            }
        });
    }

    @Override // io.grpc.ManagedChannel
    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1ResetConnectBackoff
            @Override // java.lang.Runnable
            public void run() {
                if (ManagedChannelImpl.this.shutdown.get()) {
                    return;
                }
                if (ManagedChannelImpl.this.scheduledNameResolverRefresh != null && ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                    Preconditions.checkState(ManagedChannelImpl.this.nameResolverStarted, "name resolver must be started");
                    ManagedChannelImpl.this.refreshAndResetNameResolution();
                }
                Iterator it = ManagedChannelImpl.this.subchannels.iterator();
                while (it.hasNext()) {
                    ((InternalSubchannel) it.next()).resetConnectBackoff();
                }
                Iterator it2 = ManagedChannelImpl.this.oobChannels.iterator();
                while (it2.hasNext()) {
                    ((OobChannel) it2.next()).resetConnectBackoff();
                }
            }
        });
    }

    @Override // io.grpc.ManagedChannel
    public void enterIdle() {
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.1PrepareToLoseNetworkRunnable
            @Override // java.lang.Runnable
            public void run() {
                if (ManagedChannelImpl.this.shutdown.get() || ManagedChannelImpl.this.lbHelper == null) {
                    return;
                }
                ManagedChannelImpl.this.cancelIdleTimer(false);
                ManagedChannelImpl.this.enterIdleMode();
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class UncommittedRetriableStreamsRegistry {
        final Object lock;
        Status shutdownStatus;
        Collection<ClientStream> uncommittedRetriableStreams;

        private UncommittedRetriableStreamsRegistry() {
            this.lock = new Object();
            this.uncommittedRetriableStreams = new HashSet();
        }

        void onShutdown(Status status) {
            synchronized (this.lock) {
                if (this.shutdownStatus != null) {
                    return;
                }
                this.shutdownStatus = status;
                boolean isEmpty = this.uncommittedRetriableStreams.isEmpty();
                if (isEmpty) {
                    ManagedChannelImpl.this.delayedTransport.shutdown(status);
                }
            }
        }

        void onShutdownNow(Status status) {
            ArrayList arrayList;
            onShutdown(status);
            synchronized (this.lock) {
                arrayList = new ArrayList(this.uncommittedRetriableStreams);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((ClientStream) it.next()).cancel(status);
            }
            ManagedChannelImpl.this.delayedTransport.shutdownNow(status);
        }

        @Nullable
        Status add(RetriableStream<?> retriableStream) {
            synchronized (this.lock) {
                if (this.shutdownStatus != null) {
                    return this.shutdownStatus;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        void remove(RetriableStream<?> retriableStream) {
            Status status;
            synchronized (this.lock) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    status = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet();
                } else {
                    status = null;
                }
            }
            if (status != null) {
                ManagedChannelImpl.this.delayedTransport.shutdown(status);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class LbHelperImpl extends LoadBalancer.Helper {
        boolean ignoreRefreshNsCheck;

        /* renamed from: lb */
        AutoConfiguredLoadBalancerFactory.AutoConfiguredLoadBalancer f8318lb;
        boolean nsRefreshedByLb;

        private LbHelperImpl() {
        }

        @Override // io.grpc.LoadBalancer.Helper
        public AbstractSubchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!ManagedChannelImpl.this.terminating, "Channel is being terminated");
            return new SubchannelImpl(createSubchannelArgs, this);
        }

        @Override // io.grpc.LoadBalancer.Helper
        public void updateBalancingState(final ConnectivityState connectivityState, final LoadBalancer.SubchannelPicker subchannelPicker) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkNotNull(connectivityState, "newState");
            Preconditions.checkNotNull(subchannelPicker, "newPicker");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.LbHelperImpl.1UpdateBalancingState
                @Override // java.lang.Runnable
                public void run() {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl != ManagedChannelImpl.this.lbHelper) {
                        return;
                    }
                    ManagedChannelImpl.this.updateSubchannelPicker(subchannelPicker);
                    if (connectivityState != ConnectivityState.SHUTDOWN) {
                        ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering {0} state with picker: {1}", connectivityState, subchannelPicker);
                        ManagedChannelImpl.this.channelStateManager.gotoState(connectivityState);
                    }
                }
            });
        }

        @Override // io.grpc.LoadBalancer.Helper
        public void refreshNameResolution() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            this.nsRefreshedByLb = true;
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.LbHelperImpl.1LoadBalancerRefreshNameResolution
                @Override // java.lang.Runnable
                public void run() {
                    ManagedChannelImpl.this.refreshAndResetNameResolution();
                }
            });
        }

        @Override // io.grpc.LoadBalancer.Helper
        public void ignoreRefreshNameResolutionCheck() {
            this.ignoreRefreshNsCheck = true;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
            return createOobChannel(Collections.singletonList(equivalentAddressGroup), str);
        }

        @Override // io.grpc.LoadBalancer.Helper
        public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String str) {
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            long currentTimeNanos = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            InternalLogId allocate = InternalLogId.allocate("OobChannel", (String) null);
            InternalLogId allocate2 = InternalLogId.allocate("Subchannel-OOB", str);
            ChannelTracer channelTracer = new ChannelTracer(allocate, ManagedChannelImpl.this.maxTraceEvents, currentTimeNanos, "OobChannel for " + list);
            final OobChannel oobChannel = new OobChannel(str, ManagedChannelImpl.this.balancerRpcExecutorPool, ManagedChannelImpl.this.oobTransportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.syncContext, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.timeProvider);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child OobChannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setChannelRef(oobChannel).build());
            ChannelTracer channelTracer2 = new ChannelTracer(allocate2, ManagedChannelImpl.this.maxTraceEvents, currentTimeNanos, "Subchannel for " + list);
            InternalSubchannel internalSubchannel = new InternalSubchannel(list, str, ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.oobTransportFactory, ManagedChannelImpl.this.oobTransportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.syncContext, new InternalSubchannel.Callback() { // from class: io.grpc.internal.ManagedChannelImpl.LbHelperImpl.1ManagedOobChannelCallback
                @Override // io.grpc.internal.InternalSubchannel.Callback
                void onTerminated(InternalSubchannel internalSubchannel2) {
                    ManagedChannelImpl.this.oobChannels.remove(oobChannel);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel2);
                    oobChannel.handleSubchannelTerminated();
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                @Override // io.grpc.internal.InternalSubchannel.Callback
                void onStateChange(InternalSubchannel internalSubchannel2, ConnectivityStateInfo connectivityStateInfo) {
                    ManagedChannelImpl.this.handleInternalSubchannelState(connectivityStateInfo);
                    oobChannel.handleSubchannelStateChange(connectivityStateInfo);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer2, allocate2, new ChannelLoggerImpl(channelTracer2, ManagedChannelImpl.this.timeProvider));
            channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setSubchannelRef(internalSubchannel).build());
            ManagedChannelImpl.this.channelz.addSubchannel(oobChannel);
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            oobChannel.setSubchannel(internalSubchannel);
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.LbHelperImpl.1AddOobChannel
                @Override // java.lang.Runnable
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        oobChannel.shutdown();
                    }
                    if (ManagedChannelImpl.this.terminated) {
                        return;
                    }
                    ManagedChannelImpl.this.oobChannels.add(oobChannel);
                }
            });
            return oobChannel;
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [io.grpc.ManagedChannelBuilder, io.grpc.ManagedChannelBuilder<?>] */
        @Override // io.grpc.LoadBalancer.Helper
        @Deprecated
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str) {
            return createResolvingOobChannelBuilder(str, new DefaultChannelCreds()).overrideAuthority(getAuthority());
        }

        @Override // io.grpc.LoadBalancer.Helper
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(final String str, final ChannelCredentials channelCredentials) {
            Preconditions.checkNotNull(channelCredentials, "channelCreds");
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            return new ForwardingChannelBuilder<C1ResolvingOobChannelBuilder>() { // from class: io.grpc.internal.ManagedChannelImpl.LbHelperImpl.1ResolvingOobChannelBuilder
                final ManagedChannelBuilder<?> delegate;

                {
                    CallCredentials callCredentials;
                    final ClientTransportFactory clientTransportFactory;
                    if (channelCredentials instanceof DefaultChannelCreds) {
                        clientTransportFactory = ManagedChannelImpl.this.originalTransportFactory;
                        callCredentials = null;
                    } else {
                        ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials = ManagedChannelImpl.this.originalTransportFactory.swapChannelCredentials(channelCredentials);
                        if (swapChannelCredentials == null) {
                            this.delegate = Grpc.newChannelBuilder(str, channelCredentials);
                            return;
                        } else {
                            ClientTransportFactory clientTransportFactory2 = swapChannelCredentials.transportFactory;
                            callCredentials = swapChannelCredentials.callCredentials;
                            clientTransportFactory = clientTransportFactory2;
                        }
                    }
                    this.delegate = new ManagedChannelImplBuilder(str, channelCredentials, callCredentials, new ManagedChannelImplBuilder.ClientTransportFactoryBuilder() { // from class: io.grpc.internal.ManagedChannelImpl.LbHelperImpl.1ResolvingOobChannelBuilder.1
                        @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
                        public ClientTransportFactory buildClientTransportFactory() {
                            return clientTransportFactory;
                        }
                    }, new ManagedChannelImplBuilder.FixedPortProvider(ManagedChannelImpl.this.nameResolverArgs.getDefaultPort()));
                }

                @Override // io.grpc.ForwardingChannelBuilder
                protected ManagedChannelBuilder<?> delegate() {
                    return this.delegate;
                }
            }.nameResolverFactory(ManagedChannelImpl.this.nameResolverFactory).executor(ManagedChannelImpl.this.executor).offloadExecutor(ManagedChannelImpl.this.offloadExecutorHolder.getExecutor()).maxTraceEvents(ManagedChannelImpl.this.maxTraceEvents).proxyDetector(ManagedChannelImpl.this.nameResolverArgs.getProxyDetector()).userAgent(ManagedChannelImpl.this.userAgent);
        }

        @Override // io.grpc.LoadBalancer.Helper
        public ChannelCredentials getUnsafeChannelCredentials() {
            if (ManagedChannelImpl.this.originalChannelCreds != null) {
                return ManagedChannelImpl.this.originalChannelCreds;
            }
            return new DefaultChannelCreds();
        }

        @Override // io.grpc.LoadBalancer.Helper
        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            updateOobChannelAddresses(managedChannel, Collections.singletonList(equivalentAddressGroup));
        }

        @Override // io.grpc.LoadBalancer.Helper
        public void updateOobChannelAddresses(ManagedChannel managedChannel, List<EquivalentAddressGroup> list) {
            Preconditions.checkArgument(managedChannel instanceof OobChannel, "channel must have been returned from createOobChannel");
            ((OobChannel) managedChannel).updateAddresses(list);
        }

        @Override // io.grpc.LoadBalancer.Helper
        public String getAuthority() {
            return ManagedChannelImpl.this.authority();
        }

        @Override // io.grpc.LoadBalancer.Helper
        public SynchronizationContext getSynchronizationContext() {
            return ManagedChannelImpl.this.syncContext;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public ScheduledExecutorService getScheduledExecutorService() {
            return ManagedChannelImpl.this.scheduledExecutor;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public ChannelLogger getChannelLogger() {
            return ManagedChannelImpl.this.channelLogger;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public NameResolver.Args getNameResolverArgs() {
            return ManagedChannelImpl.this.nameResolverArgs;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public NameResolverRegistry getNameResolverRegistry() {
            return ManagedChannelImpl.this.nameResolverRegistry;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        final class DefaultChannelCreds extends ChannelCredentials {
            @Override // io.grpc.ChannelCredentials
            public ChannelCredentials withoutBearerTokens() {
                return this;
            }

            DefaultChannelCreds() {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class NameResolverListener extends NameResolver.Listener2 {
        final LbHelperImpl helper;
        final NameResolver resolver;

        NameResolverListener(LbHelperImpl lbHelperImpl, NameResolver nameResolver) {
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helperImpl");
            this.resolver = (NameResolver) Preconditions.checkNotNull(nameResolver, "resolver");
        }

        @Override // io.grpc.NameResolver.Listener2
        public void onResult(final NameResolver.ResolutionResult resolutionResult) {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.NameResolverListener.1NamesResolved
                @Override // java.lang.Runnable
                public void run() {
                    ManagedChannelServiceConfig managedChannelServiceConfig;
                    List<EquivalentAddressGroup> addresses = resolutionResult.getAddresses();
                    ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Resolved address: {0}, config={1}", addresses, resolutionResult.getAttributes());
                    if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.SUCCESS) {
                        ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Address resolved: {0}", addresses);
                        ManagedChannelImpl.this.lastResolutionState = ResolutionState.SUCCESS;
                    }
                    ManagedChannelImpl.this.nameResolverBackoffPolicy = null;
                    NameResolver.ConfigOrError serviceConfig = resolutionResult.getServiceConfig();
                    InternalConfigSelector internalConfigSelector = (InternalConfigSelector) resolutionResult.getAttributes().get(InternalConfigSelector.KEY);
                    ManagedChannelServiceConfig managedChannelServiceConfig2 = (serviceConfig == null || serviceConfig.getConfig() == null) ? null : (ManagedChannelServiceConfig) serviceConfig.getConfig();
                    Status error = serviceConfig != null ? serviceConfig.getError() : null;
                    if (!ManagedChannelImpl.this.lookUpServiceConfig) {
                        if (managedChannelServiceConfig2 != null) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Service config from name resolver discarded by channel settings");
                        }
                        managedChannelServiceConfig = ManagedChannelImpl.this.defaultServiceConfig == null ? ManagedChannelImpl.EMPTY_SERVICE_CONFIG : ManagedChannelImpl.this.defaultServiceConfig;
                        if (internalConfigSelector != null) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Config selector from name resolver discarded by channel settings");
                        }
                        ManagedChannelImpl.this.realChannel.updateConfigSelector(managedChannelServiceConfig.getDefaultConfigSelector());
                    } else {
                        if (managedChannelServiceConfig2 != null) {
                            if (internalConfigSelector != null) {
                                ManagedChannelImpl.this.realChannel.updateConfigSelector(internalConfigSelector);
                                if (managedChannelServiceConfig2.getDefaultConfigSelector() != null) {
                                    ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Method configs in service config will be discarded due to presence ofconfig-selector");
                                }
                            } else {
                                ManagedChannelImpl.this.realChannel.updateConfigSelector(managedChannelServiceConfig2.getDefaultConfigSelector());
                            }
                        } else if (ManagedChannelImpl.this.defaultServiceConfig != null) {
                            managedChannelServiceConfig2 = ManagedChannelImpl.this.defaultServiceConfig;
                            ManagedChannelImpl.this.realChannel.updateConfigSelector(managedChannelServiceConfig2.getDefaultConfigSelector());
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Received no service config, using default service config");
                        } else if (error != null) {
                            if (!ManagedChannelImpl.this.serviceConfigUpdated) {
                                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Fallback to error due to invalid first service config without default config");
                                NameResolverListener.this.onError(serviceConfig.getError());
                                return;
                            }
                            managedChannelServiceConfig2 = ManagedChannelImpl.this.lastServiceConfig;
                        } else {
                            managedChannelServiceConfig2 = ManagedChannelImpl.EMPTY_SERVICE_CONFIG;
                            ManagedChannelImpl.this.realChannel.updateConfigSelector(null);
                        }
                        managedChannelServiceConfig = managedChannelServiceConfig2;
                        if (!managedChannelServiceConfig.equals(ManagedChannelImpl.this.lastServiceConfig)) {
                            ChannelLogger channelLogger = ManagedChannelImpl.this.channelLogger;
                            ChannelLogger.ChannelLogLevel channelLogLevel = ChannelLogger.ChannelLogLevel.INFO;
                            Object[] objArr = new Object[1];
                            objArr[0] = managedChannelServiceConfig == ManagedChannelImpl.EMPTY_SERVICE_CONFIG ? " to empty" : "";
                            channelLogger.log(channelLogLevel, "Service config changed{0}", objArr);
                            ManagedChannelImpl.this.lastServiceConfig = managedChannelServiceConfig;
                        }
                        try {
                            ManagedChannelImpl.this.serviceConfigUpdated = true;
                        } catch (RuntimeException e) {
                            ManagedChannelImpl.logger.log(Level.WARNING, "[" + ManagedChannelImpl.this.getLogId() + "] Unexpected exception from parsing service config", (Throwable) e);
                        }
                    }
                    Attributes attributes = resolutionResult.getAttributes();
                    if (NameResolverListener.this.helper == ManagedChannelImpl.this.lbHelper) {
                        Attributes.Builder discard = attributes.toBuilder().discard(InternalConfigSelector.KEY);
                        Map<String, ?> healthCheckingConfig = managedChannelServiceConfig.getHealthCheckingConfig();
                        if (healthCheckingConfig != null) {
                            discard.set(LoadBalancer.ATTR_HEALTH_CHECKING_CONFIG, healthCheckingConfig).build();
                        }
                        Status tryHandleResolvedAddresses = NameResolverListener.this.helper.f8318lb.tryHandleResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(addresses).setAttributes(discard.build()).setLoadBalancingPolicyConfig(managedChannelServiceConfig.getLoadBalancingConfig()).build());
                        if (tryHandleResolvedAddresses.isOk()) {
                            return;
                        }
                        NameResolverListener.this.handleErrorInSyncContext(tryHandleResolvedAddresses.augmentDescription(NameResolverListener.this.resolver + " was used"));
                    }
                }
            });
        }

        @Override // io.grpc.NameResolver.Listener2, io.grpc.NameResolver.Listener
        public void onError(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "the error status must not be OK");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.NameResolverListener.1NameResolverErrorHandler
                @Override // java.lang.Runnable
                public void run() {
                    NameResolverListener.this.handleErrorInSyncContext(status);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleErrorInSyncContext(Status status) {
            ManagedChannelImpl.logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{ManagedChannelImpl.this.getLogId(), status});
            ManagedChannelImpl.this.realChannel.onConfigError();
            if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.ERROR) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.WARNING, "Failed to resolve name: {0}", status);
                ManagedChannelImpl.this.lastResolutionState = ResolutionState.ERROR;
            }
            if (this.helper != ManagedChannelImpl.this.lbHelper) {
                return;
            }
            this.helper.f8318lb.handleNameResolutionError(status);
            scheduleExponentialBackOffInSyncContext();
        }

        private void scheduleExponentialBackOffInSyncContext() {
            if (ManagedChannelImpl.this.scheduledNameResolverRefresh == null || !ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                if (ManagedChannelImpl.this.nameResolverBackoffPolicy == null) {
                    ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                    managedChannelImpl.nameResolverBackoffPolicy = managedChannelImpl.backoffPolicyProvider.get();
                }
                long nextBackoffNanos = ManagedChannelImpl.this.nameResolverBackoffPolicy.nextBackoffNanos();
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Scheduling DNS resolution backoff for {0} ns", Long.valueOf(nextBackoffNanos));
                ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
                managedChannelImpl2.scheduledNameResolverRefresh = managedChannelImpl2.syncContext.schedule(new DelayedNameResolverRefresh(), nextBackoffNanos, TimeUnit.NANOSECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class SubchannelImpl extends AbstractSubchannel {
        List<EquivalentAddressGroup> addressGroups;
        final LoadBalancer.CreateSubchannelArgs args;
        SynchronizationContext.ScheduledHandle delayedShutdownTask;
        final LbHelperImpl helper;
        boolean shutdown;
        boolean started;
        InternalSubchannel subchannel;
        final InternalLogId subchannelLogId;
        final ChannelLoggerImpl subchannelLogger;
        final ChannelTracer subchannelTracer;

        SubchannelImpl(LoadBalancer.CreateSubchannelArgs createSubchannelArgs, LbHelperImpl lbHelperImpl) {
            this.addressGroups = createSubchannelArgs.getAddresses();
            if (ManagedChannelImpl.this.authorityOverride != null) {
                createSubchannelArgs = createSubchannelArgs.toBuilder().setAddresses(stripOverrideAuthorityAttributes(createSubchannelArgs.getAddresses())).build();
            }
            this.args = (LoadBalancer.CreateSubchannelArgs) Preconditions.checkNotNull(createSubchannelArgs, "args");
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helper");
            this.subchannelLogId = InternalLogId.allocate("Subchannel", ManagedChannelImpl.this.authority());
            this.subchannelTracer = new ChannelTracer(this.subchannelLogId, ManagedChannelImpl.this.maxTraceEvents, ManagedChannelImpl.this.timeProvider.currentTimeNanos(), "Subchannel for " + createSubchannelArgs.getAddresses());
            this.subchannelLogger = new ChannelLoggerImpl(this.subchannelTracer, ManagedChannelImpl.this.timeProvider);
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public void start(final LoadBalancer.SubchannelStateListener subchannelStateListener) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!this.started, "already started");
            Preconditions.checkState(!this.shutdown, "already shutdown");
            Preconditions.checkState(!ManagedChannelImpl.this.terminating, "Channel is being terminated");
            this.started = true;
            InternalSubchannel internalSubchannel = new InternalSubchannel(this.args.getAddresses(), ManagedChannelImpl.this.authority(), ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.transportFactory, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.syncContext, new InternalSubchannel.Callback() { // from class: io.grpc.internal.ManagedChannelImpl.SubchannelImpl.1ManagedInternalSubchannelCallback
                @Override // io.grpc.internal.InternalSubchannel.Callback
                void onTerminated(InternalSubchannel internalSubchannel2) {
                    ManagedChannelImpl.this.subchannels.remove(internalSubchannel2);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel2);
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                @Override // io.grpc.internal.InternalSubchannel.Callback
                void onStateChange(InternalSubchannel internalSubchannel2, ConnectivityStateInfo connectivityStateInfo) {
                    Preconditions.checkState(subchannelStateListener != null, "listener is null");
                    subchannelStateListener.onSubchannelState(connectivityStateInfo);
                    if ((connectivityStateInfo.getState() != ConnectivityState.TRANSIENT_FAILURE && connectivityStateInfo.getState() != ConnectivityState.IDLE) || SubchannelImpl.this.helper.ignoreRefreshNsCheck || SubchannelImpl.this.helper.nsRefreshedByLb) {
                        return;
                    }
                    ManagedChannelImpl.logger.log(Level.WARNING, "LoadBalancer should call Helper.refreshNameResolution() to refresh name resolution if subchannel state becomes TRANSIENT_FAILURE or IDLE. This will no longer happen automatically in the future releases");
                    ManagedChannelImpl.this.refreshAndResetNameResolution();
                    SubchannelImpl.this.helper.nsRefreshedByLb = true;
                }

                @Override // io.grpc.internal.InternalSubchannel.Callback
                void onInUse(InternalSubchannel internalSubchannel2) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel2, true);
                }

                @Override // io.grpc.internal.InternalSubchannel.Callback
                void onNotInUse(InternalSubchannel internalSubchannel2) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel2, false);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), this.subchannelTracer, this.subchannelLogId, this.subchannelLogger);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel started").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(ManagedChannelImpl.this.timeProvider.currentTimeNanos()).setSubchannelRef(internalSubchannel).build());
            this.subchannel = internalSubchannel;
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            ManagedChannelImpl.this.subchannels.add(internalSubchannel);
        }

        @Override // io.grpc.internal.AbstractSubchannel
        InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel() {
            Preconditions.checkState(this.started, "not started");
            return this.subchannel;
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public void shutdown() {
            SynchronizationContext.ScheduledHandle scheduledHandle;
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            if (this.subchannel == null) {
                this.shutdown = true;
                return;
            }
            if (this.shutdown) {
                if (!ManagedChannelImpl.this.terminating || (scheduledHandle = this.delayedShutdownTask) == null) {
                    return;
                }
                scheduledHandle.cancel();
                this.delayedShutdownTask = null;
            } else {
                this.shutdown = true;
            }
            if (!ManagedChannelImpl.this.terminating) {
                this.delayedShutdownTask = ManagedChannelImpl.this.syncContext.schedule(new LogExceptionRunnable(new Runnable() { // from class: io.grpc.internal.ManagedChannelImpl.SubchannelImpl.1ShutdownSubchannel
                    @Override // java.lang.Runnable
                    public void run() {
                        SubchannelImpl.this.subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
                    }
                }), 5L, TimeUnit.SECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
            } else {
                this.subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
            }
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public void requestConnection() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            this.subchannel.obtainActiveTransport();
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public List<EquivalentAddressGroup> getAllAddresses() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            return this.addressGroups;
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public Attributes getAttributes() {
            return this.args.getAttributes();
        }

        public String toString() {
            return this.subchannelLogId.toString();
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public Channel asChannel() {
            Preconditions.checkState(this.started, "not started");
            return new SubchannelChannel(this.subchannel, ManagedChannelImpl.this.balancerRpcExecutorHolder.getExecutor(), ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.callTracerFactory.create(), new AtomicReference(null));
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public Object getInternalSubchannel() {
            Preconditions.checkState(this.started, "Subchannel is not started");
            return this.subchannel;
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public ChannelLogger getChannelLogger() {
            return this.subchannelLogger;
        }

        @Override // io.grpc.LoadBalancer.Subchannel
        public void updateAddresses(List<EquivalentAddressGroup> list) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            this.addressGroups = list;
            if (ManagedChannelImpl.this.authorityOverride != null) {
                list = stripOverrideAuthorityAttributes(list);
            }
            this.subchannel.updateAddresses(list);
        }

        private List<EquivalentAddressGroup> stripOverrideAuthorityAttributes(List<EquivalentAddressGroup> list) {
            ArrayList arrayList = new ArrayList();
            for (EquivalentAddressGroup equivalentAddressGroup : list) {
                arrayList.add(new EquivalentAddressGroup(equivalentAddressGroup.getAddresses(), equivalentAddressGroup.getAttributes().toBuilder().discard(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE).build()));
            }
            return Collections.unmodifiableList(arrayList);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add(TypedValues.Attributes.S_TARGET, this.target).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class DelayedTransportListener implements ManagedClientTransport.Listener {
        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportReady() {
        }

        private DelayedTransportListener() {
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportShutdown(Status status) {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportInUse(boolean z) {
            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.delayedTransport, z);
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportTerminated() {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            ManagedChannelImpl.this.terminating = true;
            ManagedChannelImpl.this.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            ManagedChannelImpl.this.maybeTerminateChannel();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class IdleModeStateAggregator extends InUseStateAggregator<Object> {
        private IdleModeStateAggregator() {
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected void handleNotInUse() {
            if (ManagedChannelImpl.this.shutdown.get()) {
                return;
            }
            ManagedChannelImpl.this.rescheduleIdleTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ExecutorHolder {
        private Executor executor;
        private final ObjectPool<? extends Executor> pool;

        ExecutorHolder(ObjectPool<? extends Executor> objectPool) {
            this.pool = (ObjectPool) Preconditions.checkNotNull(objectPool, "executorPool");
        }

        synchronized Executor getExecutor() {
            if (this.executor == null) {
                this.executor = (Executor) Preconditions.checkNotNull(this.pool.getObject(), "%s.getObject()", this.executor);
            }
            return this.executor;
        }

        synchronized void release() {
            if (this.executor != null) {
                this.executor = this.pool.returnObject(this.executor);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class RestrictedScheduledExecutor implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        private RestrictedScheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "delegate");
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(callable, j, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(runnable, j, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return this.delegate.invokeAll(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.invokeAll(collection, j, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return (T) this.delegate.invokeAny(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (T) this.delegate.invokeAny(collection, j, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override // java.util.concurrent.ExecutorService
        public void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        @Override // java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Callable<T> callable) {
            return this.delegate.submit(callable);
        }

        @Override // java.util.concurrent.ExecutorService
        public Future<?> submit(Runnable runnable) {
            return this.delegate.submit(runnable);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.delegate.submit(runnable, t);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class ScParser extends NameResolver.ServiceConfigParser {
        private final AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory;
        private final int maxHedgedAttemptsLimit;
        private final int maxRetryAttemptsLimit;
        private final boolean retryEnabled;

        ScParser(boolean z, int i, int i2, AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory) {
            this.retryEnabled = z;
            this.maxRetryAttemptsLimit = i;
            this.maxHedgedAttemptsLimit = i2;
            this.autoLoadBalancerFactory = (AutoConfiguredLoadBalancerFactory) Preconditions.checkNotNull(autoConfiguredLoadBalancerFactory, "autoLoadBalancerFactory");
        }

        @Override // io.grpc.NameResolver.ServiceConfigParser
        public NameResolver.ConfigOrError parseServiceConfig(Map<String, ?> map) {
            Object config;
            try {
                NameResolver.ConfigOrError parseLoadBalancerPolicy = this.autoLoadBalancerFactory.parseLoadBalancerPolicy(map);
                if (parseLoadBalancerPolicy == null) {
                    config = null;
                } else {
                    if (parseLoadBalancerPolicy.getError() != null) {
                        return NameResolver.ConfigOrError.fromError(parseLoadBalancerPolicy.getError());
                    }
                    config = parseLoadBalancerPolicy.getConfig();
                }
                return NameResolver.ConfigOrError.fromConfig(ManagedChannelServiceConfig.fromServiceConfig(map, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, config));
            } catch (RuntimeException e) {
                return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse service config").withCause(e));
            }
        }
    }
}
