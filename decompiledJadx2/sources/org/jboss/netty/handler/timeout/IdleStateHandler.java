package org.jboss.netty.handler.timeout;

import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.LifeCycleAwareChannelHandler;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.WriteCompletionEvent;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class IdleStateHandler extends SimpleChannelUpstreamHandler implements LifeCycleAwareChannelHandler, ExternalResourceReleasable {
    final long allIdleTimeMillis;
    final long readerIdleTimeMillis;
    final Timer timer;
    final long writerIdleTimeMillis;

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public IdleStateHandler(Timer timer, int i, int i2, int i3) {
        this(timer, i, i2, i3, TimeUnit.SECONDS);
    }

    public IdleStateHandler(Timer timer, long j, long j2, long j3, TimeUnit timeUnit) {
        if (timer == null) {
            throw new NullPointerException("timer");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit");
        }
        this.timer = timer;
        if (j <= 0) {
            this.readerIdleTimeMillis = 0L;
        } else {
            this.readerIdleTimeMillis = Math.max(timeUnit.toMillis(j), 1L);
        }
        if (j2 <= 0) {
            this.writerIdleTimeMillis = 0L;
        } else {
            this.writerIdleTimeMillis = Math.max(timeUnit.toMillis(j2), 1L);
        }
        if (j3 <= 0) {
            this.allIdleTimeMillis = 0L;
        } else {
            this.allIdleTimeMillis = Math.max(timeUnit.toMillis(j3), 1L);
        }
    }

    public long getReaderIdleTimeInMillis() {
        return this.readerIdleTimeMillis;
    }

    public long getWriterIdleTimeInMillis() {
        return this.writerIdleTimeMillis;
    }

    public long getAllIdleTimeInMillis() {
        return this.allIdleTimeMillis;
    }

    @Override // org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        this.timer.stop();
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.getPipeline().isAttached()) {
            initialize(channelHandlerContext);
        }
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
        destroy(channelHandlerContext);
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void channelOpen(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        initialize(channelHandlerContext);
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void channelClosed(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        destroy(channelHandlerContext);
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        ((State) channelHandlerContext.getAttachment()).lastReadTime = System.currentTimeMillis();
        channelHandlerContext.sendUpstream(messageEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void writeComplete(ChannelHandlerContext channelHandlerContext, WriteCompletionEvent writeCompletionEvent) throws Exception {
        if (writeCompletionEvent.getWrittenAmount() > 0) {
            ((State) channelHandlerContext.getAttachment()).lastWriteTime = System.currentTimeMillis();
        }
        channelHandlerContext.sendUpstream(writeCompletionEvent);
    }

    private void initialize(ChannelHandlerContext channelHandlerContext) {
        State state = state(channelHandlerContext);
        if (state.destroyed) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        state.lastWriteTime = currentTimeMillis;
        state.lastReadTime = currentTimeMillis;
        if (this.readerIdleTimeMillis > 0) {
            state.readerIdleTimeout = this.timer.newTimeout(new ReaderIdleTimeoutTask(channelHandlerContext), this.readerIdleTimeMillis, TimeUnit.MILLISECONDS);
        }
        if (this.writerIdleTimeMillis > 0) {
            state.writerIdleTimeout = this.timer.newTimeout(new WriterIdleTimeoutTask(channelHandlerContext), this.writerIdleTimeMillis, TimeUnit.MILLISECONDS);
        }
        if (this.allIdleTimeMillis > 0) {
            state.allIdleTimeout = this.timer.newTimeout(new AllIdleTimeoutTask(channelHandlerContext), this.allIdleTimeMillis, TimeUnit.MILLISECONDS);
        }
    }

    private static void destroy(ChannelHandlerContext channelHandlerContext) {
        State state;
        synchronized (channelHandlerContext) {
            state = state(channelHandlerContext);
            state.destroyed = true;
        }
        if (state.readerIdleTimeout != null) {
            state.readerIdleTimeout.cancel();
            state.readerIdleTimeout = null;
        }
        if (state.writerIdleTimeout != null) {
            state.writerIdleTimeout.cancel();
            state.writerIdleTimeout = null;
        }
        if (state.allIdleTimeout != null) {
            state.allIdleTimeout.cancel();
            state.allIdleTimeout = null;
        }
    }

    private static State state(ChannelHandlerContext channelHandlerContext) {
        synchronized (channelHandlerContext) {
            State state = (State) channelHandlerContext.getAttachment();
            if (state != null) {
                return state;
            }
            State state2 = new State();
            channelHandlerContext.setAttachment(state2);
            return state2;
        }
    }

    protected void channelIdle(ChannelHandlerContext channelHandlerContext, IdleState idleState, long j) throws Exception {
        channelHandlerContext.sendUpstream(new DefaultIdleStateEvent(channelHandlerContext.getChannel(), idleState, j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class ReaderIdleTimeoutTask implements TimerTask {
        private final ChannelHandlerContext ctx;

        ReaderIdleTimeoutTask(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        @Override // org.jboss.netty.util.TimerTask
        public void run(Timeout timeout) throws Exception {
            if (timeout.isCancelled() || !this.ctx.getChannel().isOpen()) {
                return;
            }
            State state = (State) this.ctx.getAttachment();
            long currentTimeMillis = System.currentTimeMillis();
            long j = state.lastReadTime;
            long j2 = IdleStateHandler.this.readerIdleTimeMillis - (currentTimeMillis - j);
            if (j2 <= 0) {
                state.readerIdleTimeout = IdleStateHandler.this.timer.newTimeout(this, IdleStateHandler.this.readerIdleTimeMillis, TimeUnit.MILLISECONDS);
                try {
                    IdleStateHandler.this.channelIdle(this.ctx, IdleState.READER_IDLE, j);
                    return;
                } catch (Throwable th) {
                    Channels.fireExceptionCaught(this.ctx, th);
                    return;
                }
            }
            state.readerIdleTimeout = IdleStateHandler.this.timer.newTimeout(this, j2, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class WriterIdleTimeoutTask implements TimerTask {
        private final ChannelHandlerContext ctx;

        WriterIdleTimeoutTask(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        @Override // org.jboss.netty.util.TimerTask
        public void run(Timeout timeout) throws Exception {
            if (timeout.isCancelled() || !this.ctx.getChannel().isOpen()) {
                return;
            }
            State state = (State) this.ctx.getAttachment();
            long currentTimeMillis = System.currentTimeMillis();
            long j = state.lastWriteTime;
            long j2 = IdleStateHandler.this.writerIdleTimeMillis - (currentTimeMillis - j);
            if (j2 <= 0) {
                state.writerIdleTimeout = IdleStateHandler.this.timer.newTimeout(this, IdleStateHandler.this.writerIdleTimeMillis, TimeUnit.MILLISECONDS);
                try {
                    IdleStateHandler.this.channelIdle(this.ctx, IdleState.WRITER_IDLE, j);
                    return;
                } catch (Throwable th) {
                    Channels.fireExceptionCaught(this.ctx, th);
                    return;
                }
            }
            state.writerIdleTimeout = IdleStateHandler.this.timer.newTimeout(this, j2, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class AllIdleTimeoutTask implements TimerTask {
        private final ChannelHandlerContext ctx;

        AllIdleTimeoutTask(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        @Override // org.jboss.netty.util.TimerTask
        public void run(Timeout timeout) throws Exception {
            if (timeout.isCancelled() || !this.ctx.getChannel().isOpen()) {
                return;
            }
            State state = (State) this.ctx.getAttachment();
            long currentTimeMillis = System.currentTimeMillis();
            long max = Math.max(state.lastReadTime, state.lastWriteTime);
            long j = IdleStateHandler.this.allIdleTimeMillis - (currentTimeMillis - max);
            if (j <= 0) {
                state.allIdleTimeout = IdleStateHandler.this.timer.newTimeout(this, IdleStateHandler.this.allIdleTimeMillis, TimeUnit.MILLISECONDS);
                try {
                    IdleStateHandler.this.channelIdle(this.ctx, IdleState.ALL_IDLE, max);
                    return;
                } catch (Throwable th) {
                    Channels.fireExceptionCaught(this.ctx, th);
                    return;
                }
            }
            state.allIdleTimeout = IdleStateHandler.this.timer.newTimeout(this, j, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class State {
        volatile Timeout allIdleTimeout;
        volatile boolean destroyed;
        volatile long lastReadTime;
        volatile long lastWriteTime;
        volatile Timeout readerIdleTimeout;
        volatile Timeout writerIdleTimeout;

        State() {
        }
    }
}
