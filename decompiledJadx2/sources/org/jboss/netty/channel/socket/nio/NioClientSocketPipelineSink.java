package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.codec.language.Soundex;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.ThreadRenamingRunnable;
import org.jboss.netty.util.internal.DeadLockProofWorker;
import org.jboss.netty.util.internal.QueueFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class NioClientSocketPipelineSink extends AbstractNioChannelSink {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    final Executor bossExecutor;
    private final Boss[] bosses;
    private final WorkerPool<NioWorker> workerPool;
    private static final AtomicInteger nextId = new AtomicInteger();
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioClientSocketPipelineSink.class);

    /* renamed from: id */
    final int f10022id = nextId.incrementAndGet();
    private final AtomicInteger bossIndex = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioClientSocketPipelineSink(Executor executor, int i, WorkerPool<NioWorker> workerPool) {
        this.bossExecutor = executor;
        this.bosses = new Boss[i];
        int i2 = 0;
        while (true) {
            Boss[] bossArr = this.bosses;
            if (i2 < bossArr.length) {
                bossArr[i2] = new Boss(i2);
                i2++;
            } else {
                this.workerPool = workerPool;
                return;
            }
        }
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            NioClientSocketChannel nioClientSocketChannel = (NioClientSocketChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86942.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    nioClientSocketChannel.worker.close(nioClientSocketChannel, future);
                    return;
                }
                return;
            }
            if (i == 2) {
                if (value != null) {
                    bind(nioClientSocketChannel, future, (SocketAddress) value);
                    return;
                } else {
                    nioClientSocketChannel.worker.close(nioClientSocketChannel, future);
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                nioClientSocketChannel.worker.setInterestOps(nioClientSocketChannel, future, ((Integer) value).intValue());
                return;
            } else if (value != null) {
                connect(nioClientSocketChannel, future, (SocketAddress) value);
                return;
            } else {
                nioClientSocketChannel.worker.close(nioClientSocketChannel, future);
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            MessageEvent messageEvent = (MessageEvent) channelEvent;
            NioSocketChannel nioSocketChannel = (NioSocketChannel) messageEvent.getChannel();
            nioSocketChannel.writeBufferQueue.offer(messageEvent);
            nioSocketChannel.worker.writeFromUserCode(nioSocketChannel);
        }
    }

    /* renamed from: org.jboss.netty.channel.socket.nio.NioClientSocketPipelineSink$2 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86942 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.INTEREST_OPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void bind(NioClientSocketChannel nioClientSocketChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        try {
            ((SocketChannel) nioClientSocketChannel.channel).socket().bind(socketAddress);
            nioClientSocketChannel.boundManually = true;
            nioClientSocketChannel.setBound();
            channelFuture.setSuccess();
            Channels.fireChannelBound(nioClientSocketChannel, nioClientSocketChannel.getLocalAddress());
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            Channels.fireExceptionCaught(nioClientSocketChannel, th);
        }
    }

    private void connect(NioClientSocketChannel nioClientSocketChannel, final ChannelFuture channelFuture, SocketAddress socketAddress) {
        try {
            if (((SocketChannel) nioClientSocketChannel.channel).connect(socketAddress)) {
                nioClientSocketChannel.worker.register(nioClientSocketChannel, channelFuture);
            } else {
                nioClientSocketChannel.getCloseFuture().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.nio.NioClientSocketPipelineSink.1
                    @Override // org.jboss.netty.channel.ChannelFutureListener
                    public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                        if (channelFuture.isDone()) {
                            return;
                        }
                        channelFuture.setFailure(new ClosedChannelException());
                    }
                });
                channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                nioClientSocketChannel.connectFuture = channelFuture;
                nextBoss().register(nioClientSocketChannel);
            }
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            Channels.fireExceptionCaught(nioClientSocketChannel, th);
            nioClientSocketChannel.worker.close(nioClientSocketChannel, Channels.succeededFuture(nioClientSocketChannel));
        }
    }

    Boss nextBoss() {
        return this.bosses[Math.abs(this.bossIndex.getAndIncrement() % this.bosses.length)];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioWorker nextWorker() {
        return this.workerPool.nextWorker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class Boss implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        volatile Selector selector;
        private boolean started;
        private final int subId;
        private final AtomicBoolean wakenUp = new AtomicBoolean();
        private final Object startStopLock = new Object();
        private final Queue<Runnable> registerTaskQueue = QueueFactory.createQueue(Runnable.class);

        Boss(int i) {
            this.subId = i;
        }

        void register(NioClientSocketChannel nioClientSocketChannel) {
            Selector open;
            RegisterTask registerTask = new RegisterTask(this, nioClientSocketChannel);
            synchronized (this.startStopLock) {
                if (!this.started) {
                    try {
                        open = Selector.open();
                        this.selector = open;
                        try {
                            DeadLockProofWorker.start(NioClientSocketPipelineSink.this.bossExecutor, new ThreadRenamingRunnable(this, "New I/O client boss #" + NioClientSocketPipelineSink.this.f10022id + Soundex.SILENT_MARKER + this.subId));
                        } finally {
                        }
                    } catch (Throwable th) {
                        throw new ChannelException("Failed to create a selector.", th);
                    }
                } else {
                    open = this.selector;
                }
                this.started = true;
                this.registerTaskQueue.offer(registerTask);
            }
            if (this.wakenUp.compareAndSet(false, true)) {
                open.wakeup();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Selector selector = this.selector;
            long nanoTime = System.nanoTime();
            loop0: while (true) {
                boolean z = false;
                while (true) {
                    this.wakenUp.set(false);
                    try {
                        int select = selector.select(10L);
                        if (this.wakenUp.get()) {
                            selector.wakeup();
                        }
                        processRegisterTaskQueue();
                        if (select > 0) {
                            processSelectedKeys(selector.selectedKeys());
                        }
                        long nanoTime2 = System.nanoTime();
                        if (nanoTime2 - nanoTime >= 10000000) {
                            try {
                                processConnectTimeout(selector.keys(), nanoTime2);
                                nanoTime = nanoTime2;
                            } catch (Throwable th) {
                                th = th;
                                nanoTime = nanoTime2;
                                if (NioClientSocketPipelineSink.logger.isWarnEnabled()) {
                                    NioClientSocketPipelineSink.logger.warn("Unexpected exception in the selector loop.", th);
                                }
                                try {
                                    Thread.sleep(1000L);
                                } catch (InterruptedException unused) {
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (!selector.keys().isEmpty()) {
                        break;
                    }
                    if (!z && (!(NioClientSocketPipelineSink.this.bossExecutor instanceof ExecutorService) || !((ExecutorService) NioClientSocketPipelineSink.this.bossExecutor).isShutdown())) {
                        z = true;
                    }
                    synchronized (this.startStopLock) {
                        try {
                            if (!this.registerTaskQueue.isEmpty() || !selector.keys().isEmpty()) {
                                try {
                                } catch (Throwable th3) {
                                    th = th3;
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
                                    } finally {
                                        this.selector = null;
                                    }
                                } catch (IOException e) {
                                    if (!NioClientSocketPipelineSink.logger.isWarnEnabled()) {
                                        break loop0;
                                    }
                                    NioClientSocketPipelineSink.logger.warn("Failed to close a selector.", e);
                                    break loop0;
                                    return;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            throw th;
                            break loop0;
                            break loop0;
                        }
                    }
                    break;
                }
            }
        }

        private void processRegisterTaskQueue() {
            while (true) {
                Runnable poll = this.registerTaskQueue.poll();
                if (poll == null) {
                    return;
                } else {
                    poll.run();
                }
            }
        }

        private void processSelectedKeys(Set<SelectionKey> set) {
            Iterator<SelectionKey> it = set.iterator();
            while (it.hasNext()) {
                SelectionKey next = it.next();
                it.remove();
                if (!next.isValid()) {
                    close(next);
                } else if (next.isConnectable()) {
                    connect(next);
                }
            }
        }

        private void processConnectTimeout(Set<SelectionKey> set, long j) {
            ConnectException connectException = null;
            for (SelectionKey selectionKey : set) {
                if (selectionKey.isValid()) {
                    NioClientSocketChannel nioClientSocketChannel = (NioClientSocketChannel) selectionKey.attachment();
                    if (nioClientSocketChannel.connectDeadlineNanos > 0 && j >= nioClientSocketChannel.connectDeadlineNanos) {
                        if (connectException == null) {
                            connectException = new ConnectException("connection timed out");
                        }
                        nioClientSocketChannel.connectFuture.setFailure(connectException);
                        Channels.fireExceptionCaught(nioClientSocketChannel, connectException);
                        nioClientSocketChannel.worker.close(nioClientSocketChannel, Channels.succeededFuture(nioClientSocketChannel));
                    }
                }
            }
        }

        private void connect(SelectionKey selectionKey) {
            NioClientSocketChannel nioClientSocketChannel = (NioClientSocketChannel) selectionKey.attachment();
            try {
                if (((SocketChannel) nioClientSocketChannel.channel).finishConnect()) {
                    selectionKey.cancel();
                    nioClientSocketChannel.worker.register(nioClientSocketChannel, nioClientSocketChannel.connectFuture);
                }
            } catch (Throwable th) {
                nioClientSocketChannel.connectFuture.setFailure(th);
                Channels.fireExceptionCaught(nioClientSocketChannel, th);
                selectionKey.cancel();
                nioClientSocketChannel.worker.close(nioClientSocketChannel, Channels.succeededFuture(nioClientSocketChannel));
            }
        }

        private void close(SelectionKey selectionKey) {
            NioClientSocketChannel nioClientSocketChannel = (NioClientSocketChannel) selectionKey.attachment();
            nioClientSocketChannel.worker.close(nioClientSocketChannel, Channels.succeededFuture(nioClientSocketChannel));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class RegisterTask implements Runnable {
        private final Boss boss;
        private final NioClientSocketChannel channel;

        RegisterTask(Boss boss, NioClientSocketChannel nioClientSocketChannel) {
            this.boss = boss;
            this.channel = nioClientSocketChannel;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ((SocketChannel) this.channel.channel).register(this.boss.selector, 8, this.channel);
            } catch (ClosedChannelException unused) {
                AbstractNioWorker abstractNioWorker = this.channel.worker;
                NioClientSocketChannel nioClientSocketChannel = this.channel;
                abstractNioWorker.close(nioClientSocketChannel, Channels.succeededFuture(nioClientSocketChannel));
            }
            int connectTimeoutMillis = this.channel.getConfig().getConnectTimeoutMillis();
            if (connectTimeoutMillis > 0) {
                this.channel.connectDeadlineNanos = System.nanoTime() + (connectTimeoutMillis * 1000000);
            }
        }
    }
}
