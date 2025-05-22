package io.grpc.netty.shaded.io.netty.channel.nio;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.AbstractChannel;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.ConnectTimeoutException;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractNioChannel extends AbstractChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractNioChannel.class);

    /* renamed from: ch */
    private final SelectableChannel f8339ch;
    private final Runnable clearReadPendingRunnable;
    private ChannelPromise connectPromise;
    private ScheduledFuture<?> connectTimeoutFuture;
    protected final int readInterestOp;
    boolean readPending;
    private SocketAddress requestedRemoteAddress;
    volatile SelectionKey selectionKey;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface NioUnsafe extends Channel.Unsafe {
        /* renamed from: ch */
        SelectableChannel mo3912ch();

        void finishConnect();

        void forceFlush();

        void read();
    }

    protected abstract boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception;

    protected abstract void doFinishConnect() throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractNioChannel(Channel channel, SelectableChannel selectableChannel, int i) {
        super(channel);
        this.clearReadPendingRunnable = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.1
            @Override // java.lang.Runnable
            public void run() {
                AbstractNioChannel.this.clearReadPending0();
            }
        };
        this.f8339ch = selectableChannel;
        this.readInterestOp = i;
        try {
            selectableChannel.configureBlocking(false);
        } catch (IOException e) {
            try {
                selectableChannel.close();
            } catch (IOException e2) {
                logger.warn("Failed to close a partially initialized socket.", (Throwable) e2);
            }
            throw new ChannelException("Failed to enter non-blocking mode.", e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return this.f8339ch.isOpen();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public NioUnsafe unsafe() {
        return (NioUnsafe) super.unsafe();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SelectableChannel javaChannel() {
        return this.f8339ch;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public NioEventLoop eventLoop() {
        return (NioEventLoop) super.eventLoop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SelectionKey selectionKey() {
        return this.selectionKey;
    }

    @Deprecated
    protected boolean isReadPending() {
        return this.readPending;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public void setReadPending(final boolean z) {
        if (isRegistered()) {
            NioEventLoop eventLoop = eventLoop();
            if (eventLoop.inEventLoop()) {
                setReadPending0(z);
                return;
            } else {
                eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractNioChannel.this.setReadPending0(z);
                    }
                });
                return;
            }
        }
        this.readPending = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void clearReadPending() {
        if (isRegistered()) {
            NioEventLoop eventLoop = eventLoop();
            if (eventLoop.inEventLoop()) {
                clearReadPending0();
                return;
            } else {
                eventLoop.execute(this.clearReadPendingRunnable);
                return;
            }
        }
        this.readPending = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadPending0(boolean z) {
        this.readPending = z;
        if (z) {
            return;
        }
        ((AbstractNioUnsafe) unsafe()).removeReadOp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadPending0() {
        this.readPending = false;
        ((AbstractNioUnsafe) unsafe()).removeReadOp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public abstract class AbstractNioUnsafe extends AbstractChannel.AbstractUnsafe implements NioUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractNioUnsafe() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void removeReadOp() {
            SelectionKey selectionKey = AbstractNioChannel.this.selectionKey();
            if (selectionKey.isValid()) {
                int interestOps = selectionKey.interestOps();
                if ((AbstractNioChannel.this.readInterestOp & interestOps) != 0) {
                    selectionKey.interestOps(interestOps & (~AbstractNioChannel.this.readInterestOp));
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.NioUnsafe
        /* renamed from: ch */
        public final SelectableChannel mo3912ch() {
            return AbstractNioChannel.this.javaChannel();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void connect(final SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable() && ensureOpen(channelPromise)) {
                try {
                    if (AbstractNioChannel.this.connectPromise != null) {
                        throw new ConnectionPendingException();
                    }
                    boolean isActive = AbstractNioChannel.this.isActive();
                    if (!AbstractNioChannel.this.doConnect(socketAddress, socketAddress2)) {
                        AbstractNioChannel.this.connectPromise = channelPromise;
                        AbstractNioChannel.this.requestedRemoteAddress = socketAddress;
                        int connectTimeoutMillis = AbstractNioChannel.this.config().getConnectTimeoutMillis();
                        if (connectTimeoutMillis > 0) {
                            AbstractNioChannel.this.connectTimeoutFuture = AbstractNioChannel.this.eventLoop().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.AbstractNioUnsafe.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ChannelPromise channelPromise2 = AbstractNioChannel.this.connectPromise;
                                    ConnectTimeoutException connectTimeoutException = new ConnectTimeoutException("connection timed out: " + socketAddress);
                                    if (channelPromise2 == null || !channelPromise2.tryFailure(connectTimeoutException)) {
                                        return;
                                    }
                                    AbstractNioUnsafe abstractNioUnsafe = AbstractNioUnsafe.this;
                                    abstractNioUnsafe.close(abstractNioUnsafe.voidPromise());
                                }
                            }, connectTimeoutMillis, TimeUnit.MILLISECONDS);
                        }
                        channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.AbstractNioUnsafe.2
                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (channelFuture.isCancelled()) {
                                    if (AbstractNioChannel.this.connectTimeoutFuture != null) {
                                        AbstractNioChannel.this.connectTimeoutFuture.cancel(false);
                                    }
                                    AbstractNioChannel.this.connectPromise = null;
                                    AbstractNioUnsafe abstractNioUnsafe = AbstractNioUnsafe.this;
                                    abstractNioUnsafe.close(abstractNioUnsafe.voidPromise());
                                }
                            }
                        });
                        return;
                    }
                    fulfillConnectPromise(channelPromise, isActive);
                } catch (Throwable th) {
                    channelPromise.tryFailure(annotateConnectException(th, socketAddress));
                    closeIfClosed();
                }
            }
        }

        private void fulfillConnectPromise(ChannelPromise channelPromise, boolean z) {
            if (channelPromise == null) {
                return;
            }
            boolean isActive = AbstractNioChannel.this.isActive();
            boolean trySuccess = channelPromise.trySuccess();
            if (!z && isActive) {
                AbstractNioChannel.this.pipeline().fireChannelActive();
            }
            if (trySuccess) {
                return;
            }
            close(voidPromise());
        }

        private void fulfillConnectPromise(ChannelPromise channelPromise, Throwable th) {
            if (channelPromise == null) {
                return;
            }
            channelPromise.tryFailure(th);
            closeIfClosed();
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
        
            if (r5.this$0.connectTimeoutFuture == null) goto L7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x004a, code lost:
        
            return;
         */
        @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.NioUnsafe
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void finishConnect() {
            try {
                boolean isActive = AbstractNioChannel.this.isActive();
                AbstractNioChannel.this.doFinishConnect();
                fulfillConnectPromise(AbstractNioChannel.this.connectPromise, isActive);
            } catch (Throwable th) {
                try {
                    fulfillConnectPromise(AbstractNioChannel.this.connectPromise, annotateConnectException(th, AbstractNioChannel.this.requestedRemoteAddress));
                } finally {
                    if (AbstractNioChannel.this.connectTimeoutFuture != null) {
                        AbstractNioChannel.this.connectTimeoutFuture.cancel(false);
                    }
                    AbstractNioChannel.this.connectPromise = null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe
        public final void flush0() {
            if (isFlushPending()) {
                return;
            }
            super.flush0();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.NioUnsafe
        public final void forceFlush() {
            super.flush0();
        }

        private boolean isFlushPending() {
            SelectionKey selectionKey = AbstractNioChannel.this.selectionKey();
            return selectionKey.isValid() && (selectionKey.interestOps() & 4) != 0;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof NioEventLoop;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doRegister() throws Exception {
        boolean z = false;
        while (true) {
            try {
                this.selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this);
                return;
            } catch (CancelledKeyException e) {
                if (!z) {
                    eventLoop().selectNow();
                    z = true;
                } else {
                    throw e;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doDeregister() throws Exception {
        eventLoop().cancel(selectionKey());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doBeginRead() throws Exception {
        SelectionKey selectionKey = this.selectionKey;
        if (selectionKey.isValid()) {
            this.readPending = true;
            int interestOps = selectionKey.interestOps();
            int i = this.readInterestOp;
            if ((interestOps & i) == 0) {
                selectionKey.interestOps(interestOps | i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteBuf newDirectBuffer(ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            ReferenceCountUtil.safeRelease(byteBuf);
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBufAllocator alloc = alloc();
        if (alloc.isDirectBufferPooled()) {
            ByteBuf directBuffer = alloc.directBuffer(readableBytes);
            directBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
            ReferenceCountUtil.safeRelease(byteBuf);
            return directBuffer;
        }
        ByteBuf threadLocalDirectBuffer = ByteBufUtil.threadLocalDirectBuffer();
        if (threadLocalDirectBuffer == null) {
            return byteBuf;
        }
        threadLocalDirectBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
        ReferenceCountUtil.safeRelease(byteBuf);
        return threadLocalDirectBuffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteBuf newDirectBuffer(ReferenceCounted referenceCounted, ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            ReferenceCountUtil.safeRelease(referenceCounted);
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBufAllocator alloc = alloc();
        if (alloc.isDirectBufferPooled()) {
            ByteBuf directBuffer = alloc.directBuffer(readableBytes);
            directBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
            ReferenceCountUtil.safeRelease(referenceCounted);
            return directBuffer;
        }
        ByteBuf threadLocalDirectBuffer = ByteBufUtil.threadLocalDirectBuffer();
        if (threadLocalDirectBuffer != null) {
            threadLocalDirectBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
            ReferenceCountUtil.safeRelease(referenceCounted);
            return threadLocalDirectBuffer;
        }
        if (referenceCounted != byteBuf) {
            byteBuf.retain();
            ReferenceCountUtil.safeRelease(referenceCounted);
        }
        return byteBuf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doClose() throws Exception {
        ChannelPromise channelPromise = this.connectPromise;
        if (channelPromise != null) {
            channelPromise.tryFailure(new ClosedChannelException());
            this.connectPromise = null;
        }
        ScheduledFuture<?> scheduledFuture = this.connectTimeoutFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.connectTimeoutFuture = null;
        }
    }
}
