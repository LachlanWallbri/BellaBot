package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelId;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelProgressivePromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.VoidChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec;
import io.grpc.netty.shaded.io.netty.util.DefaultAttributeMap;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractHttp2StreamChannel extends DefaultAttributeMap implements Http2StreamChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_HTTP2_FRAME_SIZE = 9;
    private final ChannelId channelId;
    private final ChannelPromise closePromise;
    private Runnable fireChannelWritabilityChangedTask;
    private boolean firstFrameWritten;
    private int flowControlledBytes;
    private Queue<Object> inboundBuffer;
    private boolean outboundClosed;
    private final ChannelPipeline pipeline;
    private boolean readCompletePending;
    private volatile boolean registered;
    private final Http2FrameCodec.DefaultHttp2FrameStream stream;
    private volatile long totalPendingSize;
    private volatile int unwritable;
    static final Http2FrameStreamVisitor WRITABLE_VISITOR = new Http2FrameStreamVisitor() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.1
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameStreamVisitor
        public boolean visit(Http2FrameStream http2FrameStream) {
            ((AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStream).attachment).trySetWritable();
            return true;
        }
    };
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractHttp2StreamChannel.class);
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final AtomicLongFieldUpdater<AbstractHttp2StreamChannel> TOTAL_PENDING_SIZE_UPDATER = AtomicLongFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "totalPendingSize");
    private static final AtomicIntegerFieldUpdater<AbstractHttp2StreamChannel> UNWRITABLE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "unwritable");
    private final ChannelFutureListener windowUpdateFrameWriteListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.2
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) {
            AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(channelFuture, AbstractHttp2StreamChannel.this);
        }
    };
    private final Http2StreamChannelConfig config = new Http2StreamChannelConfig(this);
    private final Http2ChannelUnsafe unsafe = new Http2ChannelUnsafe();
    private ReadStatus readStatus = ReadStatus.IDLE;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum ReadStatus {
        IDLE,
        IN_PROGRESS,
        REQUESTED
    }

    protected abstract void addChannelToReadCompletePendingQueue();

    public boolean equals(Object obj) {
        return this == obj;
    }

    protected abstract boolean isParentReadInProgress();

    protected abstract ChannelHandlerContext parentContext();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class FlowControlledFrameSizeEstimator implements MessageSizeEstimator {
        static final FlowControlledFrameSizeEstimator INSTANCE = new FlowControlledFrameSizeEstimator();
        private static final MessageSizeEstimator.Handle HANDLE_INSTANCE = new MessageSizeEstimator.Handle() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.FlowControlledFrameSizeEstimator.1
            @Override // io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator.Handle
            public int size(Object obj) {
                if (obj instanceof Http2DataFrame) {
                    return (int) Math.min(2147483647L, ((Http2DataFrame) obj).initialFlowControlledBytes() + 9);
                }
                return 9;
            }
        };

        private FlowControlledFrameSizeEstimator() {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator
        public MessageSizeEstimator.Handle newHandle() {
            return HANDLE_INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void windowUpdateFrameWriteComplete(ChannelFuture channelFuture, Channel channel) {
        Throwable cause;
        Throwable cause2 = channelFuture.cause();
        if (cause2 != null) {
            if ((cause2 instanceof Http2FrameStreamException) && (cause = cause2.getCause()) != null) {
                cause2 = cause;
            }
            channel.pipeline().fireExceptionCaught(cause2);
            channel.unsafe().close(channel.unsafe().voidPromise());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractHttp2StreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, int i, ChannelHandler channelHandler) {
        this.stream = defaultHttp2FrameStream;
        defaultHttp2FrameStream.attachment = this;
        this.pipeline = new DefaultChannelPipeline(this) { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline
            public void incrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(j, true);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline
            public void decrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(j, true);
            }
        };
        this.closePromise = this.pipeline.newPromise();
        this.channelId = new Http2StreamChannelId(parent().mo3907id(), i);
        if (channelHandler != null) {
            this.pipeline.addLast(channelHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incrementPendingOutboundBytes(long j, boolean z) {
        if (j != 0 && TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, j) > config().getWriteBufferHighWaterMark()) {
            setUnwritable(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decrementPendingOutboundBytes(long j, boolean z) {
        if (j != 0 && TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, -j) < config().getWriteBufferLowWaterMark() && parent().isWritable()) {
            setWritable(z);
        }
    }

    final void trySetWritable() {
        if (this.totalPendingSize < config().getWriteBufferLowWaterMark()) {
            setWritable(false);
        }
    }

    private void setWritable(boolean z) {
        int i;
        int i2;
        do {
            i = this.unwritable;
            i2 = i & (-2);
        } while (!UNWRITABLE_UPDATER.compareAndSet(this, i, i2));
        if (i == 0 || i2 != 0) {
            return;
        }
        fireChannelWritabilityChanged(z);
    }

    private void setUnwritable(boolean z) {
        int i;
        int i2;
        do {
            i = this.unwritable;
            i2 = i | 1;
        } while (!UNWRITABLE_UPDATER.compareAndSet(this, i, i2));
        if (i != 0 || i2 == 0) {
            return;
        }
        fireChannelWritabilityChanged(z);
    }

    private void fireChannelWritabilityChanged(boolean z) {
        final ChannelPipeline pipeline = pipeline();
        if (z) {
            Runnable runnable = this.fireChannelWritabilityChangedTask;
            if (runnable == null) {
                runnable = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.4
                    @Override // java.lang.Runnable
                    public void run() {
                        pipeline.fireChannelWritabilityChanged();
                    }
                };
                this.fireChannelWritabilityChangedTask = runnable;
            }
            eventLoop().execute(runnable);
            return;
        }
        pipeline.fireChannelWritabilityChanged();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamChannel
    public Http2FrameStream stream() {
        return this.stream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeOutbound() {
        this.outboundClosed = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void streamClosed() {
        this.unsafe.readEOS();
        this.unsafe.doBeginRead();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return !this.closePromise.isDone();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return isOpen();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isWritable() {
        return this.unwritable == 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    /* renamed from: id */
    public ChannelId mo3907id() {
        return this.channelId;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public EventLoop eventLoop() {
        return parent().eventLoop();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public Channel parent() {
        return parentContext().channel();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isRegistered() {
        return this.registered;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public SocketAddress localAddress() {
        return parent().localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public SocketAddress remoteAddress() {
        return parent().remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelFuture closeFuture() {
        return this.closePromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public long bytesBeforeUnwritable() {
        long writeBufferHighWaterMark = config().getWriteBufferHighWaterMark() - this.totalPendingSize;
        if (writeBufferHighWaterMark <= 0 || !isWritable()) {
            return 0L;
        }
        return writeBufferHighWaterMark;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public long bytesBeforeWritable() {
        long writeBufferLowWaterMark = this.totalPendingSize - config().getWriteBufferLowWaterMark();
        if (writeBufferLowWaterMark <= 0 || isWritable()) {
            return 0L;
        }
        return writeBufferLowWaterMark;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public Channel.Unsafe unsafe() {
        return this.unsafe;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelPipeline pipeline() {
        return this.pipeline;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ByteBufAllocator alloc() {
        return config().getAllocator();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public Channel read() {
        pipeline().read();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public Channel flush() {
        pipeline().flush();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(SocketAddress socketAddress) {
        return pipeline().bind(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress) {
        return pipeline().connect(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return pipeline().connect(socketAddress, socketAddress2);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect() {
        return pipeline().disconnect();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close() {
        return pipeline().close();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister() {
        return pipeline().deregister();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return pipeline().bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return pipeline().connect(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        return pipeline().connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect(ChannelPromise channelPromise) {
        return pipeline().disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close(ChannelPromise channelPromise) {
        return pipeline().close(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister(ChannelPromise channelPromise) {
        return pipeline().deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj) {
        return pipeline().write(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
        return pipeline().write(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
        return pipeline().writeAndFlush(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj) {
        return pipeline().writeAndFlush(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelPromise newPromise() {
        return pipeline().newPromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelProgressivePromise newProgressivePromise() {
        return pipeline().newProgressivePromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newSucceededFuture() {
        return pipeline().newSucceededFuture();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newFailedFuture(Throwable th) {
        return pipeline().newFailedFuture(th);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelPromise voidPromise() {
        return pipeline().voidPromise();
    }

    public int hashCode() {
        return mo3907id().hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(Channel channel) {
        if (this == channel) {
            return 0;
        }
        return mo3907id().compareTo(channel.mo3907id());
    }

    public String toString() {
        return parent().toString() + "(H2 - " + this.stream + ')';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fireChildRead(Http2Frame http2Frame) {
        if (!isActive()) {
            ReferenceCountUtil.release(http2Frame);
            return;
        }
        if (this.readStatus != ReadStatus.IDLE) {
            RecvByteBufAllocator.Handle recvBufAllocHandle = this.unsafe.recvBufAllocHandle();
            this.unsafe.doRead0(http2Frame, recvBufAllocHandle);
            if (recvBufAllocHandle.continueReading()) {
                maybeAddChannelToReadCompletePendingQueue();
                return;
            } else {
                this.unsafe.notifyReadComplete(recvBufAllocHandle, true);
                return;
            }
        }
        if (this.inboundBuffer == null) {
            this.inboundBuffer = new ArrayDeque(4);
        }
        this.inboundBuffer.add(http2Frame);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fireChildReadComplete() {
        Http2ChannelUnsafe http2ChannelUnsafe = this.unsafe;
        http2ChannelUnsafe.notifyReadComplete(http2ChannelUnsafe.recvBufAllocHandle(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class Http2ChannelUnsafe implements Channel.Unsafe {
        private boolean closeInitiated;
        private boolean readEOS;
        private RecvByteBufAllocator.Handle recvHandle;
        private final VoidChannelPromise unsafeVoidPromise;
        private boolean writeDoneAndNoFlush;

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public ChannelOutboundBuffer outboundBuffer() {
            return null;
        }

        private Http2ChannelUnsafe() {
            this.unsafeVoidPromise = new VoidChannelPromise(AbstractHttp2StreamChannel.this, false);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                channelPromise.setFailure((Throwable) new UnsupportedOperationException());
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public RecvByteBufAllocator.Handle recvBufAllocHandle() {
            if (this.recvHandle == null) {
                this.recvHandle = AbstractHttp2StreamChannel.this.config().getRecvByteBufAllocator().newHandle();
                this.recvHandle.reset(AbstractHttp2StreamChannel.this.config());
            }
            return this.recvHandle;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public SocketAddress localAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().localAddress();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public SocketAddress remoteAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().remoteAddress();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void register(EventLoop eventLoop, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                if (!AbstractHttp2StreamChannel.this.registered) {
                    AbstractHttp2StreamChannel.this.registered = true;
                    channelPromise.setSuccess();
                    AbstractHttp2StreamChannel.this.pipeline().fireChannelRegistered();
                    if (AbstractHttp2StreamChannel.this.isActive()) {
                        AbstractHttp2StreamChannel.this.pipeline().fireChannelActive();
                        return;
                    }
                    return;
                }
                channelPromise.setFailure((Throwable) new UnsupportedOperationException("Re-register is not supported"));
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                channelPromise.setFailure((Throwable) new UnsupportedOperationException());
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void disconnect(ChannelPromise channelPromise) {
            close(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void close(final ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                if (this.closeInitiated) {
                    if (AbstractHttp2StreamChannel.this.closePromise.isDone()) {
                        channelPromise.setSuccess();
                        return;
                    } else {
                        if (channelPromise instanceof VoidChannelPromise) {
                            return;
                        }
                        AbstractHttp2StreamChannel.this.closePromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.1
                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) {
                                channelPromise.setSuccess();
                            }
                        });
                        return;
                    }
                }
                this.closeInitiated = true;
                AbstractHttp2StreamChannel.this.readCompletePending = false;
                boolean isActive = AbstractHttp2StreamChannel.this.isActive();
                if (AbstractHttp2StreamChannel.this.parent().isActive() && !this.readEOS && Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream.mo3917id())) {
                    write(new DefaultHttp2ResetFrame(Http2Error.CANCEL).stream(AbstractHttp2StreamChannel.this.stream()), AbstractHttp2StreamChannel.this.unsafe().voidPromise());
                    flush();
                }
                if (AbstractHttp2StreamChannel.this.inboundBuffer != null) {
                    while (true) {
                        Object poll = AbstractHttp2StreamChannel.this.inboundBuffer.poll();
                        if (poll == null) {
                            break;
                        } else {
                            ReferenceCountUtil.release(poll);
                        }
                    }
                    AbstractHttp2StreamChannel.this.inboundBuffer = null;
                }
                AbstractHttp2StreamChannel.this.outboundClosed = true;
                AbstractHttp2StreamChannel.this.closePromise.setSuccess();
                channelPromise.setSuccess();
                fireChannelInactiveAndDeregister(voidPromise(), isActive);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void closeForcibly() {
            close(AbstractHttp2StreamChannel.this.unsafe().voidPromise());
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void deregister(ChannelPromise channelPromise) {
            fireChannelInactiveAndDeregister(channelPromise, false);
        }

        private void fireChannelInactiveAndDeregister(final ChannelPromise channelPromise, final boolean z) {
            if (channelPromise.setUncancellable()) {
                if (!AbstractHttp2StreamChannel.this.registered) {
                    channelPromise.setSuccess();
                } else {
                    invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (z) {
                                AbstractHttp2StreamChannel.this.pipeline.fireChannelInactive();
                            }
                            if (AbstractHttp2StreamChannel.this.registered) {
                                AbstractHttp2StreamChannel.this.registered = false;
                                AbstractHttp2StreamChannel.this.pipeline.fireChannelUnregistered();
                            }
                            Http2ChannelUnsafe.this.safeSetSuccess(channelPromise);
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void safeSetSuccess(ChannelPromise channelPromise) {
            if ((channelPromise instanceof VoidChannelPromise) || channelPromise.trySuccess()) {
                return;
            }
            AbstractHttp2StreamChannel.logger.warn("Failed to mark a promise as success because it is done already: {}", channelPromise);
        }

        private void invokeLater(Runnable runnable) {
            try {
                AbstractHttp2StreamChannel.this.eventLoop().execute(runnable);
            } catch (RejectedExecutionException e) {
                AbstractHttp2StreamChannel.logger.warn("Can't invoke task later as EventLoop rejected it", (Throwable) e);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void beginRead() {
            if (AbstractHttp2StreamChannel.this.isActive()) {
                updateLocalWindowIfNeeded();
                int i = C65225.f8375x5f8c880f[AbstractHttp2StreamChannel.this.readStatus.ordinal()];
                if (i == 1) {
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IN_PROGRESS;
                    doBeginRead();
                } else {
                    if (i != 2) {
                        return;
                    }
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.REQUESTED;
                }
            }
        }

        private Object pollQueuedMessage() {
            if (AbstractHttp2StreamChannel.this.inboundBuffer == null) {
                return null;
            }
            return AbstractHttp2StreamChannel.this.inboundBuffer.poll();
        }

        void doBeginRead() {
            boolean z;
            while (AbstractHttp2StreamChannel.this.readStatus != ReadStatus.IDLE) {
                Object pollQueuedMessage = pollQueuedMessage();
                if (pollQueuedMessage == null) {
                    if (this.readEOS) {
                        AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
                    }
                    flush();
                    return;
                }
                RecvByteBufAllocator.Handle recvBufAllocHandle = recvBufAllocHandle();
                recvBufAllocHandle.reset(AbstractHttp2StreamChannel.this.config());
                boolean z2 = false;
                while (true) {
                    doRead0((Http2Frame) pollQueuedMessage, recvBufAllocHandle);
                    if (!this.readEOS) {
                        z = recvBufAllocHandle.continueReading();
                        if (!z) {
                            break;
                        } else {
                            z2 = z;
                        }
                    }
                    pollQueuedMessage = pollQueuedMessage();
                    if (pollQueuedMessage == null) {
                        z = z2;
                        break;
                    }
                }
                if (z && AbstractHttp2StreamChannel.this.isParentReadInProgress() && !this.readEOS) {
                    AbstractHttp2StreamChannel.this.maybeAddChannelToReadCompletePendingQueue();
                } else {
                    notifyReadComplete(recvBufAllocHandle, true);
                }
            }
        }

        void readEOS() {
            this.readEOS = true;
        }

        private void updateLocalWindowIfNeeded() {
            if (AbstractHttp2StreamChannel.this.flowControlledBytes != 0) {
                int i = AbstractHttp2StreamChannel.this.flowControlledBytes;
                AbstractHttp2StreamChannel.this.flowControlledBytes = 0;
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                ChannelFuture write0 = abstractHttp2StreamChannel.write0(abstractHttp2StreamChannel.parentContext(), new DefaultHttp2WindowUpdateFrame(i).stream((Http2FrameStream) AbstractHttp2StreamChannel.this.stream));
                this.writeDoneAndNoFlush = true;
                if (write0.isDone()) {
                    AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(write0, AbstractHttp2StreamChannel.this);
                } else {
                    write0.addListener((GenericFutureListener<? extends Future<? super Void>>) AbstractHttp2StreamChannel.this.windowUpdateFrameWriteListener);
                }
            }
        }

        void notifyReadComplete(RecvByteBufAllocator.Handle handle, boolean z) {
            if (AbstractHttp2StreamChannel.this.readCompletePending || z) {
                AbstractHttp2StreamChannel.this.readCompletePending = false;
                if (AbstractHttp2StreamChannel.this.readStatus == ReadStatus.REQUESTED) {
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IN_PROGRESS;
                } else {
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IDLE;
                }
                handle.readComplete();
                AbstractHttp2StreamChannel.this.pipeline().fireChannelReadComplete();
                flush();
                if (this.readEOS) {
                    AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
                }
            }
        }

        void doRead0(Http2Frame http2Frame, RecvByteBufAllocator.Handle handle) {
            int i;
            if (http2Frame instanceof Http2DataFrame) {
                i = ((Http2DataFrame) http2Frame).initialFlowControlledBytes();
                AbstractHttp2StreamChannel.this.flowControlledBytes += i;
            } else {
                i = 9;
            }
            handle.attemptedBytesRead(i);
            handle.lastBytesRead(i);
            handle.incMessagesRead(1);
            AbstractHttp2StreamChannel.this.pipeline().fireChannelRead((Object) http2Frame);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void write(Object obj, ChannelPromise channelPromise) {
            if (!channelPromise.setUncancellable()) {
                ReferenceCountUtil.release(obj);
                return;
            }
            if (!AbstractHttp2StreamChannel.this.isActive() || (AbstractHttp2StreamChannel.this.outboundClosed && ((obj instanceof Http2HeadersFrame) || (obj instanceof Http2DataFrame)))) {
                ReferenceCountUtil.release(obj);
                channelPromise.setFailure((Throwable) new ClosedChannelException());
                return;
            }
            try {
                if (obj instanceof Http2StreamFrame) {
                    writeHttp2StreamFrame(validateStreamFrame((Http2StreamFrame) obj).stream(AbstractHttp2StreamChannel.this.stream()), channelPromise);
                    return;
                }
                String obj2 = obj.toString();
                ReferenceCountUtil.release(obj);
                channelPromise.setFailure((Throwable) new IllegalArgumentException("Message must be an " + StringUtil.simpleClassName((Class<?>) Http2StreamFrame.class) + ": " + obj2));
            } catch (Throwable th) {
                channelPromise.tryFailure(th);
            }
        }

        private void writeHttp2StreamFrame(Http2StreamFrame http2StreamFrame, final ChannelPromise channelPromise) {
            if (AbstractHttp2StreamChannel.this.firstFrameWritten || Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream().mo3917id()) || (http2StreamFrame instanceof Http2HeadersFrame)) {
                final boolean z = AbstractHttp2StreamChannel.this.firstFrameWritten ? false : AbstractHttp2StreamChannel.this.firstFrameWritten = true;
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                ChannelFuture write0 = abstractHttp2StreamChannel.write0(abstractHttp2StreamChannel.parentContext(), http2StreamFrame);
                if (write0.isDone()) {
                    if (z) {
                        firstWriteComplete(write0, channelPromise);
                        return;
                    } else {
                        writeComplete(write0, channelPromise);
                        return;
                    }
                }
                final long size = FlowControlledFrameSizeEstimator.HANDLE_INSTANCE.size(http2StreamFrame);
                AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(size, false);
                write0.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.3
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) {
                        if (z) {
                            Http2ChannelUnsafe.this.firstWriteComplete(channelFuture, channelPromise);
                        } else {
                            Http2ChannelUnsafe.this.writeComplete(channelFuture, channelPromise);
                        }
                        AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(size, false);
                    }
                });
                this.writeDoneAndNoFlush = true;
                return;
            }
            ReferenceCountUtil.release(http2StreamFrame);
            channelPromise.setFailure((Throwable) new IllegalArgumentException("The first frame must be a headers frame. Was: " + http2StreamFrame.name()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void firstWriteComplete(ChannelFuture channelFuture, ChannelPromise channelPromise) {
            Throwable cause = channelFuture.cause();
            if (cause == null) {
                channelPromise.setSuccess();
            } else {
                closeForcibly();
                channelPromise.setFailure(wrapStreamClosedError(cause));
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
            if (wrapStreamClosedError instanceof IOException) {
                if (!AbstractHttp2StreamChannel.this.config.isAutoClose()) {
                    AbstractHttp2StreamChannel.this.outboundClosed = true;
                } else {
                    closeForcibly();
                }
            }
            channelPromise.setFailure(wrapStreamClosedError);
        }

        private Throwable wrapStreamClosedError(Throwable th) {
            return ((th instanceof Http2Exception) && ((Http2Exception) th).error() == Http2Error.STREAM_CLOSED) ? new ClosedChannelException().initCause(th) : th;
        }

        private Http2StreamFrame validateStreamFrame(Http2StreamFrame http2StreamFrame) {
            if (http2StreamFrame.stream() == null || http2StreamFrame.stream() == AbstractHttp2StreamChannel.this.stream) {
                return http2StreamFrame;
            }
            String obj = http2StreamFrame.toString();
            ReferenceCountUtil.release(http2StreamFrame);
            throw new IllegalArgumentException("Stream " + http2StreamFrame.stream() + " must not be set on the frame: " + obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void flush() {
            if (!this.writeDoneAndNoFlush || AbstractHttp2StreamChannel.this.isParentReadInProgress()) {
                return;
            }
            this.writeDoneAndNoFlush = false;
            AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
            abstractHttp2StreamChannel.flush0(abstractHttp2StreamChannel.parentContext());
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public ChannelPromise voidPromise() {
            return this.unsafeVoidPromise;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel$5 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C65225 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus */
        static final /* synthetic */ int[] f8375x5f8c880f = new int[ReadStatus.values().length];

        static {
            try {
                f8375x5f8c880f[ReadStatus.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8375x5f8c880f[ReadStatus.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Http2StreamChannelConfig extends DefaultChannelConfig {
        Http2StreamChannelConfig(Channel channel) {
            super(channel);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public MessageSizeEstimator getMessageSizeEstimator() {
            return FlowControlledFrameSizeEstimator.INSTANCE;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
            if (!(recvByteBufAllocator.newHandle() instanceof RecvByteBufAllocator.ExtendedHandle)) {
                throw new IllegalArgumentException("allocator.newHandle() must return an object of type: " + RecvByteBufAllocator.ExtendedHandle.class);
            }
            super.setRecvByteBufAllocator(recvByteBufAllocator);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAddChannelToReadCompletePendingQueue() {
        if (this.readCompletePending) {
            return;
        }
        this.readCompletePending = true;
        addChannelToReadCompletePendingQueue();
    }

    protected void flush0(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.flush();
    }

    protected ChannelFuture write0(ChannelHandlerContext channelHandlerContext, Object obj) {
        ChannelPromise newPromise = channelHandlerContext.newPromise();
        channelHandlerContext.write(obj, newPromise);
        return newPromise;
    }
}
