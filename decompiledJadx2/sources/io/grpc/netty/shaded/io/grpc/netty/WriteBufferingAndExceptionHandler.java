package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.Status;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class WriteBufferingAndExceptionHandler extends ChannelDuplexHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger = Logger.getLogger(WriteBufferingAndExceptionHandler.class.getName());
    private final Queue<ChannelWrite> bufferedWrites = new ArrayDeque();
    private Throwable failCause;
    private boolean flushRequested;
    private final ChannelHandler next;
    private boolean writing;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteBufferingAndExceptionHandler(ChannelHandler channelHandler) {
        this.next = (ChannelHandler) Preconditions.checkNotNull(channelHandler, ES6Iterator.NEXT_METHOD);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), null, this.next);
        super.handlerAdded(channelHandlerContext);
        channelHandlerContext.pipeline().fireUserEventTriggered((Object) ProtocolNegotiationEvent.DEFAULT);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.bufferedWrites.isEmpty()) {
            failWrites(Status.INTERNAL.withDescription("Buffer removed before draining writes").asRuntimeException());
        }
        super.handlerRemoved(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) {
        failWrites(Status.UNAVAILABLE.withDescription("Connection closed while performing protocol negotiation for " + channelHandlerContext.pipeline().names()).asRuntimeException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
        Throwable th2 = this.failCause;
        failWrites(Utils.statusFromThrowable(th).augmentDescription("Channel Pipeline: " + channelHandlerContext.pipeline().names()).asRuntimeException());
        if (channelHandlerContext.channel().isActive() && th2 == null) {
            channelHandlerContext.close().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.WriteBufferingAndExceptionHandler.1LogOnFailure
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) {
                    if (channelFuture.isSuccess()) {
                        return;
                    }
                    WriteBufferingAndExceptionHandler.logger.log(Level.FINE, "Failed closing channel", channelFuture.cause());
                }
            });
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) {
        Throwable th = this.failCause;
        if (th != null) {
            channelPromise.setFailure(th);
            ReferenceCountUtil.release(obj);
        } else {
            if ((obj instanceof GracefulCloseCommand) || (obj instanceof ForcefulCloseCommand)) {
                channelHandlerContext.close();
            }
            this.bufferedWrites.add(new ChannelWrite(obj, channelPromise));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        super.connect(channelHandlerContext, socketAddress, socketAddress2, channelPromise);
        channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.WriteBufferingAndExceptionHandler.1ConnectListener
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) {
                if (channelFuture.isSuccess()) {
                    return;
                }
                WriteBufferingAndExceptionHandler.this.failWrites(channelFuture.cause());
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
        try {
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "Unexpected channelRead()->{0} reached end of pipeline {1}", new Object[]{obj instanceof ByteBuf ? ByteBufUtil.hexDump((ByteBuf) obj) : obj, channelHandlerContext.pipeline().names()});
            }
            exceptionCaught(channelHandlerContext, Status.INTERNAL.withDescription("channelRead() missed by ProtocolNegotiator handler: " + obj).asRuntimeException());
        } finally {
            ReferenceCountUtil.safeRelease(obj);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) {
        this.flushRequested = true;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        failWrites(Status.UNAVAILABLE.withDescription("Connection closing while performing protocol negotiation for " + channelHandlerContext.pipeline().names()).asRuntimeException());
        super.close(channelHandlerContext, channelPromise);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeBufferedAndRemove(ChannelHandlerContext channelHandlerContext) {
        if (!channelHandlerContext.channel().isActive() || this.writing) {
            return;
        }
        this.writing = true;
        while (!this.bufferedWrites.isEmpty()) {
            ChannelWrite poll = this.bufferedWrites.poll();
            channelHandlerContext.write(poll.msg, poll.promise);
        }
        if (this.flushRequested) {
            channelHandlerContext.flush();
        }
        channelHandlerContext.pipeline().remove(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failWrites(Throwable th) {
        if (this.failCause == null) {
            this.failCause = th;
        } else {
            logger.log(Level.FINE, "Ignoring duplicate failure", th);
        }
        while (!this.bufferedWrites.isEmpty()) {
            ChannelWrite poll = this.bufferedWrites.poll();
            poll.promise.setFailure(th);
            ReferenceCountUtil.release(poll.msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ChannelWrite {
        final Object msg;
        final ChannelPromise promise;

        ChannelWrite(Object obj, ChannelPromise channelPromise) {
            this.msg = obj;
            this.promise = channelPromise;
        }
    }
}
