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
import io.netty.channel.EventLoop;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.VoidChannelPromise;
import io.netty.handler.codec.http2.Http2FrameCodec;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
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
  classes5.dex
 */
/* loaded from: classes8.dex */
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
    static final Http2FrameStreamVisitor WRITABLE_VISITOR = new Http2FrameStreamVisitor() { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.1
        @Override // io.netty.handler.codec.http2.Http2FrameStreamVisitor
        public boolean visit(Http2FrameStream http2FrameStream) {
            ((AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStream).attachment).trySetWritable();
            return true;
        }
    };
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractHttp2StreamChannel.class);
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final AtomicLongFieldUpdater<AbstractHttp2StreamChannel> TOTAL_PENDING_SIZE_UPDATER = AtomicLongFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "totalPendingSize");
    private static final AtomicIntegerFieldUpdater<AbstractHttp2StreamChannel> UNWRITABLE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "unwritable");
    private final Http2StreamChannelConfig config = new Http2StreamChannelConfig(this);
    private final Http2ChannelUnsafe unsafe = new Http2ChannelUnsafe();
    private ReadStatus readStatus = ReadStatus.IDLE;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
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
      classes5.dex
     */
    /* loaded from: classes8.dex */
    public static final class FlowControlledFrameSizeEstimator implements MessageSizeEstimator {
        static final FlowControlledFrameSizeEstimator INSTANCE = new FlowControlledFrameSizeEstimator();
        private static final MessageSizeEstimator.Handle HANDLE_INSTANCE = new MessageSizeEstimator.Handle() { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.FlowControlledFrameSizeEstimator.1
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractHttp2StreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, int i, ChannelHandler channelHandler) {
        this.stream = defaultHttp2FrameStream;
        defaultHttp2FrameStream.attachment = this;
        this.pipeline = new DefaultChannelPipeline(this) { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.channel.DefaultChannelPipeline
            public void incrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(j, true);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.channel.DefaultChannelPipeline
            public void decrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(j, true);
            }
        };
        this.closePromise = this.pipeline.newPromise();
        this.channelId = new Http2StreamChannelId(parent().mo3929id(), i);
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
                runnable = new Runnable() { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.3
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

    @Override // io.netty.handler.codec.http2.Http2StreamChannel
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

    @Override // io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
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
        return this.unwritable == 0;
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
        return parentContext().channel();
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
        long writeBufferHighWaterMark = config().getWriteBufferHighWaterMark() - this.totalPendingSize;
        if (writeBufferHighWaterMark <= 0 || !isWritable()) {
            return 0L;
        }
        return writeBufferHighWaterMark;
    }

    @Override // io.netty.channel.Channel
    public long bytesBeforeWritable() {
        long writeBufferLowWaterMark = this.totalPendingSize - config().getWriteBufferLowWaterMark();
        if (writeBufferLowWaterMark <= 0 || isWritable()) {
            return 0L;
        }
        return writeBufferLowWaterMark;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fireChildRead(Http2Frame http2Frame) {
        if (!isActive()) {
            ReferenceCountUtil.release(http2Frame);
            return;
        }
        if (this.readStatus != ReadStatus.IDLE) {
            RecvByteBufAllocator.Handle recvBufAllocHandle = this.unsafe.recvBufAllocHandle();
            this.flowControlledBytes += this.unsafe.doRead0(http2Frame, recvBufAllocHandle);
            if (recvBufAllocHandle.continueReading()) {
                if (this.readCompletePending) {
                    return;
                }
                this.readCompletePending = true;
                addChannelToReadCompletePendingQueue();
                return;
            }
            this.unsafe.notifyReadComplete(recvBufAllocHandle, true);
            return;
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
      classes5.dex
     */
    /* loaded from: classes8.dex */
    public final class Http2ChannelUnsafe implements Channel.Unsafe {
        private boolean closeInitiated;
        private boolean readEOS;
        private RecvByteBufAllocator.Handle recvHandle;
        private final VoidChannelPromise unsafeVoidPromise;
        private boolean writeDoneAndNoFlush;

        @Override // io.netty.channel.Channel.Unsafe
        public ChannelOutboundBuffer outboundBuffer() {
            return null;
        }

        private Http2ChannelUnsafe() {
            this.unsafeVoidPromise = new VoidChannelPromise(AbstractHttp2StreamChannel.this, false);
        }

        @Override // io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                channelPromise.setFailure((Throwable) new UnsupportedOperationException());
            }
        }

        @Override // io.netty.channel.Channel.Unsafe
        public RecvByteBufAllocator.Handle recvBufAllocHandle() {
            if (this.recvHandle == null) {
                this.recvHandle = AbstractHttp2StreamChannel.this.config().getRecvByteBufAllocator().newHandle();
                this.recvHandle.reset(AbstractHttp2StreamChannel.this.config());
            }
            return this.recvHandle;
        }

        @Override // io.netty.channel.Channel.Unsafe
        public SocketAddress localAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().localAddress();
        }

        @Override // io.netty.channel.Channel.Unsafe
        public SocketAddress remoteAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().remoteAddress();
        }

        @Override // io.netty.channel.Channel.Unsafe
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
                    if (AbstractHttp2StreamChannel.this.closePromise.isDone()) {
                        channelPromise.setSuccess();
                        return;
                    } else {
                        if (channelPromise instanceof VoidChannelPromise) {
                            return;
                        }
                        AbstractHttp2StreamChannel.this.closePromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.1
                            @Override // io.netty.util.concurrent.GenericFutureListener
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
                updateLocalWindowIfNeeded();
                if (AbstractHttp2StreamChannel.this.parent().isActive() && !this.readEOS && Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream.mo3941id())) {
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

        @Override // io.netty.channel.Channel.Unsafe
        public void closeForcibly() {
            close(AbstractHttp2StreamChannel.this.unsafe().voidPromise());
        }

        @Override // io.netty.channel.Channel.Unsafe
        public void deregister(ChannelPromise channelPromise) {
            fireChannelInactiveAndDeregister(channelPromise, false);
        }

        private void fireChannelInactiveAndDeregister(final ChannelPromise channelPromise, final boolean z) {
            if (channelPromise.setUncancellable()) {
                if (!AbstractHttp2StreamChannel.this.registered) {
                    channelPromise.setSuccess();
                } else {
                    invokeLater(new Runnable() { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.2
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

        @Override // io.netty.channel.Channel.Unsafe
        public void beginRead() {
            if (AbstractHttp2StreamChannel.this.isActive()) {
                updateLocalWindowIfNeeded();
                int i = C71814.f8505x5f8c880f[AbstractHttp2StreamChannel.this.readStatus.ordinal()];
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
                        return;
                    }
                    return;
                }
                RecvByteBufAllocator.Handle recvBufAllocHandle = recvBufAllocHandle();
                recvBufAllocHandle.reset(AbstractHttp2StreamChannel.this.config());
                boolean z2 = false;
                while (true) {
                    AbstractHttp2StreamChannel.this.flowControlledBytes += doRead0((Http2Frame) pollQueuedMessage, recvBufAllocHandle);
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
                    if (!AbstractHttp2StreamChannel.this.readCompletePending) {
                        AbstractHttp2StreamChannel.this.readCompletePending = true;
                        AbstractHttp2StreamChannel.this.addChannelToReadCompletePendingQueue();
                    }
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
                abstractHttp2StreamChannel.write0(abstractHttp2StreamChannel.parentContext(), new DefaultHttp2WindowUpdateFrame(i).stream((Http2FrameStream) AbstractHttp2StreamChannel.this.stream));
                this.writeDoneAndNoFlush = true;
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

        int doRead0(Http2Frame http2Frame, RecvByteBufAllocator.Handle handle) {
            AbstractHttp2StreamChannel.this.pipeline().fireChannelRead((Object) http2Frame);
            handle.incMessagesRead(1);
            if (http2Frame instanceof Http2DataFrame) {
                int initialFlowControlledBytes = ((Http2DataFrame) http2Frame).initialFlowControlledBytes();
                handle.attemptedBytesRead(initialFlowControlledBytes);
                handle.lastBytesRead(initialFlowControlledBytes);
                return initialFlowControlledBytes;
            }
            handle.attemptedBytesRead(9);
            handle.lastBytesRead(9);
            return 0;
        }

        @Override // io.netty.channel.Channel.Unsafe
        public void write(Object obj, final ChannelPromise channelPromise) {
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
                    Http2StreamFrame stream = validateStreamFrame((Http2StreamFrame) obj).stream(AbstractHttp2StreamChannel.this.stream());
                    if (!AbstractHttp2StreamChannel.this.firstFrameWritten && !Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream().mo3941id())) {
                        if (stream instanceof Http2HeadersFrame) {
                            AbstractHttp2StreamChannel.this.firstFrameWritten = true;
                            ChannelFuture write0 = AbstractHttp2StreamChannel.this.write0(AbstractHttp2StreamChannel.this.parentContext(), stream);
                            if (!write0.isDone()) {
                                final long size = FlowControlledFrameSizeEstimator.HANDLE_INSTANCE.size(obj);
                                AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(size, false);
                                write0.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.3
                                    @Override // io.netty.util.concurrent.GenericFutureListener
                                    public void operationComplete(ChannelFuture channelFuture) {
                                        Http2ChannelUnsafe.this.firstWriteComplete(channelFuture, channelPromise);
                                        AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(size, false);
                                    }
                                });
                                this.writeDoneAndNoFlush = true;
                                return;
                            }
                            firstWriteComplete(write0, channelPromise);
                            return;
                        }
                        ReferenceCountUtil.release(stream);
                        channelPromise.setFailure((Throwable) new IllegalArgumentException("The first frame must be a headers frame. Was: " + stream.name()));
                        return;
                    }
                    ChannelFuture write02 = AbstractHttp2StreamChannel.this.write0(AbstractHttp2StreamChannel.this.parentContext(), obj);
                    if (!write02.isDone()) {
                        final long size2 = FlowControlledFrameSizeEstimator.HANDLE_INSTANCE.size(obj);
                        AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(size2, false);
                        write02.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.4
                            @Override // io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) {
                                Http2ChannelUnsafe.this.writeComplete(channelFuture, channelPromise);
                                AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(size2, false);
                            }
                        });
                        this.writeDoneAndNoFlush = true;
                        return;
                    }
                    writeComplete(write02, channelPromise);
                    return;
                }
                String obj2 = obj.toString();
                ReferenceCountUtil.release(obj);
                channelPromise.setFailure((Throwable) new IllegalArgumentException("Message must be an " + StringUtil.simpleClassName((Class<?>) Http2StreamFrame.class) + ": " + obj2));
            } catch (Throwable th) {
                channelPromise.tryFailure(th);
            }
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

        @Override // io.netty.channel.Channel.Unsafe
        public void flush() {
            if (!this.writeDoneAndNoFlush || AbstractHttp2StreamChannel.this.isParentReadInProgress()) {
                return;
            }
            try {
                AbstractHttp2StreamChannel.this.flush0(AbstractHttp2StreamChannel.this.parentContext());
            } finally {
                this.writeDoneAndNoFlush = false;
            }
        }

        @Override // io.netty.channel.Channel.Unsafe
        public ChannelPromise voidPromise() {
            return this.unsafeVoidPromise;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.handler.codec.http2.AbstractHttp2StreamChannel$4 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C71814 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus */
        static final /* synthetic */ int[] f8505x5f8c880f = new int[ReadStatus.values().length];

        static {
            try {
                f8505x5f8c880f[ReadStatus.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8505x5f8c880f[ReadStatus.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    public final class Http2StreamChannelConfig extends DefaultChannelConfig {
        Http2StreamChannelConfig(Channel channel) {
            super(channel);
        }

        @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
        public MessageSizeEstimator getMessageSizeEstimator() {
            return FlowControlledFrameSizeEstimator.INSTANCE;
        }

        @Override // io.netty.channel.DefaultChannelConfig, io.netty.channel.ChannelConfig
        public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
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

    protected void flush0(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.flush();
    }

    protected ChannelFuture write0(ChannelHandlerContext channelHandlerContext, Object obj) {
        ChannelPromise newPromise = channelHandlerContext.newPromise();
        channelHandlerContext.write(obj, newPromise);
        return newPromise;
    }
}
