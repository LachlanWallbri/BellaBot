package org.jboss.netty.handler.execution;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.WriteCompletionEvent;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.DefaultObjectSizeEstimator;
import org.jboss.netty.util.ObjectSizeEstimator;
import org.jboss.netty.util.internal.ConcurrentIdentityHashMap;
import org.jboss.netty.util.internal.QueueFactory;
import org.jboss.netty.util.internal.SharedResourceMisuseDetector;

/* loaded from: classes7.dex */
public class MemoryAwareThreadPoolExecutor extends ThreadPoolExecutor {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) MemoryAwareThreadPoolExecutor.class);
    private static final SharedResourceMisuseDetector misuseDetector = new SharedResourceMisuseDetector(MemoryAwareThreadPoolExecutor.class);
    private final ConcurrentMap<Channel, AtomicLong> channelCounters;
    private volatile boolean notifyOnShutdown;
    private volatile Settings settings;
    private final Limiter totalLimiter;

    public MemoryAwareThreadPoolExecutor(int i, long j, long j2) {
        this(i, j, j2, 30L, TimeUnit.SECONDS);
    }

    public MemoryAwareThreadPoolExecutor(int i, long j, long j2, long j3, TimeUnit timeUnit) {
        this(i, j, j2, j3, timeUnit, Executors.defaultThreadFactory());
    }

    public MemoryAwareThreadPoolExecutor(int i, long j, long j2, long j3, TimeUnit timeUnit, ThreadFactory threadFactory) {
        this(i, j, j2, j3, timeUnit, new DefaultObjectSizeEstimator(), threadFactory);
    }

    public MemoryAwareThreadPoolExecutor(int i, long j, long j2, long j3, TimeUnit timeUnit, ObjectSizeEstimator objectSizeEstimator, ThreadFactory threadFactory) {
        super(i, i, j3, timeUnit, QueueFactory.createQueue(Runnable.class), threadFactory, new NewThreadRunsPolicy());
        this.channelCounters = new ConcurrentIdentityHashMap();
        if (objectSizeEstimator == null) {
            throw new NullPointerException("objectSizeEstimator");
        }
        if (j < 0) {
            throw new IllegalArgumentException("maxChannelMemorySize: " + j);
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("maxTotalMemorySize: " + j2);
        }
        try {
            getClass().getMethod("allowCoreThreadTimeOut", Boolean.TYPE).invoke(this, Boolean.TRUE);
        } catch (Throwable unused) {
            logger.debug("ThreadPoolExecutor.allowCoreThreadTimeOut() is not supported in this platform.");
        }
        this.settings = new Settings(objectSizeEstimator, j);
        if (j2 == 0) {
            this.totalLimiter = null;
        } else {
            this.totalLimiter = new Limiter(j2);
        }
        misuseDetector.increase();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void terminated() {
        super.terminated();
        misuseDetector.decrease();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return shutdownNow(this.notifyOnShutdown);
    }

    public List<Runnable> shutdownNow(boolean z) {
        if (!z) {
            return super.shutdownNow();
        }
        List<Runnable> shutdownNow = super.shutdownNow();
        HashSet hashSet = null;
        IOException iOException = null;
        for (Runnable runnable : shutdownNow) {
            if (runnable instanceof ChannelEventRunnable) {
                if (iOException == null) {
                    iOException = new IOException("Unable to process queued event");
                }
                ChannelEvent event = ((ChannelEventRunnable) runnable).getEvent();
                event.getFuture().setFailure(iOException);
                if (hashSet == null) {
                    hashSet = new HashSet();
                }
                hashSet.add(event.getChannel());
            }
        }
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                Channels.fireExceptionCaughtLater((Channel) it.next(), iOException);
            }
        }
        return shutdownNow;
    }

    public ObjectSizeEstimator getObjectSizeEstimator() {
        return this.settings.objectSizeEstimator;
    }

    public void setObjectSizeEstimator(ObjectSizeEstimator objectSizeEstimator) {
        if (objectSizeEstimator == null) {
            throw new NullPointerException("objectSizeEstimator");
        }
        this.settings = new Settings(objectSizeEstimator, this.settings.maxChannelMemorySize);
    }

    public long getMaxChannelMemorySize() {
        return this.settings.maxChannelMemorySize;
    }

    public void setMaxChannelMemorySize(long j) {
        if (j >= 0) {
            if (getTaskCount() > 0) {
                throw new IllegalStateException("can't be changed after a task is executed");
            }
            this.settings = new Settings(this.settings.objectSizeEstimator, j);
        } else {
            throw new IllegalArgumentException("maxChannelMemorySize: " + j);
        }
    }

    public long getMaxTotalMemorySize() {
        return this.totalLimiter.limit;
    }

    @Deprecated
    public void setMaxTotalMemorySize(long j) {
        if (j >= 0) {
            if (getTaskCount() > 0) {
                throw new IllegalStateException("can't be changed after a task is executed");
            }
        } else {
            throw new IllegalArgumentException("maxTotalMemorySize: " + j);
        }
    }

    public void setNotifyChannelFuturesOnShutdown(boolean z) {
        this.notifyOnShutdown = z;
    }

    public boolean getNotifyChannelFuturesOnShutdown() {
        return this.notifyOnShutdown;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable instanceof ChannelDownstreamEventRunnable) {
            throw new RejectedExecutionException("command must be enclosed with an upstream event.");
        }
        if (!(runnable instanceof ChannelEventRunnable)) {
            runnable = new MemoryAwareRunnable(runnable);
        }
        increaseCounter(runnable);
        doExecute(runnable);
    }

    protected void doExecute(Runnable runnable) {
        doUnorderedExecute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void doUnorderedExecute(Runnable runnable) {
        super.execute(runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public boolean remove(Runnable runnable) {
        boolean remove = super.remove(runnable);
        if (remove) {
            decreaseCounter(runnable);
        }
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.util.concurrent.ThreadPoolExecutor
    public void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        decreaseCounter(runnable);
    }

    protected void increaseCounter(Runnable runnable) {
        if (shouldCount(runnable)) {
            Settings settings = this.settings;
            long j = settings.maxChannelMemorySize;
            int estimateSize = settings.objectSizeEstimator.estimateSize(runnable);
            if (runnable instanceof ChannelEventRunnable) {
                ChannelEventRunnable channelEventRunnable = (ChannelEventRunnable) runnable;
                channelEventRunnable.estimatedSize = estimateSize;
                Channel channel = channelEventRunnable.getEvent().getChannel();
                long addAndGet = getChannelCounter(channel).addAndGet(estimateSize);
                if (j != 0 && addAndGet >= j && channel.isOpen() && channel.isReadable()) {
                    ChannelHandlerContext context = channelEventRunnable.getContext();
                    if (context.getHandler() instanceof ExecutionHandler) {
                        context.setAttachment(Boolean.TRUE);
                    }
                    channel.setReadable(false);
                }
            } else {
                ((MemoryAwareRunnable) runnable).estimatedSize = estimateSize;
            }
            Limiter limiter = this.totalLimiter;
            if (limiter != null) {
                limiter.increase(estimateSize);
            }
        }
    }

    protected void decreaseCounter(Runnable runnable) {
        int i;
        if (shouldCount(runnable)) {
            long j = this.settings.maxChannelMemorySize;
            boolean z = runnable instanceof ChannelEventRunnable;
            if (z) {
                i = ((ChannelEventRunnable) runnable).estimatedSize;
            } else {
                i = ((MemoryAwareRunnable) runnable).estimatedSize;
            }
            Limiter limiter = this.totalLimiter;
            if (limiter != null) {
                limiter.decrease(i);
            }
            if (z) {
                ChannelEventRunnable channelEventRunnable = (ChannelEventRunnable) runnable;
                Channel channel = channelEventRunnable.getEvent().getChannel();
                long addAndGet = getChannelCounter(channel).addAndGet(-i);
                if (j == 0 || addAndGet >= j || !channel.isOpen() || channel.isReadable()) {
                    return;
                }
                ChannelHandlerContext context = channelEventRunnable.getContext();
                if (context.getHandler() instanceof ExecutionHandler) {
                    if (context.getAttachment() != null) {
                        context.setAttachment(null);
                        channel.setReadable(true);
                        return;
                    }
                    return;
                }
                channel.setReadable(true);
            }
        }
    }

    private AtomicLong getChannelCounter(Channel channel) {
        AtomicLong putIfAbsent;
        AtomicLong atomicLong = this.channelCounters.get(channel);
        if (atomicLong == null && (putIfAbsent = this.channelCounters.putIfAbsent(channel, (atomicLong = new AtomicLong()))) != null) {
            atomicLong = putIfAbsent;
        }
        if (!channel.isOpen()) {
            this.channelCounters.remove(channel);
        }
        return atomicLong;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean shouldCount(Runnable runnable) {
        if (!(runnable instanceof ChannelUpstreamEventRunnable)) {
            return true;
        }
        ChannelEvent event = ((ChannelUpstreamEventRunnable) runnable).getEvent();
        if (event instanceof WriteCompletionEvent) {
            return false;
        }
        return ((event instanceof ChannelStateEvent) && ((ChannelStateEvent) event).getState() == ChannelState.INTEREST_OPS) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class Settings {
        final long maxChannelMemorySize;
        final ObjectSizeEstimator objectSizeEstimator;

        Settings(ObjectSizeEstimator objectSizeEstimator, long j) {
            this.objectSizeEstimator = objectSizeEstimator;
            this.maxChannelMemorySize = j;
        }
    }

    /* loaded from: classes7.dex */
    private static final class NewThreadRunsPolicy implements RejectedExecutionHandler {
        private NewThreadRunsPolicy() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                new Thread(runnable, "Temporary task executor").start();
            } catch (Throwable th) {
                throw new RejectedExecutionException("Failed to start a new thread", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class MemoryAwareRunnable implements Runnable {
        int estimatedSize;
        final Runnable task;

        MemoryAwareRunnable(Runnable runnable) {
            this.task = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.task.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class Limiter {
        private long counter;
        final long limit;
        private int waiters;

        Limiter(long j) {
            this.limit = j;
        }

        synchronized void increase(long j) {
            int i;
            while (this.counter >= this.limit) {
                this.waiters++;
                try {
                    try {
                        wait();
                        i = this.waiters;
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        i = this.waiters;
                    }
                    this.waiters = i - 1;
                } catch (Throwable th) {
                    this.waiters--;
                    throw th;
                }
            }
            this.counter += j;
        }

        synchronized void decrease(long j) {
            this.counter -= j;
            if (this.counter < this.limit && this.waiters > 0) {
                notifyAll();
            }
        }
    }
}
