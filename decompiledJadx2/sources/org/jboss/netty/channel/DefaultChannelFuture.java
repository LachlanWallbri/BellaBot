package org.jboss.netty.channel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.DeadLockProofWorker;

/* loaded from: classes7.dex */
public class DefaultChannelFuture implements ChannelFuture {
    private static boolean disabledDeadLockCheckerOnce;
    private final boolean cancellable;
    private Throwable cause;
    private final Channel channel;
    private boolean done;
    private ChannelFutureListener firstListener;
    private List<ChannelFutureListener> otherListeners;
    private List<ChannelFutureProgressListener> progressListeners;
    private int waiters;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultChannelFuture.class);
    private static final Throwable CANCELLED = new Throwable();
    private static volatile boolean useDeadLockChecker = true;

    public static boolean isUseDeadLockChecker() {
        return useDeadLockChecker;
    }

    public static void setUseDeadLockChecker(boolean z) {
        if (!z && !disabledDeadLockCheckerOnce) {
            disabledDeadLockCheckerOnce = true;
            if (logger.isDebugEnabled()) {
                logger.debug("The dead lock checker in " + DefaultChannelFuture.class.getSimpleName() + " has been disabled as requested at your own risk.");
            }
        }
        useDeadLockChecker = z;
    }

    public DefaultChannelFuture(Channel channel, boolean z) {
        this.channel = channel;
        this.cancellable = z;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public Channel getChannel() {
        return this.channel;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public synchronized boolean isDone() {
        return this.done;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public synchronized boolean isSuccess() {
        boolean z;
        if (this.done) {
            z = this.cause == null;
        }
        return z;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public synchronized Throwable getCause() {
        if (this.cause == CANCELLED) {
            return null;
        }
        return this.cause;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public synchronized boolean isCancelled() {
        return this.cause == CANCELLED;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public void addListener(ChannelFutureListener channelFutureListener) {
        if (channelFutureListener == null) {
            throw new NullPointerException("listener");
        }
        boolean z = false;
        synchronized (this) {
            if (this.done) {
                z = true;
            } else {
                if (this.firstListener == null) {
                    this.firstListener = channelFutureListener;
                } else {
                    if (this.otherListeners == null) {
                        this.otherListeners = new ArrayList(1);
                    }
                    this.otherListeners.add(channelFutureListener);
                }
                if (channelFutureListener instanceof ChannelFutureProgressListener) {
                    if (this.progressListeners == null) {
                        this.progressListeners = new ArrayList(1);
                    }
                    this.progressListeners.add((ChannelFutureProgressListener) channelFutureListener);
                }
            }
        }
        if (z) {
            notifyListener(channelFutureListener);
        }
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public void removeListener(ChannelFutureListener channelFutureListener) {
        if (channelFutureListener == null) {
            throw new NullPointerException("listener");
        }
        synchronized (this) {
            if (!this.done) {
                if (channelFutureListener == this.firstListener) {
                    if (this.otherListeners != null && !this.otherListeners.isEmpty()) {
                        this.firstListener = this.otherListeners.remove(0);
                    } else {
                        this.firstListener = null;
                    }
                } else if (this.otherListeners != null) {
                    this.otherListeners.remove(channelFutureListener);
                }
                if (channelFutureListener instanceof ChannelFutureProgressListener) {
                    this.progressListeners.remove(channelFutureListener);
                }
            }
        }
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture rethrowIfFailed() throws Exception {
        Throwable cause;
        if (!isDone() || (cause = getCause()) == null) {
            return this;
        }
        if (cause instanceof Exception) {
            throw ((Exception) cause);
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        throw new RuntimeException(cause);
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture sync() throws InterruptedException {
        await();
        rethrowIfFailed0();
        return this;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture syncUninterruptibly() {
        awaitUninterruptibly();
        rethrowIfFailed0();
        return this;
    }

    private void rethrowIfFailed0() {
        Throwable cause = getCause();
        if (cause == null) {
            return;
        }
        if (cause instanceof RuntimeException) {
            throw ((RuntimeException) cause);
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        throw new ChannelException(cause);
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture await() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            while (!this.done) {
                checkDeadLock();
                this.waiters++;
                try {
                    wait();
                    this.waiters--;
                } catch (Throwable th) {
                    this.waiters--;
                    throw th;
                }
            }
        }
        return this;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        return await0(timeUnit.toNanos(j), true);
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean await(long j) throws InterruptedException {
        return await0(TimeUnit.MILLISECONDS.toNanos(j), true);
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture awaitUninterruptibly() {
        boolean z;
        synchronized (this) {
            z = false;
            while (!this.done) {
                checkDeadLock();
                this.waiters++;
                try {
                    try {
                        wait();
                        this.waiters--;
                    } catch (Throwable th) {
                        this.waiters--;
                        throw th;
                    }
                } catch (InterruptedException unused) {
                    this.waiters--;
                    z = true;
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
        try {
            return await0(timeUnit.toNanos(j), false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean awaitUninterruptibly(long j) {
        try {
            return await0(TimeUnit.MILLISECONDS.toNanos(j), false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    private boolean await0(long j, boolean z) throws InterruptedException {
        if (z && Thread.interrupted()) {
            throw new InterruptedException();
        }
        long nanoTime = j <= 0 ? 0L : System.nanoTime();
        boolean z2 = false;
        try {
            synchronized (this) {
                if (this.done) {
                    return this.done;
                }
                if (j <= 0) {
                    return this.done;
                }
                checkDeadLock();
                this.waiters++;
                long j2 = j;
                do {
                    try {
                        try {
                            wait(j2 / 1000000, (int) (j2 % 1000000));
                        } catch (InterruptedException e) {
                            if (z) {
                                throw e;
                            }
                            z2 = true;
                        }
                        if (this.done) {
                            return true;
                        }
                        j2 = j - (System.nanoTime() - nanoTime);
                    } finally {
                        this.waiters--;
                    }
                } while (j2 > 0);
                boolean z3 = this.done;
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                return z3;
            }
        } finally {
            if (z2) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void checkDeadLock() {
        if (isUseDeadLockChecker() && DeadLockProofWorker.PARENT.get() != null) {
            throw new IllegalStateException("await*() in I/O thread causes a dead lock or sudden performance drop. Use addListener() instead or call await*() from a different thread.");
        }
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean setSuccess() {
        synchronized (this) {
            if (this.done) {
                return false;
            }
            this.done = true;
            if (this.waiters > 0) {
                notifyAll();
            }
            notifyListeners();
            return true;
        }
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean setFailure(Throwable th) {
        synchronized (this) {
            if (this.done) {
                return false;
            }
            this.cause = th;
            this.done = true;
            if (this.waiters > 0) {
                notifyAll();
            }
            notifyListeners();
            return true;
        }
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean cancel() {
        if (!this.cancellable) {
            return false;
        }
        synchronized (this) {
            if (this.done) {
                return false;
            }
            this.cause = CANCELLED;
            this.done = true;
            if (this.waiters > 0) {
                notifyAll();
            }
            notifyListeners();
            return true;
        }
    }

    private void notifyListeners() {
        ChannelFutureListener channelFutureListener = this.firstListener;
        if (channelFutureListener != null) {
            notifyListener(channelFutureListener);
            this.firstListener = null;
            List<ChannelFutureListener> list = this.otherListeners;
            if (list != null) {
                Iterator<ChannelFutureListener> it = list.iterator();
                while (it.hasNext()) {
                    notifyListener(it.next());
                }
                this.otherListeners = null;
            }
        }
    }

    private void notifyListener(ChannelFutureListener channelFutureListener) {
        try {
            channelFutureListener.operationComplete(this);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("An exception was thrown by " + ChannelFutureListener.class.getSimpleName() + ".", th);
            }
        }
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean setProgress(long j, long j2, long j3) {
        synchronized (this) {
            if (this.done) {
                return false;
            }
            List<ChannelFutureProgressListener> list = this.progressListeners;
            if (list != null && !list.isEmpty()) {
                for (ChannelFutureProgressListener channelFutureProgressListener : (ChannelFutureProgressListener[]) list.toArray(new ChannelFutureProgressListener[list.size()])) {
                    notifyProgressListener(channelFutureProgressListener, j, j2, j3);
                }
                return true;
            }
            return true;
        }
    }

    private void notifyProgressListener(ChannelFutureProgressListener channelFutureProgressListener, long j, long j2, long j3) {
        try {
            channelFutureProgressListener.operationProgressed(this, j, j2, j3);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("An exception was thrown by " + ChannelFutureProgressListener.class.getSimpleName() + ".", th);
            }
        }
    }
}
