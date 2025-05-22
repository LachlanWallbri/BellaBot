package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.ThreadRenamingRunnable;
import org.jboss.netty.util.internal.DeadLockProofWorker;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class NioServerSocketPipelineSink extends AbstractNioChannelSink {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* renamed from: id */
    final int f10023id = nextId.incrementAndGet();
    private final WorkerPool<NioWorker> workerPool;
    private static final AtomicInteger nextId = new AtomicInteger();
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioServerSocketPipelineSink.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioServerSocketPipelineSink(WorkerPool<NioWorker> workerPool) {
        this.workerPool = workerPool;
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        Channel channel = channelEvent.getChannel();
        if (channel instanceof NioServerSocketChannel) {
            handleServerSocket(channelEvent);
        } else if (channel instanceof NioSocketChannel) {
            handleAcceptedSocket(channelEvent);
        }
    }

    private void handleServerSocket(ChannelEvent channelEvent) {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            NioServerSocketChannel nioServerSocketChannel = (NioServerSocketChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86971.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    close(nioServerSocketChannel, future);
                }
            } else {
                if (i != 2) {
                    return;
                }
                if (value != null) {
                    bind(nioServerSocketChannel, future, (SocketAddress) value);
                } else {
                    close(nioServerSocketChannel, future);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.channel.socket.nio.NioServerSocketPipelineSink$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C86971 {
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

    private static void handleAcceptedSocket(ChannelEvent channelEvent) {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            NioSocketChannel nioSocketChannel = (NioSocketChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86971.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    nioSocketChannel.worker.close(nioSocketChannel, future);
                    return;
                }
                return;
            } else if (i == 2 || i == 3) {
                if (value == null) {
                    nioSocketChannel.worker.close(nioSocketChannel, future);
                    return;
                }
                return;
            } else {
                if (i != 4) {
                    return;
                }
                nioSocketChannel.worker.setInterestOps(nioSocketChannel, future, ((Integer) value).intValue());
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            MessageEvent messageEvent = (MessageEvent) channelEvent;
            NioSocketChannel nioSocketChannel2 = (NioSocketChannel) messageEvent.getChannel();
            nioSocketChannel2.writeBufferQueue.offer(messageEvent);
            nioSocketChannel2.worker.writeFromUserCode(nioSocketChannel2);
        }
    }

    private void bind(NioServerSocketChannel nioServerSocketChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        boolean z = false;
        try {
            nioServerSocketChannel.socket.socket().bind(socketAddress, nioServerSocketChannel.getConfig().getBacklog());
            z = true;
            channelFuture.setSuccess();
            Channels.fireChannelBound(nioServerSocketChannel, nioServerSocketChannel.getLocalAddress());
            DeadLockProofWorker.start(((NioServerSocketChannelFactory) nioServerSocketChannel.getFactory()).bossExecutor, new ThreadRenamingRunnable(new Boss(nioServerSocketChannel), "New I/O server boss #" + this.f10023id + " (" + nioServerSocketChannel + ')'));
        } catch (Throwable th) {
            try {
                channelFuture.setFailure(th);
                Channels.fireExceptionCaught(nioServerSocketChannel, th);
            } finally {
                if (z) {
                    close(nioServerSocketChannel, channelFuture);
                }
            }
        }
    }

    private static void close(NioServerSocketChannel nioServerSocketChannel, ChannelFuture channelFuture) {
        boolean isBound = nioServerSocketChannel.isBound();
        try {
            if (nioServerSocketChannel.socket.isOpen()) {
                nioServerSocketChannel.socket.close();
                Selector selector = nioServerSocketChannel.selector;
                if (selector != null) {
                    selector.wakeup();
                }
            }
            nioServerSocketChannel.shutdownLock.lock();
            try {
                if (nioServerSocketChannel.setClosed()) {
                    channelFuture.setSuccess();
                    if (isBound) {
                        Channels.fireChannelUnbound(nioServerSocketChannel);
                    }
                    Channels.fireChannelClosed(nioServerSocketChannel);
                } else {
                    channelFuture.setSuccess();
                }
                nioServerSocketChannel.shutdownLock.unlock();
            } catch (Throwable th) {
                nioServerSocketChannel.shutdownLock.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            channelFuture.setFailure(th2);
            Channels.fireExceptionCaught(nioServerSocketChannel, th2);
        }
    }

    NioWorker nextWorker() {
        return this.workerPool.nextWorker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class Boss implements Runnable {
        private final NioServerSocketChannel channel;
        private final Selector selector = Selector.open();

        Boss(NioServerSocketChannel nioServerSocketChannel) throws IOException {
            this.channel = nioServerSocketChannel;
            try {
                nioServerSocketChannel.socket.register(this.selector, 16);
                nioServerSocketChannel.selector = this.selector;
            } catch (Throwable th) {
                closeSelector();
                throw th;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread currentThread = Thread.currentThread();
            this.channel.shutdownLock.lock();
            while (true) {
                try {
                    if (this.selector.select(1000L) > 0) {
                        this.selector.selectedKeys().clear();
                    }
                    while (true) {
                        SocketChannel accept = this.channel.socket.accept();
                        if (accept == null) {
                            break;
                        } else {
                            registerAcceptedChannel(accept, currentThread);
                        }
                    }
                } catch (InterruptedException | SocketTimeoutException | CancelledKeyException | ClosedSelectorException unused) {
                } catch (ClosedChannelException unused2) {
                    return;
                } catch (Throwable th) {
                    try {
                        if (NioServerSocketPipelineSink.logger.isWarnEnabled()) {
                            NioServerSocketPipelineSink.logger.warn("Failed to accept a connection.", th);
                        }
                        Thread.sleep(1000L);
                    } finally {
                        this.channel.shutdownLock.unlock();
                        closeSelector();
                    }
                }
            }
        }

        private void registerAcceptedChannel(SocketChannel socketChannel, Thread thread) {
            try {
                ChannelPipeline pipeline = this.channel.getConfig().getPipelineFactory().getPipeline();
                NioWorker nextWorker = NioServerSocketPipelineSink.this.nextWorker();
                nextWorker.register(new NioAcceptedSocketChannel(this.channel.getFactory(), pipeline, this.channel, NioServerSocketPipelineSink.this, socketChannel, nextWorker, thread), null);
            } catch (Exception e) {
                if (NioServerSocketPipelineSink.logger.isWarnEnabled()) {
                    NioServerSocketPipelineSink.logger.warn("Failed to initialize an accepted socket.", e);
                }
                try {
                    socketChannel.close();
                } catch (IOException e2) {
                    if (NioServerSocketPipelineSink.logger.isWarnEnabled()) {
                        NioServerSocketPipelineSink.logger.warn("Failed to close a partially accepted socket.", e2);
                    }
                }
            }
        }

        private void closeSelector() {
            this.channel.selector = null;
            try {
                this.selector.close();
            } catch (Exception e) {
                if (NioServerSocketPipelineSink.logger.isWarnEnabled()) {
                    NioServerSocketPipelineSink.logger.warn("Failed to close a selector.", e);
                }
            }
        }
    }
}
