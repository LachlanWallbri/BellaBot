package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.util.Queue;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.Worker;
import org.jboss.netty.channel.socket.oio.AbstractOioChannel;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
abstract class AbstractOioWorker<C extends AbstractOioChannel> implements Worker {
    protected final C channel;
    private volatile boolean done;
    private final Queue<Runnable> eventQueue = QueueFactory.createQueue(Runnable.class);
    protected volatile Thread thread;

    abstract boolean process() throws IOException;

    public AbstractOioWorker(C c) {
        this.channel = c;
        c.worker = this;
    }

    @Override // java.lang.Runnable
    public void run() {
        C c = this.channel;
        Thread currentThread = Thread.currentThread();
        c.workerThread = currentThread;
        this.thread = currentThread;
        while (this.channel.isOpen()) {
            synchronized (this.channel.interestOpsLock) {
                while (!this.channel.isReadable()) {
                    try {
                        this.channel.interestOpsLock.wait();
                    } catch (InterruptedException unused) {
                        if (!this.channel.isOpen()) {
                            break;
                        }
                    }
                }
            }
            try {
                boolean process = process();
                processEventQueue();
                if (!process) {
                    break;
                }
            } catch (Throwable th) {
                try {
                    if (!this.channel.isSocketClosed()) {
                        Channels.fireExceptionCaught(this.channel, th);
                    }
                } catch (Throwable unused2) {
                }
                processEventQueue();
            }
        }
        C c2 = this.channel;
        c2.workerThread = null;
        close(c2, Channels.succeededFuture(c2), true);
        this.done = true;
        processEventQueue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isIoThread(AbstractOioChannel abstractOioChannel) {
        return Thread.currentThread() == abstractOioChannel.workerThread;
    }

    @Override // org.jboss.netty.channel.socket.Worker
    public void executeInIoThread(Runnable runnable) {
        if (Thread.currentThread() == this.thread || this.done) {
            runnable.run();
        } else {
            this.eventQueue.offer(runnable);
        }
    }

    private void processEventQueue() {
        while (true) {
            Runnable poll = this.eventQueue.poll();
            if (poll == null) {
                return;
            } else {
                poll.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setInterestOps(AbstractOioChannel abstractOioChannel, ChannelFuture channelFuture, int i) {
        boolean isIoThread = isIoThread(abstractOioChannel);
        int interestOps = (i & (-5)) | (abstractOioChannel.getInterestOps() & 4);
        try {
            boolean z = false;
            if (abstractOioChannel.getInterestOps() != interestOps) {
                if ((interestOps & 1) != 0) {
                    abstractOioChannel.setInterestOpsNow(1);
                } else {
                    abstractOioChannel.setInterestOpsNow(0);
                }
                z = true;
            }
            channelFuture.setSuccess();
            if (z) {
                synchronized (abstractOioChannel.interestOpsLock) {
                    abstractOioChannel.setInterestOpsNow(interestOps);
                    Thread currentThread = Thread.currentThread();
                    Thread thread = abstractOioChannel.workerThread;
                    if (thread != null && currentThread != thread) {
                        thread.interrupt();
                    }
                }
                if (isIoThread) {
                    Channels.fireChannelInterestChanged(abstractOioChannel);
                } else {
                    Channels.fireChannelInterestChangedLater(abstractOioChannel);
                }
            }
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            if (isIoThread) {
                Channels.fireExceptionCaught(abstractOioChannel, th);
            } else {
                Channels.fireExceptionCaughtLater(abstractOioChannel, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void close(AbstractOioChannel abstractOioChannel, ChannelFuture channelFuture) {
        close(abstractOioChannel, channelFuture, isIoThread(abstractOioChannel));
    }

    private static void close(AbstractOioChannel abstractOioChannel, ChannelFuture channelFuture, boolean z) {
        boolean isConnected = abstractOioChannel.isConnected();
        boolean isBound = abstractOioChannel.isBound();
        try {
            abstractOioChannel.closeSocket();
            if (abstractOioChannel.setClosed()) {
                channelFuture.setSuccess();
                if (isConnected) {
                    Thread currentThread = Thread.currentThread();
                    Thread thread = abstractOioChannel.workerThread;
                    if (thread != null && currentThread != thread) {
                        thread.interrupt();
                    }
                    if (z) {
                        Channels.fireChannelDisconnected(abstractOioChannel);
                    } else {
                        Channels.fireChannelDisconnectedLater(abstractOioChannel);
                    }
                }
                if (isBound) {
                    if (z) {
                        Channels.fireChannelUnbound(abstractOioChannel);
                    } else {
                        Channels.fireChannelUnboundLater(abstractOioChannel);
                    }
                }
                if (z) {
                    Channels.fireChannelClosed(abstractOioChannel);
                    return;
                } else {
                    Channels.fireChannelClosedLater(abstractOioChannel);
                    return;
                }
            }
            channelFuture.setSuccess();
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            if (z) {
                Channels.fireExceptionCaught(abstractOioChannel, th);
            } else {
                Channels.fireExceptionCaughtLater(abstractOioChannel, th);
            }
        }
    }
}
