package org.jboss.netty.handler.traffic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.DefaultObjectSizeEstimator;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.ObjectSizeEstimator;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

/* loaded from: classes7.dex */
public abstract class AbstractTrafficShapingHandler extends SimpleChannelHandler implements ExternalResourceReleasable {
    public static final long DEFAULT_CHECK_INTERVAL = 1000;
    private static final long MINIMAL_WAIT = 10;
    static InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractTrafficShapingHandler.class);
    private ObjectSizeEstimator objectSizeEstimator;
    private long readLimit;
    private volatile Timeout timeout;
    protected Timer timer;
    protected TrafficCounter trafficCounter;
    private long writeLimit;
    protected long checkInterval = 1000;
    final AtomicBoolean release = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: protected */
    public void doAccounting(TrafficCounter trafficCounter) {
    }

    private void init(ObjectSizeEstimator objectSizeEstimator, Timer timer, long j, long j2, long j3) {
        this.objectSizeEstimator = objectSizeEstimator;
        this.timer = timer;
        this.writeLimit = j;
        this.readLimit = j2;
        this.checkInterval = j3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTrafficCounter(TrafficCounter trafficCounter) {
        this.trafficCounter = trafficCounter;
    }

    public AbstractTrafficShapingHandler(Timer timer, long j, long j2, long j3) {
        init(new DefaultObjectSizeEstimator(), timer, j, j2, j3);
    }

    public AbstractTrafficShapingHandler(ObjectSizeEstimator objectSizeEstimator, Timer timer, long j, long j2, long j3) {
        init(objectSizeEstimator, timer, j, j2, j3);
    }

    public AbstractTrafficShapingHandler(Timer timer, long j, long j2) {
        init(new DefaultObjectSizeEstimator(), timer, j, j2, 1000L);
    }

    public AbstractTrafficShapingHandler(ObjectSizeEstimator objectSizeEstimator, Timer timer, long j, long j2) {
        init(objectSizeEstimator, timer, j, j2, 1000L);
    }

    public AbstractTrafficShapingHandler(Timer timer) {
        init(new DefaultObjectSizeEstimator(), timer, 0L, 0L, 1000L);
    }

    public AbstractTrafficShapingHandler(ObjectSizeEstimator objectSizeEstimator, Timer timer) {
        init(objectSizeEstimator, timer, 0L, 0L, 1000L);
    }

    public AbstractTrafficShapingHandler(Timer timer, long j) {
        init(new DefaultObjectSizeEstimator(), timer, 0L, 0L, j);
    }

    public AbstractTrafficShapingHandler(ObjectSizeEstimator objectSizeEstimator, Timer timer, long j) {
        init(objectSizeEstimator, timer, 0L, 0L, j);
    }

    public void configure(long j, long j2, long j3) {
        configure(j, j2);
        configure(j3);
    }

    public void configure(long j, long j2) {
        this.writeLimit = j;
        this.readLimit = j2;
        TrafficCounter trafficCounter = this.trafficCounter;
        if (trafficCounter != null) {
            trafficCounter.resetAccounting(System.currentTimeMillis() + 1);
        }
    }

    public void configure(long j) {
        this.checkInterval = j;
        TrafficCounter trafficCounter = this.trafficCounter;
        if (trafficCounter != null) {
            trafficCounter.configure(this.checkInterval);
        }
    }

    /* loaded from: classes7.dex */
    private class ReopenReadTimerTask implements TimerTask {
        ChannelHandlerContext ctx;

        ReopenReadTimerTask(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        @Override // org.jboss.netty.util.TimerTask
        public void run(Timeout timeout) throws Exception {
            ChannelHandlerContext channelHandlerContext;
            if (AbstractTrafficShapingHandler.this.release.get() || (channelHandlerContext = this.ctx) == null || channelHandlerContext.getChannel() == null || !this.ctx.getChannel().isConnected()) {
                return;
            }
            this.ctx.setAttachment(null);
            this.ctx.getChannel().setReadable(true);
        }
    }

    private static long getTimeToWait(long j, long j2, long j3, long j4) {
        long j5 = j4 - j3;
        if (j5 == 0) {
            return 0L;
        }
        return ((((j2 * 1000) / j) - j5) / MINIMAL_WAIT) * MINIMAL_WAIT;
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long estimateSize = this.objectSizeEstimator.estimateSize(messageEvent.getMessage());
            if (this.trafficCounter != null) {
                this.trafficCounter.bytesRecvFlowControl(channelHandlerContext, estimateSize);
                if (this.readLimit == 0) {
                    return;
                }
                long timeToWait = getTimeToWait(this.readLimit, this.trafficCounter.getCurrentReadBytes(), this.trafficCounter.getLastTime(), currentTimeMillis);
                if (timeToWait >= MINIMAL_WAIT) {
                    Channel channel = channelHandlerContext.getChannel();
                    if (channel == null || !channel.isConnected()) {
                        if (this.release.get()) {
                        } else {
                            Thread.sleep(timeToWait);
                        }
                    } else if (this.timer == null) {
                        if (this.release.get()) {
                            return;
                        }
                        Thread.sleep(timeToWait);
                    } else if (channelHandlerContext.getAttachment() == null) {
                        channelHandlerContext.setAttachment(Boolean.TRUE);
                        channel.setReadable(false);
                        this.timeout = this.timer.newTimeout(new ReopenReadTimerTask(channelHandlerContext), timeToWait, TimeUnit.MILLISECONDS);
                    } else if (this.release.get()) {
                    } else {
                        Thread.sleep(timeToWait);
                    }
                }
            }
        } finally {
            super.messageReceived(channelHandlerContext, messageEvent);
        }
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void writeRequested(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long estimateSize = this.objectSizeEstimator.estimateSize(messageEvent.getMessage());
            if (this.trafficCounter != null) {
                this.trafficCounter.bytesWriteFlowControl(estimateSize);
                if (this.writeLimit == 0) {
                    return;
                }
                long timeToWait = getTimeToWait(this.writeLimit, this.trafficCounter.getCurrentWrittenBytes(), this.trafficCounter.getLastTime(), currentTimeMillis);
                if (timeToWait >= MINIMAL_WAIT) {
                    if (this.release.get()) {
                    } else {
                        Thread.sleep(timeToWait);
                    }
                }
            }
        } finally {
            super.writeRequested(channelHandlerContext, messageEvent);
        }
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler, org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            if (channelStateEvent.getState() == ChannelState.INTEREST_OPS) {
                if ((((Integer) channelStateEvent.getValue()).intValue() & 1) != 0) {
                    if (channelHandlerContext.getAttachment() != null) {
                        channelEvent.getFuture().setSuccess();
                        return;
                    }
                }
            }
        }
        super.handleDownstream(channelHandlerContext, channelEvent);
    }

    public TrafficCounter getTrafficCounter() {
        return this.trafficCounter;
    }

    @Override // org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        TrafficCounter trafficCounter = this.trafficCounter;
        if (trafficCounter != null) {
            trafficCounter.stop();
        }
        this.release.set(true);
        if (this.timeout != null) {
            this.timeout.cancel();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrafficShaping with Write Limit: ");
        sb.append(this.writeLimit);
        sb.append(" Read Limit: ");
        sb.append(this.readLimit);
        sb.append(" and Counter: ");
        TrafficCounter trafficCounter = this.trafficCounter;
        sb.append(trafficCounter != null ? trafficCounter.toString() : "none");
        return sb.toString();
    }
}
