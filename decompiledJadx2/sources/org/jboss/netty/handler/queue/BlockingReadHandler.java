package org.jboss.netty.handler.queue;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.util.internal.DeadLockProofWorker;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
public class BlockingReadHandler<E> extends SimpleChannelUpstreamHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private volatile boolean closed;
    private final BlockingQueue<ChannelEvent> queue;

    public BlockingReadHandler() {
        this(QueueFactory.createQueue(ChannelEvent.class));
    }

    public BlockingReadHandler(BlockingQueue<ChannelEvent> blockingQueue) {
        if (blockingQueue == null) {
            throw new NullPointerException("queue");
        }
        this.queue = blockingQueue;
    }

    protected BlockingQueue<ChannelEvent> getQueue() {
        return this.queue;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public E read() throws IOException, InterruptedException {
        ChannelEvent readEvent = readEvent();
        if (readEvent == null) {
            return null;
        }
        if (readEvent instanceof MessageEvent) {
            return getMessage((MessageEvent) readEvent);
        }
        if (readEvent instanceof ExceptionEvent) {
            throw ((IOException) new IOException().initCause(((ExceptionEvent) readEvent).getCause()));
        }
        throw new IllegalStateException();
    }

    public E read(long j, TimeUnit timeUnit) throws IOException, InterruptedException {
        ChannelEvent readEvent = readEvent(j, timeUnit);
        if (readEvent == null) {
            return null;
        }
        if (readEvent instanceof MessageEvent) {
            return getMessage((MessageEvent) readEvent);
        }
        if (readEvent instanceof ExceptionEvent) {
            throw ((IOException) new IOException().initCause(((ExceptionEvent) readEvent).getCause()));
        }
        throw new IllegalStateException();
    }

    public ChannelEvent readEvent() throws InterruptedException {
        detectDeadLock();
        if (isClosed() && getQueue().isEmpty()) {
            return null;
        }
        ChannelEvent take = getQueue().take();
        if (take instanceof ChannelStateEvent) {
            return null;
        }
        return take;
    }

    public ChannelEvent readEvent(long j, TimeUnit timeUnit) throws InterruptedException, BlockingReadTimeoutException {
        detectDeadLock();
        if (isClosed() && getQueue().isEmpty()) {
            return null;
        }
        ChannelEvent poll = getQueue().poll(j, timeUnit);
        if (poll == null) {
            throw new BlockingReadTimeoutException();
        }
        if (poll instanceof ChannelStateEvent) {
            return null;
        }
        return poll;
    }

    private static void detectDeadLock() {
        if (DeadLockProofWorker.PARENT.get() != null) {
            throw new IllegalStateException("read*(...) in I/O thread causes a dead lock or sudden performance drop. Implement a state machine or call read*() from a different thread.");
        }
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        getQueue().put(messageEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, ExceptionEvent exceptionEvent) throws Exception {
        getQueue().put(exceptionEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void channelClosed(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        this.closed = true;
        getQueue().put(channelStateEvent);
    }

    private E getMessage(MessageEvent messageEvent) {
        return (E) messageEvent.getMessage();
    }
}
