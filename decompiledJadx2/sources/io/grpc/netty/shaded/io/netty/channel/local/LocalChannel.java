package io.grpc.netty.shaded.io.netty.channel.local;

import io.grpc.netty.shaded.io.netty.channel.AbstractChannel;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.PreferHeapByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.SingleThreadEventLoop;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class LocalChannel extends AbstractChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_READER_STACK_DEPTH = 8;
    private final ChannelConfig config;
    private volatile ChannelPromise connectPromise;
    private volatile Future<?> finishReadFuture;
    final Queue<Object> inboundBuffer;
    private volatile LocalAddress localAddress;
    private volatile LocalChannel peer;
    private volatile boolean readInProgress;
    private final Runnable readTask;
    private volatile LocalAddress remoteAddress;
    private final Runnable shutdownHook;
    private volatile State state;
    private volatile boolean writeInProgress;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) LocalChannel.class);
    private static final AtomicReferenceFieldUpdater<LocalChannel, Future> FINISH_READ_FUTURE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(LocalChannel.class, Future.class, "finishReadFuture");
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        OPEN,
        BOUND,
        CONNECTED,
        CLOSED
    }

    public LocalChannel() {
        super(null);
        this.config = new DefaultChannelConfig(this);
        this.inboundBuffer = PlatformDependent.newSpscQueue();
        this.readTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel.1
            @Override // java.lang.Runnable
            public void run() {
                if (LocalChannel.this.inboundBuffer.isEmpty()) {
                    return;
                }
                LocalChannel.this.readInbound();
            }
        };
        this.shutdownHook = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel.2
            @Override // java.lang.Runnable
            public void run() {
                LocalChannel.this.unsafe().close(LocalChannel.this.unsafe().voidPromise());
            }
        };
        config().setAllocator(new PreferHeapByteBufAllocator(this.config.getAllocator()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LocalChannel(LocalServerChannel localServerChannel, LocalChannel localChannel) {
        super(localServerChannel);
        this.config = new DefaultChannelConfig(this);
        this.inboundBuffer = PlatformDependent.newSpscQueue();
        this.readTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel.1
            @Override // java.lang.Runnable
            public void run() {
                if (LocalChannel.this.inboundBuffer.isEmpty()) {
                    return;
                }
                LocalChannel.this.readInbound();
            }
        };
        this.shutdownHook = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel.2
            @Override // java.lang.Runnable
            public void run() {
                LocalChannel.this.unsafe().close(LocalChannel.this.unsafe().voidPromise());
            }
        };
        config().setAllocator(new PreferHeapByteBufAllocator(this.config.getAllocator()));
        this.peer = localChannel;
        this.localAddress = localServerChannel.localAddress();
        this.remoteAddress = localChannel.localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public LocalServerChannel parent() {
        return (LocalServerChannel) super.parent();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public LocalAddress localAddress() {
        return (LocalAddress) super.localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public LocalAddress remoteAddress() {
        return (LocalAddress) super.remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return this.state != State.CLOSED;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return this.state == State.CONNECTED;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected AbstractChannel.AbstractUnsafe newUnsafe() {
        return new LocalUnsafe();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof SingleThreadEventLoop;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return this.localAddress;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return this.remoteAddress;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doRegister() throws Exception {
        if (this.peer != null && parent() != null) {
            final LocalChannel localChannel = this.peer;
            this.state = State.CONNECTED;
            localChannel.remoteAddress = parent() == null ? null : parent().localAddress();
            localChannel.state = State.CONNECTED;
            localChannel.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel.3
                @Override // java.lang.Runnable
                public void run() {
                    ChannelPromise channelPromise = localChannel.connectPromise;
                    if (channelPromise == null || !channelPromise.trySuccess()) {
                        return;
                    }
                    localChannel.pipeline().fireChannelActive();
                }
            });
        }
        ((SingleThreadEventExecutor) eventLoop()).addShutdownHook(this.shutdownHook);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        this.localAddress = LocalChannelRegistry.register(this, this.localAddress, socketAddress);
        this.state = State.BOUND;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        doClose();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        final LocalChannel localChannel = this.peer;
        State state = this.state;
        try {
            if (state != State.CLOSED) {
                if (this.localAddress != null) {
                    if (parent() == null) {
                        LocalChannelRegistry.unregister(this.localAddress);
                    }
                    this.localAddress = null;
                }
                this.state = State.CLOSED;
                if (this.writeInProgress && localChannel != null) {
                    finishPeerRead(localChannel);
                }
                ChannelPromise channelPromise = this.connectPromise;
                if (channelPromise != null) {
                    channelPromise.tryFailure(new ClosedChannelException());
                    this.connectPromise = null;
                }
            }
            if (localChannel != null) {
                this.peer = null;
                EventLoop eventLoop = localChannel.eventLoop();
                final boolean isActive = localChannel.isActive();
                try {
                    eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel.4
                        @Override // java.lang.Runnable
                        public void run() {
                            localChannel.tryClose(isActive);
                        }
                    });
                } catch (Throwable th) {
                    logger.warn("Releasing Inbound Queues for channels {}-{} because exception occurred!", this, localChannel, th);
                    if (eventLoop.inEventLoop()) {
                        localChannel.releaseInboundBuffers();
                    } else {
                        localChannel.close();
                    }
                    PlatformDependent.throwException(th);
                }
            }
        } finally {
            if (state != null && state != State.CLOSED) {
                releaseInboundBuffers();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryClose(boolean z) {
        if (z) {
            unsafe().close(unsafe().voidPromise());
        } else {
            releaseInboundBuffers();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDeregister() throws Exception {
        ((SingleThreadEventExecutor) eventLoop()).removeShutdownHook(this.shutdownHook);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readInbound() {
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.reset(config());
        ChannelPipeline pipeline = pipeline();
        do {
            Object poll = this.inboundBuffer.poll();
            if (poll == null) {
                break;
            } else {
                pipeline.fireChannelRead(poll);
            }
        } while (recvBufAllocHandle.continueReading());
        pipeline.fireChannelReadComplete();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBeginRead() throws Exception {
        if (this.readInProgress) {
            return;
        }
        if (this.inboundBuffer.isEmpty()) {
            this.readInProgress = true;
            return;
        }
        InternalThreadLocalMap internalThreadLocalMap = InternalThreadLocalMap.get();
        Integer valueOf = Integer.valueOf(internalThreadLocalMap.localChannelReaderStackDepth());
        if (valueOf.intValue() < 8) {
            internalThreadLocalMap.setLocalChannelReaderStackDepth(valueOf.intValue() + 1);
            try {
                readInbound();
                return;
            } finally {
                internalThreadLocalMap.setLocalChannelReaderStackDepth(valueOf.intValue());
            }
        }
        try {
            eventLoop().execute(this.readTask);
        } catch (Throwable th) {
            logger.warn("Closing Local channels {}-{} because exception occurred!", this, this.peer, th);
            close();
            this.peer.close();
            PlatformDependent.throwException(th);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel$6 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C63816 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$local$LocalChannel$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$io$netty$channel$local$LocalChannel$State[State.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$channel$local$LocalChannel$State[State.BOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$channel$local$LocalChannel$State[State.CLOSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$channel$local$LocalChannel$State[State.CONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        int i = C63816.$SwitchMap$io$netty$channel$local$LocalChannel$State[this.state.ordinal()];
        if (i == 1 || i == 2) {
            throw new NotYetConnectedException();
        }
        if (i == 3) {
            throw new ClosedChannelException();
        }
        LocalChannel localChannel = this.peer;
        this.writeInProgress = true;
        ClosedChannelException closedChannelException = null;
        while (true) {
            try {
                Object current = channelOutboundBuffer.current();
                if (current != null) {
                    try {
                        if (localChannel.state == State.CONNECTED) {
                            localChannel.inboundBuffer.add(ReferenceCountUtil.retain(current));
                            channelOutboundBuffer.remove();
                        } else {
                            if (closedChannelException == null) {
                                closedChannelException = new ClosedChannelException();
                            }
                            channelOutboundBuffer.remove(closedChannelException);
                        }
                    } catch (Throwable th) {
                        channelOutboundBuffer.remove(th);
                    }
                } else {
                    this.writeInProgress = false;
                    finishPeerRead(localChannel);
                    return;
                }
            } catch (Throwable th2) {
                this.writeInProgress = false;
                throw th2;
            }
        }
    }

    private void finishPeerRead(LocalChannel localChannel) {
        if (localChannel.eventLoop() == eventLoop() && !localChannel.writeInProgress) {
            finishPeerRead0(localChannel);
        } else {
            runFinishPeerReadTask(localChannel);
        }
    }

    private void runFinishPeerReadTask(final LocalChannel localChannel) {
        Runnable runnable = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalChannel.5
            @Override // java.lang.Runnable
            public void run() {
                LocalChannel.this.finishPeerRead0(localChannel);
            }
        };
        try {
            if (localChannel.writeInProgress) {
                localChannel.finishReadFuture = localChannel.eventLoop().submit(runnable);
            } else {
                localChannel.eventLoop().execute(runnable);
            }
        } catch (Throwable th) {
            logger.warn("Closing Local channels {}-{} because exception occurred!", this, localChannel, th);
            close();
            localChannel.close();
            PlatformDependent.throwException(th);
        }
    }

    private void releaseInboundBuffers() {
        this.readInProgress = false;
        Queue<Object> queue = this.inboundBuffer;
        while (true) {
            Object poll = queue.poll();
            if (poll == null) {
                return;
            } else {
                ReferenceCountUtil.release(poll);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishPeerRead0(LocalChannel localChannel) {
        Future<?> future = localChannel.finishReadFuture;
        if (future != null) {
            if (!future.isDone()) {
                runFinishPeerReadTask(localChannel);
                return;
            }
            FINISH_READ_FUTURE_UPDATER.compareAndSet(localChannel, future, null);
        }
        if (!localChannel.readInProgress || localChannel.inboundBuffer.isEmpty()) {
            return;
        }
        localChannel.readInProgress = false;
        localChannel.readInbound();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class LocalUnsafe extends AbstractChannel.AbstractUnsafe {
        private LocalUnsafe() {
            super();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable() && ensureOpen(channelPromise)) {
                if (LocalChannel.this.state != State.CONNECTED) {
                    if (LocalChannel.this.connectPromise == null) {
                        LocalChannel.this.connectPromise = channelPromise;
                        if (LocalChannel.this.state != State.BOUND && socketAddress2 == null) {
                            socketAddress2 = new LocalAddress(LocalChannel.this);
                        }
                        if (socketAddress2 != null) {
                            try {
                                LocalChannel.this.doBind(socketAddress2);
                            } catch (Throwable th) {
                                safeSetFailure(channelPromise, th);
                                close(voidPromise());
                                return;
                            }
                        }
                        Channel channel = LocalChannelRegistry.get(socketAddress);
                        if (!(channel instanceof LocalServerChannel)) {
                            safeSetFailure(channelPromise, new ConnectException("connection refused: " + socketAddress));
                            close(voidPromise());
                            return;
                        }
                        LocalChannel localChannel = LocalChannel.this;
                        localChannel.peer = ((LocalServerChannel) channel).serve(localChannel);
                        return;
                    }
                    throw new ConnectionPendingException();
                }
                AlreadyConnectedException alreadyConnectedException = new AlreadyConnectedException();
                safeSetFailure(channelPromise, alreadyConnectedException);
                LocalChannel.this.pipeline().fireExceptionCaught((Throwable) alreadyConnectedException);
            }
        }
    }
}
