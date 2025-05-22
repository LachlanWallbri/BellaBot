package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ChannelLogger;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ManagedClientTransport;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalSubchannel implements InternalInstrumented<InternalChannelz.ChannelStats>, TransportProvider {

    @Nullable
    private volatile ManagedClientTransport activeTransport;
    private volatile List<EquivalentAddressGroup> addressGroups;
    private final Index addressIndex;
    private final String authority;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private final Callback callback;
    private final CallTracer callsTracer;
    private final ChannelLogger channelLogger;
    private final ChannelTracer channelTracer;
    private final InternalChannelz channelz;
    private final Stopwatch connectingTimer;
    private final InternalLogId logId;

    @Nullable
    private ConnectionClientTransport pendingTransport;
    private BackoffPolicy reconnectPolicy;

    @Nullable
    private SynchronizationContext.ScheduledHandle reconnectTask;
    private final ScheduledExecutorService scheduledExecutor;

    @Nullable
    private SynchronizationContext.ScheduledHandle shutdownDueToUpdateTask;

    @Nullable
    private ManagedClientTransport shutdownDueToUpdateTransport;
    private Status shutdownReason;
    private final SynchronizationContext syncContext;
    private final ClientTransportFactory transportFactory;
    private final String userAgent;
    private final Collection<ConnectionClientTransport> transports = new ArrayList();
    private final InUseStateAggregator<ConnectionClientTransport> inUseStateAggregator = new InUseStateAggregator<ConnectionClientTransport>() { // from class: io.grpc.internal.InternalSubchannel.1
        @Override // io.grpc.internal.InUseStateAggregator
        protected void handleInUse() {
            InternalSubchannel.this.callback.onInUse(InternalSubchannel.this);
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected void handleNotInUse() {
            InternalSubchannel.this.callback.onNotInUse(InternalSubchannel.this);
        }
    };
    private volatile ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Callback {
        void onInUse(InternalSubchannel internalSubchannel) {
        }

        void onNotInUse(InternalSubchannel internalSubchannel) {
        }

        void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
        }

        void onTerminated(InternalSubchannel internalSubchannel) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalSubchannel(List<EquivalentAddressGroup> list, String str, String str2, BackoffPolicy.Provider provider, ClientTransportFactory clientTransportFactory, ScheduledExecutorService scheduledExecutorService, Supplier<Stopwatch> supplier, SynchronizationContext synchronizationContext, Callback callback, InternalChannelz internalChannelz, CallTracer callTracer, ChannelTracer channelTracer, InternalLogId internalLogId, ChannelLogger channelLogger) {
        Preconditions.checkNotNull(list, "addressGroups");
        Preconditions.checkArgument(!list.isEmpty(), "addressGroups is empty");
        checkListHasNoNulls(list, "addressGroups contains null entry");
        List<EquivalentAddressGroup> unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.addressGroups = unmodifiableList;
        this.addressIndex = new Index(unmodifiableList);
        this.authority = str;
        this.userAgent = str2;
        this.backoffPolicyProvider = provider;
        this.transportFactory = clientTransportFactory;
        this.scheduledExecutor = scheduledExecutorService;
        this.connectingTimer = supplier.get();
        this.syncContext = synchronizationContext;
        this.callback = callback;
        this.channelz = internalChannelz;
        this.callsTracer = callTracer;
        this.channelTracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer, "channelTracer");
        this.logId = (InternalLogId) Preconditions.checkNotNull(internalLogId, "logId");
        this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger, "channelLogger");
    }

    ChannelLogger getChannelLogger() {
        return this.channelLogger;
    }

    @Override // io.grpc.internal.TransportProvider
    public ClientTransport obtainActiveTransport() {
        ManagedClientTransport managedClientTransport = this.activeTransport;
        if (managedClientTransport != null) {
            return managedClientTransport;
        }
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.2
            @Override // java.lang.Runnable
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.IDLE) {
                    InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING as requested");
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    InternalSubchannel.this.startNewTransport();
                }
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ClientTransport getTransport() {
        return this.activeTransport;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAuthority() {
        return this.authority;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNewTransport() {
        SocketAddress socketAddress;
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress;
        this.syncContext.throwIfNotInThisSynchronizationContext();
        Preconditions.checkState(this.reconnectTask == null, "Should have no reconnectTask scheduled");
        if (this.addressIndex.isAtBeginning()) {
            this.connectingTimer.reset().start();
        }
        SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
        if (currentAddress instanceof HttpConnectProxiedSocketAddress) {
            httpConnectProxiedSocketAddress = (HttpConnectProxiedSocketAddress) currentAddress;
            socketAddress = httpConnectProxiedSocketAddress.getTargetAddress();
        } else {
            socketAddress = currentAddress;
            httpConnectProxiedSocketAddress = null;
        }
        Attributes currentEagAttributes = this.addressIndex.getCurrentEagAttributes();
        String str = (String) currentEagAttributes.get(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE);
        ClientTransportFactory.ClientTransportOptions clientTransportOptions = new ClientTransportFactory.ClientTransportOptions();
        if (str == null) {
            str = this.authority;
        }
        ClientTransportFactory.ClientTransportOptions httpConnectProxiedSocketAddress2 = clientTransportOptions.setAuthority(str).setEagAttributes(currentEagAttributes).setUserAgent(this.userAgent).setHttpConnectProxiedSocketAddress(httpConnectProxiedSocketAddress);
        TransportLogger transportLogger = new TransportLogger();
        transportLogger.logId = getLogId();
        CallTracingTransport callTracingTransport = new CallTracingTransport(this.transportFactory.newClientTransport(socketAddress, httpConnectProxiedSocketAddress2, transportLogger), this.callsTracer);
        transportLogger.logId = callTracingTransport.getLogId();
        this.channelz.addClientSocket(callTracingTransport);
        this.pendingTransport = callTracingTransport;
        this.transports.add(callTracingTransport);
        Runnable start = callTracingTransport.start(new TransportListener(callTracingTransport, socketAddress));
        if (start != null) {
            this.syncContext.executeLater(start);
        }
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Started transport {0}", transportLogger.logId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleBackoff(Status status) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forTransientFailure(status));
        if (this.reconnectPolicy == null) {
            this.reconnectPolicy = this.backoffPolicyProvider.get();
        }
        long nextBackoffNanos = this.reconnectPolicy.nextBackoffNanos() - this.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", printShortStatus(status), Long.valueOf(nextBackoffNanos));
        Preconditions.checkState(this.reconnectTask == null, "previous reconnectTask is not done");
        this.reconnectTask = this.syncContext.schedule(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.1EndOfCurrentBackoff
            @Override // java.lang.Runnable
            public void run() {
                InternalSubchannel.this.reconnectTask = null;
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING after backoff");
                InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                InternalSubchannel.this.startNewTransport();
            }
        }, nextBackoffNanos, TimeUnit.NANOSECONDS, this.scheduledExecutor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.3
            @Override // java.lang.Runnable
            public void run() {
                if (InternalSubchannel.this.state.getState() != ConnectivityState.TRANSIENT_FAILURE) {
                    return;
                }
                InternalSubchannel.this.cancelReconnectTask();
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
                InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                InternalSubchannel.this.startNewTransport();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoNonErrorState(ConnectivityState connectivityState) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forNonError(connectivityState));
    }

    private void gotoState(ConnectivityStateInfo connectivityStateInfo) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.state.getState() != connectivityStateInfo.getState()) {
            Preconditions.checkState(this.state.getState() != ConnectivityState.SHUTDOWN, "Cannot transition out of SHUTDOWN to " + connectivityStateInfo);
            this.state = connectivityStateInfo;
            this.callback.onStateChange(this, connectivityStateInfo);
        }
    }

    public void updateAddresses(List<EquivalentAddressGroup> list) {
        Preconditions.checkNotNull(list, "newAddressGroups");
        checkListHasNoNulls(list, "newAddressGroups contains null entry");
        Preconditions.checkArgument(!list.isEmpty(), "newAddressGroups is empty");
        final List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.4
            /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0096  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                ManagedClientTransport managedClientTransport;
                SocketAddress currentAddress = InternalSubchannel.this.addressIndex.getCurrentAddress();
                InternalSubchannel.this.addressIndex.updateGroups(unmodifiableList);
                InternalSubchannel.this.addressGroups = unmodifiableList;
                if ((InternalSubchannel.this.state.getState() == ConnectivityState.READY || InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING) && !InternalSubchannel.this.addressIndex.seekTo(currentAddress)) {
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.READY) {
                        managedClientTransport = InternalSubchannel.this.activeTransport;
                        InternalSubchannel.this.activeTransport = null;
                        InternalSubchannel.this.addressIndex.reset();
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                        if (managedClientTransport == null) {
                            if (InternalSubchannel.this.shutdownDueToUpdateTask != null) {
                                InternalSubchannel.this.shutdownDueToUpdateTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport early due to address change"));
                                InternalSubchannel.this.shutdownDueToUpdateTask.cancel();
                                InternalSubchannel.this.shutdownDueToUpdateTask = null;
                                InternalSubchannel.this.shutdownDueToUpdateTransport = null;
                            }
                            InternalSubchannel.this.shutdownDueToUpdateTransport = managedClientTransport;
                            InternalSubchannel internalSubchannel = InternalSubchannel.this;
                            internalSubchannel.shutdownDueToUpdateTask = internalSubchannel.syncContext.schedule(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ManagedClientTransport managedClientTransport2 = InternalSubchannel.this.shutdownDueToUpdateTransport;
                                    InternalSubchannel.this.shutdownDueToUpdateTask = null;
                                    InternalSubchannel.this.shutdownDueToUpdateTransport = null;
                                    managedClientTransport2.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
                                }
                            }, 5L, TimeUnit.SECONDS, InternalSubchannel.this.scheduledExecutor);
                            return;
                        }
                        return;
                    }
                    InternalSubchannel.this.pendingTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed pending transport due to address change"));
                    InternalSubchannel.this.pendingTransport = null;
                    InternalSubchannel.this.addressIndex.reset();
                    InternalSubchannel.this.startNewTransport();
                }
                managedClientTransport = null;
                if (managedClientTransport == null) {
                }
            }
        });
    }

    public void shutdown(final Status status) {
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.5
            @Override // java.lang.Runnable
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN) {
                    return;
                }
                InternalSubchannel.this.shutdownReason = status;
                ManagedClientTransport managedClientTransport = InternalSubchannel.this.activeTransport;
                ConnectionClientTransport connectionClientTransport = InternalSubchannel.this.pendingTransport;
                InternalSubchannel.this.activeTransport = null;
                InternalSubchannel.this.pendingTransport = null;
                InternalSubchannel.this.gotoNonErrorState(ConnectivityState.SHUTDOWN);
                InternalSubchannel.this.addressIndex.reset();
                if (InternalSubchannel.this.transports.isEmpty()) {
                    InternalSubchannel.this.handleTermination();
                }
                InternalSubchannel.this.cancelReconnectTask();
                if (InternalSubchannel.this.shutdownDueToUpdateTask != null) {
                    InternalSubchannel.this.shutdownDueToUpdateTask.cancel();
                    InternalSubchannel.this.shutdownDueToUpdateTransport.shutdown(status);
                    InternalSubchannel.this.shutdownDueToUpdateTask = null;
                    InternalSubchannel.this.shutdownDueToUpdateTransport = null;
                }
                if (managedClientTransport != null) {
                    managedClientTransport.shutdown(status);
                }
                if (connectionClientTransport != null) {
                    connectionClientTransport.shutdown(status);
                }
            }
        });
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("addressGroups", this.addressGroups).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTermination() {
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.6
            @Override // java.lang.Runnable
            public void run() {
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
                InternalSubchannel.this.callback.onTerminated(InternalSubchannel.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransportInUseState(final ConnectionClientTransport connectionClientTransport, final boolean z) {
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.7
            @Override // java.lang.Runnable
            public void run() {
                InternalSubchannel.this.inUseStateAggregator.updateObjectInUse(connectionClientTransport, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void shutdownNow(final Status status) {
        shutdown(status);
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = new ArrayList(InternalSubchannel.this.transports).iterator();
                while (it.hasNext()) {
                    ((ManagedClientTransport) it.next()).shutdownNow(status);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<EquivalentAddressGroup> getAddressGroups() {
        return this.addressGroups;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelReconnectTask() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.reconnectTask;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.reconnectTask = null;
            this.reconnectPolicy = null;
        }
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.9
            @Override // java.lang.Runnable
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                List<EquivalentAddressGroup> groups = InternalSubchannel.this.addressIndex.getGroups();
                ArrayList arrayList = new ArrayList(InternalSubchannel.this.transports);
                builder.setTarget(groups.toString()).setState(InternalSubchannel.this.getState());
                builder.setSockets(arrayList);
                InternalSubchannel.this.callsTracer.updateBuilder(builder);
                InternalSubchannel.this.channelTracer.updateBuilder(builder);
                create.set(builder.build());
            }
        });
        return create;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityState getState() {
        return this.state.getState();
    }

    private static void checkListHasNoNulls(List<?> list, String str) {
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class TransportListener implements ManagedClientTransport.Listener {
        final SocketAddress address;
        boolean shutdownInitiated = false;
        final ConnectionClientTransport transport;

        TransportListener(ConnectionClientTransport connectionClientTransport, SocketAddress socketAddress) {
            this.transport = connectionClientTransport;
            this.address = socketAddress;
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportReady() {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "READY");
            InternalSubchannel.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.TransportListener.1
                @Override // java.lang.Runnable
                public void run() {
                    InternalSubchannel.this.reconnectPolicy = null;
                    if (InternalSubchannel.this.shutdownReason != null) {
                        Preconditions.checkState(InternalSubchannel.this.activeTransport == null, "Unexpected non-null activeTransport");
                        TransportListener.this.transport.shutdown(InternalSubchannel.this.shutdownReason);
                    } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                        InternalSubchannel.this.activeTransport = TransportListener.this.transport;
                        InternalSubchannel.this.pendingTransport = null;
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.READY);
                    }
                }
            });
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportInUse(boolean z) {
            InternalSubchannel.this.handleTransportInUseState(this.transport, z);
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportShutdown(final Status status) {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} SHUTDOWN with {1}", this.transport.getLogId(), InternalSubchannel.this.printShortStatus(status));
            this.shutdownInitiated = true;
            InternalSubchannel.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.TransportListener.2
                @Override // java.lang.Runnable
                public void run() {
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN) {
                        return;
                    }
                    if (InternalSubchannel.this.activeTransport == TransportListener.this.transport) {
                        InternalSubchannel.this.activeTransport = null;
                        InternalSubchannel.this.addressIndex.reset();
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                    } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                        Preconditions.checkState(InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING, "Expected state is CONNECTING, actual state is %s", InternalSubchannel.this.state.getState());
                        InternalSubchannel.this.addressIndex.increment();
                        if (!InternalSubchannel.this.addressIndex.isValid()) {
                            InternalSubchannel.this.pendingTransport = null;
                            InternalSubchannel.this.addressIndex.reset();
                            InternalSubchannel.this.scheduleBackoff(status);
                            return;
                        }
                        InternalSubchannel.this.startNewTransport();
                    }
                }
            });
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public void transportTerminated() {
            Preconditions.checkState(this.shutdownInitiated, "transportShutdown() must be called before transportTerminated().");
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} Terminated", this.transport.getLogId());
            InternalSubchannel.this.channelz.removeClientSocket(this.transport);
            InternalSubchannel.this.handleTransportInUseState(this.transport, false);
            InternalSubchannel.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.InternalSubchannel.TransportListener.3
                @Override // java.lang.Runnable
                public void run() {
                    InternalSubchannel.this.transports.remove(TransportListener.this.transport);
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN && InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class CallTracingTransport extends ForwardingConnectionClientTransport {
        private final CallTracer callTracer;
        private final ConnectionClientTransport delegate;

        private CallTracingTransport(ConnectionClientTransport connectionClientTransport, CallTracer callTracer) {
            this.delegate = connectionClientTransport;
            this.callTracer = callTracer;
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport
        protected ConnectionClientTransport delegate() {
            return this.delegate;
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport, io.grpc.internal.ClientTransport
        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
            final ClientStream newStream = super.newStream(methodDescriptor, metadata, callOptions);
            return new ForwardingClientStream() { // from class: io.grpc.internal.InternalSubchannel.CallTracingTransport.1
                @Override // io.grpc.internal.ForwardingClientStream
                protected ClientStream delegate() {
                    return newStream;
                }

                @Override // io.grpc.internal.ForwardingClientStream, io.grpc.internal.ClientStream
                public void start(final ClientStreamListener clientStreamListener) {
                    CallTracingTransport.this.callTracer.reportCallStarted();
                    super.start(new ForwardingClientStreamListener() { // from class: io.grpc.internal.InternalSubchannel.CallTracingTransport.1.1
                        @Override // io.grpc.internal.ForwardingClientStreamListener
                        protected ClientStreamListener delegate() {
                            return clientStreamListener;
                        }

                        @Override // io.grpc.internal.ForwardingClientStreamListener, io.grpc.internal.ClientStreamListener
                        public void closed(Status status, Metadata metadata2) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, metadata2);
                        }

                        @Override // io.grpc.internal.ForwardingClientStreamListener, io.grpc.internal.ClientStreamListener
                        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata2) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, rpcProgress, metadata2);
                        }
                    });
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Index {
        private List<EquivalentAddressGroup> addressGroups;
        private int addressIndex;
        private int groupIndex;

        public Index(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
        }

        public boolean isValid() {
            return this.groupIndex < this.addressGroups.size();
        }

        public boolean isAtBeginning() {
            return this.groupIndex == 0 && this.addressIndex == 0;
        }

        public void increment() {
            EquivalentAddressGroup equivalentAddressGroup = this.addressGroups.get(this.groupIndex);
            this.addressIndex++;
            if (this.addressIndex >= equivalentAddressGroup.getAddresses().size()) {
                this.groupIndex++;
                this.addressIndex = 0;
            }
        }

        public void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public SocketAddress getCurrentAddress() {
            return this.addressGroups.get(this.groupIndex).getAddresses().get(this.addressIndex);
        }

        public Attributes getCurrentEagAttributes() {
            return this.addressGroups.get(this.groupIndex).getAttributes();
        }

        public List<EquivalentAddressGroup> getGroups() {
            return this.addressGroups;
        }

        public void updateGroups(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
            reset();
        }

        public boolean seekTo(SocketAddress socketAddress) {
            for (int i = 0; i < this.addressGroups.size(); i++) {
                int indexOf = this.addressGroups.get(i).getAddresses().indexOf(socketAddress);
                if (indexOf != -1) {
                    this.groupIndex = i;
                    this.addressIndex = indexOf;
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String printShortStatus(Status status) {
        StringBuilder sb = new StringBuilder();
        sb.append(status.getCode());
        if (status.getDescription() != null) {
            sb.append("(");
            sb.append(status.getDescription());
            sb.append(")");
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class TransportLogger extends ChannelLogger {
        InternalLogId logId;

        TransportLogger() {
        }

        @Override // io.grpc.ChannelLogger
        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str);
        }

        @Override // io.grpc.ChannelLogger
        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str, objArr);
        }
    }
}
