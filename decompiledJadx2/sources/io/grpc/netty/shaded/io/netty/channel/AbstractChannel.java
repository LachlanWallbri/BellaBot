package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.socket.ChannelOutputShutdownEvent;
import io.grpc.netty.shaded.io.netty.channel.socket.ChannelOutputShutdownException;
import io.grpc.netty.shaded.io.netty.util.DefaultAttributeMap;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractChannel extends DefaultAttributeMap implements Channel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractChannel.class);
    private final CloseFuture closeFuture;
    private boolean closeInitiated;
    private volatile EventLoop eventLoop;

    /* renamed from: id */
    private final ChannelId f8330id;
    private Throwable initialCloseCause;
    private volatile SocketAddress localAddress;
    private final Channel parent;
    private final DefaultChannelPipeline pipeline;
    private volatile boolean registered;
    private volatile SocketAddress remoteAddress;
    private String strVal;
    private boolean strValActive;
    private final Channel.Unsafe unsafe;
    private final VoidChannelPromise unsafeVoidPromise;

    protected abstract void doBeginRead() throws Exception;

    protected abstract void doBind(SocketAddress socketAddress) throws Exception;

    protected abstract void doClose() throws Exception;

    protected void doDeregister() throws Exception {
    }

    protected abstract void doDisconnect() throws Exception;

    protected void doRegister() throws Exception {
    }

    protected abstract void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception;

    public final boolean equals(Object obj) {
        return this == obj;
    }

    protected Object filterOutboundMessage(Object obj) throws Exception {
        return obj;
    }

    protected abstract boolean isCompatible(EventLoop eventLoop);

    protected abstract SocketAddress localAddress0();

    protected abstract AbstractUnsafe newUnsafe();

    protected abstract SocketAddress remoteAddress0();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractChannel(Channel channel) {
        this.unsafeVoidPromise = new VoidChannelPromise(this, false);
        this.closeFuture = new CloseFuture(this);
        this.parent = channel;
        this.f8330id = newId();
        this.unsafe = newUnsafe();
        this.pipeline = newChannelPipeline();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractChannel(Channel channel, ChannelId channelId) {
        this.unsafeVoidPromise = new VoidChannelPromise(this, false);
        this.closeFuture = new CloseFuture(this);
        this.parent = channel;
        this.f8330id = channelId;
        this.unsafe = newUnsafe();
        this.pipeline = newChannelPipeline();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    /* renamed from: id */
    public final ChannelId mo3907id() {
        return this.f8330id;
    }

    protected ChannelId newId() {
        return DefaultChannelId.newInstance();
    }

    protected DefaultChannelPipeline newChannelPipeline() {
        return new DefaultChannelPipeline(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isWritable() {
        ChannelOutboundBuffer outboundBuffer = this.unsafe.outboundBuffer();
        return outboundBuffer != null && outboundBuffer.isWritable();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public long bytesBeforeUnwritable() {
        ChannelOutboundBuffer outboundBuffer = this.unsafe.outboundBuffer();
        if (outboundBuffer != null) {
            return outboundBuffer.bytesBeforeUnwritable();
        }
        return 0L;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public long bytesBeforeWritable() {
        ChannelOutboundBuffer outboundBuffer = this.unsafe.outboundBuffer();
        if (outboundBuffer != null) {
            return outboundBuffer.bytesBeforeWritable();
        }
        return Long.MAX_VALUE;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public Channel parent() {
        return this.parent;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelPipeline pipeline() {
        return this.pipeline;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ByteBufAllocator alloc() {
        return config().getAllocator();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public EventLoop eventLoop() {
        EventLoop eventLoop = this.eventLoop;
        if (eventLoop != null) {
            return eventLoop;
        }
        throw new IllegalStateException("channel not registered to an event loop");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public SocketAddress localAddress() {
        SocketAddress socketAddress = this.localAddress;
        if (socketAddress != null) {
            return socketAddress;
        }
        try {
            SocketAddress localAddress = unsafe().localAddress();
            this.localAddress = localAddress;
            return localAddress;
        } catch (Error e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Deprecated
    protected void invalidateLocalAddress() {
        this.localAddress = null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public SocketAddress remoteAddress() {
        SocketAddress socketAddress = this.remoteAddress;
        if (socketAddress != null) {
            return socketAddress;
        }
        try {
            SocketAddress remoteAddress = unsafe().remoteAddress();
            this.remoteAddress = remoteAddress;
            return remoteAddress;
        } catch (Error e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Deprecated
    protected void invalidateRemoteAddress() {
        this.remoteAddress = null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isRegistered() {
        return this.registered;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(SocketAddress socketAddress) {
        return this.pipeline.bind(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress) {
        return this.pipeline.connect(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return this.pipeline.connect(socketAddress, socketAddress2);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect() {
        return this.pipeline.disconnect();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close() {
        return this.pipeline.close();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister() {
        return this.pipeline.deregister();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public Channel flush() {
        this.pipeline.flush();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return this.pipeline.bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return this.pipeline.connect(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        return this.pipeline.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect(ChannelPromise channelPromise) {
        return this.pipeline.disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close(ChannelPromise channelPromise) {
        return this.pipeline.close(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister(ChannelPromise channelPromise) {
        return this.pipeline.deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public Channel read() {
        this.pipeline.read();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj) {
        return this.pipeline.write(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
        return this.pipeline.write(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj) {
        return this.pipeline.writeAndFlush(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
        return this.pipeline.writeAndFlush(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelPromise newPromise() {
        return this.pipeline.newPromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelProgressivePromise newProgressivePromise() {
        return this.pipeline.newProgressivePromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newSucceededFuture() {
        return this.pipeline.newSucceededFuture();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newFailedFuture(Throwable th) {
        return this.pipeline.newFailedFuture(th);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelFuture closeFuture() {
        return this.closeFuture;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public Channel.Unsafe unsafe() {
        return this.unsafe;
    }

    public final int hashCode() {
        return this.f8330id.hashCode();
    }

    @Override // java.lang.Comparable
    public final int compareTo(Channel channel) {
        if (this == channel) {
            return 0;
        }
        return mo3907id().compareTo(channel.mo3907id());
    }

    public String toString() {
        String str;
        boolean isActive = isActive();
        if (this.strValActive == isActive && (str = this.strVal) != null) {
            return str;
        }
        SocketAddress remoteAddress = remoteAddress();
        SocketAddress localAddress = localAddress();
        if (remoteAddress != null) {
            StringBuilder sb = new StringBuilder(96);
            sb.append("[id: 0x");
            sb.append(this.f8330id.asShortText());
            sb.append(", L:");
            sb.append(localAddress);
            sb.append(isActive ? " - " : " ! ");
            sb.append("R:");
            sb.append(remoteAddress);
            sb.append(']');
            this.strVal = sb.toString();
        } else if (localAddress != null) {
            StringBuilder sb2 = new StringBuilder(64);
            sb2.append("[id: 0x");
            sb2.append(this.f8330id.asShortText());
            sb2.append(", L:");
            sb2.append(localAddress);
            sb2.append(']');
            this.strVal = sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder(16);
            sb3.append("[id: 0x");
            sb3.append(this.f8330id.asShortText());
            sb3.append(']');
            this.strVal = sb3.toString();
        }
        this.strValActive = isActive;
        return this.strVal;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelPromise voidPromise() {
        return this.pipeline.voidPromise();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    protected abstract class AbstractUnsafe implements Channel.Unsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean inFlush0;
        private boolean neverRegistered = true;
        private volatile ChannelOutboundBuffer outboundBuffer;
        private RecvByteBufAllocator.Handle recvHandle;

        private void assertEventLoop() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Executor prepareToClose() {
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractUnsafe() {
            this.outboundBuffer = new ChannelOutboundBuffer(AbstractChannel.this);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public RecvByteBufAllocator.Handle recvBufAllocHandle() {
            if (this.recvHandle == null) {
                this.recvHandle = AbstractChannel.this.config().getRecvByteBufAllocator().newHandle();
            }
            return this.recvHandle;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final ChannelOutboundBuffer outboundBuffer() {
            return this.outboundBuffer;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final SocketAddress localAddress() {
            return AbstractChannel.this.localAddress0();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final SocketAddress remoteAddress() {
            return AbstractChannel.this.remoteAddress0();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void register(EventLoop eventLoop, final ChannelPromise channelPromise) {
            ObjectUtil.checkNotNull(eventLoop, "eventLoop");
            if (AbstractChannel.this.isRegistered()) {
                channelPromise.setFailure((Throwable) new IllegalStateException("registered to an event loop already"));
                return;
            }
            if (AbstractChannel.this.isCompatible(eventLoop)) {
                AbstractChannel.this.eventLoop = eventLoop;
                if (eventLoop.inEventLoop()) {
                    register0(channelPromise);
                    return;
                }
                try {
                    eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AbstractUnsafe.this.register0(channelPromise);
                        }
                    });
                    return;
                } catch (Throwable th) {
                    AbstractChannel.logger.warn("Force-closing a channel whose registration task was not accepted by an event loop: {}", AbstractChannel.this, th);
                    closeForcibly();
                    AbstractChannel.this.closeFuture.setClosed();
                    safeSetFailure(channelPromise, th);
                    return;
                }
            }
            channelPromise.setFailure((Throwable) new IllegalStateException("incompatible event loop type: " + eventLoop.getClass().getName()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void register0(ChannelPromise channelPromise) {
            try {
                if (channelPromise.setUncancellable() && ensureOpen(channelPromise)) {
                    boolean z = this.neverRegistered;
                    AbstractChannel.this.doRegister();
                    this.neverRegistered = false;
                    AbstractChannel.this.registered = true;
                    AbstractChannel.this.pipeline.invokeHandlerAddedIfNeeded();
                    safeSetSuccess(channelPromise);
                    AbstractChannel.this.pipeline.fireChannelRegistered();
                    if (AbstractChannel.this.isActive()) {
                        if (z) {
                            AbstractChannel.this.pipeline.fireChannelActive();
                        } else if (AbstractChannel.this.config().isAutoRead()) {
                            beginRead();
                        }
                    }
                }
            } catch (Throwable th) {
                closeForcibly();
                AbstractChannel.this.closeFuture.setClosed();
                safeSetFailure(channelPromise, th);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
            assertEventLoop();
            if (channelPromise.setUncancellable() && ensureOpen(channelPromise)) {
                if (Boolean.TRUE.equals(AbstractChannel.this.config().getOption(ChannelOption.SO_BROADCAST)) && (socketAddress instanceof InetSocketAddress) && !((InetSocketAddress) socketAddress).getAddress().isAnyLocalAddress() && !PlatformDependent.isWindows() && !PlatformDependent.maybeSuperUser()) {
                    AbstractChannel.logger.warn("A non-root user can't receive a broadcast packet if the socket is not bound to a wildcard address; binding to a non-wildcard address (" + socketAddress + ") anyway as requested.");
                }
                boolean isActive = AbstractChannel.this.isActive();
                try {
                    AbstractChannel.this.doBind(socketAddress);
                    if (!isActive && AbstractChannel.this.isActive()) {
                        invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.2
                            @Override // java.lang.Runnable
                            public void run() {
                                AbstractChannel.this.pipeline.fireChannelActive();
                            }
                        });
                    }
                    safeSetSuccess(channelPromise);
                } catch (Throwable th) {
                    safeSetFailure(channelPromise, th);
                    closeIfClosed();
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void disconnect(ChannelPromise channelPromise) {
            assertEventLoop();
            if (channelPromise.setUncancellable()) {
                boolean isActive = AbstractChannel.this.isActive();
                try {
                    AbstractChannel.this.doDisconnect();
                    AbstractChannel.this.remoteAddress = null;
                    AbstractChannel.this.localAddress = null;
                    if (isActive && !AbstractChannel.this.isActive()) {
                        invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.3
                            @Override // java.lang.Runnable
                            public void run() {
                                AbstractChannel.this.pipeline.fireChannelInactive();
                            }
                        });
                    }
                    safeSetSuccess(channelPromise);
                    closeIfClosed();
                } catch (Throwable th) {
                    safeSetFailure(channelPromise, th);
                    closeIfClosed();
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void close(ChannelPromise channelPromise) {
            assertEventLoop();
            ClosedChannelException newInstance = StacklessClosedChannelException.newInstance(AbstractChannel.class, "close(ChannelPromise)");
            close(channelPromise, newInstance, newInstance, false);
        }

        public final void shutdownOutput(ChannelPromise channelPromise) {
            assertEventLoop();
            shutdownOutput(channelPromise, null);
        }

        private void shutdownOutput(final ChannelPromise channelPromise, Throwable th) {
            if (channelPromise.setUncancellable()) {
                final ChannelOutboundBuffer channelOutboundBuffer = this.outboundBuffer;
                if (channelOutboundBuffer == null) {
                    channelPromise.setFailure((Throwable) new ClosedChannelException());
                    return;
                }
                this.outboundBuffer = null;
                final ChannelOutputShutdownException channelOutputShutdownException = th == null ? new ChannelOutputShutdownException("Channel output shutdown") : new ChannelOutputShutdownException("Channel output shutdown", th);
                Executor prepareToClose = prepareToClose();
                if (prepareToClose != null) {
                    prepareToClose.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.4
                        @Override // java.lang.Runnable
                        public void run() {
                            EventLoop eventLoop;
                            Runnable runnable;
                            try {
                                AbstractChannel.this.doShutdownOutput();
                                channelPromise.setSuccess();
                                eventLoop = AbstractChannel.this.eventLoop();
                                runnable = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.4.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        AbstractUnsafe.this.closeOutboundBufferForShutdown(AbstractChannel.this.pipeline, channelOutboundBuffer, channelOutputShutdownException);
                                    }
                                };
                            } catch (Throwable th2) {
                                try {
                                    channelPromise.setFailure(th2);
                                    eventLoop = AbstractChannel.this.eventLoop();
                                    runnable = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.4.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            AbstractUnsafe.this.closeOutboundBufferForShutdown(AbstractChannel.this.pipeline, channelOutboundBuffer, channelOutputShutdownException);
                                        }
                                    };
                                } catch (Throwable th3) {
                                    AbstractChannel.this.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.4.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            AbstractUnsafe.this.closeOutboundBufferForShutdown(AbstractChannel.this.pipeline, channelOutboundBuffer, channelOutputShutdownException);
                                        }
                                    });
                                    throw th3;
                                }
                            }
                            eventLoop.execute(runnable);
                        }
                    });
                    return;
                }
                try {
                    AbstractChannel.this.doShutdownOutput();
                    channelPromise.setSuccess();
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void closeOutboundBufferForShutdown(ChannelPipeline channelPipeline, ChannelOutboundBuffer channelOutboundBuffer, Throwable th) {
            channelOutboundBuffer.failFlushed(th, false);
            channelOutboundBuffer.close(th, true);
            channelPipeline.fireUserEventTriggered((Object) ChannelOutputShutdownEvent.INSTANCE);
        }

        private void close(final ChannelPromise channelPromise, final Throwable th, final ClosedChannelException closedChannelException, final boolean z) {
            if (channelPromise.setUncancellable()) {
                if (AbstractChannel.this.closeInitiated) {
                    if (AbstractChannel.this.closeFuture.isDone()) {
                        safeSetSuccess(channelPromise);
                        return;
                    } else {
                        if (channelPromise instanceof VoidChannelPromise) {
                            return;
                        }
                        AbstractChannel.this.closeFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.5
                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                channelPromise.setSuccess();
                            }
                        });
                        return;
                    }
                }
                AbstractChannel.this.closeInitiated = true;
                final boolean isActive = AbstractChannel.this.isActive();
                final ChannelOutboundBuffer channelOutboundBuffer = this.outboundBuffer;
                this.outboundBuffer = null;
                Executor prepareToClose = prepareToClose();
                if (prepareToClose != null) {
                    prepareToClose.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.6
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                AbstractUnsafe.this.doClose0(channelPromise);
                            } finally {
                                AbstractUnsafe.this.invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.6.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (channelOutboundBuffer != null) {
                                            channelOutboundBuffer.failFlushed(th, z);
                                            channelOutboundBuffer.close(closedChannelException);
                                        }
                                        AbstractUnsafe.this.fireChannelInactiveAndDeregister(isActive);
                                    }
                                });
                            }
                        }
                    });
                    return;
                }
                try {
                    doClose0(channelPromise);
                    if (this.inFlush0) {
                        invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.7
                            @Override // java.lang.Runnable
                            public void run() {
                                AbstractUnsafe.this.fireChannelInactiveAndDeregister(isActive);
                            }
                        });
                    } else {
                        fireChannelInactiveAndDeregister(isActive);
                    }
                } finally {
                    if (channelOutboundBuffer != null) {
                        channelOutboundBuffer.failFlushed(th, z);
                        channelOutboundBuffer.close(closedChannelException);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doClose0(ChannelPromise channelPromise) {
            try {
                AbstractChannel.this.doClose();
                AbstractChannel.this.closeFuture.setClosed();
                safeSetSuccess(channelPromise);
            } catch (Throwable th) {
                AbstractChannel.this.closeFuture.setClosed();
                safeSetFailure(channelPromise, th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fireChannelInactiveAndDeregister(boolean z) {
            deregister(voidPromise(), z && !AbstractChannel.this.isActive());
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void closeForcibly() {
            assertEventLoop();
            try {
                AbstractChannel.this.doClose();
            } catch (Exception e) {
                AbstractChannel.logger.warn("Failed to close a channel.", (Throwable) e);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void deregister(ChannelPromise channelPromise) {
            assertEventLoop();
            deregister(channelPromise, false);
        }

        private void deregister(final ChannelPromise channelPromise, final boolean z) {
            if (channelPromise.setUncancellable()) {
                if (!AbstractChannel.this.registered) {
                    safeSetSuccess(channelPromise);
                } else {
                    invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.8
                        /* JADX WARN: Code restructure failed: missing block: B:11:0x005f, code lost:
                        
                            return;
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:21:0x005c, code lost:
                        
                            if (r4.this$1.this$0.registered == false) goto L10;
                         */
                        @Override // java.lang.Runnable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void run() {
                            try {
                                AbstractChannel.this.doDeregister();
                            } catch (Throwable th) {
                                try {
                                    AbstractChannel.logger.warn("Unexpected exception occurred while deregistering a channel.", th);
                                    if (z) {
                                        AbstractChannel.this.pipeline.fireChannelInactive();
                                    }
                                } finally {
                                    if (z) {
                                        AbstractChannel.this.pipeline.fireChannelInactive();
                                    }
                                    if (AbstractChannel.this.registered) {
                                        AbstractChannel.this.registered = false;
                                        AbstractChannel.this.pipeline.fireChannelUnregistered();
                                    }
                                    AbstractUnsafe.this.safeSetSuccess(channelPromise);
                                }
                            }
                        }
                    });
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void beginRead() {
            assertEventLoop();
            if (AbstractChannel.this.isActive()) {
                try {
                    AbstractChannel.this.doBeginRead();
                } catch (Exception e) {
                    invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe.9
                        @Override // java.lang.Runnable
                        public void run() {
                            AbstractChannel.this.pipeline.fireExceptionCaught((Throwable) e);
                        }
                    });
                    close(voidPromise());
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void write(Object obj, ChannelPromise channelPromise) {
            assertEventLoop();
            ChannelOutboundBuffer channelOutboundBuffer = this.outboundBuffer;
            if (channelOutboundBuffer == null) {
                safeSetFailure(channelPromise, newClosedChannelException(AbstractChannel.this.initialCloseCause, "write(Object, ChannelPromise)"));
                ReferenceCountUtil.release(obj);
                return;
            }
            try {
                obj = AbstractChannel.this.filterOutboundMessage(obj);
                int size = AbstractChannel.this.pipeline.estimatorHandle().size(obj);
                if (size < 0) {
                    size = 0;
                }
                channelOutboundBuffer.addMessage(obj, size, channelPromise);
            } catch (Throwable th) {
                safeSetFailure(channelPromise, th);
                ReferenceCountUtil.release(obj);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void flush() {
            assertEventLoop();
            ChannelOutboundBuffer channelOutboundBuffer = this.outboundBuffer;
            if (channelOutboundBuffer == null) {
                return;
            }
            channelOutboundBuffer.addFlush();
            flush0();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't wrap try/catch for region: R(8:17|18|(2:20|(4:22|23|14|15))|25|26|23|14|15) */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void flush0() {
            ChannelOutboundBuffer channelOutboundBuffer;
            if (this.inFlush0 || (channelOutboundBuffer = this.outboundBuffer) == null || channelOutboundBuffer.isEmpty()) {
                return;
            }
            this.inFlush0 = true;
            if (!AbstractChannel.this.isActive()) {
                try {
                    if (!channelOutboundBuffer.isEmpty()) {
                        if (!AbstractChannel.this.isOpen()) {
                            channelOutboundBuffer.failFlushed(newClosedChannelException(AbstractChannel.this.initialCloseCause, "flush0()"), false);
                        } else {
                            channelOutboundBuffer.failFlushed(new NotYetConnectedException(), true);
                        }
                    }
                    return;
                } finally {
                }
            }
            try {
                AbstractChannel.this.doWrite(channelOutboundBuffer);
            } finally {
                try {
                } finally {
                }
            }
        }

        private ClosedChannelException newClosedChannelException(Throwable th, String str) {
            StacklessClosedChannelException newInstance = StacklessClosedChannelException.newInstance(AbstractUnsafe.class, str);
            if (th != null) {
                newInstance.initCause(th);
            }
            return newInstance;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final ChannelPromise voidPromise() {
            assertEventLoop();
            return AbstractChannel.this.unsafeVoidPromise;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final boolean ensureOpen(ChannelPromise channelPromise) {
            if (AbstractChannel.this.isOpen()) {
                return true;
            }
            safeSetFailure(channelPromise, newClosedChannelException(AbstractChannel.this.initialCloseCause, "ensureOpen(ChannelPromise)"));
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void safeSetSuccess(ChannelPromise channelPromise) {
            if ((channelPromise instanceof VoidChannelPromise) || channelPromise.trySuccess()) {
                return;
            }
            AbstractChannel.logger.warn("Failed to mark a promise as success because it is done already: {}", channelPromise);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void safeSetFailure(ChannelPromise channelPromise, Throwable th) {
            if ((channelPromise instanceof VoidChannelPromise) || channelPromise.tryFailure(th)) {
                return;
            }
            AbstractChannel.logger.warn("Failed to mark a promise as failure because it's done already: {}", channelPromise, th);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void closeIfClosed() {
            if (AbstractChannel.this.isOpen()) {
                return;
            }
            close(voidPromise());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invokeLater(Runnable runnable) {
            try {
                AbstractChannel.this.eventLoop().execute(runnable);
            } catch (RejectedExecutionException e) {
                AbstractChannel.logger.warn("Can't invoke task later as EventLoop rejected it", (Throwable) e);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final Throwable annotateConnectException(Throwable th, SocketAddress socketAddress) {
            if (th instanceof ConnectException) {
                return new AnnotatedConnectException((ConnectException) th, socketAddress);
            }
            if (th instanceof NoRouteToHostException) {
                return new AnnotatedNoRouteToHostException((NoRouteToHostException) th, socketAddress);
            }
            return th instanceof SocketException ? new AnnotatedSocketException((SocketException) th, socketAddress) : th;
        }
    }

    protected void doShutdownOutput() throws Exception {
        doClose();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateFileRegion(DefaultFileRegion defaultFileRegion, long j) throws IOException {
        DefaultFileRegion.validate(defaultFileRegion, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class CloseFuture extends DefaultChannelPromise {
        CloseFuture(AbstractChannel abstractChannel) {
            super(abstractChannel);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPromise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
        public ChannelPromise setSuccess() {
            throw new IllegalStateException();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
        public ChannelPromise setFailure(Throwable th) {
            throw new IllegalStateException();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPromise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
        public boolean trySuccess() {
            throw new IllegalStateException();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
        public boolean tryFailure(Throwable th) {
            throw new IllegalStateException();
        }

        boolean setClosed() {
            return super.trySuccess();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AnnotatedConnectException extends ConnectException {
        private static final long serialVersionUID = 3901958112696433556L;

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }

        AnnotatedConnectException(ConnectException connectException, SocketAddress socketAddress) {
            super(connectException.getMessage() + ": " + socketAddress);
            initCause(connectException);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AnnotatedNoRouteToHostException extends NoRouteToHostException {
        private static final long serialVersionUID = -6801433937592080623L;

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }

        AnnotatedNoRouteToHostException(NoRouteToHostException noRouteToHostException, SocketAddress socketAddress) {
            super(noRouteToHostException.getMessage() + ": " + socketAddress);
            initCause(noRouteToHostException);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AnnotatedSocketException extends SocketException {
        private static final long serialVersionUID = 3896743275010454039L;

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }

        AnnotatedSocketException(SocketException socketException, SocketAddress socketAddress) {
            super(socketException.getMessage() + ": " + socketAddress);
            initCause(socketException);
        }
    }
}
