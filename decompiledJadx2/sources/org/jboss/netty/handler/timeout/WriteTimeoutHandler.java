package org.jboss.netty.handler.timeout;

import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class WriteTimeoutHandler extends SimpleChannelDownstreamHandler implements ExternalResourceReleasable {
    static final WriteTimeoutException EXCEPTION = new WriteTimeoutException();
    private final long timeoutMillis;
    private final Timer timer;

    public WriteTimeoutHandler(Timer timer, int i) {
        this(timer, i, TimeUnit.SECONDS);
    }

    public WriteTimeoutHandler(Timer timer, long j, TimeUnit timeUnit) {
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

    protected long getTimeoutMillis(MessageEvent messageEvent) {
        return this.timeoutMillis;
    }

    @Override // org.jboss.netty.channel.SimpleChannelDownstreamHandler
    public void writeRequested(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        long timeoutMillis = getTimeoutMillis(messageEvent);
        if (timeoutMillis > 0) {
            ChannelFuture future = messageEvent.getFuture();
            future.addListener(new TimeoutCanceller(this.timer.newTimeout(new WriteTimeoutTask(channelHandlerContext, future), timeoutMillis, TimeUnit.MILLISECONDS)));
        }
        super.writeRequested(channelHandlerContext, messageEvent);
    }

    protected void writeTimedOut(ChannelHandlerContext channelHandlerContext) throws Exception {
        Channels.fireExceptionCaughtLater(channelHandlerContext, EXCEPTION);
    }

    /* loaded from: classes7.dex */
    private final class WriteTimeoutTask implements TimerTask {
        private final ChannelHandlerContext ctx;
        private final ChannelFuture future;

        WriteTimeoutTask(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture) {
            this.ctx = channelHandlerContext;
            this.future = channelFuture;
        }

        @Override // org.jboss.netty.util.TimerTask
        public void run(Timeout timeout) throws Exception {
            if (!timeout.isCancelled() && this.ctx.getChannel().isOpen() && this.future.setFailure(WriteTimeoutHandler.EXCEPTION)) {
                try {
                    WriteTimeoutHandler.this.writeTimedOut(this.ctx);
                } catch (Throwable th) {
                    Channels.fireExceptionCaught(this.ctx, th);
                }
            }
        }
    }

    /* loaded from: classes7.dex */
    private static final class TimeoutCanceller implements ChannelFutureListener {
        private final Timeout timeout;

        TimeoutCanceller(Timeout timeout) {
            this.timeout = timeout;
        }

        @Override // org.jboss.netty.channel.ChannelFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            this.timeout.cancel();
        }
    }
}
