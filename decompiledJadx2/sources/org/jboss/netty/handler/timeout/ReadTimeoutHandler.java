package org.jboss.netty.handler.timeout;

import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.LifeCycleAwareChannelHandler;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class ReadTimeoutHandler extends SimpleChannelUpstreamHandler implements LifeCycleAwareChannelHandler, ExternalResourceReleasable {
    static final ReadTimeoutException EXCEPTION = new ReadTimeoutException();
    final long timeoutMillis;
    final Timer timer;

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public ReadTimeoutHandler(Timer timer, int i) {
        this(timer, i, TimeUnit.SECONDS);
    }

    public ReadTimeoutHandler(Timer timer, long j, TimeUnit timeUnit) {
        if (timer == null) {
            throw new NullPointerException("timer");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit");
        }
        this.timer = timer;
        if (j <= 0) {
            this.timeoutMillis = 0L;
        } else {
            this.timeoutMillis = Math.max(timeUnit.toMillis(j), 1L);
        }
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

    private void initialize(ChannelHandlerContext channelHandlerContext) {
        State state = state(channelHandlerContext);
        if (!state.destroyed && this.timeoutMillis > 0) {
            state.timeout = this.timer.newTimeout(new ReadTimeoutTask(channelHandlerContext), this.timeoutMillis, TimeUnit.MILLISECONDS);
        }
    }

    private static void destroy(ChannelHandlerContext channelHandlerContext) {
        State state;
        synchronized (channelHandlerContext) {
            state = state(channelHandlerContext);
            state.destroyed = true;
        }
        if (state.timeout != null) {
            state.timeout.cancel();
            state.timeout = null;
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

    protected void readTimedOut(ChannelHandlerContext channelHandlerContext) throws Exception {
        Channels.fireExceptionCaught(channelHandlerContext, EXCEPTION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class ReadTimeoutTask implements TimerTask {
        private final ChannelHandlerContext ctx;

        ReadTimeoutTask(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        @Override // org.jboss.netty.util.TimerTask
        public void run(Timeout timeout) throws Exception {
            if (!timeout.isCancelled() && this.ctx.getChannel().isOpen()) {
                State state = (State) this.ctx.getAttachment();
                long currentTimeMillis = ReadTimeoutHandler.this.timeoutMillis - (System.currentTimeMillis() - state.lastReadTime);
                if (currentTimeMillis <= 0) {
                    state.timeout = ReadTimeoutHandler.this.timer.newTimeout(this, ReadTimeoutHandler.this.timeoutMillis, TimeUnit.MILLISECONDS);
                    try {
                        ReadTimeoutHandler.this.readTimedOut(this.ctx);
                        return;
                    } catch (Throwable th) {
                        Channels.fireExceptionCaught(this.ctx, th);
                        return;
                    }
                }
                state.timeout = ReadTimeoutHandler.this.timer.newTimeout(this, currentTimeMillis, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class State {
        volatile boolean destroyed;
        volatile long lastReadTime = System.currentTimeMillis();
        volatile Timeout timeout;

        State() {
        }
    }
}
