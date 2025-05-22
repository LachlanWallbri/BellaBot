package io.grpc.netty.shaded.io.netty.handler.flush;

import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.concurrent.Future;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class FlushConsolidationHandler extends ChannelDuplexHandler {
    public static final int DEFAULT_EXPLICIT_FLUSH_AFTER_FLUSHES = 256;
    private final boolean consolidateWhenNoReadInProgress;
    private ChannelHandlerContext ctx;
    private final int explicitFlushAfterFlushes;
    private int flushPendingCount;
    private final Runnable flushTask;
    private Future<?> nextScheduledFlush;
    private boolean readInProgress;

    public FlushConsolidationHandler() {
        this(256, false);
    }

    public FlushConsolidationHandler(int i) {
        this(i, false);
    }

    public FlushConsolidationHandler(int i, boolean z) {
        this.explicitFlushAfterFlushes = ObjectUtil.checkPositive(i, "explicitFlushAfterFlushes");
        this.consolidateWhenNoReadInProgress = z;
        this.flushTask = z ? new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.flush.FlushConsolidationHandler.1
            @Override // java.lang.Runnable
            public void run() {
                if (FlushConsolidationHandler.this.flushPendingCount <= 0 || FlushConsolidationHandler.this.readInProgress) {
                    return;
                }
                FlushConsolidationHandler.this.flushPendingCount = 0;
                FlushConsolidationHandler.this.nextScheduledFlush = null;
                FlushConsolidationHandler.this.ctx.flush();
            }
        } : null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.readInProgress) {
            int i = this.flushPendingCount + 1;
            this.flushPendingCount = i;
            if (i == this.explicitFlushAfterFlushes) {
                flushNow(channelHandlerContext);
                return;
            }
            return;
        }
        if (this.consolidateWhenNoReadInProgress) {
            int i2 = this.flushPendingCount + 1;
            this.flushPendingCount = i2;
            if (i2 == this.explicitFlushAfterFlushes) {
                flushNow(channelHandlerContext);
                return;
            } else {
                scheduleFlush(channelHandlerContext);
                return;
            }
        }
        flushNow(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        resetReadAndFlushIfNeeded(channelHandlerContext);
        channelHandlerContext.fireChannelReadComplete();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        this.readInProgress = true;
        channelHandlerContext.fireChannelRead(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        resetReadAndFlushIfNeeded(channelHandlerContext);
        channelHandlerContext.fireExceptionCaught(th);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        resetReadAndFlushIfNeeded(channelHandlerContext);
        channelHandlerContext.disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        resetReadAndFlushIfNeeded(channelHandlerContext);
        channelHandlerContext.close(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!channelHandlerContext.channel().isWritable()) {
            flushIfNeeded(channelHandlerContext);
        }
        channelHandlerContext.fireChannelWritabilityChanged();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        flushIfNeeded(channelHandlerContext);
    }

    private void resetReadAndFlushIfNeeded(ChannelHandlerContext channelHandlerContext) {
        this.readInProgress = false;
        flushIfNeeded(channelHandlerContext);
    }

    private void flushIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (this.flushPendingCount > 0) {
            flushNow(channelHandlerContext);
        }
    }

    private void flushNow(ChannelHandlerContext channelHandlerContext) {
        cancelScheduledFlush();
        this.flushPendingCount = 0;
        channelHandlerContext.flush();
    }

    private void scheduleFlush(ChannelHandlerContext channelHandlerContext) {
        if (this.nextScheduledFlush == null) {
            this.nextScheduledFlush = channelHandlerContext.channel().eventLoop().submit(this.flushTask);
        }
    }

    private void cancelScheduledFlush() {
        Future<?> future = this.nextScheduledFlush;
        if (future != null) {
            future.cancel(false);
            this.nextScheduledFlush = null;
        }
    }
}
