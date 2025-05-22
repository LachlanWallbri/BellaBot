package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.pudutech.mirsdk.compat.topo.MapElement;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.FailingClientStream;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientStream;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFactory;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamBufferingEncoder;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NettyClientTransport implements ConnectionClientTransport {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final AsciiString authority;
    private final String authorityString;
    private final boolean autoFlowControl;
    private Channel channel;
    private final ChannelFactory<? extends Channel> channelFactory;
    private final ChannelLogger channelLogger;
    private final Map<ChannelOption<?>, ?> channelOptions;
    private final Attributes eagAttributes;
    private final int flowControlWindow;
    private final EventLoopGroup group;
    private NettyClientHandler handler;
    private KeepAliveManager keepAliveManager;
    private final long keepAliveTimeNanos;
    private final long keepAliveTimeoutNanos;
    private final boolean keepAliveWithoutCalls;
    private ClientTransportLifecycleManager lifecycleManager;
    private final NettyChannelBuilder.LocalSocketPicker localSocketPicker;
    private final InternalLogId logId;
    private final int maxHeaderListSize;
    private final int maxMessageSize;
    private final AsciiString negotiationScheme;
    private final ProtocolNegotiator negotiator;
    private final SocketAddress remoteAddress;
    private Status statusExplainingWhyTheChannelIsNull;
    private final Runnable tooManyPingsRunnable;
    private final TransportTracer transportTracer;
    private final boolean useGetForSafeMethods;
    private final AsciiString userAgent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyClientTransport(SocketAddress socketAddress, ChannelFactory<? extends Channel> channelFactory, Map<ChannelOption<?>, ?> map, EventLoopGroup eventLoopGroup, ProtocolNegotiator protocolNegotiator, boolean z, int i, int i2, int i3, long j, long j2, boolean z2, String str, @Nullable String str2, Runnable runnable, TransportTracer transportTracer, Attributes attributes, NettyChannelBuilder.LocalSocketPicker localSocketPicker, ChannelLogger channelLogger, boolean z3) {
        this.negotiator = (ProtocolNegotiator) Preconditions.checkNotNull(protocolNegotiator, "negotiator");
        this.negotiationScheme = this.negotiator.scheme();
        this.remoteAddress = (SocketAddress) Preconditions.checkNotNull(socketAddress, "address");
        this.group = (EventLoopGroup) Preconditions.checkNotNull(eventLoopGroup, MapElement.Key.GROUP);
        this.channelFactory = channelFactory;
        this.channelOptions = (Map) Preconditions.checkNotNull(map, "channelOptions");
        this.autoFlowControl = z;
        this.flowControlWindow = i;
        this.maxMessageSize = i2;
        this.maxHeaderListSize = i3;
        this.keepAliveTimeNanos = j;
        this.keepAliveTimeoutNanos = j2;
        this.keepAliveWithoutCalls = z2;
        this.authorityString = str;
        this.authority = new AsciiString(str);
        this.userAgent = new AsciiString(GrpcUtil.getGrpcUserAgent("netty", str2));
        this.tooManyPingsRunnable = (Runnable) Preconditions.checkNotNull(runnable, "tooManyPingsRunnable");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
        this.eagAttributes = (Attributes) Preconditions.checkNotNull(attributes, "eagAttributes");
        this.localSocketPicker = (NettyChannelBuilder.LocalSocketPicker) Preconditions.checkNotNull(localSocketPicker, "localSocketPicker");
        this.logId = InternalLogId.allocate(getClass(), this.remoteAddress.toString());
        this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger, "channelLogger");
        this.useGetForSafeMethods = z3;
    }

    @Override // io.grpc.internal.ClientTransport
    public void ping(final ClientTransport.PingCallback pingCallback, final Executor executor) {
        if (this.channel == null) {
            executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.1
                @Override // java.lang.Runnable
                public void run() {
                    pingCallback.onFailure(NettyClientTransport.this.statusExplainingWhyTheChannelIsNull.asException());
                }
            });
        } else {
            this.handler.getWriteQueue().enqueue((WriteQueue.QueuedCommand) new SendPingCommand(pingCallback, executor), true).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.2
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        return;
                    }
                    Http2Ping.notifyFailed(pingCallback, executor, NettyClientTransport.this.statusFromFailedFuture(channelFuture).asException());
                }
            });
        }
    }

    @Override // io.grpc.internal.ClientTransport
    public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        Preconditions.checkNotNull(methodDescriptor, "method");
        Preconditions.checkNotNull(metadata, "headers");
        if (this.channel == null) {
            return new FailingClientStream(this.statusExplainingWhyTheChannelIsNull);
        }
        StatsTraceContext newClientContext = StatsTraceContext.newClientContext(callOptions, getAttributes(), metadata);
        return new NettyClientStream(new NettyClientStream.TransportState(this.handler, this.channel.eventLoop(), this.maxMessageSize, newClientContext, this.transportTracer, methodDescriptor.getFullMethodName()) { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.3
            @Override // io.grpc.netty.shaded.io.grpc.netty.NettyClientStream.TransportState
            protected Status statusFromFailedFuture(ChannelFuture channelFuture) {
                return NettyClientTransport.this.statusFromFailedFuture(channelFuture);
            }
        }, methodDescriptor, metadata, this.channel, this.authority, this.negotiationScheme, this.userAgent, newClientContext, this.transportTracer, callOptions, this.useGetForSafeMethods);
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public Runnable start(ManagedClientTransport.Listener listener) {
        ChannelOption<Integer> maybeGetTcpUserTimeoutOption;
        this.lifecycleManager = new ClientTransportLifecycleManager((ManagedClientTransport.Listener) Preconditions.checkNotNull(listener, "listener"));
        EventLoop next = this.group.next();
        if (this.keepAliveTimeNanos != Long.MAX_VALUE) {
            this.keepAliveManager = new KeepAliveManager(new KeepAliveManager.ClientKeepAlivePinger(this), next, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
        }
        this.handler = NettyClientHandler.newHandler(this.lifecycleManager, this.keepAliveManager, this.autoFlowControl, this.flowControlWindow, this.maxHeaderListSize, GrpcUtil.STOPWATCH_SUPPLIER, this.tooManyPingsRunnable, this.transportTracer, this.eagAttributes, this.authorityString, this.channelLogger);
        ChannelHandler newHandler = this.negotiator.newHandler(this.handler);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.option(ChannelOption.ALLOCATOR, Utils.getByteBufAllocator(false));
        bootstrap.group(next);
        bootstrap.channelFactory((ChannelFactory) this.channelFactory);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        if (this.keepAliveTimeNanos != Long.MAX_VALUE && (maybeGetTcpUserTimeoutOption = Utils.maybeGetTcpUserTimeoutOption()) != null) {
            bootstrap.option(maybeGetTcpUserTimeoutOption, Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(this.keepAliveTimeoutNanos)));
        }
        for (Map.Entry<ChannelOption<?>, ?> entry : this.channelOptions.entrySet()) {
            bootstrap.option(entry.getKey(), entry.getValue());
        }
        bootstrap.handler(new WriteBufferingAndExceptionHandler(newHandler));
        ChannelFuture register = bootstrap.register();
        if (register.isDone() && !register.isSuccess()) {
            this.channel = null;
            Throwable cause = register.cause();
            if (cause == null) {
                cause = new IllegalStateException("Channel is null, but future doesn't have a cause");
            }
            this.statusExplainingWhyTheChannelIsNull = Utils.statusFromThrowable(cause);
            return new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.4
                @Override // java.lang.Runnable
                public void run() {
                    NettyClientTransport.this.lifecycleManager.notifyTerminated(NettyClientTransport.this.statusExplainingWhyTheChannelIsNull);
                }
            };
        }
        this.channel = register.channel();
        this.handler.startWriteQueue(this.channel);
        this.channel.writeAndFlush(NettyClientHandler.NOOP_MESSAGE).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.5
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    return;
                }
                NettyClientTransport.this.lifecycleManager.notifyTerminated(Utils.statusFromThrowable(channelFuture.cause()));
            }
        });
        SocketAddress createSocketAddress = this.localSocketPicker.createSocketAddress(this.remoteAddress, this.eagAttributes);
        if (createSocketAddress != null) {
            this.channel.connect(this.remoteAddress, createSocketAddress);
        } else {
            this.channel.connect(this.remoteAddress);
        }
        KeepAliveManager keepAliveManager = this.keepAliveManager;
        if (keepAliveManager != null) {
            keepAliveManager.onTransportStarted();
        }
        return null;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public void shutdown(Status status) {
        Channel channel = this.channel;
        if (channel != null && channel.isOpen()) {
            this.handler.getWriteQueue().enqueue((WriteQueue.QueuedCommand) new GracefulCloseCommand(status), true);
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public void shutdownNow(final Status status) {
        Channel channel = this.channel;
        if (channel == null || !channel.isOpen()) {
            return;
        }
        this.handler.getWriteQueue().enqueue(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.6
            @Override // java.lang.Runnable
            public void run() {
                NettyClientTransport.this.lifecycleManager.notifyShutdown(status);
                NettyClientTransport.this.channel.write(new ForcefulCloseCommand(status));
            }
        }, true);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("remoteAddress", this.remoteAddress).add("channel", this.channel).toString();
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.internal.ConnectionClientTransport
    public Attributes getAttributes() {
        return this.handler.getAttributes();
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        if (this.channel.eventLoop().inEventLoop()) {
            create.set(getStatsHelper(this.channel));
            return create;
        }
        this.channel.eventLoop().submit(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.8
            @Override // java.lang.Runnable
            public void run() {
                SettableFuture settableFuture = create;
                NettyClientTransport nettyClientTransport = NettyClientTransport.this;
                settableFuture.set(nettyClientTransport.getStatsHelper(nettyClientTransport.channel));
            }
        }).addListener(new GenericFutureListener<Future<Object>>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport.7
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

    /* JADX INFO: Access modifiers changed from: private */
    public InternalChannelz.SocketStats getStatsHelper(Channel channel) {
        InternalChannelz.TransportStats stats = this.transportTracer.getStats();
        SocketAddress localAddress = this.channel.localAddress();
        SocketAddress remoteAddress = this.channel.remoteAddress();
        InternalChannelz.SocketOptions socketOptions = Utils.getSocketOptions(channel);
        NettyClientHandler nettyClientHandler = this.handler;
        return new InternalChannelz.SocketStats(stats, localAddress, remoteAddress, socketOptions, nettyClientHandler == null ? null : nettyClientHandler.getSecurityInfo());
    }

    Channel channel() {
        return this.channel;
    }

    KeepAliveManager keepAliveManager() {
        return this.keepAliveManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Status statusFromFailedFuture(ChannelFuture channelFuture) {
        Throwable cause = channelFuture.cause();
        if ((cause instanceof ClosedChannelException) || (cause instanceof StreamBufferingEncoder.Http2ChannelClosedException)) {
            Status shutdownStatus = this.lifecycleManager.getShutdownStatus();
            return shutdownStatus == null ? Status.UNKNOWN.withDescription("Channel closed but for unknown reason").withCause(new ClosedChannelException().initCause(cause)) : shutdownStatus;
        }
        return Utils.statusFromThrowable(cause);
    }
}
