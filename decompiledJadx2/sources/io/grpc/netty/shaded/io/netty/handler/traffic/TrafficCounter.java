package io.grpc.netty.shaded.io.netty.handler.traffic;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class TrafficCounter {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) TrafficCounter.class);
    final AtomicLong checkInterval;
    private final AtomicLong cumulativeReadBytes;
    private final AtomicLong cumulativeWrittenBytes;
    private final AtomicLong currentReadBytes;
    private final AtomicLong currentWrittenBytes;
    final ScheduledExecutorService executor;
    private long lastCumulativeTime;
    private volatile long lastReadBytes;
    private long lastReadThroughput;
    private volatile long lastReadingTime;
    final AtomicLong lastTime;
    private long lastWriteThroughput;
    private volatile long lastWritingTime;
    private volatile long lastWrittenBytes;
    Runnable monitor;
    volatile boolean monitorActive;
    final String name;
    private long readingTime;
    private long realWriteThroughput;
    private final AtomicLong realWrittenBytes;
    volatile ScheduledFuture<?> scheduledFuture;
    final AbstractTrafficShapingHandler trafficShapingHandler;
    private long writingTime;

    public static long milliSecondFromNano() {
        return System.nanoTime() / 1000000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class TrafficMonitoringTask implements Runnable {
        private TrafficMonitoringTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TrafficCounter.this.monitorActive) {
                TrafficCounter.this.resetAccounting(TrafficCounter.milliSecondFromNano());
                if (TrafficCounter.this.trafficShapingHandler != null) {
                    TrafficCounter.this.trafficShapingHandler.doAccounting(TrafficCounter.this);
                }
            }
        }
    }

    public synchronized void start() {
        if (this.monitorActive) {
            return;
        }
        this.lastTime.set(milliSecondFromNano());
        long j = this.checkInterval.get();
        if (j > 0 && this.executor != null) {
            this.monitorActive = true;
            this.monitor = new TrafficMonitoringTask();
            this.scheduledFuture = this.executor.scheduleAtFixedRate(this.monitor, 0L, j, TimeUnit.MILLISECONDS);
        }
    }

    public synchronized void stop() {
        if (this.monitorActive) {
            this.monitorActive = false;
            resetAccounting(milliSecondFromNano());
            if (this.trafficShapingHandler != null) {
                this.trafficShapingHandler.doAccounting(this);
            }
            if (this.scheduledFuture != null) {
                this.scheduledFuture.cancel(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void resetAccounting(long j) {
        long andSet = j - this.lastTime.getAndSet(j);
        if (andSet == 0) {
            return;
        }
        if (logger.isDebugEnabled() && andSet > (checkInterval() << 1)) {
            logger.debug("Acct schedule not ok: " + andSet + " > 2*" + checkInterval() + " from " + this.name);
        }
        this.lastReadBytes = this.currentReadBytes.getAndSet(0L);
        this.lastWrittenBytes = this.currentWrittenBytes.getAndSet(0L);
        this.lastReadThroughput = (this.lastReadBytes * 1000) / andSet;
        this.lastWriteThroughput = (this.lastWrittenBytes * 1000) / andSet;
        this.realWriteThroughput = (this.realWrittenBytes.getAndSet(0L) * 1000) / andSet;
        this.lastWritingTime = Math.max(this.lastWritingTime, this.writingTime);
        this.lastReadingTime = Math.max(this.lastReadingTime, this.readingTime);
    }

    public TrafficCounter(ScheduledExecutorService scheduledExecutorService, String str, long j) {
        this.currentWrittenBytes = new AtomicLong();
        this.currentReadBytes = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.lastTime = new AtomicLong();
        this.realWrittenBytes = new AtomicLong();
        this.checkInterval = new AtomicLong(1000L);
        this.name = (String) ObjectUtil.checkNotNull(str, "name");
        this.trafficShapingHandler = null;
        this.executor = scheduledExecutorService;
        init(j);
    }

    public TrafficCounter(AbstractTrafficShapingHandler abstractTrafficShapingHandler, ScheduledExecutorService scheduledExecutorService, String str, long j) {
        this.currentWrittenBytes = new AtomicLong();
        this.currentReadBytes = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.lastTime = new AtomicLong();
        this.realWrittenBytes = new AtomicLong();
        this.checkInterval = new AtomicLong(1000L);
        if (abstractTrafficShapingHandler == null) {
            throw new IllegalArgumentException("trafficShapingHandler");
        }
        this.name = (String) ObjectUtil.checkNotNull(str, "name");
        this.trafficShapingHandler = abstractTrafficShapingHandler;
        this.executor = scheduledExecutorService;
        init(j);
    }

    private void init(long j) {
        this.lastCumulativeTime = System.currentTimeMillis();
        this.writingTime = milliSecondFromNano();
        long j2 = this.writingTime;
        this.readingTime = j2;
        this.lastWritingTime = j2;
        this.lastReadingTime = j2;
        configure(j);
    }

    public void configure(long j) {
        long j2 = (j / 10) * 10;
        if (this.checkInterval.getAndSet(j2) != j2) {
            if (j2 <= 0) {
                stop();
                this.lastTime.set(milliSecondFromNano());
            } else {
                stop();
                start();
            }
        }
    }

    void bytesRecvFlowControl(long j) {
        this.currentReadBytes.addAndGet(j);
        this.cumulativeReadBytes.addAndGet(j);
    }

    void bytesWriteFlowControl(long j) {
        this.currentWrittenBytes.addAndGet(j);
        this.cumulativeWrittenBytes.addAndGet(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bytesRealWriteFlowControl(long j) {
        this.realWrittenBytes.addAndGet(j);
    }

    public long checkInterval() {
        return this.checkInterval.get();
    }

    public long lastReadThroughput() {
        return this.lastReadThroughput;
    }

    public long lastWriteThroughput() {
        return this.lastWriteThroughput;
    }

    public long lastReadBytes() {
        return this.lastReadBytes;
    }

    public long lastWrittenBytes() {
        return this.lastWrittenBytes;
    }

    public long currentReadBytes() {
        return this.currentReadBytes.get();
    }

    public long currentWrittenBytes() {
        return this.currentWrittenBytes.get();
    }

    public long lastTime() {
        return this.lastTime.get();
    }

    public long cumulativeWrittenBytes() {
        return this.cumulativeWrittenBytes.get();
    }

    public long cumulativeReadBytes() {
        return this.cumulativeReadBytes.get();
    }

    public long lastCumulativeTime() {
        return this.lastCumulativeTime;
    }

    public AtomicLong getRealWrittenBytes() {
        return this.realWrittenBytes;
    }

    public long getRealWriteThroughput() {
        return this.realWriteThroughput;
    }

    public void resetCumulativeTime() {
        this.lastCumulativeTime = System.currentTimeMillis();
        this.cumulativeReadBytes.set(0L);
        this.cumulativeWrittenBytes.set(0L);
    }

    public String name() {
        return this.name;
    }

    @Deprecated
    public long readTimeToWait(long j, long j2, long j3) {
        return readTimeToWait(j, j2, j3, milliSecondFromNano());
    }

    public long readTimeToWait(long j, long j2, long j3, long j4) {
        bytesRecvFlowControl(j);
        if (j == 0 || j2 == 0) {
            return 0L;
        }
        long j5 = this.lastTime.get();
        long j6 = this.currentReadBytes.get();
        long j7 = this.readingTime;
        long j8 = this.lastReadBytes;
        long j9 = j4 - j5;
        long max = Math.max(this.lastReadingTime - j5, 0L);
        if (j9 > 10) {
            long j10 = (((1000 * j6) / j2) - j9) + max;
            if (j10 > 10) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Time: " + j10 + ':' + j6 + ':' + j9 + ':' + max);
                }
                if (j10 > j3 && (j4 + j10) - j7 > j3) {
                    j10 = j3;
                }
                this.readingTime = Math.max(j7, j4 + j10);
                return j10;
            }
            this.readingTime = Math.max(j7, j4);
            return 0L;
        }
        long j11 = j6 + j8;
        long j12 = j9 + this.checkInterval.get();
        long j13 = (((1000 * j11) / j2) - j12) + max;
        if (j13 > 10) {
            if (logger.isDebugEnabled()) {
                logger.debug("Time: " + j13 + ':' + j11 + ':' + j12 + ':' + max);
            }
            if (j13 > j3 && (j4 + j13) - j7 > j3) {
                j13 = j3;
            }
            this.readingTime = Math.max(j7, j4 + j13);
            return j13;
        }
        this.readingTime = Math.max(j7, j4);
        return 0L;
    }

    @Deprecated
    public long writeTimeToWait(long j, long j2, long j3) {
        return writeTimeToWait(j, j2, j3, milliSecondFromNano());
    }

    public long writeTimeToWait(long j, long j2, long j3, long j4) {
        bytesWriteFlowControl(j);
        if (j == 0 || j2 == 0) {
            return 0L;
        }
        long j5 = this.lastTime.get();
        long j6 = this.currentWrittenBytes.get();
        long j7 = this.lastWrittenBytes;
        long j8 = this.writingTime;
        long max = Math.max(this.lastWritingTime - j5, 0L);
        long j9 = j4 - j5;
        if (j9 > 10) {
            long j10 = (((1000 * j6) / j2) - j9) + max;
            if (j10 > 10) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Time: " + j10 + ':' + j6 + ':' + j9 + ':' + max);
                }
                if (j10 > j3 && (j4 + j10) - j8 > j3) {
                    j10 = j3;
                }
                this.writingTime = Math.max(j8, j4 + j10);
                return j10;
            }
            this.writingTime = Math.max(j8, j4);
            return 0L;
        }
        long j11 = j6 + j7;
        long j12 = j9 + this.checkInterval.get();
        long j13 = (((1000 * j11) / j2) - j12) + max;
        if (j13 > 10) {
            if (logger.isDebugEnabled()) {
                logger.debug("Time: " + j13 + ':' + j11 + ':' + j12 + ':' + max);
            }
            if (j13 > j3 && (j4 + j13) - j8 > j3) {
                j13 = j3;
            }
            this.writingTime = Math.max(j8, j4 + j13);
            return j13;
        }
        this.writingTime = Math.max(j8, j4);
        return 0L;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(165);
        sb.append("Monitor ");
        sb.append(this.name);
        sb.append(" Current Speed Read: ");
        sb.append(this.lastReadThroughput >> 10);
        sb.append(" KB/s, ");
        sb.append("Asked Write: ");
        sb.append(this.lastWriteThroughput >> 10);
        sb.append(" KB/s, ");
        sb.append("Real Write: ");
        sb.append(this.realWriteThroughput >> 10);
        sb.append(" KB/s, ");
        sb.append("Current Read: ");
        sb.append(this.currentReadBytes.get() >> 10);
        sb.append(" KB, ");
        sb.append("Current asked Write: ");
        sb.append(this.currentWrittenBytes.get() >> 10);
        sb.append(" KB, ");
        sb.append("Current real Write: ");
        sb.append(this.realWrittenBytes.get() >> 10);
        sb.append(" KB");
        return sb.toString();
    }
}
