package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.CoalescingBufferQueue;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpStatusClass;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttp2ConnectionEncoder implements Http2ConnectionEncoder, Http2SettingsReceivedConsumer {
    private final Http2Connection connection;
    private final Http2FrameWriter frameWriter;
    private Http2LifecycleManager lifecycleManager;
    private final Queue<Http2Settings> outstandingLocalSettingsQueue = new ArrayDeque(4);
    private Queue<Http2Settings> outstandingRemoteSettingsQueue;

    public DefaultHttp2ConnectionEncoder(Http2Connection http2Connection, Http2FrameWriter http2FrameWriter) {
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        this.frameWriter = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "frameWriter");
        if (http2Connection.remote().flowController() == null) {
            http2Connection.remote().flowController(new DefaultHttp2RemoteFlowController(http2Connection));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public void lifecycleManager(Http2LifecycleManager http2LifecycleManager) {
        this.lifecycleManager = (Http2LifecycleManager) ObjectUtil.checkNotNull(http2LifecycleManager, "lifecycleManager");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2FrameWriter frameWriter() {
        return this.frameWriter;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2Connection connection() {
        return this.connection;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public final Http2RemoteFlowController flowController() {
        return connection().remote().flowController();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public void remoteSettings(Http2Settings http2Settings) throws Http2Exception {
        Boolean pushEnabled = http2Settings.pushEnabled();
        Http2FrameWriter.Configuration configuration = configuration();
        Http2HeadersEncoder.Configuration headersConfiguration = configuration.headersConfiguration();
        Http2FrameSizePolicy frameSizePolicy = configuration.frameSizePolicy();
        if (pushEnabled != null) {
            if (!this.connection.isServer() && pushEnabled.booleanValue()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Client received a value of ENABLE_PUSH specified to other than 0", new Object[0]);
            }
            this.connection.remote().allowPushTo(pushEnabled.booleanValue());
        }
        Long maxConcurrentStreams = http2Settings.maxConcurrentStreams();
        if (maxConcurrentStreams != null) {
            this.connection.local().maxActiveStreams((int) Math.min(maxConcurrentStreams.longValue(), 2147483647L));
        }
        if (http2Settings.headerTableSize() != null) {
            headersConfiguration.maxHeaderTableSize((int) Math.min(r0.longValue(), 2147483647L));
        }
        Long maxHeaderListSize = http2Settings.maxHeaderListSize();
        if (maxHeaderListSize != null) {
            headersConfiguration.maxHeaderListSize(maxHeaderListSize.longValue());
        }
        Integer maxFrameSize = http2Settings.maxFrameSize();
        if (maxFrameSize != null) {
            frameSizePolicy.maxFrameSize(maxFrameSize.intValue());
        }
        Integer initialWindowSize = http2Settings.initialWindowSize();
        if (initialWindowSize != null) {
            flowController().initialWindowSize(initialWindowSize.intValue());
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2DataWriter
    public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
        try {
            Http2Stream requireStream = requireStream(i);
            int i3 = C65342.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[requireStream.state().ordinal()];
            if (i3 != 1 && i3 != 2) {
                throw new IllegalStateException("Stream " + requireStream.mo3916id() + " in unexpected state " + requireStream.state());
            }
            flowController().addFlowControlled(requireStream, new FlowControlledData(requireStream, byteBuf, i2, z, channelPromise));
            return channelPromise;
        } catch (Throwable th) {
            byteBuf.release();
            return channelPromise.setFailure(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C65342 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = new int[Http2Stream.State.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.HALF_CLOSED_REMOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.RESERVED_LOCAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        return writeHeaders0(channelHandlerContext, i, http2Headers, false, 0, (short) 0, false, i2, z, channelPromise);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateHeadersSentState(Http2Stream http2Stream, Http2Headers http2Headers, boolean z, boolean z2) {
        boolean z3 = z && HttpStatusClass.valueOf(http2Headers.status()) == HttpStatusClass.INFORMATIONAL;
        if (((!z3 && z2) || !http2Stream.isHeadersSent()) && !http2Stream.isTrailersSent()) {
            return z3;
        }
        throw new IllegalStateException("Stream " + http2Stream.mo3916id() + " sent too many headers EOS: " + z2);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        return writeHeaders0(channelHandlerContext, i, http2Headers, true, i2, s, z, i3, z2, channelPromise);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ChannelFuture sendHeaders(Http2FrameWriter http2FrameWriter, ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, boolean z, int i2, short s, boolean z2, int i3, boolean z3, ChannelPromise channelPromise) {
        if (z) {
            return http2FrameWriter.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z2, i3, z3, channelPromise);
        }
        return http2FrameWriter.writeHeaders(channelHandlerContext, i, http2Headers, i3, z3, channelPromise);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LifecycleManager] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2, types: [io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    private ChannelFuture writeHeaders0(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, boolean z, int i2, short s, boolean z2, int i3, boolean z3, ChannelPromise channelPromise) {
        ?? r3;
        Http2Stream http2Stream;
        ChannelPromise channelPromise2 = channelPromise;
        try {
            Http2Stream stream = this.connection.stream(i);
            if (stream == null) {
                try {
                    stream = this.connection.local().createStream(i, false);
                } catch (Http2Exception e) {
                    if (this.connection.remote().mayHaveCreatedStream(i)) {
                        channelPromise2.tryFailure(new IllegalStateException("Stream no longer exists: " + i, e));
                        return channelPromise2;
                    }
                    throw e;
                }
            } else {
                int i4 = C65342.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[stream.state().ordinal()];
                if (i4 != 1 && i4 != 2) {
                    if (i4 == 3) {
                        stream.open(z3);
                    } else {
                        throw new IllegalStateException("Stream " + stream.mo3916id() + " in unexpected state " + stream.state());
                    }
                }
            }
            Http2Stream http2Stream2 = stream;
            Http2RemoteFlowController flowController = flowController();
            if (z3) {
                try {
                    if (flowController.hasFlowControlled(http2Stream2)) {
                        flowController.addFlowControlled(http2Stream2, new FlowControlledHeaders(http2Stream2, http2Headers, z, i2, s, z2, i3, true, channelPromise));
                        return channelPromise2;
                    }
                } catch (Throwable th) {
                    th = th;
                    r3 = channelHandlerContext;
                    this.lifecycleManager.onError(r3, true, th);
                    channelPromise2.tryFailure(th);
                    return channelPromise2;
                }
            }
            channelPromise2 = channelPromise.unvoid();
            boolean validateHeadersSentState = validateHeadersSentState(http2Stream2, http2Headers, this.connection.isServer(), z3);
            r3 = i;
            ChannelFuture sendHeaders = sendHeaders(this.frameWriter, channelHandlerContext, r3 == true ? 1 : 0, http2Headers, z, i2, s, z2, i3, z3, channelPromise2);
            Throwable cause = sendHeaders.cause();
            try {
                if (cause == null) {
                    http2Stream2.headersSent(validateHeadersSentState);
                    if (sendHeaders.isSuccess()) {
                        r3 = channelHandlerContext;
                        http2Stream = http2Stream2;
                    } else {
                        ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
                        http2Stream = http2Stream2;
                        notifyLifecycleManagerOnError(sendHeaders, channelHandlerContext2);
                        r3 = channelHandlerContext2;
                    }
                } else {
                    ChannelHandlerContext channelHandlerContext3 = channelHandlerContext;
                    http2Stream = http2Stream2;
                    this.lifecycleManager.onError(channelHandlerContext3, true, cause);
                    r3 = channelHandlerContext3;
                }
                if (z3) {
                    this.lifecycleManager.closeStreamLocal(http2Stream, sendHeaders);
                }
                return sendHeaders;
            } catch (Throwable th2) {
                th = th2;
                this.lifecycleManager.onError(r3, true, th);
                channelPromise2.tryFailure(th);
                return channelPromise2;
            }
        } catch (Throwable th3) {
            th = th3;
            r3 = channelHandlerContext;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePriority(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z, ChannelPromise channelPromise) {
        return this.frameWriter.writePriority(channelHandlerContext, i, i2, s, z, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        return this.lifecycleManager.resetStream(channelHandlerContext, i, j, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettings(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings, ChannelPromise channelPromise) {
        this.outstandingLocalSettingsQueue.add(http2Settings);
        try {
            if (http2Settings.pushEnabled() != null && this.connection.isServer()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Server sending SETTINGS frame with ENABLE_PUSH specified", new Object[0]);
            }
            return this.frameWriter.writeSettings(channelHandlerContext, http2Settings, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        Queue<Http2Settings> queue = this.outstandingRemoteSettingsQueue;
        if (queue == null) {
            return this.frameWriter.writeSettingsAck(channelHandlerContext, channelPromise);
        }
        Http2Settings poll = queue.poll();
        if (poll == null) {
            return channelPromise.setFailure((Throwable) new Http2Exception(Http2Error.INTERNAL_ERROR, "attempted to write a SETTINGS ACK with no  pending SETTINGS"));
        }
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        this.frameWriter.writeSettingsAck(channelHandlerContext, simpleChannelPromiseAggregator.newPromise());
        ChannelPromise newPromise = simpleChannelPromiseAggregator.newPromise();
        try {
            remoteSettings(poll);
            newPromise.setSuccess();
        } catch (Throwable th) {
            newPromise.setFailure(th);
            this.lifecycleManager.onError(channelHandlerContext, true, th);
        }
        return simpleChannelPromiseAggregator.doneAllocatingPromises();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        return this.frameWriter.writePing(channelHandlerContext, z, j, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePushPromise(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3, ChannelPromise channelPromise) {
        ChannelPromise channelPromise2;
        try {
            if (this.connection.goAwayReceived()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Sending PUSH_PROMISE after GO_AWAY received.", new Object[0]);
            }
            Http2Stream requireStream = requireStream(i);
            this.connection.local().reservePushStream(i2, requireStream);
            channelPromise2 = channelPromise.unvoid();
            try {
                ChannelFuture writePushPromise = this.frameWriter.writePushPromise(channelHandlerContext, i, i2, http2Headers, i3, channelPromise2);
                Throwable cause = writePushPromise.cause();
                if (cause == null) {
                    requireStream.pushPromiseSent();
                    if (!writePushPromise.isSuccess()) {
                        notifyLifecycleManagerOnError(writePushPromise, channelHandlerContext);
                    }
                } else {
                    this.lifecycleManager.onError(channelHandlerContext, true, cause);
                }
                return writePushPromise;
            } catch (Throwable th) {
                th = th;
                this.lifecycleManager.onError(channelHandlerContext, true, th);
                channelPromise2.tryFailure(th);
                return channelPromise2;
            }
        } catch (Throwable th2) {
            th = th2;
            channelPromise2 = channelPromise;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeGoAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise) {
        return this.lifecycleManager.goAway(channelHandlerContext, i, j, byteBuf, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
        return channelPromise.setFailure((Throwable) new UnsupportedOperationException("Use the Http2[Inbound|Outbound]FlowController objects to control window sizes"));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf, ChannelPromise channelPromise) {
        return this.frameWriter.writeFrame(channelHandlerContext, b, i, http2Flags, byteBuf, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.frameWriter.close();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2Settings pollSentSettings() {
        return this.outstandingLocalSettingsQueue.poll();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public Http2FrameWriter.Configuration configuration() {
        return this.frameWriter.configuration();
    }

    private Http2Stream requireStream(int i) {
        String str;
        Http2Stream stream = this.connection.stream(i);
        if (stream != null) {
            return stream;
        }
        if (this.connection.streamMayHaveExisted(i)) {
            str = "Stream no longer exists: " + i;
        } else {
            str = "Stream does not exist: " + i;
        }
        throw new IllegalArgumentException(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SettingsReceivedConsumer
    public void consumeReceivedSettings(Http2Settings http2Settings) {
        if (this.outstandingRemoteSettingsQueue == null) {
            this.outstandingRemoteSettingsQueue = new ArrayDeque(2);
        }
        this.outstandingRemoteSettingsQueue.add(http2Settings);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class FlowControlledData extends FlowControlledBase {
        private int dataSize;
        private final CoalescingBufferQueue queue;

        FlowControlledData(Http2Stream http2Stream, ByteBuf byteBuf, int i, boolean z, ChannelPromise channelPromise) {
            super(http2Stream, i, z, channelPromise);
            this.queue = new CoalescingBufferQueue(channelPromise.channel());
            this.queue.add(byteBuf, channelPromise);
            this.dataSize = this.queue.readableBytes();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public int size() {
            return this.dataSize + this.padding;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void error(ChannelHandlerContext channelHandlerContext, Throwable th) {
            this.queue.releaseAndFailAll(th);
            DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r12v11, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
        /* JADX WARN: Type inference failed for: r9v0, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void write(ChannelHandlerContext channelHandlerContext, int i) {
            int readableBytes = this.queue.readableBytes();
            boolean z = false;
            if (!this.endOfStream) {
                if (readableBytes == 0) {
                    if (this.queue.isEmpty()) {
                        this.dataSize = 0;
                        this.padding = 0;
                        return;
                    } else {
                        ?? addListener = channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) this);
                        channelHandlerContext.write(this.queue.remove(0, addListener), addListener);
                        return;
                    }
                }
                if (i == 0) {
                    return;
                }
            }
            int min = Math.min(readableBytes, i);
            ?? addListener2 = channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) this);
            ByteBuf remove = this.queue.remove(min, addListener2);
            this.dataSize = this.queue.readableBytes();
            int min2 = Math.min(i - min, this.padding);
            this.padding -= min2;
            Http2FrameWriter frameWriter = DefaultHttp2ConnectionEncoder.this.frameWriter();
            int mo3916id = this.stream.mo3916id();
            if (this.endOfStream && size() == 0) {
                z = true;
            }
            frameWriter.writeData(channelHandlerContext, mo3916id, remove, min2, z, addListener2);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public boolean merge(ChannelHandlerContext channelHandlerContext, Http2RemoteFlowController.FlowControlled flowControlled) {
            if (FlowControlledData.class != flowControlled.getClass()) {
                return false;
            }
            FlowControlledData flowControlledData = (FlowControlledData) flowControlled;
            if (Integer.MAX_VALUE - flowControlledData.size() < size()) {
                return false;
            }
            flowControlledData.queue.copyTo(this.queue);
            this.dataSize = this.queue.readableBytes();
            this.padding = Math.max(this.padding, flowControlledData.padding);
            this.endOfStream = flowControlledData.endOfStream;
            return true;
        }
    }

    private void notifyLifecycleManagerOnError(ChannelFuture channelFuture, final ChannelHandlerContext channelHandlerContext) {
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                Throwable cause = channelFuture2.cause();
                if (cause != null) {
                    DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, cause);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class FlowControlledHeaders extends FlowControlledBase {
        private final boolean exclusive;
        private final boolean hasPriorty;
        private final Http2Headers headers;
        private final int streamDependency;
        private final short weight;

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public boolean merge(ChannelHandlerContext channelHandlerContext, Http2RemoteFlowController.FlowControlled flowControlled) {
            return false;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public int size() {
            return 0;
        }

        FlowControlledHeaders(Http2Stream http2Stream, Http2Headers http2Headers, boolean z, int i, short s, boolean z2, int i2, boolean z3, ChannelPromise channelPromise) {
            super(http2Stream, i2, z3, channelPromise.unvoid());
            this.headers = http2Headers;
            this.hasPriorty = z;
            this.streamDependency = i;
            this.weight = s;
            this.exclusive = z2;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void error(ChannelHandlerContext channelHandlerContext, Throwable th) {
            if (channelHandlerContext != null) {
                DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, th);
            }
            this.promise.tryFailure(th);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void write(ChannelHandlerContext channelHandlerContext, int i) {
            boolean validateHeadersSentState = DefaultHttp2ConnectionEncoder.validateHeadersSentState(this.stream, this.headers, DefaultHttp2ConnectionEncoder.this.connection.isServer(), this.endOfStream);
            this.promise.addListener((GenericFutureListener<? extends Future<? super Void>>) this);
            if (DefaultHttp2ConnectionEncoder.sendHeaders(DefaultHttp2ConnectionEncoder.this.frameWriter, channelHandlerContext, this.stream.mo3916id(), this.headers, this.hasPriorty, this.streamDependency, this.weight, this.exclusive, this.padding, this.endOfStream, this.promise).cause() == null) {
                this.stream.headersSent(validateHeadersSentState);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public abstract class FlowControlledBase implements Http2RemoteFlowController.FlowControlled, ChannelFutureListener {
        protected boolean endOfStream;
        protected int padding;
        protected ChannelPromise promise;
        protected final Http2Stream stream;

        FlowControlledBase(Http2Stream http2Stream, int i, boolean z, ChannelPromise channelPromise) {
            ObjectUtil.checkPositiveOrZero(i, "padding");
            this.padding = i;
            this.endOfStream = z;
            this.stream = http2Stream;
            this.promise = channelPromise;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void writeComplete() {
            if (this.endOfStream) {
                DefaultHttp2ConnectionEncoder.this.lifecycleManager.closeStreamLocal(this.stream, this.promise);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                return;
            }
            error(DefaultHttp2ConnectionEncoder.this.flowController().channelHandlerContext(), channelFuture.cause());
        }
    }
}
