package io.netty.channel.local;

import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.channel.PreferHeapByteBufAllocator;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
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
    private static final ClosedChannelException DO_WRITE_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), LocalChannel.class, "doWrite(...)");
    private static final ClosedChannelException DO_CLOSE_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), LocalChannel.class, "doClose()");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
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
        this.readTask = new Runnable() { // from class: io.netty.channel.local.LocalChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ChannelPipeline pipeline = LocalChannel.this.pipeline();
                while (true) {
                    Object poll = LocalChannel.this.inboundBuffer.poll();
                    if (poll != null) {
                        pipeline.fireChannelRead(poll);
                    } else {
                        pipeline.fireChannelReadComplete();
                        return;
                    }
                }
            }
        };
        this.shutdownHook = new Runnable() { // from class: io.netty.channel.local.LocalChannel.2
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
        this.readTask = new Runnable() { // from class: io.netty.channel.local.LocalChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ChannelPipeline pipeline = LocalChannel.this.pipeline();
                while (true) {
                    Object poll = LocalChannel.this.inboundBuffer.poll();
                    if (poll != null) {
                        pipeline.fireChannelRead(poll);
                    } else {
                        pipeline.fireChannelReadComplete();
                        return;
                    }
                }
            }
        };
        this.shutdownHook = new Runnable() { // from class: io.netty.channel.local.LocalChannel.2
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

    @Override // io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public LocalServerChannel parent() {
        return (LocalServerChannel) super.parent();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public LocalAddress localAddress() {
        return (LocalAddress) super.localAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public LocalAddress remoteAddress() {
        return (LocalAddress) super.remoteAddress();
    }

    @Override // io.netty.channel.Channel
    public boolean isOpen() {
        return this.state != State.CLOSED;
    }

    @Override // io.netty.channel.Channel
    public boolean isActive() {
        return this.state == State.CONNECTED;
    }

    @Override // io.netty.channel.AbstractChannel
    protected AbstractChannel.AbstractUnsafe newUnsafe() {
        return new LocalUnsafe();
    }

    @Override // io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof SingleThreadEventLoop;
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return this.localAddress;
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return this.remoteAddress;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doRegister() throws Exception {
        if (this.peer != null && parent() != null) {
            final LocalChannel localChannel = this.peer;
            this.state = State.CONNECTED;
            localChannel.remoteAddress = parent() == null ? null : parent().localAddress();
            localChannel.state = State.CONNECTED;
            localChannel.eventLoop().execute(new Runnable() { // from class: io.netty.channel.local.LocalChannel.3
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

    @Override // io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        this.localAddress = LocalChannelRegistry.register(this, this.localAddress, socketAddress);
        this.state = State.BOUND;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        doClose();
    }

    @Override // io.netty.channel.AbstractChannel
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
                    channelPromise.tryFailure(DO_CLOSE_CLOSED_CHANNEL_EXCEPTION);
                    this.connectPromise = null;
                }
            }
            if (localChannel != null) {
                this.peer = null;
                EventLoop eventLoop = localChannel.eventLoop();
                final boolean isActive = localChannel.isActive();
                try {
                    eventLoop.execute(new Runnable() { // from class: io.netty.channel.local.LocalChannel.4
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

    @Override // io.netty.channel.AbstractChannel
    protected void doDeregister() throws Exception {
        ((SingleThreadEventExecutor) eventLoop()).removeShutdownHook(this.shutdownHook);
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doBeginRead() throws Exception {
        if (this.readInProgress) {
            return;
        }
        ChannelPipeline pipeline = pipeline();
        Queue<Object> queue = this.inboundBuffer;
        if (queue.isEmpty()) {
            this.readInProgress = true;
            return;
        }
        InternalThreadLocalMap internalThreadLocalMap = InternalThreadLocalMap.get();
        Integer valueOf = Integer.valueOf(internalThreadLocalMap.localChannelReaderStackDepth());
        if (valueOf.intValue() < 8) {
            internalThreadLocalMap.setLocalChannelReaderStackDepth(valueOf.intValue() + 1);
            while (true) {
                try {
                    Object poll = queue.poll();
                    if (poll != null) {
                        pipeline.fireChannelRead(poll);
                    } else {
                        pipeline.fireChannelReadComplete();
                        return;
                    }
                } finally {
                    internalThreadLocalMap.setLocalChannelReaderStackDepth(valueOf.intValue());
                }
            }
        } else {
            try {
                eventLoop().execute(this.readTask);
            } catch (Throwable th) {
                logger.warn("Closing Local channels {}-{} because exception occurred!", this, this.peer, th);
                close();
                this.peer.close();
                PlatformDependent.throwException(th);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* renamed from: io.netty.channel.local.LocalChannel$6 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C70316 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$local$LocalChannel$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$channel$local$LocalChannel$State = iArr;
            try {
                iArr[State.OPEN.ordinal()] = 1;
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

    @Override // io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        int i = C70316.$SwitchMap$io$netty$channel$local$LocalChannel$State[this.state.ordinal()];
        if (i == 1 || i == 2) {
            throw new NotYetConnectedException();
        }
        if (i == 3) {
            throw DO_WRITE_CLOSED_CHANNEL_EXCEPTION;
        }
        LocalChannel localChannel = this.peer;
        this.writeInProgress = true;
        while (true) {
            try {
                Object current = channelOutboundBuffer.current();
                if (current != null) {
                    try {
                        if (localChannel.state == State.CONNECTED) {
                            localChannel.inboundBuffer.add(ReferenceCountUtil.retain(current));
                            channelOutboundBuffer.remove();
                        } else {
                            channelOutboundBuffer.remove(DO_WRITE_CLOSED_CHANNEL_EXCEPTION);
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
        Runnable runnable = new Runnable() { // from class: io.netty.channel.local.LocalChannel.5
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
        ChannelPipeline pipeline = localChannel.pipeline();
        if (!localChannel.readInProgress) {
            return;
        }
        localChannel.readInProgress = false;
        while (true) {
            Object poll = localChannel.inboundBuffer.poll();
            if (poll != null) {
                pipeline.fireChannelRead(poll);
            } else {
                pipeline.fireChannelReadComplete();
                return;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    private class LocalUnsafe extends AbstractChannel.AbstractUnsafe {
        private LocalUnsafe() {
            super();
        }

        @Override // io.netty.channel.Channel.Unsafe
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
