package io.netty.handler.traffic;

import io.netty.handler.traffic.GlobalChannelTrafficShapingHandler;
import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class GlobalChannelTrafficCounter extends TrafficCounter {
    public GlobalChannelTrafficCounter(GlobalChannelTrafficShapingHandler globalChannelTrafficShapingHandler, ScheduledExecutorService scheduledExecutorService, String str, long j) {
        super(globalChannelTrafficShapingHandler, scheduledExecutorService, str, j);
        if (scheduledExecutorService == null) {
            throw new IllegalArgumentException("Executor must not be null");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static class MixedTrafficMonitoringTask implements Runnable {
        private final TrafficCounter counter;
        private final GlobalChannelTrafficShapingHandler trafficShapingHandler1;

        MixedTrafficMonitoringTask(GlobalChannelTrafficShapingHandler globalChannelTrafficShapingHandler, TrafficCounter trafficCounter) {
            this.trafficShapingHandler1 = globalChannelTrafficShapingHandler;
            this.counter = trafficCounter;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.counter.monitorActive) {
                long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
                this.counter.resetAccounting(milliSecondFromNano);
                Iterator<GlobalChannelTrafficShapingHandler.PerChannel> it = this.trafficShapingHandler1.channelQueues.values().iterator();
                while (it.hasNext()) {
                    it.next().channelTrafficCounter.resetAccounting(milliSecondFromNano);
                }
                this.trafficShapingHandler1.doAccounting(this.counter);
                TrafficCounter trafficCounter = this.counter;
                trafficCounter.scheduledFuture = trafficCounter.executor.schedule(this, this.counter.checkInterval.get(), TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // io.netty.handler.traffic.TrafficCounter
    public synchronized void start() {
        if (this.monitorActive) {
            return;
        }
        this.lastTime.set(milliSecondFromNano());
        long j = this.checkInterval.get();
        if (j > 0) {
            this.monitorActive = true;
            this.monitor = new MixedTrafficMonitoringTask((GlobalChannelTrafficShapingHandler) this.trafficShapingHandler, this);
            this.scheduledFuture = this.executor.schedule(this.monitor, j, TimeUnit.MILLISECONDS);
        }
    }

    @Override // io.netty.handler.traffic.TrafficCounter
    public synchronized void stop() {
        if (this.monitorActive) {
            this.monitorActive = false;
            resetAccounting(milliSecondFromNano());
            this.trafficShapingHandler.doAccounting(this);
            if (this.scheduledFuture != null) {
                this.scheduledFuture.cancel(true);
            }
        }
    }

    @Override // io.netty.handler.traffic.TrafficCounter
    public void resetCumulativeTime() {
        Iterator<GlobalChannelTrafficShapingHandler.PerChannel> it = ((GlobalChannelTrafficShapingHandler) this.trafficShapingHandler).channelQueues.values().iterator();
        while (it.hasNext()) {
            it.next().channelTrafficCounter.resetCumulativeTime();
        }
        super.resetCumulativeTime();
    }
}
