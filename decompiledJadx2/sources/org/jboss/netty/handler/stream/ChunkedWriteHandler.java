package org.jboss.netty.handler.stream;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.LifeCycleAwareChannelHandler;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
public class ChunkedWriteHandler implements ChannelUpstreamHandler, ChannelDownstreamHandler, LifeCycleAwareChannelHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ChunkedWriteHandler.class);
    private volatile ChannelHandlerContext ctx;
    private MessageEvent currentEvent;
    private final Queue<MessageEvent> queue = QueueFactory.createQueue(MessageEvent.class);
    private final AtomicBoolean flush = new AtomicBoolean(false);

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public void resumeTransfer() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            return;
        }
        try {
            flush(channelHandlerContext, false);
        } catch (Exception e) {
            if (logger.isWarnEnabled()) {
                logger.warn("Unexpected exception while sending chunks.", e);
            }
        }
    }

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (!(channelEvent instanceof MessageEvent)) {
            channelHandlerContext.sendDownstream(channelEvent);
            return;
        }
        this.queue.offer((MessageEvent) channelEvent);
        Channel channel = channelHandlerContext.getChannel();
        if (channel.isWritable() || !channel.isConnected()) {
            this.ctx = channelHandlerContext;
            flush(channelHandlerContext, false);
        }
    }

    @Override // org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C87323.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if (i == 1) {
                flush(channelHandlerContext, true);
            } else if (i == 2 && !Boolean.TRUE.equals(channelStateEvent.getValue())) {
                flush(channelHandlerContext, true);
            }
        }
        channelHandlerContext.sendUpstream(channelEvent);
    }

    /* renamed from: org.jboss.netty.handler.stream.ChunkedWriteHandler$3 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87323 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.INTEREST_OPS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void discard(ChannelHandlerContext channelHandlerContext, boolean z) {
        ClosedChannelException closedChannelException = null;
        while (true) {
            MessageEvent messageEvent = this.currentEvent;
            if (messageEvent == null) {
                messageEvent = this.queue.poll();
            } else {
                this.currentEvent = null;
            }
            if (messageEvent == null) {
                break;
            }
            Object message = messageEvent.getMessage();
            if (message instanceof ChunkedInput) {
                closeInput((ChunkedInput) message);
            }
            if (closedChannelException == null) {
                closedChannelException = new ClosedChannelException();
            }
            messageEvent.getFuture().setFailure(closedChannelException);
        }
        if (closedChannelException != null) {
            if (z) {
                Channels.fireExceptionCaught(channelHandlerContext.getChannel(), closedChannelException);
            } else {
                Channels.fireExceptionCaughtLater(channelHandlerContext.getChannel(), closedChannelException);
            }
        }
    }

    private void flush(ChannelHandlerContext channelHandlerContext, boolean z) throws Exception {
        boolean z2;
        ChannelFuture future;
        Channel channel = channelHandlerContext.getChannel();
        boolean compareAndSet = this.flush.compareAndSet(false, true);
        if (compareAndSet) {
            try {
                if (!channel.isConnected()) {
                    discard(channelHandlerContext, z);
                    return;
                }
                while (channel.isWritable()) {
                    if (this.currentEvent == null) {
                        this.currentEvent = this.queue.poll();
                    }
                    if (this.currentEvent == null) {
                        break;
                    }
                    if (this.currentEvent.getFuture().isDone()) {
                        this.currentEvent = null;
                    } else {
                        final MessageEvent messageEvent = this.currentEvent;
                        Object message = messageEvent.getMessage();
                        if (message instanceof ChunkedInput) {
                            final ChunkedInput chunkedInput = (ChunkedInput) message;
                            try {
                                Object nextChunk = chunkedInput.nextChunk();
                                boolean isEndOfInput = chunkedInput.isEndOfInput();
                                if (nextChunk == null) {
                                    nextChunk = ChannelBuffers.EMPTY_BUFFER;
                                    z2 = !isEndOfInput;
                                } else {
                                    z2 = false;
                                }
                                if (z2) {
                                    break;
                                }
                                if (isEndOfInput) {
                                    this.currentEvent = null;
                                    future = messageEvent.getFuture();
                                    future.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.stream.ChunkedWriteHandler.1
                                        @Override // org.jboss.netty.channel.ChannelFutureListener
                                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                            ChunkedWriteHandler.closeInput(chunkedInput);
                                        }
                                    });
                                } else {
                                    future = Channels.future(channel);
                                    future.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.stream.ChunkedWriteHandler.2
                                        @Override // org.jboss.netty.channel.ChannelFutureListener
                                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                            if (channelFuture.isSuccess()) {
                                                return;
                                            }
                                            messageEvent.getFuture().setFailure(channelFuture.getCause());
                                            ChunkedWriteHandler.closeInput((ChunkedInput) messageEvent.getMessage());
                                        }
                                    });
                                }
                                Channels.write(channelHandlerContext, future, nextChunk, messageEvent.getRemoteAddress());
                            } catch (Throwable th) {
                                this.currentEvent = null;
                                messageEvent.getFuture().setFailure(th);
                                if (z) {
                                    Channels.fireExceptionCaught(channelHandlerContext, th);
                                } else {
                                    Channels.fireExceptionCaughtLater(channelHandlerContext, th);
                                }
                                closeInput(chunkedInput);
                            }
                        } else {
                            this.currentEvent = null;
                            channelHandlerContext.sendDownstream(messageEvent);
                        }
                    }
                    if (!channel.isConnected()) {
                        discard(channelHandlerContext, z);
                        return;
                    }
                }
            } finally {
                this.flush.set(false);
            }
        }
        if (compareAndSet) {
            if (!channel.isConnected() || (channel.isWritable() && !this.queue.isEmpty())) {
                flush(channelHandlerContext, z);
            }
        }
    }

    static void closeInput(ChunkedInput chunkedInput) {
        try {
            chunkedInput.close();
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to close a chunked input.", th);
            }
        }
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
        flush(channelHandlerContext, false);
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
        boolean z = false;
        IOException iOException = null;
        while (true) {
            MessageEvent messageEvent = this.currentEvent;
            if (messageEvent == null) {
                messageEvent = this.queue.poll();
            } else {
                this.currentEvent = null;
            }
            if (messageEvent == null) {
                break;
            }
            Object message = messageEvent.getMessage();
            if (message instanceof ChunkedInput) {
                closeInput((ChunkedInput) message);
            }
            if (iOException == null) {
                iOException = new IOException("Unable to flush event, discarding");
            }
            messageEvent.getFuture().setFailure(iOException);
            z = true;
        }
        if (z) {
            Channels.fireExceptionCaughtLater(channelHandlerContext.getChannel(), iOException);
        }
    }
}
