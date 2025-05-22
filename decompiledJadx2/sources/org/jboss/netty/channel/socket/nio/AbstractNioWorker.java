package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.socket.Worker;
import org.jboss.netty.channel.socket.nio.SocketSendBufferPool;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.ThreadRenamingRunnable;
import org.jboss.netty.util.internal.DeadLockProofWorker;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
abstract class AbstractNioWorker implements Worker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int CLEANUP_INTERVAL = 256;
    private final boolean allowShutdownOnIdle;
    private volatile int cancelledKeys;
    private final Queue<Runnable> eventQueue;
    private final Executor executor;

    /* renamed from: id */
    final int f10021id;
    private final Queue<Runnable> registerTaskQueue;
    volatile Selector selector;
    private final ReadWriteLock selectorGuard;
    protected final SocketSendBufferPool sendBufferPool;
    private final Object startStopLock;
    private boolean started;
    protected volatile Thread thread;
    protected final AtomicBoolean wakenUp;
    protected final Queue<Runnable> writeTaskQueue;
    private static final AtomicInteger nextId = new AtomicInteger();
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractNioWorker.class);
    private static final int CONSTRAINT_LEVEL = NioProviderMetadata.CONSTRAINT_LEVEL;

    protected abstract Runnable createRegisterTask(AbstractNioChannel<?> abstractNioChannel, ChannelFuture channelFuture);

    protected abstract boolean read(SelectionKey selectionKey);

    protected abstract boolean scheduleWriteIfNecessary(AbstractNioChannel<?> abstractNioChannel);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractNioWorker(Executor executor) {
        this(executor, true);
    }

    public AbstractNioWorker(Executor executor, boolean z) {
        this.f10021id = nextId.incrementAndGet();
        this.wakenUp = new AtomicBoolean();
        this.selectorGuard = new ReentrantReadWriteLock();
        this.startStopLock = new Object();
        this.registerTaskQueue = QueueFactory.createQueue(Runnable.class);
        this.writeTaskQueue = QueueFactory.createQueue(Runnable.class);
        this.eventQueue = QueueFactory.createQueue(Runnable.class);
        this.sendBufferPool = new SocketSendBufferPool();
        this.executor = executor;
        this.allowShutdownOnIdle = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void register(AbstractNioChannel<?> abstractNioChannel, ChannelFuture channelFuture) {
        Runnable createRegisterTask = createRegisterTask(abstractNioChannel, channelFuture);
        Selector start = start();
        this.registerTaskQueue.offer(createRegisterTask);
        if (this.wakenUp.compareAndSet(false, true)) {
            start.wakeup();
        }
    }

    private Selector start() {
        synchronized (this.startStopLock) {
            if (!this.started) {
                try {
                    this.selector = Selector.open();
                    try {
                        DeadLockProofWorker.start(this.executor, new ThreadRenamingRunnable(this, "New I/O  worker #" + this.f10021id));
                    } finally {
                    }
                } catch (Throwable th) {
                    throw new ChannelException("Failed to create a selector.", th);
                }
            }
            this.started = true;
        }
        return this.selector;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.thread = Thread.currentThread();
        Selector selector = this.selector;
        loop0: while (true) {
            boolean z = false;
            while (true) {
                this.wakenUp.set(false);
                if (CONSTRAINT_LEVEL != 0) {
                    this.selectorGuard.writeLock().lock();
                    this.selectorGuard.writeLock().unlock();
                }
                try {
                    SelectorUtil.select(selector);
                    if (this.wakenUp.get()) {
                        selector.wakeup();
                    }
                    this.cancelledKeys = 0;
                    processRegisterTaskQueue();
                    processEventQueue();
                    processWriteTaskQueue();
                    processSelectedKeys(selector.selectedKeys());
                } catch (Throwable th) {
                    logger.warn("Unexpected exception in the selector loop.", th);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                    }
                }
                if (!selector.keys().isEmpty()) {
                    break;
                }
                if (!z && (!(this.executor instanceof ExecutorService) || !((ExecutorService) this.executor).isShutdown())) {
                    if (this.allowShutdownOnIdle) {
                        z = true;
                    }
                }
                synchronized (this.startStopLock) {
                    try {
                        if (!this.registerTaskQueue.isEmpty() || !selector.keys().isEmpty()) {
                            try {
                            } catch (Throwable th2) {
                                th = th2;
                                z = false;
                                throw th;
                                break loop0;
                            }
                        } else {
                            this.started = false;
                            try {
                                try {
                                    selector.close();
                                    break loop0;
                                } catch (IOException e) {
                                    logger.warn("Failed to close a selector.", e);
                                    return;
                                }
                            } finally {
                                this.selector = null;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                        break loop0;
                        break loop0;
                    }
                }
                break;
            }
        }
    }

    @Override // org.jboss.netty.channel.socket.Worker
    public void executeInIoThread(Runnable runnable) {
        executeInIoThread(runnable, false);
    }

    public void executeInIoThread(Runnable runnable, boolean z) {
        Selector selector;
        if (!z && Thread.currentThread() == this.thread) {
            runnable.run();
            return;
        }
        start();
        if (!this.eventQueue.offer(runnable) || (selector = this.selector) == null) {
            return;
        }
        selector.wakeup();
    }

    private void processRegisterTaskQueue() throws IOException {
        while (true) {
            Runnable poll = this.registerTaskQueue.poll();
            if (poll == null) {
                return;
            }
            poll.run();
            cleanUpCancelledKeys();
        }
    }

    private void processWriteTaskQueue() throws IOException {
        while (true) {
            Runnable poll = this.writeTaskQueue.poll();
            if (poll == null) {
                return;
            }
            poll.run();
            cleanUpCancelledKeys();
        }
    }

    private void processEventQueue() throws IOException {
        while (true) {
            Runnable poll = this.eventQueue.poll();
            if (poll == null) {
                return;
            }
            poll.run();
            cleanUpCancelledKeys();
        }
    }

    private void processSelectedKeys(Set<SelectionKey> set) throws IOException {
        int readyOps;
        Iterator<SelectionKey> it = set.iterator();
        while (it.hasNext()) {
            SelectionKey next = it.next();
            it.remove();
            try {
                readyOps = next.readyOps();
            } catch (CancelledKeyException unused) {
                close(next);
            }
            if (((readyOps & 1) == 0 && readyOps != 0) || read(next)) {
                if ((readyOps & 4) != 0) {
                    writeFromSelectorLoop(next);
                }
                if (cleanUpCancelledKeys()) {
                    return;
                }
            }
        }
    }

    private boolean cleanUpCancelledKeys() throws IOException {
        if (this.cancelledKeys < 256) {
            return false;
        }
        this.cancelledKeys = 0;
        this.selector.selectNow();
        return true;
    }

    private void close(SelectionKey selectionKey) {
        AbstractNioChannel<?> abstractNioChannel = (AbstractNioChannel) selectionKey.attachment();
        close(abstractNioChannel, Channels.succeededFuture(abstractNioChannel));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeFromUserCode(AbstractNioChannel<?> abstractNioChannel) {
        if (!abstractNioChannel.isConnected()) {
            cleanUpWriteBuffer(abstractNioChannel);
        } else {
            if (scheduleWriteIfNecessary(abstractNioChannel) || abstractNioChannel.writeSuspended || abstractNioChannel.inWriteNowLoop) {
                return;
            }
            write0(abstractNioChannel);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeFromTaskLoop(AbstractNioChannel<?> abstractNioChannel) {
        if (abstractNioChannel.writeSuspended) {
            return;
        }
        write0(abstractNioChannel);
    }

    void writeFromSelectorLoop(SelectionKey selectionKey) {
        AbstractNioChannel<?> abstractNioChannel = (AbstractNioChannel) selectionKey.attachment();
        abstractNioChannel.writeSuspended = false;
        write0(abstractNioChannel);
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a4, code lost:
    
        r14 = true;
        r4 = r15;
        r6 = false;
        r17 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d8 A[Catch: all -> 0x00fd, TRY_ENTER, TryCatch #7 {, blocks: (B:5:0x001d, B:6:0x0024, B:8:0x0028, B:87:0x0032, B:56:0x00a9, B:59:0x00af, B:61:0x00b5, B:62:0x00b8, B:10:0x003a, B:11:0x0047, B:36:0x00d8, B:37:0x00db, B:39:0x00e4, B:40:0x00eb, B:42:0x00ef, B:45:0x00e8, B:88:0x0045), top: B:4:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e4 A[Catch: all -> 0x00fd, TryCatch #7 {, blocks: (B:5:0x001d, B:6:0x0024, B:8:0x0028, B:87:0x0032, B:56:0x00a9, B:59:0x00af, B:61:0x00b5, B:62:0x00b8, B:10:0x003a, B:11:0x0047, B:36:0x00d8, B:37:0x00db, B:39:0x00e4, B:40:0x00eb, B:42:0x00ef, B:45:0x00e8, B:88:0x0045), top: B:4:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ef A[Catch: all -> 0x00fd, TryCatch #7 {, blocks: (B:5:0x001d, B:6:0x0024, B:8:0x0028, B:87:0x0032, B:56:0x00a9, B:59:0x00af, B:61:0x00b5, B:62:0x00b8, B:10:0x003a, B:11:0x0047, B:36:0x00d8, B:37:0x00db, B:39:0x00e4, B:40:0x00eb, B:42:0x00ef, B:45:0x00e8, B:88:0x0045), top: B:4:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e8 A[Catch: all -> 0x00fd, TryCatch #7 {, blocks: (B:5:0x001d, B:6:0x0024, B:8:0x0028, B:87:0x0032, B:56:0x00a9, B:59:0x00af, B:61:0x00b5, B:62:0x00b8, B:10:0x003a, B:11:0x0047, B:36:0x00d8, B:37:0x00db, B:39:0x00e4, B:40:0x00eb, B:42:0x00ef, B:45:0x00e8, B:88:0x0045), top: B:4:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void write0(AbstractNioChannel<?> abstractNioChannel) {
        SocketSendBufferPool.SendBuffer sendBuffer;
        boolean z;
        boolean z2;
        long j;
        boolean z3;
        boolean z4;
        Throwable th;
        boolean isIoThread = isIoThread(abstractNioChannel);
        SocketSendBufferPool socketSendBufferPool = this.sendBufferPool;
        WritableByteChannel writableByteChannel = abstractNioChannel.channel;
        Queue<MessageEvent> queue = abstractNioChannel.writeBufferQueue;
        int writeSpinCount = abstractNioChannel.getConfig().getWriteSpinCount();
        synchronized (abstractNioChannel.writeLock) {
            boolean z5 = true;
            abstractNioChannel.inWriteNowLoop = true;
            boolean z6 = false;
            boolean z7 = true;
            boolean z8 = false;
            long j2 = 0;
            while (true) {
                MessageEvent messageEvent = abstractNioChannel.currentWriteEvent;
                if (messageEvent == null) {
                    messageEvent = queue.poll();
                    abstractNioChannel.currentWriteEvent = messageEvent;
                    if (messageEvent == null) {
                        abstractNioChannel.writeSuspended = z6;
                        z = z5;
                        z2 = z6;
                        j = j2;
                        break;
                    }
                    sendBuffer = socketSendBufferPool.acquire(messageEvent.getMessage());
                    abstractNioChannel.currentWriteBuffer = sendBuffer;
                } else {
                    sendBuffer = abstractNioChannel.currentWriteBuffer;
                }
                ChannelFuture future = messageEvent.getFuture();
                int i = writeSpinCount;
                long j3 = 0;
                while (true) {
                    if (i <= 0) {
                        break;
                    }
                    try {
                        try {
                            j3 = sendBuffer.transferTo(writableByteChannel);
                            if (j3 != 0) {
                                j2 += j3;
                                break;
                            } else if (sendBuffer.finished()) {
                                break;
                            } else {
                                i--;
                            }
                        } catch (AsynchronousCloseException unused) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        z4 = true;
                        z3 = false;
                        if (sendBuffer != null) {
                            sendBuffer.release();
                        }
                        abstractNioChannel.currentWriteEvent = null;
                        abstractNioChannel.currentWriteBuffer = null;
                        future.setFailure(th);
                        if (isIoThread) {
                            Channels.fireExceptionCaught(abstractNioChannel, th);
                        } else {
                            Channels.fireExceptionCaughtLater(abstractNioChannel, th);
                        }
                        if (th instanceof IOException) {
                            close(abstractNioChannel, Channels.succeededFuture(abstractNioChannel));
                            z7 = z3;
                        }
                        z5 = z4;
                        z6 = z3;
                    }
                }
                long j4 = j3;
                if (sendBuffer.finished()) {
                    sendBuffer.release();
                    abstractNioChannel.currentWriteEvent = null;
                    abstractNioChannel.currentWriteBuffer = null;
                    try {
                        future.setSuccess();
                        z4 = true;
                        z3 = false;
                    } catch (Throwable th3) {
                        th = th3;
                        sendBuffer = null;
                        z4 = true;
                        z3 = false;
                        if (sendBuffer != null) {
                        }
                        abstractNioChannel.currentWriteEvent = null;
                        abstractNioChannel.currentWriteBuffer = null;
                        future.setFailure(th);
                        if (isIoThread) {
                        }
                        if (th instanceof IOException) {
                        }
                        z5 = z4;
                        z6 = z3;
                    }
                    z5 = z4;
                    z6 = z3;
                } else {
                    z4 = true;
                    try {
                        abstractNioChannel.writeSuspended = true;
                        if (j4 <= 0) {
                            break;
                        }
                        try {
                            future.setProgress(j4, sendBuffer.writtenBytes(), sendBuffer.totalBytes());
                            break;
                        } catch (AsynchronousCloseException unused2) {
                            z8 = true;
                            z3 = false;
                        } catch (Throwable th4) {
                            th = th4;
                            z8 = true;
                            z3 = false;
                            if (sendBuffer != null) {
                            }
                            abstractNioChannel.currentWriteEvent = null;
                            abstractNioChannel.currentWriteBuffer = null;
                            future.setFailure(th);
                            if (isIoThread) {
                            }
                            if (th instanceof IOException) {
                            }
                            z5 = z4;
                            z6 = z3;
                        }
                    } catch (AsynchronousCloseException unused3) {
                        z3 = false;
                        z8 = true;
                    } catch (Throwable th5) {
                        th = th5;
                        z3 = false;
                        z8 = true;
                    }
                }
            }
            abstractNioChannel.inWriteNowLoop = z2;
            if (z7) {
                if (z8) {
                    setOpWrite(abstractNioChannel);
                } else if (z) {
                    clearOpWrite(abstractNioChannel);
                }
            }
        }
        if (isIoThread) {
            Channels.fireWriteComplete(abstractNioChannel, j);
        } else {
            Channels.fireWriteCompleteLater(abstractNioChannel, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isIoThread(AbstractNioChannel<?> abstractNioChannel) {
        return Thread.currentThread() == abstractNioChannel.worker.thread;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOpWrite(AbstractNioChannel<?> abstractNioChannel) {
        SelectionKey keyFor = abstractNioChannel.channel.keyFor(this.selector);
        if (keyFor == null) {
            return;
        }
        if (!keyFor.isValid()) {
            close(keyFor);
            return;
        }
        synchronized (abstractNioChannel.interestOpsLock) {
            int rawInterestOps = abstractNioChannel.getRawInterestOps();
            if ((rawInterestOps & 4) == 0) {
                int i = rawInterestOps | 4;
                keyFor.interestOps(i);
                abstractNioChannel.setRawInterestOpsNow(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearOpWrite(AbstractNioChannel<?> abstractNioChannel) {
        SelectionKey keyFor = abstractNioChannel.channel.keyFor(this.selector);
        if (keyFor == null) {
            return;
        }
        if (!keyFor.isValid()) {
            close(keyFor);
            return;
        }
        synchronized (abstractNioChannel.interestOpsLock) {
            int rawInterestOps = abstractNioChannel.getRawInterestOps();
            if ((rawInterestOps & 4) != 0) {
                int i = rawInterestOps & (-5);
                keyFor.interestOps(i);
                abstractNioChannel.setRawInterestOpsNow(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close(AbstractNioChannel<?> abstractNioChannel, ChannelFuture channelFuture) {
        boolean isConnected = abstractNioChannel.isConnected();
        boolean isBound = abstractNioChannel.isBound();
        boolean isIoThread = isIoThread(abstractNioChannel);
        try {
            abstractNioChannel.channel.close();
            this.cancelledKeys++;
            if (abstractNioChannel.setClosed()) {
                channelFuture.setSuccess();
                if (isConnected) {
                    if (isIoThread) {
                        Channels.fireChannelDisconnected(abstractNioChannel);
                    } else {
                        Channels.fireChannelDisconnectedLater(abstractNioChannel);
                    }
                }
                if (isBound) {
                    if (isIoThread) {
                        Channels.fireChannelUnbound(abstractNioChannel);
                    } else {
                        Channels.fireChannelUnboundLater(abstractNioChannel);
                    }
                }
                cleanUpWriteBuffer(abstractNioChannel);
                if (isIoThread) {
                    Channels.fireChannelClosed(abstractNioChannel);
                    return;
                } else {
                    Channels.fireChannelClosedLater(abstractNioChannel);
                    return;
                }
            }
            channelFuture.setSuccess();
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            if (isIoThread) {
                Channels.fireExceptionCaught(abstractNioChannel, th);
            } else {
                Channels.fireExceptionCaughtLater(abstractNioChannel, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cleanUpWriteBuffer(AbstractNioChannel<?> abstractNioChannel) {
        Throwable th;
        boolean z;
        Throwable closedChannelException;
        Throwable closedChannelException2;
        synchronized (abstractNioChannel.writeLock) {
            MessageEvent messageEvent = abstractNioChannel.currentWriteEvent;
            th = null;
            if (messageEvent != null) {
                if (abstractNioChannel.isOpen()) {
                    closedChannelException2 = new NotYetConnectedException();
                } else {
                    closedChannelException2 = new ClosedChannelException();
                }
                ChannelFuture future = messageEvent.getFuture();
                abstractNioChannel.currentWriteBuffer.release();
                abstractNioChannel.currentWriteBuffer = null;
                abstractNioChannel.currentWriteEvent = null;
                future.setFailure(closedChannelException2);
                z = true;
                th = closedChannelException2;
            } else {
                z = false;
            }
            Queue<MessageEvent> queue = abstractNioChannel.writeBufferQueue;
            while (true) {
                MessageEvent poll = queue.poll();
                if (poll == null) {
                    break;
                }
                if (th == null) {
                    if (abstractNioChannel.isOpen()) {
                        closedChannelException = new NotYetConnectedException();
                    } else {
                        closedChannelException = new ClosedChannelException();
                    }
                    th = closedChannelException;
                    z = true;
                }
                poll.getFuture().setFailure(th);
            }
        }
        if (z) {
            if (isIoThread(abstractNioChannel)) {
                Channels.fireExceptionCaught(abstractNioChannel, th);
            } else {
                Channels.fireExceptionCaughtLater(abstractNioChannel, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInterestOps(AbstractNioChannel<?> abstractNioChannel, ChannelFuture channelFuture, int i) {
        boolean isIoThread = isIoThread(abstractNioChannel);
        try {
            synchronized (abstractNioChannel.interestOpsLock) {
                Selector selector = this.selector;
                SelectionKey keyFor = abstractNioChannel.channel.keyFor(selector);
                int rawInterestOps = (i & (-5)) | (abstractNioChannel.getRawInterestOps() & 4);
                boolean z = true;
                if (keyFor != null && selector != null) {
                    int i2 = CONSTRAINT_LEVEL;
                    if (i2 == 0) {
                        if (abstractNioChannel.getRawInterestOps() != rawInterestOps) {
                            keyFor.interestOps(rawInterestOps);
                            if (Thread.currentThread() != this.thread && this.wakenUp.compareAndSet(false, true)) {
                                selector.wakeup();
                            }
                        }
                        z = false;
                    } else {
                        if (i2 != 1 && i2 != 2) {
                            throw new Error();
                        }
                        if (abstractNioChannel.getRawInterestOps() != rawInterestOps) {
                            if (Thread.currentThread() == this.thread) {
                                keyFor.interestOps(rawInterestOps);
                            } else {
                                this.selectorGuard.readLock().lock();
                                try {
                                    if (this.wakenUp.compareAndSet(false, true)) {
                                        selector.wakeup();
                                    }
                                    keyFor.interestOps(rawInterestOps);
                                    this.selectorGuard.readLock().unlock();
                                } catch (Throwable th) {
                                    this.selectorGuard.readLock().unlock();
                                    throw th;
                                }
                            }
                        }
                        z = false;
                    }
                    if (z) {
                        abstractNioChannel.setRawInterestOpsNow(rawInterestOps);
                    }
                    channelFuture.setSuccess();
                    if (z) {
                        if (isIoThread) {
                            Channels.fireChannelInterestChanged(abstractNioChannel);
                            return;
                        } else {
                            Channels.fireChannelInterestChangedLater(abstractNioChannel);
                            return;
                        }
                    }
                    return;
                }
                boolean z2 = abstractNioChannel.getRawInterestOps() != rawInterestOps;
                abstractNioChannel.setRawInterestOpsNow(rawInterestOps);
                channelFuture.setSuccess();
                if (z2) {
                    if (isIoThread) {
                        Channels.fireChannelInterestChanged(abstractNioChannel);
                    } else {
                        Channels.fireChannelInterestChangedLater(abstractNioChannel);
                    }
                }
            }
        } catch (CancelledKeyException unused) {
            ClosedChannelException closedChannelException = new ClosedChannelException();
            channelFuture.setFailure(closedChannelException);
            if (isIoThread) {
                Channels.fireExceptionCaught(abstractNioChannel, closedChannelException);
            } else {
                Channels.fireExceptionCaughtLater(abstractNioChannel, closedChannelException);
            }
        } catch (Throwable th2) {
            channelFuture.setFailure(th2);
            if (isIoThread) {
                Channels.fireExceptionCaught(abstractNioChannel, th2);
            } else {
                Channels.fireExceptionCaughtLater(abstractNioChannel, th2);
            }
        }
    }
}
