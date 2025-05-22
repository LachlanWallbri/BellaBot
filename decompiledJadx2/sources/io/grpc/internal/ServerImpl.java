package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.InternalServerInterceptors;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerTransportFilter;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServerImpl extends Server implements InternalInstrumented<InternalChannelz.ServerStats> {
    private final BinaryLog binlog;
    private final InternalChannelz channelz;
    private final CompressorRegistry compressorRegistry;
    private final DecompressorRegistry decompressorRegistry;
    private Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    private final HandlerRegistry fallbackRegistry;
    private final long handshakeTimeoutMillis;
    private final ServerInterceptor[] interceptors;
    private final HandlerRegistry registry;
    private final Context rootContext;
    private final CallTracer serverCallTracer;
    private boolean serverShutdownCallbackInvoked;
    private boolean shutdown;
    private Status shutdownNowStatus;
    private boolean started;
    private boolean terminated;
    private final Deadline.Ticker ticker;
    private final List<ServerTransportFilter> transportFilters;
    private final InternalServer transportServer;
    private boolean transportServersTerminated;
    private static final Logger log = Logger.getLogger(ServerImpl.class.getName());
    private static final ServerStreamListener NOOP_LISTENER = new NoopListener();
    private final Object lock = new Object();
    private final Set<ServerTransport> transports = new HashSet();
    private final InternalLogId logId = InternalLogId.allocate("Server", String.valueOf(getListenSocketsIgnoringLifecycle()));

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerImpl(ServerImplBuilder serverImplBuilder, InternalServer internalServer, Context context) {
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(serverImplBuilder.executorPool, "executorPool");
        this.registry = (HandlerRegistry) Preconditions.checkNotNull(serverImplBuilder.registryBuilder.build(), "registryBuilder");
        this.fallbackRegistry = (HandlerRegistry) Preconditions.checkNotNull(serverImplBuilder.fallbackRegistry, "fallbackRegistry");
        this.transportServer = (InternalServer) Preconditions.checkNotNull(internalServer, "transportServer");
        this.rootContext = ((Context) Preconditions.checkNotNull(context, "rootContext")).fork();
        this.decompressorRegistry = serverImplBuilder.decompressorRegistry;
        this.compressorRegistry = serverImplBuilder.compressorRegistry;
        this.transportFilters = Collections.unmodifiableList(new ArrayList(serverImplBuilder.transportFilters));
        this.interceptors = (ServerInterceptor[]) serverImplBuilder.interceptors.toArray(new ServerInterceptor[serverImplBuilder.interceptors.size()]);
        this.handshakeTimeoutMillis = serverImplBuilder.handshakeTimeoutMillis;
        this.binlog = serverImplBuilder.binlog;
        this.channelz = serverImplBuilder.channelz;
        this.serverCallTracer = serverImplBuilder.callTracerFactory.create();
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(serverImplBuilder.ticker, "ticker");
        this.channelz.addServer(this);
    }

    @Override // io.grpc.Server
    public ServerImpl start() throws IOException {
        synchronized (this.lock) {
            Preconditions.checkState(!this.started, "Already started");
            Preconditions.checkState(this.shutdown ? false : true, "Shutting down");
            this.transportServer.start(new ServerListenerImpl());
            this.executor = (Executor) Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
            this.started = true;
        }
        return this;
    }

    @Override // io.grpc.Server
    public int getPort() {
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            for (SocketAddress socketAddress : this.transportServer.getListenSocketAddresses()) {
                if (socketAddress instanceof InetSocketAddress) {
                    return ((InetSocketAddress) socketAddress).getPort();
                }
            }
            return -1;
        }
    }

    @Override // io.grpc.Server
    public List<SocketAddress> getListenSockets() {
        List<SocketAddress> listenSocketsIgnoringLifecycle;
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            listenSocketsIgnoringLifecycle = getListenSocketsIgnoringLifecycle();
        }
        return listenSocketsIgnoringLifecycle;
    }

    private List<SocketAddress> getListenSocketsIgnoringLifecycle() {
        List<SocketAddress> unmodifiableList;
        synchronized (this.lock) {
            unmodifiableList = Collections.unmodifiableList(this.transportServer.getListenSocketAddresses());
        }
        return unmodifiableList;
    }

    @Override // io.grpc.Server
    public List<ServerServiceDefinition> getServices() {
        List<ServerServiceDefinition> services = this.fallbackRegistry.getServices();
        if (services.isEmpty()) {
            return this.registry.getServices();
        }
        List<ServerServiceDefinition> services2 = this.registry.getServices();
        ArrayList arrayList = new ArrayList(services2.size() + services.size());
        arrayList.addAll(services2);
        arrayList.addAll(services);
        return Collections.unmodifiableList(arrayList);
    }

    @Override // io.grpc.Server
    public List<ServerServiceDefinition> getImmutableServices() {
        return this.registry.getServices();
    }

    @Override // io.grpc.Server
    public List<ServerServiceDefinition> getMutableServices() {
        return Collections.unmodifiableList(this.fallbackRegistry.getServices());
    }

    @Override // io.grpc.Server
    public ServerImpl shutdown() {
        synchronized (this.lock) {
            if (this.shutdown) {
                return this;
            }
            this.shutdown = true;
            boolean z = this.started;
            if (!z) {
                this.transportServersTerminated = true;
                checkForTermination();
            }
            if (z) {
                this.transportServer.shutdown();
            }
            return this;
        }
    }

    @Override // io.grpc.Server
    public ServerImpl shutdownNow() {
        shutdown();
        Status withDescription = Status.UNAVAILABLE.withDescription("Server shutdownNow invoked");
        synchronized (this.lock) {
            if (this.shutdownNowStatus != null) {
                return this;
            }
            this.shutdownNowStatus = withDescription;
            ArrayList arrayList = new ArrayList(this.transports);
            boolean z = this.serverShutdownCallbackInvoked;
            if (z) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((ServerTransport) it.next()).shutdownNow(withDescription);
                }
            }
            return this;
        }
    }

    @Override // io.grpc.Server
    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    @Override // io.grpc.Server
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        synchronized (this.lock) {
            long nanoTime = System.nanoTime() + timeUnit.toNanos(j);
            while (!this.terminated) {
                long nanoTime2 = nanoTime - System.nanoTime();
                if (nanoTime2 <= 0) {
                    break;
                }
                TimeUnit.NANOSECONDS.timedWait(this.lock, nanoTime2);
            }
            z = this.terminated;
        }
        return z;
    }

    @Override // io.grpc.Server
    public void awaitTermination() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.terminated) {
                this.lock.wait();
            }
        }
    }

    @Override // io.grpc.Server
    public boolean isTerminated() {
        boolean z;
        synchronized (this.lock) {
            z = this.terminated;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transportClosed(ServerTransport serverTransport) {
        synchronized (this.lock) {
            if (!this.transports.remove(serverTransport)) {
                throw new AssertionError("Transport already removed");
            }
            this.channelz.removeServerSocket(this, serverTransport);
            checkForTermination();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkForTermination() {
        synchronized (this.lock) {
            if (this.shutdown && this.transports.isEmpty() && this.transportServersTerminated) {
                if (this.terminated) {
                    throw new AssertionError("Server already terminated");
                }
                this.terminated = true;
                this.channelz.removeServer(this);
                if (this.executor != null) {
                    this.executor = this.executorPool.returnObject(this.executor);
                }
                this.lock.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class ServerListenerImpl implements ServerListener {
        private ServerListenerImpl() {
        }

        @Override // io.grpc.internal.ServerListener
        public ServerTransportListener transportCreated(ServerTransport serverTransport) {
            synchronized (ServerImpl.this.lock) {
                ServerImpl.this.transports.add(serverTransport);
            }
            ServerTransportListenerImpl serverTransportListenerImpl = new ServerTransportListenerImpl(serverTransport);
            serverTransportListenerImpl.init();
            return serverTransportListenerImpl;
        }

        @Override // io.grpc.internal.ServerListener
        public void serverShutdown() {
            synchronized (ServerImpl.this.lock) {
                if (ServerImpl.this.serverShutdownCallbackInvoked) {
                    return;
                }
                ArrayList arrayList = new ArrayList(ServerImpl.this.transports);
                Status status = ServerImpl.this.shutdownNowStatus;
                ServerImpl.this.serverShutdownCallbackInvoked = true;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ServerTransport serverTransport = (ServerTransport) it.next();
                    if (status == null) {
                        serverTransport.shutdown();
                    } else {
                        serverTransport.shutdownNow(status);
                    }
                }
                synchronized (ServerImpl.this.lock) {
                    ServerImpl.this.transportServersTerminated = true;
                    ServerImpl.this.checkForTermination();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class ServerTransportListenerImpl implements ServerTransportListener {
        private Attributes attributes;
        private Future<?> handshakeTimeoutFuture;
        private final ServerTransport transport;

        ServerTransportListenerImpl(ServerTransport serverTransport) {
            this.transport = serverTransport;
        }

        public void init() {
            if (ServerImpl.this.handshakeTimeoutMillis != Long.MAX_VALUE) {
                this.handshakeTimeoutFuture = this.transport.getScheduledExecutorService().schedule(new Runnable() { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1TransportShutdownNow
                    @Override // java.lang.Runnable
                    public void run() {
                        ServerTransportListenerImpl.this.transport.shutdownNow(Status.CANCELLED.withDescription("Handshake timeout exceeded"));
                    }
                }, ServerImpl.this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
            } else {
                this.handshakeTimeoutFuture = new FutureTask(new Runnable() { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                    }
                }, null);
            }
            ServerImpl.this.channelz.addServerSocket(ServerImpl.this, this.transport);
        }

        @Override // io.grpc.internal.ServerTransportListener
        public Attributes transportReady(Attributes attributes) {
            this.handshakeTimeoutFuture.cancel(false);
            this.handshakeTimeoutFuture = null;
            for (ServerTransportFilter serverTransportFilter : ServerImpl.this.transportFilters) {
                attributes = (Attributes) Preconditions.checkNotNull(serverTransportFilter.transportReady(attributes), "Filter %s returned null", serverTransportFilter);
            }
            this.attributes = attributes;
            return attributes;
        }

        @Override // io.grpc.internal.ServerTransportListener
        public void transportTerminated() {
            Future<?> future = this.handshakeTimeoutFuture;
            if (future != null) {
                future.cancel(false);
                this.handshakeTimeoutFuture = null;
            }
            Iterator it = ServerImpl.this.transportFilters.iterator();
            while (it.hasNext()) {
                ((ServerTransportFilter) it.next()).transportTerminated(this.attributes);
            }
            ServerImpl.this.transportClosed(this.transport);
        }

        @Override // io.grpc.internal.ServerTransportListener
        public void streamCreated(ServerStream serverStream, String str, Metadata metadata) {
            Tag createTag = PerfMark.createTag(str, serverStream.streamId());
            PerfMark.startTask("ServerTransportListener.streamCreated", createTag);
            try {
                streamCreatedInternal(serverStream, str, metadata, createTag);
            } finally {
                PerfMark.stopTask("ServerTransportListener.streamCreated", createTag);
            }
        }

        private void streamCreatedInternal(ServerStream serverStream, String str, Metadata metadata, Tag tag) {
            Executor serializingExecutor;
            if (ServerImpl.this.executor == MoreExecutors.directExecutor()) {
                serializingExecutor = new SerializeReentrantCallsDirectExecutor();
                serverStream.optimizeForDirectExecutor();
            } else {
                serializingExecutor = new SerializingExecutor(ServerImpl.this.executor);
            }
            Executor executor = serializingExecutor;
            if (metadata.containsKey(GrpcUtil.MESSAGE_ENCODING_KEY)) {
                String str2 = (String) metadata.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                Decompressor lookupDecompressor = ServerImpl.this.decompressorRegistry.lookupDecompressor(str2);
                if (lookupDecompressor == null) {
                    serverStream.setListener(ServerImpl.NOOP_LISTENER);
                    serverStream.close(Status.UNIMPLEMENTED.withDescription(String.format("Can't find decompressor for %s", str2)), new Metadata());
                    return;
                }
                serverStream.setDecompressor(lookupDecompressor);
            }
            StatsTraceContext statsTraceContext = (StatsTraceContext) Preconditions.checkNotNull(serverStream.statsTraceContext(), "statsTraceCtx not present from stream");
            Context.CancellableContext createContext = createContext(metadata, statsTraceContext);
            Link linkOut = PerfMark.linkOut();
            JumpToApplicationThreadServerStreamListener jumpToApplicationThreadServerStreamListener = new JumpToApplicationThreadServerStreamListener(executor, ServerImpl.this.executor, serverStream, createContext, tag);
            serverStream.setListener(jumpToApplicationThreadServerStreamListener);
            executor.execute(new ContextRunnable(createContext, tag, linkOut, str, serverStream, metadata, statsTraceContext, jumpToApplicationThreadServerStreamListener) { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1StreamCreated
                final /* synthetic */ Context.CancellableContext val$context;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ JumpToApplicationThreadServerStreamListener val$jumpListener;
                final /* synthetic */ Link val$link;
                final /* synthetic */ String val$methodName;
                final /* synthetic */ StatsTraceContext val$statsTraceCtx;
                final /* synthetic */ ServerStream val$stream;
                final /* synthetic */ Tag val$tag;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(createContext);
                    this.val$context = createContext;
                    this.val$tag = tag;
                    this.val$link = linkOut;
                    this.val$methodName = str;
                    this.val$stream = serverStream;
                    this.val$headers = metadata;
                    this.val$statsTraceCtx = statsTraceContext;
                    this.val$jumpListener = jumpToApplicationThreadServerStreamListener;
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    PerfMark.startTask("ServerTransportListener$StreamCreated.startCall", this.val$tag);
                    PerfMark.linkIn(this.val$link);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ServerTransportListener$StreamCreated.startCall", this.val$tag);
                    }
                }

                private void runInternal() {
                    ServerStreamListener serverStreamListener = ServerImpl.NOOP_LISTENER;
                    try {
                        ServerMethodDefinition<?, ?> lookupMethod = ServerImpl.this.registry.lookupMethod(this.val$methodName);
                        if (lookupMethod == null) {
                            lookupMethod = ServerImpl.this.fallbackRegistry.lookupMethod(this.val$methodName, this.val$stream.getAuthority());
                        }
                        ServerMethodDefinition<?, ?> serverMethodDefinition = lookupMethod;
                        if (serverMethodDefinition != null) {
                            this.val$jumpListener.setListener(ServerTransportListenerImpl.this.startCall(this.val$stream, this.val$methodName, serverMethodDefinition, this.val$headers, this.val$context, this.val$statsTraceCtx, this.val$tag));
                            this.val$context.addListener(new Context.CancellationListener() { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1StreamCreated.1ServerStreamCancellationListener
                                @Override // io.grpc.Context.CancellationListener
                                public void cancelled(Context context) {
                                    Status statusFromCancelled = Contexts.statusFromCancelled(context);
                                    if (Status.DEADLINE_EXCEEDED.getCode().equals(statusFromCancelled.getCode())) {
                                        C1StreamCreated.this.val$stream.cancel(statusFromCancelled);
                                    }
                                }
                            }, MoreExecutors.directExecutor());
                            return;
                        }
                        this.val$stream.close(Status.UNIMPLEMENTED.withDescription("Method not found: " + this.val$methodName), new Metadata());
                        this.val$context.cancel(null);
                    } catch (Throwable th) {
                        try {
                            this.val$stream.close(Status.fromThrowable(th), new Metadata());
                            this.val$context.cancel(null);
                            throw th;
                        } finally {
                            this.val$jumpListener.setListener(serverStreamListener);
                        }
                    }
                }
            });
        }

        private Context.CancellableContext createContext(Metadata metadata, StatsTraceContext statsTraceContext) {
            Long l = (Long) metadata.get(GrpcUtil.TIMEOUT_KEY);
            Context withValue = statsTraceContext.serverFilterContext(ServerImpl.this.rootContext).withValue(io.grpc.InternalServer.SERVER_CONTEXT_KEY, ServerImpl.this);
            if (l == null) {
                return withValue.withCancellation();
            }
            return withValue.withDeadline(Deadline.after(l.longValue(), TimeUnit.NANOSECONDS, ServerImpl.this.ticker), this.transport.getScheduledExecutorService());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <ReqT, RespT> ServerStreamListener startCall(ServerStream serverStream, String str, ServerMethodDefinition<ReqT, RespT> serverMethodDefinition, Metadata metadata, Context.CancellableContext cancellableContext, StatsTraceContext statsTraceContext, Tag tag) {
            statsTraceContext.serverCallStarted(new ServerCallInfoImpl(serverMethodDefinition.getMethodDescriptor(), serverStream.getAttributes(), serverStream.getAuthority()));
            ServerCallHandler<ReqT, RespT> serverCallHandler = serverMethodDefinition.getServerCallHandler();
            for (ServerInterceptor serverInterceptor : ServerImpl.this.interceptors) {
                serverCallHandler = InternalServerInterceptors.interceptCallHandlerCreate(serverInterceptor, serverCallHandler);
            }
            ServerMethodDefinition<ReqT, RespT> withServerCallHandler = serverMethodDefinition.withServerCallHandler(serverCallHandler);
            if (ServerImpl.this.binlog != null) {
                withServerCallHandler = (ServerMethodDefinition<ReqT, RespT>) ServerImpl.this.binlog.wrapMethodDefinition(withServerCallHandler);
            }
            return startWrappedCall(str, withServerCallHandler, serverStream, metadata, cancellableContext, tag);
        }

        private <WReqT, WRespT> ServerStreamListener startWrappedCall(String str, ServerMethodDefinition<WReqT, WRespT> serverMethodDefinition, ServerStream serverStream, Metadata metadata, Context.CancellableContext cancellableContext, Tag tag) {
            ServerCallImpl serverCallImpl = new ServerCallImpl(serverStream, serverMethodDefinition.getMethodDescriptor(), metadata, cancellableContext, ServerImpl.this.decompressorRegistry, ServerImpl.this.compressorRegistry, ServerImpl.this.serverCallTracer, tag);
            ServerCall.Listener<WReqT> startCall = serverMethodDefinition.getServerCallHandler().startCall(serverCallImpl, metadata);
            if (startCall == null) {
                throw new NullPointerException("startCall() returned a null listener for method " + str);
            }
            return serverCallImpl.newServerStreamListener(startCall);
        }
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.ServerStats> getStats() {
        InternalChannelz.ServerStats.Builder builder = new InternalChannelz.ServerStats.Builder();
        List<InternalInstrumented<InternalChannelz.SocketStats>> listenSocketStatsList = this.transportServer.getListenSocketStatsList();
        if (listenSocketStatsList != null) {
            builder.addListenSockets(listenSocketStatsList);
        }
        this.serverCallTracer.updateBuilder(builder);
        SettableFuture create = SettableFuture.create();
        create.set(builder.build());
        return create;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("transportServer", this.transportServer).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class NoopListener implements ServerStreamListener {
        @Override // io.grpc.internal.ServerStreamListener
        public void closed(Status status) {
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void halfClosed() {
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
        }

        private NoopListener() {
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            while (true) {
                InputStream next = messageProducer.next();
                if (next == null) {
                    return;
                }
                try {
                    next.close();
                } catch (IOException e) {
                    while (true) {
                        InputStream next2 = messageProducer.next();
                        if (next2 == null) {
                            break;
                        }
                        try {
                            next2.close();
                        } catch (IOException e2) {
                            ServerImpl.log.log(Level.WARNING, "Exception closing stream", (Throwable) e2);
                        }
                    }
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class JumpToApplicationThreadServerStreamListener implements ServerStreamListener {
        private final Executor callExecutor;
        private final Executor cancelExecutor;
        private final Context.CancellableContext context;
        private ServerStreamListener listener;
        private final ServerStream stream;
        private final Tag tag;

        public JumpToApplicationThreadServerStreamListener(Executor executor, Executor executor2, ServerStream serverStream, Context.CancellableContext cancellableContext, Tag tag) {
            this.callExecutor = executor;
            this.cancelExecutor = executor2;
            this.stream = serverStream;
            this.context = cancellableContext;
            this.tag = tag;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ServerStreamListener getListener() {
            ServerStreamListener serverStreamListener = this.listener;
            if (serverStreamListener != null) {
                return serverStreamListener;
            }
            throw new IllegalStateException("listener unset");
        }

        void setListener(ServerStreamListener serverStreamListener) {
            Preconditions.checkNotNull(serverStreamListener, "listener must not be null");
            Preconditions.checkState(this.listener == null, "Listener already set");
            this.listener = serverStreamListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void internalClose(Throwable th) {
            this.stream.close(Status.UNKNOWN.withCause(th), new Metadata());
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            PerfMark.startTask("ServerStreamListener.messagesAvailable", this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1MessagesAvailable
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).messagesAvailable", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().messagesAvailable(messageProducer);
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.messagesAvailable", this.tag);
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void halfClosed() {
            PerfMark.startTask("ServerStreamListener.halfClosed", this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1HalfClosed
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).halfClosed", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().halfClosed();
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.halfClosed", this.tag);
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void closed(Status status) {
            PerfMark.startTask("ServerStreamListener.closed", this.tag);
            try {
                closedInternal(status);
            } finally {
                PerfMark.stopTask("ServerStreamListener.closed", this.tag);
            }
        }

        private void closedInternal(final Status status) {
            if (!status.isOk()) {
                this.cancelExecutor.execute(new ContextCloser(this.context, status.getCause()));
            }
            final Link linkOut = PerfMark.linkOut();
            this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1Closed
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(JumpToApplicationThreadServerStreamListener.this.context);
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    PerfMark.startTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    PerfMark.linkIn(linkOut);
                    try {
                        JumpToApplicationThreadServerStreamListener.this.getListener().closed(status);
                    } finally {
                        PerfMark.stopTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    }
                }
            });
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
            PerfMark.startTask("ServerStreamListener.onReady", this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1OnReady
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).onReady", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().onReady();
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.onReady", this.tag);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ContextCloser implements Runnable {
        private final Throwable cause;
        private final Context.CancellableContext context;

        ContextCloser(Context.CancellableContext cancellableContext, Throwable th) {
            this.context = cancellableContext;
            this.cause = th;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.context.cancel(this.cause);
        }
    }
}
