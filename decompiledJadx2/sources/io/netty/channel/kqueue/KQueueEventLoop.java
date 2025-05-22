package io.netty.channel.kqueue;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.SelectStrategy;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.channel.kqueue.AbstractKQueueChannel;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.IovArray;
import io.netty.util.IntSupplier;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
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
public final class KQueueEventLoop extends SingleThreadEventLoop {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int KQUEUE_WAKE_UP_IDENT = 0;
    private final boolean allowGrowing;
    private final KQueueEventArray changeList;
    private final KQueueEventArray eventList;
    private volatile int ioRatio;
    private final IovArray iovArray;
    private final NativeLongArray jniChannelPointers;
    private final FileDescriptor kqueueFd;
    private final Callable<Integer> pendingTasksCallable;
    private final IntSupplier selectNowSupplier;
    private final SelectStrategy selectStrategy;
    private volatile int wakenUp;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) KQueueEventLoop.class);
    private static final AtomicIntegerFieldUpdater<KQueueEventLoop> WAKEN_UP_UPDATER = AtomicIntegerFieldUpdater.newUpdater(KQueueEventLoop.class, "wakenUp");

    static {
        KQueue.ensureAvailability();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KQueueEventLoop(EventLoopGroup eventLoopGroup, Executor executor, int i, SelectStrategy selectStrategy, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventLoopGroup, executor, false, DEFAULT_MAX_PENDING_TASKS, rejectedExecutionHandler);
        this.iovArray = new IovArray();
        this.selectNowSupplier = new IntSupplier() { // from class: io.netty.channel.kqueue.KQueueEventLoop.1
            @Override // io.netty.util.IntSupplier
            public int get() throws Exception {
                return KQueueEventLoop.this.kqueueWaitNow();
            }
        };
        this.pendingTasksCallable = new Callable<Integer>() { // from class: io.netty.channel.kqueue.KQueueEventLoop.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(KQueueEventLoop.super.pendingTasks());
            }
        };
        this.ioRatio = 50;
        this.selectStrategy = (SelectStrategy) ObjectUtil.checkNotNull(selectStrategy, "strategy");
        this.kqueueFd = Native.newKQueue();
        if (i == 0) {
            this.allowGrowing = true;
            i = 4096;
        } else {
            this.allowGrowing = false;
        }
        this.changeList = new KQueueEventArray(i);
        this.eventList = new KQueueEventArray(i);
        this.jniChannelPointers = new NativeLongArray(4096);
        int keventAddUserEvent = Native.keventAddUserEvent(this.kqueueFd.intValue(), 0);
        if (keventAddUserEvent >= 0) {
            return;
        }
        cleanup();
        throw new IllegalStateException("kevent failed to add user event with errno: " + (-keventAddUserEvent));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void evSet(AbstractKQueueChannel abstractKQueueChannel, short s, short s2, int i) {
        this.changeList.evSet(abstractKQueueChannel, s, s2, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void remove(AbstractKQueueChannel abstractKQueueChannel) throws IOException {
        if (abstractKQueueChannel.jniSelfPtr == 0) {
            return;
        }
        this.jniChannelPointers.add(abstractKQueueChannel.jniSelfPtr);
        abstractKQueueChannel.jniSelfPtr = 0L;
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
        wakeup();
    }

    private void wakeup() {
        Native.keventTriggerUserEvent(this.kqueueFd.intValue(), 0);
    }

    private int kqueueWait(boolean z) throws IOException {
        if (z && hasTasks()) {
            return kqueueWaitNow();
        }
        long delayNanos = delayNanos(System.nanoTime());
        int min = (int) Math.min(delayNanos / 1000000000, 2147483647L);
        return kqueueWait(min, (int) Math.min(delayNanos - (min * 1000000000), 2147483647L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int kqueueWaitNow() throws IOException {
        return kqueueWait(0, 0);
    }

    private int kqueueWait(int i, int i2) throws IOException {
        deleteJniChannelPointers();
        int keventWait = Native.keventWait(this.kqueueFd.intValue(), this.changeList, this.eventList, i, i2);
        this.changeList.clear();
        return keventWait;
    }

    private void deleteJniChannelPointers() {
        if (this.jniChannelPointers.isEmpty()) {
            return;
        }
        KQueueEventArray.deleteGlobalRefs(this.jniChannelPointers.memoryAddress(), this.jniChannelPointers.memoryAddressEnd());
        this.jniChannelPointers.clear();
    }

    private void processReady(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            short filter = this.eventList.filter(i2);
            short flags = this.eventList.flags(i2);
            if (filter != Native.EVFILT_USER && (Native.EV_ERROR & flags) == 0) {
                AbstractKQueueChannel channel = this.eventList.channel(i2);
                if (channel == null) {
                    logger.warn("events[{}]=[{}, {}] had no channel!", Integer.valueOf(i2), Integer.valueOf(this.eventList.m3933fd(i2)), Short.valueOf(filter));
                } else {
                    AbstractKQueueChannel.AbstractKQueueUnsafe abstractKQueueUnsafe = (AbstractKQueueChannel.AbstractKQueueUnsafe) channel.unsafe();
                    if (filter == Native.EVFILT_WRITE) {
                        abstractKQueueUnsafe.writeReady();
                    } else if (filter == Native.EVFILT_READ) {
                        abstractKQueueUnsafe.readReady(this.eventList.data(i2));
                    } else if (filter == Native.EVFILT_SOCK && (this.eventList.fflags(i2) & Native.NOTE_RDHUP) != 0) {
                        abstractKQueueUnsafe.readEOF();
                    }
                    if ((Native.EV_EOF & flags) != 0) {
                        abstractKQueueUnsafe.readEOF();
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0082 A[SYNTHETIC] */
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
                    calculateStrategy = kqueueWait(WAKEN_UP_UPDATER.getAndSet(this, 0) == 1);
                    if (this.wakenUp == 1) {
                        wakeup();
                    }
                }
                int i = this.ioRatio;
                if (i == 100) {
                    if (calculateStrategy > 0) {
                        try {
                            processReady(calculateStrategy);
                        } catch (Throwable th2) {
                            runAllTasks();
                            throw th2;
                        }
                    }
                    runAllTasks();
                    if (this.allowGrowing && calculateStrategy == this.eventList.capacity()) {
                        this.eventList.realloc(false);
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
                            processReady(calculateStrategy);
                        } finally {
                            runAllTasks(((System.nanoTime() - nanoTime) * (100 - i)) / i);
                        }
                    }
                    if (this.allowGrowing) {
                        this.eventList.realloc(false);
                    }
                    if (isShuttingDown()) {
                    }
                }
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
        return inEventLoop() ? super.pendingTasks() : ((Integer) submit((Callable) this.pendingTasksCallable).syncUninterruptibly().getNow()).intValue();
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

    @Override // io.netty.util.concurrent.SingleThreadEventExecutor
    protected void cleanup() {
        try {
            try {
                this.kqueueFd.close();
            } catch (IOException e) {
                logger.warn("Failed to close the kqueue fd.", (Throwable) e);
            }
        } finally {
            deleteJniChannelPointers();
            this.jniChannelPointers.free();
            this.changeList.free();
            this.eventList.free();
        }
    }

    private void closeAll() {
        try {
            kqueueWaitNow();
        } catch (IOException unused) {
        }
    }

    private static void handleLoopException(Throwable th) {
        logger.warn("Unexpected exception in the selector loop.", th);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException unused) {
        }
    }
}
