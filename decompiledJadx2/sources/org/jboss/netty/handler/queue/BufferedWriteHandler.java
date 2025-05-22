package org.jboss.netty.handler.queue;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.LifeCycleAwareChannelHandler;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
public class BufferedWriteHandler extends SimpleChannelHandler implements LifeCycleAwareChannelHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean consolidateOnFlush;
    private volatile ChannelHandlerContext ctx;
    private final Queue<MessageEvent> queue;

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public BufferedWriteHandler() {
        this(false);
    }

    public BufferedWriteHandler(Queue<MessageEvent> queue) {
        this(queue, false);
    }

    public BufferedWriteHandler(boolean z) {
        this(QueueFactory.createQueue(MessageEvent.class), z);
    }

    public BufferedWriteHandler(Queue<MessageEvent> queue, boolean z) {
        if (queue == null) {
            throw new NullPointerException("queue");
        }
        this.queue = queue;
        this.consolidateOnFlush = z;
    }

    public boolean isConsolidateOnFlush() {
        return this.consolidateOnFlush;
    }

    protected Queue<MessageEvent> getQueue() {
        return this.queue;
    }

    public void flush() {
        flush(this.consolidateOnFlush);
    }

    public void flush(boolean z) {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            return;
        }
        Queue<MessageEvent> queue = getQueue();
        if (z) {
            if (queue.isEmpty()) {
                return;
            }
            List<MessageEvent> arrayList = new ArrayList<>();
            synchronized (this) {
                while (true) {
                    MessageEvent poll = queue.poll();
                    if (poll != null) {
                        if (!(poll.getMessage() instanceof ChannelBuffer)) {
                            arrayList = consolidatedWrite(arrayList);
                            if (arrayList == null) {
                                arrayList = new ArrayList<>();
                            }
                            channelHandlerContext.sendDownstream(poll);
                        } else {
                            arrayList.add(poll);
                        }
                    } else {
                        consolidatedWrite(arrayList);
                    }
                }
            }
            return;
        }
        synchronized (this) {
            while (true) {
                MessageEvent poll2 = queue.poll();
                if (poll2 != null) {
                    channelHandlerContext.sendDownstream(poll2);
                }
            }
        }
    }

    private List<MessageEvent> consolidatedWrite(final List<MessageEvent> list) {
        int size = list.size();
        if (size == 1) {
            this.ctx.sendDownstream(list.remove(0));
            return list;
        }
        if (size == 0) {
            return list;
        }
        ChannelBuffer[] channelBufferArr = new ChannelBuffer[size];
        for (int i = 0; i < channelBufferArr.length; i++) {
            channelBufferArr[i] = (ChannelBuffer) list.get(i).getMessage();
        }
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(channelBufferArr);
        ChannelFuture future = Channels.future(this.ctx.getChannel());
        future.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.queue.BufferedWriteHandler.1
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((MessageEvent) it.next()).getFuture().setSuccess();
                    }
                } else {
                    Throwable cause = channelFuture.getCause();
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        ((MessageEvent) it2.next()).getFuture().setFailure(cause);
                    }
                }
            }
        });
        Channels.write(this.ctx, future, wrappedBuffer);
        return null;
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void writeRequested(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        if (this.ctx == null) {
            this.ctx = channelHandlerContext;
        }
        getQueue().add(messageEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void disconnectRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        try {
            flush(this.consolidateOnFlush);
        } finally {
            channelHandlerContext.sendDownstream(channelStateEvent);
        }
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void closeRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        try {
            flush(this.consolidateOnFlush);
        } finally {
            channelHandlerContext.sendDownstream(channelStateEvent);
        }
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void channelClosed(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        ClosedChannelException closedChannelException = null;
        while (true) {
            MessageEvent poll = this.queue.poll();
            if (poll == null) {
                break;
            }
            if (closedChannelException == null) {
                closedChannelException = new ClosedChannelException();
            }
            poll.getFuture().setFailure(closedChannelException);
        }
        if (closedChannelException != null) {
            Channels.fireExceptionCaught(channelHandlerContext.getChannel(), closedChannelException);
        }
        super.channelClosed(channelHandlerContext, channelStateEvent);
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
        flush(this.consolidateOnFlush);
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
        IOException iOException = null;
        while (true) {
            MessageEvent poll = this.queue.poll();
            if (poll == null) {
                break;
            }
            if (iOException == null) {
                iOException = new IOException("Unable to flush message");
            }
            poll.getFuture().setFailure(iOException);
        }
        if (iOException != null) {
            Channels.fireExceptionCaughtLater(channelHandlerContext.getChannel(), iOException);
        }
    }
}
