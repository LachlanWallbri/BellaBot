package org.jboss.netty.channel.group;

import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.DeadLockProofWorker;

/* loaded from: classes7.dex */
public class DefaultChannelGroupFuture implements ChannelGroupFuture {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultChannelGroupFuture.class);
    private final ChannelFutureListener childListener = new ChannelFutureListener() { // from class: org.jboss.netty.channel.group.DefaultChannelGroupFuture.1
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // org.jboss.netty.channel.ChannelFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            boolean z;
            boolean isSuccess = channelFuture.isSuccess();
            synchronized (DefaultChannelGroupFuture.this) {
                z = true;
                if (isSuccess) {
                    DefaultChannelGroupFuture.this.successCount++;
                } else {
                    DefaultChannelGroupFuture.this.failureCount++;
                }
                if (DefaultChannelGroupFuture.this.successCount + DefaultChannelGroupFuture.this.failureCount != DefaultChannelGroupFuture.this.futures.size()) {
                    z = false;
                }
            }
            if (z) {
                DefaultChannelGroupFuture.this.setDone();
            }
        }
    };
    private boolean done;
    int failureCount;
    private ChannelGroupFutureListener firstListener;
    final Map<Integer, ChannelFuture> futures;
    private final ChannelGroup group;
    private List<ChannelGroupFutureListener> otherListeners;
    int successCount;
    private int waiters;

    public DefaultChannelGroupFuture(ChannelGroup channelGroup, Collection<ChannelFuture> collection) {
        if (channelGroup == null) {
            throw new NullPointerException(MapElement.Key.GROUP);
        }
        if (collection == null) {
            throw new NullPointerException("futures");
        }
        this.group = channelGroup;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ChannelFuture channelFuture : collection) {
            linkedHashMap.put(channelFuture.getChannel().getId(), channelFuture);
        }
        this.futures = Collections.unmodifiableMap(linkedHashMap);
        Iterator<ChannelFuture> it = this.futures.values().iterator();
        while (it.hasNext()) {
            it.next().addListener(this.childListener);
        }
        if (this.futures.isEmpty()) {
            setDone();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultChannelGroupFuture(ChannelGroup channelGroup, Map<Integer, ChannelFuture> map) {
        this.group = channelGroup;
        this.futures = Collections.unmodifiableMap(map);
        Iterator<ChannelFuture> it = this.futures.values().iterator();
        while (it.hasNext()) {
            it.next().addListener(this.childListener);
        }
        if (this.futures.isEmpty()) {
            setDone();
        }
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public ChannelGroup getGroup() {
        return this.group;
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public ChannelFuture find(Integer num) {
        return this.futures.get(num);
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public ChannelFuture find(Channel channel) {
        return this.futures.get(channel.getId());
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture, java.lang.Iterable
    public Iterator<ChannelFuture> iterator() {
        return this.futures.values().iterator();
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public synchronized boolean isDone() {
        return this.done;
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public synchronized boolean isCompleteSuccess() {
        return this.successCount == this.futures.size();
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public synchronized boolean isPartialSuccess() {
        boolean z;
        if (this.successCount != 0) {
            z = this.successCount != this.futures.size();
        }
        return z;
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public synchronized boolean isPartialFailure() {
        boolean z;
        if (this.failureCount != 0) {
            z = this.failureCount != this.futures.size();
        }
        return z;
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public synchronized boolean isCompleteFailure() {
        boolean z;
        int size = this.futures.size();
        if (size != 0) {
            z = this.failureCount == size;
        }
        return z;
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public void addListener(ChannelGroupFutureListener channelGroupFutureListener) {
        if (channelGroupFutureListener == null) {
            throw new NullPointerException("listener");
        }
        boolean z = false;
        synchronized (this) {
            if (this.done) {
                z = true;
            } else if (this.firstListener == null) {
                this.firstListener = channelGroupFutureListener;
            } else {
                if (this.otherListeners == null) {
                    this.otherListeners = new ArrayList(1);
                }
                this.otherListeners.add(channelGroupFutureListener);
            }
        }
        if (z) {
            notifyListener(channelGroupFutureListener);
        }
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public void removeListener(ChannelGroupFutureListener channelGroupFutureListener) {
        if (channelGroupFutureListener == null) {
            throw new NullPointerException("listener");
        }
        synchronized (this) {
            if (!this.done) {
                if (channelGroupFutureListener == this.firstListener) {
                    if (this.otherListeners != null && !this.otherListeners.isEmpty()) {
                        this.firstListener = this.otherListeners.remove(0);
                    } else {
                        this.firstListener = null;
                    }
                } else if (this.otherListeners != null) {
                    this.otherListeners.remove(channelGroupFutureListener);
                }
            }
        }
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public ChannelGroupFuture await() throws InterruptedException {
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

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        return await0(timeUnit.toNanos(j), true);
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public boolean await(long j) throws InterruptedException {
        return await0(TimeUnit.MILLISECONDS.toNanos(j), true);
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public ChannelGroupFuture awaitUninterruptibly() {
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

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
    public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
        try {
            return await0(timeUnit.toNanos(j), false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    @Override // org.jboss.netty.channel.group.ChannelGroupFuture
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
        if (DeadLockProofWorker.PARENT.get() != null) {
            throw new IllegalStateException("await*() in I/O thread causes a dead lock or sudden performance drop. Use addListener() instead or call await*() from a different thread.");
        }
    }

    boolean setDone() {
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

    private void notifyListeners() {
        ChannelGroupFutureListener channelGroupFutureListener = this.firstListener;
        if (channelGroupFutureListener != null) {
            notifyListener(channelGroupFutureListener);
            this.firstListener = null;
            List<ChannelGroupFutureListener> list = this.otherListeners;
            if (list != null) {
                Iterator<ChannelGroupFutureListener> it = list.iterator();
                while (it.hasNext()) {
                    notifyListener(it.next());
                }
                this.otherListeners = null;
            }
        }
    }

    private void notifyListener(ChannelGroupFutureListener channelGroupFutureListener) {
        try {
            channelGroupFutureListener.operationComplete(this);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("An exception was thrown by " + ChannelFutureListener.class.getSimpleName() + ".", th);
            }
        }
    }
}
