package io.grpc.netty.shaded.io.netty.handler.stream;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelProgressivePromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ChunkedWriteHandler extends ChannelDuplexHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ChunkedWriteHandler.class);
    private volatile ChannelHandlerContext ctx;
    private final Queue<PendingWrite> queue = new ArrayDeque();

    public ChunkedWriteHandler() {
    }

    @Deprecated
    public ChunkedWriteHandler(int i) {
        if (i > 0) {
            return;
        }
        throw new IllegalArgumentException("maxPendingWrites: " + i + " (expected: > 0)");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    public void resumeTransfer() {
        final ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            return;
        }
        if (channelHandlerContext.executor().inEventLoop()) {
            resumeTransfer0(channelHandlerContext);
        } else {
            channelHandlerContext.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.stream.ChunkedWriteHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    ChunkedWriteHandler.this.resumeTransfer0(channelHandlerContext);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeTransfer0(ChannelHandlerContext channelHandlerContext) {
        try {
            doFlush(channelHandlerContext);
        } catch (Exception e) {
            logger.warn("Unexpected exception while sending chunks.", (Throwable) e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        this.queue.add(new PendingWrite(obj, channelPromise));
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        doFlush(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        doFlush(channelHandlerContext);
        channelHandlerContext.fireChannelInactive();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.channel().isWritable()) {
            doFlush(channelHandlerContext);
        }
        channelHandlerContext.fireChannelWritabilityChanged();
    }

    private void discard(Throwable th) {
        while (true) {
            PendingWrite poll = this.queue.poll();
            if (poll == null) {
                return;
            }
            Object obj = poll.msg;
            if (obj instanceof ChunkedInput) {
                ChunkedInput chunkedInput = (ChunkedInput) obj;
                try {
                    boolean isEndOfInput = chunkedInput.isEndOfInput();
                    long length = chunkedInput.length();
                    closeInput(chunkedInput);
                    if (!isEndOfInput) {
                        if (th == null) {
                            th = new ClosedChannelException();
                        }
                        poll.fail(th);
                    } else {
                        poll.success(length);
                    }
                } catch (Exception e) {
                    closeInput(chunkedInput);
                    poll.fail(e);
                    if (logger.isWarnEnabled()) {
                        logger.warn(ChunkedInput.class.getSimpleName() + " failed", (Throwable) e);
                    }
                }
            } else {
                if (th == null) {
                    th = new ClosedChannelException();
                }
                poll.fail(th);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void doFlush(ChannelHandlerContext channelHandlerContext) {
        final PendingWrite peek;
        Object readChunk;
        Channel channel = channelHandlerContext.channel();
        Object obj = null;
        if (!channel.isActive()) {
            discard(null);
            return;
        }
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        boolean z = true;
        while (true) {
            if (!channel.isWritable() || (peek = this.queue.peek()) == null) {
                break;
            }
            if (peek.promise.isDone()) {
                this.queue.remove();
            } else {
                Object obj2 = peek.msg;
                if (obj2 instanceof ChunkedInput) {
                    ChunkedInput chunkedInput = (ChunkedInput) obj2;
                    try {
                        readChunk = chunkedInput.readChunk(alloc);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        boolean isEndOfInput = chunkedInput.isEndOfInput();
                        if (readChunk == null ? !isEndOfInput : false) {
                            break;
                        }
                        if (readChunk == null) {
                            readChunk = Unpooled.EMPTY_BUFFER;
                        }
                        ChannelFuture writeAndFlush = channelHandlerContext.writeAndFlush(readChunk);
                        if (isEndOfInput) {
                            this.queue.remove();
                            if (writeAndFlush.isDone()) {
                                handleEndOfInputFuture(writeAndFlush, peek);
                            } else {
                                writeAndFlush.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.stream.ChunkedWriteHandler.2
                                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                                    public void operationComplete(ChannelFuture channelFuture) {
                                        ChunkedWriteHandler.handleEndOfInputFuture(channelFuture, peek);
                                    }
                                });
                            }
                        } else {
                            final boolean z2 = !channel.isWritable();
                            if (writeAndFlush.isDone()) {
                                handleFuture(writeAndFlush, peek, z2);
                            } else {
                                writeAndFlush.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.stream.ChunkedWriteHandler.3
                                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                                    public void operationComplete(ChannelFuture channelFuture) {
                                        ChunkedWriteHandler.this.handleFuture(channelFuture, peek, z2);
                                    }
                                });
                            }
                        }
                        z = false;
                    } catch (Throwable th2) {
                        th = th2;
                        obj = readChunk;
                        this.queue.remove();
                        if (obj != null) {
                            ReferenceCountUtil.release(obj);
                        }
                        closeInput(chunkedInput);
                        peek.fail(th);
                        if (z) {
                        }
                    }
                } else {
                    this.queue.remove();
                    channelHandlerContext.write(obj2, peek.promise);
                    z = true;
                }
                if (!channel.isActive()) {
                    discard(new ClosedChannelException());
                    break;
                }
            }
        }
        if (z) {
            return;
        }
        channelHandlerContext.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleEndOfInputFuture(ChannelFuture channelFuture, PendingWrite pendingWrite) {
        ChunkedInput chunkedInput = (ChunkedInput) pendingWrite.msg;
        if (!channelFuture.isSuccess()) {
            closeInput(chunkedInput);
            pendingWrite.fail(channelFuture.cause());
            return;
        }
        long progress = chunkedInput.progress();
        long length = chunkedInput.length();
        closeInput(chunkedInput);
        pendingWrite.progress(progress, length);
        pendingWrite.success(length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFuture(ChannelFuture channelFuture, PendingWrite pendingWrite, boolean z) {
        ChunkedInput chunkedInput = (ChunkedInput) pendingWrite.msg;
        if (!channelFuture.isSuccess()) {
            closeInput(chunkedInput);
            pendingWrite.fail(channelFuture.cause());
            return;
        }
        pendingWrite.progress(chunkedInput.progress(), chunkedInput.length());
        if (z && channelFuture.channel().isWritable()) {
            resumeTransfer();
        }
    }

    private static void closeInput(ChunkedInput<?> chunkedInput) {
        try {
            chunkedInput.close();
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to close a chunked input.", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PendingWrite {
        final Object msg;
        final ChannelPromise promise;

        PendingWrite(Object obj, ChannelPromise channelPromise) {
            this.msg = obj;
            this.promise = channelPromise;
        }

        void fail(Throwable th) {
            ReferenceCountUtil.release(this.msg);
            this.promise.tryFailure(th);
        }

        void success(long j) {
            if (this.promise.isDone()) {
                return;
            }
            progress(j, j);
            this.promise.trySuccess();
        }

        void progress(long j, long j2) {
            ChannelPromise channelPromise = this.promise;
            if (channelPromise instanceof ChannelProgressivePromise) {
                ((ChannelProgressivePromise) channelPromise).tryProgress(j, j2);
            }
        }
    }
}
