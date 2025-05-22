package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class Http2MultiplexCodec extends Http2FrameCodec {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    volatile ChannelHandlerContext ctx;
    private int idCount;
    private final ChannelHandler inboundStreamHandler;
    private boolean parentReadInProgress;
    private final Queue<AbstractHttp2StreamChannel> readCompletePendingQueue;
    private final ChannelHandler upgradeStreamHandler;

    static /* synthetic */ int access$004(Http2MultiplexCodec http2MultiplexCodec) {
        int i = http2MultiplexCodec.idCount + 1;
        http2MultiplexCodec.idCount = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2MultiplexCodec(Http2ConnectionEncoder http2ConnectionEncoder, Http2ConnectionDecoder http2ConnectionDecoder, Http2Settings http2Settings, ChannelHandler channelHandler, ChannelHandler channelHandler2, boolean z) {
        super(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings, z);
        this.readCompletePendingQueue = new MaxCapacityQueue(new ArrayDeque(8), 100);
        this.inboundStreamHandler = channelHandler;
        this.upgradeStreamHandler = channelHandler2;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    public void onHttpClientUpgrade() throws Http2Exception {
        if (this.upgradeStreamHandler == null) {
            throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Client is misconfigured for upgrade requests", new Object[0]);
        }
        super.onHttpClientUpgrade();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec
    public final void handlerAdded0(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.executor() != channelHandlerContext.channel().eventLoop()) {
            throw new IllegalStateException("EventExecutor must be EventLoop of Channel");
        }
        this.ctx = channelHandlerContext;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    public final void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerRemoved0(channelHandlerContext);
        this.readCompletePendingQueue.clear();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec
    final void onHttp2Frame(ChannelHandlerContext channelHandlerContext, Http2Frame http2Frame) {
        if (http2Frame instanceof Http2StreamFrame) {
            Http2StreamFrame http2StreamFrame = (Http2StreamFrame) http2Frame;
            ((AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2StreamFrame.stream()).attachment).fireChildRead(http2StreamFrame);
        } else {
            if (http2Frame instanceof Http2GoAwayFrame) {
                onHttp2GoAwayFrame(channelHandlerContext, (Http2GoAwayFrame) http2Frame);
            }
            channelHandlerContext.fireChannelRead((Object) http2Frame);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2MultiplexCodec$2 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C65632 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = new int[Http2Stream.State.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.HALF_CLOSED_LOCAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.HALF_CLOSED_REMOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec
    final void onHttp2StreamStateChanged(ChannelHandlerContext channelHandlerContext, Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream) {
        Http2MultiplexCodecStreamChannel http2MultiplexCodecStreamChannel;
        AbstractHttp2StreamChannel abstractHttp2StreamChannel;
        int i = C65632.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[defaultHttp2FrameStream.state().ordinal()];
        if (i != 1) {
            if (i != 2 && i != 3) {
                if (i == 4 && (abstractHttp2StreamChannel = (AbstractHttp2StreamChannel) defaultHttp2FrameStream.attachment) != null) {
                    abstractHttp2StreamChannel.streamClosed();
                    return;
                }
                return;
            }
        } else if (defaultHttp2FrameStream.mo3917id() != 1) {
            return;
        }
        if (defaultHttp2FrameStream.attachment != null) {
            return;
        }
        if (defaultHttp2FrameStream.mo3917id() == 1 && !connection().isServer()) {
            http2MultiplexCodecStreamChannel = new Http2MultiplexCodecStreamChannel(defaultHttp2FrameStream, this.upgradeStreamHandler);
            http2MultiplexCodecStreamChannel.closeOutbound();
        } else {
            http2MultiplexCodecStreamChannel = new Http2MultiplexCodecStreamChannel(defaultHttp2FrameStream, this.inboundStreamHandler);
        }
        ChannelFuture register = channelHandlerContext.channel().eventLoop().register(http2MultiplexCodecStreamChannel);
        if (register.isDone()) {
            Http2MultiplexHandler.registerDone(register);
        } else {
            register.addListener((GenericFutureListener<? extends Future<? super Void>>) Http2MultiplexHandler.CHILD_CHANNEL_REGISTRATION_LISTENER);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Http2StreamChannel newOutboundStream() {
        return new Http2MultiplexCodecStreamChannel(newStream(), null);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec
    final void onHttp2FrameStreamException(ChannelHandlerContext channelHandlerContext, Http2FrameStreamException http2FrameStreamException) {
        AbstractHttp2StreamChannel abstractHttp2StreamChannel = (AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStreamException.stream()).attachment;
        try {
            abstractHttp2StreamChannel.pipeline().fireExceptionCaught(http2FrameStreamException.getCause());
        } finally {
            abstractHttp2StreamChannel.unsafe().closeForcibly();
        }
    }

    private void onHttp2GoAwayFrame(ChannelHandlerContext channelHandlerContext, final Http2GoAwayFrame http2GoAwayFrame) {
        try {
            forEachActiveStream(new Http2FrameStreamVisitor() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2MultiplexCodec.1
                @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameStreamVisitor
                public boolean visit(Http2FrameStream http2FrameStream) {
                    int mo3917id = http2FrameStream.mo3917id();
                    AbstractHttp2StreamChannel abstractHttp2StreamChannel = (AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStream).attachment;
                    if (mo3917id <= http2GoAwayFrame.lastStreamId() || !Http2MultiplexCodec.this.connection().local().isValidStreamId(mo3917id)) {
                        return true;
                    }
                    abstractHttp2StreamChannel.pipeline().fireUserEventTriggered((Object) http2GoAwayFrame.retainedDuplicate());
                    return true;
                }
            });
        } catch (Http2Exception e) {
            channelHandlerContext.fireExceptionCaught((Throwable) e);
            channelHandlerContext.close();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public final void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        processPendingReadCompleteQueue();
        channelReadComplete0(channelHandlerContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPendingReadCompleteQueue() {
        this.parentReadInProgress = true;
        while (true) {
            try {
                AbstractHttp2StreamChannel poll = this.readCompletePendingQueue.poll();
                if (poll == null) {
                    return;
                } else {
                    poll.fireChildReadComplete();
                }
            } finally {
                this.parentReadInProgress = false;
                this.readCompletePendingQueue.clear();
                flush0(this.ctx);
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public final void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        this.parentReadInProgress = true;
        super.channelRead(channelHandlerContext, obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public final void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.channel().isWritable()) {
            forEachActiveStream(AbstractHttp2StreamChannel.WRITABLE_VISITOR);
        }
        super.channelWritabilityChanged(channelHandlerContext);
    }

    final void flush0(ChannelHandlerContext channelHandlerContext) {
        flush(channelHandlerContext);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class Http2MultiplexCodecStreamChannel extends AbstractHttp2StreamChannel {
        Http2MultiplexCodecStreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, ChannelHandler channelHandler) {
            super(defaultHttp2FrameStream, Http2MultiplexCodec.access$004(Http2MultiplexCodec.this), channelHandler);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected boolean isParentReadInProgress() {
            return Http2MultiplexCodec.this.parentReadInProgress;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected void addChannelToReadCompletePendingQueue() {
            while (!Http2MultiplexCodec.this.readCompletePendingQueue.offer(this)) {
                Http2MultiplexCodec.this.processPendingReadCompleteQueue();
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected ChannelHandlerContext parentContext() {
            return Http2MultiplexCodec.this.ctx;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected ChannelFuture write0(ChannelHandlerContext channelHandlerContext, Object obj) {
            ChannelPromise newPromise = channelHandlerContext.newPromise();
            Http2MultiplexCodec.this.write(channelHandlerContext, obj, newPromise);
            return newPromise;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel
        protected void flush0(ChannelHandlerContext channelHandlerContext) {
            Http2MultiplexCodec.this.flush0(channelHandlerContext);
        }
    }
}
