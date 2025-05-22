package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.CoalescingBufferQueue;
import io.netty.handler.codec.http.HttpStatusClass;
import io.netty.handler.codec.http2.Http2FrameWriter;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayDeque;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultHttp2ConnectionEncoder implements Http2ConnectionEncoder {
    private final Http2Connection connection;
    private final Http2FrameWriter frameWriter;
    private Http2LifecycleManager lifecycleManager;
    private final ArrayDeque<Http2Settings> outstandingLocalSettingsQueue = new ArrayDeque<>(4);

    public DefaultHttp2ConnectionEncoder(Http2Connection http2Connection, Http2FrameWriter http2FrameWriter) {
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        this.frameWriter = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "frameWriter");
        if (http2Connection.remote().flowController() == null) {
            http2Connection.remote().flowController(new DefaultHttp2RemoteFlowController(http2Connection));
        }
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionEncoder
    public void lifecycleManager(Http2LifecycleManager http2LifecycleManager) {
        this.lifecycleManager = (Http2LifecycleManager) ObjectUtil.checkNotNull(http2LifecycleManager, "lifecycleManager");
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2FrameWriter frameWriter() {
        return this.frameWriter;
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2Connection connection() {
        return this.connection;
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionEncoder
    public final Http2RemoteFlowController flowController() {
        return connection().remote().flowController();
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionEncoder
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

    @Override // io.netty.handler.codec.http2.Http2DataWriter
    public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
        try {
            Http2Stream requireStream = requireStream(i);
            int i3 = C71973.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[requireStream.state().ordinal()];
            if (i3 != 1 && i3 != 2) {
                throw new IllegalStateException("Stream " + requireStream.mo3940id() + " in unexpected state " + requireStream.state());
            }
            flowController().addFlowControlled(requireStream, new FlowControlledData(requireStream, byteBuf, i2, z, channelPromise));
            return channelPromise;
        } catch (Throwable th) {
            byteBuf.release();
            return channelPromise.setFailure(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder$3 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C71973 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        static {
            int[] iArr = new int[Http2Stream.State.values().length];
            $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = iArr;
            try {
                iArr[Http2Stream.State.OPEN.ordinal()] = 1;
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

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        return writeHeaders(channelHandlerContext, i, http2Headers, 0, (short) 16, false, i2, z, channelPromise);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateHeadersSentState(Http2Stream http2Stream, Http2Headers http2Headers, boolean z, boolean z2) {
        boolean z3 = z && HttpStatusClass.valueOf(http2Headers.status()) == HttpStatusClass.INFORMATIONAL;
        if (((!z3 && z2) || !http2Stream.isHeadersSent()) && !http2Stream.isTrailersSent()) {
            return z3;
        }
        throw new IllegalStateException("Stream " + http2Stream.mo3940id() + " sent too many headers EOS: " + z2);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        ChannelPromise channelPromise2 = channelPromise;
        try {
            Http2Stream stream = this.connection.stream(i);
            if (stream == null) {
                try {
                    stream = this.connection.local().createStream(i, z2);
                } catch (Http2Exception e) {
                    if (this.connection.remote().mayHaveCreatedStream(i)) {
                        channelPromise2.tryFailure(new IllegalStateException("Stream no longer exists: " + i, e));
                        return channelPromise2;
                    }
                    throw e;
                }
            } else {
                int i4 = C71973.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[stream.state().ordinal()];
                if (i4 != 1 && i4 != 2) {
                    if (i4 == 3) {
                        stream.open(z2);
                    } else {
                        throw new IllegalStateException("Stream " + stream.mo3940id() + " in unexpected state " + stream.state());
                    }
                }
            }
            final Http2Stream http2Stream = stream;
            Http2RemoteFlowController flowController = flowController();
            if (z2 && flowController.hasFlowControlled(http2Stream)) {
                flowController.addFlowControlled(http2Stream, new FlowControlledHeaders(http2Stream, http2Headers, i2, s, z, i3, true, channelPromise));
                return channelPromise2;
            }
            boolean validateHeadersSentState = validateHeadersSentState(http2Stream, http2Headers, this.connection.isServer(), z2);
            if (z2) {
                channelPromise2 = channelPromise.unvoid().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder.1
                    @Override // io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        DefaultHttp2ConnectionEncoder.this.lifecycleManager.closeStreamLocal(http2Stream, channelFuture);
                    }
                });
            }
            ChannelFuture writeHeaders = this.frameWriter.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2, channelPromise2);
            Throwable cause = writeHeaders.cause();
            if (cause == null) {
                http2Stream.headersSent(validateHeadersSentState);
                if (!writeHeaders.isSuccess()) {
                    notifyLifecycleManagerOnError(writeHeaders, channelHandlerContext);
                }
            } else {
                this.lifecycleManager.onError(channelHandlerContext, true, cause);
            }
            return writeHeaders;
        } catch (Throwable th) {
            this.lifecycleManager.onError(channelHandlerContext, true, th);
            channelPromise2.tryFailure(th);
            return channelPromise2;
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePriority(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z, ChannelPromise channelPromise) {
        return this.frameWriter.writePriority(channelHandlerContext, i, i2, s, z, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        return this.lifecycleManager.resetStream(channelHandlerContext, i, j, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
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

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        return this.frameWriter.writeSettingsAck(channelHandlerContext, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        return this.frameWriter.writePing(channelHandlerContext, z, j, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePushPromise(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3, ChannelPromise channelPromise) {
        try {
            if (this.connection.goAwayReceived()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Sending PUSH_PROMISE after GO_AWAY received.", new Object[0]);
            }
            Http2Stream requireStream = requireStream(i);
            this.connection.local().reservePushStream(i2, requireStream);
            ChannelFuture writePushPromise = this.frameWriter.writePushPromise(channelHandlerContext, i, i2, http2Headers, i3, channelPromise);
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
            this.lifecycleManager.onError(channelHandlerContext, true, th);
            channelPromise.tryFailure(th);
            return channelPromise;
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeGoAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise) {
        return this.lifecycleManager.goAway(channelHandlerContext, i, j, byteBuf, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
        return channelPromise.setFailure((Throwable) new UnsupportedOperationException("Use the Http2[Inbound|Outbound]FlowController objects to control window sizes"));
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionEncoder, io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf, ChannelPromise channelPromise) {
        return this.frameWriter.writeFrame(channelHandlerContext, b, i, http2Flags, byteBuf, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.frameWriter.close();
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2Settings pollSentSettings() {
        return this.outstandingLocalSettingsQueue.poll();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
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

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class FlowControlledData extends FlowControlledBase {
        private int dataSize;
        private final CoalescingBufferQueue queue;

        FlowControlledData(Http2Stream http2Stream, ByteBuf byteBuf, int i, boolean z, ChannelPromise channelPromise) {
            super(http2Stream, i, z, channelPromise);
            CoalescingBufferQueue coalescingBufferQueue = new CoalescingBufferQueue(channelPromise.channel());
            this.queue = coalescingBufferQueue;
            coalescingBufferQueue.add(byteBuf, channelPromise);
            this.dataSize = this.queue.readableBytes();
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public int size() {
            return this.dataSize + this.padding;
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void error(ChannelHandlerContext channelHandlerContext, Throwable th) {
            this.queue.releaseAndFailAll(th);
            DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r12v9, types: [io.netty.channel.ChannelPromise] */
        /* JADX WARN: Type inference failed for: r9v0, types: [io.netty.channel.ChannelPromise] */
        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void write(ChannelHandlerContext channelHandlerContext, int i) {
            int readableBytes = this.queue.readableBytes();
            boolean z = false;
            if (!this.endOfStream) {
                if (readableBytes == 0) {
                    ?? addListener = channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) this);
                    channelHandlerContext.write(this.queue.remove(0, addListener), addListener);
                    return;
                } else if (i == 0) {
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
            int mo3940id = this.stream.mo3940id();
            if (this.endOfStream && size() == 0) {
                z = true;
            }
            frameWriter.writeData(channelHandlerContext, mo3940id, remove, min2, z, addListener2);
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
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
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder.2
            @Override // io.netty.util.concurrent.GenericFutureListener
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
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public final class FlowControlledHeaders extends FlowControlledBase {
        private final boolean exclusive;
        private final Http2Headers headers;
        private final int streamDependency;
        private final short weight;

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public boolean merge(ChannelHandlerContext channelHandlerContext, Http2RemoteFlowController.FlowControlled flowControlled) {
            return false;
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public int size() {
            return 0;
        }

        FlowControlledHeaders(Http2Stream http2Stream, Http2Headers http2Headers, int i, short s, boolean z, int i2, boolean z2, ChannelPromise channelPromise) {
            super(http2Stream, i2, z2, channelPromise);
            this.headers = http2Headers;
            this.streamDependency = i;
            this.weight = s;
            this.exclusive = z;
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void error(ChannelHandlerContext channelHandlerContext, Throwable th) {
            if (channelHandlerContext != null) {
                DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, th);
            }
            this.promise.tryFailure(th);
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void write(ChannelHandlerContext channelHandlerContext, int i) {
            boolean validateHeadersSentState = DefaultHttp2ConnectionEncoder.validateHeadersSentState(this.stream, this.headers, DefaultHttp2ConnectionEncoder.this.connection.isServer(), this.endOfStream);
            if (this.promise.isVoid()) {
                this.promise = channelHandlerContext.newPromise();
            }
            this.promise.addListener((GenericFutureListener<? extends Future<? super Void>>) this);
            if (DefaultHttp2ConnectionEncoder.this.frameWriter.writeHeaders(channelHandlerContext, this.stream.mo3940id(), this.headers, this.streamDependency, this.weight, this.exclusive, this.padding, this.endOfStream, this.promise).cause() == null) {
                this.stream.headersSent(validateHeadersSentState);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public abstract class FlowControlledBase implements Http2RemoteFlowController.FlowControlled, ChannelFutureListener {
        protected boolean endOfStream;
        protected int padding;
        protected ChannelPromise promise;
        protected final Http2Stream stream;

        FlowControlledBase(Http2Stream http2Stream, int i, boolean z, ChannelPromise channelPromise) {
            if (i < 0) {
                throw new IllegalArgumentException("padding must be >= 0");
            }
            this.padding = i;
            this.endOfStream = z;
            this.stream = http2Stream;
            this.promise = channelPromise;
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void writeComplete() {
            if (this.endOfStream) {
                DefaultHttp2ConnectionEncoder.this.lifecycleManager.closeStreamLocal(this.stream, this.promise);
            }
        }

        @Override // io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                return;
            }
            error(DefaultHttp2ConnectionEncoder.this.flowController().channelHandlerContext(), channelFuture.cause());
        }
    }
}
