package io.netty.channel;

import androidx.core.app.NotificationCompat;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AbstractChannel;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ResourceLeakHint;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.OrderedEventExecutor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PromiseNotificationUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractChannelHandlerContext extends DefaultAttributeMap implements ChannelHandlerContext, ResourceLeakHint {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ADD_COMPLETE = 2;
    private static final int ADD_PENDING = 1;
    private static final int INIT = 0;
    private static final int REMOVE_COMPLETE = 3;
    final EventExecutor executor;
    private volatile int handlerState = 0;
    private final boolean inbound;
    private Runnable invokeChannelReadCompleteTask;
    private Runnable invokeChannelWritableStateChangedTask;
    private Runnable invokeFlushTask;
    private Runnable invokeReadTask;
    private final String name;
    volatile AbstractChannelHandlerContext next;
    private final boolean ordered;
    private final boolean outbound;
    private final DefaultChannelPipeline pipeline;
    volatile AbstractChannelHandlerContext prev;
    private ChannelFuture succeededFuture;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractChannelHandlerContext.class);
    private static final AtomicIntegerFieldUpdater<AbstractChannelHandlerContext> HANDLER_STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractChannelHandlerContext.class, "handlerState");

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractChannelHandlerContext(DefaultChannelPipeline defaultChannelPipeline, EventExecutor eventExecutor, String str, boolean z, boolean z2) {
        this.name = (String) ObjectUtil.checkNotNull(str, "name");
        this.pipeline = defaultChannelPipeline;
        this.executor = eventExecutor;
        this.inbound = z;
        this.outbound = z2;
        this.ordered = eventExecutor == null || (eventExecutor instanceof OrderedEventExecutor);
    }

    @Override // io.netty.channel.ChannelHandlerContext
    public Channel channel() {
        return this.pipeline.channel();
    }

    @Override // io.netty.channel.ChannelHandlerContext
    public ChannelPipeline pipeline() {
        return this.pipeline;
    }

    @Override // io.netty.channel.ChannelHandlerContext
    public ByteBufAllocator alloc() {
        return channel().config().getAllocator();
    }

    @Override // io.netty.channel.ChannelHandlerContext
    public EventExecutor executor() {
        EventExecutor eventExecutor = this.executor;
        return eventExecutor == null ? channel().eventLoop() : eventExecutor;
    }

    @Override // io.netty.channel.ChannelHandlerContext
    public String name() {
        return this.name;
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireChannelRegistered() {
        invokeChannelRegistered(findContextInbound());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeChannelRegistered(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeChannelRegistered();
        } else {
            executor.execute(new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.1
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeChannelRegistered();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeChannelRegistered() {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).channelRegistered(this);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireChannelRegistered();
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireChannelUnregistered() {
        invokeChannelUnregistered(findContextInbound());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeChannelUnregistered(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeChannelUnregistered();
        } else {
            executor.execute(new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.2
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeChannelUnregistered();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeChannelUnregistered() {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).channelUnregistered(this);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireChannelUnregistered();
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireChannelActive() {
        invokeChannelActive(findContextInbound());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeChannelActive(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeChannelActive();
        } else {
            executor.execute(new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.3
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeChannelActive();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeChannelActive() {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).channelActive(this);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireChannelActive();
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireChannelInactive() {
        invokeChannelInactive(findContextInbound());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeChannelInactive(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeChannelInactive();
        } else {
            executor.execute(new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.4
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeChannelInactive();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeChannelInactive() {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).channelInactive(this);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireChannelInactive();
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireExceptionCaught(Throwable th) {
        invokeExceptionCaught(this.next, th);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeExceptionCaught(AbstractChannelHandlerContext abstractChannelHandlerContext, final Throwable th) {
        ObjectUtil.checkNotNull(th, "cause");
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeExceptionCaught(th);
            return;
        }
        try {
            executor.execute(new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.5
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeExceptionCaught(th);
                }
            });
        } catch (Throwable th2) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to submit an exceptionCaught() event.", th2);
                logger.warn("The exceptionCaught() event that was failed to submit was:", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeExceptionCaught(Throwable th) {
        if (invokeHandler()) {
            try {
                handler().exceptionCaught(this, th);
                return;
            } catch (Throwable th2) {
                if (logger.isDebugEnabled()) {
                    logger.debug("An exception {}was thrown by a user handler's exceptionCaught() method while handling the following exception:", ThrowableUtil.stackTraceToString(th2), th);
                    return;
                } else {
                    if (logger.isWarnEnabled()) {
                        logger.warn("An exception '{}' [enable DEBUG level for full stacktrace] was thrown by a user handler's exceptionCaught() method while handling the following exception:", th2, th);
                        return;
                    }
                    return;
                }
            }
        }
        fireExceptionCaught(th);
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireUserEventTriggered(Object obj) {
        invokeUserEventTriggered(findContextInbound(), obj);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeUserEventTriggered(AbstractChannelHandlerContext abstractChannelHandlerContext, final Object obj) {
        ObjectUtil.checkNotNull(obj, "event");
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeUserEventTriggered(obj);
        } else {
            executor.execute(new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.6
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeUserEventTriggered(obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeUserEventTriggered(Object obj) {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).userEventTriggered(this, obj);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireUserEventTriggered(obj);
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireChannelRead(Object obj) {
        invokeChannelRead(findContextInbound(), obj);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeChannelRead(AbstractChannelHandlerContext abstractChannelHandlerContext, Object obj) {
        final Object obj2 = abstractChannelHandlerContext.pipeline.touch(ObjectUtil.checkNotNull(obj, NotificationCompat.CATEGORY_MESSAGE), abstractChannelHandlerContext);
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeChannelRead(obj2);
        } else {
            executor.execute(new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.7
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeChannelRead(obj2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeChannelRead(Object obj) {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).channelRead(this, obj);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireChannelRead(obj);
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireChannelReadComplete() {
        invokeChannelReadComplete(findContextInbound());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeChannelReadComplete(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeChannelReadComplete();
            return;
        }
        Runnable runnable = abstractChannelHandlerContext.invokeChannelReadCompleteTask;
        if (runnable == null) {
            runnable = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.8
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeChannelReadComplete();
                }
            };
            abstractChannelHandlerContext.invokeChannelReadCompleteTask = runnable;
        }
        executor.execute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeChannelReadComplete() {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).channelReadComplete(this);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireChannelReadComplete();
    }

    @Override // io.netty.channel.ChannelInboundInvoker
    public ChannelHandlerContext fireChannelWritabilityChanged() {
        invokeChannelWritabilityChanged(findContextInbound());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeChannelWritabilityChanged(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        EventExecutor executor = abstractChannelHandlerContext.executor();
        if (executor.inEventLoop()) {
            abstractChannelHandlerContext.invokeChannelWritabilityChanged();
            return;
        }
        Runnable runnable = abstractChannelHandlerContext.invokeChannelWritableStateChangedTask;
        if (runnable == null) {
            runnable = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.9
                @Override // java.lang.Runnable
                public void run() {
                    AbstractChannelHandlerContext.this.invokeChannelWritabilityChanged();
                }
            };
            abstractChannelHandlerContext.invokeChannelWritableStateChangedTask = runnable;
        }
        executor.execute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeChannelWritabilityChanged() {
        if (invokeHandler()) {
            try {
                ((ChannelInboundHandler) handler()).channelWritabilityChanged(this);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        fireChannelWritabilityChanged();
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(SocketAddress socketAddress) {
        return bind(socketAddress, newPromise());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress) {
        return connect(socketAddress, newPromise());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return connect(socketAddress, socketAddress2, newPromise());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect() {
        return disconnect(newPromise());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close() {
        return close(newPromise());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister() {
        return deregister(newPromise());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(final SocketAddress socketAddress, final ChannelPromise channelPromise) {
        if (socketAddress == null) {
            throw new NullPointerException("localAddress");
        }
        if (isNotValidPromise(channelPromise, false)) {
            return channelPromise;
        }
        final AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            findContextOutbound.invokeBind(socketAddress, channelPromise);
        } else {
            safeExecute(executor, new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.10
                @Override // java.lang.Runnable
                public void run() {
                    findContextOutbound.invokeBind(socketAddress, channelPromise);
                }
            }, channelPromise, null);
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeBind(SocketAddress socketAddress, ChannelPromise channelPromise) {
        if (invokeHandler()) {
            try {
                ((ChannelOutboundHandler) handler()).bind(this, socketAddress, channelPromise);
                return;
            } catch (Throwable th) {
                notifyOutboundHandlerException(th, channelPromise);
                return;
            }
        }
        bind(socketAddress, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return connect(socketAddress, null, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(final SocketAddress socketAddress, final SocketAddress socketAddress2, final ChannelPromise channelPromise) {
        if (socketAddress == null) {
            throw new NullPointerException("remoteAddress");
        }
        if (isNotValidPromise(channelPromise, false)) {
            return channelPromise;
        }
        final AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            findContextOutbound.invokeConnect(socketAddress, socketAddress2, channelPromise);
        } else {
            safeExecute(executor, new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.11
                @Override // java.lang.Runnable
                public void run() {
                    findContextOutbound.invokeConnect(socketAddress, socketAddress2, channelPromise);
                }
            }, channelPromise, null);
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeConnect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        if (invokeHandler()) {
            try {
                ((ChannelOutboundHandler) handler()).connect(this, socketAddress, socketAddress2, channelPromise);
                return;
            } catch (Throwable th) {
                notifyOutboundHandlerException(th, channelPromise);
                return;
            }
        }
        connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect(final ChannelPromise channelPromise) {
        if (isNotValidPromise(channelPromise, false)) {
            return channelPromise;
        }
        final AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            if (!channel().metadata().hasDisconnect()) {
                findContextOutbound.invokeClose(channelPromise);
            } else {
                findContextOutbound.invokeDisconnect(channelPromise);
            }
        } else {
            safeExecute(executor, new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.12
                @Override // java.lang.Runnable
                public void run() {
                    if (!AbstractChannelHandlerContext.this.channel().metadata().hasDisconnect()) {
                        findContextOutbound.invokeClose(channelPromise);
                    } else {
                        findContextOutbound.invokeDisconnect(channelPromise);
                    }
                }
            }, channelPromise, null);
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeDisconnect(ChannelPromise channelPromise) {
        if (invokeHandler()) {
            try {
                ((ChannelOutboundHandler) handler()).disconnect(this, channelPromise);
                return;
            } catch (Throwable th) {
                notifyOutboundHandlerException(th, channelPromise);
                return;
            }
        }
        disconnect(channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close(final ChannelPromise channelPromise) {
        if (isNotValidPromise(channelPromise, false)) {
            return channelPromise;
        }
        final AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            findContextOutbound.invokeClose(channelPromise);
        } else {
            safeExecute(executor, new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.13
                @Override // java.lang.Runnable
                public void run() {
                    findContextOutbound.invokeClose(channelPromise);
                }
            }, channelPromise, null);
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeClose(ChannelPromise channelPromise) {
        if (invokeHandler()) {
            try {
                ((ChannelOutboundHandler) handler()).close(this, channelPromise);
                return;
            } catch (Throwable th) {
                notifyOutboundHandlerException(th, channelPromise);
                return;
            }
        }
        close(channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister(final ChannelPromise channelPromise) {
        if (isNotValidPromise(channelPromise, false)) {
            return channelPromise;
        }
        final AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            findContextOutbound.invokeDeregister(channelPromise);
        } else {
            safeExecute(executor, new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.14
                @Override // java.lang.Runnable
                public void run() {
                    findContextOutbound.invokeDeregister(channelPromise);
                }
            }, channelPromise, null);
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeDeregister(ChannelPromise channelPromise) {
        if (invokeHandler()) {
            try {
                ((ChannelOutboundHandler) handler()).deregister(this, channelPromise);
                return;
            } catch (Throwable th) {
                notifyOutboundHandlerException(th, channelPromise);
                return;
            }
        }
        deregister(channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelHandlerContext read() {
        final AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            findContextOutbound.invokeRead();
        } else {
            Runnable runnable = findContextOutbound.invokeReadTask;
            if (runnable == null) {
                runnable = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.15
                    @Override // java.lang.Runnable
                    public void run() {
                        findContextOutbound.invokeRead();
                    }
                };
                findContextOutbound.invokeReadTask = runnable;
            }
            executor.execute(runnable);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeRead() {
        if (invokeHandler()) {
            try {
                ((ChannelOutboundHandler) handler()).read(this);
                return;
            } catch (Throwable th) {
                notifyHandlerException(th);
                return;
            }
        }
        read();
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj) {
        return write(obj, newPromise());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
        if (obj == null) {
            throw new NullPointerException(NotificationCompat.CATEGORY_MESSAGE);
        }
        try {
            if (isNotValidPromise(channelPromise, true)) {
                ReferenceCountUtil.release(obj);
                return channelPromise;
            }
            write(obj, false, channelPromise);
            return channelPromise;
        } catch (RuntimeException e) {
            ReferenceCountUtil.release(obj);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeWrite(Object obj, ChannelPromise channelPromise) {
        if (invokeHandler()) {
            invokeWrite0(obj, channelPromise);
        } else {
            write(obj, channelPromise);
        }
    }

    private void invokeWrite0(Object obj, ChannelPromise channelPromise) {
        try {
            ((ChannelOutboundHandler) handler()).write(this, obj, channelPromise);
        } catch (Throwable th) {
            notifyOutboundHandlerException(th, channelPromise);
        }
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelHandlerContext flush() {
        final AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            findContextOutbound.invokeFlush();
        } else {
            Runnable runnable = findContextOutbound.invokeFlushTask;
            if (runnable == null) {
                runnable = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.16
                    @Override // java.lang.Runnable
                    public void run() {
                        findContextOutbound.invokeFlush();
                    }
                };
                findContextOutbound.invokeFlushTask = runnable;
            }
            safeExecute(executor, runnable, channel().voidPromise(), null);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeFlush() {
        if (invokeHandler()) {
            invokeFlush0();
        } else {
            flush();
        }
    }

    private void invokeFlush0() {
        try {
            ((ChannelOutboundHandler) handler()).flush(this);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
        if (obj == null) {
            throw new NullPointerException(NotificationCompat.CATEGORY_MESSAGE);
        }
        if (isNotValidPromise(channelPromise, true)) {
            ReferenceCountUtil.release(obj);
            return channelPromise;
        }
        write(obj, true, channelPromise);
        return channelPromise;
    }

    private void invokeWriteAndFlush(Object obj, ChannelPromise channelPromise) {
        if (invokeHandler()) {
            invokeWrite0(obj, channelPromise);
            invokeFlush0();
        } else {
            writeAndFlush(obj, channelPromise);
        }
    }

    private void write(Object obj, boolean z, ChannelPromise channelPromise) {
        Runnable newInstance;
        AbstractChannelHandlerContext findContextOutbound = findContextOutbound();
        Object obj2 = this.pipeline.touch(obj, findContextOutbound);
        EventExecutor executor = findContextOutbound.executor();
        if (executor.inEventLoop()) {
            if (z) {
                findContextOutbound.invokeWriteAndFlush(obj2, channelPromise);
                return;
            } else {
                findContextOutbound.invokeWrite(obj2, channelPromise);
                return;
            }
        }
        if (!z) {
            newInstance = WriteTask.newInstance(findContextOutbound, obj2, channelPromise);
        } else {
            newInstance = WriteAndFlushTask.newInstance(findContextOutbound, obj2, channelPromise);
        }
        safeExecute(executor, newInstance, channelPromise, obj2);
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj) {
        return writeAndFlush(obj, newPromise());
    }

    private static void notifyOutboundHandlerException(Throwable th, ChannelPromise channelPromise) {
        PromiseNotificationUtil.tryFailure(channelPromise, th, channelPromise instanceof VoidChannelPromise ? null : logger);
    }

    private void notifyHandlerException(Throwable th) {
        if (inExceptionCaught(th)) {
            if (logger.isWarnEnabled()) {
                logger.warn("An exception was thrown by a user handler while handling an exceptionCaught event", th);
                return;
            }
            return;
        }
        invokeExceptionCaught(th);
    }

    private static boolean inExceptionCaught(Throwable th) {
        do {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement == null) {
                        break;
                    }
                    if ("exceptionCaught".equals(stackTraceElement.getMethodName())) {
                        return true;
                    }
                }
            }
            th = th.getCause();
        } while (th != null);
        return false;
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelPromise newPromise() {
        return new DefaultChannelPromise(channel(), executor());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelProgressivePromise newProgressivePromise() {
        return new DefaultChannelProgressivePromise(channel(), executor());
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newSucceededFuture() {
        ChannelFuture channelFuture = this.succeededFuture;
        if (channelFuture != null) {
            return channelFuture;
        }
        SucceededChannelFuture succeededChannelFuture = new SucceededChannelFuture(channel(), executor());
        this.succeededFuture = succeededChannelFuture;
        return succeededChannelFuture;
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newFailedFuture(Throwable th) {
        return new FailedChannelFuture(channel(), executor(), th);
    }

    private boolean isNotValidPromise(ChannelPromise channelPromise, boolean z) {
        if (channelPromise == null) {
            throw new NullPointerException("promise");
        }
        if (channelPromise.isDone()) {
            if (channelPromise.isCancelled()) {
                return true;
            }
            throw new IllegalArgumentException("promise already done: " + channelPromise);
        }
        if (channelPromise.channel() != channel()) {
            throw new IllegalArgumentException(String.format("promise.channel does not match: %s (expected: %s)", channelPromise.channel(), channel()));
        }
        if (channelPromise.getClass() == DefaultChannelPromise.class) {
            return false;
        }
        if (!z && (channelPromise instanceof VoidChannelPromise)) {
            throw new IllegalArgumentException(StringUtil.simpleClassName((Class<?>) VoidChannelPromise.class) + " not allowed for this operation");
        }
        if (!(channelPromise instanceof AbstractChannel.CloseFuture)) {
            return false;
        }
        throw new IllegalArgumentException(StringUtil.simpleClassName((Class<?>) AbstractChannel.CloseFuture.class) + " not allowed in a pipeline");
    }

    private AbstractChannelHandlerContext findContextInbound() {
        AbstractChannelHandlerContext abstractChannelHandlerContext = this;
        do {
            abstractChannelHandlerContext = abstractChannelHandlerContext.next;
        } while (!abstractChannelHandlerContext.inbound);
        return abstractChannelHandlerContext;
    }

    private AbstractChannelHandlerContext findContextOutbound() {
        AbstractChannelHandlerContext abstractChannelHandlerContext = this;
        do {
            abstractChannelHandlerContext = abstractChannelHandlerContext.prev;
        } while (!abstractChannelHandlerContext.outbound);
        return abstractChannelHandlerContext;
    }

    @Override // io.netty.channel.ChannelOutboundInvoker
    public ChannelPromise voidPromise() {
        return channel().voidPromise();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setRemoved() {
        this.handlerState = 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setAddComplete() {
        int i;
        do {
            i = this.handlerState;
            if (i == 3) {
                return;
            }
        } while (!HANDLER_STATE_UPDATER.compareAndSet(this, i, 2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setAddPending() {
        HANDLER_STATE_UPDATER.compareAndSet(this, 0, 1);
    }

    private boolean invokeHandler() {
        int i = this.handlerState;
        if (i != 2) {
            return !this.ordered && i == 1;
        }
        return true;
    }

    @Override // io.netty.channel.ChannelHandlerContext
    public boolean isRemoved() {
        return this.handlerState == 3;
    }

    @Override // io.netty.util.DefaultAttributeMap, io.netty.util.AttributeMap, io.netty.channel.ChannelHandlerContext
    public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
        return channel().attr(attributeKey);
    }

    @Override // io.netty.util.DefaultAttributeMap, io.netty.util.AttributeMap, io.netty.channel.ChannelHandlerContext
    public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
        return channel().hasAttr(attributeKey);
    }

    private static void safeExecute(EventExecutor eventExecutor, Runnable runnable, ChannelPromise channelPromise, Object obj) {
        try {
            eventExecutor.execute(runnable);
        } catch (Throwable th) {
            try {
                channelPromise.setFailure(th);
            } finally {
                if (obj != null) {
                    ReferenceCountUtil.release(obj);
                }
            }
        }
    }

    @Override // io.netty.util.ResourceLeakHint
    public String toHintString() {
        return '\'' + this.name + "' will handle the message from this point.";
    }

    public String toString() {
        return StringUtil.simpleClassName((Class<?>) ChannelHandlerContext.class) + '(' + this.name + ", " + channel() + ')';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static abstract class AbstractWriteTask implements Runnable {
        private static final boolean ESTIMATE_TASK_SIZE_ON_SUBMIT = SystemPropertyUtil.getBoolean("io.netty.transport.estimateSizeOnSubmit", true);
        private static final int WRITE_TASK_OVERHEAD = SystemPropertyUtil.getInt("io.netty.transport.writeTaskSizeOverhead", 48);
        private AbstractChannelHandlerContext ctx;
        private final Recycler.Handle<AbstractWriteTask> handle;
        private Object msg;
        private ChannelPromise promise;
        private int size;

        /* JADX WARN: Multi-variable type inference failed */
        private AbstractWriteTask(Recycler.Handle<? extends AbstractWriteTask> handle) {
            this.handle = handle;
        }

        protected static void init(AbstractWriteTask abstractWriteTask, AbstractChannelHandlerContext abstractChannelHandlerContext, Object obj, ChannelPromise channelPromise) {
            abstractWriteTask.ctx = abstractChannelHandlerContext;
            abstractWriteTask.msg = obj;
            abstractWriteTask.promise = channelPromise;
            if (ESTIMATE_TASK_SIZE_ON_SUBMIT) {
                abstractWriteTask.size = abstractChannelHandlerContext.pipeline.estimatorHandle().size(obj) + WRITE_TASK_OVERHEAD;
                abstractChannelHandlerContext.pipeline.incrementPendingOutboundBytes(abstractWriteTask.size);
            } else {
                abstractWriteTask.size = 0;
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (ESTIMATE_TASK_SIZE_ON_SUBMIT) {
                    this.ctx.pipeline.decrementPendingOutboundBytes(this.size);
                }
                write(this.ctx, this.msg, this.promise);
            } finally {
                this.ctx = null;
                this.msg = null;
                this.promise = null;
                this.handle.recycle(this);
            }
        }

        protected void write(AbstractChannelHandlerContext abstractChannelHandlerContext, Object obj, ChannelPromise channelPromise) {
            abstractChannelHandlerContext.invokeWrite(obj, channelPromise);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class WriteTask extends AbstractWriteTask implements SingleThreadEventLoop.NonWakeupRunnable {
        private static final Recycler<WriteTask> RECYCLER = new Recycler<WriteTask>() { // from class: io.netty.channel.AbstractChannelHandlerContext.WriteTask.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.netty.util.Recycler
            public WriteTask newObject(Recycler.Handle<WriteTask> handle) {
                return new WriteTask(handle);
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        public static WriteTask newInstance(AbstractChannelHandlerContext abstractChannelHandlerContext, Object obj, ChannelPromise channelPromise) {
            WriteTask writeTask = RECYCLER.get();
            init(writeTask, abstractChannelHandlerContext, obj, channelPromise);
            return writeTask;
        }

        private WriteTask(Recycler.Handle<WriteTask> handle) {
            super(handle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class WriteAndFlushTask extends AbstractWriteTask {
        private static final Recycler<WriteAndFlushTask> RECYCLER = new Recycler<WriteAndFlushTask>() { // from class: io.netty.channel.AbstractChannelHandlerContext.WriteAndFlushTask.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.netty.util.Recycler
            public WriteAndFlushTask newObject(Recycler.Handle<WriteAndFlushTask> handle) {
                return new WriteAndFlushTask(handle);
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        public static WriteAndFlushTask newInstance(AbstractChannelHandlerContext abstractChannelHandlerContext, Object obj, ChannelPromise channelPromise) {
            WriteAndFlushTask writeAndFlushTask = RECYCLER.get();
            init(writeAndFlushTask, abstractChannelHandlerContext, obj, channelPromise);
            return writeAndFlushTask;
        }

        private WriteAndFlushTask(Recycler.Handle<WriteAndFlushTask> handle) {
            super(handle);
        }

        @Override // io.netty.channel.AbstractChannelHandlerContext.AbstractWriteTask
        public void write(AbstractChannelHandlerContext abstractChannelHandlerContext, Object obj, ChannelPromise channelPromise) {
            super.write(abstractChannelHandlerContext, obj, channelPromise);
            abstractChannelHandlerContext.invokeFlush();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    private static final class Tasks {
        private final AbstractChannelHandlerContext next;
        private final Runnable invokeChannelReadCompleteTask = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.Tasks.1
            @Override // java.lang.Runnable
            public void run() {
                AbstractChannelHandlerContext.access$2300(Tasks.this.next);
            }
        };
        private final Runnable invokeReadTask = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.Tasks.2
            @Override // java.lang.Runnable
            public void run() {
                AbstractChannelHandlerContext.access$2400(Tasks.this.next);
            }
        };
        private final Runnable invokeChannelWritableStateChangedTask = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.Tasks.3
            @Override // java.lang.Runnable
            public void run() {
                AbstractChannelHandlerContext.access$2500(Tasks.this.next);
            }
        };
        private final Runnable invokeFlushTask = new Runnable() { // from class: io.netty.channel.AbstractChannelHandlerContext.Tasks.4
            @Override // java.lang.Runnable
            public void run() {
                AbstractChannelHandlerContext.access$2100(Tasks.this.next);
            }
        };

        Tasks(AbstractChannelHandlerContext abstractChannelHandlerContext) {
            this.next = abstractChannelHandlerContext;
        }
    }
}
