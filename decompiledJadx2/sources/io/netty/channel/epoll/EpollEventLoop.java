package io.netty.channel.epoll;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.SelectStrategy;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.IovArray;
import io.netty.util.IntSupplier;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class EpollEventLoop extends SingleThreadEventLoop {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean allowGrowing;
    private final IntObjectMap<AbstractEpollChannel> channels;
    private final FileDescriptor epollFd;
    private final FileDescriptor eventFd;
    private final EpollEventArray events;
    private volatile int ioRatio;
    private final IovArray iovArray;
    private final Callable<Integer> pendingTasksCallable;
    private final IntSupplier selectNowSupplier;
    private final SelectStrategy selectStrategy;
    private final FileDescriptor timerFd;
    private volatile int wakenUp;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) EpollEventLoop.class);
    private static final AtomicIntegerFieldUpdater<EpollEventLoop> WAKEN_UP_UPDATER = AtomicIntegerFieldUpdater.newUpdater(EpollEventLoop.class, "wakenUp");

    static {
        Epoll.ensureAvailability();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EpollEventLoop(EventLoopGroup eventLoopGroup, Executor executor, int i, SelectStrategy selectStrategy, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventLoopGroup, executor, false, DEFAULT_MAX_PENDING_TASKS, rejectedExecutionHandler);
        FileDescriptor fileDescriptor;
        FileDescriptor fileDescriptor2;
        FileDescriptor newEpollCreate;
        this.channels = new IntObjectHashMap(4096);
        this.iovArray = new IovArray();
        this.selectNowSupplier = new IntSupplier() { // from class: io.netty.channel.epoll.EpollEventLoop.1
            @Override // io.netty.util.IntSupplier
            public int get() throws Exception {
                return EpollEventLoop.this.epollWaitNow();
            }
        };
        this.pendingTasksCallable = new Callable<Integer>() { // from class: io.netty.channel.epoll.EpollEventLoop.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(EpollEventLoop.super.pendingTasks());
            }
        };
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
            newEpollCreate = Native.newEpollCreate();
            try {
                this.epollFd = newEpollCreate;
                fileDescriptor2 = Native.newEventFd();
            } catch (Throwable th) {
                th = th;
                fileDescriptor2 = null;
                fileDescriptor3 = newEpollCreate;
                fileDescriptor = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileDescriptor = null;
            fileDescriptor2 = null;
        }
        try {
            this.eventFd = fileDescriptor2;
            try {
                Native.epollCtlAdd(newEpollCreate.intValue(), fileDescriptor2.intValue(), Native.EPOLLIN);
                fileDescriptor3 = Native.newTimerFd();
                this.timerFd = fileDescriptor3;
                try {
                    Native.epollCtlAdd(newEpollCreate.intValue(), fileDescriptor3.intValue(), Native.EPOLLIN | Native.EPOLLET);
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to add timerFd filedescriptor to epoll", e);
                }
            } catch (IOException e2) {
                throw new IllegalStateException("Unable to add eventFd filedescriptor to epoll", e2);
            }
        } catch (Throwable th3) {
            th = th3;
            fileDescriptor = fileDescriptor3;
            fileDescriptor3 = newEpollCreate;
            if (fileDescriptor3 != null) {
                try {
                    fileDescriptor3.close();
                } catch (Exception unused) {
                }
            }
            if (fileDescriptor2 != null) {
                try {
                    fileDescriptor2.close();
                } catch (Exception unused2) {
                }
            }
            if (fileDescriptor != null) {
                try {
                    fileDescriptor.close();
                    throw th;
                } catch (Exception unused3) {
                    throw th;
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IovArray cleanArray() {
        this.iovArray.clear();
        return this.iovArray;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.util.concurrent.SingleThreadEventExecutor
    public void wakeup(boolean z) {
        if (z || !WAKEN_UP_UPDATER.compareAndSet(this, 0, 1)) {
            return;
        }
        Native.eventFdWrite(this.eventFd.intValue(), 1L);
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
        if (abstractEpollChannel.isOpen()) {
            if (this.channels.remove(abstractEpollChannel.socket.intValue()) != null) {
                Native.epollCtlDel(this.epollFd.intValue(), abstractEpollChannel.mo3930fd().intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.util.concurrent.SingleThreadEventExecutor
    public Queue<Runnable> newTaskQueue(int i) {
        if (i == Integer.MAX_VALUE) {
            return PlatformDependent.newMpscQueue();
        }
        return PlatformDependent.newMpscQueue(i);
    }

    @Override // io.netty.channel.SingleThreadEventLoop, io.netty.util.concurrent.SingleThreadEventExecutor
    public int pendingTasks() {
        if (inEventLoop()) {
            return super.pendingTasks();
        }
        return ((Integer) submit((Callable) this.pendingTasksCallable).syncUninterruptibly().getNow()).intValue();
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

    private int epollWait(boolean z) throws IOException {
        if (z && hasTasks()) {
            return epollWaitNow();
        }
        long delayNanos = delayNanos(System.nanoTime());
        int min = (int) Math.min(delayNanos / 1000000000, 2147483647L);
        return Native.epollWait(this.epollFd, this.events, this.timerFd, min, (int) Math.min(delayNanos - (min * 1000000000), 2147483647L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int epollWaitNow() throws IOException {
        return Native.epollWait(this.epollFd, this.events, this.timerFd, 0, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0000 A[SYNTHETIC] */
    @Override // io.netty.util.concurrent.SingleThreadEventExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void run() {
        int calculateStrategy;
        while (true) {
            try {
                calculateStrategy = this.selectStrategy.calculateStrategy(this.selectNowSupplier, hasTasks());
            } catch (Throwable th) {
                handleLoopException(th);
            }
            if (calculateStrategy == -2) {
                continue;
            } else {
                if (calculateStrategy == -1) {
                    calculateStrategy = epollWait(WAKEN_UP_UPDATER.getAndSet(this, 0) == 1);
                    if (this.wakenUp == 1) {
                        Native.eventFdWrite(this.eventFd.intValue(), 1L);
                    }
                }
                int i = this.ioRatio;
                if (i == 100) {
                    if (calculateStrategy > 0) {
                        try {
                            processReady(this.events, calculateStrategy);
                        } catch (Throwable th2) {
                            runAllTasks();
                            throw th2;
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
                    } catch (Throwable th3) {
                        handleLoopException(th3);
                    }
                } else {
                    long nanoTime = System.nanoTime();
                    if (calculateStrategy > 0) {
                        try {
                            processReady(this.events, calculateStrategy);
                        } finally {
                            runAllTasks(((System.nanoTime() - nanoTime) * (100 - i)) / i);
                        }
                    }
                    if (this.allowGrowing) {
                        this.events.increase();
                    }
                    if (isShuttingDown()) {
                    }
                }
            }
        }
    }

    private static void handleLoopException(Throwable th) {
        logger.warn("Unexpected exception in the selector loop.", th);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException unused) {
        }
    }

    private void closeAll() {
        try {
            epollWaitNow();
        } catch (IOException unused) {
        }
        ArrayList<AbstractEpollChannel> arrayList = new ArrayList(this.channels.size());
        Iterator<AbstractEpollChannel> it = this.channels.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        for (AbstractEpollChannel abstractEpollChannel : arrayList) {
            abstractEpollChannel.unsafe().close(abstractEpollChannel.unsafe().voidPromise());
        }
    }

    private void processReady(EpollEventArray epollEventArray, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int m3931fd = epollEventArray.m3931fd(i2);
            if (m3931fd == this.eventFd.intValue()) {
                Native.eventFdRead(m3931fd);
            } else if (m3931fd == this.timerFd.intValue()) {
                Native.timerFdRead(m3931fd);
            } else {
                long events = epollEventArray.events(i2);
                AbstractEpollChannel abstractEpollChannel = this.channels.get(m3931fd);
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
                        Native.epollCtlDel(this.epollFd.intValue(), m3931fd);
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    @Override // io.netty.util.concurrent.SingleThreadEventExecutor
    protected void cleanup() {
        try {
            try {
                this.epollFd.close();
            } catch (IOException e) {
                logger.warn("Failed to close the epoll fd.", (Throwable) e);
            }
            try {
                this.eventFd.close();
            } catch (IOException e2) {
                logger.warn("Failed to close the event fd.", (Throwable) e2);
            }
            try {
                this.timerFd.close();
            } catch (IOException e3) {
                logger.warn("Failed to close the timer fd.", (Throwable) e3);
            }
        } finally {
            this.iovArray.release();
            this.events.free();
        }
    }
}
