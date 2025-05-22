package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.EventLoopTaskQueueFactory;
import io.grpc.netty.shaded.io.netty.channel.SelectStrategy;
import io.grpc.netty.shaded.io.netty.channel.SingleThreadEventLoop;
import io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel;
import io.grpc.netty.shaded.io.netty.channel.unix.FileDescriptor;
import io.grpc.netty.shaded.io.netty.channel.unix.IovArray;
import io.grpc.netty.shaded.io.netty.util.IntSupplier;
import io.grpc.netty.shaded.io.netty.util.collection.IntObjectHashMap;
import io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap;
import io.grpc.netty.shaded.io.netty.util.concurrent.RejectedExecutionHandler;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class EpollEventLoop extends SingleThreadEventLoop {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long AWAKE = -1;
    private static final long MAX_SCHEDULED_TIMERFD_NS = 999999999;
    private static final long NONE = Long.MAX_VALUE;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) EpollEventLoop.class);
    private final boolean allowGrowing;
    private final IntObjectMap<AbstractEpollChannel> channels;
    private NativeDatagramPacketArray datagramPacketArray;
    private final FileDescriptor epollFd;
    private final FileDescriptor eventFd;
    private final EpollEventArray events;
    private volatile int ioRatio;
    private IovArray iovArray;
    private final AtomicLong nextWakeupNanos;
    private boolean pendingWakeup;
    private final IntSupplier selectNowSupplier;
    private final SelectStrategy selectStrategy;
    private final FileDescriptor timerFd;

    static {
        Epoll.ensureAvailability();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EpollEventLoop(EventLoopGroup eventLoopGroup, Executor executor, int i, SelectStrategy selectStrategy, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        super(eventLoopGroup, executor, false, newTaskQueue(eventLoopTaskQueueFactory), newTaskQueue(eventLoopTaskQueueFactory), rejectedExecutionHandler);
        FileDescriptor fileDescriptor;
        FileDescriptor fileDescriptor2;
        this.channels = new IntObjectHashMap(4096);
        this.selectNowSupplier = new IntSupplier() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.EpollEventLoop.1
            @Override // io.grpc.netty.shaded.io.netty.util.IntSupplier
            public int get() throws Exception {
                return EpollEventLoop.this.epollWaitNow();
            }
        };
        this.nextWakeupNanos = new AtomicLong(-1L);
        this.ioRatio = 50;
        this.selectStrategy = (SelectStrategy) ObjectUtil.checkNotNull(selectStrategy, "strategy");
        if (i == 0) {
            this.allowGrowing = true;
            this.events = new EpollEventArray(4096);
        } else {
            this.allowGrowing = false;
            this.events = new EpollEventArray(i);
        }
        FileDescriptor fileDescriptor3 = null;
        try {
            fileDescriptor = Native.newEpollCreate();
            try {
                this.epollFd = fileDescriptor;
                fileDescriptor2 = Native.newEventFd();
            } catch (Throwable th) {
                th = th;
                fileDescriptor2 = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileDescriptor = null;
            fileDescriptor2 = null;
        }
        try {
            this.eventFd = fileDescriptor2;
            try {
                Native.epollCtlAdd(fileDescriptor.intValue(), fileDescriptor2.intValue(), Native.EPOLLIN | Native.EPOLLET);
                fileDescriptor3 = Native.newTimerFd();
                this.timerFd = fileDescriptor3;
                try {
                    Native.epollCtlAdd(fileDescriptor.intValue(), fileDescriptor3.intValue(), Native.EPOLLIN | Native.EPOLLET);
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to add timerFd filedescriptor to epoll", e);
                }
            } catch (IOException e2) {
                throw new IllegalStateException("Unable to add eventFd filedescriptor to epoll", e2);
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileDescriptor != null) {
                try {
                    fileDescriptor.close();
                } catch (Exception unused) {
                }
            }
            if (fileDescriptor2 != null) {
                try {
                    fileDescriptor2.close();
                } catch (Exception unused2) {
                }
            }
            if (fileDescriptor3 != null) {
                try {
                    fileDescriptor3.close();
                    throw th;
                } catch (Exception unused3) {
                    throw th;
                }
            }
            throw th;
        }
    }

    private static Queue<Runnable> newTaskQueue(EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        if (eventLoopTaskQueueFactory == null) {
            return newTaskQueue0(DEFAULT_MAX_PENDING_TASKS);
        }
        return eventLoopTaskQueueFactory.newTaskQueue(DEFAULT_MAX_PENDING_TASKS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IovArray cleanIovArray() {
        IovArray iovArray = this.iovArray;
        if (iovArray == null) {
            this.iovArray = new IovArray();
        } else {
            iovArray.clear();
        }
        return this.iovArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeDatagramPacketArray cleanDatagramPacketArray() {
        NativeDatagramPacketArray nativeDatagramPacketArray = this.datagramPacketArray;
        if (nativeDatagramPacketArray == null) {
            this.datagramPacketArray = new NativeDatagramPacketArray();
        } else {
            nativeDatagramPacketArray.clear();
        }
        return this.datagramPacketArray;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    public void wakeup(boolean z) {
        if (z || this.nextWakeupNanos.getAndSet(-1L) == -1) {
            return;
        }
        Native.eventFdWrite(this.eventFd.intValue(), 1L);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractScheduledEventExecutor
    protected boolean beforeScheduledTaskSubmitted(long j) {
        return j < this.nextWakeupNanos.get();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractScheduledEventExecutor
    protected boolean afterScheduledTaskSubmitted(long j) {
        return j < this.nextWakeupNanos.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(AbstractEpollChannel abstractEpollChannel) throws IOException {
        int intValue = abstractEpollChannel.socket.intValue();
        Native.epollCtlAdd(this.epollFd.intValue(), intValue, abstractEpollChannel.flags);
        this.channels.put(intValue, (int) abstractEpollChannel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void modify(AbstractEpollChannel abstractEpollChannel) throws IOException {
        Native.epollCtlMod(this.epollFd.intValue(), abstractEpollChannel.socket.intValue(), abstractEpollChannel.flags);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void remove(AbstractEpollChannel abstractEpollChannel) throws IOException {
        int intValue = abstractEpollChannel.socket.intValue();
        AbstractEpollChannel remove = this.channels.remove(intValue);
        if (remove != null && remove != abstractEpollChannel) {
            this.channels.put(intValue, (int) remove);
        } else if (abstractEpollChannel.isOpen()) {
            Native.epollCtlDel(this.epollFd.intValue(), intValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    public Queue<Runnable> newTaskQueue(int i) {
        return newTaskQueue0(i);
    }

    private static Queue<Runnable> newTaskQueue0(int i) {
        if (i == Integer.MAX_VALUE) {
            return PlatformDependent.newMpscQueue();
        }
        return PlatformDependent.newMpscQueue(i);
    }

    public int getIoRatio() {
        return this.ioRatio;
    }

    public void setIoRatio(int i) {
        if (i <= 0 || i > 100) {
            throw new IllegalArgumentException("ioRatio: " + i + " (expected: 0 < ioRatio <= 100)");
        }
        this.ioRatio = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.SingleThreadEventLoop
    public int registeredChannels() {
        return this.channels.size();
    }

    private int epollWait(long j) throws IOException {
        if (j == Long.MAX_VALUE) {
            return Native.epollWait(this.epollFd, this.events, this.timerFd, Integer.MAX_VALUE, 0);
        }
        long deadlineToDelayNanos = deadlineToDelayNanos(j);
        int min = (int) Math.min(deadlineToDelayNanos / 1000000000, 2147483647L);
        return Native.epollWait(this.epollFd, this.events, this.timerFd, min, (int) Math.min(deadlineToDelayNanos - (min * 1000000000), MAX_SCHEDULED_TIMERFD_NS));
    }

    private int epollWaitNoTimerChange() throws IOException {
        return Native.epollWait(this.epollFd, this.events, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int epollWaitNow() throws IOException {
        return Native.epollWait(this.epollFd, this.events, true);
    }

    private int epollBusyWait() throws IOException {
        return Native.epollBusyWait(this.epollFd, this.events);
    }

    private int epollWaitTimeboxed() throws IOException {
        return Native.epollWait(this.epollFd, this.events, 1000);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0006 A[SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void run() {
        int calculateStrategy;
        long j = Long.MAX_VALUE;
        while (true) {
            try {
                calculateStrategy = this.selectStrategy.calculateStrategy(this.selectNowSupplier, hasTasks());
            } catch (Throwable th) {
                handleLoopException(th);
            }
            if (calculateStrategy == -3) {
                calculateStrategy = epollBusyWait();
            } else if (calculateStrategy == -2) {
                continue;
            } else if (calculateStrategy == -1) {
                if (this.pendingWakeup) {
                    calculateStrategy = epollWaitTimeboxed();
                    if (calculateStrategy == 0) {
                        logger.warn("Missed eventfd write (not seen after > 1 second)");
                        this.pendingWakeup = false;
                        if (hasTasks()) {
                        }
                    }
                }
                long nextScheduledTaskDeadlineNanos = nextScheduledTaskDeadlineNanos();
                if (nextScheduledTaskDeadlineNanos == -1) {
                    nextScheduledTaskDeadlineNanos = Long.MAX_VALUE;
                }
                this.nextWakeupNanos.set(nextScheduledTaskDeadlineNanos);
                try {
                    if (!hasTasks()) {
                        if (nextScheduledTaskDeadlineNanos == j) {
                            calculateStrategy = epollWaitNoTimerChange();
                        } else {
                            try {
                                calculateStrategy = epollWait(nextScheduledTaskDeadlineNanos);
                                j = nextScheduledTaskDeadlineNanos;
                            } catch (Throwable th2) {
                                th = th2;
                                if (this.nextWakeupNanos.get() == -1 || this.nextWakeupNanos.getAndSet(-1L) == -1) {
                                    this.pendingWakeup = true;
                                }
                                throw th;
                            }
                        }
                    }
                    if (this.nextWakeupNanos.get() == -1 || this.nextWakeupNanos.getAndSet(-1L) == -1) {
                        this.pendingWakeup = true;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            int i = this.ioRatio;
            if (i == 100) {
                if (calculateStrategy > 0) {
                    try {
                        if (processReady(this.events, calculateStrategy)) {
                            j = Long.MAX_VALUE;
                        }
                    } catch (Throwable th4) {
                        runAllTasks();
                        throw th4;
                    }
                }
                runAllTasks();
                if (this.allowGrowing && calculateStrategy == this.events.length()) {
                    this.events.increase();
                }
                try {
                    if (isShuttingDown()) {
                        continue;
                    } else {
                        closeAll();
                        if (confirmShutdown()) {
                            return;
                        }
                    }
                } catch (Throwable th5) {
                    handleLoopException(th5);
                }
            } else {
                if (calculateStrategy > 0) {
                    long nanoTime = System.nanoTime();
                    try {
                        if (processReady(this.events, calculateStrategy)) {
                            j = Long.MAX_VALUE;
                        }
                        runAllTasks(((System.nanoTime() - nanoTime) * (100 - i)) / i);
                    } catch (Throwable th6) {
                        runAllTasks(((System.nanoTime() - nanoTime) * (100 - i)) / i);
                        throw th6;
                    }
                } else {
                    runAllTasks(0L);
                }
                if (this.allowGrowing) {
                    this.events.increase();
                }
                if (isShuttingDown()) {
                }
            }
        }
    }

    void handleLoopException(Throwable th) {
        logger.warn("Unexpected exception in the selector loop.", th);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException unused) {
        }
    }

    private void closeAll() {
        for (AbstractEpollChannel abstractEpollChannel : (AbstractEpollChannel[]) this.channels.values().toArray(new AbstractEpollChannel[0])) {
            abstractEpollChannel.unsafe().close(abstractEpollChannel.unsafe().voidPromise());
        }
    }

    private boolean processReady(EpollEventArray epollEventArray, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < i; i2++) {
            int m3909fd = epollEventArray.m3909fd(i2);
            if (m3909fd == this.eventFd.intValue()) {
                this.pendingWakeup = false;
            } else if (m3909fd == this.timerFd.intValue()) {
                z = true;
            } else {
                long events = epollEventArray.events(i2);
                AbstractEpollChannel abstractEpollChannel = this.channels.get(m3909fd);
                if (abstractEpollChannel != null) {
                    AbstractEpollChannel.AbstractEpollUnsafe abstractEpollUnsafe = (AbstractEpollChannel.AbstractEpollUnsafe) abstractEpollChannel.unsafe();
                    if (((Native.EPOLLERR | Native.EPOLLOUT) & events) != 0) {
                        abstractEpollUnsafe.epollOutReady();
                    }
                    if (((Native.EPOLLERR | Native.EPOLLIN) & events) != 0) {
                        abstractEpollUnsafe.epollInReady();
                    }
                    if ((events & Native.EPOLLRDHUP) != 0) {
                        abstractEpollUnsafe.epollRdHupReady();
                    }
                } else {
                    try {
                        Native.epollCtlDel(this.epollFd.intValue(), m3909fd);
                    } catch (IOException unused) {
                    }
                }
            }
        }
        return z;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    protected void cleanup() {
        int epollWaitTimeboxed;
        while (true) {
            try {
                if (this.pendingWakeup) {
                    try {
                        epollWaitTimeboxed = epollWaitTimeboxed();
                    } catch (IOException unused) {
                    }
                    if (epollWaitTimeboxed == 0) {
                        break;
                    }
                    int i = 0;
                    while (true) {
                        if (i >= epollWaitTimeboxed) {
                            break;
                        }
                        if (this.events.m3909fd(i) == this.eventFd.intValue()) {
                            this.pendingWakeup = false;
                            break;
                        }
                        i++;
                    }
                }
            } finally {
                IovArray iovArray = this.iovArray;
                if (iovArray != null) {
                    iovArray.release();
                    this.iovArray = null;
                }
                NativeDatagramPacketArray nativeDatagramPacketArray = this.datagramPacketArray;
                if (nativeDatagramPacketArray != null) {
                    nativeDatagramPacketArray.release();
                    this.datagramPacketArray = null;
                }
                this.events.free();
            }
        }
        try {
            this.eventFd.close();
        } catch (IOException e) {
            logger.warn("Failed to close the event fd.", (Throwable) e);
        }
        try {
            this.timerFd.close();
        } catch (IOException e2) {
            logger.warn("Failed to close the timer fd.", (Throwable) e2);
        }
        try {
            this.epollFd.close();
        } catch (IOException e3) {
            logger.warn("Failed to close the epoll fd.", (Throwable) e3);
        }
    }
}
