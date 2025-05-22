package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.Attributes;
import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.AbstractNettyHandler;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientStream;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2FrameReader;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Error;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameReader;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2InboundFrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2OutboundFrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Settings;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamBufferingEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.WeightedFairQueueByteDistributor;
import io.grpc.netty.shaded.io.netty.handler.logging.LogLevel;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NettyClientHandler extends AbstractNettyHandler {
    private static final long USER_PING_PAYLOAD = 1111;
    private Status abruptGoAwayStatus;
    private Attributes attributes;
    private final String authority;
    private Status channelInactiveReason;
    private WriteQueue clientWriteQueue;
    private final Attributes eagAttributes;
    private final InUseStateAggregator<Http2Stream> inUseState;
    private final KeepAliveManager keepAliveManager;
    private final ClientTransportLifecycleManager lifecycleManager;
    private Http2Ping ping;
    private InternalChannelz.Security securityInfo;
    private final Supplier<Stopwatch> stopwatchFactory;
    private final Http2Connection.PropertyKey streamKey;
    private final TransportTracer transportTracer;
    private static final Logger logger = Logger.getLogger(NettyClientHandler.class.getName());
    static final Object NOOP_MESSAGE = new Object();
    private static final Status EXHAUSTED_STREAMS_STATUS = Status.UNAVAILABLE.withDescription("Stream IDs have been exhausted");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NettyClientHandler newHandler(ClientTransportLifecycleManager clientTransportLifecycleManager, @Nullable KeepAliveManager keepAliveManager, boolean z, int i, int i2, Supplier<Stopwatch> supplier, Runnable runnable, TransportTracer transportTracer, Attributes attributes, String str, ChannelLogger channelLogger) {
        Preconditions.checkArgument(i2 > 0, "maxHeaderListSize must be positive");
        DefaultHttp2FrameReader defaultHttp2FrameReader = new DefaultHttp2FrameReader(new GrpcHttp2HeadersUtils.GrpcHttp2ClientHeadersDecoder(i2));
        DefaultHttp2FrameWriter defaultHttp2FrameWriter = new DefaultHttp2FrameWriter();
        DefaultHttp2Connection defaultHttp2Connection = new DefaultHttp2Connection(false);
        WeightedFairQueueByteDistributor weightedFairQueueByteDistributor = new WeightedFairQueueByteDistributor(defaultHttp2Connection);
        weightedFairQueueByteDistributor.allocationQuantum(16384);
        defaultHttp2Connection.remote().flowController(new DefaultHttp2RemoteFlowController(defaultHttp2Connection, weightedFairQueueByteDistributor));
        return newHandler(defaultHttp2Connection, defaultHttp2FrameReader, defaultHttp2FrameWriter, clientTransportLifecycleManager, keepAliveManager, z, i, i2, supplier, runnable, transportTracer, attributes, str, channelLogger);
    }

    static NettyClientHandler newHandler(final Http2Connection http2Connection, Http2FrameReader http2FrameReader, Http2FrameWriter http2FrameWriter, ClientTransportLifecycleManager clientTransportLifecycleManager, KeepAliveManager keepAliveManager, boolean z, int i, int i2, Supplier<Stopwatch> supplier, Runnable runnable, TransportTracer transportTracer, Attributes attributes, String str, ChannelLogger channelLogger) {
        Preconditions.checkNotNull(http2Connection, "connection");
        Preconditions.checkNotNull(http2FrameReader, "frameReader");
        Preconditions.checkNotNull(clientTransportLifecycleManager, "lifecycleManager");
        Preconditions.checkArgument(i > 0, "flowControlWindow must be positive");
        Preconditions.checkArgument(i2 > 0, "maxHeaderListSize must be positive");
        Preconditions.checkNotNull(supplier, "stopwatchFactory");
        Preconditions.checkNotNull(runnable, "tooManyPingsRunnable");
        Preconditions.checkNotNull(attributes, "eagAttributes");
        Preconditions.checkNotNull(str, "authority");
        Http2FrameLogger http2FrameLogger = new Http2FrameLogger(LogLevel.DEBUG, (Class<?>) NettyClientHandler.class);
        Http2InboundFrameLogger http2InboundFrameLogger = new Http2InboundFrameLogger(http2FrameReader, http2FrameLogger);
        PingCountingFrameWriter pingCountingFrameWriter = new PingCountingFrameWriter(new Http2OutboundFrameLogger(http2FrameWriter, http2FrameLogger));
        StreamBufferingEncoder streamBufferingEncoder = new StreamBufferingEncoder(new DefaultHttp2ConnectionEncoder(http2Connection, pingCountingFrameWriter));
        http2Connection.local().flowController(new DefaultHttp2LocalFlowController(http2Connection, 0.5f, true));
        DefaultHttp2ConnectionDecoder defaultHttp2ConnectionDecoder = new DefaultHttp2ConnectionDecoder(http2Connection, streamBufferingEncoder, http2InboundFrameLogger);
        transportTracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.2
            final Http2FlowController local;
            final Http2FlowController remote;

            {
                this.local = Http2Connection.this.local().flowController();
                this.remote = Http2Connection.this.remote().flowController();
            }

            @Override // io.grpc.internal.TransportTracer.FlowControlReader
            public TransportTracer.FlowControlWindows read() {
                return new TransportTracer.FlowControlWindows(this.local.windowSize(Http2Connection.this.connectionStream()), this.remote.windowSize(Http2Connection.this.connectionStream()));
            }
        });
        Http2Settings http2Settings = new Http2Settings();
        http2Settings.pushEnabled(false);
        http2Settings.initialWindowSize(i);
        http2Settings.maxConcurrentStreams(0L);
        http2Settings.maxHeaderListSize(i2);
        return new NettyClientHandler(defaultHttp2ConnectionDecoder, streamBufferingEncoder, http2Settings, channelLogger, clientTransportLifecycleManager, keepAliveManager, supplier, runnable, transportTracer, attributes, str, z, pingCountingFrameWriter);
    }

    private NettyClientHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, ChannelLogger channelLogger, ClientTransportLifecycleManager clientTransportLifecycleManager, KeepAliveManager keepAliveManager, Supplier<Stopwatch> supplier, final Runnable runnable, TransportTracer transportTracer, Attributes attributes, String str, boolean z, AbstractNettyHandler.PingLimiter pingLimiter) {
        super(null, http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, channelLogger, z, pingLimiter);
        this.inUseState = new InUseStateAggregator<Http2Stream>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.1
            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleInUse() {
                NettyClientHandler.this.lifecycleManager.notifyInUse(true);
            }

            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleNotInUse() {
                NettyClientHandler.this.lifecycleManager.notifyInUse(false);
            }
        };
        this.lifecycleManager = clientTransportLifecycleManager;
        this.keepAliveManager = keepAliveManager;
        this.stopwatchFactory = supplier;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer);
        this.eagAttributes = attributes;
        this.authority = str;
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, attributes).build();
        decoder().frameListener(new FrameListener());
        Http2Connection connection = http2ConnectionEncoder.connection();
        this.streamKey = connection.newKey();
        connection.addListener(new Http2ConnectionAdapter() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.3
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onGoAwayReceived(int i, long j, ByteBuf byteBuf) {
                byte[] bytes = ByteBufUtil.getBytes(byteBuf);
                NettyClientHandler.this.goingAway(j, bytes);
                if (j == Http2Error.ENHANCE_YOUR_CALM.code()) {
                    String str2 = new String(bytes, CharsetUtil.UTF_8);
                    NettyClientHandler.logger.log(Level.WARNING, "Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: {0}", str2);
                    if ("too_many_pings".equals(str2)) {
                        runnable.run();
                    }
                }
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamActive(Http2Stream http2Stream) {
                if (NettyClientHandler.this.connection().numActiveStreams() != 1 || NettyClientHandler.this.keepAliveManager == null) {
                    return;
                }
                NettyClientHandler.this.keepAliveManager.onTransportActive();
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamClosed(Http2Stream http2Stream) {
                NettyClientHandler.this.inUseState.updateObjectInUse(http2Stream, false);
                if (NettyClientHandler.this.connection().numActiveStreams() != 0 || NettyClientHandler.this.keepAliveManager == null) {
                    return;
                }
                NettyClientHandler.this.keepAliveManager.onTransportIdle();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (obj instanceof CreateStreamCommand) {
            createStream((CreateStreamCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof SendGrpcFrameCommand) {
            sendGrpcFrame(channelHandlerContext, (SendGrpcFrameCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof CancelClientStreamCommand) {
            cancelStream(channelHandlerContext, (CancelClientStreamCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof SendPingCommand) {
            sendPingFrame(channelHandlerContext, (SendPingCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof GracefulCloseCommand) {
            gracefulClose(channelHandlerContext, (GracefulCloseCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof ForcefulCloseCommand) {
            forcefulClose(channelHandlerContext, (ForcefulCloseCommand) obj, channelPromise);
        } else {
            if (obj == NOOP_MESSAGE) {
                channelHandlerContext.write(Unpooled.EMPTY_BUFFER, channelPromise);
                return;
            }
            throw new AssertionError("Write called for unexpected type: " + obj.getClass().getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startWriteQueue(Channel channel) {
        this.clientWriteQueue = new WriteQueue(channel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteQueue getWriteQueue() {
        return this.clientWriteQueue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientTransportLifecycleManager getLifecycleManager() {
        return this.lifecycleManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void returnProcessedBytes(Http2Stream http2Stream, int i) {
        try {
            decoder().flowController().consumeBytes(http2Stream, i);
        } catch (Http2Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHeadersRead(int i, Http2Headers http2Headers, boolean z) {
        if (i != 1) {
            NettyClientStream.TransportState clientStream = clientStream(requireHttp2Stream(i));
            PerfMark.event("NettyClientHandler.onHeadersRead", clientStream.tag());
            clientStream.transportHeadersReceived(http2Headers, z);
        }
        KeepAliveManager keepAliveManager = this.keepAliveManager;
        if (keepAliveManager != null) {
            keepAliveManager.onDataReceived();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataRead(int i, ByteBuf byteBuf, int i2, boolean z) {
        flowControlPing().onDataRead(byteBuf.readableBytes(), i2);
        NettyClientStream.TransportState clientStream = clientStream(requireHttp2Stream(i));
        PerfMark.event("NettyClientHandler.onDataRead", clientStream.tag());
        clientStream.transportDataReceived(byteBuf, z);
        KeepAliveManager keepAliveManager = this.keepAliveManager;
        if (keepAliveManager != null) {
            keepAliveManager.onDataReceived();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRstStreamRead(int i, long j) {
        NettyClientStream.TransportState clientStream = clientStream(connection().stream(i));
        if (clientStream != null) {
            PerfMark.event("NettyClientHandler.onRstStreamRead", clientStream.tag());
            clientStream.transportReportStatus(statusFromH2Error(null, "RST_STREAM closed stream", j, null), j == Http2Error.REFUSED_STREAM.code() ? ClientStreamListener.RpcProgress.REFUSED : ClientStreamListener.RpcProgress.PROCESSED, false, new Metadata());
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onDataReceived();
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        logger.fine("Network channel being closed by the application.");
        if (channelHandlerContext.channel().isActive()) {
            this.lifecycleManager.notifyShutdown(Status.UNAVAILABLE.withDescription("Transport closed for unknown reason"));
        }
        super.close(channelHandlerContext, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        final Status shutdownStatus;
        try {
            logger.fine("Network channel is closed");
            Status withDescription = Status.UNAVAILABLE.withDescription("Network closed for unknown reason");
            this.lifecycleManager.notifyShutdown(withDescription);
            if (this.channelInactiveReason != null) {
                shutdownStatus = this.channelInactiveReason;
            } else {
                shutdownStatus = this.lifecycleManager.getShutdownStatus();
            }
            try {
                cancelPing(this.lifecycleManager.getShutdownThrowable());
                connection().forEachActiveStream(new Http2StreamVisitor() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.4
                    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
                    public boolean visit(Http2Stream http2Stream) throws Http2Exception {
                        NettyClientStream.TransportState clientStream = NettyClientHandler.this.clientStream(http2Stream);
                        if (clientStream == null) {
                            return true;
                        }
                        clientStream.transportReportStatus(shutdownStatus, false, new Metadata());
                        return true;
                    }
                });
            } finally {
                this.lifecycleManager.notifyTerminated(withDescription);
            }
        } finally {
            super.channelInactive(channelHandlerContext);
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onTransportTermination();
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler
    public void handleProtocolNegotiationCompleted(Attributes attributes, InternalChannelz.Security security) {
        this.attributes = this.attributes.toBuilder().setAll(attributes).build();
        this.securityInfo = security;
        super.handleProtocolNegotiationCompleted(attributes, security);
        writeBufferingAndRemove(ctx().channel());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeBufferingAndRemove(Channel channel) {
        ObjectUtil.checkNotNull(channel, "channel");
        ChannelHandlerContext context = channel.pipeline().context(WriteBufferingAndExceptionHandler.class);
        if (context == null) {
            return;
        }
        ((WriteBufferingAndExceptionHandler) context.handler()).writeBufferedAndRemove(context);
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler
    public Attributes getEagAttributes() {
        return this.eagAttributes;
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler
    public String getAuthority() {
        return this.authority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalChannelz.Security getSecurityInfo() {
        return this.securityInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    public void onConnectionError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception http2Exception) {
        logger.log(Level.FINE, "Caught a connection error", th);
        this.lifecycleManager.notifyShutdown(Utils.statusFromThrowable(th));
        super.onConnectionError(channelHandlerContext, z, th, http2Exception);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    public void onStreamError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception.StreamException streamException) {
        NettyClientStream.TransportState clientStream = clientStream(connection().stream(streamException.streamId()));
        if (clientStream != null) {
            clientStream.transportReportStatus(Utils.statusFromThrowable(th), false, new Metadata());
        } else {
            logger.log(Level.FINE, "Stream error for unknown stream " + streamException.streamId(), th);
        }
        super.onStreamError(channelHandlerContext, z, th, streamException);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    public boolean isGracefulShutdownComplete() {
        return super.isGracefulShutdownComplete() && ((StreamBufferingEncoder) encoder()).numBufferedStreams() == 0;
    }

    private void createStream(CreateStreamCommand createStreamCommand, ChannelPromise channelPromise) throws Exception {
        if (this.lifecycleManager.getShutdownThrowable() != null) {
            createStreamCommand.stream().setNonExistent();
            createStreamCommand.stream().transportReportStatus(this.lifecycleManager.getShutdownStatus(), ClientStreamListener.RpcProgress.REFUSED, true, new Metadata());
            channelPromise.setFailure(this.lifecycleManager.getShutdownThrowable());
            return;
        }
        try {
            int incrementAndGetNextStreamId = incrementAndGetNextStreamId();
            if (connection().goAwayReceived()) {
                Status status = this.abruptGoAwayStatus;
                int maxActiveStreams = connection().local().maxActiveStreams();
                int lastStreamKnownByPeer = connection().local().lastStreamKnownByPeer();
                if (status == null) {
                    status = Status.INTERNAL.withDescription("Failed due to abrupt GOAWAY, but can't find GOAWAY details");
                } else if (incrementAndGetNextStreamId > lastStreamKnownByPeer) {
                    status = status.augmentDescription("stream id: " + incrementAndGetNextStreamId + ", GOAWAY Last-Stream-ID:" + lastStreamKnownByPeer);
                } else if (connection().local().numActiveStreams() == maxActiveStreams) {
                    status = status.augmentDescription("At MAX_CONCURRENT_STREAMS limit. limit: " + maxActiveStreams);
                }
                if (incrementAndGetNextStreamId > lastStreamKnownByPeer || connection().local().numActiveStreams() == maxActiveStreams) {
                    createStreamCommand.stream().setNonExistent();
                    createStreamCommand.stream().transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, true, new Metadata());
                    channelPromise.setFailure(status.asRuntimeException());
                    return;
                }
            }
            NettyClientStream.TransportState stream = createStreamCommand.stream();
            Http2Headers headers = createStreamCommand.headers();
            stream.setId(incrementAndGetNextStreamId);
            PerfMark.startTask("NettyClientHandler.createStream", stream.tag());
            PerfMark.linkIn(createStreamCommand.getLink());
            try {
                createStreamTraced(incrementAndGetNextStreamId, stream, headers, createStreamCommand.isGet(), createStreamCommand.shouldBeCountedForInUse(), channelPromise);
            } finally {
                PerfMark.stopTask("NettyClientHandler.createStream", stream.tag());
            }
        } catch (StatusException e) {
            createStreamCommand.stream().setNonExistent();
            channelPromise.setFailure((Throwable) e);
            if (connection().goAwaySent()) {
                return;
            }
            logger.fine("Stream IDs have been exhausted for this connection. Initiating graceful shutdown of the connection.");
            this.lifecycleManager.notifyShutdown(e.getStatus());
            close(ctx(), ctx().newPromise());
        }
    }

    private void createStreamTraced(final int i, final NettyClientStream.TransportState transportState, Http2Headers http2Headers, boolean z, final boolean z2, final ChannelPromise channelPromise) {
        encoder().writeHeaders(ctx(), i, http2Headers, 0, z, ctx().newPromise()).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.5
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    Http2Stream stream = NettyClientHandler.this.connection().stream(i);
                    if (stream != null) {
                        transportState.getStatsTraceContext().clientOutboundHeaders();
                        stream.setProperty(NettyClientHandler.this.streamKey, transportState);
                        if (z2) {
                            NettyClientHandler.this.inUseState.updateObjectInUse(stream, true);
                        }
                        transportState.setHttp2Stream(stream);
                    }
                    channelPromise.setSuccess();
                    return;
                }
                Throwable cause = channelFuture.cause();
                if (cause instanceof StreamBufferingEncoder.Http2GoAwayException) {
                    StreamBufferingEncoder.Http2GoAwayException http2GoAwayException = (StreamBufferingEncoder.Http2GoAwayException) cause;
                    Status statusFromH2Error = NettyClientHandler.this.statusFromH2Error(Status.Code.UNAVAILABLE, "GOAWAY closed buffered stream", http2GoAwayException.errorCode(), http2GoAwayException.debugData());
                    transportState.transportReportStatus(statusFromH2Error, ClientStreamListener.RpcProgress.REFUSED, true, new Metadata());
                    channelPromise.setFailure((Throwable) statusFromH2Error.asRuntimeException());
                    return;
                }
                channelPromise.setFailure(cause);
            }
        });
    }

    private void cancelStream(ChannelHandlerContext channelHandlerContext, CancelClientStreamCommand cancelClientStreamCommand, ChannelPromise channelPromise) {
        NettyClientStream.TransportState stream = cancelClientStreamCommand.stream();
        PerfMark.startTask("NettyClientHandler.cancelStream", stream.tag());
        PerfMark.linkIn(cancelClientStreamCommand.getLink());
        try {
            Status reason = cancelClientStreamCommand.reason();
            if (reason != null) {
                stream.transportReportStatus(reason, true, new Metadata());
            }
            if (!cancelClientStreamCommand.stream().isNonExistent()) {
                encoder().writeRstStream(channelHandlerContext, stream.mo3906id(), Http2Error.CANCEL.code(), channelPromise);
            } else {
                channelPromise.setSuccess();
            }
        } finally {
            PerfMark.stopTask("NettyClientHandler.cancelStream", stream.tag());
        }
    }

    private void sendGrpcFrame(ChannelHandlerContext channelHandlerContext, SendGrpcFrameCommand sendGrpcFrameCommand, ChannelPromise channelPromise) {
        PerfMark.startTask("NettyClientHandler.sendGrpcFrame", sendGrpcFrameCommand.stream().tag());
        PerfMark.linkIn(sendGrpcFrameCommand.getLink());
        try {
            encoder().writeData(channelHandlerContext, sendGrpcFrameCommand.stream().mo3906id(), sendGrpcFrameCommand.content(), 0, sendGrpcFrameCommand.endStream(), channelPromise);
        } finally {
            PerfMark.stopTask("NettyClientHandler.sendGrpcFrame", sendGrpcFrameCommand.stream().tag());
        }
    }

    private void sendPingFrame(ChannelHandlerContext channelHandlerContext, SendPingCommand sendPingCommand, ChannelPromise channelPromise) {
        PerfMark.startTask("NettyClientHandler.sendPingFrame");
        PerfMark.linkIn(sendPingCommand.getLink());
        try {
            sendPingFrameTraced(channelHandlerContext, sendPingCommand, channelPromise);
        } finally {
            PerfMark.stopTask("NettyClientHandler.sendPingFrame");
        }
    }

    private void sendPingFrameTraced(ChannelHandlerContext channelHandlerContext, SendPingCommand sendPingCommand, ChannelPromise channelPromise) {
        ClientTransport.PingCallback callback = sendPingCommand.callback();
        Executor executor = sendPingCommand.executor();
        if (this.ping != null) {
            channelPromise.setSuccess();
            this.ping.addCallback(callback, executor);
            return;
        }
        channelPromise.setSuccess();
        ChannelPromise newPromise = ctx().newPromise();
        Stopwatch stopwatch = this.stopwatchFactory.get();
        stopwatch.start();
        this.ping = new Http2Ping(USER_PING_PAYLOAD, stopwatch);
        this.ping.addCallback(callback, executor);
        encoder().writePing(channelHandlerContext, false, USER_PING_PAYLOAD, newPromise);
        channelHandlerContext.flush();
        final Http2Ping http2Ping = this.ping;
        newPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.6
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    NettyClientHandler.this.transportTracer.reportKeepAliveSent();
                    return;
                }
                Throwable cause = channelFuture.cause();
                if ((cause instanceof ClosedChannelException) && (cause = NettyClientHandler.this.lifecycleManager.getShutdownThrowable()) == null) {
                    cause = Status.UNKNOWN.withDescription("Ping failed but for unknown reason.").withCause(channelFuture.cause()).asException();
                }
                http2Ping.failed(cause);
                if (NettyClientHandler.this.ping == http2Ping) {
                    NettyClientHandler.this.ping = null;
                }
            }
        });
    }

    private void gracefulClose(ChannelHandlerContext channelHandlerContext, GracefulCloseCommand gracefulCloseCommand, ChannelPromise channelPromise) throws Exception {
        this.lifecycleManager.notifyShutdown(gracefulCloseCommand.getStatus());
        flush(channelHandlerContext);
        close(channelHandlerContext, channelPromise);
    }

    private void forcefulClose(final ChannelHandlerContext channelHandlerContext, final ForcefulCloseCommand forcefulCloseCommand, ChannelPromise channelPromise) throws Exception {
        connection().forEachActiveStream(new Http2StreamVisitor() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.7
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
            public boolean visit(Http2Stream http2Stream) throws Http2Exception {
                NettyClientStream.TransportState clientStream = NettyClientHandler.this.clientStream(http2Stream);
                Tag tag = clientStream != null ? clientStream.tag() : PerfMark.createTag();
                PerfMark.startTask("NettyClientHandler.forcefulClose", tag);
                PerfMark.linkIn(forcefulCloseCommand.getLink());
                if (clientStream != null) {
                    try {
                        clientStream.transportReportStatus(forcefulCloseCommand.getStatus(), true, new Metadata());
                        NettyClientHandler.this.resetStream(channelHandlerContext, http2Stream.mo3916id(), Http2Error.CANCEL.code(), channelHandlerContext.newPromise());
                    } finally {
                        PerfMark.stopTask("NettyClientHandler.forcefulClose", tag);
                    }
                }
                http2Stream.close();
                return true;
            }
        });
        close(channelHandlerContext, channelPromise);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goingAway(long j, byte[] bArr) {
        Status statusFromH2Error = statusFromH2Error(Status.Code.UNAVAILABLE, "GOAWAY shut down transport", j, bArr);
        this.lifecycleManager.notifyGracefulShutdown(statusFromH2Error);
        this.abruptGoAwayStatus = statusFromH2Error(Status.Code.UNAVAILABLE, "Abrupt GOAWAY closed unsent stream", j, bArr);
        final Status statusFromH2Error2 = statusFromH2Error(null, "Abrupt GOAWAY closed sent stream", j, bArr);
        this.clientWriteQueue.drainNow();
        if (this.lifecycleManager.notifyShutdown(statusFromH2Error)) {
            this.channelInactiveReason = statusFromH2Error(null, "Connection closed after GOAWAY", j, bArr);
        }
        final int lastStreamKnownByPeer = connection().local().lastStreamKnownByPeer();
        try {
            connection().forEachActiveStream(new Http2StreamVisitor() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler.8
                @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
                public boolean visit(Http2Stream http2Stream) throws Http2Exception {
                    if (http2Stream.mo3916id() <= lastStreamKnownByPeer) {
                        return true;
                    }
                    NettyClientStream.TransportState clientStream = NettyClientHandler.this.clientStream(http2Stream);
                    if (clientStream != null) {
                        clientStream.transportReportStatus(statusFromH2Error2, ClientStreamListener.RpcProgress.PROCESSED, false, new Metadata());
                    }
                    http2Stream.close();
                    return true;
                }
            });
        } catch (Http2Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void cancelPing(Throwable th) {
        Http2Ping http2Ping = this.ping;
        if (http2Ping != null) {
            http2Ping.failed(th);
            this.ping = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Status statusFromH2Error(Status.Code code, String str, long j, byte[] bArr) {
        String str2;
        Status statusForCode = GrpcUtil.Http2Error.statusForCode((int) j);
        if (code == null) {
            code = statusForCode.getCode();
        }
        if (bArr == null || bArr.length <= 0) {
            str2 = "";
        } else {
            str2 = ", debug data: " + new String(bArr, CharsetUtil.UTF_8);
        }
        return code.toStatus().withDescription(str + ". " + statusForCode.getDescription() + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NettyClientStream.TransportState clientStream(Http2Stream http2Stream) {
        if (http2Stream == null) {
            return null;
        }
        return (NettyClientStream.TransportState) http2Stream.getProperty(this.streamKey);
    }

    private int incrementAndGetNextStreamId() throws StatusException {
        int incrementAndGetNextStreamId = connection().local().incrementAndGetNextStreamId();
        if (incrementAndGetNextStreamId >= 0) {
            return incrementAndGetNextStreamId;
        }
        logger.fine("Stream IDs have been exhausted for this connection. Initiating graceful shutdown of the connection.");
        throw EXHAUSTED_STREAMS_STATUS.asException();
    }

    private Http2Stream requireHttp2Stream(int i) {
        Http2Stream stream = connection().stream(i);
        if (stream != null) {
            return stream;
        }
        throw new AssertionError("Stream does not exist: " + i);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class FrameListener extends Http2FrameAdapter {
        private boolean firstSettings;

        private FrameListener() {
            this.firstSettings = true;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) {
            if (this.firstSettings) {
                this.firstSettings = false;
                NettyClientHandler.this.lifecycleManager.notifyReady();
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
            NettyClientHandler.this.onDataRead(i, byteBuf, i2, z);
            return i2;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) throws Http2Exception {
            NettyClientHandler.this.onHeadersRead(i, http2Headers, z2);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) throws Http2Exception {
            NettyClientHandler.this.onRstStreamRead(i, j);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            Http2Ping http2Ping = NettyClientHandler.this.ping;
            if (j == NettyClientHandler.this.flowControlPing().payload()) {
                NettyClientHandler.this.flowControlPing().updateWindow();
                if (NettyClientHandler.logger.isLoggable(Level.FINE)) {
                    NettyClientHandler.logger.log(Level.FINE, String.format("Window: %d", Integer.valueOf(NettyClientHandler.this.decoder().flowController().initialWindowSize(NettyClientHandler.this.connection().connectionStream()))));
                }
            } else if (http2Ping == null) {
                NettyClientHandler.logger.warning("Received unexpected ping ack. No ping outstanding");
            } else if (http2Ping.payload() == j) {
                http2Ping.complete();
                NettyClientHandler.this.ping = null;
            } else {
                NettyClientHandler.logger.log(Level.WARNING, String.format("Received unexpected ping ack. Expecting %d, got %d", Long.valueOf(http2Ping.payload()), Long.valueOf(j)));
            }
            if (NettyClientHandler.this.keepAliveManager != null) {
                NettyClientHandler.this.keepAliveManager.onDataReceived();
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            if (NettyClientHandler.this.keepAliveManager != null) {
                NettyClientHandler.this.keepAliveManager.onDataReceived();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class PingCountingFrameWriter extends DecoratingHttp2FrameWriter implements AbstractNettyHandler.PingLimiter {
        private int pingCount;

        public PingCountingFrameWriter(Http2FrameWriter http2FrameWriter) {
            super(http2FrameWriter);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractNettyHandler.PingLimiter
        public boolean isPingAllowed() {
            return this.pingCount < 2;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
        public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
            this.pingCount = 0;
            return super.writeHeaders(channelHandlerContext, i, http2Headers, i2, z, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
        public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
            this.pingCount = 0;
            return super.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
        public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
            this.pingCount = 0;
            return super.writeWindowUpdate(channelHandlerContext, i, i2, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
        public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
            if (!z) {
                this.pingCount++;
            }
            return super.writePing(channelHandlerContext, z, j, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2DataWriter
        public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
            if (byteBuf.isReadable()) {
                this.pingCount = 0;
            }
            return super.writeData(channelHandlerContext, i, byteBuf, i2, z, channelPromise);
        }
    }
}
