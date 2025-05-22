package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.InternalWithLogId;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFactory;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.channel.group.ChannelGroup;
import io.grpc.netty.shaded.io.netty.channel.group.ChannelGroupFuture;
import io.grpc.netty.shaded.io.netty.channel.group.ChannelGroupFutureListener;
import io.grpc.netty.shaded.io.netty.channel.group.DefaultChannelGroup;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NettyServer implements InternalServer, InternalWithLogId {
    private static final Logger log = Logger.getLogger(InternalServer.class.getName());
    private final List<? extends SocketAddress> addresses;
    private final boolean autoFlowControl;
    private final EventLoop bossExecutor;
    private EventLoopGroup bossGroup;
    private final ObjectPool<? extends EventLoopGroup> bossGroupPool;
    private final ChannelFactory<? extends ServerChannel> channelFactory;
    private final ChannelGroup channelGroup;
    private final Map<ChannelOption<?>, ?> channelOptions;
    private final InternalChannelz channelz;
    private final Map<ChannelOption<?>, ?> childChannelOptions;
    private final Attributes eagAttributes;
    private final int flowControlWindow;
    private final boolean forceHeapBuffer;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private ServerListener listener;
    private final InternalLogId logId;
    private final long maxConnectionAgeGraceInNanos;
    private final long maxConnectionAgeInNanos;
    private final long maxConnectionIdleInNanos;
    private final int maxHeaderListSize;
    private final int maxMessageSize;
    private final int maxStreamsPerConnection;
    private final long permitKeepAliveTimeInNanos;
    private final boolean permitKeepAliveWithoutCalls;
    private final ProtocolNegotiator protocolNegotiator;
    private final List<? extends ServerStreamTracer.Factory> streamTracerFactories;
    private volatile boolean terminated;
    private final TransportTracer.Factory transportTracerFactory;
    private EventLoopGroup workerGroup;
    private final ObjectPool<? extends EventLoopGroup> workerGroupPool;
    private final ReferenceCounted sharedResourceReferenceCounter = new SharedResourceReferenceCounter();
    private volatile List<InternalInstrumented<InternalChannelz.SocketStats>> listenSocketStatsList = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyServer(List<? extends SocketAddress> list, ChannelFactory<? extends ServerChannel> channelFactory, Map<ChannelOption<?>, ?> map, Map<ChannelOption<?>, ?> map2, ObjectPool<? extends EventLoopGroup> objectPool, ObjectPool<? extends EventLoopGroup> objectPool2, boolean z, ProtocolNegotiator protocolNegotiator, List<? extends ServerStreamTracer.Factory> list2, TransportTracer.Factory factory, int i, boolean z2, int i2, int i3, int i4, long j, long j2, long j3, long j4, long j5, boolean z3, long j6, Attributes attributes, InternalChannelz internalChannelz) {
        this.addresses = (List) Preconditions.checkNotNull(list, "addresses");
        this.channelFactory = (ChannelFactory) Preconditions.checkNotNull(channelFactory, "channelFactory");
        Preconditions.checkNotNull(map, "channelOptions");
        this.channelOptions = new HashMap(map);
        Preconditions.checkNotNull(map2, "childChannelOptions");
        this.childChannelOptions = new HashMap(map2);
        this.bossGroupPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "bossGroupPool");
        this.workerGroupPool = (ObjectPool) Preconditions.checkNotNull(objectPool2, "workerGroupPool");
        this.forceHeapBuffer = z;
        this.bossGroup = objectPool.getObject();
        this.bossExecutor = this.bossGroup.next();
        this.channelGroup = new DefaultChannelGroup(this.bossExecutor);
        this.workerGroup = objectPool2.getObject();
        this.protocolNegotiator = (ProtocolNegotiator) Preconditions.checkNotNull(protocolNegotiator, "protocolNegotiator");
        this.streamTracerFactories = (List) Preconditions.checkNotNull(list2, "streamTracerFactories");
        this.transportTracerFactory = factory;
        this.maxStreamsPerConnection = i;
        this.autoFlowControl = z2;
        this.flowControlWindow = i2;
        this.maxMessageSize = i3;
        this.maxHeaderListSize = i4;
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        this.maxConnectionIdleInNanos = j3;
        this.maxConnectionAgeInNanos = j4;
        this.maxConnectionAgeGraceInNanos = j5;
        this.permitKeepAliveWithoutCalls = z3;
        this.permitKeepAliveTimeInNanos = j6;
        this.eagAttributes = (Attributes) Preconditions.checkNotNull(attributes, "eagAttributes");
        this.channelz = (InternalChannelz) Preconditions.checkNotNull(internalChannelz);
        this.logId = InternalLogId.allocate(getClass(), list.isEmpty() ? "No address" : String.valueOf(list));
    }

    @Override // io.grpc.internal.InternalServer
    public SocketAddress getListenSocketAddress() {
        Iterator<Channel> it = this.channelGroup.iterator();
        if (it.hasNext()) {
            return it.next().localAddress();
        }
        if (this.addresses.isEmpty()) {
            return null;
        }
        return this.addresses.get(0);
    }

    @Override // io.grpc.internal.InternalServer
    public List<SocketAddress> getListenSocketAddresses() {
        ArrayList arrayList = new ArrayList();
        Iterator<Channel> it = this.channelGroup.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().localAddress());
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.addresses);
        }
        return arrayList;
    }

    @Override // io.grpc.internal.InternalServer
    public InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats() {
        List<InternalInstrumented<InternalChannelz.SocketStats>> list = this.listenSocketStatsList;
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override // io.grpc.internal.InternalServer
    public List<InternalInstrumented<InternalChannelz.SocketStats>> getListenSocketStatsList() {
        return this.listenSocketStatsList;
    }

    @Override // io.grpc.internal.InternalServer
    public void start(ServerListener serverListener) throws IOException {
        this.listener = (ServerListener) Preconditions.checkNotNull(serverListener, "serverListener");
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.option(ChannelOption.ALLOCATOR, Utils.getByteBufAllocator(this.forceHeapBuffer));
        serverBootstrap.childOption(ChannelOption.ALLOCATOR, Utils.getByteBufAllocator(this.forceHeapBuffer));
        serverBootstrap.group(this.bossExecutor, this.workerGroup);
        serverBootstrap.channelFactory((ChannelFactory) this.channelFactory);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        Map<ChannelOption<?>, ?> map = this.channelOptions;
        if (map != null) {
            for (Map.Entry<ChannelOption<?>, ?> entry : map.entrySet()) {
                serverBootstrap.option(entry.getKey(), entry.getValue());
            }
        }
        Map<ChannelOption<?>, ?> map2 = this.childChannelOptions;
        if (map2 != null) {
            for (Map.Entry<ChannelOption<?>, ?> entry2 : map2.entrySet()) {
                serverBootstrap.childOption(entry2.getKey(), entry2.getValue());
            }
        }
        serverBootstrap.childHandler(new ChannelInitializer<Channel>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.1
            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInitializer
            public void initChannel(Channel channel) {
                ChannelPromise newPromise = channel.newPromise();
                long j = NettyServer.this.maxConnectionAgeInNanos;
                if (j != Long.MAX_VALUE) {
                    j = (long) (((Math.random() * 0.2d) + 0.9d) * j);
                }
                NettyServerTransport nettyServerTransport = new NettyServerTransport(channel, newPromise, NettyServer.this.protocolNegotiator, NettyServer.this.streamTracerFactories, NettyServer.this.transportTracerFactory.create(), NettyServer.this.maxStreamsPerConnection, NettyServer.this.autoFlowControl, NettyServer.this.flowControlWindow, NettyServer.this.maxMessageSize, NettyServer.this.maxHeaderListSize, NettyServer.this.keepAliveTimeInNanos, NettyServer.this.keepAliveTimeoutInNanos, NettyServer.this.maxConnectionIdleInNanos, j, NettyServer.this.maxConnectionAgeGraceInNanos, NettyServer.this.permitKeepAliveWithoutCalls, NettyServer.this.permitKeepAliveTimeInNanos, NettyServer.this.eagAttributes);
                synchronized (NettyServer.this) {
                    if (!NettyServer.this.terminated) {
                        NettyServer.this.sharedResourceReferenceCounter.retain();
                        nettyServerTransport.start(NettyServer.this.listener.transportCreated(nettyServerTransport));
                        ChannelFutureListener channelFutureListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.1.1LoopReleaser
                            private boolean done;

                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (this.done) {
                                    return;
                                }
                                this.done = true;
                                NettyServer.this.sharedResourceReferenceCounter.release();
                            }
                        };
                        newPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
                        channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
                        return;
                    }
                    channel.close();
                }
            }
        });
        Future submit = this.bossExecutor.submit((Callable) new Callable<Map<ChannelFuture, SocketAddress>>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.2
            @Override // java.util.concurrent.Callable
            public Map<ChannelFuture, SocketAddress> call() {
                HashMap hashMap = new HashMap();
                for (SocketAddress socketAddress : NettyServer.this.addresses) {
                    ChannelFuture bind = serverBootstrap.bind(socketAddress);
                    NettyServer.this.channelGroup.add(bind.channel());
                    hashMap.put(bind, socketAddress);
                }
                return hashMap;
            }
        });
        Map map3 = (Map) submit.awaitUninterruptibly().getNow();
        if (!submit.isSuccess()) {
            this.channelGroup.close().awaitUninterruptibly();
            throw new IOException(String.format("Failed to bind to addresses %s", this.addresses), submit.cause());
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry3 : map3.entrySet()) {
            ChannelFuture channelFuture = (ChannelFuture) entry3.getKey();
            if (!channelFuture.awaitUninterruptibly().isSuccess()) {
                this.channelGroup.close().awaitUninterruptibly();
                throw new IOException(String.format("Failed to bind to address %s", entry3.getValue()), channelFuture.cause());
            }
            final ListenSocket listenSocket = new ListenSocket(channelFuture.channel());
            this.channelz.addListenSocket(listenSocket);
            arrayList.add(listenSocket);
            channelFuture.channel().closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.3
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                    NettyServer.this.channelz.removeListenSocket(listenSocket);
                }
            });
        }
        this.listenSocketStatsList = Collections.unmodifiableList(arrayList);
    }

    @Override // io.grpc.internal.InternalServer
    public void shutdown() {
        if (this.terminated) {
            return;
        }
        try {
            this.channelGroup.close().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelGroupFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.4
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelGroupFuture channelGroupFuture) throws Exception {
                    if (!channelGroupFuture.isSuccess()) {
                        NettyServer.log.log(Level.WARNING, "Error closing server channel group", (Throwable) channelGroupFuture.cause());
                    }
                    NettyServer.this.sharedResourceReferenceCounter.release();
                    NettyServer.this.protocolNegotiator.close();
                    NettyServer.this.listenSocketStatsList = Collections.emptyList();
                    synchronized (NettyServer.this) {
                        NettyServer.this.listener.serverShutdown();
                        NettyServer.this.terminated = true;
                    }
                }
            }).await();
        } catch (InterruptedException e) {
            log.log(Level.FINE, "Interrupted while shutting down", (Throwable) e);
            Thread.currentThread().interrupt();
        }
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("addresses", this.addresses).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    class SharedResourceReferenceCounter extends AbstractReferenceCounted {
        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public ReferenceCounted touch(Object obj) {
            return this;
        }

        SharedResourceReferenceCounter() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
        protected void deallocate() {
            try {
                if (NettyServer.this.bossGroup != null) {
                    NettyServer.this.bossGroupPool.returnObject(NettyServer.this.bossGroup);
                }
                NettyServer.this.bossGroup = null;
                try {
                    if (NettyServer.this.workerGroup != null) {
                        NettyServer.this.workerGroupPool.returnObject(NettyServer.this.workerGroup);
                    }
                } finally {
                }
            } catch (Throwable th) {
                NettyServer.this.bossGroup = null;
                try {
                    if (NettyServer.this.workerGroup != null) {
                        NettyServer.this.workerGroupPool.returnObject(NettyServer.this.workerGroup);
                    }
                    throw th;
                } finally {
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class ListenSocket implements InternalInstrumented<InternalChannelz.SocketStats> {

        /* renamed from: ch */
        private final Channel f8327ch;

        /* renamed from: id */
        private final InternalLogId f8328id;

        ListenSocket(Channel channel) {
            this.f8327ch = channel;
            this.f8328id = InternalLogId.allocate(getClass(), String.valueOf(channel.localAddress()));
        }

        @Override // io.grpc.InternalInstrumented
        public ListenableFuture<InternalChannelz.SocketStats> getStats() {
            final SettableFuture create = SettableFuture.create();
            if (this.f8327ch.eventLoop().inEventLoop()) {
                create.set(new InternalChannelz.SocketStats(null, this.f8327ch.localAddress(), null, Utils.getSocketOptions(this.f8327ch), null));
                return create;
            }
            this.f8327ch.eventLoop().submit(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.ListenSocket.2
                @Override // java.lang.Runnable
                public void run() {
                    create.set(new InternalChannelz.SocketStats(null, ListenSocket.this.f8327ch.localAddress(), null, Utils.getSocketOptions(ListenSocket.this.f8327ch), null));
                }
            }).addListener(new GenericFutureListener<Future<Object>>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.ListenSocket.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<Object> future) throws Exception {
                    if (future.isSuccess()) {
                        return;
                    }
                    create.setException(future.cause());
                }
            });
            return create;
        }

        @Override // io.grpc.InternalWithLogId
        public InternalLogId getLogId() {
            return this.f8328id;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("logId", this.f8328id.getId()).add("channel", this.f8327ch).toString();
        }
    }
}
