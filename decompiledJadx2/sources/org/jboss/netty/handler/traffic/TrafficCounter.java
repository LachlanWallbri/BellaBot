package org.jboss.netty.handler.traffic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

/* loaded from: classes7.dex */
public class TrafficCounter {
    private long lastReadBytes;
    private long lastReadThroughput;
    private long lastWriteThroughput;
    private long lastWrittenBytes;
    final String name;
    private volatile Timeout timeout;
    private final Timer timer;
    private TimerTask timerTask;
    private final AbstractTrafficShapingHandler trafficShapingHandler;
    private final AtomicLong currentWrittenBytes = new AtomicLong();
    private final AtomicLong currentReadBytes = new AtomicLong();
    private final AtomicLong cumulativeWrittenBytes = new AtomicLong();
    private final AtomicLong cumulativeReadBytes = new AtomicLong();
    private final AtomicLong lastTime = new AtomicLong();
    AtomicLong checkInterval = new AtomicLong(1000);
    AtomicBoolean monitorActive = new AtomicBoolean();
    private long lastCumulativeTime = System.currentTimeMillis();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class TrafficMonitoringTask implements TimerTask {
        private final TrafficCounter counter;
        private final AbstractTrafficShapingHandler trafficShapingHandler1;

        protected TrafficMonitoringTask(AbstractTrafficShapingHandler abstractTrafficShapingHandler, TrafficCounter trafficCounter) {
            this.trafficShapingHandler1 = abstractTrafficShapingHandler;
            this.counter = trafficCounter;
        }

        @Override // org.jboss.netty.util.TimerTask
        public void run(Timeout timeout) throws Exception {
            if (this.counter.monitorActive.get()) {
                this.counter.resetAccounting(System.currentTimeMillis());
                AbstractTrafficShapingHandler abstractTrafficShapingHandler = this.trafficShapingHandler1;
                if (abstractTrafficShapingHandler != null) {
                    abstractTrafficShapingHandler.doAccounting(this.counter);
                }
                this.counter.timer.newTimeout(this, this.counter.checkInterval.get(), TimeUnit.MILLISECONDS);
            }
        }
    }

    public void start() {
        synchronized (this.lastTime) {
            if (this.monitorActive.get()) {
                return;
            }
            this.lastTime.set(System.currentTimeMillis());
            if (this.checkInterval.get() > 0) {
                this.monitorActive.set(true);
                this.timerTask = new TrafficMonitoringTask(this.trafficShapingHandler, this);
                this.timeout = this.timer.newTimeout(this.timerTask, this.checkInterval.get(), TimeUnit.MILLISECONDS);
            }
        }
    }

    public void stop() {
        synchronized (this.lastTime) {
            if (this.monitorActive.get()) {
                this.monitorActive.set(false);
                resetAccounting(System.currentTimeMillis());
                if (this.trafficShapingHandler != null) {
                    this.trafficShapingHandler.doAccounting(this);
                }
                if (this.timeout != null) {
                    this.timeout.cancel();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetAccounting(long j) {
        synchronized (this.lastTime) {
            long andSet = j - this.lastTime.getAndSet(j);
            if (andSet == 0) {
                return;
            }
            this.lastReadBytes = this.currentReadBytes.getAndSet(0L);
            this.lastWrittenBytes = this.currentWrittenBytes.getAndSet(0L);
            this.lastReadThroughput = (this.lastReadBytes / andSet) * 1000;
            this.lastWriteThroughput = (this.lastWrittenBytes / andSet) * 1000;
        }
    }

    public TrafficCounter(AbstractTrafficShapingHandler abstractTrafficShapingHandler, Timer timer, String str, long j) {
        this.trafficShapingHandler = abstractTrafficShapingHandler;
        this.timer = timer;
        this.name = str;
        configure(j);
    }

    public void configure(long j) {
        long j2 = (j / 10) * 10;
        if (this.checkInterval.get() != j2) {
            this.checkInterval.set(j2);
            if (j2 <= 0) {
                stop();
                this.lastTime.set(System.currentTimeMillis());
            } else {
                start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bytesRecvFlowControl(ChannelHandlerContext channelHandlerContext, long j) {
        this.currentReadBytes.addAndGet(j);
        this.cumulativeReadBytes.addAndGet(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bytesWriteFlowControl(long j) {
        this.currentWrittenBytes.addAndGet(j);
        this.cumulativeWrittenBytes.addAndGet(j);
    }

    public long getCheckInterval() {
        return this.checkInterval.get();
    }

    public long getLastReadThroughput() {
        return this.lastReadThroughput;
    }

    public long getLastWriteThroughput() {
        return this.lastWriteThroughput;
    }

    public long getLastReadBytes() {
        return this.lastReadBytes;
    }

    public long getLastWrittenBytes() {
        return this.lastWrittenBytes;
    }

    public long getCurrentReadBytes() {
        return this.currentReadBytes.get();
    }

    public long getCurrentWrittenBytes() {
        return this.currentWrittenBytes.get();
    }

    public long getLastTime() {
        return this.lastTime.get();
    }

    public long getCumulativeWrittenBytes() {
        return this.cumulativeWrittenBytes.get();
    }

    public long getCumulativeReadBytes() {
        return this.cumulativeReadBytes.get();
    }

    public long getLastCumulativeTime() {
        return this.lastCumulativeTime;
    }

    public void resetCumulativeTime() {
        this.lastCumulativeTime = System.currentTimeMillis();
        this.cumulativeReadBytes.set(0L);
        this.cumulativeWrittenBytes.set(0L);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Monitor " + this.name + " Current Speed Read: " + (this.lastReadThroughput >> 10) + " KB/s, Write: " + (this.lastWriteThroughput >> 10) + " KB/s Current Read: " + (this.currentReadBytes.get() >> 10) + " KB Current Write: " + (this.currentWrittenBytes.get() >> 10) + " KB";
    }
}
