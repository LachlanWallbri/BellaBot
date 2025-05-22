package io.grpc.grpclb;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.protobuf.util.Durations;
import io.grpc.Attributes;
import io.grpc.ChannelLogger;
import io.grpc.ClientStreamTracer;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.grpclb.SubchannelPool;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.TimeProvider;
import io.grpc.lb.p072v1.InitialLoadBalanceRequest;
import io.grpc.lb.p072v1.LoadBalanceRequest;
import io.grpc.lb.p072v1.LoadBalanceResponse;
import io.grpc.lb.p072v1.LoadBalancerGrpc;
import io.grpc.lb.p072v1.Server;
import io.grpc.lb.p072v1.ServerList;
import io.grpc.stub.StreamObserver;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GrpclbState {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String NO_USE_AUTHORITY_SUFFIX = "-notIntendedToBeUsed";
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private boolean balancerWorking;
    private final GrpclbConfig config;
    private final Context context;

    @Nullable
    private Status fallbackReason;

    @Nullable
    private SynchronizationContext.ScheduledHandle fallbackTimer;
    private final LoadBalancer.Helper helper;

    @Nullable
    private ManagedChannel lbCommChannel;

    @Nullable
    private BackoffPolicy lbRpcRetryPolicy;

    @Nullable
    private SynchronizationContext.ScheduledHandle lbRpcRetryTimer;

    @Nullable
    private LbStream lbStream;
    private final ChannelLogger logger;
    private boolean requestConnectionPending;
    private final String serviceName;
    private final Stopwatch stopwatch;

    @Nullable
    private final SubchannelPool subchannelPool;
    private final SynchronizationContext syncContext;
    private final TimeProvider time;
    private final ScheduledExecutorService timerService;
    private boolean usingFallbackBackends;
    static final long FALLBACK_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    private static final Attributes LB_PROVIDED_BACKEND_ATTRS = Attributes.newBuilder().set(GrpclbConstants.ATTR_LB_PROVIDED_BACKEND, true).build();
    static final LoadBalancer.PickResult DROP_PICK_RESULT = LoadBalancer.PickResult.withDrop(Status.UNAVAILABLE.withDescription("Dropped as requested by balancer"));
    static final Status NO_AVAILABLE_BACKENDS_STATUS = Status.UNAVAILABLE.withDescription("LoadBalancer responded without any backends");
    static final Status BALANCER_TIMEOUT_STATUS = Status.UNAVAILABLE.withDescription("Timeout waiting for remote balancer");
    static final Status BALANCER_REQUESTED_FALLBACK_STATUS = Status.UNAVAILABLE.withDescription("Fallback requested by balancer");
    static final Status NO_FALLBACK_BACKENDS_STATUS = Status.UNAVAILABLE.withDescription("Unable to fallback, no fallback addresses found");
    private static final Status NO_LB_ADDRESS_PROVIDED_STATUS = Status.UNAVAILABLE.withDescription("No balancer address found");
    static final RoundRobinEntry BUFFER_ENTRY = new RoundRobinEntry() { // from class: io.grpc.grpclb.GrpclbState.1
        public String toString() {
            return "BUFFER_ENTRY";
        }

        @Override // io.grpc.grpclb.GrpclbState.RoundRobinEntry
        public LoadBalancer.PickResult picked(Metadata metadata) {
            return LoadBalancer.PickResult.withNoResult();
        }
    };
    private static final Attributes.Key<AtomicReference<ConnectivityStateInfo>> STATE_INFO = Attributes.Key.create("io.grpc.grpclb.GrpclbLoadBalancer.stateInfo");
    private List<EquivalentAddressGroup> fallbackBackendList = Collections.emptyList();
    private Map<List<EquivalentAddressGroup>, LoadBalancer.Subchannel> subchannels = Collections.emptyMap();
    private List<DropEntry> dropList = Collections.emptyList();
    private List<BackendEntry> backendList = Collections.emptyList();
    private RoundRobinPicker currentPicker = new RoundRobinPicker(Collections.emptyList(), Arrays.asList(BUFFER_ENTRY));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum Mode {
        ROUND_ROBIN,
        PICK_FIRST
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface RoundRobinEntry {
        LoadBalancer.PickResult picked(Metadata metadata);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpclbState(GrpclbConfig grpclbConfig, LoadBalancer.Helper helper, Context context, SubchannelPool subchannelPool, TimeProvider timeProvider, Stopwatch stopwatch, BackoffPolicy.Provider provider) {
        this.config = (GrpclbConfig) Preconditions.checkNotNull(grpclbConfig, "config");
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
        this.context = (Context) Preconditions.checkNotNull(context, "context");
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(helper.getSynchronizationContext(), "syncContext");
        if (grpclbConfig.getMode() == Mode.ROUND_ROBIN) {
            this.subchannelPool = (SubchannelPool) Preconditions.checkNotNull(subchannelPool, "subchannelPool");
            subchannelPool.registerListener(new SubchannelPool.PooledSubchannelStateListener() { // from class: io.grpc.grpclb.GrpclbState.2
                @Override // io.grpc.grpclb.SubchannelPool.PooledSubchannelStateListener
                public void onSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
                    GrpclbState.this.handleSubchannelState(subchannel, connectivityStateInfo);
                }
            });
        } else {
            this.subchannelPool = null;
        }
        this.time = (TimeProvider) Preconditions.checkNotNull(timeProvider, "time provider");
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, "stopwatch");
        this.timerService = (ScheduledExecutorService) Preconditions.checkNotNull(helper.getScheduledExecutorService(), "timerService");
        this.backoffPolicyProvider = (BackoffPolicy.Provider) Preconditions.checkNotNull(provider, "backoffPolicyProvider");
        if (grpclbConfig.getServiceName() != null) {
            this.serviceName = grpclbConfig.getServiceName();
        } else {
            this.serviceName = (String) Preconditions.checkNotNull(helper.getAuthority(), "helper returns null authority");
        }
        this.logger = (ChannelLogger) Preconditions.checkNotNull(helper.getChannelLogger(), "logger");
        this.logger.log(ChannelLogger.ChannelLogLevel.INFO, "[grpclb-<{0}>] Created", this.serviceName);
    }

    void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        if (connectivityStateInfo.getState() == ConnectivityState.SHUTDOWN || !this.subchannels.containsValue(subchannel)) {
            return;
        }
        if (this.config.getMode() == Mode.ROUND_ROBIN && connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            subchannel.requestConnection();
        }
        if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            this.helper.refreshNameResolution();
        }
        AtomicReference atomicReference = (AtomicReference) subchannel.getAttributes().get(STATE_INFO);
        if (this.config.getMode() == Mode.ROUND_ROBIN && ((ConnectivityStateInfo) atomicReference.get()).getState() == ConnectivityState.TRANSIENT_FAILURE && (connectivityStateInfo.getState() == ConnectivityState.CONNECTING || connectivityStateInfo.getState() == ConnectivityState.IDLE)) {
            return;
        }
        atomicReference.set(connectivityStateInfo);
        maybeUseFallbackBackends();
        maybeUpdatePicker();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleAddresses(List<EquivalentAddressGroup> list, List<EquivalentAddressGroup> list2) {
        this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "[grpclb-<{0}>] Resolved addresses: lb addresses {0}, backends: {1}", this.serviceName, list, list2);
        if (list.isEmpty()) {
            shutdownLbComm();
            this.syncContext.execute(new FallbackModeTask(NO_LB_ADDRESS_PROVIDED_STATUS));
        } else {
            startLbComm(list);
            if (this.lbStream == null) {
                cancelLbRpcRetryTimer();
                startLbRpc();
            }
            if (this.fallbackTimer == null) {
                this.fallbackTimer = this.syncContext.schedule(new FallbackModeTask(BALANCER_TIMEOUT_STATUS), FALLBACK_TIMEOUT_MS, TimeUnit.MILLISECONDS, this.timerService);
            }
        }
        this.fallbackBackendList = list2;
        if (this.usingFallbackBackends) {
            useFallbackBackends();
        }
        maybeUpdatePicker();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestConnection() {
        this.requestConnectionPending = true;
        for (RoundRobinEntry roundRobinEntry : this.currentPicker.pickList) {
            if (roundRobinEntry instanceof IdleSubchannelEntry) {
                ((IdleSubchannelEntry) roundRobinEntry).subchannel.requestConnection();
                this.requestConnectionPending = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeUseFallbackBackends() {
        if (this.balancerWorking || this.usingFallbackBackends) {
            return;
        }
        Preconditions.checkState(this.fallbackReason != null, "no reason to fallback");
        Iterator<LoadBalancer.Subchannel> it = this.subchannels.values().iterator();
        while (it.hasNext()) {
            ConnectivityStateInfo connectivityStateInfo = (ConnectivityStateInfo) ((AtomicReference) it.next().getAttributes().get(STATE_INFO)).get();
            if (connectivityStateInfo.getState() == ConnectivityState.READY) {
                return;
            }
            if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE) {
                this.fallbackReason = connectivityStateInfo.getStatus();
            }
        }
        useFallbackBackends();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void useFallbackBackends() {
        this.usingFallbackBackends = true;
        this.logger.log(ChannelLogger.ChannelLogLevel.INFO, "[grpclb-<{0}>] Using fallback backends", this.serviceName);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (EquivalentAddressGroup equivalentAddressGroup : this.fallbackBackendList) {
            arrayList.add(null);
            arrayList2.add(new BackendAddressGroup(equivalentAddressGroup, null));
        }
        updateServerList(arrayList, arrayList2, null);
    }

    private void shutdownLbComm() {
        ManagedChannel managedChannel = this.lbCommChannel;
        if (managedChannel != null) {
            managedChannel.shutdown();
            this.lbCommChannel = null;
        }
        shutdownLbRpc();
    }

    private void shutdownLbRpc() {
        LbStream lbStream = this.lbStream;
        if (lbStream != null) {
            lbStream.close(Status.CANCELLED.withDescription("balancer shutdown").asException());
        }
    }

    private void startLbComm(List<EquivalentAddressGroup> list) {
        Preconditions.checkNotNull(list, "overrideAuthorityEags");
        String str = ((String) list.get(0).getAttributes().get(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE)) + NO_USE_AUTHORITY_SUFFIX;
        ManagedChannel managedChannel = this.lbCommChannel;
        if (managedChannel == null) {
            this.lbCommChannel = this.helper.createOobChannel(list, str);
            this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "[grpclb-<{0}>] Created grpclb channel: EAG={1}", this.serviceName, list);
        } else {
            this.helper.updateOobChannelAddresses(managedChannel, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLbRpc() {
        Preconditions.checkState(this.lbStream == null, "previous lbStream has not been cleared yet");
        this.lbStream = new LbStream(LoadBalancerGrpc.newStub(this.lbCommChannel));
        Context attach = this.context.attach();
        try {
            this.lbStream.start();
            this.context.detach(attach);
            this.stopwatch.reset().start();
            LoadBalanceRequest build = LoadBalanceRequest.newBuilder().setInitialRequest(InitialLoadBalanceRequest.newBuilder().setName(this.serviceName).build()).build();
            this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "[grpclb-<{0}>] Sent initial grpclb request {1}", this.serviceName, build);
            try {
                this.lbStream.lbRequestWriter.onNext(build);
            } catch (Exception e) {
                this.lbStream.close(e);
            }
        } catch (Throwable th) {
            this.context.detach(attach);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelFallbackTimer() {
        SynchronizationContext.ScheduledHandle scheduledHandle = this.fallbackTimer;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
        }
    }

    private void cancelLbRpcRetryTimer() {
        SynchronizationContext.ScheduledHandle scheduledHandle = this.lbRpcRetryTimer;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.lbRpcRetryTimer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void shutdown() {
        this.logger.log(ChannelLogger.ChannelLogLevel.INFO, "[grpclb-<{0}>] Shutdown", this.serviceName);
        shutdownLbComm();
        int i = C60374.$SwitchMap$io$grpc$grpclb$GrpclbState$Mode[this.config.getMode().ordinal()];
        if (i == 1) {
            Iterator<LoadBalancer.Subchannel> it = this.subchannels.values().iterator();
            while (it.hasNext()) {
                returnSubchannelToPool(it.next());
            }
            this.subchannelPool.clear();
        } else if (i == 2) {
            if (!this.subchannels.isEmpty()) {
                Preconditions.checkState(this.subchannels.size() == 1, "Excessive Subchannels: %s", this.subchannels);
                this.subchannels.values().iterator().next().shutdown();
            }
        } else {
            throw new AssertionError("Missing case for " + this.config.getMode());
        }
        this.subchannels = Collections.emptyMap();
        cancelFallbackTimer();
        cancelLbRpcRetryTimer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void propagateError(Status status) {
        this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "[grpclb-<{0}>] Error: {1}", this.serviceName, status);
        if (this.backendList.isEmpty()) {
            maybeUpdatePicker(ConnectivityState.TRANSIENT_FAILURE, new RoundRobinPicker(this.dropList, Arrays.asList(new ErrorEntry(Status.UNAVAILABLE.withCause(status.getCause()).withDescription(status.getDescription())))));
        }
    }

    private void returnSubchannelToPool(LoadBalancer.Subchannel subchannel) {
        this.subchannelPool.returnSubchannel(subchannel, (ConnectivityStateInfo) ((AtomicReference) subchannel.getAttributes().get(STATE_INFO)).get());
    }

    @Nullable
    GrpclbClientLoadRecorder getLoadRecorder() {
        LbStream lbStream = this.lbStream;
        if (lbStream == null) {
            return null;
        }
        return lbStream.loadRecorder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateServerList(List<DropEntry> list, List<BackendAddressGroup> list2, @Nullable GrpclbClientLoadRecorder grpclbClientLoadRecorder) {
        BackendEntry backendEntry;
        final LoadBalancer.Subchannel next;
        this.logger.log(ChannelLogger.ChannelLogLevel.INFO, "[grpclb-<{0}>] Using RR list={1}, drop={2}", this.serviceName, list2, list);
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        int i = C60374.$SwitchMap$io$grpc$grpclb$GrpclbState$Mode[this.config.getMode().ordinal()];
        if (i == 1) {
            for (BackendAddressGroup backendAddressGroup : list2) {
                EquivalentAddressGroup addresses = backendAddressGroup.getAddresses();
                List singletonList = Collections.singletonList(addresses);
                LoadBalancer.Subchannel subchannel = (LoadBalancer.Subchannel) hashMap.get(singletonList);
                if (subchannel == null) {
                    subchannel = this.subchannels.get(singletonList);
                    if (subchannel == null) {
                        LoadBalancer.Subchannel takeOrCreateSubchannel = this.subchannelPool.takeOrCreateSubchannel(addresses, createSubchannelAttrs());
                        takeOrCreateSubchannel.requestConnection();
                        subchannel = takeOrCreateSubchannel;
                    }
                    hashMap.put(singletonList, subchannel);
                }
                if (backendAddressGroup.getToken() == null) {
                    backendEntry = new BackendEntry(subchannel);
                } else {
                    backendEntry = new BackendEntry(subchannel, grpclbClientLoadRecorder, backendAddressGroup.getToken());
                }
                arrayList.add(backendEntry);
            }
            for (Map.Entry<List<EquivalentAddressGroup>, LoadBalancer.Subchannel> entry : this.subchannels.entrySet()) {
                if (!hashMap.containsKey(entry.getKey())) {
                    returnSubchannelToPool(entry.getValue());
                }
            }
            this.subchannels = Collections.unmodifiableMap(hashMap);
        } else if (i == 2) {
            Preconditions.checkState(this.subchannels.size() <= 1, "Unexpected Subchannel count: %s", this.subchannels);
            if (list2.isEmpty()) {
                if (this.subchannels.size() == 1) {
                    this.subchannels.values().iterator().next().shutdown();
                    this.subchannels = Collections.emptyMap();
                }
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (BackendAddressGroup backendAddressGroup2 : list2) {
                    EquivalentAddressGroup addresses2 = backendAddressGroup2.getAddresses();
                    Attributes attributes = addresses2.getAttributes();
                    if (backendAddressGroup2.getToken() != null) {
                        attributes = attributes.toBuilder().set(GrpclbConstants.TOKEN_ATTRIBUTE_KEY, backendAddressGroup2.getToken()).build();
                    }
                    arrayList2.add(new EquivalentAddressGroup(addresses2.getAddresses(), attributes));
                }
                if (this.subchannels.isEmpty()) {
                    next = this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(arrayList2).setAttributes(createSubchannelAttrs()).build());
                    next.start(new LoadBalancer.SubchannelStateListener() { // from class: io.grpc.grpclb.GrpclbState.3
                        @Override // io.grpc.LoadBalancer.SubchannelStateListener
                        public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                            GrpclbState.this.handleSubchannelState(next, connectivityStateInfo);
                        }
                    });
                    if (this.requestConnectionPending) {
                        next.requestConnection();
                        this.requestConnectionPending = false;
                    }
                } else {
                    next = this.subchannels.values().iterator().next();
                    next.updateAddresses(arrayList2);
                }
                this.subchannels = Collections.singletonMap(arrayList2, next);
                arrayList.add(new BackendEntry(next, new TokenAttachingTracerFactory(grpclbClientLoadRecorder)));
            }
        } else {
            throw new AssertionError("Missing case for " + this.config.getMode());
        }
        this.dropList = Collections.unmodifiableList(list);
        this.backendList = Collections.unmodifiableList(arrayList);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    class FallbackModeTask implements Runnable {
        private final Status reason;

        private FallbackModeTask(Status status) {
            this.reason = status;
        }

        @Override // java.lang.Runnable
        public void run() {
            Preconditions.checkState(!GrpclbState.this.usingFallbackBackends, "already in fallback");
            GrpclbState.this.fallbackReason = this.reason;
            GrpclbState.this.maybeUseFallbackBackends();
            GrpclbState.this.maybeUpdatePicker();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class LbRpcRetryTask implements Runnable {
        LbRpcRetryTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GrpclbState.this.startLbRpc();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class LoadReportingTask implements Runnable {
        private final LbStream stream;

        LoadReportingTask(LbStream lbStream) {
            this.stream = lbStream;
        }

        @Override // java.lang.Runnable
        public void run() {
            LbStream lbStream = this.stream;
            lbStream.loadReportTimer = null;
            lbStream.sendLoadReport();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class LbStream implements StreamObserver<LoadBalanceResponse> {
        boolean closed;
        boolean initialResponseReceived;
        StreamObserver<LoadBalanceRequest> lbRequestWriter;
        final GrpclbClientLoadRecorder loadRecorder;
        long loadReportIntervalMillis = -1;
        SynchronizationContext.ScheduledHandle loadReportTimer;
        final LoadBalancerGrpc.LoadBalancerStub stub;

        LbStream(LoadBalancerGrpc.LoadBalancerStub loadBalancerStub) {
            this.stub = (LoadBalancerGrpc.LoadBalancerStub) Preconditions.checkNotNull(loadBalancerStub, "stub");
            this.loadRecorder = new GrpclbClientLoadRecorder(GrpclbState.this.time);
        }

        /* JADX WARN: Multi-variable type inference failed */
        void start() {
            this.lbRequestWriter = ((LoadBalancerGrpc.LoadBalancerStub) this.stub.withWaitForReady()).balanceLoad(this);
        }

        @Override // io.grpc.stub.StreamObserver
        public void onNext(final LoadBalanceResponse loadBalanceResponse) {
            GrpclbState.this.syncContext.execute(new Runnable() { // from class: io.grpc.grpclb.GrpclbState.LbStream.1
                @Override // java.lang.Runnable
                public void run() {
                    LbStream.this.handleResponse(loadBalanceResponse);
                }
            });
        }

        @Override // io.grpc.stub.StreamObserver
        public void onError(final Throwable th) {
            GrpclbState.this.syncContext.execute(new Runnable() { // from class: io.grpc.grpclb.GrpclbState.LbStream.2
                @Override // java.lang.Runnable
                public void run() {
                    LbStream.this.handleStreamClosed(Status.fromThrowable(th).augmentDescription("Stream to GRPCLB LoadBalancer had an error"));
                }
            });
        }

        @Override // io.grpc.stub.StreamObserver
        public void onCompleted() {
            GrpclbState.this.syncContext.execute(new Runnable() { // from class: io.grpc.grpclb.GrpclbState.LbStream.3
                @Override // java.lang.Runnable
                public void run() {
                    LbStream.this.handleStreamClosed(Status.UNAVAILABLE.withDescription("Stream to GRPCLB LoadBalancer was closed"));
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendLoadReport() {
            if (this.closed) {
                return;
            }
            try {
                this.lbRequestWriter.onNext(LoadBalanceRequest.newBuilder().setClientStats(this.loadRecorder.generateLoadReport()).build());
                scheduleNextLoadReport();
            } catch (Exception e) {
                close(e);
            }
        }

        private void scheduleNextLoadReport() {
            if (this.loadReportIntervalMillis > 0) {
                this.loadReportTimer = GrpclbState.this.syncContext.schedule(new LoadReportingTask(this), this.loadReportIntervalMillis, TimeUnit.MILLISECONDS, GrpclbState.this.timerService);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleResponse(LoadBalanceResponse loadBalanceResponse) {
            if (this.closed) {
                return;
            }
            GrpclbState.this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "[grpclb-<{0}>] Got an LB response: {1}", GrpclbState.this.serviceName, loadBalanceResponse);
            LoadBalanceResponse.LoadBalanceResponseTypeCase loadBalanceResponseTypeCase = loadBalanceResponse.getLoadBalanceResponseTypeCase();
            if (!this.initialResponseReceived) {
                if (loadBalanceResponseTypeCase != LoadBalanceResponse.LoadBalanceResponseTypeCase.INITIAL_RESPONSE) {
                    GrpclbState.this.logger.log(ChannelLogger.ChannelLogLevel.WARNING, "[grpclb-<{0}>] Received a response without initial response", GrpclbState.this.serviceName);
                    return;
                }
                this.initialResponseReceived = true;
                this.loadReportIntervalMillis = Durations.toMillis(loadBalanceResponse.getInitialResponse().getClientStatsReportInterval());
                scheduleNextLoadReport();
                return;
            }
            if (loadBalanceResponseTypeCase == LoadBalanceResponse.LoadBalanceResponseTypeCase.FALLBACK_RESPONSE) {
                GrpclbState.this.cancelFallbackTimer();
                GrpclbState.this.fallbackReason = GrpclbState.BALANCER_REQUESTED_FALLBACK_STATUS;
                GrpclbState.this.useFallbackBackends();
                GrpclbState.this.maybeUpdatePicker();
                return;
            }
            if (loadBalanceResponseTypeCase == LoadBalanceResponse.LoadBalanceResponseTypeCase.SERVER_LIST) {
                GrpclbState.this.balancerWorking = true;
                ServerList serverList = loadBalanceResponse.getServerList();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (Server server : serverList.getServersList()) {
                    String loadBalanceToken = server.getLoadBalanceToken();
                    if (server.getDrop()) {
                        arrayList.add(new DropEntry(this.loadRecorder, loadBalanceToken));
                    } else {
                        arrayList.add(null);
                        try {
                            arrayList2.add(new BackendAddressGroup(new EquivalentAddressGroup(new InetSocketAddress(InetAddress.getByAddress(server.getIpAddress().toByteArray()), server.getPort()), GrpclbState.LB_PROVIDED_BACKEND_ATTRS), loadBalanceToken));
                        } catch (UnknownHostException e) {
                            GrpclbState.this.propagateError(Status.UNAVAILABLE.withDescription("Invalid backend address: " + server).withCause(e));
                        }
                    }
                }
                GrpclbState.this.usingFallbackBackends = false;
                GrpclbState.this.fallbackReason = null;
                GrpclbState.this.cancelFallbackTimer();
                GrpclbState.this.updateServerList(arrayList, arrayList2, this.loadRecorder);
                GrpclbState.this.maybeUpdatePicker();
                return;
            }
            GrpclbState.this.logger.log(ChannelLogger.ChannelLogLevel.WARNING, "[grpclb-<{0}>] Ignoring unexpected response type: {1}", GrpclbState.this.serviceName, loadBalanceResponseTypeCase);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleStreamClosed(Status status) {
            Preconditions.checkArgument(!status.isOk(), "unexpected OK status");
            if (this.closed) {
                return;
            }
            this.closed = true;
            cleanUp();
            GrpclbState.this.propagateError(status);
            GrpclbState.this.balancerWorking = false;
            GrpclbState.this.fallbackReason = status;
            GrpclbState.this.cancelFallbackTimer();
            GrpclbState.this.maybeUseFallbackBackends();
            GrpclbState.this.maybeUpdatePicker();
            if (this.initialResponseReceived || GrpclbState.this.lbRpcRetryPolicy == null) {
                GrpclbState grpclbState = GrpclbState.this;
                grpclbState.lbRpcRetryPolicy = grpclbState.backoffPolicyProvider.get();
            }
            long nextBackoffNanos = !this.initialResponseReceived ? GrpclbState.this.lbRpcRetryPolicy.nextBackoffNanos() - GrpclbState.this.stopwatch.elapsed(TimeUnit.NANOSECONDS) : 0L;
            if (nextBackoffNanos <= 0) {
                GrpclbState.this.startLbRpc();
            } else {
                GrpclbState grpclbState2 = GrpclbState.this;
                grpclbState2.lbRpcRetryTimer = grpclbState2.syncContext.schedule(new LbRpcRetryTask(), nextBackoffNanos, TimeUnit.NANOSECONDS, GrpclbState.this.timerService);
            }
            GrpclbState.this.helper.refreshNameResolution();
        }

        void close(Exception exc) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            cleanUp();
            this.lbRequestWriter.onError(exc);
        }

        private void cleanUp() {
            SynchronizationContext.ScheduledHandle scheduledHandle = this.loadReportTimer;
            if (scheduledHandle != null) {
                scheduledHandle.cancel();
                this.loadReportTimer = null;
            }
            if (GrpclbState.this.lbStream == this) {
                GrpclbState.this.lbStream = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeUpdatePicker() {
        List arrayList;
        ConnectivityState connectivityState;
        List singletonList;
        ConnectivityState connectivityState2;
        if (this.backendList.isEmpty()) {
            if (this.usingFallbackBackends) {
                singletonList = Collections.singletonList(new ErrorEntry(NO_FALLBACK_BACKENDS_STATUS.withCause(this.fallbackReason.getCause()).augmentDescription(this.fallbackReason.getDescription())));
                connectivityState2 = ConnectivityState.TRANSIENT_FAILURE;
            } else if (this.balancerWorking) {
                singletonList = Collections.singletonList(new ErrorEntry(NO_AVAILABLE_BACKENDS_STATUS));
                connectivityState2 = ConnectivityState.TRANSIENT_FAILURE;
            } else {
                singletonList = Collections.singletonList(BUFFER_ENTRY);
                connectivityState2 = ConnectivityState.CONNECTING;
            }
            maybeUpdatePicker(connectivityState2, new RoundRobinPicker(this.dropList, singletonList));
            return;
        }
        int i = C60374.$SwitchMap$io$grpc$grpclb$GrpclbState$Mode[this.config.getMode().ordinal()];
        boolean z = false;
        if (i == 1) {
            arrayList = new ArrayList(this.backendList.size());
            Status status = null;
            for (BackendEntry backendEntry : this.backendList) {
                ConnectivityStateInfo connectivityStateInfo = (ConnectivityStateInfo) ((AtomicReference) backendEntry.subchannel.getAttributes().get(STATE_INFO)).get();
                if (connectivityStateInfo.getState() == ConnectivityState.READY) {
                    arrayList.add(backendEntry);
                } else if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE) {
                    status = connectivityStateInfo.getStatus();
                } else {
                    z = true;
                }
            }
            if (!arrayList.isEmpty()) {
                connectivityState = ConnectivityState.READY;
            } else if (z) {
                arrayList.add(BUFFER_ENTRY);
                connectivityState = ConnectivityState.CONNECTING;
            } else {
                arrayList.add(new ErrorEntry(status));
                connectivityState = ConnectivityState.TRANSIENT_FAILURE;
            }
        } else if (i == 2) {
            Preconditions.checkState(this.backendList.size() == 1, "Excessive backend entries: %s", this.backendList);
            BackendEntry backendEntry2 = this.backendList.get(0);
            ConnectivityStateInfo connectivityStateInfo2 = (ConnectivityStateInfo) ((AtomicReference) backendEntry2.subchannel.getAttributes().get(STATE_INFO)).get();
            connectivityState = connectivityStateInfo2.getState();
            int i2 = C60374.$SwitchMap$io$grpc$ConnectivityState[connectivityState.ordinal()];
            arrayList = i2 != 1 ? i2 != 2 ? i2 != 3 ? Collections.singletonList(new IdleSubchannelEntry(backendEntry2.subchannel, this.syncContext)) : Collections.singletonList(BUFFER_ENTRY) : Collections.singletonList(new ErrorEntry(connectivityStateInfo2.getStatus())) : Collections.singletonList(backendEntry2);
        } else {
            throw new AssertionError("Missing case for " + this.config.getMode());
        }
        maybeUpdatePicker(connectivityState, new RoundRobinPicker(this.dropList, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.grpclb.GrpclbState$4 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C60374 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ConnectivityState = new int[ConnectivityState.values().length];
        static final /* synthetic */ int[] $SwitchMap$io$grpc$grpclb$GrpclbState$Mode;

        static {
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.TRANSIENT_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$io$grpc$grpclb$GrpclbState$Mode = new int[Mode.values().length];
            try {
                $SwitchMap$io$grpc$grpclb$GrpclbState$Mode[Mode.ROUND_ROBIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$grpc$grpclb$GrpclbState$Mode[Mode.PICK_FIRST.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void maybeUpdatePicker(ConnectivityState connectivityState, RoundRobinPicker roundRobinPicker) {
        if (roundRobinPicker.dropList.equals(this.currentPicker.dropList) && roundRobinPicker.pickList.equals(this.currentPicker.pickList)) {
            return;
        }
        this.currentPicker = roundRobinPicker;
        this.logger.log(ChannelLogger.ChannelLogLevel.INFO, "[grpclb-<{0}>] Update balancing state to {1}: picks={2}, drops={3}", this.serviceName, connectivityState, roundRobinPicker.pickList, roundRobinPicker.dropList);
        this.helper.updateBalancingState(connectivityState, roundRobinPicker);
    }

    private static Attributes createSubchannelAttrs() {
        return Attributes.newBuilder().set(STATE_INFO, new AtomicReference(ConnectivityStateInfo.forNonError(ConnectivityState.IDLE))).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DropEntry {
        private final GrpclbClientLoadRecorder loadRecorder;
        private final String token;

        DropEntry(GrpclbClientLoadRecorder grpclbClientLoadRecorder, String str) {
            this.loadRecorder = (GrpclbClientLoadRecorder) Preconditions.checkNotNull(grpclbClientLoadRecorder, "loadRecorder");
            this.token = (String) Preconditions.checkNotNull(str, "token");
        }

        LoadBalancer.PickResult picked() {
            this.loadRecorder.recordDroppedRequest(this.token);
            return GrpclbState.DROP_PICK_RESULT;
        }

        public String toString() {
            return "drop(" + this.token + ")";
        }

        public int hashCode() {
            return Objects.hashCode(this.loadRecorder, this.token);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DropEntry)) {
                return false;
            }
            DropEntry dropEntry = (DropEntry) obj;
            return Objects.equal(this.loadRecorder, dropEntry.loadRecorder) && Objects.equal(this.token, dropEntry.token);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class BackendEntry implements RoundRobinEntry {
        final LoadBalancer.PickResult result;
        final LoadBalancer.Subchannel subchannel;

        @Nullable
        private final String token;

        BackendEntry(LoadBalancer.Subchannel subchannel, GrpclbClientLoadRecorder grpclbClientLoadRecorder, String str) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
            this.result = LoadBalancer.PickResult.withSubchannel(subchannel, (ClientStreamTracer.Factory) Preconditions.checkNotNull(grpclbClientLoadRecorder, "loadRecorder"));
            this.token = (String) Preconditions.checkNotNull(str, "token");
        }

        BackendEntry(LoadBalancer.Subchannel subchannel) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
            this.result = LoadBalancer.PickResult.withSubchannel(subchannel);
            this.token = null;
        }

        BackendEntry(LoadBalancer.Subchannel subchannel, TokenAttachingTracerFactory tokenAttachingTracerFactory) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
            this.result = LoadBalancer.PickResult.withSubchannel(subchannel, (ClientStreamTracer.Factory) Preconditions.checkNotNull(tokenAttachingTracerFactory, "tracerFactory"));
            this.token = null;
        }

        @Override // io.grpc.grpclb.GrpclbState.RoundRobinEntry
        public LoadBalancer.PickResult picked(Metadata metadata) {
            metadata.discardAll(GrpclbConstants.TOKEN_METADATA_KEY);
            if (this.token != null) {
                metadata.put(GrpclbConstants.TOKEN_METADATA_KEY, this.token);
            }
            return this.result;
        }

        public String toString() {
            return "[" + this.subchannel.getAllAddresses().toString() + "(" + this.token + ")]";
        }

        public int hashCode() {
            return Objects.hashCode(this.result, this.token);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof BackendEntry)) {
                return false;
            }
            BackendEntry backendEntry = (BackendEntry) obj;
            return Objects.equal(this.result, backendEntry.result) && Objects.equal(this.token, backendEntry.token);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class IdleSubchannelEntry implements RoundRobinEntry {
        private final AtomicBoolean connectionRequested = new AtomicBoolean(false);
        private final LoadBalancer.Subchannel subchannel;
        private final SynchronizationContext syncContext;

        IdleSubchannelEntry(LoadBalancer.Subchannel subchannel, SynchronizationContext synchronizationContext) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
            this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(synchronizationContext, "syncContext");
        }

        @Override // io.grpc.grpclb.GrpclbState.RoundRobinEntry
        public LoadBalancer.PickResult picked(Metadata metadata) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                this.syncContext.execute(new Runnable() { // from class: io.grpc.grpclb.GrpclbState.IdleSubchannelEntry.1
                    @Override // java.lang.Runnable
                    public void run() {
                        IdleSubchannelEntry.this.subchannel.requestConnection();
                    }
                });
            }
            return LoadBalancer.PickResult.withNoResult();
        }

        public String toString() {
            return "(idle)[" + this.subchannel.getAllAddresses().toString() + "]";
        }

        public int hashCode() {
            return Objects.hashCode(this.subchannel, this.syncContext);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof IdleSubchannelEntry)) {
                return false;
            }
            IdleSubchannelEntry idleSubchannelEntry = (IdleSubchannelEntry) obj;
            return Objects.equal(this.subchannel, idleSubchannelEntry.subchannel) && Objects.equal(this.syncContext, idleSubchannelEntry.syncContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ErrorEntry implements RoundRobinEntry {
        final LoadBalancer.PickResult result;

        ErrorEntry(Status status) {
            this.result = LoadBalancer.PickResult.withError(status);
        }

        @Override // io.grpc.grpclb.GrpclbState.RoundRobinEntry
        public LoadBalancer.PickResult picked(Metadata metadata) {
            return this.result;
        }

        public int hashCode() {
            return Objects.hashCode(this.result);
        }

        public boolean equals(Object obj) {
            if (obj instanceof ErrorEntry) {
                return Objects.equal(this.result, ((ErrorEntry) obj).result);
            }
            return false;
        }

        public String toString() {
            return this.result.getStatus().toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class RoundRobinPicker extends LoadBalancer.SubchannelPicker {
        private int dropIndex;
        final List<DropEntry> dropList;
        private int pickIndex;
        final List<? extends RoundRobinEntry> pickList;

        RoundRobinPicker(List<DropEntry> list, List<? extends RoundRobinEntry> list2) {
            this.dropList = (List) Preconditions.checkNotNull(list, "dropList");
            this.pickList = (List) Preconditions.checkNotNull(list2, "pickList");
            Preconditions.checkArgument(!list2.isEmpty(), "pickList is empty");
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            synchronized (this.pickList) {
                if (!this.dropList.isEmpty()) {
                    DropEntry dropEntry = this.dropList.get(this.dropIndex);
                    this.dropIndex++;
                    if (this.dropIndex == this.dropList.size()) {
                        this.dropIndex = 0;
                    }
                    if (dropEntry != null) {
                        return dropEntry.picked();
                    }
                }
                RoundRobinEntry roundRobinEntry = this.pickList.get(this.pickIndex);
                this.pickIndex++;
                if (this.pickIndex == this.pickList.size()) {
                    this.pickIndex = 0;
                }
                return roundRobinEntry.picked(pickSubchannelArgs.getHeaders());
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) RoundRobinPicker.class).add("dropList", this.dropList).add("pickList", this.pickList).toString();
        }
    }
}
