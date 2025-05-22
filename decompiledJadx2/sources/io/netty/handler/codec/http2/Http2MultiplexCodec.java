package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.DefaultMaxMessagesRecvByteBufAllocator;
import io.netty.channel.EventLoop;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.VoidChannelPromise;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.handler.codec.http2.Http2FrameCodec;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.ThrowableUtil;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Http2MultiplexCodec extends Http2FrameCodec {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_HTTP2_FRAME_SIZE = 9;
    volatile ChannelHandlerContext ctx;
    private DefaultHttp2StreamChannel head;
    private int idCount;
    private final ChannelHandler inboundStreamHandler;
    private int initialOutboundStreamWindow;
    private boolean parentReadInProgress;
    private DefaultHttp2StreamChannel tail;
    private static final ChannelFutureListener CHILD_CHANNEL_REGISTRATION_LISTENER = new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.1
        @Override // io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            Http2MultiplexCodec.registerDone(channelFuture);
        }
    };
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final ClosedChannelException CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), DefaultHttp2StreamChannel.Http2ChannelUnsafe.class, "write(...)");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum ReadState {
        READ_QUEUED,
        READ_IGNORED_CHANNEL_INACTIVE,
        READ_PROCESSED_BUT_STOP_READING,
        READ_PROCESSED_OK_TO_PROCESS_MORE
    }

    static /* synthetic */ int access$304(Http2MultiplexCodec http2MultiplexCodec) {
        int i = http2MultiplexCodec.idCount + 1;
        http2MultiplexCodec.idCount = i;
        return i;
    }

    /* loaded from: classes.dex */
    private static final class FlowControlledFrameSizeEstimator implements MessageSizeEstimator {
        static final FlowControlledFrameSizeEstimator INSTANCE = new FlowControlledFrameSizeEstimator();
        static final MessageSizeEstimator.Handle HANDLE_INSTANCE = new MessageSizeEstimator.Handle() { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.FlowControlledFrameSizeEstimator.1
            @Override // io.netty.channel.MessageSizeEstimator.Handle
            public int size(Object obj) {
                if (obj instanceof Http2DataFrame) {
                    return (int) Math.min(2147483647L, ((Http2DataFrame) obj).initialFlowControlledBytes() + 9);
                }
                return 9;
            }
        };

        private FlowControlledFrameSizeEstimator() {
        }

        @Override // io.netty.channel.MessageSizeEstimator
        public MessageSizeEstimator.Handle newHandle() {
            return HANDLE_INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    private static final class Http2StreamChannelRecvByteBufAllocator extends DefaultMaxMessagesRecvByteBufAllocator {
        private Http2StreamChannelRecvByteBufAllocator() {
        }

        @Override // io.netty.channel.RecvByteBufAllocator
        public DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle newHandle() {
            return new DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle() { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.Http2StreamChannelRecvByteBufAllocator.1
                @Override // io.netty.channel.RecvByteBufAllocator.Handle
                public int guess() {
                    return 1024;
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2MultiplexCodec(Http2ConnectionEncoder http2ConnectionEncoder, Http2ConnectionDecoder http2ConnectionDecoder, Http2Settings http2Settings, ChannelHandler channelHandler) {
        super(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings);
        this.initialOutboundStreamWindow = 65535;
        this.inboundStreamHandler = channelHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void registerDone(ChannelFuture channelFuture) {
        if (channelFuture.isSuccess()) {
            return;
        }
        Channel channel = channelFuture.channel();
        if (channel.isRegistered()) {
            channel.close();
        } else {
            channel.unsafe().closeForcibly();
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameCodec
    public final void handlerAdded0(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.executor() != channelHandlerContext.channel().eventLoop()) {
            throw new IllegalStateException("EventExecutor must be EventLoop of Channel");
        }
        this.ctx = channelHandlerContext;
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.handler.codec.ByteToMessageDecoder
    public final void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerRemoved0(channelHandlerContext);
        DefaultHttp2StreamChannel defaultHttp2StreamChannel = this.head;
        while (defaultHttp2StreamChannel != null) {
            DefaultHttp2StreamChannel defaultHttp2StreamChannel2 = defaultHttp2StreamChannel.next;
            defaultHttp2StreamChannel.next = null;
            defaultHttp2StreamChannel = defaultHttp2StreamChannel2;
        }
        this.tail = null;
        this.head = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.handler.codec.http2.Http2FrameCodec
    public Http2MultiplexCodecStream newStream() {
        return new Http2MultiplexCodecStream();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameCodec
    final void onHttp2Frame(ChannelHandlerContext channelHandlerContext, Http2Frame http2Frame) {
        if (http2Frame instanceof Http2StreamFrame) {
            Http2StreamFrame http2StreamFrame = (Http2StreamFrame) http2Frame;
            onHttp2StreamFrame(((Http2MultiplexCodecStream) http2StreamFrame.stream()).channel, http2StreamFrame);
            return;
        }
        if (http2Frame instanceof Http2GoAwayFrame) {
            onHttp2GoAwayFrame(channelHandlerContext, (Http2GoAwayFrame) http2Frame);
            channelHandlerContext.fireChannelRead((Object) http2Frame);
        } else {
            if (http2Frame instanceof Http2SettingsFrame) {
                Http2Settings http2Settings = ((Http2SettingsFrame) http2Frame).settings();
                if (http2Settings.initialWindowSize() != null) {
                    this.initialOutboundStreamWindow = http2Settings.initialWindowSize().intValue();
                }
                channelHandlerContext.fireChannelRead((Object) http2Frame);
                return;
            }
            channelHandlerContext.fireChannelRead((Object) http2Frame);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameCodec
    final void onHttp2StreamStateChanged(ChannelHandlerContext channelHandlerContext, Http2FrameStream http2FrameStream) {
        DefaultHttp2StreamChannel defaultHttp2StreamChannel;
        Http2MultiplexCodecStream http2MultiplexCodecStream = (Http2MultiplexCodecStream) http2FrameStream;
        int i = C72273.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[http2FrameStream.state().ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3 && (defaultHttp2StreamChannel = http2MultiplexCodecStream.channel) != null) {
                defaultHttp2StreamChannel.streamClosed();
                return;
            }
            return;
        }
        if (http2MultiplexCodecStream.channel != null) {
            return;
        }
        ChannelFuture register = channelHandlerContext.channel().eventLoop().register(new DefaultHttp2StreamChannel(http2MultiplexCodecStream, false));
        if (register.isDone()) {
            registerDone(register);
        } else {
            register.addListener((GenericFutureListener<? extends Future<? super Void>>) CHILD_CHANNEL_REGISTRATION_LISTENER);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameCodec
    final void onHttp2StreamWritabilityChanged(ChannelHandlerContext channelHandlerContext, Http2FrameStream http2FrameStream, boolean z) {
        ((Http2MultiplexCodecStream) http2FrameStream).channel.writabilityChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Http2StreamChannel newOutboundStream() {
        return new DefaultHttp2StreamChannel(newStream(), true);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameCodec
    final void onHttp2FrameStreamException(ChannelHandlerContext channelHandlerContext, Http2FrameStreamException http2FrameStreamException) {
        DefaultHttp2StreamChannel defaultHttp2StreamChannel = ((Http2MultiplexCodecStream) http2FrameStreamException.stream()).channel;
        try {
            defaultHttp2StreamChannel.pipeline().fireExceptionCaught(http2FrameStreamException.getCause());
        } finally {
            defaultHttp2StreamChannel.unsafe().closeForcibly();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private final class Http2MultiplexCodecStreamChannel extends AbstractHttp2StreamChannel {
        Http2MultiplexCodecStreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, ChannelHandler channelHandler) {
            super(defaultHttp2FrameStream, Http2MultiplexCodec.access$004(Http2MultiplexCodec.this), channelHandler);
        }

        @Override // io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected boolean isParentReadInProgress() {
            return Http2MultiplexCodec.access$100(Http2MultiplexCodec.this);
        }

        @Override // io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected void addChannelToReadCompletePendingQueue() {
            while (!Http2MultiplexCodec.access$200(Http2MultiplexCodec.this).offer(this)) {
                Http2MultiplexCodec.access$300(Http2MultiplexCodec.this);
            }
        }

        @Override // io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected ChannelHandlerContext parentContext() {
            return Http2MultiplexCodec.this.ctx;
        }

        @Override // io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected ChannelFuture write0(ChannelHandlerContext channelHandlerContext, Object obj) {
            ChannelPromise newPromise = channelHandlerContext.newPromise();
            Http2MultiplexCodec.this.write(channelHandlerContext, obj, newPromise);
            return newPromise;
        }

        @Override // io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected void flush0(ChannelHandlerContext channelHandlerContext) {
            Http2MultiplexCodec.this.flush0(channelHandlerContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.netty.handler.codec.http2.Http2MultiplexCodec$3 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C72273 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$http2$Http2MultiplexCodec$ReadState */
        static final /* synthetic */ int[] f8510x95806bcd;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        static {
            int[] iArr = new int[ReadState.values().length];
            f8510x95806bcd = iArr;
            try {
                iArr[ReadState.READ_PROCESSED_BUT_STOP_READING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8510x95806bcd[ReadState.READ_PROCESSED_OK_TO_PROCESS_MORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8510x95806bcd[ReadState.READ_IGNORED_CHANNEL_INACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8510x95806bcd[ReadState.READ_QUEUED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Http2Stream.State.values().length];
            $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = iArr2;
            try {
                iArr2[Http2Stream.State.HALF_CLOSED_REMOTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.CLOSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private void onHttp2StreamFrame(DefaultHttp2StreamChannel defaultHttp2StreamChannel, Http2StreamFrame http2StreamFrame) {
        int i = C72273.f8510x95806bcd[defaultHttp2StreamChannel.fireChildRead(http2StreamFrame).ordinal()];
        if (i == 1) {
            defaultHttp2StreamChannel.fireChildReadComplete();
        } else if (i == 2) {
            addChildChannelToReadPendingQueue(defaultHttp2StreamChannel);
        } else if (i != 3 && i != 4) {
            throw new Error();
        }
    }

    final void addChildChannelToReadPendingQueue(DefaultHttp2StreamChannel defaultHttp2StreamChannel) {
        if (defaultHttp2StreamChannel.fireChannelReadPending) {
            return;
        }
        DefaultHttp2StreamChannel defaultHttp2StreamChannel2 = this.tail;
        if (defaultHttp2StreamChannel2 == null) {
            this.head = defaultHttp2StreamChannel;
            this.tail = defaultHttp2StreamChannel;
        } else {
            defaultHttp2StreamChannel2.next = defaultHttp2StreamChannel;
            this.tail = defaultHttp2StreamChannel;
        }
        defaultHttp2StreamChannel.fireChannelReadPending = true;
    }

    private void onHttp2GoAwayFrame(ChannelHandlerContext channelHandlerContext, final Http2GoAwayFrame http2GoAwayFrame) {
        try {
            try {
                forEachActiveStream(new Http2FrameStreamVisitor() { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.2
                    @Override // io.netty.handler.codec.http2.Http2FrameStreamVisitor
                    public boolean visit(Http2FrameStream http2FrameStream) {
                        int mo3941id = http2FrameStream.mo3941id();
                        DefaultHttp2StreamChannel defaultHttp2StreamChannel = ((Http2MultiplexCodecStream) http2FrameStream).channel;
                        if (mo3941id <= http2GoAwayFrame.lastStreamId() || !Http2MultiplexCodec.this.connection().local().isValidStreamId(mo3941id)) {
                            return true;
                        }
                        defaultHttp2StreamChannel.pipeline().fireUserEventTriggered((Object) http2GoAwayFrame.retainedDuplicate());
                        return true;
                    }
                });
            } catch (Http2Exception e) {
                channelHandlerContext.fireExceptionCaught((Throwable) e);
                channelHandlerContext.close();
            }
        } finally {
            http2GoAwayFrame.release();
        }
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public final void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.parentReadInProgress = false;
        onChannelReadComplete(channelHandlerContext);
        channelReadComplete0(channelHandlerContext);
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public final void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        this.parentReadInProgress = true;
        super.channelRead(channelHandlerContext, obj);
    }

    final void onChannelReadComplete(ChannelHandlerContext channelHandlerContext) {
        try {
            for (DefaultHttp2StreamChannel defaultHttp2StreamChannel = this.head; defaultHttp2StreamChannel != null; defaultHttp2StreamChannel = defaultHttp2StreamChannel.next) {
                if (defaultHttp2StreamChannel.fireChannelReadPending) {
                    defaultHttp2StreamChannel.fireChannelReadPending = false;
                    defaultHttp2StreamChannel.fireChildReadComplete();
                }
                defaultHttp2StreamChannel.next = null;
            }
        } finally {
            this.head = null;
            this.tail = null;
            flush0(channelHandlerContext);
        }
    }

    void flush0(ChannelHandlerContext channelHandlerContext) {
        flush(channelHandlerContext);
    }

    boolean onBytesConsumed(ChannelHandlerContext channelHandlerContext, Http2FrameStream http2FrameStream, int i) throws Http2Exception {
        return consumeBytes(http2FrameStream.mo3941id(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Http2MultiplexCodecStream extends Http2FrameCodec.DefaultHttp2FrameStream {
        DefaultHttp2StreamChannel channel;

        Http2MultiplexCodecStream() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initialWritability(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream) {
        return !Http2CodecUtil.isStreamIdValid(defaultHttp2FrameStream.mo3941id()) || isWritable(defaultHttp2FrameStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DefaultHttp2StreamChannel extends DefaultAttributeMap implements Http2StreamChannel {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ChannelId channelId;
        private boolean closePending;
        private final ChannelPromise closePromise;
        boolean fireChannelReadPending;
        private boolean firstFrameWritten;
        private boolean inFireChannelReadComplete;
        private Queue<Object> inboundBuffer;
        DefaultHttp2StreamChannel next;
        private final boolean outbound;
        private boolean outboundClosed;
        private final ChannelPipeline pipeline;
        private boolean readInProgress;
        private volatile boolean registered;
        private final Http2FrameCodec.DefaultHttp2FrameStream stream;
        private boolean streamClosedWithoutError;
        private volatile boolean writable;
        private final Http2StreamChannelConfig config = new Http2StreamChannelConfig(this);
        private final Http2ChannelUnsafe unsafe = new Http2ChannelUnsafe();

        @Override // io.netty.channel.Channel
        public long bytesBeforeWritable() {
            return 0L;
        }

        public boolean equals(Object obj) {
            return this == obj;
        }

        DefaultHttp2StreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, boolean z) {
            this.stream = defaultHttp2FrameStream;
            this.outbound = z;
            this.writable = Http2MultiplexCodec.this.initialWritability(defaultHttp2FrameStream);
            ((Http2MultiplexCodecStream) defaultHttp2FrameStream).channel = this;
            DefaultChannelPipeline defaultChannelPipeline = new DefaultChannelPipeline(this) { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.DefaultHttp2StreamChannel.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.netty.channel.DefaultChannelPipeline
                public void decrementPendingOutboundBytes(long j) {
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.netty.channel.DefaultChannelPipeline
                public void incrementPendingOutboundBytes(long j) {
                }
            };
            this.pipeline = defaultChannelPipeline;
            this.closePromise = defaultChannelPipeline.newPromise();
            this.channelId = new Http2StreamChannelId(parent().mo3929id(), Http2MultiplexCodec.access$304(Http2MultiplexCodec.this));
        }

        @Override // io.netty.handler.codec.http2.Http2StreamChannel
        public Http2FrameStream stream() {
            return this.stream;
        }

        void streamClosed() {
            this.streamClosedWithoutError = true;
            if (this.readInProgress) {
                unsafe().closeForcibly();
            } else {
                this.closePending = true;
            }
        }

        @Override // io.netty.channel.Channel
        public ChannelMetadata metadata() {
            return Http2MultiplexCodec.METADATA;
        }

        @Override // io.netty.channel.Channel
        public ChannelConfig config() {
            return this.config;
        }

        @Override // io.netty.channel.Channel
        public boolean isOpen() {
            return !this.closePromise.isDone();
        }

        @Override // io.netty.channel.Channel
        public boolean isActive() {
            return isOpen();
        }

        @Override // io.netty.channel.Channel
        public boolean isWritable() {
            return this.writable;
        }

        @Override // io.netty.channel.Channel
        /* renamed from: id */
        public ChannelId mo3929id() {
            return this.channelId;
        }

        @Override // io.netty.channel.Channel
        public EventLoop eventLoop() {
            return parent().eventLoop();
        }

        @Override // io.netty.channel.Channel
        public Channel parent() {
            return Http2MultiplexCodec.this.ctx.channel();
        }

        @Override // io.netty.channel.Channel
        public boolean isRegistered() {
            return this.registered;
        }

        @Override // io.netty.channel.Channel
        public SocketAddress localAddress() {
            return parent().localAddress();
        }

        @Override // io.netty.channel.Channel
        public SocketAddress remoteAddress() {
            return parent().remoteAddress();
        }

        @Override // io.netty.channel.Channel
        public ChannelFuture closeFuture() {
            return this.closePromise;
        }

        @Override // io.netty.channel.Channel
        public long bytesBeforeUnwritable() {
            return config().getWriteBufferHighWaterMark();
        }

        @Override // io.netty.channel.Channel
        public Channel.Unsafe unsafe() {
            return this.unsafe;
        }

        @Override // io.netty.channel.Channel
        public ChannelPipeline pipeline() {
            return this.pipeline;
        }

        @Override // io.netty.channel.Channel
        public ByteBufAllocator alloc() {
            return config().getAllocator();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public Channel read() {
            pipeline().read();
            return this;
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public Channel flush() {
            pipeline().flush();
            return this;
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture bind(SocketAddress socketAddress) {
            return pipeline().bind(socketAddress);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress) {
            return pipeline().connect(socketAddress);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
            return pipeline().connect(socketAddress, socketAddress2);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture disconnect() {
            return pipeline().disconnect();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture close() {
            return pipeline().close();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture deregister() {
            return pipeline().deregister();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
            return pipeline().bind(socketAddress, channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
            return pipeline().connect(socketAddress, channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            return pipeline().connect(socketAddress, socketAddress2, channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture disconnect(ChannelPromise channelPromise) {
            return pipeline().disconnect(channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture close(ChannelPromise channelPromise) {
            return pipeline().close(channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture deregister(ChannelPromise channelPromise) {
            return pipeline().deregister(channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture write(Object obj) {
            return pipeline().write(obj);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
            return pipeline().write(obj, channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
            return pipeline().writeAndFlush(obj, channelPromise);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture writeAndFlush(Object obj) {
            return pipeline().writeAndFlush(obj);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelPromise newPromise() {
            return pipeline().newPromise();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelProgressivePromise newProgressivePromise() {
            return pipeline().newProgressivePromise();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture newSucceededFuture() {
            return pipeline().newSucceededFuture();
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture newFailedFuture(Throwable th) {
            return pipeline().newFailedFuture(th);
        }

        @Override // io.netty.channel.ChannelOutboundInvoker
        public ChannelPromise voidPromise() {
            return pipeline().voidPromise();
        }

        public int hashCode() {
            return mo3929id().hashCode();
        }

        @Override // java.lang.Comparable
        public int compareTo(Channel channel) {
            if (this == channel) {
                return 0;
            }
            return mo3929id().compareTo(channel.mo3929id());
        }

        public String toString() {
            return parent().toString() + "(H2 - " + this.stream + ')';
        }

        void writabilityChanged(boolean z) {
            if (z == this.writable || !isActive()) {
                return;
            }
            this.writable = z;
            pipeline().fireChannelWritabilityChanged();
        }

        ReadState fireChildRead(Http2Frame http2Frame) {
            Queue<Object> queue;
            if (!isActive()) {
                ReferenceCountUtil.release(http2Frame);
                return ReadState.READ_IGNORED_CHANNEL_INACTIVE;
            }
            if (this.readInProgress && ((queue = this.inboundBuffer) == null || queue.isEmpty())) {
                RecvByteBufAllocator.ExtendedHandle recvBufAllocHandle = this.unsafe.recvBufAllocHandle();
                this.unsafe.doRead0(http2Frame, recvBufAllocHandle);
                return recvBufAllocHandle.continueReading() ? ReadState.READ_PROCESSED_OK_TO_PROCESS_MORE : ReadState.READ_PROCESSED_BUT_STOP_READING;
            }
            if (this.inboundBuffer == null) {
                this.inboundBuffer = new ArrayDeque(4);
            }
            this.inboundBuffer.add(http2Frame);
            return ReadState.READ_QUEUED;
        }

        void fireChildReadComplete() {
            try {
                if (this.readInProgress) {
                    this.inFireChannelReadComplete = true;
                    this.readInProgress = false;
                    unsafe().recvBufAllocHandle().readComplete();
                    pipeline().fireChannelReadComplete();
                }
            } finally {
                this.inFireChannelReadComplete = false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public final class Http2ChannelUnsafe implements Channel.Unsafe {
            private boolean closeInitiated;
            private RecvByteBufAllocator.ExtendedHandle recvHandle;
            private final VoidChannelPromise unsafeVoidPromise;
            private boolean writeDoneAndNoFlush;

            @Override // io.netty.channel.Channel.Unsafe
            public ChannelOutboundBuffer outboundBuffer() {
                return null;
            }

            private Http2ChannelUnsafe() {
                this.unsafeVoidPromise = new VoidChannelPromise(DefaultHttp2StreamChannel.this, false);
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
                if (channelPromise.setUncancellable()) {
                    channelPromise.setFailure((Throwable) new UnsupportedOperationException());
                }
            }

            @Override // io.netty.channel.Channel.Unsafe
            public RecvByteBufAllocator.ExtendedHandle recvBufAllocHandle() {
                if (this.recvHandle == null) {
                    this.recvHandle = (RecvByteBufAllocator.ExtendedHandle) DefaultHttp2StreamChannel.this.config().getRecvByteBufAllocator().newHandle();
                }
                return this.recvHandle;
            }

            @Override // io.netty.channel.Channel.Unsafe
            public SocketAddress localAddress() {
                return DefaultHttp2StreamChannel.this.parent().unsafe().localAddress();
            }

            @Override // io.netty.channel.Channel.Unsafe
            public SocketAddress remoteAddress() {
                return DefaultHttp2StreamChannel.this.parent().unsafe().remoteAddress();
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void register(EventLoop eventLoop, ChannelPromise channelPromise) {
                if (channelPromise.setUncancellable()) {
                    if (!DefaultHttp2StreamChannel.this.registered) {
                        DefaultHttp2StreamChannel.this.registered = true;
                        if (!DefaultHttp2StreamChannel.this.outbound) {
                            DefaultHttp2StreamChannel.this.pipeline().addLast(Http2MultiplexCodec.this.inboundStreamHandler);
                        }
                        channelPromise.setSuccess();
                        DefaultHttp2StreamChannel.this.pipeline().fireChannelRegistered();
                        if (DefaultHttp2StreamChannel.this.isActive()) {
                            DefaultHttp2StreamChannel.this.pipeline().fireChannelActive();
                            return;
                        }
                        return;
                    }
                    throw new UnsupportedOperationException("Re-register is not supported");
                }
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
                if (channelPromise.setUncancellable()) {
                    channelPromise.setFailure((Throwable) new UnsupportedOperationException());
                }
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void disconnect(ChannelPromise channelPromise) {
                close(channelPromise);
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void close(final ChannelPromise channelPromise) {
                if (channelPromise.setUncancellable()) {
                    if (this.closeInitiated) {
                        if (DefaultHttp2StreamChannel.this.closePromise.isDone()) {
                            channelPromise.setSuccess();
                            return;
                        } else {
                            if (channelPromise instanceof VoidChannelPromise) {
                                return;
                            }
                            DefaultHttp2StreamChannel.this.closePromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.DefaultHttp2StreamChannel.Http2ChannelUnsafe.1
                                @Override // io.netty.util.concurrent.GenericFutureListener
                                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                    channelPromise.setSuccess();
                                }
                            });
                            return;
                        }
                    }
                    this.closeInitiated = true;
                    DefaultHttp2StreamChannel.this.closePending = false;
                    DefaultHttp2StreamChannel.this.fireChannelReadPending = false;
                    if (DefaultHttp2StreamChannel.this.parent().isActive() && !DefaultHttp2StreamChannel.this.streamClosedWithoutError && Http2CodecUtil.isStreamIdValid(DefaultHttp2StreamChannel.this.stream().mo3941id())) {
                        write(new DefaultHttp2ResetFrame(Http2Error.CANCEL).stream(DefaultHttp2StreamChannel.this.stream()), DefaultHttp2StreamChannel.this.unsafe().voidPromise());
                        flush();
                    }
                    if (DefaultHttp2StreamChannel.this.inboundBuffer != null) {
                        while (true) {
                            Object poll = DefaultHttp2StreamChannel.this.inboundBuffer.poll();
                            if (poll == null) {
                                break;
                            } else {
                                ReferenceCountUtil.release(poll);
                            }
                        }
                    }
                    DefaultHttp2StreamChannel.this.outboundClosed = true;
                    DefaultHttp2StreamChannel.this.closePromise.setSuccess();
                    channelPromise.setSuccess();
                    DefaultHttp2StreamChannel.this.pipeline().fireChannelInactive();
                    if (DefaultHttp2StreamChannel.this.isRegistered()) {
                        deregister(DefaultHttp2StreamChannel.this.unsafe().voidPromise());
                    }
                }
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void closeForcibly() {
                close(DefaultHttp2StreamChannel.this.unsafe().voidPromise());
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void deregister(ChannelPromise channelPromise) {
                if (channelPromise.setUncancellable()) {
                    if (DefaultHttp2StreamChannel.this.registered) {
                        DefaultHttp2StreamChannel.this.registered = true;
                        channelPromise.setSuccess();
                        DefaultHttp2StreamChannel.this.pipeline().fireChannelUnregistered();
                        return;
                    }
                    channelPromise.setFailure((Throwable) new IllegalStateException("Not registered"));
                }
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void beginRead() {
                boolean z;
                if (DefaultHttp2StreamChannel.this.readInProgress || !DefaultHttp2StreamChannel.this.isActive()) {
                    return;
                }
                DefaultHttp2StreamChannel.this.readInProgress = true;
                RecvByteBufAllocator.Handle recvBufAllocHandle = DefaultHttp2StreamChannel.this.unsafe().recvBufAllocHandle();
                recvBufAllocHandle.reset(DefaultHttp2StreamChannel.this.config());
                if (DefaultHttp2StreamChannel.this.inboundBuffer == null || DefaultHttp2StreamChannel.this.inboundBuffer.isEmpty()) {
                    if (DefaultHttp2StreamChannel.this.closePending) {
                        DefaultHttp2StreamChannel.this.unsafe.closeForcibly();
                        return;
                    }
                    return;
                }
                while (true) {
                    Object poll = DefaultHttp2StreamChannel.this.inboundBuffer.poll();
                    if (poll == null) {
                        z = false;
                        break;
                    }
                    doRead0((Http2Frame) poll, recvBufAllocHandle);
                    z = recvBufAllocHandle.continueReading();
                    if (!z) {
                        break;
                    }
                }
                if (!z || !Http2MultiplexCodec.this.parentReadInProgress) {
                    DefaultHttp2StreamChannel.this.readInProgress = false;
                    recvBufAllocHandle.readComplete();
                    DefaultHttp2StreamChannel.this.pipeline().fireChannelReadComplete();
                    flush();
                    if (DefaultHttp2StreamChannel.this.closePending) {
                        DefaultHttp2StreamChannel.this.unsafe.closeForcibly();
                        return;
                    }
                    return;
                }
                Http2MultiplexCodec.this.addChildChannelToReadPendingQueue(DefaultHttp2StreamChannel.this);
            }

            void doRead0(Http2Frame http2Frame, RecvByteBufAllocator.Handle handle) {
                int i;
                if (http2Frame instanceof Http2DataFrame) {
                    i = ((Http2DataFrame) http2Frame).initialFlowControlledBytes();
                    handle.lastBytesRead(i);
                } else {
                    handle.lastBytesRead(9);
                    i = 0;
                }
                handle.incMessagesRead(1);
                DefaultHttp2StreamChannel.this.pipeline().fireChannelRead((Object) http2Frame);
                if (i != 0) {
                    try {
                        this.writeDoneAndNoFlush |= Http2MultiplexCodec.this.onBytesConsumed(Http2MultiplexCodec.this.ctx, DefaultHttp2StreamChannel.this.stream, i);
                    } catch (Http2Exception e) {
                        DefaultHttp2StreamChannel.this.pipeline().fireExceptionCaught((Throwable) e);
                    }
                }
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void write(Object obj, final ChannelPromise channelPromise) {
                if (!channelPromise.setUncancellable()) {
                    ReferenceCountUtil.release(obj);
                    return;
                }
                if (!DefaultHttp2StreamChannel.this.isActive() || (DefaultHttp2StreamChannel.this.outboundClosed && ((obj instanceof Http2HeadersFrame) || (obj instanceof Http2DataFrame)))) {
                    ReferenceCountUtil.release(obj);
                    channelPromise.setFailure((Throwable) Http2MultiplexCodec.CLOSED_CHANNEL_EXCEPTION);
                    return;
                }
                try {
                } finally {
                    try {
                        return;
                    } finally {
                    }
                }
                if (!(obj instanceof Http2StreamFrame)) {
                    String obj2 = obj.toString();
                    ReferenceCountUtil.release(obj);
                    channelPromise.setFailure((Throwable) new IllegalArgumentException("Message must be an " + StringUtil.simpleClassName((Class<?>) Http2StreamFrame.class) + ": " + obj2));
                    return;
                }
                Http2StreamFrame stream = validateStreamFrame((Http2StreamFrame) obj).stream(DefaultHttp2StreamChannel.this.stream());
                if (DefaultHttp2StreamChannel.this.firstFrameWritten || Http2CodecUtil.isStreamIdValid(DefaultHttp2StreamChannel.this.stream().mo3941id())) {
                    ChannelFuture write0 = write0(obj);
                    if (write0.isDone()) {
                        writeComplete(write0, channelPromise);
                    } else {
                        write0.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.DefaultHttp2StreamChannel.Http2ChannelUnsafe.3
                            @Override // io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                Http2ChannelUnsafe.this.writeComplete(channelFuture, channelPromise);
                            }
                        });
                    }
                    return;
                }
                if (stream instanceof Http2HeadersFrame) {
                    DefaultHttp2StreamChannel.this.firstFrameWritten = true;
                    ChannelFuture write02 = write0(stream);
                    if (write02.isDone()) {
                        firstWriteComplete(write02, channelPromise);
                    } else {
                        write02.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.Http2MultiplexCodec.DefaultHttp2StreamChannel.Http2ChannelUnsafe.2
                            @Override // io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                Http2ChannelUnsafe.this.firstWriteComplete(channelFuture, channelPromise);
                            }
                        });
                    }
                    return;
                }
                ReferenceCountUtil.release(stream);
                channelPromise.setFailure((Throwable) new IllegalArgumentException("The first frame must be a headers frame. Was: " + stream.name()));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void firstWriteComplete(ChannelFuture channelFuture, ChannelPromise channelPromise) {
                Throwable cause = channelFuture.cause();
                if (cause == null) {
                    DefaultHttp2StreamChannel defaultHttp2StreamChannel = DefaultHttp2StreamChannel.this;
                    defaultHttp2StreamChannel.writabilityChanged(Http2MultiplexCodec.this.isWritable(DefaultHttp2StreamChannel.this.stream));
                    channelPromise.setSuccess();
                } else {
                    channelPromise.setFailure(wrapStreamClosedError(cause));
                    closeForcibly();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void writeComplete(ChannelFuture channelFuture, ChannelPromise channelPromise) {
                Throwable cause = channelFuture.cause();
                if (cause == null) {
                    channelPromise.setSuccess();
                    return;
                }
                Throwable wrapStreamClosedError = wrapStreamClosedError(cause);
                channelPromise.setFailure(wrapStreamClosedError);
                if (wrapStreamClosedError instanceof ClosedChannelException) {
                    if (!DefaultHttp2StreamChannel.this.config.isAutoClose()) {
                        DefaultHttp2StreamChannel.this.outboundClosed = true;
                    } else {
                        closeForcibly();
                    }
                }
            }

            private Throwable wrapStreamClosedError(Throwable th) {
                return ((th instanceof Http2Exception) && ((Http2Exception) th).error() == Http2Error.STREAM_CLOSED) ? new ClosedChannelException().initCause(th) : th;
            }

            private Http2StreamFrame validateStreamFrame(Http2StreamFrame http2StreamFrame) {
                if (http2StreamFrame.stream() == null || http2StreamFrame.stream() == DefaultHttp2StreamChannel.this.stream) {
                    return http2StreamFrame;
                }
                String obj = http2StreamFrame.toString();
                ReferenceCountUtil.release(http2StreamFrame);
                throw new IllegalArgumentException("Stream " + http2StreamFrame.stream() + " must not be set on the frame: " + obj);
            }

            private ChannelFuture write0(Object obj) {
                ChannelPromise newPromise = Http2MultiplexCodec.this.ctx.newPromise();
                Http2MultiplexCodec.this.write(Http2MultiplexCodec.this.ctx, obj, newPromise);
                return newPromise;
            }

            @Override // io.netty.channel.Channel.Unsafe
            public void flush() {
                if (this.writeDoneAndNoFlush) {
                    try {
                        if (!DefaultHttp2StreamChannel.this.inFireChannelReadComplete) {
                            Http2MultiplexCodec.this.flush0(Http2MultiplexCodec.this.ctx);
                        }
                    } finally {
                        this.writeDoneAndNoFlush = false;
                    }
                }
            }

            @Override // io.netty.channel.Channel.Unsafe
            public ChannelPromise voidPromise() {
                return this.unsafeVoidPromise;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public final class Http2StreamChannelConfig extends DefaultChannelConfig {
            Http2StreamChannelConfig(Channel channel) {
                super(channel);
                setRecvByteBufAllocator(new Http2StreamChannelRecvByteBufAllocator());
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            public int getWriteBufferHighWaterMark() {
                return Math.min(DefaultHttp2StreamChannel.this.parent().config().getWriteBufferHighWaterMark(), Http2MultiplexCodec.this.initialOutboundStreamWindow);
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            public int getWriteBufferLowWaterMark() {
                return Math.min(DefaultHttp2StreamChannel.this.parent().config().getWriteBufferLowWaterMark(), Http2MultiplexCodec.this.initialOutboundStreamWindow);
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            public MessageSizeEstimator getMessageSizeEstimator() {
                return FlowControlledFrameSizeEstimator.INSTANCE;
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            public WriteBufferWaterMark getWriteBufferWaterMark() {
                int writeBufferHighWaterMark = getWriteBufferHighWaterMark();
                return new WriteBufferWaterMark(writeBufferHighWaterMark, writeBufferHighWaterMark);
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            @Deprecated
            public ChannelConfig setWriteBufferHighWaterMark(int i) {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            @Deprecated
            public ChannelConfig setWriteBufferLowWaterMark(int i) {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            public ChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
                throw new UnsupportedOperationException();
            }

            @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
            public ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
                if (!(recvByteBufAllocator.newHandle() instanceof RecvByteBufAllocator.ExtendedHandle)) {
                    throw new IllegalArgumentException("allocator.newHandle() must return an object of type: " + RecvByteBufAllocator.ExtendedHandle.class);
                }
                super.setRecvByteBufAllocator(recvByteBufAllocator);
                return this;
            }
        }
    }
}
