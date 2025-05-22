package io.grpc.netty.shaded.io.netty.channel.nio;

import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.EventLoopException;
import io.grpc.netty.shaded.io.netty.channel.EventLoopTaskQueueFactory;
import io.grpc.netty.shaded.io.netty.channel.SelectStrategy;
import io.grpc.netty.shaded.io.netty.channel.SingleThreadEventLoop;
import io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel;
import io.grpc.netty.shaded.io.netty.util.IntSupplier;
import io.grpc.netty.shaded.io.netty.util.concurrent.RejectedExecutionHandler;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.ReflectionUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NioEventLoop extends SingleThreadEventLoop {
    private static final long AWAKE = -1;
    private static final int CLEANUP_INTERVAL = 256;
    private static final int MIN_PREMATURE_SELECTOR_RETURNS = 3;
    private static final long NONE = Long.MAX_VALUE;
    private static final int SELECTOR_AUTO_REBUILD_THRESHOLD;
    private int cancelledKeys;
    private volatile int ioRatio;
    private boolean needsToSelectAgain;
    private final AtomicLong nextWakeupNanos;
    private final SelectorProvider provider;
    private final IntSupplier selectNowSupplier;
    private final SelectStrategy selectStrategy;
    private SelectedSelectionKeySet selectedKeys;
    private Selector selector;
    private Selector unwrappedSelector;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioEventLoop.class);
    private static final boolean DISABLE_KEY_SET_OPTIMIZATION = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.noKeySetOptimization", false);

    static {
        if (SystemPropertyUtil.get("sun.nio.ch.bugLevel") == null) {
            try {
                AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoop.2
                    @Override // java.security.PrivilegedAction
                    public Void run() {
                        System.setProperty("sun.nio.ch.bugLevel", "");
                        return null;
                    }
                });
            } catch (SecurityException e) {
                logger.debug("Unable to get/set System Property: sun.nio.ch.bugLevel", (Throwable) e);
            }
        }
        int i = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.selectorAutoRebuildThreshold", 512);
        SELECTOR_AUTO_REBUILD_THRESHOLD = i >= 3 ? i : 0;
        if (logger.isDebugEnabled()) {
            logger.debug("-Dio.netty.noKeySetOptimization: {}", Boolean.valueOf(DISABLE_KEY_SET_OPTIMIZATION));
            logger.debug("-Dio.netty.selectorAutoRebuildThreshold: {}", Integer.valueOf(SELECTOR_AUTO_REBUILD_THRESHOLD));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioEventLoop(NioEventLoopGroup nioEventLoopGroup, Executor executor, SelectorProvider selectorProvider, SelectStrategy selectStrategy, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        super(nioEventLoopGroup, executor, false, newTaskQueue(eventLoopTaskQueueFactory), newTaskQueue(eventLoopTaskQueueFactory), rejectedExecutionHandler);
        this.selectNowSupplier = new IntSupplier() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoop.1
            @Override // io.grpc.netty.shaded.io.netty.util.IntSupplier
            public int get() throws Exception {
                return NioEventLoop.this.selectNow();
            }
        };
        this.nextWakeupNanos = new AtomicLong(-1L);
        this.ioRatio = 50;
        this.provider = (SelectorProvider) ObjectUtil.checkNotNull(selectorProvider, "selectorProvider");
        this.selectStrategy = (SelectStrategy) ObjectUtil.checkNotNull(selectStrategy, "selectStrategy");
        SelectorTuple openSelector = openSelector();
        this.selector = openSelector.selector;
        this.unwrappedSelector = openSelector.unwrappedSelector;
    }

    private static Queue<Runnable> newTaskQueue(EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        if (eventLoopTaskQueueFactory == null) {
            return newTaskQueue0(DEFAULT_MAX_PENDING_TASKS);
        }
        return eventLoopTaskQueueFactory.newTaskQueue(DEFAULT_MAX_PENDING_TASKS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SelectorTuple {
        final Selector selector;
        final Selector unwrappedSelector;

        SelectorTuple(Selector selector) {
            this.unwrappedSelector = selector;
            this.selector = selector;
        }

        SelectorTuple(Selector selector, Selector selector2) {
            this.unwrappedSelector = selector;
            this.selector = selector2;
        }
    }

    private SelectorTuple openSelector() {
        try {
            final AbstractSelector openSelector = this.provider.openSelector();
            if (DISABLE_KEY_SET_OPTIMIZATION) {
                return new SelectorTuple(openSelector);
            }
            Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoop.3
                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        return Class.forName("sun.nio.ch.SelectorImpl", false, PlatformDependent.getSystemClassLoader());
                    } catch (Throwable th) {
                        return th;
                    }
                }
            });
            if (doPrivileged instanceof Class) {
                final Class cls = (Class) doPrivileged;
                if (cls.isAssignableFrom(openSelector.getClass())) {
                    final SelectedSelectionKeySet selectedSelectionKeySet = new SelectedSelectionKeySet();
                    Object doPrivileged2 = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoop.4
                        @Override // java.security.PrivilegedAction
                        public Object run() {
                            try {
                                Field declaredField = cls.getDeclaredField("selectedKeys");
                                Field declaredField2 = cls.getDeclaredField("publicSelectedKeys");
                                if (PlatformDependent.javaVersion() >= 9 && PlatformDependent.hasUnsafe()) {
                                    long objectFieldOffset = PlatformDependent.objectFieldOffset(declaredField);
                                    long objectFieldOffset2 = PlatformDependent.objectFieldOffset(declaredField2);
                                    if (objectFieldOffset != -1 && objectFieldOffset2 != -1) {
                                        PlatformDependent.putObject(openSelector, objectFieldOffset, selectedSelectionKeySet);
                                        PlatformDependent.putObject(openSelector, objectFieldOffset2, selectedSelectionKeySet);
                                        return null;
                                    }
                                }
                                Throwable trySetAccessible = ReflectionUtil.trySetAccessible(declaredField, true);
                                if (trySetAccessible != null) {
                                    return trySetAccessible;
                                }
                                Throwable trySetAccessible2 = ReflectionUtil.trySetAccessible(declaredField2, true);
                                if (trySetAccessible2 != null) {
                                    return trySetAccessible2;
                                }
                                declaredField.set(openSelector, selectedSelectionKeySet);
                                declaredField2.set(openSelector, selectedSelectionKeySet);
                                return null;
                            } catch (IllegalAccessException e) {
                                return e;
                            } catch (NoSuchFieldException e2) {
                                return e2;
                            }
                        }
                    });
                    if (doPrivileged2 instanceof Exception) {
                        this.selectedKeys = null;
                        logger.trace("failed to instrument a special java.util.Set into: {}", openSelector, (Exception) doPrivileged2);
                        return new SelectorTuple(openSelector);
                    }
                    this.selectedKeys = selectedSelectionKeySet;
                    logger.trace("instrumented a special java.util.Set into: {}", openSelector);
                    return new SelectorTuple(openSelector, new SelectedSelectionKeySetSelector(openSelector, selectedSelectionKeySet));
                }
            }
            if (doPrivileged instanceof Throwable) {
                logger.trace("failed to instrument a special java.util.Set into: {}", openSelector, (Throwable) doPrivileged);
            }
            return new SelectorTuple(openSelector);
        } catch (IOException e) {
            throw new ChannelException("failed to open a new selector", e);
        }
    }

    public SelectorProvider selectorProvider() {
        return this.provider;
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

    public void register(final SelectableChannel selectableChannel, final int i, final NioTask<?> nioTask) {
        ObjectUtil.checkNotNull(selectableChannel, "ch");
        if (i == 0) {
            throw new IllegalArgumentException("interestOps must be non-zero.");
        }
        if (((~selectableChannel.validOps()) & i) != 0) {
            throw new IllegalArgumentException("invalid interestOps: " + i + "(validOps: " + selectableChannel.validOps() + ')');
        }
        ObjectUtil.checkNotNull(nioTask, "task");
        if (isShutdown()) {
            throw new IllegalStateException("event loop shut down");
        }
        if (inEventLoop()) {
            register0(selectableChannel, i, nioTask);
            return;
        }
        try {
            submit(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoop.5
                @Override // java.lang.Runnable
                public void run() {
                    NioEventLoop.this.register0(selectableChannel, i, nioTask);
                }
            }).sync();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void register0(SelectableChannel selectableChannel, int i, NioTask<?> nioTask) {
        try {
            selectableChannel.register(this.unwrappedSelector, i, nioTask);
        } catch (Exception e) {
            throw new EventLoopException("failed to register a channel", e);
        }
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

    public void rebuildSelector() {
        if (!inEventLoop()) {
            execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoop.6
                @Override // java.lang.Runnable
                public void run() {
                    NioEventLoop.this.rebuildSelector0();
                }
            });
        } else {
            rebuildSelector0();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.SingleThreadEventLoop
    public int registeredChannels() {
        return this.selector.keys().size() - this.cancelledKeys;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rebuildSelector0() {
        Selector selector = this.selector;
        if (selector == null) {
            return;
        }
        try {
            SelectorTuple openSelector = openSelector();
            int i = 0;
            for (SelectionKey selectionKey : selector.keys()) {
                Object attachment = selectionKey.attachment();
                try {
                    if (selectionKey.isValid() && selectionKey.channel().keyFor(openSelector.unwrappedSelector) == null) {
                        int interestOps = selectionKey.interestOps();
                        selectionKey.cancel();
                        SelectionKey register = selectionKey.channel().register(openSelector.unwrappedSelector, interestOps, attachment);
                        if (attachment instanceof AbstractNioChannel) {
                            ((AbstractNioChannel) attachment).selectionKey = register;
                        }
                        i++;
                    }
                } catch (Exception e) {
                    logger.warn("Failed to re-register a Channel to the new Selector.", (Throwable) e);
                    if (attachment instanceof AbstractNioChannel) {
                        AbstractNioChannel abstractNioChannel = (AbstractNioChannel) attachment;
                        abstractNioChannel.unsafe().close(abstractNioChannel.unsafe().voidPromise());
                    } else {
                        invokeChannelUnregistered((NioTask) attachment, selectionKey, e);
                    }
                }
            }
            this.selector = openSelector.selector;
            this.unwrappedSelector = openSelector.unwrappedSelector;
            try {
                selector.close();
            } catch (Throwable th) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Failed to close the old Selector.", th);
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("Migrated " + i + " channel(s) to the new Selector.");
            }
        } catch (Exception e2) {
            logger.warn("Failed to create a new Selector.", (Throwable) e2);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:3|4|(1:79)(2:6|(2:78|37)(3:8|9|(14:11|12|13|14|(2:(2:47|48)|17)(1:(4:54|55|56|57)(1:62))|(7:21|(2:23|24)|25|26|28|(3:30|31|(2:33|34)(1:36))(1:38)|37)|42|(1:46)|24|25|26|28|(0)(0)|37)))|63|(1:65)|66|67|68|(1:70)|71|12|13|14|(0)(0)|(7:21|(0)|25|26|28|(0)(0)|37)|42|(2:44|46)|24|25|26|28|(0)(0)|37) */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0100, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0101, code lost:
    
        handleLoopException(r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0002 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00d2  */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void run() {
        int calculateStrategy;
        long nextScheduledTaskDeadlineNanos;
        int i;
        boolean runAllTasks;
        loop0: while (true) {
            int i2 = 0;
            while (true) {
                try {
                    try {
                        calculateStrategy = this.selectStrategy.calculateStrategy(this.selectNowSupplier, hasTasks());
                    } catch (CancelledKeyException e) {
                        e = e;
                        if (logger.isDebugEnabled()) {
                        }
                        if (isShuttingDown()) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        handleLoopException(th);
                        if (isShuttingDown()) {
                        }
                    }
                    try {
                        if (calculateStrategy != -3) {
                            if (calculateStrategy == -2) {
                                continue;
                            } else if (calculateStrategy != -1) {
                                i2++;
                                this.cancelledKeys = 0;
                                this.needsToSelectAgain = false;
                                i = this.ioRatio;
                                if (i != 100) {
                                    if (calculateStrategy > 0) {
                                        try {
                                            processSelectedKeys();
                                        } catch (Throwable th2) {
                                            runAllTasks();
                                            throw th2;
                                        }
                                    }
                                    runAllTasks = runAllTasks();
                                } else if (calculateStrategy > 0) {
                                    long nanoTime = System.nanoTime();
                                    try {
                                        processSelectedKeys();
                                        runAllTasks = runAllTasks(((System.nanoTime() - nanoTime) * (100 - i)) / i);
                                    } catch (Throwable th3) {
                                        runAllTasks(((System.nanoTime() - nanoTime) * (100 - i)) / i);
                                        throw th3;
                                    }
                                } else {
                                    runAllTasks = runAllTasks(0L);
                                }
                                if (!runAllTasks && calculateStrategy <= 0) {
                                    if (unexpectedSelectorWakeup(i2)) {
                                        i2 = 0;
                                    }
                                    if (isShuttingDown()) {
                                        continue;
                                    } else {
                                        closeAll();
                                        if (confirmShutdown()) {
                                            return;
                                        }
                                    }
                                }
                                if (i2 > 3 && logger.isDebugEnabled()) {
                                    logger.debug("Selector.select() returned prematurely {} times in a row for Selector {}.", Integer.valueOf(i2 - 1), this.selector);
                                }
                                i2 = 0;
                                if (isShuttingDown()) {
                                }
                            }
                        }
                        if (!hasTasks()) {
                            calculateStrategy = select(nextScheduledTaskDeadlineNanos);
                        }
                        this.nextWakeupNanos.lazySet(-1L);
                        i2++;
                        this.cancelledKeys = 0;
                        this.needsToSelectAgain = false;
                        i = this.ioRatio;
                        if (i != 100) {
                        }
                        if (!runAllTasks) {
                            if (unexpectedSelectorWakeup(i2)) {
                            }
                            if (isShuttingDown()) {
                            }
                        }
                        if (i2 > 3) {
                            logger.debug("Selector.select() returned prematurely {} times in a row for Selector {}.", Integer.valueOf(i2 - 1), this.selector);
                        }
                        i2 = 0;
                        if (isShuttingDown()) {
                        }
                    } catch (Throwable th4) {
                        this.nextWakeupNanos.lazySet(-1L);
                        throw th4;
                        break loop0;
                    }
                    nextScheduledTaskDeadlineNanos = nextScheduledTaskDeadlineNanos();
                    if (nextScheduledTaskDeadlineNanos == -1) {
                        nextScheduledTaskDeadlineNanos = Long.MAX_VALUE;
                    }
                    this.nextWakeupNanos.set(nextScheduledTaskDeadlineNanos);
                } catch (IOException e2) {
                    rebuildSelector0();
                    try {
                        handleLoopException(e2);
                    } catch (CancelledKeyException e3) {
                        e = e3;
                        i2 = 0;
                        if (logger.isDebugEnabled()) {
                            logger.debug(CancelledKeyException.class.getSimpleName() + " raised by a Selector {} - JDK bug?", this.selector, e);
                        }
                        if (isShuttingDown()) {
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        i2 = 0;
                        handleLoopException(th);
                        if (isShuttingDown()) {
                        }
                    }
                }
            }
        }
    }

    private boolean unexpectedSelectorWakeup(int i) {
        if (Thread.interrupted()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Selector.select() returned prematurely because Thread.currentThread().interrupt() was called. Use NioEventLoop.shutdownGracefully() to shutdown the NioEventLoop.");
            }
            return true;
        }
        int i2 = SELECTOR_AUTO_REBUILD_THRESHOLD;
        if (i2 <= 0 || i < i2) {
            return false;
        }
        logger.warn("Selector.select() returned prematurely {} times in a row; rebuilding Selector {}.", Integer.valueOf(i), this.selector);
        rebuildSelector();
        return true;
    }

    private static void handleLoopException(Throwable th) {
        logger.warn("Unexpected exception in the selector loop.", th);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException unused) {
        }
    }

    private void processSelectedKeys() {
        if (this.selectedKeys != null) {
            processSelectedKeysOptimized();
        } else {
            processSelectedKeysPlain(this.selector.selectedKeys());
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    protected void cleanup() {
        try {
            this.selector.close();
        } catch (IOException e) {
            logger.warn("Failed to close a selector.", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel(SelectionKey selectionKey) {
        selectionKey.cancel();
        this.cancelledKeys++;
        if (this.cancelledKeys >= 256) {
            this.cancelledKeys = 0;
            this.needsToSelectAgain = true;
        }
    }

    private void processSelectedKeysPlain(Set<SelectionKey> set) {
        if (set.isEmpty()) {
            return;
        }
        Iterator<SelectionKey> it = set.iterator();
        while (true) {
            SelectionKey next = it.next();
            Object attachment = next.attachment();
            it.remove();
            if (attachment instanceof AbstractNioChannel) {
                processSelectedKey(next, (AbstractNioChannel) attachment);
            } else {
                processSelectedKey(next, (NioTask<SelectableChannel>) attachment);
            }
            if (!it.hasNext()) {
                return;
            }
            if (this.needsToSelectAgain) {
                selectAgain();
                Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
                if (selectedKeys.isEmpty()) {
                    return;
                } else {
                    it = selectedKeys.iterator();
                }
            }
        }
    }

    private void processSelectedKeysOptimized() {
        int i = 0;
        while (i < this.selectedKeys.size) {
            SelectionKey selectionKey = this.selectedKeys.keys[i];
            this.selectedKeys.keys[i] = null;
            Object attachment = selectionKey.attachment();
            if (attachment instanceof AbstractNioChannel) {
                processSelectedKey(selectionKey, (AbstractNioChannel) attachment);
            } else {
                processSelectedKey(selectionKey, (NioTask<SelectableChannel>) attachment);
            }
            if (this.needsToSelectAgain) {
                this.selectedKeys.reset(i + 1);
                selectAgain();
                i = -1;
            }
            i++;
        }
    }

    private void processSelectedKey(SelectionKey selectionKey, AbstractNioChannel abstractNioChannel) {
        AbstractNioChannel.NioUnsafe unsafe = abstractNioChannel.unsafe();
        if (!selectionKey.isValid()) {
            try {
                if (abstractNioChannel.eventLoop() == this) {
                    unsafe.close(unsafe.voidPromise());
                    return;
                }
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        try {
            int readyOps = selectionKey.readyOps();
            if ((readyOps & 8) != 0) {
                selectionKey.interestOps(selectionKey.interestOps() & (-9));
                unsafe.finishConnect();
            }
            if ((readyOps & 4) != 0) {
                abstractNioChannel.unsafe().forceFlush();
            }
            if ((readyOps & 17) != 0 || readyOps == 0) {
                unsafe.read();
            }
        } catch (CancelledKeyException unused2) {
            unsafe.close(unsafe.voidPromise());
        }
    }

    private static void processSelectedKey(SelectionKey selectionKey, NioTask<SelectableChannel> nioTask) {
        try {
            try {
                nioTask.channelReady(selectionKey.channel(), selectionKey);
                if (!selectionKey.isValid()) {
                    invokeChannelUnregistered(nioTask, selectionKey, null);
                }
            } catch (Exception e) {
                selectionKey.cancel();
                invokeChannelUnregistered(nioTask, selectionKey, e);
            }
        } catch (Throwable th) {
            selectionKey.cancel();
            invokeChannelUnregistered(nioTask, selectionKey, null);
            throw th;
        }
    }

    private void closeAll() {
        selectAgain();
        Set<SelectionKey> keys = this.selector.keys();
        ArrayList<AbstractNioChannel> arrayList = new ArrayList(keys.size());
        for (SelectionKey selectionKey : keys) {
            Object attachment = selectionKey.attachment();
            if (attachment instanceof AbstractNioChannel) {
                arrayList.add((AbstractNioChannel) attachment);
            } else {
                selectionKey.cancel();
                invokeChannelUnregistered((NioTask) attachment, selectionKey, null);
            }
        }
        for (AbstractNioChannel abstractNioChannel : arrayList) {
            abstractNioChannel.unsafe().close(abstractNioChannel.unsafe().voidPromise());
        }
    }

    private static void invokeChannelUnregistered(NioTask<SelectableChannel> nioTask, SelectionKey selectionKey, Throwable th) {
        try {
            nioTask.channelUnregistered(selectionKey.channel(), th);
        } catch (Exception e) {
            logger.warn("Unexpected exception while running NioTask.channelUnregistered()", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    public void wakeup(boolean z) {
        if (z || this.nextWakeupNanos.getAndSet(-1L) == -1) {
            return;
        }
        this.selector.wakeup();
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
    public Selector unwrappedSelector() {
        return this.unwrappedSelector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int selectNow() throws IOException {
        return this.selector.selectNow();
    }

    private int select(long j) throws IOException {
        if (j == Long.MAX_VALUE) {
            return this.selector.select();
        }
        long deadlineToDelayNanos = deadlineToDelayNanos(j + 995000) / 1000000;
        return deadlineToDelayNanos <= 0 ? this.selector.selectNow() : this.selector.select(deadlineToDelayNanos);
    }

    private void selectAgain() {
        this.needsToSelectAgain = false;
        try {
            this.selector.selectNow();
        } catch (Throwable th) {
            logger.warn("Failed to update SelectionKeys.", th);
        }
    }
}
