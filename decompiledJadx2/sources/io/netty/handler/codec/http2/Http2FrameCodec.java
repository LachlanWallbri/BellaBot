package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.handler.codec.http.HttpServerUpgradeHandler;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.codec.http2.HttpConversionUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Http2FrameCodec extends Http2ConnectionHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger LOG = InternalLoggerFactory.getInstance((Class<?>) Http2FrameCodec.class);
    private ChannelHandlerContext ctx;
    private DefaultHttp2FrameStream frameStreamToInitialize;
    private final Integer initialFlowControlWindowSize;
    private int numBufferedStreams;
    private final Http2Connection.PropertyKey streamKey;
    private final Http2Connection.PropertyKey upgradeKey;

    void handlerAdded0(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    static /* synthetic */ int access$610(Http2FrameCodec http2FrameCodec) {
        int i = http2FrameCodec.numBufferedStreams;
        http2FrameCodec.numBufferedStreams = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2FrameCodec(Http2ConnectionEncoder http2ConnectionEncoder, Http2ConnectionDecoder http2ConnectionDecoder, Http2Settings http2Settings) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings);
        http2ConnectionDecoder.frameListener(new FrameListener());
        connection().addListener(new ConnectionListener());
        connection().remote().flowController().listener(new Http2RemoteFlowControllerListener());
        this.streamKey = connection().newKey();
        this.upgradeKey = connection().newKey();
        this.initialFlowControlWindowSize = http2Settings.initialWindowSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultHttp2FrameStream newStream() {
        return new DefaultHttp2FrameStream();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void forEachActiveStream(final Http2FrameStreamVisitor http2FrameStreamVisitor) throws Http2Exception {
        connection().forEachActiveStream(new Http2StreamVisitor() { // from class: io.netty.handler.codec.http2.Http2FrameCodec.1
            @Override // io.netty.handler.codec.http2.Http2StreamVisitor
            public boolean visit(Http2Stream http2Stream) {
                try {
                    return http2FrameStreamVisitor.visit((Http2FrameStream) http2Stream.getProperty(Http2FrameCodec.this.streamKey));
                } catch (Throwable th) {
                    Http2FrameCodec http2FrameCodec = Http2FrameCodec.this;
                    http2FrameCodec.onError(http2FrameCodec.ctx, false, th);
                    return false;
                }
            }
        });
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public final void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        super.handlerAdded(channelHandlerContext);
        handlerAdded0(channelHandlerContext);
        Http2Connection connection = connection();
        if (connection.isServer()) {
            tryExpandConnectionFlowControlWindow(connection);
        }
    }

    private void tryExpandConnectionFlowControlWindow(Http2Connection http2Connection) throws Http2Exception {
        if (this.initialFlowControlWindowSize != null) {
            Http2Stream connectionStream = http2Connection.connectionStream();
            Http2LocalFlowController flowController = http2Connection.local().flowController();
            int intValue = this.initialFlowControlWindowSize.intValue() - flowController.initialWindowSize(connectionStream);
            if (intValue > 0) {
                flowController.incrementWindowSize(connectionStream, Math.max(intValue << 1, intValue));
                flush(this.ctx);
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public final void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj == Http2ConnectionPrefaceAndSettingsFrameWrittenEvent.INSTANCE) {
            tryExpandConnectionFlowControlWindow(connection());
        } else if (obj instanceof HttpServerUpgradeHandler.UpgradeEvent) {
            HttpServerUpgradeHandler.UpgradeEvent upgradeEvent = (HttpServerUpgradeHandler.UpgradeEvent) obj;
            try {
                onUpgradeEvent(channelHandlerContext, upgradeEvent.retain());
                Http2Stream stream = connection().stream(1);
                if (stream.getProperty(this.streamKey) == null) {
                    onStreamActive0(stream);
                }
                upgradeEvent.upgradeRequest().headers().setInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_ID.text(), 1);
                stream.setProperty(this.upgradeKey, true);
                InboundHttpToHttp2Adapter.handle(channelHandlerContext, connection(), decoder().frameListener(), upgradeEvent.upgradeRequest().retain());
                return;
            } finally {
                upgradeEvent.release();
            }
        }
        super.userEventTriggered(channelHandlerContext, obj);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.handler.codec.http2.Http2FrameCodec$3 */
    /* loaded from: classes8.dex */
    class RunnableC72213 implements Runnable {
        final /* synthetic */ ChannelHandlerContext val$ctx;
        final /* synthetic */ Object val$evt;

        RunnableC72213(ChannelHandlerContext channelHandlerContext, Object obj) {
            this.val$ctx = channelHandlerContext;
            this.val$evt = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$ctx.fireUserEventTriggered(this.val$evt);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) {
        if (obj instanceof Http2DataFrame) {
            Http2DataFrame http2DataFrame = (Http2DataFrame) obj;
            encoder().writeData(channelHandlerContext, http2DataFrame.stream().mo3941id(), http2DataFrame.content(), http2DataFrame.padding(), http2DataFrame.isEndStream(), channelPromise);
            return;
        }
        if (obj instanceof Http2HeadersFrame) {
            writeHeadersFrame(channelHandlerContext, (Http2HeadersFrame) obj, channelPromise);
            return;
        }
        if (obj instanceof Http2WindowUpdateFrame) {
            Http2WindowUpdateFrame http2WindowUpdateFrame = (Http2WindowUpdateFrame) obj;
            Http2FrameStream stream = http2WindowUpdateFrame.stream();
            try {
                if (stream == null) {
                    increaseInitialConnectionWindow(http2WindowUpdateFrame.windowSizeIncrement());
                } else {
                    consumeBytes(stream.mo3941id(), http2WindowUpdateFrame.windowSizeIncrement());
                }
                channelPromise.setSuccess();
                return;
            } catch (Throwable th) {
                channelPromise.setFailure(th);
                return;
            }
        }
        if (obj instanceof Http2ResetFrame) {
            Http2ResetFrame http2ResetFrame = (Http2ResetFrame) obj;
            encoder().writeRstStream(channelHandlerContext, http2ResetFrame.stream().mo3941id(), http2ResetFrame.errorCode(), channelPromise);
            return;
        }
        if (obj instanceof Http2PingFrame) {
            Http2PingFrame http2PingFrame = (Http2PingFrame) obj;
            encoder().writePing(channelHandlerContext, http2PingFrame.ack(), http2PingFrame.content(), channelPromise);
        } else {
            if (obj instanceof Http2SettingsFrame) {
                encoder().writeSettings(channelHandlerContext, ((Http2SettingsFrame) obj).settings(), channelPromise);
                return;
            }
            if (obj instanceof Http2GoAwayFrame) {
                writeGoAwayFrame(channelHandlerContext, (Http2GoAwayFrame) obj, channelPromise);
            } else if (!(obj instanceof Http2Frame)) {
                channelHandlerContext.write(obj, channelPromise);
            } else {
                ReferenceCountUtil.release(obj);
                throw new UnsupportedMessageTypeException(obj, (Class<?>[]) new Class[0]);
            }
        }
    }

    private void increaseInitialConnectionWindow(int i) throws Http2Exception {
        connection().local().flowController().incrementWindowSize(connection().connectionStream(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean consumeBytes(int i, int i2) throws Http2Exception {
        Http2Stream stream = connection().stream(i);
        if (i == 1) {
            if (Boolean.TRUE.equals((Boolean) stream.getProperty(this.upgradeKey))) {
                return false;
            }
        }
        return connection().local().flowController().consumeBytes(stream, i2);
    }

    private void writeGoAwayFrame(ChannelHandlerContext channelHandlerContext, Http2GoAwayFrame http2GoAwayFrame, ChannelPromise channelPromise) {
        if (http2GoAwayFrame.lastStreamId() > -1) {
            http2GoAwayFrame.release();
            throw new IllegalArgumentException("Last stream id must not be set on GOAWAY frame");
        }
        long lastStreamCreated = connection().remote().lastStreamCreated() + (http2GoAwayFrame.extraStreamIds() * 2);
        if (lastStreamCreated > 2147483647L) {
            lastStreamCreated = 2147483647L;
        }
        goAway(channelHandlerContext, (int) lastStreamCreated, http2GoAwayFrame.errorCode(), http2GoAwayFrame.content(), channelPromise);
    }

    private void writeHeadersFrame(ChannelHandlerContext channelHandlerContext, Http2HeadersFrame http2HeadersFrame, final ChannelPromise channelPromise) {
        if (Http2CodecUtil.isStreamIdValid(http2HeadersFrame.stream().mo3941id())) {
            encoder().writeHeaders(channelHandlerContext, http2HeadersFrame.stream().mo3941id(), http2HeadersFrame.headers(), http2HeadersFrame.padding(), http2HeadersFrame.isEndStream(), channelPromise);
            return;
        }
        DefaultHttp2FrameStream defaultHttp2FrameStream = (DefaultHttp2FrameStream) http2HeadersFrame.stream();
        int incrementAndGetNextStreamId = connection().local().incrementAndGetNextStreamId();
        if (incrementAndGetNextStreamId < 0) {
            channelPromise.setFailure((Throwable) new Http2NoMoreStreamIdsException());
            return;
        }
        defaultHttp2FrameStream.f8509id = incrementAndGetNextStreamId;
        this.frameStreamToInitialize = defaultHttp2FrameStream;
        ChannelPromise newPromise = channelHandlerContext.newPromise();
        encoder().writeHeaders(channelHandlerContext, incrementAndGetNextStreamId, http2HeadersFrame.headers(), http2HeadersFrame.padding(), http2HeadersFrame.isEndStream(), newPromise);
        if (newPromise.isDone()) {
            notifyHeaderWritePromise(newPromise, channelPromise);
        } else {
            this.numBufferedStreams++;
            newPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.Http2FrameCodec.2
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    Http2FrameCodec.access$610(Http2FrameCodec.this);
                    Http2FrameCodec.notifyHeaderWritePromise(channelFuture, channelPromise);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyHeaderWritePromise(ChannelFuture channelFuture, ChannelPromise channelPromise) {
        Throwable cause = channelFuture.cause();
        if (cause == null) {
            channelPromise.setSuccess();
        } else {
            channelPromise.setFailure(cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStreamActive0(Http2Stream http2Stream) {
        if (connection().local().isValidStreamId(http2Stream.mo3940id())) {
            return;
        }
        onHttp2StreamStateChanged(this.ctx, newStream().setStreamAndProperty(this.streamKey, http2Stream));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class ConnectionListener extends Http2ConnectionAdapter {
        private ConnectionListener() {
        }

        @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamAdded(Http2Stream http2Stream) {
            if (Http2FrameCodec.this.frameStreamToInitialize == null || http2Stream.mo3940id() != Http2FrameCodec.this.frameStreamToInitialize.mo3941id()) {
                return;
            }
            Http2FrameCodec.this.frameStreamToInitialize.setStreamAndProperty(Http2FrameCodec.this.streamKey, http2Stream);
            Http2FrameCodec.this.frameStreamToInitialize = null;
        }

        @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamActive(Http2Stream http2Stream) {
            Http2FrameCodec.this.onStreamActive0(http2Stream);
        }

        @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamClosed(Http2Stream http2Stream) {
            DefaultHttp2FrameStream defaultHttp2FrameStream = (DefaultHttp2FrameStream) http2Stream.getProperty(Http2FrameCodec.this.streamKey);
            if (defaultHttp2FrameStream != null) {
                Http2FrameCodec http2FrameCodec = Http2FrameCodec.this;
                http2FrameCodec.onHttp2StreamStateChanged(http2FrameCodec.ctx, defaultHttp2FrameStream);
            }
        }

        @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamHalfClosed(Http2Stream http2Stream) {
            DefaultHttp2FrameStream defaultHttp2FrameStream = (DefaultHttp2FrameStream) http2Stream.getProperty(Http2FrameCodec.this.streamKey);
            if (defaultHttp2FrameStream != null) {
                Http2FrameCodec http2FrameCodec = Http2FrameCodec.this;
                http2FrameCodec.onHttp2StreamStateChanged(http2FrameCodec.ctx, defaultHttp2FrameStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler
    public void onConnectionError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception http2Exception) {
        if (!z) {
            channelHandlerContext.fireExceptionCaught(th);
        }
        super.onConnectionError(channelHandlerContext, z, th, http2Exception);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler
    public final void onStreamError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception.StreamException streamException) {
        Http2Stream stream = connection().stream(streamException.streamId());
        if (stream == null) {
            onHttp2UnknownStreamError(channelHandlerContext, th, streamException);
            super.onStreamError(channelHandlerContext, z, th, streamException);
            return;
        }
        Http2FrameStream http2FrameStream = (Http2FrameStream) stream.getProperty(this.streamKey);
        if (http2FrameStream == null) {
            LOG.warn("Stream exception thrown without stream object attached.", th);
            super.onStreamError(channelHandlerContext, z, th, streamException);
        } else {
            if (z) {
                return;
            }
            onHttp2FrameStreamException(channelHandlerContext, new Http2FrameStreamException(http2FrameStream, streamException.error(), th));
        }
    }

    void onHttp2UnknownStreamError(ChannelHandlerContext channelHandlerContext, Throwable th, Http2Exception.StreamException streamException) {
        LOG.warn("Stream exception thrown for unkown stream {}.", Integer.valueOf(streamException.streamId()), th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler
    public final boolean isGracefulShutdownComplete() {
        return super.isGracefulShutdownComplete() && this.numBufferedStreams == 0;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class FrameListener implements Http2FrameListener {
        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onPriorityRead(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) {
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) {
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onSettingsAckRead(ChannelHandlerContext channelHandlerContext) {
        }

        private FrameListener() {
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2UnknownFrame(b, http2Flags, byteBuf).stream(requireStream(i)).retain());
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2SettingsFrame(http2Settings));
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2PingFrame(j, false));
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2PingFrame(j, true));
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2ResetFrame(j).stream(requireStream(i)));
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onWindowUpdateRead(ChannelHandlerContext channelHandlerContext, int i, int i2) {
            if (i == 0) {
                return;
            }
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2WindowUpdateFrame(i2).stream(requireStream(i)));
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) {
            onHeadersRead(channelHandlerContext, i, http2Headers, i3, z2);
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2HeadersFrame(http2Headers, z, i2).stream(requireStream(i)));
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2DataFrame(byteBuf, z, i2).stream(requireStream(i)).retain());
            return 0;
        }

        @Override // io.netty.handler.codec.http2.Http2FrameListener
        public void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2GoAwayFrame(i, j, byteBuf).retain());
        }

        private Http2FrameStream requireStream(int i) {
            Http2FrameStream http2FrameStream = (Http2FrameStream) Http2FrameCodec.this.connection().stream(i).getProperty(Http2FrameCodec.this.streamKey);
            if (http2FrameStream != null) {
                return http2FrameStream;
            }
            throw new IllegalStateException("Stream object required for identifier: " + i);
        }
    }

    void onUpgradeEvent(ChannelHandlerContext channelHandlerContext, HttpServerUpgradeHandler.UpgradeEvent upgradeEvent) {
        channelHandlerContext.fireUserEventTriggered((Object) upgradeEvent);
    }

    void onHttp2StreamWritabilityChanged(ChannelHandlerContext channelHandlerContext, Http2FrameStream http2FrameStream, boolean z) {
        channelHandlerContext.fireUserEventTriggered((Object) Http2FrameStreamEvent.writabilityChanged(http2FrameStream));
    }

    void onHttp2StreamStateChanged(ChannelHandlerContext channelHandlerContext, Http2FrameStream http2FrameStream) {
        channelHandlerContext.fireUserEventTriggered((Object) Http2FrameStreamEvent.stateChanged(http2FrameStream));
    }

    void onHttp2Frame(ChannelHandlerContext channelHandlerContext, Http2Frame http2Frame) {
        channelHandlerContext.fireChannelRead((Object) http2Frame);
    }

    void onHttp2FrameStreamException(ChannelHandlerContext channelHandlerContext, Http2FrameStreamException http2FrameStreamException) {
        channelHandlerContext.fireExceptionCaught((Throwable) http2FrameStreamException);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isWritable(DefaultHttp2FrameStream defaultHttp2FrameStream) {
        Http2Stream http2Stream = defaultHttp2FrameStream.stream;
        return http2Stream != null && connection().remote().flowController().isWritable(http2Stream);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class Http2RemoteFlowControllerListener implements Http2RemoteFlowController.Listener {
        private Http2RemoteFlowControllerListener() {
        }

        @Override // io.netty.handler.codec.http2.Http2RemoteFlowController.Listener
        public void writabilityChanged(Http2Stream http2Stream) {
            Http2FrameStream http2FrameStream = (Http2FrameStream) http2Stream.getProperty(Http2FrameCodec.this.streamKey);
            if (http2FrameStream == null) {
                return;
            }
            Http2FrameCodec http2FrameCodec = Http2FrameCodec.this;
            http2FrameCodec.onHttp2StreamWritabilityChanged(http2FrameCodec.ctx, http2FrameStream, Http2FrameCodec.this.connection().remote().flowController().isWritable(http2Stream));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static class DefaultHttp2FrameStream implements Http2FrameStream {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        /* renamed from: id */
        private volatile int f8509id = -1;
        volatile Http2Stream stream;

        DefaultHttp2FrameStream setStreamAndProperty(Http2Connection.PropertyKey propertyKey, Http2Stream http2Stream) {
            this.stream = http2Stream;
            http2Stream.setProperty(propertyKey, this);
            return this;
        }

        @Override // io.netty.handler.codec.http2.Http2FrameStream
        /* renamed from: id */
        public int mo3941id() {
            Http2Stream http2Stream = this.stream;
            return http2Stream == null ? this.f8509id : http2Stream.mo3940id();
        }

        @Override // io.netty.handler.codec.http2.Http2FrameStream
        public Http2Stream.State state() {
            Http2Stream http2Stream = this.stream;
            return http2Stream == null ? Http2Stream.State.IDLE : http2Stream.state();
        }

        public String toString() {
            return String.valueOf(mo3941id());
        }
    }
}
