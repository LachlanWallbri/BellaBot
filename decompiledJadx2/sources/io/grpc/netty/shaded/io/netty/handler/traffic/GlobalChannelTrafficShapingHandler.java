package io.grpc.netty.shaded.io.netty.handler.traffic;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.grpc.netty.shaded.io.netty.util.Attribute;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class GlobalChannelTrafficShapingHandler extends AbstractTrafficShapingHandler {
    private static final float DEFAULT_ACCELERATION = -0.1f;
    private static final float DEFAULT_DEVIATION = 0.1f;
    private static final float DEFAULT_SLOWDOWN = 0.4f;
    private static final float MAX_DEVIATION = 0.4f;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) GlobalChannelTrafficShapingHandler.class);
    private volatile float accelerationFactor;
    final ConcurrentMap<Integer, PerChannel> channelQueues;
    private final AtomicLong cumulativeReadBytes;
    private final AtomicLong cumulativeWrittenBytes;
    private volatile float maxDeviation;
    volatile long maxGlobalWriteSize;
    private final AtomicLong queuesSize;
    private volatile long readChannelLimit;
    private volatile boolean readDeviationActive;
    private volatile float slowDownFactor;
    private volatile long writeChannelLimit;
    private volatile boolean writeDeviationActive;

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected int userDefinedWritabilityIndex() {
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PerChannel {
        TrafficCounter channelTrafficCounter;
        long lastReadTimestamp;
        long lastWriteTimestamp;
        ArrayDeque<ToSend> messagesQueue;
        long queueSize;

        PerChannel() {
        }
    }

    void createGlobalTrafficCounter(ScheduledExecutorService scheduledExecutorService) {
        setMaxDeviation(0.1f, 0.4f, DEFAULT_ACCELERATION);
        if (scheduledExecutorService == null) {
            throw new IllegalArgumentException("Executor must not be null");
        }
        GlobalChannelTrafficCounter globalChannelTrafficCounter = new GlobalChannelTrafficCounter(this, scheduledExecutorService, "GlobalChannelTC", this.checkInterval);
        setTrafficCounter(globalChannelTrafficCounter);
        globalChannelTrafficCounter.start();
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4, long j5, long j6) {
        super(j, j2, j5, j6);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        createGlobalTrafficCounter(scheduledExecutorService);
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4, long j5) {
        super(j, j2, j5);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4) {
        super(j, j2);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j) {
        super(j);
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService) {
        this.channelQueues = PlatformDependent.newConcurrentHashMap();
        this.queuesSize = new AtomicLong();
        this.cumulativeWrittenBytes = new AtomicLong();
        this.cumulativeReadBytes = new AtomicLong();
        this.maxGlobalWriteSize = 419430400L;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public float maxDeviation() {
        return this.maxDeviation;
    }

    public float accelerationFactor() {
        return this.accelerationFactor;
    }

    public float slowDownFactor() {
        return this.slowDownFactor;
    }

    public void setMaxDeviation(float f, float f2, float f3) {
        if (f > 0.4f) {
            throw new IllegalArgumentException("maxDeviation must be <= 0.4");
        }
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("slowDownFactor must be >= 0");
        }
        if (f3 > 0.0f) {
            throw new IllegalArgumentException("accelerationFactor must be <= 0");
        }
        this.maxDeviation = f;
        this.accelerationFactor = f3 + 1.0f;
        this.slowDownFactor = f2 + 1.0f;
    }

    private void computeDeviationCumulativeBytes() {
        long j = Long.MAX_VALUE;
        long j2 = 0;
        long j3 = Long.MAX_VALUE;
        long j4 = 0;
        for (PerChannel perChannel : this.channelQueues.values()) {
            long cumulativeWrittenBytes = perChannel.channelTrafficCounter.cumulativeWrittenBytes();
            if (j2 < cumulativeWrittenBytes) {
                j2 = cumulativeWrittenBytes;
            }
            if (j > cumulativeWrittenBytes) {
                j = cumulativeWrittenBytes;
            }
            long cumulativeReadBytes = perChannel.channelTrafficCounter.cumulativeReadBytes();
            if (j4 < cumulativeReadBytes) {
                j4 = cumulativeReadBytes;
            }
            if (j3 > cumulativeReadBytes) {
                j3 = cumulativeReadBytes;
            }
        }
        boolean z = false;
        boolean z2 = this.channelQueues.size() > 1;
        this.readDeviationActive = z2 && j3 < j4 / 2;
        if (z2 && j < j2 / 2) {
            z = true;
        }
        this.writeDeviationActive = z;
        this.cumulativeWrittenBytes.set(j2);
        this.cumulativeReadBytes.set(j4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    public void doAccounting(TrafficCounter trafficCounter) {
        computeDeviationCumulativeBytes();
        super.doAccounting(trafficCounter);
    }

    private long computeBalancedWait(float f, float f2, long j) {
        float f3;
        if (f2 == 0.0f) {
            return j;
        }
        float f4 = f / f2;
        if (f4 <= this.maxDeviation) {
            f3 = this.accelerationFactor;
        } else {
            if (f4 < 1.0f - this.maxDeviation) {
                return j;
            }
            f3 = this.slowDownFactor;
            if (j < 10) {
                j = 10;
            }
        }
        return ((float) j) * f3;
    }

    public long getMaxGlobalWriteSize() {
        return this.maxGlobalWriteSize;
    }

    public void setMaxGlobalWriteSize(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxGlobalWriteSize must be positive");
        }
        this.maxGlobalWriteSize = j;
    }

    public long queuesSize() {
        return this.queuesSize.get();
    }

    public void configureChannel(long j, long j2) {
        this.writeChannelLimit = j;
        this.readChannelLimit = j2;
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        Iterator<PerChannel> it = this.channelQueues.values().iterator();
        while (it.hasNext()) {
            it.next().channelTrafficCounter.resetAccounting(milliSecondFromNano);
        }
    }

    public long getWriteChannelLimit() {
        return this.writeChannelLimit;
    }

    public void setWriteChannelLimit(long j) {
        this.writeChannelLimit = j;
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        Iterator<PerChannel> it = this.channelQueues.values().iterator();
        while (it.hasNext()) {
            it.next().channelTrafficCounter.resetAccounting(milliSecondFromNano);
        }
    }

    public long getReadChannelLimit() {
        return this.readChannelLimit;
    }

    public void setReadChannelLimit(long j) {
        this.readChannelLimit = j;
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        Iterator<PerChannel> it = this.channelQueues.values().iterator();
        while (it.hasNext()) {
            it.next().channelTrafficCounter.resetAccounting(milliSecondFromNano);
        }
    }

    public final void release() {
        this.trafficCounter.stop();
    }

    private PerChannel getOrSetPerChannel(ChannelHandlerContext channelHandlerContext) {
        Integer valueOf = Integer.valueOf(channelHandlerContext.channel().hashCode());
        PerChannel perChannel = this.channelQueues.get(valueOf);
        if (perChannel != null) {
            return perChannel;
        }
        PerChannel perChannel2 = new PerChannel();
        perChannel2.messagesQueue = new ArrayDeque<>();
        perChannel2.channelTrafficCounter = new TrafficCounter(this, null, "ChannelTC" + channelHandlerContext.channel().hashCode(), this.checkInterval);
        perChannel2.queueSize = 0L;
        perChannel2.lastReadTimestamp = TrafficCounter.milliSecondFromNano();
        perChannel2.lastWriteTimestamp = perChannel2.lastReadTimestamp;
        this.channelQueues.put(valueOf, perChannel2);
        return perChannel2;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        getOrSetPerChannel(channelHandlerContext);
        this.trafficCounter.resetCumulativeTime();
        super.handlerAdded(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.trafficCounter.resetCumulativeTime();
        Channel channel = channelHandlerContext.channel();
        PerChannel remove = this.channelQueues.remove(Integer.valueOf(channel.hashCode()));
        if (remove != null) {
            synchronized (remove) {
                if (channel.isActive()) {
                    Iterator<ToSend> it = remove.messagesQueue.iterator();
                    while (it.hasNext()) {
                        ToSend next = it.next();
                        long calculateSize = calculateSize(next.toSend);
                        this.trafficCounter.bytesRealWriteFlowControl(calculateSize);
                        remove.channelTrafficCounter.bytesRealWriteFlowControl(calculateSize);
                        remove.queueSize -= calculateSize;
                        this.queuesSize.addAndGet(-calculateSize);
                        channelHandlerContext.write(next.toSend, next.promise);
                    }
                } else {
                    this.queuesSize.addAndGet(-remove.queueSize);
                    Iterator<ToSend> it2 = remove.messagesQueue.iterator();
                    while (it2.hasNext()) {
                        ToSend next2 = it2.next();
                        if (next2.toSend instanceof ByteBuf) {
                            ((ByteBuf) next2.toSend).release();
                        }
                    }
                }
                remove.messagesQueue.clear();
            }
        }
        releaseWriteSuspended(channelHandlerContext);
        releaseReadSuspended(channelHandlerContext);
        super.handlerRemoved(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        long j;
        long calculateSize = calculateSize(obj);
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        long j2 = 0;
        if (calculateSize > 0) {
            long readTimeToWait = this.trafficCounter.readTimeToWait(calculateSize, getReadLimit(), this.maxTime, milliSecondFromNano);
            PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
            if (perChannel != null) {
                long readTimeToWait2 = perChannel.channelTrafficCounter.readTimeToWait(calculateSize, this.readChannelLimit, this.maxTime, milliSecondFromNano);
                if (this.readDeviationActive) {
                    long cumulativeReadBytes = perChannel.channelTrafficCounter.cumulativeReadBytes();
                    long j3 = this.cumulativeReadBytes.get();
                    if (cumulativeReadBytes <= 0) {
                        cumulativeReadBytes = 0;
                    }
                    if (j3 < cumulativeReadBytes) {
                        j3 = cumulativeReadBytes;
                    }
                    j2 = computeBalancedWait((float) cumulativeReadBytes, (float) j3, readTimeToWait2);
                } else {
                    j2 = readTimeToWait2;
                }
            }
            if (j2 < readTimeToWait) {
                j2 = readTimeToWait;
            }
            j = milliSecondFromNano;
            long checkWaitReadTime = checkWaitReadTime(channelHandlerContext, j2, milliSecondFromNano);
            if (checkWaitReadTime >= 10) {
                Channel channel = channelHandlerContext.channel();
                ChannelConfig config = channel.config();
                if (logger.isDebugEnabled()) {
                    logger.debug("Read Suspend: " + checkWaitReadTime + ':' + config.isAutoRead() + ':' + isHandlerActive(channelHandlerContext));
                }
                if (config.isAutoRead() && isHandlerActive(channelHandlerContext)) {
                    config.setAutoRead(false);
                    channel.attr(READ_SUSPENDED).set(true);
                    Attribute attr = channel.attr(REOPEN_TASK);
                    Runnable runnable = (Runnable) attr.get();
                    if (runnable == null) {
                        runnable = new AbstractTrafficShapingHandler.ReopenReadTimerTask(channelHandlerContext);
                        attr.set(runnable);
                    }
                    channelHandlerContext.executor().schedule(runnable, checkWaitReadTime, TimeUnit.MILLISECONDS);
                    if (logger.isDebugEnabled()) {
                        logger.debug("Suspend final status => " + config.isAutoRead() + ':' + isHandlerActive(channelHandlerContext) + " will reopened at: " + checkWaitReadTime);
                    }
                }
            }
        } else {
            j = milliSecondFromNano;
        }
        informReadOperation(channelHandlerContext, j);
        channelHandlerContext.fireChannelRead(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected long checkWaitReadTime(ChannelHandlerContext channelHandlerContext, long j, long j2) {
        PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        return (perChannel == null || j <= this.maxTime || (j2 + j) - perChannel.lastReadTimestamp <= this.maxTime) ? j : this.maxTime;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected void informReadOperation(ChannelHandlerContext channelHandlerContext, long j) {
        PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        if (perChannel != null) {
            perChannel.lastReadTimestamp = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ToSend {
        final ChannelPromise promise;
        final long relativeTimeAction;
        final long size;
        final Object toSend;

        private ToSend(long j, Object obj, long j2, ChannelPromise channelPromise) {
            this.relativeTimeAction = j;
            this.toSend = obj;
            this.size = j2;
            this.promise = channelPromise;
        }
    }

    protected long maximumCumulativeWrittenBytes() {
        return this.cumulativeWrittenBytes.get();
    }

    protected long maximumCumulativeReadBytes() {
        return this.cumulativeReadBytes.get();
    }

    public Collection<TrafficCounter> channelTrafficCounters() {
        return new AbstractCollection<TrafficCounter>() { // from class: io.grpc.netty.shaded.io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public Iterator<TrafficCounter> iterator() {
                return new Iterator<TrafficCounter>() { // from class: io.grpc.netty.shaded.io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.1.1
                    final Iterator<PerChannel> iter;

                    {
                        this.iter = GlobalChannelTrafficShapingHandler.this.channelQueues.values().iterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.iter.hasNext();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public TrafficCounter next() {
                        return this.iter.next().channelTrafficCounter;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public int size() {
                return GlobalChannelTrafficShapingHandler.this.channelQueues.size();
            }
        };
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler, io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        long calculateSize = calculateSize(obj);
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        long j = 0;
        if (calculateSize > 0) {
            long writeTimeToWait = this.trafficCounter.writeTimeToWait(calculateSize, getWriteLimit(), this.maxTime, milliSecondFromNano);
            PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
            if (perChannel != null) {
                long writeTimeToWait2 = perChannel.channelTrafficCounter.writeTimeToWait(calculateSize, this.writeChannelLimit, this.maxTime, milliSecondFromNano);
                if (this.writeDeviationActive) {
                    long cumulativeWrittenBytes = perChannel.channelTrafficCounter.cumulativeWrittenBytes();
                    long j2 = this.cumulativeWrittenBytes.get();
                    if (cumulativeWrittenBytes <= 0) {
                        cumulativeWrittenBytes = 0;
                    }
                    j = computeBalancedWait((float) cumulativeWrittenBytes, (float) (j2 < cumulativeWrittenBytes ? cumulativeWrittenBytes : j2), writeTimeToWait2);
                } else {
                    j = writeTimeToWait2;
                }
            }
            if (j >= writeTimeToWait) {
                writeTimeToWait = j;
            }
            if (writeTimeToWait >= 10) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Write suspend: " + writeTimeToWait + ':' + channelHandlerContext.channel().config().isAutoRead() + ':' + isHandlerActive(channelHandlerContext));
                }
                submitWrite(channelHandlerContext, obj, calculateSize, writeTimeToWait, milliSecondFromNano, channelPromise);
                return;
            }
        }
        submitWrite(channelHandlerContext, obj, calculateSize, 0L, milliSecondFromNano, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    protected void submitWrite(final ChannelHandlerContext channelHandlerContext, Object obj, long j, long j2, long j3, ChannelPromise channelPromise) {
        PerChannel perChannel = this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        if (perChannel == null) {
            perChannel = getOrSetPerChannel(channelHandlerContext);
        }
        final PerChannel perChannel2 = perChannel;
        synchronized (perChannel2) {
            if (j2 == 0) {
                if (perChannel2.messagesQueue.isEmpty()) {
                    this.trafficCounter.bytesRealWriteFlowControl(j);
                    perChannel2.channelTrafficCounter.bytesRealWriteFlowControl(j);
                    channelHandlerContext.write(obj, channelPromise);
                    perChannel2.lastWriteTimestamp = j3;
                    return;
                }
            }
            long j4 = (j2 <= this.maxTime || (j3 + j2) - perChannel2.lastWriteTimestamp <= this.maxTime) ? j2 : this.maxTime;
            ToSend toSend = new ToSend(j4 + j3, obj, j, channelPromise);
            perChannel2.messagesQueue.addLast(toSend);
            perChannel2.queueSize += j;
            this.queuesSize.addAndGet(j);
            checkWriteSuspend(channelHandlerContext, j4, perChannel2.queueSize);
            boolean z = this.queuesSize.get() > this.maxGlobalWriteSize;
            if (z) {
                setUserDefinedWritability(channelHandlerContext, false);
            }
            final long j5 = toSend.relativeTimeAction;
            channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    GlobalChannelTrafficShapingHandler.this.sendAllValid(channelHandlerContext, perChannel2, j5);
                }
            }, j4, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAllValid(ChannelHandlerContext channelHandlerContext, PerChannel perChannel, long j) {
        synchronized (perChannel) {
            ToSend pollFirst = perChannel.messagesQueue.pollFirst();
            while (true) {
                if (pollFirst != null) {
                    if (pollFirst.relativeTimeAction <= j) {
                        long j2 = pollFirst.size;
                        this.trafficCounter.bytesRealWriteFlowControl(j2);
                        perChannel.channelTrafficCounter.bytesRealWriteFlowControl(j2);
                        perChannel.queueSize -= j2;
                        this.queuesSize.addAndGet(-j2);
                        channelHandlerContext.write(pollFirst.toSend, pollFirst.promise);
                        perChannel.lastWriteTimestamp = j;
                        pollFirst = perChannel.messagesQueue.pollFirst();
                    } else {
                        perChannel.messagesQueue.addFirst(pollFirst);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (perChannel.messagesQueue.isEmpty()) {
                releaseWriteSuspended(channelHandlerContext);
            }
        }
        channelHandlerContext.flush();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.traffic.AbstractTrafficShapingHandler
    public String toString() {
        StringBuilder sb = new StringBuilder(340);
        sb.append(super.toString());
        sb.append(" Write Channel Limit: ");
        sb.append(this.writeChannelLimit);
        sb.append(" Read Channel Limit: ");
        sb.append(this.readChannelLimit);
        return sb.toString();
    }
}
