package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NettyServerTransport implements ServerTransport {
    private final boolean autoFlowControl;
    private final Channel channel;
    private final ChannelPromise channelUnused;
    private final Attributes eagAttributes;
    private final int flowControlWindow;
    private NettyServerHandler grpcHandler;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private ServerTransportListener listener;
    private final InternalLogId logId;
    private final long maxConnectionAgeGraceInNanos;
    private final long maxConnectionAgeInNanos;
    private final long maxConnectionIdleInNanos;
    private final int maxHeaderListSize;
    private final int maxMessageSize;
    private final int maxStreams;
    private final long permitKeepAliveTimeInNanos;
    private final boolean permitKeepAliveWithoutCalls;
    private final ProtocolNegotiator protocolNegotiator;
    private final List<? extends ServerStreamTracer.Factory> streamTracerFactories;
    private boolean terminated;
    private final TransportTracer transportTracer;
    private static final Logger connectionLog = Logger.getLogger(String.format("%s.connections", NettyServerTransport.class.getName()));
    private static final ImmutableList<String> QUIET_EXCEPTIONS = ImmutableList.m635of("NativeIoException");

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyServerTransport(Channel channel, ChannelPromise channelPromise, ProtocolNegotiator protocolNegotiator, List<? extends ServerStreamTracer.Factory> list, TransportTracer transportTracer, int i, boolean z, int i2, int i3, int i4, long j, long j2, long j3, long j4, long j5, boolean z2, long j6, Attributes attributes) {
        this.channel = (Channel) Preconditions.checkNotNull(channel, "channel");
        this.channelUnused = channelPromise;
        this.protocolNegotiator = (ProtocolNegotiator) Preconditions.checkNotNull(protocolNegotiator, "protocolNegotiator");
        this.streamTracerFactories = (List) Preconditions.checkNotNull(list, "streamTracerFactories");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
        this.maxStreams = i;
        this.autoFlowControl = z;
        this.flowControlWindow = i2;
        this.maxMessageSize = i3;
        this.maxHeaderListSize = i4;
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        this.maxConnectionIdleInNanos = j3;
        this.maxConnectionAgeInNanos = j4;
        this.maxConnectionAgeGraceInNanos = j5;
        this.permitKeepAliveWithoutCalls = z2;
        this.permitKeepAliveTimeInNanos = j6;
        this.eagAttributes = (Attributes) Preconditions.checkNotNull(attributes, "eagAttributes");
        SocketAddress remoteAddress = channel.remoteAddress();
        this.logId = InternalLogId.allocate(getClass(), remoteAddress != null ? remoteAddress.toString() : null);
    }

    public void start(ServerTransportListener serverTransportListener) {
        Preconditions.checkState(this.listener == null, "Handler already registered");
        this.listener = serverTransportListener;
        this.grpcHandler = createHandler(serverTransportListener, this.channelUnused);
        WriteBufferingAndExceptionHandler writeBufferingAndExceptionHandler = new WriteBufferingAndExceptionHandler(this.protocolNegotiator.newHandler(this.grpcHandler));
        ChannelFutureListener channelFutureListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerTransport.1TerminationNotifier
            boolean done;

            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (this.done) {
                    return;
                }
                this.done = true;
                NettyServerTransport nettyServerTransport = NettyServerTransport.this;
                nettyServerTransport.notifyTerminated(nettyServerTransport.grpcHandler.connectionError());
            }
        };
        this.channelUnused.addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
        this.channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
        this.channel.pipeline().addLast(writeBufferingAndExceptionHandler);
    }

    @Override // io.grpc.internal.ServerTransport
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.channel.eventLoop();
    }

    @Override // io.grpc.internal.ServerTransport
    public void shutdown() {
        if (this.channel.isOpen()) {
            this.channel.close();
        }
    }

    @Override // io.grpc.internal.ServerTransport, io.grpc.internal.ManagedClientTransport
    public void shutdownNow(Status status) {
        if (this.channel.isOpen()) {
            this.channel.writeAndFlush(new ForcefulCloseCommand(status));
        }
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    Channel channel() {
        return this.channel;
    }

    static Level getLogLevel(Throwable th) {
        if (th.getClass().equals(IOException.class) || th.getClass().equals(SocketException.class) || QUIET_EXCEPTIONS.contains(th.getClass().getSimpleName())) {
            return Level.FINE;
        }
        return Level.INFO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTerminated(Throwable th) {
        if (th != null) {
            connectionLog.log(getLogLevel(th), "Transport failed", th);
        }
        if (this.terminated) {
            return;
        }
        this.terminated = true;
        this.listener.transportTerminated();
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        if (this.channel.eventLoop().inEventLoop()) {
            create.set(getStatsHelper(this.channel));
            return create;
        }
        this.channel.eventLoop().submit(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerTransport.2
            @Override // java.lang.Runnable
            public void run() {
                SettableFuture settableFuture = create;
                NettyServerTransport nettyServerTransport = NettyServerTransport.this;
                settableFuture.set(nettyServerTransport.getStatsHelper(nettyServerTransport.channel));
            }
        }).addListener(new GenericFutureListener<Future<Object>>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerTransport.1
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
        Preconditions.checkState(channel.eventLoop().inEventLoop());
        InternalChannelz.TransportStats stats = this.transportTracer.getStats();
        SocketAddress localAddress = this.channel.localAddress();
        SocketAddress remoteAddress = this.channel.remoteAddress();
        InternalChannelz.SocketOptions socketOptions = Utils.getSocketOptions(channel);
        NettyServerHandler nettyServerHandler = this.grpcHandler;
        return new InternalChannelz.SocketStats(stats, localAddress, remoteAddress, socketOptions, nettyServerHandler == null ? null : nettyServerHandler.getSecurityInfo());
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("channel", this.channel).toString();
    }

    private NettyServerHandler createHandler(ServerTransportListener serverTransportListener, ChannelPromise channelPromise) {
        return NettyServerHandler.newHandler(serverTransportListener, channelPromise, this.streamTracerFactories, this.transportTracer, this.maxStreams, this.autoFlowControl, this.flowControlWindow, this.maxHeaderListSize, this.maxMessageSize, this.keepAliveTimeInNanos, this.keepAliveTimeoutInNanos, this.maxConnectionIdleInNanos, this.maxConnectionAgeInNanos, this.maxConnectionAgeGraceInNanos, this.permitKeepAliveWithoutCalls, this.permitKeepAliveTimeInNanos, this.eagAttributes);
    }
}
