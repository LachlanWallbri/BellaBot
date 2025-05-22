package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler;
import io.grpc.netty.shaded.io.netty.util.Attribute;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CombinedChannelDuplexHandler<I extends ChannelInboundHandler, O extends ChannelOutboundHandler> extends ChannelDuplexHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CombinedChannelDuplexHandler.class);
    private volatile boolean handlerAdded;
    private DelegatingChannelHandlerContext inboundCtx;
    private I inboundHandler;
    private DelegatingChannelHandlerContext outboundCtx;
    private O outboundHandler;

    /* JADX INFO: Access modifiers changed from: protected */
    public CombinedChannelDuplexHandler() {
        ensureNotSharable();
    }

    public CombinedChannelDuplexHandler(I i, O o) {
        ensureNotSharable();
        init(i, o);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void init(I i, O o) {
        validate(i, o);
        this.inboundHandler = i;
        this.outboundHandler = o;
    }

    private void validate(I i, O o) {
        if (this.inboundHandler != null) {
            throw new IllegalStateException("init() can not be invoked if " + CombinedChannelDuplexHandler.class.getSimpleName() + " was constructed with non-default constructor.");
        }
        ObjectUtil.checkNotNull(i, "inboundHandler");
        ObjectUtil.checkNotNull(o, "outboundHandler");
        if (i instanceof ChannelOutboundHandler) {
            throw new IllegalArgumentException("inboundHandler must not implement " + ChannelOutboundHandler.class.getSimpleName() + " to get combined.");
        }
        if (o instanceof ChannelInboundHandler) {
            throw new IllegalArgumentException("outboundHandler must not implement " + ChannelInboundHandler.class.getSimpleName() + " to get combined.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final I inboundHandler() {
        return this.inboundHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final O outboundHandler() {
        return this.outboundHandler;
    }

    private void checkAdded() {
        if (!this.handlerAdded) {
            throw new IllegalStateException("handler not added to pipeline yet");
        }
    }

    public final void removeInboundHandler() {
        checkAdded();
        this.inboundCtx.remove();
    }

    public final void removeOutboundHandler() {
        checkAdded();
        this.outboundCtx.remove();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.inboundHandler == null) {
            throw new IllegalStateException("init() must be invoked before being added to a " + ChannelPipeline.class.getSimpleName() + " if " + CombinedChannelDuplexHandler.class.getSimpleName() + " was constructed with the default constructor.");
        }
        this.outboundCtx = new DelegatingChannelHandlerContext(channelHandlerContext, this.outboundHandler);
        this.inboundCtx = new DelegatingChannelHandlerContext(channelHandlerContext, this.inboundHandler) { // from class: io.grpc.netty.shaded.io.netty.channel.CombinedChannelDuplexHandler.1
            @Override // io.grpc.netty.shaded.io.netty.channel.CombinedChannelDuplexHandler.DelegatingChannelHandlerContext, io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
            public ChannelHandlerContext fireExceptionCaught(Throwable th) {
                if (!CombinedChannelDuplexHandler.this.outboundCtx.removed) {
                    try {
                        CombinedChannelDuplexHandler.this.outboundHandler.exceptionCaught(CombinedChannelDuplexHandler.this.outboundCtx, th);
                    } catch (Throwable th2) {
                        if (!CombinedChannelDuplexHandler.logger.isDebugEnabled()) {
                            if (CombinedChannelDuplexHandler.logger.isWarnEnabled()) {
                                CombinedChannelDuplexHandler.logger.warn("An exception '{}' [enable DEBUG level for full stacktrace] was thrown by a user handler's exceptionCaught() method while handling the following exception:", th2, th);
                            }
                        } else {
                            CombinedChannelDuplexHandler.logger.debug("An exception {}was thrown by a user handler's exceptionCaught() method while handling the following exception:", ThrowableUtil.stackTraceToString(th2), th);
                        }
                    }
                } else {
                    super.fireExceptionCaught(th);
                }
                return this;
            }
        };
        this.handlerAdded = true;
        try {
            this.inboundHandler.handlerAdded(this.inboundCtx);
        } finally {
            this.outboundHandler.handlerAdded(this.outboundCtx);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            this.inboundCtx.remove();
        } finally {
            this.outboundCtx.remove();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelRegistered(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelRegistered();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelUnregistered(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelUnregistered();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelActive(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelActive();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelInactive(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelInactive();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.exceptionCaught(this.inboundCtx, th);
        } else {
            this.inboundCtx.fireExceptionCaught(th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.userEventTriggered(this.inboundCtx, obj);
        } else {
            this.inboundCtx.fireUserEventTriggered(obj);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelRead(this.inboundCtx, obj);
        } else {
            this.inboundCtx.fireChannelRead(obj);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelReadComplete(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelReadComplete();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelWritabilityChanged(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelWritabilityChanged();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.bind(this.outboundCtx, socketAddress, channelPromise);
        } else {
            this.outboundCtx.bind(socketAddress, channelPromise);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.connect(this.outboundCtx, socketAddress, socketAddress2, channelPromise);
        } else {
            this.outboundCtx.connect(socketAddress2, channelPromise);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.disconnect(this.outboundCtx, channelPromise);
        } else {
            this.outboundCtx.disconnect(channelPromise);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.close(this.outboundCtx, channelPromise);
        } else {
            this.outboundCtx.close(channelPromise);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.deregister(this.outboundCtx, channelPromise);
        } else {
            this.outboundCtx.deregister(channelPromise);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.read(this.outboundCtx);
        } else {
            this.outboundCtx.read();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.write(this.outboundCtx, obj, channelPromise);
        } else {
            this.outboundCtx.write(obj, channelPromise);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.flush(this.outboundCtx);
        } else {
            this.outboundCtx.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class DelegatingChannelHandlerContext implements ChannelHandlerContext {
        private final ChannelHandlerContext ctx;
        private final ChannelHandler handler;
        boolean removed;

        DelegatingChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ChannelHandler channelHandler) {
            this.ctx = channelHandlerContext;
            this.handler = channelHandler;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public Channel channel() {
            return this.ctx.channel();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public EventExecutor executor() {
            return this.ctx.executor();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public String name() {
            return this.ctx.name();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public ChannelHandler handler() {
            return this.ctx.handler();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public boolean isRemoved() {
            return this.removed || this.ctx.isRemoved();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelRegistered() {
            this.ctx.fireChannelRegistered();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelUnregistered() {
            this.ctx.fireChannelUnregistered();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelActive() {
            this.ctx.fireChannelActive();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelInactive() {
            this.ctx.fireChannelInactive();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireExceptionCaught(Throwable th) {
            this.ctx.fireExceptionCaught(th);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireUserEventTriggered(Object obj) {
            this.ctx.fireUserEventTriggered(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelRead(Object obj) {
            this.ctx.fireChannelRead(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelReadComplete() {
            this.ctx.fireChannelReadComplete();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
        public ChannelHandlerContext fireChannelWritabilityChanged() {
            this.ctx.fireChannelWritabilityChanged();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture bind(SocketAddress socketAddress) {
            return this.ctx.bind(socketAddress);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress) {
            return this.ctx.connect(socketAddress);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
            return this.ctx.connect(socketAddress, socketAddress2);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture disconnect() {
            return this.ctx.disconnect();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture close() {
            return this.ctx.close();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture deregister() {
            return this.ctx.deregister();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
            return this.ctx.bind(socketAddress, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
            return this.ctx.connect(socketAddress, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            return this.ctx.connect(socketAddress, socketAddress2, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture disconnect(ChannelPromise channelPromise) {
            return this.ctx.disconnect(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture close(ChannelPromise channelPromise) {
            return this.ctx.close(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture deregister(ChannelPromise channelPromise) {
            return this.ctx.deregister(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelHandlerContext read() {
            this.ctx.read();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture write(Object obj) {
            return this.ctx.write(obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
            return this.ctx.write(obj, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelHandlerContext flush() {
            this.ctx.flush();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
            return this.ctx.writeAndFlush(obj, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture writeAndFlush(Object obj) {
            return this.ctx.writeAndFlush(obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public ChannelPipeline pipeline() {
            return this.ctx.pipeline();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public ByteBufAllocator alloc() {
            return this.ctx.alloc();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelPromise newPromise() {
            return this.ctx.newPromise();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelProgressivePromise newProgressivePromise() {
            return this.ctx.newProgressivePromise();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture newSucceededFuture() {
            return this.ctx.newSucceededFuture();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelFuture newFailedFuture(Throwable th) {
            return this.ctx.newFailedFuture(th);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
        public ChannelPromise voidPromise() {
            return this.ctx.voidPromise();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.util.AttributeMap
        public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
            return this.ctx.channel().attr(attributeKey);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.util.AttributeMap
        public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
            return this.ctx.channel().hasAttr(attributeKey);
        }

        final void remove() {
            EventExecutor executor = executor();
            if (executor.inEventLoop()) {
                remove0();
            } else {
                executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.CombinedChannelDuplexHandler.DelegatingChannelHandlerContext.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DelegatingChannelHandlerContext.this.remove0();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void remove0() {
            if (this.removed) {
                return;
            }
            this.removed = true;
            try {
                this.handler.handlerRemoved(this);
            } catch (Throwable th) {
                fireExceptionCaught((Throwable) new ChannelPipelineException(this.handler.getClass().getName() + ".handlerRemoved() has thrown an exception.", th));
            }
        }
    }
}
